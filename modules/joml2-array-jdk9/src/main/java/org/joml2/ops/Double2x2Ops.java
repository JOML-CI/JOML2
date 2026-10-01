// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.ops;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.kernels.*;
import org.joml2.internal.unsafe.*;

/**
 * Static, allocation-free operations on raw storage holding a {@link Double2x2}.
 *
 * <p>Each method takes one or more buffers ({@code double[]},
 * {@link java.nio.DoubleBuffer}, or {@link java.nio.ByteBuffer})
 * plus an element/byte offset and operates
 * directly on that storage. No {@link Double2x2} instance is allocated.</p>
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
 * {@code store}/{@code load} of Double2x2 write and read.</p>
 *
 * <p>Configuration freezing: this class holds no static state of its own, so a call freezes only
 * the flags its overload reads. Every non-bulk buffer and raw-address overload - and the
 * array-to-array {@code copy} - reads {@code Joml.storeLoadBackend()}, which class-initializes
 * {@link Joml} and freezes the {@link JomlConfig} flags ({@code returnNew},
 * {@code storeLoadBackend}, {@code vectorApi}); the bulk {@code count} overloads loop over the
 * buffer API directly, and freeze the {@code Math} flags only when their arithmetic calls
 * {@link Math}: the bulk {@code fma} and the batched matrix transforms do (through
 * {@code Math.fma}), the other bulk overloads freeze nothing. An array overload whose arithmetic
 * contains a fused multiply-add or a transcendental function calls {@link Math} ({@code fma},
 * {@code sin}, {@code cos}, {@code atan2}, ...), which snapshots and freezes the {@code Math} flags
 * ({@code useFma}, {@code cosFromSin}, {@code fastmath}, {@code sinLookup}, {@code strictMath}) on
 * its first use; the array overloads of the remaining operations (no multiply-add, no
 * transcendental) freeze nothing.</p>
 *
 * <p>Each method summary below is the one the {@link Double2x2} API carries, so
 * the two can never describe the same operation differently: "this matrix" there is the
 * matrix held in {@code src} at {@code srcOffset}, and the result is written to
 * {@code dest} at {@code destOffset}. The full text sits on the {@code double[]} overload of
 * each method; the other storage overloads point at it, differing from it in
 * storage alone.</p>
 */
