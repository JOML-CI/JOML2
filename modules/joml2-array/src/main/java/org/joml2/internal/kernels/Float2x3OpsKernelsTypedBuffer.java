// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float2x3Ops} whose leading storage
 * parameter is a typed {@link java.nio.FloatBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float2x3Ops} and its sibling kernel units. Not public API.
 */
public final class Float2x3OpsKernelsTypedBuffer {
    private Float2x3OpsKernelsTypedBuffer() {}

    public static java.nio.FloatBuffer getColumn_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int col) {
        Float2x3OpsKernelsAddress.getColumn_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, col);
        return dest;
    }

    public static java.nio.FloatBuffer getColumn_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int col) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.getColumn(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, col);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.getColumn_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, col);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.getColumn_apiGet(dest, destOffset, src, srcOffset, col);
        return dest;
    }

    public static java.nio.FloatBuffer getColumn_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int col) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _idxSw0;
        float _idxSw1;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; break;
            case 2: _idxSw0 = _self02; _idxSw1 = _self12; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest.put(destOffset, _idxSw0);
        dest.put(destOffset + 1, _idxSw1);
        return dest;
    }

    public static float getRotationAngle_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        return Float2x3OpsKernelsAddress.getRotationAngle_unsafe(UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L);
    }

    public static float getRotationAngle_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            return Float2x3Ops.getRotationAngle(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2x3OpsKernelsSegment.getRotationAngle_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
        }
        return Float2x3OpsKernelsTypedBuffer.getRotationAngle_apiGet(src, srcOffset);
    }

    public static float getRotationAngle_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        return Math.atan2(src.get(srcOffset + 1), src.get(srcOffset));
    }

    public static java.nio.FloatBuffer getRow_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int row) {
        Float2x3OpsKernelsAddress.getRow_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, row);
        return dest;
    }

    public static java.nio.FloatBuffer getRow_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int row) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 3 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.getRow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, row);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.getRow_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, row);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.getRow_apiGet(dest, destOffset, src, srcOffset, row);
        return dest;
    }

    public static java.nio.FloatBuffer getRow_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int row) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; _idxSw2 = _self02; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; _idxSw2 = _self12; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest.put(destOffset, _idxSw0);
        dest.put(destOffset + 1, _idxSw1);
        dest.put(destOffset + 2, _idxSw2);
        return dest;
    }

    public static java.nio.FloatBuffer getTranslation_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        Float2x3OpsKernelsAddress.getTranslation_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L);
        return dest;
    }

    public static java.nio.FloatBuffer getTranslation_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.getTranslation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.getTranslation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.getTranslation_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer getTranslation_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self12 = src.get(srcOffset + 5);
        dest.put(destOffset, src.get(srcOffset + 4));
        dest.put(destOffset + 1, _self12);
        return dest;
    }

    public static float determinant_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2x3OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static float determinant_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            return Float2x3Ops.determinant(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2x3OpsKernelsSegment.determinant_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
        }
        return Float2x3OpsKernelsTypedBuffer.determinant_apiGet(src, srcOffset);
    }

    public static float determinant_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        return Math.fma(src.get(srcOffset), src.get(srcOffset + 3), -(src.get(srcOffset + 2) * src.get(srcOffset + 1)));
    }

    public static float frobeniusNorm_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2x3OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static float frobeniusNorm_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            return Float2x3Ops.frobeniusNorm(src.array(), src.arrayOffset() + srcOffset);
        }
        if (src.order() == java.nio.ByteOrder.nativeOrder()) {
            return Float2x3OpsKernelsSegment.frobeniusNorm_api(java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
        }
        return Float2x3OpsKernelsTypedBuffer.frobeniusNorm_apiGet(src, srcOffset);
    }

    public static float frobeniusNorm_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        return (float) java.lang.Math.sqrt(Math.fma(_self12, _self12, Math.fma(_self11, _self11, Math.fma(_self10, _self10, Math.fma(_self02, _self02, Math.fma(_self00, _self00, _self01 * _self01))))));
    }

    public static java.nio.FloatBuffer invert_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invert_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.invert(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.invert_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.invert_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invert_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _t3 = Math.fma(_self00, _self11, -(_self01 * _self10));
        if (!(java.lang.Math.abs(_t3) > 1.1754944E-38f && java.lang.Math.abs(_t3) < 8.507059E37f)) return Float2x3OpsKernelsTypedBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        float _t3_inv = 1.0f / _t3;
        dest.put(destOffset, _self11 * _t3_inv);
        dest.put(destOffset + 1, -(_self10 * _t3_inv));
        dest.put(destOffset + 2, -(_self01 * _t3_inv));
        dest.put(destOffset + 3, _self00 * _t3_inv);
        dest.put(destOffset + 4, -(Math.fma(_self02, _self11, -(_self01 * _self12)) * _t3_inv));
        dest.put(destOffset + 5, -(Math.fma(_self00, _self12, -(_self02 * _self10)) * _t3_inv));
        return dest;
    }

    public static java.nio.FloatBuffer invert_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float2x3OpsKernelsTypedBuffer.invert_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.FloatBuffer invert_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invert_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3OpsKernelsArray.invert_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.invert_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.invert_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invert_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _t0 = unitScale(_self10, _self11, _self10);
        float _t1 = unitScale(_self00, _self01, _self00);
        float _t8 = _self11 * _t0;
        float _t9 = _self00 * _t1;
        float _t10 = _self01 * _t1;
        float _t11 = _self10 * _t0;
        float _t12 = _self02 * _t1;
        float _t13 = _self12 * _t0;
        float _t16_inv = 1.0f / Math.fma(_t9, _t8, -(_t10 * _t11));
        float _sp1 = _t0 * _t16_inv;
        float _sp0 = _t1 * _t16_inv;
        dest.put(destOffset, _t8 * _sp0);
        dest.put(destOffset + 1, -(_t11 * _sp0));
        dest.put(destOffset + 2, -(_t10 * _sp1));
        dest.put(destOffset + 3, _t9 * _sp1);
        dest.put(destOffset + 4, -(Math.fma(_t12, _t8, -(_t10 * _t13)) * _t16_inv));
        dest.put(destOffset + 5, -(Math.fma(_t9, _t13, -(_t12 * _t11)) * _t16_inv));
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2x3OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 6) {
            Float2x3Ops.invertProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.invertProduct_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.invertProduct_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _other00 = other.get(otherOffset);
        float _other10 = other.get(otherOffset + 1);
        float _other01 = other.get(otherOffset + 2);
        float _other11 = other.get(otherOffset + 3);
        float _other02 = other.get(otherOffset + 4);
        float _other12 = other.get(otherOffset + 5);
        return invertProduct_apiGet_s2c11ff3b_1(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self10, _self01, _self11, _other02, _other12, Math.fma(_other01, _self10, _other11 * _self11), Math.fma(_other00, _self00, _other10 * _self01), Math.fma(_other00, _self10, _other10 * _self11), Math.fma(_other01, _self00, _other11 * _self01));
    }

    /** Piece 2 of {@code invertProduct_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer invertProduct_apiGet_s2c11ff3b_1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float _self00, float _self10, float _self01, float _self11, float _other02, float _other12, float _t6, float _t7, float _t8, float _t9) {
        float _t10 = Math.fma(_other02, _self00, Math.fma(_other12, _self01, src.get(srcOffset + 4)));
        float _t11 = Math.fma(_other02, _self10, Math.fma(_other12, _self11, src.get(srcOffset + 5)));
        float _t15 = Math.fma(_t7, _t6, -(_t8 * _t9));
        if (!(java.lang.Math.abs(_t15) > 1.1754944E-38f && java.lang.Math.abs(_t15) < 8.507059E37f)) return Float2x3OpsKernelsTypedBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t15_inv = 1.0f / _t15;
        dest.put(destOffset, _t6 * _t15_inv);
        dest.put(destOffset + 1, -(_t8 * _t15_inv));
        dest.put(destOffset + 2, -(_t9 * _t15_inv));
        dest.put(destOffset + 3, _t7 * _t15_inv);
        dest.put(destOffset + 4, -(Math.fma(_t10, _t6, -(_t11 * _t9)) * _t15_inv));
        dest.put(destOffset + 5, -(Math.fma(_t11, _t7, -(_t10 * _t8)) * _t15_inv));
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float2x3OpsKernelsTypedBuffer.invertProduct_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.nio.FloatBuffer invertProduct_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2x3OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 6) {
            Float2x3OpsKernelsArray.invertProduct_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.invertProduct_degenerate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.invertProduct_degenerate_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _other00 = other.get(otherOffset);
        float _other10 = other.get(otherOffset + 1);
        float _other01 = other.get(otherOffset + 2);
        float _other11 = other.get(otherOffset + 3);
        float _other02 = other.get(otherOffset + 4);
        float _other12 = other.get(otherOffset + 5);
        float _t6 = Math.fma(_other01, _self10, _other11 * _self11);
        float _t7 = Math.fma(_other00, _self10, _other10 * _self11);
        float _t8 = Math.fma(_other00, _self00, _other10 * _self01);
        float _t9 = Math.fma(_other01, _self00, _other11 * _self01);
        float _t12 = unitScale(_t7, _t6, _t7);
        float _t13 = unitScale(_t8, _t9, _t8);
        float _t18 = _t6 * _t12;
        float _t19 = _t8 * _t13;
        float _t20 = _t7 * _t12;
        float _t21 = _t9 * _t13;
        float _t28_inv = 1.0f / Math.fma(_t19, _t18, -(_t20 * _t21));
        return invertProduct_degenerate_apiGet_sc368d868_1(dest, destOffset, _t18, _t19, _t20, _t21, Math.fma(_other02, _self00, Math.fma(_other12, _self01, _self02)) * _t13, Math.fma(_other02, _self10, Math.fma(_other12, _self11, _self12)) * _t12, _t28_inv, _t12 * _t28_inv, _t13 * _t28_inv);
    }

    /** Piece 2 of {@code invertProduct_degenerate_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer invertProduct_degenerate_apiGet_sc368d868_1(java.nio.FloatBuffer dest, int destOffset, float _t18, float _t19, float _t20, float _t21, float _t24, float _t25, float _t28_inv, float _sp1, float _sp0) {
        dest.put(destOffset, _t18 * _sp0);
        dest.put(destOffset + 1, -(_t20 * _sp0));
        dest.put(destOffset + 2, -(_t21 * _sp1));
        dest.put(destOffset + 3, _t19 * _sp1);
        dest.put(destOffset + 4, -(Math.fma(_t24, _t18, -(_t25 * _t21)) * _t28_inv));
        dest.put(destOffset + 5, -(Math.fma(_t25, _t19, -(_t24 * _t20)) * _t28_inv));
        return dest;
    }

    public static java.nio.FloatBuffer transpose_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer transpose_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.transpose(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.transpose_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.transpose_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer transpose_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, _self01);
        dest.put(destOffset + 2, _self02);
        dest.put(destOffset + 3, _self10);
        dest.put(destOffset + 4, _self11);
        dest.put(destOffset + 5, _self12);
        return dest;
    }

    public static java.nio.FloatBuffer add_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2x3OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer add_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 6) {
            Float2x3Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.add_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer add_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eother1 = other.get(otherOffset + _lo + 1);
            dest.put(destOffset + _lo, other.get(otherOffset + _lo) + src.get(srcOffset + _lo));
            dest.put(destOffset + _lo + 1, _eother1 + _eself1);
        }
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.mul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, scalar);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src.get(srcOffset + _lo + 1);
            dest.put(destOffset + _lo, scalar * src.get(srcOffset + _lo));
            dest.put(destOffset + _lo + 1, scalar * _eself1);
        }
        return dest;
    }

    public static java.nio.FloatBuffer negate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer negate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.negate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.negate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.negate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer negate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src.get(srcOffset + _lo + 1);
            dest.put(destOffset + _lo, -src.get(srcOffset + _lo));
            dest.put(destOffset + _lo + 1, -_eself1);
        }
        return dest;
    }

    public static java.nio.FloatBuffer sub_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2x3OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer sub_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 6) {
            Float2x3Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.sub_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sub_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eother1 = other.get(otherOffset + _lo + 1);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo) - other.get(otherOffset + _lo));
            dest.put(destOffset + _lo + 1, _eself1 - _eother1);
        }
        return dest;
    }

    public static java.nio.FloatBuffer set_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        Float2x3OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer set_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 6) {
            Float2x3Ops.set(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.set_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), vOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer set_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _ev1 = v.get(vOffset + _lo + 1);
            dest.put(destOffset + _lo, v.get(vOffset + _lo));
            dest.put(destOffset + _lo + 1, _ev1);
        }
        return dest;
    }

    public static java.nio.FloatBuffer setMat2x2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 4L;
        Float2x3OpsKernelsAddress.setMat2x2_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.FloatBuffer setMat2x2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 4) {
            Float2x3Ops.setMat2x2(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && m.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.setMat2x2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(m.duplicate().position(0)), mOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.setMat2x2_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.FloatBuffer setMat2x2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        float _m10 = m.get(mOffset + 1);
        float _m01 = m.get(mOffset + 2);
        float _m11 = m.get(mOffset + 3);
        dest.put(destOffset, m.get(mOffset));
        dest.put(destOffset + 1, _m10);
        dest.put(destOffset + 2, _m01);
        dest.put(destOffset + 3, _m11);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer setMat3x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 4L;
        Float2x3OpsKernelsAddress.setMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.FloatBuffer setMat3x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 9) {
            Float2x3Ops.setMat3x3(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && m.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.setMat3x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(m.duplicate().position(0)), mOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.setMat3x3_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.FloatBuffer setMat3x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            int _lom = _l * 3;
            float _em1 = m.get(mOffset + _lom + 1);
            dest.put(destOffset + _lo, m.get(mOffset + _lom));
            dest.put(destOffset + _lo + 1, _em1);
        }
        return dest;
    }

    public static java.nio.FloatBuffer withTranslation_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float tX, float tY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, tX, tY);
        return dest;
    }

    public static java.nio.FloatBuffer withTranslation_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float tX, float tY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.withTranslation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, tX, tY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.withTranslation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, tX, tY);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.withTranslation_apiGet(dest, destOffset, src, srcOffset, tX, tY);
        return dest;
    }

    public static java.nio.FloatBuffer withTranslation_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float tX, float tY) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self01);
        dest.put(destOffset + 3, _self11);
        dest.put(destOffset + 4, tX);
        dest.put(destOffset + 5, tY);
        return dest;
    }

    public static java.nio.FloatBuffer withTranslation_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t, int tOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _tBase = UnsafeOpsHolder.U.getLong(t, UnsafeCopy.BB_ADDRESS_OFFSET) + tOffset * 4L;
        Float2x3OpsKernelsAddress.withTranslation_unsafe(_destBase, _srcBase, _tBase);
        return dest;
    }

    public static java.nio.FloatBuffer withTranslation_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t, int tOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && t.hasArray() && tOffset >= 0 && tOffset <= t.limit() - 2) {
            Float2x3Ops.withTranslation(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, t.array(), t.arrayOffset() + tOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && t.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.withTranslation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(t.duplicate().position(0)), tOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.withTranslation_apiGet(dest, destOffset, src, srcOffset, t, tOffset);
        return dest;
    }

    public static java.nio.FloatBuffer withTranslation_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t, int tOffset) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _tx = t.get(tOffset);
        float _ty = t.get(tOffset + 1);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self01);
        dest.put(destOffset + 3, _self11);
        dest.put(destOffset + 4, _tx);
        dest.put(destOffset + 5, _ty);
        return dest;
    }

    public static java.nio.FloatBuffer to2x2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.to2x2_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer to2x2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.to2x2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.to2x2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.to2x2_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer to2x2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self01);
        dest.put(destOffset + 3, _self11);
        return dest;
    }

    public static java.nio.FloatBuffer to3x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.to3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer to3x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.to3x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.to3x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.to3x3_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer to3x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, _self01);
        dest.put(destOffset + 4, _self11);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, _self02);
        dest.put(destOffset + 7, _self12);
        dest.put(destOffset + 8, 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeIdentity_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        Float2x3OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeIdentity_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6) {
            Float2x3Ops.makeIdentity(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Float2x3OpsKernelsSegment.makeIdentity_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.makeIdentity_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeIdentity_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset, 1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 1.0f);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2x3OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 6) {
            Float2x3Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.lerp_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L, t);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eother1 = other.get(otherOffset + _lo + 1);
            dest.put(destOffset + _lo, Math.fma(t, other.get(otherOffset + _lo) - _eself0, _eself0));
            dest.put(destOffset + _lo + 1, Math.fma(t, _eother1 - _eself1, _eself1));
        }
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset * 4L;
        Float2x3OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 6) {
            Float2x3Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && right.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.mul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(right.duplicate().position(0)), rightOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _right00 = right.get(rightOffset);
        float _right10 = right.get(rightOffset + 1);
        float _right01 = right.get(rightOffset + 2);
        float _right11 = right.get(rightOffset + 3);
        float _right02 = right.get(rightOffset + 4);
        float _right12 = right.get(rightOffset + 5);
        dest.put(destOffset, Math.fma(_right00, _self00, _right10 * _self01));
        dest.put(destOffset + 1, Math.fma(_right00, _self10, _right10 * _self11));
        dest.put(destOffset + 2, Math.fma(_right01, _self00, _right11 * _self01));
        dest.put(destOffset + 3, Math.fma(_right01, _self10, _right11 * _self11));
        dest.put(destOffset + 4, Math.fma(_right02, _self00, Math.fma(_right12, _self01, _self02)));
        dest.put(destOffset + 5, Math.fma(_right02, _self10, Math.fma(_right12, _self11, _self12)));
        return dest;
    }

    public static java.nio.FloatBuffer mulMat2x2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset * 4L;
        Float2x3OpsKernelsAddress.mulMat2x2_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.FloatBuffer mulMat2x2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 4) {
            Float2x3Ops.mulMat2x2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && right.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.mulMat2x2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(right.duplicate().position(0)), rightOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.mulMat2x2_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mulMat2x2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _right00 = right.get(rightOffset);
        float _right10 = right.get(rightOffset + 1);
        float _right01 = right.get(rightOffset + 2);
        float _right11 = right.get(rightOffset + 3);
        dest.put(destOffset, Math.fma(_right00, _self00, _right10 * _self01));
        dest.put(destOffset + 1, Math.fma(_right00, _self10, _right10 * _self11));
        dest.put(destOffset + 2, Math.fma(_right01, _self00, _right11 * _self01));
        dest.put(destOffset + 3, Math.fma(_right01, _self10, _right11 * _self11));
        dest.put(destOffset + 4, _self02);
        dest.put(destOffset + 5, _self12);
        return dest;
    }

    public static java.nio.FloatBuffer mulMat3x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset * 4L;
        Float2x3OpsKernelsAddress.mulMat3x3_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.FloatBuffer mulMat3x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 9) {
            Float2x3Ops.mulMat3x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && right.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.mulMat3x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(right.duplicate().position(0)), rightOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.mulMat3x3_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mulMat3x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eright0 = right.get(rightOffset + _lo);
            float _eright1 = right.get(rightOffset + _lo + 1);
            float _eright2 = right.get(rightOffset + _lo + 2);
            dest.put(destOffset + _lo, Math.fma(_eright2, _self02, Math.fma(_eright0, _self00, _eright1 * _self01)));
            dest.put(destOffset + _lo + 1, Math.fma(_eright2, _self12, Math.fma(_eright0, _self10, _eright1 * _self11)));
            dest.put(destOffset + _lo + 2, _eright2);
        }
        return dest;
    }

    public static java.nio.FloatBuffer preMul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2x3OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 6) {
            Float2x3Ops.preMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.preMul_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.preMul_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _other00 = other.get(otherOffset);
        float _other10 = other.get(otherOffset + 1);
        float _other01 = other.get(otherOffset + 2);
        float _other11 = other.get(otherOffset + 3);
        float _other02 = other.get(otherOffset + 4);
        float _other12 = other.get(otherOffset + 5);
        dest.put(destOffset, Math.fma(_other00, _self00, _other01 * _self10));
        dest.put(destOffset + 1, Math.fma(_other10, _self00, _other11 * _self10));
        dest.put(destOffset + 2, Math.fma(_other00, _self01, _other01 * _self11));
        dest.put(destOffset + 3, Math.fma(_other10, _self01, _other11 * _self11));
        dest.put(destOffset + 4, Math.fma(_other00, _self02, Math.fma(_other01, _self12, _other02)));
        dest.put(destOffset + 5, Math.fma(_other10, _self02, Math.fma(_other11, _self12, _other12)));
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat2x2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2x3OpsKernelsAddress.preMulMat2x2_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat2x2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float2x3Ops.preMulMat2x2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.preMulMat2x2_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.preMulMat2x2_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat2x2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _other00 = other.get(otherOffset);
        float _other10 = other.get(otherOffset + 1);
        float _other01 = other.get(otherOffset + 2);
        float _other11 = other.get(otherOffset + 3);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = src.get(srcOffset + _lo);
            float _eself1 = src.get(srcOffset + _lo + 1);
            dest.put(destOffset + _lo, Math.fma(_other00, _eself0, _other01 * _eself1));
            dest.put(destOffset + _lo + 1, Math.fma(_other10, _eself0, _other11 * _eself1));
        }
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat3x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2x3OpsKernelsAddress.preMulMat3x3_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat3x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 9) {
            Float2x3Ops.preMulMat3x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.preMulMat3x3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.preMulMat3x3_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMulMat3x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _other00 = other.get(otherOffset);
        float _other10 = other.get(otherOffset + 1);
        float _other20 = other.get(otherOffset + 2);
        float _other01 = other.get(otherOffset + 3);
        float _other11 = other.get(otherOffset + 4);
        float _other21 = other.get(otherOffset + 5);
        float _other02 = other.get(otherOffset + 6);
        float _other12 = other.get(otherOffset + 7);
        float _other22 = other.get(otherOffset + 8);
        dest.put(destOffset, Math.fma(_other00, _self00, _other01 * _self10));
        dest.put(destOffset + 1, Math.fma(_other10, _self00, _other11 * _self10));
        dest.put(destOffset + 2, Math.fma(_other20, _self00, _other21 * _self10));
        dest.put(destOffset + 3, Math.fma(_other00, _self01, _other01 * _self11));
        dest.put(destOffset + 4, Math.fma(_other10, _self01, _other11 * _self11));
        dest.put(destOffset + 5, Math.fma(_other20, _self01, _other21 * _self11));
        return preMulMat3x3_apiGet_s28e5b6d1_1(dest, destOffset, _self02, _self12, _other00, _other10, _other20, _other01, _other11, _other21, _other02, _other12, _other22);
    }

    /** Piece 2 of {@code preMulMat3x3_apiGet}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer preMulMat3x3_apiGet_s28e5b6d1_1(java.nio.FloatBuffer dest, int destOffset, float _self02, float _self12, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21, float _other02, float _other12, float _other22) {
        dest.put(destOffset + 6, Math.fma(_other00, _self02, Math.fma(_other01, _self12, _other02)));
        dest.put(destOffset + 7, Math.fma(_other10, _self02, Math.fma(_other11, _self12, _other12)));
        dest.put(destOffset + 8, Math.fma(_other20, _self02, Math.fma(_other21, _self12, _other22)));
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2x3OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float weight) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 6) {
            Float2x3Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, weight);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && other.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.addScaled_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(other.duplicate().position(0)), otherOffset * 4L, weight);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float weight) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src.get(srcOffset + _lo + 1);
            float _eother1 = other.get(otherOffset + _lo + 1);
            dest.put(destOffset + _lo, Math.fma(weight, other.get(otherOffset + _lo), src.get(srcOffset + _lo)));
            dest.put(destOffset + _lo + 1, Math.fma(weight, _eother1, _eself1));
        }
        return dest;
    }

    public static java.nio.FloatBuffer makeRotation_unsafe(java.nio.FloatBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        Float2x3OpsKernelsAddress.makeRotation_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotation_api(java.nio.FloatBuffer dest, int destOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6) {
            Float2x3Ops.makeRotation(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Float2x3OpsKernelsSegment.makeRotation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, angle);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.makeRotation_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotation_apiGet(java.nio.FloatBuffer dest, int destOffset, float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset, _t1);
        dest.put(destOffset + 1, _t0);
        dest.put(destOffset + 2, -_t0);
        dest.put(destOffset + 3, _t1);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_unsafe(java.nio.FloatBuffer dest, int destOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        Float2x3OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_api(java.nio.FloatBuffer dest, int destOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6) {
            Float2x3Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Float2x3OpsKernelsSegment.makeScaling_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, vX, vY);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_apiGet(java.nio.FloatBuffer dest, int destOffset, float vX, float vY) {
        dest.put(destOffset, vX);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, vY);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        Float2x3OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float2x3Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.makeScaling_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), vOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        float _vy = v.get(vOffset + 1);
        dest.put(destOffset, v.get(vOffset));
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, _vy);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_unsafe(java.nio.FloatBuffer dest, int destOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        Float2x3OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_api(java.nio.FloatBuffer dest, int destOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6) {
            Float2x3Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, s);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Float2x3OpsKernelsSegment.makeScaling_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, s);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_apiGet(java.nio.FloatBuffer dest, int destOffset, float s) {
        dest.put(destOffset, s);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, s);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer makeTranslation_unsafe(java.nio.FloatBuffer dest, int destOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        Float2x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer makeTranslation_api(java.nio.FloatBuffer dest, int destOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6) {
            Float2x3Ops.makeTranslation(dest.array(), dest.arrayOffset() + destOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Float2x3OpsKernelsSegment.makeTranslation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, vX, vY);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.makeTranslation_apiGet(dest, destOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer makeTranslation_apiGet(java.nio.FloatBuffer dest, int destOffset, float vX, float vY) {
        dest.put(destOffset, 1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 1.0f);
        dest.put(destOffset + 4, vX);
        dest.put(destOffset + 5, vY);
        return dest;
    }

    public static java.nio.FloatBuffer makeTranslation_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        Float2x3OpsKernelsAddress.makeTranslation_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeTranslation_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float2x3Ops.makeTranslation(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.makeTranslation_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), vOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.makeTranslation_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeTranslation_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        float _vx = v.get(vOffset);
        float _vy = v.get(vOffset + 1);
        dest.put(destOffset, 1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 1.0f);
        dest.put(destOffset + 4, _vx);
        dest.put(destOffset + 5, _vy);
        return dest;
    }

    public static java.nio.FloatBuffer makeView_unsafe(java.nio.FloatBuffer dest, int destOffset, float left, float right, float bottom, float top) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        Float2x3OpsKernelsAddress.makeView_unsafe(_destBase, left, right, bottom, top);
        return dest;
    }

    public static java.nio.FloatBuffer makeView_api(java.nio.FloatBuffer dest, int destOffset, float left, float right, float bottom, float top) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6) {
            Float2x3Ops.makeView(dest.array(), dest.arrayOffset() + destOffset, left, right, bottom, top);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) {
            Float2x3OpsKernelsSegment.makeView_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, left, right, bottom, top);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.makeView_apiGet(dest, destOffset, left, right, bottom, top);
        return dest;
    }

    public static java.nio.FloatBuffer makeView_apiGet(java.nio.FloatBuffer dest, int destOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dest.put(destOffset, _t0_inv + _t0_inv);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, _t1_inv + _t1_inv);
        dest.put(destOffset + 4, -((left + right) * _t0_inv));
        dest.put(destOffset + 5, -((bottom + top) * _t1_inv));
        return dest;
    }

    public static java.nio.FloatBuffer preRotate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.preRotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.preRotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.preRotate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, angle);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.preRotate_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset, Math.fma(_self00, _t1, -(_self10 * _t0)));
        dest.put(destOffset + 1, Math.fma(_self00, _t0, _self10 * _t1));
        dest.put(destOffset + 2, Math.fma(_self01, _t1, -(_self11 * _t0)));
        dest.put(destOffset + 3, Math.fma(_self01, _t0, _self11 * _t1));
        dest.put(destOffset + 4, Math.fma(_self02, _t1, -(_self12 * _t0)));
        dest.put(destOffset + 5, Math.fma(_self02, _t0, _self12 * _t1));
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.preRotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, pivotX, pivotY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.preRotateAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, angle, pivotX, pivotY);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.preRotateAround_apiGet(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        dest.put(destOffset, Math.fma(_self00, _t2, -(_self10 * _t0)));
        dest.put(destOffset + 1, Math.fma(_self00, _t0, _self10 * _t2));
        dest.put(destOffset + 2, Math.fma(_self01, _t2, -(_self11 * _t0)));
        dest.put(destOffset + 3, Math.fma(_self01, _t0, _self11 * _t2));
        dest.put(destOffset + 4, Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(_self02, _t2, -(_self12 * _t0)));
        dest.put(destOffset + 5, Math.fma(_self02, _t0, _self12 * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0)));
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset * 4L;
        Float2x3OpsKernelsAddress.preRotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 2) {
            Float2x3Ops.preRotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.preRotateAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(pivot.duplicate().position(0)), pivotOffset * 4L, angle);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.preRotateAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotateAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _pivotx = pivot.get(pivotOffset);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        dest.put(destOffset, Math.fma(_self00, _t2, -(_self10 * _t0)));
        dest.put(destOffset + 1, Math.fma(_self00, _t0, _self10 * _t2));
        dest.put(destOffset + 2, Math.fma(_self01, _t2, -(_self11 * _t0)));
        dest.put(destOffset + 3, Math.fma(_self01, _t0, _self11 * _t2));
        dest.put(destOffset + 4, Math.fma(_pivotx, _t5, _pivoty * _t0) + Math.fma(_self02, _t2, -(_self12 * _t0)));
        dest.put(destOffset + 5, Math.fma(_self02, _t0, _self12 * _t2) + Math.fma(_pivoty, _t5, -(_pivotx * _t0)));
        return dest;
    }

    public static java.nio.FloatBuffer preScale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.preScale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, vX, vY);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src.get(srcOffset + _lo + 1);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo) * vX);
            dest.put(destOffset + _lo + 1, _eself1 * vY);
        }
        return dest;
    }

    public static java.nio.FloatBuffer preScale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        Float2x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float2x3Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.preScale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), vOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _vx = v.get(vOffset);
        float _vy = v.get(vOffset + 1);
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src.get(srcOffset + _lo + 1);
            dest.put(destOffset + _lo, src.get(srcOffset + _lo) * _vx);
            dest.put(destOffset + _lo + 1, _eself1 * _vy);
        }
        return dest;
    }

    public static java.nio.FloatBuffer preScale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.preScale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, s);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src.get(srcOffset + _lo + 1);
            dest.put(destOffset + _lo, s * src.get(srcOffset + _lo));
            dest.put(destOffset + _lo + 1, s * _eself1);
        }
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.preScaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s, pivotX, pivotY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.preScaleAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, s, pivotX, pivotY);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.preScaleAround_apiGet(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _t0 = 1.0f - s;
        dest.put(destOffset, s * src.get(srcOffset));
        dest.put(destOffset + 1, s * _self10);
        dest.put(destOffset + 2, s * _self01);
        dest.put(destOffset + 3, s * _self11);
        dest.put(destOffset + 4, Math.fma(s, _self02, pivotX * _t0));
        dest.put(destOffset + 5, Math.fma(s, _self12, pivotY * _t0));
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset * 4L;
        Float2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 2) {
            Float2x3Ops.preScaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, s);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.preScaleAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(pivot.duplicate().position(0)), pivotOffset * 4L, s);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.preScaleAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float s) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _pivotx = pivot.get(pivotOffset);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _t0 = 1.0f - s;
        dest.put(destOffset, s * src.get(srcOffset));
        dest.put(destOffset + 1, s * _self10);
        dest.put(destOffset + 2, s * _self01);
        dest.put(destOffset + 3, s * _self11);
        dest.put(destOffset + 4, Math.fma(s, _self02, _pivotx * _t0));
        dest.put(destOffset + 5, Math.fma(s, _self12, _pivoty * _t0));
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.preScaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sX, sY, pivotX, pivotY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.preScaleAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, sX, sY, pivotX, pivotY);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.preScaleAround_apiGet(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        dest.put(destOffset, sX * src.get(srcOffset));
        dest.put(destOffset + 1, sY * _self10);
        dest.put(destOffset + 2, sX * _self01);
        dest.put(destOffset + 3, sY * _self11);
        dest.put(destOffset + 4, Math.fma(pivotX, 1.0f - sX, sX * _self02));
        dest.put(destOffset + 5, Math.fma(pivotY, 1.0f - sY, sY * _self12));
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer s, int sOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + sOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset * 4L;
        Float2x3OpsKernelsAddress.preScaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer s, int sOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && s.hasArray() && sOffset >= 0 && sOffset <= s.limit() - 2 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 2) {
            Float2x3Ops.preScaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s.array(), s.arrayOffset() + sOffset, pivot.array(), pivot.arrayOffset() + pivotOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && s.order() == java.nio.ByteOrder.nativeOrder() && pivot.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.preScaleAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(s.duplicate().position(0)), sOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(pivot.duplicate().position(0)), pivotOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.preScaleAround_apiGet(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preScaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer s, int sOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _sx = s.get(sOffset);
        float _sy = s.get(sOffset + 1);
        float _pivotx = pivot.get(pivotOffset);
        float _pivoty = pivot.get(pivotOffset + 1);
        dest.put(destOffset, _sx * src.get(srcOffset));
        dest.put(destOffset + 1, _sy * _self10);
        dest.put(destOffset + 2, _sx * _self01);
        dest.put(destOffset + 3, _sy * _self11);
        dest.put(destOffset + 4, Math.fma(_pivotx, 1.0f - _sx, _sx * _self02));
        dest.put(destOffset + 5, Math.fma(_pivoty, 1.0f - _sy, _sy * _self12));
        return dest;
    }

    public static java.nio.FloatBuffer preTranslate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer preTranslate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.preTranslate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.preTranslate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, vX, vY);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.preTranslate_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer preTranslate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self01);
        dest.put(destOffset + 3, _self11);
        dest.put(destOffset + 4, _self02 + vX);
        dest.put(destOffset + 5, _self12 + vY);
        return dest;
    }

    public static java.nio.FloatBuffer preTranslate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        Float2x3OpsKernelsAddress.preTranslate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer preTranslate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float2x3Ops.preTranslate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.preTranslate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), vOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.preTranslate_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preTranslate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _vx = v.get(vOffset);
        float _vy = v.get(vOffset + 1);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self01);
        dest.put(destOffset + 3, _self11);
        dest.put(destOffset + 4, _self02 + _vx);
        dest.put(destOffset + 5, _self12 + _vy);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.rotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.rotate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, angle);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.rotate_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset, Math.fma(_self00, _t1, _self01 * _t0));
        dest.put(destOffset + 1, Math.fma(_self10, _t1, _self11 * _t0));
        dest.put(destOffset + 2, Math.fma(_self01, _t1, -(_self00 * _t0)));
        dest.put(destOffset + 3, Math.fma(_self11, _t1, -(_self10 * _t0)));
        dest.put(destOffset + 4, _self02);
        dest.put(destOffset + 5, _self12);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.rotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle, pivotX, pivotY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.rotateAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, angle, pivotX, pivotY);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.rotateAround_apiGet(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        dest.put(destOffset, Math.fma(_self00, _t2, _self01 * _t0));
        dest.put(destOffset + 1, Math.fma(_self10, _t2, _self11 * _t0));
        dest.put(destOffset + 2, Math.fma(_self01, _t2, -(_self00 * _t0)));
        dest.put(destOffset + 3, Math.fma(_self11, _t2, -(_self10 * _t0)));
        dest.put(destOffset + 4, Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02)));
        dest.put(destOffset + 5, Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12)));
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset * 4L;
        Float2x3OpsKernelsAddress.rotateAround_unsafe(_destBase, _srcBase, _pivotBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 2) {
            Float2x3Ops.rotateAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, angle);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.rotateAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(pivot.duplicate().position(0)), pivotOffset * 4L, angle);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.rotateAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotateAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _pivotx = pivot.get(pivotOffset);
        float _pivoty = pivot.get(pivotOffset + 1);
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(_pivotx, _t8, _pivoty * _t0);
        float _t10 = Math.fma(_pivoty, _t8, -(_pivotx * _t0));
        dest.put(destOffset, Math.fma(_self00, _t2, _self01 * _t0));
        dest.put(destOffset + 1, Math.fma(_self10, _t2, _self11 * _t0));
        dest.put(destOffset + 2, Math.fma(_self01, _t2, -(_self00 * _t0)));
        dest.put(destOffset + 3, Math.fma(_self11, _t2, -(_self10 * _t0)));
        dest.put(destOffset + 4, Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02)));
        dest.put(destOffset + 5, Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12)));
        return dest;
    }

    public static java.nio.FloatBuffer scale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer scale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.scale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, vX, vY);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer scale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        dest.put(destOffset, src.get(srcOffset) * vX);
        dest.put(destOffset + 1, _self10 * vX);
        dest.put(destOffset + 2, _self01 * vY);
        dest.put(destOffset + 3, _self11 * vY);
        dest.put(destOffset + 4, _self02);
        dest.put(destOffset + 5, _self12);
        return dest;
    }

    public static java.nio.FloatBuffer scale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        Float2x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer scale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float2x3Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.scale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), vOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer scale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _vx = v.get(vOffset);
        float _vy = v.get(vOffset + 1);
        dest.put(destOffset, src.get(srcOffset) * _vx);
        dest.put(destOffset + 1, _self10 * _vx);
        dest.put(destOffset + 2, _self01 * _vy);
        dest.put(destOffset + 3, _self11 * _vy);
        dest.put(destOffset + 4, _self02);
        dest.put(destOffset + 5, _self12);
        return dest;
    }

    public static java.nio.FloatBuffer scale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer scale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.scale_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, s);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer scale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        dest.put(destOffset, s * src.get(srcOffset));
        dest.put(destOffset + 1, s * _self10);
        dest.put(destOffset + 2, s * _self01);
        dest.put(destOffset + 3, s * _self11);
        dest.put(destOffset + 4, _self02);
        dest.put(destOffset + 5, _self12);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.scaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s, pivotX, pivotY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.scaleAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, s, pivotX, pivotY);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.scaleAround_apiGet(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        dest.put(destOffset, s * _self00);
        dest.put(destOffset + 1, s * _self10);
        dest.put(destOffset + 2, s * _self01);
        dest.put(destOffset + 3, s * _self11);
        dest.put(destOffset + 4, Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02)));
        dest.put(destOffset + 5, Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12)));
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset * 4L;
        Float2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _pivotBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 2) {
            Float2x3Ops.scaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, pivot.array(), pivot.arrayOffset() + pivotOffset, s);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.scaleAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(pivot.duplicate().position(0)), pivotOffset * 4L, s);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.scaleAround_apiGet(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float s) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _t0 = 1.0f - s;
        float _t1 = pivot.get(pivotOffset) * _t0;
        float _t2 = pivot.get(pivotOffset + 1) * _t0;
        dest.put(destOffset, s * _self00);
        dest.put(destOffset + 1, s * _self10);
        dest.put(destOffset + 2, s * _self01);
        dest.put(destOffset + 3, s * _self11);
        dest.put(destOffset + 4, Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02)));
        dest.put(destOffset + 5, Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12)));
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.scaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, sX, sY, pivotX, pivotY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.scaleAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, sX, sY, pivotX, pivotY);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.scaleAround_apiGet(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        dest.put(destOffset, sX * _self00);
        dest.put(destOffset + 1, sX * _self10);
        dest.put(destOffset + 2, sY * _self01);
        dest.put(destOffset + 3, sY * _self11);
        dest.put(destOffset + 4, Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02)));
        dest.put(destOffset + 5, Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12)));
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer s, int sOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _sBase = UnsafeOpsHolder.U.getLong(s, UnsafeCopy.BB_ADDRESS_OFFSET) + sOffset * 4L;
        long _pivotBase = UnsafeOpsHolder.U.getLong(pivot, UnsafeCopy.BB_ADDRESS_OFFSET) + pivotOffset * 4L;
        Float2x3OpsKernelsAddress.scaleAround_unsafe(_destBase, _srcBase, _sBase, _pivotBase);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer s, int sOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && s.hasArray() && sOffset >= 0 && sOffset <= s.limit() - 2 && pivot.hasArray() && pivotOffset >= 0 && pivotOffset <= pivot.limit() - 2) {
            Float2x3Ops.scaleAround(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s.array(), s.arrayOffset() + sOffset, pivot.array(), pivot.arrayOffset() + pivotOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && s.order() == java.nio.ByteOrder.nativeOrder() && pivot.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.scaleAround_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(s.duplicate().position(0)), sOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(pivot.duplicate().position(0)), pivotOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.scaleAround_apiGet(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return dest;
    }

    public static java.nio.FloatBuffer scaleAround_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer s, int sOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _sx = s.get(sOffset);
        float _sy = s.get(sOffset + 1);
        float _t2 = pivot.get(pivotOffset) * (1.0f - _sx);
        float _t3 = pivot.get(pivotOffset + 1) * (1.0f - _sy);
        dest.put(destOffset, _sx * _self00);
        dest.put(destOffset + 1, _sx * _self10);
        dest.put(destOffset + 2, _sy * _self01);
        dest.put(destOffset + 3, _sy * _self11);
        dest.put(destOffset + 4, Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02)));
        dest.put(destOffset + 5, Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12)));
        return dest;
    }

    public static java.nio.FloatBuffer translate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer translate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.translate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.translate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, vX, vY);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.translate_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer translate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        dest.put(destOffset, _self00);
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self01);
        dest.put(destOffset + 3, _self11);
        dest.put(destOffset + 4, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        dest.put(destOffset + 5, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        return dest;
    }

    public static java.nio.FloatBuffer translate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        Float2x3OpsKernelsAddress.translate_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer translate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float2x3Ops.translate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.translate_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), vOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.translate_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer translate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _vx = v.get(vOffset);
        float _vy = v.get(vOffset + 1);
        dest.put(destOffset, _self00);
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self01);
        dest.put(destOffset + 3, _self11);
        dest.put(destOffset + 4, Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02)));
        dest.put(destOffset + 5, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
        return dest;
    }

    public static java.nio.FloatBuffer view_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float left, float right, float bottom, float top) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.view_unsafe(_destBase, _srcBase, left, right, bottom, top);
        return dest;
    }

    public static java.nio.FloatBuffer view_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float left, float right, float bottom, float top) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.view(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, left, right, bottom, top);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.view_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, left, right, bottom, top);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.view_apiGet(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return dest;
    }

    public static java.nio.FloatBuffer view_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float left, float right, float bottom, float top) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _self02 = src.get(srcOffset + 4);
        float _self12 = src.get(srcOffset + 5);
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        dest.put(destOffset, _sp0 * _self00);
        dest.put(destOffset + 1, _sp0 * _self10);
        dest.put(destOffset + 2, _sp1 * _self01);
        dest.put(destOffset + 3, _sp1 * _self11);
        dest.put(destOffset + 4, _self02 + (-(_self00 * _sp2) - _self01 * _sp3));
        dest.put(destOffset + 5, _self12 + (-(_self10 * _sp2) - _self11 * _sp3));
        return dest;
    }

    public static java.nio.FloatBuffer mulVec3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer mulVec3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.mulVec3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY, vZ);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.mulVec3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, vX, vY, vZ);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.mulVec3_apiGet(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return dest;
    }

    public static java.nio.FloatBuffer mulVec3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        float _self10 = src.get(srcOffset + 1);
        float _self11 = src.get(srcOffset + 3);
        float _self12 = src.get(srcOffset + 5);
        dest.put(destOffset, Math.fma(src.get(srcOffset + 4), vZ, Math.fma(src.get(srcOffset), vX, src.get(srcOffset + 2) * vY)));
        dest.put(destOffset + 1, Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY)));
        return dest;
    }

    public static java.nio.FloatBuffer mulVec3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        Float2x3OpsKernelsAddress.mulVec3_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer mulVec3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 3) {
            Float2x3Ops.mulVec3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.mulVec3_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), vOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.mulVec3_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mulVec3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _self10 = src.get(srcOffset + 1);
        float _self11 = src.get(srcOffset + 3);
        float _self12 = src.get(srcOffset + 5);
        float _vx = v.get(vOffset);
        float _vy = v.get(vOffset + 1);
        float _vz = v.get(vOffset + 2);
        dest.put(destOffset, Math.fma(src.get(srcOffset + 4), _vz, Math.fma(src.get(srcOffset), _vx, src.get(srcOffset + 2) * _vy)));
        dest.put(destOffset + 1, Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy)));
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.transformDirection(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.transformDirection_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, vX, vY);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.transformDirection_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        float _self10 = src.get(srcOffset + 1);
        float _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, Math.fma(src.get(srcOffset), vX, src.get(srcOffset + 2) * vY));
        dest.put(destOffset + 1, Math.fma(_self10, vX, _self11 * vY));
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        Float2x3OpsKernelsAddress.transformDirection_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float2x3Ops.transformDirection(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.transformDirection_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), vOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.transformDirection_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer transformDirection_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _self10 = src.get(srcOffset + 1);
        float _self11 = src.get(srcOffset + 3);
        float _vx = v.get(vOffset);
        float _vy = v.get(vOffset + 1);
        dest.put(destOffset, Math.fma(src.get(srcOffset), _vx, src.get(srcOffset + 2) * _vy));
        dest.put(destOffset + 1, Math.fma(_self10, _vx, _self11 * _vy));
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            Float2x3Ops.transformPosition(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.transformPosition_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, vX, vY);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.transformPosition_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        float _self10 = src.get(srcOffset + 1);
        float _self11 = src.get(srcOffset + 3);
        float _self12 = src.get(srcOffset + 5);
        dest.put(destOffset, Math.fma(src.get(srcOffset), vX, Math.fma(src.get(srcOffset + 2), vY, src.get(srcOffset + 4))));
        dest.put(destOffset + 1, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        Float2x3OpsKernelsAddress.transformPosition_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 6 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float2x3Ops.transformPosition(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        if (dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder() && v.order() == java.nio.ByteOrder.nativeOrder()) {
            Float2x3OpsKernelsSegment.transformPosition_api(java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0)), destOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0)), srcOffset * 4L, java.lang.foreign.MemorySegment.ofBuffer(v.duplicate().position(0)), vOffset * 4L);
            return dest;
        }
        Float2x3OpsKernelsTypedBuffer.transformPosition_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer transformPosition_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _self10 = src.get(srcOffset + 1);
        float _self11 = src.get(srcOffset + 3);
        float _self12 = src.get(srcOffset + 5);
        float _vx = v.get(vOffset);
        float _vy = v.get(vOffset + 1);
        dest.put(destOffset, Math.fma(src.get(srcOffset), _vx, Math.fma(src.get(srcOffset + 2), _vy, src.get(srcOffset + 4))));
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
}
