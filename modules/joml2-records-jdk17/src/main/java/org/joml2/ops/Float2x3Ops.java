// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.ops;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.kernels.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Static, allocation-free operations on raw storage holding a {@link Float2x3}.
 *
 * <p>Each method takes one or more buffers ({@code float[]},
 * {@link java.nio.FloatBuffer}, or {@link java.nio.ByteBuffer})
 * plus an element/byte offset and operates
 * directly on that storage. No {@link Float2x3} instance is allocated.</p>
 *
 * <p>NIO buffers in native byte order take the fast paths; any other byte order
 * (the {@code ByteBuffer} default is big-endian) is honoured through the slower
 * API path. Writing into a read-only buffer throws
 * {@link java.nio.ReadOnlyBufferException}, as NIO's own {@code put} does.</p>
 *
 * <p>With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API
 * backend goes through the buffers' own {@code get}/{@code put} methods and performs the
 * standard checks. Heap arrays are bounds-checked on every backend
 * ({@link IndexOutOfBoundsException}). The UNSAFE backend uses {@code sun.misc.Unsafe}; on
 * JDK 23+ (JEP 471) run with {@code --sun-misc-unsafe-memory-access=allow} or select
 * {@code -Djoml.storeLoadBackend=api}.</p>
 *
 * <p>Edge cases, per backend: a read-only {@code dest} buffer never takes the Unsafe path
 * and is rejected by the API path ({@link java.nio.ReadOnlyBufferException} from the buffer
 * {@code put}). Buffer offsets are absolute indices counted from index 0, regardless of
 * the buffer's position; the API path uses the buffer's absolute {@code get}/{@code put}, so
 * an access beyond the {@code limit} throws {@link IndexOutOfBoundsException}, whereas the
 * UNSAFE path addresses a direct buffer by its base address and ignores position, limit and
 * capacity. A negative {@code count} performs no reads or writes on the API and SIMD paths;
 * the UNSAFE {@code copy} fast path rejects it ({@link IndexOutOfBoundsException} for an
 * array end, {@link IllegalArgumentException} from {@code Unsafe.copyMemory} otherwise).</p>
 *
 * <p>All buffer parameters in a single call must use the same storage backing,
 * except the {@code copy} methods, which translate between any two backings.
 * Element layout is column-major: what {@code storeCM}/{@code loadCM} and the bare
 * {@code store}/{@code load} of Float2x3 write and read.</p>
 *
 * <p>Configuration freezing: this class holds no static state of its own, so a call freezes only
 * the flags its overload reads. Every non-bulk buffer and raw-address overload - and the
 * array-to-array {@code copy} - reads {@code Joml.storeLoadBackend()}, which class-initializes
 * {@link Joml} and freezes the {@link JomlConfig} flags ({@code returnNew},
 * {@code storeLoadBackend}, {@code vectorApi}); the bulk {@code count} overloads dispatch through
 * {@code SimdSupport} like every other SIMD path (below). Every overload with a Vector-API or
 * fused-multiply-add dispatch consults {@code SimdSupport}, whose initialization snapshots
 * {@code Math.useFma()} - freezing all {@link Math} flags ({@code useFma}, {@code cosFromSin},
 * {@code fastmath}, {@code sinLookup}, {@code strictMath}) - and {@code Joml.VECTOR_API}, freezing
 * the {@link JomlConfig} flags as well; the scalar kernels call {@link Math} for their
 * multiply-adds and transcendentals, which freezes the {@code Math} flags likewise. Only the array
 * overloads of operations with neither a SIMD path nor a multiply-add nor a transcendental freeze
 * nothing.</p>
 *
 * <p>Each method summary below is the one the {@link Float2x3} API carries, so
 * the two can never describe the same operation differently: "this matrix" there is the
 * matrix held in {@code src} at {@code srcOffset}, and the result is written to
 * {@code dest} at {@code destOffset}. The full text sits on the {@code float[]} overload of
 * each method; the other storage overloads point at it, differing from it in
 * storage alone.</p>
 */
public final class Float2x3Ops {
    private Float2x3Ops() {}

