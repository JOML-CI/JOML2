// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.ops;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.kernels.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Static, allocation-free operations on raw storage holding a {@link Double2x3}.
 *
 * <p>Each method takes one or more buffers ({@code double[]},
 * {@link java.nio.DoubleBuffer}, {@link java.nio.ByteBuffer}, or
 * {@link java.lang.foreign.MemorySegment}) plus an element/byte offset and operates
 * directly on that storage. No {@link Double2x3} instance is allocated;
 * the array overloads and the Unsafe-backed buffer fast paths never allocate at all.
 * The portable fallback taken for heap {@code ByteBuffer}s, read-only buffers, and the
 * API backend may wrap buffers in lightweight {@link java.lang.foreign.MemorySegment} views.</p>
 *
 * <p>NIO buffers in native byte order take the fast paths; any other byte order
 * (the {@code ByteBuffer} default is big-endian) is honoured through the slower
 * API path. Writing into a read-only buffer throws
 * {@link java.nio.ReadOnlyBufferException}, as NIO's own {@code put} does.</p>
 *
 * <p>With the UNSAFE backend, offsets into direct buffers and native segments are not
 * bounds-checked and segment liveness / thread confinement is not verified; the API
 * backend performs the standard checks. Heap arrays are bounds-checked on every backend
 * ({@link IndexOutOfBoundsException}). The UNSAFE backend uses {@code sun.misc.Unsafe}; on
 * JDK 23+ (JEP 471) run with {@code --sun-misc-unsafe-memory-access=allow} or select
 * {@code -Djoml.storeLoadBackend=api}.</p>
 *
 * <p>Edge cases, per backend: a read-only {@code dest} buffer or segment never takes the
 * Unsafe path and is rejected by the API path ({@link IllegalArgumentException} from the
 * read-only segment view, or {@link java.nio.ReadOnlyBufferException} from a buffer
 * {@code put}). Buffer offsets are absolute indices counted from index 0, regardless of
 * the buffer's position; the API path addresses a buffer through a segment view that ends at
 * its {@code limit}, so an access beyond the limit throws {@link IndexOutOfBoundsException},
 * whereas the UNSAFE path addresses a direct buffer by its base address and ignores position,
 * limit and capacity. A negative {@code count} performs no reads or writes on the API and
 * SIMD paths, except through the raw {@code long}-address {@code copy} overloads, whose API path
 * slices {@code count} elements off the address first and throws {@link IllegalArgumentException}
 * for a negative length; the UNSAFE {@code copy} fast path rejects it ({@link IndexOutOfBoundsException}
 * for an array end, {@link IllegalArgumentException} from {@code Unsafe.copyMemory} otherwise).</p>
 *
 * <p>All buffer parameters in a single call must use the same storage backing,
 * except the {@code copy} methods, which translate between any two backings.
 * Element layout is column-major: what {@code storeCM}/{@code loadCM} and the bare
 * {@code store}/{@code load} of Double2x3 write and read.</p>
 *
 * <p>Configuration freezing: this class holds no static state of its own, so a call freezes only
 * the flags its overload reads. Every non-bulk buffer, segment and raw-address overload - and the
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
 * <p>Each method summary below is the one the {@link Double2x3} API carries, so
 * the two can never describe the same operation differently: "this matrix" there is the
 * matrix held in {@code src} at {@code srcOffset}, and the result is written to
 * {@code dest} at {@code destOffset}. The full text sits on the {@code double[]} overload of
 * each method; the other storage overloads point at it, differing from it in
 * storage alone.</p>
 */
public final class Double2x3Ops {
    private Double2x3Ops() {}

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
    public static double[] getColumn(double[] dest, int destOffset, double[] src, int srcOffset, int col) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _idxSw0;
        double _idxSw1;
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

