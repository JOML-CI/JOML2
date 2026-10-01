// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Scalar/Unsafe kernel leaves of {@link Float2x2Ops} whose leading storage
 * parameter is a typed {@link java.nio.FloatBuffer}. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code Float2x2Ops} and its sibling kernel units. Not public API.
 */
public final class Float2x2OpsKernelsTypedBuffer {
    private Float2x2OpsKernelsTypedBuffer() {}

    public static java.nio.FloatBuffer getColumn_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int col) {
        Float2x2OpsKernelsAddress.getColumn_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, col);
        return dest;
    }

    public static java.nio.FloatBuffer getColumn_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int col) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float2x2Ops.getColumn(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, col);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.getColumn_apiGet(dest, destOffset, src, srcOffset, col);
        return dest;
    }

    public static java.nio.FloatBuffer getColumn_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int col) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _idxSw0;
        float _idxSw1;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest.put(destOffset, _idxSw0);
        dest.put(destOffset + 1, _idxSw1);
        return dest;
    }

    public static float getRotationAngle_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        return Float2x2OpsKernelsAddress.getRotationAngle_unsafe(UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L);
    }

    public static float getRotationAngle_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float2x2Ops.getRotationAngle(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float2x2OpsKernelsTypedBuffer.getRotationAngle_apiGet(src, srcOffset);
    }

    public static float getRotationAngle_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        return Math.atan2(src.get(srcOffset + 1), src.get(srcOffset));
    }

    public static java.nio.FloatBuffer getRow_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int row) {
        Float2x2OpsKernelsAddress.getRow_unsafe(UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, row);
        return dest;
    }

    public static java.nio.FloatBuffer getRow_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int row) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float2x2Ops.getRow(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, row);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.getRow_apiGet(dest, destOffset, src, srcOffset, row);
        return dest;
    }

    public static java.nio.FloatBuffer getRow_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int row) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _idxSw0;
        float _idxSw1;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest.put(destOffset, _idxSw0);
        dest.put(destOffset + 1, _idxSw1);
        return dest;
    }

    public static java.nio.FloatBuffer cofactor_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x2OpsKernelsAddress.cofactor_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer cofactor_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float2x2Ops.cofactor(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.cofactor_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer cofactor_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        dest.put(destOffset, src.get(srcOffset + 3));
        dest.put(destOffset + 1, -_self01);
        dest.put(destOffset + 2, -_self10);
        dest.put(destOffset + 3, _self00);
        return dest;
    }

    public static float determinant_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2x2OpsKernelsAddress.determinant_unsafe(_srcBase);
    }

    public static float determinant_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float2x2Ops.determinant(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float2x2OpsKernelsTypedBuffer.determinant_apiGet(src, srcOffset);
    }

    public static float determinant_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        return Math.fma(src.get(srcOffset), src.get(srcOffset + 3), -(src.get(srcOffset + 2) * src.get(srcOffset + 1)));
    }

    public static float frobeniusNorm_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2x2OpsKernelsAddress.frobeniusNorm_unsafe(_srcBase);
    }

    public static float frobeniusNorm_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float2x2Ops.frobeniusNorm(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float2x2OpsKernelsTypedBuffer.frobeniusNorm_apiGet(src, srcOffset);
    }

    public static float frobeniusNorm_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        return (float) java.lang.Math.sqrt(Math.fma(_self11, _self11, Math.fma(_self10, _self10, Math.fma(_self00, _self00, _self01 * _self01))));
    }

    public static java.nio.FloatBuffer invert_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x2OpsKernelsAddress.invert_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invert_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float2x2Ops.invert(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.invert_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invert_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _t3 = Math.fma(_self00, _self11, -(_self01 * _self10));
        if (!(java.lang.Math.abs(_t3) > 1.1754944E-38f && java.lang.Math.abs(_t3) < 8.507059E37f)) return Float2x2OpsKernelsTypedBuffer.invert_degenerate(dest, destOffset, src, srcOffset);
        float _t3_inv = 1.0f / _t3;
        dest.put(destOffset, _self11 * _t3_inv);
        dest.put(destOffset + 1, -(_self10 * _t3_inv));
        dest.put(destOffset + 2, -(_self01 * _t3_inv));
        dest.put(destOffset + 3, _self00 * _t3_inv);
        return dest;
    }

    public static java.nio.FloatBuffer invert_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x2OpsKernelsTypedBuffer.invert_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float2x2OpsKernelsTypedBuffer.invert_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.FloatBuffer invert_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x2OpsKernelsAddress.invert_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer invert_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float2x2OpsKernelsArray.invert_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.invert_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invert_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _t0 = unitScale(_self10, _self11, _self10);
        float _t1 = unitScale(_self00, _self01, _self00);
        float _t6 = _self11 * _t0;
        float _t7 = _self00 * _t1;
        float _t8 = _self01 * _t1;
        float _t9 = _self10 * _t0;
        float _t12_inv = 1.0f / Math.fma(_t7, _t6, -(_t8 * _t9));
        float _sp1 = _t0 * _t12_inv;
        float _sp0 = _t1 * _t12_inv;
        dest.put(destOffset, _t6 * _sp0);
        dest.put(destOffset + 1, -(_t9 * _sp0));
        dest.put(destOffset + 2, -(_t8 * _sp1));
        dest.put(destOffset + 3, _t7 * _sp1);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2x2OpsKernelsAddress.invertProduct_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float2x2Ops.invertProduct(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.invertProduct_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
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
        float _t4 = Math.fma(_other01, _self10, _other11 * _self11);
        float _t5 = Math.fma(_other00, _self00, _other10 * _self01);
        float _t6 = Math.fma(_other00, _self10, _other10 * _self11);
        float _t7 = Math.fma(_other01, _self00, _other11 * _self01);
        float _t11 = Math.fma(_t5, _t4, -(_t6 * _t7));
        if (!(java.lang.Math.abs(_t11) > 1.1754944E-38f && java.lang.Math.abs(_t11) < 8.507059E37f)) return Float2x2OpsKernelsTypedBuffer.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t11_inv = 1.0f / _t11;
        dest.put(destOffset, _t4 * _t11_inv);
        dest.put(destOffset + 1, -(_t6 * _t11_inv));
        dest.put(destOffset + 2, -(_t7 * _t11_inv));
        dest.put(destOffset + 3, _t5 * _t11_inv);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x2OpsKernelsTypedBuffer.invertProduct_degenerate_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float2x2OpsKernelsTypedBuffer.invertProduct_degenerate_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.nio.FloatBuffer invertProduct_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2x2OpsKernelsAddress.invertProduct_degenerate_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float2x2OpsKernelsArray.invertProduct_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.invertProduct_degenerate_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer invertProduct_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _other00 = other.get(otherOffset);
        float _other10 = other.get(otherOffset + 1);
        float _other01 = other.get(otherOffset + 2);
        float _other11 = other.get(otherOffset + 3);
        float _t4 = Math.fma(_other01, _self10, _other11 * _self11);
        float _t5 = Math.fma(_other00, _self10, _other10 * _self11);
        float _t6 = Math.fma(_other00, _self00, _other10 * _self01);
        float _t7 = Math.fma(_other01, _self00, _other11 * _self01);
        float _t8 = unitScale(_t5, _t4, _t5);
        float _t9 = unitScale(_t6, _t7, _t6);
        float _t14 = _t4 * _t8;
        float _t15 = _t6 * _t9;
        float _t16 = _t5 * _t8;
        float _t17 = _t7 * _t9;
        float _t20_inv = 1.0f / Math.fma(_t15, _t14, -(_t16 * _t17));
        float _sp1 = _t8 * _t20_inv;
        float _sp0 = _t9 * _t20_inv;
        dest.put(destOffset, _t14 * _sp0);
        dest.put(destOffset + 1, -(_t16 * _sp0));
        dest.put(destOffset + 2, -(_t17 * _sp1));
        dest.put(destOffset + 3, _t15 * _sp1);
        return dest;
    }

    public static java.nio.FloatBuffer normal_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x2OpsKernelsAddress.normal_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normal_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float2x2Ops.normal(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.normal_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normal_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _t3 = Math.fma(_self00, _self11, -(_self01 * _self10));
        if (!(java.lang.Math.abs(_t3) > 1.1754944E-38f && java.lang.Math.abs(_t3) < 8.507059E37f)) return Float2x2OpsKernelsTypedBuffer.normal_degenerate(dest, destOffset, src, srcOffset);
        float _t3_inv = 1.0f / _t3;
        dest.put(destOffset, _self11 * _t3_inv);
        dest.put(destOffset + 1, -(_self01 * _t3_inv));
        dest.put(destOffset + 2, -(_self10 * _t3_inv));
        dest.put(destOffset + 3, _self00 * _t3_inv);
        return dest;
    }

    public static java.nio.FloatBuffer normal_degenerate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x2OpsKernelsTypedBuffer.normal_degenerate_unsafe(dest, destOffset, src, srcOffset);
        return Float2x2OpsKernelsTypedBuffer.normal_degenerate_api(dest, destOffset, src, srcOffset);
    }

    public static java.nio.FloatBuffer normal_degenerate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x2OpsKernelsAddress.normal_degenerate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer normal_degenerate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float2x2OpsKernelsArray.normal_degenerate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.normal_degenerate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer normal_degenerate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _t0 = unitScale(_self10, _self11, _self10);
        float _t1 = unitScale(_self00, _self01, _self00);
        float _t6 = _self11 * _t0;
        float _t7 = _self00 * _t1;
        float _t8 = _self01 * _t1;
        float _t9 = _self10 * _t0;
        float _t12_inv = 1.0f / Math.fma(_t7, _t6, -(_t8 * _t9));
        float _sp1 = _t0 * _t12_inv;
        float _sp0 = _t1 * _t12_inv;
        dest.put(destOffset, _t6 * _sp0);
        dest.put(destOffset + 1, -(_t8 * _sp1));
        dest.put(destOffset + 2, -(_t9 * _sp0));
        dest.put(destOffset + 3, _t7 * _sp1);
        return dest;
    }

    public static float trace_unsafe(java.nio.FloatBuffer src, int srcOffset) {
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        return Float2x2OpsKernelsAddress.trace_unsafe(_srcBase);
    }

    public static float trace_api(java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            return Float2x2Ops.trace(src.array(), src.arrayOffset() + srcOffset);
        }
        return Float2x2OpsKernelsTypedBuffer.trace_apiGet(src, srcOffset);
    }

    public static float trace_apiGet(java.nio.FloatBuffer src, int srcOffset) {
        return src.get(srcOffset) + src.get(srcOffset + 3);
    }

    public static java.nio.FloatBuffer transpose_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x2OpsKernelsAddress.transpose_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer transpose_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float2x2Ops.transpose(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.transpose_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer transpose_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, _self01);
        dest.put(destOffset + 2, _self10);
        dest.put(destOffset + 3, _self11);
        return dest;
    }

    public static java.nio.FloatBuffer add_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2x2OpsKernelsAddress.add_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer add_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float2x2Ops.add(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.add_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer add_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _other10 = other.get(otherOffset + 1);
        float _other01 = other.get(otherOffset + 2);
        float _other11 = other.get(otherOffset + 3);
        dest.put(destOffset, other.get(otherOffset) + src.get(srcOffset));
        dest.put(destOffset + 1, _other10 + _self10);
        dest.put(destOffset + 2, _other01 + _self01);
        dest.put(destOffset + 3, _other11 + _self11);
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float2x2Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, scalar);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, scalar);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, scalar * src.get(srcOffset));
        dest.put(destOffset + 1, scalar * _self10);
        dest.put(destOffset + 2, scalar * _self01);
        dest.put(destOffset + 3, scalar * _self11);
        return dest;
    }

    public static java.nio.FloatBuffer negate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x2OpsKernelsAddress.negate_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer negate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float2x2Ops.negate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.negate_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer negate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, -src.get(srcOffset));
        dest.put(destOffset + 1, -_self10);
        dest.put(destOffset + 2, -_self01);
        dest.put(destOffset + 3, -_self11);
        return dest;
    }

    public static java.nio.FloatBuffer sub_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2x2OpsKernelsAddress.sub_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer sub_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float2x2Ops.sub(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.sub_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer sub_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _other10 = other.get(otherOffset + 1);
        float _other01 = other.get(otherOffset + 2);
        float _other11 = other.get(otherOffset + 3);
        dest.put(destOffset, src.get(srcOffset) - other.get(otherOffset));
        dest.put(destOffset + 1, _self10 - _other10);
        dest.put(destOffset + 2, _self01 - _other01);
        dest.put(destOffset + 3, _self11 - _other11);
        return dest;
    }

    public static java.nio.FloatBuffer set_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        Float2x2OpsKernelsAddress.set_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer set_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 4) {
            Float2x2Ops.set(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.set_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer set_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        float _v10 = v.get(vOffset + 1);
        float _v01 = v.get(vOffset + 2);
        float _v11 = v.get(vOffset + 3);
        dest.put(destOffset, v.get(vOffset));
        dest.put(destOffset + 1, _v10);
        dest.put(destOffset + 2, _v01);
        dest.put(destOffset + 3, _v11);
        return dest;
    }

    public static java.nio.FloatBuffer setMat2x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 4L;
        Float2x2OpsKernelsAddress.setMat2x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.FloatBuffer setMat2x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 6) {
            Float2x2Ops.setMat2x3(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.setMat2x3_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.FloatBuffer setMat2x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        float _m10 = m.get(mOffset + 1);
        float _m01 = m.get(mOffset + 2);
        float _m11 = m.get(mOffset + 3);
        dest.put(destOffset, m.get(mOffset));
        dest.put(destOffset + 1, _m10);
        dest.put(destOffset + 2, _m01);
        dest.put(destOffset + 3, _m11);
        return dest;
    }

    public static java.nio.FloatBuffer setMat3x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _mBase = UnsafeOpsHolder.U.getLong(m, UnsafeCopy.BB_ADDRESS_OFFSET) + mOffset * 4L;
        Float2x2OpsKernelsAddress.setMat3x3_unsafe(_destBase, _mBase);
        return dest;
    }

    public static java.nio.FloatBuffer setMat3x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && m.hasArray() && mOffset >= 0 && mOffset <= m.limit() - 9) {
            Float2x2Ops.setMat3x3(dest.array(), dest.arrayOffset() + destOffset, m.array(), m.arrayOffset() + mOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.setMat3x3_apiGet(dest, destOffset, m, mOffset);
        return dest;
    }

    public static java.nio.FloatBuffer setMat3x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        float _m10 = m.get(mOffset + 1);
        float _m01 = m.get(mOffset + 3);
        float _m11 = m.get(mOffset + 4);
        dest.put(destOffset, m.get(mOffset));
        dest.put(destOffset + 1, _m10);
        dest.put(destOffset + 2, _m01);
        dest.put(destOffset + 3, _m11);
        return dest;
    }

    public static java.nio.FloatBuffer to2x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x2OpsKernelsAddress.to2x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer to2x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 6 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float2x2Ops.to2x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.to2x3_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer to2x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, _self01);
        dest.put(destOffset + 3, _self11);
        dest.put(destOffset + 4, 0.0f);
        dest.put(destOffset + 5, 0.0f);
        return dest;
    }

    public static java.nio.FloatBuffer to3x3_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x2OpsKernelsAddress.to3x3_unsafe(_destBase, _srcBase);
        return dest;
    }

    public static java.nio.FloatBuffer to3x3_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float2x2Ops.to3x3(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.to3x3_apiGet(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.FloatBuffer to3x3_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset));
        dest.put(destOffset + 1, _self10);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, _self01);
        dest.put(destOffset + 4, _self11);
        dest.put(destOffset + 5, 0.0f);
        dest.put(destOffset + 6, 0.0f);
        dest.put(destOffset + 7, 0.0f);
        dest.put(destOffset + 8, 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer decomposeLDU_unsafe(java.nio.FloatBuffer lower, int lowerOffset, java.nio.FloatBuffer diagonal, int diagonalOffset, java.nio.FloatBuffer upper, int upperOffset, java.nio.FloatBuffer src, int srcOffset) {
        long _lowerBase = UnsafeOpsHolder.U.getLong(lower, UnsafeCopy.BB_ADDRESS_OFFSET) + lowerOffset * 4L;
        long _diagonalBase = UnsafeOpsHolder.U.getLong(diagonal, UnsafeCopy.BB_ADDRESS_OFFSET) + diagonalOffset * 4L;
        long _upperBase = UnsafeOpsHolder.U.getLong(upper, UnsafeCopy.BB_ADDRESS_OFFSET) + upperOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x2OpsKernelsAddress.decomposeLDU_unsafe(_lowerBase, _diagonalBase, _upperBase, _srcBase);
        return lower;
    }

    public static java.nio.FloatBuffer decomposeLDU_api(java.nio.FloatBuffer lower, int lowerOffset, java.nio.FloatBuffer diagonal, int diagonalOffset, java.nio.FloatBuffer upper, int upperOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (lower.hasArray() && lowerOffset >= 0 && lowerOffset <= lower.limit() - 4 && diagonal.hasArray() && diagonalOffset >= 0 && diagonalOffset <= diagonal.limit() - 4 && upper.hasArray() && upperOffset >= 0 && upperOffset <= upper.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float2x2Ops.decomposeLDU(lower.array(), lower.arrayOffset() + lowerOffset, diagonal.array(), diagonal.arrayOffset() + diagonalOffset, upper.array(), upper.arrayOffset() + upperOffset, src.array(), src.arrayOffset() + srcOffset);
            return lower;
        }
        Float2x2OpsKernelsTypedBuffer.decomposeLDU_apiGet(lower, lowerOffset, diagonal, diagonalOffset, upper, upperOffset, src, srcOffset);
        return lower;
    }

    public static java.nio.FloatBuffer decomposeLDU_apiGet(java.nio.FloatBuffer lower, int lowerOffset, java.nio.FloatBuffer diagonal, int diagonalOffset, java.nio.FloatBuffer upper, int upperOffset, java.nio.FloatBuffer src, int srcOffset) {
        float _self00 = src.get(srcOffset);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _rcp0 = 1.0f / _self00;
        float _sp0 = src.get(srcOffset + 1) * _rcp0;
        lower.put(lowerOffset, 1.0f);
        lower.put(lowerOffset + 1, _sp0);
        lower.put(lowerOffset + 2, 0.0f);
        lower.put(lowerOffset + 3, 1.0f);
        diagonal.put(diagonalOffset, _self00);
        diagonal.put(diagonalOffset + 1, 0.0f);
        diagonal.put(diagonalOffset + 2, 0.0f);
        diagonal.put(diagonalOffset + 3, _self11 - _self01 * _sp0);
        upper.put(upperOffset, 1.0f);
        upper.put(upperOffset + 1, 0.0f);
        upper.put(upperOffset + 2, _self01 * _rcp0);
        upper.put(upperOffset + 3, 1.0f);
        return lower;
    }

    public static java.nio.FloatBuffer makeIdentity_unsafe(java.nio.FloatBuffer dest, int destOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        Float2x2OpsKernelsAddress.makeIdentity_unsafe(_destBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeIdentity_api(java.nio.FloatBuffer dest, int destOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Float2x2Ops.makeIdentity(dest.array(), dest.arrayOffset() + destOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.makeIdentity_apiGet(dest, destOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeIdentity_apiGet(java.nio.FloatBuffer dest, int destOffset) {
        dest.put(destOffset, 1.0f);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, 1.0f);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2x2OpsKernelsAddress.lerp_unsafe(_destBase, _srcBase, _otherBase, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float2x2Ops.lerp(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, t);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.lerp_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return dest;
    }

    public static java.nio.FloatBuffer lerp_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _other10 = other.get(otherOffset + 1);
        float _other01 = other.get(otherOffset + 2);
        float _other11 = other.get(otherOffset + 3);
        dest.put(destOffset, Math.fma(t, other.get(otherOffset) - _self00, _self00));
        dest.put(destOffset + 1, Math.fma(t, _other10 - _self10, _self10));
        dest.put(destOffset + 2, Math.fma(t, _other01 - _self01, _self01));
        dest.put(destOffset + 3, Math.fma(t, _other11 - _self11, _self11));
        return dest;
    }

    public static java.nio.FloatBuffer mul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _rightBase = UnsafeOpsHolder.U.getLong(right, UnsafeCopy.BB_ADDRESS_OFFSET) + rightOffset * 4L;
        Float2x2OpsKernelsAddress.mul_unsafe(_destBase, _srcBase, _rightBase);
        return dest;
    }

    public static java.nio.FloatBuffer mul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && right.hasArray() && rightOffset >= 0 && rightOffset <= right.limit() - 4) {
            Float2x2Ops.mul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, right.array(), right.arrayOffset() + rightOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.mul_apiGet(dest, destOffset, src, srcOffset, right, rightOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _right00 = right.get(rightOffset);
        float _right10 = right.get(rightOffset + 1);
        float _right01 = right.get(rightOffset + 2);
        float _right11 = right.get(rightOffset + 3);
        dest.put(destOffset, Math.fma(_right00, _self00, _right10 * _self01));
        dest.put(destOffset + 1, Math.fma(_right00, _self10, _right10 * _self11));
        dest.put(destOffset + 2, Math.fma(_right01, _self00, _right11 * _self01));
        dest.put(destOffset + 3, Math.fma(_right01, _self10, _right11 * _self11));
        return dest;
    }

    public static java.nio.FloatBuffer preMul_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2x2OpsKernelsAddress.preMul_unsafe(_destBase, _srcBase, _otherBase);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float2x2Ops.preMul(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.preMul_apiGet(dest, destOffset, src, srcOffset, other, otherOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preMul_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _other00 = other.get(otherOffset);
        float _other10 = other.get(otherOffset + 1);
        float _other01 = other.get(otherOffset + 2);
        float _other11 = other.get(otherOffset + 3);
        dest.put(destOffset, Math.fma(_other00, _self00, _other01 * _self10));
        dest.put(destOffset + 1, Math.fma(_other10, _self00, _other11 * _self10));
        dest.put(destOffset + 2, Math.fma(_other00, _self01, _other01 * _self11));
        dest.put(destOffset + 3, Math.fma(_other10, _self01, _other11 * _self11));
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float weight) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _otherBase = UnsafeOpsHolder.U.getLong(other, UnsafeCopy.BB_ADDRESS_OFFSET) + otherOffset * 4L;
        Float2x2OpsKernelsAddress.addScaled_unsafe(_destBase, _srcBase, _otherBase, weight);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float weight) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && other.hasArray() && otherOffset >= 0 && otherOffset <= other.limit() - 4) {
            Float2x2Ops.addScaled(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, other.array(), other.arrayOffset() + otherOffset, weight);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.addScaled_apiGet(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return dest;
    }

    public static java.nio.FloatBuffer addScaled_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float weight) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _other10 = other.get(otherOffset + 1);
        float _other01 = other.get(otherOffset + 2);
        float _other11 = other.get(otherOffset + 3);
        dest.put(destOffset, Math.fma(weight, other.get(otherOffset), src.get(srcOffset)));
        dest.put(destOffset + 1, Math.fma(weight, _other10, _self10));
        dest.put(destOffset + 2, Math.fma(weight, _other01, _self01));
        dest.put(destOffset + 3, Math.fma(weight, _other11, _self11));
        return dest;
    }

    public static java.nio.FloatBuffer makeOuterProduct_unsafe(java.nio.FloatBuffer dest, int destOffset, float colX, float colY, float rowX, float rowY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        Float2x2OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, colX, colY, rowX, rowY);
        return dest;
    }

    public static java.nio.FloatBuffer makeOuterProduct_api(java.nio.FloatBuffer dest, int destOffset, float colX, float colY, float rowX, float rowY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Float2x2Ops.makeOuterProduct(dest.array(), dest.arrayOffset() + destOffset, colX, colY, rowX, rowY);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.makeOuterProduct_apiGet(dest, destOffset, colX, colY, rowX, rowY);
        return dest;
    }

    public static java.nio.FloatBuffer makeOuterProduct_apiGet(java.nio.FloatBuffer dest, int destOffset, float colX, float colY, float rowX, float rowY) {
        dest.put(destOffset, colX * rowX);
        dest.put(destOffset + 1, colY * rowX);
        dest.put(destOffset + 2, colX * rowY);
        dest.put(destOffset + 3, colY * rowY);
        return dest;
    }

    public static java.nio.FloatBuffer makeOuterProduct_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer col, int colOffset, java.nio.FloatBuffer row, int rowOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _colBase = UnsafeOpsHolder.U.getLong(col, UnsafeCopy.BB_ADDRESS_OFFSET) + colOffset * 4L;
        long _rowBase = UnsafeOpsHolder.U.getLong(row, UnsafeCopy.BB_ADDRESS_OFFSET) + rowOffset * 4L;
        Float2x2OpsKernelsAddress.makeOuterProduct_unsafe(_destBase, _colBase, _rowBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeOuterProduct_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer col, int colOffset, java.nio.FloatBuffer row, int rowOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && col.hasArray() && colOffset >= 0 && colOffset <= col.limit() - 2 && row.hasArray() && rowOffset >= 0 && rowOffset <= row.limit() - 2) {
            Float2x2Ops.makeOuterProduct(dest.array(), dest.arrayOffset() + destOffset, col.array(), col.arrayOffset() + colOffset, row.array(), row.arrayOffset() + rowOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.makeOuterProduct_apiGet(dest, destOffset, col, colOffset, row, rowOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeOuterProduct_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer col, int colOffset, java.nio.FloatBuffer row, int rowOffset) {
        float _colx = col.get(colOffset);
        float _coly = col.get(colOffset + 1);
        float _rowx = row.get(rowOffset);
        float _rowy = row.get(rowOffset + 1);
        dest.put(destOffset, _colx * _rowx);
        dest.put(destOffset + 1, _coly * _rowx);
        dest.put(destOffset + 2, _colx * _rowy);
        dest.put(destOffset + 3, _coly * _rowy);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotation_unsafe(java.nio.FloatBuffer dest, int destOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        Float2x2OpsKernelsAddress.makeRotation_unsafe(_destBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotation_api(java.nio.FloatBuffer dest, int destOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Float2x2Ops.makeRotation(dest.array(), dest.arrayOffset() + destOffset, angle);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.makeRotation_apiGet(dest, destOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer makeRotation_apiGet(java.nio.FloatBuffer dest, int destOffset, float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset, _t1);
        dest.put(destOffset + 1, _t0);
        dest.put(destOffset + 2, -_t0);
        dest.put(destOffset + 3, _t1);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_unsafe(java.nio.FloatBuffer dest, int destOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        Float2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_api(java.nio.FloatBuffer dest, int destOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Float2x2Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, vX, vY);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_apiGet(java.nio.FloatBuffer dest, int destOffset, float vX, float vY) {
        dest.put(destOffset, vX);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, vY);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        Float2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float2x2Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        float _vy = v.get(vOffset + 1);
        dest.put(destOffset, v.get(vOffset));
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, _vy);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_unsafe(java.nio.FloatBuffer dest, int destOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        Float2x2OpsKernelsAddress.makeScaling_unsafe(_destBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_api(java.nio.FloatBuffer dest, int destOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            Float2x2Ops.makeScaling(dest.array(), dest.arrayOffset() + destOffset, s);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.makeScaling_apiGet(dest, destOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer makeScaling_apiGet(java.nio.FloatBuffer dest, int destOffset, float s) {
        dest.put(destOffset, s);
        dest.put(destOffset + 1, 0.0f);
        dest.put(destOffset + 2, 0.0f);
        dest.put(destOffset + 3, s);
        return dest;
    }

    public static java.nio.FloatBuffer preRotate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x2OpsKernelsAddress.preRotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float2x2Ops.preRotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.preRotate_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer preRotate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset, Math.fma(_self00, _t1, -(_self10 * _t0)));
        dest.put(destOffset + 1, Math.fma(_self00, _t0, _self10 * _t1));
        dest.put(destOffset + 2, Math.fma(_self01, _t1, -(_self11 * _t0)));
        dest.put(destOffset + 3, Math.fma(_self01, _t0, _self11 * _t1));
        return dest;
    }

    public static java.nio.FloatBuffer preScale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float2x2Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset) * vX);
        dest.put(destOffset + 1, _self10 * vY);
        dest.put(destOffset + 2, _self01 * vX);
        dest.put(destOffset + 3, _self11 * vY);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        Float2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float2x2Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _vx = v.get(vOffset);
        float _vy = v.get(vOffset + 1);
        dest.put(destOffset, src.get(srcOffset) * _vx);
        dest.put(destOffset + 1, _self10 * _vy);
        dest.put(destOffset + 2, _self01 * _vx);
        dest.put(destOffset + 3, _self11 * _vy);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x2OpsKernelsAddress.preScale_unsafe(_destBase, _srcBase, s);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float2x2Ops.preScale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, s);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.preScale_apiGet(dest, destOffset, src, srcOffset, s);
        return dest;
    }

    public static java.nio.FloatBuffer preScale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, s * src.get(srcOffset));
        dest.put(destOffset + 1, s * _self10);
        dest.put(destOffset + 2, s * _self01);
        dest.put(destOffset + 3, s * _self11);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x2OpsKernelsAddress.rotate_unsafe(_destBase, _srcBase, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float2x2Ops.rotate(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, angle);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.rotate_apiGet(dest, destOffset, src, srcOffset, angle);
        return dest;
    }

    public static java.nio.FloatBuffer rotate_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        float _self00 = src.get(srcOffset);
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest.put(destOffset, Math.fma(_self00, _t1, _self01 * _t0));
        dest.put(destOffset + 1, Math.fma(_self10, _t1, _self11 * _t0));
        dest.put(destOffset + 2, Math.fma(_self01, _t1, -(_self00 * _t0)));
        dest.put(destOffset + 3, Math.fma(_self11, _t1, -(_self10 * _t0)));
        return dest;
    }

    public static java.nio.FloatBuffer scale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x2OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer scale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float2x2Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer scale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, src.get(srcOffset) * vX);
        dest.put(destOffset + 1, _self10 * vX);
        dest.put(destOffset + 2, _self01 * vY);
        dest.put(destOffset + 3, _self11 * vY);
        return dest;
    }

    public static java.nio.FloatBuffer scale_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        Float2x2OpsKernelsAddress.scale_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer scale_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float2x2Ops.scale(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.scale_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer scale_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _self10 = src.get(srcOffset + 1);
        float _self01 = src.get(srcOffset + 2);
        float _self11 = src.get(srcOffset + 3);
        float _vx = v.get(vOffset);
        float _vy = v.get(vOffset + 1);
        dest.put(destOffset, src.get(srcOffset) * _vx);
        dest.put(destOffset + 1, _self10 * _vx);
        dest.put(destOffset + 2, _self01 * _vy);
        dest.put(destOffset + 3, _self11 * _vy);
        return dest;
    }

    public static java.nio.FloatBuffer mulVec2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        Float2x2OpsKernelsAddress.mulVec2_unsafe(_destBase, _srcBase, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer mulVec2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            Float2x2Ops.mulVec2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, vX, vY);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.mulVec2_apiGet(dest, destOffset, src, srcOffset, vX, vY);
        return dest;
    }

    public static java.nio.FloatBuffer mulVec2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        float _self10 = src.get(srcOffset + 1);
        float _self11 = src.get(srcOffset + 3);
        dest.put(destOffset, Math.fma(src.get(srcOffset), vX, src.get(srcOffset + 2) * vY));
        dest.put(destOffset + 1, Math.fma(_self10, vX, _self11 * vY));
        return dest;
    }

    public static java.nio.FloatBuffer mulVec2_unsafe(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        long _destBase = UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L;
        long _srcBase = UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L;
        long _vBase = UnsafeOpsHolder.U.getLong(v, UnsafeCopy.BB_ADDRESS_OFFSET) + vOffset * 4L;
        Float2x2OpsKernelsAddress.mulVec2_unsafe(_destBase, _srcBase, _vBase);
        return dest;
    }

    public static java.nio.FloatBuffer mulVec2_api(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 2 && src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4 && v.hasArray() && vOffset >= 0 && vOffset <= v.limit() - 2) {
            Float2x2Ops.mulVec2(dest.array(), dest.arrayOffset() + destOffset, src.array(), src.arrayOffset() + srcOffset, v.array(), v.arrayOffset() + vOffset);
            return dest;
        }
        Float2x2OpsKernelsTypedBuffer.mulVec2_apiGet(dest, destOffset, src, srcOffset, v, vOffset);
        return dest;
    }

    public static java.nio.FloatBuffer mulVec2_apiGet(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        float _self10 = src.get(srcOffset + 1);
        float _self11 = src.get(srcOffset + 3);
        float _vx = v.get(vOffset);
        float _vy = v.get(vOffset + 1);
        dest.put(destOffset, Math.fma(src.get(srcOffset), _vx, src.get(srcOffset + 2) * _vy));
        dest.put(destOffset + 1, Math.fma(_self10, _vx, _self11 * _vy));
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
