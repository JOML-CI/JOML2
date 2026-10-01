// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.ops;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.kernels.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Static, allocation-free operations on raw storage holding a {@link Double3x3}.
 *
 * <p>Each method takes one or more buffers ({@code double[]},
 * {@link java.nio.DoubleBuffer}, {@link java.nio.ByteBuffer}, or
 * {@link java.lang.foreign.MemorySegment}) plus an element/byte offset and operates
 * directly on that storage. No {@link Double3x3} instance is allocated;
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
 * {@code store}/{@code load} of Double3x3 write and read.</p>
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
 * <p>Each method summary below is the one the {@link Double3x3} API carries, so
 * the two can never describe the same operation differently: "this matrix" there is the
 * matrix held in {@code src} at {@code srcOffset}, and the result is written to
 * {@code dest} at {@code destOffset}. The full text sits on the {@code double[]} overload of
 * each method; the other storage overloads point at it, differing from it in
 * storage alone.</p>
 */
public final class Double3x3Ops {
    private Double3x3Ops() {}

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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (col) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self10; _idxSw2 = _self20; break;
            case 1: _idxSw0 = _self01; _idxSw1 = _self11; _idxSw2 = _self21; break;
            case 2: _idxSw0 = _self02; _idxSw1 = _self12; _idxSw2 = _self22; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + col);
        }
        dest[destOffset] = _idxSw0;
        dest[destOffset + 1] = _idxSw1;
        dest[destOffset + 2] = _idxSw2;
        return dest;
    }

    /** {@link #getColumn(double[], int, double[], int, int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer getColumn(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int col) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.getColumn_unsafe(dest, destOffset, src, srcOffset, col);
        return Double3x3OpsKernelsTypedBuffer.getColumn_api(dest, destOffset, src, srcOffset, col);
    }

    /** {@link #getColumn(double[], int, double[], int, int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getColumn(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int col) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.getColumn_unsafe(dest, destOffset, src, srcOffset, col);
        return Double3x3OpsKernelsByteBuffer.getColumn_api(dest, destOffset, src, srcOffset, col);
    }

    /** {@link #getColumn(double[], int, double[], int, int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getColumn(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int col) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            getColumn(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), col);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.getColumn_unsafe(dest, destOffset, src, srcOffset, col);
        return Double3x3OpsKernelsSegment.getColumn_api(dest, destOffset, src, srcOffset, col);
    }

    /** {@link #getColumn(double[], int, double[], int, int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getColumn(long dest, long src, int col) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.getColumn_unsafe(dest, src, col);
        getColumn(VirtualMemoryHolder.virtualMemory().asSlice(dest, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, col);
        return dest;
    }

    /**
     * Get the Euler angles in radians of this matrix, to be applied about the X, Y and Z axes, in
     * that order and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationXYZ(e.x(), e.y(), e.z())}, which takes the angles in
     * application order, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] getEulerAnglesXYZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t1 = Math.fma(_self12, _self12, _self22 * _self22);
        if (_t1 < Math.fma(_self02, _self02, _t1) * 1.0E-15) {
            dest[destOffset] = Math.atan2(_self21, _self11);
            dest[destOffset + 2] = 0.0;
        } else {
            dest[destOffset] = Math.atan2(-_self12, _self22);
            dest[destOffset + 2] = Math.atan2(-_self01, _self00);
        }
        dest[destOffset + 1] = Math.atan2(_self02, java.lang.Math.sqrt(_t1));
        return dest;
    }

    /** {@link #getEulerAnglesXYZ(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer getEulerAnglesXYZ(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.getEulerAnglesXYZ_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.getEulerAnglesXYZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesXYZ(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getEulerAnglesXYZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.getEulerAnglesXYZ_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.getEulerAnglesXYZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesXYZ(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getEulerAnglesXYZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            getEulerAnglesXYZ(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.getEulerAnglesXYZ_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.getEulerAnglesXYZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesXYZ(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getEulerAnglesXYZ(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.getEulerAnglesXYZ_unsafe(dest, src);
        getEulerAnglesXYZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Get the Euler angles in radians of this matrix, to be applied about the X, Z and Y axes, in
     * that order and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationXZY(e.x(), e.z(), e.y())}, which takes the angles in
     * application order, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] getEulerAnglesXZY(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t1 = Math.fma(_self11, _self11, _self21 * _self21);
        if (_t1 < Math.fma(_self01, _self01, _t1) * 1.0E-15) {
            dest[destOffset] = Math.atan2(-_self12, _self22);
            dest[destOffset + 1] = 0.0;
        } else {
            dest[destOffset] = Math.atan2(_self21, _self11);
            dest[destOffset + 1] = Math.atan2(_self02, _self00);
        }
        dest[destOffset + 2] = Math.atan2(-_self01, java.lang.Math.sqrt(_t1));
        return dest;
    }

    /** {@link #getEulerAnglesXZY(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer getEulerAnglesXZY(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.getEulerAnglesXZY_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.getEulerAnglesXZY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesXZY(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getEulerAnglesXZY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.getEulerAnglesXZY_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.getEulerAnglesXZY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesXZY(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getEulerAnglesXZY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            getEulerAnglesXZY(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.getEulerAnglesXZY_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.getEulerAnglesXZY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesXZY(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getEulerAnglesXZY(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.getEulerAnglesXZY_unsafe(dest, src);
        getEulerAnglesXZY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Get the Euler angles in radians of this matrix, to be applied about the Y, X and Z axes, in
     * that order and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationYXZ(e.y(), e.x(), e.z())}, which takes the angles in
     * application order, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] getEulerAnglesYXZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 4];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t1 = Math.fma(_self02, _self02, _self22 * _self22);
        if (_t1 < Math.fma(_self12, _self12, _t1) * 1.0E-15) {
            dest[destOffset + 1] = Math.atan2(-_self20, _self00);
            dest[destOffset + 2] = 0.0;
        } else {
            dest[destOffset + 1] = Math.atan2(_self02, _self22);
            dest[destOffset + 2] = Math.atan2(_self10, _self11);
        }
        dest[destOffset] = Math.atan2(-_self12, java.lang.Math.sqrt(_t1));
        return dest;
    }

    /** {@link #getEulerAnglesYXZ(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer getEulerAnglesYXZ(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.getEulerAnglesYXZ_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.getEulerAnglesYXZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesYXZ(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getEulerAnglesYXZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.getEulerAnglesYXZ_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.getEulerAnglesYXZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesYXZ(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getEulerAnglesYXZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            getEulerAnglesYXZ(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.getEulerAnglesYXZ_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.getEulerAnglesYXZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesYXZ(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getEulerAnglesYXZ(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.getEulerAnglesYXZ_unsafe(dest, src);
        getEulerAnglesYXZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Get the Euler angles in radians of this matrix, to be applied about the Y, Z and X axes, in
     * that order and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationYZX(e.y(), e.z(), e.x())}, which takes the angles in
     * application order, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] getEulerAnglesYZX(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 4];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t1 = Math.fma(_self11, _self11, _self12 * _self12);
        if (_t1 < Math.fma(_self10, _self10, _t1) * 1.0E-15) {
            dest[destOffset] = 0.0;
            dest[destOffset + 1] = Math.atan2(_self02, _self22);
        } else {
            dest[destOffset] = Math.atan2(-_self12, _self11);
            dest[destOffset + 1] = Math.atan2(-_self20, _self00);
        }
        dest[destOffset + 2] = Math.atan2(_self10, java.lang.Math.sqrt(_t1));
        return dest;
    }

    /** {@link #getEulerAnglesYZX(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer getEulerAnglesYZX(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.getEulerAnglesYZX_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.getEulerAnglesYZX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesYZX(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getEulerAnglesYZX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.getEulerAnglesYZX_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.getEulerAnglesYZX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesYZX(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getEulerAnglesYZX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            getEulerAnglesYZX(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.getEulerAnglesYZX_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.getEulerAnglesYZX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesYZX(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getEulerAnglesYZX(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.getEulerAnglesYZX_unsafe(dest, src);
        getEulerAnglesYZX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Get the Euler angles in radians of this matrix, to be applied about the Z, X and Y axes, in
     * that order and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationZXY(e.z(), e.x(), e.y())}, which takes the angles in
     * application order, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] getEulerAnglesZXY(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self22 = src[srcOffset + 8];
        double _t1 = Math.fma(_self01, _self01, _self11 * _self11);
        if (_t1 < Math.fma(_self21, _self21, _t1) * 1.0E-15) {
            dest[destOffset + 1] = 0.0;
            dest[destOffset + 2] = Math.atan2(_self10, _self00);
        } else {
            dest[destOffset + 1] = Math.atan2(-_self20, _self22);
            dest[destOffset + 2] = Math.atan2(-_self01, _self11);
        }
        dest[destOffset] = Math.atan2(_self21, java.lang.Math.sqrt(_t1));
        return dest;
    }

    /** {@link #getEulerAnglesZXY(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer getEulerAnglesZXY(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.getEulerAnglesZXY_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.getEulerAnglesZXY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesZXY(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getEulerAnglesZXY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.getEulerAnglesZXY_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.getEulerAnglesZXY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesZXY(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getEulerAnglesZXY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            getEulerAnglesZXY(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.getEulerAnglesZXY_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.getEulerAnglesZXY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesZXY(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getEulerAnglesZXY(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.getEulerAnglesZXY_unsafe(dest, src);
        getEulerAnglesZXY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Get the Euler angles in radians of this matrix, to be applied about the Z, Y and X axes, in
     * that order and store the result in {@code dest}.
     * <p>
     * The result holds each angle at the component of its axis, not at its position in the order:
     * the angle about X in {@code x}, about Y in {@code y} and about Z in {@code z}. So, with
     * {@code e} the result, {@code makeRotationZYX(e.z(), e.y(), e.x())}, which takes the angles in
     * application order, rebuilds the rotation.
     * <p>
     * At gimbal lock (a middle rotation of ±90 degrees) the decomposition is not unique; one valid
     * set of angles is returned.
     * <p>
     * The middle angle is recovered with {@code atan2} rather than {@code asin}, so it keeps full
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] getEulerAnglesZYX(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self22 = src[srcOffset + 8];
        double _t1 = Math.fma(_self21, _self21, _self22 * _self22);
        if (_t1 < Math.fma(_self20, _self20, _t1) * 1.0E-15) {
            dest[destOffset] = 0.0;
            dest[destOffset + 2] = Math.atan2(-_self01, _self11);
        } else {
            dest[destOffset] = Math.atan2(_self21, _self22);
            dest[destOffset + 2] = Math.atan2(_self10, _self00);
        }
        dest[destOffset + 1] = Math.atan2(-_self20, java.lang.Math.sqrt(_t1));
        return dest;
    }

    /** {@link #getEulerAnglesZYX(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer getEulerAnglesZYX(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.getEulerAnglesZYX_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.getEulerAnglesZYX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesZYX(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getEulerAnglesZYX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.getEulerAnglesZYX_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.getEulerAnglesZYX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesZYX(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getEulerAnglesZYX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            getEulerAnglesZYX(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.getEulerAnglesZYX_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.getEulerAnglesZYX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesZYX(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getEulerAnglesZYX(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.getEulerAnglesZYX_unsafe(dest, src);
        getEulerAnglesZYX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Extract the rotation of this matrix as a quaternion, column-normalizing the linear block
     * first to strip scale (skew is not removed: a sheared block yields a quaternion that is not
     * unit length) and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] getNormalizedRotation(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t6 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        double _t7 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        double _t8 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t6));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t11 = (1.0 / java.lang.Math.sqrt(_t8));
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
        return getNormalizedRotation_s9f5a0668_1(dest, destOffset, _t21, _t23, _t27, _t22, _t24, _t26, _t25, _t28, _t29, _t27 - _t22, _t27 + _t22);
    }

    /** Piece 2 of {@code getNormalizedRotation}, split to fit the inline budget; reached only through it. */
    private static double[] getNormalizedRotation_s9f5a0668_1(double[] dest, int destOffset, double _t21, double _t23, double _t27, double _t22, double _t24, double _t26, double _t25, double _t28, double _t29, double _t36, double _t39) {
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
        return getNormalizedRotation_s9f5a0668_2(dest, destOffset, _t23, _t26, _t36, _t39, _t49, _t50 + _t21, _t51 + _t24, _t24 - _t51, _t50 - _t21, _t58, _t62, _t63, _t64, _t65, 0.5 * (1.0 / java.lang.Math.sqrt(_t62)), 0.5 * (1.0 / java.lang.Math.sqrt(_t64)), 0.5 * (1.0 / java.lang.Math.sqrt(_t65)), 0.5 * (1.0 / java.lang.Math.sqrt(_t63)));
    }

    /** Piece 3 of {@code getNormalizedRotation}, split to fit the inline budget; reached only through it. */
    private static double[] getNormalizedRotation_s9f5a0668_2(double[] dest, int destOffset, double _t23, double _t26, double _t36, double _t39, double _t49, double _t53, double _t55, double _t56, double _t57, double _t58, double _t62, double _t63, double _t64, double _t65, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t58 > 0.0) {
            dest[destOffset] = _sp0 * _t36;
            dest[destOffset + 1] = _sp0 * _t56;
            dest[destOffset + 2] = _sp0 * _t57;
            dest[destOffset + 3] = 0.5 * java.lang.Math.sqrt(_t62);
        } else {
            if (_t49 > java.lang.Math.max(_t23, _t26)) {
                dest[destOffset] = 0.5 * java.lang.Math.sqrt(_t63);
                dest[destOffset + 1] = _sp3 * _t53;
                dest[destOffset + 2] = _sp3 * _t55;
                dest[destOffset + 3] = _sp3 * _t36;
            } else {
                if (_t23 > _t26) {
                    dest[destOffset] = _sp1 * _t53;
                    dest[destOffset + 1] = 0.5 * java.lang.Math.sqrt(_t64);
                    dest[destOffset + 2] = _sp1 * _t39;
                    dest[destOffset + 3] = _sp1 * _t56;
                } else {
                    dest[destOffset] = _sp2 * _t55;
                    dest[destOffset + 1] = _sp2 * _t39;
                    dest[destOffset + 2] = 0.5 * java.lang.Math.sqrt(_t65);
                    dest[destOffset + 3] = _sp2 * _t57;
                }
            }
        }
        return dest;
    }

    /** {@link #getNormalizedRotation(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer getNormalizedRotation(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.getNormalizedRotation_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.getNormalizedRotation_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getNormalizedRotation(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getNormalizedRotation(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.getNormalizedRotation_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.getNormalizedRotation_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getNormalizedRotation(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getNormalizedRotation(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            getNormalizedRotation(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.getNormalizedRotation_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.getNormalizedRotation_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getNormalizedRotation(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getNormalizedRotation(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.getNormalizedRotation_unsafe(dest, src);
        getNormalizedRotation(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
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
     * @throws IndexOutOfBoundsException if {@code row} is not in {@code [0, 3)}
     */
    public static double[] getRow(double[] dest, int destOffset, double[] src, int srcOffset, int row) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _idxSw0;
        double _idxSw1;
        double _idxSw2;
        switch (row) {
            case 0: _idxSw0 = _self00; _idxSw1 = _self01; _idxSw2 = _self02; break;
            case 1: _idxSw0 = _self10; _idxSw1 = _self11; _idxSw2 = _self12; break;
            case 2: _idxSw0 = _self20; _idxSw1 = _self21; _idxSw2 = _self22; break;
            default: throw new IndexOutOfBoundsException("Index out of range: " + row);
        }
        dest[destOffset] = _idxSw0;
        dest[destOffset + 1] = _idxSw1;
        dest[destOffset + 2] = _idxSw2;
        return dest;
    }

    /** {@link #getRow(double[], int, double[], int, int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer getRow(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int row) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.getRow_unsafe(dest, destOffset, src, srcOffset, row);
        return Double3x3OpsKernelsTypedBuffer.getRow_api(dest, destOffset, src, srcOffset, row);
    }

    /** {@link #getRow(double[], int, double[], int, int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getRow(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int row) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.getRow_unsafe(dest, destOffset, src, srcOffset, row);
        return Double3x3OpsKernelsByteBuffer.getRow_api(dest, destOffset, src, srcOffset, row);
    }

    /** {@link #getRow(double[], int, double[], int, int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getRow(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int row) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            getRow(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), row);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.getRow_unsafe(dest, destOffset, src, srcOffset, row);
        return Double3x3OpsKernelsSegment.getRow_api(dest, destOffset, src, srcOffset, row);
    }

    /** {@link #getRow(double[], int, double[], int, int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getRow(long dest, long src, int row) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.getRow_unsafe(dest, src, row);
        getRow(VirtualMemoryHolder.virtualMemory().asSlice(dest, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, row);
        return dest;
    }

    /**
     * Get the scaling factors of this matrix, as the lengths of its basis columns (always
     * non-negative; skew is ignored) and store the result in {@code dest}.
     * <p>
     * For a 2D homogeneous 3x3 matrix the third factor is simply the length of the third column -
     * {@code sqrt(m02² + m12² + 1)} for a 2D affine transform, not a scale of anything.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] getScale(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        dest[destOffset] = java.lang.Math.sqrt(Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10)));
        dest[destOffset + 1] = java.lang.Math.sqrt(Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11)));
        dest[destOffset + 2] = java.lang.Math.sqrt(Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12)));
        return dest;
    }

    /** {@link #getScale(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer getScale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.getScale_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.getScale_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getScale(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getScale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.getScale_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.getScale_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getScale(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getScale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            getScale(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.getScale_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.getScale_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getScale(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getScale(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.getScale_unsafe(dest, src);
        getScale(VirtualMemoryHolder.virtualMemory().asSlice(dest, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Get the translation of this matrix, read from its last column as {@code (m02, m12)} (the 2D
     * homogeneous convention) and store the result in {@code dest}.
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
        double _self12 = src[srcOffset + 7];
        dest[destOffset] = src[srcOffset + 6];
        dest[destOffset + 1] = _self12;
        return dest;
    }

    /** {@link #getTranslation(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer getTranslation(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.getTranslation_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.getTranslation_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getTranslation(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getTranslation(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.getTranslation_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.getTranslation_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getTranslation(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getTranslation(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            getTranslation(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.getTranslation_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.getTranslation_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getTranslation(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getTranslation(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.getTranslation_unsafe(dest, src);
        getTranslation(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Extract the rotation of this matrix as a quaternion directly from the linear block, without
     * normalizing it and store the result in {@code dest}.
     * <p>
     * Valid input: this matrix must be a rotation matrix.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] getUnnormalizedRotation(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t0 = _self00 + _self11;
        double _t10 = _self22 + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (_self00 - (_self11 + _self22));
        double _t16 = 1.0 + (_self11 - (_self00 + _self22));
        double _t17 = 1.0 + (_self22 - _t0);
        return getUnnormalizedRotation_s7abc138d_1(dest, destOffset, _self00, _self11, _self22, _self21 - _self12, _self01 + _self10, _self02 + _self20, _self02 - _self20, _self12 + _self21, _self10 - _self01, _t10, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code getUnnormalizedRotation}, split to fit the inline budget; reached only through it. */
    private static double[] getUnnormalizedRotation_s7abc138d_1(double[] dest, int destOffset, double _self00, double _self11, double _self22, double _t1, double _t4, double _t6, double _t7, double _t8, double _t9, double _t10, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t10 > 0.0) {
            dest[destOffset] = _sp0 * _t1;
            dest[destOffset + 1] = _sp0 * _t7;
            dest[destOffset + 2] = _sp0 * _t9;
            dest[destOffset + 3] = 0.5 * java.lang.Math.sqrt(_t14);
        } else {
            if (_self00 > java.lang.Math.max(_self11, _self22)) {
                dest[destOffset] = 0.5 * java.lang.Math.sqrt(_t15);
                dest[destOffset + 1] = _sp3 * _t4;
                dest[destOffset + 2] = _sp3 * _t6;
                dest[destOffset + 3] = _sp3 * _t1;
            } else {
                if (_self11 > _self22) {
                    dest[destOffset] = _sp1 * _t4;
                    dest[destOffset + 1] = 0.5 * java.lang.Math.sqrt(_t16);
                    dest[destOffset + 2] = _sp1 * _t8;
                    dest[destOffset + 3] = _sp1 * _t7;
                } else {
                    dest[destOffset] = _sp2 * _t6;
                    dest[destOffset + 1] = _sp2 * _t8;
                    dest[destOffset + 2] = 0.5 * java.lang.Math.sqrt(_t17);
                    dest[destOffset + 3] = _sp2 * _t9;
                }
            }
        }
        return dest;
    }

    /** {@link #getUnnormalizedRotation(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer getUnnormalizedRotation(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.getUnnormalizedRotation_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.getUnnormalizedRotation_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getUnnormalizedRotation(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getUnnormalizedRotation(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.getUnnormalizedRotation_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.getUnnormalizedRotation_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getUnnormalizedRotation(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getUnnormalizedRotation(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            getUnnormalizedRotation(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.getUnnormalizedRotation_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.getUnnormalizedRotation_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getUnnormalizedRotation(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getUnnormalizedRotation(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.getUnnormalizedRotation_unsafe(dest, src);
        getUnnormalizedRotation(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        dest[destOffset] = Math.fma(_self11, _self22, -(_self12 * _self21));
        dest[destOffset + 1] = Math.fma(_self02, _self21, -(_self01 * _self22));
        dest[destOffset + 2] = Math.fma(_self01, _self12, -(_self02 * _self11));
        dest[destOffset + 3] = Math.fma(_self12, _self20, -(_self10 * _self22));
        dest[destOffset + 4] = Math.fma(_self00, _self22, -(_self02 * _self20));
        dest[destOffset + 5] = Math.fma(_self02, _self10, -(_self00 * _self12));
        dest[destOffset + 6] = Math.fma(_self10, _self21, -(_self11 * _self20));
        dest[destOffset + 7] = Math.fma(_self01, _self20, -(_self00 * _self21));
        dest[destOffset + 8] = Math.fma(_self00, _self11, -(_self01 * _self10));
        return dest;
    }

    /** {@link #cofactor(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer cofactor(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.cofactor_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.cofactor_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cofactor(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer cofactor(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.cofactor_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.cofactor_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cofactor(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment cofactor(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            cofactor(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.cofactor_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.cofactor_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cofactor(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long cofactor(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.cofactor_unsafe(dest, src);
        cofactor(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
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
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        return Math.fma(src[srcOffset + 6], Math.fma(_self10, _self21, -(_self11 * _self20)), Math.fma(src[srcOffset], Math.fma(_self11, _self22, -(_self12 * _self21)), -(src[srcOffset + 3] * Math.fma(_self10, _self22, -(_self12 * _self20)))));
    }

    /** {@link #determinant(double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double determinant(java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.determinant_unsafe(src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.determinant_api(src, srcOffset);
    }

    /** {@link #determinant(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double determinant(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.determinant_unsafe(src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.determinant_api(src, srcOffset);
    }

    /** {@link #determinant(double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double determinant(java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _srcArray;
        if ((_srcArray = heapDoubles(src, srcOffset)) != null) {
            return determinant(_srcArray, heapIndex(src, srcOffset, 3));
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Double3x3OpsKernelsSegment.determinant_unsafe(src, srcOffset);
        return Double3x3OpsKernelsSegment.determinant_api(src, srcOffset);
    }

    /** {@link #determinant(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static double determinant(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.determinant_unsafe(src);
        return determinant(VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        return java.lang.Math.sqrt(Math.fma(_self00, _self00, _self01 * _self01) + Math.fma(_self02, _self02, _self10 * _self10) + (Math.fma(_self11, _self11, _self12 * _self12) + Math.fma(_self20, _self20, Math.fma(_self21, _self21, _self22 * _self22))));
    }

    /** {@link #frobeniusNorm(double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double frobeniusNorm(java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.frobeniusNorm_unsafe(src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.frobeniusNorm_api(src, srcOffset);
    }

    /** {@link #frobeniusNorm(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double frobeniusNorm(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.frobeniusNorm_unsafe(src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.frobeniusNorm_api(src, srcOffset);
    }

    /** {@link #frobeniusNorm(double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double frobeniusNorm(java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _srcArray;
        if ((_srcArray = heapDoubles(src, srcOffset)) != null) {
            return frobeniusNorm(_srcArray, heapIndex(src, srcOffset, 3));
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Double3x3OpsKernelsSegment.frobeniusNorm_unsafe(src, srcOffset);
        return Double3x3OpsKernelsSegment.frobeniusNorm_api(src, srcOffset);
    }

    /** {@link #frobeniusNorm(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static double frobeniusNorm(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.frobeniusNorm_unsafe(src);
        return frobeniusNorm(VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        double _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        double _t13 = Math.fma(_self02, _t7, Math.fma(_self00, _t6, -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
        if (!(java.lang.Math.abs(_t13) > 2.2250738585072014E-308 && java.lang.Math.abs(_t13) < 4.49423283715579E307)) return Double3x3OpsKernelsArray.invert_degenerate(dest, destOffset, src, srcOffset);
        double _t13_inv = 1.0 / _t13;
        dest[destOffset] = _t6 * _t13_inv;
        dest[destOffset + 1] = Math.fma(_self12, _self20, -(_self10 * _self22)) * _t13_inv;
        dest[destOffset + 2] = _t7 * _t13_inv;
        dest[destOffset + 3] = Math.fma(_self02, _self21, -(_self01 * _self22)) * _t13_inv;
        dest[destOffset + 4] = Math.fma(_self00, _self22, -(_self02 * _self20)) * _t13_inv;
        dest[destOffset + 5] = Math.fma(_self01, _self20, -(_self00 * _self21)) * _t13_inv;
        return invert_sb8af583_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12, _t13_inv);
    }

    /** Piece 2 of {@code invert}, split to fit the inline budget; reached only through it. */
    private static double[] invert_sb8af583_1(double[] dest, int destOffset, double _self00, double _self10, double _self01, double _self11, double _self02, double _self12, double _t13_inv) {
        dest[destOffset + 6] = Math.fma(_self01, _self12, -(_self02 * _self11)) * _t13_inv;
        dest[destOffset + 7] = Math.fma(_self02, _self10, -(_self00 * _self12)) * _t13_inv;
        dest[destOffset + 8] = Math.fma(_self00, _self11, -(_self01 * _self10)) * _t13_inv;
        return dest;
    }

    /** {@link #invert(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer invert(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.invert_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.invert_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invert(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invert(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.invert_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.invert_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invert(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment invert(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            invert(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.invert_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.invert_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invert(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long invert(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.invert_unsafe(dest, src);
        invert(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _other00 = other[otherOffset];
        double _other10 = other[otherOffset + 1];
        double _other20 = other[otherOffset + 2];
        double _other01 = other[otherOffset + 3];
        double _other11 = other[otherOffset + 4];
        double _other21 = other[otherOffset + 5];
        double _other02 = other[otherOffset + 6];
        double _other12 = other[otherOffset + 7];
        double _other22 = other[otherOffset + 8];
        return invertProduct_sf4891159_1(dest, destOffset, src, srcOffset, other, otherOffset, _self00, _self20, _self01, _self21, _self02, _self22, _other00, _other10, _other20, _other01, _other11, _other21, Math.fma(_other21, _self12, Math.fma(_other01, _self10, _other11 * _self11)), Math.fma(_other22, _self22, Math.fma(_other02, _self20, _other12 * _self21)), Math.fma(_other21, _self22, Math.fma(_other01, _self20, _other11 * _self21)), Math.fma(_other22, _self12, Math.fma(_other02, _self10, _other12 * _self11)), Math.fma(_other22, _self02, Math.fma(_other02, _self00, _other12 * _self01)), Math.fma(_other20, _self12, Math.fma(_other00, _self10, _other10 * _self11)));
    }

    /** Piece 2 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private static double[] invertProduct_sf4891159_1(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double _self00, double _self20, double _self01, double _self21, double _self02, double _self22, double _other00, double _other10, double _other20, double _other01, double _other11, double _other21, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23) {
        double _t24 = Math.fma(_other20, _self22, Math.fma(_other00, _self20, _other10 * _self21));
        double _t25 = Math.fma(_other20, _self02, Math.fma(_other00, _self00, _other10 * _self01));
        double _t26 = Math.fma(_other21, _self02, Math.fma(_other01, _self00, _other11 * _self01));
        double _t33 = Math.fma(_t18, _t19, -(_t20 * _t21));
        double _t34 = Math.fma(_t23, _t20, -(_t24 * _t18));
        double _t40 = Math.fma(_t22, _t34, Math.fma(_t25, _t33, -(_t26 * Math.fma(_t23, _t19, -(_t24 * _t21)))));
        if (!(java.lang.Math.abs(_t40) > 2.2250738585072014E-308 && java.lang.Math.abs(_t40) < 4.49423283715579E307)) return Double3x3OpsKernelsArray.invertProduct_degenerate(dest, destOffset, src, srcOffset, other, otherOffset);
        double _t40_inv = 1.0 / _t40;
        dest[destOffset] = _t33 * _t40_inv;
        dest[destOffset + 1] = Math.fma(_t24, _t21, -(_t23 * _t19)) * _t40_inv;
        dest[destOffset + 2] = _t34 * _t40_inv;
        dest[destOffset + 3] = Math.fma(_t20, _t22, -(_t26 * _t19)) * _t40_inv;
        dest[destOffset + 4] = Math.fma(_t25, _t19, -(_t24 * _t22)) * _t40_inv;
        dest[destOffset + 5] = Math.fma(_t24, _t26, -(_t25 * _t20)) * _t40_inv;
        return invertProduct_sf4891159_2(dest, destOffset, _t18, _t21, _t22, _t23, _t25, _t26, _t40_inv);
    }

    /** Piece 3 of {@code invertProduct}, split to fit the inline budget; reached only through it. */
    private static double[] invertProduct_sf4891159_2(double[] dest, int destOffset, double _t18, double _t21, double _t22, double _t23, double _t25, double _t26, double _t40_inv) {
        dest[destOffset + 6] = Math.fma(_t26, _t21, -(_t18 * _t22)) * _t40_inv;
        dest[destOffset + 7] = Math.fma(_t23, _t22, -(_t25 * _t21)) * _t40_inv;
        dest[destOffset + 8] = Math.fma(_t25, _t18, -(_t23 * _t26)) * _t40_inv;
        return dest;
    }

    /** {@link #invertProduct(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer invertProduct(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.invertProduct_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsTypedBuffer.invertProduct_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #invertProduct(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invertProduct(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.invertProduct_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsByteBuffer.invertProduct_api(dest, destOffset, src, srcOffset, other, otherOffset);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double3x3OpsKernelsSegment.invertProduct_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsSegment.invertProduct_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #invertProduct(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long invertProduct(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.invertProduct_unsafe(dest, src, other);
        invertProduct(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 72L), 0L);
        return dest;
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t6 = Math.fma(_self11, _self22, -(_self12 * _self21));
        double _t7 = Math.fma(_self10, _self21, -(_self11 * _self20));
        double _t13 = Math.fma(_self02, _t7, Math.fma(_self00, _t6, -(_self01 * Math.fma(_self10, _self22, -(_self12 * _self20)))));
        if (!(java.lang.Math.abs(_t13) > 2.2250738585072014E-308 && java.lang.Math.abs(_t13) < 4.49423283715579E307)) return Double3x3OpsKernelsArray.normal_degenerate(dest, destOffset, src, srcOffset);
        double _t13_inv = 1.0 / _t13;
        dest[destOffset] = _t6 * _t13_inv;
        dest[destOffset + 1] = Math.fma(_self02, _self21, -(_self01 * _self22)) * _t13_inv;
        dest[destOffset + 2] = Math.fma(_self01, _self12, -(_self02 * _self11)) * _t13_inv;
        dest[destOffset + 3] = Math.fma(_self12, _self20, -(_self10 * _self22)) * _t13_inv;
        dest[destOffset + 4] = Math.fma(_self00, _self22, -(_self02 * _self20)) * _t13_inv;
        dest[destOffset + 5] = Math.fma(_self02, _self10, -(_self00 * _self12)) * _t13_inv;
        return normal_s265fedee_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _t7, _t13_inv);
    }

    /** Piece 2 of {@code normal}, split to fit the inline budget; reached only through it. */
    private static double[] normal_s265fedee_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _t7, double _t13_inv) {
        dest[destOffset + 6] = _t7 * _t13_inv;
        dest[destOffset + 7] = Math.fma(_self01, _self20, -(_self00 * _self21)) * _t13_inv;
        dest[destOffset + 8] = Math.fma(_self00, _self11, -(_self01 * _self10)) * _t13_inv;
        return dest;
    }

    /** {@link #normal(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer normal(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.normal_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.normal_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normal(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer normal(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.normal_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.normal_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normal(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment normal(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            normal(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.normal_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.normal_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normal(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long normal(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.normal_unsafe(dest, src);
        normal(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
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
        return src[srcOffset + 8] + (src[srcOffset] + src[srcOffset + 4]);
    }

    /** {@link #trace(double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double trace(java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.trace_unsafe(src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.trace_api(src, srcOffset);
    }

    /** {@link #trace(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double trace(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.trace_unsafe(src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.trace_api(src, srcOffset);
    }

    /** {@link #trace(double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double trace(java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _srcArray;
        if ((_srcArray = heapDoubles(src, srcOffset)) != null) {
            return trace(_srcArray, heapIndex(src, srcOffset, 3));
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Double3x3OpsKernelsSegment.trace_unsafe(src, srcOffset);
        return Double3x3OpsKernelsSegment.trace_api(src, srcOffset);
    }

    /** {@link #trace(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static double trace(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.trace_unsafe(src);
        return trace(VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self01;
        dest[destOffset + 2] = _self02;
        dest[destOffset + 3] = _self10;
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = _self12;
        dest[destOffset + 6] = _self20;
        dest[destOffset + 7] = _self21;
        dest[destOffset + 8] = _self22;
        return dest;
    }

    /** {@link #transpose(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer transpose(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.transpose_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.transpose_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #transpose(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer transpose(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.transpose_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.transpose_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #transpose(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment transpose(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            transpose(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.transpose_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.transpose_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #transpose(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long transpose(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.transpose_unsafe(dest, src);
        transpose(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
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
        for (int _i = 0; _i < 9; _i++) {
            dest[destOffset + _i] = other[otherOffset + _i] + src[srcOffset + _i];
        }
        return dest;
    }

    /** {@link #add(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer add(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.add_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsTypedBuffer.add_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer add(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.add_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsByteBuffer.add_api(dest, destOffset, src, srcOffset, other, otherOffset);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double3x3OpsKernelsSegment.add_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsSegment.add_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long add(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.add_unsafe(dest, src, other);
        add(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 72L), 0L);
        return dest;
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
        for (int _i = 0; _i < 9; _i++) {
            dest[destOffset + _i] = scalar * src[srcOffset + _i];
        }
        return dest;
    }

    /** {@link #mul(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mul(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.mul_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Double3x3OpsKernelsTypedBuffer.mul_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.mul_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Double3x3OpsKernelsByteBuffer.mul_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            mul(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), scalar);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.mul_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Double3x3OpsKernelsSegment.mul_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mul(long dest, long src, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.mul_unsafe(dest, src, scalar);
        mul(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, scalar);
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
        for (int _i = 0; _i < 9; _i++) {
            dest[destOffset + _i] = -src[srcOffset + _i];
        }
        return dest;
    }

    /** {@link #negate(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer negate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.negate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.negate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer negate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.negate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.negate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment negate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            negate(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.negate_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.negate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long negate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.negate_unsafe(dest, src);
        negate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
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
        for (int _i = 0; _i < 9; _i++) {
            dest[destOffset + _i] = src[srcOffset + _i] - other[otherOffset + _i];
        }
        return dest;
    }

    /** {@link #sub(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer sub(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.sub_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsTypedBuffer.sub_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sub(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.sub_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsByteBuffer.sub_api(dest, destOffset, src, srcOffset, other, otherOffset);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double3x3OpsKernelsSegment.sub_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsSegment.sub_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long sub(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.sub_unsafe(dest, src, other);
        sub(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 72L), 0L);
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
        if (SimdSupport.DOUBLE_COLUMNS) return Double3x3OpsSimd.set(dest, destOffset, v, vOffset);
        return Double3x3OpsKernelsArray.set_scalar(dest, destOffset, v, vOffset);
    }

    /** {@link #set(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer set(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.set_unsafe(dest, destOffset, v, vOffset);
        return Double3x3OpsKernelsTypedBuffer.set_api(dest, destOffset, v, vOffset);
    }

    /** {@link #set(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer set(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.set_unsafe(dest, destOffset, v, vOffset);
        return Double3x3OpsKernelsByteBuffer.set_api(dest, destOffset, v, vOffset);
    }

    /** {@link #set(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment set(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double[] _destArray, _vArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_vArray = heapDoubles(v, vOffset)) != null) {
            set(_destArray, heapIndex(dest, destOffset, 3), _vArray, heapIndex(v, vOffset, 3));
            return dest;
        }
        if (SimdSupport.DOUBLE_COLUMNS) return Double3x3OpsSimd.set(dest, destOffset, v, vOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && v.isNative()) return Double3x3OpsKernelsSegment.set_unsafe(dest, destOffset, v, vOffset);
        return Double3x3OpsKernelsSegment.set_api(dest, destOffset, v, vOffset);
    }

    /** {@link #set(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long set(long dest, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.set_unsafe(dest, v);
        set(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 72L), 0L);
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
        double _m10 = m[mOffset + 1];
        double _m01 = m[mOffset + 2];
        double _m11 = m[mOffset + 3];
        dest[destOffset] = m[mOffset];
        dest[destOffset + 1] = _m10;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = _m01;
        dest[destOffset + 4] = _m11;
        dest[destOffset + 5] = 0.0;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 1.0;
        return dest;
    }

    /** {@link #setMat2x2(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer setMat2x2(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.setMat2x2_unsafe(dest, destOffset, m, mOffset);
        return Double3x3OpsKernelsTypedBuffer.setMat2x2_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat2x2(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer setMat2x2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.setMat2x2_unsafe(dest, destOffset, m, mOffset);
        return Double3x3OpsKernelsByteBuffer.setMat2x2_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat2x2(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment setMat2x2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        double[] _destArray, _mArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_mArray = heapDoubles(m, mOffset)) != null) {
            setMat2x2(_destArray, heapIndex(dest, destOffset, 3), _mArray, heapIndex(m, mOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && m.isNative()) return Double3x3OpsKernelsSegment.setMat2x2_unsafe(dest, destOffset, m, mOffset);
        return Double3x3OpsKernelsSegment.setMat2x2_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat2x2(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long setMat2x2(long dest, long m) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.setMat2x2_unsafe(dest, m);
        setMat2x2(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(m, 32L), 0L);
        return dest;
    }

    /**
     * Set this matrix to the given 2x3 matrix, copying the overlapping cells and filling the rest
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
    public static double[] setMat2x3(double[] dest, int destOffset, double[] m, int mOffset) {
        double _m10 = m[mOffset + 1];
        double _m01 = m[mOffset + 2];
        double _m11 = m[mOffset + 3];
        double _m02 = m[mOffset + 4];
        double _m12 = m[mOffset + 5];
        dest[destOffset] = m[mOffset];
        dest[destOffset + 1] = _m10;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = _m01;
        dest[destOffset + 4] = _m11;
        dest[destOffset + 5] = 0.0;
        dest[destOffset + 6] = _m02;
        dest[destOffset + 7] = _m12;
        dest[destOffset + 8] = 1.0;
        return dest;
    }

    /** {@link #setMat2x3(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer setMat2x3(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.setMat2x3_unsafe(dest, destOffset, m, mOffset);
        return Double3x3OpsKernelsTypedBuffer.setMat2x3_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat2x3(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer setMat2x3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.setMat2x3_unsafe(dest, destOffset, m, mOffset);
        return Double3x3OpsKernelsByteBuffer.setMat2x3_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat2x3(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment setMat2x3(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        double[] _destArray, _mArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_mArray = heapDoubles(m, mOffset)) != null) {
            setMat2x3(_destArray, heapIndex(dest, destOffset, 3), _mArray, heapIndex(m, mOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && m.isNative()) return Double3x3OpsKernelsSegment.setMat2x3_unsafe(dest, destOffset, m, mOffset);
        return Double3x3OpsKernelsSegment.setMat2x3_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat2x3(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long setMat2x3(long dest, long m) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.setMat2x3_unsafe(dest, m);
        setMat2x3(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(m, 48L), 0L);
        return dest;
    }

    /**
     * Set this matrix to the given 3x4 matrix, copying the overlapping cells and dropping the rest.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param m the storage holding the matrix to copy from
     * @param mOffset the element index in {@code m} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] setMat3x4(double[] dest, int destOffset, double[] m, int mOffset) {
        double _m01 = m[mOffset + 1];
        double _m02 = m[mOffset + 2];
        double _m10 = m[mOffset + 4];
        double _m11 = m[mOffset + 5];
        double _m12 = m[mOffset + 6];
        double _m20 = m[mOffset + 8];
        double _m21 = m[mOffset + 9];
        double _m22 = m[mOffset + 10];
        dest[destOffset] = m[mOffset];
        dest[destOffset + 1] = _m10;
        dest[destOffset + 2] = _m20;
        dest[destOffset + 3] = _m01;
        dest[destOffset + 4] = _m11;
        dest[destOffset + 5] = _m21;
        dest[destOffset + 6] = _m02;
        dest[destOffset + 7] = _m12;
        dest[destOffset + 8] = _m22;
        return dest;
    }

    /** {@link #setMat3x4(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer setMat3x4(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.setMat3x4_unsafe(dest, destOffset, m, mOffset);
        return Double3x3OpsKernelsTypedBuffer.setMat3x4_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat3x4(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer setMat3x4(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.setMat3x4_unsafe(dest, destOffset, m, mOffset);
        return Double3x3OpsKernelsByteBuffer.setMat3x4_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat3x4(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment setMat3x4(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        double[] _destArray, _mArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_mArray = heapDoubles(m, mOffset)) != null) {
            setMat3x4(_destArray, heapIndex(dest, destOffset, 3), _mArray, heapIndex(m, mOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && m.isNative()) return Double3x3OpsKernelsSegment.setMat3x4_unsafe(dest, destOffset, m, mOffset);
        return Double3x3OpsKernelsSegment.setMat3x4_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat3x4(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long setMat3x4(long dest, long m) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.setMat3x4_unsafe(dest, m);
        setMat3x4(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(m, 96L), 0L);
        return dest;
    }

    /**
     * Set this matrix to the given 4x4 matrix, copying the overlapping cells and dropping the rest.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param m the storage holding the matrix to copy from
     * @param mOffset the element index in {@code m} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] setMat4x4(double[] dest, int destOffset, double[] m, int mOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            int _lom = _l * 4;
            double _em1 = m[mOffset + _lom + 1];
            double _em2 = m[mOffset + _lom + 2];
            dest[destOffset + _lo] = m[mOffset + _lom];
            dest[destOffset + _lo + 1] = _em1;
            dest[destOffset + _lo + 2] = _em2;
        }
        return dest;
    }

    /** {@link #setMat4x4(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer setMat4x4(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.setMat4x4_unsafe(dest, destOffset, m, mOffset);
        return Double3x3OpsKernelsTypedBuffer.setMat4x4_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat4x4(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer setMat4x4(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.setMat4x4_unsafe(dest, destOffset, m, mOffset);
        return Double3x3OpsKernelsByteBuffer.setMat4x4_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat4x4(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment setMat4x4(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        double[] _destArray, _mArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_mArray = heapDoubles(m, mOffset)) != null) {
            setMat4x4(_destArray, heapIndex(dest, destOffset, 3), _mArray, heapIndex(m, mOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && m.isNative()) return Double3x3OpsKernelsSegment.setMat4x4_unsafe(dest, destOffset, m, mOffset);
        return Double3x3OpsKernelsSegment.setMat4x4_api(dest, destOffset, m, mOffset);
    }

    /** {@link #setMat4x4(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long setMat4x4(long dest, long m) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.setMat4x4_unsafe(dest, m);
        setMat4x4(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(m, 128L), 0L);
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
        if (SimdSupport.DOUBLE_COLUMNS) return Double3x3OpsSimd.withTranslation(dest, destOffset, src, srcOffset, tX, tY);
        return Double3x3OpsKernelsArray.withTranslation_scalar(dest, destOffset, src, srcOffset, tX, tY);
    }

    /** {@link #withTranslation(double[], int, double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer withTranslation(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double tX, double tY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.withTranslation_unsafe(dest, destOffset, src, srcOffset, tX, tY);
        return Double3x3OpsKernelsTypedBuffer.withTranslation_api(dest, destOffset, src, srcOffset, tX, tY);
    }

    /** {@link #withTranslation(double[], int, double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer withTranslation(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double tX, double tY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.withTranslation_unsafe(dest, destOffset, src, srcOffset, tX, tY);
        return Double3x3OpsKernelsByteBuffer.withTranslation_api(dest, destOffset, src, srcOffset, tX, tY);
    }

    /** {@link #withTranslation(double[], int, double[], int, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment withTranslation(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double tX, double tY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            withTranslation(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), tX, tY);
            return dest;
        }
        if (SimdSupport.DOUBLE_COLUMNS) return Double3x3OpsSimd.withTranslation(dest, destOffset, src, srcOffset, tX, tY);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.withTranslation_unsafe(dest, destOffset, src, srcOffset, tX, tY);
        return Double3x3OpsKernelsSegment.withTranslation_api(dest, destOffset, src, srcOffset, tX, tY);
    }

    /** {@link #withTranslation(double[], int, double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long withTranslation(long dest, long src, double tX, double tY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.withTranslation_unsafe(dest, src, tX, tY);
        withTranslation(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, tX, tY);
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
        if (SimdSupport.DOUBLE_COLUMNS) return Double3x3OpsSimd.withTranslation(dest, destOffset, src, srcOffset, t, tOffset);
        return Double3x3OpsKernelsArray.withTranslation_scalar(dest, destOffset, src, srcOffset, t, tOffset);
    }

    /** {@link #withTranslation(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer withTranslation(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t, int tOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && t.isDirect() && t.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.withTranslation_unsafe(dest, destOffset, src, srcOffset, t, tOffset);
        return Double3x3OpsKernelsTypedBuffer.withTranslation_api(dest, destOffset, src, srcOffset, t, tOffset);
    }

    /** {@link #withTranslation(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer withTranslation(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t, int tOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && t.isDirect() && t.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.withTranslation_unsafe(dest, destOffset, src, srcOffset, t, tOffset);
        return Double3x3OpsKernelsByteBuffer.withTranslation_api(dest, destOffset, src, srcOffset, t, tOffset);
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
        if (SimdSupport.DOUBLE_COLUMNS) return Double3x3OpsSimd.withTranslation(dest, destOffset, src, srcOffset, t, tOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && t.isNative()) return Double3x3OpsKernelsSegment.withTranslation_unsafe(dest, destOffset, src, srcOffset, t, tOffset);
        return Double3x3OpsKernelsSegment.withTranslation_api(dest, destOffset, src, srcOffset, t, tOffset);
    }

    /** {@link #withTranslation(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long withTranslation(long dest, long src, long t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.withTranslation_unsafe(dest, src, t);
        withTranslation(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(t, 16L), 0L);
        return dest;
    }

    /**
     * Set this matrix to the given rigid transform's rotation block (the translation is dropped).
     * <p>
     * Valid input: {@code (rRX, rRY, rRZ, rRW)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param rTX the {@code tX} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @param rTY the {@code tY} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @param rTZ the {@code tZ} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @param rRX the {@code rX} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @param rRY the {@code rY} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @param rRZ the {@code rZ} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @param rRW the {@code rW} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @return {@code dest}
     */
    public static double[] makeFromRigid(double[] dest, int destOffset, double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        double _t0 = rRZ * rRZ;
        double _t1 = rRZ * rRW;
        double _t2 = rRY * rRW;
        dest[destOffset] = Math.fma(-2.0, Math.fma(rRY, rRY, _t0), 1.0);
        dest[destOffset + 1] = 2.0 * Math.fma(rRX, rRY, _t1);
        dest[destOffset + 2] = 2.0 * Math.fma(rRX, rRZ, -_t2);
        dest[destOffset + 3] = 2.0 * Math.fma(rRX, rRY, -_t1);
        dest[destOffset + 4] = Math.fma(-2.0, Math.fma(rRX, rRX, _t0), 1.0);
        dest[destOffset + 5] = 2.0 * Math.fma(rRX, rRW, rRY * rRZ);
        dest[destOffset + 6] = 2.0 * Math.fma(rRX, rRZ, _t2);
        dest[destOffset + 7] = 2.0 * Math.fma(rRY, rRZ, -(rRX * rRW));
        dest[destOffset + 8] = Math.fma(-2.0, Math.fma(rRX, rRX, rRY * rRY), 1.0);
        return dest;
    }

    /** {@link #makeFromRigid(double[], int, double, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeFromRigid(java.nio.DoubleBuffer dest, int destOffset, double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeFromRigid_unsafe(dest, destOffset, rRX, rRY, rRZ, rRW);
        return Double3x3OpsKernelsTypedBuffer.makeFromRigid_api(dest, destOffset, rTX, rTY, rTZ, rRX, rRY, rRZ, rRW);
    }

    /** {@link #makeFromRigid(double[], int, double, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeFromRigid(java.nio.ByteBuffer dest, int destOffset, double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeFromRigid_unsafe(dest, destOffset, rRX, rRY, rRZ, rRW);
        return Double3x3OpsKernelsByteBuffer.makeFromRigid_api(dest, destOffset, rRX, rRY, rRZ, rRW);
    }

    /** {@link #makeFromRigid(double[], int, double, double, double, double, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeFromRigid(java.lang.foreign.MemorySegment dest, long destOffset, double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeFromRigid(_destArray, heapIndex(dest, destOffset, 3), rTX, rTY, rTZ, rRX, rRY, rRZ, rRW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeFromRigid_unsafe(dest, destOffset, rRX, rRY, rRZ, rRW);
        return Double3x3OpsKernelsSegment.makeFromRigid_api(dest, destOffset, rRX, rRY, rRZ, rRW);
    }

    /** {@link #makeFromRigid(double[], int, double, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeFromRigid(long dest, double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeFromRigid_unsafe(dest, rRX, rRY, rRZ, rRW);
        makeFromRigid(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, rTX, rTY, rTZ, rRX, rRY, rRZ, rRW);
        return dest;
    }

    /**
     * Set this matrix to the given transform's linear block {@code R * S} (the translation is
     * dropped).
     * <p>
     * Valid input: {@code (tRX, tRY, tRZ, tRW)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param tTX the {@code tX} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tTY the {@code tY} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tTZ the {@code tZ} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tRX the {@code rX} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tRY the {@code rY} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tRZ the {@code rZ} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tRW the {@code rW} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tSX the {@code sX} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tSY the {@code sY} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tSZ the {@code sZ} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @return {@code dest}
     */
    public static double[] makeFromTransform(double[] dest, int destOffset, double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        double _t0 = tSX + tSX;
        double _t1 = tSY + tSY;
        double _t2 = tSZ + tSZ;
        double _t3 = tRZ * tRZ;
        double _t4 = tRZ * tRW;
        double _t5 = tRY * tRW;
        dest[destOffset] = Math.fma(-Math.fma(tRY, tRY, _t3), _t0, tSX);
        dest[destOffset + 1] = Math.fma(tRX, tRY, _t4) * _t0;
        dest[destOffset + 2] = Math.fma(tRX, tRZ, -_t5) * _t0;
        dest[destOffset + 3] = Math.fma(tRX, tRY, -_t4) * _t1;
        dest[destOffset + 4] = Math.fma(-Math.fma(tRX, tRX, _t3), _t1, tSY);
        dest[destOffset + 5] = Math.fma(tRX, tRW, tRY * tRZ) * _t1;
        dest[destOffset + 6] = Math.fma(tRX, tRZ, _t5) * _t2;
        dest[destOffset + 7] = Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2;
        dest[destOffset + 8] = Math.fma(-Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ);
        return dest;
    }

    /** {@link #makeFromTransform(double[], int, double, double, double, double, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeFromTransform(java.nio.DoubleBuffer dest, int destOffset, double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeFromTransform_unsafe(dest, destOffset, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        return Double3x3OpsKernelsTypedBuffer.makeFromTransform_api(dest, destOffset, tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
    }

    /** {@link #makeFromTransform(double[], int, double, double, double, double, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeFromTransform(java.nio.ByteBuffer dest, int destOffset, double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeFromTransform_unsafe(dest, destOffset, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        return Double3x3OpsKernelsByteBuffer.makeFromTransform_api(dest, destOffset, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
    }

    /** {@link #makeFromTransform(double[], int, double, double, double, double, double, double, double, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeFromTransform(java.lang.foreign.MemorySegment dest, long destOffset, double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeFromTransform(_destArray, heapIndex(dest, destOffset, 3), tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeFromTransform_unsafe(dest, destOffset, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        return Double3x3OpsKernelsSegment.makeFromTransform_api(dest, destOffset, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
    }

    /** {@link #makeFromTransform(double[], int, double, double, double, double, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeFromTransform(long dest, double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeFromTransform_unsafe(dest, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        makeFromTransform(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ);
        return dest;
    }

    /**
     * Extract the upper-left 2x2 linear block of this matrix (dropping the translation column and
     * the last row) and store the result in {@code dest}.
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
        double _self10 = src[srcOffset + 1];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _self01;
        dest[destOffset + 3] = _self11;
        return dest;
    }

    /** {@link #to2x2(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer to2x2(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.to2x2_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.to2x2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to2x2(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer to2x2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.to2x2_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.to2x2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to2x2(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment to2x2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            to2x2(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.to2x2_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.to2x2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to2x2(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long to2x2(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.to2x2_unsafe(dest, src);
        to2x2(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Truncate this matrix to a 2x3 matrix, dropping the last row (assumed {@code 0, 0, 1}) and
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
    public static double[] to2x3(double[] dest, int destOffset, double[] src, int srcOffset) {
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 2;
            int _loself = _l * 3;
            double _eself1 = src[srcOffset + _loself + 1];
            dest[destOffset + _lo] = src[srcOffset + _loself];
            dest[destOffset + _lo + 1] = _eself1;
        }
        return dest;
    }

    /** {@link #to2x3(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer to2x3(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.to2x3_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.to2x3_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to2x3(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer to2x3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.to2x3_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.to2x3_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to2x3(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment to2x3(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            to2x3(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.to2x3_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.to2x3_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to2x3(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long to2x3(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.to2x3_unsafe(dest, src);
        to2x3(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Extend this matrix to a 3x4 matrix with a zero translation column and store the result in
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
    public static double[] to3x4(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self01;
        dest[destOffset + 2] = _self02;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _self10;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = _self12;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _self20;
        dest[destOffset + 9] = _self21;
        dest[destOffset + 10] = _self22;
        dest[destOffset + 11] = 0.0;
        return dest;
    }

    /** {@link #to3x4(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer to3x4(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.to3x4_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.to3x4_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to3x4(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer to3x4(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.to3x4_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.to3x4_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to3x4(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment to3x4(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            to3x4(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.to3x4_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.to3x4_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to3x4(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long to3x4(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.to3x4_unsafe(dest, src);
        to3x4(VirtualMemoryHolder.virtualMemory().asSlice(dest, 96L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Extend this matrix to a 4x4 matrix, filling the missing cells with identity and store the
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
    public static double[] to4x4(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _self20;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _self01;
        dest[destOffset + 5] = _self11;
        dest[destOffset + 6] = _self21;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _self02;
        dest[destOffset + 9] = _self12;
        dest[destOffset + 10] = _self22;
        dest[destOffset + 11] = 0.0;
        dest[destOffset + 12] = 0.0;
        dest[destOffset + 13] = 0.0;
        dest[destOffset + 14] = 0.0;
        dest[destOffset + 15] = 1.0;
        return dest;
    }

    /** {@link #to4x4(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer to4x4(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.to4x4_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.to4x4_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to4x4(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer to4x4(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.to4x4_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.to4x4_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to4x4(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment to4x4(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            to4x4(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.to4x4_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.to4x4_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #to4x4(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long to4x4(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.to4x4_unsafe(dest, src);
        to4x4(VirtualMemoryHolder.virtualMemory().asSlice(dest, 128L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Convert this matrix (assumed orthonormal) to a pure-rotation dual quaternion and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this matrix must be a rotation matrix.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the dual quaternion starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] toDualQuat(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t1 = 1.0 - _self00;
        double _t13 = _self22 + (_self00 + _self11);
        double _t14 = 1.0 + _t13;
        double _t15 = _self00 + (1.0 - _self11 - _self22);
        double _t16 = _self11 + (_t1 - _self22);
        double _t17 = _self22 + (_t1 - _self11);
        return toDualQuat_scbc34153_1(dest, destOffset, _self00, _self11, _self22, _self21 - _self12, _self01 + _self10, _self02 + _self20, _self02 - _self20, _self12 + _self21, _self10 - _self01, _t13, _t14, _t15, _t16, _t17, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), 0.5 * (1.0 / java.lang.Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code toDualQuat}, split to fit the inline budget; reached only through it. */
    private static double[] toDualQuat_scbc34153_1(double[] dest, int destOffset, double _self00, double _self11, double _self22, double _t3, double _t5, double _t6, double _t7, double _t8, double _t9, double _t13, double _t14, double _t15, double _t16, double _t17, double _sp0, double _sp1, double _sp2, double _sp3) {
        if (_t13 > 0.0) {
            dest[destOffset] = _sp0 * _t3;
            dest[destOffset + 1] = _sp0 * _t7;
            dest[destOffset + 2] = _sp0 * _t9;
            dest[destOffset + 3] = 0.5 * java.lang.Math.sqrt(_t14);
        } else {
            if (_self00 > java.lang.Math.max(_self11, _self22)) {
                dest[destOffset] = 0.5 * java.lang.Math.sqrt(_t15);
                dest[destOffset + 1] = _sp3 * _t5;
                dest[destOffset + 2] = _sp3 * _t6;
                dest[destOffset + 3] = _sp3 * _t3;
            } else {
                if (_self11 > _self22) {
                    dest[destOffset] = _sp1 * _t5;
                    dest[destOffset + 1] = 0.5 * java.lang.Math.sqrt(_t16);
                    dest[destOffset + 2] = _sp1 * _t8;
                    dest[destOffset + 3] = _sp1 * _t7;
                } else {
                    dest[destOffset] = _sp2 * _t6;
                    dest[destOffset + 1] = _sp2 * _t8;
                    dest[destOffset + 2] = 0.5 * java.lang.Math.sqrt(_t17);
                    dest[destOffset + 3] = _sp2 * _t9;
                }
            }
        }
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = 0.0;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        return dest;
    }

    /** {@link #toDualQuat(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer toDualQuat(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.toDualQuat_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.toDualQuat_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toDualQuat(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer toDualQuat(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.toDualQuat_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.toDualQuat_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toDualQuat(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment toDualQuat(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            toDualQuat(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.toDualQuat_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.toDualQuat_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toDualQuat(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long toDualQuat(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.toDualQuat_unsafe(dest, src);
        toDualQuat(VirtualMemoryHolder.virtualMemory().asSlice(dest, 64L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Extract this matrix's rotation into a rigid transform with zero translation (scale is removed
     * by normalizing the columns, but shear is not removed: a sheared block yields a rotation
     * quaternion that is not unit length) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the rigid transform starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] toRigid(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _ct0 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsArray.toRigid_degenerate(dest, destOffset, src, srcOffset);
        double _t15 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _ct1 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsArray.toRigid_degenerate(dest, destOffset, src, srcOffset);
        double _t16 = (1.0 / java.lang.Math.sqrt(_ct1));
        double _ct2 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsArray.toRigid_degenerate(dest, destOffset, src, srcOffset);
        double _t17 = (1.0 / java.lang.Math.sqrt(_ct2));
        double _t23 = _self21 * _t15;
        return toRigid_s1323252d_1(dest, destOffset, _self01, _self11, _self21, _self02, _self22, -_self11, -_self22, _t15, _t16, _self10 * _t17, _self22 * _t16, _self12 * _t16, _self20 * _t17, _t23, _self11 * _t15, _self00 * _t17, Math.fma(_self12, _t16, _t23));
    }

    /** Piece 2 of {@code toRigid}, split to fit the inline budget; reached only through it. */
    private static double[] toRigid_s1323252d_1(double[] dest, int destOffset, double _self01, double _self11, double _self21, double _self02, double _self22, double _t0, double _t1, double _t15, double _t16, double _t18, double _t19, double _t20, double _t21, double _t23, double _t24, double _t26, double _t31) {
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
        double _t65 = Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t52));
        double _t66 = Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t52));
        double _t67 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51));
        return toRigid_s1323252d_2(dest, destOffset, _self11, _self22, _t15, _t16, _t19, _t24, _t31, Math.fma(_self21, _t15, -_t20), _t47, Math.fma(_self01, _t15, _t48), Math.fma(_self02, _t16, _t49), Math.fma(_self02, _t16, -_t49), Math.fma(-_self01, _t15, _t48), _t63, 0.5 * (1.0 / java.lang.Math.sqrt(_t63)), _t65, _t66, _t67, 0.5 * (1.0 / java.lang.Math.sqrt(_t65)), 0.5 * (1.0 / java.lang.Math.sqrt(_t66)), 0.5 * (1.0 / java.lang.Math.sqrt(_t67)));
    }

    /** Piece 3 of {@code toRigid}, split to fit the inline budget; reached only through it. */
    private static double[] toRigid_s1323252d_2(double[] dest, int destOffset, double _self11, double _self22, double _t15, double _t16, double _t19, double _t24, double _t31, double _t35, double _t47, double _t54, double _t55, double _t56, double _t57, double _t63, double _sp0, double _t65, double _t66, double _t67, double _sp1, double _sp2, double _sp3) {
        if (Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t47)) > 0.0) {
            dest[destOffset + 3] = _sp0 * _t35;
            dest[destOffset + 4] = _sp0 * _t56;
            dest[destOffset + 5] = _sp0 * _t57;
            dest[destOffset + 6] = 0.5 * java.lang.Math.sqrt(_t63);
        } else {
            if (_t47 > java.lang.Math.max(_t24, _t19)) {
                dest[destOffset + 3] = 0.5 * java.lang.Math.sqrt(_t67);
                dest[destOffset + 4] = _sp3 * _t54;
                dest[destOffset + 5] = _sp3 * _t55;
                dest[destOffset + 6] = _sp3 * _t35;
            } else {
                if (_t24 > _t19) {
                    dest[destOffset + 3] = _sp1 * _t54;
                    dest[destOffset + 4] = 0.5 * java.lang.Math.sqrt(_t65);
                    dest[destOffset + 5] = _sp1 * _t31;
                    dest[destOffset + 6] = _sp1 * _t56;
                } else {
                    dest[destOffset + 3] = _sp2 * _t55;
                    dest[destOffset + 4] = _sp2 * _t31;
                    dest[destOffset + 5] = 0.5 * java.lang.Math.sqrt(_t66);
                    dest[destOffset + 6] = _sp2 * _t57;
                }
            }
        }
        dest[destOffset] = 0.0;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        return dest;
    }

    /** {@link #toRigid(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer toRigid(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.toRigid_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.toRigid_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toRigid(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer toRigid(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.toRigid_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.toRigid_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toRigid(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment toRigid(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            toRigid(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.toRigid_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.toRigid_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toRigid(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long toRigid(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.toRigid_unsafe(dest, src);
        toRigid(VirtualMemoryHolder.virtualMemory().asSlice(dest, 56L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Decompose this matrix's linear {@code R * S} block into a TRS transform with zero translation
     * (scale is removed by normalizing the columns, but shear is not removed: a sheared block
     * yields a rotation quaternion that is not unit length) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the transform starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] toTransform(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t12 = Math.fma(_self21, _self21, Math.fma(_self01, _self01, _self11 * _self11));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsArray.toTransform_degenerate(dest, destOffset, src, srcOffset);
        double _t13 = Math.fma(_self22, _self22, Math.fma(_self02, _self02, _self12 * _self12));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsArray.toTransform_degenerate(dest, destOffset, src, srcOffset);
        double _t14 = Math.fma(_self20, _self20, Math.fma(_self00, _self00, _self10 * _self10));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return Double3x3OpsKernelsArray.toTransform_degenerate(dest, destOffset, src, srcOffset);
        double _t15 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t16 = (1.0 / java.lang.Math.sqrt(_t13));
        double _t18 = java.lang.Math.sqrt(_t14);
        double _t17 = 1.0 / _t18;
        return toTransform_s80d43fe8_4(dest, destOffset, _self01, _self11, _self21, _self02, _self12, _self22, -_self11, -_self22, _t12, _t13, _t15, _t16, _t18, _self10 * _t17, _self22 * _t16, _self12 * _t16, _self20 * _t17, _self21 * _t15, _self11 * _t15, _self00 * _t17);
    }

    /** Part 1 of {@code toTransform}, split to fit the inline budget; reached only through it. */
    private static double toTransform_s80d43fe8_1(double _self01, double _self02, double _t15, double _t16, double _t19, double _t20, double _t21, double _t22, double _t24, double _t25, double _t27) {
        return Math.fma(-Math.fma(_t19, _t20, -(_t21 * _t22)), _self01 * _t15, Math.fma(Math.fma(_t19, _t24, -(_t25 * _t22)), _self02 * _t16, Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27));
    }

    /** Part 2 of {@code toTransform}, split to fit the inline budget; reached only through it. */
    private static void toTransform_s80d43fe8_2(double[] dest, int destOffset, double _t20, double _t25, double _t32, double _t36, double _t37, double _t48, double _t55, double _t56, double _t57, double _t58, double _t63, double _t64, double _sp0, double _t66, double _t67, double _t68) {
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t66));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t67));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t68));
        dest[destOffset] = 0.0;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = _t63 > 0.0 ? _sp0 * _t36 : _t48 > _t37 ? 0.5 * java.lang.Math.sqrt(_t68) : _t25 > _t20 ? _sp1 * _t55 : _sp2 * _t56;
        dest[destOffset + 4] = _t63 > 0.0 ? _sp0 * _t57 : _t48 > _t37 ? _sp3 * _t55 : _t25 > _t20 ? 0.5 * java.lang.Math.sqrt(_t66) : _sp2 * _t32;
        dest[destOffset + 5] = _t63 > 0.0 ? _sp0 * _t58 : _t48 > _t37 ? _sp3 * _t56 : _t25 > _t20 ? _sp1 * _t32 : 0.5 * java.lang.Math.sqrt(_t67);
        dest[destOffset + 6] = _t63 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t64) : _t48 > _t37 ? _sp3 * _t36 : _t25 > _t20 ? _sp1 * _t57 : _sp2 * _t58;
    }

    /** Part 3 of {@code toTransform}, split to fit the inline budget; reached only through it. */
    private static double[] toTransform_s80d43fe8_3(double[] dest, int destOffset, double _t12, double _t13, double _t18, double _t47) {
        dest[destOffset + 7] = _t47 < 0.0 ? -_t18 : _t18;
        dest[destOffset + 8] = java.lang.Math.sqrt(_t12);
        dest[destOffset + 9] = java.lang.Math.sqrt(_t13);
        return dest;
    }

    /** Piece 2 of {@code toTransform}, split to fit the inline budget; reached only through it. */
    private static double[] toTransform_s80d43fe8_4(double[] dest, int destOffset, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t0, double _t1, double _t12, double _t13, double _t15, double _t16, double _t18, double _t19, double _t20, double _t21, double _t22, double _t24, double _t25, double _t27) {
        double _t47 = toTransform_s80d43fe8_1(_self01, _self02, _t15, _t16, _t19, _t20, _t21, _t22, _t24, _t25, _t27);
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
        double _t64 = Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t52));
        toTransform_s80d43fe8_2(dest, destOffset, _t20, _t25, Math.fma(_self12, _t16, _t24), Math.fma(_self21, _t15, -_t21), java.lang.Math.max(_t25, _t20), _t48, Math.fma(_self01, _t15, _t49), Math.fma(_self02, _t16, _t50), Math.fma(_self02, _t16, -_t50), Math.fma(-_self01, _t15, _t49), Math.fma(_self11, _t15, Math.fma(_self22, _t16, _t48)), _t64, (0.5 * (1.0 / java.lang.Math.sqrt(_t64))), Math.fma(_self11, _t15, Math.fma(_t1, _t16, _t53)), Math.fma(_self22, _t16, Math.fma(_t0, _t15, _t53)), Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52)));
        return toTransform_s80d43fe8_3(dest, destOffset, _t12, _t13, _t18, _t47);
    }

    /** {@link #toTransform(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer toTransform(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.toTransform_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.toTransform_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toTransform(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer toTransform(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.toTransform_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.toTransform_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toTransform(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment toTransform(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            toTransform(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.toTransform_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.toTransform_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toTransform(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long toTransform(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.toTransform_unsafe(dest, src);
        toTransform(VirtualMemoryHolder.virtualMemory().asSlice(dest, 80L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Extract the rotation part of this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] decomposeRotation(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
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
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
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
        return decomposeRotation_sbf3c5260_1(dest, destOffset, _self02, _self12, _self22, _t7, _t8, _t9, -Math.fma(_self22, _t7, Math.fma(_self02, _t8, _self12 * _t9)), _t34, _t35, _t36);
    }

    /** Piece 2 of {@code decomposeRotation}, split to fit the inline budget; reached only through it. */
    private static double[] decomposeRotation_sbf3c5260_1(double[] dest, int destOffset, double _self02, double _self12, double _self22, double _t7, double _t8, double _t9, double _t20, double _t34, double _t35, double _t36) {
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
        return decomposeRotation_sbf3c5260_2(dest, destOffset, _t36, _t56, _t35 - _t54, _t35 + _t54, _t73, _t76, _t74 + _t34, _t74 - _t34, _t75 + _t55, _t55 - _t75, _t76 + _t56);
    }

    /** Piece 3 of {@code decomposeRotation}, split to fit the inline budget; reached only through it. */
    private static double[] decomposeRotation_sbf3c5260_2(double[] dest, int destOffset, double _t36, double _t56, double _t60, double _t63, double _t73, double _t76, double _t77, double _t78, double _t80, double _t81, double _t82) {
        double _t86 = 1.0 + _t82;
        double _t87 = 1.0 + (_t73 - (_t36 + _t56));
        double _t88 = 1.0 + (_t36 - (_t73 + _t56));
        double _t89 = 1.0 + (_t56 - _t76);
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t86));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t88));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t89));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t87));
        if (_t82 > 0.0) {
            dest[destOffset] = _sp0 * _t60;
            dest[destOffset + 1] = _sp0 * _t81;
            dest[destOffset + 2] = _sp0 * _t78;
            dest[destOffset + 3] = 0.5 * java.lang.Math.sqrt(_t86);
        } else {
            if (_t73 > java.lang.Math.max(_t36, _t56)) {
                dest[destOffset] = 0.5 * java.lang.Math.sqrt(_t87);
                dest[destOffset + 1] = _sp3 * _t77;
                dest[destOffset + 2] = _sp3 * _t80;
                dest[destOffset + 3] = _sp3 * _t60;
            } else {
                if (_t36 > _t56) {
                    dest[destOffset] = _sp1 * _t77;
                    dest[destOffset + 1] = 0.5 * java.lang.Math.sqrt(_t88);
                    dest[destOffset + 2] = _sp1 * _t63;
                    dest[destOffset + 3] = _sp1 * _t81;
                } else {
                    dest[destOffset] = _sp2 * _t80;
                    dest[destOffset + 1] = _sp2 * _t63;
                    dest[destOffset + 2] = 0.5 * java.lang.Math.sqrt(_t89);
                    dest[destOffset + 3] = _sp2 * _t78;
                }
            }
        }
        return dest;
    }

    /** {@link #decomposeRotation(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer decomposeRotation(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.decomposeRotation_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.decomposeRotation_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #decomposeRotation(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer decomposeRotation(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.decomposeRotation_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.decomposeRotation_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #decomposeRotation(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment decomposeRotation(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            decomposeRotation(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.decomposeRotation_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.decomposeRotation_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #decomposeRotation(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long decomposeRotation(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.decomposeRotation_unsafe(dest, src);
        decomposeRotation(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Extract the scaling factors of this matrix via Gram-Schmidt orthogonalization (skew-aware;
     * the x factor carries the sign of a reflection when the determinant is negative) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] decomposeScale(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
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
        return decomposeScale_sf133eb8_1(dest, destOffset, _self02, _self12, _self22, _t4, _t8, _t9, _t10, -Math.fma(_self22, _t8, Math.fma(_self02, _t9, _self12 * _t10)), _t19, _t20, _t21, _t27, (1.0 / java.lang.Math.sqrt(_t27)));
    }

    /** Piece 2 of {@code decomposeScale}, split to fit the inline budget; reached only through it. */
    private static double[] decomposeScale_sf133eb8_1(double[] dest, int destOffset, double _self02, double _self12, double _self22, double _t4, double _t8, double _t9, double _t10, double _t18, double _t19, double _t20, double _t21, double _t27, double _t28) {
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
        dest[destOffset] = Math.fma(Math.fma(_t32, _t52, -(_t34 * _t53)), _t8, Math.fma(Math.fma(_t34, _t54, -(_t33 * _t52)), _t9, Math.fma(_t33, _t53, -(_t32 * _t54)) * _t10)) < 0.0 ? -_t4 : _t4;
        dest[destOffset + 1] = java.lang.Math.sqrt(_t27);
        dest[destOffset + 2] = java.lang.Math.sqrt(_t47);
        return dest;
    }

    /** {@link #decomposeScale(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer decomposeScale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.decomposeScale_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.decomposeScale_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #decomposeScale(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer decomposeScale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.decomposeScale_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.decomposeScale_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #decomposeScale(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment decomposeScale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            decomposeScale(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.decomposeScale_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.decomposeScale_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #decomposeScale(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long decomposeScale(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.decomposeScale_unsafe(dest, src);
        decomposeScale(VirtualMemoryHolder.virtualMemory().asSlice(dest, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Extract the shear (skew) factors of this matrix via Gram-Schmidt orthogonalization, as
     * {@code (skewYZ, skewXZ, skewXY)} (all zero for a shear-free matrix) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; this
     * matrix must be invertible.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @return {@code dest}
     */
    public static double[] decomposeSkew(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
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
        return decomposeSkew_s15187490_1(dest, destOffset, _self02, _self12, _self22, _t7, _t8, _t9, _t14, -_t14, _t19, _t20, _t21, _t26, _t27, _t15 * _t27);
    }

    /** Piece 2 of {@code decomposeSkew}, split to fit the inline budget; reached only through it. */
    private static double[] decomposeSkew_s15187490_1(double[] dest, int destOffset, double _self02, double _self12, double _self22, double _t7, double _t8, double _t9, double _t14, double _t16, double _t19, double _t20, double _t21, double _t26, double _t27, double _t28) {
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
        return decomposeSkew_s15187490_2(dest, destOffset, _t7, _t8, _t9, _t28, _t32, _t33, _t34, _t37, _t48, _t14 * _t48, _t53, _t54, _t55);
    }

    /** Piece 3 of {@code decomposeSkew}, split to fit the inline budget; reached only through it. */
    private static double[] decomposeSkew_s15187490_2(double[] dest, int destOffset, double _t7, double _t8, double _t9, double _t28, double _t32, double _t33, double _t34, double _t37, double _t48, double _t49, double _t53, double _t54, double _t55) {
        if (Math.fma(Math.fma(_t33, _t53, -(_t34 * _t54)), _t7, Math.fma(Math.fma(_t34, _t55, -(_t32 * _t53)), _t8, Math.fma(_t32, _t54, -(_t33 * _t55)) * _t9)) < 0.0) {
            dest[destOffset + 1] = -_t49;
            dest[destOffset + 2] = -_t28;
        } else {
            dest[destOffset + 1] = _t49;
            dest[destOffset + 2] = _t28;
        }
        dest[destOffset] = _t37 * _t48;
        return dest;
    }

    /** {@link #decomposeSkew(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer decomposeSkew(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.decomposeSkew_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.decomposeSkew_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #decomposeSkew(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer decomposeSkew(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.decomposeSkew_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.decomposeSkew_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #decomposeSkew(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment decomposeSkew(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            decomposeSkew(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.decomposeSkew_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.decomposeSkew_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #decomposeSkew(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long decomposeSkew(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.decomposeSkew_unsafe(dest, src);
        decomposeSkew(VirtualMemoryHolder.virtualMemory().asSlice(dest, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
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
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 1.0;
        dest[destOffset + 5] = 0.0;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 1.0;
        return dest;
    }

    /** {@link #makeIdentity(double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeIdentity(java.nio.DoubleBuffer dest, int destOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeIdentity_unsafe(dest, destOffset);
        return Double3x3OpsKernelsTypedBuffer.makeIdentity_api(dest, destOffset);
    }

    /** {@link #makeIdentity(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeIdentity(java.nio.ByteBuffer dest, int destOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeIdentity_unsafe(dest, destOffset);
        return Double3x3OpsKernelsByteBuffer.makeIdentity_api(dest, destOffset);
    }

    /** {@link #makeIdentity(double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeIdentity(java.lang.foreign.MemorySegment dest, long destOffset) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeIdentity(_destArray, heapIndex(dest, destOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeIdentity_unsafe(dest, destOffset);
        return Double3x3OpsKernelsSegment.makeIdentity_api(dest, destOffset);
    }

    /** {@link #makeIdentity(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeIdentity(long dest) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeIdentity_unsafe(dest);
        makeIdentity(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L);
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
        for (int _i = 0; _i < 9; _i++) {
            double _eself = src[srcOffset + _i];
            dest[destOffset + _i] = Math.fma(t, other[otherOffset + _i] - _eself, _eself);
        }
        return dest;
    }

    /** {@link #lerp(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer lerp(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Double3x3OpsKernelsTypedBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Double3x3OpsKernelsByteBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double3x3OpsKernelsSegment.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Double3x3OpsKernelsSegment.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long lerp(long dest, long src, long other, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.lerp_unsafe(dest, src, other, t);
        lerp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 72L), 0L, t);
        return dest;
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eright0 = right[rightOffset + _lo];
            double _eright1 = right[rightOffset + _lo + 1];
            double _eright2 = right[rightOffset + _lo + 2];
            dest[destOffset + _lo] = Math.fma(_eright2, _self02, Math.fma(_eright0, _self00, _eright1 * _self01));
            dest[destOffset + _lo + 1] = Math.fma(_eright2, _self12, Math.fma(_eright0, _self10, _eright1 * _self11));
            dest[destOffset + _lo + 2] = Math.fma(_eright2, _self22, Math.fma(_eright0, _self20, _eright1 * _self21));
        }
        return dest;
    }

    /** {@link #mul(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mul(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && right.isDirect() && right.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.mul_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Double3x3OpsKernelsTypedBuffer.mul_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mul(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && right.isDirect() && right.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.mul_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Double3x3OpsKernelsByteBuffer.mul_api(dest, destOffset, src, srcOffset, right, rightOffset);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && right.isNative()) return Double3x3OpsKernelsSegment.mul_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Double3x3OpsKernelsSegment.mul_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mul(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mul(long dest, long src, long right) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.mul_unsafe(dest, src, right);
        mul(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(right, 72L), 0L);
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _right00 = right[rightOffset];
        double _right10 = right[rightOffset + 1];
        double _right01 = right[rightOffset + 2];
        double _right11 = right[rightOffset + 3];
        dest[destOffset] = Math.fma(_right00, _self00, _right10 * _self01);
        dest[destOffset + 1] = Math.fma(_right00, _self10, _right10 * _self11);
        dest[destOffset + 2] = Math.fma(_right00, _self20, _right10 * _self21);
        dest[destOffset + 3] = Math.fma(_right01, _self00, _right11 * _self01);
        dest[destOffset + 4] = Math.fma(_right01, _self10, _right11 * _self11);
        dest[destOffset + 5] = Math.fma(_right01, _self20, _right11 * _self21);
        dest[destOffset + 6] = _self02;
        dest[destOffset + 7] = _self12;
        dest[destOffset + 8] = _self22;
        return dest;
    }

    /** {@link #mulMat2x2(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mulMat2x2(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && right.isDirect() && right.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.mulMat2x2_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Double3x3OpsKernelsTypedBuffer.mulMat2x2_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mulMat2x2(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mulMat2x2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && right.isDirect() && right.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.mulMat2x2_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Double3x3OpsKernelsByteBuffer.mulMat2x2_api(dest, destOffset, src, srcOffset, right, rightOffset);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && right.isNative()) return Double3x3OpsKernelsSegment.mulMat2x2_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Double3x3OpsKernelsSegment.mulMat2x2_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mulMat2x2(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mulMat2x2(long dest, long src, long right) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.mulMat2x2_unsafe(dest, src, right);
        mulMat2x2(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(right, 32L), 0L);
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
    public static double[] mulMat2x3(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _right00 = right[rightOffset];
        double _right10 = right[rightOffset + 1];
        double _right01 = right[rightOffset + 2];
        double _right11 = right[rightOffset + 3];
        double _right02 = right[rightOffset + 4];
        double _right12 = right[rightOffset + 5];
        dest[destOffset] = Math.fma(_right00, _self00, _right10 * _self01);
        dest[destOffset + 1] = Math.fma(_right00, _self10, _right10 * _self11);
        dest[destOffset + 2] = Math.fma(_right00, _self20, _right10 * _self21);
        dest[destOffset + 3] = Math.fma(_right01, _self00, _right11 * _self01);
        dest[destOffset + 4] = Math.fma(_right01, _self10, _right11 * _self11);
        dest[destOffset + 5] = Math.fma(_right01, _self20, _right11 * _self21);
        dest[destOffset + 6] = Math.fma(_right02, _self00, Math.fma(_right12, _self01, _self02));
        dest[destOffset + 7] = Math.fma(_right02, _self10, Math.fma(_right12, _self11, _self12));
        dest[destOffset + 8] = Math.fma(_right02, _self20, Math.fma(_right12, _self21, _self22));
        return dest;
    }

    /** {@link #mulMat2x3(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mulMat2x3(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer right, int rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && right.isDirect() && right.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.mulMat2x3_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Double3x3OpsKernelsTypedBuffer.mulMat2x3_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mulMat2x3(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mulMat2x3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer right, int rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && right.isDirect() && right.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.mulMat2x3_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Double3x3OpsKernelsByteBuffer.mulMat2x3_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mulMat2x3(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment mulMat2x3(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        double[] _destArray, _srcArray, _rightArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_rightArray = heapDoubles(right, rightOffset)) != null) {
            mulMat2x3(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _rightArray, heapIndex(right, rightOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && right.isNative()) return Double3x3OpsKernelsSegment.mulMat2x3_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return Double3x3OpsKernelsSegment.mulMat2x3_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    /** {@link #mulMat2x3(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mulMat2x3(long dest, long src, long right) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.mulMat2x3_unsafe(dest, src, right);
        mulMat2x3(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(right, 48L), 0L);
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
        double _other00 = other[otherOffset];
        double _other10 = other[otherOffset + 1];
        double _other20 = other[otherOffset + 2];
        double _other01 = other[otherOffset + 3];
        double _other11 = other[otherOffset + 4];
        double _other21 = other[otherOffset + 5];
        double _other02 = other[otherOffset + 6];
        double _other12 = other[otherOffset + 7];
        double _other22 = other[otherOffset + 8];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src[srcOffset + _lo];
            double _eself1 = src[srcOffset + _lo + 1];
            double _eself2 = src[srcOffset + _lo + 2];
            dest[destOffset + _lo] = Math.fma(_other02, _eself2, Math.fma(_other00, _eself0, _other01 * _eself1));
            dest[destOffset + _lo + 1] = Math.fma(_other12, _eself2, Math.fma(_other10, _eself0, _other11 * _eself1));
            dest[destOffset + _lo + 2] = Math.fma(_other22, _eself2, Math.fma(_other20, _eself0, _other21 * _eself1));
        }
        return dest;
    }

    /** {@link #preMul(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preMul(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.preMul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsTypedBuffer.preMul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMul(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.preMul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsByteBuffer.preMul_api(dest, destOffset, src, srcOffset, other, otherOffset);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double3x3OpsKernelsSegment.preMul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsSegment.preMul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMul(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preMul(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.preMul_unsafe(dest, src, other);
        preMul(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 72L), 0L);
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
            int _lo = _l * 3;
            double _eself0 = src[srcOffset + _lo];
            double _eself1 = src[srcOffset + _lo + 1];
            double _eself2 = src[srcOffset + _lo + 2];
            dest[destOffset + _lo] = Math.fma(_other00, _eself0, _other01 * _eself1);
            dest[destOffset + _lo + 1] = Math.fma(_other10, _eself0, _other11 * _eself1);
            dest[destOffset + _lo + 2] = _eself2;
        }
        return dest;
    }

    /** {@link #preMulMat2x2(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preMulMat2x2(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.preMulMat2x2_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsTypedBuffer.preMulMat2x2_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMulMat2x2(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMulMat2x2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.preMulMat2x2_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsByteBuffer.preMulMat2x2_api(dest, destOffset, src, srcOffset, other, otherOffset);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double3x3OpsKernelsSegment.preMulMat2x2_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsSegment.preMulMat2x2_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMulMat2x2(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preMulMat2x2(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.preMulMat2x2_unsafe(dest, src, other);
        preMulMat2x2(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 32L), 0L);
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
    public static double[] preMulMat2x3(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _other00 = other[otherOffset];
        double _other10 = other[otherOffset + 1];
        double _other01 = other[otherOffset + 2];
        double _other11 = other[otherOffset + 3];
        double _other02 = other[otherOffset + 4];
        double _other12 = other[otherOffset + 5];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself0 = src[srcOffset + _lo];
            double _eself1 = src[srcOffset + _lo + 1];
            double _eself2 = src[srcOffset + _lo + 2];
            dest[destOffset + _lo] = Math.fma(_other02, _eself2, Math.fma(_other00, _eself0, _other01 * _eself1));
            dest[destOffset + _lo + 1] = Math.fma(_other12, _eself2, Math.fma(_other10, _eself0, _other11 * _eself1));
            dest[destOffset + _lo + 2] = _eself2;
        }
        return dest;
    }

    /** {@link #preMulMat2x3(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preMulMat2x3(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.preMulMat2x3_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsTypedBuffer.preMulMat2x3_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMulMat2x3(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMulMat2x3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.preMulMat2x3_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsByteBuffer.preMulMat2x3_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMulMat2x3(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preMulMat2x3(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        double[] _destArray, _srcArray, _otherArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_otherArray = heapDoubles(other, otherOffset)) != null) {
            preMulMat2x3(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _otherArray, heapIndex(other, otherOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double3x3OpsKernelsSegment.preMulMat2x3_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3x3OpsKernelsSegment.preMulMat2x3_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMulMat2x3(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preMulMat2x3(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.preMulMat2x3_unsafe(dest, src, other);
        preMulMat2x3(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 48L), 0L);
        return dest;
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
        for (int _i = 0; _i < 9; _i++) {
            dest[destOffset + _i] = Math.fma(weight, other[otherOffset + _i], src[srcOffset + _i]);
        }
        return dest;
    }

    /** {@link #addScaled(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer addScaled(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double weight) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return Double3x3OpsKernelsTypedBuffer.addScaled_api(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    /** {@link #addScaled(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer addScaled(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double weight) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return Double3x3OpsKernelsByteBuffer.addScaled_api(dest, destOffset, src, srcOffset, other, otherOffset, weight);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Double3x3OpsKernelsSegment.addScaled_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return Double3x3OpsKernelsSegment.addScaled_api(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    /** {@link #addScaled(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long addScaled(long dest, long src, long other, double weight) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.addScaled_unsafe(dest, src, other, weight);
        addScaled(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 72L), 0L, weight);
        return dest;
    }

    /**
     * Set this matrix to the outer product of ({@code colX}, {@code colY}, {@code colZ}) and
     * ({@code rowX}, {@code rowY}, {@code rowZ}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param colX the {@code x} component of the vector {@code (colX, colY, colZ)}
     * @param colY the {@code y} component of the vector {@code (colX, colY, colZ)}
     * @param colZ the {@code z} component of the vector {@code (colX, colY, colZ)}
     * @param rowX the {@code x} component of the vector {@code (rowX, rowY, rowZ)}
     * @param rowY the {@code y} component of the vector {@code (rowX, rowY, rowZ)}
     * @param rowZ the {@code z} component of the vector {@code (rowX, rowY, rowZ)}
     * @return {@code dest}
     */
    public static double[] makeOuterProduct(double[] dest, int destOffset, double colX, double colY, double colZ, double rowX, double rowY, double rowZ) {
        dest[destOffset] = colX * rowX;
        dest[destOffset + 1] = colY * rowX;
        dest[destOffset + 2] = colZ * rowX;
        dest[destOffset + 3] = colX * rowY;
        dest[destOffset + 4] = colY * rowY;
        dest[destOffset + 5] = colZ * rowY;
        dest[destOffset + 6] = colX * rowZ;
        dest[destOffset + 7] = colY * rowZ;
        dest[destOffset + 8] = colZ * rowZ;
        return dest;
    }

    /** {@link #makeOuterProduct(double[], int, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeOuterProduct(java.nio.DoubleBuffer dest, int destOffset, double colX, double colY, double colZ, double rowX, double rowY, double rowZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeOuterProduct_unsafe(dest, destOffset, colX, colY, colZ, rowX, rowY, rowZ);
        return Double3x3OpsKernelsTypedBuffer.makeOuterProduct_api(dest, destOffset, colX, colY, colZ, rowX, rowY, rowZ);
    }

    /** {@link #makeOuterProduct(double[], int, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeOuterProduct(java.nio.ByteBuffer dest, int destOffset, double colX, double colY, double colZ, double rowX, double rowY, double rowZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeOuterProduct_unsafe(dest, destOffset, colX, colY, colZ, rowX, rowY, rowZ);
        return Double3x3OpsKernelsByteBuffer.makeOuterProduct_api(dest, destOffset, colX, colY, colZ, rowX, rowY, rowZ);
    }

    /** {@link #makeOuterProduct(double[], int, double, double, double, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeOuterProduct(java.lang.foreign.MemorySegment dest, long destOffset, double colX, double colY, double colZ, double rowX, double rowY, double rowZ) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeOuterProduct(_destArray, heapIndex(dest, destOffset, 3), colX, colY, colZ, rowX, rowY, rowZ);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeOuterProduct_unsafe(dest, destOffset, colX, colY, colZ, rowX, rowY, rowZ);
        return Double3x3OpsKernelsSegment.makeOuterProduct_api(dest, destOffset, colX, colY, colZ, rowX, rowY, rowZ);
    }

    /** {@link #makeOuterProduct(double[], int, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeOuterProduct(long dest, double colX, double colY, double colZ, double rowX, double rowY, double rowZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeOuterProduct_unsafe(dest, colX, colY, colZ, rowX, rowY, rowZ);
        makeOuterProduct(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, colX, colY, colZ, rowX, rowY, rowZ);
        return dest;
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
        double _colz = col[colOffset + 2];
        double _rowx = row[rowOffset];
        double _rowy = row[rowOffset + 1];
        double _rowz = row[rowOffset + 2];
        dest[destOffset] = _colx * _rowx;
        dest[destOffset + 1] = _coly * _rowx;
        dest[destOffset + 2] = _colz * _rowx;
        dest[destOffset + 3] = _colx * _rowy;
        dest[destOffset + 4] = _coly * _rowy;
        dest[destOffset + 5] = _colz * _rowy;
        dest[destOffset + 6] = _colx * _rowz;
        dest[destOffset + 7] = _coly * _rowz;
        dest[destOffset + 8] = _colz * _rowz;
        return dest;
    }

    /** {@link #makeOuterProduct(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeOuterProduct(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer col, int colOffset, java.nio.DoubleBuffer row, int rowOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && col.isDirect() && col.order() == java.nio.ByteOrder.nativeOrder() && row.isDirect() && row.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeOuterProduct_unsafe(dest, destOffset, col, colOffset, row, rowOffset);
        return Double3x3OpsKernelsTypedBuffer.makeOuterProduct_api(dest, destOffset, col, colOffset, row, rowOffset);
    }

    /** {@link #makeOuterProduct(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeOuterProduct(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer col, int colOffset, java.nio.ByteBuffer row, int rowOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && col.isDirect() && col.order() == java.nio.ByteOrder.nativeOrder() && row.isDirect() && row.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeOuterProduct_unsafe(dest, destOffset, col, colOffset, row, rowOffset);
        return Double3x3OpsKernelsByteBuffer.makeOuterProduct_api(dest, destOffset, col, colOffset, row, rowOffset);
    }

    /** {@link #makeOuterProduct(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeOuterProduct(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment col, long colOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        double[] _destArray, _colArray, _rowArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_colArray = heapDoubles(col, colOffset)) != null
                && (_rowArray = heapDoubles(row, rowOffset)) != null) {
            makeOuterProduct(_destArray, heapIndex(dest, destOffset, 3), _colArray, heapIndex(col, colOffset, 3), _rowArray, heapIndex(row, rowOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && col.isNative() && row.isNative()) return Double3x3OpsKernelsSegment.makeOuterProduct_unsafe(dest, destOffset, col, colOffset, row, rowOffset);
        return Double3x3OpsKernelsSegment.makeOuterProduct_api(dest, destOffset, col, colOffset, row, rowOffset);
    }

    /** {@link #makeOuterProduct(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeOuterProduct(long dest, long col, long row) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeOuterProduct_unsafe(dest, col, row);
        makeOuterProduct(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(col, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(row, 24L), 0L);
        return dest;
    }

    /**
     * Apply a rotation transformation that makes {@code +z} point along ({@code dirX},
     * {@code dirY}, {@code dirZ}) to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code L} the "look along" matrix, then the new
     * matrix will be {@code M * L}. So when transforming a vector {@code v} with the new matrix by
     * using {@code M * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input - an up vector parallel to the view direction (to within about 64 units in
     * the last place) or zero, or a zero view direction - writes zero rows for the axes it leaves
     * undetermined.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @return {@code dest}
     */
    public static double[] lookAlong(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
        return lookAlong_sbeab1de5_1(dest, destOffset, upX, upY, upZ, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_sbeab1de5_1(double[] dest, int destOffset, double upX, double upY, double upZ, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
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
        dest[destOffset] = Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39));
        dest[destOffset + 1] = Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39));
        dest[destOffset + 2] = Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39));
        dest[destOffset + 3] = Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48));
        dest[destOffset + 4] = Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48));
        dest[destOffset + 5] = Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48));
        dest[destOffset + 6] = Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13));
        return lookAlong_sbeab1de5_2(dest, destOffset, _self10, _self20, _self11, _self21, _self12, _self22, _t11, _t12, _t13);
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_sbeab1de5_2(double[] dest, int destOffset, double _self10, double _self20, double _self11, double _self21, double _self12, double _self22, double _t11, double _t12, double _t13) {
        dest[destOffset + 7] = Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13));
        dest[destOffset + 8] = Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13));
        return dest;
    }

    /** {@link #lookAlong(double[], int, double[], int, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer lookAlong(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.lookAlong_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return Double3x3OpsKernelsTypedBuffer.lookAlong_api(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@link #lookAlong(double[], int, double[], int, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lookAlong(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.lookAlong_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return Double3x3OpsKernelsByteBuffer.lookAlong_api(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@link #lookAlong(double[], int, double[], int, double, double, double, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment lookAlong(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            lookAlong(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.lookAlong_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return Double3x3OpsKernelsSegment.lookAlong_api(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@link #lookAlong(double[], int, double[], int, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long lookAlong(long dest, long src, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.lookAlong_unsafe(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
        lookAlong(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    /**
     * Apply a rotation transformation that makes {@code +z} point along {@code dir} to this matrix
     * and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code L} the "look along" matrix, then the new
     * matrix will be {@code M * L}. So when transforming a vector {@code v} with the new matrix by
     * using {@code M * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input - an up vector parallel to the view direction (to within about 64 units in
     * the last place) or zero, or a zero view direction - writes zero rows for the axes it leaves
     * undetermined.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param dir the storage holding the direction to look along, i.e. the direction the local
     *        {@code +z} axis is mapped to
     * @param dirOffset the element index in {@code dir} at which the vector starts
     * @param up the storage holding the direction of "up"
     * @param upOffset the element index in {@code up} at which the vector starts
     * @return {@code dest}
     */
    public static double[] lookAlong(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
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
        return lookAlong_s6dd6d72b_1(dest, destOffset, src[srcOffset], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 7], src[srcOffset + 8], _upx, _upy, _upz, _t11, _t12, _t13, Math.fma(_t18, _t13, -(_t19 * _t12)), Math.fma(_t19, _t11, -(_t20 * _t13)), Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_s6dd6d72b_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29) {
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
        double _t46 = Math.fma(_t39, _t12, -(_t38 * _t13));
        double _t47 = Math.fma(_t37, _t13, -(_t39 * _t11));
        double _t48 = Math.fma(_t38, _t11, -(_t37 * _t12));
        dest[destOffset] = Math.fma(_self02, _t37, Math.fma(_self00, _t38, _self01 * _t39));
        dest[destOffset + 1] = Math.fma(_self12, _t37, Math.fma(_self10, _t38, _self11 * _t39));
        dest[destOffset + 2] = Math.fma(_self22, _t37, Math.fma(_self20, _t38, _self21 * _t39));
        dest[destOffset + 3] = Math.fma(_self02, _t46, Math.fma(_self00, _t47, _self01 * _t48));
        dest[destOffset + 4] = Math.fma(_self12, _t46, Math.fma(_self10, _t47, _self11 * _t48));
        return lookAlong_s6dd6d72b_2(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t11, _t12, _t13, _t46, _t47, _t48);
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_s6dd6d72b_2(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t12, double _t13, double _t46, double _t47, double _t48) {
        dest[destOffset + 5] = Math.fma(_self22, _t46, Math.fma(_self20, _t47, _self21 * _t48));
        dest[destOffset + 6] = Math.fma(_self02, _t11, Math.fma(_self00, _t12, _self01 * _t13));
        dest[destOffset + 7] = Math.fma(_self12, _t11, Math.fma(_self10, _t12, _self11 * _t13));
        dest[destOffset + 8] = Math.fma(_self22, _t11, Math.fma(_self20, _t12, _self21 * _t13));
        return dest;
    }

    /** {@link #lookAlong(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer lookAlong(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer dir, int dirOffset, java.nio.DoubleBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.lookAlong_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return Double3x3OpsKernelsTypedBuffer.lookAlong_api(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@link #lookAlong(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lookAlong(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.lookAlong_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return Double3x3OpsKernelsByteBuffer.lookAlong_api(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@link #lookAlong(double[], int, double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment lookAlong(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        double[] _destArray, _srcArray, _dirArray, _upArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_dirArray = heapDoubles(dir, dirOffset)) != null
                && (_upArray = heapDoubles(up, upOffset)) != null) {
            lookAlong(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _dirArray, heapIndex(dir, dirOffset, 3), _upArray, heapIndex(up, upOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && dir.isNative() && up.isNative()) return Double3x3OpsKernelsSegment.lookAlong_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return Double3x3OpsKernelsSegment.lookAlong_api(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@link #lookAlong(double[], int, double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long lookAlong(long dest, long src, long dir, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.lookAlong_unsafe(dest, src, dir, up);
        lookAlong(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 24L), 0L);
        return dest;
    }

    /**
     * Set this matrix to the rotation part of the unit dual quaternion ({@code dqRX}, {@code dqRY},
     * {@code dqRZ}, {@code dqRW}, {@code dqDX}, {@code dqDY}, {@code dqDZ}, {@code dqDW}) (the
     * encoded translation is dropped).
     * <p>
     * Valid input: {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)} must be a unit dual
     * quaternion.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param dqRX the {@code rX} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqRY the {@code rY} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqRZ the {@code rZ} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqRW the {@code rW} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqDX the {@code dX} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqDY the {@code dY} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqDZ the {@code dZ} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqDW the {@code dW} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @return {@code dest}
     */
    public static double[] makeFromDualQuat(double[] dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        double _sp0 = dqRX + dqRX;
        double _t0 = dqRY * dqRY;
        double _t2 = dqRZ * dqRW;
        double _t3 = dqRY * dqRW;
        double _t4 = dqRX * dqRX;
        double _t5 = dqRY * dqRZ;
        double _t6 = Math.fma(-2.0, dqRZ * dqRZ, 1.0);
        dest[destOffset] = Math.fma(-2.0, _t0, _t6);
        dest[destOffset + 1] = 2.0 * Math.fma(dqRX, dqRY, _t2);
        dest[destOffset + 2] = Math.fma(-2.0, _t3, _sp0 * dqRZ);
        dest[destOffset + 3] = Math.fma(-2.0, _t2, _sp0 * dqRY);
        dest[destOffset + 4] = Math.fma(-2.0, _t4, _t6);
        dest[destOffset + 5] = 2.0 * Math.fma(dqRX, dqRW, _t5);
        dest[destOffset + 6] = 2.0 * Math.fma(dqRX, dqRZ, _t3);
        dest[destOffset + 7] = Math.fma(-2.0, dqRX * dqRW, _t5 + _t5);
        dest[destOffset + 8] = Math.fma(-2.0, _t4, Math.fma(-2.0, _t0, 1.0));
        return dest;
    }

    /** {@link #makeFromDualQuat(double[], int, double, double, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeFromDualQuat(java.nio.DoubleBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeFromDualQuat_unsafe(dest, destOffset, dqRX, dqRY, dqRZ, dqRW);
        return Double3x3OpsKernelsTypedBuffer.makeFromDualQuat_api(dest, destOffset, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
    }

    /** {@link #makeFromDualQuat(double[], int, double, double, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeFromDualQuat(java.nio.ByteBuffer dest, int destOffset, double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeFromDualQuat_unsafe(dest, destOffset, dqRX, dqRY, dqRZ, dqRW);
        return Double3x3OpsKernelsByteBuffer.makeFromDualQuat_api(dest, destOffset, dqRX, dqRY, dqRZ, dqRW);
    }

    /** {@link #makeFromDualQuat(double[], int, double, double, double, double, double, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeFromDualQuat(java.lang.foreign.MemorySegment dest, long destOffset, double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeFromDualQuat(_destArray, heapIndex(dest, destOffset, 3), dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeFromDualQuat_unsafe(dest, destOffset, dqRX, dqRY, dqRZ, dqRW);
        return Double3x3OpsKernelsSegment.makeFromDualQuat_api(dest, destOffset, dqRX, dqRY, dqRZ, dqRW);
    }

    /** {@link #makeFromDualQuat(double[], int, double, double, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeFromDualQuat(long dest, double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeFromDualQuat_unsafe(dest, dqRX, dqRY, dqRZ, dqRW);
        makeFromDualQuat(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
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
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = -_t0;
        dest[destOffset + 4] = _t1;
        dest[destOffset + 5] = 0.0;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 1.0;
        return dest;
    }

    /** {@link #makeRotation(double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeRotation(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeRotation_unsafe(dest, destOffset, angle);
        return Double3x3OpsKernelsTypedBuffer.makeRotation_api(dest, destOffset, angle);
    }

    /** {@link #makeRotation(double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotation(java.nio.ByteBuffer dest, int destOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotation_unsafe(dest, destOffset, angle);
        return Double3x3OpsKernelsByteBuffer.makeRotation_api(dest, destOffset, angle);
    }

    /** {@link #makeRotation(double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotation(java.lang.foreign.MemorySegment dest, long destOffset, double angle) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeRotation(_destArray, heapIndex(dest, destOffset, 3), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeRotation_unsafe(dest, destOffset, angle);
        return Double3x3OpsKernelsSegment.makeRotation_api(dest, destOffset, angle);
    }

    /** {@link #makeRotation(double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotation(long dest, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotation_unsafe(dest, angle);
        makeRotation(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, angle);
        return dest;
    }

    /**
     * Set this matrix to a rotation of {@code angle} radians about the axis ({@code axisX},
     * {@code axisY}, {@code axisZ}).
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return {@code dest}
     */
    public static double[] makeRotationAxis(double[] dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisY;
        double _t3 = axisX * axisZ;
        double _t4 = axisY * axisZ;
        double _t5 = 1.0 - _t1;
        dest[destOffset] = Math.fma(_t5, axisX * axisX, _t1);
        dest[destOffset + 1] = Math.fma(axisZ, _t0, _t5 * _t2);
        dest[destOffset + 2] = Math.fma(_t5, _t3, -(axisY * _t0));
        dest[destOffset + 3] = Math.fma(_t5, _t2, -(axisZ * _t0));
        dest[destOffset + 4] = Math.fma(_t5, axisY * axisY, _t1);
        dest[destOffset + 5] = Math.fma(axisX, _t0, _t5 * _t4);
        dest[destOffset + 6] = Math.fma(axisY, _t0, _t5 * _t3);
        dest[destOffset + 7] = Math.fma(_t5, _t4, -(axisX * _t0));
        dest[destOffset + 8] = Math.fma(_t5, axisZ * axisZ, _t1);
        return dest;
    }

    /** {@link #makeRotationAxis(double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeRotationAxis(java.nio.DoubleBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeRotationAxis_unsafe(dest, destOffset, angle, axisX, axisY, axisZ);
        return Double3x3OpsKernelsTypedBuffer.makeRotationAxis_api(dest, destOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #makeRotationAxis(double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationAxis(java.nio.ByteBuffer dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationAxis_unsafe(dest, destOffset, angle, axisX, axisY, axisZ);
        return Double3x3OpsKernelsByteBuffer.makeRotationAxis_api(dest, destOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #makeRotationAxis(double[], int, double, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationAxis(java.lang.foreign.MemorySegment dest, long destOffset, double angle, double axisX, double axisY, double axisZ) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeRotationAxis(_destArray, heapIndex(dest, destOffset, 3), angle, axisX, axisY, axisZ);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeRotationAxis_unsafe(dest, destOffset, angle, axisX, axisY, axisZ);
        return Double3x3OpsKernelsSegment.makeRotationAxis_api(dest, destOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #makeRotationAxis(double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationAxis(long dest, double angle, double axisX, double axisY, double axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationAxis_unsafe(dest, angle, axisX, axisY, axisZ);
        makeRotationAxis(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, angle, axisX, axisY, axisZ);
        return dest;
    }

    /**
     * Set this matrix to a rotation of {@code angle} radians about the axis {@code axis}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param axis the storage holding the rotation axis
     * @param axisOffset the element index in {@code axis} at which the vector starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static double[] makeRotationAxis(double[] dest, int destOffset, double[] axis, int axisOffset, double angle) {
        double _axisx = axis[axisOffset];
        double _axisy = axis[axisOffset + 1];
        double _axisz = axis[axisOffset + 2];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisy;
        double _t3 = _axisx * _axisz;
        double _t4 = _axisy * _axisz;
        double _t5 = 1.0 - _t1;
        dest[destOffset] = Math.fma(_t5, _axisx * _axisx, _t1);
        dest[destOffset + 1] = Math.fma(_axisz, _t0, _t5 * _t2);
        dest[destOffset + 2] = Math.fma(_t5, _t3, -(_axisy * _t0));
        dest[destOffset + 3] = Math.fma(_t5, _t2, -(_axisz * _t0));
        dest[destOffset + 4] = Math.fma(_t5, _axisy * _axisy, _t1);
        dest[destOffset + 5] = Math.fma(_axisx, _t0, _t5 * _t4);
        dest[destOffset + 6] = Math.fma(_axisy, _t0, _t5 * _t3);
        dest[destOffset + 7] = Math.fma(_t5, _t4, -(_axisx * _t0));
        dest[destOffset + 8] = Math.fma(_t5, _axisz * _axisz, _t1);
        return dest;
    }

    /** {@link #makeRotationAxis(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeRotationAxis(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeRotationAxis_unsafe(dest, destOffset, axis, axisOffset, angle);
        return Double3x3OpsKernelsTypedBuffer.makeRotationAxis_api(dest, destOffset, axis, axisOffset, angle);
    }

    /** {@link #makeRotationAxis(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationAxis(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationAxis_unsafe(dest, destOffset, axis, axisOffset, angle);
        return Double3x3OpsKernelsByteBuffer.makeRotationAxis_api(dest, destOffset, axis, axisOffset, angle);
    }

    /** {@link #makeRotationAxis(double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        double[] _destArray, _axisArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_axisArray = heapDoubles(axis, axisOffset)) != null) {
            makeRotationAxis(_destArray, heapIndex(dest, destOffset, 3), _axisArray, heapIndex(axis, axisOffset, 3), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && axis.isNative()) return Double3x3OpsKernelsSegment.makeRotationAxis_unsafe(dest, destOffset, axis, axisOffset, angle);
        return Double3x3OpsKernelsSegment.makeRotationAxis_api(dest, destOffset, axis, axisOffset, angle);
    }

    /** {@link #makeRotationAxis(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationAxis(long dest, long axis, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationAxis_unsafe(dest, axis, angle);
        makeRotationAxis(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(axis, 24L), 0L, angle);
        return dest;
    }

    /**
     * Set this matrix to a rotation that makes {@code +z} point along ({@code dirX}, {@code dirY},
     * {@code dirZ}).
     * <p>
     * Degenerate input - an up vector parallel to the view direction (to within about 64 units in
     * the last place) or zero, or a zero view direction - writes zero rows for the axes it leaves
     * undetermined.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @return {@code dest}
     */
    public static double[] makeRotationLookAlong(double[] dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
        dest[destOffset] = _t37;
        dest[destOffset + 1] = _t39;
        dest[destOffset + 2] = _t38;
        return makeRotationLookAlong_sf06b0b9a_1(dest, destOffset, _t11, _t12, _t13, _t37, _t38, _t39);
    }

    /** Piece 2 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private static double[] makeRotationLookAlong_sf06b0b9a_1(double[] dest, int destOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        dest[destOffset + 3] = Math.fma(_t38, _t13, -(_t39 * _t11));
        dest[destOffset + 4] = Math.fma(_t37, _t11, -(_t38 * _t12));
        dest[destOffset + 5] = Math.fma(_t39, _t12, -(_t37 * _t13));
        dest[destOffset + 6] = _t12;
        dest[destOffset + 7] = _t13;
        dest[destOffset + 8] = _t11;
        return dest;
    }

    /** {@link #makeRotationLookAlong(double[], int, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeRotationLookAlong(java.nio.DoubleBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeRotationLookAlong_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return Double3x3OpsKernelsTypedBuffer.makeRotationLookAlong_api(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@link #makeRotationLookAlong(double[], int, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationLookAlong(java.nio.ByteBuffer dest, int destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationLookAlong_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return Double3x3OpsKernelsByteBuffer.makeRotationLookAlong_api(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@link #makeRotationLookAlong(double[], int, double, double, double, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationLookAlong(java.lang.foreign.MemorySegment dest, long destOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeRotationLookAlong(_destArray, heapIndex(dest, destOffset, 3), dirX, dirY, dirZ, upX, upY, upZ);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeRotationLookAlong_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return Double3x3OpsKernelsSegment.makeRotationLookAlong_api(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@link #makeRotationLookAlong(double[], int, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationLookAlong(long dest, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationLookAlong_unsafe(dest, dirX, dirY, dirZ, upX, upY, upZ);
        makeRotationLookAlong(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    /**
     * Set this matrix to a rotation that makes {@code +z} point along {@code dir}.
     * <p>
     * Degenerate input - an up vector parallel to the view direction (to within about 64 units in
     * the last place) or zero, or a zero view direction - writes zero rows for the axes it leaves
     * undetermined.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param dir the storage holding the direction to look along, i.e. the direction the local
     *        {@code +z} axis is mapped to
     * @param dirOffset the element index in {@code dir} at which the vector starts
     * @param up the storage holding the direction of "up"
     * @param upOffset the element index in {@code up} at which the vector starts
     * @return {@code dest}
     */
    public static double[] makeRotationLookAlong(double[] dest, int destOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _dirz = dir[dirOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _upz = up[upOffset + 2];
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
        return makeRotationLookAlong_s9356e4ce_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private static double[] makeRotationLookAlong_s9356e4ce_1(double[] dest, int destOffset, double _upx, double _upy, double _upz, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
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
        dest[destOffset] = _t37;
        dest[destOffset + 1] = _t39;
        dest[destOffset + 2] = _t38;
        dest[destOffset + 3] = Math.fma(_t38, _t13, -(_t39 * _t11));
        dest[destOffset + 4] = Math.fma(_t37, _t11, -(_t38 * _t12));
        dest[destOffset + 5] = Math.fma(_t39, _t12, -(_t37 * _t13));
        dest[destOffset + 6] = _t12;
        dest[destOffset + 7] = _t13;
        dest[destOffset + 8] = _t11;
        return dest;
    }

    /** {@link #makeRotationLookAlong(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeRotationLookAlong(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer dir, int dirOffset, java.nio.DoubleBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeRotationLookAlong_unsafe(dest, destOffset, dir, dirOffset, up, upOffset);
        return Double3x3OpsKernelsTypedBuffer.makeRotationLookAlong_api(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@link #makeRotationLookAlong(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationLookAlong(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationLookAlong_unsafe(dest, destOffset, dir, dirOffset, up, upOffset);
        return Double3x3OpsKernelsByteBuffer.makeRotationLookAlong_api(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@link #makeRotationLookAlong(double[], int, double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationLookAlong(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        double[] _destArray, _dirArray, _upArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_dirArray = heapDoubles(dir, dirOffset)) != null
                && (_upArray = heapDoubles(up, upOffset)) != null) {
            makeRotationLookAlong(_destArray, heapIndex(dest, destOffset, 3), _dirArray, heapIndex(dir, dirOffset, 3), _upArray, heapIndex(up, upOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && dir.isNative() && up.isNative()) return Double3x3OpsKernelsSegment.makeRotationLookAlong_unsafe(dest, destOffset, dir, dirOffset, up, upOffset);
        return Double3x3OpsKernelsSegment.makeRotationLookAlong_api(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@link #makeRotationLookAlong(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationLookAlong(long dest, long dir, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationLookAlong_unsafe(dest, dir, up);
        makeRotationLookAlong(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 24L), 0L);
        return dest;
    }

    /**
     * Set this matrix to the rotation represented by the quaternion ({@code qX}, {@code qY},
     * {@code qZ}, {@code qW}).
     * <p>
     * Valid input: {@code (qX, qY, qZ, qW)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param qX the {@code x} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qY the {@code y} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qZ the {@code z} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qW the {@code w} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @return {@code dest}
     */
    public static double[] makeRotationQuat(double[] dest, int destOffset, double qX, double qY, double qZ, double qW) {
        double _t0 = qZ * qZ;
        double _t1 = qZ * qW;
        double _t2 = qY * qW;
        dest[destOffset] = Math.fma(-2.0, Math.fma(qY, qY, _t0), 1.0);
        dest[destOffset + 1] = 2.0 * Math.fma(qX, qY, _t1);
        dest[destOffset + 2] = 2.0 * Math.fma(qX, qZ, -_t2);
        dest[destOffset + 3] = 2.0 * Math.fma(qX, qY, -_t1);
        dest[destOffset + 4] = Math.fma(-2.0, Math.fma(qX, qX, _t0), 1.0);
        dest[destOffset + 5] = 2.0 * Math.fma(qX, qW, qY * qZ);
        dest[destOffset + 6] = 2.0 * Math.fma(qX, qZ, _t2);
        dest[destOffset + 7] = 2.0 * Math.fma(qY, qZ, -(qX * qW));
        dest[destOffset + 8] = Math.fma(-2.0, Math.fma(qX, qX, qY * qY), 1.0);
        return dest;
    }

    /** {@link #makeRotationQuat(double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeRotationQuat(java.nio.DoubleBuffer dest, int destOffset, double qX, double qY, double qZ, double qW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeRotationQuat_unsafe(dest, destOffset, qX, qY, qZ, qW);
        return Double3x3OpsKernelsTypedBuffer.makeRotationQuat_api(dest, destOffset, qX, qY, qZ, qW);
    }

    /** {@link #makeRotationQuat(double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationQuat(java.nio.ByteBuffer dest, int destOffset, double qX, double qY, double qZ, double qW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationQuat_unsafe(dest, destOffset, qX, qY, qZ, qW);
        return Double3x3OpsKernelsByteBuffer.makeRotationQuat_api(dest, destOffset, qX, qY, qZ, qW);
    }

    /** {@link #makeRotationQuat(double[], int, double, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationQuat(java.lang.foreign.MemorySegment dest, long destOffset, double qX, double qY, double qZ, double qW) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeRotationQuat(_destArray, heapIndex(dest, destOffset, 3), qX, qY, qZ, qW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeRotationQuat_unsafe(dest, destOffset, qX, qY, qZ, qW);
        return Double3x3OpsKernelsSegment.makeRotationQuat_api(dest, destOffset, qX, qY, qZ, qW);
    }

    /** {@link #makeRotationQuat(double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationQuat(long dest, double qX, double qY, double qZ, double qW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationQuat_unsafe(dest, qX, qY, qZ, qW);
        makeRotationQuat(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, qX, qY, qZ, qW);
        return dest;
    }

    /**
     * Set this matrix to the rotation represented by the quaternion {@code q}.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param q the storage holding the rotation quaternion
     * @param qOffset the element index in {@code q} at which the quaternion starts
     * @return {@code dest}
     */
    public static double[] makeRotationQuat(double[] dest, int destOffset, double[] q, int qOffset) {
        double _qx = q[qOffset];
        double _qy = q[qOffset + 1];
        double _qz = q[qOffset + 2];
        double _qw = q[qOffset + 3];
        double _t0 = _qz * _qz;
        double _t1 = _qz * _qw;
        double _t2 = _qy * _qw;
        dest[destOffset] = Math.fma(-2.0, Math.fma(_qy, _qy, _t0), 1.0);
        dest[destOffset + 1] = 2.0 * Math.fma(_qx, _qy, _t1);
        dest[destOffset + 2] = 2.0 * Math.fma(_qx, _qz, -_t2);
        dest[destOffset + 3] = 2.0 * Math.fma(_qx, _qy, -_t1);
        dest[destOffset + 4] = Math.fma(-2.0, Math.fma(_qx, _qx, _t0), 1.0);
        dest[destOffset + 5] = 2.0 * Math.fma(_qx, _qw, _qy * _qz);
        dest[destOffset + 6] = 2.0 * Math.fma(_qx, _qz, _t2);
        dest[destOffset + 7] = 2.0 * Math.fma(_qy, _qz, -(_qx * _qw));
        dest[destOffset + 8] = Math.fma(-2.0, Math.fma(_qx, _qx, _qy * _qy), 1.0);
        return dest;
    }

    /** {@link #makeRotationQuat(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeRotationQuat(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer q, int qOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && q.isDirect() && q.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeRotationQuat_unsafe(dest, destOffset, q, qOffset);
        return Double3x3OpsKernelsTypedBuffer.makeRotationQuat_api(dest, destOffset, q, qOffset);
    }

    /** {@link #makeRotationQuat(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationQuat(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer q, int qOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && q.isDirect() && q.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationQuat_unsafe(dest, destOffset, q, qOffset);
        return Double3x3OpsKernelsByteBuffer.makeRotationQuat_api(dest, destOffset, q, qOffset);
    }

    /** {@link #makeRotationQuat(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationQuat(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        double[] _destArray, _qArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_qArray = heapDoubles(q, qOffset)) != null) {
            makeRotationQuat(_destArray, heapIndex(dest, destOffset, 3), _qArray, heapIndex(q, qOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && q.isNative()) return Double3x3OpsKernelsSegment.makeRotationQuat_unsafe(dest, destOffset, q, qOffset);
        return Double3x3OpsKernelsSegment.makeRotationQuat_api(dest, destOffset, q, qOffset);
    }

    /** {@link #makeRotationQuat(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationQuat(long dest, long q) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationQuat_unsafe(dest, q);
        makeRotationQuat(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(q, 32L), 0L);
        return dest;
    }

    /**
     * Set this matrix to a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static double[] makeRotationX(double[] dest, int destOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = 1.0;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t1;
        dest[destOffset + 5] = _t0;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = -_t0;
        dest[destOffset + 8] = _t1;
        return dest;
    }

    /** {@link #makeRotationX(double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeRotationX(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeRotationX_unsafe(dest, destOffset, angle);
        return Double3x3OpsKernelsTypedBuffer.makeRotationX_api(dest, destOffset, angle);
    }

    /** {@link #makeRotationX(double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationX(java.nio.ByteBuffer dest, int destOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationX_unsafe(dest, destOffset, angle);
        return Double3x3OpsKernelsByteBuffer.makeRotationX_api(dest, destOffset, angle);
    }

    /** {@link #makeRotationX(double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationX(java.lang.foreign.MemorySegment dest, long destOffset, double angle) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeRotationX(_destArray, heapIndex(dest, destOffset, 3), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeRotationX_unsafe(dest, destOffset, angle);
        return Double3x3OpsKernelsSegment.makeRotationX_api(dest, destOffset, angle);
    }

    /** {@link #makeRotationX(double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationX(long dest, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationX_unsafe(dest, angle);
        makeRotationX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, angle);
        return dest;
    }

    /**
     * Set this matrix to a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians
     * about the X, Y and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a
     * vector is rotated about the Z axis first, then Y, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return {@code dest}
     */
    public static double[] makeRotationXYZ(double[] dest, int destOffset, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t0;
        double _t7 = _t0 * _t5;
        dest[destOffset] = _t3 * _t4;
        dest[destOffset + 1] = Math.fma(_t6, _t4, _t1 * _t5);
        dest[destOffset + 2] = Math.fma(_t2, _t1, -(_t7 * _t4));
        dest[destOffset + 3] = -(_t1 * _t3);
        dest[destOffset + 4] = Math.fma(_t5, _t4, -(_t6 * _t1));
        dest[destOffset + 5] = Math.fma(_t7, _t1, _t2 * _t4);
        dest[destOffset + 6] = _t0;
        dest[destOffset + 7] = -(_t2 * _t3);
        dest[destOffset + 8] = _t5 * _t3;
        return dest;
    }

    /** {@link #makeRotationXYZ(double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeRotationXYZ(java.nio.DoubleBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeRotationXYZ_unsafe(dest, destOffset, angleX, angleY, angleZ);
        return Double3x3OpsKernelsTypedBuffer.makeRotationXYZ_api(dest, destOffset, angleX, angleY, angleZ);
    }

    /** {@link #makeRotationXYZ(double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationXYZ(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleY, double angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationXYZ_unsafe(dest, destOffset, angleX, angleY, angleZ);
        return Double3x3OpsKernelsByteBuffer.makeRotationXYZ_api(dest, destOffset, angleX, angleY, angleZ);
    }

    /** {@link #makeRotationXYZ(double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationXYZ(java.lang.foreign.MemorySegment dest, long destOffset, double angleX, double angleY, double angleZ) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeRotationXYZ(_destArray, heapIndex(dest, destOffset, 3), angleX, angleY, angleZ);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeRotationXYZ_unsafe(dest, destOffset, angleX, angleY, angleZ);
        return Double3x3OpsKernelsSegment.makeRotationXYZ_api(dest, destOffset, angleX, angleY, angleZ);
    }

    /** {@link #makeRotationXYZ(double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationXYZ(long dest, double angleX, double angleY, double angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationXYZ_unsafe(dest, angleX, angleY, angleZ);
        makeRotationXYZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, angleX, angleY, angleZ);
        return dest;
    }

    /**
     * Set this matrix to a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians
     * about the X, Z and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a
     * vector is rotated about the Y axis first, then Z, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return {@code dest}
     */
    public static double[] makeRotationXZY(double[] dest, int destOffset, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t1 * _t5;
        dest[destOffset] = _t3 * _t4;
        dest[destOffset + 1] = Math.fma(_t7, _t3, _t2 * _t0);
        dest[destOffset + 2] = Math.fma(_t6, _t3, -(_t0 * _t5));
        dest[destOffset + 3] = -_t1;
        dest[destOffset + 4] = _t5 * _t4;
        dest[destOffset + 5] = _t2 * _t4;
        dest[destOffset + 6] = _t0 * _t4;
        dest[destOffset + 7] = Math.fma(_t7, _t0, -(_t2 * _t3));
        dest[destOffset + 8] = Math.fma(_t6, _t0, _t5 * _t3);
        return dest;
    }

    /** {@link #makeRotationXZY(double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeRotationXZY(java.nio.DoubleBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeRotationXZY_unsafe(dest, destOffset, angleX, angleZ, angleY);
        return Double3x3OpsKernelsTypedBuffer.makeRotationXZY_api(dest, destOffset, angleX, angleZ, angleY);
    }

    /** {@link #makeRotationXZY(double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationXZY(java.nio.ByteBuffer dest, int destOffset, double angleX, double angleZ, double angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationXZY_unsafe(dest, destOffset, angleX, angleZ, angleY);
        return Double3x3OpsKernelsByteBuffer.makeRotationXZY_api(dest, destOffset, angleX, angleZ, angleY);
    }

    /** {@link #makeRotationXZY(double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationXZY(java.lang.foreign.MemorySegment dest, long destOffset, double angleX, double angleZ, double angleY) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeRotationXZY(_destArray, heapIndex(dest, destOffset, 3), angleX, angleZ, angleY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeRotationXZY_unsafe(dest, destOffset, angleX, angleZ, angleY);
        return Double3x3OpsKernelsSegment.makeRotationXZY_api(dest, destOffset, angleX, angleZ, angleY);
    }

    /** {@link #makeRotationXZY(double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationXZY(long dest, double angleX, double angleZ, double angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationXZY_unsafe(dest, angleX, angleZ, angleY);
        makeRotationXZY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, angleX, angleZ, angleY);
        return dest;
    }

    /**
     * Set this matrix to a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static double[] makeRotationY(double[] dest, int destOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = _t1;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = -_t0;
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 1.0;
        dest[destOffset + 5] = 0.0;
        dest[destOffset + 6] = _t0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = _t1;
        return dest;
    }

    /** {@link #makeRotationY(double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeRotationY(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeRotationY_unsafe(dest, destOffset, angle);
        return Double3x3OpsKernelsTypedBuffer.makeRotationY_api(dest, destOffset, angle);
    }

    /** {@link #makeRotationY(double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationY(java.nio.ByteBuffer dest, int destOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationY_unsafe(dest, destOffset, angle);
        return Double3x3OpsKernelsByteBuffer.makeRotationY_api(dest, destOffset, angle);
    }

    /** {@link #makeRotationY(double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationY(java.lang.foreign.MemorySegment dest, long destOffset, double angle) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeRotationY(_destArray, heapIndex(dest, destOffset, 3), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeRotationY_unsafe(dest, destOffset, angle);
        return Double3x3OpsKernelsSegment.makeRotationY_api(dest, destOffset, angle);
    }

    /** {@link #makeRotationY(double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationY(long dest, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationY_unsafe(dest, angle);
        makeRotationY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, angle);
        return dest;
    }

    /**
     * Set this matrix to a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians
     * about the Y, X and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a
     * vector is rotated about the Z axis first, then X, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return {@code dest}
     */
    public static double[] makeRotationYXZ(double[] dest, int destOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t3;
        dest[destOffset] = Math.fma(_t6, _t2, _t3 * _t4);
        dest[destOffset + 1] = _t2 * _t5;
        dest[destOffset + 2] = Math.fma(_t7, _t2, -(_t1 * _t4));
        dest[destOffset + 3] = Math.fma(_t6, _t4, -(_t2 * _t3));
        dest[destOffset + 4] = _t5 * _t4;
        dest[destOffset + 5] = Math.fma(_t7, _t4, _t1 * _t2);
        dest[destOffset + 6] = _t1 * _t5;
        dest[destOffset + 7] = -_t0;
        dest[destOffset + 8] = _t5 * _t3;
        return dest;
    }

    /** {@link #makeRotationYXZ(double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeRotationYXZ(java.nio.DoubleBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeRotationYXZ_unsafe(dest, destOffset, angleY, angleX, angleZ);
        return Double3x3OpsKernelsTypedBuffer.makeRotationYXZ_api(dest, destOffset, angleY, angleX, angleZ);
    }

    /** {@link #makeRotationYXZ(double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationYXZ(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleX, double angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationYXZ_unsafe(dest, destOffset, angleY, angleX, angleZ);
        return Double3x3OpsKernelsByteBuffer.makeRotationYXZ_api(dest, destOffset, angleY, angleX, angleZ);
    }

    /** {@link #makeRotationYXZ(double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationYXZ(java.lang.foreign.MemorySegment dest, long destOffset, double angleY, double angleX, double angleZ) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeRotationYXZ(_destArray, heapIndex(dest, destOffset, 3), angleY, angleX, angleZ);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeRotationYXZ_unsafe(dest, destOffset, angleY, angleX, angleZ);
        return Double3x3OpsKernelsSegment.makeRotationYXZ_api(dest, destOffset, angleY, angleX, angleZ);
    }

    /** {@link #makeRotationYXZ(double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationYXZ(long dest, double angleY, double angleX, double angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationYXZ_unsafe(dest, angleY, angleX, angleZ);
        makeRotationYXZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, angleY, angleX, angleZ);
        return dest;
    }

    /**
     * Set this matrix to a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians
     * about the Y, Z and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a
     * vector is rotated about the X axis first, then Z, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return {@code dest}
     */
    public static double[] makeRotationYZX(double[] dest, int destOffset, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t1 * _t3;
        dest[destOffset] = _t3 * _t4;
        dest[destOffset + 1] = _t1;
        dest[destOffset + 2] = -(_t0 * _t4);
        dest[destOffset + 3] = Math.fma(_t2, _t0, -(_t7 * _t5));
        dest[destOffset + 4] = _t5 * _t4;
        dest[destOffset + 5] = Math.fma(_t6, _t5, _t2 * _t3);
        dest[destOffset + 6] = Math.fma(_t7, _t2, _t0 * _t5);
        dest[destOffset + 7] = -(_t2 * _t4);
        dest[destOffset + 8] = Math.fma(_t5, _t3, -(_t6 * _t2));
        return dest;
    }

    /** {@link #makeRotationYZX(double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeRotationYZX(java.nio.DoubleBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeRotationYZX_unsafe(dest, destOffset, angleY, angleZ, angleX);
        return Double3x3OpsKernelsTypedBuffer.makeRotationYZX_api(dest, destOffset, angleY, angleZ, angleX);
    }

    /** {@link #makeRotationYZX(double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationYZX(java.nio.ByteBuffer dest, int destOffset, double angleY, double angleZ, double angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationYZX_unsafe(dest, destOffset, angleY, angleZ, angleX);
        return Double3x3OpsKernelsByteBuffer.makeRotationYZX_api(dest, destOffset, angleY, angleZ, angleX);
    }

    /** {@link #makeRotationYZX(double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationYZX(java.lang.foreign.MemorySegment dest, long destOffset, double angleY, double angleZ, double angleX) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeRotationYZX(_destArray, heapIndex(dest, destOffset, 3), angleY, angleZ, angleX);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeRotationYZX_unsafe(dest, destOffset, angleY, angleZ, angleX);
        return Double3x3OpsKernelsSegment.makeRotationYZX_api(dest, destOffset, angleY, angleZ, angleX);
    }

    /** {@link #makeRotationYZX(double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationYZX(long dest, double angleY, double angleZ, double angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationYZX_unsafe(dest, angleY, angleZ, angleX);
        makeRotationYZX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, angleY, angleZ, angleX);
        return dest;
    }

    /**
     * Set this matrix to a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static double[] makeRotationZ(double[] dest, int destOffset, double angle) {
        return Double3x3Ops.makeRotation(dest, destOffset, angle);
    }

    /** {@link #makeRotationZ(double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeRotationZ(java.nio.DoubleBuffer dest, int destOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeRotation_unsafe(dest, destOffset, angle);
        return Double3x3OpsKernelsTypedBuffer.makeRotation_api(dest, destOffset, angle);
    }

    /** {@link #makeRotationZ(double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationZ(java.nio.ByteBuffer dest, int destOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotation_unsafe(dest, destOffset, angle);
        return Double3x3OpsKernelsByteBuffer.makeRotation_api(dest, destOffset, angle);
    }

    /** {@link #makeRotationZ(double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationZ(java.lang.foreign.MemorySegment dest, long destOffset, double angle) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeRotationZ(_destArray, heapIndex(dest, destOffset, 3), angle);
            return dest;
        }
        return Double3x3Ops.makeRotation(dest, destOffset, angle);
    }

    /** {@link #makeRotationZ(double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationZ(long dest, double angle) {
        return Double3x3Ops.makeRotation(dest, angle);
    }

    /**
     * Set this matrix to a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians
     * about the Z, X and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a
     * vector is rotated about the Y axis first, then X, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return {@code dest}
     */
    public static double[] makeRotationZXY(double[] dest, int destOffset, double angleZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t2 * _t4;
        dest[destOffset] = Math.fma(_t3, _t4, -(_t6 * _t0));
        dest[destOffset + 1] = Math.fma(_t7, _t0, _t1 * _t3);
        dest[destOffset + 2] = -(_t0 * _t5);
        dest[destOffset + 3] = -(_t1 * _t5);
        dest[destOffset + 4] = _t5 * _t4;
        dest[destOffset + 5] = _t2;
        dest[destOffset + 6] = Math.fma(_t6, _t3, _t0 * _t4);
        dest[destOffset + 7] = Math.fma(_t0, _t1, -(_t7 * _t3));
        dest[destOffset + 8] = _t5 * _t3;
        return dest;
    }

    /** {@link #makeRotationZXY(double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeRotationZXY(java.nio.DoubleBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeRotationZXY_unsafe(dest, destOffset, angleZ, angleX, angleY);
        return Double3x3OpsKernelsTypedBuffer.makeRotationZXY_api(dest, destOffset, angleZ, angleX, angleY);
    }

    /** {@link #makeRotationZXY(double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationZXY(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleX, double angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationZXY_unsafe(dest, destOffset, angleZ, angleX, angleY);
        return Double3x3OpsKernelsByteBuffer.makeRotationZXY_api(dest, destOffset, angleZ, angleX, angleY);
    }

    /** {@link #makeRotationZXY(double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationZXY(java.lang.foreign.MemorySegment dest, long destOffset, double angleZ, double angleX, double angleY) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeRotationZXY(_destArray, heapIndex(dest, destOffset, 3), angleZ, angleX, angleY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeRotationZXY_unsafe(dest, destOffset, angleZ, angleX, angleY);
        return Double3x3OpsKernelsSegment.makeRotationZXY_api(dest, destOffset, angleZ, angleX, angleY);
    }

    /** {@link #makeRotationZXY(double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationZXY(long dest, double angleZ, double angleX, double angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationZXY_unsafe(dest, angleZ, angleX, angleY);
        makeRotationZXY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, angleZ, angleX, angleY);
        return dest;
    }

    /**
     * Set this matrix to a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians
     * about the Z, Y and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a
     * vector is rotated about the X axis first, then Y, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return {@code dest}
     */
    public static double[] makeRotationZYX(double[] dest, int destOffset, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t4;
        dest[destOffset] = _t3 * _t4;
        dest[destOffset + 1] = _t1 * _t3;
        dest[destOffset + 2] = -_t0;
        dest[destOffset + 3] = Math.fma(_t7, _t2, -(_t1 * _t5));
        dest[destOffset + 4] = Math.fma(_t6, _t2, _t5 * _t4);
        dest[destOffset + 5] = _t2 * _t3;
        dest[destOffset + 6] = Math.fma(_t7, _t5, _t2 * _t1);
        dest[destOffset + 7] = Math.fma(_t6, _t5, -(_t2 * _t4));
        dest[destOffset + 8] = _t5 * _t3;
        return dest;
    }

    /** {@link #makeRotationZYX(double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeRotationZYX(java.nio.DoubleBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeRotationZYX_unsafe(dest, destOffset, angleZ, angleY, angleX);
        return Double3x3OpsKernelsTypedBuffer.makeRotationZYX_api(dest, destOffset, angleZ, angleY, angleX);
    }

    /** {@link #makeRotationZYX(double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationZYX(java.nio.ByteBuffer dest, int destOffset, double angleZ, double angleY, double angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeRotationZYX_unsafe(dest, destOffset, angleZ, angleY, angleX);
        return Double3x3OpsKernelsByteBuffer.makeRotationZYX_api(dest, destOffset, angleZ, angleY, angleX);
    }

    /** {@link #makeRotationZYX(double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationZYX(java.lang.foreign.MemorySegment dest, long destOffset, double angleZ, double angleY, double angleX) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeRotationZYX(_destArray, heapIndex(dest, destOffset, 3), angleZ, angleY, angleX);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeRotationZYX_unsafe(dest, destOffset, angleZ, angleY, angleX);
        return Double3x3OpsKernelsSegment.makeRotationZYX_api(dest, destOffset, angleZ, angleY, angleX);
    }

    /** {@link #makeRotationZYX(double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationZYX(long dest, double angleZ, double angleY, double angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeRotationZYX_unsafe(dest, angleZ, angleY, angleX);
        makeRotationZYX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, angleZ, angleY, angleX);
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
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = vY;
        dest[destOffset + 5] = 0.0;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 1.0;
        return dest;
    }

    /** {@link #makeScaling(double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeScaling(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeScaling_unsafe(dest, destOffset, vX, vY);
        return Double3x3OpsKernelsTypedBuffer.makeScaling_api(dest, destOffset, vX, vY);
    }

    /** {@link #makeScaling(double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeScaling(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeScaling_unsafe(dest, destOffset, vX, vY);
        return Double3x3OpsKernelsByteBuffer.makeScaling_api(dest, destOffset, vX, vY);
    }

    /** {@link #makeScaling(double[], int, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeScaling(java.lang.foreign.MemorySegment dest, long destOffset, double vX, double vY) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeScaling(_destArray, heapIndex(dest, destOffset, 3), vX, vY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeScaling_unsafe(dest, destOffset, vX, vY);
        return Double3x3OpsKernelsSegment.makeScaling_api(dest, destOffset, vX, vY);
    }

    /** {@link #makeScaling(double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeScaling(long dest, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeScaling_unsafe(dest, vX, vY);
        makeScaling(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, vX, vY);
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
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _vy;
        dest[destOffset + 5] = 0.0;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 1.0;
        return dest;
    }

    /** {@link #makeScaling(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeScaling(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeScaling_unsafe(dest, destOffset, v, vOffset);
        return Double3x3OpsKernelsTypedBuffer.makeScaling_api(dest, destOffset, v, vOffset);
    }

    /** {@link #makeScaling(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeScaling(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeScaling_unsafe(dest, destOffset, v, vOffset);
        return Double3x3OpsKernelsByteBuffer.makeScaling_api(dest, destOffset, v, vOffset);
    }

    /** {@link #makeScaling(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeScaling(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double[] _destArray, _vArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_vArray = heapDoubles(v, vOffset)) != null) {
            makeScaling(_destArray, heapIndex(dest, destOffset, 3), _vArray, heapIndex(v, vOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && v.isNative()) return Double3x3OpsKernelsSegment.makeScaling_unsafe(dest, destOffset, v, vOffset);
        return Double3x3OpsKernelsSegment.makeScaling_api(dest, destOffset, v, vOffset);
    }

    /** {@link #makeScaling(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeScaling(long dest, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeScaling_unsafe(dest, v);
        makeScaling(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 16L), 0L);
        return dest;
    }

    /**
     * Set this matrix to a scaling transformation that scales by {@code s} of the x and y axes only
     * (the 2D homogeneous {@code diag(s, s, 1)}: the third row and column are left unscaled).
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
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = s;
        dest[destOffset + 5] = 0.0;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        dest[destOffset + 8] = 1.0;
        return dest;
    }

    /** {@link #makeScaling(double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeScaling(java.nio.DoubleBuffer dest, int destOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeScaling_unsafe(dest, destOffset, s);
        return Double3x3OpsKernelsTypedBuffer.makeScaling_api(dest, destOffset, s);
    }

    /** {@link #makeScaling(double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeScaling(java.nio.ByteBuffer dest, int destOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeScaling_unsafe(dest, destOffset, s);
        return Double3x3OpsKernelsByteBuffer.makeScaling_api(dest, destOffset, s);
    }

    /** {@link #makeScaling(double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeScaling(java.lang.foreign.MemorySegment dest, long destOffset, double s) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeScaling(_destArray, heapIndex(dest, destOffset, 3), s);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeScaling_unsafe(dest, destOffset, s);
        return Double3x3OpsKernelsSegment.makeScaling_api(dest, destOffset, s);
    }

    /** {@link #makeScaling(double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeScaling(long dest, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeScaling_unsafe(dest, s);
        makeScaling(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, s);
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
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 1.0;
        dest[destOffset + 5] = 0.0;
        dest[destOffset + 6] = vX;
        dest[destOffset + 7] = vY;
        dest[destOffset + 8] = 1.0;
        return dest;
    }

    /** {@link #makeTranslation(double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeTranslation(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeTranslation_unsafe(dest, destOffset, vX, vY);
        return Double3x3OpsKernelsTypedBuffer.makeTranslation_api(dest, destOffset, vX, vY);
    }

    /** {@link #makeTranslation(double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeTranslation(java.nio.ByteBuffer dest, int destOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeTranslation_unsafe(dest, destOffset, vX, vY);
        return Double3x3OpsKernelsByteBuffer.makeTranslation_api(dest, destOffset, vX, vY);
    }

    /** {@link #makeTranslation(double[], int, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeTranslation(java.lang.foreign.MemorySegment dest, long destOffset, double vX, double vY) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeTranslation(_destArray, heapIndex(dest, destOffset, 3), vX, vY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeTranslation_unsafe(dest, destOffset, vX, vY);
        return Double3x3OpsKernelsSegment.makeTranslation_api(dest, destOffset, vX, vY);
    }

    /** {@link #makeTranslation(double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeTranslation(long dest, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeTranslation_unsafe(dest, vX, vY);
        makeTranslation(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, vX, vY);
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
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = 1.0;
        dest[destOffset + 5] = 0.0;
        dest[destOffset + 6] = _vx;
        dest[destOffset + 7] = _vy;
        dest[destOffset + 8] = 1.0;
        return dest;
    }

    /** {@link #makeTranslation(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeTranslation(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeTranslation_unsafe(dest, destOffset, v, vOffset);
        return Double3x3OpsKernelsTypedBuffer.makeTranslation_api(dest, destOffset, v, vOffset);
    }

    /** {@link #makeTranslation(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeTranslation(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeTranslation_unsafe(dest, destOffset, v, vOffset);
        return Double3x3OpsKernelsByteBuffer.makeTranslation_api(dest, destOffset, v, vOffset);
    }

    /** {@link #makeTranslation(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeTranslation(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double[] _destArray, _vArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_vArray = heapDoubles(v, vOffset)) != null) {
            makeTranslation(_destArray, heapIndex(dest, destOffset, 3), _vArray, heapIndex(v, vOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && v.isNative()) return Double3x3OpsKernelsSegment.makeTranslation_unsafe(dest, destOffset, v, vOffset);
        return Double3x3OpsKernelsSegment.makeTranslation_api(dest, destOffset, v, vOffset);
    }

    /** {@link #makeTranslation(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeTranslation(long dest, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeTranslation_unsafe(dest, v);
        makeTranslation(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 16L), 0L);
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
        dest[destOffset + 3] = 0.0;
        dest[destOffset + 4] = _t1_inv + _t1_inv;
        dest[destOffset + 5] = 0.0;
        dest[destOffset + 6] = -((left + right) * _t0_inv);
        dest[destOffset + 7] = -((bottom + top) * _t1_inv);
        dest[destOffset + 8] = 1.0;
        return dest;
    }

    /** {@link #makeView(double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeView(java.nio.DoubleBuffer dest, int destOffset, double left, double right, double bottom, double top) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.makeView_unsafe(dest, destOffset, left, right, bottom, top);
        return Double3x3OpsKernelsTypedBuffer.makeView_api(dest, destOffset, left, right, bottom, top);
    }

    /** {@link #makeView(double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeView(java.nio.ByteBuffer dest, int destOffset, double left, double right, double bottom, double top) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.makeView_unsafe(dest, destOffset, left, right, bottom, top);
        return Double3x3OpsKernelsByteBuffer.makeView_api(dest, destOffset, left, right, bottom, top);
    }

    /** {@link #makeView(double[], int, double, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeView(java.lang.foreign.MemorySegment dest, long destOffset, double left, double right, double bottom, double top) {
        double[] _destArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null) {
            makeView(_destArray, heapIndex(dest, destOffset, 3), left, right, bottom, top);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Double3x3OpsKernelsSegment.makeView_unsafe(dest, destOffset, left, right, bottom, top);
        return Double3x3OpsKernelsSegment.makeView_api(dest, destOffset, left, right, bottom, top);
    }

    /** {@link #makeView(double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeView(long dest, double left, double right, double bottom, double top) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.makeView_unsafe(dest, left, right, bottom, top);
        makeView(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, left, right, bottom, top);
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = Math.fma(_self00, _t1, -(_self10 * _t0));
        dest[destOffset + 1] = Math.fma(_self00, _t0, _self10 * _t1);
        dest[destOffset + 2] = _self20;
        dest[destOffset + 3] = Math.fma(_self01, _t1, -(_self11 * _t0));
        dest[destOffset + 4] = Math.fma(_self01, _t0, _self11 * _t1);
        dest[destOffset + 5] = _self21;
        dest[destOffset + 6] = Math.fma(_self02, _t1, -(_self12 * _t0));
        dest[destOffset + 7] = Math.fma(_self02, _t0, _self12 * _t1);
        dest[destOffset + 8] = _self22;
        return dest;
    }

    /** {@link #preRotate(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preRotate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.preRotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsTypedBuffer.preRotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotate(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preRotate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.preRotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsByteBuffer.preRotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotate(double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preRotate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            preRotate(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.preRotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsSegment.preRotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotate(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preRotate(long dest, long src, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.preRotate_unsafe(dest, src, angle);
        preRotate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, angle);
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t0 = Math.sin(angle);
        double _t2 = Math.sin(0.5 * angle);
        double _t3 = Math.cosFromSin(_t0, angle);
        double _t8 = (_t2 + _t2) * _t2;
        double _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        dest[destOffset] = Math.fma(_self20, _t9, Math.fma(_self00, _t3, -(_self10 * _t0)));
        dest[destOffset + 1] = Math.fma(_self20, _t10, Math.fma(_self00, _t0, _self10 * _t3));
        dest[destOffset + 2] = _self20;
        dest[destOffset + 3] = Math.fma(_self21, _t9, Math.fma(_self01, _t3, -(_self11 * _t0)));
        dest[destOffset + 4] = Math.fma(_self21, _t10, Math.fma(_self01, _t0, _self11 * _t3));
        dest[destOffset + 5] = _self21;
        dest[destOffset + 6] = Math.fma(_self22, _t9, Math.fma(_self02, _t3, -(_self12 * _t0)));
        dest[destOffset + 7] = Math.fma(_self22, _t10, Math.fma(_self02, _t0, _self12 * _t3));
        dest[destOffset + 8] = _self22;
        return dest;
    }

    /** {@link #preRotateAround(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preRotateAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.preRotateAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return Double3x3OpsKernelsTypedBuffer.preRotateAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@link #preRotateAround(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preRotateAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.preRotateAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return Double3x3OpsKernelsByteBuffer.preRotateAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@link #preRotateAround(double[], int, double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preRotateAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotX, double pivotY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            preRotateAround(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angle, pivotX, pivotY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.preRotateAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return Double3x3OpsKernelsSegment.preRotateAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@link #preRotateAround(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preRotateAround(long dest, long src, double angle, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.preRotateAround_unsafe(dest, src, angle, pivotX, pivotY);
        preRotateAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, angle, pivotX, pivotY);
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
        return preRotateAround(dest, destOffset, src, srcOffset, angle, pivot[pivotOffset], pivot[pivotOffset + 1]);
    }

    /** {@link #preRotateAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preRotateAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.preRotateAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Double3x3OpsKernelsTypedBuffer.preRotateAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #preRotateAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preRotateAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.preRotateAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Double3x3OpsKernelsByteBuffer.preRotateAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && pivot.isNative()) return Double3x3OpsKernelsSegment.preRotateAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Double3x3OpsKernelsSegment.preRotateAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #preRotateAround(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preRotateAround(long dest, long src, long pivot, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.preRotateAround_unsafe(dest, src, pivot, angle);
        preRotateAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(pivot, 16L), 0L, angle);
        return dest;
    }

    /**
     * Pre-multiply a rotation of {@code angle} radians about the axis ({@code axisX},
     * {@code axisY}, {@code axisZ}) onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return {@code dest}
     */
    public static double[] preRotateAxis(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t4 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        double _t18 = Math.fma(_t11, axisX * axisX, _t1);
        double _t21 = Math.fma(axisY, _t0, _t11 * _t2);
        double _t24 = Math.fma(_t11, _t4, -(axisZ * _t0));
        dest[destOffset] = Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24));
        return preRotateAxis_s913f8e09_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t18, Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), _t21, Math.fma(axisZ, _t0, _t11 * _t4), Math.fma(axisX, _t0, _t11 * _t6), _t24, Math.fma(_t11, _t6, -(axisX * _t0)), Math.fma(_t11, _t2, -(axisY * _t0)));
    }

    /**
     * Piece 2 of {@code preRotateAxis}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code preRotateAxis}; reached only through it.
     */
    private static double[] preRotateAxis_s913f8e09_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest[destOffset + 1] = Math.fma(_self20, _t25, Math.fma(_self00, _t22, _self10 * _t19));
        dest[destOffset + 2] = Math.fma(_self20, _t20, Math.fma(_self00, _t26, _self10 * _t23));
        dest[destOffset + 3] = Math.fma(_self21, _t21, Math.fma(_self01, _t18, _self11 * _t24));
        dest[destOffset + 4] = Math.fma(_self21, _t25, Math.fma(_self01, _t22, _self11 * _t19));
        dest[destOffset + 5] = Math.fma(_self21, _t20, Math.fma(_self01, _t26, _self11 * _t23));
        dest[destOffset + 6] = Math.fma(_self22, _t21, Math.fma(_self02, _t18, _self12 * _t24));
        dest[destOffset + 7] = Math.fma(_self22, _t25, Math.fma(_self02, _t22, _self12 * _t19));
        dest[destOffset + 8] = Math.fma(_self22, _t20, Math.fma(_self02, _t26, _self12 * _t23));
        return dest;
    }

    /** {@link #preRotateAxis(double[], int, double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preRotateAxis(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.preRotateAxis_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return Double3x3OpsKernelsTypedBuffer.preRotateAxis_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #preRotateAxis(double[], int, double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preRotateAxis(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.preRotateAxis_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return Double3x3OpsKernelsByteBuffer.preRotateAxis_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #preRotateAxis(double[], int, double[], int, double, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preRotateAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            preRotateAxis(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angle, axisX, axisY, axisZ);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.preRotateAxis_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return Double3x3OpsKernelsSegment.preRotateAxis_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #preRotateAxis(double[], int, double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preRotateAxis(long dest, long src, double angle, double axisX, double axisY, double axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.preRotateAxis_unsafe(dest, src, angle, axisX, axisY, axisZ);
        preRotateAxis(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, angle, axisX, axisY, axisZ);
        return dest;
    }

    /**
     * Pre-multiply a rotation of {@code angle} radians about the axis {@code axis} onto this matrix
     * and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param axis the storage holding the rotation axis
     * @param axisOffset the element index in {@code axis} at which the vector starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static double[] preRotateAxis(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _axisx = axis[axisOffset];
        double _axisy = axis[axisOffset + 1];
        double _axisz = axis[axisOffset + 2];
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisz;
        double _t4 = _axisx * _axisy;
        double _t6 = _axisy * _axisz;
        double _t11 = 1.0 - _t1;
        double _t18 = Math.fma(_t11, _axisx * _axisx, _t1);
        double _t21 = Math.fma(_axisy, _t0, _t11 * _t2);
        double _t24 = Math.fma(_t11, _t4, -(_axisz * _t0));
        dest[destOffset] = Math.fma(_self20, _t21, Math.fma(_self00, _t18, _self10 * _t24));
        return preRotateAxis_s913f8e09_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t18, Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1), _t21, Math.fma(_axisz, _t0, _t11 * _t4), Math.fma(_axisx, _t0, _t11 * _t6), _t24, Math.fma(_t11, _t6, -(_axisx * _t0)), Math.fma(_t11, _t2, -(_axisy * _t0)));
    }

    /** {@link #preRotateAxis(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preRotateAxis(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.preRotateAxis_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return Double3x3OpsKernelsTypedBuffer.preRotateAxis_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@link #preRotateAxis(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preRotateAxis(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.preRotateAxis_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return Double3x3OpsKernelsByteBuffer.preRotateAxis_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@link #preRotateAxis(double[], int, double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preRotateAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        double[] _destArray, _srcArray, _axisArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_axisArray = heapDoubles(axis, axisOffset)) != null) {
            preRotateAxis(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _axisArray, heapIndex(axis, axisOffset, 3), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && axis.isNative()) return Double3x3OpsKernelsSegment.preRotateAxis_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return Double3x3OpsKernelsSegment.preRotateAxis_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@link #preRotateAxis(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preRotateAxis(long dest, long src, long axis, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.preRotateAxis_unsafe(dest, src, axis, angle);
        preRotateAxis(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(axis, 24L), 0L, angle);
        return dest;
    }

    /**
     * Pre-multiply a rotation of {@code angle} radians about the X axis onto this matrix and store
     * the result in {@code dest}.
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
    public static double[] preRotateX(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = Math.fma(_self10, _t1, -(_self20 * _t0));
        dest[destOffset + 2] = Math.fma(_self10, _t0, _self20 * _t1);
        dest[destOffset + 3] = _self01;
        dest[destOffset + 4] = Math.fma(_self11, _t1, -(_self21 * _t0));
        dest[destOffset + 5] = Math.fma(_self11, _t0, _self21 * _t1);
        dest[destOffset + 6] = _self02;
        dest[destOffset + 7] = Math.fma(_self12, _t1, -(_self22 * _t0));
        dest[destOffset + 8] = Math.fma(_self12, _t0, _self22 * _t1);
        return dest;
    }

    /** {@link #preRotateX(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preRotateX(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.preRotateX_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsTypedBuffer.preRotateX_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotateX(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preRotateX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.preRotateX_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsByteBuffer.preRotateX_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotateX(double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preRotateX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            preRotateX(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.preRotateX_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsSegment.preRotateX_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotateX(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preRotateX(long dest, long src, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.preRotateX_unsafe(dest, src, angle);
        preRotateX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, angle);
        return dest;
    }

    /**
     * Pre-multiply a rotation of {@code angle} radians about the Y axis onto this matrix and store
     * the result in {@code dest}.
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
    public static double[] preRotateY(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = Math.fma(_self00, _t1, _self20 * _t0);
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = Math.fma(_self20, _t1, -(_self00 * _t0));
        dest[destOffset + 3] = Math.fma(_self01, _t1, _self21 * _t0);
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = Math.fma(_self21, _t1, -(_self01 * _t0));
        dest[destOffset + 6] = Math.fma(_self02, _t1, _self22 * _t0);
        dest[destOffset + 7] = _self12;
        dest[destOffset + 8] = Math.fma(_self22, _t1, -(_self02 * _t0));
        return dest;
    }

    /** {@link #preRotateY(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preRotateY(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.preRotateY_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsTypedBuffer.preRotateY_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotateY(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preRotateY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.preRotateY_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsByteBuffer.preRotateY_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotateY(double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preRotateY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            preRotateY(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.preRotateY_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsSegment.preRotateY_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotateY(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preRotateY(long dest, long src, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.preRotateY_unsafe(dest, src, angle);
        preRotateY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, angle);
        return dest;
    }

    /**
     * Pre-multiply a rotation of {@code angle} radians about the Z axis onto this matrix and store
     * the result in {@code dest}.
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
    public static double[] preRotateZ(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        return Double3x3Ops.preRotate(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotateZ(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preRotateZ(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.preRotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsTypedBuffer.preRotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotateZ(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preRotateZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.preRotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsByteBuffer.preRotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotateZ(double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preRotateZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            preRotateZ(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angle);
            return dest;
        }
        return Double3x3Ops.preRotate(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotateZ(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preRotateZ(long dest, long src, double angle) {
        return Double3x3Ops.preRotate(dest, src, angle);
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
            int _lo = _l * 3;
            double _eself1 = src[srcOffset + _lo + 1];
            double _eself2 = src[srcOffset + _lo + 2];
            dest[destOffset + _lo] = src[srcOffset + _lo] * vX;
            dest[destOffset + _lo + 1] = _eself1 * vY;
            dest[destOffset + _lo + 2] = _eself2;
        }
        return dest;
    }

    /** {@link #preScale(double[], int, double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preScale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double3x3OpsKernelsTypedBuffer.preScale_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #preScale(double[], int, double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double3x3OpsKernelsByteBuffer.preScale_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #preScale(double[], int, double[], int, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preScale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            preScale(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), vX, vY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.preScale_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double3x3OpsKernelsSegment.preScale_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #preScale(double[], int, double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preScale(long dest, long src, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.preScale_unsafe(dest, src, vX, vY);
        preScale(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, vX, vY);
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
            int _lo = _l * 3;
            double _eself1 = src[srcOffset + _lo + 1];
            double _eself2 = src[srcOffset + _lo + 2];
            dest[destOffset + _lo] = src[srcOffset + _lo] * _vx;
            dest[destOffset + _lo + 1] = _eself1 * _vy;
            dest[destOffset + _lo + 2] = _eself2;
        }
        return dest;
    }

    /** {@link #preScale(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preScale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsTypedBuffer.preScale_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #preScale(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsByteBuffer.preScale_api(dest, destOffset, src, srcOffset, v, vOffset);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && v.isNative()) return Double3x3OpsKernelsSegment.preScale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsSegment.preScale_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #preScale(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preScale(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.preScale_unsafe(dest, src, v);
        preScale(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 16L), 0L);
        return dest;
    }

    /**
     * Pre-multiply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) onto this matrix and store
     * the result in {@code dest}.
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
            int _lo = _l * 3;
            double _eself1 = src[srcOffset + _lo + 1];
            double _eself2 = src[srcOffset + _lo + 2];
            dest[destOffset + _lo] = s * src[srcOffset + _lo];
            dest[destOffset + _lo + 1] = s * _eself1;
            dest[destOffset + _lo + 2] = _eself2;
        }
        return dest;
    }

    /** {@link #preScale(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preScale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, s);
        return Double3x3OpsKernelsTypedBuffer.preScale_api(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #preScale(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.preScale_unsafe(dest, destOffset, src, srcOffset, s);
        return Double3x3OpsKernelsByteBuffer.preScale_api(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #preScale(double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preScale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            preScale(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), s);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.preScale_unsafe(dest, destOffset, src, srcOffset, s);
        return Double3x3OpsKernelsSegment.preScale_api(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #preScale(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preScale(long dest, long src, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.preScale_unsafe(dest, src, s);
        preScale(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, s);
        return dest;
    }

    /**
     * Pre-multiply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) about the pivot point
     * ({@code pivotX}, {@code pivotY}) onto this matrix and store the result in {@code dest}.
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        dest[destOffset] = Math.fma(s, src[srcOffset], _self20 * _t1);
        dest[destOffset + 1] = Math.fma(s, _self10, _self20 * _t2);
        dest[destOffset + 2] = _self20;
        dest[destOffset + 3] = Math.fma(s, _self01, _self21 * _t1);
        dest[destOffset + 4] = Math.fma(s, _self11, _self21 * _t2);
        dest[destOffset + 5] = _self21;
        dest[destOffset + 6] = Math.fma(s, _self02, _self22 * _t1);
        dest[destOffset + 7] = Math.fma(s, _self12, _self22 * _t2);
        dest[destOffset + 8] = _self22;
        return dest;
    }

    /** {@link #preScaleAround(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preScaleAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return Double3x3OpsKernelsTypedBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return Double3x3OpsKernelsByteBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preScaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double pivotX, double pivotY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            preScaleAround(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), s, pivotX, pivotY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.preScaleAround_unsafe(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return Double3x3OpsKernelsSegment.preScaleAround_api(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preScaleAround(long dest, long src, double s, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.preScaleAround_unsafe(dest, src, s, pivotX, pivotY);
        preScaleAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, s, pivotX, pivotY);
        return dest;
    }

    /**
     * Pre-multiply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) about the pivot point
     * {@code pivot} onto this matrix and store the result in {@code dest}.
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t0 = 1.0 - s;
        double _t1 = pivot[pivotOffset] * _t0;
        double _t2 = pivot[pivotOffset + 1] * _t0;
        dest[destOffset] = Math.fma(s, src[srcOffset], _self20 * _t1);
        dest[destOffset + 1] = Math.fma(s, _self10, _self20 * _t2);
        dest[destOffset + 2] = _self20;
        dest[destOffset + 3] = Math.fma(s, _self01, _self21 * _t1);
        dest[destOffset + 4] = Math.fma(s, _self11, _self21 * _t2);
        dest[destOffset + 5] = _self21;
        dest[destOffset + 6] = Math.fma(s, _self02, _self22 * _t1);
        dest[destOffset + 7] = Math.fma(s, _self12, _self22 * _t2);
        dest[destOffset + 8] = _self22;
        return dest;
    }

    /** {@link #preScaleAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preScaleAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return Double3x3OpsKernelsTypedBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return Double3x3OpsKernelsByteBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && pivot.isNative()) return Double3x3OpsKernelsSegment.preScaleAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return Double3x3OpsKernelsSegment.preScaleAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preScaleAround(long dest, long src, long pivot, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.preScaleAround_unsafe(dest, src, pivot, s);
        preScaleAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(pivot, 16L), 0L, s);
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        dest[destOffset] = Math.fma(sX, src[srcOffset], _self20 * _t2);
        dest[destOffset + 1] = Math.fma(sY, _self10, _self20 * _t3);
        dest[destOffset + 2] = _self20;
        dest[destOffset + 3] = Math.fma(sX, _self01, _self21 * _t2);
        dest[destOffset + 4] = Math.fma(sY, _self11, _self21 * _t3);
        dest[destOffset + 5] = _self21;
        dest[destOffset + 6] = Math.fma(sX, _self02, _self22 * _t2);
        dest[destOffset + 7] = Math.fma(sY, _self12, _self22 * _t3);
        dest[destOffset + 8] = _self22;
        return dest;
    }

    /** {@link #preScaleAround(double[], int, double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preScaleAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return Double3x3OpsKernelsTypedBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return Double3x3OpsKernelsByteBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preScaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double pivotX, double pivotY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            preScaleAround(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), sX, sY, pivotX, pivotY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.preScaleAround_unsafe(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return Double3x3OpsKernelsSegment.preScaleAround_api(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preScaleAround(long dest, long src, double sX, double sY, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.preScaleAround_unsafe(dest, src, sX, sY, pivotX, pivotY);
        preScaleAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, sX, sY, pivotX, pivotY);
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _sx = s[sOffset];
        double _sy = s[sOffset + 1];
        double _t2 = pivot[pivotOffset] * (1.0 - _sx);
        double _t3 = pivot[pivotOffset + 1] * (1.0 - _sy);
        dest[destOffset] = Math.fma(_sx, src[srcOffset], _self20 * _t2);
        dest[destOffset + 1] = Math.fma(_sy, _self10, _self20 * _t3);
        dest[destOffset + 2] = _self20;
        dest[destOffset + 3] = Math.fma(_sx, _self01, _self21 * _t2);
        dest[destOffset + 4] = Math.fma(_sy, _self11, _self21 * _t3);
        dest[destOffset + 5] = _self21;
        dest[destOffset + 6] = Math.fma(_sx, _self02, _self22 * _t2);
        dest[destOffset + 7] = Math.fma(_sy, _self12, _self22 * _t3);
        dest[destOffset + 8] = _self22;
        return dest;
    }

    /** {@link #preScaleAround(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preScaleAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer s, int sOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && s.isDirect() && s.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return Double3x3OpsKernelsTypedBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preScaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && s.isDirect() && s.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.preScaleAround_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return Double3x3OpsKernelsByteBuffer.preScaleAround_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && s.isNative() && pivot.isNative()) return Double3x3OpsKernelsSegment.preScaleAround_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return Double3x3OpsKernelsSegment.preScaleAround_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@link #preScaleAround(double[], int, double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preScaleAround(long dest, long src, long s, long pivot) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.preScaleAround_unsafe(dest, src, s, pivot);
        preScaleAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(s, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(pivot, 16L), 0L);
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
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself1 = src[srcOffset + _lo + 1];
            double _eself2 = src[srcOffset + _lo + 2];
            dest[destOffset + _lo] = Math.fma(_eself2, vX, src[srcOffset + _lo]);
            dest[destOffset + _lo + 1] = Math.fma(_eself2, vY, _eself1);
            dest[destOffset + _lo + 2] = _eself2;
        }
        return dest;
    }

    /** {@link #preTranslate(double[], int, double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preTranslate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.preTranslate_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double3x3OpsKernelsTypedBuffer.preTranslate_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #preTranslate(double[], int, double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preTranslate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.preTranslate_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double3x3OpsKernelsByteBuffer.preTranslate_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #preTranslate(double[], int, double[], int, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preTranslate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            preTranslate(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), vX, vY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.preTranslate_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double3x3OpsKernelsSegment.preTranslate_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #preTranslate(double[], int, double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preTranslate(long dest, long src, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.preTranslate_unsafe(dest, src, vX, vY);
        preTranslate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, vX, vY);
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
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        for (int _l = 0; _l < 3; _l++) {
            int _lo = _l * 3;
            double _eself1 = src[srcOffset + _lo + 1];
            double _eself2 = src[srcOffset + _lo + 2];
            dest[destOffset + _lo] = Math.fma(_eself2, _vx, src[srcOffset + _lo]);
            dest[destOffset + _lo + 1] = Math.fma(_eself2, _vy, _eself1);
            dest[destOffset + _lo + 2] = _eself2;
        }
        return dest;
    }

    /** {@link #preTranslate(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preTranslate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.preTranslate_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsTypedBuffer.preTranslate_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #preTranslate(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preTranslate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.preTranslate_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsByteBuffer.preTranslate_api(dest, destOffset, src, srcOffset, v, vOffset);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && v.isNative()) return Double3x3OpsKernelsSegment.preTranslate_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsSegment.preTranslate_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #preTranslate(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preTranslate(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.preTranslate_unsafe(dest, src, v);
        preTranslate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 16L), 0L);
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = Math.fma(_self00, _t1, _self01 * _t0);
        dest[destOffset + 1] = Math.fma(_self10, _t1, _self11 * _t0);
        dest[destOffset + 2] = Math.fma(_self20, _t1, _self21 * _t0);
        dest[destOffset + 3] = Math.fma(_self01, _t1, -(_self00 * _t0));
        dest[destOffset + 4] = Math.fma(_self11, _t1, -(_self10 * _t0));
        dest[destOffset + 5] = Math.fma(_self21, _t1, -(_self20 * _t0));
        dest[destOffset + 6] = _self02;
        dest[destOffset + 7] = _self12;
        dest[destOffset + 8] = _self22;
        return dest;
    }

    /** {@link #rotate(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsTypedBuffer.rotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotate(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsByteBuffer.rotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotate(double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotate(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.rotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsSegment.rotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotate(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotate(long dest, long src, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotate_unsafe(dest, src, angle);
        rotate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, angle);
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        dest[destOffset] = Math.fma(_self00, _t2, _self01 * _t0);
        dest[destOffset + 1] = Math.fma(_self10, _t2, _self11 * _t0);
        dest[destOffset + 2] = Math.fma(_self20, _t2, _self21 * _t0);
        dest[destOffset + 3] = Math.fma(_self01, _t2, -(_self00 * _t0));
        dest[destOffset + 4] = Math.fma(_self11, _t2, -(_self10 * _t0));
        dest[destOffset + 5] = Math.fma(_self21, _t2, -(_self20 * _t0));
        dest[destOffset + 6] = Math.fma(_self00, _t9, Math.fma(_self01, _t10, _self02));
        dest[destOffset + 7] = Math.fma(_self10, _t9, Math.fma(_self11, _t10, _self12));
        dest[destOffset + 8] = Math.fma(_self20, _t9, Math.fma(_self21, _t10, _self22));
        return dest;
    }

    /** {@link #rotateAround(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return Double3x3OpsKernelsTypedBuffer.rotateAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@link #rotateAround(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return Double3x3OpsKernelsByteBuffer.rotateAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@link #rotateAround(double[], int, double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double pivotX, double pivotY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateAround(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angle, pivotX, pivotY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.rotateAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return Double3x3OpsKernelsSegment.rotateAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@link #rotateAround(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateAround(long dest, long src, double angle, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateAround_unsafe(dest, src, angle, pivotX, pivotY);
        rotateAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, angle, pivotX, pivotY);
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
        return rotateAround(dest, destOffset, src, srcOffset, angle, pivot[pivotOffset], pivot[pivotOffset + 1]);
    }

    /** {@link #rotateAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Double3x3OpsKernelsTypedBuffer.rotateAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Double3x3OpsKernelsByteBuffer.rotateAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && pivot.isNative()) return Double3x3OpsKernelsSegment.rotateAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Double3x3OpsKernelsSegment.rotateAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateAround(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateAround(long dest, long src, long pivot, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateAround_unsafe(dest, src, pivot, angle);
        rotateAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(pivot, 16L), 0L, angle);
        return dest;
    }

    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return {@code dest}
     */
    public static double[] rotateAxis(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t5 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        double _t18 = Math.fma(_t11, axisX * axisX, _t1);
        double _t21 = Math.fma(axisZ, _t0, _t11 * _t5);
        double _t24 = Math.fma(_t11, _t2, -(axisY * _t0));
        dest[destOffset] = Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21));
        dest[destOffset + 1] = Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21));
        return rotateAxis_s9b919e40_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t18, Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), _t21, Math.fma(axisX, _t0, _t11 * _t6), Math.fma(axisY, _t0, _t11 * _t2), _t24, Math.fma(_t11, _t5, -(axisZ * _t0)), Math.fma(_t11, _t6, -(axisX * _t0)));
    }

    /** Piece 2 of {@code rotateAxis}, split to fit the inline budget; reached only through it. */
    private static double[] rotateAxis_s9b919e40_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest[destOffset + 2] = Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21));
        dest[destOffset + 3] = Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19));
        dest[destOffset + 4] = Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19));
        dest[destOffset + 5] = Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19));
        dest[destOffset + 6] = Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26));
        dest[destOffset + 7] = Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26));
        dest[destOffset + 8] = Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26));
        return dest;
    }

    /** {@link #rotateAxis(double[], int, double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateAxis(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateAxis_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return Double3x3OpsKernelsTypedBuffer.rotateAxis_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #rotateAxis(double[], int, double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAxis(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateAxis_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return Double3x3OpsKernelsByteBuffer.rotateAxis_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #rotateAxis(double[], int, double[], int, double, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateAxis(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angle, axisX, axisY, axisZ);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.rotateAxis_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return Double3x3OpsKernelsSegment.rotateAxis_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #rotateAxis(double[], int, double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateAxis(long dest, long src, double angle, double axisX, double axisY, double axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateAxis_unsafe(dest, src, angle, axisX, axisY, axisZ);
        rotateAxis(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, angle, axisX, axisY, axisZ);
        return dest;
    }

    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this matrix and
     * store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the matrix
     * @param srcOffset the element index in {@code src} at which the matrix starts
     * @param axis the storage holding the rotation axis
     * @param axisOffset the element index in {@code axis} at which the vector starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static double[] rotateAxis(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        double _t0 = Math.sin(angle);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _axisx = axis[axisOffset];
        double _axisy = axis[axisOffset + 1];
        double _axisz = axis[axisOffset + 2];
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisz;
        double _t5 = _axisx * _axisy;
        double _t6 = _axisy * _axisz;
        double _t11 = 1.0 - _t1;
        double _t18 = Math.fma(_t11, _axisx * _axisx, _t1);
        double _t21 = Math.fma(_axisz, _t0, _t11 * _t5);
        double _t24 = Math.fma(_t11, _t2, -(_axisy * _t0));
        dest[destOffset] = Math.fma(_self02, _t24, Math.fma(_self00, _t18, _self01 * _t21));
        return rotateAxis_s64104566_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t18, Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1), _t21, Math.fma(_axisx, _t0, _t11 * _t6), Math.fma(_axisy, _t0, _t11 * _t2), _t24, Math.fma(_t11, _t5, -(_axisz * _t0)), Math.fma(_t11, _t6, -(_axisx * _t0)));
    }

    /** Piece 2 of {@code rotateAxis}, split to fit the inline budget; reached only through it. */
    private static double[] rotateAxis_s64104566_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24, double _t25, double _t26) {
        dest[destOffset + 1] = Math.fma(_self12, _t24, Math.fma(_self10, _t18, _self11 * _t21));
        dest[destOffset + 2] = Math.fma(_self22, _t24, Math.fma(_self20, _t18, _self21 * _t21));
        dest[destOffset + 3] = Math.fma(_self02, _t22, Math.fma(_self00, _t25, _self01 * _t19));
        dest[destOffset + 4] = Math.fma(_self12, _t22, Math.fma(_self10, _t25, _self11 * _t19));
        dest[destOffset + 5] = Math.fma(_self22, _t22, Math.fma(_self20, _t25, _self21 * _t19));
        dest[destOffset + 6] = Math.fma(_self02, _t20, Math.fma(_self00, _t23, _self01 * _t26));
        dest[destOffset + 7] = Math.fma(_self12, _t20, Math.fma(_self10, _t23, _self11 * _t26));
        dest[destOffset + 8] = Math.fma(_self22, _t20, Math.fma(_self20, _t23, _self21 * _t26));
        return dest;
    }

    /** {@link #rotateAxis(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateAxis(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateAxis_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return Double3x3OpsKernelsTypedBuffer.rotateAxis_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@link #rotateAxis(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAxis(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateAxis_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return Double3x3OpsKernelsByteBuffer.rotateAxis_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@link #rotateAxis(double[], int, double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        double[] _destArray, _srcArray, _axisArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null
                && (_axisArray = heapDoubles(axis, axisOffset)) != null) {
            rotateAxis(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), _axisArray, heapIndex(axis, axisOffset, 3), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && axis.isNative()) return Double3x3OpsKernelsSegment.rotateAxis_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return Double3x3OpsKernelsSegment.rotateAxis_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@link #rotateAxis(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateAxis(long dest, long src, long axis, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateAxis_unsafe(dest, src, axis, angle);
        rotateAxis(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(axis, 24L), 0L, angle);
        return dest;
    }

    /**
     * Apply a rotation of {@code angle} radians about the X axis to this matrix and store the
     * result in {@code dest}.
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
    public static double[] rotateX(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _self20;
        dest[destOffset + 3] = Math.fma(_self01, _t1, _self02 * _t0);
        dest[destOffset + 4] = Math.fma(_self11, _t1, _self12 * _t0);
        dest[destOffset + 5] = Math.fma(_self21, _t1, _self22 * _t0);
        dest[destOffset + 6] = Math.fma(_self02, _t1, -(_self01 * _t0));
        dest[destOffset + 7] = Math.fma(_self12, _t1, -(_self11 * _t0));
        dest[destOffset + 8] = Math.fma(_self22, _t1, -(_self21 * _t0));
        return dest;
    }

    /** {@link #rotateX(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateX(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateX_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsTypedBuffer.rotateX_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateX(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateX_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsByteBuffer.rotateX_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateX(double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateX(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.rotateX_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsSegment.rotateX_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateX(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateX(long dest, long src, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateX_unsafe(dest, src, angle);
        rotateX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, angle);
        return dest;
    }

    /**
     * Apply a rotation of 180 degrees about the X axis to this matrix and store the result in
     * {@code dest}.
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
     * @return {@code dest}
     */
    public static double[] rotateX180(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _self20;
        dest[destOffset + 3] = -_self01;
        dest[destOffset + 4] = -_self11;
        dest[destOffset + 5] = -_self21;
        dest[destOffset + 6] = -_self02;
        dest[destOffset + 7] = -_self12;
        dest[destOffset + 8] = -_self22;
        return dest;
    }

    /** {@link #rotateX180(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateX180(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateX180_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.rotateX180_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateX180(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateX180(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateX180_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.rotateX180_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateX180(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateX180(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateX180(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.rotateX180_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.rotateX180_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateX180(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateX180(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateX180_unsafe(dest, src);
        rotateX180(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Apply a rotation of 270 degrees about the X axis to this matrix and store the result in
     * {@code dest}.
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
     * @return {@code dest}
     */
    public static double[] rotateX270(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _self20;
        dest[destOffset + 3] = -_self02;
        dest[destOffset + 4] = -_self12;
        dest[destOffset + 5] = -_self22;
        dest[destOffset + 6] = _self01;
        dest[destOffset + 7] = _self11;
        dest[destOffset + 8] = _self21;
        return dest;
    }

    /** {@link #rotateX270(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateX270(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateX270_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.rotateX270_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateX270(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateX270(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateX270_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.rotateX270_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateX270(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateX270(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateX270(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.rotateX270_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.rotateX270_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateX270(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateX270(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateX270_unsafe(dest, src);
        rotateX270(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Apply a rotation of 90 degrees about the X axis to this matrix and store the result in
     * {@code dest}.
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
     * @return {@code dest}
     */
    public static double[] rotateX90(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _self10;
        dest[destOffset + 2] = _self20;
        dest[destOffset + 3] = _self02;
        dest[destOffset + 4] = _self12;
        dest[destOffset + 5] = _self22;
        dest[destOffset + 6] = -_self01;
        dest[destOffset + 7] = -_self11;
        dest[destOffset + 8] = -_self21;
        return dest;
    }

    /** {@link #rotateX90(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateX90(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateX90_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.rotateX90_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateX90(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateX90(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateX90_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.rotateX90_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateX90(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateX90(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateX90(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.rotateX90_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.rotateX90_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateX90(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateX90(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateX90_unsafe(dest, src);
        rotateX90(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X), to this matrix and store the result in {@code dest}.
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
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return {@code dest}
     */
    public static double[] rotateXYZ(double[] dest, int destOffset, double[] src, int srcOffset, double angleX, double angleY, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t3 = Math.cosFromSin(_t0, angleX);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleY);
        double _t6 = _t0 * _t2;
        double _t7 = _t2 * _t3;
        double _t13 = _t5 * _t4;
        double _t18 = Math.fma(_t6, _t4, _t1 * _t3);
        double _t20 = Math.fma(_t0, _t1, -(_t7 * _t4));
        dest[destOffset] = Math.fma(_self02, _t20, Math.fma(_self00, _t13, _self01 * _t18));
        dest[destOffset + 1] = Math.fma(_self12, _t20, Math.fma(_self10, _t13, _self11 * _t18));
        dest[destOffset + 2] = Math.fma(_self22, _t20, Math.fma(_self20, _t13, _self21 * _t18));
        return rotateXYZ_s7ff71224_1(dest, destOffset, _t2, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t1 * _t5, _t0 * _t5, _t3 * _t5, Math.fma(_t7, _t1, _t0 * _t4), Math.fma(_t3, _t4, -(_t6 * _t1)));
    }

    /** Piece 2 of {@code rotateXYZ}, split to fit the inline budget; reached only through it. */
    private static double[] rotateXYZ_s7ff71224_1(double[] dest, int destOffset, double _t2, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11, double _t15, double _t19, double _t21) {
        dest[destOffset + 3] = Math.fma(_self02, _t19, Math.fma(_self01, _t21, -(_self00 * _t10)));
        dest[destOffset + 4] = Math.fma(_self12, _t19, Math.fma(_self11, _t21, -(_self10 * _t10)));
        dest[destOffset + 5] = Math.fma(_self22, _t19, Math.fma(_self21, _t21, -(_self20 * _t10)));
        dest[destOffset + 6] = Math.fma(_self02, _t15, Math.fma(_self00, _t2, -(_self01 * _t11)));
        dest[destOffset + 7] = Math.fma(_self12, _t15, Math.fma(_self10, _t2, -(_self11 * _t11)));
        dest[destOffset + 8] = Math.fma(_self22, _t15, Math.fma(_self20, _t2, -(_self21 * _t11)));
        return dest;
    }

    /** {@link #rotateXYZ(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateXYZ(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateXYZ_unsafe(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return Double3x3OpsKernelsTypedBuffer.rotateXYZ_api(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
    }

    /** {@link #rotateXYZ(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateXYZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleY, double angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateXYZ_unsafe(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return Double3x3OpsKernelsByteBuffer.rotateXYZ_api(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
    }

    /** {@link #rotateXYZ(double[], int, double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateXYZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleX, double angleY, double angleZ) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateXYZ(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angleX, angleY, angleZ);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.rotateXYZ_unsafe(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return Double3x3OpsKernelsSegment.rotateXYZ_api(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
    }

    /** {@link #rotateXYZ(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateXYZ(long dest, long src, double angleX, double angleY, double angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateXYZ_unsafe(dest, src, angleX, angleY, angleZ);
        rotateXYZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, angleX, angleY, angleZ);
        return dest;
    }

    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X), to this matrix and store the result in {@code dest}.
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
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return {@code dest}
     */
    public static double[] rotateXZY(double[] dest, int destOffset, double[] src, int srcOffset, double angleX, double angleZ, double angleY) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t3 = Math.cosFromSin(_t2, angleY);
        double _t4 = Math.cosFromSin(_t0, angleX);
        double _t5 = Math.cosFromSin(_t1, angleZ);
        double _t6 = _t0 * _t1;
        double _t9 = _t1 * _t4;
        double _t15 = _t3 * _t5;
        double _t18 = Math.fma(_t9, _t3, _t0 * _t2);
        double _t20 = Math.fma(_t6, _t3, -(_t2 * _t4));
        dest[destOffset] = Math.fma(_self02, _t20, Math.fma(_self00, _t15, _self01 * _t18));
        dest[destOffset + 1] = Math.fma(_self12, _t20, Math.fma(_self10, _t15, _self11 * _t18));
        dest[destOffset + 2] = Math.fma(_self22, _t20, Math.fma(_self20, _t15, _self21 * _t18));
        return rotateXZY_sfaebe028_1(dest, destOffset, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t0 * _t5, _t2 * _t5, _t4 * _t5, Math.fma(_t6, _t2, _t4 * _t3), Math.fma(_t9, _t2, -(_t0 * _t3)));
    }

    /** Piece 2 of {@code rotateXZY}, split to fit the inline budget; reached only through it. */
    private static double[] rotateXZY_sfaebe028_1(double[] dest, int destOffset, double _t1, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t11, double _t16, double _t19, double _t21) {
        dest[destOffset + 3] = Math.fma(_self02, _t10, Math.fma(_self01, _t16, -(_self00 * _t1)));
        dest[destOffset + 4] = Math.fma(_self12, _t10, Math.fma(_self11, _t16, -(_self10 * _t1)));
        dest[destOffset + 5] = Math.fma(_self22, _t10, Math.fma(_self21, _t16, -(_self20 * _t1)));
        dest[destOffset + 6] = Math.fma(_self02, _t19, Math.fma(_self00, _t11, _self01 * _t21));
        dest[destOffset + 7] = Math.fma(_self12, _t19, Math.fma(_self10, _t11, _self11 * _t21));
        dest[destOffset + 8] = Math.fma(_self22, _t19, Math.fma(_self20, _t11, _self21 * _t21));
        return dest;
    }

    /** {@link #rotateXZY(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateXZY(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateXZY_unsafe(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return Double3x3OpsKernelsTypedBuffer.rotateXZY_api(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
    }

    /** {@link #rotateXZY(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateXZY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleX, double angleZ, double angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateXZY_unsafe(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return Double3x3OpsKernelsByteBuffer.rotateXZY_api(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
    }

    /** {@link #rotateXZY(double[], int, double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateXZY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleX, double angleZ, double angleY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateXZY(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angleX, angleZ, angleY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.rotateXZY_unsafe(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return Double3x3OpsKernelsSegment.rotateXZY_api(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
    }

    /** {@link #rotateXZY(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateXZY(long dest, long src, double angleX, double angleZ, double angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateXZY_unsafe(dest, src, angleX, angleZ, angleY);
        rotateXZY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, angleX, angleZ, angleY);
        return dest;
    }

    /**
     * Apply a rotation of -180 degrees about the X axis to this matrix and store the result in
     * {@code dest}.
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
     * @return {@code dest}
     */
    public static double[] rotateXn180(double[] dest, int destOffset, double[] src, int srcOffset) {
        return Double3x3Ops.rotateX180(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateXn180(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateXn180(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateX180_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.rotateX180_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateXn180(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateXn180(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateX180_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.rotateX180_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateXn180(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateXn180(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateXn180(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        return Double3x3Ops.rotateX180(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateXn180(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateXn180(long dest, long src) {
        return Double3x3Ops.rotateX180(dest, src);
    }

    /**
     * Apply a rotation of -270 degrees about the X axis to this matrix and store the result in
     * {@code dest}.
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
     * @return {@code dest}
     */
    public static double[] rotateXn270(double[] dest, int destOffset, double[] src, int srcOffset) {
        return Double3x3Ops.rotateX90(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateXn270(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateXn270(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateX90_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.rotateX90_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateXn270(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateXn270(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateX90_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.rotateX90_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateXn270(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateXn270(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateXn270(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        return Double3x3Ops.rotateX90(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateXn270(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateXn270(long dest, long src) {
        return Double3x3Ops.rotateX90(dest, src);
    }

    /**
     * Apply a rotation of -90 degrees about the X axis to this matrix and store the result in
     * {@code dest}.
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
     * @return {@code dest}
     */
    public static double[] rotateXn90(double[] dest, int destOffset, double[] src, int srcOffset) {
        return Double3x3Ops.rotateX270(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateXn90(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateXn90(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateX270_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.rotateX270_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateXn90(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateXn90(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateX270_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.rotateX270_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateXn90(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateXn90(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateXn90(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        return Double3x3Ops.rotateX270(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateXn90(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateXn90(long dest, long src) {
        return Double3x3Ops.rotateX270(dest, src);
    }

    /**
     * Apply a rotation of {@code angle} radians about the Y axis to this matrix and store the
     * result in {@code dest}.
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
    public static double[] rotateY(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = Math.fma(_self00, _t1, -(_self02 * _t0));
        dest[destOffset + 1] = Math.fma(_self10, _t1, -(_self12 * _t0));
        dest[destOffset + 2] = Math.fma(_self20, _t1, -(_self22 * _t0));
        dest[destOffset + 3] = _self01;
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = _self21;
        dest[destOffset + 6] = Math.fma(_self00, _t0, _self02 * _t1);
        dest[destOffset + 7] = Math.fma(_self10, _t0, _self12 * _t1);
        dest[destOffset + 8] = Math.fma(_self20, _t0, _self22 * _t1);
        return dest;
    }

    /** {@link #rotateY(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateY(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateY_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsTypedBuffer.rotateY_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateY(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateY_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsByteBuffer.rotateY_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateY(double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateY(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.rotateY_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsSegment.rotateY_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateY(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateY(long dest, long src, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateY_unsafe(dest, src, angle);
        rotateY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, angle);
        return dest;
    }

    /**
     * Apply a rotation of 180 degrees about the Y axis to this matrix and store the result in
     * {@code dest}.
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
     * @return {@code dest}
     */
    public static double[] rotateY180(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        dest[destOffset] = -src[srcOffset];
        dest[destOffset + 1] = -_self10;
        dest[destOffset + 2] = -_self20;
        dest[destOffset + 3] = _self01;
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = _self21;
        dest[destOffset + 6] = -_self02;
        dest[destOffset + 7] = -_self12;
        dest[destOffset + 8] = -_self22;
        return dest;
    }

    /** {@link #rotateY180(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateY180(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateY180_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.rotateY180_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateY180(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateY180(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateY180_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.rotateY180_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateY180(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateY180(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateY180(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.rotateY180_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.rotateY180_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateY180(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateY180(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateY180_unsafe(dest, src);
        rotateY180(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Apply a rotation of 270 degrees about the Y axis to this matrix and store the result in
     * {@code dest}.
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
     * @return {@code dest}
     */
    public static double[] rotateY270(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        dest[destOffset] = src[srcOffset + 6];
        dest[destOffset + 1] = _self12;
        dest[destOffset + 2] = _self22;
        dest[destOffset + 3] = _self01;
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = _self21;
        dest[destOffset + 6] = -_self00;
        dest[destOffset + 7] = -_self10;
        dest[destOffset + 8] = -_self20;
        return dest;
    }

    /** {@link #rotateY270(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateY270(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateY270_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.rotateY270_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateY270(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateY270(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateY270_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.rotateY270_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateY270(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateY270(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateY270(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.rotateY270_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.rotateY270_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateY270(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateY270(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateY270_unsafe(dest, src);
        rotateY270(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Apply a rotation of 90 degrees about the Y axis to this matrix and store the result in
     * {@code dest}.
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
     * @return {@code dest}
     */
    public static double[] rotateY90(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        dest[destOffset] = -src[srcOffset + 6];
        dest[destOffset + 1] = -_self12;
        dest[destOffset + 2] = -_self22;
        dest[destOffset + 3] = _self01;
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = _self21;
        dest[destOffset + 6] = _self00;
        dest[destOffset + 7] = _self10;
        dest[destOffset + 8] = _self20;
        return dest;
    }

    /** {@link #rotateY90(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateY90(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateY90_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.rotateY90_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateY90(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateY90(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateY90_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.rotateY90_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateY90(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateY90(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateY90(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.rotateY90_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.rotateY90_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateY90(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateY90(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateY90_unsafe(dest, src);
        rotateY90(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y), to this matrix and store the result in {@code dest}.
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
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return {@code dest}
     */
    public static double[] rotateYXZ(double[] dest, int destOffset, double[] src, int srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t8 = _t0 * _t3;
        double _t10 = _t2 * _t5;
        double _t18 = Math.fma(_t6, _t2, _t3 * _t4);
        double _t20 = Math.fma(_t8, _t2, -(_t1 * _t4));
        dest[destOffset] = Math.fma(_self02, _t20, Math.fma(_self00, _t18, _self01 * _t10));
        dest[destOffset + 1] = Math.fma(_self12, _t20, Math.fma(_self10, _t18, _self11 * _t10));
        dest[destOffset + 2] = Math.fma(_self22, _t20, Math.fma(_self20, _t18, _self21 * _t10));
        return rotateYXZ_s7b402a80_1(dest, destOffset, _t0, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t1 * _t5, _t5 * _t4, _t5 * _t3, Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t6, _t4, -(_t2 * _t3)));
    }

    /** Piece 2 of {@code rotateYXZ}, split to fit the inline budget; reached only through it. */
    private static double[] rotateYXZ_s7b402a80_1(double[] dest, int destOffset, double _t0, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t12, double _t16, double _t17, double _t19, double _t21) {
        dest[destOffset + 3] = Math.fma(_self02, _t19, Math.fma(_self00, _t21, _self01 * _t16));
        dest[destOffset + 4] = Math.fma(_self12, _t19, Math.fma(_self10, _t21, _self11 * _t16));
        dest[destOffset + 5] = Math.fma(_self22, _t19, Math.fma(_self20, _t21, _self21 * _t16));
        dest[destOffset + 6] = Math.fma(_self02, _t17, Math.fma(_self00, _t12, -(_self01 * _t0)));
        dest[destOffset + 7] = Math.fma(_self12, _t17, Math.fma(_self10, _t12, -(_self11 * _t0)));
        dest[destOffset + 8] = Math.fma(_self22, _t17, Math.fma(_self20, _t12, -(_self21 * _t0)));
        return dest;
    }

    /** {@link #rotateYXZ(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateYXZ(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateYXZ_unsafe(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return Double3x3OpsKernelsTypedBuffer.rotateYXZ_api(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    /** {@link #rotateYXZ(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateYXZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleX, double angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateYXZ_unsafe(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return Double3x3OpsKernelsByteBuffer.rotateYXZ_api(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    /** {@link #rotateYXZ(double[], int, double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateYXZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleY, double angleX, double angleZ) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateYXZ(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angleY, angleX, angleZ);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.rotateYXZ_unsafe(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return Double3x3OpsKernelsSegment.rotateYXZ_api(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    /** {@link #rotateYXZ(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateYXZ(long dest, long src, double angleY, double angleX, double angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateYXZ_unsafe(dest, src, angleY, angleX, angleZ);
        rotateYXZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, angleY, angleX, angleZ);
        return dest;
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y), to this matrix and store the result in {@code dest}.
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
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return {@code dest}
     */
    public static double[] rotateYZX(double[] dest, int destOffset, double[] src, int srcOffset, double angleY, double angleZ, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t3 = Math.cosFromSin(_t1, angleZ);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t3;
        double _t9 = _t1 * _t4;
        double _t13 = _t4 * _t3;
        dest[destOffset] = Math.fma(-_self02, _t7, Math.fma(_self00, _t13, _self01 * _t1));
        dest[destOffset + 1] = Math.fma(-_self12, _t7, Math.fma(_self10, _t13, _self11 * _t1));
        dest[destOffset + 2] = Math.fma(-_self22, _t7, Math.fma(_self20, _t13, _self21 * _t1));
        return rotateYZX_s8a5d2810_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2 * _t3, _t5 * _t3, Math.fma(_t6, _t5, _t2 * _t4), Math.fma(_t9, _t2, _t0 * _t5), Math.fma(_t2, _t0, -(_t9 * _t5)), Math.fma(_t5, _t4, -(_t6 * _t2)));
    }

    /** Piece 2 of {@code rotateYZX}, split to fit the inline budget; reached only through it. */
    private static double[] rotateYZX_s8a5d2810_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t11, double _t14, double _t18, double _t19, double _t20, double _t21) {
        dest[destOffset + 3] = Math.fma(_self02, _t18, Math.fma(_self00, _t20, _self01 * _t14));
        dest[destOffset + 4] = Math.fma(_self12, _t18, Math.fma(_self10, _t20, _self11 * _t14));
        dest[destOffset + 5] = Math.fma(_self22, _t18, Math.fma(_self20, _t20, _self21 * _t14));
        dest[destOffset + 6] = Math.fma(_self02, _t21, Math.fma(_self00, _t19, -(_self01 * _t11)));
        dest[destOffset + 7] = Math.fma(_self12, _t21, Math.fma(_self10, _t19, -(_self11 * _t11)));
        dest[destOffset + 8] = Math.fma(_self22, _t21, Math.fma(_self20, _t19, -(_self21 * _t11)));
        return dest;
    }

    /** {@link #rotateYZX(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateYZX(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateYZX_unsafe(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return Double3x3OpsKernelsTypedBuffer.rotateYZX_api(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
    }

    /** {@link #rotateYZX(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateYZX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleY, double angleZ, double angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateYZX_unsafe(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return Double3x3OpsKernelsByteBuffer.rotateYZX_api(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
    }

    /** {@link #rotateYZX(double[], int, double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateYZX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleY, double angleZ, double angleX) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateYZX(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angleY, angleZ, angleX);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.rotateYZX_unsafe(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return Double3x3OpsKernelsSegment.rotateYZX_api(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
    }

    /** {@link #rotateYZX(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateYZX(long dest, long src, double angleY, double angleZ, double angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateYZX_unsafe(dest, src, angleY, angleZ, angleX);
        rotateYZX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, angleY, angleZ, angleX);
        return dest;
    }

    /**
     * Apply a rotation of -180 degrees about the Y axis to this matrix and store the result in
     * {@code dest}.
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
     * @return {@code dest}
     */
    public static double[] rotateYn180(double[] dest, int destOffset, double[] src, int srcOffset) {
        return Double3x3Ops.rotateY180(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateYn180(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateYn180(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateY180_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.rotateY180_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateYn180(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateYn180(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateY180_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.rotateY180_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateYn180(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateYn180(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateYn180(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        return Double3x3Ops.rotateY180(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateYn180(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateYn180(long dest, long src) {
        return Double3x3Ops.rotateY180(dest, src);
    }

    /**
     * Apply a rotation of -270 degrees about the Y axis to this matrix and store the result in
     * {@code dest}.
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
     * @return {@code dest}
     */
    public static double[] rotateYn270(double[] dest, int destOffset, double[] src, int srcOffset) {
        return Double3x3Ops.rotateY90(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateYn270(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateYn270(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateY90_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.rotateY90_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateYn270(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateYn270(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateY90_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.rotateY90_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateYn270(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateYn270(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateYn270(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        return Double3x3Ops.rotateY90(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateYn270(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateYn270(long dest, long src) {
        return Double3x3Ops.rotateY90(dest, src);
    }

    /**
     * Apply a rotation of -90 degrees about the Y axis to this matrix and store the result in
     * {@code dest}.
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
     * @return {@code dest}
     */
    public static double[] rotateYn90(double[] dest, int destOffset, double[] src, int srcOffset) {
        return Double3x3Ops.rotateY270(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateYn90(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateYn90(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateY270_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.rotateY270_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateYn90(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateYn90(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateY270_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.rotateY270_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateYn90(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateYn90(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateYn90(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        return Double3x3Ops.rotateY270(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateYn90(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateYn90(long dest, long src) {
        return Double3x3Ops.rotateY270(dest, src);
    }

    /**
     * Apply a rotation of {@code angle} radians about the Z axis to this matrix and store the
     * result in {@code dest}.
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
    public static double[] rotateZ(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        return Double3x3Ops.rotate(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateZ(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateZ(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsTypedBuffer.rotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateZ(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3x3OpsKernelsByteBuffer.rotate_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateZ(double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateZ(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angle);
            return dest;
        }
        return Double3x3Ops.rotate(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateZ(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateZ(long dest, long src, double angle) {
        return Double3x3Ops.rotate(dest, src, angle);
    }

    /**
     * Apply a rotation of 180 degrees about the Z axis to this matrix and store the result in
     * {@code dest}.
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
     * @return {@code dest}
     */
    public static double[] rotateZ180(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        dest[destOffset] = -src[srcOffset];
        dest[destOffset + 1] = -_self10;
        dest[destOffset + 2] = -_self20;
        dest[destOffset + 3] = -_self01;
        dest[destOffset + 4] = -_self11;
        dest[destOffset + 5] = -_self21;
        dest[destOffset + 6] = _self02;
        dest[destOffset + 7] = _self12;
        dest[destOffset + 8] = _self22;
        return dest;
    }

    /** {@link #rotateZ180(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateZ180(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateZ180_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.rotateZ180_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZ180(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateZ180(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateZ180_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.rotateZ180_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZ180(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateZ180(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateZ180(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.rotateZ180_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.rotateZ180_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZ180(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateZ180(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateZ180_unsafe(dest, src);
        rotateZ180(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Apply a rotation of 270 degrees about the Z axis to this matrix and store the result in
     * {@code dest}.
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
     * @return {@code dest}
     */
    public static double[] rotateZ270(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        dest[destOffset] = -src[srcOffset + 3];
        dest[destOffset + 1] = -_self11;
        dest[destOffset + 2] = -_self21;
        dest[destOffset + 3] = _self00;
        dest[destOffset + 4] = _self10;
        dest[destOffset + 5] = _self20;
        dest[destOffset + 6] = _self02;
        dest[destOffset + 7] = _self12;
        dest[destOffset + 8] = _self22;
        return dest;
    }

    /** {@link #rotateZ270(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateZ270(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateZ270_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.rotateZ270_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZ270(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateZ270(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateZ270_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.rotateZ270_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZ270(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateZ270(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateZ270(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.rotateZ270_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.rotateZ270_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZ270(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateZ270(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateZ270_unsafe(dest, src);
        rotateZ270(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Apply a rotation of 90 degrees about the Z axis to this matrix and store the result in
     * {@code dest}.
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
     * @return {@code dest}
     */
    public static double[] rotateZ90(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        dest[destOffset] = src[srcOffset + 3];
        dest[destOffset + 1] = _self11;
        dest[destOffset + 2] = _self21;
        dest[destOffset + 3] = -_self00;
        dest[destOffset + 4] = -_self10;
        dest[destOffset + 5] = -_self20;
        dest[destOffset + 6] = _self02;
        dest[destOffset + 7] = _self12;
        dest[destOffset + 8] = _self22;
        return dest;
    }

    /** {@link #rotateZ90(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateZ90(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateZ90_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.rotateZ90_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZ90(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateZ90(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateZ90_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.rotateZ90_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZ90(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateZ90(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateZ90(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.rotateZ90_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsSegment.rotateZ90_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZ90(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateZ90(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateZ90_unsafe(dest, src);
        rotateZ90(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z), to this matrix and store the result in {@code dest}.
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
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return {@code dest}
     */
    public static double[] rotateZXY(double[] dest, int destOffset, double[] src, int srcOffset, double angleZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleX);
        double _t2 = Math.sin(angleZ);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t3 = Math.cosFromSin(_t1, angleX);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleZ);
        double _t6 = _t1 * _t2;
        double _t7 = _t0 * _t3;
        double _t8 = _t1 * _t5;
        double _t18 = Math.fma(_t8, _t0, _t2 * _t4);
        double _t20 = Math.fma(_t4, _t5, -(_t6 * _t0));
        dest[destOffset] = Math.fma(-_self02, _t7, Math.fma(_self00, _t20, _self01 * _t18));
        dest[destOffset + 1] = Math.fma(-_self12, _t7, Math.fma(_self10, _t20, _self11 * _t18));
        dest[destOffset + 2] = Math.fma(-_self22, _t7, Math.fma(_self20, _t20, _self21 * _t18));
        return rotateZXY_s504aa0b0_1(dest, destOffset, _t1, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2 * _t3, _t3 * _t5, _t3 * _t4, Math.fma(_t6, _t4, _t0 * _t5), Math.fma(_t0, _t2, -(_t8 * _t4)));
    }

    /** Piece 2 of {@code rotateZXY}, split to fit the inline budget; reached only through it. */
    private static double[] rotateZXY_s504aa0b0_1(double[] dest, int destOffset, double _t1, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t10, double _t14, double _t15, double _t19, double _t21) {
        dest[destOffset + 3] = Math.fma(_self02, _t1, Math.fma(_self01, _t14, -(_self00 * _t10)));
        dest[destOffset + 4] = Math.fma(_self12, _t1, Math.fma(_self11, _t14, -(_self10 * _t10)));
        dest[destOffset + 5] = Math.fma(_self22, _t1, Math.fma(_self21, _t14, -(_self20 * _t10)));
        dest[destOffset + 6] = Math.fma(_self02, _t15, Math.fma(_self00, _t19, _self01 * _t21));
        dest[destOffset + 7] = Math.fma(_self12, _t15, Math.fma(_self10, _t19, _self11 * _t21));
        dest[destOffset + 8] = Math.fma(_self22, _t15, Math.fma(_self20, _t19, _self21 * _t21));
        return dest;
    }

    /** {@link #rotateZXY(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateZXY(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateZXY_unsafe(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return Double3x3OpsKernelsTypedBuffer.rotateZXY_api(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
    }

    /** {@link #rotateZXY(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateZXY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleX, double angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateZXY_unsafe(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return Double3x3OpsKernelsByteBuffer.rotateZXY_api(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
    }

    /** {@link #rotateZXY(double[], int, double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateZXY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleZ, double angleX, double angleY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateZXY(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angleZ, angleX, angleY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.rotateZXY_unsafe(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return Double3x3OpsKernelsSegment.rotateZXY_api(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
    }

    /** {@link #rotateZXY(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateZXY(long dest, long src, double angleZ, double angleX, double angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateZXY_unsafe(dest, src, angleZ, angleX, angleY);
        rotateZXY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, angleZ, angleX, angleY);
        return dest;
    }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z), to this matrix and store the result in {@code dest}.
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
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return {@code dest}
     */
    public static double[] rotateZYX(double[] dest, int destOffset, double[] src, int srcOffset, double angleZ, double angleY, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t8 = _t1 * _t3;
        double _t10 = _t0 * _t4;
        double _t15 = _t3 * _t4;
        dest[destOffset] = Math.fma(-_self02, _t0, Math.fma(_self00, _t15, _self01 * _t8));
        dest[destOffset + 1] = Math.fma(-_self12, _t0, Math.fma(_self10, _t15, _self11 * _t8));
        dest[destOffset + 2] = Math.fma(-_self22, _t0, Math.fma(_self20, _t15, _self21 * _t8));
        return rotateZYX_s153e29f4_1(dest, destOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _t2 * _t3, _t5 * _t3, Math.fma(_t6, _t2, _t5 * _t4), Math.fma(_t10, _t5, _t2 * _t1), Math.fma(_t10, _t2, -(_t1 * _t5)), Math.fma(_t6, _t5, -(_t2 * _t4)));
    }

    /** Piece 2 of {@code rotateZYX}, split to fit the inline budget; reached only through it. */
    private static double[] rotateZYX_s153e29f4_1(double[] dest, int destOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _t9, double _t17, double _t18, double _t19, double _t20, double _t21) {
        dest[destOffset + 3] = Math.fma(_self02, _t9, Math.fma(_self00, _t20, _self01 * _t18));
        dest[destOffset + 4] = Math.fma(_self12, _t9, Math.fma(_self10, _t20, _self11 * _t18));
        dest[destOffset + 5] = Math.fma(_self22, _t9, Math.fma(_self20, _t20, _self21 * _t18));
        dest[destOffset + 6] = Math.fma(_self02, _t17, Math.fma(_self00, _t19, _self01 * _t21));
        dest[destOffset + 7] = Math.fma(_self12, _t17, Math.fma(_self10, _t19, _self11 * _t21));
        dest[destOffset + 8] = Math.fma(_self22, _t17, Math.fma(_self20, _t19, _self21 * _t21));
        return dest;
    }

    /** {@link #rotateZYX(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateZYX(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateZYX_unsafe(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return Double3x3OpsKernelsTypedBuffer.rotateZYX_api(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
    }

    /** {@link #rotateZYX(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateZYX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angleZ, double angleY, double angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateZYX_unsafe(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return Double3x3OpsKernelsByteBuffer.rotateZYX_api(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
    }

    /** {@link #rotateZYX(double[], int, double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateZYX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleZ, double angleY, double angleX) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateZYX(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), angleZ, angleY, angleX);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.rotateZYX_unsafe(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return Double3x3OpsKernelsSegment.rotateZYX_api(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
    }

    /** {@link #rotateZYX(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateZYX(long dest, long src, double angleZ, double angleY, double angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.rotateZYX_unsafe(dest, src, angleZ, angleY, angleX);
        rotateZYX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, angleZ, angleY, angleX);
        return dest;
    }

    /**
     * Apply a rotation of -180 degrees about the Z axis to this matrix and store the result in
     * {@code dest}.
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
     * @return {@code dest}
     */
    public static double[] rotateZn180(double[] dest, int destOffset, double[] src, int srcOffset) {
        return Double3x3Ops.rotateZ180(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZn180(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateZn180(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateZ180_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.rotateZ180_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZn180(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateZn180(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateZ180_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.rotateZ180_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZn180(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateZn180(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateZn180(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        return Double3x3Ops.rotateZ180(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZn180(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateZn180(long dest, long src) {
        return Double3x3Ops.rotateZ180(dest, src);
    }

    /**
     * Apply a rotation of -270 degrees about the Z axis to this matrix and store the result in
     * {@code dest}.
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
     * @return {@code dest}
     */
    public static double[] rotateZn270(double[] dest, int destOffset, double[] src, int srcOffset) {
        return Double3x3Ops.rotateZ90(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZn270(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateZn270(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateZ90_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.rotateZ90_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZn270(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateZn270(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateZ90_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.rotateZ90_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZn270(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateZn270(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateZn270(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        return Double3x3Ops.rotateZ90(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZn270(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateZn270(long dest, long src) {
        return Double3x3Ops.rotateZ90(dest, src);
    }

    /**
     * Apply a rotation of -90 degrees about the Z axis to this matrix and store the result in
     * {@code dest}.
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
     * @return {@code dest}
     */
    public static double[] rotateZn90(double[] dest, int destOffset, double[] src, int srcOffset) {
        return Double3x3Ops.rotateZ270(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZn90(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateZn90(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.rotateZ270_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsTypedBuffer.rotateZ270_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZn90(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateZn90(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.rotateZ270_unsafe(dest, destOffset, src, srcOffset);
        return Double3x3OpsKernelsByteBuffer.rotateZ270_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZn90(double[], int, double[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateZn90(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            rotateZn90(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3));
            return dest;
        }
        return Double3x3Ops.rotateZ270(dest, destOffset, src, srcOffset);
    }

    /** {@link #rotateZn90(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateZn90(long dest, long src) {
        return Double3x3Ops.rotateZ270(dest, src);
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        dest[destOffset] = src[srcOffset] * vX;
        dest[destOffset + 1] = _self10 * vX;
        dest[destOffset + 2] = _self20 * vX;
        dest[destOffset + 3] = _self01 * vY;
        dest[destOffset + 4] = _self11 * vY;
        dest[destOffset + 5] = _self21 * vY;
        dest[destOffset + 6] = _self02;
        dest[destOffset + 7] = _self12;
        dest[destOffset + 8] = _self22;
        return dest;
    }

    /** {@link #scale(double[], int, double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer scale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.scale_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double3x3OpsKernelsTypedBuffer.scale_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #scale(double[], int, double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.scale_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double3x3OpsKernelsByteBuffer.scale_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #scale(double[], int, double[], int, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment scale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            scale(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), vX, vY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.scale_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double3x3OpsKernelsSegment.scale_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #scale(double[], int, double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long scale(long dest, long src, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.scale_unsafe(dest, src, vX, vY);
        scale(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, vX, vY);
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        dest[destOffset] = src[srcOffset] * _vx;
        dest[destOffset + 1] = _self10 * _vx;
        dest[destOffset + 2] = _self20 * _vx;
        dest[destOffset + 3] = _self01 * _vy;
        dest[destOffset + 4] = _self11 * _vy;
        dest[destOffset + 5] = _self21 * _vy;
        dest[destOffset + 6] = _self02;
        dest[destOffset + 7] = _self12;
        dest[destOffset + 8] = _self22;
        return dest;
    }

    /** {@link #scale(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer scale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.scale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsTypedBuffer.scale_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #scale(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.scale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsByteBuffer.scale_api(dest, destOffset, src, srcOffset, v, vOffset);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && v.isNative()) return Double3x3OpsKernelsSegment.scale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsSegment.scale_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #scale(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long scale(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.scale_unsafe(dest, src, v);
        scale(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 16L), 0L);
        return dest;
    }

    /**
     * Apply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) to this matrix and store
     * the result in {@code dest}.
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        dest[destOffset] = s * src[srcOffset];
        dest[destOffset + 1] = s * _self10;
        dest[destOffset + 2] = s * _self20;
        dest[destOffset + 3] = s * _self01;
        dest[destOffset + 4] = s * _self11;
        dest[destOffset + 5] = s * _self21;
        dest[destOffset + 6] = _self02;
        dest[destOffset + 7] = _self12;
        dest[destOffset + 8] = _self22;
        return dest;
    }

    /** {@link #scale(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer scale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.scale_unsafe(dest, destOffset, src, srcOffset, s);
        return Double3x3OpsKernelsTypedBuffer.scale_api(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #scale(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.scale_unsafe(dest, destOffset, src, srcOffset, s);
        return Double3x3OpsKernelsByteBuffer.scale_api(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #scale(double[], int, double[], int, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment scale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            scale(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), s);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.scale_unsafe(dest, destOffset, src, srcOffset, s);
        return Double3x3OpsKernelsSegment.scale_api(dest, destOffset, src, srcOffset, s);
    }

    /** {@link #scale(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long scale(long dest, long src, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.scale_unsafe(dest, src, s);
        scale(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, s);
        return dest;
    }

    /**
     * Apply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) about the pivot point
     * ({@code pivotX}, {@code pivotY}) to this matrix and store the result in {@code dest}.
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        dest[destOffset] = s * _self00;
        dest[destOffset + 1] = s * _self10;
        dest[destOffset + 2] = s * _self20;
        dest[destOffset + 3] = s * _self01;
        dest[destOffset + 4] = s * _self11;
        dest[destOffset + 5] = s * _self21;
        dest[destOffset + 6] = Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02));
        dest[destOffset + 7] = Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12));
        dest[destOffset + 8] = Math.fma(_self20, _t1, Math.fma(_self21, _t2, _self22));
        return dest;
    }

    /** {@link #scaleAround(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer scaleAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return Double3x3OpsKernelsTypedBuffer.scaleAround_api(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
    }

    /** {@link #scaleAround(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return Double3x3OpsKernelsByteBuffer.scaleAround_api(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
    }

    /** {@link #scaleAround(double[], int, double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment scaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double pivotX, double pivotY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            scaleAround(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), s, pivotX, pivotY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.scaleAround_unsafe(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
        return Double3x3OpsKernelsSegment.scaleAround_api(dest, destOffset, src, srcOffset, s, pivotX, pivotY);
    }

    /** {@link #scaleAround(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long scaleAround(long dest, long src, double s, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.scaleAround_unsafe(dest, src, s, pivotX, pivotY);
        scaleAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, s, pivotX, pivotY);
        return dest;
    }

    /**
     * Apply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) about the pivot point
     * {@code pivot} to this matrix and store the result in {@code dest}.
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t0 = 1.0 - s;
        double _t1 = pivot[pivotOffset] * _t0;
        double _t2 = pivot[pivotOffset + 1] * _t0;
        dest[destOffset] = s * _self00;
        dest[destOffset + 1] = s * _self10;
        dest[destOffset + 2] = s * _self20;
        dest[destOffset + 3] = s * _self01;
        dest[destOffset + 4] = s * _self11;
        dest[destOffset + 5] = s * _self21;
        dest[destOffset + 6] = Math.fma(_self00, _t1, Math.fma(_self01, _t2, _self02));
        dest[destOffset + 7] = Math.fma(_self10, _t1, Math.fma(_self11, _t2, _self12));
        dest[destOffset + 8] = Math.fma(_self20, _t1, Math.fma(_self21, _t2, _self22));
        return dest;
    }

    /** {@link #scaleAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer scaleAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return Double3x3OpsKernelsTypedBuffer.scaleAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    /** {@link #scaleAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return Double3x3OpsKernelsByteBuffer.scaleAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && pivot.isNative()) return Double3x3OpsKernelsSegment.scaleAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return Double3x3OpsKernelsSegment.scaleAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    /** {@link #scaleAround(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long scaleAround(long dest, long src, long pivot, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.scaleAround_unsafe(dest, src, pivot, s);
        scaleAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(pivot, 16L), 0L, s);
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        dest[destOffset] = sX * _self00;
        dest[destOffset + 1] = sX * _self10;
        dest[destOffset + 2] = sX * _self20;
        dest[destOffset + 3] = sY * _self01;
        dest[destOffset + 4] = sY * _self11;
        dest[destOffset + 5] = sY * _self21;
        dest[destOffset + 6] = Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02));
        dest[destOffset + 7] = Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12));
        dest[destOffset + 8] = Math.fma(_self20, _t2, Math.fma(_self21, _t3, _self22));
        return dest;
    }

    /** {@link #scaleAround(double[], int, double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer scaleAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return Double3x3OpsKernelsTypedBuffer.scaleAround_api(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
    }

    /** {@link #scaleAround(double[], int, double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sX, double sY, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return Double3x3OpsKernelsByteBuffer.scaleAround_api(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
    }

    /** {@link #scaleAround(double[], int, double[], int, double, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment scaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double pivotX, double pivotY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            scaleAround(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), sX, sY, pivotX, pivotY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.scaleAround_unsafe(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
        return Double3x3OpsKernelsSegment.scaleAround_api(dest, destOffset, src, srcOffset, sX, sY, pivotX, pivotY);
    }

    /** {@link #scaleAround(double[], int, double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long scaleAround(long dest, long src, double sX, double sY, double pivotX, double pivotY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.scaleAround_unsafe(dest, src, sX, sY, pivotX, pivotY);
        scaleAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, sX, sY, pivotX, pivotY);
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _sx = s[sOffset];
        double _sy = s[sOffset + 1];
        double _t2 = pivot[pivotOffset] * (1.0 - _sx);
        double _t3 = pivot[pivotOffset + 1] * (1.0 - _sy);
        dest[destOffset] = _sx * _self00;
        dest[destOffset + 1] = _sx * _self10;
        dest[destOffset + 2] = _sx * _self20;
        dest[destOffset + 3] = _sy * _self01;
        dest[destOffset + 4] = _sy * _self11;
        dest[destOffset + 5] = _sy * _self21;
        dest[destOffset + 6] = Math.fma(_self00, _t2, Math.fma(_self01, _t3, _self02));
        dest[destOffset + 7] = Math.fma(_self10, _t2, Math.fma(_self11, _t3, _self12));
        dest[destOffset + 8] = Math.fma(_self20, _t2, Math.fma(_self21, _t3, _self22));
        return dest;
    }

    /** {@link #scaleAround(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer scaleAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer s, int sOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && s.isDirect() && s.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return Double3x3OpsKernelsTypedBuffer.scaleAround_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@link #scaleAround(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer scaleAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer s, int sOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && s.isDirect() && s.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.scaleAround_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return Double3x3OpsKernelsByteBuffer.scaleAround_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && s.isNative() && pivot.isNative()) return Double3x3OpsKernelsSegment.scaleAround_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return Double3x3OpsKernelsSegment.scaleAround_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    /** {@link #scaleAround(double[], int, double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long scaleAround(long dest, long src, long s, long pivot) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.scaleAround_unsafe(dest, src, s, pivot);
        scaleAround(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(s, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(pivot, 16L), 0L);
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
        if (SimdSupport.DOUBLE_COLUMNS) return Double3x3OpsSimd.translate(dest, destOffset, src, srcOffset, vX, vY);
        return Double3x3OpsKernelsArray.translate_scalar(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #translate(double[], int, double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer translate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.translate_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double3x3OpsKernelsTypedBuffer.translate_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #translate(double[], int, double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer translate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.translate_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double3x3OpsKernelsByteBuffer.translate_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #translate(double[], int, double[], int, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment translate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            translate(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), vX, vY);
            return dest;
        }
        if (SimdSupport.DOUBLE_COLUMNS) return Double3x3OpsSimd.translate(dest, destOffset, src, srcOffset, vX, vY);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.translate_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double3x3OpsKernelsSegment.translate_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #translate(double[], int, double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long translate(long dest, long src, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.translate_unsafe(dest, src, vX, vY);
        translate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, vX, vY);
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
        if (SimdSupport.DOUBLE_COLUMNS) return Double3x3OpsSimd.translate(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsArray.translate_scalar(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #translate(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer translate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.translate_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsTypedBuffer.translate_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #translate(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer translate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.translate_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsByteBuffer.translate_api(dest, destOffset, src, srcOffset, v, vOffset);
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
        if (SimdSupport.DOUBLE_COLUMNS) return Double3x3OpsSimd.translate(dest, destOffset, src, srcOffset, v, vOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && v.isNative()) return Double3x3OpsKernelsSegment.translate_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsSegment.translate_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #translate(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long translate(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.translate_unsafe(dest, src, v);
        translate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 16L), 0L);
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
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        dest[destOffset] = _sp0 * _self00;
        dest[destOffset + 1] = _sp0 * _self10;
        dest[destOffset + 2] = _sp0 * _self20;
        dest[destOffset + 3] = _sp1 * _self01;
        dest[destOffset + 4] = _sp1 * _self11;
        dest[destOffset + 5] = _sp1 * _self21;
        dest[destOffset + 6] = _self02 + (-(_self00 * _sp2) - _self01 * _sp3);
        dest[destOffset + 7] = _self12 + (-(_self10 * _sp2) - _self11 * _sp3);
        dest[destOffset + 8] = _self22 + (-(_self20 * _sp2) - _self21 * _sp3);
        return dest;
    }

    /** {@link #view(double[], int, double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer view(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double left, double right, double bottom, double top) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.view_unsafe(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return Double3x3OpsKernelsTypedBuffer.view_api(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    /** {@link #view(double[], int, double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer view(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double left, double right, double bottom, double top) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.view_unsafe(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return Double3x3OpsKernelsByteBuffer.view_api(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    /** {@link #view(double[], int, double[], int, double, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment view(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double left, double right, double bottom, double top) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            view(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), left, right, bottom, top);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.view_unsafe(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return Double3x3OpsKernelsSegment.view_api(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    /** {@link #view(double[], int, double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long view(long dest, long src, double left, double right, double bottom, double top) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.view_unsafe(dest, src, left, right, bottom, top);
        view(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, left, right, bottom, top);
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
        double _self20 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        dest[destOffset] = Math.fma(src[srcOffset + 6], vZ, Math.fma(src[srcOffset], vX, src[srcOffset + 3] * vY));
        dest[destOffset + 1] = Math.fma(_self12, vZ, Math.fma(_self10, vX, _self11 * vY));
        dest[destOffset + 2] = Math.fma(_self22, vZ, Math.fma(_self20, vX, _self21 * vY));
        return dest;
    }

    /** {@link #mulVec3(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mulVec3(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY, double vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.mulVec3_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return Double3x3OpsKernelsTypedBuffer.mulVec3_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    /** {@link #mulVec3(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mulVec3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY, double vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.mulVec3_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return Double3x3OpsKernelsByteBuffer.mulVec3_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    /** {@link #mulVec3(double[], int, double[], int, double, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment mulVec3(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            mulVec3(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), vX, vY, vZ);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.mulVec3_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return Double3x3OpsKernelsSegment.mulVec3_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    /** {@link #mulVec3(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mulVec3(long dest, long src, double vX, double vY, double vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.mulVec3_unsafe(dest, src, vX, vY, vZ);
        mulVec3(VirtualMemoryHolder.virtualMemory().asSlice(dest, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, vX, vY, vZ);
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
        double _self20 = src[srcOffset + 2];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        dest[destOffset] = Math.fma(src[srcOffset + 6], _vz, Math.fma(src[srcOffset], _vx, src[srcOffset + 3] * _vy));
        dest[destOffset + 1] = Math.fma(_self12, _vz, Math.fma(_self10, _vx, _self11 * _vy));
        dest[destOffset + 2] = Math.fma(_self22, _vz, Math.fma(_self20, _vx, _self21 * _vy));
        return dest;
    }

    /** {@link #mulVec3(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mulVec3(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.mulVec3_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsTypedBuffer.mulVec3_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #mulVec3(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mulVec3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.mulVec3_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsByteBuffer.mulVec3_api(dest, destOffset, src, srcOffset, v, vOffset);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && v.isNative()) return Double3x3OpsKernelsSegment.mulVec3_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsSegment.mulVec3_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #mulVec3(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mulVec3(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.mulVec3_unsafe(dest, src, v);
        mulVec3(VirtualMemoryHolder.virtualMemory().asSlice(dest, 24L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 24L), 0L);
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
        double _self11 = src[srcOffset + 4];
        dest[destOffset] = Math.fma(src[srcOffset], vX, src[srcOffset + 3] * vY);
        dest[destOffset + 1] = Math.fma(_self10, vX, _self11 * vY);
        return dest;
    }

    /** {@link #transformDirection(double[], int, double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer transformDirection(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.transformDirection_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double3x3OpsKernelsTypedBuffer.transformDirection_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #transformDirection(double[], int, double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer transformDirection(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.transformDirection_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double3x3OpsKernelsByteBuffer.transformDirection_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #transformDirection(double[], int, double[], int, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment transformDirection(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            transformDirection(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), vX, vY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.transformDirection_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double3x3OpsKernelsSegment.transformDirection_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #transformDirection(double[], int, double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long transformDirection(long dest, long src, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.transformDirection_unsafe(dest, src, vX, vY);
        transformDirection(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, vX, vY);
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
        double _self11 = src[srcOffset + 4];
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        dest[destOffset] = Math.fma(src[srcOffset], _vx, src[srcOffset + 3] * _vy);
        dest[destOffset + 1] = Math.fma(_self10, _vx, _self11 * _vy);
        return dest;
    }

    /** {@link #transformDirection(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer transformDirection(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.transformDirection_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsTypedBuffer.transformDirection_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #transformDirection(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer transformDirection(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.transformDirection_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsByteBuffer.transformDirection_api(dest, destOffset, src, srcOffset, v, vOffset);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && v.isNative()) return Double3x3OpsKernelsSegment.transformDirection_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsSegment.transformDirection_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #transformDirection(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long transformDirection(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.transformDirection_unsafe(dest, src, v);
        transformDirection(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 16L), 0L);
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
        double _self11 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 7];
        dest[destOffset] = Math.fma(src[srcOffset], vX, Math.fma(src[srcOffset + 3], vY, src[srcOffset + 6]));
        dest[destOffset + 1] = Math.fma(_self10, vX, Math.fma(_self11, vY, _self12));
        return dest;
    }

    /** {@link #transformPosition(double[], int, double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer transformPosition(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.transformPosition_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double3x3OpsKernelsTypedBuffer.transformPosition_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #transformPosition(double[], int, double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer transformPosition(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.transformPosition_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double3x3OpsKernelsByteBuffer.transformPosition_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #transformPosition(double[], int, double[], int, double, double)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment transformPosition(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        double[] _destArray, _srcArray;
        if ((_destArray = heapDoubles(dest, destOffset)) != null
                && (_srcArray = heapDoubles(src, srcOffset)) != null) {
            transformPosition(_destArray, heapIndex(dest, destOffset, 3), _srcArray, heapIndex(src, srcOffset, 3), vX, vY);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Double3x3OpsKernelsSegment.transformPosition_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return Double3x3OpsKernelsSegment.transformPosition_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    /** {@link #transformPosition(double[], int, double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long transformPosition(long dest, long src, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.transformPosition_unsafe(dest, src, vX, vY);
        transformPosition(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, vX, vY);
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
        double _self11 = src[srcOffset + 4];
        double _self12 = src[srcOffset + 7];
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        dest[destOffset] = Math.fma(src[srcOffset], _vx, Math.fma(src[srcOffset + 3], _vy, src[srcOffset + 6]));
        dest[destOffset + 1] = Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12));
        return dest;
    }

    /** {@link #transformPosition(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer transformPosition(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsTypedBuffer.transformPosition_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsTypedBuffer.transformPosition_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #transformPosition(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer transformPosition(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsKernelsByteBuffer.transformPosition_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsByteBuffer.transformPosition_api(dest, destOffset, src, srcOffset, v, vOffset);
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
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && v.isNative()) return Double3x3OpsKernelsSegment.transformPosition_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return Double3x3OpsKernelsSegment.transformPosition_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #transformPosition(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long transformPosition(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3x3OpsKernelsAddress.transformPosition_unsafe(dest, src, v);
        transformPosition(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 16L), 0L);
        return dest;
    }


    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count > 238609294) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 238609294 ? -1 : count * 9), src.length);
            java.util.Objects.checkFromIndexSize(destOffset, (count > 238609294 ? -1 : count * 9), dest.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.DOUBLE_ARRAY_BASE + srcOffset * 8L, dest, UnsafeCopy.DOUBLE_ARRAY_BASE + destOffset * 8L, count * 72L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 9;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src[srcOffset + _i];
        return dest;
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count > 238609294) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 238609294 ? -1 : count * 9), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, dest, UnsafeCopy.DOUBLE_ARRAY_BASE + destOffset * 8L, count * 72L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 9;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src.get(srcOffset + _i);
        return dest;
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count > 238609294) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 238609294 ? -1 : count * 9), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, dest, UnsafeCopy.DOUBLE_ARRAY_BASE + destOffset * 8L, count * 72L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 9;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src.getDouble(srcOffset + _i * 8);
        return dest;
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count > 238609294) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 238609294 ? -1 : count * 9), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, src.address() + srcOffset, dest, UnsafeCopy.DOUBLE_ARRAY_BASE + destOffset * 8L, count * 72L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 9;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L);
        return dest;
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, long src) {
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 238609294 ? -1 : count * 9), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest, UnsafeCopy.DOUBLE_ARRAY_BASE + destOffset * 8L, count * 72L);
            return dest;
        }
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, count * 72L), 0L, count);
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count > 238609294) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 238609294 ? -1 : count * 9), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.DOUBLE_ARRAY_BASE + srcOffset * 8L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, count * 72L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 9;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src[srcOffset + _i]);
        return dest;
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count > 238609294) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, count * 72L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 9;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src.get(srcOffset + _i));
        return dest;
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count > 238609294) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, count * 72L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 9;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src.getDouble(srcOffset + _i * 8));
        return dest;
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count > 238609294) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isNative()) {
            UnsafeOpsHolder.U.copyMemory(null, src.address() + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, count * 72L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 9;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L));
        return dest;
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, long src) {
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, count * 72L);
            return dest;
        }
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, count * 72L), 0L, count);
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, double[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count > 238609294) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 238609294 ? -1 : count * 9), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.DOUBLE_ARRAY_BASE + srcOffset * 8L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 72L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 9;
        for (int _i = 0; _i < n; _i++)
            dest.putDouble(destOffset + _i * 8, src[srcOffset + _i]);
        return dest;
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count > 238609294) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 72L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 9;
        for (int _i = 0; _i < n; _i++)
            dest.putDouble(destOffset + _i * 8, src.get(srcOffset + _i));
        return dest;
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count > 238609294) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 72L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 9;
        for (int _i = 0; _i < n; _i++)
            dest.putDouble(destOffset + _i * 8, src.getDouble(srcOffset + _i * 8));
        return dest;
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count > 238609294) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isNative()) {
            UnsafeOpsHolder.U.copyMemory(null, src.address() + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 72L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 9;
        for (int _i = 0; _i < n; _i++)
            dest.putDouble(destOffset + _i * 8, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L));
        return dest;
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, long src) {
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 72L);
            return dest;
        }
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, count * 72L), 0L, count);
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, double[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, double[] src, int srcOffset, int count) {
        if (count > 238609294) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 238609294 ? -1 : count * 9), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.DOUBLE_ARRAY_BASE + srcOffset * 8L, null, dest.address() + destOffset, count * 72L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 9;
        for (int _i = 0; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, src[srcOffset + _i]);
        return dest;
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count > 238609294) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, null, dest.address() + destOffset, count * 72L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 9;
        for (int _i = 0; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, src.get(srcOffset + _i));
        return dest;
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count > 238609294) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, dest.address() + destOffset, count * 72L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 9;
        for (int _i = 0; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, src.getDouble(srcOffset + _i * 8));
        return dest;
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count > 238609294) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API) return Double3x3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) {
            UnsafeOpsHolder.U.copyMemory(null, src.address() + srcOffset, null, dest.address() + destOffset, count * 72L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 9;
        for (int _i = 0; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L));
        return dest;
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, long src) {
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, dest.address() + destOffset, count * 72L);
            return dest;
        }
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, count * 72L), 0L, count);
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, double[] src, int srcOffset) {
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, src, srcOffset);
        return dest;
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, double[] src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 238609294 ? -1 : count * 9), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.DOUBLE_ARRAY_BASE + srcOffset * 8L, null, dest, count * 72L);
            return dest;
        }
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, count * 72L), 0L, src, srcOffset, count);
        return dest;
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.nio.DoubleBuffer src, int srcOffset) {
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, src, srcOffset);
        return dest;
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, null, dest, count * 72L);
            return dest;
        }
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, count * 72L), 0L, src, srcOffset, count);
        return dest;
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.nio.ByteBuffer src, int srcOffset) {
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, src, srcOffset);
        return dest;
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, dest, count * 72L);
            return dest;
        }
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, count * 72L), 0L, src, srcOffset, count);
        return dest;
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.lang.foreign.MemorySegment src, long srcOffset) {
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, src, srcOffset);
        return dest;
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) {
            UnsafeOpsHolder.U.copyMemory(null, src.address() + srcOffset, null, dest, count * 72L);
            return dest;
        }
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, count * 72L), 0L, src, srcOffset, count);
        return dest;
    }

    /**
     * Copy one Double3x3 (9 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, long src) {
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 72L), 0L);
        return dest;
    }

    /**
     * Bulk-copy {@code count} consecutive Double3x3 values ({@code count * 9} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, dest, count * 72L);
            return dest;
        }
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, count * 72L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, count * 72L), 0L, count);
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