    /**
     * Get the column at the given index of this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param col the column index
     * @return {@code dest}
     * @throws IndexOutOfBoundsException if {@code col} is not in {@code [0, 3)}
     */
    public static float[] getColumn(float[] dest, int destOffset, float[] src, int srcOffset, int col) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _idxSw0;
        float _idxSw1;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; break;
            case 2: _idxSw0 = _self02; _idxSw1 = _self12; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest[destOffset] = _idxSw0;
        dest[destOffset + 1] = _idxSw1;
        return dest;
    }

    /** {@link #getColumn(float[], int, float[], int, int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer getColumn(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int col) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.getColumn_unsafe(dest, destOffset, src, srcOffset, col);
        return Float2x3OpsKernelsTypedBuffer.getColumn_api(dest, destOffset, src, srcOffset, col);
    }

    /** {@link #getColumn(float[], int, float[], int, int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getColumn(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.getColumn_unsafe(dest, destOffset, src, srcOffset, col);
        return Float2x3OpsKernelsByteBuffer.getColumn_api(dest, destOffset, src, srcOffset, col);
    }

    /** {@link #getColumn(float[], int, float[], int, int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long getColumn(long dest, long src, int col) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.getColumn_unsafe(dest, src, col);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the rotation angle in radians ({@code atan2(m10, m00)}) of this matrix; for a matrix
     * carrying scale the rotation angle is still recovered as long as the X-axis scale is positive.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return the rotation angle in radians ({@code atan2(m10, m00)}) of this matrix; for a matrix
     *        carrying scale the rotation angle is still recovered as long as the X-axis scale is
     *        positive
     */
    public static float getRotationAngle(float[] src, int srcOffset) {
        return Math.atan2(src[srcOffset + 1], src[srcOffset]);
    }

    /** {@link #getRotationAngle(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float getRotationAngle(java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.getRotationAngle_unsafe(src, srcOffset);
        return Float2x3OpsKernelsTypedBuffer.getRotationAngle_api(src, srcOffset);
    }

    /** {@link #getRotationAngle(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float getRotationAngle(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.getRotationAngle_unsafe(src, srcOffset);
        return Float2x3OpsKernelsByteBuffer.getRotationAngle_api(src, srcOffset);
    }

    /** {@link #getRotationAngle(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float getRotationAngle(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.getRotationAngle_unsafe(src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Get the row at the given index of this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param row the row index
     * @return {@code dest}
     * @throws IndexOutOfBoundsException if {@code row} is not in {@code [0, 2)}
     */
    public static float[] getRow(float[] dest, int destOffset, float[] src, int srcOffset, int row) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _idxSw0;
        float _idxSw1;
        float _idxSw2;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; _idxSw2 = _self02; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; _idxSw2 = _self12; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest[destOffset] = _idxSw0;
        dest[destOffset + 1] = _idxSw1;
        dest[destOffset + 2] = _idxSw2;
        return dest;
    }

    /** {@link #getRow(float[], int, float[], int, int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer getRow(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int row) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.getRow_unsafe(dest, destOffset, src, srcOffset, row);
        return Float2x3OpsKernelsTypedBuffer.getRow_api(dest, destOffset, src, srcOffset, row);
    }

    /** {@link #getRow(float[], int, float[], int, int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getRow(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.getRow_unsafe(dest, destOffset, src, srcOffset, row);
        return Float2x3OpsKernelsByteBuffer.getRow_api(dest, destOffset, src, srcOffset, row);
    }

    /** {@link #getRow(float[], int, float[], int, int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long getRow(long dest, long src, int row) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.getRow_unsafe(dest, src, row);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Get the translation of this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] getTranslation(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self12 = src[srcOffset + 5];
        dest[destOffset] = src[srcOffset + 4];
        dest[destOffset + 1] = _self12;
        return dest;
    }

    /** {@link #getTranslation(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer getTranslation(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.getTranslation_unsafe(dest, destOffset, src, srcOffset);
        return Float2x3OpsKernelsTypedBuffer.getTranslation_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getTranslation(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getTranslation(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.getTranslation_unsafe(dest, destOffset, src, srcOffset);
        return Float2x3OpsKernelsByteBuffer.getTranslation_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getTranslation(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long getTranslation(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.getTranslation_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the determinant of the linear part (the upper-left square block) of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return the determinant of the linear part (the upper-left square block) of this matrix
     */
    public static float determinant(float[] src, int srcOffset) {
        return Math.fma(src[srcOffset], src[srcOffset + 3], -(src[srcOffset + 2] * src[srcOffset + 1]));
    }

    /** {@link #determinant(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float determinant(java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.determinant_unsafe(src, srcOffset);
        return Float2x3OpsKernelsTypedBuffer.determinant_api(src, srcOffset);
    }

    /** {@link #determinant(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float determinant(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.determinant_unsafe(src, srcOffset);
        return Float2x3OpsKernelsByteBuffer.determinant_api(src, srcOffset);
    }

    /** {@link #determinant(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float determinant(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.determinant_unsafe(src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the Frobenius norm of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return the Frobenius norm of this matrix
     */
    public static float frobeniusNorm(float[] src, int srcOffset) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        return (float) java.lang.Math.sqrt(Math.fma(_self12, _self12, Math.fma(_self11, _self11, Math.fma(_self10, _self10, Math.fma(_self02, _self02, Math.fma(_self00, _self00, _self01 * _self01))))));
    }

    /** {@link #frobeniusNorm(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float frobeniusNorm(java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.frobeniusNorm_unsafe(src, srcOffset);
        return Float2x3OpsKernelsTypedBuffer.frobeniusNorm_api(src, srcOffset);
    }

    /** {@link #frobeniusNorm(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float frobeniusNorm(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.frobeniusNorm_unsafe(src, srcOffset);
        return Float2x3OpsKernelsByteBuffer.frobeniusNorm_api(src, srcOffset);
    }

    /** {@link #frobeniusNorm(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float frobeniusNorm(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.frobeniusNorm_unsafe(src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Invert this affine matrix, i.e. compute the inverse of the implied square homogeneous matrix
     * and store the result in {@code dest}.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] invert(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _t3 = Math.fma(_self00, _self11, -(_self01 * _self10));
        if (!(java.lang.Math.abs(_t3) > 1.1754944E-38f && java.lang.Math.abs(_t3) < 8.507059E37f)) return Float2x3OpsKernelsArray.invert_degenerate(dest, destOffset, src, srcOffset);
        float _t3_inv = 1.0f / _t3;
        dest[destOffset] = _self11 * _t3_inv;
        dest[destOffset + 1] = -(_self10 * _t3_inv);
        dest[destOffset + 2] = -(_self01 * _t3_inv);
        dest[destOffset + 3] = _self00 * _t3_inv;
        dest[destOffset + 4] = -(Math.fma(_self02, _self11, -(_self01 * _self12)) * _t3_inv);
        dest[destOffset + 5] = -(Math.fma(_self00, _self12, -(_self02 * _self10)) * _t3_inv);
        return dest;
    }

    /** {@link #invert(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer invert(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.invert_unsafe(dest, destOffset, src, srcOffset);
        return Float2x3OpsKernelsTypedBuffer.invert_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invert(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invert(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.invert_unsafe(dest, destOffset, src, srcOffset);
        return Float2x3OpsKernelsByteBuffer.invert_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invert(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long invert(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.invert_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the inverse of the product of this matrix and {@code other}, i.e.
     * {@code (this * other)^-1} and store the result in {@code dest}.
     * <p>
     * The product is formed first and inverted afterwards, so the result is the inverse of the
     * rounded product: its accuracy is bounded by the condition number of {@code this * other}, not
     * by the condition numbers of the two factors. For an ill-conditioned product (a near-singular
     * factor, or factors of very different scale) invert both factors separately and multiply the
     * inverses in reverse order instead.
     * <p>
     * Valid input: the product of this matrix and {@code other} must be invertible.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param other the storage holding the right factor of the product
     * @param otherOffset the element index in {@code other} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] invertProduct(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _other00 = other[otherOffset];
        float _other10 = other[otherOffset + 1];
        float _other01 = other[otherOffset + 2];
        float _other11 = other[otherOffset + 3];
        float _other02 = other[otherOffset + 4];
        float _other12 = other[otherOffset + 5];
        float _t6 = Math.fma(_other01, _self10, _other11 * _self11);
        float _t7 = Math.fma(_other00, _self00, _other10 * _self01);
        float _t8 = Math.fma(_other00, _self10, _other10 * _self11);
        float _t9 = Math.fma(_other01, _self00, _other11 * _self01);
        float _t15 = Math.fma(_t7, _t6, -(_t8 * _t9));
        if (!(java.lang.Math.abs(_t15) > 1.1754944E-38f && java.lang.Math.abs(_t15) < 8.507059E37f)) return Float2x3OpsKernelsArray.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        float _t15_inv = 1.0f / _t15;
        dest[destOffset] = _t6 * _t15_inv;
        dest[destOffset + 1] = -(_t8 * _t15_inv);
        dest[destOffset + 2] = -(_t9 * _t15_inv);
        dest[destOffset + 3] = _t7 * _t15_inv;
        return invertProduct_sbd09442e_1(dest, destOffset, _t6, _t7, _t8, _t9, Math.fma(_other02, _self00, Math.fma(_other12, _self01, _self02)), Math.fma(_other02, _self10, Math.fma(_other12, _self11, _self12)), _t15_inv);
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private static float[] invertProduct_sbd09442e_1(float[] dest, int destOffset, float _t6, float _t7, float _t8, float _t9, float _t10, float _t11, float _t15_inv) {
        dest[destOffset + 4] = -(Math.fma(_t10, _t6, -(_t11 * _t9)) * _t15_inv);
        dest[destOffset + 5] = -(Math.fma(_t11, _t7, -(_t10 * _t8)) * _t15_inv);
        return dest;
    }

    /** {@link #invertProduct(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer invertProduct(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.invertProduct_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float2x3OpsKernelsTypedBuffer.invertProduct_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #invertProduct(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invertProduct(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.invertProduct_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float2x3OpsKernelsByteBuffer.invertProduct_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #invertProduct(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long invertProduct(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.invertProduct_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Transpose this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] transpose(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self01;
        dest[destOffset + 2] = _self02;
        dest[destOffset + 3] = _self10;
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = _self12;
        return dest;
    }

    /** {@link #transpose(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer transpose(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.transpose_unsafe(dest, destOffset, src, srcOffset);
        return Float2x3OpsKernelsTypedBuffer.transpose_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #transpose(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer transpose(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.transpose_unsafe(dest, destOffset, src, srcOffset);
        return Float2x3OpsKernelsByteBuffer.transpose_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #transpose(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long transpose(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.transpose_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Add {@code other} to this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param other the storage holding the matrix to add
     * @param otherOffset the element index in {@code other} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] add(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src[srcOffset + _lo + 1];
            float _eother1 = other[otherOffset + _lo + 1];
            dest[destOffset + _lo] = other[otherOffset + _lo] + src[srcOffset + _lo];
            dest[destOffset + _lo + 1] = _eother1 + _eself1;
        }
        return dest;
    }

    /** {@link #add(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer add(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.add_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float2x3OpsKernelsTypedBuffer.add_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer add(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.add_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float2x3OpsKernelsByteBuffer.add_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long add(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.add_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Multiply each component of this matrix by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * Only the stored elements take part: the implicit last row {@code (0, 0, 1)} stays as it is,
     * so the result is still affine.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param scalar the factor to multiply each component by
     * @return {@code dest}
     */
    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src[srcOffset + _lo + 1];
            dest[destOffset + _lo] = scalar * src[srcOffset + _lo];
            dest[destOffset + _lo + 1] = scalar * _eself1;
        }
        return dest;
    }

    /** {@link #mul(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.mul_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float2x3OpsKernelsTypedBuffer.mul_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.mul_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float2x3OpsKernelsByteBuffer.mul_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mul(long dest, long src, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.mul_unsafe(dest, src, scalar);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Negate this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] negate(float[] dest, int destOffset, float[] src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src[srcOffset + _lo + 1];
            dest[destOffset + _lo] = -src[srcOffset + _lo];
            dest[destOffset + _lo + 1] = -_eself1;
        }
        return dest;
    }

    /** {@link #negate(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer negate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.negate_unsafe(dest, destOffset, src, srcOffset);
        return Float2x3OpsKernelsTypedBuffer.negate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer negate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.negate_unsafe(dest, destOffset, src, srcOffset);
        return Float2x3OpsKernelsByteBuffer.negate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long negate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.negate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Subtract {@code other} from this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param other the storage holding the matrix to subtract
     * @param otherOffset the element index in {@code other} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] sub(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src[srcOffset + _lo + 1];
            float _eother1 = other[otherOffset + _lo + 1];
            dest[destOffset + _lo] = src[srcOffset + _lo] - other[otherOffset + _lo];
            dest[destOffset + _lo + 1] = _eself1 - _eother1;
        }
        return dest;
    }

    /** {@link #sub(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer sub(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.sub_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float2x3OpsKernelsTypedBuffer.sub_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sub(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.sub_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float2x3OpsKernelsByteBuffer.sub_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long sub(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.sub_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Set this matrix to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param v the storage holding the matrix to copy
     * @param vOffset the element index in {@code v} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] set(float[] dest, int destOffset, float[] v, int vOffset) {
        if (SimdSupport.VECTOR_API) return Float2x3OpsSimd.set(dest, destOffset, v, vOffset);
        return Float2x3OpsKernelsArray.set_scalar(dest, destOffset, v, vOffset);
    }

    /** {@link #set(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer set(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.set_unsafe(dest, destOffset, v, vOffset);
        return Float2x3OpsKernelsTypedBuffer.set_api(dest, destOffset, v, vOffset);
    }

    /** {@link #set(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer set(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.set_unsafe(dest, destOffset, v, vOffset);
        return Float2x3OpsKernelsByteBuffer.set_api(dest, destOffset, v, vOffset);
    }

    /** {@link #set(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long set(long dest, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.set_unsafe(dest, v);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Set this matrix to the given 2x2 matrix, copying the overlapping cells and filling the rest
     * with identity.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param m the storage holding the matrix to copy from
     * @param mOffset the element index in {@code m} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] setMat2x2(float[] dest, int destOffset, float[] m, int mOffset) {
        if (SimdSupport.VECTOR_API) return Float2x3OpsSimd.setMat2x2(dest, destOffset, m, mOffset);
        return Float2x3OpsKernelsArray.setMat2x2_scalar(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat2x2(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer setMat2x2(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.setMat2x2_unsafe(dest, destOffset, m, mOffset);
        return Float2x3OpsKernelsTypedBuffer.setMat2x2_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat2x2(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer setMat2x2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.setMat2x2_unsafe(dest, destOffset, m, mOffset);
        return Float2x3OpsKernelsByteBuffer.setMat2x2_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat2x2(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long setMat2x2(long dest, long m) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.setMat2x2_unsafe(dest, m);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Set this matrix to the given 3x3 matrix, copying the overlapping cells and dropping the rest.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param m the storage holding the matrix to copy from
     * @param mOffset the element index in {@code m} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] setMat3x3(float[] dest, int destOffset, float[] m, int mOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            int _lom = _l * 3;
            float _em1 = m[mOffset + _lom + 1];
            dest[destOffset + _lo] = m[mOffset + _lom];
            dest[destOffset + _lo + 1] = _em1;
        }
        return dest;
    }

    /** {@link #setMat3x3(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer setMat3x3(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.setMat3x3_unsafe(dest, destOffset, m, mOffset);
        return Float2x3OpsKernelsTypedBuffer.setMat3x3_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat3x3(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer setMat3x3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.setMat3x3_unsafe(dest, destOffset, m, mOffset);
        return Float2x3OpsKernelsByteBuffer.setMat3x3_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat3x3(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long setMat3x3(long dest, long m) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.setMat3x3_unsafe(dest, m);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Set the translation of this matrix, leaving every other element untouched.
     * <p>
     * Only the first two elements of the last column are written, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this overwrites the
     * translation instead of composing a translation onto the existing transformation.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param tX the {@code x} component of the translation offsets {@code (tX, tY)}
     * @param tY the {@code y} component of the translation offsets {@code (tX, tY)}
     * @return {@code dest}
     */
    public static float[] withTranslation(float[] dest, int destOffset, float[] src, int srcOffset, float tX, float tY) {
        if (SimdSupport.VECTOR_API) return Float2x3OpsSimd.withTranslation(dest, destOffset, src, srcOffset, tX, tY);
        return Float2x3OpsKernelsArray.withTranslation_scalar(dest, destOffset, src, srcOffset, tX, tY);
    }

    /** {@link #withTranslation(float[], int, float[], int, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer withTranslation(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float tX, float tY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.withTranslation_unsafe(dest, destOffset, src, srcOffset, tX, tY);
        return Float2x3OpsKernelsTypedBuffer.withTranslation_api(dest, destOffset, src, srcOffset, tX, tY);
    }

    /** {@link #withTranslation(float[], int, float[], int, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer withTranslation(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float tX, float tY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.withTranslation_unsafe(dest, destOffset, src, srcOffset, tX, tY);
        return Float2x3OpsKernelsByteBuffer.withTranslation_api(dest, destOffset, src, srcOffset, tX, tY);
    }

    /** {@link #withTranslation(float[], int, float[], int, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long withTranslation(long dest, long src, float tX, float tY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.withTranslation_unsafe(dest, src, tX, tY);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Set the translation of this matrix, leaving every other element untouched.
     * <p>
     * Only the first two elements of the last column are written, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this overwrites the
     * translation instead of composing a translation onto the existing transformation.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param t the storage holding the translation offsets
     * @param tOffset the element index in {@code t} at which the vector starts
     * @return {@code dest}
     */
    public static float[] withTranslation(float[] dest, int destOffset, float[] src, int srcOffset, float[] t, int tOffset) {
        if (SimdSupport.VECTOR_API) return Float2x3OpsSimd.withTranslation(dest, destOffset, src, srcOffset, t, tOffset);
        return Float2x3OpsKernelsArray.withTranslation_scalar(dest, destOffset, src, srcOffset, t, tOffset);
    }

    /** {@link #withTranslation(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer withTranslation(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t, int tOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && t.isDirect() && t.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.withTranslation_unsafe(dest, destOffset, src, srcOffset, t, tOffset);
        return Float2x3OpsKernelsTypedBuffer.withTranslation_api(dest, destOffset, src, srcOffset, t, tOffset);
    }

    /** {@link #withTranslation(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer withTranslation(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t, int tOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && t.isDirect() && t.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.withTranslation_unsafe(dest, destOffset, src, srcOffset, t, tOffset);
        return Float2x3OpsKernelsByteBuffer.withTranslation_api(dest, destOffset, src, srcOffset, t, tOffset);
    }

    /** {@link #withTranslation(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long withTranslation(long dest, long src, long t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.withTranslation_unsafe(dest, src, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Extract the upper-left 2x2 linear block of this matrix (dropping the translation column) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] to2x2(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return Float2x3OpsSimd.to2x2(dest, destOffset, src, srcOffset);
        return Float2x3OpsKernelsArray.to2x2_scalar(dest, destOffset, src, srcOffset);
    }

    /** {@link #to2x2(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer to2x2(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.to2x2_unsafe(dest, destOffset, src, srcOffset);
        return Float2x3OpsKernelsTypedBuffer.to2x2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to2x2(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer to2x2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.to2x2_unsafe(dest, destOffset, src, srcOffset);
        return Float2x3OpsKernelsByteBuffer.to2x2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to2x2(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long to2x2(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.to2x2_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Extend this matrix to a 3x3 matrix, filling the missing cells with identity and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] to3x3(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = _self01;
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = 0.0f;
        dest[destOffset + 6] = _self02;
        dest[destOffset + 7] = _self12;
        dest[destOffset + 8] = 1.0f;
        return dest;
    }

    /** {@link #to3x3(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer to3x3(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.to3x3_unsafe(dest, destOffset, src, srcOffset);
        return Float2x3OpsKernelsTypedBuffer.to3x3_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to3x3(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer to3x3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.to3x3_unsafe(dest, destOffset, src, srcOffset);
        return Float2x3OpsKernelsByteBuffer.to3x3_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to3x3(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long to3x3(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.to3x3_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] makeIdentity(float[] dest, int destOffset) {
        dest[destOffset] = 1.0f;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 1.0f;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = 0.0f;
        return dest;
    }

    /** {@link #makeIdentity(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeIdentity(java.nio.FloatBuffer dest, int destOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.makeIdentity_unsafe(dest, destOffset);
        return Float2x3OpsKernelsTypedBuffer.makeIdentity_api(dest, destOffset);
    }

    /** {@link #makeIdentity(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeIdentity(java.nio.ByteBuffer dest, int destOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.makeIdentity_unsafe(dest, destOffset);
        return Float2x3OpsKernelsByteBuffer.makeIdentity_api(dest, destOffset);
    }

    /** {@link #makeIdentity(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long makeIdentity(long dest) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.makeIdentity_unsafe(dest);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Linearly interpolate between this matrix and {@code other} using the interpolation factor
     * {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this matrix (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param other the storage holding the matrix to interpolate towards
     * @param otherOffset the element index in {@code other} at which the matrix starts
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = src[srcOffset + _lo];
            float _eself1 = src[srcOffset + _lo + 1];
            float _eother1 = other[otherOffset + _lo + 1];
            dest[destOffset + _lo] = Math.fma(t, other[otherOffset + _lo] - _eself0, _eself0);
            dest[destOffset + _lo + 1] = Math.fma(t, _eother1 - _eself1, _eself1);
        }
        return dest;
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer lerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float2x3OpsKernelsTypedBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float2x3OpsKernelsByteBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long lerp(long dest, long src, long other, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.lerp_unsafe(dest, src, other, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Multiply this matrix by {@code right} and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * Both operands are identity-extended to this matrix's square size for the multiplication, and
     * the product is projected back onto this shape.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param right the storage holding the right operand
     * @param rightOffset the element index in {@code right} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _right00 = right[rightOffset];
        float _right10 = right[rightOffset + 1];
        float _right01 = right[rightOffset + 2];
        float _right11 = right[rightOffset + 3];
        float _right02 = right[rightOffset + 4];
        float _right12 = right[rightOffset + 5];
        dest[destOffset] = Math.fma(_right00, _self00, _right10 * _self01);
        dest[destOffset + 1] = Math.fma(_right00, _self10, _right10 * _self11);
        dest[destOffset + 2] = Math.fma(_right01, _self00, _right11 * _self01);
        dest[destOffset + 3] = Math.fma(_right01, _self10, _right11 * _self11);
        dest[destOffset + 4] = Math.fma(_right02, _self00, Math.fma(_right12, _self01, _self02));
        dest[destOffset + 5] = Math.fma(_right02, _self10, Math.fma(_right12, _self11, _self12));
        return dest;
    }

    /** {@link #mul(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && right.isDirect() && right.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.mul_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Float2x3OpsKernelsTypedBuffer.mul_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mul(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && right.isDirect() && right.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.mul_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Float2x3OpsKernelsByteBuffer.mul_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mul(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mul(long dest, long src, long right) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.mul_unsafe(dest, src, right);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Multiply this matrix by {@code right} and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * The operand is identity-extended to this matrix's square size before the multiplication, and
     * the product is projected back onto this shape.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param right the storage holding the right operand
     * @param rightOffset the element index in {@code right} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] mulMat2x2(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _right00 = right[rightOffset];
        float _right10 = right[rightOffset + 1];
        float _right01 = right[rightOffset + 2];
        float _right11 = right[rightOffset + 3];
        dest[destOffset] = Math.fma(_right00, _self00, _right10 * _self01);
        dest[destOffset + 1] = Math.fma(_right00, _self10, _right10 * _self11);
        dest[destOffset + 2] = Math.fma(_right01, _self00, _right11 * _self01);
        dest[destOffset + 3] = Math.fma(_right01, _self10, _right11 * _self11);
        dest[destOffset + 4] = _self02;
        dest[destOffset + 5] = _self12;
        return dest;
    }

    /** {@link #mulMat2x2(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mulMat2x2(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && right.isDirect() && right.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.mulMat2x2_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Float2x3OpsKernelsTypedBuffer.mulMat2x2_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mulMat2x2(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mulMat2x2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && right.isDirect() && right.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.mulMat2x2_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Float2x3OpsKernelsByteBuffer.mulMat2x2_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mulMat2x2(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mulMat2x2(long dest, long src, long right) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.mulMat2x2_unsafe(dest, src, right);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Multiply this matrix by the given matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param right the storage holding the right operand
     * @param rightOffset the element index in {@code right} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] mulMat3x3(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            float _eright0 = right[rightOffset + _lo];
            float _eright1 = right[rightOffset + _lo + 1];
            float _eright2 = right[rightOffset + _lo + 2];
            dest[destOffset + _lo] = Math.fma(_eright2, _self02, Math.fma(_eright0, _self00, _eright1 * _self01));
            dest[destOffset + _lo + 1] = Math.fma(_eright2, _self12, Math.fma(_eright0, _self10, _eright1 * _self11));
            dest[destOffset + _lo + 2] = _eright2;
        }
        return dest;
    }

    /** {@link #mulMat3x3(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mulMat3x3(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer right, int rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && right.isDirect() && right.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.mulMat3x3_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Float2x3OpsKernelsTypedBuffer.mulMat3x3_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mulMat3x3(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mulMat3x3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && right.isDirect() && right.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.mulMat3x3_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Float2x3OpsKernelsByteBuffer.mulMat3x3_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mulMat3x3(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mulMat3x3(long dest, long src, long right) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.mulMat3x3_unsafe(dest, src, right);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Pre-multiply the transformation {@code other} onto this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the given transformation matrix, then the
     * new matrix will be {@code T * M}. So when transforming a vector {@code v} with the new matrix
     * by using {@code T * M * v}, the given transformation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param other the storage holding the left operand
     * @param otherOffset the element index in {@code other} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] preMul(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _other00 = other[otherOffset];
        float _other10 = other[otherOffset + 1];
        float _other01 = other[otherOffset + 2];
        float _other11 = other[otherOffset + 3];
        float _other02 = other[otherOffset + 4];
        float _other12 = other[otherOffset + 5];
        dest[destOffset] = Math.fma(_other00, _self00, _other01 * _self10);
        dest[destOffset + 1] = Math.fma(_other10, _self00, _other11 * _self10);
        dest[destOffset + 2] = Math.fma(_other00, _self01, _other01 * _self11);
        dest[destOffset + 3] = Math.fma(_other10, _self01, _other11 * _self11);
        dest[destOffset + 4] = Math.fma(_other00, _self02, Math.fma(_other01, _self12, _other02));
        dest[destOffset + 5] = Math.fma(_other10, _self02, Math.fma(_other11, _self12, _other12));
        return dest;
    }

    /** {@link #preMul(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preMul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.preMul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float2x3OpsKernelsTypedBuffer.preMul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMul(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.preMul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float2x3OpsKernelsByteBuffer.preMul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMul(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preMul(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.preMul_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Pre-multiply {@code other} onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the transformation of the operand will be applied last.
     * <p>
     * The operand is identity-extended to this matrix's square size before the multiplication, and
     * the product is projected back onto this shape.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param other the storage holding the left operand
     * @param otherOffset the element index in {@code other} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] preMulMat2x2(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _other00 = other[otherOffset];
        float _other10 = other[otherOffset + 1];
        float _other01 = other[otherOffset + 2];
        float _other11 = other[otherOffset + 3];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself0 = src[srcOffset + _lo];
            float _eself1 = src[srcOffset + _lo + 1];
            dest[destOffset + _lo] = Math.fma(_other00, _eself0, _other01 * _eself1);
            dest[destOffset + _lo + 1] = Math.fma(_other10, _eself0, _other11 * _eself1);
        }
        return dest;
    }

    /** {@link #preMulMat2x2(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preMulMat2x2(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.preMulMat2x2_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float2x3OpsKernelsTypedBuffer.preMulMat2x2_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMulMat2x2(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMulMat2x2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.preMulMat2x2_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float2x3OpsKernelsByteBuffer.preMulMat2x2_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMulMat2x2(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preMulMat2x2(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.preMulMat2x2_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Pre-multiply the given matrix onto this matrix, i.e. compute {@code other * this} and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param other the storage holding the left operand
     * @param otherOffset the element index in {@code other} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] preMulMat3x3(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _other00 = other[otherOffset];
        float _other10 = other[otherOffset + 1];
        float _other20 = other[otherOffset + 2];
        float _other01 = other[otherOffset + 3];
        float _other11 = other[otherOffset + 4];
        float _other21 = other[otherOffset + 5];
        float _other02 = other[otherOffset + 6];
        float _other12 = other[otherOffset + 7];
        float _other22 = other[otherOffset + 8];
        dest[destOffset] = Math.fma(_other00, _self00, _other01 * _self10);
        dest[destOffset + 1] = Math.fma(_other10, _self00, _other11 * _self10);
        dest[destOffset + 2] = Math.fma(_other20, _self00, _other21 * _self10);
        dest[destOffset + 3] = Math.fma(_other00, _self01, _other01 * _self11);
        dest[destOffset + 4] = Math.fma(_other10, _self01, _other11 * _self11);
        dest[destOffset + 5] = Math.fma(_other20, _self01, _other21 * _self11);
        dest[destOffset + 6] = Math.fma(_other00, _self02, Math.fma(_other01, _self12, _other02));
        dest[destOffset + 7] = Math.fma(_other10, _self02, Math.fma(_other11, _self12, _other12));
        dest[destOffset + 8] = Math.fma(_other20, _self02, Math.fma(_other21, _self12, _other22));
        return dest;
    }

    /** {@link #preMulMat3x3(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preMulMat3x3(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.preMulMat3x3_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float2x3OpsKernelsTypedBuffer.preMulMat3x3_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMulMat3x3(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMulMat3x3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.preMulMat3x3_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float2x3OpsKernelsByteBuffer.preMulMat3x3_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMulMat3x3(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preMulMat3x3(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.preMulMat3x3_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Add {@code other} scaled by {@code weight} to this matrix and store the result in
     * {@code dest}.
     * <p>
     * Only the stored elements take part: the implicit last row {@code (0, 0, 1)} stays as it is,
     * so the result is still affine.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param other the storage holding the matrix to scale and add
     * @param otherOffset the element index in {@code other} at which the matrix starts
     * @param weight the factor to scale the given matrix by before adding
     * @return {@code dest}
     */
    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src[srcOffset + _lo + 1];
            float _eother1 = other[otherOffset + _lo + 1];
            dest[destOffset + _lo] = Math.fma(weight, other[otherOffset + _lo], src[srcOffset + _lo]);
            dest[destOffset + _lo + 1] = Math.fma(weight, _eother1, _eself1);
        }
        return dest;
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer addScaled(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float weight) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return Float2x3OpsKernelsTypedBuffer.addScaled_api(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer addScaled(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float weight) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return Float2x3OpsKernelsByteBuffer.addScaled_api(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long addScaled(long dest, long src, long other, float weight) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.addScaled_unsafe(dest, src, other, weight);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Set this matrix to a rotation by {@code angle}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] makeRotation(float[] dest, int destOffset, float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = _t1;
        dest[destOffset + 1] = _t0;
        dest[destOffset + 2] = -_t0;
        dest[destOffset + 3] = _t1;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = 0.0f;
        return dest;
    }

    /** {@link #makeRotation(float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeRotation(java.nio.FloatBuffer dest, int destOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.makeRotation_unsafe(dest, destOffset, angle);
        return Float2x3OpsKernelsTypedBuffer.makeRotation_api(dest, destOffset, angle);
    }

    /** {@link #makeRotation(float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotation(java.nio.ByteBuffer dest, int destOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.makeRotation_unsafe(dest, destOffset, angle);
        return Float2x3OpsKernelsByteBuffer.makeRotation_api(dest, destOffset, angle);
    }

    /** {@link #makeRotation(float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long makeRotation(long dest, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.makeRotation_unsafe(dest, angle);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Set this matrix to a scaling transformation that scales by ({@code vX}, {@code vY}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return {@code dest}
     */
    public static float[] makeScaling(float[] dest, int destOffset, float vX, float vY) {
        dest[destOffset] = vX;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = vY;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = 0.0f;
        return dest;
    }

    /** {@link #makeScaling(float[], int, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeScaling(java.nio.FloatBuffer dest, int destOffset, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.makeScaling_unsafe(dest, destOffset, vX, vY);
        return Float2x3OpsKernelsTypedBuffer.makeScaling_api(dest, destOffset, vX, vY);
    }

    /** {@link #makeScaling(float[], int, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeScaling(java.nio.ByteBuffer dest, int destOffset, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.makeScaling_unsafe(dest, destOffset, vX, vY);
        return Float2x3OpsKernelsByteBuffer.makeScaling_api(dest, destOffset, vX, vY);
    }

    /** {@link #makeScaling(float[], int, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long makeScaling(long dest, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.makeScaling_unsafe(dest, vX, vY);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Set this matrix to a scaling transformation that scales by {@code v}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param v the storage holding the scale factors
     * @param vOffset the element index in {@code v} at which the vector starts
     * @return {@code dest}
     */
    public static float[] makeScaling(float[] dest, int destOffset, float[] v, int vOffset) {
        float _vy = v[vOffset + 1];
        dest[destOffset] = v[vOffset];
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = _vy;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = 0.0f;
        return dest;
    }

    /** {@link #makeScaling(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeScaling(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.makeScaling_unsafe(dest, destOffset, v, vOffset);
        return Float2x3OpsKernelsTypedBuffer.makeScaling_api(dest, destOffset, v, vOffset);
    }

    /** {@link #makeScaling(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeScaling(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.makeScaling_unsafe(dest, destOffset, v, vOffset);
        return Float2x3OpsKernelsByteBuffer.makeScaling_api(dest, destOffset, v, vOffset);
    }

    /** {@link #makeScaling(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long makeScaling(long dest, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.makeScaling_unsafe(dest, v);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Set this matrix to a scaling transformation that scales by {@code s}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param s the uniform scale factor
     * @return {@code dest}
     */
    public static float[] makeScaling(float[] dest, int destOffset, float s) {
        dest[destOffset] = s;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = s;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = 0.0f;
        return dest;
    }

    /** {@link #makeScaling(float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeScaling(java.nio.FloatBuffer dest, int destOffset, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.makeScaling_unsafe(dest, destOffset, s);
        return Float2x3OpsKernelsTypedBuffer.makeScaling_api(dest, destOffset, s);
    }

    /** {@link #makeScaling(float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeScaling(java.nio.ByteBuffer dest, int destOffset, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.makeScaling_unsafe(dest, destOffset, s);
        return Float2x3OpsKernelsByteBuffer.makeScaling_api(dest, destOffset, s);
    }

    /** {@link #makeScaling(float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long makeScaling(long dest, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.makeScaling_unsafe(dest, s);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Set this matrix to a translation transformation that translates by ({@code vX}, {@code vY}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param vX the {@code x} component of the translation offsets {@code (vX, vY)}
     * @param vY the {@code y} component of the translation offsets {@code (vX, vY)}
     * @return {@code dest}
     */
    public static float[] makeTranslation(float[] dest, int destOffset, float vX, float vY) {
        dest[destOffset] = 1.0f;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 1.0f;
        dest[destOffset + 4] = vX;
        dest[destOffset + 5] = vY;
        return dest;
    }

    /** {@link #makeTranslation(float[], int, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeTranslation(java.nio.FloatBuffer dest, int destOffset, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.makeTranslation_unsafe(dest, destOffset, vX, vY);
        return Float2x3OpsKernelsTypedBuffer.makeTranslation_api(dest, destOffset, vX, vY);
    }

    /** {@link #makeTranslation(float[], int, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeTranslation(java.nio.ByteBuffer dest, int destOffset, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.makeTranslation_unsafe(dest, destOffset, vX, vY);
        return Float2x3OpsKernelsByteBuffer.makeTranslation_api(dest, destOffset, vX, vY);
    }

    /** {@link #makeTranslation(float[], int, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long makeTranslation(long dest, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.makeTranslation_unsafe(dest, vX, vY);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Set this matrix to a translation transformation that translates by {@code v}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param v the storage holding the translation offsets
     * @param vOffset the element index in {@code v} at which the vector starts
     * @return {@code dest}
     */
    public static float[] makeTranslation(float[] dest, int destOffset, float[] v, int vOffset) {
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        dest[destOffset] = 1.0f;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 1.0f;
        dest[destOffset + 4] = _vx;
        dest[destOffset + 5] = _vy;
        return dest;
    }

    /** {@link #makeTranslation(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeTranslation(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.makeTranslation_unsafe(dest, destOffset, v, vOffset);
        return Float2x3OpsKernelsTypedBuffer.makeTranslation_api(dest, destOffset, v, vOffset);
    }

    /** {@link #makeTranslation(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeTranslation(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.makeTranslation_unsafe(dest, destOffset, v, vOffset);
        return Float2x3OpsKernelsByteBuffer.makeTranslation_api(dest, destOffset, v, vOffset);
    }

    /** {@link #makeTranslation(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long makeTranslation(long dest, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.makeTranslation_unsafe(dest, v);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Set this matrix to the 2D view transformation that maps the rectangle
     * {@code [left, right] x [bottom, top]} onto {@code [-1, +1] x [-1, +1]}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param left the distance to the left edge of the view rectangle
     * @param right the distance to the right edge of the view rectangle
     * @param bottom the distance to the bottom edge of the view rectangle
     * @param top the distance to the top edge of the view rectangle
     * @return {@code dest}
     */
    public static float[] makeView(float[] dest, int destOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        dest[destOffset] = _t0_inv + _t0_inv;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = _t1_inv + _t1_inv;
        dest[destOffset + 4] = -((left + right) * _t0_inv);
        dest[destOffset + 5] = -((bottom + top) * _t1_inv);
        return dest;
    }

    /** {@link #makeView(float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeView(java.nio.FloatBuffer dest, int destOffset, float left, float right, float bottom, float top) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.makeView_unsafe(dest, destOffset, left, right, bottom, top);
        return Float2x3OpsKernelsTypedBuffer.makeView_api(dest, destOffset, left, right, bottom, top);
    }

    /** {@link #makeView(float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeView(java.nio.ByteBuffer dest, int destOffset, float left, float right, float bottom, float top) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.makeView_unsafe(dest, destOffset, left, right, bottom, top);
        return Float2x3OpsKernelsByteBuffer.makeView_api(dest, destOffset, left, right, bottom, top);
    }

    /** {@link #makeView(float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long makeView(long dest, float left, float right, float bottom, float top) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.makeView_unsafe(dest, left, right, bottom, top);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Pre-multiply a rotation by {@code angle} onto this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] preRotate(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = Math.fma(_self00, _t1, -(_self10 * _t0));
        dest[destOffset + 1] = Math.fma(_self00, _t0, _self10 * _t1);
        dest[destOffset + 2] = Math.fma(_self01, _t1, -(_self11 * _t0));
        dest[destOffset + 3] = Math.fma(_self01, _t0, _self11 * _t1);
        dest[destOffset + 4] = Math.fma(_self02, _t1, -(_self12 * _t0));
        dest[destOffset + 5] = Math.fma(_self02, _t0, _self12 * _t1);
        return dest;
    }

    /** {@link #preRotate(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preRotate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.preRotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Float2x3OpsKernelsTypedBuffer.preRotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotate(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preRotate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.preRotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Float2x3OpsKernelsByteBuffer.preRotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotate(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preRotate(long dest, long src, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.preRotate_unsafe(dest, src, angle);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Pre-multiply the rotation {@code angle} about the pivot point ({@code pivotX},
     * {@code pivotY}) onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * The pivot sandwich {@code translate(pivot) * R * translate(-pivot)} is evaluated so that its
     * translation part, {@code pivot - R * pivot}, keeps its accuracy for pivots far from the
     * origin.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @return {@code dest}
     */
    public static float[] preRotateAround(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float pivotX, float pivotY) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        dest[destOffset] = Math.fma(_self00, _t2, -(_self10 * _t0));
        dest[destOffset + 1] = Math.fma(_self00, _t0, _self10 * _t2);
        dest[destOffset + 2] = Math.fma(_self01, _t2, -(_self11 * _t0));
        dest[destOffset + 3] = Math.fma(_self01, _t0, _self11 * _t2);
        dest[destOffset + 4] = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(_self02, _t2, -(_self12 * _t0));
        dest[destOffset + 5] = Math.fma(_self02, _t0, _self12 * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        return dest;
    }

    /** {@link #preRotateAround(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preRotateAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.preRotateAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return Float2x3OpsKernelsTypedBuffer.preRotateAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@link #preRotateAround(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preRotateAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.preRotateAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return Float2x3OpsKernelsByteBuffer.preRotateAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@link #preRotateAround(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preRotateAround(long dest, long src, float angle, float pivotX, float pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.preRotateAround_unsafe(dest, src, angle, pivotX, pivotY);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Pre-multiply the rotation {@code angle} about the pivot point {@code pivot} onto this matrix
     * and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * The pivot sandwich {@code translate(pivot) * R * translate(-pivot)} is evaluated so that its
     * translation part, {@code pivot - R * pivot}, keeps its accuracy for pivots far from the
     * origin.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param pivot the storage holding the pivot point
     * @param pivotOffset the element index in {@code pivot} at which the vector starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] preRotateAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float angle) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _pivotx = pivot[pivotOffset];
        float _pivoty = pivot[pivotOffset + 1];
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        dest[destOffset] = Math.fma(_self00, _t2, -(_self10 * _t0));
        dest[destOffset + 1] = Math.fma(_self00, _t0, _self10 * _t2);
        dest[destOffset + 2] = Math.fma(_self01, _t2, -(_self11 * _t0));
        dest[destOffset + 3] = Math.fma(_self01, _t0, _self11 * _t2);
        dest[destOffset + 4] = Math.fma(_pivotx, _t5, _pivoty * _t0) + Math.fma(_self02, _t2, -(_self12 * _t0));
        dest[destOffset + 5] = Math.fma(_self02, _t0, _self12 * _t2) + Math.fma(_pivoty, _t5, -(_pivotx * _t0));
        return dest;
    }

    /** {@link #preRotateAround(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preRotateAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.preRotateAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Float2x3OpsKernelsTypedBuffer.preRotateAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #preRotateAround(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preRotateAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.preRotateAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Float2x3OpsKernelsByteBuffer.preRotateAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #preRotateAround(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preRotateAround(long dest, long src, long pivot, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.preRotateAround_unsafe(dest, src, pivot, angle);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Pre-multiply a scaling by ({@code vX}, {@code vY}) onto this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return {@code dest}
     */
    public static float[] preScale(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src[srcOffset + _lo + 1];
            dest[destOffset + _lo] = src[srcOffset + _lo] * vX;
            dest[destOffset + _lo + 1] = _eself1 * vY;
        }
        return dest;
    }

    /** {@link #preScale(float[], int, float[], int, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preScale(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Float2x3OpsKernelsTypedBuffer.preScale_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #preScale(float[], int, float[], int, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Float2x3OpsKernelsByteBuffer.preScale_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #preScale(float[], int, float[], int, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preScale(long dest, long src, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.preScale_unsafe(dest, src, vX, vY);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Pre-multiply a scaling by {@code v} onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code p} with the new matrix by using
     * {@code S * M * p}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param v the storage holding the scale factors
     * @param vOffset the element index in {@code v} at which the vector starts
     * @return {@code dest}
     */
    public static float[] preScale(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src[srcOffset + _lo + 1];
            dest[destOffset + _lo] = src[srcOffset + _lo] * _vx;
            dest[destOffset + _lo + 1] = _eself1 * _vy;
        }
        return dest;
    }

    /** {@link #preScale(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preScale(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Float2x3OpsKernelsTypedBuffer.preScale_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #preScale(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Float2x3OpsKernelsByteBuffer.preScale_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #preScale(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preScale(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.preScale_unsafe(dest, src, v);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Pre-multiply a scaling by {@code s} onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param s the uniform scale factor
     * @return {@code dest}
     */
    public static float[] preScale(float[] dest, int destOffset, float[] src, int srcOffset, float s) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            float _eself1 = src[srcOffset + _lo + 1];
            dest[destOffset + _lo] = s * src[srcOffset + _lo];
            dest[destOffset + _lo + 1] = s * _eself1;
        }
        return dest;
    }

    /** {@link #preScale(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preScale(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, s);
        return Float2x3OpsKernelsTypedBuffer.preScale_api(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #preScale(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, s);
        return Float2x3OpsKernelsByteBuffer.preScale_api(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #preScale(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preScale(long dest, long src, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.preScale_unsafe(dest, src, s);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Pre-multiply a scaling by {@code s} about the pivot point ({@code pivotX}, {@code pivotY})
     * onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param s the uniform scale factor
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @return {@code dest}
     */
    public static float[] preScaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float s, float pivotX, float pivotY) {
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _t0 = 1.0f - s;
        dest[destOffset] = s * src[srcOffset];
        dest[destOffset + 1] = s * _self10;
        dest[destOffset + 2] = s * _self01;
        dest[destOffset + 3] = s * _self11;
        dest[destOffset + 4] = Math.fma(s, _self02, pivotX * _t0);
        dest[destOffset + 5] = Math.fma(s, _self12, pivotY * _t0);
        return dest;
    }

    /** {@link #preScaleAround(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preScaleAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return Float2x3OpsKernelsTypedBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
    }

    /** {@link #preScaleAround(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return Float2x3OpsKernelsByteBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
    }

    /** {@link #preScaleAround(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preScaleAround(long dest, long src, float s, float pivotX, float pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.preScaleAround_unsafe(dest, src, s, pivotX, pivotY);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Pre-multiply a scaling by {@code s} about the pivot point {@code pivot} onto this matrix and
     * store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param pivot the storage holding the pivot point
     * @param pivotOffset the element index in {@code pivot} at which the vector starts
     * @param s the uniform scale factor
     * @return {@code dest}
     */
    public static float[] preScaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _pivotx = pivot[pivotOffset];
        float _pivoty = pivot[pivotOffset + 1];
        float _t0 = 1.0f - s;
        dest[destOffset] = s * src[srcOffset];
        dest[destOffset + 1] = s * _self10;
        dest[destOffset + 2] = s * _self01;
        dest[destOffset + 3] = s * _self11;
        dest[destOffset + 4] = Math.fma(s, _self02, _pivotx * _t0);
        dest[destOffset + 5] = Math.fma(s, _self12, _pivoty * _t0);
        return dest;
    }

    /** {@link #preScaleAround(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preScaleAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return Float2x3OpsKernelsTypedBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    /** {@link #preScaleAround(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return Float2x3OpsKernelsByteBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    /** {@link #preScaleAround(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preScaleAround(long dest, long src, long pivot, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.preScaleAround_unsafe(dest, src, pivot, s);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Pre-multiply a scaling by ({@code sX}, {@code sY}) about the pivot point ({@code pivotX},
     * {@code pivotY}) onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param sX the {@code x} component of the vector {@code (sX, sY)}
     * @param sY the {@code y} component of the vector {@code (sX, sY)}
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @return {@code dest}
     */
    public static float[] preScaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        dest[destOffset] = sX * src[srcOffset];
        dest[destOffset + 1] = sY * _self10;
        dest[destOffset + 2] = sX * _self01;
        dest[destOffset + 3] = sY * _self11;
        dest[destOffset + 4] = Math.fma(pivotX, 1.0f - sX, sX * _self02);
        dest[destOffset + 5] = Math.fma(pivotY, 1.0f - sY, sY * _self12);
        return dest;
    }

    /** {@link #preScaleAround(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preScaleAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return Float2x3OpsKernelsTypedBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
    }

    /** {@link #preScaleAround(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return Float2x3OpsKernelsByteBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
    }

    /** {@link #preScaleAround(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preScaleAround(long dest, long src, float sX, float sY, float pivotX, float pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.preScaleAround_unsafe(dest, src, sX, sY, pivotX, pivotY);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Pre-multiply a scaling by {@code s} about the pivot point {@code pivot} onto this matrix and
     * store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param s the storage holding the scale factors
     * @param sOffset the element index in {@code s} at which the vector starts
     * @param pivot the storage holding the pivot point
     * @param pivotOffset the element index in {@code pivot} at which the vector starts
     * @return {@code dest}
     */
    public static float[] preScaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _sx = s[sOffset];
        float _sy = s[sOffset + 1];
        float _pivotx = pivot[pivotOffset];
        float _pivoty = pivot[pivotOffset + 1];
        dest[destOffset] = _sx * src[srcOffset];
        dest[destOffset + 1] = _sy * _self10;
        dest[destOffset + 2] = _sx * _self01;
        dest[destOffset + 3] = _sy * _self11;
        dest[destOffset + 4] = Math.fma(_pivotx, 1.0f - _sx, _sx * _self02);
        dest[destOffset + 5] = Math.fma(_pivoty, 1.0f - _sy, _sy * _self12);
        return dest;
    }

    /** {@link #preScaleAround(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preScaleAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer s, int sOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && s.isDirect() && s.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return Float2x3OpsKernelsTypedBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@link #preScaleAround(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && s.isDirect() && s.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return Float2x3OpsKernelsByteBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@link #preScaleAround(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preScaleAround(long dest, long src, long s, long pivot) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.preScaleAround_unsafe(dest, src, s, pivot);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Pre-multiply a translation by ({@code vX}, {@code vY}) onto this matrix and store the result
     * in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code T * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code T * M * v}, the translation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return {@code dest}
     */
    public static float[] preTranslate(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY) {
        if (SimdSupport.VECTOR_API) return Float2x3OpsSimd.preTranslate(dest, destOffset, src, srcOffset, vX, vY);
        return Float2x3OpsKernelsArray.preTranslate_scalar(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #preTranslate(float[], int, float[], int, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preTranslate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.preTranslate_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Float2x3OpsKernelsTypedBuffer.preTranslate_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #preTranslate(float[], int, float[], int, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preTranslate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.preTranslate_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Float2x3OpsKernelsByteBuffer.preTranslate_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #preTranslate(float[], int, float[], int, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preTranslate(long dest, long src, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.preTranslate_unsafe(dest, src, vX, vY);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Pre-multiply a translation by {@code v} onto this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code T * M}. So when transforming a vector {@code p} with the new matrix by using
     * {@code T * M * p}, the translation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param v the storage holding the translation offsets
     * @param vOffset the element index in {@code v} at which the vector starts
     * @return {@code dest}
     */
    public static float[] preTranslate(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        if (SimdSupport.VECTOR_API) return Float2x3OpsSimd.preTranslate(dest, destOffset, src, srcOffset, v, vOffset);
        return Float2x3OpsKernelsArray.preTranslate_scalar(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #preTranslate(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preTranslate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.preTranslate_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Float2x3OpsKernelsTypedBuffer.preTranslate_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #preTranslate(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preTranslate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.preTranslate_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Float2x3OpsKernelsByteBuffer.preTranslate_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #preTranslate(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preTranslate(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.preTranslate_unsafe(dest, src, v);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Apply a rotation by {@code angle} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] rotate(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = Math.fma(_self00, _t1, _self01 * _t0);
        dest[destOffset + 1] = Math.fma(_self10, _t1, _self11 * _t0);
        dest[destOffset + 2] = Math.fma(_self01, _t1, -(_self00 * _t0));
        dest[destOffset + 3] = Math.fma(_self11, _t1, -(_self10 * _t0));
        dest[destOffset + 4] = _self02;
        dest[destOffset + 5] = _self12;
        return dest;
    }

    /** {@link #rotate(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Float2x3OpsKernelsTypedBuffer.rotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotate(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Float2x3OpsKernelsByteBuffer.rotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotate(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotate(long dest, long src, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.rotate_unsafe(dest, src, angle);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Apply the rotation {@code angle} about the pivot point ({@code pivotX}, {@code pivotY}) to
     * this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The pivot sandwich {@code translate(pivot) * R * translate(-pivot)} is evaluated so that its
     * translation part, {@code pivot - R * pivot}, keeps its accuracy for pivots far from the
     * origin.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @return {@code dest}
     */
    public static float[] rotateAround(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float pivotX, float pivotY) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        dest[destOffset] = Math.fma(_self00, _t2, _self01 * _t0);
        dest[destOffset + 1] = Math.fma(_self10, _t2, _self11 * _t0);
        dest[destOffset + 2] = Math.fma(_self01, _t2, -(_self00 * _t0));
        dest[destOffset + 3] = Math.fma(_self11, _t2, -(_self10 * _t0));
        dest[destOffset + 4] = Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02));
        dest[destOffset + 5] = Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12));
        return dest;
    }

    /** {@link #rotateAround(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.rotateAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return Float2x3OpsKernelsTypedBuffer.rotateAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@link #rotateAround(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.rotateAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return Float2x3OpsKernelsByteBuffer.rotateAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@link #rotateAround(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateAround(long dest, long src, float angle, float pivotX, float pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.rotateAround_unsafe(dest, src, angle, pivotX, pivotY);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Apply the rotation {@code angle} about the pivot point {@code pivot} to this matrix and store
     * the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The pivot sandwich {@code translate(pivot) * R * translate(-pivot)} is evaluated so that its
     * translation part, {@code pivot - R * pivot}, keeps its accuracy for pivots far from the
     * origin.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param pivot the storage holding the pivot point
     * @param pivotOffset the element index in {@code pivot} at which the vector starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] rotateAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float angle) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _pivotx = pivot[pivotOffset];
        float _pivoty = pivot[pivotOffset + 1];
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(_pivotx, _t8, _pivoty * _t0);
        float _t10 = Math.fma(_pivoty, _t8, -(_pivotx * _t0));
        dest[destOffset] = Math.fma(_self00, _t2, _self01 * _t0);
        dest[destOffset + 1] = Math.fma(_self10, _t2, _self11 * _t0);
        dest[destOffset + 2] = Math.fma(_self01, _t2, -(_self00 * _t0));
        dest[destOffset + 3] = Math.fma(_self11, _t2, -(_self10 * _t0));
        dest[destOffset + 4] = Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02));
        dest[destOffset + 5] = Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12));
        return dest;
    }

    /** {@link #rotateAround(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.rotateAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Float2x3OpsKernelsTypedBuffer.rotateAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateAround(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.rotateAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Float2x3OpsKernelsByteBuffer.rotateAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateAround(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateAround(long dest, long src, long pivot, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.rotateAround_unsafe(dest, src, pivot, angle);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Apply a scaling by ({@code vX}, {@code vY}) to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return {@code dest}
     */
    public static float[] scale(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY) {
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        dest[destOffset] = src[srcOffset] * vX;
        dest[destOffset + 1] = _self10 * vX;
        dest[destOffset + 2] = _self01 * vY;
        dest[destOffset + 3] = _self11 * vY;
        dest[destOffset + 4] = _self02;
        dest[destOffset + 5] = _self12;
        return dest;
    }

    /** {@link #scale(float[], int, float[], int, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer scale(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.scale_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Float2x3OpsKernelsTypedBuffer.scale_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #scale(float[], int, float[], int, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.scale_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Float2x3OpsKernelsByteBuffer.scale_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #scale(float[], int, float[], int, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long scale(long dest, long src, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.scale_unsafe(dest, src, vX, vY);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Apply a scaling by {@code v} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code p} with the new matrix by using
     * {@code M * S * p}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param v the storage holding the scale factors
     * @param vOffset the element index in {@code v} at which the vector starts
     * @return {@code dest}
     */
    public static float[] scale(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        dest[destOffset] = src[srcOffset] * _vx;
        dest[destOffset + 1] = _self10 * _vx;
        dest[destOffset + 2] = _self01 * _vy;
        dest[destOffset + 3] = _self11 * _vy;
        dest[destOffset + 4] = _self02;
        dest[destOffset + 5] = _self12;
        return dest;
    }

    /** {@link #scale(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer scale(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.scale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Float2x3OpsKernelsTypedBuffer.scale_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #scale(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.scale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Float2x3OpsKernelsByteBuffer.scale_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #scale(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long scale(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.scale_unsafe(dest, src, v);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Apply a scaling by {@code s} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param s the uniform scale factor
     * @return {@code dest}
     */
    public static float[] scale(float[] dest, int destOffset, float[] src, int srcOffset, float s) {
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        dest[destOffset] = s * src[srcOffset];
        dest[destOffset + 1] = s * _self10;
        dest[destOffset + 2] = s * _self01;
        dest[destOffset + 3] = s * _self11;
        dest[destOffset + 4] = _self02;
        dest[destOffset + 5] = _self12;
        return dest;
    }

    /** {@link #scale(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer scale(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.scale_unsafe(dest, destOffset, src, srcOffset, s);
        return Float2x3OpsKernelsTypedBuffer.scale_api(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #scale(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.scale_unsafe(dest, destOffset, src, srcOffset, s);
        return Float2x3OpsKernelsByteBuffer.scale_api(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #scale(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long scale(long dest, long src, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.scale_unsafe(dest, src, s);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Apply a scaling by {@code s} about the pivot point ({@code pivotX}, {@code pivotY}) to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param s the uniform scale factor
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @return {@code dest}
     */
    public static float[] scaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float s, float pivotX, float pivotY) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        dest[destOffset] = s * _self00;
        dest[destOffset + 1] = s * _self10;
        dest[destOffset + 2] = s * _self01;
        dest[destOffset + 3] = s * _self11;
        dest[destOffset + 4] = Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02));
        dest[destOffset + 5] = Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12));
        return dest;
    }

    /** {@link #scaleAround(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer scaleAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return Float2x3OpsKernelsTypedBuffer.scaleAround_api(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
    }

    /** {@link #scaleAround(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s, float pivotX, float pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return Float2x3OpsKernelsByteBuffer.scaleAround_api(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
    }

    /** {@link #scaleAround(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long scaleAround(long dest, long src, float s, float pivotX, float pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.scaleAround_unsafe(dest, src, s, pivotX, pivotY);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Apply a scaling by {@code s} about the pivot point {@code pivot} to this matrix and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param pivot the storage holding the pivot point
     * @param pivotOffset the element index in {@code pivot} at which the vector starts
     * @param s the uniform scale factor
     * @return {@code dest}
     */
    public static float[] scaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _t0 = 1.0f - s;
        float _t1 = pivot[pivotOffset] * _t0;
        float _t2 = pivot[pivotOffset + 1] * _t0;
        dest[destOffset] = s * _self00;
        dest[destOffset + 1] = s * _self10;
        dest[destOffset + 2] = s * _self01;
        dest[destOffset + 3] = s * _self11;
        dest[destOffset + 4] = Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02));
        dest[destOffset + 5] = Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12));
        return dest;
    }

    /** {@link #scaleAround(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer scaleAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return Float2x3OpsKernelsTypedBuffer.scaleAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    /** {@link #scaleAround(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return Float2x3OpsKernelsByteBuffer.scaleAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    /** {@link #scaleAround(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long scaleAround(long dest, long src, long pivot, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.scaleAround_unsafe(dest, src, pivot, s);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Apply a scaling by ({@code sX}, {@code sY}) about the pivot point ({@code pivotX},
     * {@code pivotY}) to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param sX the {@code x} component of the vector {@code (sX, sY)}
     * @param sY the {@code y} component of the vector {@code (sX, sY)}
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @return {@code dest}
     */
    public static float[] scaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        dest[destOffset] = sX * _self00;
        dest[destOffset + 1] = sX * _self10;
        dest[destOffset + 2] = sY * _self01;
        dest[destOffset + 3] = sY * _self11;
        dest[destOffset + 4] = Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02));
        dest[destOffset + 5] = Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12));
        return dest;
    }

    /** {@link #scaleAround(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer scaleAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return Float2x3OpsKernelsTypedBuffer.scaleAround_api(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
    }

    /** {@link #scaleAround(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sX, float sY, float pivotX, float pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return Float2x3OpsKernelsByteBuffer.scaleAround_api(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
    }

    /** {@link #scaleAround(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long scaleAround(long dest, long src, float sX, float sY, float pivotX, float pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.scaleAround_unsafe(dest, src, sX, sY, pivotX, pivotY);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Apply a scaling by {@code s} about the pivot point {@code pivot} to this matrix and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param s the storage holding the scale factors
     * @param sOffset the element index in {@code s} at which the vector starts
     * @param pivot the storage holding the pivot point
     * @param pivotOffset the element index in {@code pivot} at which the vector starts
     * @return {@code dest}
     */
    public static float[] scaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _sx = s[sOffset];
        float _sy = s[sOffset + 1];
        float _t2 = pivot[pivotOffset] * (1.0f - _sx);
        float _t3 = pivot[pivotOffset + 1] * (1.0f - _sy);
        dest[destOffset] = _sx * _self00;
        dest[destOffset + 1] = _sx * _self10;
        dest[destOffset + 2] = _sy * _self01;
        dest[destOffset + 3] = _sy * _self11;
        dest[destOffset + 4] = Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02));
        dest[destOffset + 5] = Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12));
        return dest;
    }

    /** {@link #scaleAround(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer scaleAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer s, int sOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && s.isDirect() && s.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return Float2x3OpsKernelsTypedBuffer.scaleAround_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@link #scaleAround(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && s.isDirect() && s.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return Float2x3OpsKernelsByteBuffer.scaleAround_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@link #scaleAround(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long scaleAround(long dest, long src, long s, long pivot) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.scaleAround_unsafe(dest, src, s, pivot);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Apply a translation by ({@code vX}, {@code vY}) to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the translation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param vX the {@code x} component of the translation offsets {@code (vX, vY)}
     * @param vY the {@code y} component of the translation offsets {@code (vX, vY)}
     * @return {@code dest}
     */
    public static float[] translate(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY) {
        if (SimdSupport.VECTOR_API) return Float2x3OpsSimd.translate(dest, destOffset, src, srcOffset, vX, vY);
        return Float2x3OpsKernelsArray.translate_scalar(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #translate(float[], int, float[], int, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer translate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.translate_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Float2x3OpsKernelsTypedBuffer.translate_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #translate(float[], int, float[], int, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer translate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.translate_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Float2x3OpsKernelsByteBuffer.translate_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #translate(float[], int, float[], int, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long translate(long dest, long src, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.translate_unsafe(dest, src, vX, vY);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Apply a translation by {@code v} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code p} with the new matrix by using
     * {@code M * T * p}, the translation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param v the storage holding the translation offsets
     * @param vOffset the element index in {@code v} at which the vector starts
     * @return {@code dest}
     */
    public static float[] translate(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        if (SimdSupport.VECTOR_API) return Float2x3OpsSimd.translate(dest, destOffset, src, srcOffset, v, vOffset);
        return Float2x3OpsKernelsArray.translate_scalar(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #translate(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer translate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.translate_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Float2x3OpsKernelsTypedBuffer.translate_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #translate(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer translate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.translate_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Float2x3OpsKernelsByteBuffer.translate_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #translate(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long translate(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.translate_unsafe(dest, src, v);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Apply a 2D view transformation that maps the rectangle {@code [left, right] x [bottom, top]}
     * onto {@code [-1, +1] x [-1, +1]} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code V} the view matrix, then the new matrix will
     * be {@code M * V}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * V * v}, the view will be applied first.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param left the distance to the left edge of the view rectangle
     * @param right the distance to the right edge of the view rectangle
     * @param bottom the distance to the bottom edge of the view rectangle
     * @param top the distance to the top edge of the view rectangle
     * @return {@code dest}
     */
    public static float[] view(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self01 = src[srcOffset + 2];
        float _self11 = src[srcOffset + 3];
        float _self02 = src[srcOffset + 4];
        float _self12 = src[srcOffset + 5];
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        dest[destOffset] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp1 * _self01;
        dest[destOffset + 3] = _sp1 * _self11;
        dest[destOffset + 4] = _self02 + (-(_self00 * _sp2) - _self01 * _sp3);
        dest[destOffset + 5] = _self12 + (-(_self10 * _sp2) - _self11 * _sp3);
        return dest;
    }

    /** {@link #view(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer view(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float left, float right, float bottom, float top) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.view_unsafe(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return Float2x3OpsKernelsTypedBuffer.view_api(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    /** {@link #view(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer view(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float left, float right, float bottom, float top) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.view_unsafe(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return Float2x3OpsKernelsByteBuffer.view_api(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    /** {@link #view(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long view(long dest, long src, float left, float right, float bottom, float top) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.view_unsafe(dest, src, left, right, bottom, top);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return {@code dest}
     */
    public static float[] mulVec3(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        float _self10 = src[srcOffset + 1];
        float _self11 = src[srcOffset + 3];
        float _self12 = src[srcOffset + 5];
        dest[destOffset] = Math.fma(src[srcOffset + 4], vZ, Math.fma(src[srcOffset], vX, src[srcOffset + 2] * vY));
        dest[destOffset + 1] = Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY));
        return dest;
    }

    /** {@link #mulVec3(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mulVec3(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.mulVec3_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return Float2x3OpsKernelsTypedBuffer.mulVec3_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    /** {@link #mulVec3(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mulVec3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.mulVec3_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return Float2x3OpsKernelsByteBuffer.mulVec3_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    /** {@link #mulVec3(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mulVec3(long dest, long src, float vX, float vY, float vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.mulVec3_unsafe(dest, src, vX, vY, vZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param v the storage holding the right operand of the product
     * @param vOffset the element index in {@code v} at which the vector starts
     * @return {@code dest}
     */
    public static float[] mulVec3(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _self10 = src[srcOffset + 1];
        float _self11 = src[srcOffset + 3];
        float _self12 = src[srcOffset + 5];
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        dest[destOffset] = Math.fma(src[srcOffset + 4], _vz, Math.fma(src[srcOffset], _vx, src[srcOffset + 2] * _vy));
        dest[destOffset + 1] = Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy));
        return dest;
    }

    /** {@link #mulVec3(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mulVec3(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.mulVec3_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Float2x3OpsKernelsTypedBuffer.mulVec3_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #mulVec3(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mulVec3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.mulVec3_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Float2x3OpsKernelsByteBuffer.mulVec3_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #mulVec3(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mulVec3(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.mulVec3_unsafe(dest, src, v);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Transform the given direction by this matrix, ignoring any translation and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return {@code dest}
     */
    public static float[] transformDirection(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY) {
        float _self10 = src[srcOffset + 1];
        float _self11 = src[srcOffset + 3];
        dest[destOffset] = Math.fma(src[srcOffset], vX, src[srcOffset + 2] * vY);
        dest[destOffset + 1] = Math.fma(_self10, vX, _self11 * vY);
        return dest;
    }

    /** {@link #transformDirection(float[], int, float[], int, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer transformDirection(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.transformDirection_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Float2x3OpsKernelsTypedBuffer.transformDirection_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #transformDirection(float[], int, float[], int, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer transformDirection(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.transformDirection_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Float2x3OpsKernelsByteBuffer.transformDirection_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #transformDirection(float[], int, float[], int, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long transformDirection(long dest, long src, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.transformDirection_unsafe(dest, src, vX, vY);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Transform the given direction by this matrix, ignoring any translation and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param v the storage holding the direction to transform
     * @param vOffset the element index in {@code v} at which the vector starts
     * @return {@code dest}
     */
    public static float[] transformDirection(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _self10 = src[srcOffset + 1];
        float _self11 = src[srcOffset + 3];
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        dest[destOffset] = Math.fma(src[srcOffset], _vx, src[srcOffset + 2] * _vy);
        dest[destOffset + 1] = Math.fma(_self10, _vx, _self11 * _vy);
        return dest;
    }

    /** {@link #transformDirection(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer transformDirection(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.transformDirection_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Float2x3OpsKernelsTypedBuffer.transformDirection_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #transformDirection(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer transformDirection(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.transformDirection_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Float2x3OpsKernelsByteBuffer.transformDirection_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #transformDirection(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long transformDirection(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.transformDirection_unsafe(dest, src, v);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Transform the given position by this matrix, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return {@code dest}
     */
    public static float[] transformPosition(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY) {
        float _self10 = src[srcOffset + 1];
        float _self11 = src[srcOffset + 3];
        float _self12 = src[srcOffset + 5];
        dest[destOffset] = Math.fma(src[srcOffset], vX, Math.fma(src[srcOffset + 2], vY, src[srcOffset + 4]));
        dest[destOffset + 1] = Math.fma(_self10, vX, Math.fma(_self11, vY, _self12));
        return dest;
    }

    /** {@link #transformPosition(float[], int, float[], int, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer transformPosition(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.transformPosition_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Float2x3OpsKernelsTypedBuffer.transformPosition_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #transformPosition(float[], int, float[], int, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer transformPosition(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.transformPosition_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Float2x3OpsKernelsByteBuffer.transformPosition_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #transformPosition(float[], int, float[], int, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long transformPosition(long dest, long src, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.transformPosition_unsafe(dest, src, vX, vY);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Transform the given position by this matrix, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param v the storage holding the position to transform
     * @param vOffset the element index in {@code v} at which the vector starts
     * @return {@code dest}
     */
    public static float[] transformPosition(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _self10 = src[srcOffset + 1];
        float _self11 = src[srcOffset + 3];
        float _self12 = src[srcOffset + 5];
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        dest[destOffset] = Math.fma(src[srcOffset], _vx, Math.fma(src[srcOffset + 2], _vy, src[srcOffset + 4]));
        dest[destOffset + 1] = Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12));
        return dest;
    }

    /** {@link #transformPosition(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer transformPosition(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsTypedBuffer.transformPosition_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Float2x3OpsKernelsTypedBuffer.transformPosition_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #transformPosition(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer transformPosition(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsKernelsByteBuffer.transformPosition_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Float2x3OpsKernelsByteBuffer.transformPosition_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #transformPosition(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long transformPosition(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float2x3OpsKernelsAddress.transformPosition_unsafe(dest, src, v);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }


    /**
     * Copy one Float2x3 (6 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return Float2x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float2x3 values ({@code count * 6} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API) return Float2x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 357913941 ? -1 : count * 6), src.length);
            java.util.Objects.checkFromIndexSize(destOffset, (count > 357913941 ? -1 : count * 6), dest.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.FLOAT_ARRAY_BASE + srcOffset * 4L, dest, UnsafeCopy.FLOAT_ARRAY_BASE + destOffset * 4L, count * 24L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src[srcOffset + _i];
        return dest;
    }

    /**
     * Copy one Float2x3 (6 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float2x3 values ({@code count * 6} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 357913941 ? -1 : count * 6), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, dest, UnsafeCopy.FLOAT_ARRAY_BASE + destOffset * 4L, count * 24L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src.get(srcOffset + _i);
        return dest;
    }

    /**
     * Copy one Float2x3 (6 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float2x3 values ({@code count * 6} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 357913941 ? -1 : count * 6), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, dest, UnsafeCopy.FLOAT_ARRAY_BASE + destOffset * 4L, count * 24L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src.getFloat(srcOffset + _i * 4);
        return dest;
    }

    /**
     * Copy one Float2x3 (6 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static float[] copy(float[] dest, int destOffset, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(destOffset, 6, dest.length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest, UnsafeCopy.FLOAT_ARRAY_BASE + destOffset * 4L, 24L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Float2x3 values ({@code count * 6} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static float[] copy(float[] dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 357913941 ? -1 : count * 6), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest, UnsafeCopy.FLOAT_ARRAY_BASE + destOffset * 4L, count * 24L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Float2x3 (6 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Float2x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float2x3 values ({@code count * 6} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Float2x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 357913941 ? -1 : count * 6), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.FLOAT_ARRAY_BASE + srcOffset * 4L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, count * 24L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src[srcOffset + _i]);
        return dest;
    }

    /**
     * Copy one Float2x3 (6 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float2x3 values ({@code count * 6} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float2x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, count * 24L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src.get(srcOffset + _i));
        return dest;
    }

    /**
     * Copy one Float2x3 (6 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float2x3 values ({@code count * 6} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, count * 24L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src.getFloat(srcOffset + _i * 4));
        return dest;
    }

    /**
     * Copy one Float2x3 (6 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.hasArray() && dest.order() == java.nio.ByteOrder.nativeOrder() && destOffset >= 0 && destOffset <= dest.limit() - 6) {
            java.util.Objects.checkFromIndexSize(dest.arrayOffset() + destOffset, 6, dest.array().length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest.array(), UnsafeCopy.FLOAT_ARRAY_BASE + (dest.arrayOffset() + destOffset) * 4L, 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            for (int _i = 0; _i < 6; _i++)
                dest.put(destOffset + _i, UnsafeOpsHolder.U.getFloat(src + _i * 4L));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Float2x3 values ({@code count * 6} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, count * 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.hasArray() && dest.order() == java.nio.ByteOrder.nativeOrder() && destOffset >= 0 && (count > 357913941 ? -1 : count * 6) >= 0 && destOffset <= dest.limit() - (count > 357913941 ? -1 : count * 6)) {
            java.util.Objects.checkFromIndexSize(dest.arrayOffset() + destOffset, (count > 357913941 ? -1 : count * 6), dest.array().length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest.array(), UnsafeCopy.FLOAT_ARRAY_BASE + (dest.arrayOffset() + destOffset) * 4L, count * 24L);
            return dest;
        }
        return copy_sb21a46f0_1(dest, destOffset, src, count);
    }

    /** Piece 2 of {@code copy}, split to fit the inline budget; reached only through it. */
    private static java.nio.FloatBuffer copy_sb21a46f0_1(java.nio.FloatBuffer dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            if (count < 0) return dest;
            if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
            int n = count * 6;
            for (int _i = 0; _i < n; _i++)
                dest.put(destOffset + _i, UnsafeOpsHolder.U.getFloat(src + _i * 4L));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Float2x3 (6 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, float[] src, int srcOffset) {
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float2x3 values ({@code count * 6} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, float[] src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 357913941 ? -1 : count * 6), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.FLOAT_ARRAY_BASE + srcOffset * 4L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 24L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest.putFloat(destOffset + _i * 4, src[srcOffset + _i]);
        return dest;
    }

    /**
     * Copy one Float2x3 (6 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float2x3 values ({@code count * 6} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 24L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest.putFloat(destOffset + _i * 4, src.get(srcOffset + _i));
        return dest;
    }

    /**
     * Copy one Float2x3 (6 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float2x3 values ({@code count * 6} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 24L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest.putFloat(destOffset + _i * 4, src.getFloat(srcOffset + _i * 4));
        return dest;
    }

    /**
     * Copy one Float2x3 (6 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.hasArray() && dest.order() == java.nio.ByteOrder.nativeOrder() && destOffset >= 0 && destOffset <= dest.limit() - 24) {
            java.util.Objects.checkFromIndexSize(dest.arrayOffset() + destOffset, 24, dest.array().length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest.array(), UnsafeCopy.BYTE_ARRAY_BASE + (dest.arrayOffset() + destOffset), 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            for (int _i = 0; _i < 6; _i++)
                dest.putFloat(destOffset + _i * 4, UnsafeOpsHolder.U.getFloat(src + _i * 4L));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Float2x3 values ({@code count * 6} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.hasArray() && dest.order() == java.nio.ByteOrder.nativeOrder() && destOffset >= 0 && (count > 89478485 ? -1 : count * 24) >= 0 && destOffset <= dest.limit() - (count > 89478485 ? -1 : count * 24)) {
            java.util.Objects.checkFromIndexSize(dest.arrayOffset() + destOffset, (count > 89478485 ? -1 : count * 24), dest.array().length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest.array(), UnsafeCopy.BYTE_ARRAY_BASE + (dest.arrayOffset() + destOffset), count * 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            if (count < 0) return dest;
            if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
            int n = count * 6;
            for (int _i = 0; _i < n; _i++)
                dest.putFloat(destOffset + _i * 4, UnsafeOpsHolder.U.getFloat(src + _i * 4L));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Float2x3 (6 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, float[] src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(srcOffset, 6, src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.FLOAT_ARRAY_BASE + srcOffset * 4L, null, dest, 24L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Float2x3 values ({@code count * 6} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, float[] src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 357913941 ? -1 : count * 6), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.FLOAT_ARRAY_BASE + srcOffset * 4L, null, dest, count * 24L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Float2x3 (6 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, null, dest, 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.hasArray() && src.order() == java.nio.ByteOrder.nativeOrder() && srcOffset >= 0 && srcOffset <= src.limit() - 6) {
            java.util.Objects.checkFromIndexSize(src.arrayOffset() + srcOffset, 6, src.array().length);
            UnsafeOpsHolder.U.copyMemory(src.array(), UnsafeCopy.FLOAT_ARRAY_BASE + (src.arrayOffset() + srcOffset) * 4L, null, dest, 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            for (int _i = 0; _i < 6; _i++)
                UnsafeOpsHolder.U.putFloat(dest + _i * 4L, src.get(srcOffset + _i));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Float2x3 values ({@code count * 6} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, null, dest, count * 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.hasArray() && src.order() == java.nio.ByteOrder.nativeOrder() && srcOffset >= 0 && (count > 357913941 ? -1 : count * 6) >= 0 && srcOffset <= src.limit() - (count > 357913941 ? -1 : count * 6)) {
            java.util.Objects.checkFromIndexSize(src.arrayOffset() + srcOffset, (count > 357913941 ? -1 : count * 6), src.array().length);
            UnsafeOpsHolder.U.copyMemory(src.array(), UnsafeCopy.FLOAT_ARRAY_BASE + (src.arrayOffset() + srcOffset) * 4L, null, dest, count * 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            if (count < 0) return dest;
            if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
            int n = count * 6;
            for (int _i = 0; _i < n; _i++)
                UnsafeOpsHolder.U.putFloat(dest + _i * 4L, src.get(srcOffset + _i));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Float2x3 (6 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, dest, 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.hasArray() && src.order() == java.nio.ByteOrder.nativeOrder() && srcOffset >= 0 && srcOffset <= src.limit() - 24) {
            java.util.Objects.checkFromIndexSize(src.arrayOffset() + srcOffset, 24, src.array().length);
            UnsafeOpsHolder.U.copyMemory(src.array(), UnsafeCopy.BYTE_ARRAY_BASE + (src.arrayOffset() + srcOffset), null, dest, 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            for (int _i = 0; _i < 6; _i++)
                UnsafeOpsHolder.U.putFloat(dest + _i * 4L, src.getFloat(srcOffset + _i * 4));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Float2x3 values ({@code count * 6} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, dest, count * 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.hasArray() && src.order() == java.nio.ByteOrder.nativeOrder() && srcOffset >= 0 && (count > 89478485 ? -1 : count * 24) >= 0 && srcOffset <= src.limit() - (count > 89478485 ? -1 : count * 24)) {
            java.util.Objects.checkFromIndexSize(src.arrayOffset() + srcOffset, (count > 89478485 ? -1 : count * 24), src.array().length);
            UnsafeOpsHolder.U.copyMemory(src.array(), UnsafeCopy.BYTE_ARRAY_BASE + (src.arrayOffset() + srcOffset), null, dest, count * 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            if (count < 0) return dest;
            if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
            int n = count * 6;
            for (int _i = 0; _i < n; _i++)
                UnsafeOpsHolder.U.putFloat(dest + _i * 4L, src.getFloat(srcOffset + _i * 4));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Float2x3 (6 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, dest, 24L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Float2x3 values ({@code count * 6} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, dest, count * 24L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }
}
