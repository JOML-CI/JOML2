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
 * parameter is a typed {@link java.nio.DoubleBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Double3x4Ops} and its sibling kernel units. Not public API.
 */
public final class Double3x4OpsKernelsTypedBuffer {
    private Double3x4OpsKernelsTypedBuffer() {}

    public static java.nio.DoubleBuffer getColumn_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int col) {
        Double3x4OpsKernelsAddress.getColumn_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, col);
        return dest;
    }

    public static java.nio.DoubleBuffer getColumn_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int col) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.getColumn(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, col);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.getColumn_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L, col);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.getColumn_apiGet(dest, destOffset, src, srcOffset, col);
        return dest;
    }

    public static java.nio.DoubleBuffer getColumn_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int col) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset, _idxSw0);
        dest.put(destOffset + 1, _idxSw1);
        dest.put(destOffset + 2, _idxSw2);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesXYZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        Double3x4OpsKernelsAddress.getEulerAnglesXYZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesXYZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.getEulerAnglesXYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.getEulerAnglesXYZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.getEulerAnglesXYZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesXYZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _t1 = Math.fma(_self12, _self12, _self22 * _self22);
        if (_t1 < Math.fma(_self02, _self02, _t1) * 1.0E-15) {
            dest.put(destOffset, Math.atan2(_self21, _self11));
            dest.put(destOffset + 2, 0.0);
        } else {
            dest.put(destOffset, Math.atan2(-_self12, _self22));
            dest.put(destOffset + 2, Math.atan2(-_self01, _self00));
        }
        dest.put(destOffset + 1, Math.atan2(_self02, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesXZY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        Double3x4OpsKernelsAddress.getEulerAnglesXZY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesXZY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.getEulerAnglesXZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.getEulerAnglesXZY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.getEulerAnglesXZY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesXZY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _t1 = Math.fma(_self11, _self11, _self21 * _self21);
        if (_t1 < Math.fma(_self01, _self01, _t1) * 1.0E-15) {
            dest.put(destOffset, Math.atan2(-_self12, _self22));
            dest.put(destOffset + 1, 0.0);
        } else {
            dest.put(destOffset, Math.atan2(_self21, _self11));
            dest.put(destOffset + 1, Math.atan2(_self02, _self00));
        }
        dest.put(destOffset + 2, Math.atan2(-_self01, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesYXZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        Double3x4OpsKernelsAddress.getEulerAnglesYXZ_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesYXZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.getEulerAnglesYXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.getEulerAnglesYXZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.getEulerAnglesYXZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesYXZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self02 = src.get(srcOffset + 2);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self20 = src.get(srcOffset + 8);
        double _self22 = src.get(srcOffset + 10);
        double _t1 = Math.fma(_self02, _self02, _self22 * _self22);
        if (_t1 < Math.fma(_self12, _self12, _t1) * 1.0E-15) {
            dest.put(destOffset + 1, Math.atan2(-_self20, _self00));
            dest.put(destOffset + 2, 0.0);
        } else {
            dest.put(destOffset + 1, Math.atan2(_self02, _self22));
            dest.put(destOffset + 2, Math.atan2(_self10, _self11));
        }
        dest.put(destOffset, Math.atan2(-_self12, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesYZX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        Double3x4OpsKernelsAddress.getEulerAnglesYZX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesYZX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.getEulerAnglesYZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.getEulerAnglesYZX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.getEulerAnglesYZX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesYZX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self02 = src.get(srcOffset + 2);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self20 = src.get(srcOffset + 8);
        double _self22 = src.get(srcOffset + 10);
        double _t1 = Math.fma(_self11, _self11, _self12 * _self12);
        if (_t1 < Math.fma(_self10, _self10, _t1) * 1.0E-15) {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, Math.atan2(_self02, _self22));
        } else {
            dest.put(destOffset, Math.atan2(-_self12, _self11));
            dest.put(destOffset + 1, Math.atan2(-_self20, _self00));
        }
        dest.put(destOffset + 2, Math.atan2(_self10, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesZXY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        Double3x4OpsKernelsAddress.getEulerAnglesZXY_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesZXY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.getEulerAnglesZXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.getEulerAnglesZXY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.getEulerAnglesZXY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesZXY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _t1 = Math.fma(_self01, _self01, _self11 * _self11);
        if (_t1 < Math.fma(_self21, _self21, _t1) * 1.0E-15) {
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, Math.atan2(_self10, _self00));
        } else {
            dest.put(destOffset + 1, Math.atan2(-_self20, _self22));
            dest.put(destOffset + 2, Math.atan2(-_self01, _self11));
        }
        dest.put(destOffset, Math.atan2(_self21, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesZYX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        Double3x4OpsKernelsAddress.getEulerAnglesZYX_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesZYX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.getEulerAnglesZYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.getEulerAnglesZYX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.getEulerAnglesZYX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getEulerAnglesZYX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _t1 = Math.fma(_self21, _self21, _self22 * _self22);
        if (_t1 < Math.fma(_self20, _self20, _t1) * 1.0E-15) {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 2, Math.atan2(-_self01, _self11));
        } else {
            dest.put(destOffset, Math.atan2(_self21, _self22));
            dest.put(destOffset + 2, Math.atan2(_self10, _self00));
        }
        dest.put(destOffset + 1, Math.atan2(-_self20, java.lang.Math.sqrt(_t1)));
        return dest;
    }

    public static java.nio.DoubleBuffer getNormalizedRotation_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        Double3x4OpsKernelsAddress.getNormalizedRotation_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L);
        return dest;
    }

    public static java.nio.DoubleBuffer getNormalizedRotation_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.getNormalizedRotation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.getNormalizedRotation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.getNormalizedRotation_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getNormalizedRotation_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
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
        return getNormalizedRotation_apiGet_s7388f4bd_1(dest, destOffset, _self00, _self10, _self20, _t8, (1.0 / java.lang.Math.sqrt(_t8)), _t21, _t23, _t27, _t22, _t24, _t26);
    }

    /** Piece 2 of {@code getNormalizedRotation_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer getNormalizedRotation_apiGet_s7388f4bd_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self10, double _self20, double _t8, double _t11, double _t21, double _t23, double _t27, double _t22, double _t24, double _t26) {
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
        return getNormalizedRotation_apiGet_s7388f4bd_2(dest, destOffset, _t23, _t26, _t27 - _t22, _t27 + _t22, _t49, _t50 + _t21, _t51 + _t24, _t24 - _t51, _t50 - _t21, _t58, _t62, _t63, _t64, _t65, 0.5 * (1.0 / java.lang.Math.sqrt(_t62)), 0.5 * (1.0 / java.lang.Math.sqrt(_t64)), 0.5 * (1.0 / java.lang.Math.sqrt(_t65)), 0.5 * (1.0 / java.lang.Math.sqrt(_t63)));
    }

    /** Piece 3 of {@code getNormalizedRotation_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer getNormalizedRotation_apiGet_s7388f4bd_2(java.nio.DoubleBuffer dest, int destOffset, double _t23, double _t26, double _t36, double _t39, double _t49, double _t53, double _t55, double _t56, double _t57, double _t58, double _t62, double _t63, double _t64, double _t65, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t58 > 0.0) {
            dest.put(destOffset, _sp0 * _t36);
            dest.put(destOffset + 1, _sp0 * _t56);
            dest.put(destOffset + 2, _sp0 * _t57);
            dest.put(destOffset + 3, 0.5 * java.lang.Math.sqrt(_t62));
        } else {
            if (_t49 > java.lang.Math.max(_t23, _t26)) {
                dest.put(destOffset, 0.5 * java.lang.Math.sqrt(_t63));
                dest.put(destOffset + 1, _sp3 * _t53);
                dest.put(destOffset + 2, _sp3 * _t55);
                dest.put(destOffset + 3, _sp3 * _t36);
            } else {
                if (_t23 > _t26) {
                    dest.put(destOffset, _sp1 * _t53);
                    dest.put(destOffset + 1, 0.5 * java.lang.Math.sqrt(_t64));
                    dest.put(destOffset + 2, _sp1 * _t39);
                    dest.put(destOffset + 3, _sp1 * _t56);
                } else {
                    dest.put(destOffset, _sp2 * _t55);
                    dest.put(destOffset + 1, _sp2 * _t39);
                    dest.put(destOffset + 2, 0.5 * java.lang.Math.sqrt(_t65));
                    dest.put(destOffset + 3, _sp2 * _t57);
                }
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer getRow_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int row) {
        Double3x4OpsKernelsAddress.getRow_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, row);
        return dest;
    }

    public static java.nio.DoubleBuffer getRow_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int row) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.getRow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, row);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.getRow_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L, row);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.getRow_apiGet(dest, destOffset, src, srcOffset, row);
        return dest;
    }

    public static java.nio.DoubleBuffer getRow_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int row) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset, _idxSw0);
        dest.put(destOffset + 1, _idxSw1);
        dest.put(destOffset + 2, _idxSw2);
        dest.put(destOffset + 3, _idxSw3);
        return dest;
    }

    public static java.nio.DoubleBuffer getScale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        Double3x4OpsKernelsAddress.getScale_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L);
        return dest;
    }

    public static java.nio.DoubleBuffer getScale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.getScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.getScale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.getScale_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getScale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        dest.put(destOffset, java.lang.Math.sqrt(Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10))));
        dest.put(destOffset + 1, java.lang.Math.sqrt(Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11))));
        dest.put(destOffset + 2, java.lang.Math.sqrt(Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12))));
        return dest;
    }

    public static java.nio.DoubleBuffer getTranslation_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        Double3x4OpsKernelsAddress.getTranslation_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L);
        return dest;
    }

    public static java.nio.DoubleBuffer getTranslation_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.getTranslation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.getTranslation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.getTranslation_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getTranslation_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self13 = src.get(srcOffset + 7);
        double _self23 = src.get(srcOffset + 11);
        dest.put(destOffset, src.get(srcOffset + 3));
        dest.put(destOffset + 1, _self13);
        dest.put(destOffset + 2, _self23);
        return dest;
    }

    public static java.nio.DoubleBuffer getUnnormalizedRotation_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        Double3x4OpsKernelsAddress.getUnnormalizedRotation_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L);
        return dest;
    }

    public static java.nio.DoubleBuffer getUnnormalizedRotation_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.getUnnormalizedRotation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.getUnnormalizedRotation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.getUnnormalizedRotation_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer getUnnormalizedRotation_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _t0 = _self00 + _self11;
        double _t10 = _self22 + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (_self00 - (_self11 + _self22));
        double _t16 = 1.0 + (_self11 - (_self00 + _self22));
        double _t17 = 1.0 + (_self22 - _t0);
        return getUnnormalizedRotation_apiGet_s26b71fa_1(dest, destOffset, _self00, _self11, _self22, _self21 - _self12, _self01 + _self10, _self02 + _self20, _self02 - _self20, _self12 + _self21, _self10 - _self01, _t10, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code getUnnormalizedRotation_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer getUnnormalizedRotation_apiGet_s26b71fa_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self11, double _self22, double _t1, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t10 > 0.0) {
            dest.put(destOffset, _sp0 * _t1);
            dest.put(destOffset + 1, _sp0 * _t7);
            dest.put(destOffset + 2, _sp0 * _t9);
            dest.put(destOffset + 3, 0.5 * java.lang.Math.sqrt(_t14));
        } else {
            if (_self00 > java.lang.Math.max(_self11, _self22)) {
                dest.put(destOffset, 0.5 * java.lang.Math.sqrt(_t15));
                dest.put(destOffset + 1, _sp3 * _t4);
                dest.put(destOffset + 2, _sp3 * _t6);
                dest.put(destOffset + 3, _sp3 * _t1);
            } else {
                if (_self11 > _self22) {
                    dest.put(destOffset, _sp1 * _t4);
                    dest.put(destOffset + 1, 0.5 * java.lang.Math.sqrt(_t16));
                    dest.put(destOffset + 2, _sp1 * _t8);
                    dest.put(destOffset + 3, _sp1 * _t7);
                } else {
                    dest.put(destOffset, _sp2 * _t6);
                    dest.put(destOffset + 1, _sp2 * _t8);
                    dest.put(destOffset + 2, 0.5 * java.lang.Math.sqrt(_t17));
                    dest.put(destOffset + 3, _sp2 * _t9);
                }
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.invNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.invNegativeX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invNegativeX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invNegativeX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        double _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        double _t8 = Math.fma(_self12, _self20, -(_self10 * _self22));
        double _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsTypedBuffer.invNegativeX_degenerate(dest, destOffset, src, srcOffset);
        double _t13 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.put(destOffset, -(_t6 * _t13));
        dest.put(destOffset + 1, -(_t8 * _t13));
        dest.put(destOffset + 2, -(_t7 * _t13));
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeX_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsTypedBuffer.invNegativeX_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x4OpsKernelsTypedBuffer.invNegativeX_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.DoubleBuffer invNegativeX_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.invNegativeX_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeX_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4OpsKernelsArray.invNegativeX_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invNegativeX_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invNegativeX_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeX_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
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
            dest.put(destOffset, -(_t21 * _t26));
            dest.put(destOffset + 1, -(_t22 * _t26));
            dest.put(destOffset + 2, -(_t20 * _t26));
        } else {
            dest.put(destOffset, -0.0);
            dest.put(destOffset + 1, -0.0);
            dest.put(destOffset + 2, -0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.invNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.invNegativeY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invNegativeY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invNegativeY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _t6 = Math.fma(_self02, _self21, -(_self01 * _self22));
        double _t7 = Math.fma(_self01, _self20, -(_self00 * _self21));
        double _t8 = Math.fma(_self00, _self22, -(_self02 * _self20));
        double _ct0 = Math.fma(_t7, _t7, Math.fma(_t8, _t8, _t6 * _t6));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsTypedBuffer.invNegativeY_degenerate(dest, destOffset, src, srcOffset);
        double _t13 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.put(destOffset, -(_t6 * _t13));
        dest.put(destOffset + 1, -(_t8 * _t13));
        dest.put(destOffset + 2, -(_t7 * _t13));
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeY_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsTypedBuffer.invNegativeY_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x4OpsKernelsTypedBuffer.invNegativeY_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.DoubleBuffer invNegativeY_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.invNegativeY_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeY_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4OpsKernelsArray.invNegativeY_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invNegativeY_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invNegativeY_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeY_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
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
            dest.put(destOffset, -(_t22 * _t26));
            dest.put(destOffset + 1, -(_t21 * _t26));
            dest.put(destOffset + 2, -(_t20 * _t26));
        } else {
            dest.put(destOffset, -0.0);
            dest.put(destOffset + 1, -0.0);
            dest.put(destOffset + 2, -0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.invNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.invNegativeZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invNegativeZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invNegativeZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _t6 = Math.fma(_self01, _self12, -(_self02 * _self11));
        double _t7 = Math.fma(_self00, _self11, -(_self01 * _self10));
        double _t8 = Math.fma(_self02, _self10, -(_self00 * _self12));
        double _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsTypedBuffer.invNegativeZ_degenerate(dest, destOffset, src, srcOffset);
        double _t13 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.put(destOffset, -(_t6 * _t13));
        dest.put(destOffset + 1, -(_t8 * _t13));
        dest.put(destOffset + 2, -(_t7 * _t13));
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeZ_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsTypedBuffer.invNegativeZ_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x4OpsKernelsTypedBuffer.invNegativeZ_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.DoubleBuffer invNegativeZ_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.invNegativeZ_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeZ_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4OpsKernelsArray.invNegativeZ_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invNegativeZ_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invNegativeZ_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNegativeZ_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
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
            dest.put(destOffset, -(_t21 * _t26));
            dest.put(destOffset + 1, -(_t22 * _t26));
            dest.put(destOffset + 2, -(_t20 * _t26));
        } else {
            dest.put(destOffset, -0.0);
            dest.put(destOffset + 1, -0.0);
            dest.put(destOffset + 2, -0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedNegativeX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.invNormalizedNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedNegativeX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.invNormalizedNegativeX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invNormalizedNegativeX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invNormalizedNegativeX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedNegativeX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        dest.put(destOffset, -src.get(srcOffset));
        dest.put(destOffset + 1, -_self01);
        dest.put(destOffset + 2, -_self02);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedNegativeY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.invNormalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedNegativeY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.invNormalizedNegativeY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invNormalizedNegativeY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invNormalizedNegativeY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedNegativeY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        dest.put(destOffset, -src.get(srcOffset + 4));
        dest.put(destOffset + 1, -_self11);
        dest.put(destOffset + 2, -_self12);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedNegativeZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.invNormalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedNegativeZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.invNormalizedNegativeZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invNormalizedNegativeZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invNormalizedNegativeZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedNegativeZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        dest.put(destOffset, -src.get(srcOffset + 8));
        dest.put(destOffset + 1, -_self21);
        dest.put(destOffset + 2, -_self22);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedPositiveX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.invNormalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedPositiveX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.invNormalizedPositiveX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invNormalizedPositiveX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invNormalizedPositiveX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedPositiveX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, _self01);
        dest.put(destOffset + 2, _self02);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedPositiveY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.invNormalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedPositiveY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.invNormalizedPositiveY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invNormalizedPositiveY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invNormalizedPositiveY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedPositiveY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        dest.put(destOffset, src.get(srcOffset + 4));
        dest.put(destOffset + 1, _self11);
        dest.put(destOffset + 2, _self12);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedPositiveZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.invNormalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedPositiveZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.invNormalizedPositiveZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invNormalizedPositiveZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invNormalizedPositiveZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invNormalizedPositiveZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        dest.put(destOffset, src.get(srcOffset + 8));
        dest.put(destOffset + 1, _self21);
        dest.put(destOffset + 2, _self22);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.invPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.invPositiveX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invPositiveX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invPositiveX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        double _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        double _t8 = Math.fma(_self12, _self20, -(_self10 * _self22));
        double _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsTypedBuffer.invPositiveX_degenerate(dest, destOffset, src, srcOffset);
        double _t13 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.put(destOffset, _t6 * _t13);
        dest.put(destOffset + 1, _t8 * _t13);
        dest.put(destOffset + 2, _t7 * _t13);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveX_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsTypedBuffer.invPositiveX_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x4OpsKernelsTypedBuffer.invPositiveX_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.DoubleBuffer invPositiveX_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.invPositiveX_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveX_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4OpsKernelsArray.invPositiveX_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invPositiveX_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invPositiveX_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveX_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
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
            dest.put(destOffset, _t21 * _t26);
            dest.put(destOffset + 1, _t22 * _t26);
            dest.put(destOffset + 2, _t20 * _t26);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.invPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.invPositiveY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invPositiveY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invPositiveY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _t6 = Math.fma(_self02, _self21, -(_self01 * _self22));
        double _t7 = Math.fma(_self01, _self20, -(_self00 * _self21));
        double _t8 = Math.fma(_self00, _self22, -(_self02 * _self20));
        double _ct0 = Math.fma(_t7, _t7, Math.fma(_t8, _t8, _t6 * _t6));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsTypedBuffer.invPositiveY_degenerate(dest, destOffset, src, srcOffset);
        double _t13 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.put(destOffset, _t6 * _t13);
        dest.put(destOffset + 1, _t8 * _t13);
        dest.put(destOffset + 2, _t7 * _t13);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveY_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsTypedBuffer.invPositiveY_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x4OpsKernelsTypedBuffer.invPositiveY_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.DoubleBuffer invPositiveY_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.invPositiveY_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveY_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4OpsKernelsArray.invPositiveY_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invPositiveY_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invPositiveY_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveY_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
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
            dest.put(destOffset, _t22 * _t26);
            dest.put(destOffset + 1, _t21 * _t26);
            dest.put(destOffset + 2, _t20 * _t26);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.invPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.invPositiveZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invPositiveZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invPositiveZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _t6 = Math.fma(_self01, _self12, -(_self02 * _self11));
        double _t7 = Math.fma(_self00, _self11, -(_self01 * _self10));
        double _t8 = Math.fma(_self02, _self10, -(_self00 * _self12));
        double _ct0 = Math.fma(_t7, _t7, Math.fma(_t6, _t6, _t8 * _t8));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsTypedBuffer.invPositiveZ_degenerate(dest, destOffset, src, srcOffset);
        double _t13 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest.put(destOffset, _t6 * _t13);
        dest.put(destOffset + 1, _t8 * _t13);
        dest.put(destOffset + 2, _t7 * _t13);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveZ_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsTypedBuffer.invPositiveZ_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x4OpsKernelsTypedBuffer.invPositiveZ_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.DoubleBuffer invPositiveZ_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.invPositiveZ_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveZ_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4OpsKernelsArray.invPositiveZ_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invPositiveZ_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invPositiveZ_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invPositiveZ_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
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
            dest.put(destOffset, _t21 * _t26);
            dest.put(destOffset + 1, _t22 * _t26);
            dest.put(destOffset + 2, _t20 * _t26);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer negativeX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.negativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer negativeX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.negativeX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.negativeX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.negativeX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer negativeX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 4);
        double _self20 = src.get(srcOffset + 8);
        double _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            dest.put(destOffset, -(_self00 * _t3));
            dest.put(destOffset + 1, -(_self10 * _t3));
            dest.put(destOffset + 2, -(_self20 * _t3));
        } else {
            dest.put(destOffset, -0.0);
            dest.put(destOffset + 1, -0.0);
            dest.put(destOffset + 2, -0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer negativeY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.negativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer negativeY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.negativeY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.negativeY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.negativeY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer negativeY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self01 = src.get(srcOffset + 1);
        double _self11 = src.get(srcOffset + 5);
        double _self21 = src.get(srcOffset + 9);
        double _t2 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            dest.put(destOffset, -(_self01 * _t3));
            dest.put(destOffset + 1, -(_self11 * _t3));
            dest.put(destOffset + 2, -(_self21 * _t3));
        } else {
            dest.put(destOffset, -0.0);
            dest.put(destOffset + 1, -0.0);
            dest.put(destOffset + 2, -0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer negativeZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.negativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer negativeZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.negativeZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.negativeZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.negativeZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer negativeZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self02 = src.get(srcOffset + 2);
        double _self12 = src.get(srcOffset + 6);
        double _self22 = src.get(srcOffset + 10);
        double _t2 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            dest.put(destOffset, -(_self02 * _t3));
            dest.put(destOffset + 1, -(_self12 * _t3));
            dest.put(destOffset + 2, -(_self22 * _t3));
        } else {
            dest.put(destOffset, -0.0);
            dest.put(destOffset + 1, -0.0);
            dest.put(destOffset + 2, -0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedNegativeX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.normalizedNegativeX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedNegativeX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.normalizedNegativeX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.normalizedNegativeX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.normalizedNegativeX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedNegativeX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self10 = src.get(srcOffset + 4);
        double _self20 = src.get(srcOffset + 8);
        dest.put(destOffset, -src.get(srcOffset));
        dest.put(destOffset + 1, -_self10);
        dest.put(destOffset + 2, -_self20);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedNegativeY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.normalizedNegativeY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedNegativeY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.normalizedNegativeY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.normalizedNegativeY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.normalizedNegativeY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedNegativeY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self11 = src.get(srcOffset + 5);
        double _self21 = src.get(srcOffset + 9);
        dest.put(destOffset, -src.get(srcOffset + 1));
        dest.put(destOffset + 1, -_self11);
        dest.put(destOffset + 2, -_self21);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedNegativeZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.normalizedNegativeZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedNegativeZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.normalizedNegativeZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.normalizedNegativeZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.normalizedNegativeZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedNegativeZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self12 = src.get(srcOffset + 6);
        double _self22 = src.get(srcOffset + 10);
        dest.put(destOffset, -src.get(srcOffset + 2));
        dest.put(destOffset + 1, -_self12);
        dest.put(destOffset + 2, -_self22);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedPositiveX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.normalizedPositiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedPositiveX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.normalizedPositiveX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.normalizedPositiveX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.normalizedPositiveX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedPositiveX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self10 = src.get(srcOffset + 4);
        double _self20 = src.get(srcOffset + 8);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self20);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedPositiveY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.normalizedPositiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedPositiveY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.normalizedPositiveY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.normalizedPositiveY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.normalizedPositiveY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedPositiveY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self11 = src.get(srcOffset + 5);
        double _self21 = src.get(srcOffset + 9);
        dest.put(destOffset, src.get(srcOffset + 1));
        dest.put(destOffset + 1, _self11);
        dest.put(destOffset + 2, _self21);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedPositiveZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.normalizedPositiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedPositiveZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.normalizedPositiveZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.normalizedPositiveZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.normalizedPositiveZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer normalizedPositiveZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self12 = src.get(srcOffset + 6);
        double _self22 = src.get(srcOffset + 10);
        dest.put(destOffset, src.get(srcOffset + 2));
        dest.put(destOffset + 1, _self12);
        dest.put(destOffset + 2, _self22);
        return dest;
    }

    public static java.nio.DoubleBuffer origin_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.origin_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer origin_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.origin(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.origin_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.origin_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer origin_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        dest.put(destOffset, -Math.fma(src.get(srcOffset + 8), _self23, Math.fma(src.get(srcOffset), _self03, src.get(srcOffset + 4) * _self13)));
        dest.put(destOffset + 1, -Math.fma(_self21, _self23, Math.fma(_self01, _self03, _self11 * _self13)));
        dest.put(destOffset + 2, -Math.fma(_self22, _self23, Math.fma(_self02, _self03, _self12 * _self13)));
        return dest;
    }

    public static java.nio.DoubleBuffer positiveX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.positiveX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer positiveX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.positiveX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.positiveX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.positiveX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer positiveX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self10 = src.get(srcOffset + 4);
        double _self20 = src.get(srcOffset + 8);
        double _t2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            dest.put(destOffset, _self00 * _t3);
            dest.put(destOffset + 1, _self10 * _t3);
            dest.put(destOffset + 2, _self20 * _t3);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer positiveY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.positiveY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer positiveY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.positiveY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.positiveY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.positiveY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer positiveY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self01 = src.get(srcOffset + 1);
        double _self11 = src.get(srcOffset + 5);
        double _self21 = src.get(srcOffset + 9);
        double _t2 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            dest.put(destOffset, _self01 * _t3);
            dest.put(destOffset + 1, _self11 * _t3);
            dest.put(destOffset + 2, _self21 * _t3);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer positiveZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.positiveZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer positiveZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.positiveZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.positiveZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.positiveZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer positiveZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self02 = src.get(srcOffset + 2);
        double _self12 = src.get(srcOffset + 6);
        double _self22 = src.get(srcOffset + 10);
        double _t2 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            dest.put(destOffset, _self02 * _t3);
            dest.put(destOffset + 1, _self12 * _t3);
            dest.put(destOffset + 2, _self22 * _t3);
        } else {
            dest.put(destOffset, 0.0);
            dest.put(destOffset + 1, 0.0);
            dest.put(destOffset + 2, 0.0);
        }
        return dest;
    }

    public static double determinant_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double3x4OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static double determinant_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            return Double3x4Ops.determinant(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double3x4OpsKernelsSegment.determinant_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
        }
        return Double3x4OpsKernelsTypedBuffer.determinant_apiGet(src, srcOffset);
    }

    public static double determinant_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        return Math.fma(src.get(srcOffset + 2), Math.fma(_self10, _self21, -(_self11 * _self20)), Math.fma(src.get(srcOffset), Math.fma(_self11, _self22, -(_self12 * _self21)), -(src.get(srcOffset + 1) * Math.fma(_self10, _self22, -(_self12 * _self20)))));
    }

    public static double frobeniusNorm_unsafe(java.nio.DoubleBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        return Double3x4OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static double frobeniusNorm_api(java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            return Double3x4Ops.frobeniusNorm(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Double3x4OpsKernelsSegment.frobeniusNorm_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
        }
        return Double3x4OpsKernelsTypedBuffer.frobeniusNorm_apiGet(src, srcOffset);
    }

    public static double frobeniusNorm_apiGet(java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        return java.lang.Math.sqrt(Math.fma(_self00, _self00, Math.fma(_self01, _self01, _self02 * _self02)) + Math.fma(_self03, _self03, Math.fma(_self10, _self10, _self11 * _self11)) + (Math.fma(_self12, _self12, Math.fma(_self13, _self13, _self20 * _self20)) + Math.fma(_self21, _self21, Math.fma(_self22, _self22, _self23 * _self23))));
    }

    public static java.nio.DoubleBuffer invert_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invert_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.invert(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invert_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invert_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invert_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t20 = Math.fma(_self11, _self22, -(_self12 * _self21));
        double _t21 = Math.fma(_self10, _self21, -(_self11 * _self20));
        return invert_apiGet_sd61f3374_1(dest, destOffset, src, srcOffset, _self03, _self13, _self23, _t20, _t21, Math.fma(_self02, _self21, -(_self01 * _self22)), Math.fma(_self01, _self12, -(_self02 * _self11)), Math.fma(_self12, _self20, -(_self10 * _self22)), Math.fma(_self00, _self22, -(_self02 * _self20)), Math.fma(_self02, _self10, -(_self00 * _self12)), Math.fma(_self01, _self20, -(_self00 * _self21)), Math.fma(_self00, _self11, -(_self01 * _self10)), Math.fma(_self02, _t21, Math.fma(_self00, _t20, -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20))))));
    }

    /** Piece 2 of {@code invert_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer invert_apiGet_sd61f3374_1(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double _self03, double _self13, double _self23, double _t20, double _t21, double _t23, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t34) {
        if (!(java.lang.Math.abs(_t34) > 2.2250738585072014E-308 && java.lang.Math.abs(_t34) < 4.49423283715579E307)) return Double3x4OpsKernelsTypedBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        double _t34_inv = 1.0 / _t34;
        dest.put(destOffset, _t20 * _t34_inv);
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

    public static java.nio.DoubleBuffer invert_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsTypedBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x4OpsKernelsTypedBuffer.invert_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.DoubleBuffer invert_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invert_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4OpsKernelsArray.invert_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invert_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invert_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invert_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
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
        return invert_degenerate_apiGet_sc517efd7_1(dest, destOffset, _t0, _t1, _t2, _t15, _t16, _t17, _t18, _t19, _t20, _t21, _self00 * _t2, _t23, _self23 * _t1, _self13 * _t0, _self03 * _t2, Math.fma(_t15, _t16, -(_t17 * _t18)), Math.fma(_t19, _t18, -(_t15 * _t20)), Math.fma(_t21, _t18, -(_t23 * _t16)), Math.fma(_t23, _t17, -(_t21 * _t15)));
    }

    /** Piece 2 of {@code invert_degenerate_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer invert_degenerate_apiGet_sc517efd7_1(java.nio.DoubleBuffer dest, int destOffset, double _t0, double _t1, double _t2, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26, double _t47, double _t48, double _t50, double _t51) {
        double _t52 = Math.fma(_t17, _t20, -(_t19 * _t16));
        double _t53 = Math.fma(_t22, _t16, -(_t21 * _t20));
        double _t54 = Math.fma(_t21, _t19, -(_t22 * _t17));
        double _t60_inv = 1.0 / Math.fma(_t48, _t21, Math.fma(_t47, _t22, -(Math.fma(_t19, _t16, -(_t17 * _t20)) * _t23)));
        double _sp2 = _t1 * _t60_inv;
        double _sp1 = _t0 * _t60_inv;
        double _sp0 = _t2 * _t60_inv;
        dest.put(destOffset, _t47 * _sp0);
        dest.put(destOffset + 1, _t50 * _sp1);
        dest.put(destOffset + 2, _t51 * _sp2);
        dest.put(destOffset + 3, -(Math.fma(_t51, _t24, Math.fma(_t50, _t25, _t47 * _t26)) * _t60_inv));
        dest.put(destOffset + 4, _t52 * _sp0);
        dest.put(destOffset + 5, _t53 * _sp1);
        dest.put(destOffset + 6, _t54 * _sp2);
        dest.put(destOffset + 7, -(Math.fma(_t54, _t24, Math.fma(_t53, _t25, _t52 * _t26)) * _t60_inv));
        dest.put(destOffset + 8, _t48 * _sp0);
        return invert_degenerate_apiGet_sc517efd7_2(dest, destOffset, _t24, _t25, _t26, _t48, Math.fma(_t23, _t20, -(_t22 * _t18)), Math.fma(_t22, _t15, -(_t23 * _t19)), _t60_inv, _sp2, _sp1);
    }

    /** Piece 3 of {@code invert_degenerate_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer invert_degenerate_apiGet_sc517efd7_2(java.nio.DoubleBuffer dest, int destOffset, double _t24, double _t25, double _t26, double _t48, double _t55, double _t56, double _t60_inv, double _sp2, double _sp1) {
        dest.put(destOffset + 9, _t55 * _sp1);
        dest.put(destOffset + 10, _t56 * _sp2);
        dest.put(destOffset + 11, -(Math.fma(_t56, _t24, Math.fma(_t55, _t25, _t48 * _t26)) * _t60_inv));
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3x4OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 12) {
            Double3x4Ops.invertProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invertProduct_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invertProduct_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _other00 = other.get(otherOffset);
        double _other01 = other.get(otherOffset + 1);
        double _other02 = other.get(otherOffset + 2);
        double _other03 = other.get(otherOffset + 3);
        double _other10 = other.get(otherOffset + 4);
        double _other11 = other.get(otherOffset + 5);
        double _other12 = other.get(otherOffset + 6);
        double _other13 = other.get(otherOffset + 7);
        double _other20 = other.get(otherOffset + 8);
        double _other21 = other.get(otherOffset + 9);
        double _other22 = other.get(otherOffset + 10);
        double _other23 = other.get(otherOffset + 11);
        return invertProduct_apiGet_s865640f0_4(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self01, _self02, _self10, _self11, _self12, _self20, _self21, _self22, _other00, _other01, _other02, _other03, _other10, _other11, _other12, _other13, _other20, _other21, _other22, _other23, Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11)));
    }

    /** Part 1 of {@code invertProduct_apiGet}, split to fit the inline budget; reached only through it. */
    private static double invertProduct_apiGet_s865640f0_1(double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32) {
        return Math.fma(_t28, (Math.fma(_t29, _t26, -(_t30 * _t24))), Math.fma(_t31, (Math.fma(_t24, _t25, -(_t26 * _t27))), -(_t32 * Math.fma(_t29, _t25, -(_t30 * _t27)))));
    }

    /** Part 2 of {@code invertProduct_apiGet}, split to fit the inline budget; reached only through it. */
    private static void invertProduct_apiGet_s865640f0_2(java.nio.DoubleBuffer dest, int destOffset, double _t24, double _t25, double _t26, double _t27, double _t28, double _t32, double _t33, double _t34, double _t35, double _t70) {
        double _t56 = Math.fma(_t24, _t25, -(_t26 * _t27));
        double _t59 = Math.fma(_t26, _t28, -(_t32 * _t25));
        double _t60 = Math.fma(_t32, _t27, -(_t24 * _t28));
        double _t70_inv = 1.0 / _t70;
        dest.put(destOffset, _t56 * _t70_inv);
        dest.put(destOffset + 1, _t59 * _t70_inv);
        dest.put(destOffset + 2, _t60 * _t70_inv);
        dest.put(destOffset + 3, -(Math.fma(_t33, _t60, Math.fma(_t34, _t56, _t35 * _t59)) * _t70_inv));
    }

    /** Part 3 of {@code invertProduct_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer invertProduct_apiGet_s865640f0_3(java.nio.DoubleBuffer dest, int destOffset, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32, double _t33, double _t34, double _t35, double _t70) {
        double _t57 = Math.fma(_t29, _t26, -(_t30 * _t24));
        double _t61 = Math.fma(_t30, _t27, -(_t29 * _t25));
        double _t62 = Math.fma(_t31, _t25, -(_t30 * _t28));
        double _t63 = Math.fma(_t29, _t28, -(_t31 * _t27));
        double _t64 = Math.fma(_t30, _t32, -(_t31 * _t26));
        double _t65 = Math.fma(_t31, _t24, -(_t29 * _t32));
        double _t70_inv = 1.0 / _t70;
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

    /** Piece 2 of {@code invertProduct_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer invertProduct_apiGet_s865640f0_4(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double _self00, double _self01, double _self02, double _self10, double _self11, double _self12, double _self20, double _self21, double _self22, double _other00, double _other01, double _other02, double _other03, double _other10, double _other11, double _other12, double _other13, double _other20, double _other21, double _other22, double _other23, double _t24) {
        return invertProduct_apiGet_s865640f0_5(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self01, _self02, _self10, _self11, _self12, _self20, _self21, _self22, _other03, _other13, _other23, _t24, Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21)), Math.fma(_other21, _self22, Math.fma(_other01, _self20, _other11 * _self21)), Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11)), Math.fma(_other22, _self02, Math.fma(_other02, _self00, _other12 * _self01)), Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11)), Math.fma(_other20, _self22, Math.fma(_other00, _self20, _other10 * _self21)), Math.fma(_other20, _self02, Math.fma(_other00, _self00, _other10 * _self01)), Math.fma(_other21, _self02, Math.fma(_other01, _self00, _other11 * _self01)));
    }

    /** Piece 3 of {@code invertProduct_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer invertProduct_apiGet_s865640f0_5(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double _self00, double _self01, double _self02, double _self10, double _self11, double _self12, double _self20, double _self21, double _self22, double _other03, double _other13, double _other23, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30, double _t31, double _t32) {
        double _t33 = Math.fma(_other03, _self20, Math.fma(_other13, _self21, Math.fma(_other23, _self22, src.get(srcOffset + 11))));
        double _t34 = Math.fma(_other03, _self00, Math.fma(_other13, _self01, Math.fma(_other23, _self02, src.get(srcOffset + 3))));
        double _t35 = Math.fma(_other03, _self10, Math.fma(_other13, _self11, Math.fma(_other23, _self12, src.get(srcOffset + 7))));
        double _t70 = invertProduct_apiGet_s865640f0_1(_t24, _t25, _t26, _t27, _t28, _t29, _t30, _t31, _t32);
        if (!(java.lang.Math.abs(_t70) > 2.2250738585072014E-308 && java.lang.Math.abs(_t70) < 4.49423283715579E307)) return Double3x4OpsKernelsTypedBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        invertProduct_apiGet_s865640f0_2(dest, destOffset, _t24, _t25, _t26, _t27, _t28, _t32, _t33, _t34, _t35, _t70);
        return invertProduct_apiGet_s865640f0_3(dest, destOffset, _t24, _t25, _t26, _t27, _t28, _t29, _t30, _t31, _t32, _t33, _t34, _t35, _t70);
    }

    public static java.nio.DoubleBuffer invertProduct_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsTypedBuffer.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x4OpsKernelsTypedBuffer.invertProduct_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.nio.DoubleBuffer invertProduct_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3x4OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 12) {
            Double3x4OpsKernelsArray.invertProduct_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.invertProduct_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.invertProduct_degenerate_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer invertProduct_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _other00 = other.get(otherOffset);
        double _other01 = other.get(otherOffset + 1);
        double _other02 = other.get(otherOffset + 2);
        double _other03 = other.get(otherOffset + 3);
        double _other10 = other.get(otherOffset + 4);
        double _other11 = other.get(otherOffset + 5);
        double _other12 = other.get(otherOffset + 6);
        double _other13 = other.get(otherOffset + 7);
        double _other20 = other.get(otherOffset + 8);
        double _other21 = other.get(otherOffset + 9);
        double _other22 = other.get(otherOffset + 10);
        double _other23 = other.get(otherOffset + 11);
        return invertProduct_degenerate_apiGet_s8770bc71_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other00, _other01, _other02, _other03, _other10, _other11, _other12, _other13, _other20, _other21, _other22, _other23);
    }

    /** Piece 2 of {@code invertProduct_degenerate_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer invertProduct_degenerate_apiGet_s8770bc71_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other00, double _other01, double _other02, double _other03, double _other10, double _other11, double _other12, double _other13, double _other20, double _other21, double _other22, double _other23) {
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
        double _t38 = unitScale(_t30, _t31, _t32);
        return invertProduct_degenerate_apiGet_s8770bc71_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other03, _other13, _other23, _t36, _t37, _t38, _t24 * _t36, _t27 * _t37, _t29 * _t37, _t26 * _t36, _t25 * _t36, _t28 * _t37, _t32 * _t38, _t30 * _t38, _t31 * _t38);
    }

    /** Piece 3 of {@code invertProduct_degenerate_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer invertProduct_degenerate_apiGet_s8770bc71_2(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other03, double _other13, double _other23, double _t36, double _t37, double _t38, double _t48, double _t49, double _t50, double _t51, double _t52, double _t53, double _t54, double _t55, double _t56) {
        double _t83 = Math.fma(_t48, _t49, -(_t50 * _t51));
        double _t84 = Math.fma(_t52, _t50, -(_t53 * _t48));
        double _t86 = Math.fma(_t50, _t54, -(_t56 * _t49));
        double _t96_inv = 1.0 / Math.fma(_t84, _t54, Math.fma(_t83, _t55, -(Math.fma(_t52, _t49, -(_t53 * _t51)) * _t56)));
        double _sp1 = _t36 * _t96_inv;
        double _sp0 = _t38 * _t96_inv;
        dest.put(destOffset, _t83 * _sp0);
        dest.put(destOffset + 1, _t86 * _sp1);
        return invertProduct_degenerate_apiGet_s8770bc71_3(dest, destOffset, Math.fma(_other03, _self20, Math.fma(_other13, _self21, Math.fma(_other23, _self22, _self23))) * _t37, Math.fma(_other03, _self00, Math.fma(_other13, _self01, Math.fma(_other23, _self02, _self03))) * _t38, Math.fma(_other03, _self10, Math.fma(_other13, _self11, Math.fma(_other23, _self12, _self13))) * _t36, _t83, _t84, _t86, Math.fma(_t56, _t51, -(_t48 * _t54)), Math.fma(_t53, _t51, -(_t52 * _t49)), Math.fma(_t55, _t49, -(_t53 * _t54)), Math.fma(_t52, _t54, -(_t55 * _t51)), Math.fma(_t53, _t56, -(_t55 * _t50)), Math.fma(_t55, _t48, -(_t52 * _t56)), _t96_inv, _t37 * _t96_inv, _sp1, _sp0);
    }

    /** Piece 4 of {@code invertProduct_degenerate_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer invertProduct_degenerate_apiGet_s8770bc71_3(java.nio.DoubleBuffer dest, int destOffset, double _t60, double _t61, double _t62, double _t83, double _t84, double _t86, double _t87, double _t88, double _t89, double _t90, double _t91, double _t92, double _t96_inv, double _sp2, double _sp1, double _sp0) {
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

    public static java.nio.DoubleBuffer transpose_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer transpose_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.transpose(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.transpose_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer transpose_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _i = 0; _i < 12; _i++) {
            dest.put(destOffset + _i, src.get(srcOffset + _i));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer add_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3x4OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer add_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 12) {
            Double3x4Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer add_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        for (int _i = 0; _i < 12; _i++) {
            dest.put(destOffset + _i, other.get(otherOffset + _i) + src.get(srcOffset + _i));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        for (int _i = 0; _i < 12; _i++) {
            dest.put(destOffset + _i, scalar * src.get(srcOffset + _i));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer negate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer negate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.negate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.negate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer negate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _i = 0; _i < 12; _i++) {
            dest.put(destOffset + _i, -src.get(srcOffset + _i));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer sub_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3x4OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 12) {
            Double3x4Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer sub_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        for (int _i = 0; _i < 12; _i++) {
            dest.put(destOffset + _i, src.get(srcOffset + _i) - other.get(otherOffset + _i));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer set_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 8L;
        Double3x4OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer set_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 12) {
            Double3x4Ops.set(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer set_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        for (int _i = 0; _i < 12; _i++) {
            dest.put(destOffset + _i, v.get(vOffset + _i));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer setMat3x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 8L;
        Double3x4OpsKernelsAddress.setMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat3x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 9) {
            Double3x4Ops.setMat3x3(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && m.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.setMat3x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(m.duplicate().position(0)), mOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.setMat3x3_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat3x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        double _m10 = m.get(mOffset + 1);
        double _m20 = m.get(mOffset + 2);
        double _m01 = m.get(mOffset + 3);
        double _m11 = m.get(mOffset + 4);
        double _m21 = m.get(mOffset + 5);
        double _m02 = m.get(mOffset + 6);
        double _m12 = m.get(mOffset + 7);
        double _m22 = m.get(mOffset + 8);
        dest.put(destOffset, m.get(mOffset));
        dest.put(destOffset + 1, _m01);
        dest.put(destOffset + 2, _m02);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, _m10);
        dest.put(destOffset + 5, _m11);
        dest.put(destOffset + 6, _m12);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, _m20);
        dest.put(destOffset + 9, _m21);
        dest.put(destOffset + 10, _m22);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat4x4_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 8L;
        Double3x4OpsKernelsAddress.setMat4x4_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat4x4_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 16) {
            Double3x4Ops.setMat4x4(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && m.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.setMat4x4_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(m.duplicate().position(0)), mOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.setMat4x4_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer setMat4x4_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        double _m10 = m.get(mOffset + 1);
        double _m20 = m.get(mOffset + 2);
        double _m01 = m.get(mOffset + 4);
        double _m11 = m.get(mOffset + 5);
        double _m21 = m.get(mOffset + 6);
        double _m02 = m.get(mOffset + 8);
        double _m12 = m.get(mOffset + 9);
        double _m22 = m.get(mOffset + 10);
        double _m03 = m.get(mOffset + 12);
        double _m13 = m.get(mOffset + 13);
        double _m23 = m.get(mOffset + 14);
        dest.put(destOffset, m.get(mOffset));
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

    public static java.nio.DoubleBuffer withTranslation_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double tX, double tY, double tZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, tX, tY, tZ);
        return dest;
    }

    public static java.nio.DoubleBuffer withTranslation_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double tX, double tY, double tZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.withTranslation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, tX, tY, tZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.withTranslation_apiGet(dest, destOffset, src, srcOffset, tX, tY, tZ);
        return dest;
    }

    public static java.nio.DoubleBuffer withTranslation_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double tX, double tY, double tZ) {
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        dest.put(destOffset, src.get(srcOffset));
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

    public static java.nio.DoubleBuffer withTranslation_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + tOffset * 8L;
        Double3x4OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, _tBase);
        return dest;
    }

    public static java.nio.DoubleBuffer withTranslation_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t, int tOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && t.hasArray() && tOffset >= 0 && tOffset <= t.limit() - 3) {
            Double3x4Ops.withTranslation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t.array(), t.arrayOffset() + tOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.withTranslation_apiGet(dest, destOffset, src, srcOffset, t, tOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer withTranslation_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t, int tOffset) {
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _tx = t.get(tOffset);
        double _ty = t.get(tOffset + 1);
        double _tz = t.get(tOffset + 2);
        dest.put(destOffset, src.get(srcOffset));
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

    public static java.nio.DoubleBuffer makeFromRigid_unsafe(java.nio.DoubleBuffer dest, int destOffset, double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeFromRigid_unsafe(_destBase, rTX, rTY, rTZ, rRX, rRY, rRZ, rRW);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromRigid_api(java.nio.DoubleBuffer dest, int destOffset, double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeFromRigid(dest.array(), dest.arrayOffset() + destOffset, rTX, rTY, rTZ, rRX, rRY, rRZ, rRW);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeFromRigid_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, rTX, rTY, rTZ, rRX, rRY, rRZ, rRW);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeFromRigid_apiGet(dest, destOffset, rTX, rTY, rTZ, rRX, rRY, rRZ, rRW);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromRigid_apiGet(java.nio.DoubleBuffer dest, int destOffset, double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        double _t0 = rRZ * rRZ;
        double _t1 = rRZ * rRW;
        double _t2 = rRY * rRW;
        dest.put(destOffset, Math.fma(-2.0, Math.fma(rRY, rRY, _t0), 1.0));
        dest.put(destOffset + 1, 2.0 * Math.fma(rRX, rRY, -_t1));
        dest.put(destOffset + 2, 2.0 * Math.fma(rRX, rRZ, _t2));
        dest.put(destOffset + 3, rTX);
        dest.put(destOffset + 4, 2.0 * Math.fma(rRX, rRY, _t1));
        dest.put(destOffset + 5, Math.fma(-2.0, Math.fma(rRX, rRX, _t0), 1.0));
        dest.put(destOffset + 6, 2.0 * Math.fma(rRY, rRZ, -(rRX * rRW)));
        dest.put(destOffset + 7, rTY);
        dest.put(destOffset + 8, 2.0 * Math.fma(rRX, rRZ, -_t2));
        dest.put(destOffset + 9, 2.0 * Math.fma(rRX, rRW, rRY * rRZ));
        dest.put(destOffset + 10, Math.fma(-2.0, Math.fma(rRX, rRX, rRY * rRY), 1.0));
        dest.put(destOffset + 11, rTZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromTransform_unsafe(java.nio.DoubleBuffer dest, int destOffset, double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeFromTransform_unsafe(_destBase, tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromTransform_api(java.nio.DoubleBuffer dest, int destOffset, double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeFromTransform(dest.array(), dest.arrayOffset() + destOffset, tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeFromTransform_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeFromTransform_apiGet(dest, destOffset, tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromTransform_apiGet(java.nio.DoubleBuffer dest, int destOffset, double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        double _t0 = tSX + tSX;
        double _t1 = tSY + tSY;
        double _t2 = tSZ + tSZ;
        double _t3 = tRZ * tRZ;
        double _t4 = tRZ * tRW;
        double _t5 = tRY * tRW;
        dest.put(destOffset, Math.fma(-Math.fma(tRY, tRY, _t3), _t0, tSX));
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

    public static java.nio.DoubleBuffer to3x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.to3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer to3x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.to3x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.to3x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.to3x3_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer to3x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        dest.put(destOffset, src.get(srcOffset));
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

    public static java.nio.DoubleBuffer to4x4_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.to4x4_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer to4x4_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 16 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.to4x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.to4x4_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.to4x4_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer to4x4_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        dest.put(destOffset, src.get(srcOffset));
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
        dest.put(destOffset + 12, _self03);
        dest.put(destOffset + 13, _self13);
        dest.put(destOffset + 14, _self23);
        dest.put(destOffset + 15, 1.0);
        return dest;
    }

    public static java.nio.DoubleBuffer toDualQuat_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.toDualQuat_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer toDualQuat_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 8 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.toDualQuat(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.toDualQuat_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.toDualQuat_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer toDualQuat_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t2 = 1.0 - _self00;
        double _t14 = _self22 + (_self00 + _self11);
        double _t15 = 1.0 + _t14;
        double _t16 = _self00 + (1.0 - _self11 - _self22);
        double _t17 = _self11 + (_t2 - _self22);
        double _t18 = _self22 + (_t2 - _self11);
        return toDualQuat_apiGet_sa85bfa44_1(dest, destOffset, _self00, _self03, _self11, _self13, _self22, _self23, -_self23, _self21 - _self12, _self01 + _self10, _self02 + _self20, _self02 - _self20, _self12 + _self21, _self10 - _self01, _t14, _t15, _t16, _t17, _t18, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t18)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)));
    }

    /** Piece 2 of {@code toDualQuat_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer toDualQuat_apiGet_sa85bfa44_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self03, double _self11, double _self13, double _self22, double _self23, double _t0, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _t18, double _sp0, double _sp1, double _sp2, double _sp3) {
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
        dest.put(destOffset, _t63);
        dest.put(destOffset + 1, _t64);
        dest.put(destOffset + 2, _t65);
        dest.put(destOffset + 3, _t66);
        dest.put(destOffset + 4, 0.5 * Math.fma(_t0, _t64, Math.fma(_self03, _t66, _self13 * _t65)));
        dest.put(destOffset + 5, 0.5 * Math.fma(_self23, _t63, Math.fma(_self13, _t66, -(_self03 * _t65))));
        return toDualQuat_apiGet_sa85bfa44_2(dest, destOffset, _self03, _self13, _self23, _t0, _t63, _t64, _t65, _t66);
    }

    /** Piece 3 of {@code toDualQuat_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer toDualQuat_apiGet_sa85bfa44_2(java.nio.DoubleBuffer dest, int destOffset, double _self03, double _self13, double _self23, double _t0, double _t63, double _t64, double _t65, double _t66) {
        dest.put(destOffset + 6, 0.5 * Math.fma(_self23, _t66, Math.fma(_self03, _t64, -(_self13 * _t63))));
        dest.put(destOffset + 7, 0.5 * Math.fma(_t0, _t65, Math.fma(-_self13, _t64, -(_self03 * _t63))));
        return dest;
    }

    public static java.nio.DoubleBuffer toRigid_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.toRigid_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer toRigid_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 7 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.toRigid(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.toRigid_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.toRigid_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer toRigid_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _ct0 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsTypedBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        double _ct1 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsTypedBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        double _ct2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsTypedBuffer.toRigid_degenerate(dest, destOffset, src, srcOffset);
        return toRigid_apiGet_s4d9ea99a_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_self11, -_self22, (1.0 / java.lang.Math.sqrt(_ct0)), (1.0 / java.lang.Math.sqrt(_ct1)), _ct2);
    }

    /** Piece 2 of {@code toRigid_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer toRigid_apiGet_s4d9ea99a_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t1, double _t15, double _t16, double _ct2) {
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
        double _t52 = 1.0 - _t47;
        double _t63 = Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t51));
        return toRigid_apiGet_s4d9ea99a_2(dest, destOffset, _self03, _self11, _self13, _self22, _self23, _t0, _t1, _t15, _t16, _t19, _t24, Math.fma(_self12, _t16, _t23), Math.fma(_self21, _t15, -_t20), _t47, _t51, _t52, Math.fma(_self01, _t15, _t48), Math.fma(_self02, _t16, _t49), Math.fma(_self02, _t16, -_t49), Math.fma(-_self01, _t15, _t48), _t63, 0.5 * (1.0 / java.lang.Math.sqrt(_t63)), Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t52)));
    }

    /** Piece 3 of {@code toRigid_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer toRigid_apiGet_s4d9ea99a_2(java.nio.DoubleBuffer dest, int destOffset, double _self03, double _self11, double _self13, double _self22, double _self23, double _t0, double _t1, double _t15, double _t16, double _t19, double _t24, double _t31, double _t35, double _t47, double _t51, double _t52, double _t54, double _t55, double _t56, double _t57, double _t63, double _sp0, double _t65) {
        double _t66 = Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t52));
        double _t67 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51));
        return toRigid_apiGet_s4d9ea99a_3(dest, destOffset, _self03, _self11, _self13, _self22, _self23, _t15, _t16, _t19, _t24, _t31, _t35, _t47, _t54, _t55, _t56, _t57, _t63, _sp0, _t65, _t66, _t67, 0.5 * (1.0 / java.lang.Math.sqrt(_t65)), 0.5 * (1.0 / java.lang.Math.sqrt(_t66)), 0.5 * (1.0 / java.lang.Math.sqrt(_t67)));
    }

    /** Piece 4 of {@code toRigid_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer toRigid_apiGet_s4d9ea99a_3(java.nio.DoubleBuffer dest, int destOffset, double _self03, double _self11, double _self13, double _self22, double _self23, double _t15, double _t16, double _t19, double _t24, double _t31, double _t35, double _t47, double _t54, double _t55, double _t56, double _t57, double _t63, double _sp0, double _t65, double _t66, double _t67, double _sp1, double _sp2, double _sp3) {
        if (Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t47)) > 0.0) {
            dest.put(destOffset + 3, _sp0 * _t35);
            dest.put(destOffset + 4, _sp0 * _t56);
            dest.put(destOffset + 5, _sp0 * _t57);
            dest.put(destOffset + 6, 0.5 * java.lang.Math.sqrt(_t63));
        } else {
            if (_t47 > java.lang.Math.max(_t24, _t19)) {
                dest.put(destOffset + 3, 0.5 * java.lang.Math.sqrt(_t67));
                dest.put(destOffset + 4, _sp3 * _t54);
                dest.put(destOffset + 5, _sp3 * _t55);
                dest.put(destOffset + 6, _sp3 * _t35);
            } else {
                if (_t24 > _t19) {
                    dest.put(destOffset + 3, _sp1 * _t54);
                    dest.put(destOffset + 4, 0.5 * java.lang.Math.sqrt(_t65));
                    dest.put(destOffset + 5, _sp1 * _t31);
                    dest.put(destOffset + 6, _sp1 * _t56);
                } else {
                    dest.put(destOffset + 3, _sp2 * _t55);
                    dest.put(destOffset + 4, _sp2 * _t31);
                    dest.put(destOffset + 5, 0.5 * java.lang.Math.sqrt(_t66));
                    dest.put(destOffset + 6, _sp2 * _t57);
                }
            }
        }
        dest.put(destOffset, _self03);
        dest.put(destOffset + 1, _self13);
        dest.put(destOffset + 2, _self23);
        return dest;
    }

    public static java.nio.DoubleBuffer toRigid_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsTypedBuffer.toRigid_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x4OpsKernelsTypedBuffer.toRigid_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.DoubleBuffer toRigid_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.toRigid_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer toRigid_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 7 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4OpsKernelsArray.toRigid_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.toRigid_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.toRigid_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer toRigid_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
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
            dest.put(destOffset + 3, _sp0 * _t182);
            dest.put(destOffset + 4, _sp0 * _t201);
            dest.put(destOffset + 5, _sp0 * _t202);
            dest.put(destOffset + 6, 0.5 * java.lang.Math.sqrt(_t207));
        } else {
            if (_t194 > java.lang.Math.max(_t167, _t171)) {
                dest.put(destOffset + 3, 0.5 * java.lang.Math.sqrt(_t208));
                dest.put(destOffset + 4, _sp3 * _t199);
                dest.put(destOffset + 5, _sp3 * _t200);
                dest.put(destOffset + 6, _sp3 * _t182);
            } else {
                if (_t167 > _t171) {
                    dest.put(destOffset + 3, _sp1 * _t199);
                    dest.put(destOffset + 4, 0.5 * java.lang.Math.sqrt(_t209));
                    dest.put(destOffset + 5, _sp1 * _t184);
                    dest.put(destOffset + 6, _sp1 * _t201);
                } else {
                    dest.put(destOffset + 3, _sp2 * _t200);
                    dest.put(destOffset + 4, _sp2 * _t184);
                    dest.put(destOffset + 5, 0.5 * java.lang.Math.sqrt(_t210));
                    dest.put(destOffset + 6, _sp2 * _t202);
                }
            }
        }
        dest.put(destOffset, _self03);
        dest.put(destOffset + 1, _self13);
        dest.put(destOffset + 2, _self23);
        return dest;
    }

    public static java.nio.DoubleBuffer toTransform_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.toTransform_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer toTransform_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 10 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.toTransform(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.toTransform_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.toTransform_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer toTransform_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t12 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsTypedBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        double _t13 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsTypedBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        double _t14 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return Double3x4OpsKernelsTypedBuffer.toTransform_degenerate(dest, destOffset, src, srcOffset);
        return toTransform_apiGet_sf38db93d_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_self11, -_self22, _t12, _t13, _t14, (1.0 / java.lang.Math.sqrt(_t12)));
    }

    /** Piece 2 of {@code toTransform_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer toTransform_apiGet_sf38db93d_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t1, double _t12, double _t13, double _t14, double _t15) {
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
        return toTransform_apiGet_sf38db93d_2(dest, destOffset, _self03, _self11, _self13, _self22, _self23, _t0, _t1, _t12, _t13, _t15, _t16, _t18, _t20, _t25, Math.fma(_self12, _t16, _t24), Math.fma(_self21, _t15, -_t21), java.lang.Math.max(_t25, _t20), _t47, _t48, 1.0 + _t48, 1.0 - _t48, Math.fma(_self01, _t15, _t49), Math.fma(_self02, _t16, _t50), Math.fma(_self02, _t16, -_t50), Math.fma(-_self01, _t15, _t49), Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t48)));
    }

    /** Piece 3 of {@code toTransform_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer toTransform_apiGet_sf38db93d_2(java.nio.DoubleBuffer dest, int destOffset, double _self03, double _self11, double _self13, double _self22, double _self23, double _t0, double _t1, double _t12, double _t13, double _t15, double _t16, double _t18, double _t20, double _t25, double _t32, double _t36, double _t37, double _t47, double _t48, double _t52, double _t53, double _t55, double _t56, double _t57, double _t58, double _t63) {
        double _t64 = Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t52));
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t64));
        double _t66 = Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t53));
        double _t67 = Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t53));
        double _t68 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t66));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        dest.put(destOffset, _self03);
        dest.put(destOffset + 1, _self13);
        dest.put(destOffset + 2, _self23);
        dest.put(destOffset + 3, _t63 > 0.0 ? _sp0 * _t36 : _t48 > _t37 ? 0.5 * java.lang.Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56);
        return toTransform_apiGet_sf38db93d_3(dest, destOffset, _t12, _t13, _t18, _t20, _t25, _t32, _t36, _t37, _t47, _t48, _t55, _t56, _t57, _t58, _t63, _t64, _sp0, _t66, _t67, _sp1, _sp2, 0.5 * (1.0 / java.lang.Math.sqrt(_t68)));
    }

    /** Piece 4 of {@code toTransform_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer toTransform_apiGet_sf38db93d_3(java.nio.DoubleBuffer dest, int destOffset, double _t12, double _t13, double _t18, double _t20, double _t25, double _t32, double _t36, double _t37, double _t47, double _t48, double _t55, double _t56, double _t57, double _t58, double _t63, double _t64, double _sp0, double _t66, double _t67, double _sp1, double _sp2, double _sp3) {
        dest.put(destOffset + 4, _t63 > 0.0 ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5 * java.lang.Math.sqrt(_t66) : _sp2 * _t32);
        dest.put(destOffset + 5, _t63 > 0.0 ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5 * java.lang.Math.sqrt(_t67));
        dest.put(destOffset + 6, _t63 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58);
        dest.put(destOffset + 7, _t47 < 0.0 ? -_t18 : _t18);
        dest.put(destOffset + 8, java.lang.Math.sqrt(_t12));
        dest.put(destOffset + 9, java.lang.Math.sqrt(_t13));
        return dest;
    }

    public static java.nio.DoubleBuffer toTransform_degenerate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsTypedBuffer.toTransform_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x4OpsKernelsTypedBuffer.toTransform_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.DoubleBuffer toTransform_degenerate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.toTransform_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer toTransform_degenerate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 10 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4OpsKernelsArray.toTransform_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.toTransform_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.toTransform_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer toTransform_degenerate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
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
        dest.put(destOffset, _self03);
        dest.put(destOffset + 1, _self13);
        dest.put(destOffset + 2, _self23);
        dest.put(destOffset + 3, _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203);
        dest.put(destOffset + 4, _t209 > 0.0 ? _sp0 * _t204 : _t197 > _t186 ? _sp3 * _t202 : _t170 > _t174 ? 0.5 * java.lang.Math.sqrt(_t212) : _sp2 * _t187);
        dest.put(destOffset + 5, _t209 > 0.0 ? _sp0 * _t205 : _t197 > _t186 ? _sp3 * _t203 : _t170 > _t174 ? _sp1 * _t187 : 0.5 * java.lang.Math.sqrt(_t213));
        dest.put(destOffset + 6, _t209 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t210) : _t197 > _t186 ? _sp3 * _t185 : _t170 > _t174 ? _sp1 * _t204 : _sp2 * _t205);
        dest.put(destOffset + 7, _t196 < 0.0 ? -_t56 : _t56);
        dest.put(destOffset + 8, _t27 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t27) / _t0);
        dest.put(destOffset + 9, _t28 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t28) / _t1);
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeRotation_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.decomposeRotation_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeRotation_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.decomposeRotation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.decomposeRotation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.decomposeRotation_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeRotation_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
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
        return decomposeRotation_apiGet_sf5687a85_1(dest, destOffset, _self02, _self12, _self22, _t7, _t8, _t9, -Math.fma(_self22, _t7, Math.fma(_self02, _t8, _self12 * _t9)), _t21, _t22, _t23, _t29, (1.0 / java.lang.Math.sqrt(_t29)));
    }

    /** Piece 2 of {@code decomposeRotation_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer decomposeRotation_apiGet_sf5687a85_1(java.nio.DoubleBuffer dest, int destOffset, double _self02, double _self12, double _self22, double _t7, double _t8, double _t9, double _t20, double _t21, double _t22, double _t23, double _t29, double _t30) {
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
        return decomposeRotation_apiGet_sf5687a85_2(dest, destOffset, _t7, _t8, _t9, _t34, _t35, _t36, _t54, _t55, _t56, _t35 - _t54, _t35 + _t54);
    }

    /** Piece 3 of {@code decomposeRotation_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer decomposeRotation_apiGet_sf5687a85_2(java.nio.DoubleBuffer dest, int destOffset, double _t7, double _t8, double _t9, double _t34, double _t35, double _t36, double _t54, double _t55, double _t56, double _t60, double _t63) {
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
        return decomposeRotation_apiGet_sf5687a85_3(dest, destOffset, _t36, _t56, _t60, _t63, _t73, _t74 + _t34, _t74 - _t34, _t75 + _t55, _t55 - _t75, _t82, _t86, _t87, _t88, _t89, 0.5 * (1.0 / java.lang.Math.sqrt(_t86)), 0.5 * (1.0 / java.lang.Math.sqrt(_t88)), 0.5 * (1.0 / java.lang.Math.sqrt(_t89)), 0.5 * (1.0 / java.lang.Math.sqrt(_t87)));
    }

    /** Piece 4 of {@code decomposeRotation_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer decomposeRotation_apiGet_sf5687a85_3(java.nio.DoubleBuffer dest, int destOffset, double _t36, double _t56, double _t60, double _t63, double _t73, double _t77, double _t78, double _t80, double _t81, double _t82, double _t86, double _t87, double _t88, double _t89, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t82 > 0.0) {
            dest.put(destOffset, _sp0 * _t60);
            dest.put(destOffset + 1, _sp0 * _t81);
            dest.put(destOffset + 2, _sp0 * _t78);
            dest.put(destOffset + 3, 0.5 * java.lang.Math.sqrt(_t86));
        } else {
            if (_t73 > java.lang.Math.max(_t36, _t56)) {
                dest.put(destOffset, 0.5 * java.lang.Math.sqrt(_t87));
                dest.put(destOffset + 1, _sp3 * _t77);
                dest.put(destOffset + 2, _sp3 * _t80);
                dest.put(destOffset + 3, _sp3 * _t60);
            } else {
                if (_t36 > _t56) {
                    dest.put(destOffset, _sp1 * _t77);
                    dest.put(destOffset + 1, 0.5 * java.lang.Math.sqrt(_t88));
                    dest.put(destOffset + 2, _sp1 * _t63);
                    dest.put(destOffset + 3, _sp1 * _t81);
                } else {
                    dest.put(destOffset, _sp2 * _t80);
                    dest.put(destOffset + 1, _sp2 * _t63);
                    dest.put(destOffset + 2, 0.5 * java.lang.Math.sqrt(_t89));
                    dest.put(destOffset + 3, _sp2 * _t78);
                }
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeScale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.decomposeScale_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeScale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.decomposeScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.decomposeScale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.decomposeScale_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeScale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
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
        return decomposeScale_apiGet_s49d40b8d_1(dest, destOffset, _self02, _self12, _self22, _t4, _t8, _t9, _t10, -Math.fma(_self22, _t8, Math.fma(_self02, _t9, _self12 * _t10)), _t19, _t20, _t21, _t27, (1.0 / java.lang.Math.sqrt(_t27)));
    }

    /** Piece 2 of {@code decomposeScale_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer decomposeScale_apiGet_s49d40b8d_1(java.nio.DoubleBuffer dest, int destOffset, double _self02, double _self12, double _self22, double _t4, double _t8, double _t9, double _t10, double _t18, double _t19, double _t20, double _t21, double _t27, double _t28) {
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
        dest.put(destOffset, Math.fma(Math.fma(_t32, _t52, -(_t34 * _t53)), _t8, Math.fma(Math.fma(_t34, _t54, -(_t33 * _t52)), _t9, Math.fma(_t33, _t53, -(_t32 * _t54)) * _t10)) < 0.0 ? -_t4 : _t4);
        dest.put(destOffset + 1, java.lang.Math.sqrt(_t27));
        dest.put(destOffset + 2, java.lang.Math.sqrt(_t47));
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeSkew_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.decomposeSkew_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeSkew_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.decomposeSkew(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.decomposeSkew_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.decomposeSkew_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeSkew_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
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
        return decomposeSkew_apiGet_s5f67af75_1(dest, destOffset, _self02, _self12, _self22, _t7, _t8, _t9, _t14, -_t14, _t19, _t20, _t21, _t26, _t27, _t15 * _t27);
    }

    /** Piece 2 of {@code decomposeSkew_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer decomposeSkew_apiGet_s5f67af75_1(java.nio.DoubleBuffer dest, int destOffset, double _self02, double _self12, double _self22, double _t7, double _t8, double _t9, double _t14, double _t16, double _t19, double _t20, double _t21, double _t26, double _t27, double _t28) {
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
        return decomposeSkew_apiGet_s5f67af75_2(dest, destOffset, _t7, _t8, _t9, _t28, _t32, _t33, _t34, _t37, _t48, _t14 * _t48, _t53, _t54, _t55);
    }

    /** Piece 3 of {@code decomposeSkew_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer decomposeSkew_apiGet_s5f67af75_2(java.nio.DoubleBuffer dest, int destOffset, double _t7, double _t8, double _t9, double _t28, double _t32, double _t33, double _t34, double _t37, double _t48, double _t49, double _t53, double _t54, double _t55) {
        if (Math.fma(Math.fma(_t33, _t53, -(_t34 * _t54)), _t7, Math.fma(Math.fma(_t34, _t55, -(_t32 * _t53)), _t8, Math.fma(_t32, _t54, -(_t33 * _t55)) * _t9)) < 0.0) {
            dest.put(destOffset + 1, -_t49);
            dest.put(destOffset + 2, -_t28);
        } else {
            dest.put(destOffset + 1, _t49);
            dest.put(destOffset + 2, _t28);
        }
        dest.put(destOffset, _t37 * _t48);
        return dest;
    }

    public static java.nio.DoubleBuffer decomposeTRS_unsafe(java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset * 8L;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset * 8L;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.decomposeTRS_unsafe(_translationBase, _rotationBase, _scaleBase, _srcBase);
        return translation;
    }

    public static java.nio.DoubleBuffer decomposeTRS_api(java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (translation.hasArray() && translationOffset >= 0 && translationOffset <= translation.limit() - 3 && rotation.hasArray() && rotationOffset >= 0 && rotationOffset <= rotation.limit() - 4 && scale.hasArray() && scaleOffset >= 0 && scaleOffset <= scale.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.decomposeTRS(translation.array(), translation.arrayOffset() + translationOffset, rotation.array(), rotation.arrayOffset() + rotationOffset, scale.array(), scale.arrayOffset() + scaleOffset, src.array(), src.arrayOffset() + srcOffset);
            return translation;
        }
        if (translation.order() == java.nio.ByteOrder.nativeOrder() && !translation.isReadOnly() && rotation.order() == java.nio.ByteOrder.nativeOrder() && !rotation.isReadOnly() && scale.order() == java.nio.ByteOrder.nativeOrder() && !scale.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.decomposeTRS_api(java.lang.foreign.MemorySegment.ofBuffer(translation.duplicate().position(0)), translationOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(rotation.duplicate().position(0)), rotationOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(scale.duplicate().position(0)), scaleOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return translation;
        }
        Double3x4OpsKernelsTypedBuffer.decomposeTRS_apiGet(translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, src, srcOffset);
        return translation;
    }

    public static java.nio.DoubleBuffer decomposeTRS_apiGet(java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, java.nio.DoubleBuffer src, int srcOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
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
        return decomposeTRS_apiGet_sc299acbe_1(translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, _self02, _self03, _self11, _self12, _self13, _self22, _self23, _t4, _t8, _t9, _t10, _t20, -Math.fma(_self22, _t8, Math.fma(_self02, _t9, _self12 * _t10)), Math.fma(_t20, _t8, _self21), Math.fma(_t20, _t9, _self01));
    }

    /** Piece 2 of {@code decomposeTRS_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer decomposeTRS_apiGet_sc299acbe_1(java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, double _self02, double _self03, double _self11, double _self12, double _self13, double _self22, double _self23, double _t4, double _t8, double _t9, double _t10, double _t20, double _t21, double _t22, double _t23) {
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
        return decomposeTRS_apiGet_sc299acbe_2(translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, _self03, _self13, _self23, _t4, _t8, _t9, _t10, _t30, _t35, _t36, _t37, _t50, _t55, _t56, _t57, _t36 - _t55, _t36 + _t55);
    }

    /** Piece 3 of {@code decomposeTRS_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer decomposeTRS_apiGet_sc299acbe_2(java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, double _self03, double _self13, double _self23, double _t4, double _t8, double _t9, double _t10, double _t30, double _t35, double _t36, double _t37, double _t50, double _t55, double _t56, double _t57, double _t61, double _t64) {
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
        translation.put(translationOffset, _self03);
        translation.put(translationOffset + 1, _self13);
        translation.put(translationOffset + 2, _self23);
        return decomposeTRS_apiGet_sc299acbe_3(translation, rotation, rotationOffset, scale, scaleOffset, _t4, _t30, _t37, _t50, _t57, _t61, _t64, _t73, _t74, _t75 + _t35, _t75 - _t35, _t76 + _t56, _t56 - _t76, _t83, _t87, _t88, _t89, _t90, 0.5 * (1.0 / java.lang.Math.sqrt(_t87)), 0.5 * (1.0 / java.lang.Math.sqrt(_t89)), 0.5 * (1.0 / java.lang.Math.sqrt(_t90)), 0.5 * (1.0 / java.lang.Math.sqrt(_t88)));
    }

    /** Piece 4 of {@code decomposeTRS_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer decomposeTRS_apiGet_sc299acbe_3(java.nio.DoubleBuffer translation, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, double _t4, double _t30, double _t37, double _t50, double _t57, double _t61, double _t64, double _t73, double _t74, double _t78, double _t79, double _t81, double _t82, double _t83, double _t87, double _t88, double _t89, double _t90, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t83 > 0.0) {
            rotation.put(rotationOffset, _sp0 * _t61);
            rotation.put(rotationOffset + 1, _sp0 * _t82);
            rotation.put(rotationOffset + 2, _sp0 * _t79);
            rotation.put(rotationOffset + 3, 0.5 * java.lang.Math.sqrt(_t87));
        } else {
            if (_t74 > java.lang.Math.max(_t37, _t57)) {
                rotation.put(rotationOffset, 0.5 * java.lang.Math.sqrt(_t88));
                rotation.put(rotationOffset + 1, _sp3 * _t78);
                rotation.put(rotationOffset + 2, _sp3 * _t81);
                rotation.put(rotationOffset + 3, _sp3 * _t61);
            } else {
                if (_t37 > _t57) {
                    rotation.put(rotationOffset, _sp1 * _t78);
                    rotation.put(rotationOffset + 1, 0.5 * java.lang.Math.sqrt(_t89));
                    rotation.put(rotationOffset + 2, _sp1 * _t64);
                    rotation.put(rotationOffset + 3, _sp1 * _t82);
                } else {
                    rotation.put(rotationOffset, _sp2 * _t81);
                    rotation.put(rotationOffset + 1, _sp2 * _t64);
                    rotation.put(rotationOffset + 2, 0.5 * java.lang.Math.sqrt(_t90));
                    rotation.put(rotationOffset + 3, _sp2 * _t79);
                }
            }
        }
        scale.put(scaleOffset, _t73 < 0.0 ? -_t4 : _t4);
        scale.put(scaleOffset + 1, java.lang.Math.sqrt(_t30));
        scale.put(scaleOffset + 2, java.lang.Math.sqrt(_t50));
        return translation;
    }

    public static java.nio.DoubleBuffer makeIdentity_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeIdentity_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeIdentity(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeIdentity_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeIdentity_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeIdentity_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 1.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 1.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3x4OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 12) {
            Double3x4Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.DoubleBuffer lerp_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        for (int _i = 0; _i < 12; _i++) {
            double _eself = src.get(srcOffset + _i);
            dest.put(destOffset + _i, Math.fma(t, other.get(otherOffset + _i) - _eself, _eself));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset * 8L;
        Double3x4OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 12) {
            Double3x4Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        double _right00 = right.get(rightOffset);
        double _right01 = right.get(rightOffset + 1);
        double _right02 = right.get(rightOffset + 2);
        double _right03 = right.get(rightOffset + 3);
        double _right10 = right.get(rightOffset + 4);
        double _right11 = right.get(rightOffset + 5);
        double _right12 = right.get(rightOffset + 6);
        double _right13 = right.get(rightOffset + 7);
        double _right20 = right.get(rightOffset + 8);
        double _right21 = right.get(rightOffset + 9);
        double _right22 = right.get(rightOffset + 10);
        double _right23 = right.get(rightOffset + 11);
        return mul_apiGet_s5f3ee815_1(dest, destOffset, src, srcOffset, _right00, _right01, _right02, _right03, _right10, _right11, _right12, _right13, _right20, _right21, _right22, _right23);
    }

    /** Piece 2 of {@code mul_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer mul_apiGet_s5f3ee815_1(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double _right00, double _right01, double _right02, double _right03, double _right10, double _right11, double _right12, double _right13, double _right20, double _right21, double _right22, double _right23) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, Math.fma(_right20, _eself2, Math.fma(_right00, _eself0, _right10 * _eself1)));
            dest.put(destOffset + _lo + 1, Math.fma(_right21, _eself2, Math.fma(_right01, _eself0, _right11 * _eself1)));
            dest.put(destOffset + _lo + 2, Math.fma(_right22, _eself2, Math.fma(_right02, _eself0, _right12 * _eself1)));
            dest.put(destOffset + _lo + 3, Math.fma(_right03, _eself0, Math.fma(_right13, _eself1, Math.fma(_right23, _eself2, _eself3))));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mulMat2x2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset * 8L;
        Double3x4OpsKernelsAddress.mulMat2x2_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mulMat2x2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 4) {
            Double3x4Ops.mulMat2x2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && right.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mulMat2x2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(right.duplicate().position(0)), rightOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mulMat2x2_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mulMat2x2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        double _right00 = right.get(rightOffset);
        double _right10 = right.get(rightOffset + 1);
        double _right01 = right.get(rightOffset + 2);
        double _right11 = right.get(rightOffset + 3);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, Math.fma(_right00, _eself0, _right10 * _eself1));
            dest.put(destOffset + _lo + 1, Math.fma(_right01, _eself0, _right11 * _eself1));
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mulMat2x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset * 8L;
        Double3x4OpsKernelsAddress.mulMat2x3_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mulMat2x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 6) {
            Double3x4Ops.mulMat2x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mulMat2x3_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mulMat2x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        double _right00 = right.get(rightOffset);
        double _right10 = right.get(rightOffset + 1);
        double _right01 = right.get(rightOffset + 2);
        double _right11 = right.get(rightOffset + 3);
        double _right02 = right.get(rightOffset + 4);
        double _right12 = right.get(rightOffset + 5);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, Math.fma(_right00, _eself0, _right10 * _eself1));
            dest.put(destOffset + _lo + 1, Math.fma(_right01, _eself0, _right11 * _eself1));
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, Math.fma(_right02, _eself0, Math.fma(_right12, _eself1, _eself3)));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mulMat3x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset * 8L;
        Double3x4OpsKernelsAddress.mulMat3x3_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mulMat3x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 9) {
            Double3x4Ops.mulMat3x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mulMat3x3_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mulMat3x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        double _right00 = right.get(rightOffset);
        double _right10 = right.get(rightOffset + 1);
        double _right20 = right.get(rightOffset + 2);
        double _right01 = right.get(rightOffset + 3);
        double _right11 = right.get(rightOffset + 4);
        double _right21 = right.get(rightOffset + 5);
        double _right02 = right.get(rightOffset + 6);
        double _right12 = right.get(rightOffset + 7);
        double _right22 = right.get(rightOffset + 8);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, Math.fma(_right20, _eself2, Math.fma(_right00, _eself0, _right10 * _eself1)));
            dest.put(destOffset + _lo + 1, Math.fma(_right21, _eself2, Math.fma(_right01, _eself0, _right11 * _eself1)));
            dest.put(destOffset + _lo + 2, Math.fma(_right22, _eself2, Math.fma(_right02, _eself0, _right12 * _eself1)));
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mulMat4x4_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset * 8L;
        Double3x4OpsKernelsAddress.mulMat4x4_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mulMat4x4_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 16 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 16) {
            Double3x4Ops.mulMat4x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mulMat4x4_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mulMat4x4_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        return mulMat4x4_apiGet_s68740363_1(dest, destOffset, right, rightOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23);
    }

    /** Piece 2 of {@code mulMat4x4_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer mulMat4x4_apiGet_s68740363_1(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer right, int rightOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23) {
        for (int _l = 0; _l < 4; _l++) {
            int _lo = _l * 4;
            double _eright0 = right.get(rightOffset + _lo);
            double _eright1 = right.get(rightOffset + _lo + 1);
            double _eright2 = right.get(rightOffset + _lo + 2);
            double _eright3 = right.get(rightOffset + _lo + 3);
            dest.put(destOffset + _lo, Math.fma(_eright3, _self03, Math.fma(_eright2, _self02, Math.fma(_eright0, _self00, _eright1 * _self01))));
            dest.put(destOffset + _lo + 1, Math.fma(_eright3, _self13, Math.fma(_eright2, _self12, Math.fma(_eright0, _self10, _eright1 * _self11))));
            dest.put(destOffset + _lo + 2, Math.fma(_eright3, _self23, Math.fma(_eright2, _self22, Math.fma(_eright0, _self20, _eright1 * _self21))));
            dest.put(destOffset + _lo + 3, _eright3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3x4OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 12) {
            Double3x4Ops.preMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preMul_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        return preMul_apiGet_s66da2c80_1(dest, destOffset, other, otherOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23);
    }

    /** Piece 2 of {@code preMul_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preMul_apiGet_s66da2c80_1(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer other, int otherOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eother0 = other.get(otherOffset + _lo);
            double _eother1 = other.get(otherOffset + _lo + 1);
            double _eother2 = other.get(otherOffset + _lo + 2);
            double _eother3 = other.get(otherOffset + _lo + 3);
            dest.put(destOffset + _lo, Math.fma(_eother2, _self20, Math.fma(_eother0, _self00, _eother1 * _self10)));
            dest.put(destOffset + _lo + 1, Math.fma(_eother2, _self21, Math.fma(_eother0, _self01, _eother1 * _self11)));
            dest.put(destOffset + _lo + 2, Math.fma(_eother2, _self22, Math.fma(_eother0, _self02, _eother1 * _self12)));
            dest.put(destOffset + _lo + 3, Math.fma(_eother0, _self03, Math.fma(_eother1, _self13, Math.fma(_eother2, _self23, _eother3))));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer preMulMat2x2_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3x4OpsKernelsAddress.preMulMat2x2_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulMat2x2_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Double3x4Ops.preMulMat2x2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preMulMat2x2_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulMat2x2_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _other00 = other.get(otherOffset);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
        dest.put(destOffset, Math.fma(_other00, _self00, _other01 * _self10));
        dest.put(destOffset + 1, Math.fma(_other00, _self01, _other01 * _self11));
        dest.put(destOffset + 2, Math.fma(_other00, _self02, _other01 * _self12));
        dest.put(destOffset + 3, Math.fma(_other00, _self03, _other01 * _self13));
        dest.put(destOffset + 4, Math.fma(_other10, _self00, _other11 * _self10));
        dest.put(destOffset + 5, Math.fma(_other10, _self01, _other11 * _self11));
        return preMulMat2x2_apiGet_saea8492_1(dest, destOffset, _self02, _self03, _self12, _self13, _self20, _self21, _self22, _self23, _other10, _other11);
    }

    /** Piece 2 of {@code preMulMat2x2_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preMulMat2x2_apiGet_saea8492_1(java.nio.DoubleBuffer dest, int destOffset, double _self02, double _self03, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other10, double _other11) {
        dest.put(destOffset + 6, Math.fma(_other10, _self02, _other11 * _self12));
        dest.put(destOffset + 7, Math.fma(_other10, _self03, _other11 * _self13));
        dest.put(destOffset + 8, _self20);
        dest.put(destOffset + 9, _self21);
        dest.put(destOffset + 10, _self22);
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulMat2x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3x4OpsKernelsAddress.preMulMat2x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulMat2x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 6) {
            Double3x4Ops.preMulMat2x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preMulMat2x3_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulMat2x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _other00 = other.get(otherOffset);
        double _other10 = other.get(otherOffset + 1);
        double _other01 = other.get(otherOffset + 2);
        double _other11 = other.get(otherOffset + 3);
        double _other02 = other.get(otherOffset + 4);
        double _other12 = other.get(otherOffset + 5);
        dest.put(destOffset, Math.fma(_other00, _self00, _other01 * _self10));
        dest.put(destOffset + 1, Math.fma(_other00, _self01, _other01 * _self11));
        dest.put(destOffset + 2, Math.fma(_other00, _self02, _other01 * _self12));
        dest.put(destOffset + 3, Math.fma(_other00, _self03, Math.fma(_other01, _self13, _other02)));
        return preMulMat2x3_apiGet_scad23c05_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other10, _other11, _other12);
    }

    /** Piece 2 of {@code preMulMat2x3_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preMulMat2x3_apiGet_scad23c05_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other10, double _other11, double _other12) {
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

    public static java.nio.DoubleBuffer preMulMat3x3_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3x4OpsKernelsAddress.preMulMat3x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulMat3x3_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 9) {
            Double3x4Ops.preMulMat3x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preMulMat3x3_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulMat3x3_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _other00 = other.get(otherOffset);
        double _other10 = other.get(otherOffset + 1);
        double _other20 = other.get(otherOffset + 2);
        double _other01 = other.get(otherOffset + 3);
        double _other11 = other.get(otherOffset + 4);
        double _other21 = other.get(otherOffset + 5);
        double _other02 = other.get(otherOffset + 6);
        double _other12 = other.get(otherOffset + 7);
        double _other22 = other.get(otherOffset + 8);
        dest.put(destOffset, Math.fma(_other02, _self20, Math.fma(_other00, _self00, _other01 * _self10)));
        return preMulMat3x3_apiGet_s2166f3fa_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Piece 2 of {@code preMulMat3x3_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preMulMat3x3_apiGet_s2166f3fa_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _other02, double _other12, double _other22) {
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

    public static java.nio.DoubleBuffer preMulMat4x4_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3x4OpsKernelsAddress.preMulMat4x4_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulMat4x4_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 16 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 16) {
            Double3x4Ops.preMulMat4x4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preMulMat4x4_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preMulMat4x4_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _other00 = other.get(otherOffset);
        double _other10 = other.get(otherOffset + 1);
        double _other20 = other.get(otherOffset + 2);
        double _other30 = other.get(otherOffset + 3);
        double _other01 = other.get(otherOffset + 4);
        double _other11 = other.get(otherOffset + 5);
        double _other21 = other.get(otherOffset + 6);
        double _other31 = other.get(otherOffset + 7);
        double _other02 = other.get(otherOffset + 8);
        double _other12 = other.get(otherOffset + 9);
        double _other22 = other.get(otherOffset + 10);
        double _other32 = other.get(otherOffset + 11);
        return preMulMat4x4_apiGet_sdfb0f7a2_1(dest, destOffset, other, otherOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _other00, _other10, _other20, _other30, _other01, _other11, _other21, _other31, _other02, _other12, _other22, _other32);
    }

    /** Piece 2 of {@code preMulMat4x4_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preMulMat4x4_apiGet_sdfb0f7a2_1(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer other, int otherOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _other00, double _other10, double _other20, double _other30, double _other01, double _other11, double _other21, double _other31, double _other02, double _other12, double _other22, double _other32) {
        double _other03 = other.get(otherOffset + 12);
        double _other13 = other.get(otherOffset + 13);
        double _other23 = other.get(otherOffset + 14);
        double _other33 = other.get(otherOffset + 15);
        dest.put(destOffset, Math.fma(_other02, _self20, Math.fma(_other00, _self00, _other01 * _self10)));
        dest.put(destOffset + 1, Math.fma(_other12, _self20, Math.fma(_other10, _self00, _other11 * _self10)));
        dest.put(destOffset + 2, Math.fma(_other22, _self20, Math.fma(_other20, _self00, _other21 * _self10)));
        dest.put(destOffset + 3, Math.fma(_other32, _self20, Math.fma(_other30, _self00, _other31 * _self10)));
        dest.put(destOffset + 4, Math.fma(_other02, _self21, Math.fma(_other00, _self01, _other01 * _self11)));
        dest.put(destOffset + 5, Math.fma(_other12, _self21, Math.fma(_other10, _self01, _other11 * _self11)));
        dest.put(destOffset + 6, Math.fma(_other22, _self21, Math.fma(_other20, _self01, _other21 * _self11)));
        dest.put(destOffset + 7, Math.fma(_other32, _self21, Math.fma(_other30, _self01, _other31 * _self11)));
        return preMulMat4x4_apiGet_sdfb0f7a2_2(dest, destOffset, _self02, _self03, _self12, _self13, _self22, _self23, _other00, _other10, _other20, _other30, _other01, _other11, _other21, _other31, _other02, _other12, _other22, _other32, _other03, _other13, _other23, _other33);
    }

    /** Piece 3 of {@code preMulMat4x4_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preMulMat4x4_apiGet_sdfb0f7a2_2(java.nio.DoubleBuffer dest, int destOffset, double _self02, double _self03, double _self12, double _self13, double _self22, double _self23, double _other00, double _other10, double _other20, double _other30, double _other01, double _other11, double _other21, double _other31, double _other02, double _other12, double _other22, double _other32, double _other03, double _other13, double _other23, double _other33) {
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

    public static java.nio.DoubleBuffer addScaled_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 8L;
        Double3x4OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double weight) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 12) {
            Double3x4Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, weight);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return dest;
    }

    public static java.nio.DoubleBuffer addScaled_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double weight) {
        for (int _i = 0; _i < 12; _i++) {
            dest.put(destOffset + _i, Math.fma(weight, other.get(otherOffset + _i), src.get(srcOffset + _i)));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRS_unsafe(java.nio.DoubleBuffer dest, int destOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.composeTRS_unsafe(_destBase, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRS_api(java.nio.DoubleBuffer dest, int destOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.composeTRS(dest.array(), dest.arrayOffset() + destOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.composeTRS_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.composeTRS_apiGet(dest, destOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRS_apiGet(java.nio.DoubleBuffer dest, int destOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        double _t0 = scaleX + scaleX;
        double _t1 = scaleY + scaleY;
        double _t2 = scaleZ + scaleZ;
        double _t3 = rotationZ * rotationZ;
        double _t4 = rotationZ * rotationW;
        double _t5 = rotationY * rotationW;
        dest.put(destOffset, Math.fma(-Math.fma(rotationY, rotationY, _t3), _t0, scaleX));
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

    public static java.nio.DoubleBuffer composeTRS_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset * 8L;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset * 8L;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset * 8L;
        Double3x4OpsKernelsAddress.composeTRS_unsafe(_destBase, _translationBase, _rotationBase, _scaleBase);
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRS_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && translation.hasArray() && translationOffset >= 0 && translationOffset <= translation.limit() - 3 && rotation.hasArray() && rotationOffset >= 0 && rotationOffset <= rotation.limit() - 4 && scale.hasArray() && scaleOffset >= 0 && scaleOffset <= scale.limit() - 3) {
            Double3x4Ops.composeTRS(dest.array(), dest.arrayOffset() + destOffset, translation.array(), translation.arrayOffset() + translationOffset, rotation.array(), rotation.arrayOffset() + rotationOffset, scale.array(), scale.arrayOffset() + scaleOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && translation.order() == java.nio.ByteOrder.nativeOrder() && rotation.order() == java.nio.ByteOrder.nativeOrder() && scale.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.composeTRS_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(translation.duplicate().position(0)), translationOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(rotation.duplicate().position(0)), rotationOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(scale.duplicate().position(0)), scaleOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.composeTRS_apiGet(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRS_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset) {
        double _translationx = translation.get(translationOffset);
        double _translationy = translation.get(translationOffset + 1);
        double _translationz = translation.get(translationOffset + 2);
        double _rotationx = rotation.get(rotationOffset);
        double _rotationy = rotation.get(rotationOffset + 1);
        double _rotationz = rotation.get(rotationOffset + 2);
        double _rotationw = rotation.get(rotationOffset + 3);
        double _scalex = scale.get(scaleOffset);
        double _scaley = scale.get(scaleOffset + 1);
        double _scalez = scale.get(scaleOffset + 2);
        double _t0 = _scalex + _scalex;
        double _t1 = _scaley + _scaley;
        double _t2 = _scalez + _scalez;
        double _t3 = _rotationz * _rotationz;
        double _t4 = _rotationz * _rotationw;
        double _t5 = _rotationy * _rotationw;
        dest.put(destOffset, Math.fma(-Math.fma(_rotationy, _rotationy, _t3), _t0, _scalex));
        dest.put(destOffset + 1, Math.fma(_rotationx, _rotationy, -_t4) * _t1);
        dest.put(destOffset + 2, Math.fma(_rotationx, _rotationz, _t5) * _t2);
        dest.put(destOffset + 3, _translationx);
        dest.put(destOffset + 4, Math.fma(_rotationx, _rotationy, _t4) * _t0);
        dest.put(destOffset + 5, Math.fma(-Math.fma(_rotationx, _rotationx, _t3), _t1, _scaley));
        return composeTRS_apiGet_sa01024d3_1(dest, destOffset, _translationy, _translationz, _rotationx, _rotationy, _rotationz, _rotationw, _scalez, _t0, _t1, _t2, _t5);
    }

    /** Piece 2 of {@code composeTRS_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer composeTRS_apiGet_sa01024d3_1(java.nio.DoubleBuffer dest, int destOffset, double _translationy, double _translationz, double _rotationx, double _rotationy, double _rotationz, double _rotationw, double _scalez, double _t0, double _t1, double _t2, double _t5) {
        dest.put(destOffset + 6, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t2);
        dest.put(destOffset + 7, _translationy);
        dest.put(destOffset + 8, Math.fma(_rotationx, _rotationz, -_t5) * _t0);
        dest.put(destOffset + 9, Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t1);
        dest.put(destOffset + 10, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t2, _scalez));
        dest.put(destOffset + 11, _translationz);
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRSAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ, double pivotX, double pivotY, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.composeTRSAround_unsafe(_destBase, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRSAround_api(java.nio.DoubleBuffer dest, int destOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ, double pivotX, double pivotY, double pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.composeTRSAround(dest.array(), dest.arrayOffset() + destOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ, pivotX, pivotY, pivotZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.composeTRSAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ, pivotX, pivotY, pivotZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.composeTRSAround_apiGet(dest, destOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRSAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ, double pivotX, double pivotY, double pivotZ) {
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
        dest.put(destOffset, Math.fma(-_t15, _t3, scaleX));
        dest.put(destOffset + 1, _t27);
        dest.put(destOffset + 2, _t24);
        dest.put(destOffset + 3, Math.fma(pivotX, Math.fma(_t15, _t3, 1.0 - scaleX), Math.fma(_t0, _t27, Math.fma(_t1, _t24, translationX))));
        dest.put(destOffset + 4, _t25);
        return composeTRSAround_apiGet_sb338b83a_1(dest, destOffset, translationY, translationZ, scaleY, scaleZ, pivotY, pivotZ, _t0, _t1, -pivotX, _t4, _t5, Math.fma(rotationX, rotationX, _t6), Math.fma(rotationX, rotationX, rotationY * rotationY), _t25, Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t4, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t5, Math.fma(rotationX, rotationZ, -_t8) * _t3);
    }

    /** Piece 2 of {@code composeTRSAround_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer composeTRSAround_apiGet_sb338b83a_1(java.nio.DoubleBuffer dest, int destOffset, double translationY, double translationZ, double scaleY, double scaleZ, double pivotY, double pivotZ, double _t0, double _t1, double _t2, double _t4, double _t5, double _t18, double _t20, double _t25, double _t26, double _t28, double _t29) {
        dest.put(destOffset + 5, Math.fma(-_t18, _t4, scaleY));
        dest.put(destOffset + 6, _t28);
        dest.put(destOffset + 7, Math.fma(pivotY, Math.fma(_t18, _t4, 1.0 - scaleY), Math.fma(_t2, _t25, Math.fma(_t1, _t28, translationY))));
        dest.put(destOffset + 8, _t29);
        dest.put(destOffset + 9, _t26);
        dest.put(destOffset + 10, Math.fma(-_t20, _t5, scaleZ));
        dest.put(destOffset + 11, Math.fma(pivotZ, Math.fma(_t20, _t5, 1.0 - scaleZ), Math.fma(_t2, _t29, Math.fma(_t0, _t26, translationZ))));
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRSAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset * 8L;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset * 8L;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset * 8L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset * 8L;
        Double3x4OpsKernelsAddress.composeTRSAround_unsafe(_destBase, _translationBase, _rotationBase, _scaleBase, _pivotBase);
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRSAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && translation.hasArray() && translationOffset >= 0 && translationOffset <= translation.limit() - 3 && rotation.hasArray() && rotationOffset >= 0 && rotationOffset <= rotation.limit() - 4 && scale.hasArray() && scaleOffset >= 0 && scaleOffset <= scale.limit() - 3 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Double3x4Ops.composeTRSAround(dest.array(), dest.arrayOffset() + destOffset, translation.array(), translation.arrayOffset() + translationOffset, rotation.array(), rotation.arrayOffset() + rotationOffset, scale.array(), scale.arrayOffset() + scaleOffset, pivot.array(), pivot.arrayOffset() + pivotOffset);
            return dest;
        }
        return composeTRSAround_api_s672b647f_1(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, pivot, pivotOffset);
    }

    /** Piece 2 of {@code composeTRSAround_api}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer composeTRSAround_api_s672b647f_1(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && translation.order() == java.nio.ByteOrder.nativeOrder() && rotation.order() == java.nio.ByteOrder.nativeOrder() && scale.order() == java.nio.ByteOrder.nativeOrder() && pivot.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.composeTRSAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(translation.duplicate().position(0)), translationOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(rotation.duplicate().position(0)), rotationOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(scale.duplicate().position(0)), scaleOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(pivot.duplicate().position(0)), pivotOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.composeTRSAround_apiGet(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, pivot, pivotOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRSAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        double _translationx = translation.get(translationOffset);
        double _translationy = translation.get(translationOffset + 1);
        double _translationz = translation.get(translationOffset + 2);
        double _rotationx = rotation.get(rotationOffset);
        double _rotationy = rotation.get(rotationOffset + 1);
        double _rotationz = rotation.get(rotationOffset + 2);
        double _rotationw = rotation.get(rotationOffset + 3);
        double _scalex = scale.get(scaleOffset);
        double _scaley = scale.get(scaleOffset + 1);
        double _scalez = scale.get(scaleOffset + 2);
        double _pivotx = pivot.get(pivotOffset);
        double _pivoty = pivot.get(pivotOffset + 1);
        double _pivotz = pivot.get(pivotOffset + 2);
        double _t3 = _scalex + _scalex;
        double _t4 = _scaley + _scaley;
        double _t5 = _scalez + _scalez;
        double _t6 = _rotationz * _rotationz;
        double _t7 = _rotationz * _rotationw;
        double _t8 = _rotationy * _rotationw;
        return composeTRSAround_apiGet_se600a3f3_1(dest, destOffset, _translationx, _translationy, _translationz, _rotationx, _rotationy, _rotationz, _rotationw, _scalex, _scaley, _scalez, _pivotx, _pivoty, _pivotz, -_pivoty, -_pivotz, -_pivotx, _t3, _t4, _t5, _t8, Math.fma(_rotationy, _rotationy, _t6), Math.fma(_rotationx, _rotationx, _t6), Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), Math.fma(_rotationx, _rotationz, _t8) * _t5, Math.fma(_rotationx, _rotationy, _t7) * _t3, Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t4, Math.fma(_rotationx, _rotationy, -_t7) * _t4);
    }

    /** Piece 2 of {@code composeTRSAround_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer composeTRSAround_apiGet_se600a3f3_1(java.nio.DoubleBuffer dest, int destOffset, double _translationx, double _translationy, double _translationz, double _rotationx, double _rotationy, double _rotationz, double _rotationw, double _scalex, double _scaley, double _scalez, double _pivotx, double _pivoty, double _pivotz, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t8, double _t15, double _t18, double _t20, double _t24, double _t25, double _t26, double _t27) {
        double _t28 = Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t5;
        double _t29 = Math.fma(_rotationx, _rotationz, -_t8) * _t3;
        dest.put(destOffset, Math.fma(-_t15, _t3, _scalex));
        dest.put(destOffset + 1, _t27);
        dest.put(destOffset + 2, _t24);
        dest.put(destOffset + 3, Math.fma(_pivotx, Math.fma(_t15, _t3, 1.0 - _scalex), Math.fma(_t0, _t27, Math.fma(_t1, _t24, _translationx))));
        dest.put(destOffset + 4, _t25);
        dest.put(destOffset + 5, Math.fma(-_t18, _t4, _scaley));
        dest.put(destOffset + 6, _t28);
        dest.put(destOffset + 7, Math.fma(_pivoty, Math.fma(_t18, _t4, 1.0 - _scaley), Math.fma(_t2, _t25, Math.fma(_t1, _t28, _translationy))));
        dest.put(destOffset + 8, _t29);
        dest.put(destOffset + 9, _t26);
        dest.put(destOffset + 10, Math.fma(-_t20, _t5, _scalez));
        dest.put(destOffset + 11, Math.fma(_pivotz, Math.fma(_t20, _t5, 1.0 - _scalez), Math.fma(_t2, _t29, Math.fma(_t0, _t26, _translationz))));
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRSMul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 8L;
        Double3x4OpsKernelsAddress.composeTRSMul_unsafe(_destBase, _mBase, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRSMul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 12) {
            Double3x4Ops.composeTRSMul(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.composeTRSMul_apiGet(dest, destOffset, m, mOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRSMul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        double _m00 = m.get(mOffset);
        double _m01 = m.get(mOffset + 1);
        double _m02 = m.get(mOffset + 2);
        double _m03 = m.get(mOffset + 3);
        double _m10 = m.get(mOffset + 4);
        double _m11 = m.get(mOffset + 5);
        double _m12 = m.get(mOffset + 6);
        double _m13 = m.get(mOffset + 7);
        double _m20 = m.get(mOffset + 8);
        double _m21 = m.get(mOffset + 9);
        double _m22 = m.get(mOffset + 10);
        double _m23 = m.get(mOffset + 11);
        double _t0 = scaleZ + scaleZ;
        double _t1 = scaleX + scaleX;
        double _t2 = scaleY + scaleY;
        double _t3 = rotationY * rotationW;
        double _t4 = rotationZ * rotationZ;
        double _t5 = rotationZ * rotationW;
        return composeTRSMul_apiGet_saffdef28_1(dest, destOffset, translationX, translationY, translationZ, rotationX, rotationY, scaleY, scaleZ, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t0, _t2, _t4, Math.fma(rotationX, rotationZ, _t3) * _t0, Math.fma(rotationX, rotationY, _t5) * _t1, Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2, Math.fma(rotationX, rotationY, -_t5) * _t2, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0, Math.fma(rotationX, rotationZ, -_t3) * _t1, Math.fma(-Math.fma(rotationY, rotationY, _t4), _t1, scaleX));
    }

    /** Piece 2 of {@code composeTRSMul_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer composeTRSMul_apiGet_saffdef28_1(java.nio.DoubleBuffer dest, int destOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double scaleY, double scaleZ, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t0, double _t2, double _t4, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t30) {
        double _t31 = Math.fma(-Math.fma(rotationX, rotationX, _t4), _t2, scaleY);
        dest.put(destOffset, Math.fma(_m20, _t24, Math.fma(_m00, _t30, _m10 * _t27)));
        dest.put(destOffset + 1, Math.fma(_m21, _t24, Math.fma(_m01, _t30, _m11 * _t27)));
        dest.put(destOffset + 2, Math.fma(_m22, _t24, Math.fma(_m02, _t30, _m12 * _t27)));
        dest.put(destOffset + 3, Math.fma(_m03, _t30, Math.fma(_m13, _t27, Math.fma(_m23, _t24, translationX))));
        dest.put(destOffset + 4, Math.fma(_m20, _t28, Math.fma(_m00, _t25, _m10 * _t31)));
        dest.put(destOffset + 5, Math.fma(_m21, _t28, Math.fma(_m01, _t25, _m11 * _t31)));
        dest.put(destOffset + 6, Math.fma(_m22, _t28, Math.fma(_m02, _t25, _m12 * _t31)));
        dest.put(destOffset + 7, Math.fma(_m03, _t25, Math.fma(_m13, _t31, Math.fma(_m23, _t28, translationY))));
        return composeTRSMul_apiGet_saffdef28_2(dest, destOffset, translationZ, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t26, _t29, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ));
    }

    /** Piece 3 of {@code composeTRSMul_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer composeTRSMul_apiGet_saffdef28_2(java.nio.DoubleBuffer dest, int destOffset, double translationZ, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t26, double _t29, double _t32) {
        dest.put(destOffset + 8, Math.fma(_m20, _t32, Math.fma(_m00, _t29, _m10 * _t26)));
        dest.put(destOffset + 9, Math.fma(_m21, _t32, Math.fma(_m01, _t29, _m11 * _t26)));
        dest.put(destOffset + 10, Math.fma(_m22, _t32, Math.fma(_m02, _t29, _m12 * _t26)));
        dest.put(destOffset + 11, Math.fma(_m03, _t29, Math.fma(_m13, _t26, Math.fma(_m23, _t32, translationZ))));
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRSMul_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, java.nio.DoubleBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset * 8L;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset * 8L;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 8L;
        Double3x4OpsKernelsAddress.composeTRSMul_unsafe(_destBase, _translationBase, _rotationBase, _scaleBase, _mBase);
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRSMul_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && translation.hasArray() && translationOffset >= 0 && translationOffset <= translation.limit() - 3 && rotation.hasArray() && rotationOffset >= 0 && rotationOffset <= rotation.limit() - 4 && scale.hasArray() && scaleOffset >= 0 && scaleOffset <= scale.limit() - 3 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 12) {
            Double3x4Ops.composeTRSMul(dest.array(), dest.arrayOffset() + destOffset, translation.array(), translation.arrayOffset() + translationOffset, rotation.array(), rotation.arrayOffset() + rotationOffset, scale.array(), scale.arrayOffset() + scaleOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.composeTRSMul_apiGet(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRSMul_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, java.nio.DoubleBuffer m, int mOffset) {
        double _translationx = translation.get(translationOffset);
        double _translationy = translation.get(translationOffset + 1);
        double _translationz = translation.get(translationOffset + 2);
        double _rotationx = rotation.get(rotationOffset);
        double _rotationy = rotation.get(rotationOffset + 1);
        double _rotationz = rotation.get(rotationOffset + 2);
        double _rotationw = rotation.get(rotationOffset + 3);
        double _scalex = scale.get(scaleOffset);
        double _scaley = scale.get(scaleOffset + 1);
        double _scalez = scale.get(scaleOffset + 2);
        double _m00 = m.get(mOffset);
        double _m01 = m.get(mOffset + 1);
        double _m02 = m.get(mOffset + 2);
        double _m03 = m.get(mOffset + 3);
        double _m10 = m.get(mOffset + 4);
        double _m11 = m.get(mOffset + 5);
        double _m12 = m.get(mOffset + 6);
        double _m13 = m.get(mOffset + 7);
        double _m20 = m.get(mOffset + 8);
        double _m21 = m.get(mOffset + 9);
        double _m22 = m.get(mOffset + 10);
        double _m23 = m.get(mOffset + 11);
        return composeTRSMul_apiGet_s169f61de_1(dest, destOffset, _translationx, _translationy, _translationz, _rotationx, _rotationy, _rotationz, _rotationw, _scalex, _scaley, _scalez, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _scalez + _scalez, _scalex + _scalex, _scaley + _scaley, _rotationy * _rotationw);
    }

    /** Piece 2 of {@code composeTRSMul_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer composeTRSMul_apiGet_s169f61de_1(java.nio.DoubleBuffer dest, int destOffset, double _translationx, double _translationy, double _translationz, double _rotationx, double _rotationy, double _rotationz, double _rotationw, double _scalex, double _scaley, double _scalez, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t0, double _t1, double _t2, double _t3) {
        double _t4 = _rotationz * _rotationz;
        double _t5 = _rotationz * _rotationw;
        double _t24 = Math.fma(_rotationx, _rotationz, _t3) * _t0;
        double _t27 = Math.fma(_rotationx, _rotationy, -_t5) * _t2;
        double _t30 = Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex);
        dest.put(destOffset, Math.fma(_m20, _t24, Math.fma(_m00, _t30, _m10 * _t27)));
        dest.put(destOffset + 1, Math.fma(_m21, _t24, Math.fma(_m01, _t30, _m11 * _t27)));
        dest.put(destOffset + 2, Math.fma(_m22, _t24, Math.fma(_m02, _t30, _m12 * _t27)));
        dest.put(destOffset + 3, Math.fma(_m03, _t30, Math.fma(_m13, _t27, Math.fma(_m23, _t24, _translationx))));
        return composeTRSMul_apiGet_s169f61de_2(dest, destOffset, _translationy, _translationz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, Math.fma(_rotationx, _rotationz, -_t3) * _t1, Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez));
    }

    /** Piece 3 of {@code composeTRSMul_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer composeTRSMul_apiGet_s169f61de_2(java.nio.DoubleBuffer dest, int destOffset, double _translationy, double _translationz, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t25, double _t26, double _t28, double _t29, double _t31, double _t32) {
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

    public static java.nio.DoubleBuffer lookAlong_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer lookAlong_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.lookAlong(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.lookAlong_apiGet(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer lookAlong_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
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
        return lookAlong_apiGet_s60ad30e0_1(dest, destOffset, upX, upY, upZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t18, _t19, Math.fma(_t17, _t11, upZ), Math.fma(_t18, _t13, -(_t19 * _t12)));
    }

    /** Piece 2 of {@code lookAlong_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer lookAlong_apiGet_s60ad30e0_1(java.nio.DoubleBuffer dest, int destOffset, double upX, double upY, double upZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t11, double _t12, double _t13, double _t18, double _t19, double _t20, double _t27) {
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
        dest.put(destOffset, Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39)));
        dest.put(destOffset + 1, Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48)));
        dest.put(destOffset + 2, Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13)));
        dest.put(destOffset + 3, _self03);
        return lookAlong_apiGet_s60ad30e0_2(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code lookAlong_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer lookAlong_apiGet_s60ad30e0_2(java.nio.DoubleBuffer dest, int destOffset, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39, double _t46, double _t47, double _t48) {
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

    public static java.nio.DoubleBuffer lookAlong_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer dir, int dirOffset, java.nio.DoubleBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset * 8L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset * 8L;
        Double3x4OpsKernelsAddress.lookAlong_unsafe(_destBase, _srcBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.DoubleBuffer lookAlong_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer dir, int dirOffset, java.nio.DoubleBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && dir.hasArray() && dirOffset >= 0 && dirOffset <= dir.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            Double3x4Ops.lookAlong(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, dir.array(), dir.arrayOffset() + dirOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.lookAlong_apiGet(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer lookAlong_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer dir, int dirOffset, java.nio.DoubleBuffer up, int upOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _dirx = dir.get(dirOffset);
        double _diry = dir.get(dirOffset + 1);
        double _dirz = dir.get(dirOffset + 2);
        double _upx = up.get(upOffset);
        double _upy = up.get(upOffset + 1);
        double _upz = up.get(upOffset + 2);
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
        return lookAlong_apiGet_s33ef3e0a_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _upx, _upy, _upz, _t11, _t12, _t13, -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13)));
    }

    /** Piece 2 of {@code lookAlong_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer lookAlong_apiGet_s33ef3e0a_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t17) {
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
        dest.put(destOffset, Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39)));
        return lookAlong_apiGet_s33ef3e0a_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t11, _t12, _t13, _t37, _t38, _t39, Math.fma(_t39, _t12, -(_t38 * _t13)), Math.fma(_t37, _t13, -(_t39 * _t11)), Math.fma(_t38, _t11, -(_t37 * _t12)));
    }

    /** Piece 3 of {@code lookAlong_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer lookAlong_apiGet_s33ef3e0a_2(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39, double _t46, double _t47, double _t48) {
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

    public static java.nio.DoubleBuffer lookAt_lh(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsTypedBuffer.lookAt_lh_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return Double3x4OpsKernelsTypedBuffer.lookAt_lh_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.nio.DoubleBuffer lookAt_lh_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.lookAt_lh_unsafe(_destBase, _srcBase, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer lookAt_lh_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4OpsKernelsArray.lookAt_lh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.lookAt_lh_apiGet(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer lookAt_lh_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
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
        return lookAt_lh_apiGet_s18f87444_3(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)), _t23, Math.fma(_t23, _t14, upX));
    }

    /** Part 1 of {@code lookAt_lh_apiGet}, split to fit the inline budget; reached only through it. */
    private static double lookAt_lh_apiGet_s18f87444_1(java.nio.DoubleBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _t14, double _t16, double _t15, double _t22, double _t43, double _t45, double _t44, double _t54, double _t55, double _t56, double _t58) {
        double _t60 = Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55));
        dest.put(destOffset, Math.fma(_self02, _t14, Math.fma(_self00, _t43, _self01 * _t54)));
        dest.put(destOffset + 1, Math.fma(_self02, _t16, Math.fma(_self00, _t45, _self01 * _t55)));
        dest.put(destOffset + 2, Math.fma(_self02, _t15, Math.fma(_self00, _t44, _self01 * _t56)));
        dest.put(destOffset + 3, Math.fma(-_self00, _t58, Math.fma(-_self01, _t60, Math.fma(-_self02, _t22, _self03))));
        dest.put(destOffset + 4, Math.fma(_self12, _t14, Math.fma(_self10, _t43, _self11 * _t54)));
        dest.put(destOffset + 5, Math.fma(_self12, _t16, Math.fma(_self10, _t45, _self11 * _t55)));
        dest.put(destOffset + 6, Math.fma(_self12, _t15, Math.fma(_self10, _t44, _self11 * _t56)));
        dest.put(destOffset + 7, Math.fma(-_self10, _t58, Math.fma(-_self11, _t60, Math.fma(-_self12, _t22, _self13))));
        dest.put(destOffset + 8, Math.fma(_self22, _t14, Math.fma(_self20, _t43, _self21 * _t54)));
        dest.put(destOffset + 9, Math.fma(_self22, _t16, Math.fma(_self20, _t45, _self21 * _t55)));
        return _t60;
    }

    /** Part 2 of {@code lookAt_lh_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer lookAt_lh_apiGet_s18f87444_2(java.nio.DoubleBuffer dest, int destOffset, double _self20, double _self21, double _self22, double _self23, double _t15, double _t22, double _t44, double _t56, double _t58, double _t60) {
        dest.put(destOffset + 10, Math.fma(_self22, _t15, Math.fma(_self20, _t44, _self21 * _t56)));
        dest.put(destOffset + 11, Math.fma(-_self20, _t58, Math.fma(-_self21, _t60, Math.fma(-_self22, _t22, _self23))));
        return dest;
    }

    /** Piece 2 of {@code lookAt_lh_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer lookAt_lh_apiGet_s18f87444_3(java.nio.DoubleBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16, double _t22, double _t23, double _t24) {
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
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        double _t58 = Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45));
        return lookAt_lh_apiGet_s18f87444_2(dest, destOffset, _self20, _self21, _self22, _self23, _t15, _t22, _t44, _t56, _t58, lookAt_lh_apiGet_s18f87444_1(dest, destOffset, eyeX, eyeY, eyeZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _t14, _t16, _t15, _t22, _t43, _t45, _t44, Math.fma(_t44, _t16, -(_t45 * _t15)), Math.fma(_t43, _t15, -(_t44 * _t14)), _t56, _t58));
    }

    public static java.nio.DoubleBuffer lookAt_rh(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsTypedBuffer.lookAt_rh_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return Double3x4OpsKernelsTypedBuffer.lookAt_rh_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.nio.DoubleBuffer lookAt_rh_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.lookAt_rh_unsafe(_destBase, _srcBase, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer lookAt_rh_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4OpsKernelsArray.lookAt_rh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.lookAt_rh_apiGet(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer lookAt_rh_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
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
        double _t26 = -Math.fma(upZ, _t18, Math.fma(upX, _t17, upY * _t19));
        return lookAt_rh_apiGet_s7e8e19be_3(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_self22, _t17, _t18, _t19, Math.fma(eyeZ, _t18, Math.fma(eyeX, _t17, eyeY * _t19)), _t26, Math.fma(_t26, _t19, upY));
    }

    /** Part 1 of {@code lookAt_rh_apiGet}, split to fit the inline budget; reached only through it. */
    private static double lookAt_rh_apiGet_s7e8e19be_1(java.nio.DoubleBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _t0, double _t1, double _t2, double _t17, double _t19, double _t18, double _t25, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61) {
        double _t63 = Math.fma(eyeZ, _t59, Math.fma(eyeX, _t57, eyeY * _t58));
        dest.put(destOffset, Math.fma(_t0, _t17, Math.fma(_self00, _t46, _self01 * _t57)));
        dest.put(destOffset + 1, Math.fma(_t0, _t19, Math.fma(_self00, _t47, _self01 * _t58)));
        dest.put(destOffset + 2, Math.fma(_t0, _t18, Math.fma(_self00, _t48, _self01 * _t59)));
        dest.put(destOffset + 3, Math.fma(-_self00, _t61, Math.fma(-_self01, _t63, Math.fma(_self02, _t25, _self03))));
        dest.put(destOffset + 4, Math.fma(_t1, _t17, Math.fma(_self10, _t46, _self11 * _t57)));
        dest.put(destOffset + 5, Math.fma(_t1, _t19, Math.fma(_self10, _t47, _self11 * _t58)));
        dest.put(destOffset + 6, Math.fma(_t1, _t18, Math.fma(_self10, _t48, _self11 * _t59)));
        dest.put(destOffset + 7, Math.fma(-_self10, _t61, Math.fma(-_self11, _t63, Math.fma(_self12, _t25, _self13))));
        dest.put(destOffset + 8, Math.fma(_t2, _t17, Math.fma(_self20, _t46, _self21 * _t57)));
        dest.put(destOffset + 9, Math.fma(_t2, _t19, Math.fma(_self20, _t47, _self21 * _t58)));
        return _t63;
    }

    /** Part 2 of {@code lookAt_rh_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer lookAt_rh_apiGet_s7e8e19be_2(java.nio.DoubleBuffer dest, int destOffset, double _self20, double _self21, double _self22, double _self23, double _t2, double _t18, double _t25, double _t48, double _t59, double _t61, double _t63) {
        dest.put(destOffset + 10, Math.fma(_t2, _t18, Math.fma(_self20, _t48, _self21 * _t59)));
        dest.put(destOffset + 11, Math.fma(-_self20, _t61, Math.fma(-_self21, _t63, Math.fma(_self22, _t25, _self23))));
        return dest;
    }

    /** Piece 2 of {@code lookAt_rh_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer lookAt_rh_apiGet_s7e8e19be_3(java.nio.DoubleBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t2, double _t17, double _t18, double _t19, double _t25, double _t26, double _t27) {
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
        double _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        double _t61 = Math.fma(eyeZ, _t48, Math.fma(eyeX, _t46, eyeY * _t47));
        return lookAt_rh_apiGet_s7e8e19be_2(dest, destOffset, _self20, _self21, _self22, _self23, _t2, _t18, _t25, _t48, _t59, _t61, lookAt_rh_apiGet_s7e8e19be_1(dest, destOffset, eyeX, eyeY, eyeZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, (-_self02), (-_self12), _t2, _t17, _t19, _t18, _t25, _t46, _t47, _t48, Math.fma(_t47, _t18, -(_t48 * _t19)), Math.fma(_t48, _t17, -(_t46 * _t18)), _t59, _t61));
    }

    public static java.nio.DoubleBuffer lookAt_lh(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer eye, int eyeOffset, java.nio.DoubleBuffer center, int centerOffset, java.nio.DoubleBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && eye.isDirect() && eye.order() == java.nio.ByteOrder.nativeOrder() && center.isDirect() && center.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsTypedBuffer.lookAt_lh_unsafe(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return Double3x4OpsKernelsTypedBuffer.lookAt_lh_api(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.nio.DoubleBuffer lookAt_lh_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer eye, int eyeOffset, java.nio.DoubleBuffer center, int centerOffset, java.nio.DoubleBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _eyeBase = UnsafeOpsHolder.U.getLong(eye, UnsafeCopy.BB_ADDRESS_OFFSET) + eyeOffset * 8L;
        long _centerBase = UnsafeOpsHolder.U.getLong(center, UnsafeCopy.BB_ADDRESS_OFFSET) + centerOffset * 8L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset * 8L;
        Double3x4OpsKernelsAddress.lookAt_lh_unsafe(_destBase, _srcBase, _eyeBase, _centerBase, _upBase);
        return dest;
    }

    public static java.nio.DoubleBuffer lookAt_lh_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer eye, int eyeOffset, java.nio.DoubleBuffer center, int centerOffset, java.nio.DoubleBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && eye.hasArray() && eyeOffset >= 0 && eyeOffset <= eye.limit() - 3 && center.hasArray() && centerOffset >= 0 && centerOffset <= center.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            Double3x4OpsKernelsArray.lookAt_lh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, eye.array(), eye.arrayOffset() + eyeOffset, center.array(), center.arrayOffset() + centerOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.lookAt_lh_apiGet(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer lookAt_lh_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer eye, int eyeOffset, java.nio.DoubleBuffer center, int centerOffset, java.nio.DoubleBuffer up, int upOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _eyex = eye.get(eyeOffset);
        double _eyey = eye.get(eyeOffset + 1);
        double _eyez = eye.get(eyeOffset + 2);
        double _upx = up.get(upOffset);
        double _upy = up.get(upOffset + 1);
        double _upz = up.get(upOffset + 2);
        double _t0 = center.get(centerOffset + 2) - _eyez;
        double _t1 = center.get(centerOffset) - _eyex;
        double _t2 = center.get(centerOffset + 1) - _eyey;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        return lookAt_lh_apiGet_sa21dfd62_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t0, _t1, _t2, _t9, (1.0 / java.lang.Math.sqrt(_t9)));
    }

    /** Piece 2 of {@code lookAt_lh_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer lookAt_lh_apiGet_sa21dfd62_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _t0, double _t1, double _t2, double _t9, double _t10) {
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
        return lookAt_lh_apiGet_sa21dfd62_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _eyex, _eyey, _eyez, _t14, _t15, _t16, Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)), _t43, _t44, _t45);
    }

    /** Piece 3 of {@code lookAt_lh_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer lookAt_lh_apiGet_sa21dfd62_2(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _eyex, double _eyey, double _eyez, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45) {
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        double _t58 = Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45));
        double _t60 = Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55));
        dest.put(destOffset, Math.fma(_self02, _t14, Math.fma(_self00, _t43, _self01 * _t54)));
        dest.put(destOffset + 1, Math.fma(_self02, _t16, Math.fma(_self00, _t45, _self01 * _t55)));
        dest.put(destOffset + 2, Math.fma(_self02, _t15, Math.fma(_self00, _t44, _self01 * _t56)));
        dest.put(destOffset + 3, Math.fma(-_self00, _t58, Math.fma(-_self01, _t60, Math.fma(-_self02, _t22, _self03))));
        dest.put(destOffset + 4, Math.fma(_self12, _t14, Math.fma(_self10, _t43, _self11 * _t54)));
        dest.put(destOffset + 5, Math.fma(_self12, _t16, Math.fma(_self10, _t45, _self11 * _t55)));
        return lookAt_lh_apiGet_sa21dfd62_3(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t14, _t15, _t16, _t22, _t43, _t44, _t45, _t54, _t55, _t56, _t58, _t60);
    }

    /** Piece 4 of {@code lookAt_lh_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer lookAt_lh_apiGet_sa21dfd62_3(java.nio.DoubleBuffer dest, int destOffset, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t58, double _t60) {
        dest.put(destOffset + 6, Math.fma(_self12, _t15, Math.fma(_self10, _t44, _self11 * _t56)));
        dest.put(destOffset + 7, Math.fma(-_self10, _t58, Math.fma(-_self11, _t60, Math.fma(-_self12, _t22, _self13))));
        dest.put(destOffset + 8, Math.fma(_self22, _t14, Math.fma(_self20, _t43, _self21 * _t54)));
        dest.put(destOffset + 9, Math.fma(_self22, _t16, Math.fma(_self20, _t45, _self21 * _t55)));
        dest.put(destOffset + 10, Math.fma(_self22, _t15, Math.fma(_self20, _t44, _self21 * _t56)));
        dest.put(destOffset + 11, Math.fma(-_self20, _t58, Math.fma(-_self21, _t60, Math.fma(-_self22, _t22, _self23))));
        return dest;
    }

    public static java.nio.DoubleBuffer lookAt_rh(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer eye, int eyeOffset, java.nio.DoubleBuffer center, int centerOffset, java.nio.DoubleBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && eye.isDirect() && eye.order() == java.nio.ByteOrder.nativeOrder() && center.isDirect() && center.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsTypedBuffer.lookAt_rh_unsafe(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return Double3x4OpsKernelsTypedBuffer.lookAt_rh_api(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.nio.DoubleBuffer lookAt_rh_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer eye, int eyeOffset, java.nio.DoubleBuffer center, int centerOffset, java.nio.DoubleBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _eyeBase = UnsafeOpsHolder.U.getLong(eye, UnsafeCopy.BB_ADDRESS_OFFSET) + eyeOffset * 8L;
        long _centerBase = UnsafeOpsHolder.U.getLong(center, UnsafeCopy.BB_ADDRESS_OFFSET) + centerOffset * 8L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset * 8L;
        Double3x4OpsKernelsAddress.lookAt_rh_unsafe(_destBase, _srcBase, _eyeBase, _centerBase, _upBase);
        return dest;
    }

    public static java.nio.DoubleBuffer lookAt_rh_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer eye, int eyeOffset, java.nio.DoubleBuffer center, int centerOffset, java.nio.DoubleBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && eye.hasArray() && eyeOffset >= 0 && eyeOffset <= eye.limit() - 3 && center.hasArray() && centerOffset >= 0 && centerOffset <= center.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            Double3x4OpsKernelsArray.lookAt_rh(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, eye.array(), eye.arrayOffset() + eyeOffset, center.array(), center.arrayOffset() + centerOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.lookAt_rh_apiGet(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer lookAt_rh_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer eye, int eyeOffset, java.nio.DoubleBuffer center, int centerOffset, java.nio.DoubleBuffer up, int upOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _eyex = eye.get(eyeOffset);
        double _eyey = eye.get(eyeOffset + 1);
        double _eyez = eye.get(eyeOffset + 2);
        double _upx = up.get(upOffset);
        double _upy = up.get(upOffset + 1);
        double _upz = up.get(upOffset + 2);
        double _t3 = center.get(centerOffset + 2) - _eyez;
        double _t4 = center.get(centerOffset) - _eyex;
        double _t5 = center.get(centerOffset + 1) - _eyey;
        double _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        return lookAt_rh_apiGet_s2ef8b94c_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _eyex, _eyey, _eyez, _upx, _upy, _upz, -_self02, -_self12, -_self22, _t3, _t4, _t5, _t12, (1.0 / java.lang.Math.sqrt(_t12)));
    }

    /** Piece 2 of {@code lookAt_rh_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer lookAt_rh_apiGet_s2ef8b94c_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t12, double _t13) {
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
        return lookAt_rh_apiGet_s2ef8b94c_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t0, _t1, _t2, _t17, _t18, _t19, Math.fma(_eyez, _t18, Math.fma(_eyex, _t17, _eyey * _t19)), _t36, _t37, _t38, _t41, (1.0 / java.lang.Math.sqrt(_t41)));
    }

    /** Piece 3 of {@code lookAt_rh_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer lookAt_rh_apiGet_s2ef8b94c_2(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _t0, double _t1, double _t2, double _t17, double _t18, double _t19, double _t25, double _t36, double _t37, double _t38, double _t41, double _t42) {
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
        dest.put(destOffset, Math.fma(_t0, _t17, Math.fma(_self00, _t46, _self01 * _t57)));
        dest.put(destOffset + 1, Math.fma(_t0, _t19, Math.fma(_self00, _t47, _self01 * _t58)));
        dest.put(destOffset + 2, Math.fma(_t0, _t18, Math.fma(_self00, _t48, _self01 * _t59)));
        return lookAt_rh_apiGet_s2ef8b94c_3(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t1, _t2, _t17, _t18, _t19, _t25, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(_eyez, _t48, Math.fma(_eyex, _t46, _eyey * _t47)), Math.fma(_eyez, _t59, Math.fma(_eyex, _t57, _eyey * _t58)));
    }

    /** Piece 4 of {@code lookAt_rh_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer lookAt_rh_apiGet_s2ef8b94c_3(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t1, double _t2, double _t17, double _t18, double _t19, double _t25, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
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

    public static java.nio.DoubleBuffer makeBillboardCylindrical_unsafe(java.nio.DoubleBuffer dest, int destOffset, double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeBillboardCylindrical_unsafe(_destBase, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeBillboardCylindrical_api(java.nio.DoubleBuffer dest, int destOffset, double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ, double upX, double upY, double upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeBillboardCylindrical(dest.array(), dest.arrayOffset() + destOffset, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ, upX, upY, upZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeBillboardCylindrical_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ, upX, upY, upZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeBillboardCylindrical_apiGet(dest, destOffset, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeBillboardCylindrical_apiGet(java.nio.DoubleBuffer dest, int destOffset, double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ, double upX, double upY, double upZ) {
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
        return makeBillboardCylindrical_apiGet_sffcfaeb3_1(dest, destOffset, objPosX, objPosY, objPosZ, upX, upY, upZ, _t36, _t37, _t38, Math.fma(upY, _t36, -(upX * _t37)), Math.fma(upX, _t38, -(upZ * _t36)), Math.fma(upZ, _t37, -(upY * _t38)));
    }

    /** Piece 2 of {@code makeBillboardCylindrical_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeBillboardCylindrical_apiGet_sffcfaeb3_1(java.nio.DoubleBuffer dest, int destOffset, double objPosX, double objPosY, double objPosZ, double upX, double upY, double upZ, double _t36, double _t37, double _t38, double _t45, double _t46, double _t47) {
        double _t50 = Math.fma(_t45, _t45, Math.fma(_t46, _t46, _t47 * _t47));
        double _t51 = (1.0 / java.lang.Math.sqrt(_t50));
        if (_t50 != 0.0) {
            dest.put(destOffset + 2, _t47 * _t51);
            dest.put(destOffset + 6, _t46 * _t51);
            dest.put(destOffset + 10, _t45 * _t51);
        } else {
            dest.put(destOffset + 2, 0.0);
            dest.put(destOffset + 6, 0.0);
            dest.put(destOffset + 10, 0.0);
        }
        dest.put(destOffset, _t36);
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

    public static java.nio.DoubleBuffer makeBillboardCylindrical_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer objPos, int objPosOffset, java.nio.DoubleBuffer targetPos, int targetPosOffset, java.nio.DoubleBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _objPosBase = UnsafeOpsHolder.U.getLong(objPos, UnsafeCopy.BB_ADDRESS_OFFSET) + objPosOffset * 8L;
        long _targetPosBase = UnsafeOpsHolder.U.getLong(targetPos, UnsafeCopy.BB_ADDRESS_OFFSET) + targetPosOffset * 8L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset * 8L;
        Double3x4OpsKernelsAddress.makeBillboardCylindrical_unsafe(_destBase, _objPosBase, _targetPosBase, _upBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeBillboardCylindrical_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer objPos, int objPosOffset, java.nio.DoubleBuffer targetPos, int targetPosOffset, java.nio.DoubleBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && objPos.hasArray() && objPosOffset >= 0 && objPosOffset <= objPos.limit() - 3 && targetPos.hasArray() && targetPosOffset >= 0 && targetPosOffset <= targetPos.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            Double3x4Ops.makeBillboardCylindrical(dest.array(), dest.arrayOffset() + destOffset, objPos.array(), objPos.arrayOffset() + objPosOffset, targetPos.array(), targetPos.arrayOffset() + targetPosOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && objPos.order() == java.nio.ByteOrder.nativeOrder() && targetPos.order() == java.nio.ByteOrder.nativeOrder() && up.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.makeBillboardCylindrical_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(objPos.duplicate().position(0)), objPosOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(targetPos.duplicate().position(0)), targetPosOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(up.duplicate().position(0)), upOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeBillboardCylindrical_apiGet(dest, destOffset, objPos, objPosOffset, targetPos, targetPosOffset, up, upOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeBillboardCylindrical_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer objPos, int objPosOffset, java.nio.DoubleBuffer targetPos, int targetPosOffset, java.nio.DoubleBuffer up, int upOffset) {
        double _objPosx = objPos.get(objPosOffset);
        double _objPosy = objPos.get(objPosOffset + 1);
        double _objPosz = objPos.get(objPosOffset + 2);
        double _upx = up.get(upOffset);
        double _upy = up.get(upOffset + 1);
        double _upz = up.get(upOffset + 2);
        double _t3 = targetPos.get(targetPosOffset + 2) - _objPosz;
        double _t4 = targetPos.get(targetPosOffset) - _objPosx;
        double _t5 = targetPos.get(targetPosOffset + 1) - _objPosy;
        double _t14 = Math.fma(_upz, _t3, Math.fma(_upx, _t4, _upy * _t5));
        double _t15 = Math.fma(-_upy, _t14, _t5);
        double _t16 = Math.fma(-_upx, _t14, _t4);
        double _t17 = Math.fma(-_upz, _t14, _t3);
        double _t26 = Math.fma(_upx, _t15, -(_upy * _t16));
        double _t27 = Math.fma(_upy, _t17, -(_upz * _t15));
        double _t28 = Math.fma(_upz, _t16, -(_upx * _t17));
        double _t31 = Math.fma(_t26, _t26, Math.fma(_t27, _t27, _t28 * _t28));
        return makeBillboardCylindrical_apiGet_s6f1609a1_1(dest, destOffset, _objPosx, _objPosy, _objPosz, _upx, _upy, _upz, _t3, _t4, _t5, _t26, _t27, _t28, _t31, (1.0 / java.lang.Math.sqrt(_t31)));
    }

    /** Piece 2 of {@code makeBillboardCylindrical_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeBillboardCylindrical_apiGet_s6f1609a1_1(java.nio.DoubleBuffer dest, int destOffset, double _objPosx, double _objPosy, double _objPosz, double _upx, double _upy, double _upz, double _t3, double _t4, double _t5, double _t26, double _t27, double _t28, double _t31, double _t32) {
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
            dest.put(destOffset + 2, _t47 * _t51);
            dest.put(destOffset + 6, _t46 * _t51);
            dest.put(destOffset + 10, _t45 * _t51);
        } else {
            dest.put(destOffset + 2, 0.0);
            dest.put(destOffset + 6, 0.0);
            dest.put(destOffset + 10, 0.0);
        }
        dest.put(destOffset, _t36);
        dest.put(destOffset + 1, _upx);
        dest.put(destOffset + 3, _objPosx);
        dest.put(destOffset + 4, _t37);
        dest.put(destOffset + 5, _upy);
        return makeBillboardCylindrical_apiGet_s6f1609a1_2(dest, destOffset, _objPosy, _objPosz, _upz, _t38);
    }

    /** Piece 3 of {@code makeBillboardCylindrical_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeBillboardCylindrical_apiGet_s6f1609a1_2(java.nio.DoubleBuffer dest, int destOffset, double _objPosy, double _objPosz, double _upz, double _t38) {
        dest.put(destOffset + 7, _objPosy);
        dest.put(destOffset + 8, _t38);
        dest.put(destOffset + 9, _upz);
        dest.put(destOffset + 11, _objPosz);
        return dest;
    }

    public static java.nio.DoubleBuffer makeBillboardSpherical_unsafe(java.nio.DoubleBuffer dest, int destOffset, double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeBillboardSpherical_unsafe(_destBase, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeBillboardSpherical_api(java.nio.DoubleBuffer dest, int destOffset, double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ, double upX, double upY, double upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeBillboardSpherical(dest.array(), dest.arrayOffset() + destOffset, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ, upX, upY, upZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeBillboardSpherical_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ, upX, upY, upZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeBillboardSpherical_apiGet(dest, destOffset, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeBillboardSpherical_apiGet(java.nio.DoubleBuffer dest, int destOffset, double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ, double upX, double upY, double upZ) {
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
        return makeBillboardSpherical_apiGet_se89b93aa_1(dest, destOffset, objPosX, objPosY, objPosZ, upX, upY, upZ, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeBillboardSpherical_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeBillboardSpherical_apiGet_se89b93aa_1(java.nio.DoubleBuffer dest, int destOffset, double objPosX, double objPosY, double objPosZ, double upX, double upY, double upZ, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35, double _t36) {
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
        dest.put(destOffset, _t40);
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

    public static java.nio.DoubleBuffer makeBillboardSpherical_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer objPos, int objPosOffset, java.nio.DoubleBuffer targetPos, int targetPosOffset, java.nio.DoubleBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _objPosBase = UnsafeOpsHolder.U.getLong(objPos, UnsafeCopy.BB_ADDRESS_OFFSET) + objPosOffset * 8L;
        long _targetPosBase = UnsafeOpsHolder.U.getLong(targetPos, UnsafeCopy.BB_ADDRESS_OFFSET) + targetPosOffset * 8L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset * 8L;
        Double3x4OpsKernelsAddress.makeBillboardSpherical_unsafe(_destBase, _objPosBase, _targetPosBase, _upBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeBillboardSpherical_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer objPos, int objPosOffset, java.nio.DoubleBuffer targetPos, int targetPosOffset, java.nio.DoubleBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && objPos.hasArray() && objPosOffset >= 0 && objPosOffset <= objPos.limit() - 3 && targetPos.hasArray() && targetPosOffset >= 0 && targetPosOffset <= targetPos.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            Double3x4Ops.makeBillboardSpherical(dest.array(), dest.arrayOffset() + destOffset, objPos.array(), objPos.arrayOffset() + objPosOffset, targetPos.array(), targetPos.arrayOffset() + targetPosOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && objPos.order() == java.nio.ByteOrder.nativeOrder() && targetPos.order() == java.nio.ByteOrder.nativeOrder() && up.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.makeBillboardSpherical_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(objPos.duplicate().position(0)), objPosOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(targetPos.duplicate().position(0)), targetPosOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(up.duplicate().position(0)), upOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeBillboardSpherical_apiGet(dest, destOffset, objPos, objPosOffset, targetPos, targetPosOffset, up, upOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeBillboardSpherical_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer objPos, int objPosOffset, java.nio.DoubleBuffer targetPos, int targetPosOffset, java.nio.DoubleBuffer up, int upOffset) {
        double _objPosx = objPos.get(objPosOffset);
        double _objPosy = objPos.get(objPosOffset + 1);
        double _objPosz = objPos.get(objPosOffset + 2);
        double _upx = up.get(upOffset);
        double _upy = up.get(upOffset + 1);
        double _upz = up.get(upOffset + 2);
        double _t0 = targetPos.get(targetPosOffset + 2) - _objPosz;
        double _t1 = targetPos.get(targetPosOffset) - _objPosx;
        double _t2 = targetPos.get(targetPosOffset + 1) - _objPosy;
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
        return makeBillboardSpherical_apiGet_s50f0f036_1(dest, destOffset, _objPosx, _objPosy, _objPosz, _upx, _upy, _upz, _t14, _t15, _t16, Math.fma(_t21, _t16, -(_t22 * _t15)), Math.fma(_t22, _t14, -(_t23 * _t16)), Math.fma(_t23, _t15, -(_t21 * _t14)));
    }

    /** Piece 2 of {@code makeBillboardSpherical_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeBillboardSpherical_apiGet_s50f0f036_1(java.nio.DoubleBuffer dest, int destOffset, double _objPosx, double _objPosy, double _objPosz, double _upx, double _upy, double _upz, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32) {
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
        dest.put(destOffset, _t40);
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

    public static java.nio.DoubleBuffer makeBillboardSphericalShortest_unsafe(java.nio.DoubleBuffer dest, int destOffset, double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeBillboardSphericalShortest_unsafe(_destBase, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeBillboardSphericalShortest_api(java.nio.DoubleBuffer dest, int destOffset, double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeBillboardSphericalShortest(dest.array(), dest.arrayOffset() + destOffset, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeBillboardSphericalShortest_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeBillboardSphericalShortest_apiGet(dest, destOffset, objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeBillboardSphericalShortest_apiGet(java.nio.DoubleBuffer dest, int destOffset, double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ) {
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
        dest.put(destOffset, _t32);
        dest.put(destOffset + 1, _t29);
        dest.put(destOffset + 2, _t30);
        dest.put(destOffset + 3, objPosX);
        dest.put(destOffset + 4, _t29);
        dest.put(destOffset + 5, 1.0 - _t26);
        dest.put(destOffset + 6, _t27);
        dest.put(destOffset + 7, objPosY);
        return makeBillboardSphericalShortest_apiGet_sf4259b7f_1(dest, destOffset, objPosZ, _t26, _t27, _t30, _t32);
    }

    /** Piece 2 of {@code makeBillboardSphericalShortest_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeBillboardSphericalShortest_apiGet_sf4259b7f_1(java.nio.DoubleBuffer dest, int destOffset, double objPosZ, double _t26, double _t27, double _t30, double _t32) {
        dest.put(destOffset + 8, -_t30);
        dest.put(destOffset + 9, -_t27);
        dest.put(destOffset + 10, _t32 - _t26);
        dest.put(destOffset + 11, objPosZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeBillboardSphericalShortest_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer objPos, int objPosOffset, java.nio.DoubleBuffer targetPos, int targetPosOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _objPosBase = UnsafeOpsHolder.U.getLong(objPos, UnsafeCopy.BB_ADDRESS_OFFSET) + objPosOffset * 8L;
        long _targetPosBase = UnsafeOpsHolder.U.getLong(targetPos, UnsafeCopy.BB_ADDRESS_OFFSET) + targetPosOffset * 8L;
        Double3x4OpsKernelsAddress.makeBillboardSphericalShortest_unsafe(_destBase, _objPosBase, _targetPosBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeBillboardSphericalShortest_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer objPos, int objPosOffset, java.nio.DoubleBuffer targetPos, int targetPosOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && objPos.hasArray() && objPosOffset >= 0 && objPosOffset <= objPos.limit() - 3 && targetPos.hasArray() && targetPosOffset >= 0 && targetPosOffset <= targetPos.limit() - 3) {
            Double3x4Ops.makeBillboardSphericalShortest(dest.array(), dest.arrayOffset() + destOffset, objPos.array(), objPos.arrayOffset() + objPosOffset, targetPos.array(), targetPos.arrayOffset() + targetPosOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && objPos.order() == java.nio.ByteOrder.nativeOrder() && targetPos.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.makeBillboardSphericalShortest_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(objPos.duplicate().position(0)), objPosOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(targetPos.duplicate().position(0)), targetPosOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeBillboardSphericalShortest_apiGet(dest, destOffset, objPos, objPosOffset, targetPos, targetPosOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeBillboardSphericalShortest_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer objPos, int objPosOffset, java.nio.DoubleBuffer targetPos, int targetPosOffset) {
        double _objPosx = objPos.get(objPosOffset);
        double _objPosy = objPos.get(objPosOffset + 1);
        double _objPosz = objPos.get(objPosOffset + 2);
        double _t0 = targetPos.get(targetPosOffset + 2) - _objPosz;
        double _t1 = targetPos.get(targetPosOffset) - _objPosx;
        double _t2 = targetPos.get(targetPosOffset + 1) - _objPosy;
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
        double _t29 = -(_t3 * _sp0);
        double _t30 = _sp0 * _t14;
        double _t32 = 1.0 - (_sp0 + _sp0) * _t15;
        dest.put(destOffset, _t32);
        dest.put(destOffset + 1, _t29);
        dest.put(destOffset + 2, _t30);
        dest.put(destOffset + 3, _objPosx);
        dest.put(destOffset + 4, _t29);
        dest.put(destOffset + 5, 1.0 - _t26);
        return makeBillboardSphericalShortest_apiGet_sc37f7043_1(dest, destOffset, _objPosy, _objPosz, _t26, _sp1 * _t14, _t30, _t32);
    }

    /** Piece 2 of {@code makeBillboardSphericalShortest_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeBillboardSphericalShortest_apiGet_sc37f7043_1(java.nio.DoubleBuffer dest, int destOffset, double _objPosy, double _objPosz, double _t26, double _t27, double _t30, double _t32) {
        dest.put(destOffset + 6, _t27);
        dest.put(destOffset + 7, _objPosy);
        dest.put(destOffset + 8, -_t30);
        dest.put(destOffset + 9, -_t27);
        dest.put(destOffset + 10, _t32 - _t26);
        dest.put(destOffset + 11, _objPosz);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromDualQuat_unsafe(java.nio.DoubleBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeFromDualQuat_unsafe(_destBase, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
        return dest;
    }

    public static java.nio.DoubleBuffer makeFromDualQuat_api(java.nio.DoubleBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeFromDualQuat(dest.array(), dest.arrayOffset() + destOffset, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeFromDualQuat_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeFromDualQuat_apiGet(dest, destOffset, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
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
        dest.put(destOffset, Math.fma(-2.0, _t0, _t6));
        dest.put(destOffset + 1, Math.fma(-2.0, _t2, _sp0 * dqRY));
        dest.put(destOffset + 2, 2.0 * Math.fma(dqRX, dqRZ, _t3));
        dest.put(destOffset + 3, 2.0 * (Math.fma(dqRY, dqDZ, -(dqRZ * dqDY)) + Math.fma(dqRW, dqDX, -(dqRX * dqDW))));
        dest.put(destOffset + 4, 2.0 * Math.fma(dqRX, dqRY, _t2));
        dest.put(destOffset + 5, Math.fma(-2.0, _t4, _t6));
        dest.put(destOffset + 6, Math.fma(-2.0, dqRX * dqRW, _t5 + _t5));
        dest.put(destOffset + 7, 2.0 * (Math.fma(dqRZ, dqDX, -(dqRX * dqDZ)) + Math.fma(dqRW, dqDY, -(dqRY * dqDW))));
        dest.put(destOffset + 8, Math.fma(-2.0, _t3, _sp0 * dqRZ));
        return makeFromDualQuat_apiGet_s4284fb5e_1(dest, destOffset, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW, _t0, _t4, _t5);
    }

    /** Piece 2 of {@code makeFromDualQuat_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeFromDualQuat_apiGet_s4284fb5e_1(java.nio.DoubleBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW, double _t0, double _t4, double _t5) {
        dest.put(destOffset + 9, 2.0 * Math.fma(dqRX, dqRW, _t5));
        dest.put(destOffset + 10, Math.fma(-2.0, _t4, Math.fma(-2.0, _t0, 1.0)));
        dest.put(destOffset + 11, 2.0 * (Math.fma(dqRX, dqDY, -(dqRY * dqDX)) + Math.fma(dqRW, dqDZ, -(dqRZ * dqDW))));
        return dest;
    }

    public static java.nio.DoubleBuffer makeLookAt_lh(java.nio.DoubleBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsTypedBuffer.makeLookAt_lh_unsafe(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return Double3x4OpsKernelsTypedBuffer.makeLookAt_lh_api(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.nio.DoubleBuffer makeLookAt_lh_unsafe(java.nio.DoubleBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeLookAt_lh_unsafe(_destBase, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeLookAt_lh_api(java.nio.DoubleBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4OpsKernelsArray.makeLookAt_lh(dest.array(), dest.arrayOffset() + destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeLookAt_lh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeLookAt_lh_apiGet(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeLookAt_lh_apiGet(java.nio.DoubleBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
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
        return makeLookAt_lh_apiGet_s59ce1875_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeLookAt_lh_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeLookAt_lh_apiGet_s59ce1875_1(java.nio.DoubleBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35, double _t36) {
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
        dest.put(destOffset, _t40);
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

    public static java.nio.DoubleBuffer makeLookAt_rh(java.nio.DoubleBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsTypedBuffer.makeLookAt_rh_unsafe(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return Double3x4OpsKernelsTypedBuffer.makeLookAt_rh_api(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.nio.DoubleBuffer makeLookAt_rh_unsafe(java.nio.DoubleBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeLookAt_rh_unsafe(_destBase, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeLookAt_rh_api(java.nio.DoubleBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4OpsKernelsArray.makeLookAt_rh(dest.array(), dest.arrayOffset() + destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeLookAt_rh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeLookAt_rh_apiGet(dest, destOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeLookAt_rh_apiGet(java.nio.DoubleBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
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
        return makeLookAt_rh_apiGet_s3add9f77_1(dest, destOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t30, _t31, _t32, _t35, (1.0 / java.lang.Math.sqrt(_t35)));
    }

    /** Piece 2 of {@code makeLookAt_rh_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeLookAt_rh_apiGet_s3add9f77_1(java.nio.DoubleBuffer dest, int destOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32, double _t35, double _t36) {
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
        dest.put(destOffset, _t40);
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

    public static java.nio.DoubleBuffer makeLookAt_lh(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer eye, int eyeOffset, java.nio.DoubleBuffer center, int centerOffset, java.nio.DoubleBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && eye.isDirect() && eye.order() == java.nio.ByteOrder.nativeOrder() && center.isDirect() && center.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsTypedBuffer.makeLookAt_lh_unsafe(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return Double3x4OpsKernelsTypedBuffer.makeLookAt_lh_api(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.nio.DoubleBuffer makeLookAt_lh_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer eye, int eyeOffset, java.nio.DoubleBuffer center, int centerOffset, java.nio.DoubleBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _eyeBase = UnsafeOpsHolder.U.getLong(eye, UnsafeCopy.BB_ADDRESS_OFFSET) + eyeOffset * 8L;
        long _centerBase = UnsafeOpsHolder.U.getLong(center, UnsafeCopy.BB_ADDRESS_OFFSET) + centerOffset * 8L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset * 8L;
        Double3x4OpsKernelsAddress.makeLookAt_lh_unsafe(_destBase, _eyeBase, _centerBase, _upBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeLookAt_lh_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer eye, int eyeOffset, java.nio.DoubleBuffer center, int centerOffset, java.nio.DoubleBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && eye.hasArray() && eyeOffset >= 0 && eyeOffset <= eye.limit() - 3 && center.hasArray() && centerOffset >= 0 && centerOffset <= center.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            Double3x4OpsKernelsArray.makeLookAt_lh(dest.array(), dest.arrayOffset() + destOffset, eye.array(), eye.arrayOffset() + eyeOffset, center.array(), center.arrayOffset() + centerOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && eye.order() == java.nio.ByteOrder.nativeOrder() && center.order() == java.nio.ByteOrder.nativeOrder() && up.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.makeLookAt_lh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(eye.duplicate().position(0)), eyeOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(center.duplicate().position(0)), centerOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(up.duplicate().position(0)), upOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeLookAt_lh_apiGet(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeLookAt_lh_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer eye, int eyeOffset, java.nio.DoubleBuffer center, int centerOffset, java.nio.DoubleBuffer up, int upOffset) {
        double _eyex = eye.get(eyeOffset);
        double _eyey = eye.get(eyeOffset + 1);
        double _eyez = eye.get(eyeOffset + 2);
        double _upx = up.get(upOffset);
        double _upy = up.get(upOffset + 1);
        double _upz = up.get(upOffset + 2);
        double _t0 = center.get(centerOffset + 2) - _eyez;
        double _t1 = center.get(centerOffset) - _eyex;
        double _t2 = center.get(centerOffset + 1) - _eyey;
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
        return makeLookAt_lh_apiGet_se9f79a75_1(dest, destOffset, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, Math.fma(_t21, _t16, -(_t22 * _t15)), Math.fma(_t22, _t14, -(_t23 * _t16)), Math.fma(_t23, _t15, -(_t21 * _t14)));
    }

    /** Piece 2 of {@code makeLookAt_lh_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeLookAt_lh_apiGet_se9f79a75_1(java.nio.DoubleBuffer dest, int destOffset, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32) {
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
        dest.put(destOffset, _t40);
        dest.put(destOffset + 1, _t41);
        dest.put(destOffset + 2, _t42);
        dest.put(destOffset + 3, -Math.fma(_eyez, _t42, Math.fma(_eyex, _t40, _eyey * _t41)));
        dest.put(destOffset + 4, _t49);
        dest.put(destOffset + 5, _t50);
        dest.put(destOffset + 6, _t51);
        dest.put(destOffset + 7, -Math.fma(_eyez, _t51, Math.fma(_eyex, _t49, _eyey * _t50)));
        dest.put(destOffset + 8, _t15);
        return makeLookAt_lh_apiGet_se9f79a75_2(dest, destOffset, _eyex, _eyey, _eyez, _t14, _t15, _t16);
    }

    /** Piece 3 of {@code makeLookAt_lh_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeLookAt_lh_apiGet_se9f79a75_2(java.nio.DoubleBuffer dest, int destOffset, double _eyex, double _eyey, double _eyez, double _t14, double _t15, double _t16) {
        dest.put(destOffset + 9, _t16);
        dest.put(destOffset + 10, _t14);
        dest.put(destOffset + 11, -Math.fma(_eyez, _t14, Math.fma(_eyex, _t15, _eyey * _t16)));
        return dest;
    }

    public static java.nio.DoubleBuffer makeLookAt_rh(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer eye, int eyeOffset, java.nio.DoubleBuffer center, int centerOffset, java.nio.DoubleBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && eye.isDirect() && eye.order() == java.nio.ByteOrder.nativeOrder() && center.isDirect() && center.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Double3x4OpsKernelsTypedBuffer.makeLookAt_rh_unsafe(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return Double3x4OpsKernelsTypedBuffer.makeLookAt_rh_api(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.nio.DoubleBuffer makeLookAt_rh_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer eye, int eyeOffset, java.nio.DoubleBuffer center, int centerOffset, java.nio.DoubleBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _eyeBase = UnsafeOpsHolder.U.getLong(eye, UnsafeCopy.BB_ADDRESS_OFFSET) + eyeOffset * 8L;
        long _centerBase = UnsafeOpsHolder.U.getLong(center, UnsafeCopy.BB_ADDRESS_OFFSET) + centerOffset * 8L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset * 8L;
        Double3x4OpsKernelsAddress.makeLookAt_rh_unsafe(_destBase, _eyeBase, _centerBase, _upBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeLookAt_rh_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer eye, int eyeOffset, java.nio.DoubleBuffer center, int centerOffset, java.nio.DoubleBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && eye.hasArray() && eyeOffset >= 0 && eyeOffset <= eye.limit() - 3 && center.hasArray() && centerOffset >= 0 && centerOffset <= center.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            Double3x4OpsKernelsArray.makeLookAt_rh(dest.array(), dest.arrayOffset() + destOffset, eye.array(), eye.arrayOffset() + eyeOffset, center.array(), center.arrayOffset() + centerOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && eye.order() == java.nio.ByteOrder.nativeOrder() && center.order() == java.nio.ByteOrder.nativeOrder() && up.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.makeLookAt_rh_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(eye.duplicate().position(0)), eyeOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(center.duplicate().position(0)), centerOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(up.duplicate().position(0)), upOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeLookAt_rh_apiGet(dest, destOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeLookAt_rh_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer eye, int eyeOffset, java.nio.DoubleBuffer center, int centerOffset, java.nio.DoubleBuffer up, int upOffset) {
        double _eyex = eye.get(eyeOffset);
        double _eyey = eye.get(eyeOffset + 1);
        double _eyez = eye.get(eyeOffset + 2);
        double _upx = up.get(upOffset);
        double _upy = up.get(upOffset + 1);
        double _upz = up.get(upOffset + 2);
        double _t0 = center.get(centerOffset + 2) - _eyez;
        double _t1 = center.get(centerOffset) - _eyex;
        double _t2 = center.get(centerOffset + 1) - _eyey;
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
        return makeLookAt_rh_apiGet_sb003f5cf_1(dest, destOffset, _eyex, _eyey, _eyez, _upx, _upy, _upz, _t14, _t15, _t16, Math.fma(_t21, _t15, -(_t22 * _t16)), Math.fma(_t22, _t14, -(_t23 * _t15)), Math.fma(_t23, _t16, -(_t21 * _t14)));
    }

    /** Piece 2 of {@code makeLookAt_rh_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeLookAt_rh_apiGet_sb003f5cf_1(java.nio.DoubleBuffer dest, int destOffset, double _eyex, double _eyey, double _eyez, double _upx, double _upy, double _upz, double _t14, double _t15, double _t16, double _t30, double _t31, double _t32) {
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
        dest.put(destOffset, _t40);
        dest.put(destOffset + 1, _t41);
        dest.put(destOffset + 2, _t42);
        dest.put(destOffset + 3, -Math.fma(_eyez, _t42, Math.fma(_eyex, _t40, _eyey * _t41)));
        dest.put(destOffset + 4, _t49);
        dest.put(destOffset + 5, _t50);
        dest.put(destOffset + 6, _t51);
        dest.put(destOffset + 7, -Math.fma(_eyez, _t51, Math.fma(_eyex, _t49, _eyey * _t50)));
        dest.put(destOffset + 8, -_t15);
        return makeLookAt_rh_apiGet_sb003f5cf_2(dest, destOffset, _eyex, _eyey, _eyez, _t14, _t15, _t16);
    }

    /** Piece 3 of {@code makeLookAt_rh_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeLookAt_rh_apiGet_sb003f5cf_2(java.nio.DoubleBuffer dest, int destOffset, double _eyex, double _eyey, double _eyez, double _t14, double _t15, double _t16) {
        dest.put(destOffset + 9, -_t16);
        dest.put(destOffset + 10, -_t14);
        dest.put(destOffset + 11, Math.fma(_eyez, _t14, Math.fma(_eyex, _t15, _eyey * _t16)));
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXYZ_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingXYZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXYZ_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingXYZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingXYZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingXYZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXYZ_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 1.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 1.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXYnZ_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingXYnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXYnZ_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingXYnZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingXYnZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingXYnZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXYnZ_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 1.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, -1.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXZY_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingXZY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXZY_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingXZY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingXZY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingXZY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXZY_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 1.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 1.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXZnY_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingXZnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXZnY_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingXZnY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingXZnY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingXZnY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXZnY_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, -1.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 1.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXnYZ_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingXnYZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXnYZ_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingXnYZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingXnYZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingXnYZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXnYZ_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, -1.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 1.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXnYnZ_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingXnYnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXnYnZ_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingXnYnZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingXnYnZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingXnYnZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXnYnZ_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, -1.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, -1.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXnZY_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingXnZY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXnZY_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingXnZY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingXnZY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingXnZY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXnZY_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 1.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, -1.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXnZnY_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingXnZnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXnZnY_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingXnZnY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingXnZnY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingXnZnY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingXnZnY_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, -1.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, -1.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYXZ_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingYXZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYXZ_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingYXZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingYXZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingYXZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYXZ_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 1.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 1.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 1.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYXnZ_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingYXnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYXnZ_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingYXnZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingYXnZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingYXnZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYXnZ_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 1.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 1.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, -1.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYZX_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingYZX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYZX_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingYZX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingYZX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingYZX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYZX_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 1.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 1.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 1.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYZnX_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingYZnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYZnX_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingYZnX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingYZnX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingYZnX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYZnX_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, -1.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 1.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 1.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYnXZ_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingYnXZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYnXZ_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingYnXZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingYnXZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingYnXZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYnXZ_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, -1.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 1.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 1.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYnXnZ_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingYnXnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYnXnZ_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingYnXnZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingYnXnZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingYnXnZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYnXnZ_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, -1.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 1.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, -1.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYnZX_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingYnZX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYnZX_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingYnZX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingYnZX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingYnZX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYnZX_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 1.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 1.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, -1.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYnZnX_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingYnZnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYnZnX_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingYnZnX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingYnZnX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingYnZnX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingYnZnX_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, -1.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 1.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, -1.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZXY_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingZXY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZXY_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingZXY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingZXY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingZXY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZXY_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 1.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 1.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 1.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZXnY_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingZXnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZXnY_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingZXnY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingZXnY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingZXnY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZXnY_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 1.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, -1.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 1.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZYX_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingZYX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZYX_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingZYX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingZYX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingZYX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZYX_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 1.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 1.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 1.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZYnX_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingZYnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZYnX_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingZYnX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingZYnX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingZYnX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZYnX_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, -1.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 1.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 1.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZnXY_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingZnXY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZnXY_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingZnXY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingZnXY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingZnXY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZnXY_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, -1.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 1.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 1.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZnXnY_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingZnXnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZnXnY_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingZnXnY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingZnXnY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingZnXnY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZnXnY_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, -1.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, -1.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 1.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZnYX_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingZnYX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZnYX_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingZnYX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingZnYX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingZnYX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZnYX_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 1.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, -1.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 1.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZnYnX_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingZnYnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZnYnX_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingZnYnX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingZnYnX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingZnYnX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingZnYnX_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, -1.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, -1.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 1.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXYZ_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnXYZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXYZ_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnXYZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnXYZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnXYZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXYZ_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, -1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 1.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 1.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXYnZ_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnXYnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXYnZ_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnXYnZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnXYnZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnXYnZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXYnZ_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, -1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 1.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, -1.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXZY_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnXZY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXZY_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnXZY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnXZY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnXZY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXZY_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, -1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 1.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 1.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXZnY_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnXZnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXZnY_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnXZnY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnXZnY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnXZnY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXZnY_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, -1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, -1.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 1.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXnYZ_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnXnYZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXnYZ_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnXnYZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnXnYZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnXnYZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXnYZ_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, -1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, -1.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 1.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXnYnZ_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnXnYnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXnYnZ_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnXnYnZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnXnYnZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnXnYnZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXnYnZ_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, -1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, -1.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, -1.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXnZY_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnXnZY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXnZY_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnXnZY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnXnZY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnXnZY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXnZY_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, -1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 1.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, -1.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXnZnY_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnXnZnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXnZnY_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnXnZnY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnXnZnY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnXnZnY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnXnZnY_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, -1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, -1.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, -1.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYXZ_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnYXZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYXZ_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnYXZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnYXZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnYXZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYXZ_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 1.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, -1.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 1.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYXnZ_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnYXnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYXnZ_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnYXnZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnYXnZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnYXnZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYXnZ_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 1.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, -1.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, -1.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYZX_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnYZX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYZX_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnYZX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnYZX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnYZX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYZX_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 1.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, -1.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 1.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYZnX_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnYZnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYZnX_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnYZnX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnYZnX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnYZnX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYZnX_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, -1.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, -1.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 1.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYnXZ_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnYnXZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYnXZ_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnYnXZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnYnXZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnYnXZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYnXZ_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, -1.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, -1.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 1.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYnXnZ_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnYnXnZ_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYnXnZ_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnYnXnZ(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnYnXnZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnYnXnZ_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYnXnZ_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, -1.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, -1.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, -1.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYnZX_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnYnZX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYnZX_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnYnZX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnYnZX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnYnZX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYnZX_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 1.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, -1.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, -1.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYnZnX_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnYnZnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYnZnX_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnYnZnX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnYnZnX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnYnZnX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnYnZnX_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, -1.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, -1.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, -1.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZXY_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnZXY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZXY_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnZXY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnZXY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnZXY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZXY_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 1.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 1.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, -1.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZXnY_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnZXnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZXnY_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnZXnY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnZXnY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnZXnY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZXnY_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 1.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, -1.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, -1.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZYX_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnZYX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZYX_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnZYX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnZYX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnZYX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZYX_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 1.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 1.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, -1.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZYnX_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnZYnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZYnX_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnZYnX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnZYnX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnZYnX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZYnX_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, -1.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 1.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, -1.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZnXY_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnZnXY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZnXY_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnZnXY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnZnXY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnZnXY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZnXY_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, -1.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, 1.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, -1.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZnXnY_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnZnXnY_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZnXnY_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnZnXnY(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnZnXnY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnZnXnY_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZnXnY_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, -1.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 0.0);
        dest.put(destOffset + 6, -1.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, -1.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZnYX_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnZnYX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZnYX_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnZnYX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnZnYX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnZnYX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZnYX_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 1.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, -1.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, -1.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZnYnX_unsafe(java.nio.DoubleBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeMappingnZnYnX_unsafe(_destBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZnYnX_api(java.nio.DoubleBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeMappingnZnYnX(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeMappingnZnYnX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeMappingnZnYnX_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeMappingnZnYnX_apiGet(java.nio.DoubleBuffer dest, int destOffset) {
        dest.put(destOffset, 0.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, -1.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, -1.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, -1.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 0.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeReflection_unsafe(java.nio.DoubleBuffer dest, int destOffset, double normalX, double normalY, double normalZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeReflection_unsafe(_destBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeReflection_api(java.nio.DoubleBuffer dest, int destOffset, double normalX, double normalY, double normalZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeReflection(dest.array(), dest.arrayOffset() + destOffset, normalX, normalY, normalZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeReflection_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, normalX, normalY, normalZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeReflection_apiGet(dest, destOffset, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeReflection_apiGet(java.nio.DoubleBuffer dest, int destOffset, double normalX, double normalY, double normalZ) {
        double _sp0 = normalX + normalX;
        double _t6 = -(_sp0 * normalY);
        double _t7 = -(_sp0 * normalZ);
        double _t8 = -((normalY + normalY) * normalZ);
        dest.put(destOffset, Math.fma(-2.0, normalX * normalX, 1.0));
        dest.put(destOffset + 1, _t6);
        dest.put(destOffset + 2, _t7);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, _t6);
        dest.put(destOffset + 5, Math.fma(-2.0, normalY * normalY, 1.0));
        dest.put(destOffset + 6, _t8);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, _t7);
        dest.put(destOffset + 9, _t8);
        dest.put(destOffset + 10, Math.fma(-2.0, normalZ * normalZ, 1.0));
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeReflection_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset * 8L;
        Double3x4OpsKernelsAddress.makeReflection_unsafe(_destBase, _normalBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeReflection_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 3) {
            Double3x4Ops.makeReflection(dest.array(), dest.arrayOffset() + destOffset, normal.array(), normal.arrayOffset() + normalOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && normal.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.makeReflection_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(normal.duplicate().position(0)), normalOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeReflection_apiGet(dest, destOffset, normal, normalOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeReflection_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        double _normalx = normal.get(normalOffset);
        double _normaly = normal.get(normalOffset + 1);
        double _normalz = normal.get(normalOffset + 2);
        double _sp0 = _normalx + _normalx;
        double _t6 = -(_sp0 * _normaly);
        double _t7 = -(_sp0 * _normalz);
        double _t8 = -((_normaly + _normaly) * _normalz);
        dest.put(destOffset, Math.fma(-2.0, _normalx * _normalx, 1.0));
        dest.put(destOffset + 1, _t6);
        dest.put(destOffset + 2, _t7);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, _t6);
        dest.put(destOffset + 5, Math.fma(-2.0, _normaly * _normaly, 1.0));
        dest.put(destOffset + 6, _t8);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, _t7);
        dest.put(destOffset + 9, _t8);
        dest.put(destOffset + 10, Math.fma(-2.0, _normalz * _normalz, 1.0));
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationAxis_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationAxis_api(java.nio.DoubleBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeRotationAxis(dest.array(), dest.arrayOffset() + destOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeRotationAxis_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, angle, axisX, axisY, axisZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeRotationAxis_apiGet(dest, destOffset, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationAxis_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisY;
        double _t3 = axisX * axisZ;
        double _t4 = axisY * axisZ;
        double _t5 = 1.0 - _t1;
        dest.put(destOffset, Math.fma(_t5, axisX * axisX, _t1));
        dest.put(destOffset + 1, Math.fma(_t5, _t2, -(axisZ * _t0)));
        dest.put(destOffset + 2, Math.fma(axisY, _t0, _t5 * _t3));
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, Math.fma(axisZ, _t0, _t5 * _t2));
        dest.put(destOffset + 5, Math.fma(_t5, axisY * axisY, _t1));
        dest.put(destOffset + 6, Math.fma(_t5, _t4, -(axisX * _t0)));
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, Math.fma(_t5, _t3, -(axisY * _t0)));
        dest.put(destOffset + 9, Math.fma(axisX, _t0, _t5 * _t4));
        dest.put(destOffset + 10, Math.fma(_t5, axisZ * axisZ, _t1));
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationAxis_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset * 8L;
        Double3x4OpsKernelsAddress.makeRotationAxis_unsafe(_destBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationAxis_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            Double3x4Ops.makeRotationAxis(dest.array(), dest.arrayOffset() + destOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && axis.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.makeRotationAxis_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(axis.duplicate().position(0)), axisOffset * 8L, angle);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeRotationAxis_apiGet(dest, destOffset, axis, axisOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationAxis_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        double _axisx = axis.get(axisOffset);
        double _axisy = axis.get(axisOffset + 1);
        double _axisz = axis.get(axisOffset + 2);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisy;
        double _t3 = _axisx * _axisz;
        double _t4 = _axisy * _axisz;
        double _t5 = 1.0 - _t1;
        dest.put(destOffset, Math.fma(_t5, _axisx * _axisx, _t1));
        dest.put(destOffset + 1, Math.fma(_t5, _t2, -(_axisz * _t0)));
        dest.put(destOffset + 2, Math.fma(_axisy, _t0, _t5 * _t3));
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, Math.fma(_axisz, _t0, _t5 * _t2));
        dest.put(destOffset + 5, Math.fma(_t5, _axisy * _axisy, _t1));
        dest.put(destOffset + 6, Math.fma(_t5, _t4, -(_axisx * _t0)));
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, Math.fma(_t5, _t3, -(_axisy * _t0)));
        dest.put(destOffset + 9, Math.fma(_axisx, _t0, _t5 * _t4));
        dest.put(destOffset + 10, Math.fma(_t5, _axisz * _axisz, _t1));
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationLookAlong_unsafe(java.nio.DoubleBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationLookAlong_api(java.nio.DoubleBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeRotationLookAlong(dest.array(), dest.arrayOffset() + destOffset, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeRotationLookAlong_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeRotationLookAlong_apiGet(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationLookAlong_apiGet(java.nio.DoubleBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
        dest.put(destOffset, _t37);
        return makeRotationLookAlong_apiGet_se21a0127_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeRotationLookAlong_apiGet_se21a0127_1(java.nio.DoubleBuffer dest, int destOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        dest.put(destOffset + 1, Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.put(destOffset + 2, _t12);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, _t39);
        dest.put(destOffset + 5, Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.put(destOffset + 6, _t13);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, _t38);
        dest.put(destOffset + 9, Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.put(destOffset + 10, _t11);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationLookAlong_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer dir, int dirOffset, java.nio.DoubleBuffer up, int upOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _dirBase = UnsafeOpsHolder.U.getLong(dir, UnsafeCopy.BB_ADDRESS_OFFSET) + dirOffset * 8L;
        long _upBase = UnsafeOpsHolder.U.getLong(up, UnsafeCopy.BB_ADDRESS_OFFSET) + upOffset * 8L;
        Double3x4OpsKernelsAddress.makeRotationLookAlong_unsafe(_destBase, _dirBase, _upBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationLookAlong_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer dir, int dirOffset, java.nio.DoubleBuffer up, int upOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && dir.hasArray() && dirOffset >= 0 && dirOffset <= dir.limit() - 3 && up.hasArray() && upOffset >= 0 && upOffset <= up.limit() - 3) {
            Double3x4Ops.makeRotationLookAlong(dest.array(), dest.arrayOffset() + destOffset, dir.array(), dir.arrayOffset() + dirOffset, up.array(), up.arrayOffset() + upOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.makeRotationLookAlong_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(dir.duplicate().position(0)), dirOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(up.duplicate().position(0)), upOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeRotationLookAlong_apiGet(dest, destOffset, dir, dirOffset, up, upOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationLookAlong_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer dir, int dirOffset, java.nio.DoubleBuffer up, int upOffset) {
        double _dirx = dir.get(dirOffset);
        double _diry = dir.get(dirOffset + 1);
        double _dirz = dir.get(dirOffset + 2);
        double _upx = up.get(upOffset);
        double _upy = up.get(upOffset + 1);
        double _upz = up.get(upOffset + 2);
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
        return makeRotationLookAlong_apiGet_s4218861f_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer makeRotationLookAlong_apiGet_s4218861f_1(java.nio.DoubleBuffer dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
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
        dest.put(destOffset, _t37);
        dest.put(destOffset + 1, Math.fma(_t38, _t13, -(_t39 * _t11)));
        dest.put(destOffset + 2, _t12);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, _t39);
        dest.put(destOffset + 5, Math.fma(_t37, _t11, -(_t38 * _t12)));
        dest.put(destOffset + 6, _t13);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, _t38);
        dest.put(destOffset + 9, Math.fma(_t39, _t12, -(_t37 * _t13)));
        dest.put(destOffset + 10, _t11);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationQuat_unsafe(java.nio.DoubleBuffer dest, int destOffset, double qX, double qY, double qZ, double qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationQuat_api(java.nio.DoubleBuffer dest, int destOffset, double qX, double qY, double qZ, double qW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeRotationQuat(dest.array(), dest.arrayOffset() + destOffset, qX, qY, qZ, qW);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeRotationQuat_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, qX, qY, qZ, qW);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeRotationQuat_apiGet(dest, destOffset, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationQuat_apiGet(java.nio.DoubleBuffer dest, int destOffset, double qX, double qY, double qZ, double qW) {
        double _t0 = qZ * qZ;
        double _t1 = qZ * qW;
        double _t2 = qY * qW;
        dest.put(destOffset, Math.fma(-2.0, Math.fma(qY, qY, _t0), 1.0));
        dest.put(destOffset + 1, 2.0 * Math.fma(qX, qY, -_t1));
        dest.put(destOffset + 2, 2.0 * Math.fma(qX, qZ, _t2));
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 2.0 * Math.fma(qX, qY, _t1));
        dest.put(destOffset + 5, Math.fma(-2.0, Math.fma(qX, qX, _t0), 1.0));
        dest.put(destOffset + 6, 2.0 * Math.fma(qY, qZ, -(qX * qW)));
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 2.0 * Math.fma(qX, qZ, -_t2));
        dest.put(destOffset + 9, 2.0 * Math.fma(qX, qW, qY * qZ));
        dest.put(destOffset + 10, Math.fma(-2.0, Math.fma(qX, qX, qY * qY), 1.0));
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationQuat_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + qOffset * 8L;
        Double3x4OpsKernelsAddress.makeRotationQuat_unsafe(_destBase, _qBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationQuat_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer q, int qOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && q.hasArray() && qOffset >= 0 && qOffset <= q.limit() - 4) {
            Double3x4Ops.makeRotationQuat(dest.array(), dest.arrayOffset() + destOffset, q.array(), q.arrayOffset() + qOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && q.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.makeRotationQuat_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(q.duplicate().position(0)), qOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeRotationQuat_apiGet(dest, destOffset, q, qOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationQuat_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer q, int qOffset) {
        double _qx = q.get(qOffset);
        double _qy = q.get(qOffset + 1);
        double _qz = q.get(qOffset + 2);
        double _qw = q.get(qOffset + 3);
        double _t0 = _qz * _qz;
        double _t1 = _qz * _qw;
        double _t2 = _qy * _qw;
        dest.put(destOffset, Math.fma(-2.0, Math.fma(_qy, _qy, _t0), 1.0));
        dest.put(destOffset + 1, 2.0 * Math.fma(_qx, _qy, -_t1));
        dest.put(destOffset + 2, 2.0 * Math.fma(_qx, _qz, _t2));
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 2.0 * Math.fma(_qx, _qy, _t1));
        dest.put(destOffset + 5, Math.fma(-2.0, Math.fma(_qx, _qx, _t0), 1.0));
        dest.put(destOffset + 6, 2.0 * Math.fma(_qy, _qz, -(_qx * _qw)));
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 2.0 * Math.fma(_qx, _qz, -_t2));
        dest.put(destOffset + 9, 2.0 * Math.fma(_qx, _qw, _qy * _qz));
        dest.put(destOffset + 10, Math.fma(-2.0, Math.fma(_qx, _qx, _qy * _qy), 1.0));
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationX_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeRotationX_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationX_api(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeRotationX(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeRotationX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, angle);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeRotationX_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationX_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset, 1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, _t1);
        dest.put(destOffset + 6, -_t0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, _t0);
        dest.put(destOffset + 10, _t1);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationXYZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeRotationXYZ_unsafe(_destBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationXYZ_api(java.nio.DoubleBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeRotationXYZ(dest.array(), dest.arrayOffset() + destOffset, angleX, angleY, angleZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeRotationXYZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, angleX, angleY, angleZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeRotationXYZ_apiGet(dest, destOffset, angleX, angleY, angleZ);
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
        dest.put(destOffset, _t3 * _t4);
        dest.put(destOffset + 1, -(_t1 * _t3));
        dest.put(destOffset + 2, _t0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, Math.fma(_t6, _t4, _t1 * _t5));
        dest.put(destOffset + 5, Math.fma(_t5, _t4, -(_t6 * _t1)));
        dest.put(destOffset + 6, -(_t2 * _t3));
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, Math.fma(_t2, _t1, -(_t7 * _t4)));
        dest.put(destOffset + 9, Math.fma(_t7, _t1, _t2 * _t4));
        dest.put(destOffset + 10, _t5 * _t3);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationXZY_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeRotationXZY_unsafe(_destBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationXZY_api(java.nio.DoubleBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeRotationXZY(dest.array(), dest.arrayOffset() + destOffset, angleX, angleZ, angleY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeRotationXZY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, angleX, angleZ, angleY);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeRotationXZY_apiGet(dest, destOffset, angleX, angleZ, angleY);
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
        dest.put(destOffset, _t3 * _t4);
        dest.put(destOffset + 1, -_t1);
        dest.put(destOffset + 2, _t0 * _t4);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, Math.fma(_t7, _t3, _t2 * _t0));
        dest.put(destOffset + 5, _t5 * _t4);
        dest.put(destOffset + 6, Math.fma(_t7, _t0, -(_t2 * _t3)));
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, Math.fma(_t6, _t3, -(_t0 * _t5)));
        dest.put(destOffset + 9, _t2 * _t4);
        dest.put(destOffset + 10, Math.fma(_t6, _t0, _t5 * _t3));
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationY_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeRotationY_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationY_api(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeRotationY(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeRotationY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, angle);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeRotationY_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationY_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset, _t1);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, _t0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 1.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, -_t0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, _t1);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationYXZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeRotationYXZ_unsafe(_destBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationYXZ_api(java.nio.DoubleBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeRotationYXZ(dest.array(), dest.arrayOffset() + destOffset, angleY, angleX, angleZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeRotationYXZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, angleY, angleX, angleZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeRotationYXZ_apiGet(dest, destOffset, angleY, angleX, angleZ);
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
        dest.put(destOffset, Math.fma(_t6, _t2, _t3 * _t4));
        dest.put(destOffset + 1, Math.fma(_t6, _t4, -(_t2 * _t3)));
        dest.put(destOffset + 2, _t1 * _t5);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, _t2 * _t5);
        dest.put(destOffset + 5, _t5 * _t4);
        dest.put(destOffset + 6, -_t0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, Math.fma(_t7, _t2, -(_t1 * _t4)));
        dest.put(destOffset + 9, Math.fma(_t7, _t4, _t1 * _t2));
        dest.put(destOffset + 10, _t5 * _t3);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationYZX_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeRotationYZX_unsafe(_destBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationYZX_api(java.nio.DoubleBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeRotationYZX(dest.array(), dest.arrayOffset() + destOffset, angleY, angleZ, angleX);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeRotationYZX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, angleY, angleZ, angleX);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeRotationYZX_apiGet(dest, destOffset, angleY, angleZ, angleX);
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
        dest.put(destOffset, _t3 * _t4);
        dest.put(destOffset + 1, Math.fma(_t2, _t0, -(_t7 * _t5)));
        dest.put(destOffset + 2, Math.fma(_t7, _t2, _t0 * _t5));
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, _t1);
        dest.put(destOffset + 5, _t5 * _t4);
        dest.put(destOffset + 6, -(_t2 * _t4));
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, -(_t0 * _t4));
        dest.put(destOffset + 9, Math.fma(_t6, _t5, _t2 * _t3));
        dest.put(destOffset + 10, Math.fma(_t5, _t3, -(_t6 * _t2)));
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeRotationZ_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationZ_api(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeRotationZ(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeRotationZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, angle);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeRotationZ_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset, _t1);
        dest.put(destOffset + 1, -_t0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, _t0);
        dest.put(destOffset + 5, _t1);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 1.0);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationZXY_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeRotationZXY_unsafe(_destBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationZXY_api(java.nio.DoubleBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeRotationZXY(dest.array(), dest.arrayOffset() + destOffset, angleZ, angleX, angleY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeRotationZXY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, angleZ, angleX, angleY);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeRotationZXY_apiGet(dest, destOffset, angleZ, angleX, angleY);
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
        dest.put(destOffset, Math.fma(_t3, _t4, -(_t6 * _t0)));
        dest.put(destOffset + 1, -(_t1 * _t5));
        dest.put(destOffset + 2, Math.fma(_t6, _t3, _t0 * _t4));
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, Math.fma(_t7, _t0, _t1 * _t3));
        dest.put(destOffset + 5, _t5 * _t4);
        dest.put(destOffset + 6, Math.fma(_t0, _t1, -(_t7 * _t3)));
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, -(_t0 * _t5));
        dest.put(destOffset + 9, _t2);
        dest.put(destOffset + 10, _t5 * _t3);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationZYX_unsafe(java.nio.DoubleBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeRotationZYX_unsafe(_destBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer makeRotationZYX_api(java.nio.DoubleBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeRotationZYX(dest.array(), dest.arrayOffset() + destOffset, angleZ, angleY, angleX);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeRotationZYX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, angleZ, angleY, angleX);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeRotationZYX_apiGet(dest, destOffset, angleZ, angleY, angleX);
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
        dest.put(destOffset, _t3 * _t4);
        dest.put(destOffset + 1, Math.fma(_t7, _t2, -(_t1 * _t5)));
        dest.put(destOffset + 2, Math.fma(_t7, _t5, _t2 * _t1));
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, _t1 * _t3);
        dest.put(destOffset + 5, Math.fma(_t6, _t2, _t5 * _t4));
        dest.put(destOffset + 6, Math.fma(_t6, _t5, -(_t2 * _t4)));
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, -_t0);
        dest.put(destOffset + 9, _t2 * _t3);
        dest.put(destOffset + 10, _t5 * _t3);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_unsafe(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_api(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY, double vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, vX, vY, vZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeScaling_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, vX, vY, vZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_apiGet(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY, double vZ) {
        dest.put(destOffset, vX);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, vY);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, vZ);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 8L;
        Double3x4OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Double3x4Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.makeScaling_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), vOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _vy = v.get(vOffset + 1);
        double _vz = v.get(vOffset + 2);
        dest.put(destOffset, v.get(vOffset));
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, _vy);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, _vz);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_unsafe(java.nio.DoubleBuffer dest, int destOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_api(java.nio.DoubleBuffer dest, int destOffset, double s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, s);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeScaling_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, s);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, s);
        return dest;
    }

    public static java.nio.DoubleBuffer makeScaling_apiGet(java.nio.DoubleBuffer dest, int destOffset, double s) {
        dest.put(destOffset, s);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, 0.0);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, s);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, 0.0);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, s);
        dest.put(destOffset + 11, 0.0);
        return dest;
    }

    public static java.nio.DoubleBuffer makeTranslation_unsafe(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.makeTranslation_unsafe(_destBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeTranslation_api(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY, double vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            Double3x4Ops.makeTranslation(dest.array(), dest.arrayOffset() + destOffset, vX, vY, vZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Double3x4OpsKernelsSegment.makeTranslation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, vX, vY, vZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeTranslation_apiGet(dest, destOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeTranslation_apiGet(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY, double vZ) {
        dest.put(destOffset, 1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, vX);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 1.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, vY);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 1.0);
        dest.put(destOffset + 11, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer makeTranslation_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 8L;
        Double3x4OpsKernelsAddress.makeTranslation_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer makeTranslation_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Double3x4Ops.makeTranslation(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.makeTranslation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), vOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.makeTranslation_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer makeTranslation_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _vx = v.get(vOffset);
        double _vy = v.get(vOffset + 1);
        double _vz = v.get(vOffset + 2);
        dest.put(destOffset, 1.0);
        dest.put(destOffset + 1, 0.0);
        dest.put(destOffset + 2, 0.0);
        dest.put(destOffset + 3, _vx);
        dest.put(destOffset + 4, 0.0);
        dest.put(destOffset + 5, 1.0);
        dest.put(destOffset + 6, 0.0);
        dest.put(destOffset + 7, _vy);
        dest.put(destOffset + 8, 0.0);
        dest.put(destOffset + 9, 0.0);
        dest.put(destOffset + 10, 1.0);
        dest.put(destOffset + 11, _vz);
        return dest;
    }

    public static java.nio.DoubleBuffer mapXYZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapXYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapXYZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapXYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapXYZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapXYZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _i = 0; _i < 12; _i++) {
            dest.put(destOffset + _i, src.get(srcOffset + _i));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapXYnZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapXYnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapXYnZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapXYnZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapXYnZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapXYnZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo));
            dest.put(destOffset + _lo + 1, _eself1);
            dest.put(destOffset + _lo + 2, -_eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapXZY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapXZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapXZY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapXZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapXZY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapXZY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapXZY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo));
            dest.put(destOffset + _lo + 1, _eself2);
            dest.put(destOffset + _lo + 2, _eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapXZnY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapXZnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapXZnY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapXZnY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapXZnY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapXZnY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapXZnY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo));
            dest.put(destOffset + _lo + 1, _eself2);
            dest.put(destOffset + _lo + 2, -_eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapXnYZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapXnYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapXnYZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapXnYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapXnYZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapXnYZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo));
            dest.put(destOffset + _lo + 1, -_eself1);
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapXnYnZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapXnYnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapXnYnZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapXnYnZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapXnYnZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapXnYnZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapXnYnZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo));
            dest.put(destOffset + _lo + 1, -_eself1);
            dest.put(destOffset + _lo + 2, -_eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapXnZY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapXnZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapXnZY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapXnZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapXnZY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapXnZY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapXnZY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo));
            dest.put(destOffset + _lo + 1, -_eself2);
            dest.put(destOffset + _lo + 2, _eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapXnZnY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapXnZnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapXnZnY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapXnZnY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapXnZnY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapXnZnY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapXnZnY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo));
            dest.put(destOffset + _lo + 1, -_eself2);
            dest.put(destOffset + _lo + 2, -_eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapYXZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapYXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapYXZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapYXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapYXZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapYXZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapYXZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo + 1));
            dest.put(destOffset + _lo + 1, _eself0);
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapYXnZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapYXnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapYXnZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapYXnZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapYXnZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapYXnZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapYXnZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo + 1));
            dest.put(destOffset + _lo + 1, _eself0);
            dest.put(destOffset + _lo + 2, -_eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapYZX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapYZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapYZX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapYZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapYZX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapYZX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapYZX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo + 1));
            dest.put(destOffset + _lo + 1, _eself2);
            dest.put(destOffset + _lo + 2, _eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapYZnX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapYZnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapYZnX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapYZnX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapYZnX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapYZnX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapYZnX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo + 1));
            dest.put(destOffset + _lo + 1, _eself2);
            dest.put(destOffset + _lo + 2, -_eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapYnXZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapYnXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapYnXZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapYnXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapYnXZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapYnXZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapYnXZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo + 1));
            dest.put(destOffset + _lo + 1, -_eself0);
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapYnXnZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapYnXnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapYnXnZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapYnXnZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapYnXnZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapYnXnZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapYnXnZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo + 1));
            dest.put(destOffset + _lo + 1, -_eself0);
            dest.put(destOffset + _lo + 2, -_eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapYnZX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapYnZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapYnZX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapYnZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapYnZX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapYnZX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapYnZX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo + 1));
            dest.put(destOffset + _lo + 1, -_eself2);
            dest.put(destOffset + _lo + 2, _eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapYnZnX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapYnZnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapYnZnX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapYnZnX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapYnZnX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapYnZnX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapYnZnX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo + 1));
            dest.put(destOffset + _lo + 1, -_eself2);
            dest.put(destOffset + _lo + 2, -_eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapZXY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapZXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapZXY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapZXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapZXY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapZXY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapZXY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo + 2));
            dest.put(destOffset + _lo + 1, _eself0);
            dest.put(destOffset + _lo + 2, _eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapZXnY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapZXnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapZXnY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapZXnY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapZXnY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapZXnY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapZXnY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo + 2));
            dest.put(destOffset + _lo + 1, _eself0);
            dest.put(destOffset + _lo + 2, -_eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapZYX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapZYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapZYX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapZYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapZYX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapZYX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapZYX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo + 2));
            dest.put(destOffset + _lo + 1, _eself1);
            dest.put(destOffset + _lo + 2, _eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapZYnX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapZYnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapZYnX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapZYnX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapZYnX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapZYnX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapZYnX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo + 2));
            dest.put(destOffset + _lo + 1, _eself1);
            dest.put(destOffset + _lo + 2, -_eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapZnXY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapZnXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapZnXY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapZnXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapZnXY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapZnXY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapZnXY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo + 2));
            dest.put(destOffset + _lo + 1, -_eself0);
            dest.put(destOffset + _lo + 2, _eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapZnXnY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapZnXnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapZnXnY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapZnXnY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapZnXnY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapZnXnY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapZnXnY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo + 2));
            dest.put(destOffset + _lo + 1, -_eself0);
            dest.put(destOffset + _lo + 2, -_eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapZnYX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapZnYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapZnYX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapZnYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapZnYX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapZnYX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapZnYX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo + 2));
            dest.put(destOffset + _lo + 1, -_eself1);
            dest.put(destOffset + _lo + 2, _eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapZnYnX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapZnYnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapZnYnX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapZnYnX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapZnYnX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapZnYnX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapZnYnX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo + 2));
            dest.put(destOffset + _lo + 1, -_eself1);
            dest.put(destOffset + _lo + 2, -_eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXYZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnXYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXYZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnXYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnXYZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnXYZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXYZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo));
            dest.put(destOffset + _lo + 1, _eself1);
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXYnZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnXYnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXYnZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnXYnZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnXYnZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnXYnZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXYnZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo));
            dest.put(destOffset + _lo + 1, _eself1);
            dest.put(destOffset + _lo + 2, -_eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXZY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnXZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXZY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnXZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnXZY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnXZY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXZY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo));
            dest.put(destOffset + _lo + 1, _eself2);
            dest.put(destOffset + _lo + 2, _eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXZnY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnXZnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXZnY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnXZnY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnXZnY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnXZnY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXZnY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo));
            dest.put(destOffset + _lo + 1, _eself2);
            dest.put(destOffset + _lo + 2, -_eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXnYZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnXnYZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXnYZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnXnYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnXnYZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnXnYZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXnYZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo));
            dest.put(destOffset + _lo + 1, -_eself1);
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXnYnZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnXnYnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXnYnZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnXnYnZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnXnYnZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnXnYnZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXnYnZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo));
            dest.put(destOffset + _lo + 1, -_eself1);
            dest.put(destOffset + _lo + 2, -_eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXnZY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnXnZY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXnZY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnXnZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnXnZY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnXnZY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXnZY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo));
            dest.put(destOffset + _lo + 1, -_eself2);
            dest.put(destOffset + _lo + 2, _eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXnZnY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnXnZnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXnZnY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnXnZnY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnXnZnY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnXnZnY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnXnZnY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo));
            dest.put(destOffset + _lo + 1, -_eself2);
            dest.put(destOffset + _lo + 2, -_eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYXZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnYXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYXZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnYXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnYXZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnYXZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYXZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo + 1));
            dest.put(destOffset + _lo + 1, _eself0);
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYXnZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnYXnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYXnZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnYXnZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnYXnZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnYXnZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYXnZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo + 1));
            dest.put(destOffset + _lo + 1, _eself0);
            dest.put(destOffset + _lo + 2, -_eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYZX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnYZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYZX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnYZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnYZX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnYZX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYZX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo + 1));
            dest.put(destOffset + _lo + 1, _eself2);
            dest.put(destOffset + _lo + 2, _eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYZnX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnYZnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYZnX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnYZnX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnYZnX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnYZnX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYZnX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo + 1));
            dest.put(destOffset + _lo + 1, _eself2);
            dest.put(destOffset + _lo + 2, -_eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYnXZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnYnXZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYnXZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnYnXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnYnXZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnYnXZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYnXZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo + 1));
            dest.put(destOffset + _lo + 1, -_eself0);
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYnXnZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnYnXnZ_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYnXnZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnYnXnZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnYnXnZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnYnXnZ_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYnXnZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo + 1));
            dest.put(destOffset + _lo + 1, -_eself0);
            dest.put(destOffset + _lo + 2, -_eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYnZX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnYnZX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYnZX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnYnZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnYnZX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnYnZX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYnZX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo + 1));
            dest.put(destOffset + _lo + 1, -_eself2);
            dest.put(destOffset + _lo + 2, _eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYnZnX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnYnZnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYnZnX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnYnZnX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnYnZnX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnYnZnX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnYnZnX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo + 1));
            dest.put(destOffset + _lo + 1, -_eself2);
            dest.put(destOffset + _lo + 2, -_eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZXY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnZXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZXY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnZXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnZXY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnZXY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZXY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo + 2));
            dest.put(destOffset + _lo + 1, _eself0);
            dest.put(destOffset + _lo + 2, _eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZXnY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnZXnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZXnY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnZXnY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnZXnY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnZXnY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZXnY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo + 2));
            dest.put(destOffset + _lo + 1, _eself0);
            dest.put(destOffset + _lo + 2, -_eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZYX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnZYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZYX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnZYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnZYX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnZYX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZYX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo + 2));
            dest.put(destOffset + _lo + 1, _eself1);
            dest.put(destOffset + _lo + 2, _eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZYnX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnZYnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZYnX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnZYnX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnZYnX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnZYnX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZYnX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo + 2));
            dest.put(destOffset + _lo + 1, _eself1);
            dest.put(destOffset + _lo + 2, -_eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZnXY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnZnXY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZnXY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnZnXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnZnXY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnZnXY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZnXY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo + 2));
            dest.put(destOffset + _lo + 1, -_eself0);
            dest.put(destOffset + _lo + 2, _eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZnXnY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnZnXnY_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZnXnY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnZnXnY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnZnXnY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnZnXnY_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZnXnY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo + 2));
            dest.put(destOffset + _lo + 1, -_eself0);
            dest.put(destOffset + _lo + 2, -_eself1);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZnYX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnZnYX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZnYX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnZnYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnZnYX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnZnYX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZnYX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo + 2));
            dest.put(destOffset + _lo + 1, -_eself1);
            dest.put(destOffset + _lo + 2, _eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZnYnX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mapnZnYnX_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZnYnX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mapnZnYnX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mapnZnYnX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mapnZnYnX_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mapnZnYnX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo + 2));
            dest.put(destOffset + _lo + 1, -_eself1);
            dest.put(destOffset + _lo + 2, -_eself0);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.preRotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preRotateAround_apiGet(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t0 = -rotY;
        double _t3 = -rotX;
        double _t4 = rotX + rotX;
        double _t5 = rotY + rotY;
        double _t6 = rotZ + rotZ;
        double _t7 = rotW * _t5;
        double _t8 = rotW * _t6;
        double _t10 = rotW * _t4;
        double _t14 = Math.fma(-rotZ, _t6, 1.0);
        return preRotateAround_apiGet_s8b7ccfed_1(dest, destOffset, rotX, rotY, pivotX, pivotY, pivotZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, -pivotZ, _t3, _t4, _t5, rotZ * _t6, Math.fma(rotZ, _t4, _t7), Math.fma(rotY, _t4, _t8), Math.fma(rotZ, _t5, _t10), Math.fma(rotY, _t4, -_t8), Math.fma(rotZ, _t5, -_t10), Math.fma(rotZ, _t4, -_t7), Math.fma(_t0, _t5, _t14), Math.fma(_t3, _t4, _t14));
    }

    /** Piece 2 of {@code preRotateAround_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preRotateAround_apiGet_s8b7ccfed_1(java.nio.DoubleBuffer dest, int destOffset, double rotX, double rotY, double pivotX, double pivotY, double pivotZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t2, double _t3, double _t4, double _t5, double _t9, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        dest.put(destOffset, Math.fma(_self20, _t16, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.put(destOffset + 1, Math.fma(_self21, _t16, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.put(destOffset + 2, Math.fma(_self22, _t16, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.put(destOffset + 3, Math.fma(_self23, _t16, Math.fma(_self03, _t22, _self13 * _t19)) + Math.fma(_t2, _t16, Math.fma(pivotX, Math.fma(rotY, _t5, _t9), -(pivotY * _t19))));
        dest.put(destOffset + 4, Math.fma(_self20, _t20, Math.fma(_self00, _t17, _self10 * _t23)));
        dest.put(destOffset + 5, Math.fma(_self21, _t20, Math.fma(_self01, _t17, _self11 * _t23)));
        dest.put(destOffset + 6, Math.fma(_self22, _t20, Math.fma(_self02, _t17, _self12 * _t23)));
        return preRotateAround_apiGet_s8b7ccfed_2(dest, destOffset, rotX, rotY, pivotX, pivotY, pivotZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t2, _t4, _t5, _t9, _t17, _t18, _t20, _t21, _t23, Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0)));
    }

    /** Piece 3 of {@code preRotateAround_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preRotateAround_apiGet_s8b7ccfed_2(java.nio.DoubleBuffer dest, int destOffset, double rotX, double rotY, double pivotX, double pivotY, double pivotZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t2, double _t4, double _t5, double _t9, double _t17, double _t18, double _t20, double _t21, double _t23, double _t24) {
        dest.put(destOffset + 7, Math.fma(_self23, _t20, Math.fma(_self03, _t17, _self13 * _t23)) + Math.fma(_t2, _t20, Math.fma(pivotY, Math.fma(rotX, _t4, _t9), -(pivotX * _t17))));
        dest.put(destOffset + 8, Math.fma(_self20, _t24, Math.fma(_self00, _t21, _self10 * _t18)));
        dest.put(destOffset + 9, Math.fma(_self21, _t24, Math.fma(_self01, _t21, _self11 * _t18)));
        dest.put(destOffset + 10, Math.fma(_self22, _t24, Math.fma(_self02, _t21, _self12 * _t18)));
        dest.put(destOffset + 11, Math.fma(_self23, _t24, Math.fma(_self03, _t21, _self13 * _t18)) + Math.fma(-pivotY, _t18, Math.fma(pivotZ, Math.fma(rotX, _t4, rotY * _t5), -(pivotX * _t21))));
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer rot, int rotOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _rotBase = UnsafeOpsHolder.U.getLong(rot, UnsafeCopy.BB_ADDRESS_OFFSET) + rotOffset * 8L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset * 8L;
        Double3x4OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, _rotBase, _pivotBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer rot, int rotOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && rot.hasArray() && rotOffset >= 0 && rotOffset <= rot.limit() - 4 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Double3x4Ops.preRotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, rot.array(), rot.arrayOffset() + rotOffset, pivot.array(), pivot.arrayOffset() + pivotOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preRotateAround_apiGet(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer rot, int rotOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _rotx = rot.get(rotOffset);
        double _roty = rot.get(rotOffset + 1);
        double _rotz = rot.get(rotOffset + 2);
        double _rotw = rot.get(rotOffset + 3);
        double _pivotx = pivot.get(pivotOffset);
        double _pivoty = pivot.get(pivotOffset + 1);
        double _pivotz = pivot.get(pivotOffset + 2);
        double _t4 = _rotx + _rotx;
        double _t5 = _roty + _roty;
        double _t6 = _rotz + _rotz;
        double _t7 = _rotw * _t5;
        return preRotateAround_apiGet_s1f284b27_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _rotx, _roty, _rotz, _pivotx, _pivoty, _pivotz, -_roty, -_pivotz, -_rotx, _t4, _t5, _t7, _rotw * _t6, _rotz * _t6, _rotw * _t4, Math.fma(-_rotz, _t6, 1.0), Math.fma(_rotz, _t4, _t7));
    }

    /** Piece 2 of {@code preRotateAround_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preRotateAround_apiGet_s1f284b27_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _rotx, double _roty, double _rotz, double _pivotx, double _pivoty, double _pivotz, double _t0, double _t2, double _t3, double _t4, double _t5, double _t7, double _t8, double _t9, double _t10, double _t14, double _t16) {
        double _t17 = Math.fma(_roty, _t4, _t8);
        double _t19 = Math.fma(_roty, _t4, -_t8);
        double _t20 = Math.fma(_rotz, _t5, -_t10);
        double _t22 = Math.fma(_t0, _t5, _t14);
        double _t23 = Math.fma(_t3, _t4, _t14);
        dest.put(destOffset, Math.fma(_self20, _t16, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.put(destOffset + 1, Math.fma(_self21, _t16, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.put(destOffset + 2, Math.fma(_self22, _t16, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.put(destOffset + 3, Math.fma(_self23, _t16, Math.fma(_self03, _t22, _self13 * _t19)) + Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19))));
        dest.put(destOffset + 4, Math.fma(_self20, _t20, Math.fma(_self00, _t17, _self10 * _t23)));
        return preRotateAround_apiGet_s1f284b27_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _rotx, _roty, _pivotx, _pivoty, _pivotz, _t2, _t4, _t5, _t9, _t17, Math.fma(_rotz, _t5, _t10), _t20, Math.fma(_rotz, _t4, -_t7), _t23, Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0)));
    }

    /** Piece 3 of {@code preRotateAround_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preRotateAround_apiGet_s1f284b27_2(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _rotx, double _roty, double _pivotx, double _pivoty, double _pivotz, double _t2, double _t4, double _t5, double _t9, double _t17, double _t18, double _t20, double _t21, double _t23, double _t24) {
        dest.put(destOffset + 5, Math.fma(_self21, _t20, Math.fma(_self01, _t17, _self11 * _t23)));
        dest.put(destOffset + 6, Math.fma(_self22, _t20, Math.fma(_self02, _t17, _self12 * _t23)));
        dest.put(destOffset + 7, Math.fma(_self23, _t20, Math.fma(_self03, _t17, _self13 * _t23)) + Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17))));
        dest.put(destOffset + 8, Math.fma(_self20, _t24, Math.fma(_self00, _t21, _self10 * _t18)));
        dest.put(destOffset + 9, Math.fma(_self21, _t24, Math.fma(_self01, _t21, _self11 * _t18)));
        dest.put(destOffset + 10, Math.fma(_self22, _t24, Math.fma(_self02, _t21, _self12 * _t18)));
        dest.put(destOffset + 11, Math.fma(_self23, _t24, Math.fma(_self03, _t21, _self13 * _t18)) + Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21))));
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateAxis_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateAxis_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.preRotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preRotateAxis_apiGet(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateAxis_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t4 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        return preRotateAxis_apiGet_s283f001c_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(axisZ, _t0, _t11 * _t4), Math.fma(axisX, _t0, _t11 * _t6), Math.fma(_t11, _t4, -(axisZ * _t0)), Math.fma(_t11, _t6, -(axisX * _t0)), Math.fma(_t11, _t2, -(axisY * _t0)));
    }

    /** Piece 2 of {@code preRotateAxis_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preRotateAxis_apiGet_s283f001c_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest.put(destOffset, Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24)));
        dest.put(destOffset + 1, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        dest.put(destOffset + 2, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        dest.put(destOffset + 3, Math.fma(_self23, _t21, Math.fma(_self03, _t18, _self13 * _t24)));
        dest.put(destOffset + 4, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.put(destOffset + 5, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.put(destOffset + 6, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.put(destOffset + 7, Math.fma(_self23, _t25, Math.fma(_self03, _t22, _self13 * _t19)));
        dest.put(destOffset + 8, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        dest.put(destOffset + 9, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        return preRotateAxis_apiGet_s283f001c_2(dest, destOffset, _self02, _self03, _self12, _self13, _self22, _self23, _t20, _t23, _t26);
    }

    /** Piece 3 of {@code preRotateAxis_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preRotateAxis_apiGet_s283f001c_2(java.nio.DoubleBuffer dest, int destOffset, double _self02, double _self03, double _self12, double _self13, double _self22, double _self23, double _t20, double _t23, double _t26) {
        dest.put(destOffset + 10, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        dest.put(destOffset + 11, Math.fma(_self23, _t20, Math.fma(_self03, _t26, _self13 * _t23)));
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateAxis_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset * 8L;
        Double3x4OpsKernelsAddress.preRotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateAxis_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            Double3x4Ops.preRotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preRotateAxis_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateAxis_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _axisx = axis.get(axisOffset);
        double _axisy = axis.get(axisOffset + 1);
        double _axisz = axis.get(axisOffset + 2);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisz;
        double _t4 = _axisx * _axisy;
        double _t6 = _axisy * _axisz;
        double _t11 = 1.0 - _t1;
        return preRotateAxis_apiGet_sb09cf7a8_1(dest, destOffset, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _axisx, _axisy, _axisz, _t2, _t4, _t6, _t11, Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1), Math.fma(_axisy, _t0, _t11 * _t2), Math.fma(_axisz, _t0, _t11 * _t4), Math.fma(_axisx, _t0, _t11 * _t6));
    }

    /** Piece 2 of {@code preRotateAxis_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preRotateAxis_apiGet_sb09cf7a8_1(java.nio.DoubleBuffer dest, int destOffset, double _t0, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _axisx, double _axisy, double _axisz, double _t2, double _t4, double _t6, double _t11, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _t24 = Math.fma(_t11, _t4, -(_axisz * _t0));
        double _t25 = Math.fma(_t11, _t6, -(_axisx * _t0));
        dest.put(destOffset, Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24)));
        dest.put(destOffset + 1, Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24)));
        dest.put(destOffset + 2, Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24)));
        dest.put(destOffset + 3, Math.fma(_self23, _t21, Math.fma(_self03, _t18, _self13 * _t24)));
        dest.put(destOffset + 4, Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19)));
        dest.put(destOffset + 5, Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19)));
        dest.put(destOffset + 6, Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19)));
        dest.put(destOffset + 7, Math.fma(_self23, _t25, Math.fma(_self03, _t22, _self13 * _t19)));
        return preRotateAxis_apiGet_sb09cf7a8_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t20, _t23, Math.fma(_t11, _t2, -(_axisy * _t0)));
    }

    /** Piece 3 of {@code preRotateAxis_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preRotateAxis_apiGet_sb09cf7a8_2(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t20, double _t23, double _t26) {
        dest.put(destOffset + 8, Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23)));
        dest.put(destOffset + 9, Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23)));
        dest.put(destOffset + 10, Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23)));
        dest.put(destOffset + 11, Math.fma(_self23, _t20, Math.fma(_self03, _t26, _self13 * _t23)));
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateQuat_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double qX, double qY, double qZ, double qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.preRotateQuat_unsafe(_destBase, _srcBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateQuat_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double qX, double qY, double qZ, double qW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.preRotateQuat(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, qX, qY, qZ, qW);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preRotateQuat_apiGet(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateQuat_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double qX, double qY, double qZ, double qW) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t0 = -qY;
        double _t2 = -qX;
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        double _t6 = qW * _t4;
        double _t7 = qW * _t5;
        double _t8 = qW * _t3;
        double _t12 = Math.fma(-qZ, _t5, 1.0);
        return preRotateQuat_apiGet_s1ac5bf57_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, Math.fma(qZ, _t3, _t6), Math.fma(qY, _t3, _t7), Math.fma(qZ, _t4, _t8), Math.fma(qY, _t3, -_t7), Math.fma(qZ, _t4, -_t8), Math.fma(qZ, _t3, -_t6), Math.fma(_t0, _t4, _t12), Math.fma(_t2, _t3, _t12), Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
    }

    /** Piece 2 of {@code preRotateQuat_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preRotateQuat_apiGet_s1ac5bf57_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22) {
        dest.put(destOffset, Math.fma(_self20, _t14, Math.fma(_self00, _t20, _self10 * _t17)));
        dest.put(destOffset + 1, Math.fma(_self21, _t14, Math.fma(_self01, _t20, _self11 * _t17)));
        dest.put(destOffset + 2, Math.fma(_self22, _t14, Math.fma(_self02, _t20, _self12 * _t17)));
        dest.put(destOffset + 3, Math.fma(_self23, _t14, Math.fma(_self03, _t20, _self13 * _t17)));
        dest.put(destOffset + 4, Math.fma(_self20, _t18, Math.fma(_self00, _t15, _self10 * _t21)));
        dest.put(destOffset + 5, Math.fma(_self21, _t18, Math.fma(_self01, _t15, _self11 * _t21)));
        dest.put(destOffset + 6, Math.fma(_self22, _t18, Math.fma(_self02, _t15, _self12 * _t21)));
        dest.put(destOffset + 7, Math.fma(_self23, _t18, Math.fma(_self03, _t15, _self13 * _t21)));
        dest.put(destOffset + 8, Math.fma(_self20, _t22, Math.fma(_self00, _t19, _self10 * _t16)));
        dest.put(destOffset + 9, Math.fma(_self21, _t22, Math.fma(_self01, _t19, _self11 * _t16)));
        return preRotateQuat_apiGet_s1ac5bf57_2(dest, destOffset, _self02, _self03, _self12, _self13, _self22, _self23, _t16, _t19, _t22);
    }

    /** Piece 3 of {@code preRotateQuat_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preRotateQuat_apiGet_s1ac5bf57_2(java.nio.DoubleBuffer dest, int destOffset, double _self02, double _self03, double _self12, double _self13, double _self22, double _self23, double _t16, double _t19, double _t22) {
        dest.put(destOffset + 10, Math.fma(_self22, _t22, Math.fma(_self02, _t19, _self12 * _t16)));
        dest.put(destOffset + 11, Math.fma(_self23, _t22, Math.fma(_self03, _t19, _self13 * _t16)));
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateQuat_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + qOffset * 8L;
        Double3x4OpsKernelsAddress.preRotateQuat_unsafe(_destBase, _srcBase, _qBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateQuat_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer q, int qOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && q.hasArray() && qOffset >= 0 && qOffset <= q.limit() - 4) {
            Double3x4Ops.preRotateQuat(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, q.array(), q.arrayOffset() + qOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preRotateQuat_apiGet(dest, destOffset, src, srcOffset, q, qOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateQuat_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer q, int qOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _qx = q.get(qOffset);
        double _qy = q.get(qOffset + 1);
        double _qz = q.get(qOffset + 2);
        double _qw = q.get(qOffset + 3);
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        double _t6 = _qw * _t4;
        double _t7 = _qw * _t5;
        double _t8 = _qw * _t3;
        return preRotateQuat_apiGet_s7082b488_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_qy, -_qx, _t3, _t4, Math.fma(-_qz, _t5, 1.0), Math.fma(_qz, _t3, _t6), Math.fma(_qy, _t3, _t7), Math.fma(_qz, _t4, _t8), Math.fma(_qy, _t3, -_t7), Math.fma(_qz, _t4, -_t8), Math.fma(_qz, _t3, -_t6));
    }

    /** Piece 2 of {@code preRotateQuat_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preRotateQuat_apiGet_s7082b488_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t2, double _t3, double _t4, double _t12, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19) {
        double _t20 = Math.fma(_t0, _t4, _t12);
        double _t21 = Math.fma(_t2, _t3, _t12);
        dest.put(destOffset, Math.fma(_self20, _t14, Math.fma(_self00, _t20, _self10 * _t17)));
        dest.put(destOffset + 1, Math.fma(_self21, _t14, Math.fma(_self01, _t20, _self11 * _t17)));
        dest.put(destOffset + 2, Math.fma(_self22, _t14, Math.fma(_self02, _t20, _self12 * _t17)));
        dest.put(destOffset + 3, Math.fma(_self23, _t14, Math.fma(_self03, _t20, _self13 * _t17)));
        dest.put(destOffset + 4, Math.fma(_self20, _t18, Math.fma(_self00, _t15, _self10 * _t21)));
        dest.put(destOffset + 5, Math.fma(_self21, _t18, Math.fma(_self01, _t15, _self11 * _t21)));
        dest.put(destOffset + 6, Math.fma(_self22, _t18, Math.fma(_self02, _t15, _self12 * _t21)));
        dest.put(destOffset + 7, Math.fma(_self23, _t18, Math.fma(_self03, _t15, _self13 * _t21)));
        return preRotateQuat_apiGet_s7082b488_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t16, _t19, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
    }

    /** Piece 3 of {@code preRotateQuat_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preRotateQuat_apiGet_s7082b488_2(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t16, double _t19, double _t22) {
        dest.put(destOffset + 8, Math.fma(_self20, _t22, Math.fma(_self00, _t19, _self10 * _t16)));
        dest.put(destOffset + 9, Math.fma(_self21, _t22, Math.fma(_self01, _t19, _self11 * _t16)));
        dest.put(destOffset + 10, Math.fma(_self22, _t22, Math.fma(_self02, _t19, _self12 * _t16)));
        dest.put(destOffset + 11, Math.fma(_self23, _t22, Math.fma(_self03, _t19, _self13 * _t16)));
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.preRotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.preRotateX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preRotateX_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, _self01);
        dest.put(destOffset + 2, _self02);
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(_self10, _t1, -(_self20 * _t0)));
        dest.put(destOffset + 5, Math.fma(_self11, _t1, -(_self21 * _t0)));
        dest.put(destOffset + 6, Math.fma(_self12, _t1, -(_self22 * _t0)));
        dest.put(destOffset + 7, Math.fma(_self13, _t1, -(_self23 * _t0)));
        dest.put(destOffset + 8, Math.fma(_self10, _t0, _self20 * _t1));
        return preRotateX_apiGet_s2ba06134_1(dest, destOffset, _t0, _self11, _self12, _self13, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code preRotateX_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preRotateX_apiGet_s2ba06134_1(java.nio.DoubleBuffer dest, int destOffset, double _t0, double _self11, double _self12, double _self13, double _self21, double _self22, double _self23, double _t1) {
        dest.put(destOffset + 9, Math.fma(_self11, _t0, _self21 * _t1));
        dest.put(destOffset + 10, Math.fma(_self12, _t0, _self22 * _t1));
        dest.put(destOffset + 11, Math.fma(_self13, _t0, _self23 * _t1));
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.preRotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.preRotateY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preRotateY_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset, Math.fma(_self00, _t1, _self20 * _t0));
        dest.put(destOffset + 1, Math.fma(_self01, _t1, _self21 * _t0));
        dest.put(destOffset + 2, Math.fma(_self02, _t1, _self22 * _t0));
        dest.put(destOffset + 3, Math.fma(_self03, _t1, _self23 * _t0));
        dest.put(destOffset + 4, _self10);
        dest.put(destOffset + 5, _self11);
        dest.put(destOffset + 6, _self12);
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(_self20, _t1, -(_self00 * _t0)));
        return preRotateY_apiGet_s8835aac3_1(dest, destOffset, _t0, _self01, _self02, _self03, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code preRotateY_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preRotateY_apiGet_s8835aac3_1(java.nio.DoubleBuffer dest, int destOffset, double _t0, double _self01, double _self02, double _self03, double _self21, double _self22, double _self23, double _t1) {
        dest.put(destOffset + 9, Math.fma(_self21, _t1, -(_self01 * _t0)));
        dest.put(destOffset + 10, Math.fma(_self22, _t1, -(_self02 * _t0)));
        dest.put(destOffset + 11, Math.fma(_self23, _t1, -(_self03 * _t0)));
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.preRotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.preRotateZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preRotateZ_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer preRotateZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset, Math.fma(_self00, _t1, -(_self10 * _t0)));
        dest.put(destOffset + 1, Math.fma(_self01, _t1, -(_self11 * _t0)));
        dest.put(destOffset + 2, Math.fma(_self02, _t1, -(_self12 * _t0)));
        dest.put(destOffset + 3, Math.fma(_self03, _t1, -(_self13 * _t0)));
        dest.put(destOffset + 4, Math.fma(_self00, _t0, _self10 * _t1));
        dest.put(destOffset + 5, Math.fma(_self01, _t0, _self11 * _t1));
        dest.put(destOffset + 6, Math.fma(_self02, _t0, _self12 * _t1));
        dest.put(destOffset + 7, Math.fma(_self03, _t0, _self13 * _t1));
        return preRotateZ_apiGet_sea7627ea_1(dest, destOffset, _self20, _self21, _self22, _self23);
    }

    /** Piece 2 of {@code preRotateZ_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preRotateZ_apiGet_sea7627ea_1(java.nio.DoubleBuffer dest, int destOffset, double _self20, double _self21, double _self22, double _self23) {
        dest.put(destOffset + 8, _self20);
        dest.put(destOffset + 9, _self21);
        dest.put(destOffset + 10, _self22);
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        dest.put(destOffset, src.get(srcOffset) * vX);
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

    public static java.nio.DoubleBuffer preScale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 8L;
        Double3x4OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Double3x4Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _vx = v.get(vOffset);
        double _vy = v.get(vOffset + 1);
        double _vz = v.get(vOffset + 2);
        dest.put(destOffset, src.get(srcOffset) * _vx);
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

    public static java.nio.DoubleBuffer preScale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, s);
        return dest;
    }

    public static java.nio.DoubleBuffer preScale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        for (int _i = 0; _i < 12; _i++) {
            dest.put(destOffset + _i, s * src.get(srcOffset + _i));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer preScaleAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer preScaleAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.preScaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s, pivotX, pivotY, pivotZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preScaleAround_apiGet(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer preScaleAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t0 = 1.0 - s;
        dest.put(destOffset, s * src.get(srcOffset));
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

    public static java.nio.DoubleBuffer preScaleAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset * 8L;
        Double3x4OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.DoubleBuffer preScaleAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Double3x4Ops.preScaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, s);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preScaleAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return dest;
    }

    public static java.nio.DoubleBuffer preScaleAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double s) {
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _pivotx = pivot.get(pivotOffset);
        double _pivoty = pivot.get(pivotOffset + 1);
        double _pivotz = pivot.get(pivotOffset + 2);
        double _t0 = 1.0 - s;
        dest.put(destOffset, s * src.get(srcOffset));
        dest.put(destOffset + 1, s * _self01);
        dest.put(destOffset + 2, s * _self02);
        dest.put(destOffset + 3, Math.fma(s, _self03, _pivotx * _t0));
        dest.put(destOffset + 4, s * _self10);
        dest.put(destOffset + 5, s * _self11);
        dest.put(destOffset + 6, s * _self12);
        dest.put(destOffset + 7, Math.fma(s, _self13, _pivoty * _t0));
        dest.put(destOffset + 8, s * _self20);
        return preScaleAround_apiGet_s95ce45e5_1(dest, destOffset, s, _self21, _self22, _self23, _pivotz, _t0);
    }

    /** Piece 2 of {@code preScaleAround_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preScaleAround_apiGet_s95ce45e5_1(java.nio.DoubleBuffer dest, int destOffset, double s, double _self21, double _self22, double _self23, double _pivotz, double _t0) {
        dest.put(destOffset + 9, s * _self21);
        dest.put(destOffset + 10, s * _self22);
        dest.put(destOffset + 11, Math.fma(s, _self23, _pivotz * _t0));
        return dest;
    }

    public static java.nio.DoubleBuffer preScaleAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer preScaleAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.preScaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preScaleAround_apiGet(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer preScaleAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        dest.put(destOffset, sX * src.get(srcOffset));
        dest.put(destOffset + 1, sX * _self01);
        dest.put(destOffset + 2, sX * _self02);
        dest.put(destOffset + 3, Math.fma(pivotX, 1.0 - sX, sX * _self03));
        dest.put(destOffset + 4, sY * _self10);
        dest.put(destOffset + 5, sY * _self11);
        dest.put(destOffset + 6, sY * _self12);
        dest.put(destOffset + 7, Math.fma(pivotY, 1.0 - sY, sY * _self13));
        dest.put(destOffset + 8, sZ * _self20);
        dest.put(destOffset + 9, sZ * _self21);
        dest.put(destOffset + 10, sZ * _self22);
        dest.put(destOffset + 11, Math.fma(pivotZ, 1.0 - sZ, sZ * _self23));
        return dest;
    }

    public static java.nio.DoubleBuffer preScaleAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer s, int sOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + sOffset * 8L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset * 8L;
        Double3x4OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preScaleAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer s, int sOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && s.hasArray() && sOffset >= 0 && sOffset <= s.limit() - 3 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Double3x4Ops.preScaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s.array(), s.arrayOffset() + sOffset, pivot.array(), pivot.arrayOffset() + pivotOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preScaleAround_apiGet(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preScaleAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer s, int sOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _sx = s.get(sOffset);
        double _sy = s.get(sOffset + 1);
        double _sz = s.get(sOffset + 2);
        double _pivotx = pivot.get(pivotOffset);
        double _pivoty = pivot.get(pivotOffset + 1);
        double _pivotz = pivot.get(pivotOffset + 2);
        dest.put(destOffset, _sx * src.get(srcOffset));
        dest.put(destOffset + 1, _sx * _self01);
        dest.put(destOffset + 2, _sx * _self02);
        dest.put(destOffset + 3, Math.fma(_pivotx, 1.0 - _sx, _sx * _self03));
        dest.put(destOffset + 4, _sy * _self10);
        dest.put(destOffset + 5, _sy * _self11);
        dest.put(destOffset + 6, _sy * _self12);
        return preScaleAround_apiGet_sfde06b6a_1(dest, destOffset, _self13, _self20, _self21, _self22, _self23, _sy, _sz, _pivoty, _pivotz);
    }

    /** Piece 2 of {@code preScaleAround_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer preScaleAround_apiGet_sfde06b6a_1(java.nio.DoubleBuffer dest, int destOffset, double _self13, double _self20, double _self21, double _self22, double _self23, double _sy, double _sz, double _pivoty, double _pivotz) {
        dest.put(destOffset + 7, Math.fma(_pivoty, 1.0 - _sy, _sy * _self13));
        dest.put(destOffset + 8, _sz * _self20);
        dest.put(destOffset + 9, _sz * _self21);
        dest.put(destOffset + 10, _sz * _self22);
        dest.put(destOffset + 11, Math.fma(_pivotz, 1.0 - _sz, _sz * _self23));
        return dest;
    }

    public static java.nio.DoubleBuffer preTranslate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer preTranslate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.preTranslate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preTranslate_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer preTranslate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        dest.put(destOffset, src.get(srcOffset));
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

    public static java.nio.DoubleBuffer preTranslate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 8L;
        Double3x4OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer preTranslate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Double3x4Ops.preTranslate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.preTranslate_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer preTranslate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _vx = v.get(vOffset);
        double _vy = v.get(vOffset + 1);
        double _vz = v.get(vOffset + 2);
        dest.put(destOffset, src.get(srcOffset));
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

    public static java.nio.DoubleBuffer reflect_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.reflect(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normalX, normalY, normalZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.reflect_apiGet(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _sp0 = normalX + normalX;
        double _t0 = -_self02;
        double _t9 = _sp0 * normalZ;
        double _t10 = _sp0 * normalY;
        double _t11 = (normalY + normalY) * normalZ;
        double _t12 = Math.fma(-2.0, normalX * normalX, 1.0);
        double _t13 = Math.fma(-2.0, normalY * normalY, 1.0);
        dest.put(destOffset, Math.fma(_t0, _t9, Math.fma(_self00, _t12, -(_self01 * _t10))));
        dest.put(destOffset + 1, Math.fma(_t0, _t11, Math.fma(_self01, _t13, -(_self00 * _t10))));
        return reflect_apiGet_s33256dce_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_self12, -_self22, _t9, _t10, _t11, _t12, _t13, Math.fma(-2.0, normalZ * normalZ, 1.0));
    }

    /** Piece 2 of {@code reflect_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer reflect_apiGet_s33256dce_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t1, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14) {
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

    public static java.nio.DoubleBuffer reflect_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _normalBase = UnsafeOpsHolder.U.getLong(normal, UnsafeCopy.BB_ADDRESS_OFFSET) + normalOffset * 8L;
        Double3x4OpsKernelsAddress.reflect_unsafe(_destBase, _srcBase, _normalBase);
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && normal.hasArray() && normalOffset >= 0 && normalOffset <= normal.limit() - 3) {
            Double3x4Ops.reflect(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, normal.array(), normal.arrayOffset() + normalOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.reflect_apiGet(dest, destOffset, src, srcOffset, normal, normalOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer reflect_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _normalx = normal.get(normalOffset);
        double _normaly = normal.get(normalOffset + 1);
        double _normalz = normal.get(normalOffset + 2);
        double _sp0 = _normalx + _normalx;
        double _t0 = -_self02;
        double _t9 = _sp0 * _normalz;
        double _t10 = _sp0 * _normaly;
        double _t12 = Math.fma(-2.0, _normalx * _normalx, 1.0);
        dest.put(destOffset, Math.fma(_t0, _t9, Math.fma(_self00, _t12, -(_self01 * _t10))));
        return reflect_apiGet_se353c874_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, -_self12, -_self22, _t9, _t10, (_normaly + _normaly) * _normalz, _t12, Math.fma(-2.0, _normaly * _normaly, 1.0), Math.fma(-2.0, _normalz * _normalz, 1.0));
    }

    /** Piece 2 of {@code reflect_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer reflect_apiGet_se353c874_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t1, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14) {
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

    public static java.nio.DoubleBuffer rotateAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.rotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.rotateAround_apiGet(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t0 = -rotY;
        double _t2 = -rotX;
        double _t5 = rotX + rotX;
        double _t6 = rotY + rotY;
        double _t7 = rotZ + rotZ;
        double _t8 = rotW * _t6;
        double _t9 = rotW * _t7;
        double _t10 = rotW * _t5;
        double _t16 = Math.fma(-rotZ, _t7, 1.0);
        return rotateAround_apiGet_s33645cdc_1(dest, destOffset, rotX, rotY, pivotX, pivotY, pivotZ, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0, _t2, -pivotZ, _t5, _t6, rotZ * _t7, Math.fma(rotY, _t5, _t9), Math.fma(rotZ, _t6, _t10), Math.fma(rotZ, _t5, _t8), Math.fma(rotZ, _t5, -_t8), Math.fma(rotY, _t5, -_t9), Math.fma(rotZ, _t6, -_t10), Math.fma(_t0, _t6, _t16), Math.fma(_t2, _t5, _t16));
    }

    /** Piece 2 of {@code rotateAround_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateAround_apiGet_s33645cdc_1(java.nio.DoubleBuffer dest, int destOffset, double rotX, double rotY, double pivotX, double pivotY, double pivotZ, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t2, double _t3, double _t5, double _t6, double _t11, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t27, double _t28) {
        double _t29 = Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0));
        double _t39 = Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25)));
        double _t40 = Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18)));
        double _t41 = Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24)));
        dest.put(destOffset, Math.fma(_self02, _t24, Math.fma(_self00, _t27, _self01 * _t18)));
        dest.put(destOffset + 1, Math.fma(_self02, _t19, Math.fma(_self00, _t25, _self01 * _t28)));
        dest.put(destOffset + 2, Math.fma(_self02, _t29, Math.fma(_self00, _t20, _self01 * _t26)));
        dest.put(destOffset + 3, Math.fma(_self00, _t39, Math.fma(_self01, _t40, Math.fma(_self02, _t41, _self03))));
        dest.put(destOffset + 4, Math.fma(_self12, _t24, Math.fma(_self10, _t27, _self11 * _t18)));
        return rotateAround_apiGet_s33645cdc_2(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t18, _t19, _t20, _t24, _t25, _t26, _t27, _t28, _t29, _t39, _t40, _t41);
    }

    /** Piece 3 of {@code rotateAround_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateAround_apiGet_s33645cdc_2(java.nio.DoubleBuffer dest, int destOffset, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t39, double _t40, double _t41) {
        dest.put(destOffset + 5, Math.fma(_self12, _t19, Math.fma(_self10, _t25, _self11 * _t28)));
        dest.put(destOffset + 6, Math.fma(_self12, _t29, Math.fma(_self10, _t20, _self11 * _t26)));
        dest.put(destOffset + 7, Math.fma(_self10, _t39, Math.fma(_self11, _t40, Math.fma(_self12, _t41, _self13))));
        dest.put(destOffset + 8, Math.fma(_self22, _t24, Math.fma(_self20, _t27, _self21 * _t18)));
        dest.put(destOffset + 9, Math.fma(_self22, _t19, Math.fma(_self20, _t25, _self21 * _t28)));
        dest.put(destOffset + 10, Math.fma(_self22, _t29, Math.fma(_self20, _t20, _self21 * _t26)));
        dest.put(destOffset + 11, Math.fma(_self20, _t39, Math.fma(_self21, _t40, Math.fma(_self22, _t41, _self23))));
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer rot, int rotOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _rotBase = UnsafeOpsHolder.U.getLong(rot, UnsafeCopy.BB_ADDRESS_OFFSET) + rotOffset * 8L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset * 8L;
        Double3x4OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _rotBase, _pivotBase);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer rot, int rotOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && rot.hasArray() && rotOffset >= 0 && rotOffset <= rot.limit() - 4 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Double3x4Ops.rotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, rot.array(), rot.arrayOffset() + rotOffset, pivot.array(), pivot.arrayOffset() + pivotOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.rotateAround_apiGet(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer rot, int rotOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _rotx = rot.get(rotOffset);
        double _roty = rot.get(rotOffset + 1);
        double _rotz = rot.get(rotOffset + 2);
        double _rotw = rot.get(rotOffset + 3);
        double _pivotx = pivot.get(pivotOffset);
        double _pivoty = pivot.get(pivotOffset + 1);
        double _pivotz = pivot.get(pivotOffset + 2);
        double _t5 = _rotx + _rotx;
        double _t6 = _roty + _roty;
        double _t7 = _rotz + _rotz;
        double _t9 = _rotw * _t7;
        return rotateAround_apiGet_s304dab34_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _rotx, _roty, _rotz, _pivotx, _pivoty, _pivotz, -_roty, -_rotx, -_pivotz, _t5, _t6, _rotw * _t6, _t9, _rotw * _t5, _rotz * _t7, Math.fma(-_rotz, _t7, 1.0), Math.fma(_roty, _t5, _t9));
    }

    /** Piece 2 of {@code rotateAround_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateAround_apiGet_s304dab34_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _rotx, double _roty, double _rotz, double _pivotx, double _pivoty, double _pivotz, double _t0, double _t2, double _t3, double _t5, double _t6, double _t8, double _t9, double _t10, double _t11, double _t16, double _t18) {
        double _t19 = Math.fma(_rotz, _t6, _t10);
        double _t20 = Math.fma(_rotz, _t5, _t8);
        double _t24 = Math.fma(_rotz, _t5, -_t8);
        double _t25 = Math.fma(_roty, _t5, -_t9);
        double _t26 = Math.fma(_rotz, _t6, -_t10);
        double _t27 = Math.fma(_t0, _t6, _t16);
        double _t28 = Math.fma(_t2, _t5, _t16);
        double _t29 = Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0));
        dest.put(destOffset, Math.fma(_self02, _t24, Math.fma(_self00, _t27, _self01 * _t18)));
        dest.put(destOffset + 1, Math.fma(_self02, _t19, Math.fma(_self00, _t25, _self01 * _t28)));
        dest.put(destOffset + 2, Math.fma(_self02, _t29, Math.fma(_self00, _t20, _self01 * _t26)));
        return rotateAround_apiGet_s304dab34_2(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t18, _t19, _t20, _t24, _t25, _t26, _t27, _t28, _t29, Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25))), Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18))), Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24))));
    }

    /** Piece 3 of {@code rotateAround_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateAround_apiGet_s304dab34_2(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t39, double _t40, double _t41) {
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

    public static java.nio.DoubleBuffer rotateAxis_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, axisX, axisY, axisZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t5 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        return rotateAxis_apiGet_sd4f98b77_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisZ, _t0, _t11 * _t5), Math.fma(axisX, _t0, _t11 * _t6), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(_t11, _t5, -(axisZ * _t0)), Math.fma(_t11, _t6, -(axisX * _t0)));
    }

    /** Piece 2 of {@code rotateAxis_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateAxis_apiGet_sd4f98b77_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest.put(destOffset, Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21)));
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

    public static java.nio.DoubleBuffer rotateAxis_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _axisBase = UnsafeOpsHolder.U.getLong(axis, UnsafeCopy.BB_ADDRESS_OFFSET) + axisOffset * 8L;
        Double3x4OpsKernelsAddress.rotateAxis_unsafe(_destBase, _srcBase, _axisBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && axis.hasArray() && axisOffset >= 0 && axisOffset <= axis.limit() - 3) {
            Double3x4Ops.rotateAxis(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, axis.array(), axis.arrayOffset() + axisOffset, angle);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.rotateAxis_apiGet(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateAxis_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _axisx = axis.get(axisOffset);
        double _axisy = axis.get(axisOffset + 1);
        double _axisz = axis.get(axisOffset + 2);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisz;
        double _t5 = _axisx * _axisy;
        double _t6 = _axisy * _axisz;
        double _t11 = 1.0 - _t1;
        return rotateAxis_apiGet_sb8f81183_1(dest, destOffset, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _axisx, _axisy, _axisz, _t2, _t5, _t6, _t11, Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1), Math.fma(_axisz, _t0, _t11 * _t5), Math.fma(_axisx, _t0, _t11 * _t6), Math.fma(_axisy, _t0, _t11 * _t2));
    }

    /** Piece 2 of {@code rotateAxis_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateAxis_apiGet_sb8f81183_1(java.nio.DoubleBuffer dest, int destOffset, double _t0, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _axisx, double _axisy, double _axisz, double _t2, double _t5, double _t6, double _t11, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _t24 = Math.fma(_t11, _t2, -(_axisy * _t0));
        double _t25 = Math.fma(_t11, _t5, -(_axisz * _t0));
        double _t26 = Math.fma(_t11, _t6, -(_axisx * _t0));
        dest.put(destOffset, Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21)));
        dest.put(destOffset + 1, Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19)));
        dest.put(destOffset + 2, Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26)));
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21)));
        dest.put(destOffset + 5, Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19)));
        dest.put(destOffset + 6, Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26)));
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21)));
        return rotateAxis_apiGet_sb8f81183_2(dest, destOffset, _self20, _self21, _self22, _self23, _t19, _t20, _t22, _t23, _t25, _t26);
    }

    /** Piece 3 of {@code rotateAxis_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateAxis_apiGet_sb8f81183_2(java.nio.DoubleBuffer dest, int destOffset, double _self20, double _self21, double _self22, double _self23, double _t19, double _t20, double _t22, double _t23, double _t25, double _t26) {
        dest.put(destOffset + 9, Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19)));
        dest.put(destOffset + 10, Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26)));
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateQuat_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double qX, double qY, double qZ, double qW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.rotateQuat_unsafe(_destBase, _srcBase, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateQuat_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double qX, double qY, double qZ, double qW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.rotateQuat(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, qX, qY, qZ, qW);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.rotateQuat_apiGet(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateQuat_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double qX, double qY, double qZ, double qW) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t0 = -qY;
        double _t2 = -qX;
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        double _t6 = qW * _t4;
        double _t7 = qW * _t5;
        double _t8 = qW * _t3;
        double _t12 = Math.fma(-qZ, _t5, 1.0);
        return rotateQuat_apiGet_sc126ed54_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, Math.fma(qY, _t3, _t7), Math.fma(qZ, _t4, _t8), Math.fma(qZ, _t3, _t6), Math.fma(qZ, _t3, -_t6), Math.fma(qY, _t3, -_t7), Math.fma(qZ, _t4, -_t8), Math.fma(_t0, _t4, _t12), Math.fma(_t2, _t3, _t12), Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
    }

    /** Piece 2 of {@code rotateQuat_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateQuat_apiGet_sc126ed54_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22) {
        dest.put(destOffset, Math.fma(_self02, _t17, Math.fma(_self00, _t20, _self01 * _t14)));
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

    public static java.nio.DoubleBuffer rotateQuat_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer q, int qOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _qBase = UnsafeOpsHolder.U.getLong(q, UnsafeCopy.BB_ADDRESS_OFFSET) + qOffset * 8L;
        Double3x4OpsKernelsAddress.rotateQuat_unsafe(_destBase, _srcBase, _qBase);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateQuat_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer q, int qOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && q.hasArray() && qOffset >= 0 && qOffset <= q.limit() - 4) {
            Double3x4Ops.rotateQuat(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, q.array(), q.arrayOffset() + qOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.rotateQuat_apiGet(dest, destOffset, src, srcOffset, q, qOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateQuat_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer q, int qOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _qx = q.get(qOffset);
        double _qy = q.get(qOffset + 1);
        double _qz = q.get(qOffset + 2);
        double _qw = q.get(qOffset + 3);
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        double _t6 = _qw * _t4;
        double _t7 = _qw * _t5;
        double _t8 = _qw * _t3;
        return rotateQuat_apiGet_see5f110f_1(dest, destOffset, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, -_qy, -_qx, _t3, _t4, Math.fma(-_qz, _t5, 1.0), Math.fma(_qy, _t3, _t7), Math.fma(_qz, _t4, _t8), Math.fma(_qz, _t3, _t6), Math.fma(_qz, _t3, -_t6), Math.fma(_qy, _t3, -_t7), Math.fma(_qz, _t4, -_t8));
    }

    /** Piece 2 of {@code rotateQuat_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateQuat_apiGet_see5f110f_1(java.nio.DoubleBuffer dest, int destOffset, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t0, double _t2, double _t3, double _t4, double _t12, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19) {
        double _t20 = Math.fma(_t0, _t4, _t12);
        double _t21 = Math.fma(_t2, _t3, _t12);
        double _t22 = Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0));
        dest.put(destOffset, Math.fma(_self02, _t17, Math.fma(_self00, _t20, _self01 * _t14)));
        dest.put(destOffset + 1, Math.fma(_self02, _t15, Math.fma(_self00, _t18, _self01 * _t21)));
        dest.put(destOffset + 2, Math.fma(_self02, _t22, Math.fma(_self00, _t16, _self01 * _t19)));
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(_self12, _t17, Math.fma(_self10, _t20, _self11 * _t14)));
        dest.put(destOffset + 5, Math.fma(_self12, _t15, Math.fma(_self10, _t18, _self11 * _t21)));
        dest.put(destOffset + 6, Math.fma(_self12, _t22, Math.fma(_self10, _t16, _self11 * _t19)));
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(_self22, _t17, Math.fma(_self20, _t20, _self21 * _t14)));
        return rotateQuat_apiGet_see5f110f_2(dest, destOffset, _self20, _self21, _self22, _self23, _t15, _t16, _t18, _t19, _t21, _t22);
    }

    /** Piece 3 of {@code rotateQuat_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateQuat_apiGet_see5f110f_2(java.nio.DoubleBuffer dest, int destOffset, double _self20, double _self21, double _self22, double _self23, double _t15, double _t16, double _t18, double _t19, double _t21, double _t22) {
        dest.put(destOffset + 9, Math.fma(_self22, _t15, Math.fma(_self20, _t18, _self21 * _t21)));
        dest.put(destOffset + 10, Math.fma(_self22, _t22, Math.fma(_self20, _t16, _self21 * _t19)));
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.rotateX_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.rotateX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.rotateX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L, angle);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.rotateX_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, Math.fma(_self01, _t1, _self02 * _t0));
        dest.put(destOffset + 2, Math.fma(_self02, _t1, -(_self01 * _t0)));
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, _self10);
        dest.put(destOffset + 5, Math.fma(_self11, _t1, _self12 * _t0));
        dest.put(destOffset + 6, Math.fma(_self12, _t1, -(_self11 * _t0)));
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, _self20);
        return rotateX_apiGet_sb27d081d_1(dest, destOffset, _t0, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code rotateX_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateX_apiGet_sb27d081d_1(java.nio.DoubleBuffer dest, int destOffset, double _t0, double _self21, double _self22, double _self23, double _t1) {
        dest.put(destOffset + 9, Math.fma(_self21, _t1, _self22 * _t0));
        dest.put(destOffset + 10, Math.fma(_self22, _t1, -(_self21 * _t0)));
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateXYZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.rotateXYZ_unsafe(_destBase, _srcBase, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateXYZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.rotateXYZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleX, angleY, angleZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.rotateXYZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L, angleX, angleY, angleZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.rotateXYZ_apiGet(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateXYZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t3 = Math.cosFromSin(_t0, angleX);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleY);
        double _t6 = _t0 * _t2;
        double _t7 = _t2 * _t3;
        return rotateXYZ_apiGet_sb84d38b5_1(dest, destOffset, _t2, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t1 * _t5, _t0 * _t5, _t5 * _t4, _t3 * _t5, Math.fma(_t6, _t4, _t1 * _t3), Math.fma(_t7, _t1, _t0 * _t4), Math.fma(_t0, _t1, -(_t7 * _t4)), Math.fma(_t3, _t4, -(_t6 * _t1)));
    }

    /** Piece 2 of {@code rotateXYZ_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateXYZ_apiGet_sb84d38b5_1(java.nio.DoubleBuffer dest, int destOffset, double _t2, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t10, double _t11, double _t13, double _t15, double _t18, double _t19, double _t20, double _t21) {
        dest.put(destOffset, Math.fma(_self02, _t20, Math.fma(_self00, _t13, _self01 * _t18)));
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

    public static java.nio.DoubleBuffer rotateXZY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.rotateXZY_unsafe(_destBase, _srcBase, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateXZY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.rotateXZY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleX, angleZ, angleY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.rotateXZY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L, angleX, angleZ, angleY);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.rotateXZY_apiGet(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateXZY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t3 = Math.cosFromSin(_t2, angleY);
        double _t4 = Math.cosFromSin(_t0, angleX);
        double _t5 = Math.cosFromSin(_t1, angleZ);
        double _t6 = _t0 * _t1;
        double _t9 = _t1 * _t4;
        return rotateXZY_apiGet_s3b84394d_1(dest, destOffset, _t1, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0 * _t5, _t2 * _t5, _t3 * _t5, _t4 * _t5, Math.fma(_t9, _t3, _t0 * _t2), Math.fma(_t6, _t2, _t4 * _t3), Math.fma(_t6, _t3, -(_t2 * _t4)), Math.fma(_t9, _t2, -(_t0 * _t3)));
    }

    /** Piece 2 of {@code rotateXZY_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateXZY_apiGet_s3b84394d_1(java.nio.DoubleBuffer dest, int destOffset, double _t1, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t10, double _t11, double _t15, double _t16, double _t18, double _t19, double _t20, double _t21) {
        dest.put(destOffset, Math.fma(_self02, _t20, Math.fma(_self00, _t15, _self01 * _t18)));
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

    public static java.nio.DoubleBuffer rotateY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.rotateY_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.rotateY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.rotateY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L, angle);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.rotateY_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset, Math.fma(_self00, _t1, -(_self02 * _t0)));
        dest.put(destOffset + 1, _self01);
        dest.put(destOffset + 2, Math.fma(_self00, _t0, _self02 * _t1));
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(_self10, _t1, -(_self12 * _t0)));
        dest.put(destOffset + 5, _self11);
        dest.put(destOffset + 6, Math.fma(_self10, _t0, _self12 * _t1));
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(_self20, _t1, -(_self22 * _t0)));
        return rotateY_apiGet_s9bb43dee_1(dest, destOffset, _t0, _self20, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code rotateY_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateY_apiGet_s9bb43dee_1(java.nio.DoubleBuffer dest, int destOffset, double _t0, double _self20, double _self21, double _self22, double _self23, double _t1) {
        dest.put(destOffset + 9, _self21);
        dest.put(destOffset + 10, Math.fma(_self20, _t0, _self22 * _t1));
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateYXZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.rotateYXZ_unsafe(_destBase, _srcBase, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateYXZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.rotateYXZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleY, angleX, angleZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.rotateYXZ_apiGet(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateYXZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t8 = _t0 * _t3;
        return rotateYXZ_apiGet_sb01cbf11_1(dest, destOffset, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t2 * _t5, _t1 * _t5, _t5 * _t4, _t5 * _t3, Math.fma(_t6, _t2, _t3 * _t4), Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t8, _t2, -(_t1 * _t4)), Math.fma(_t6, _t4, -(_t2 * _t3)));
    }

    /** Piece 2 of {@code rotateYXZ_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateYXZ_apiGet_sb01cbf11_1(java.nio.DoubleBuffer dest, int destOffset, double _t0, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t10, double _t12, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21) {
        dest.put(destOffset, Math.fma(_self02, _t20, Math.fma(_self00, _t18, _self01 * _t10)));
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

    public static java.nio.DoubleBuffer rotateYZX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.rotateYZX_unsafe(_destBase, _srcBase, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateYZX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.rotateYZX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleY, angleZ, angleX);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.rotateYZX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L, angleY, angleZ, angleX);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.rotateYZX_apiGet(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateYZX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t3 = Math.cosFromSin(_t1, angleZ);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t9 = _t1 * _t4;
        return rotateYZX_apiGet_s539ae65d_1(dest, destOffset, _t1, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0 * _t3, _t2 * _t3, _t4 * _t3, _t5 * _t3, Math.fma(_t6, _t5, _t2 * _t4), Math.fma(_t9, _t2, _t0 * _t5), Math.fma(_t2, _t0, -(_t9 * _t5)), Math.fma(_t5, _t4, -(_t6 * _t2)));
    }

    /** Piece 2 of {@code rotateYZX_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateYZX_apiGet_s539ae65d_1(java.nio.DoubleBuffer dest, int destOffset, double _t1, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t7, double _t11, double _t13, double _t14, double _t18, double _t19, double _t20, double _t21) {
        dest.put(destOffset, Math.fma(-_self02, _t7, Math.fma(_self00, _t13, _self01 * _t1)));
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

    public static java.nio.DoubleBuffer rotateZ_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.rotateZ_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZ_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.rotateZ(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.rotateZ_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L, angle);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.rotateZ_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZ_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset, Math.fma(_self00, _t1, _self01 * _t0));
        dest.put(destOffset + 1, Math.fma(_self01, _t1, -(_self00 * _t0)));
        dest.put(destOffset + 2, _self02);
        dest.put(destOffset + 3, _self03);
        dest.put(destOffset + 4, Math.fma(_self10, _t1, _self11 * _t0));
        dest.put(destOffset + 5, Math.fma(_self11, _t1, -(_self10 * _t0)));
        dest.put(destOffset + 6, _self12);
        dest.put(destOffset + 7, _self13);
        dest.put(destOffset + 8, Math.fma(_self20, _t1, _self21 * _t0));
        return rotateZ_apiGet_sf0ba0e47_1(dest, destOffset, _t0, _self20, _self21, _self22, _self23, _t1);
    }

    /** Piece 2 of {@code rotateZ_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateZ_apiGet_sf0ba0e47_1(java.nio.DoubleBuffer dest, int destOffset, double _t0, double _self20, double _self21, double _self22, double _self23, double _t1) {
        dest.put(destOffset + 9, Math.fma(_self21, _t1, -(_self20 * _t0)));
        dest.put(destOffset + 10, _self22);
        dest.put(destOffset + 11, _self23);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZXY_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.rotateZXY_unsafe(_destBase, _srcBase, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZXY_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.rotateZXY(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleZ, angleX, angleY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.rotateZXY_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L, angleZ, angleX, angleY);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.rotateZXY_apiGet(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZXY_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleX);
        double _t2 = Math.sin(angleZ);
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t3 = Math.cosFromSin(_t1, angleX);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleZ);
        double _t6 = _t1 * _t2;
        double _t8 = _t1 * _t5;
        return rotateZXY_apiGet_s86436381_1(dest, destOffset, _t1, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t0 * _t3, _t2 * _t3, _t3 * _t5, _t3 * _t4, Math.fma(_t8, _t0, _t2 * _t4), Math.fma(_t6, _t4, _t0 * _t5), Math.fma(_t4, _t5, -(_t6 * _t0)), Math.fma(_t0, _t2, -(_t8 * _t4)));
    }

    /** Piece 2 of {@code rotateZXY_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateZXY_apiGet_s86436381_1(java.nio.DoubleBuffer dest, int destOffset, double _t1, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t7, double _t10, double _t14, double _t15, double _t18, double _t19, double _t20, double _t21) {
        dest.put(destOffset, Math.fma(-_self02, _t7, Math.fma(_self00, _t20, _self01 * _t18)));
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

    public static java.nio.DoubleBuffer rotateZYX_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.rotateZYX_unsafe(_destBase, _srcBase, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZYX_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.rotateZYX(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angleZ, angleY, angleX);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.rotateZYX_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L, angleZ, angleY, angleX);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.rotateZYX_apiGet(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return dest;
    }

    public static java.nio.DoubleBuffer rotateZYX_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t10 = _t0 * _t4;
        return rotateZYX_apiGet_s1a5c95d5_1(dest, destOffset, _t0, _self00, _self01, _self02, _self03, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t1 * _t3, _t2 * _t3, _t3 * _t4, _t5 * _t3, Math.fma(_t6, _t2, _t5 * _t4), Math.fma(_t10, _t5, _t2 * _t1), Math.fma(_t10, _t2, -(_t1 * _t5)), Math.fma(_t6, _t5, -(_t2 * _t4)));
    }

    /** Piece 2 of {@code rotateZYX_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer rotateZYX_apiGet_s1a5c95d5_1(java.nio.DoubleBuffer dest, int destOffset, double _t0, double _self00, double _self01, double _self02, double _self03, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t8, double _t9, double _t15, double _t17, double _t18, double _t19, double _t20, double _t21) {
        dest.put(destOffset, Math.fma(-_self02, _t0, Math.fma(_self00, _t15, _self01 * _t8)));
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

    public static java.nio.DoubleBuffer scale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo) * vX);
            dest.put(destOffset + _lo + 1, _eself1 * vY);
            dest.put(destOffset + _lo + 2, _eself2 * vZ);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer scale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 8L;
        Double3x4OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Double3x4Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _vx = v.get(vOffset);
        double _vy = v.get(vOffset + 1);
        double _vz = v.get(vOffset + 2);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo) * _vx);
            dest.put(destOffset + _lo + 1, _eself1 * _vy);
            dest.put(destOffset + _lo + 2, _eself2 * _vz);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer scale_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, s);
        return dest;
    }

    public static java.nio.DoubleBuffer scale_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, s * src.get(srcOffset + _lo));
            dest.put(destOffset + _lo + 1, s * _eself1);
            dest.put(destOffset + _lo + 2, s * _eself2);
            dest.put(destOffset + _lo + 3, _eself3);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer scaleAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer scaleAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.scaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s, pivotX, pivotY, pivotZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.scaleAround_apiGet(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer scaleAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        double _t3 = pivotZ * _t0;
        dest.put(destOffset, s * _self00);
        dest.put(destOffset + 1, s * _self01);
        dest.put(destOffset + 2, s * _self02);
        dest.put(destOffset + 3, Math.fma(_self00, _t1, Math.fma(_self01, _t2, Math.fma(_self02, _t3, _self03))));
        dest.put(destOffset + 4, s * _self10);
        dest.put(destOffset + 5, s * _self11);
        dest.put(destOffset + 6, s * _self12);
        dest.put(destOffset + 7, Math.fma(_self10, _t1, Math.fma(_self11, _t2, Math.fma(_self12, _t3, _self13))));
        return scaleAround_apiGet_s8dd46199_1(dest, destOffset, s, _self20, _self21, _self22, _self23, _t1, _t2, _t3);
    }

    /** Piece 2 of {@code scaleAround_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer scaleAround_apiGet_s8dd46199_1(java.nio.DoubleBuffer dest, int destOffset, double s, double _self20, double _self21, double _self22, double _self23, double _t1, double _t2, double _t3) {
        dest.put(destOffset + 8, s * _self20);
        dest.put(destOffset + 9, s * _self21);
        dest.put(destOffset + 10, s * _self22);
        dest.put(destOffset + 11, Math.fma(_self20, _t1, Math.fma(_self21, _t2, Math.fma(_self22, _t3, _self23))));
        return dest;
    }

    public static java.nio.DoubleBuffer scaleAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset * 8L;
        Double3x4OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.DoubleBuffer scaleAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Double3x4Ops.scaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, s);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.scaleAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return dest;
    }

    public static java.nio.DoubleBuffer scaleAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double s) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t0 = 1.0 - s;
        double _t1 = pivot.get(pivotOffset) * _t0;
        double _t2 = pivot.get(pivotOffset + 1) * _t0;
        double _t3 = pivot.get(pivotOffset + 2) * _t0;
        dest.put(destOffset, s * _self00);
        dest.put(destOffset + 1, s * _self01);
        dest.put(destOffset + 2, s * _self02);
        dest.put(destOffset + 3, Math.fma(_self00, _t1, Math.fma(_self01, _t2, Math.fma(_self02, _t3, _self03))));
        dest.put(destOffset + 4, s * _self10);
        dest.put(destOffset + 5, s * _self11);
        dest.put(destOffset + 6, s * _self12);
        return scaleAround_apiGet_sca3ce21c_1(dest, destOffset, s, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _t1, _t2, _t3);
    }

    /** Piece 2 of {@code scaleAround_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer scaleAround_apiGet_sca3ce21c_1(java.nio.DoubleBuffer dest, int destOffset, double s, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _t1, double _t2, double _t3) {
        dest.put(destOffset + 7, Math.fma(_self10, _t1, Math.fma(_self11, _t2, Math.fma(_self12, _t3, _self13))));
        dest.put(destOffset + 8, s * _self20);
        dest.put(destOffset + 9, s * _self21);
        dest.put(destOffset + 10, s * _self22);
        dest.put(destOffset + 11, Math.fma(_self20, _t1, Math.fma(_self21, _t2, Math.fma(_self22, _t3, _self23))));
        return dest;
    }

    public static java.nio.DoubleBuffer scaleAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer scaleAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.scaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.scaleAround_apiGet(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return dest;
    }

    public static java.nio.DoubleBuffer scaleAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _t3 = pivotX * (1.0 - sX);
        double _t4 = pivotY * (1.0 - sY);
        double _t5 = pivotZ * (1.0 - sZ);
        dest.put(destOffset, sX * _self00);
        dest.put(destOffset + 1, sY * _self01);
        dest.put(destOffset + 2, sZ * _self02);
        dest.put(destOffset + 3, Math.fma(_self00, _t3, Math.fma(_self01, _t4, Math.fma(_self02, _t5, _self03))));
        dest.put(destOffset + 4, sX * _self10);
        dest.put(destOffset + 5, sY * _self11);
        dest.put(destOffset + 6, sZ * _self12);
        dest.put(destOffset + 7, Math.fma(_self10, _t3, Math.fma(_self11, _t4, Math.fma(_self12, _t5, _self13))));
        return scaleAround_apiGet_sdceefba6_1(dest, destOffset, sX, sY, sZ, _self20, _self21, _self22, _self23, _t3, _t4, _t5);
    }

    /** Piece 2 of {@code scaleAround_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer scaleAround_apiGet_sdceefba6_1(java.nio.DoubleBuffer dest, int destOffset, double sX, double sY, double sZ, double _self20, double _self21, double _self22, double _self23, double _t3, double _t4, double _t5) {
        dest.put(destOffset + 8, sX * _self20);
        dest.put(destOffset + 9, sY * _self21);
        dest.put(destOffset + 10, sZ * _self22);
        dest.put(destOffset + 11, Math.fma(_self20, _t3, Math.fma(_self21, _t4, Math.fma(_self22, _t5, _self23))));
        return dest;
    }

    public static java.nio.DoubleBuffer scaleAround_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer s, int sOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + sOffset * 8L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset * 8L;
        Double3x4OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.DoubleBuffer scaleAround_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer s, int sOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && s.hasArray() && sOffset >= 0 && sOffset <= s.limit() - 3 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 3) {
            Double3x4Ops.scaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s.array(), s.arrayOffset() + sOffset, pivot.array(), pivot.arrayOffset() + pivotOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.scaleAround_apiGet(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer scaleAround_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer s, int sOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _sx = s.get(sOffset);
        double _sy = s.get(sOffset + 1);
        double _sz = s.get(sOffset + 2);
        double _t3 = pivot.get(pivotOffset) * (1.0 - _sx);
        double _t4 = pivot.get(pivotOffset + 1) * (1.0 - _sy);
        double _t5 = pivot.get(pivotOffset + 2) * (1.0 - _sz);
        dest.put(destOffset, _sx * _self00);
        dest.put(destOffset + 1, _sy * _self01);
        dest.put(destOffset + 2, _sz * _self02);
        dest.put(destOffset + 3, Math.fma(_self00, _t3, Math.fma(_self01, _t4, Math.fma(_self02, _t5, _self03))));
        dest.put(destOffset + 4, _sx * _self10);
        return scaleAround_apiGet_sd23a70ef_1(dest, destOffset, _self10, _self11, _self12, _self13, _self20, _self21, _self22, _self23, _sx, _sy, _sz, _t3, _t4, _t5);
    }

    /** Piece 2 of {@code scaleAround_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer scaleAround_apiGet_sd23a70ef_1(java.nio.DoubleBuffer dest, int destOffset, double _self10, double _self11, double _self12, double _self13, double _self20, double _self21, double _self22, double _self23, double _sx, double _sy, double _sz, double _t3, double _t4, double _t5) {
        dest.put(destOffset + 5, _sy * _self11);
        dest.put(destOffset + 6, _sz * _self12);
        dest.put(destOffset + 7, Math.fma(_self10, _t3, Math.fma(_self11, _t4, Math.fma(_self12, _t5, _self13))));
        dest.put(destOffset + 8, _sx * _self20);
        dest.put(destOffset + 9, _sy * _self21);
        dest.put(destOffset + 10, _sz * _self22);
        dest.put(destOffset + 11, Math.fma(_self20, _t3, Math.fma(_self21, _t4, Math.fma(_self22, _t5, _self23))));
        return dest;
    }

    public static java.nio.DoubleBuffer translate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer translate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.translate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.translate_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer translate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself0);
            dest.put(destOffset + _lo + 1, _eself1);
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, Math.fma(_eself0, vX, Math.fma(_eself1, vY, Math.fma(_eself2, vZ, _eself3))));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer translate_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 8L;
        Double3x4OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer translate_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Double3x4Ops.translate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.translate_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer translate_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _vx = v.get(vOffset);
        double _vy = v.get(vOffset + 1);
        double _vz = v.get(vOffset + 2);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 4;
            double _eself0 = src.get(srcOffset + _lo);
            double _eself1 = src.get(srcOffset + _lo + 1);
            double _eself2 = src.get(srcOffset + _lo + 2);
            double _eself3 = src.get(srcOffset + _lo + 3);
            dest.put(destOffset + _lo, _eself0);
            dest.put(destOffset + _lo + 1, _eself1);
            dest.put(destOffset + _lo + 2, _eself2);
            dest.put(destOffset + _lo + 3, Math.fma(_eself0, _vx, Math.fma(_eself1, _vy, Math.fma(_eself2, _vz, _eself3))));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec4_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ, double vW) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.mulVec4_unsafe(_destBase, _srcBase, vX, vY, vZ, vW);
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec4_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ, double vW) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.mulVec4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ, vW);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mulVec4_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L, vX, vY, vZ, vW);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mulVec4_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ, vW);
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec4_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ, double vW) {
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        dest.put(destOffset, Math.fma(src.get(srcOffset + 3), vW, Math.fma(src.get(srcOffset + 2), vZ, Math.fma(src.get(srcOffset), vX, src.get(srcOffset + 1) * vY))));
        dest.put(destOffset + 1, Math.fma(_self13, vW, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY))));
        dest.put(destOffset + 2, Math.fma(_self23, vW, Math.fma(_self22, vZ, Math.fma(_self20, vX, _self21 * vY))));
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec4_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 8L;
        Double3x4OpsKernelsAddress.mulVec4_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec4_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 4) {
            Double3x4Ops.mulVec4(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.mulVec4_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), vOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.mulVec4_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer mulVec4_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _vx = v.get(vOffset);
        double _vy = v.get(vOffset + 1);
        double _vz = v.get(vOffset + 2);
        double _vw = v.get(vOffset + 3);
        dest.put(destOffset, Math.fma(src.get(srcOffset + 3), _vw, Math.fma(src.get(srcOffset + 2), _vz, Math.fma(src.get(srcOffset), _vx, src.get(srcOffset + 1) * _vy))));
        dest.put(destOffset + 1, Math.fma(_self13, _vw, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy))));
        dest.put(destOffset + 2, Math.fma(_self23, _vw, Math.fma(_self22, _vz, Math.fma(_self20, _vx, _self21 * _vy))));
        return dest;
    }

    public static java.nio.DoubleBuffer transformAabb_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.transformAabb_unsafe(_destBase, _srcBase, minX, minY, minZ, maxX, maxY, maxZ);
        return dest;
    }

    public static java.nio.DoubleBuffer transformAabb_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.transformAabb(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, minX, minY, minZ, maxX, maxY, maxZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.transformAabb_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L, minX, minY, minZ, maxX, maxY, maxZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.transformAabb_apiGet(dest, destOffset, src, srcOffset, minX, minY, minZ, maxX, maxY, maxZ);
        return dest;
    }

    public static java.nio.DoubleBuffer transformAabb_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        double _self00 = src.get(srcOffset);
        double _self01 = src.get(srcOffset + 1);
        double _self02 = src.get(srcOffset + 2);
        double _self03 = src.get(srcOffset + 3);
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        return transformAabb_apiGet_s3639f8fe_1(dest, destOffset, minX, minY, minZ, maxX, maxY, maxZ, _self03, _self13, _self23, minX * _self00, maxX * _self00, minY * _self01, maxY * _self01, minZ * _self02, maxZ * _self02, minX * _self10, maxX * _self10, minY * _self11, maxY * _self11, minZ * _self12, maxZ * _self12, minX * _self20, maxX * _self20, minY * _self21, maxY * _self21, minZ * _self22, maxZ * _self22);
    }

    /** Piece 2 of {@code transformAabb_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer transformAabb_apiGet_s3639f8fe_1(java.nio.DoubleBuffer dest, int destOffset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, double _self03, double _self13, double _self23, double _t3, double _t4, double _t5, double _t6, double _t7, double _t8, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _t15, double _t16, double _t17, double _t18, double _t19, double _t20) {
        if (java.lang.Math.min(java.lang.Math.min(maxX - minX, maxY - minY), maxZ - minZ) < 0.0) {
            dest.put(destOffset, Double.POSITIVE_INFINITY);
            dest.put(destOffset + 1, Double.POSITIVE_INFINITY);
            dest.put(destOffset + 2, Double.POSITIVE_INFINITY);
            dest.put(destOffset + 3, Double.NEGATIVE_INFINITY);
            dest.put(destOffset + 4, Double.NEGATIVE_INFINITY);
            dest.put(destOffset + 5, Double.NEGATIVE_INFINITY);
        } else {
            dest.put(destOffset, _self03 + java.lang.Math.min(_t3, _t4) + java.lang.Math.min(_t5, _t6) + java.lang.Math.min(_t7, _t8));
            dest.put(destOffset + 1, _self13 + java.lang.Math.min(_t9, _t10) + java.lang.Math.min(_t11, _t12) + java.lang.Math.min(_t13, _t14));
            dest.put(destOffset + 2, _self23 + java.lang.Math.min(_t15, _t16) + java.lang.Math.min(_t17, _t18) + java.lang.Math.min(_t19, _t20));
            dest.put(destOffset + 3, _self03 + java.lang.Math.max(_t3, _t4) + java.lang.Math.max(_t5, _t6) + java.lang.Math.max(_t7, _t8));
            dest.put(destOffset + 4, _self13 + java.lang.Math.max(_t9, _t10) + java.lang.Math.max(_t11, _t12) + java.lang.Math.max(_t13, _t14));
            dest.put(destOffset + 5, _self23 + java.lang.Math.max(_t15, _t16) + java.lang.Math.max(_t17, _t18) + java.lang.Math.max(_t19, _t20));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer transformDirection_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer transformDirection_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.transformDirection(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.transformDirection_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L, vX, vY, vZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.transformDirection_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer transformDirection_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        dest.put(destOffset, Math.fma(src.get(srcOffset + 2), vZ, Math.fma(src.get(srcOffset), vX, src.get(srcOffset + 1) * vY)));
        dest.put(destOffset + 1, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY)));
        dest.put(destOffset + 2, Math.fma(_self22, vZ, Math.fma(_self20, vX, _self21 * vY)));
        return dest;
    }

    public static java.nio.DoubleBuffer transformDirection_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 8L;
        Double3x4OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer transformDirection_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Double3x4Ops.transformDirection(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.transformDirection_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), vOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.transformDirection_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer transformDirection_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _vx = v.get(vOffset);
        double _vy = v.get(vOffset + 1);
        double _vz = v.get(vOffset + 2);
        dest.put(destOffset, Math.fma(src.get(srcOffset + 2), _vz, Math.fma(src.get(srcOffset), _vx, src.get(srcOffset + 1) * _vy)));
        dest.put(destOffset + 1, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy)));
        dest.put(destOffset + 2, Math.fma(_self22, _vz, Math.fma(_self20, _vx, _self21 * _vy)));
        return dest;
    }

    public static java.nio.DoubleBuffer transformPosition_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        Double3x4OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer transformPosition_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            Double3x4Ops.transformPosition(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.transformPosition_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L, vX, vY, vZ);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.transformPosition_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.DoubleBuffer transformPosition_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        dest.put(destOffset, Math.fma(src.get(srcOffset), vX, Math.fma(src.get(srcOffset + 1), vY, Math.fma(src.get(srcOffset + 2), vZ, src.get(srcOffset + 3)))));
        dest.put(destOffset + 1, Math.fma(_self10, vX, Math.fma(_self11, vY, Math.fma(_self12, vZ, _self13))));
        dest.put(destOffset + 2, Math.fma(_self20, vX, Math.fma(_self21, vY, Math.fma(_self22, vZ, _self23))));
        return dest;
    }

    public static java.nio.DoubleBuffer transformPosition_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 8L;
        Double3x4OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.DoubleBuffer transformPosition_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Double3x4Ops.transformPosition(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Double3x4OpsKernelsSegment.transformPosition_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 8L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), vOffset * 8L);
            return dest;
        }
        Double3x4OpsKernelsTypedBuffer.transformPosition_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.DoubleBuffer transformPosition_apiGet(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        double _self10 = src.get(srcOffset + 4);
        double _self11 = src.get(srcOffset + 5);
        double _self12 = src.get(srcOffset + 6);
        double _self13 = src.get(srcOffset + 7);
        double _self20 = src.get(srcOffset + 8);
        double _self21 = src.get(srcOffset + 9);
        double _self22 = src.get(srcOffset + 10);
        double _self23 = src.get(srcOffset + 11);
        double _vx = v.get(vOffset);
        double _vy = v.get(vOffset + 1);
        double _vz = v.get(vOffset + 2);
        dest.put(destOffset, Math.fma(src.get(srcOffset), _vx, Math.fma(src.get(srcOffset + 1), _vy, Math.fma(src.get(srcOffset + 2), _vz, src.get(srcOffset + 3)))));
        dest.put(destOffset + 1, Math.fma(_self10, _vx, Math.fma(_self11, _vy, Math.fma(_self12, _vz, _self13))));
        dest.put(destOffset + 2, Math.fma(_self20, _vx, Math.fma(_self21, _vy, Math.fma(_self22, _vz, _self23))));
        return dest;
    }

    public static java.nio.DoubleBuffer transformPosition_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer matrix, int matrixOffset, java.nio.DoubleBuffer points, int pointsOffset, int count) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _matrixBase = UnsafeOpsHolder.U.getLong(matrix, UnsafeCopy.BB_ADDRESS_OFFSET) + matrixOffset * 8L;
        long _pointsBase = UnsafeOpsHolder.U.getLong(points, UnsafeCopy.BB_ADDRESS_OFFSET) + pointsOffset * 8L;
        Double3x4OpsKernelsAddress.transformPosition_unsafe(_destBase, _matrixBase, _pointsBase, count);
        return dest;
    }

    public static java.nio.DoubleBuffer transformPosition_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer matrix, int matrixOffset, java.nio.DoubleBuffer points, int pointsOffset, int count) {
        double _m00 = matrix.get(matrixOffset);
        double _m01 = matrix.get(matrixOffset + 1);
        double _m02 = matrix.get(matrixOffset + 2);
        double _m03 = matrix.get(matrixOffset + 3);
        double _m10 = matrix.get(matrixOffset + 4);
        double _m11 = matrix.get(matrixOffset + 5);
        double _m12 = matrix.get(matrixOffset + 6);
        double _m13 = matrix.get(matrixOffset + 7);
        double _m20 = matrix.get(matrixOffset + 8);
        double _m21 = matrix.get(matrixOffset + 9);
        double _m22 = matrix.get(matrixOffset + 10);
        double _m23 = matrix.get(matrixOffset + 11);
        for (int _i = 0; _i < count; _i++) {
            int _po = pointsOffset + _i * 3;
            int _do = destOffset + _i * 3;
            double px = points.get(_po), py = points.get(_po + 1), pz = points.get(_po + 2);
            dest.put(_do, Math.fma(_m00, px, Math.fma(_m01, py, Math.fma(_m02, pz, _m03))));
            dest.put(_do + 1, Math.fma(_m10, px, Math.fma(_m11, py, Math.fma(_m12, pz, _m13))));
            dest.put(_do + 2, Math.fma(_m20, px, Math.fma(_m21, py, Math.fma(_m22, pz, _m23))));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer transformPosition_unsafe(java.nio.DoubleBuffer dest, int destOffset, int destStride, java.nio.DoubleBuffer matrix, int matrixOffset, java.nio.DoubleBuffer points, int pointsOffset, int pointsStride, int count) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _matrixBase = UnsafeOpsHolder.U.getLong(matrix, UnsafeCopy.BB_ADDRESS_OFFSET) + matrixOffset * 8L;
        long _pointsBase = UnsafeOpsHolder.U.getLong(points, UnsafeCopy.BB_ADDRESS_OFFSET) + pointsOffset * 8L;
        Double3x4OpsKernelsAddress.transformPosition_unsafe(_destBase, destStride * 8L, _matrixBase, _pointsBase, pointsStride * 8L, count);
        return dest;
    }

    public static java.nio.DoubleBuffer transformPosition_api(java.nio.DoubleBuffer dest, int destOffset, int destStride, java.nio.DoubleBuffer matrix, int matrixOffset, java.nio.DoubleBuffer points, int pointsOffset, int pointsStride, int count) {
        double _m00 = matrix.get(matrixOffset);
        double _m01 = matrix.get(matrixOffset + 1);
        double _m02 = matrix.get(matrixOffset + 2);
        double _m03 = matrix.get(matrixOffset + 3);
        double _m10 = matrix.get(matrixOffset + 4);
        double _m11 = matrix.get(matrixOffset + 5);
        double _m12 = matrix.get(matrixOffset + 6);
        double _m13 = matrix.get(matrixOffset + 7);
        double _m20 = matrix.get(matrixOffset + 8);
        double _m21 = matrix.get(matrixOffset + 9);
        double _m22 = matrix.get(matrixOffset + 10);
        double _m23 = matrix.get(matrixOffset + 11);
        for (int _i = 0; _i < count; _i++) {
            int _po = pointsOffset + _i * pointsStride;
            int _do = destOffset + _i * destStride;
            double px = points.get(_po), py = points.get(_po + 1), pz = points.get(_po + 2);
            dest.put(_do, Math.fma(_m00, px, Math.fma(_m01, py, Math.fma(_m02, pz, _m03))));
            dest.put(_do + 1, Math.fma(_m10, px, Math.fma(_m11, py, Math.fma(_m12, pz, _m13))));
            dest.put(_do + 2, Math.fma(_m20, px, Math.fma(_m21, py, Math.fma(_m22, pz, _m23))));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer transformDirection_unsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer matrix, int matrixOffset, java.nio.DoubleBuffer points, int pointsOffset, int count) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _matrixBase = UnsafeOpsHolder.U.getLong(matrix, UnsafeCopy.BB_ADDRESS_OFFSET) + matrixOffset * 8L;
        long _pointsBase = UnsafeOpsHolder.U.getLong(points, UnsafeCopy.BB_ADDRESS_OFFSET) + pointsOffset * 8L;
        Double3x4OpsKernelsAddress.transformDirection_unsafe(_destBase, _matrixBase, _pointsBase, count);
        return dest;
    }

    public static java.nio.DoubleBuffer transformDirection_api(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer matrix, int matrixOffset, java.nio.DoubleBuffer points, int pointsOffset, int count) {
        double _m00 = matrix.get(matrixOffset);
        double _m01 = matrix.get(matrixOffset + 1);
        double _m02 = matrix.get(matrixOffset + 2);
        double _m10 = matrix.get(matrixOffset + 4);
        double _m11 = matrix.get(matrixOffset + 5);
        double _m12 = matrix.get(matrixOffset + 6);
        double _m20 = matrix.get(matrixOffset + 8);
        double _m21 = matrix.get(matrixOffset + 9);
        double _m22 = matrix.get(matrixOffset + 10);
        for (int _i = 0; _i < count; _i++) {
            int _po = pointsOffset + _i * 3;
            int _do = destOffset + _i * 3;
            double px = points.get(_po), py = points.get(_po + 1), pz = points.get(_po + 2);
            dest.put(_do, Math.fma(_m02, pz, Math.fma(_m00, px, _m01 * py)));
            dest.put(_do + 1, Math.fma(_m12, pz, Math.fma(_m10, px, _m11 * py)));
            dest.put(_do + 2, Math.fma(_m22, pz, Math.fma(_m20, px, _m21 * py)));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer transformDirection_unsafe(java.nio.DoubleBuffer dest, int destOffset, int destStride, java.nio.DoubleBuffer matrix, int matrixOffset, java.nio.DoubleBuffer points, int pointsOffset, int pointsStride, int count) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        long _matrixBase = UnsafeOpsHolder.U.getLong(matrix, UnsafeCopy.BB_ADDRESS_OFFSET) + matrixOffset * 8L;
        long _pointsBase = UnsafeOpsHolder.U.getLong(points, UnsafeCopy.BB_ADDRESS_OFFSET) + pointsOffset * 8L;
        Double3x4OpsKernelsAddress.transformDirection_unsafe(_destBase, destStride * 8L, _matrixBase, _pointsBase, pointsStride * 8L, count);
        return dest;
    }

    public static java.nio.DoubleBuffer transformDirection_api(java.nio.DoubleBuffer dest, int destOffset, int destStride, java.nio.DoubleBuffer matrix, int matrixOffset, java.nio.DoubleBuffer points, int pointsOffset, int pointsStride, int count) {
        double _m00 = matrix.get(matrixOffset);
        double _m01 = matrix.get(matrixOffset + 1);
        double _m02 = matrix.get(matrixOffset + 2);
        double _m10 = matrix.get(matrixOffset + 4);
        double _m11 = matrix.get(matrixOffset + 5);
        double _m12 = matrix.get(matrixOffset + 6);
        double _m20 = matrix.get(matrixOffset + 8);
        double _m21 = matrix.get(matrixOffset + 9);
        double _m22 = matrix.get(matrixOffset + 10);
        for (int _i = 0; _i < count; _i++) {
            int _po = pointsOffset + _i * pointsStride;
            int _do = destOffset + _i * destStride;
            double px = points.get(_po), py = points.get(_po + 1), pz = points.get(_po + 2);
            dest.put(_do, Math.fma(_m02, pz, Math.fma(_m00, px, _m01 * py)));
            dest.put(_do + 1, Math.fma(_m12, pz, Math.fma(_m10, px, _m11 * py)));
            dest.put(_do + 2, Math.fma(_m22, pz, Math.fma(_m20, px, _m21 * py)));
        }
        return dest;
    }

    public static java.nio.DoubleBuffer lerpComposeTRSMul_fmaUnsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer t1, int t1Offset, java.nio.DoubleBuffer t2, int t2Offset, java.nio.DoubleBuffer q1, int q1Offset, java.nio.DoubleBuffer q2, int q2Offset, java.nio.DoubleBuffer s1, int s1Offset, java.nio.DoubleBuffer s2, int s2Offset, java.nio.DoubleBuffer m, int mOffset, double alpha, int count) {
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset * 8L;
        long _t2Base = UnsafeOpsHolder.U.getLong(t2, UnsafeCopy.BB_ADDRESS_OFFSET) + t2Offset * 8L;
        long _q1Base = UnsafeOpsHolder.U.getLong(q1, UnsafeCopy.BB_ADDRESS_OFFSET) + q1Offset * 8L;
        long _q2Base = UnsafeOpsHolder.U.getLong(q2, UnsafeCopy.BB_ADDRESS_OFFSET) + q2Offset * 8L;
        long _s1Base = UnsafeOpsHolder.U.getLong(s1, UnsafeCopy.BB_ADDRESS_OFFSET) + s1Offset * 8L;
        long _s2Base = UnsafeOpsHolder.U.getLong(s2, UnsafeCopy.BB_ADDRESS_OFFSET) + s2Offset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 8L;
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.lerpComposeTRSMul_fmaUnsafe(_destBase, _t1Base, _t2Base, _q1Base, _q2Base, _s1Base, _s2Base, _mBase, alpha, count);
        return dest;
    }

    public static java.nio.DoubleBuffer lerpComposeTRSMul_mulAddUnsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer t1, int t1Offset, java.nio.DoubleBuffer t2, int t2Offset, java.nio.DoubleBuffer q1, int q1Offset, java.nio.DoubleBuffer q2, int q2Offset, java.nio.DoubleBuffer s1, int s1Offset, java.nio.DoubleBuffer s2, int s2Offset, java.nio.DoubleBuffer m, int mOffset, double alpha, int count) {
        long _t1Base = UnsafeOpsHolder.U.getLong(t1, UnsafeCopy.BB_ADDRESS_OFFSET) + t1Offset * 8L;
        long _t2Base = UnsafeOpsHolder.U.getLong(t2, UnsafeCopy.BB_ADDRESS_OFFSET) + t2Offset * 8L;
        long _q1Base = UnsafeOpsHolder.U.getLong(q1, UnsafeCopy.BB_ADDRESS_OFFSET) + q1Offset * 8L;
        long _q2Base = UnsafeOpsHolder.U.getLong(q2, UnsafeCopy.BB_ADDRESS_OFFSET) + q2Offset * 8L;
        long _s1Base = UnsafeOpsHolder.U.getLong(s1, UnsafeCopy.BB_ADDRESS_OFFSET) + s1Offset * 8L;
        long _s2Base = UnsafeOpsHolder.U.getLong(s2, UnsafeCopy.BB_ADDRESS_OFFSET) + s2Offset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 8L;
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.lerpComposeTRSMul_mulAddUnsafe(_destBase, _t1Base, _t2Base, _q1Base, _q2Base, _s1Base, _s2Base, _mBase, alpha, count);
        return dest;
    }

    public static java.nio.DoubleBuffer lerpComposeTRSMul_fmaApi(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer t1, int t1Offset, java.nio.DoubleBuffer t2, int t2Offset, java.nio.DoubleBuffer q1, int q1Offset, java.nio.DoubleBuffer q2, int q2Offset, java.nio.DoubleBuffer s1, int s1Offset, java.nio.DoubleBuffer s2, int s2Offset, java.nio.DoubleBuffer m, int mOffset, double alpha, int count) {
        for (int _i = 0; _i < count; _i++) {
            int _t1o = t1Offset + _i * 3;
            int _t2o = t2Offset + _i * 3;
            int _q1o = q1Offset + _i * 4;
            int _q2o = q2Offset + _i * 4;
            int _s1o = s1Offset + _i * 3;
            int _s2o = s2Offset + _i * 3;
            int _mo = mOffset + _i * 12;
            int _do = destOffset + _i * 12;
            double _ax = t1.get(_t1o), _ay = t1.get(_t1o + 1), _az = t1.get(_t1o + 2);
            double _tx = Math.fma(alpha, t2.get(_t2o) - _ax, _ax);
            double _ty = Math.fma(alpha, t2.get(_t2o + 1) - _ay, _ay);
            double _tz = Math.fma(alpha, t2.get(_t2o + 2) - _az, _az);
            double _bx = s1.get(_s1o), _by = s1.get(_s1o + 1), _bz = s1.get(_s1o + 2);
            double _sx = Math.fma(alpha, s2.get(_s2o) - _bx, _bx);
            double _sy = Math.fma(alpha, s2.get(_s2o + 1) - _by, _by);
            double _sz = Math.fma(alpha, s2.get(_s2o + 2) - _bz, _bz);
            double _ux = q1.get(_q1o), _uy = q1.get(_q1o + 1), _uz = q1.get(_q1o + 2), _uw = q1.get(_q1o + 3);
            double _vx = q2.get(_q2o), _vy = q2.get(_q2o + 1), _vz = q2.get(_q2o + 2), _vw = q2.get(_q2o + 3);
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
            double _m00 = m.get(_mo), _m01 = m.get(_mo + 1), _m02 = m.get(_mo + 2), _m03 = m.get(_mo + 3);
            double _m10 = m.get(_mo + 4), _m11 = m.get(_mo + 5), _m12 = m.get(_mo + 6), _m13 = m.get(_mo + 7);
            double _m20 = m.get(_mo + 8), _m21 = m.get(_mo + 9), _m22 = m.get(_mo + 10), _m23 = m.get(_mo + 11);
            double _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
            double _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
            double _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
            dest.put(_do, _e00);
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

    public static java.nio.DoubleBuffer lerpComposeTRSMul_mulAddApi(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer t1, int t1Offset, java.nio.DoubleBuffer t2, int t2Offset, java.nio.DoubleBuffer q1, int q1Offset, java.nio.DoubleBuffer q2, int q2Offset, java.nio.DoubleBuffer s1, int s1Offset, java.nio.DoubleBuffer s2, int s2Offset, java.nio.DoubleBuffer m, int mOffset, double alpha, int count) {
        for (int _i = 0; _i < count; _i++) {
            int _t1o = t1Offset + _i * 3;
            int _t2o = t2Offset + _i * 3;
            int _q1o = q1Offset + _i * 4;
            int _q2o = q2Offset + _i * 4;
            int _s1o = s1Offset + _i * 3;
            int _s2o = s2Offset + _i * 3;
            int _mo = mOffset + _i * 12;
            int _do = destOffset + _i * 12;
            double _ax = t1.get(_t1o), _ay = t1.get(_t1o + 1), _az = t1.get(_t1o + 2);
            double _bx = s1.get(_s1o), _by = s1.get(_s1o + 1), _bz = s1.get(_s1o + 2);
            double _sx = alpha * (s2.get(_s2o) - _bx) + _bx;
            double _sy = alpha * (s2.get(_s2o + 1) - _by) + _by;
            double _sz = alpha * (s2.get(_s2o + 2) - _bz) + _bz;
            double _ux = q1.get(_q1o), _uy = q1.get(_q1o + 1), _uz = q1.get(_q1o + 2), _uw = q1.get(_q1o + 3);
            double _vx = q2.get(_q2o), _vy = q2.get(_q2o + 1), _vz = q2.get(_q2o + 2), _vw = q2.get(_q2o + 3);
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
            double _m00 = m.get(_mo), _m01 = m.get(_mo + 1), _m02 = m.get(_mo + 2), _m03 = m.get(_mo + 3);
            double _m10 = m.get(_mo + 4), _m11 = m.get(_mo + 5), _m12 = m.get(_mo + 6), _m13 = m.get(_mo + 7);
            double _m20 = m.get(_mo + 8), _m21 = m.get(_mo + 9), _m22 = m.get(_mo + 10), _m23 = m.get(_mo + 11);
            double _e00 = _t02 * _m20 + (_t01 * _m10 + (_t00 * _m00)), _e01 = _t02 * _m21 + (_t01 * _m11 + (_t00 * _m01)), _e02 = _t02 * _m22 + (_t01 * _m12 + (_t00 * _m02)), _e03 = (_t02 * _m23 + (_t01 * _m13 + (_t00 * _m03))) + (alpha * (t2.get(_t2o) - _ax) + _ax);
            double _e10 = _t12 * _m20 + (_t11 * _m10 + (_t10 * _m00)), _e11 = _t12 * _m21 + (_t11 * _m11 + (_t10 * _m01)), _e12 = _t12 * _m22 + (_t11 * _m12 + (_t10 * _m02)), _e13 = (_t12 * _m23 + (_t11 * _m13 + (_t10 * _m03))) + (alpha * (t2.get(_t2o + 1) - _ay) + _ay);
            double _e20 = _t22 * _m20 + (_t21 * _m10 + (_t20 * _m00)), _e21 = _t22 * _m21 + (_t21 * _m11 + (_t20 * _m01)), _e22 = _t22 * _m22 + (_t21 * _m12 + (_t20 * _m02)), _e23 = (_t22 * _m23 + (_t21 * _m13 + (_t20 * _m03))) + (alpha * (t2.get(_t2o + 2) - _az) + _az);
            dest.put(_do, _e00);
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

    public static java.nio.DoubleBuffer composeTRSMul_fmaUnsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, java.nio.DoubleBuffer m, int mOffset, int count) {
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset * 8L;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset * 8L;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 8L;
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.composeTRSMul_fmaUnsafe(_destBase, _translationBase, _rotationBase, _scaleBase, _mBase, count);
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRSMul_mulAddUnsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, java.nio.DoubleBuffer m, int mOffset, int count) {
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset * 8L;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset * 8L;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 8L;
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.composeTRSMul_mulAddUnsafe(_destBase, _translationBase, _rotationBase, _scaleBase, _mBase, count);
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRSMul_fmaApi(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, java.nio.DoubleBuffer m, int mOffset, int count) {
        for (int _i = 0; _i < count; _i++) {
            int _translationo = translationOffset + _i * 3;
            int _rotationo = rotationOffset + _i * 4;
            int _scaleo = scaleOffset + _i * 3;
            int _mo = mOffset + _i * 12;
            int _do = destOffset + _i * 12;
            double _tx = translation.get(_translationo), _ty = translation.get(_translationo + 1), _tz = translation.get(_translationo + 2);
            double _sx = scale.get(_scaleo), _sy = scale.get(_scaleo + 1), _sz = scale.get(_scaleo + 2);
            double _qx = rotation.get(_rotationo), _qy = rotation.get(_rotationo + 1), _qz = rotation.get(_rotationo + 2), _qw = rotation.get(_rotationo + 3);
            double _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            double _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            double _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            double _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            double _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            double _m00 = m.get(_mo), _m01 = m.get(_mo + 1), _m02 = m.get(_mo + 2), _m03 = m.get(_mo + 3);
            double _m10 = m.get(_mo + 4), _m11 = m.get(_mo + 5), _m12 = m.get(_mo + 6), _m13 = m.get(_mo + 7);
            double _m20 = m.get(_mo + 8), _m21 = m.get(_mo + 9), _m22 = m.get(_mo + 10), _m23 = m.get(_mo + 11);
            double _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
            double _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
            double _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
            dest.put(_do, _e00);
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

    public static java.nio.DoubleBuffer composeTRSMul_mulAddApi(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, java.nio.DoubleBuffer m, int mOffset, int count) {
        for (int _i = 0; _i < count; _i++) {
            int _translationo = translationOffset + _i * 3;
            int _rotationo = rotationOffset + _i * 4;
            int _scaleo = scaleOffset + _i * 3;
            int _mo = mOffset + _i * 12;
            int _do = destOffset + _i * 12;
            double _tx = translation.get(_translationo), _ty = translation.get(_translationo + 1), _tz = translation.get(_translationo + 2);
            double _sx = scale.get(_scaleo), _sy = scale.get(_scaleo + 1), _sz = scale.get(_scaleo + 2);
            double _qx = rotation.get(_rotationo), _qy = rotation.get(_rotationo + 1), _qz = rotation.get(_rotationo + 2), _qw = rotation.get(_rotationo + 3);
            double _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            double _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            double _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            double _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            double _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            double _m00 = m.get(_mo), _m01 = m.get(_mo + 1), _m02 = m.get(_mo + 2), _m03 = m.get(_mo + 3);
            double _m10 = m.get(_mo + 4), _m11 = m.get(_mo + 5), _m12 = m.get(_mo + 6), _m13 = m.get(_mo + 7);
            double _m20 = m.get(_mo + 8), _m21 = m.get(_mo + 9), _m22 = m.get(_mo + 10), _m23 = m.get(_mo + 11);
            double _e00 = _t02 * _m20 + (_t01 * _m10 + (_t00 * _m00)), _e01 = _t02 * _m21 + (_t01 * _m11 + (_t00 * _m01)), _e02 = _t02 * _m22 + (_t01 * _m12 + (_t00 * _m02)), _e03 = (_t02 * _m23 + (_t01 * _m13 + (_t00 * _m03))) + _tx;
            double _e10 = _t12 * _m20 + (_t11 * _m10 + (_t10 * _m00)), _e11 = _t12 * _m21 + (_t11 * _m11 + (_t10 * _m01)), _e12 = _t12 * _m22 + (_t11 * _m12 + (_t10 * _m02)), _e13 = (_t12 * _m23 + (_t11 * _m13 + (_t10 * _m03))) + _ty;
            double _e20 = _t22 * _m20 + (_t21 * _m10 + (_t20 * _m00)), _e21 = _t22 * _m21 + (_t21 * _m11 + (_t20 * _m01)), _e22 = _t22 * _m22 + (_t21 * _m12 + (_t20 * _m02)), _e23 = (_t22 * _m23 + (_t21 * _m13 + (_t20 * _m03))) + _tz;
            dest.put(_do, _e00);
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

    public static java.nio.DoubleBuffer composeTRSMulPadded_fmaUnsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, java.nio.DoubleBuffer m, int mOffset) {
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset * 8L;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset * 8L;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 8L;
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.composeTRSMulPadded_fmaUnsafe(_destBase, _translationBase, _rotationBase, _scaleBase, _mBase);
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRSMulPadded_mulAddUnsafe(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, java.nio.DoubleBuffer m, int mOffset) {
        long _translationBase = UnsafeOpsHolder.U.getLong(translation, UnsafeCopy.BB_ADDRESS_OFFSET) + translationOffset * 8L;
        long _rotationBase = UnsafeOpsHolder.U.getLong(rotation, UnsafeCopy.BB_ADDRESS_OFFSET) + rotationOffset * 8L;
        long _scaleBase = UnsafeOpsHolder.U.getLong(scale, UnsafeCopy.BB_ADDRESS_OFFSET) + scaleOffset * 8L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 8L;
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L;
        Double3x4OpsKernelsAddress.composeTRSMulPadded_mulAddUnsafe(_destBase, _translationBase, _rotationBase, _scaleBase, _mBase);
        return dest;
    }

    public static java.nio.DoubleBuffer composeTRSMulPadded_fmaApi(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, java.nio.DoubleBuffer m, int mOffset) {
        double _qx = rotation.get(rotationOffset), _qy = rotation.get(rotationOffset + 1), _qz = rotation.get(rotationOffset + 2), _qw = rotation.get(rotationOffset + 3);
        double _tx = translation.get(translationOffset), _ty = translation.get(translationOffset + 1), _tz = translation.get(translationOffset + 2);
        double _sx = scale.get(scaleOffset), _sy = scale.get(scaleOffset + 1), _sz = scale.get(scaleOffset + 2);
        double _m00 = m.get(mOffset), _m01 = m.get(mOffset + 1), _m02 = m.get(mOffset + 2), _m03 = m.get(mOffset + 3);
        double _m10 = m.get(mOffset + 4), _m11 = m.get(mOffset + 5), _m12 = m.get(mOffset + 6), _m13 = m.get(mOffset + 7);
        double _m20 = m.get(mOffset + 8), _m21 = m.get(mOffset + 9), _m22 = m.get(mOffset + 10), _m23 = m.get(mOffset + 11);
        return composeTRSMulPadded_fmaApi_s7ba70880_1(dest, destOffset, _qx, _qy, _qz, _qw, _tx, _ty, _tz, _sx, _sy, _sz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23);
    }

    /** Piece 2 of {@code composeTRSMulPadded_fmaApi}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer composeTRSMulPadded_fmaApi_s7ba70880_1(java.nio.DoubleBuffer dest, int destOffset, double _qx, double _qy, double _qz, double _qw, double _tx, double _ty, double _tz, double _sx, double _sy, double _sz, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23) {
        double _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
        double _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
        double _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
        double _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
        double _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
        return composeTRSMulPadded_fmaApi_s7ba70880_2(dest, destOffset, _tx, _ty, _tz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t00, _t01, _t02, _t10, _t11, _t12, _t20, _t21, _t22);
    }

    /** Piece 3 of {@code composeTRSMulPadded_fmaApi}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer composeTRSMulPadded_fmaApi_s7ba70880_2(java.nio.DoubleBuffer dest, int destOffset, double _tx, double _ty, double _tz, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t00, double _t01, double _t02, double _t10, double _t11, double _t12, double _t20, double _t21, double _t22) {
        double _e00 = Math.fma(_t02, _m20, Math.fma(_t01, _m10, _t00 * _m00)), _e01 = Math.fma(_t02, _m21, Math.fma(_t01, _m11, _t00 * _m01)), _e02 = Math.fma(_t02, _m22, Math.fma(_t01, _m12, _t00 * _m02)), _e03 = (Math.fma(_t02, _m23, Math.fma(_t01, _m13, _t00 * _m03))) + _tx;
        double _e10 = Math.fma(_t12, _m20, Math.fma(_t11, _m10, _t10 * _m00)), _e11 = Math.fma(_t12, _m21, Math.fma(_t11, _m11, _t10 * _m01)), _e12 = Math.fma(_t12, _m22, Math.fma(_t11, _m12, _t10 * _m02)), _e13 = (Math.fma(_t12, _m23, Math.fma(_t11, _m13, _t10 * _m03))) + _ty;
        double _e20 = Math.fma(_t22, _m20, Math.fma(_t21, _m10, _t20 * _m00)), _e21 = Math.fma(_t22, _m21, Math.fma(_t21, _m11, _t20 * _m01)), _e22 = Math.fma(_t22, _m22, Math.fma(_t21, _m12, _t20 * _m02)), _e23 = (Math.fma(_t22, _m23, Math.fma(_t21, _m13, _t20 * _m03))) + _tz;
        dest.put(destOffset, _e00);
        dest.put(destOffset + 1, _e01);
        return composeTRSMulPadded_fmaApi_s7ba70880_3(dest, destOffset, _e02, _e03, _e10, _e11, _e12, _e13, _e20, _e21, _e22, _e23);
    }

    /** Piece 4 of {@code composeTRSMulPadded_fmaApi}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer composeTRSMulPadded_fmaApi_s7ba70880_3(java.nio.DoubleBuffer dest, int destOffset, double _e02, double _e03, double _e10, double _e11, double _e12, double _e13, double _e20, double _e21, double _e22, double _e23) {
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

    public static java.nio.DoubleBuffer composeTRSMulPadded_mulAddApi(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer translation, int translationOffset, java.nio.DoubleBuffer rotation, int rotationOffset, java.nio.DoubleBuffer scale, int scaleOffset, java.nio.DoubleBuffer m, int mOffset) {
        double _qx = rotation.get(rotationOffset), _qy = rotation.get(rotationOffset + 1), _qz = rotation.get(rotationOffset + 2), _qw = rotation.get(rotationOffset + 3);
        double _tx = translation.get(translationOffset), _ty = translation.get(translationOffset + 1), _tz = translation.get(translationOffset + 2);
        double _sx = scale.get(scaleOffset), _sy = scale.get(scaleOffset + 1), _sz = scale.get(scaleOffset + 2);
        double _m00 = m.get(mOffset), _m01 = m.get(mOffset + 1), _m02 = m.get(mOffset + 2), _m03 = m.get(mOffset + 3);
        double _m10 = m.get(mOffset + 4), _m11 = m.get(mOffset + 5), _m12 = m.get(mOffset + 6), _m13 = m.get(mOffset + 7);
        double _m20 = m.get(mOffset + 8), _m21 = m.get(mOffset + 9), _m22 = m.get(mOffset + 10), _m23 = m.get(mOffset + 11);
        return composeTRSMulPadded_mulAddApi_s95945d1f_1(dest, destOffset, _qx, _qy, _qz, _qw, _tx, _ty, _tz, _sx, _sy, _sz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23);
    }

    /** Piece 2 of {@code composeTRSMulPadded_mulAddApi}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer composeTRSMulPadded_mulAddApi_s95945d1f_1(java.nio.DoubleBuffer dest, int destOffset, double _qx, double _qy, double _qz, double _qw, double _tx, double _ty, double _tz, double _sx, double _sy, double _sz, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23) {
        double _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
        double _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
        double _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
        double _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
        double _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
        return composeTRSMulPadded_mulAddApi_s95945d1f_2(dest, destOffset, _tx, _ty, _tz, _m00, _m01, _m02, _m03, _m10, _m11, _m12, _m13, _m20, _m21, _m22, _m23, _t00, _t01, _t02, _t10, _t11, _t12, _t20, _t21, _t22);
    }

    /** Piece 3 of {@code composeTRSMulPadded_mulAddApi}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer composeTRSMulPadded_mulAddApi_s95945d1f_2(java.nio.DoubleBuffer dest, int destOffset, double _tx, double _ty, double _tz, double _m00, double _m01, double _m02, double _m03, double _m10, double _m11, double _m12, double _m13, double _m20, double _m21, double _m22, double _m23, double _t00, double _t01, double _t02, double _t10, double _t11, double _t12, double _t20, double _t21, double _t22) {
        double _e00 = _t02 * _m20 + (_t01 * _m10 + (_t00 * _m00)), _e01 = _t02 * _m21 + (_t01 * _m11 + (_t00 * _m01)), _e02 = _t02 * _m22 + (_t01 * _m12 + (_t00 * _m02)), _e03 = (_t02 * _m23 + (_t01 * _m13 + (_t00 * _m03))) + _tx;
        double _e10 = _t12 * _m20 + (_t11 * _m10 + (_t10 * _m00)), _e11 = _t12 * _m21 + (_t11 * _m11 + (_t10 * _m01)), _e12 = _t12 * _m22 + (_t11 * _m12 + (_t10 * _m02)), _e13 = (_t12 * _m23 + (_t11 * _m13 + (_t10 * _m03))) + _ty;
        double _e20 = _t22 * _m20 + (_t21 * _m10 + (_t20 * _m00)), _e21 = _t22 * _m21 + (_t21 * _m11 + (_t20 * _m01)), _e22 = _t22 * _m22 + (_t21 * _m12 + (_t20 * _m02)), _e23 = (_t22 * _m23 + (_t21 * _m13 + (_t20 * _m03))) + _tz;
        dest.put(destOffset, _e00);
        dest.put(destOffset + 1, _e01);
        dest.put(destOffset + 2, _e02);
        dest.put(destOffset + 3, _e03);
        dest.put(destOffset + 4, _e10);
        return composeTRSMulPadded_mulAddApi_s95945d1f_3(dest, destOffset, _e11, _e12, _e13, _e20, _e21, _e22, _e23);
    }

    /** Piece 4 of {@code composeTRSMulPadded_mulAddApi}, split to fit the inline budget; reached only through it. */
    private static java.nio.DoubleBuffer composeTRSMulPadded_mulAddApi_s95945d1f_3(java.nio.DoubleBuffer dest, int destOffset, double _e11, double _e12, double _e13, double _e20, double _e21, double _e22, double _e23) {
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