    /** {@link #getColumn(double[], int, double[], int, int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer getColumn(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int col) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.getColumn_unsafe(dest, destOffset, src, srcOffset, col);
        return Double2x3OpsKernelsTypedBuffer.getColumn_api(dest, destOffset, src, srcOffset, col);
    }

    /** {@link #getColumn(double[], int, double[], int, int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getColumn(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.getColumn_unsafe(dest, destOffset, src, srcOffset, col);
        return Double2x3OpsKernelsByteBuffer.getColumn_api(dest, destOffset, src, srcOffset, col);
    }

    /** {@link #getColumn(double[], int, double[], int, int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getColumn(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int col) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            getColumn(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), col);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.getColumn_unsafe(dest, destOffset, src, srcOffset, col);
        return Double2x3OpsKernelsSegment.getColumn_api(dest, destOffset, src, srcOffset, col);
    }

    /** {@link #getColumn(double[], int, double[], int, int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getColumn(long dest, long src, int col) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.getColumn_unsafe(dest, src, col);
        getColumn(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, col);
        return dest;
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.getRotationAngle_unsafe(src, srcOffset);
        return Double2x3OpsKernelsTypedBuffer.getRotationAngle_api(src, srcOffset);
    }

    /** {@link #getRotationAngle(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double getRotationAngle(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.getRotationAngle_unsafe(src, srcOffset);
        return Double2x3OpsKernelsByteBuffer.getRotationAngle_api(src, srcOffset);
    }

    /** {@link #getRotationAngle(double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double getRotationAngle(java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _srcArray;
        if ((_srcArray = heapDoubles(src, srcOffset)) != null) {
            return getRotationAngle(_srcArray, heapIndex(src, srcOffset, 3));
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Double2x3OpsKernelsSegment.getRotationAngle_unsafe(src, srcOffset);
        return Double2x3OpsKernelsSegment.getRotationAngle_api(src, srcOffset);
    }

    /** {@link #getRotationAngle(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static double getRotationAngle(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.getRotationAngle_unsafe(src);
        return getRotationAngle(VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L);
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
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
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

    /** {@link #getRow(double[], int, double[], int, int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer getRow(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int row) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.getRow_unsafe(dest, destOffset, src, srcOffset, row);
        return Double2x3OpsKernelsTypedBuffer.getRow_api(dest, destOffset, src, srcOffset, row);
    }

    /** {@link #getRow(double[], int, double[], int, int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getRow(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.getRow_unsafe(dest, destOffset, src, srcOffset, row);
        return Double2x3OpsKernelsByteBuffer.getRow_api(dest, destOffset, src, srcOffset, row);
    }

    /** {@link #getRow(double[], int, double[], int, int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getRow(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int row) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            getRow(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), row);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.getRow_unsafe(dest, destOffset, src, srcOffset, row);
        return Double2x3OpsKernelsSegment.getRow_api(dest, destOffset, src, srcOffset, row);
    }

    /** {@link #getRow(double[], int, double[], int, int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getRow(long dest, long src, int row) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.getRow_unsafe(dest, src, row);
        getRow(VirtualMemoryHolder.virtualMemory().asSlice(dest, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, row);
        return dest;
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
    public static double[] getTranslation(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self12 = src[srcOffset + 5];
        dest[destOffset] = src[srcOffset + 4];
        dest[destOffset + 1] = _self12;
        return dest;
    }

    /** {@link #getTranslation(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer getTranslation(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.getTranslation_unsafe(dest, destOffset, src, srcOffset);
        return Double2x3OpsKernelsTypedBuffer.getTranslation_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getTranslation(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getTranslation(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.getTranslation_unsafe(dest, destOffset, src, srcOffset);
        return Double2x3OpsKernelsByteBuffer.getTranslation_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getTranslation(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getTranslation(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            getTranslation(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.getTranslation_unsafe(dest, destOffset, src, srcOffset);
        return Double2x3OpsKernelsSegment.getTranslation_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getTranslation(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getTranslation(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.getTranslation_unsafe(dest, src);
        getTranslation(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L);
        return dest;
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
    public static double determinant(double[] src, int srcOffset) {
        return Math.fma(src[srcOffset], src[srcOffset + 3], -(src[srcOffset + 2] * src[srcOffset + 1]));
    }

    /** {@link #determinant(double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double determinant(java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.determinant_unsafe(src, srcOffset);
        return Double2x3OpsKernelsTypedBuffer.determinant_api(src, srcOffset);
    }

    /** {@link #determinant(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double determinant(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.determinant_unsafe(src, srcOffset);
        return Double2x3OpsKernelsByteBuffer.determinant_api(src, srcOffset);
    }

    /** {@link #determinant(double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double determinant(java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _srcArray;
        if ((_srcArray = heapDoubles(src, srcOffset)) != null) {
            return determinant(_srcArray, heapIndex(src, srcOffset, 3));
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Double2x3OpsKernelsSegment.determinant_unsafe(src, srcOffset);
        return Double2x3OpsKernelsSegment.determinant_api(src, srcOffset);
    }

    /** {@link #determinant(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static double determinant(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.determinant_unsafe(src);
        return determinant(VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L);
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
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        return java.lang.Math.sqrt(Math.fma(_self12, _self12, Math.fma(_self11, _self11, Math.fma(_self10, _self10, Math.fma(_self02, _self02, Math.fma(_self00, _self00, _self01 * _self01))))));
    }

    /** {@link #frobeniusNorm(double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double frobeniusNorm(java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.frobeniusNorm_unsafe(src, srcOffset);
        return Double2x3OpsKernelsTypedBuffer.frobeniusNorm_api(src, srcOffset);
    }

    /** {@link #frobeniusNorm(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double frobeniusNorm(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.frobeniusNorm_unsafe(src, srcOffset);
        return Double2x3OpsKernelsByteBuffer.frobeniusNorm_api(src, srcOffset);
    }

    /** {@link #frobeniusNorm(double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double frobeniusNorm(java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _srcArray;
        if ((_srcArray = heapDoubles(src, srcOffset)) != null) {
            return frobeniusNorm(_srcArray, heapIndex(src, srcOffset, 3));
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Double2x3OpsKernelsSegment.frobeniusNorm_unsafe(src, srcOffset);
        return Double2x3OpsKernelsSegment.frobeniusNorm_api(src, srcOffset);
    }

    /** {@link #frobeniusNorm(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static double frobeniusNorm(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.frobeniusNorm_unsafe(src);
        return frobeniusNorm(VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L);
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
    public static double[] invert(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _t3 = Math.fma(_self00, _self11, -(_self01 * _self10));
        if (!(java.lang.Math.abs(_t3) > 2.2250738585072014E-308 && java.lang.Math.abs(_t3) < 4.49423283715579E307)) return Double2x3OpsKernelsArray.invert_degenerate(dest, destOffset, src, srcOffset);
        double _t3_inv = 1.0 / _t3;
        dest[destOffset] = _self11 * _t3_inv;
        dest[destOffset + 1] = -(_self10 * _t3_inv);
        dest[destOffset + 2] = -(_self01 * _t3_inv);
        dest[destOffset + 3] = _self00 * _t3_inv;
        dest[destOffset + 4] = -(Math.fma(_self02, _self11, -(_self01 * _self12)) * _t3_inv);
        dest[destOffset + 5] = -(Math.fma(_self00, _self12, -(_self02 * _self10)) * _t3_inv);
        return dest;
    }

    /** {@link #invert(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer invert(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.invert_unsafe(dest, destOffset, src, srcOffset);
        return Double2x3OpsKernelsTypedBuffer.invert_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invert(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invert(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.invert_unsafe(dest, destOffset, src, srcOffset);
        return Double2x3OpsKernelsByteBuffer.invert_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invert(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment invert(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            invert(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.invert_unsafe(dest, destOffset, src, srcOffset);
        return Double2x3OpsKernelsSegment.invert_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invert(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long invert(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.invert_unsafe(dest, src);
        invert(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L);
        return dest;
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
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _other00 = other[otherOffset];
        double _other10 = other[otherOffset + 1];
        double _other01 = other[otherOffset + 2];
        double _other11 = other[otherOffset + 3];
        double _other02 = other[otherOffset + 4];
        double _other12 = other[otherOffset + 5];
        double _t6 = Math.fma(_other01, _self10, _other11 * _self11);
        double _t7 = Math.fma(_other00, _self00, _other10 * _self01);
        double _t8 = Math.fma(_other00, _self10, _other10 * _self11);
        double _t9 = Math.fma(_other01, _self00, _other11 * _self01);
        double _t15 = Math.fma(_t7, _t6, -(_t8 * _t9));
        if (!(java.lang.Math.abs(_t15) > 2.2250738585072014E-308 && java.lang.Math.abs(_t15) < 4.49423283715579E307)) return Double2x3OpsKernelsArray.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        double _t15_inv = 1.0 / _t15;
        dest[destOffset] = _t6 * _t15_inv;
        dest[destOffset + 1] = -(_t8 * _t15_inv);
        dest[destOffset + 2] = -(_t9 * _t15_inv);
        dest[destOffset + 3] = _t7 * _t15_inv;
        return invertProduct_sf4891159_1(dest, destOffset, _t6, _t7, _t8, _t9, Math.fma(_other02, _self00, Math.fma(_other12, _self01, _self02)), Math.fma(_other02, _self10, Math.fma(_other12, _self11, _self12)), _t15_inv);
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private static double[] invertProduct_sf4891159_1(double[] dest, int destOffset, double _t6, double _t7, double _t8, double _t9, double _t10, double _t11, double _t15_inv) {
        dest[destOffset + 4] = -(Math.fma(_t10, _t6, -(_t11 * _t9)) * _t15_inv);
        dest[destOffset + 5] = -(Math.fma(_t11, _t7, -(_t10 * _t8)) * _t15_inv);
        return dest;
    }

    /** {@link #invertProduct(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer invertProduct(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.invertProduct_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x3OpsKernelsTypedBuffer.invertProduct_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #invertProduct(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invertProduct(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.invertProduct_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x3OpsKernelsByteBuffer.invertProduct_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #invertProduct(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment invertProduct(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double[] _destArray, _srcArray, _otherArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_otherArray = heapDoubles(other, otherOffset)) != null) {
            invertProduct(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _otherArray, heapIndex(other, otherOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double2x3OpsKernelsSegment.invertProduct_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x3OpsKernelsSegment.invertProduct_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #invertProduct(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long invertProduct(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.invertProduct_unsafe(dest, src, other);
        invertProduct(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 48L), 0L);
        return dest;
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
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self01;
        dest[destOffset + 2] = _self02;
        dest[destOffset + 3] = _self10;
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = _self12;
        return dest;
    }

    /** {@link #transpose(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer transpose(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.transpose_unsafe(dest, destOffset, src, srcOffset);
        return Double2x3OpsKernelsTypedBuffer.transpose_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #transpose(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer transpose(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.transpose_unsafe(dest, destOffset, src, srcOffset);
        return Double2x3OpsKernelsByteBuffer.transpose_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #transpose(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment transpose(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            transpose(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.transpose_unsafe(dest, destOffset, src, srcOffset);
        return Double2x3OpsKernelsSegment.transpose_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #transpose(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long transpose(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.transpose_unsafe(dest, src);
        transpose(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L);
        return dest;
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
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself1 = src[srcOffset + _lo + 1];
            double _eother1 = other[otherOffset + _lo + 1];
            dest[destOffset + _lo] = other[otherOffset + _lo] + src[srcOffset + _lo];
            dest[destOffset + _lo + 1] = _eother1 + _eself1;
        }
        return dest;
    }

    /** {@link #add(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer add(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.add_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x3OpsKernelsTypedBuffer.add_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer add(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.add_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x3OpsKernelsByteBuffer.add_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment add(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double[] _destArray, _srcArray, _otherArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_otherArray = heapDoubles(other, otherOffset)) != null) {
            add(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _otherArray, heapIndex(other, otherOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double2x3OpsKernelsSegment.add_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x3OpsKernelsSegment.add_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long add(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.add_unsafe(dest, src, other);
        add(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 48L), 0L);
        return dest;
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
    public static double[] mul(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself1 = src[srcOffset + _lo + 1];
            dest[destOffset + _lo] = scalar * src[srcOffset + _lo];
            dest[destOffset + _lo + 1] = scalar * _eself1;
        }
        return dest;
    }

    /** {@link #mul(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mul(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.mul_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Double2x3OpsKernelsTypedBuffer.mul_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.mul_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Double2x3OpsKernelsByteBuffer.mul_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            mul(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), scalar);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.mul_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Double2x3OpsKernelsSegment.mul_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mul(long dest, long src, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.mul_unsafe(dest, src, scalar);
        mul(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, scalar);
        return dest;
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
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself1 = src[srcOffset + _lo + 1];
            dest[destOffset + _lo] = -src[srcOffset + _lo];
            dest[destOffset + _lo + 1] = -_eself1;
        }
        return dest;
    }

    /** {@link #negate(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer negate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.negate_unsafe(dest, destOffset, src, srcOffset);
        return Double2x3OpsKernelsTypedBuffer.negate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer negate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.negate_unsafe(dest, destOffset, src, srcOffset);
        return Double2x3OpsKernelsByteBuffer.negate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment negate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            negate(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.negate_unsafe(dest, destOffset, src, srcOffset);
        return Double2x3OpsKernelsSegment.negate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long negate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.negate_unsafe(dest, src);
        negate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L);
        return dest;
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
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself1 = src[srcOffset + _lo + 1];
            double _eother1 = other[otherOffset + _lo + 1];
            dest[destOffset + _lo] = src[srcOffset + _lo] - other[otherOffset + _lo];
            dest[destOffset + _lo + 1] = _eself1 - _eother1;
        }
        return dest;
    }

    /** {@link #sub(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer sub(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.sub_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x3OpsKernelsTypedBuffer.sub_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sub(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.sub_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x3OpsKernelsByteBuffer.sub_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment sub(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double[] _destArray, _srcArray, _otherArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_otherArray = heapDoubles(other, otherOffset)) != null) {
            sub(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _otherArray, heapIndex(other, otherOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double2x3OpsKernelsSegment.sub_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x3OpsKernelsSegment.sub_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long sub(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.sub_unsafe(dest, src, other);
        sub(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 48L), 0L);
        return dest;
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
        if (SimdSupport.DOUBLE_COLUMNS) return Double2x3OpsSimd.set(dest, destOffset, v, vOffset);
        return Double2x3OpsKernelsArray.set_scalar(dest, destOffset, v, vOffset);
    }

    /** {@link #set(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer set(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.set_unsafe(dest, destOffset, v, vOffset);
        return Double2x3OpsKernelsTypedBuffer.set_api(dest, destOffset, v, vOffset);
    }

    /** {@link #set(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer set(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.set_unsafe(dest, destOffset, v, vOffset);
        return Double2x3OpsKernelsByteBuffer.set_api(dest, destOffset, v, vOffset);
    }

    /** {@link #set(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment set(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double[] _destArray, _vArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_vArray = heapDoubles(v, vOffset)) != null) {
            set(_destArray, heapIndex(dest, destOffset, 3), _vArray, heapIndex(v, vOffset, 3));
            return dest;
        }
        if (SimdSupport.DOUBLE_COLUMNS) return Double2x3OpsSimd.set(dest, destOffset, v, vOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && v.isNative()) return Double2x3OpsKernelsSegment.set_unsafe(dest, destOffset, v, vOffset);
        return Double2x3OpsKernelsSegment.set_api(dest, destOffset, v, vOffset);
    }

    /** {@link #set(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long set(long dest, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.set_unsafe(dest, v);
        set(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 48L), 0L);
        return dest;
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
    public static double[] setMat2x2(double[] dest, int destOffset, double[] m, int mOffset) {
        if (SimdSupport.DOUBLE_COLUMNS) return Double2x3OpsSimd.setMat2x2(dest, destOffset, m, mOffset);
        return Double2x3OpsKernelsArray.setMat2x2_scalar(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat2x2(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer setMat2x2(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.setMat2x2_unsafe(dest, destOffset, m, mOffset);
        return Double2x3OpsKernelsTypedBuffer.setMat2x2_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat2x2(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer setMat2x2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.setMat2x2_unsafe(dest, destOffset, m, mOffset);
        return Double2x3OpsKernelsByteBuffer.setMat2x2_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat2x2(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment setMat2x2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        double[] _destArray, _mArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_mArray = heapDoubles(m, mOffset)) != null) {
            setMat2x2(_destArray, heapIndex(dest, destOffset, 3), _mArray, heapIndex(m, mOffset, 3));
            return dest;
        }
        if (SimdSupport.DOUBLE_COLUMNS) return Double2x3OpsSimd.setMat2x2(dest, destOffset, m, mOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && m.isNative()) return Double2x3OpsKernelsSegment.setMat2x2_unsafe(dest, destOffset, m, mOffset);
        return Double2x3OpsKernelsSegment.setMat2x2_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat2x2(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long setMat2x2(long dest, long m) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.setMat2x2_unsafe(dest, m);
        setMat2x2(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(m, 32L), 0L);
        return dest;
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
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            int _lom = _l * 3;
            double _em1 = m[mOffset + _lom + 1];
            dest[destOffset + _lo] = m[mOffset + _lom];
            dest[destOffset + _lo + 1] = _em1;
        }
        return dest;
    }

    /** {@link #setMat3x3(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer setMat3x3(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.setMat3x3_unsafe(dest, destOffset, m, mOffset);
        return Double2x3OpsKernelsTypedBuffer.setMat3x3_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat3x3(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer setMat3x3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.setMat3x3_unsafe(dest, destOffset, m, mOffset);
        return Double2x3OpsKernelsByteBuffer.setMat3x3_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat3x3(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment setMat3x3(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        double[] _destArray, _mArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_mArray = heapDoubles(m, mOffset)) != null) {
            setMat3x3(_destArray, heapIndex(dest, destOffset, 3), _mArray, heapIndex(m, mOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && m.isNative()) return Double2x3OpsKernelsSegment.setMat3x3_unsafe(dest, destOffset, m, mOffset);
        return Double2x3OpsKernelsSegment.setMat3x3_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat3x3(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long setMat3x3(long dest, long m) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.setMat3x3_unsafe(dest, m);
        setMat3x3(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(m, 72L), 0L);
        return dest;
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
    public static double[] withTranslation(double[] dest, int destOffset, double[] src, int srcOffset, double tX, double tY) {
        if (SimdSupport.DOUBLE_COLUMNS) return Double2x3OpsSimd.withTranslation(dest, destOffset, src, srcOffset, tX, tY);
        return Double2x3OpsKernelsArray.withTranslation_scalar(dest, destOffset, src, srcOffset, tX, tY);
    }

    /** {@link #withTranslation(double[], int, double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer withTranslation(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double tX, double tY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.withTranslation_unsafe(dest, destOffset, src, srcOffset, tX, tY);
        return Double2x3OpsKernelsTypedBuffer.withTranslation_api(dest, destOffset, src, srcOffset, tX, tY);
    }

    /** {@link #withTranslation(double[], int, double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer withTranslation(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double tX, double tY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.withTranslation_unsafe(dest, destOffset, src, srcOffset, tX, tY);
        return Double2x3OpsKernelsByteBuffer.withTranslation_api(dest, destOffset, src, srcOffset, tX, tY);
    }

    /** {@link #withTranslation(double[], int, double[], int, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment withTranslation(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double tX, double tY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            withTranslation(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), tX, tY);
            return dest;
        }
        if (SimdSupport.DOUBLE_COLUMNS) return Double2x3OpsSimd.withTranslation(dest, destOffset, src, srcOffset, tX, tY);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.withTranslation_unsafe(dest, destOffset, src, srcOffset, tX, tY);
        return Double2x3OpsKernelsSegment.withTranslation_api(dest, destOffset, src, srcOffset, tX, tY);
    }

    /** {@link #withTranslation(double[], int, double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long withTranslation(long dest, long src, double tX, double tY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.withTranslation_unsafe(dest, src, tX, tY);
        withTranslation(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, tX, tY);
        return dest;
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
    public static double[] withTranslation(double[] dest, int destOffset, double[] src, int srcOffset, double[] t, int tOffset) {
        if (SimdSupport.DOUBLE_COLUMNS) return Double2x3OpsSimd.withTranslation(dest, destOffset, src, srcOffset, t, tOffset);
        return Double2x3OpsKernelsArray.withTranslation_scalar(dest, destOffset, src, srcOffset, t, tOffset);
    }

    /** {@link #withTranslation(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer withTranslation(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t, int tOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && t.isDirect() && t.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.withTranslation_unsafe(dest, destOffset, src, srcOffset, t, tOffset);
        return Double2x3OpsKernelsTypedBuffer.withTranslation_api(dest, destOffset, src, srcOffset, t, tOffset);
    }

    /** {@link #withTranslation(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer withTranslation(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t, int tOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && t.isDirect() && t.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.withTranslation_unsafe(dest, destOffset, src, srcOffset, t, tOffset);
        return Double2x3OpsKernelsByteBuffer.withTranslation_api(dest, destOffset, src, srcOffset, t, tOffset);
    }

    /** {@link #withTranslation(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment withTranslation(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        double[] _destArray, _srcArray, _tArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_tArray = heapDoubles(t, tOffset)) != null) {
            withTranslation(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _tArray, heapIndex(t, tOffset, 3));
            return dest;
        }
        if (SimdSupport.DOUBLE_COLUMNS) return Double2x3OpsSimd.withTranslation(dest, destOffset, src, srcOffset, t, tOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && t.isNative()) return Double2x3OpsKernelsSegment.withTranslation_unsafe(dest, destOffset, src, srcOffset, t, tOffset);
        return Double2x3OpsKernelsSegment.withTranslation_api(dest, destOffset, src, srcOffset, t, tOffset);
    }

    /** {@link #withTranslation(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long withTranslation(long dest, long src, long t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.withTranslation_unsafe(dest, src, t);
        withTranslation(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(t, 16L), 0L);
        return dest;
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
    public static double[] to2x2(double[] dest, int destOffset, double[] src, int srcOffset) {
        if (SimdSupport.DOUBLE_COLUMNS) return Double2x3OpsSimd.to2x2(dest, destOffset, src, srcOffset);
        return Double2x3OpsKernelsArray.to2x2_scalar(dest, destOffset, src, srcOffset);
    }

    /** {@link #to2x2(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer to2x2(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.to2x2_unsafe(dest, destOffset, src, srcOffset);
        return Double2x3OpsKernelsTypedBuffer.to2x2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to2x2(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer to2x2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.to2x2_unsafe(dest, destOffset, src, srcOffset);
        return Double2x3OpsKernelsByteBuffer.to2x2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to2x2(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment to2x2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            to2x2(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (SimdSupport.DOUBLE_COLUMNS) return Double2x3OpsSimd.to2x2(dest, destOffset, src, srcOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.to2x2_unsafe(dest, destOffset, src, srcOffset);
        return Double2x3OpsKernelsSegment.to2x2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to2x2(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long to2x2(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.to2x2_unsafe(dest, src);
        to2x2(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L);
        return dest;
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
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = _self01;
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = 0.0;
        dest[destOffset + 6] = _self02;
        dest[destOffset + 7] = _self12;
        dest[destOffset + 8] = 1.0;
        return dest;
    }

    /** {@link #to3x3(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer to3x3(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.to3x3_unsafe(dest, destOffset, src, srcOffset);
        return Double2x3OpsKernelsTypedBuffer.to3x3_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to3x3(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer to3x3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.to3x3_unsafe(dest, destOffset, src, srcOffset);
        return Double2x3OpsKernelsByteBuffer.to3x3_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to3x3(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment to3x3(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            to3x3(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.to3x3_unsafe(dest, destOffset, src, srcOffset);
        return Double2x3OpsKernelsSegment.to3x3_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to3x3(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long to3x3(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.to3x3_unsafe(dest, src);
        to3x3(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L);
        return dest;
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
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = 0.0;
        return dest;
    }

    /** {@link #makeIdentity(double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeIdentity(java.nio.DoubleBuffer dest, int destOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.makeIdentity_unsafe(dest, destOffset);
        return Double2x3OpsKernelsTypedBuffer.makeIdentity_api(dest, destOffset);
    }

    /** {@link #makeIdentity(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeIdentity(java.nio.ByteBuffer dest, int destOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.makeIdentity_unsafe(dest, destOffset);
        return Double2x3OpsKernelsByteBuffer.makeIdentity_api(dest, destOffset);
    }

    /** {@link #makeIdentity(double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeIdentity(java.lang.foreign.MemorySegment dest, long destOffset) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeIdentity(_destArray, heapIndex(dest, destOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double2x3OpsKernelsSegment.makeIdentity_unsafe(dest, destOffset);
        return Double2x3OpsKernelsSegment.makeIdentity_api(dest, destOffset);
    }

    /** {@link #makeIdentity(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeIdentity(long dest) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.makeIdentity_unsafe(dest);
        makeIdentity(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L);
        return dest;
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
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = src[srcOffset + _lo];
            double _eself1 = src[srcOffset + _lo + 1];
            double _eother1 = other[otherOffset + _lo + 1];
            dest[destOffset + _lo] = Math.fma(t, other[otherOffset + _lo] - _eself0, _eself0);
            dest[destOffset + _lo + 1] = Math.fma(t, _eother1 - _eself1, _eself1);
        }
        return dest;
    }

    /** {@link #lerp(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer lerp(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Double2x3OpsKernelsTypedBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Double2x3OpsKernelsByteBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(double[], int, double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        double[] _destArray, _srcArray, _otherArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_otherArray = heapDoubles(other, otherOffset)) != null) {
            lerp(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _otherArray, heapIndex(other, otherOffset, 3), t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double2x3OpsKernelsSegment.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Double2x3OpsKernelsSegment.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long lerp(long dest, long src, long other, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.lerp_unsafe(dest, src, other, t);
        lerp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 48L), 0L, t);
        return dest;
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
    public static double[] mul(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _right00 = right[rightOffset];
        double _right10 = right[rightOffset + 1];
        double _right01 = right[rightOffset + 2];
        double _right11 = right[rightOffset + 3];
        double _right02 = right[rightOffset + 4];
        double _right12 = right[rightOffset + 5];
        dest[destOffset] = Math.fma(_right00, _self00, _right10 * _self01);
        dest[destOffset + 1] = Math.fma(_right00, _self10, _right10 * _self11);
        dest[destOffset + 2] = Math.fma(_right01, _self00, _right11 * _self01);
        dest[destOffset + 3] = Math.fma(_right01, _self10, _right11 * _self11);
        dest[destOffset + 4] = Math.fma(_right02, _self00, Math.fma(_right12, _self01, _self02));
        dest[destOffset + 5] = Math.fma(_right02, _self10, Math.fma(_right12, _self11, _self12));
        return dest;
    }

    /** {@link #mul(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mul(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && right.isDirect() && right.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.mul_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Double2x3OpsKernelsTypedBuffer.mul_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mul(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && right.isDirect() && right.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.mul_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Double2x3OpsKernelsByteBuffer.mul_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mul(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        double[] _destArray, _srcArray, _rightArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_rightArray = heapDoubles(right, rightOffset)) != null) {
            mul(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _rightArray, heapIndex(right, rightOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && right.isNative()) return Double2x3OpsKernelsSegment.mul_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Double2x3OpsKernelsSegment.mul_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mul(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mul(long dest, long src, long right) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.mul_unsafe(dest, src, right);
        mul(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(right, 48L), 0L);
        return dest;
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
    public static double[] mulMat2x2(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _right00 = right[rightOffset];
        double _right10 = right[rightOffset + 1];
        double _right01 = right[rightOffset + 2];
        double _right11 = right[rightOffset + 3];
        dest[destOffset] = Math.fma(_right00, _self00, _right10 * _self01);
        dest[destOffset + 1] = Math.fma(_right00, _self10, _right10 * _self11);
        dest[destOffset + 2] = Math.fma(_right01, _self00, _right11 * _self01);
        dest[destOffset + 3] = Math.fma(_right01, _self10, _right11 * _self11);
        dest[destOffset + 4] = _self02;
        dest[destOffset + 5] = _self12;
        return dest;
    }

    /** {@link #mulMat2x2(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mulMat2x2(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && right.isDirect() && right.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.mulMat2x2_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Double2x3OpsKernelsTypedBuffer.mulMat2x2_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mulMat2x2(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mulMat2x2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && right.isDirect() && right.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.mulMat2x2_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Double2x3OpsKernelsByteBuffer.mulMat2x2_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mulMat2x2(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment mulMat2x2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        double[] _destArray, _srcArray, _rightArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_rightArray = heapDoubles(right, rightOffset)) != null) {
            mulMat2x2(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _rightArray, heapIndex(right, rightOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && right.isNative()) return Double2x3OpsKernelsSegment.mulMat2x2_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Double2x3OpsKernelsSegment.mulMat2x2_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mulMat2x2(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mulMat2x2(long dest, long src, long right) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.mulMat2x2_unsafe(dest, src, right);
        mulMat2x2(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(right, 32L), 0L);
        return dest;
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
    public static double[] mulMat3x3(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eright0 = right[rightOffset + _lo];
            double _eright1 = right[rightOffset + _lo + 1];
            double _eright2 = right[rightOffset + _lo + 2];
            dest[destOffset + _lo] = Math.fma(_eright2, _self02, Math.fma(_eright0, _self00, _eright1 * _self01));
            dest[destOffset + _lo + 1] = Math.fma(_eright2, _self12, Math.fma(_eright0, _self10, _eright1 * _self11));
            dest[destOffset + _lo + 2] = _eright2;
        }
        return dest;
    }

    /** {@link #mulMat3x3(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mulMat3x3(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && right.isDirect() && right.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.mulMat3x3_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Double2x3OpsKernelsTypedBuffer.mulMat3x3_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mulMat3x3(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mulMat3x3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && right.isDirect() && right.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.mulMat3x3_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Double2x3OpsKernelsByteBuffer.mulMat3x3_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mulMat3x3(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment mulMat3x3(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        double[] _destArray, _srcArray, _rightArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_rightArray = heapDoubles(right, rightOffset)) != null) {
            mulMat3x3(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _rightArray, heapIndex(right, rightOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && right.isNative()) return Double2x3OpsKernelsSegment.mulMat3x3_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Double2x3OpsKernelsSegment.mulMat3x3_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mulMat3x3(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mulMat3x3(long dest, long src, long right) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.mulMat3x3_unsafe(dest, src, right);
        mulMat3x3(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(right, 72L), 0L);
        return dest;
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
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _other00 = other[otherOffset];
        double _other10 = other[otherOffset + 1];
        double _other01 = other[otherOffset + 2];
        double _other11 = other[otherOffset + 3];
        double _other02 = other[otherOffset + 4];
        double _other12 = other[otherOffset + 5];
        dest[destOffset] = Math.fma(_other00, _self00, _other01 * _self10);
        dest[destOffset + 1] = Math.fma(_other10, _self00, _other11 * _self10);
        dest[destOffset + 2] = Math.fma(_other00, _self01, _other01 * _self11);
        dest[destOffset + 3] = Math.fma(_other10, _self01, _other11 * _self11);
        dest[destOffset + 4] = Math.fma(_other00, _self02, Math.fma(_other01, _self12, _other02));
        dest[destOffset + 5] = Math.fma(_other10, _self02, Math.fma(_other11, _self12, _other12));
        return dest;
    }

    /** {@link #preMul(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preMul(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.preMul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x3OpsKernelsTypedBuffer.preMul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMul(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.preMul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x3OpsKernelsByteBuffer.preMul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMul(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preMul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double[] _destArray, _srcArray, _otherArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_otherArray = heapDoubles(other, otherOffset)) != null) {
            preMul(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _otherArray, heapIndex(other, otherOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double2x3OpsKernelsSegment.preMul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x3OpsKernelsSegment.preMul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMul(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preMul(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.preMul_unsafe(dest, src, other);
        preMul(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 48L), 0L);
        return dest;
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
    public static double[] preMulMat2x2(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _other00 = other[otherOffset];
        double _other10 = other[otherOffset + 1];
        double _other01 = other[otherOffset + 2];
        double _other11 = other[otherOffset + 3];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself0 = src[srcOffset + _lo];
            double _eself1 = src[srcOffset + _lo + 1];
            dest[destOffset + _lo] = Math.fma(_other00, _eself0, _other01 * _eself1);
            dest[destOffset + _lo + 1] = Math.fma(_other10, _eself0, _other11 * _eself1);
        }
        return dest;
    }

    /** {@link #preMulMat2x2(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preMulMat2x2(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.preMulMat2x2_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x3OpsKernelsTypedBuffer.preMulMat2x2_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMulMat2x2(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMulMat2x2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.preMulMat2x2_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x3OpsKernelsByteBuffer.preMulMat2x2_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMulMat2x2(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preMulMat2x2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double[] _destArray, _srcArray, _otherArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_otherArray = heapDoubles(other, otherOffset)) != null) {
            preMulMat2x2(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _otherArray, heapIndex(other, otherOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double2x3OpsKernelsSegment.preMulMat2x2_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x3OpsKernelsSegment.preMulMat2x2_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMulMat2x2(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preMulMat2x2(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.preMulMat2x2_unsafe(dest, src, other);
        preMulMat2x2(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 32L), 0L);
        return dest;
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
    public static double[] preMulMat3x3(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _other00 = other[otherOffset];
        double _other10 = other[otherOffset + 1];
        double _other20 = other[otherOffset + 2];
        double _other01 = other[otherOffset + 3];
        double _other11 = other[otherOffset + 4];
        double _other21 = other[otherOffset + 5];
        double _other02 = other[otherOffset + 6];
        double _other12 = other[otherOffset + 7];
        double _other22 = other[otherOffset + 8];
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

    /** {@link #preMulMat3x3(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preMulMat3x3(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.preMulMat3x3_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x3OpsKernelsTypedBuffer.preMulMat3x3_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMulMat3x3(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMulMat3x3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.preMulMat3x3_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x3OpsKernelsByteBuffer.preMulMat3x3_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMulMat3x3(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preMulMat3x3(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double[] _destArray, _srcArray, _otherArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_otherArray = heapDoubles(other, otherOffset)) != null) {
            preMulMat3x3(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _otherArray, heapIndex(other, otherOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double2x3OpsKernelsSegment.preMulMat3x3_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double2x3OpsKernelsSegment.preMulMat3x3_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMulMat3x3(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preMulMat3x3(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.preMulMat3x3_unsafe(dest, src, other);
        preMulMat3x3(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 72L), 0L);
        return dest;
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
    public static double[] addScaled(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double weight) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself1 = src[srcOffset + _lo + 1];
            double _eother1 = other[otherOffset + _lo + 1];
            dest[destOffset + _lo] = Math.fma(weight, other[otherOffset + _lo], src[srcOffset + _lo]);
            dest[destOffset + _lo + 1] = Math.fma(weight, _eother1, _eself1);
        }
        return dest;
    }

    /** {@link #addScaled(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer addScaled(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double weight) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return Double2x3OpsKernelsTypedBuffer.addScaled_api(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    /** {@link #addScaled(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer addScaled(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double weight) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return Double2x3OpsKernelsByteBuffer.addScaled_api(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    /** {@link #addScaled(double[], int, double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment addScaled(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double weight) {
        double[] _destArray, _srcArray, _otherArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_otherArray = heapDoubles(other, otherOffset)) != null) {
            addScaled(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _otherArray, heapIndex(other, otherOffset, 3), weight);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double2x3OpsKernelsSegment.addScaled_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return Double2x3OpsKernelsSegment.addScaled_api(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    /** {@link #addScaled(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long addScaled(long dest, long src, long other, double weight) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.addScaled_unsafe(dest, src, other, weight);
        addScaled(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 48L), 0L, weight);
        return dest;
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
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = 0.0;
        return dest;
    }

    /** {@link #makeRotation(double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeRotation(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.makeRotation_unsafe(dest, destOffset, angle);
        return Double2x3OpsKernelsTypedBuffer.makeRotation_api(dest, destOffset, angle);
    }

    /** {@link #makeRotation(double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotation(java.nio.ByteBuffer dest, int destOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.makeRotation_unsafe(dest, destOffset, angle);
        return Double2x3OpsKernelsByteBuffer.makeRotation_api(dest, destOffset, angle);
    }

    /** {@link #makeRotation(double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotation(java.lang.foreign.MemorySegment dest, long destOffset, double angle) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeRotation(_destArray, heapIndex(dest, destOffset, 3), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double2x3OpsKernelsSegment.makeRotation_unsafe(dest, destOffset, angle);
        return Double2x3OpsKernelsSegment.makeRotation_api(dest, destOffset, angle);
    }

    /** {@link #makeRotation(double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotation(long dest, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.makeRotation_unsafe(dest, angle);
        makeRotation(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, angle);
        return dest;
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
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = 0.0;
        return dest;
    }

    /** {@link #makeScaling(double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeScaling(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.makeScaling_unsafe(dest, destOffset, vX, vY);
        return Double2x3OpsKernelsTypedBuffer.makeScaling_api(dest, destOffset, vX, vY);
    }

    /** {@link #makeScaling(double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeScaling(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.makeScaling_unsafe(dest, destOffset, vX, vY);
        return Double2x3OpsKernelsByteBuffer.makeScaling_api(dest, destOffset, vX, vY);
    }

    /** {@link #makeScaling(double[], int, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeScaling(java.lang.foreign.MemorySegment dest, long destOffset, double vX, double vY) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeScaling(_destArray, heapIndex(dest, destOffset, 3), vX, vY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double2x3OpsKernelsSegment.makeScaling_unsafe(dest, destOffset, vX, vY);
        return Double2x3OpsKernelsSegment.makeScaling_api(dest, destOffset, vX, vY);
    }

    /** {@link #makeScaling(double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeScaling(long dest, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.makeScaling_unsafe(dest, vX, vY);
        makeScaling(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, vX, vY);
        return dest;
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
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = 0.0;
        return dest;
    }

    /** {@link #makeScaling(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeScaling(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.makeScaling_unsafe(dest, destOffset, v, vOffset);
        return Double2x3OpsKernelsTypedBuffer.makeScaling_api(dest, destOffset, v, vOffset);
    }

    /** {@link #makeScaling(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeScaling(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.makeScaling_unsafe(dest, destOffset, v, vOffset);
        return Double2x3OpsKernelsByteBuffer.makeScaling_api(dest, destOffset, v, vOffset);
    }

    /** {@link #makeScaling(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeScaling(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double[] _destArray, _vArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_vArray = heapDoubles(v, vOffset)) != null) {
            makeScaling(_destArray, heapIndex(dest, destOffset, 3), _vArray, heapIndex(v, vOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && v.isNative()) return Double2x3OpsKernelsSegment.makeScaling_unsafe(dest, destOffset, v, vOffset);
        return Double2x3OpsKernelsSegment.makeScaling_api(dest, destOffset, v, vOffset);
    }

    /** {@link #makeScaling(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeScaling(long dest, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.makeScaling_unsafe(dest, v);
        makeScaling(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 16L), 0L);
        return dest;
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
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = 0.0;
        return dest;
    }

    /** {@link #makeScaling(double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeScaling(java.nio.DoubleBuffer dest, int destOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.makeScaling_unsafe(dest, destOffset, s);
        return Double2x3OpsKernelsTypedBuffer.makeScaling_api(dest, destOffset, s);
    }

    /** {@link #makeScaling(double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeScaling(java.nio.ByteBuffer dest, int destOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.makeScaling_unsafe(dest, destOffset, s);
        return Double2x3OpsKernelsByteBuffer.makeScaling_api(dest, destOffset, s);
    }

    /** {@link #makeScaling(double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeScaling(java.lang.foreign.MemorySegment dest, long destOffset, double s) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeScaling(_destArray, heapIndex(dest, destOffset, 3), s);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double2x3OpsKernelsSegment.makeScaling_unsafe(dest, destOffset, s);
        return Double2x3OpsKernelsSegment.makeScaling_api(dest, destOffset, s);
    }

    /** {@link #makeScaling(double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeScaling(long dest, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.makeScaling_unsafe(dest, s);
        makeScaling(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, s);
        return dest;
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
    public static double[] makeTranslation(double[] dest, int destOffset, double vX, double vY) {
        dest[destOffset] = 1.0;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 1.0;
        dest[destOffset + 4] = vX;
        dest[destOffset + 5] = vY;
        return dest;
    }

    /** {@link #makeTranslation(double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeTranslation(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.makeTranslation_unsafe(dest, destOffset, vX, vY);
        return Double2x3OpsKernelsTypedBuffer.makeTranslation_api(dest, destOffset, vX, vY);
    }

    /** {@link #makeTranslation(double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeTranslation(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.makeTranslation_unsafe(dest, destOffset, vX, vY);
        return Double2x3OpsKernelsByteBuffer.makeTranslation_api(dest, destOffset, vX, vY);
    }

    /** {@link #makeTranslation(double[], int, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeTranslation(java.lang.foreign.MemorySegment dest, long destOffset, double vX, double vY) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeTranslation(_destArray, heapIndex(dest, destOffset, 3), vX, vY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double2x3OpsKernelsSegment.makeTranslation_unsafe(dest, destOffset, vX, vY);
        return Double2x3OpsKernelsSegment.makeTranslation_api(dest, destOffset, vX, vY);
    }

    /** {@link #makeTranslation(double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeTranslation(long dest, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.makeTranslation_unsafe(dest, vX, vY);
        makeTranslation(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, vX, vY);
        return dest;
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
    public static double[] makeTranslation(double[] dest, int destOffset, double[] v, int vOffset) {
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        dest[destOffset] = 1.0;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 1.0;
        dest[destOffset + 4] = _vx;
        dest[destOffset + 5] = _vy;
        return dest;
    }

    /** {@link #makeTranslation(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeTranslation(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.makeTranslation_unsafe(dest, destOffset, v, vOffset);
        return Double2x3OpsKernelsTypedBuffer.makeTranslation_api(dest, destOffset, v, vOffset);
    }

    /** {@link #makeTranslation(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeTranslation(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.makeTranslation_unsafe(dest, destOffset, v, vOffset);
        return Double2x3OpsKernelsByteBuffer.makeTranslation_api(dest, destOffset, v, vOffset);
    }

    /** {@link #makeTranslation(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeTranslation(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double[] _destArray, _vArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_vArray = heapDoubles(v, vOffset)) != null) {
            makeTranslation(_destArray, heapIndex(dest, destOffset, 3), _vArray, heapIndex(v, vOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && v.isNative()) return Double2x3OpsKernelsSegment.makeTranslation_unsafe(dest, destOffset, v, vOffset);
        return Double2x3OpsKernelsSegment.makeTranslation_api(dest, destOffset, v, vOffset);
    }

    /** {@link #makeTranslation(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeTranslation(long dest, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.makeTranslation_unsafe(dest, v);
        makeTranslation(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 16L), 0L);
        return dest;
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
    public static double[] makeView(double[] dest, int destOffset, double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        dest[destOffset] = _t0_inv + _t0_inv;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = _t1_inv + _t1_inv;
        dest[destOffset + 4] = -((left + right) * _t0_inv);
        dest[destOffset + 5] = -((bottom + top) * _t1_inv);
        return dest;
    }

    /** {@link #makeView(double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeView(java.nio.DoubleBuffer dest, int destOffset, double left, double right, double bottom, double top) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.makeView_unsafe(dest, destOffset, left, right, bottom, top);
        return Double2x3OpsKernelsTypedBuffer.makeView_api(dest, destOffset, left, right, bottom, top);
    }

    /** {@link #makeView(double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeView(java.nio.ByteBuffer dest, int destOffset, double left, double right, double bottom, double top) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.makeView_unsafe(dest, destOffset, left, right, bottom, top);
        return Double2x3OpsKernelsByteBuffer.makeView_api(dest, destOffset, left, right, bottom, top);
    }

    /** {@link #makeView(double[], int, double, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeView(java.lang.foreign.MemorySegment dest, long destOffset, double left, double right, double bottom, double top) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeView(_destArray, heapIndex(dest, destOffset, 3), left, right, bottom, top);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double2x3OpsKernelsSegment.makeView_unsafe(dest, destOffset, left, right, bottom, top);
        return Double2x3OpsKernelsSegment.makeView_api(dest, destOffset, left, right, bottom, top);
    }

    /** {@link #makeView(double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeView(long dest, double left, double right, double bottom, double top) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.makeView_unsafe(dest, left, right, bottom, top);
        makeView(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, left, right, bottom, top);
        return dest;
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
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = Math.fma(_self00, _t1, -(_self10 * _t0));
        dest[destOffset + 1] = Math.fma(_self00, _t0, _self10 * _t1);
        dest[destOffset + 2] = Math.fma(_self01, _t1, -(_self11 * _t0));
        dest[destOffset + 3] = Math.fma(_self01, _t0, _self11 * _t1);
        dest[destOffset + 4] = Math.fma(_self02, _t1, -(_self12 * _t0));
        dest[destOffset + 5] = Math.fma(_self02, _t0, _self12 * _t1);
        return dest;
    }

    /** {@link #preRotate(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preRotate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.preRotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double2x3OpsKernelsTypedBuffer.preRotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotate(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preRotate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.preRotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double2x3OpsKernelsByteBuffer.preRotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotate(double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preRotate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            preRotate(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.preRotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double2x3OpsKernelsSegment.preRotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotate(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preRotate(long dest, long src, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.preRotate_unsafe(dest, src, angle);
        preRotate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, angle);
        return dest;
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
    public static double[] preRotateAround(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double pivotX, double pivotY) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        dest[destOffset] = Math.fma(_self00, _t2, -(_self10 * _t0));
        dest[destOffset + 1] = Math.fma(_self00, _t0, _self10 * _t2);
        dest[destOffset + 2] = Math.fma(_self01, _t2, -(_self11 * _t0));
        dest[destOffset + 3] = Math.fma(_self01, _t0, _self11 * _t2);
        dest[destOffset + 4] = Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(_self02, _t2, -(_self12 * _t0));
        dest[destOffset + 5] = Math.fma(_self02, _t0, _self12 * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0));
        return dest;
    }

    /** {@link #preRotateAround(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preRotateAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.preRotateAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return Double2x3OpsKernelsTypedBuffer.preRotateAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@link #preRotateAround(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preRotateAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.preRotateAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return Double2x3OpsKernelsByteBuffer.preRotateAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@link #preRotateAround(double[], int, double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preRotateAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotX, double pivotY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            preRotateAround(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angle, pivotX, pivotY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.preRotateAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return Double2x3OpsKernelsSegment.preRotateAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@link #preRotateAround(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preRotateAround(long dest, long src, double angle, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.preRotateAround_unsafe(dest, src, angle, pivotX, pivotY);
        preRotateAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, angle, pivotX, pivotY);
        return dest;
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
    public static double[] preRotateAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double angle) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _pivotx = pivot[pivotOffset];
        double _pivoty = pivot[pivotOffset + 1];
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        dest[destOffset] = Math.fma(_self00, _t2, -(_self10 * _t0));
        dest[destOffset + 1] = Math.fma(_self00, _t0, _self10 * _t2);
        dest[destOffset + 2] = Math.fma(_self01, _t2, -(_self11 * _t0));
        dest[destOffset + 3] = Math.fma(_self01, _t0, _self11 * _t2);
        dest[destOffset + 4] = Math.fma(_pivotx, _t5, _pivoty * _t0) + Math.fma(_self02, _t2, -(_self12 * _t0));
        dest[destOffset + 5] = Math.fma(_self02, _t0, _self12 * _t2) + Math.fma(_pivoty, _t5, -(_pivotx * _t0));
        return dest;
    }

    /** {@link #preRotateAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preRotateAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.preRotateAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Double2x3OpsKernelsTypedBuffer.preRotateAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #preRotateAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preRotateAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.preRotateAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Double2x3OpsKernelsByteBuffer.preRotateAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #preRotateAround(double[], int, double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preRotateAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        double[] _destArray, _srcArray, _pivotArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_pivotArray = heapDoubles(pivot, pivotOffset)) != null) {
            preRotateAround(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _pivotArray, heapIndex(pivot, pivotOffset, 3), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && pivot.isNative()) return Double2x3OpsKernelsSegment.preRotateAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Double2x3OpsKernelsSegment.preRotateAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #preRotateAround(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preRotateAround(long dest, long src, long pivot, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.preRotateAround_unsafe(dest, src, pivot, angle);
        preRotateAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(pivot, 16L), 0L, angle);
        return dest;
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
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself1 = src[srcOffset + _lo + 1];
            dest[destOffset + _lo] = src[srcOffset + _lo] * vX;
            dest[destOffset + _lo + 1] = _eself1 * vY;
        }
        return dest;
    }

    /** {@link #preScale(double[], int, double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preScale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x3OpsKernelsTypedBuffer.preScale_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #preScale(double[], int, double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x3OpsKernelsByteBuffer.preScale_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #preScale(double[], int, double[], int, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preScale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            preScale(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), vX, vY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.preScale_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x3OpsKernelsSegment.preScale_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #preScale(double[], int, double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preScale(long dest, long src, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.preScale_unsafe(dest, src, vX, vY);
        preScale(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, vX, vY);
        return dest;
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
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself1 = src[srcOffset + _lo + 1];
            dest[destOffset + _lo] = src[srcOffset + _lo] * _vx;
            dest[destOffset + _lo + 1] = _eself1 * _vy;
        }
        return dest;
    }

    /** {@link #preScale(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preScale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsTypedBuffer.preScale_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #preScale(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsByteBuffer.preScale_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #preScale(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preScale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double[] _destArray, _srcArray, _vArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_vArray = heapDoubles(v, vOffset)) != null) {
            preScale(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _vArray, heapIndex(v, vOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && v.isNative()) return Double2x3OpsKernelsSegment.preScale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsSegment.preScale_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #preScale(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preScale(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.preScale_unsafe(dest, src, v);
        preScale(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 16L), 0L);
        return dest;
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
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            double _eself1 = src[srcOffset + _lo + 1];
            dest[destOffset + _lo] = s * src[srcOffset + _lo];
            dest[destOffset + _lo + 1] = s * _eself1;
        }
        return dest;
    }

    /** {@link #preScale(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preScale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, s);
        return Double2x3OpsKernelsTypedBuffer.preScale_api(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #preScale(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, s);
        return Double2x3OpsKernelsByteBuffer.preScale_api(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #preScale(double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preScale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            preScale(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), s);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.preScale_unsafe(dest, destOffset, src, srcOffset, s);
        return Double2x3OpsKernelsSegment.preScale_api(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #preScale(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preScale(long dest, long src, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.preScale_unsafe(dest, src, s);
        preScale(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, s);
        return dest;
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
    public static double[] preScaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double s, double pivotX, double pivotY) {
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _t0 = 1.0 - s;
        dest[destOffset] = s * src[srcOffset];
        dest[destOffset + 1] = s * _self10;
        dest[destOffset + 2] = s * _self01;
        dest[destOffset + 3] = s * _self11;
        dest[destOffset + 4] = Math.fma(s, _self02, pivotX * _t0);
        dest[destOffset + 5] = Math.fma(s, _self12, pivotY * _t0);
        return dest;
    }

    /** {@link #preScaleAround(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preScaleAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return Double2x3OpsKernelsTypedBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return Double2x3OpsKernelsByteBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preScaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double pivotX, double pivotY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            preScaleAround(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), s, pivotX, pivotY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.preScaleAround_unsafe(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return Double2x3OpsKernelsSegment.preScaleAround_api(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preScaleAround(long dest, long src, double s, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.preScaleAround_unsafe(dest, src, s, pivotX, pivotY);
        preScaleAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, s, pivotX, pivotY);
        return dest;
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
    public static double[] preScaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _pivotx = pivot[pivotOffset];
        double _pivoty = pivot[pivotOffset + 1];
        double _t0 = 1.0 - s;
        dest[destOffset] = s * src[srcOffset];
        dest[destOffset + 1] = s * _self10;
        dest[destOffset + 2] = s * _self01;
        dest[destOffset + 3] = s * _self11;
        dest[destOffset + 4] = Math.fma(s, _self02, _pivotx * _t0);
        dest[destOffset + 5] = Math.fma(s, _self12, _pivoty * _t0);
        return dest;
    }

    /** {@link #preScaleAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preScaleAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return Double2x3OpsKernelsTypedBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return Double2x3OpsKernelsByteBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preScaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        double[] _destArray, _srcArray, _pivotArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_pivotArray = heapDoubles(pivot, pivotOffset)) != null) {
            preScaleAround(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _pivotArray, heapIndex(pivot, pivotOffset, 3), s);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && pivot.isNative()) return Double2x3OpsKernelsSegment.preScaleAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return Double2x3OpsKernelsSegment.preScaleAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preScaleAround(long dest, long src, long pivot, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.preScaleAround_unsafe(dest, src, pivot, s);
        preScaleAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(pivot, 16L), 0L, s);
        return dest;
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
    public static double[] preScaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        dest[destOffset] = sX * src[srcOffset];
        dest[destOffset + 1] = sY * _self10;
        dest[destOffset + 2] = sX * _self01;
        dest[destOffset + 3] = sY * _self11;
        dest[destOffset + 4] = Math.fma(pivotX, 1.0 - sX, sX * _self02);
        dest[destOffset + 5] = Math.fma(pivotY, 1.0 - sY, sY * _self12);
        return dest;
    }

    /** {@link #preScaleAround(double[], int, double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preScaleAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return Double2x3OpsKernelsTypedBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return Double2x3OpsKernelsByteBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preScaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double pivotX, double pivotY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            preScaleAround(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), sX, sY, pivotX, pivotY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.preScaleAround_unsafe(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return Double2x3OpsKernelsSegment.preScaleAround_api(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preScaleAround(long dest, long src, double sX, double sY, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.preScaleAround_unsafe(dest, src, sX, sY, pivotX, pivotY);
        preScaleAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, sX, sY, pivotX, pivotY);
        return dest;
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
    public static double[] preScaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _sx = s[sOffset];
        double _sy = s[sOffset + 1];
        double _pivotx = pivot[pivotOffset];
        double _pivoty = pivot[pivotOffset + 1];
        dest[destOffset] = _sx * src[srcOffset];
        dest[destOffset + 1] = _sy * _self10;
        dest[destOffset + 2] = _sx * _self01;
        dest[destOffset + 3] = _sy * _self11;
        dest[destOffset + 4] = Math.fma(_pivotx, 1.0 - _sx, _sx * _self02);
        dest[destOffset + 5] = Math.fma(_pivoty, 1.0 - _sy, _sy * _self12);
        return dest;
    }

    /** {@link #preScaleAround(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preScaleAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer s, int sOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && s.isDirect() && s.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return Double2x3OpsKernelsTypedBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && s.isDirect() && s.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return Double2x3OpsKernelsByteBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preScaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        double[] _destArray, _srcArray, _sArray, _pivotArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_sArray = heapDoubles(s, sOffset)) != null
                && (_pivotArray = heapDoubles(pivot, pivotOffset)) != null) {
            preScaleAround(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _sArray, heapIndex(s, sOffset, 3), _pivotArray, heapIndex(pivot, pivotOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && s.isNative() && pivot.isNative()) return Double2x3OpsKernelsSegment.preScaleAround_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return Double2x3OpsKernelsSegment.preScaleAround_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preScaleAround(long dest, long src, long s, long pivot) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.preScaleAround_unsafe(dest, src, s, pivot);
        preScaleAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(s, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(pivot, 16L), 0L);
        return dest;
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
    public static double[] preTranslate(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY) {
        if (SimdSupport.DOUBLE_COLUMNS) return Double2x3OpsSimd.preTranslate(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x3OpsKernelsArray.preTranslate_scalar(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #preTranslate(double[], int, double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preTranslate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.preTranslate_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x3OpsKernelsTypedBuffer.preTranslate_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #preTranslate(double[], int, double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preTranslate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.preTranslate_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x3OpsKernelsByteBuffer.preTranslate_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #preTranslate(double[], int, double[], int, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preTranslate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            preTranslate(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), vX, vY);
            return dest;
        }
        if (SimdSupport.DOUBLE_COLUMNS) return Double2x3OpsSimd.preTranslate(dest, destOffset, src, srcOffset, vX, vY);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.preTranslate_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x3OpsKernelsSegment.preTranslate_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #preTranslate(double[], int, double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preTranslate(long dest, long src, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.preTranslate_unsafe(dest, src, vX, vY);
        preTranslate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, vX, vY);
        return dest;
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
    public static double[] preTranslate(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        if (SimdSupport.DOUBLE_COLUMNS) return Double2x3OpsSimd.preTranslate(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsArray.preTranslate_scalar(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #preTranslate(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preTranslate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.preTranslate_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsTypedBuffer.preTranslate_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #preTranslate(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preTranslate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.preTranslate_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsByteBuffer.preTranslate_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #preTranslate(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preTranslate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double[] _destArray, _srcArray, _vArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_vArray = heapDoubles(v, vOffset)) != null) {
            preTranslate(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _vArray, heapIndex(v, vOffset, 3));
            return dest;
        }
        if (SimdSupport.DOUBLE_COLUMNS) return Double2x3OpsSimd.preTranslate(dest, destOffset, src, srcOffset, v, vOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && v.isNative()) return Double2x3OpsKernelsSegment.preTranslate_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsSegment.preTranslate_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #preTranslate(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preTranslate(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.preTranslate_unsafe(dest, src, v);
        preTranslate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 16L), 0L);
        return dest;
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
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = Math.fma(_self00, _t1, _self01 * _t0);
        dest[destOffset + 1] = Math.fma(_self10, _t1, _self11 * _t0);
        dest[destOffset + 2] = Math.fma(_self01, _t1, -(_self00 * _t0));
        dest[destOffset + 3] = Math.fma(_self11, _t1, -(_self10 * _t0));
        dest[destOffset + 4] = _self02;
        dest[destOffset + 5] = _self12;
        return dest;
    }

    /** {@link #rotate(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double2x3OpsKernelsTypedBuffer.rotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotate(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double2x3OpsKernelsByteBuffer.rotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotate(double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotate(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.rotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double2x3OpsKernelsSegment.rotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotate(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotate(long dest, long src, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.rotate_unsafe(dest, src, angle);
        rotate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, angle);
        return dest;
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
    public static double[] rotateAround(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double pivotX, double pivotY) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        dest[destOffset] = Math.fma(_self00, _t2, _self01 * _t0);
        dest[destOffset + 1] = Math.fma(_self10, _t2, _self11 * _t0);
        dest[destOffset + 2] = Math.fma(_self01, _t2, -(_self00 * _t0));
        dest[destOffset + 3] = Math.fma(_self11, _t2, -(_self10 * _t0));
        dest[destOffset + 4] = Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02));
        dest[destOffset + 5] = Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12));
        return dest;
    }

    /** {@link #rotateAround(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.rotateAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return Double2x3OpsKernelsTypedBuffer.rotateAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@link #rotateAround(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.rotateAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return Double2x3OpsKernelsByteBuffer.rotateAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@link #rotateAround(double[], int, double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotX, double pivotY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateAround(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angle, pivotX, pivotY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.rotateAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return Double2x3OpsKernelsSegment.rotateAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@link #rotateAround(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateAround(long dest, long src, double angle, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.rotateAround_unsafe(dest, src, angle, pivotX, pivotY);
        rotateAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, angle, pivotX, pivotY);
        return dest;
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
    public static double[] rotateAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double angle) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _pivotx = pivot[pivotOffset];
        double _pivoty = pivot[pivotOffset + 1];
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = Math.fma(_pivotx, _t8, _pivoty * _t0);
        double _t10 = Math.fma(_pivoty, _t8, -(_pivotx * _t0));
        dest[destOffset] = Math.fma(_self00, _t2, _self01 * _t0);
        dest[destOffset + 1] = Math.fma(_self10, _t2, _self11 * _t0);
        dest[destOffset + 2] = Math.fma(_self01, _t2, -(_self00 * _t0));
        dest[destOffset + 3] = Math.fma(_self11, _t2, -(_self10 * _t0));
        dest[destOffset + 4] = Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02));
        dest[destOffset + 5] = Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12));
        return dest;
    }

    /** {@link #rotateAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.rotateAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Double2x3OpsKernelsTypedBuffer.rotateAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.rotateAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Double2x3OpsKernelsByteBuffer.rotateAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateAround(double[], int, double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double angle) {
        double[] _destArray, _srcArray, _pivotArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_pivotArray = heapDoubles(pivot, pivotOffset)) != null) {
            rotateAround(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _pivotArray, heapIndex(pivot, pivotOffset, 3), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && pivot.isNative()) return Double2x3OpsKernelsSegment.rotateAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Double2x3OpsKernelsSegment.rotateAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateAround(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateAround(long dest, long src, long pivot, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.rotateAround_unsafe(dest, src, pivot, angle);
        rotateAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(pivot, 16L), 0L, angle);
        return dest;
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
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        dest[destOffset] = src[srcOffset] * vX;
        dest[destOffset + 1] = _self10 * vX;
        dest[destOffset + 2] = _self01 * vY;
        dest[destOffset + 3] = _self11 * vY;
        dest[destOffset + 4] = _self02;
        dest[destOffset + 5] = _self12;
        return dest;
    }

    /** {@link #scale(double[], int, double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer scale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.scale_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x3OpsKernelsTypedBuffer.scale_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #scale(double[], int, double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.scale_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x3OpsKernelsByteBuffer.scale_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #scale(double[], int, double[], int, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment scale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            scale(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), vX, vY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.scale_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x3OpsKernelsSegment.scale_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #scale(double[], int, double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long scale(long dest, long src, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.scale_unsafe(dest, src, vX, vY);
        scale(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, vX, vY);
        return dest;
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
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        dest[destOffset] = src[srcOffset] * _vx;
        dest[destOffset + 1] = _self10 * _vx;
        dest[destOffset + 2] = _self01 * _vy;
        dest[destOffset + 3] = _self11 * _vy;
        dest[destOffset + 4] = _self02;
        dest[destOffset + 5] = _self12;
        return dest;
    }

    /** {@link #scale(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer scale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.scale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsTypedBuffer.scale_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #scale(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.scale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsByteBuffer.scale_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #scale(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment scale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double[] _destArray, _srcArray, _vArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_vArray = heapDoubles(v, vOffset)) != null) {
            scale(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _vArray, heapIndex(v, vOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && v.isNative()) return Double2x3OpsKernelsSegment.scale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsSegment.scale_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #scale(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long scale(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.scale_unsafe(dest, src, v);
        scale(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 16L), 0L);
        return dest;
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
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        dest[destOffset] = s * src[srcOffset];
        dest[destOffset + 1] = s * _self10;
        dest[destOffset + 2] = s * _self01;
        dest[destOffset + 3] = s * _self11;
        dest[destOffset + 4] = _self02;
        dest[destOffset + 5] = _self12;
        return dest;
    }

    /** {@link #scale(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer scale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.scale_unsafe(dest, destOffset, src, srcOffset, s);
        return Double2x3OpsKernelsTypedBuffer.scale_api(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #scale(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.scale_unsafe(dest, destOffset, src, srcOffset, s);
        return Double2x3OpsKernelsByteBuffer.scale_api(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #scale(double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment scale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            scale(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), s);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.scale_unsafe(dest, destOffset, src, srcOffset, s);
        return Double2x3OpsKernelsSegment.scale_api(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #scale(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long scale(long dest, long src, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.scale_unsafe(dest, src, s);
        scale(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, s);
        return dest;
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
    public static double[] scaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double s, double pivotX, double pivotY) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        dest[destOffset] = s * _self00;
        dest[destOffset + 1] = s * _self10;
        dest[destOffset + 2] = s * _self01;
        dest[destOffset + 3] = s * _self11;
        dest[destOffset + 4] = Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02));
        dest[destOffset + 5] = Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12));
        return dest;
    }

    /** {@link #scaleAround(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer scaleAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return Double2x3OpsKernelsTypedBuffer.scaleAround_api(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
    }

    /** {@link #scaleAround(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return Double2x3OpsKernelsByteBuffer.scaleAround_api(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
    }

    /** {@link #scaleAround(double[], int, double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment scaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double pivotX, double pivotY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            scaleAround(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), s, pivotX, pivotY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.scaleAround_unsafe(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return Double2x3OpsKernelsSegment.scaleAround_api(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
    }

    /** {@link #scaleAround(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long scaleAround(long dest, long src, double s, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.scaleAround_unsafe(dest, src, s, pivotX, pivotY);
        scaleAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, s, pivotX, pivotY);
        return dest;
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
    public static double[] scaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _t0 = 1.0 - s;
        double _t1 = pivot[pivotOffset] * _t0;
        double _t2 = pivot[pivotOffset + 1] * _t0;
        dest[destOffset] = s * _self00;
        dest[destOffset + 1] = s * _self10;
        dest[destOffset + 2] = s * _self01;
        dest[destOffset + 3] = s * _self11;
        dest[destOffset + 4] = Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02));
        dest[destOffset + 5] = Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12));
        return dest;
    }

    /** {@link #scaleAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer scaleAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return Double2x3OpsKernelsTypedBuffer.scaleAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    /** {@link #scaleAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return Double2x3OpsKernelsByteBuffer.scaleAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    /** {@link #scaleAround(double[], int, double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment scaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        double[] _destArray, _srcArray, _pivotArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_pivotArray = heapDoubles(pivot, pivotOffset)) != null) {
            scaleAround(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _pivotArray, heapIndex(pivot, pivotOffset, 3), s);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && pivot.isNative()) return Double2x3OpsKernelsSegment.scaleAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return Double2x3OpsKernelsSegment.scaleAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    /** {@link #scaleAround(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long scaleAround(long dest, long src, long pivot, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.scaleAround_unsafe(dest, src, pivot, s);
        scaleAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(pivot, 16L), 0L, s);
        return dest;
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
    public static double[] scaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        dest[destOffset] = sX * _self00;
        dest[destOffset + 1] = sX * _self10;
        dest[destOffset + 2] = sY * _self01;
        dest[destOffset + 3] = sY * _self11;
        dest[destOffset + 4] = Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02));
        dest[destOffset + 5] = Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12));
        return dest;
    }

    /** {@link #scaleAround(double[], int, double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer scaleAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return Double2x3OpsKernelsTypedBuffer.scaleAround_api(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
    }

    /** {@link #scaleAround(double[], int, double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return Double2x3OpsKernelsByteBuffer.scaleAround_api(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
    }

    /** {@link #scaleAround(double[], int, double[], int, double, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment scaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double pivotX, double pivotY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            scaleAround(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), sX, sY, pivotX, pivotY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.scaleAround_unsafe(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return Double2x3OpsKernelsSegment.scaleAround_api(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
    }

    /** {@link #scaleAround(double[], int, double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long scaleAround(long dest, long src, double sX, double sY, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.scaleAround_unsafe(dest, src, sX, sY, pivotX, pivotY);
        scaleAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, sX, sY, pivotX, pivotY);
        return dest;
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
    public static double[] scaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _sx = s[sOffset];
        double _sy = s[sOffset + 1];
        double _t2 = pivot[pivotOffset] * (1.0 - _sx);
        double _t3 = pivot[pivotOffset + 1] * (1.0 - _sy);
        dest[destOffset] = _sx * _self00;
        dest[destOffset + 1] = _sx * _self10;
        dest[destOffset + 2] = _sy * _self01;
        dest[destOffset + 3] = _sy * _self11;
        dest[destOffset + 4] = Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02));
        dest[destOffset + 5] = Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12));
        return dest;
    }

    /** {@link #scaleAround(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer scaleAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer s, int sOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && s.isDirect() && s.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return Double2x3OpsKernelsTypedBuffer.scaleAround_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@link #scaleAround(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && s.isDirect() && s.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return Double2x3OpsKernelsByteBuffer.scaleAround_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@link #scaleAround(double[], int, double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment scaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        double[] _destArray, _srcArray, _sArray, _pivotArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_sArray = heapDoubles(s, sOffset)) != null
                && (_pivotArray = heapDoubles(pivot, pivotOffset)) != null) {
            scaleAround(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _sArray, heapIndex(s, sOffset, 3), _pivotArray, heapIndex(pivot, pivotOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && s.isNative() && pivot.isNative()) return Double2x3OpsKernelsSegment.scaleAround_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return Double2x3OpsKernelsSegment.scaleAround_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@link #scaleAround(double[], int, double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long scaleAround(long dest, long src, long s, long pivot) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.scaleAround_unsafe(dest, src, s, pivot);
        scaleAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(s, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(pivot, 16L), 0L);
        return dest;
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
    public static double[] translate(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY) {
        if (SimdSupport.DOUBLE_COLUMNS) return Double2x3OpsSimd.translate(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x3OpsKernelsArray.translate_scalar(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #translate(double[], int, double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer translate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.translate_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x3OpsKernelsTypedBuffer.translate_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #translate(double[], int, double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer translate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.translate_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x3OpsKernelsByteBuffer.translate_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #translate(double[], int, double[], int, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment translate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            translate(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), vX, vY);
            return dest;
        }
        if (SimdSupport.DOUBLE_COLUMNS) return Double2x3OpsSimd.translate(dest, destOffset, src, srcOffset, vX, vY);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.translate_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x3OpsKernelsSegment.translate_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #translate(double[], int, double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long translate(long dest, long src, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.translate_unsafe(dest, src, vX, vY);
        translate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, vX, vY);
        return dest;
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
    public static double[] translate(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        if (SimdSupport.DOUBLE_COLUMNS) return Double2x3OpsSimd.translate(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsArray.translate_scalar(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #translate(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer translate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.translate_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsTypedBuffer.translate_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #translate(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer translate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.translate_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsByteBuffer.translate_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #translate(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment translate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double[] _destArray, _srcArray, _vArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_vArray = heapDoubles(v, vOffset)) != null) {
            translate(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _vArray, heapIndex(v, vOffset, 3));
            return dest;
        }
        if (SimdSupport.DOUBLE_COLUMNS) return Double2x3OpsSimd.translate(dest, destOffset, src, srcOffset, v, vOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && v.isNative()) return Double2x3OpsKernelsSegment.translate_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsSegment.translate_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #translate(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long translate(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.translate_unsafe(dest, src, v);
        translate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 16L), 0L);
        return dest;
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
    public static double[] view(double[] dest, int destOffset, double[] src, int srcOffset, double left, double right, double bottom, double top) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 3];
        double _self02 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 5];
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        dest[destOffset] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp1 * _self01;
        dest[destOffset + 3] = _sp1 * _self11;
        dest[destOffset + 4] = _self02 + (-(_self00 * _sp2) - _self01 * _sp3);
        dest[destOffset + 5] = _self12 + (-(_self10 * _sp2) - _self11 * _sp3);
        return dest;
    }

    /** {@link #view(double[], int, double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer view(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double left, double right, double bottom, double top) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.view_unsafe(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return Double2x3OpsKernelsTypedBuffer.view_api(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    /** {@link #view(double[], int, double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer view(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double left, double right, double bottom, double top) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.view_unsafe(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return Double2x3OpsKernelsByteBuffer.view_api(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    /** {@link #view(double[], int, double[], int, double, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment view(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double left, double right, double bottom, double top) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            view(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), left, right, bottom, top);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.view_unsafe(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return Double2x3OpsKernelsSegment.view_api(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    /** {@link #view(double[], int, double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long view(long dest, long src, double left, double right, double bottom, double top) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.view_unsafe(dest, src, left, right, bottom, top);
        view(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, left, right, bottom, top);
        return dest;
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
    public static double[] mulVec3(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        double _self10 = src[srcOffset + 1];
        double _self11 = src[srcOffset + 3];
        double _self12 = src[srcOffset + 5];
        dest[destOffset] = Math.fma(src[srcOffset + 4], vZ, Math.fma(src[srcOffset], vX, src[srcOffset + 2] * vY));
        dest[destOffset + 1] = Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY));
        return dest;
    }

    /** {@link #mulVec3(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mulVec3(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.mulVec3_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return Double2x3OpsKernelsTypedBuffer.mulVec3_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    /** {@link #mulVec3(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mulVec3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.mulVec3_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return Double2x3OpsKernelsByteBuffer.mulVec3_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    /** {@link #mulVec3(double[], int, double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment mulVec3(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            mulVec3(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), vX, vY, vZ);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.mulVec3_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return Double2x3OpsKernelsSegment.mulVec3_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    /** {@link #mulVec3(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mulVec3(long dest, long src, double vX, double vY, double vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.mulVec3_unsafe(dest, src, vX, vY, vZ);
        mulVec3(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, vX, vY, vZ);
        return dest;
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
    public static double[] mulVec3(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _self10 = src[srcOffset + 1];
        double _self11 = src[srcOffset + 3];
        double _self12 = src[srcOffset + 5];
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        dest[destOffset] = Math.fma(src[srcOffset + 4], _vz, Math.fma(src[srcOffset], _vx, src[srcOffset + 2] * _vy));
        dest[destOffset + 1] = Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy));
        return dest;
    }

    /** {@link #mulVec3(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mulVec3(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.mulVec3_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsTypedBuffer.mulVec3_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #mulVec3(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mulVec3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.mulVec3_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsByteBuffer.mulVec3_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #mulVec3(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment mulVec3(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double[] _destArray, _srcArray, _vArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_vArray = heapDoubles(v, vOffset)) != null) {
            mulVec3(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _vArray, heapIndex(v, vOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && v.isNative()) return Double2x3OpsKernelsSegment.mulVec3_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsSegment.mulVec3_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #mulVec3(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mulVec3(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.mulVec3_unsafe(dest, src, v);
        mulVec3(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 24L), 0L);
        return dest;
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
    public static double[] transformDirection(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY) {
        double _self10 = src[srcOffset + 1];
        double _self11 = src[srcOffset + 3];
        dest[destOffset] = Math.fma(src[srcOffset], vX, src[srcOffset + 2] * vY);
        dest[destOffset + 1] = Math.fma(_self10, vX, _self11 * vY);
        return dest;
    }

    /** {@link #transformDirection(double[], int, double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer transformDirection(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.transformDirection_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x3OpsKernelsTypedBuffer.transformDirection_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #transformDirection(double[], int, double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer transformDirection(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.transformDirection_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x3OpsKernelsByteBuffer.transformDirection_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #transformDirection(double[], int, double[], int, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment transformDirection(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            transformDirection(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), vX, vY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.transformDirection_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x3OpsKernelsSegment.transformDirection_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #transformDirection(double[], int, double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long transformDirection(long dest, long src, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.transformDirection_unsafe(dest, src, vX, vY);
        transformDirection(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, vX, vY);
        return dest;
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
    public static double[] transformDirection(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _self10 = src[srcOffset + 1];
        double _self11 = src[srcOffset + 3];
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        dest[destOffset] = Math.fma(src[srcOffset], _vx, src[srcOffset + 2] * _vy);
        dest[destOffset + 1] = Math.fma(_self10, _vx, _self11 * _vy);
        return dest;
    }

    /** {@link #transformDirection(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer transformDirection(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.transformDirection_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsTypedBuffer.transformDirection_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #transformDirection(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer transformDirection(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.transformDirection_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsByteBuffer.transformDirection_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #transformDirection(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment transformDirection(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double[] _destArray, _srcArray, _vArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_vArray = heapDoubles(v, vOffset)) != null) {
            transformDirection(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _vArray, heapIndex(v, vOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && v.isNative()) return Double2x3OpsKernelsSegment.transformDirection_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsSegment.transformDirection_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #transformDirection(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long transformDirection(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.transformDirection_unsafe(dest, src, v);
        transformDirection(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 16L), 0L);
        return dest;
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
    public static double[] transformPosition(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY) {
        double _self10 = src[srcOffset + 1];
        double _self11 = src[srcOffset + 3];
        double _self12 = src[srcOffset + 5];
        dest[destOffset] = Math.fma(src[srcOffset], vX, Math.fma(src[srcOffset + 2], vY, src[srcOffset + 4]));
        dest[destOffset + 1] = Math.fma(_self10, vX, Math.fma(_self11, vY, _self12));
        return dest;
    }

    /** {@link #transformPosition(double[], int, double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer transformPosition(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.transformPosition_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x3OpsKernelsTypedBuffer.transformPosition_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #transformPosition(double[], int, double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer transformPosition(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.transformPosition_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x3OpsKernelsByteBuffer.transformPosition_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #transformPosition(double[], int, double[], int, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment transformPosition(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            transformPosition(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), vX, vY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double2x3OpsKernelsSegment.transformPosition_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double2x3OpsKernelsSegment.transformPosition_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #transformPosition(double[], int, double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long transformPosition(long dest, long src, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.transformPosition_unsafe(dest, src, vX, vY);
        transformPosition(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, vX, vY);
        return dest;
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
    public static double[] transformPosition(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _self10 = src[srcOffset + 1];
        double _self11 = src[srcOffset + 3];
        double _self12 = src[srcOffset + 5];
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        dest[destOffset] = Math.fma(src[srcOffset], _vx, Math.fma(src[srcOffset + 2], _vy, src[srcOffset + 4]));
        dest[destOffset + 1] = Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12));
        return dest;
    }

    /** {@link #transformPosition(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer transformPosition(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsTypedBuffer.transformPosition_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsTypedBuffer.transformPosition_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #transformPosition(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer transformPosition(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsKernelsByteBuffer.transformPosition_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsByteBuffer.transformPosition_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #transformPosition(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment transformPosition(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double[] _destArray, _srcArray, _vArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_vArray = heapDoubles(v, vOffset)) != null) {
            transformPosition(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _vArray, heapIndex(v, vOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && v.isNative()) return Double2x3OpsKernelsSegment.transformPosition_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double2x3OpsKernelsSegment.transformPosition_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #transformPosition(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long transformPosition(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double2x3OpsKernelsAddress.transformPosition_unsafe(dest, src, v);
        transformPosition(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 16L), 0L);
        return dest;
    }


    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 357913941 ? -1 : count * 6), src.length);
            java.util.Objects.checkFromIndexSize(destOffset, (count > 357913941 ? -1 : count * 6), dest.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.DOUBLE_ARRAY_BASE + srcOffset * 8L, dest, UnsafeCopy.DOUBLE_ARRAY_BASE + destOffset * 8L, count * 48L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src[srcOffset + _i];
        return dest;
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 357913941 ? -1 : count * 6), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, dest, UnsafeCopy.DOUBLE_ARRAY_BASE + destOffset * 8L, count * 48L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src.get(srcOffset + _i);
        return dest;
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 357913941 ? -1 : count * 6), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, dest, UnsafeCopy.DOUBLE_ARRAY_BASE + destOffset * 8L, count * 48L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src.getDouble(srcOffset + _i * 8);
        return dest;
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 357913941 ? -1 : count * 6), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, src.address() + srcOffset, dest, UnsafeCopy.DOUBLE_ARRAY_BASE + destOffset * 8L, count * 48L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L);
        return dest;
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, long src) {
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 357913941 ? -1 : count * 6), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest, UnsafeCopy.DOUBLE_ARRAY_BASE + destOffset * 8L, count * 48L);
            return dest;
        }
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, count * 48L), 0L, count);
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 357913941 ? -1 : count * 6), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.DOUBLE_ARRAY_BASE + srcOffset * 8L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, count * 48L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src[srcOffset + _i]);
        return dest;
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, count * 48L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src.get(srcOffset + _i));
        return dest;
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, count * 48L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src.getDouble(srcOffset + _i * 8));
        return dest;
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isNative()) {
            UnsafeOpsHolder.U.copyMemory(null, src.address() + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, count * 48L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L));
        return dest;
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, long src) {
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, count * 48L);
            return dest;
        }
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, count * 48L), 0L, count);
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, double[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 357913941 ? -1 : count * 6), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.DOUBLE_ARRAY_BASE + srcOffset * 8L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 48L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest.putDouble(destOffset + _i * 8, src[srcOffset + _i]);
        return dest;
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 48L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest.putDouble(destOffset + _i * 8, src.get(srcOffset + _i));
        return dest;
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 48L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest.putDouble(destOffset + _i * 8, src.getDouble(srcOffset + _i * 8));
        return dest;
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isNative()) {
            UnsafeOpsHolder.U.copyMemory(null, src.address() + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 48L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest.putDouble(destOffset + _i * 8, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L));
        return dest;
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, long src) {
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 48L);
            return dest;
        }
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, count * 48L), 0L, count);
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, double[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, double[] src, int srcOffset, int count) {
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 357913941 ? -1 : count * 6), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.DOUBLE_ARRAY_BASE + srcOffset * 8L, null, dest.address() + destOffset, count * 48L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, src[srcOffset + _i]);
        return dest;
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, null, dest.address() + destOffset, count * 48L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, src.get(srcOffset + _i));
        return dest;
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, dest.address() + destOffset, count * 48L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, src.getDouble(srcOffset + _i * 8));
        return dest;
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count > 357913941) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API) return Double2x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) {
            UnsafeOpsHolder.U.copyMemory(null, src.address() + srcOffset, null, dest.address() + destOffset, count * 48L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 6;
        for (int _i = 0; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L));
        return dest;
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, long src) {
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L);
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, dest.address() + destOffset, count * 48L);
            return dest;
        }
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, count * 48L), 0L, count);
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, double[] src, int srcOffset) {
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, src, srcOffset);
        return dest;
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, double[] src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 357913941 ? -1 : count * 6), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.DOUBLE_ARRAY_BASE + srcOffset * 8L, null, dest, count * 48L);
            return dest;
        }
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, count * 48L), 0L, src, srcOffset, count);
        return dest;
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.nio.DoubleBuffer src, int srcOffset) {
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, src, srcOffset);
        return dest;
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, null, dest, count * 48L);
            return dest;
        }
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, count * 48L), 0L, src, srcOffset, count);
        return dest;
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.nio.ByteBuffer src, int srcOffset) {
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, src, srcOffset);
        return dest;
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, dest, count * 48L);
            return dest;
        }
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, count * 48L), 0L, src, srcOffset, count);
        return dest;
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.lang.foreign.MemorySegment src, long srcOffset) {
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, src, srcOffset);
        return dest;
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) {
            UnsafeOpsHolder.U.copyMemory(null, src.address() + srcOffset, null, dest, count * 48L);
            return dest;
        }
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, count * 48L), 0L, src, srcOffset, count);
        return dest;
    }

    /**
     * Copy one Double2x3 (6 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, long src) {
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 48L), 0L);
        return dest;
    }

    /**
     * Bulk-copy {@code count} consecutive Double2x3 values ({@code count * 6} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, dest, count * 48L);
            return dest;
        }
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, count * 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, count * 48L), 0L, count);
        return dest;
    }
    /** The double[] behind {@code s} when it is a writable heap segment over one whose byte
     *  {@code offset} starts a whole element; null otherwise (native, read-only, another array type,
     *  misaligned) - the segment path then serves the call. Neither the extent nor the segment's
     *  liveness or owner thread is checked: the caller guarantees all three. */
    private static double[] heapDoubles(java.lang.foreign.MemorySegment s, long offset) {
        if (s.isNative() || ((s.address() + offset) & 7L) != 0) return null;
        return s.heapBase().orElse(null) instanceof double[] a ? a : null;
    }

    /** The array index of byte {@code offset} of a heap segment, for elements of {@code 1 << shift} bytes. */
    private static int heapIndex(java.lang.foreign.MemorySegment s, long offset, int shift) {
        return (int) ((s.address() + offset) >>> shift);
    }
}