public final class Double2x2Ops {
    private Double2x2Ops() {}

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
     * @throws IndexOutOfBoundsException if {@code col} is not in {@code [0, 2)}
     */
    public static double[] getColumn(double[] dest, int destOffset, double[] src, int srcOffset, int col) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _idxSw0;
        double _idxSw1;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest[destOffset] = _idxSw0;
        dest[destOffset + 1] = _idxSw1;
        return dest;
    }

    /** {@link #getColumn(double[], int, double[], int, int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer getColumn(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int col) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.getColumn_unsafe(dest, destOffset, src, srcOffset, col);
        return Double2x2OpsKernelsTypedBuffer.getColumn_api(dest, destOffset, src, srcOffset, col);
    }

    /** {@link #getColumn(double[], int, double[], int, int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getColumn(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.getColumn_unsafe(dest, destOffset, src, srcOffset, col);
        return Double2x2OpsKernelsByteBuffer.getColumn_api(dest, destOffset, src, srcOffset, col);
    }

    /** {@link #getColumn(double[], int, double[], int, int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long getColumn(long dest, long src, int col) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.getColumn_unsafe(dest, src, col);
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
    public static double getRotationAngle(double[] src, int srcOffset) {
        return Math.atan2(src[srcOffset + 1], src[srcOffset]);
    }

    /** {@link #getRotationAngle(double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double getRotationAngle(java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.getRotationAngle_unsafe(src, srcOffset);
        return Double2x2OpsKernelsTypedBuffer.getRotationAngle_api(src, srcOffset);
    }

    /** {@link #getRotationAngle(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double getRotationAngle(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.getRotationAngle_unsafe(src, srcOffset);
        return Double2x2OpsKernelsByteBuffer.getRotationAngle_api(src, srcOffset);
    }

    /** {@link #getRotationAngle(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double getRotationAngle(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.getRotationAngle_unsafe(src);
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
    public static double[] getRow(double[] dest, int destOffset, double[] src, int srcOffset, int row) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _idxSw0;
        double _idxSw1;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest[destOffset] = _idxSw0;
        dest[destOffset + 1] = _idxSw1;
        return dest;
    }

    /** {@link #getRow(double[], int, double[], int, int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer getRow(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int row) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.getRow_unsafe(dest, destOffset, src, srcOffset, row);
        return Double2x2OpsKernelsTypedBuffer.getRow_api(dest, destOffset, src, srcOffset, row);
    }

    /** {@link #getRow(double[], int, double[], int, int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getRow(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.getRow_unsafe(dest, destOffset, src, srcOffset, row);
        return Double2x2OpsKernelsByteBuffer.getRow_api(dest, destOffset, src, srcOffset, row);
    }

    /** {@link #getRow(double[], int, double[], int, int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long getRow(long dest, long src, int row) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.getRow_unsafe(dest, src, row);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the cofactor matrix of this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] cofactor(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        dest[destOffset] = src[srcOffset + 3];
        dest[destOffset + 1] = -_self01;
        dest[destOffset + 2] = -_self10;
        dest[destOffset + 3] = _self00;
        return dest;
    }

    /** {@link #cofactor(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer cofactor(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.cofactor_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsTypedBuffer.cofactor_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cofactor(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer cofactor(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.cofactor_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsByteBuffer.cofactor_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cofactor(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long cofactor(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.cofactor_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the determinant of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return the determinant of this matrix
     */
    public static double determinant(double[] src, int srcOffset) {
        return Math.fma(src[srcOffset], src[srcOffset + 3], -(src[srcOffset + 2] * src[srcOffset + 1]));
    }

    /** {@link #determinant(double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double determinant(java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.determinant_unsafe(src, srcOffset);
        return Double2x2OpsKernelsTypedBuffer.determinant_api(src, srcOffset);
    }

    /** {@link #determinant(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double determinant(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.determinant_unsafe(src, srcOffset);
        return Double2x2OpsKernelsByteBuffer.determinant_api(src, srcOffset);
    }

    /** {@link #determinant(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double determinant(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.determinant_unsafe(src);
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
    public static double frobeniusNorm(double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        return java.lang.Math.sqrt(Math.fma(_self11, _self11, Math.fma(_self10, _self10, Math.fma(_self00, _self00, _self01 * _self01))));
    }

    /** {@link #frobeniusNorm(double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double frobeniusNorm(java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.frobeniusNorm_unsafe(src, srcOffset);
        return Double2x2OpsKernelsTypedBuffer.frobeniusNorm_api(src, srcOffset);
    }

    /** {@link #frobeniusNorm(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double frobeniusNorm(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.frobeniusNorm_unsafe(src, srcOffset);
        return Double2x2OpsKernelsByteBuffer.frobeniusNorm_api(src, srcOffset);
    }

    /** {@link #frobeniusNorm(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double frobeniusNorm(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.frobeniusNorm_unsafe(src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Invert this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] invert(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _t3 = Math.fma(_self00, _self11, -(_self01 * _self10));
        if (!(java.lang.Math.abs(_t3) > 2.2250738585072014E-308 && java.lang.Math.abs(_t3) < 4.49423283715579E307)) return Double2x2OpsKernelsArray.invert_degenerate(dest, destOffset, src, srcOffset);
        double _t3_inv = 1.0 / _t3;
        dest[destOffset] = _self11 * _t3_inv;
        dest[destOffset + 1] = -(_self10 * _t3_inv);
        dest[destOffset + 2] = -(_self01 * _t3_inv);
        dest[destOffset + 3] = _self00 * _t3_inv;
        return dest;
    }

    /** {@link #invert(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer invert(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.invert_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsTypedBuffer.invert_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invert(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invert(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.invert_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsByteBuffer.invert_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invert(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long invert(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.invert_unsafe(dest, src);
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
    public static double[] invertProduct(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _other00 = other[otherOffset];
        double _other10 = other[otherOffset + 1];
        double _other01 = other[otherOffset + 2];
        double _other11 = other[otherOffset + 3];
        double _t4 = Math.fma(_other01, _self10, _other11 * _self11);
        double _t5 = Math.fma(_other00, _self00, _other10 * _self01);
        double _t6 = Math.fma(_other00, _self10, _other10 * _self11);
        double _t7 = Math.fma(_other01, _self00, _other11 * _self01);
        double _t11 = Math.fma(_t5, _t4, -(_t6 * _t7));
        if (!(java.lang.Math.abs(_t11) > 2.2250738585072014E-308 && java.lang.Math.abs(_t11) < 4.49423283715579E307)) return Double2x2OpsKernelsArray.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        double _t11_inv = 1.0 / _t11;
        dest[destOffset] = _t4 * _t11_inv;
        dest[destOffset + 1] = -(_t6 * _t11_inv);
        dest[destOffset + 2] = -(_t7 * _t11_inv);
        dest[destOffset + 3] = _t5 * _t11_inv;
        return dest;
    }

    /** {@link #invertProduct(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer invertProduct(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.invertProduct_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x2OpsKernelsTypedBuffer.invertProduct_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #invertProduct(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invertProduct(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.invertProduct_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x2OpsKernelsByteBuffer.invertProduct_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #invertProduct(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long invertProduct(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.invertProduct_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the normal matrix of this matrix, i.e. the transpose of its inverse and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] normal(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _t3 = Math.fma(_self00, _self11, -(_self01 * _self10));
        if (!(java.lang.Math.abs(_t3) > 2.2250738585072014E-308 && java.lang.Math.abs(_t3) < 4.49423283715579E307)) return Double2x2OpsKernelsArray.normal_degenerate(dest, destOffset, src, srcOffset);
        double _t3_inv = 1.0 / _t3;
        dest[destOffset] = _self11 * _t3_inv;
        dest[destOffset + 1] = -(_self01 * _t3_inv);
        dest[destOffset + 2] = -(_self10 * _t3_inv);
        dest[destOffset + 3] = _self00 * _t3_inv;
        return dest;
    }

    /** {@link #normal(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer normal(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.normal_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsTypedBuffer.normal_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normal(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer normal(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.normal_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsByteBuffer.normal_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normal(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long normal(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.normal_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the trace of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return the trace of this matrix
     */
    public static double trace(double[] src, int srcOffset) {
        return src[srcOffset] + src[srcOffset + 3];
    }

    /** {@link #trace(double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double trace(java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.trace_unsafe(src, srcOffset);
        return Double2x2OpsKernelsTypedBuffer.trace_api(src, srcOffset);
    }

    /** {@link #trace(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double trace(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.trace_unsafe(src, srcOffset);
        return Double2x2OpsKernelsByteBuffer.trace_api(src, srcOffset);
    }

    /** {@link #trace(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double trace(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.trace_unsafe(src);
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
    public static double[] transpose(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self01;
        dest[destOffset + 2] = _self10;
        dest[destOffset + 3] = _self11;
        return dest;
    }

    /** {@link #transpose(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer transpose(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.transpose_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsTypedBuffer.transpose_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #transpose(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer transpose(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.transpose_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsByteBuffer.transpose_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #transpose(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long transpose(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.transpose_unsafe(dest, src);
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
    public static double[] add(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _other10 = other[otherOffset + 1];
        double _other01 = other[otherOffset + 2];
        double _other11 = other[otherOffset + 3];
        dest[destOffset] = other[otherOffset] + src[srcOffset];
        dest[destOffset + 1] = _other10 + _self10;
        dest[destOffset + 2] = _other01 + _self01;
        dest[destOffset + 3] = _other11 + _self11;
        return dest;
    }

    /** {@link #add(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer add(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.add_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x2OpsKernelsTypedBuffer.add_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer add(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.add_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x2OpsKernelsByteBuffer.add_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long add(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.add_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Multiply each component of this matrix by {@code scalar} and store the result in
     * {@code dest}.
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
    public static double[] mul(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        dest[destOffset] = scalar * src[srcOffset];
        dest[destOffset + 1] = scalar * _self10;
        dest[destOffset + 2] = scalar * _self01;
        dest[destOffset + 3] = scalar * _self11;
        return dest;
    }

    /** {@link #mul(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mul(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.mul_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Double2x2OpsKernelsTypedBuffer.mul_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.mul_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Double2x2OpsKernelsByteBuffer.mul_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mul(long dest, long src, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.mul_unsafe(dest, src, scalar);
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
    public static double[] negate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        dest[destOffset] = -src[srcOffset];
        dest[destOffset + 1] = -_self10;
        dest[destOffset + 2] = -_self01;
        dest[destOffset + 3] = -_self11;
        return dest;
    }

    /** {@link #negate(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer negate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.negate_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsTypedBuffer.negate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer negate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.negate_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsByteBuffer.negate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long negate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.negate_unsafe(dest, src);
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
    public static double[] sub(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _other10 = other[otherOffset + 1];
        double _other01 = other[otherOffset + 2];
        double _other11 = other[otherOffset + 3];
        dest[destOffset] = src[srcOffset] - other[otherOffset];
        dest[destOffset + 1] = _self10 - _other10;
        dest[destOffset + 2] = _self01 - _other01;
        dest[destOffset + 3] = _self11 - _other11;
        return dest;
    }

    /** {@link #sub(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer sub(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.sub_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x2OpsKernelsTypedBuffer.sub_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sub(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.sub_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x2OpsKernelsByteBuffer.sub_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long sub(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.sub_unsafe(dest, src, other);
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
    public static double[] set(double[] dest, int destOffset, double[] v, int vOffset) {
        double _v10 = v[vOffset + 1];
        double _v01 = v[vOffset + 2];
        double _v11 = v[vOffset + 3];
        dest[destOffset] = v[vOffset];
        dest[destOffset + 1] = _v10;
        dest[destOffset + 2] = _v01;
        dest[destOffset + 3] = _v11;
        return dest;
    }

    /** {@link #set(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer set(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.set_unsafe(dest, destOffset, v, vOffset);
        return Double2x2OpsKernelsTypedBuffer.set_api(dest, destOffset, v, vOffset);
    }

    /** {@link #set(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer set(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.set_unsafe(dest, destOffset, v, vOffset);
        return Double2x2OpsKernelsByteBuffer.set_api(dest, destOffset, v, vOffset);
    }

    /** {@link #set(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long set(long dest, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.set_unsafe(dest, v);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Set this matrix to the given 2x3 matrix, copying the overlapping cells and dropping the rest.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param m the storage holding the matrix to copy from
     * @param mOffset the element index in {@code m} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] setMat2x3(double[] dest, int destOffset, double[] m, int mOffset) {
        double _m10 = m[mOffset + 1];
        double _m01 = m[mOffset + 2];
        double _m11 = m[mOffset + 3];
        dest[destOffset] = m[mOffset];
        dest[destOffset + 1] = _m10;
        dest[destOffset + 2] = _m01;
        dest[destOffset + 3] = _m11;
        return dest;
    }

    /** {@link #setMat2x3(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer setMat2x3(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.setMat2x3_unsafe(dest, destOffset, m, mOffset);
        return Double2x2OpsKernelsTypedBuffer.setMat2x3_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat2x3(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer setMat2x3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.setMat2x3_unsafe(dest, destOffset, m, mOffset);
        return Double2x2OpsKernelsByteBuffer.setMat2x3_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat2x3(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long setMat2x3(long dest, long m) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.setMat2x3_unsafe(dest, m);
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
    public static double[] setMat3x3(double[] dest, int destOffset, double[] m, int mOffset) {
        double _m10 = m[mOffset + 1];
        double _m01 = m[mOffset + 3];
        double _m11 = m[mOffset + 4];
        dest[destOffset] = m[mOffset];
        dest[destOffset + 1] = _m10;
        dest[destOffset + 2] = _m01;
        dest[destOffset + 3] = _m11;
        return dest;
    }

    /** {@link #setMat3x3(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer setMat3x3(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.setMat3x3_unsafe(dest, destOffset, m, mOffset);
        return Double2x2OpsKernelsTypedBuffer.setMat3x3_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat3x3(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer setMat3x3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.setMat3x3_unsafe(dest, destOffset, m, mOffset);
        return Double2x2OpsKernelsByteBuffer.setMat3x3_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat3x3(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long setMat3x3(long dest, long m) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.setMat3x3_unsafe(dest, m);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Extend this matrix to a 2x3 matrix with a zero translation column and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] to2x3(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _self01;
        dest[destOffset + 3] = _self11;
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = 0.0;
        return dest;
    }

    /** {@link #to2x3(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer to2x3(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.to2x3_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsTypedBuffer.to2x3_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to2x3(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer to2x3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.to2x3_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsByteBuffer.to2x3_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to2x3(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long to2x3(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.to2x3_unsafe(dest, src);
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
    public static double[] to3x3(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = _self01;
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = 0.0;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 1.0;
        return dest;
    }

    /** {@link #to3x3(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer to3x3(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.to3x3_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsTypedBuffer.to3x3_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to3x3(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer to3x3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.to3x3_unsafe(dest, destOffset, src, srcOffset);
        return Double2x2OpsKernelsByteBuffer.to3x3_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to3x3(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long to3x3(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.to3x3_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Decompose this matrix into a unit lower-triangular matrix, a diagonal matrix and a unit
     * upper-triangular matrix whose product, in that order, is this matrix, storing them in
     * {@code lower}, {@code diagonal} and {@code upper} respectively.
     * <p>
     * This is Doolittle elimination without pivoting: {@code m00} is the first pivot, and a matrix
     * whose {@code m00} is zero has no such decomposition (the factors are then not finite).
     * <p>
     * Valid input: the element {@code m00} of this matrix must be non-zero.
     *
     * @param lower will hold the unit lower-triangular factor
     * @param lowerOffset the element index in {@code lower} at which the matrix starts
     * @param diagonal will hold the diagonal factor
     * @param diagonalOffset the element index in {@code diagonal} at which the matrix starts
     * @param upper will hold the unit upper-triangular factor
     * @param upperOffset the element index in {@code upper} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code lower}
     */
    public static double[] decomposeLDU(double[] lower, int lowerOffset, double[] diagonal, int diagonalOffset, double[] upper, int upperOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _rcp0 = 1.0 / _self00;
        double _sp0 = src[srcOffset + 1] * _rcp0;
        lower[lowerOffset] = 1.0;
        lower[lowerOffset + 1] = _sp0;
        lower[lowerOffset + 2] = 0.0;
        lower[lowerOffset + 3] = 1.0;
        diagonal[diagonalOffset] = _self00;
        diagonal[diagonalOffset + 1] = 0.0;
        diagonal[diagonalOffset + 2] = 0.0;
        diagonal[diagonalOffset + 3] = _self11 - _self01 * _sp0;
        upper[upperOffset] = 1.0;
        upper[upperOffset + 1] = 0.0;
        upper[upperOffset + 2] = _self01 * _rcp0;
        upper[upperOffset + 3] = 1.0;
        return lower;
    }

    /** {@link #decomposeLDU(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer decomposeLDU(java.nio.DoubleBuffer lower, int lowerOffset, java.nio.DoubleBuffer diagonal, int diagonalOffset, java.nio.DoubleBuffer upper, int upperOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && lower.isDirect() && !lower.isReadOnly() && lower.order() == java.nio.ByteOrder.nativeOrder() && diagonal.isDirect() && !diagonal.isReadOnly() && diagonal.order() == java.nio.ByteOrder.nativeOrder() && upper.isDirect() && !upper.isReadOnly() && upper.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.decomposeLDU_unsafe(lower, lowerOffset, diagonal, diagonalOffset, upper, upperOffset, src, srcOffset);
        return Double2x2OpsKernelsTypedBuffer.decomposeLDU_api(lower, lowerOffset, diagonal, diagonalOffset, upper, upperOffset, src, srcOffset);
    }

    /** {@link #decomposeLDU(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer decomposeLDU(java.nio.ByteBuffer lower, int lowerOffset, java.nio.ByteBuffer diagonal, int diagonalOffset, java.nio.ByteBuffer upper, int upperOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && lower.isDirect() && !lower.isReadOnly() && lower.order() == java.nio.ByteOrder.nativeOrder() && diagonal.isDirect() && !diagonal.isReadOnly() && diagonal.order() == java.nio.ByteOrder.nativeOrder() && upper.isDirect() && !upper.isReadOnly() && upper.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.decomposeLDU_unsafe(lower, lowerOffset, diagonal, diagonalOffset, upper, upperOffset, src, srcOffset);
        return Double2x2OpsKernelsByteBuffer.decomposeLDU_api(lower, lowerOffset, diagonal, diagonalOffset, upper, upperOffset, src, srcOffset);
    }

    /** {@link #decomposeLDU(double[], int, double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long decomposeLDU(long lower, long diagonal, long upper, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.decomposeLDU_unsafe(lower, diagonal, upper, src);
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
    public static double[] makeIdentity(double[] dest, int destOffset) {
        dest[destOffset] = 1.0;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 1.0;
        return dest;
    }

    /** {@link #makeIdentity(double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeIdentity(java.nio.DoubleBuffer dest, int destOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.makeIdentity_unsafe(dest, destOffset);
        return Double2x2OpsKernelsTypedBuffer.makeIdentity_api(dest, destOffset);
    }

    /** {@link #makeIdentity(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeIdentity(java.nio.ByteBuffer dest, int destOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.makeIdentity_unsafe(dest, destOffset);
        return Double2x2OpsKernelsByteBuffer.makeIdentity_api(dest, destOffset);
    }

    /** {@link #makeIdentity(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long makeIdentity(long dest) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.makeIdentity_unsafe(dest);
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
    public static double[] lerp(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _other10 = other[otherOffset + 1];
        double _other01 = other[otherOffset + 2];
        double _other11 = other[otherOffset + 3];
        dest[destOffset] = Math.fma(t, other[otherOffset] - _self00, _self00);
        dest[destOffset + 1] = Math.fma(t, _other10 - _self10, _self10);
        dest[destOffset + 2] = Math.fma(t, _other01 - _self01, _self01);
        dest[destOffset + 3] = Math.fma(t, _other11 - _self11, _self11);
        return dest;
    }

    /** {@link #lerp(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer lerp(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Double2x2OpsKernelsTypedBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Double2x2OpsKernelsByteBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long lerp(long dest, long src, long other, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.lerp_unsafe(dest, src, other, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Multiply this matrix by {@code right} and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
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
    public static double[] mul(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _right00 = right[rightOffset];
        double _right10 = right[rightOffset + 1];
        double _right01 = right[rightOffset + 2];
        double _right11 = right[rightOffset + 3];
        dest[destOffset] = Math.fma(_right00, _self00, _right10 * _self01);
        dest[destOffset + 1] = Math.fma(_right00, _self10, _right10 * _self11);
        dest[destOffset + 2] = Math.fma(_right01, _self00, _right11 * _self01);
        dest[destOffset + 3] = Math.fma(_right01, _self10, _right11 * _self11);
        return dest;
    }

    /** {@link #mul(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mul(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && right.isDirect() && right.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.mul_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Double2x2OpsKernelsTypedBuffer.mul_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mul(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && right.isDirect() && right.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.mul_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Double2x2OpsKernelsByteBuffer.mul_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mul(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mul(long dest, long src, long right) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.mul_unsafe(dest, src, right);
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
    public static double[] preMul(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _other00 = other[otherOffset];
        double _other10 = other[otherOffset + 1];
        double _other01 = other[otherOffset + 2];
        double _other11 = other[otherOffset + 3];
        dest[destOffset] = Math.fma(_other00, _self00, _other01 * _self10);
        dest[destOffset + 1] = Math.fma(_other10, _self00, _other11 * _self10);
        dest[destOffset + 2] = Math.fma(_other00, _self01, _other01 * _self11);
        dest[destOffset + 3] = Math.fma(_other10, _self01, _other11 * _self11);
        return dest;
    }

    /** {@link #preMul(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preMul(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.preMul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x2OpsKernelsTypedBuffer.preMul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMul(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.preMul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x2OpsKernelsByteBuffer.preMul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMul(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preMul(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.preMul_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Add {@code other} scaled by {@code weight} to this matrix and store the result in
     * {@code dest}.
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
    public static double[] addScaled(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double weight) {
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _other10 = other[otherOffset + 1];
        double _other01 = other[otherOffset + 2];
        double _other11 = other[otherOffset + 3];
        dest[destOffset] = Math.fma(weight, other[otherOffset], src[srcOffset]);
        dest[destOffset + 1] = Math.fma(weight, _other10, _self10);
        dest[destOffset + 2] = Math.fma(weight, _other01, _self01);
        dest[destOffset + 3] = Math.fma(weight, _other11, _self11);
        return dest;
    }

    /** {@link #addScaled(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer addScaled(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double weight) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return Double2x2OpsKernelsTypedBuffer.addScaled_api(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    /** {@link #addScaled(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer addScaled(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double weight) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return Double2x2OpsKernelsByteBuffer.addScaled_api(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    /** {@link #addScaled(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long addScaled(long dest, long src, long other, double weight) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.addScaled_unsafe(dest, src, other, weight);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Set this matrix to the outer product of ({@code colX}, {@code colY}) and ({@code rowX},
     * {@code rowY}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param colX the {@code x} component of the vector {@code (colX, colY)}
     * @param colY the {@code y} component of the vector {@code (colX, colY)}
     * @param rowX the {@code x} component of the vector {@code (rowX, rowY)}
     * @param rowY the {@code y} component of the vector {@code (rowX, rowY)}
     * @return {@code dest}
     */
    public static double[] makeOuterProduct(double[] dest, int destOffset, double colX, double colY, double rowX, double rowY) {
        dest[destOffset] = colX * rowX;
        dest[destOffset + 1] = colY * rowX;
        dest[destOffset + 2] = colX * rowY;
        dest[destOffset + 3] = colY * rowY;
        return dest;
    }

    /** {@link #makeOuterProduct(double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeOuterProduct(java.nio.DoubleBuffer dest, int destOffset, double colX, double colY, double rowX, double rowY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.makeOuterProduct_unsafe(dest, destOffset, colX, colY, rowX, rowY);
        return Double2x2OpsKernelsTypedBuffer.makeOuterProduct_api(dest, destOffset, colX, colY, rowX, rowY);
    }

    /** {@link #makeOuterProduct(double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeOuterProduct(java.nio.ByteBuffer dest, int destOffset, double colX, double colY, double rowX, double rowY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.makeOuterProduct_unsafe(dest, destOffset, colX, colY, rowX, rowY);
        return Double2x2OpsKernelsByteBuffer.makeOuterProduct_api(dest, destOffset, colX, colY, rowX, rowY);
    }

    /** {@link #makeOuterProduct(double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long makeOuterProduct(long dest, double colX, double colY, double rowX, double rowY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.makeOuterProduct_unsafe(dest, colX, colY, rowX, rowY);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Set this matrix to the outer product of {@code col} and {@code row}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param col the storage holding the column vector (left operand)
     * @param colOffset the element index in {@code col} at which the vector starts
     * @param row the storage holding the row vector (right operand)
     * @param rowOffset the element index in {@code row} at which the vector starts
     * @return {@code dest}
     */
    public static double[] makeOuterProduct(double[] dest, int destOffset, double[] col, int colOffset, double[] row, int rowOffset) {
        double _colx = col[colOffset];
        double _coly = col[colOffset + 1];
        double _rowx = row[rowOffset];
        double _rowy = row[rowOffset + 1];
        dest[destOffset] = _colx * _rowx;
        dest[destOffset + 1] = _coly * _rowx;
        dest[destOffset + 2] = _colx * _rowy;
        dest[destOffset + 3] = _coly * _rowy;
        return dest;
    }

    /** {@link #makeOuterProduct(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeOuterProduct(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer col, int colOffset, java.nio.DoubleBuffer row, int rowOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && col.isDirect() && col.order() == java.nio.ByteOrder.nativeOrder() && row.isDirect() && row.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.makeOuterProduct_unsafe(dest, destOffset, col, colOffset, row, rowOffset);
        return Double2x2OpsKernelsTypedBuffer.makeOuterProduct_api(dest, destOffset, col, colOffset, row, rowOffset);
    }

    /** {@link #makeOuterProduct(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeOuterProduct(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer col, int colOffset, java.nio.ByteBuffer row, int rowOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && col.isDirect() && col.order() == java.nio.ByteOrder.nativeOrder() && row.isDirect() && row.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.makeOuterProduct_unsafe(dest, destOffset, col, colOffset, row, rowOffset);
        return Double2x2OpsKernelsByteBuffer.makeOuterProduct_api(dest, destOffset, col, colOffset, row, rowOffset);
    }

    /** {@link #makeOuterProduct(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long makeOuterProduct(long dest, long col, long row) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.makeOuterProduct_unsafe(dest, col, row);
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
    public static double[] makeRotation(double[] dest, int destOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = _t1;
        dest[destOffset + 1] = _t0;
        dest[destOffset + 2] = -_t0;
        dest[destOffset + 3] = _t1;
        return dest;
    }

    /** {@link #makeRotation(double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeRotation(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.makeRotation_unsafe(dest, destOffset, angle);
        return Double2x2OpsKernelsTypedBuffer.makeRotation_api(dest, destOffset, angle);
    }

    /** {@link #makeRotation(double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotation(java.nio.ByteBuffer dest, int destOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.makeRotation_unsafe(dest, destOffset, angle);
        return Double2x2OpsKernelsByteBuffer.makeRotation_api(dest, destOffset, angle);
    }

    /** {@link #makeRotation(double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long makeRotation(long dest, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.makeRotation_unsafe(dest, angle);
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
    public static double[] makeScaling(double[] dest, int destOffset, double vX, double vY) {
        dest[destOffset] = vX;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = vY;
        return dest;
    }

    /** {@link #makeScaling(double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeScaling(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.makeScaling_unsafe(dest, destOffset, vX, vY);
        return Double2x2OpsKernelsTypedBuffer.makeScaling_api(dest, destOffset, vX, vY);
    }

    /** {@link #makeScaling(double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeScaling(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.makeScaling_unsafe(dest, destOffset, vX, vY);
        return Double2x2OpsKernelsByteBuffer.makeScaling_api(dest, destOffset, vX, vY);
    }

    /** {@link #makeScaling(double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long makeScaling(long dest, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.makeScaling_unsafe(dest, vX, vY);
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
    public static double[] makeScaling(double[] dest, int destOffset, double[] v, int vOffset) {
        double _vy = v[vOffset + 1];
        dest[destOffset] = v[vOffset];
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = _vy;
        return dest;
    }

    /** {@link #makeScaling(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeScaling(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.makeScaling_unsafe(dest, destOffset, v, vOffset);
        return Double2x2OpsKernelsTypedBuffer.makeScaling_api(dest, destOffset, v, vOffset);
    }

    /** {@link #makeScaling(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeScaling(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.makeScaling_unsafe(dest, destOffset, v, vOffset);
        return Double2x2OpsKernelsByteBuffer.makeScaling_api(dest, destOffset, v, vOffset);
    }

    /** {@link #makeScaling(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long makeScaling(long dest, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.makeScaling_unsafe(dest, v);
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
    public static double[] makeScaling(double[] dest, int destOffset, double s) {
        dest[destOffset] = s;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = s;
        return dest;
    }

    /** {@link #makeScaling(double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeScaling(java.nio.DoubleBuffer dest, int destOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.makeScaling_unsafe(dest, destOffset, s);
        return Double2x2OpsKernelsTypedBuffer.makeScaling_api(dest, destOffset, s);
    }

    /** {@link #makeScaling(double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeScaling(java.nio.ByteBuffer dest, int destOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.makeScaling_unsafe(dest, destOffset, s);
        return Double2x2OpsKernelsByteBuffer.makeScaling_api(dest, destOffset, s);
    }

    /** {@link #makeScaling(double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long makeScaling(long dest, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.makeScaling_unsafe(dest, s);
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
    public static double[] preRotate(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = Math.fma(_self00, _t1, -(_self10 * _t0));
        dest[destOffset + 1] = Math.fma(_self00, _t0, _self10 * _t1);
        dest[destOffset + 2] = Math.fma(_self01, _t1, -(_self11 * _t0));
        dest[destOffset + 3] = Math.fma(_self01, _t0, _self11 * _t1);
        return dest;
    }

    /** {@link #preRotate(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preRotate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.preRotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double2x2OpsKernelsTypedBuffer.preRotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotate(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preRotate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.preRotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double2x2OpsKernelsByteBuffer.preRotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotate(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preRotate(long dest, long src, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.preRotate_unsafe(dest, src, angle);
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
    public static double[] preScale(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY) {
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        dest[destOffset] = src[srcOffset] * vX;
        dest[destOffset + 1] = _self10 * vY;
        dest[destOffset + 2] = _self01 * vX;
        dest[destOffset + 3] = _self11 * vY;
        return dest;
    }

    /** {@link #preScale(double[], int, double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preScale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x2OpsKernelsTypedBuffer.preScale_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #preScale(double[], int, double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x2OpsKernelsByteBuffer.preScale_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #preScale(double[], int, double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preScale(long dest, long src, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.preScale_unsafe(dest, src, vX, vY);
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
    public static double[] preScale(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        dest[destOffset] = src[srcOffset] * _vx;
        dest[destOffset + 1] = _self10 * _vy;
        dest[destOffset + 2] = _self01 * _vx;
        dest[destOffset + 3] = _self11 * _vy;
        return dest;
    }

    /** {@link #preScale(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preScale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x2OpsKernelsTypedBuffer.preScale_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #preScale(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x2OpsKernelsByteBuffer.preScale_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #preScale(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preScale(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.preScale_unsafe(dest, src, v);
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
    public static double[] preScale(double[] dest, int destOffset, double[] src, int srcOffset, double s) {
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        dest[destOffset] = s * src[srcOffset];
        dest[destOffset + 1] = s * _self10;
        dest[destOffset + 2] = s * _self01;
        dest[destOffset + 3] = s * _self11;
        return dest;
    }

    /** {@link #preScale(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preScale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, s);
        return Double2x2OpsKernelsTypedBuffer.preScale_api(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #preScale(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, s);
        return Double2x2OpsKernelsByteBuffer.preScale_api(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #preScale(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preScale(long dest, long src, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.preScale_unsafe(dest, src, s);
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
    public static double[] rotate(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = Math.fma(_self00, _t1, _self01 * _t0);
        dest[destOffset + 1] = Math.fma(_self10, _t1, _self11 * _t0);
        dest[destOffset + 2] = Math.fma(_self01, _t1, -(_self00 * _t0));
        dest[destOffset + 3] = Math.fma(_self11, _t1, -(_self10 * _t0));
        return dest;
    }

    /** {@link #rotate(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double2x2OpsKernelsTypedBuffer.rotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotate(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double2x2OpsKernelsByteBuffer.rotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotate(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotate(long dest, long src, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.rotate_unsafe(dest, src, angle);
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
    public static double[] scale(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY) {
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        dest[destOffset] = src[srcOffset] * vX;
        dest[destOffset + 1] = _self10 * vX;
        dest[destOffset + 2] = _self01 * vY;
        dest[destOffset + 3] = _self11 * vY;
        return dest;
    }

    /** {@link #scale(double[], int, double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer scale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.scale_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x2OpsKernelsTypedBuffer.scale_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #scale(double[], int, double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.scale_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x2OpsKernelsByteBuffer.scale_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #scale(double[], int, double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long scale(long dest, long src, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.scale_unsafe(dest, src, vX, vY);
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
    public static double[] scale(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        dest[destOffset] = src[srcOffset] * _vx;
        dest[destOffset + 1] = _self10 * _vx;
        dest[destOffset + 2] = _self01 * _vy;
        dest[destOffset + 3] = _self11 * _vy;
        return dest;
    }

    /** {@link #scale(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer scale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.scale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x2OpsKernelsTypedBuffer.scale_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #scale(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.scale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x2OpsKernelsByteBuffer.scale_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #scale(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long scale(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.scale_unsafe(dest, src, v);
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
    public static double[] scale(double[] dest, int destOffset, double[] src, int srcOffset, double s) {
        return Double2x2Ops.preScale(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #scale(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer scale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, s);
        return Double2x2OpsKernelsTypedBuffer.preScale_api(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #scale(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, s);
        return Double2x2OpsKernelsByteBuffer.preScale_api(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #scale(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long scale(long dest, long src, double s) {
        return Double2x2Ops.preScale(dest, src, s);
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
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return {@code dest}
     */
    public static double[] mulVec2(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY) {
        double _self10 = src[srcOffset + 1];
        double _self11 = src[srcOffset + 3];
        dest[destOffset] = Math.fma(src[srcOffset], vX, src[srcOffset + 2] * vY);
        dest[destOffset + 1] = Math.fma(_self10, vX, _self11 * vY);
        return dest;
    }

    /** {@link #mulVec2(double[], int, double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mulVec2(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.mulVec2_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x2OpsKernelsTypedBuffer.mulVec2_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #mulVec2(double[], int, double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mulVec2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.mulVec2_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x2OpsKernelsByteBuffer.mulVec2_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #mulVec2(double[], int, double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mulVec2(long dest, long src, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.mulVec2_unsafe(dest, src, vX, vY);
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
    public static double[] mulVec2(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _self10 = src[srcOffset + 1];
        double _self11 = src[srcOffset + 3];
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        dest[destOffset] = Math.fma(src[srcOffset], _vx, src[srcOffset + 2] * _vy);
        dest[destOffset + 1] = Math.fma(_self10, _vx, _self11 * _vy);
        return dest;
    }

    /** {@link #mulVec2(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mulVec2(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsTypedBuffer.mulVec2_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x2OpsKernelsTypedBuffer.mulVec2_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #mulVec2(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mulVec2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x2OpsKernelsByteBuffer.mulVec2_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x2OpsKernelsByteBuffer.mulVec2_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #mulVec2(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mulVec2(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x2OpsKernelsAddress.mulVec2_unsafe(dest, src, v);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }


    /**
     * Copy one Double2x2 (4 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset) {
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x2 values ({@code count * 4} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 536870911 ? -1 : count * 4), src.length);
            java.util.Objects.checkFromIndexSize(destOffset, (count > 536870911 ? -1 : count * 4), dest.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.DOUBLE_ARRAY_BASE + srcOffset * 8L, dest, UnsafeCopy.DOUBLE_ARRAY_BASE + destOffset * 8L, count * 32L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src[srcOffset + _i];
        return dest;
    }

    /**
     * Copy one Double2x2 (4 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x2 values ({@code count * 4} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 536870911 ? -1 : count * 4), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, dest, UnsafeCopy.DOUBLE_ARRAY_BASE + destOffset * 8L, count * 32L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src.get(srcOffset + _i);
        return dest;
    }

    /**
     * Copy one Double2x2 (4 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x2 values ({@code count * 4} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 536870911 ? -1 : count * 4), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, dest, UnsafeCopy.DOUBLE_ARRAY_BASE + destOffset * 8L, count * 32L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src.getDouble(srcOffset + _i * 8);
        return dest;
    }

    /**
     * Copy one Double2x2 (4 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static double[] copy(double[] dest, int destOffset, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(destOffset, 4, dest.length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest, UnsafeCopy.DOUBLE_ARRAY_BASE + destOffset * 8L, 32L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x2 values ({@code count * 4} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static double[] copy(double[] dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 536870911 ? -1 : count * 4), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest, UnsafeCopy.DOUBLE_ARRAY_BASE + destOffset * 8L, count * 32L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Double2x2 (4 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset) {
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x2 values ({@code count * 4} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 536870911 ? -1 : count * 4), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.DOUBLE_ARRAY_BASE + srcOffset * 8L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, count * 32L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src[srcOffset + _i]);
        return dest;
    }

    /**
     * Copy one Double2x2 (4 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x2 values ({@code count * 4} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, count * 32L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src.get(srcOffset + _i));
        return dest;
    }

    /**
     * Copy one Double2x2 (4 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x2 values ({@code count * 4} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, count * 32L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src.getDouble(srcOffset + _i * 8));
        return dest;
    }

    /**
     * Copy one Double2x2 (4 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, 32L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.hasArray() && dest.order() == java.nio.ByteOrder.nativeOrder() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            java.util.Objects.checkFromIndexSize(dest.arrayOffset() + destOffset, 4, dest.array().length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest.array(), UnsafeCopy.DOUBLE_ARRAY_BASE + (dest.arrayOffset() + destOffset) * 8L, 32L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            for (int _i = 0; _i < 4; _i++)
                dest.put(destOffset + _i, UnsafeOpsHolder.U.getDouble(src + _i * 8L));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x2 values ({@code count * 4} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, count * 32L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.hasArray() && dest.order() == java.nio.ByteOrder.nativeOrder() && destOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && destOffset <= dest.limit() - (count > 536870911 ? -1 : count * 4)) {
            java.util.Objects.checkFromIndexSize(dest.arrayOffset() + destOffset, (count > 536870911 ? -1 : count * 4), dest.array().length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest.array(), UnsafeCopy.DOUBLE_ARRAY_BASE + (dest.arrayOffset() + destOffset) * 8L, count * 32L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            if (count < 0) return dest;
            if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
            int n = count * 4;
            for (int _i = 0; _i < n; _i++)
                dest.put(destOffset + _i, UnsafeOpsHolder.U.getDouble(src + _i * 8L));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Double2x2 (4 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, double[] src, int srcOffset) {
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x2 values ({@code count * 4} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, double[] src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 536870911 ? -1 : count * 4), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.DOUBLE_ARRAY_BASE + srcOffset * 8L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 32L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.putDouble(destOffset + _i * 8, src[srcOffset + _i]);
        return dest;
    }

    /**
     * Copy one Double2x2 (4 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x2 values ({@code count * 4} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 32L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.putDouble(destOffset + _i * 8, src.get(srcOffset + _i));
        return dest;
    }

    /**
     * Copy one Double2x2 (4 doubles) from {@code src}
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
     * Bulk-copy {@code count} consecutive Double2x2 values ({@code count * 4} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 32L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.putDouble(destOffset + _i * 8, src.getDouble(srcOffset + _i * 8));
        return dest;
    }

    /**
     * Copy one Double2x2 (4 doubles) from {@code src}
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
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, 32L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.hasArray() && dest.order() == java.nio.ByteOrder.nativeOrder() && destOffset >= 0 && destOffset <= dest.limit() - 32) {
            java.util.Objects.checkFromIndexSize(dest.arrayOffset() + destOffset, 32, dest.array().length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest.array(), UnsafeCopy.BYTE_ARRAY_BASE + (dest.arrayOffset() + destOffset), 32L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            for (int _i = 0; _i < 4; _i++)
                dest.putDouble(destOffset + _i * 8, UnsafeOpsHolder.U.getDouble(src + _i * 8L));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x2 values ({@code count * 4} doubles) from {@code src}
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
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 32L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.hasArray() && dest.order() == java.nio.ByteOrder.nativeOrder() && destOffset >= 0 && (count > 67108863 ? -1 : count * 32) >= 0 && destOffset <= dest.limit() - (count > 67108863 ? -1 : count * 32)) {
            java.util.Objects.checkFromIndexSize(dest.arrayOffset() + destOffset, (count > 67108863 ? -1 : count * 32), dest.array().length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest.array(), UnsafeCopy.BYTE_ARRAY_BASE + (dest.arrayOffset() + destOffset), count * 32L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            if (count < 0) return dest;
            if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
            int n = count * 4;
            for (int _i = 0; _i < n; _i++)
                dest.putDouble(destOffset + _i * 8, UnsafeOpsHolder.U.getDouble(src + _i * 8L));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Double2x2 (4 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, double[] src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(srcOffset, 4, src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.DOUBLE_ARRAY_BASE + srcOffset * 8L, null, dest, 32L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x2 values ({@code count * 4} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, double[] src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 536870911 ? -1 : count * 4), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.DOUBLE_ARRAY_BASE + srcOffset * 8L, null, dest, count * 32L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Double2x2 (4 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, null, dest, 32L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.hasArray() && src.order() == java.nio.ByteOrder.nativeOrder() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            java.util.Objects.checkFromIndexSize(src.arrayOffset() + srcOffset, 4, src.array().length);
            UnsafeOpsHolder.U.copyMemory(src.array(), UnsafeCopy.DOUBLE_ARRAY_BASE + (src.arrayOffset() + srcOffset) * 8L, null, dest, 32L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            for (int _i = 0; _i < 4; _i++)
                UnsafeOpsHolder.U.putDouble(dest + _i * 8L, src.get(srcOffset + _i));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x2 values ({@code count * 4} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, null, dest, count * 32L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.hasArray() && src.order() == java.nio.ByteOrder.nativeOrder() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
            java.util.Objects.checkFromIndexSize(src.arrayOffset() + srcOffset, (count > 536870911 ? -1 : count * 4), src.array().length);
            UnsafeOpsHolder.U.copyMemory(src.array(), UnsafeCopy.DOUBLE_ARRAY_BASE + (src.arrayOffset() + srcOffset) * 8L, null, dest, count * 32L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            if (count < 0) return dest;
            if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
            int n = count * 4;
            for (int _i = 0; _i < n; _i++)
                UnsafeOpsHolder.U.putDouble(dest + _i * 8L, src.get(srcOffset + _i));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Double2x2 (4 doubles) from {@code src}
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
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, dest, 32L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.hasArray() && src.order() == java.nio.ByteOrder.nativeOrder() && srcOffset >= 0 && srcOffset <= src.limit() - 32) {
            java.util.Objects.checkFromIndexSize(src.arrayOffset() + srcOffset, 32, src.array().length);
            UnsafeOpsHolder.U.copyMemory(src.array(), UnsafeCopy.BYTE_ARRAY_BASE + (src.arrayOffset() + srcOffset), null, dest, 32L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            for (int _i = 0; _i < 4; _i++)
                UnsafeOpsHolder.U.putDouble(dest + _i * 8L, src.getDouble(srcOffset + _i * 8));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x2 values ({@code count * 4} doubles) from {@code src}
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
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, dest, count * 32L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.hasArray() && src.order() == java.nio.ByteOrder.nativeOrder() && srcOffset >= 0 && (count > 67108863 ? -1 : count * 32) >= 0 && srcOffset <= src.limit() - (count > 67108863 ? -1 : count * 32)) {
            java.util.Objects.checkFromIndexSize(src.arrayOffset() + srcOffset, (count > 67108863 ? -1 : count * 32), src.array().length);
            UnsafeOpsHolder.U.copyMemory(src.array(), UnsafeCopy.BYTE_ARRAY_BASE + (src.arrayOffset() + srcOffset), null, dest, count * 32L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            if (count < 0) return dest;
            if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
            int n = count * 4;
            for (int _i = 0; _i < n; _i++)
                UnsafeOpsHolder.U.putDouble(dest + _i * 8L, src.getDouble(srcOffset + _i * 8));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Double2x2 (4 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, dest, 32L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x2 values ({@code count * 4} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, dest, count * 32L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }
}
