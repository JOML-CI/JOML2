// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.ops;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.kernels.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Static, allocation-free operations on raw storage holding a {@link Float4}.
 *
 * <p>Each method takes one or more buffers ({@code float[]},
 * {@link java.nio.FloatBuffer}, {@link java.nio.ByteBuffer}, or
 * {@link java.lang.foreign.MemorySegment}) plus an element/byte offset and operates
 * directly on that storage. No {@link Float4} instance is allocated;
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
 * Elements are laid out in component order: what {@code store}/{@code load} of
 * Float4 write and read.</p>
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
 * <p>Each method summary below is the one the {@link Float4} API carries, so
 * the two can never describe the same operation differently: "this vector" there is the
 * vector held in {@code src} at {@code srcOffset}, and the result is written to
 * {@code dest} at {@code destOffset}. The full text sits on the {@code float[]} overload of
 * each method; the other storage overloads point at it, differing from it in
 * storage alone.</p>
 */
public final class Float4Ops {
    private Float4Ops() {}

    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this vector and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return {@code dest}
     */
    public static float[] add(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = otherX + src[srcOffset];
        dest[destOffset + 1] = otherY + _selfy;
        dest[destOffset + 2] = otherZ + _selfz;
        dest[destOffset + 3] = otherW + _selfw;
        return dest;
    }

    /** {@link #add(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer add(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.add_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsTypedBuffer.add_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #add(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer add(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.add_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsByteBuffer.add_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #add(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment add(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            add(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), otherX, otherY, otherZ, otherW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.add_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsSegment.add_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #add(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long add(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.add_unsafe(dest, src, otherX, otherY, otherZ, otherW);
        add(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW);
        return dest;
    }

    /**
     * Add {@code other} to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param other the storage holding the vector to add
     * @param otherOffset the element index in {@code other} at which the vector starts
     * @return {@code dest}
     */
    public static float[] add(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.add(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsArray.add_scalar(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer add(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.add_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsTypedBuffer.add_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer add(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.add_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsByteBuffer.add_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment add(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.add(dest, destOffset, src, srcOffset, other, otherOffset);
        float[] _destArray, _srcArray, _otherArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_otherArray = heapFloats(other, otherOffset)) != null) {
            add(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _otherArray, heapIndex(other, otherOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Float4OpsKernelsSegment.add_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsSegment.add_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long add(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.add_unsafe(dest, src, other);
        add(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
        return dest;
    }

    /**
     * Divide each component of this vector by {@code scalar} and store the result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param scalar the divisor
     * @return {@code dest}
     */
    public static float[] div(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.div(dest, destOffset, src, srcOffset, scalar);
        return Float4OpsKernelsArray.div_scalar(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #div(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer div(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.div_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float4OpsKernelsTypedBuffer.div_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #div(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer div(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.div_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float4OpsKernelsByteBuffer.div_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #div(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment div(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.div(dest, destOffset, src, srcOffset, scalar);
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            div(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), scalar);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.div_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float4OpsKernelsSegment.div_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #div(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long div(long dest, long src, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.div_unsafe(dest, src, scalar);
        div(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, scalar);
        return dest;
    }

    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ, otherW)} must be non-zero.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return {@code dest}
     */
    public static float[] div(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = src[srcOffset] / otherX;
        dest[destOffset + 1] = _selfy / otherY;
        dest[destOffset + 2] = _selfz / otherZ;
        dest[destOffset + 3] = _selfw / otherW;
        return dest;
    }

    /** {@link #div(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer div(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.div_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsTypedBuffer.div_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #div(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer div(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.div_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsByteBuffer.div_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #div(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment div(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            div(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), otherX, otherY, otherZ, otherW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.div_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsSegment.div_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #div(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long div(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.div_unsafe(dest, src, otherX, otherY, otherZ, otherW);
        div(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW);
        return dest;
    }

    /**
     * Divide this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param other the storage holding the vector of per-component divisors
     * @param otherOffset the element index in {@code other} at which the vector starts
     * @return {@code dest}
     */
    public static float[] div(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.div(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsArray.div_scalar(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #div(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer div(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.div_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsTypedBuffer.div_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #div(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer div(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.div_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsByteBuffer.div_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #div(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment div(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.div(dest, destOffset, src, srcOffset, other, otherOffset);
        float[] _destArray, _srcArray, _otherArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_otherArray = heapFloats(other, otherOffset)) != null) {
            div(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _otherArray, heapIndex(other, otherOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Float4OpsKernelsSegment.div_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsSegment.div_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #div(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long div(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.div_unsafe(dest, src, other);
        div(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
        return dest;
    }

    /**
     * Multiply this vector component-wise by {@code b} and add ({@code cX}, {@code cY}, {@code cZ},
     * {@code cW}), i.e. compute {@code this * b + (cX, cY, cZ, cW)} per component and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param b the factor to multiply this vector by
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cW the {@code w} component of the vector {@code (cX, cY, cZ, cW)}
     * @return {@code dest}
     */
    public static float[] fma(float[] dest, int destOffset, float[] src, int srcOffset, float b, float cX, float cY, float cZ, float cW) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.fma(src[srcOffset], b, cX);
        dest[destOffset + 1] = Math.fma(_selfy, b, cY);
        dest[destOffset + 2] = Math.fma(_selfz, b, cZ);
        dest[destOffset + 3] = Math.fma(_selfw, b, cW);
        return dest;
    }

    /** {@link #fma(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float b, float cX, float cY, float cZ, float cW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.fma_unsafe(dest, destOffset, src, srcOffset, b, cX, cY, cZ, cW);
        return Float4OpsKernelsTypedBuffer.fma_api(dest, destOffset, src, srcOffset, b, cX, cY, cZ, cW);
    }

    /** {@link #fma(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float b, float cX, float cY, float cZ, float cW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.fma_unsafe(dest, destOffset, src, srcOffset, b, cX, cY, cZ, cW);
        return Float4OpsKernelsByteBuffer.fma_api(dest, destOffset, src, srcOffset, b, cX, cY, cZ, cW);
    }

    /** {@link #fma(float[], int, float[], int, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float b, float cX, float cY, float cZ, float cW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            fma(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), b, cX, cY, cZ, cW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.fma_unsafe(dest, destOffset, src, srcOffset, b, cX, cY, cZ, cW);
        return Float4OpsKernelsSegment.fma_api(dest, destOffset, src, srcOffset, b, cX, cY, cZ, cW);
    }

    /** {@link #fma(float[], int, float[], int, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long fma(long dest, long src, float b, float cX, float cY, float cZ, float cW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.fma_unsafe(dest, src, b, cX, cY, cZ, cW);
        fma(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, b, cX, cY, cZ, cW);
        return dest;
    }

    /**
     * Multiply this vector component-wise by {@code b} and add {@code c}, i.e. compute
     * {@code this * b + c} per component and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param c the storage holding the vector to add
     * @param cOffset the element index in {@code c} at which the vector starts
     * @param b the factor to multiply this vector by
     * @return {@code dest}
     */
    public static float[] fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] c, int cOffset, float b) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.fma_fma(dest, destOffset, src, srcOffset, c, cOffset, b);
            return Float4OpsSimd.fma_mulAdd(dest, destOffset, src, srcOffset, c, cOffset, b);
        }
        return Float4OpsKernelsArray.fma_scalar(dest, destOffset, src, srcOffset, c, cOffset, b);
    }

    /** {@link #fma(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer c, int cOffset, float b) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && c.isDirect() && c.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.fma_unsafe(dest, destOffset, src, srcOffset, c, cOffset, b);
        return Float4OpsKernelsTypedBuffer.fma_api(dest, destOffset, src, srcOffset, c, cOffset, b);
    }

    /** {@link #fma(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer c, int cOffset, float b) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && c.isDirect() && c.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.fma_unsafe(dest, destOffset, src, srcOffset, c, cOffset, b);
        return Float4OpsKernelsByteBuffer.fma_api(dest, destOffset, src, srcOffset, c, cOffset, b);
    }

    /** {@link #fma(float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment c, long cOffset, float b) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.fma(dest, destOffset, src, srcOffset, c, cOffset, b);
        float[] _destArray, _srcArray, _cArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_cArray = heapFloats(c, cOffset)) != null) {
            fma(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _cArray, heapIndex(c, cOffset, 2), b);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && c.isNative()) return Float4OpsKernelsSegment.fma_unsafe(dest, destOffset, src, srcOffset, c, cOffset, b);
        return Float4OpsKernelsSegment.fma_api(dest, destOffset, src, srcOffset, c, cOffset, b);
    }

    /** {@link #fma(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long fma(long dest, long src, long c, float b) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.fma_unsafe(dest, src, c, b);
        fma(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(c, 16L), 0L, b);
        return dest;
    }

    /**
     * Multiply this vector component-wise by ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) and
     * add ({@code cX}, {@code cY}, {@code cZ}, {@code cW}), i.e. compute
     * {@code this * (bX, bY, bZ, bW) + (cX, cY, cZ, cW)} per component and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bW the {@code w} component of the vector {@code (bX, bY, bZ, bW)}
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cW the {@code w} component of the vector {@code (cX, cY, cZ, cW)}
     * @return {@code dest}
     */
    public static float[] fma(float[] dest, int destOffset, float[] src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.fma(src[srcOffset], bX, cX);
        dest[destOffset + 1] = Math.fma(_selfy, bY, cY);
        dest[destOffset + 2] = Math.fma(_selfz, bZ, cZ);
        dest[destOffset + 3] = Math.fma(_selfw, bW, cW);
        return dest;
    }

    /** {@link #fma(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.fma_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return Float4OpsKernelsTypedBuffer.fma_api(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
    }

    /** {@link #fma(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.fma_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return Float4OpsKernelsByteBuffer.fma_api(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
    }

    /** {@link #fma(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            fma(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), bX, bY, bZ, bW, cX, cY, cZ, cW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.fma_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return Float4OpsKernelsSegment.fma_api(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
    }

    /** {@link #fma(float[], int, float[], int, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long fma(long dest, long src, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.fma_unsafe(dest, src, bX, bY, bZ, bW, cX, cY, cZ, cW);
        fma(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    /**
     * Multiply this vector component-wise by {@code b} and add {@code c}, i.e. compute
     * {@code this * b + c} per component and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param b the storage holding the factor to multiply this vector by
     * @param bOffset the element index in {@code b} at which the vector starts
     * @param c the storage holding the vector to add
     * @param cOffset the element index in {@code c} at which the vector starts
     * @return {@code dest}
     */
    public static float[] fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float[] c, int cOffset) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.fma_fma(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
            return Float4OpsSimd.fma_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        }
        return Float4OpsKernelsArray.fma_scalar(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    /** {@link #fma(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && b.isDirect() && b.order() == java.nio.ByteOrder.nativeOrder() && c.isDirect() && c.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.fma_unsafe(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return Float4OpsKernelsTypedBuffer.fma_api(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    /** {@link #fma(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && b.isDirect() && b.order() == java.nio.ByteOrder.nativeOrder() && c.isDirect() && c.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.fma_unsafe(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return Float4OpsKernelsByteBuffer.fma_api(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    /** {@link #fma(float[], int, float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.fma(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        float[] _destArray, _srcArray, _bArray, _cArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_bArray = heapFloats(b, bOffset)) != null
                && (_cArray = heapFloats(c, cOffset)) != null) {
            fma(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _bArray, heapIndex(b, bOffset, 2), _cArray, heapIndex(c, cOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && b.isNative() && c.isNative()) return Float4OpsKernelsSegment.fma_unsafe(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return Float4OpsKernelsSegment.fma_api(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    /** {@link #fma(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long fma(long dest, long src, long b, long c) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.fma_unsafe(dest, src, b, c);
        fma(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(b, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(c, 16L), 0L);
        return dest;
    }

    /**
     * Multiply each component of this vector by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param scalar the factor to multiply each component by
     * @return {@code dest}
     */
    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.mul(dest, destOffset, src, srcOffset, scalar);
        return Float4OpsKernelsArray.mul_scalar(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.mul_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float4OpsKernelsTypedBuffer.mul_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.mul_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float4OpsKernelsByteBuffer.mul_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.mul(dest, destOffset, src, srcOffset, scalar);
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            mul(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), scalar);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.mul_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float4OpsKernelsSegment.mul_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mul(long dest, long src, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.mul_unsafe(dest, src, scalar);
        mul(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, scalar);
        return dest;
    }

    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return {@code dest}
     */
    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = otherX * src[srcOffset];
        dest[destOffset + 1] = otherY * _selfy;
        dest[destOffset + 2] = otherZ * _selfz;
        dest[destOffset + 3] = otherW * _selfw;
        return dest;
    }

    /** {@link #mul(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.mul_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsTypedBuffer.mul_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #mul(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.mul_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsByteBuffer.mul_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #mul(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            mul(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), otherX, otherY, otherZ, otherW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.mul_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsSegment.mul_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #mul(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mul(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.mul_unsafe(dest, src, otherX, otherY, otherZ, otherW);
        mul(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW);
        return dest;
    }

    /**
     * Multiply this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param other the storage holding the vector of per-component factors
     * @param otherOffset the element index in {@code other} at which the vector starts
     * @return {@code dest}
     */
    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.mul(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsArray.mul_scalar(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #mul(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.mul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsTypedBuffer.mul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #mul(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.mul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsByteBuffer.mul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #mul(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.mul(dest, destOffset, src, srcOffset, other, otherOffset);
        float[] _destArray, _srcArray, _otherArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_otherArray = heapFloats(other, otherOffset)) != null) {
            mul(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _otherArray, heapIndex(other, otherOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Float4OpsKernelsSegment.mul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsSegment.mul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #mul(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mul(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.mul_unsafe(dest, src, other);
        mul(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
        return dest;
    }

    /**
     * Negate this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] negate(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.negate(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsArray.negate_scalar(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer negate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.negate_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.negate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer negate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.negate_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.negate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment negate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.negate(dest, destOffset, src, srcOffset);
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            negate(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.negate_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.negate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long negate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.negate_unsafe(dest, src);
        negate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this vector
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return {@code dest}
     */
    public static float[] sub(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = src[srcOffset] - otherX;
        dest[destOffset + 1] = _selfy - otherY;
        dest[destOffset + 2] = _selfz - otherZ;
        dest[destOffset + 3] = _selfw - otherW;
        return dest;
    }

    /** {@link #sub(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer sub(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.sub_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsTypedBuffer.sub_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #sub(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sub(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.sub_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsByteBuffer.sub_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #sub(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment sub(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            sub(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), otherX, otherY, otherZ, otherW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.sub_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsSegment.sub_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #sub(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long sub(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.sub_unsafe(dest, src, otherX, otherY, otherZ, otherW);
        sub(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW);
        return dest;
    }

    /**
     * Subtract {@code other} from this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param other the storage holding the vector to subtract
     * @param otherOffset the element index in {@code other} at which the vector starts
     * @return {@code dest}
     */
    public static float[] sub(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.sub(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsArray.sub_scalar(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer sub(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.sub_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsTypedBuffer.sub_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sub(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.sub_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsByteBuffer.sub_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment sub(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.sub(dest, destOffset, src, srcOffset, other, otherOffset);
        float[] _destArray, _srcArray, _otherArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_otherArray = heapFloats(other, otherOffset)) != null) {
            sub(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _otherArray, heapIndex(other, otherOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Float4OpsKernelsSegment.sub_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsSegment.sub_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long sub(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.sub_unsafe(dest, src, other);
        sub(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
        return dest;
    }

    /**
     * Set this vector to the unit vector
     * {@code (sqrt(1 - u) cos(2 PI v), sqrt(1 - u) sin(2 PI v), sqrt(u) cos(2 PI w), sqrt(u) sin(2 PI w))}:
     * samples uniformly distributed in {@code [0, 1)} give a direction uniformly distributed on the
     * unit sphere of four dimensions ({@code makeRandomDirection} draws them from a
     * {@link java.util.Random}).
     * <p>
     * Valid input: {@code u} must lie in {@code [0, 1]}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param u the sample that splits the unit length between {@code (x, y)} and {@code (z, w)},
     *        uniformly distributed in {@code [0, 1)} for a uniformly distributed direction
     * @param v the fraction of a full turn of {@code (x, y)}, uniformly distributed in
     *        {@code [0, 1)} for a uniformly distributed direction
     * @param w the fraction of a full turn of {@code (z, w)}, uniformly distributed in
     *        {@code [0, 1)} for a uniformly distributed direction
     * @return {@code dest}
     */
    public static float[] makeUniformDirection(float[] dest, int destOffset, float u, float v, float w) {
        float _t0 = (float) java.lang.Math.sqrt(u);
        float _t1 = v * 6.2831855f;
        float _t3 = w * 6.2831855f;
        float _t4 = Math.sin(_t1);
        float _t5 = (float) java.lang.Math.sqrt(1.0f - u);
        float _t6 = Math.sin(_t3);
        dest[destOffset] = Math.cosFromSin(_t4, _t1) * _t5;
        dest[destOffset + 1] = _t4 * _t5;
        dest[destOffset + 2] = Math.cosFromSin(_t6, _t3) * _t0;
        dest[destOffset + 3] = _t6 * _t0;
        return dest;
    }

    /** {@link #makeUniformDirection(float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeUniformDirection(java.nio.FloatBuffer dest, int destOffset, float u, float v, float w) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.makeUniformDirection_unsafe(dest, destOffset, u, v, w);
        return Float4OpsKernelsTypedBuffer.makeUniformDirection_api(dest, destOffset, u, v, w);
    }

    /** {@link #makeUniformDirection(float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeUniformDirection(java.nio.ByteBuffer dest, int destOffset, float u, float v, float w) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.makeUniformDirection_unsafe(dest, destOffset, u, v, w);
        return Float4OpsKernelsByteBuffer.makeUniformDirection_api(dest, destOffset, u, v, w);
    }

    /** {@link #makeUniformDirection(float[], int, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeUniformDirection(java.lang.foreign.MemorySegment dest, long destOffset, float u, float v, float w) {
        float[] _destArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null) {
            makeUniformDirection(_destArray, heapIndex(dest, destOffset, 2), u, v, w);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Float4OpsKernelsSegment.makeUniformDirection_unsafe(dest, destOffset, u, v, w);
        return Float4OpsKernelsSegment.makeUniformDirection_api(dest, destOffset, u, v, w);
    }

    /** {@link #makeUniformDirection(float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeUniformDirection(long dest, float u, float v, float w) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.makeUniformDirection_unsafe(dest, u, v, w);
        makeUniformDirection(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, u, v, w);
        return dest;
    }

    /**
     * Set this vector to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ, vW)}
     * @param vW the {@code w} component of the vector {@code (vX, vY, vZ, vW)}
     * @return {@code dest}
     */
    public static float[] set(float[] dest, int destOffset, float vX, float vY, float vZ, float vW) {
        dest[destOffset] = vX;
        dest[destOffset + 1] = vY;
        dest[destOffset + 2] = vZ;
        dest[destOffset + 3] = vW;
        return dest;
    }

    /** {@link #set(float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer set(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ, float vW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.set_unsafe(dest, destOffset, vX, vY, vZ, vW);
        return Float4OpsKernelsTypedBuffer.set_api(dest, destOffset, vX, vY, vZ, vW);
    }

    /** {@link #set(float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer set(java.nio.ByteBuffer dest, int destOffset, float vX, float vY, float vZ, float vW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.set_unsafe(dest, destOffset, vX, vY, vZ, vW);
        return Float4OpsKernelsByteBuffer.set_api(dest, destOffset, vX, vY, vZ, vW);
    }

    /** {@link #set(float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment set(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY, float vZ, float vW) {
        float[] _destArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null) {
            set(_destArray, heapIndex(dest, destOffset, 2), vX, vY, vZ, vW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Float4OpsKernelsSegment.set_unsafe(dest, destOffset, vX, vY, vZ, vW);
        return Float4OpsKernelsSegment.set_api(dest, destOffset, vX, vY, vZ, vW);
    }

    /** {@link #set(float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long set(long dest, float vX, float vY, float vZ, float vW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.set_unsafe(dest, vX, vY, vZ, vW);
        set(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, vX, vY, vZ, vW);
        return dest;
    }

    /**
     * Set this vector to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param v the storage holding the vector to copy
     * @param vOffset the element index in {@code v} at which the vector starts
     * @return {@code dest}
     */
    public static float[] set(float[] dest, int destOffset, float[] v, int vOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.set(dest, destOffset, v, vOffset);
        return Float4OpsKernelsArray.set_scalar(dest, destOffset, v, vOffset);
    }

    /** {@link #set(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer set(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.set_unsafe(dest, destOffset, v, vOffset);
        return Float4OpsKernelsTypedBuffer.set_api(dest, destOffset, v, vOffset);
    }

    /** {@link #set(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer set(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.set_unsafe(dest, destOffset, v, vOffset);
        return Float4OpsKernelsByteBuffer.set_api(dest, destOffset, v, vOffset);
    }

    /** {@link #set(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment set(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.set(dest, destOffset, v, vOffset);
        float[] _destArray, _vArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_vArray = heapFloats(v, vOffset)) != null) {
            set(_destArray, heapIndex(dest, destOffset, 2), _vArray, heapIndex(v, vOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && v.isNative()) return Float4OpsKernelsSegment.set_unsafe(dest, destOffset, v, vOffset);
        return Float4OpsKernelsSegment.set_api(dest, destOffset, v, vOffset);
    }

    /** {@link #set(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long set(long dest, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.set_unsafe(dest, v);
        set(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 16L), 0L);
        return dest;
    }

    /**
     * Set this vector to {@code s} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param s the value assigned to every component
     * @return {@code dest}
     */
    public static float[] set(float[] dest, int destOffset, float s) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.set(dest, destOffset, s);
        return Float4OpsKernelsArray.set_scalar(dest, destOffset, s);
    }

    /** {@link #set(float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer set(java.nio.FloatBuffer dest, int destOffset, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.set_unsafe(dest, destOffset, s);
        return Float4OpsKernelsTypedBuffer.set_api(dest, destOffset, s);
    }

    /** {@link #set(float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer set(java.nio.ByteBuffer dest, int destOffset, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.set_unsafe(dest, destOffset, s);
        return Float4OpsKernelsByteBuffer.set_api(dest, destOffset, s);
    }

    /** {@link #set(float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment set(java.lang.foreign.MemorySegment dest, long destOffset, float s) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.set(dest, destOffset, s);
        float[] _destArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null) {
            set(_destArray, heapIndex(dest, destOffset, 2), s);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Float4OpsKernelsSegment.set_unsafe(dest, destOffset, s);
        return Float4OpsKernelsSegment.set_api(dest, destOffset, s);
    }

    /** {@link #set(float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long set(long dest, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.set_unsafe(dest, s);
        set(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, s);
        return dest;
    }

    /**
     * Set all components of this vector to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @return {@code dest}
     */
    public static float[] makeZero(float[] dest, int destOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.makeZero(dest, destOffset);
        return Float4OpsKernelsArray.makeZero_scalar(dest, destOffset);
    }

    /** {@link #makeZero(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeZero(java.nio.FloatBuffer dest, int destOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.makeZero_unsafe(dest, destOffset);
        return Float4OpsKernelsTypedBuffer.makeZero_api(dest, destOffset);
    }

    /** {@link #makeZero(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeZero(java.nio.ByteBuffer dest, int destOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.makeZero_unsafe(dest, destOffset);
        return Float4OpsKernelsByteBuffer.makeZero_api(dest, destOffset);
    }

    /** {@link #makeZero(float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeZero(java.lang.foreign.MemorySegment dest, long destOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.makeZero(dest, destOffset);
        float[] _destArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null) {
            makeZero(_destArray, heapIndex(dest, destOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return Float4OpsKernelsSegment.makeZero_unsafe(dest, destOffset);
        return Float4OpsKernelsSegment.makeZero_api(dest, destOffset);
    }

    /** {@link #makeZero(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeZero(long dest) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.makeZero_unsafe(dest);
        makeZero(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L);
        return dest;
    }

    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}) and ends at ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W})
     * and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p3X}, {@code p3Y},
     * {@code p3Z}, {@code p3W}) at {@code t = 1}; the control points ({@code p1X}, {@code p1Y},
     * {@code p1Z}, {@code p1W}) and ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}) pull it
     * towards themselves but are generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Z the {@code z} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3W the {@code w} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] bezier(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest[destOffset] = Math.fma(p1X, _t7, src[srcOffset] * _t8) + Math.fma(p2X, _t6, p3X * _t2);
        dest[destOffset + 1] = Math.fma(p1Y, _t7, _selfy * _t8) + Math.fma(p2Y, _t6, p3Y * _t2);
        dest[destOffset + 2] = Math.fma(p1Z, _t7, _selfz * _t8) + Math.fma(p2Z, _t6, p3Z * _t2);
        dest[destOffset + 3] = Math.fma(p1W, _t7, _selfw * _t8) + Math.fma(p2W, _t6, p3W * _t2);
        return dest;
    }

    /** {@link #bezier(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer bezier(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.bezier_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return Float4OpsKernelsTypedBuffer.bezier_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@link #bezier(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezier(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.bezier_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return Float4OpsKernelsByteBuffer.bezier_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@link #bezier(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment bezier(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            bezier(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.bezier_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return Float4OpsKernelsSegment.bezier_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@link #bezier(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long bezier(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.bezier_unsafe(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        bezier(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points {@code p1} and {@code p2} and ends at {@code p3} and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code p3} at
     * {@code t = 1}; the control points {@code p1} and {@code p2} pull it towards themselves but
     * are generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param p1 the storage holding the first control point
     * @param p1Offset the element index in {@code p1} at which the vector starts
     * @param p2 the storage holding the second control point
     * @param p2Offset the element index in {@code p2} at which the vector starts
     * @param p3 the storage holding the end point of the curve
     * @param p3Offset the element index in {@code p3} at which the vector starts
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] bezier(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.bezier_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
            return Float4OpsSimd.bezier_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        }
        return Float4OpsKernelsArray.bezier_scalar(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #bezier(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer bezier(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.bezier_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Float4OpsKernelsTypedBuffer.bezier_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #bezier(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezier(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.bezier_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Float4OpsKernelsByteBuffer.bezier_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #bezier(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment bezier(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.bezier(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        float[] _destArray, _srcArray, _p1Array, _p2Array, _p3Array;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_p1Array = heapFloats(p1, p1Offset)) != null
                && (_p2Array = heapFloats(p2, p2Offset)) != null
                && (_p3Array = heapFloats(p3, p3Offset)) != null) {
            bezier(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _p1Array, heapIndex(p1, p1Offset, 2), _p2Array, heapIndex(p2, p2Offset, 2), _p3Array, heapIndex(p3, p3Offset, 2), t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && p1.isNative() && p2.isNative() && p3.isNative()) return Float4OpsKernelsSegment.bezier_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Float4OpsKernelsSegment.bezier_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #bezier(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long bezier(long dest, long src, long p1, long p2, long p3, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.bezier_unsafe(dest, src, p1, p2, p3, t);
        bezier(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(p1, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(p2, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(p3, 16L), 0L, t);
        return dest;
    }

    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ends at ({@code p2X},
     * {@code p2Y}, {@code p2Z}, {@code p2W}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}) at {@code t = 1}; the control point ({@code p1X}, {@code p1Y},
     * {@code p1Z}, {@code p1W}) pulls it towards itself but is generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] bezier2(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest[destOffset] = Math.fma(p2X, _t0, Math.fma(p1X, _t3, src[srcOffset] * _t4));
        dest[destOffset + 1] = Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4));
        dest[destOffset + 2] = Math.fma(p2Z, _t0, Math.fma(p1Z, _t3, _selfz * _t4));
        dest[destOffset + 3] = Math.fma(p2W, _t0, Math.fma(p1W, _t3, _selfw * _t4));
        return dest;
    }

    /** {@link #bezier2(float[], int, float[], int, float, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer bezier2(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.bezier2_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return Float4OpsKernelsTypedBuffer.bezier2_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
    }

    /** {@link #bezier2(float[], int, float[], int, float, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezier2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.bezier2_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return Float4OpsKernelsByteBuffer.bezier2_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
    }

    /** {@link #bezier2(float[], int, float[], int, float, float, float, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment bezier2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            bezier2(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.bezier2_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return Float4OpsKernelsSegment.bezier2_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
    }

    /** {@link #bezier2(float[], int, float[], int, float, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long bezier2(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.bezier2_unsafe(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        bezier2(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point {@code p1} and ends at {@code p2} and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code p2} at
     * {@code t = 1}; the control point {@code p1} pulls it towards itself but is generally not on
     * the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param p1 the storage holding the control point
     * @param p1Offset the element index in {@code p1} at which the vector starts
     * @param p2 the storage holding the end point of the curve
     * @param p2Offset the element index in {@code p2} at which the vector starts
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] bezier2(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float t) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.bezier2_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
            return Float4OpsSimd.bezier2_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        }
        return Float4OpsKernelsArray.bezier2_scalar(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    /** {@link #bezier2(float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer bezier2(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.bezier2_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return Float4OpsKernelsTypedBuffer.bezier2_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    /** {@link #bezier2(float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezier2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.bezier2_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return Float4OpsKernelsByteBuffer.bezier2_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    /** {@link #bezier2(float[], int, float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment bezier2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.bezier2(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        float[] _destArray, _srcArray, _p1Array, _p2Array;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_p1Array = heapFloats(p1, p1Offset)) != null
                && (_p2Array = heapFloats(p2, p2Offset)) != null) {
            bezier2(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _p1Array, heapIndex(p1, p1Offset, 2), _p2Array, heapIndex(p2, p2Offset, 2), t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && p1.isNative() && p2.isNative()) return Float4OpsKernelsSegment.bezier2_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return Float4OpsKernelsSegment.bezier2_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    /** {@link #bezier2(float[], int, float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long bezier2(long dest, long src, long p1, long p2, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.bezier2_unsafe(dest, src, p1, p2, t);
        bezier2(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(p1, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(p2, 16L), 0L, t);
        return dest;
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point
     * ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ends at ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}) at {@code t = 1}; the control point ({@code p1X}, {@code p1Y},
     * {@code p1Z}, {@code p1W}) pulls it towards itself but is generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] bezier2Tangent(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.bezier2Tangent_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
            return Float4OpsSimd.bezier2Tangent_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        }
        return Float4OpsKernelsArray.bezier2Tangent_scalar(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
    }

    /** {@link #bezier2Tangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer bezier2Tangent(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.bezier2Tangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return Float4OpsKernelsTypedBuffer.bezier2Tangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
    }

    /** {@link #bezier2Tangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezier2Tangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.bezier2Tangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return Float4OpsKernelsByteBuffer.bezier2Tangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
    }

    /** {@link #bezier2Tangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment bezier2Tangent(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.bezier2Tangent(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            bezier2Tangent(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.bezier2Tangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return Float4OpsKernelsSegment.bezier2Tangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
    }

    /** {@link #bezier2Tangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long bezier2Tangent(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.bezier2Tangent_unsafe(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        bezier2Tangent(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t);
        return dest;
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point {@code p1}
     * and ends at {@code p2} and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code p2} at
     * {@code t = 1}; the control point {@code p1} pulls it towards itself but is generally not on
     * the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param p1 the storage holding the control point
     * @param p1Offset the element index in {@code p1} at which the vector starts
     * @param p2 the storage holding the end point of the curve
     * @param p2Offset the element index in {@code p2} at which the vector starts
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] bezier2Tangent(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float t) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.bezier2Tangent_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
            return Float4OpsSimd.bezier2Tangent_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        }
        return Float4OpsKernelsArray.bezier2Tangent_scalar(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    /** {@link #bezier2Tangent(float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer bezier2Tangent(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.bezier2Tangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return Float4OpsKernelsTypedBuffer.bezier2Tangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    /** {@link #bezier2Tangent(float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezier2Tangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.bezier2Tangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return Float4OpsKernelsByteBuffer.bezier2Tangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    /** {@link #bezier2Tangent(float[], int, float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment bezier2Tangent(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, float t) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.bezier2Tangent(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        float[] _destArray, _srcArray, _p1Array, _p2Array;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_p1Array = heapFloats(p1, p1Offset)) != null
                && (_p2Array = heapFloats(p2, p2Offset)) != null) {
            bezier2Tangent(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _p1Array, heapIndex(p1, p1Offset, 2), _p2Array, heapIndex(p2, p2Offset, 2), t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && p1.isNative() && p2.isNative()) return Float4OpsKernelsSegment.bezier2Tangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return Float4OpsKernelsSegment.bezier2Tangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    /** {@link #bezier2Tangent(float[], int, float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long bezier2Tangent(long dest, long src, long p1, long p2, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.bezier2Tangent_unsafe(dest, src, p1, p2, t);
        bezier2Tangent(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(p1, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(p2, 16L), 0L, t);
        return dest;
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points ({@code p1X},
     * {@code p1Y}, {@code p1Z}, {@code p1W}) and ({@code p2X}, {@code p2Y}, {@code p2Z},
     * {@code p2W}) and ends at ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) and store the
     * result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p3X}, {@code p3Y},
     * {@code p3Z}, {@code p3W}) at {@code t = 1}; the control points ({@code p1X}, {@code p1Y},
     * {@code p1Z}, {@code p1W}) and ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}) pull it
     * towards themselves but are generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Z the {@code z} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3W the {@code w} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] bezierTangent(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.bezierTangent_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
            return Float4OpsSimd.bezierTangent_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        }
        return Float4OpsKernelsArray.bezierTangent_scalar(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@link #bezierTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer bezierTangent(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.bezierTangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return Float4OpsKernelsTypedBuffer.bezierTangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@link #bezierTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezierTangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.bezierTangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return Float4OpsKernelsByteBuffer.bezierTangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@link #bezierTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment bezierTangent(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.bezierTangent(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            bezierTangent(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.bezierTangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return Float4OpsKernelsSegment.bezierTangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@link #bezierTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long bezierTangent(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.bezierTangent_unsafe(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        bezierTangent(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points {@code p1} and
     * {@code p2} and ends at {@code p3} and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code p3} at
     * {@code t = 1}; the control points {@code p1} and {@code p2} pull it towards themselves but
     * are generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param p1 the storage holding the first control point
     * @param p1Offset the element index in {@code p1} at which the vector starts
     * @param p2 the storage holding the second control point
     * @param p2Offset the element index in {@code p2} at which the vector starts
     * @param p3 the storage holding the end point of the curve
     * @param p3Offset the element index in {@code p3} at which the vector starts
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] bezierTangent(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.bezierTangent_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
            return Float4OpsSimd.bezierTangent_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        }
        return Float4OpsKernelsArray.bezierTangent_scalar(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #bezierTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer bezierTangent(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.bezierTangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Float4OpsKernelsTypedBuffer.bezierTangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #bezierTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezierTangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.bezierTangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Float4OpsKernelsByteBuffer.bezierTangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #bezierTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment bezierTangent(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.bezierTangent(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        float[] _destArray, _srcArray, _p1Array, _p2Array, _p3Array;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_p1Array = heapFloats(p1, p1Offset)) != null
                && (_p2Array = heapFloats(p2, p2Offset)) != null
                && (_p3Array = heapFloats(p3, p3Offset)) != null) {
            bezierTangent(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _p1Array, heapIndex(p1, p1Offset, 2), _p2Array, heapIndex(p2, p2Offset, 2), _p3Array, heapIndex(p3, p3Offset, 2), t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && p1.isNative() && p2.isNative() && p3.isNative()) return Float4OpsKernelsSegment.bezierTangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Float4OpsKernelsSegment.bezierTangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #bezierTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long bezierTangent(long dest, long src, long p1, long p2, long p3, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.bezierTangent_unsafe(dest, src, p1, p2, p3, t);
        bezierTangent(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(p1, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(p2, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(p3, 16L), 0L, t);
        return dest;
    }

    /**
     * Interpolate along the Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z},
     * {@code p1W}) to ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}), with this vector as the
     * control point before the segment and ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) as
     * the control point after it and store the result in {@code dest}.
     * <p>
     * The curve passes through ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) at
     * {@code t = 0} and through ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}) at
     * {@code t = 1}. This vector and ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) are the
     * spline's neighbouring points, i.e. the point before ({@code p1X}, {@code p1Y}, {@code p1Z},
     * {@code p1W}) and the point after ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}): they
     * only shape the tangents at the segment's two end points and are not themselves on the
     * segment. For a spline through the points {@code p[0..n]}, the segment from {@code p[i]} to
     * {@code p[i+1]} is therefore interpolated with {@code p[i-1]} in the role of this vector and
     * {@code p[i]}, {@code p[i+1]}, {@code p[i+2]} as the three given points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Z the {@code z} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3W the {@code w} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] catmullRom(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.catmullRom_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
            return Float4OpsSimd.catmullRom_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        }
        return Float4OpsKernelsArray.catmullRom_scalar(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@link #catmullRom(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer catmullRom(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.catmullRom_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return Float4OpsKernelsTypedBuffer.catmullRom_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@link #catmullRom(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer catmullRom(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.catmullRom_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return Float4OpsKernelsByteBuffer.catmullRom_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@link #catmullRom(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment catmullRom(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.catmullRom(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            catmullRom(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.catmullRom_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return Float4OpsKernelsSegment.catmullRom_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@link #catmullRom(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long catmullRom(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.catmullRom_unsafe(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        catmullRom(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    /**
     * Interpolate along the Catmull-Rom spline segment from {@code p1} to {@code p2}, with this
     * vector as the control point before the segment and {@code p3} as the control point after it
     * and store the result in {@code dest}.
     * <p>
     * The curve passes through {@code p1} at {@code t = 0} and through {@code p2} at {@code t = 1}.
     * This vector and {@code p3} are the spline's neighbouring points, i.e. the point before
     * {@code p1} and the point after {@code p2}: they only shape the tangents at the segment's two
     * end points and are not themselves on the segment. For a spline through the points
     * {@code p[0..n]}, the segment from {@code p[i]} to {@code p[i+1]} is therefore interpolated
     * with {@code p[i-1]} in the role of this vector and {@code p[i]}, {@code p[i+1]},
     * {@code p[i+2]} as the three given points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param p1 the storage holding the start point of the interpolated segment
     * @param p1Offset the element index in {@code p1} at which the vector starts
     * @param p2 the storage holding the end point of the interpolated segment
     * @param p2Offset the element index in {@code p2} at which the vector starts
     * @param p3 the storage holding the control point after the segment, i.e. the spline point
     *        following the given vector
     * @param p3Offset the element index in {@code p3} at which the vector starts
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] catmullRom(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.catmullRom_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
            return Float4OpsSimd.catmullRom_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        }
        return Float4OpsKernelsArray.catmullRom_scalar(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #catmullRom(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer catmullRom(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.catmullRom_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Float4OpsKernelsTypedBuffer.catmullRom_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #catmullRom(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer catmullRom(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.catmullRom_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Float4OpsKernelsByteBuffer.catmullRom_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #catmullRom(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment catmullRom(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.catmullRom(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        float[] _destArray, _srcArray, _p1Array, _p2Array, _p3Array;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_p1Array = heapFloats(p1, p1Offset)) != null
                && (_p2Array = heapFloats(p2, p2Offset)) != null
                && (_p3Array = heapFloats(p3, p3Offset)) != null) {
            catmullRom(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _p1Array, heapIndex(p1, p1Offset, 2), _p2Array, heapIndex(p2, p2Offset, 2), _p3Array, heapIndex(p3, p3Offset, 2), t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && p1.isNative() && p2.isNative() && p3.isNative()) return Float4OpsKernelsSegment.catmullRom_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Float4OpsKernelsSegment.catmullRom_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #catmullRom(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long catmullRom(long dest, long src, long p1, long p2, long p3, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.catmullRom_unsafe(dest, src, p1, p2, p3, t);
        catmullRom(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(p1, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(p2, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(p3, 16L), 0L, t);
        return dest;
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) to
     * ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}), with this vector as the control point
     * before the segment and ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) as the control
     * point after it and store the result in {@code dest}.
     * <p>
     * The curve passes through ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) at
     * {@code t = 0} and through ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}) at
     * {@code t = 1}. This vector and ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) are the
     * spline's neighbouring points, i.e. the point before ({@code p1X}, {@code p1Y}, {@code p1Z},
     * {@code p1W}) and the point after ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}): they
     * only shape the tangents at the segment's two end points and are not themselves on the
     * segment. For a spline through the points {@code p[0..n]}, the segment from {@code p[i]} to
     * {@code p[i+1]} is therefore interpolated with {@code p[i-1]} in the role of this vector and
     * {@code p[i]}, {@code p[i+1]}, {@code p[i+2]} as the three given points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3Z the {@code z} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param p3W the {@code w} component of the vector {@code (p3X, p3Y, p3Z, p3W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] catmullRomTangent(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.catmullRomTangent_fma(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
            return Float4OpsSimd.catmullRomTangent_mulAdd(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        }
        return Float4OpsKernelsArray.catmullRomTangent_scalar(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@link #catmullRomTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer catmullRomTangent(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.catmullRomTangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return Float4OpsKernelsTypedBuffer.catmullRomTangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@link #catmullRomTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer catmullRomTangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.catmullRomTangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return Float4OpsKernelsByteBuffer.catmullRomTangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@link #catmullRomTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment catmullRomTangent(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.catmullRomTangent(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            catmullRomTangent(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.catmullRomTangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return Float4OpsKernelsSegment.catmullRomTangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
    }

    /** {@link #catmullRomTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long catmullRomTangent(long dest, long src, float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.catmullRomTangent_unsafe(dest, src, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        catmullRomTangent(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t);
        return dest;
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from {@code p1} to {@code p2}, with this vector as the control
     * point before the segment and {@code p3} as the control point after it and store the result in
     * {@code dest}.
     * <p>
     * The curve passes through {@code p1} at {@code t = 0} and through {@code p2} at {@code t = 1}.
     * This vector and {@code p3} are the spline's neighbouring points, i.e. the point before
     * {@code p1} and the point after {@code p2}: they only shape the tangents at the segment's two
     * end points and are not themselves on the segment. For a spline through the points
     * {@code p[0..n]}, the segment from {@code p[i]} to {@code p[i+1]} is therefore interpolated
     * with {@code p[i-1]} in the role of this vector and {@code p[i]}, {@code p[i+1]},
     * {@code p[i+2]} as the three given points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param p1 the storage holding the start point of the interpolated segment
     * @param p1Offset the element index in {@code p1} at which the vector starts
     * @param p2 the storage holding the end point of the interpolated segment
     * @param p2Offset the element index in {@code p2} at which the vector starts
     * @param p3 the storage holding the control point after the segment, i.e. the spline point
     *        following the given vector
     * @param p3Offset the element index in {@code p3} at which the vector starts
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] catmullRomTangent(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset, float[] p3, int p3Offset, float t) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.catmullRomTangent_fma(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
            return Float4OpsSimd.catmullRomTangent_mulAdd(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        }
        return Float4OpsKernelsArray.catmullRomTangent_scalar(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #catmullRomTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer catmullRomTangent(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.catmullRomTangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Float4OpsKernelsTypedBuffer.catmullRomTangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #catmullRomTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer catmullRomTangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.catmullRomTangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Float4OpsKernelsByteBuffer.catmullRomTangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #catmullRomTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment catmullRomTangent(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment p1, long p1Offset, java.lang.foreign.MemorySegment p2, long p2Offset, java.lang.foreign.MemorySegment p3, long p3Offset, float t) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.catmullRomTangent(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        float[] _destArray, _srcArray, _p1Array, _p2Array, _p3Array;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_p1Array = heapFloats(p1, p1Offset)) != null
                && (_p2Array = heapFloats(p2, p2Offset)) != null
                && (_p3Array = heapFloats(p3, p3Offset)) != null) {
            catmullRomTangent(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _p1Array, heapIndex(p1, p1Offset, 2), _p2Array, heapIndex(p2, p2Offset, 2), _p3Array, heapIndex(p3, p3Offset, 2), t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && p1.isNative() && p2.isNative() && p3.isNative()) return Float4OpsKernelsSegment.catmullRomTangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Float4OpsKernelsSegment.catmullRomTangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #catmullRomTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long catmullRomTangent(long dest, long src, long p1, long p2, long p3, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.catmullRomTangent_unsafe(dest, src, p1, p2, p3, t);
        catmullRomTangent(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(p1, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(p2, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(p3, 16L), 0L, t);
        return dest;
    }

    /**
     * Interpolate along the cubic Hermite curve that starts at this vector with the tangent
     * ({@code t0X}, {@code t0Y}, {@code t0Z}, {@code t0W}) and ends at ({@code v1X}, {@code v1Y},
     * {@code v1Z}, {@code v1W}) with the tangent ({@code t1X}, {@code t1Y}, {@code t1Z},
     * {@code t1W}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code v1X}, {@code v1Y},
     * {@code v1Z}, {@code v1W}) at {@code t = 1}; the two tangents set its direction and speed at
     * those end points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param t0X the {@code x} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param t0Y the {@code y} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param t0Z the {@code z} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param t0W the {@code w} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param v1X the {@code x} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param v1Y the {@code y} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param v1Z the {@code z} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param v1W the {@code w} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param t1X the {@code x} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t1Y the {@code y} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t1Z the {@code z} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t1W the {@code w} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] hermite(float[] dest, int destOffset, float[] src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dest[destOffset] = Math.fma(src[srcOffset], _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9);
        dest[destOffset + 1] = Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9);
        dest[destOffset + 2] = Math.fma(_selfz, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9);
        dest[destOffset + 3] = Math.fma(_selfw, _t10, t0W * _t7) + Math.fma(t1W, _t5, v1W * _t9);
        return dest;
    }

    /** {@link #hermite(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer hermite(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.hermite_unsafe(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return Float4OpsKernelsTypedBuffer.hermite_api(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
    }

    /** {@link #hermite(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hermite(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.hermite_unsafe(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return Float4OpsKernelsByteBuffer.hermite_api(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
    }

    /** {@link #hermite(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment hermite(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            hermite(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.hermite_unsafe(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return Float4OpsKernelsSegment.hermite_api(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
    }

    /** {@link #hermite(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long hermite(long dest, long src, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.hermite_unsafe(dest, src, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        hermite(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    /**
     * Interpolate along the cubic Hermite curve that starts at this vector with the tangent
     * {@code t0} and ends at {@code v1} with the tangent {@code t1} and store the result in
     * {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code v1} at
     * {@code t = 1}; the two tangents set its direction and speed at those end points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param t0 the storage holding the tangent at the start point, i.e. at this vector
     * @param t0Offset the element index in {@code t0} at which the vector starts
     * @param v1 the storage holding the end point of the curve
     * @param v1Offset the element index in {@code v1} at which the vector starts
     * @param t1 the storage holding the tangent at the end point the given vector
     * @param t1Offset the element index in {@code t1} at which the vector starts
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] hermite(float[] dest, int destOffset, float[] src, int srcOffset, float[] t0, int t0Offset, float[] v1, int v1Offset, float[] t1, int t1Offset, float t) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.hermite_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
            return Float4OpsSimd.hermite_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        }
        return Float4OpsKernelsArray.hermite_scalar(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@link #hermite(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer hermite(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && t0.isDirect() && t0.order() == java.nio.ByteOrder.nativeOrder() && v1.isDirect() && v1.order() == java.nio.ByteOrder.nativeOrder() && t1.isDirect() && t1.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.hermite_unsafe(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return Float4OpsKernelsTypedBuffer.hermite_api(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@link #hermite(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hermite(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && t0.isDirect() && t0.order() == java.nio.ByteOrder.nativeOrder() && v1.isDirect() && v1.order() == java.nio.ByteOrder.nativeOrder() && t1.isDirect() && t1.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.hermite_unsafe(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return Float4OpsKernelsByteBuffer.hermite_api(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@link #hermite(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment hermite(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.hermite(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        float[] _destArray, _srcArray, _t0Array, _v1Array, _t1Array;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_t0Array = heapFloats(t0, t0Offset)) != null
                && (_v1Array = heapFloats(v1, v1Offset)) != null
                && (_t1Array = heapFloats(t1, t1Offset)) != null) {
            hermite(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _t0Array, heapIndex(t0, t0Offset, 2), _v1Array, heapIndex(v1, v1Offset, 2), _t1Array, heapIndex(t1, t1Offset, 2), t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && t0.isNative() && v1.isNative() && t1.isNative()) return Float4OpsKernelsSegment.hermite_unsafe(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return Float4OpsKernelsSegment.hermite_api(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@link #hermite(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long hermite(long dest, long src, long t0, long v1, long t1, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.hermite_unsafe(dest, src, t0, v1, t1, t);
        hermite(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(t0, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v1, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(t1, 16L), 0L, t);
        return dest;
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Hermite curve that starts at this vector with the tangent ({@code t0X}, {@code t0Y},
     * {@code t0Z}, {@code t0W}) and ends at ({@code v1X}, {@code v1Y}, {@code v1Z}, {@code v1W})
     * with the tangent ({@code t1X}, {@code t1Y}, {@code t1Z}, {@code t1W}) and store the result in
     * {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code v1X}, {@code v1Y},
     * {@code v1Z}, {@code v1W}) at {@code t = 1}; the two tangents set its direction and speed at
     * those end points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param t0X the {@code x} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param t0Y the {@code y} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param t0Z the {@code z} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param t0W the {@code w} component of the vector {@code (t0X, t0Y, t0Z, t0W)}
     * @param v1X the {@code x} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param v1Y the {@code y} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param v1Z the {@code z} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param v1W the {@code w} component of the vector {@code (v1X, v1Y, v1Z, v1W)}
     * @param t1X the {@code x} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t1Y the {@code y} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t1Z the {@code z} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t1W the {@code w} component of the vector {@code (t1X, t1Y, t1Z, t1W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] hermiteTangent(float[] dest, int destOffset, float[] src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dest[destOffset] = Math.fma(src[srcOffset], _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7);
        dest[destOffset + 1] = Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7);
        dest[destOffset + 2] = Math.fma(_selfz, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7);
        dest[destOffset + 3] = Math.fma(_selfw, _t6, t0W * _t9) + Math.fma(t1W, _t8, v1W * _t7);
        return dest;
    }

    /** {@link #hermiteTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer hermiteTangent(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.hermiteTangent_unsafe(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return Float4OpsKernelsTypedBuffer.hermiteTangent_api(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
    }

    /** {@link #hermiteTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hermiteTangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.hermiteTangent_unsafe(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return Float4OpsKernelsByteBuffer.hermiteTangent_api(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
    }

    /** {@link #hermiteTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment hermiteTangent(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            hermiteTangent(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.hermiteTangent_unsafe(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return Float4OpsKernelsSegment.hermiteTangent_api(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
    }

    /** {@link #hermiteTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long hermiteTangent(long dest, long src, float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.hermiteTangent_unsafe(dest, src, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        hermiteTangent(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t);
        return dest;
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Hermite curve that starts at this vector with the tangent {@code t0} and ends at
     * {@code v1} with the tangent {@code t1} and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code v1} at
     * {@code t = 1}; the two tangents set its direction and speed at those end points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param t0 the storage holding the tangent at the start point, i.e. at this vector
     * @param t0Offset the element index in {@code t0} at which the vector starts
     * @param v1 the storage holding the end point of the curve
     * @param v1Offset the element index in {@code v1} at which the vector starts
     * @param t1 the storage holding the tangent at the end point the given vector
     * @param t1Offset the element index in {@code t1} at which the vector starts
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] hermiteTangent(float[] dest, int destOffset, float[] src, int srcOffset, float[] t0, int t0Offset, float[] v1, int v1Offset, float[] t1, int t1Offset, float t) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.hermiteTangent_fma(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
            return Float4OpsSimd.hermiteTangent_mulAdd(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        }
        return Float4OpsKernelsArray.hermiteTangent_scalar(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@link #hermiteTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer hermiteTangent(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && t0.isDirect() && t0.order() == java.nio.ByteOrder.nativeOrder() && v1.isDirect() && v1.order() == java.nio.ByteOrder.nativeOrder() && t1.isDirect() && t1.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.hermiteTangent_unsafe(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return Float4OpsKernelsTypedBuffer.hermiteTangent_api(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@link #hermiteTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hermiteTangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && t0.isDirect() && t0.order() == java.nio.ByteOrder.nativeOrder() && v1.isDirect() && v1.order() == java.nio.ByteOrder.nativeOrder() && t1.isDirect() && t1.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.hermiteTangent_unsafe(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return Float4OpsKernelsByteBuffer.hermiteTangent_api(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@link #hermiteTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment hermiteTangent(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t0, long t0Offset, java.lang.foreign.MemorySegment v1, long v1Offset, java.lang.foreign.MemorySegment t1, long t1Offset, float t) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.hermiteTangent(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        float[] _destArray, _srcArray, _t0Array, _v1Array, _t1Array;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_t0Array = heapFloats(t0, t0Offset)) != null
                && (_v1Array = heapFloats(v1, v1Offset)) != null
                && (_t1Array = heapFloats(t1, t1Offset)) != null) {
            hermiteTangent(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _t0Array, heapIndex(t0, t0Offset, 2), _v1Array, heapIndex(v1, v1Offset, 2), _t1Array, heapIndex(t1, t1Offset, 2), t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && t0.isNative() && v1.isNative() && t1.isNative()) return Float4OpsKernelsSegment.hermiteTangent_unsafe(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return Float4OpsKernelsSegment.hermiteTangent_api(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@link #hermiteTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long hermiteTangent(long dest, long src, long t0, long v1, long t1, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.hermiteTangent_unsafe(dest, src, t0, v1, t1, t);
        hermiteTangent(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(t0, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v1, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(t1, 16L), 0L, t);
        return dest;
    }

    /**
     * Linearly interpolate between this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) using the interpolation factor {@code t} and store the result in
     * {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) (interpolation factor
     * {@code 1}). Each linearly interpolated component is {@code this + (other - this) * t}, as in
     * JOML and glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only
     * up to the rounding of {@code other - this}, which shows when this component is much larger in
     * magnitude than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.lerp_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
            return Float4OpsSimd.lerp_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        }
        return Float4OpsKernelsArray.lerp_scalar(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@link #lerp(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer lerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return Float4OpsKernelsTypedBuffer.lerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@link #lerp(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return Float4OpsKernelsByteBuffer.lerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@link #lerp(float[], int, float[], int, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.lerp(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            lerp(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), otherX, otherY, otherZ, otherW, t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.lerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return Float4OpsKernelsSegment.lerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@link #lerp(float[], int, float[], int, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long lerp(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.lerp_unsafe(dest, src, otherX, otherY, otherZ, otherW, t);
        lerp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    /**
     * Linearly interpolate between this vector and {@code other} using the interpolation factor
     * {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param other the storage holding the vector to interpolate towards
     * @param otherOffset the element index in {@code other} at which the vector starts
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
            return Float4OpsSimd.lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
        }
        return Float4OpsKernelsArray.lerp_scalar(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer lerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float4OpsKernelsTypedBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float4OpsKernelsByteBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.lerp(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float[] _destArray, _srcArray, _otherArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_otherArray = heapFloats(other, otherOffset)) != null) {
            lerp(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _otherArray, heapIndex(other, otherOffset, 2), t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Float4OpsKernelsSegment.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float4OpsKernelsSegment.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long lerp(long dest, long src, long other, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.lerp_unsafe(dest, src, other, t);
        lerp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L, t);
        return dest;
    }

    /**
     * Linearly interpolate between this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) using the interpolation factor ({@code tX}, {@code tY}, {@code tZ},
     * {@code tW}) and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) (interpolation factor
     * {@code 1}). Each linearly interpolated component is {@code this + (other - this) * t}, as in
     * JOML and glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only
     * up to the rounding of {@code other - this}, which shows when this component is much larger in
     * magnitude than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param tX the {@code x} component of the vector {@code (tX, tY, tZ, tW)}
     * @param tY the {@code y} component of the vector {@code (tX, tY, tZ, tW)}
     * @param tZ the {@code z} component of the vector {@code (tX, tY, tZ, tW)}
     * @param tW the {@code w} component of the vector {@code (tX, tY, tZ, tW)}
     * @return {@code dest}
     */
    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.fma(tX, otherX - _selfx, _selfx);
        dest[destOffset + 1] = Math.fma(tY, otherY - _selfy, _selfy);
        dest[destOffset + 2] = Math.fma(tZ, otherZ - _selfz, _selfz);
        dest[destOffset + 3] = Math.fma(tW, otherW - _selfw, _selfw);
        return dest;
    }

    /** {@link #lerp(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer lerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
        return Float4OpsKernelsTypedBuffer.lerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
    }

    /** {@link #lerp(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
        return Float4OpsKernelsByteBuffer.lerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
    }

    /** {@link #lerp(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            lerp(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.lerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
        return Float4OpsKernelsSegment.lerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
    }

    /** {@link #lerp(float[], int, float[], int, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long lerp(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.lerp_unsafe(dest, src, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
        lerp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW, tX, tY, tZ, tW);
        return dest;
    }

    /**
     * Linearly interpolate between this vector and {@code other} using the interpolation factor
     * {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param other the storage holding the vector to interpolate towards
     * @param otherOffset the element index in {@code other} at which the vector starts
     * @param t the storage holding the per-component interpolation factors, typically within
     *        {@code [0, 1]}
     * @param tOffset the element index in {@code t} at which the vector starts
     * @return {@code dest}
     */
    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float[] t, int tOffset) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
            return Float4OpsSimd.lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        }
        return Float4OpsKernelsArray.lerp_scalar(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer lerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer t, int tOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder() && t.isDirect() && t.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        return Float4OpsKernelsTypedBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer t, int tOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder() && t.isDirect() && t.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        return Float4OpsKernelsByteBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.lerp(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        float[] _destArray, _srcArray, _otherArray, _tArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_otherArray = heapFloats(other, otherOffset)) != null
                && (_tArray = heapFloats(t, tOffset)) != null) {
            lerp(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _otherArray, heapIndex(other, otherOffset, 2), _tArray, heapIndex(t, tOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative() && t.isNative()) return Float4OpsKernelsSegment.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        return Float4OpsKernelsSegment.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long lerp(long dest, long src, long other, long t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.lerp_unsafe(dest, src, other, t);
        lerp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(t, 16L), 0L);
        return dest;
    }

    /**
     * Spherically interpolate between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) using the interpolation factor {@code t}: the direction turns
     * at a constant rate along the shorter arc between the two directions, and the length changes
     * linearly between the two lengths and store the result in {@code dest}.
     * <p>
     * For unit vectors this is the usual {@code slerp} of directions. A zero vector has no
     * direction, so the result is then the linear interpolation; for two vectors pointing in
     * opposite directions, whose arc lies in no particular plane, the direction turns through the
     * perpendicular {@code (-y, x, -w, z)} of this vector. The angle is computed with
     * {@code atan2}, and vectors of any finite length are handled: when their squared lengths leave
     * the {@code float} range, they are first scaled exactly by powers of two.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) (interpolation factor
     * {@code 1}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] slerp(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t9 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t10 = Math.fma(otherW, otherW, Math.fma(otherZ, otherZ, Math.fma(otherX, otherX, otherY * otherY)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t10));
        float _t16 = _selfx * _t11;
        float _t19 = _selfw * _t11;
        float _t21 = _selfz * _t11;
        float _t24 = _selfy * _t11;
        return slerp_sfc7e44bf_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, _t9, _t10, _t14, _t16, _t19, _t21, _t24, Math.fma(otherW * _t14, _t19, Math.fma(otherZ * _t14, _t21, Math.fma(otherX * _t14, _t16, otherY * _t14 * _t24))));
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_sfc7e44bf_1(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float _t9, float _t10, float _t14, float _t16, float _t19, float _t21, float _t24, float _t31) {
        return slerp_s8078c2bf_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t, _t14, _t16, _t19, _t21, _t24, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9), _t31, Math.fma(otherW, _t14, -(_t31 * _t19)));
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_s8078c2bf_1(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28, float _t31, float _t40) {
        float _t41 = Math.fma(otherZ, _t14, -(_t31 * _t21));
        float _t42 = Math.fma(otherX, _t14, -(_t31 * _t16));
        float _t43 = Math.fma(otherY, _t14, -(_t31 * _t24));
        float _t48 = -Math.fma(_t40, _t19, Math.fma(_t41, _t21, Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = Math.fma(_t48, _t19, _t40);
        float _t50 = Math.fma(_t48, _t21, _t41);
        float _t51 = Math.fma(_t48, _t16, _t42);
        float _t52 = Math.fma(_t48, _t24, _t43);
        float _t57 = Math.fma(_t49, _t49, Math.fma(_t50, _t50, Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        float _sp0 = _t28 * Math.sin(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57));
        float _t66 = _t28 * Math.cos(_t61);
        dest[destOffset] = Math.fma(_t16, _t66, _sp0 * _t51);
        dest[destOffset + 1] = Math.fma(_t24, _t66, _sp0 * _t52);
        dest[destOffset + 2] = Math.fma(_t21, _t66, _sp0 * _t50);
        dest[destOffset + 3] = Math.fma(_t19, _t66, _sp0 * _t49);
        return dest;
    }

    /** {@link #slerp(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer slerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.slerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return Float4OpsKernelsTypedBuffer.slerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@link #slerp(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer slerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.slerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return Float4OpsKernelsByteBuffer.slerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@link #slerp(float[], int, float[], int, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment slerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            slerp(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), otherX, otherY, otherZ, otherW, t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.slerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return Float4OpsKernelsSegment.slerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@link #slerp(float[], int, float[], int, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long slerp(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.slerp_unsafe(dest, src, otherX, otherY, otherZ, otherW, t);
        slerp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    /**
     * Spherically interpolate between this vector and {@code other} using the interpolation factor
     * {@code t}: the direction turns at a constant rate along the shorter arc between the two
     * directions, and the length changes linearly between the two lengths and store the result in
     * {@code dest}.
     * <p>
     * For unit vectors this is the usual {@code slerp} of directions. A zero vector has no
     * direction, so the result is then the linear interpolation; for two vectors pointing in
     * opposite directions, whose arc lies in no particular plane, the direction turns through the
     * perpendicular {@code (-y, x, -w, z)} of this vector. The angle is computed with
     * {@code atan2}, and vectors of any finite length are handled: when their squared lengths leave
     * the {@code float} range, they are first scaled exactly by powers of two.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param other the storage holding the vector to interpolate towards
     * @param otherOffset the element index in {@code other} at which the vector starts
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] slerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        float _t9 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        if (!(_t9 > 1.1754944E-38f && _t9 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t10 = Math.fma(_otherw, _otherw, Math.fma(_otherz, _otherz, Math.fma(_otherx, _otherx, _othery * _othery)));
        if (!(_t10 > 1.1754944E-38f && _t10 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t9));
        return slerp_s21d687e7_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _otherx, _othery, _otherz, _otherw, (1.0f / (float) java.lang.Math.sqrt(_t10)), _selfx * _t11, _selfw * _t11, _selfz * _t11, _selfy * _t11, t * (float) java.lang.Math.sqrt(_t10) + (1.0f - t) * (float) java.lang.Math.sqrt(_t9));
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_s21d687e7_1(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t, float _otherx, float _othery, float _otherz, float _otherw, float _t14, float _t16, float _t19, float _t21, float _t24, float _t28) {
        float _t31 = Math.fma(_otherw * _t14, _t19, Math.fma(_otherz * _t14, _t21, Math.fma(_otherx * _t14, _t16, _othery * _t14 * _t24)));
        float _t40 = Math.fma(_otherw, _t14, -(_t31 * _t19));
        float _t41 = Math.fma(_otherz, _t14, -(_t31 * _t21));
        float _t42 = Math.fma(_otherx, _t14, -(_t31 * _t16));
        float _t43 = Math.fma(_othery, _t14, -(_t31 * _t24));
        float _t48 = -Math.fma(_t40, _t19, Math.fma(_t41, _t21, Math.fma(_t42, _t16, _t43 * _t24)));
        float _t49 = Math.fma(_t48, _t19, _t40);
        float _t50 = Math.fma(_t48, _t21, _t41);
        float _t51 = Math.fma(_t48, _t16, _t42);
        float _t52 = Math.fma(_t48, _t24, _t43);
        float _t57 = Math.fma(_t49, _t49, Math.fma(_t50, _t50, Math.fma(_t51, _t51, _t52 * _t52)));
        if (!(_t57 > 1.4551915E-11f && _t57 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t61 = t * Math.atan2((float) java.lang.Math.sqrt(_t57), _t31);
        return slerp_s21d687e7_2(dest, destOffset, _t16, _t19, _t21, _t24, _t49, _t50, _t51, _t52, _t28 * Math.sin(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t57)), _t28 * Math.cos(_t61));
    }

    /** Piece 3 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_s21d687e7_2(float[] dest, int destOffset, float _t16, float _t19, float _t21, float _t24, float _t49, float _t50, float _t51, float _t52, float _sp0, float _t66) {
        dest[destOffset] = Math.fma(_t16, _t66, _sp0 * _t51);
        dest[destOffset + 1] = Math.fma(_t24, _t66, _sp0 * _t52);
        dest[destOffset + 2] = Math.fma(_t21, _t66, _sp0 * _t50);
        dest[destOffset + 3] = Math.fma(_t19, _t66, _sp0 * _t49);
        return dest;
    }

    /** {@link #slerp(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer slerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.slerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float4OpsKernelsTypedBuffer.slerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #slerp(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer slerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.slerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float4OpsKernelsByteBuffer.slerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #slerp(float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment slerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        float[] _destArray, _srcArray, _otherArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_otherArray = heapFloats(other, otherOffset)) != null) {
            slerp(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _otherArray, heapIndex(other, otherOffset, 2), t);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Float4OpsKernelsSegment.slerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float4OpsKernelsSegment.slerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #slerp(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long slerp(long dest, long src, long other, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.slerp_unsafe(dest, src, other, t);
        slerp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L, t);
        return dest;
    }

    /**
     * Compute the absolute value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] absolute(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.absolute(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsArray.absolute_scalar(dest, destOffset, src, srcOffset);
    }

    /** {@link #absolute(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer absolute(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.absolute_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.absolute_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #absolute(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer absolute(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.absolute_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.absolute_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #absolute(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment absolute(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.absolute(dest, destOffset, src, srcOffset);
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            absolute(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.absolute_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.absolute_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #absolute(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long absolute(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.absolute_unsafe(dest, src);
        absolute(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the arc cosine of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] acos(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.acos(src[srcOffset]);
        dest[destOffset + 1] = Math.acos(_selfy);
        dest[destOffset + 2] = Math.acos(_selfz);
        dest[destOffset + 3] = Math.acos(_selfw);
        return dest;
    }

    /** {@link #acos(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer acos(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.acos_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.acos_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #acos(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer acos(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.acos_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.acos_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #acos(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment acos(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            acos(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.acos_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.acos_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #acos(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long acos(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.acos_unsafe(dest, src);
        acos(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Add ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) scaled by {@code scalar} to this vector
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bW the {@code w} component of the vector {@code (bX, bY, bZ, bW)}
     * @param scalar the factor to scale ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) by before
     *        adding
     * @return {@code dest}
     */
    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float bX, float bY, float bZ, float bW, float scalar) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.fma(scalar, bX, src[srcOffset]);
        dest[destOffset + 1] = Math.fma(scalar, bY, _selfy);
        dest[destOffset + 2] = Math.fma(scalar, bZ, _selfz);
        dest[destOffset + 3] = Math.fma(scalar, bW, _selfw);
        return dest;
    }

    /** {@link #addScaled(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer addScaled(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, scalar);
        return Float4OpsKernelsTypedBuffer.addScaled_api(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, scalar);
    }

    /** {@link #addScaled(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer addScaled(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, scalar);
        return Float4OpsKernelsByteBuffer.addScaled_api(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, scalar);
    }

    /** {@link #addScaled(float[], int, float[], int, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment addScaled(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float bW, float scalar) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            addScaled(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), bX, bY, bZ, bW, scalar);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.addScaled_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, scalar);
        return Float4OpsKernelsSegment.addScaled_api(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, scalar);
    }

    /** {@link #addScaled(float[], int, float[], int, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long addScaled(long dest, long src, float bX, float bY, float bZ, float bW, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.addScaled_unsafe(dest, src, bX, bY, bZ, bW, scalar);
        addScaled(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, bX, bY, bZ, bW, scalar);
        return dest;
    }

    /**
     * Add {@code b} scaled by {@code scalar} to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param b the storage holding the vector to scale and add
     * @param bOffset the element index in {@code b} at which the vector starts
     * @param scalar the factor to scale the given vector by before adding
     * @return {@code dest}
     */
    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float scalar) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.addScaled_fma(dest, destOffset, src, srcOffset, b, bOffset, scalar);
            return Float4OpsSimd.addScaled_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        }
        return Float4OpsKernelsArray.addScaled_scalar(dest, destOffset, src, srcOffset, b, bOffset, scalar);
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer addScaled(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && b.isDirect() && b.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        return Float4OpsKernelsTypedBuffer.addScaled_api(dest, destOffset, src, srcOffset, b, bOffset, scalar);
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer addScaled(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && b.isDirect() && b.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        return Float4OpsKernelsByteBuffer.addScaled_api(dest, destOffset, src, srcOffset, b, bOffset, scalar);
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment addScaled(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, float scalar) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.addScaled(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        float[] _destArray, _srcArray, _bArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_bArray = heapFloats(b, bOffset)) != null) {
            addScaled(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _bArray, heapIndex(b, bOffset, 2), scalar);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && b.isNative()) return Float4OpsKernelsSegment.addScaled_unsafe(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        return Float4OpsKernelsSegment.addScaled_api(dest, destOffset, src, srcOffset, b, bOffset, scalar);
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long addScaled(long dest, long src, long b, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.addScaled_unsafe(dest, src, b, scalar);
        addScaled(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(b, 16L), 0L, scalar);
        return dest;
    }

    /**
     * Add ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) scaled by ({@code cX}, {@code cY},
     * {@code cZ}, {@code cW}) to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bW the {@code w} component of the vector {@code (bX, bY, bZ, bW)}
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cW the {@code w} component of the vector {@code (cX, cY, cZ, cW)}
     * @return {@code dest}
     */
    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.fma(bX, cX, src[srcOffset]);
        dest[destOffset + 1] = Math.fma(bY, cY, _selfy);
        dest[destOffset + 2] = Math.fma(bZ, cZ, _selfz);
        dest[destOffset + 3] = Math.fma(bW, cW, _selfw);
        return dest;
    }

    /** {@link #addScaled(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer addScaled(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return Float4OpsKernelsTypedBuffer.addScaled_api(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
    }

    /** {@link #addScaled(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer addScaled(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return Float4OpsKernelsByteBuffer.addScaled_api(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
    }

    /** {@link #addScaled(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment addScaled(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            addScaled(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), bX, bY, bZ, bW, cX, cY, cZ, cW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.addScaled_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return Float4OpsKernelsSegment.addScaled_api(dest, destOffset, src, srcOffset, bX, bY, bZ, bW, cX, cY, cZ, cW);
    }

    /** {@link #addScaled(float[], int, float[], int, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long addScaled(long dest, long src, float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.addScaled_unsafe(dest, src, bX, bY, bZ, bW, cX, cY, cZ, cW);
        addScaled(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, bX, bY, bZ, bW, cX, cY, cZ, cW);
        return dest;
    }

    /**
     * Add {@code b} scaled by {@code c} to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param b the storage holding the vector to scale and add
     * @param bOffset the element index in {@code b} at which the vector starts
     * @param c the storage holding the per-component factors to scale the given vector by before
     *        adding
     * @param cOffset the element index in {@code c} at which the vector starts
     * @return {@code dest}
     */
    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float[] b, int bOffset, float[] c, int cOffset) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.addScaled_fma(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
            return Float4OpsSimd.addScaled_mulAdd(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        }
        return Float4OpsKernelsArray.addScaled_scalar(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer addScaled(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && b.isDirect() && b.order() == java.nio.ByteOrder.nativeOrder() && c.isDirect() && c.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return Float4OpsKernelsTypedBuffer.addScaled_api(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer addScaled(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && b.isDirect() && b.order() == java.nio.ByteOrder.nativeOrder() && c.isDirect() && c.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return Float4OpsKernelsByteBuffer.addScaled_api(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment addScaled(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment b, long bOffset, java.lang.foreign.MemorySegment c, long cOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.addScaled(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        float[] _destArray, _srcArray, _bArray, _cArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_bArray = heapFloats(b, bOffset)) != null
                && (_cArray = heapFloats(c, cOffset)) != null) {
            addScaled(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _bArray, heapIndex(b, bOffset, 2), _cArray, heapIndex(c, cOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && b.isNative() && c.isNative()) return Float4OpsKernelsSegment.addScaled_unsafe(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return Float4OpsKernelsSegment.addScaled_api(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long addScaled(long dest, long src, long b, long c) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.addScaled_unsafe(dest, src, b, c);
        addScaled(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(b, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(c, 16L), 0L);
        return dest;
    }

    /**
     * Compute the angle in radians between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code float} range, the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the angle in radians between this vector and {@code other}
     */
    public static float angleBetween(float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t12 = Math.fma(otherW, _selfz, -(otherZ * _selfw));
        float _t13 = Math.fma(otherW, _selfy, -(otherY * _selfw));
        float _t14 = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        float _t15 = Math.fma(otherW, _selfx, -(otherX * _selfw));
        float _t16 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        float _t17 = Math.fma(otherZ, _selfx, -(otherX * _selfz));
        float _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsArray.angleBetween_degenerate(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), Math.fma(otherW, _selfw, Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy))));
    }

    /** {@link #angleBetween(float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float angleBetween(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.angleBetween_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsTypedBuffer.angleBetween_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #angleBetween(float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float angleBetween(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.angleBetween_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsByteBuffer.angleBetween_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #angleBetween(float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float angleBetween(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float[] _srcArray;
        if ((_srcArray = heapFloats(src, srcOffset)) != null) {
            return angleBetween(_srcArray, heapIndex(src, srcOffset, 2), otherX, otherY, otherZ, otherW);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float4OpsKernelsSegment.angleBetween_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsSegment.angleBetween_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #angleBetween(float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float angleBetween(long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.angleBetween_unsafe(src, otherX, otherY, otherZ, otherW);
        return angleBetween(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW);
    }

    /**
     * Compute the angle in radians between this vector and {@code other}.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code float} range, the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param other the storage holding the vector to measure the angle to
     * @param otherOffset the element index in {@code other} at which the vector starts
     * @return the angle in radians between this vector and {@code other}
     */
    public static float angleBetween(float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _otherx = other[otherOffset];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        float _t12 = Math.fma(_otherw, _selfz, -(_otherz * _selfw));
        float _t13 = Math.fma(_otherw, _selfy, -(_othery * _selfw));
        float _t14 = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        float _t15 = Math.fma(_otherw, _selfx, -(_otherx * _selfw));
        float _t16 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        float _t17 = Math.fma(_otherz, _selfx, -(_otherx * _selfz));
        float _ct0 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17)))));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float4OpsKernelsArray.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2((float) java.lang.Math.sqrt(_ct0), Math.fma(_otherw, _selfw, Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy))));
    }

    /** {@link #angleBetween(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float angleBetween(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.angleBetween_unsafe(src, srcOffset, other, otherOffset);
        return Float4OpsKernelsTypedBuffer.angleBetween_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #angleBetween(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float angleBetween(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.angleBetween_unsafe(src, srcOffset, other, otherOffset);
        return Float4OpsKernelsByteBuffer.angleBetween_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #angleBetween(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float angleBetween(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float[] _srcArray, _otherArray;
        if ((_srcArray = heapFloats(src, srcOffset)) != null
                && (_otherArray = heapFloats(other, otherOffset)) != null) {
            return angleBetween(_srcArray, heapIndex(src, srcOffset, 2), _otherArray, heapIndex(other, otherOffset, 2));
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative()) return Float4OpsKernelsSegment.angleBetween_unsafe(src, srcOffset, other, otherOffset);
        return Float4OpsKernelsSegment.angleBetween_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #angleBetween(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float angleBetween(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.angleBetween_unsafe(src, other);
        return angleBetween(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
    }

    /**
     * Compute the arc sine of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] asin(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.asin(src[srcOffset]);
        dest[destOffset + 1] = Math.asin(_selfy);
        dest[destOffset + 2] = Math.asin(_selfz);
        dest[destOffset + 3] = Math.asin(_selfw);
        return dest;
    }

    /** {@link #asin(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer asin(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.asin_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.asin_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #asin(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer asin(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.asin_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.asin_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #asin(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment asin(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            asin(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.asin_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.asin_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #asin(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long asin(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.asin_unsafe(dest, src);
        asin(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the arc tangent of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] atan(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.atan(src[srcOffset]);
        dest[destOffset + 1] = Math.atan(_selfy);
        dest[destOffset + 2] = Math.atan(_selfz);
        dest[destOffset + 3] = Math.atan(_selfw);
        return dest;
    }

    /** {@link #atan(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer atan(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.atan_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.atan_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #atan(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer atan(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.atan_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.atan_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #atan(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment atan(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            atan(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.atan_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.atan_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #atan(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long atan(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.atan_unsafe(dest, src);
        atan(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} {@code x} (the denominator) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param x the value to take the arc tangent over (the denominator)
     * @return {@code dest}
     */
    public static float[] atan2(float[] dest, int destOffset, float[] src, int srcOffset, float x) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.atan2(src[srcOffset], x);
        dest[destOffset + 1] = Math.atan2(_selfy, x);
        dest[destOffset + 2] = Math.atan2(_selfz, x);
        dest[destOffset + 3] = Math.atan2(_selfw, x);
        return dest;
    }

    /** {@link #atan2(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer atan2(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float x) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.atan2_unsafe(dest, destOffset, src, srcOffset, x);
        return Float4OpsKernelsTypedBuffer.atan2_api(dest, destOffset, src, srcOffset, x);
    }

    /** {@link #atan2(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer atan2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float x) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.atan2_unsafe(dest, destOffset, src, srcOffset, x);
        return Float4OpsKernelsByteBuffer.atan2_api(dest, destOffset, src, srcOffset, x);
    }

    /** {@link #atan2(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment atan2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float x) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            atan2(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), x);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.atan2_unsafe(dest, destOffset, src, srcOffset, x);
        return Float4OpsKernelsSegment.atan2_api(dest, destOffset, src, srcOffset, x);
    }

    /** {@link #atan2(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long atan2(long dest, long src, float x) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.atan2_unsafe(dest, src, x);
        atan2(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, x);
        return dest;
    }

    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} the corresponding component of ({@code xX},
     * {@code xY}, {@code xZ}, {@code xW}) (the denominator) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param xX the {@code x} component of the vector {@code (xX, xY, xZ, xW)}
     * @param xY the {@code y} component of the vector {@code (xX, xY, xZ, xW)}
     * @param xZ the {@code z} component of the vector {@code (xX, xY, xZ, xW)}
     * @param xW the {@code w} component of the vector {@code (xX, xY, xZ, xW)}
     * @return {@code dest}
     */
    public static float[] atan2(float[] dest, int destOffset, float[] src, int srcOffset, float xX, float xY, float xZ, float xW) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.atan2(src[srcOffset], xX);
        dest[destOffset + 1] = Math.atan2(_selfy, xY);
        dest[destOffset + 2] = Math.atan2(_selfz, xZ);
        dest[destOffset + 3] = Math.atan2(_selfw, xW);
        return dest;
    }

    /** {@link #atan2(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer atan2(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float xX, float xY, float xZ, float xW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.atan2_unsafe(dest, destOffset, src, srcOffset, xX, xY, xZ, xW);
        return Float4OpsKernelsTypedBuffer.atan2_api(dest, destOffset, src, srcOffset, xX, xY, xZ, xW);
    }

    /** {@link #atan2(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer atan2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float xX, float xY, float xZ, float xW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.atan2_unsafe(dest, destOffset, src, srcOffset, xX, xY, xZ, xW);
        return Float4OpsKernelsByteBuffer.atan2_api(dest, destOffset, src, srcOffset, xX, xY, xZ, xW);
    }

    /** {@link #atan2(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment atan2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float xX, float xY, float xZ, float xW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            atan2(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), xX, xY, xZ, xW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.atan2_unsafe(dest, destOffset, src, srcOffset, xX, xY, xZ, xW);
        return Float4OpsKernelsSegment.atan2_api(dest, destOffset, src, srcOffset, xX, xY, xZ, xW);
    }

    /** {@link #atan2(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long atan2(long dest, long src, float xX, float xY, float xZ, float xW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.atan2_unsafe(dest, src, xX, xY, xZ, xW);
        atan2(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, xX, xY, xZ, xW);
        return dest;
    }

    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} the corresponding component of {@code x} (the
     * denominator) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param x the storage holding the vector of denominators, one per component
     * @param xOffset the element index in {@code x} at which the vector starts
     * @return {@code dest}
     */
    public static float[] atan2(float[] dest, int destOffset, float[] src, int srcOffset, float[] x, int xOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _xy = x[xOffset + 1];
        float _xz = x[xOffset + 2];
        float _xw = x[xOffset + 3];
        dest[destOffset] = Math.atan2(src[srcOffset], x[xOffset]);
        dest[destOffset + 1] = Math.atan2(_selfy, _xy);
        dest[destOffset + 2] = Math.atan2(_selfz, _xz);
        dest[destOffset + 3] = Math.atan2(_selfw, _xw);
        return dest;
    }

    /** {@link #atan2(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer atan2(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer x, int xOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && x.isDirect() && x.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.atan2_unsafe(dest, destOffset, src, srcOffset, x, xOffset);
        return Float4OpsKernelsTypedBuffer.atan2_api(dest, destOffset, src, srcOffset, x, xOffset);
    }

    /** {@link #atan2(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer atan2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer x, int xOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && x.isDirect() && x.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.atan2_unsafe(dest, destOffset, src, srcOffset, x, xOffset);
        return Float4OpsKernelsByteBuffer.atan2_api(dest, destOffset, src, srcOffset, x, xOffset);
    }

    /** {@link #atan2(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment atan2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment x, long xOffset) {
        float[] _destArray, _srcArray, _xArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_xArray = heapFloats(x, xOffset)) != null) {
            atan2(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _xArray, heapIndex(x, xOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && x.isNative()) return Float4OpsKernelsSegment.atan2_unsafe(dest, destOffset, src, srcOffset, x, xOffset);
        return Float4OpsKernelsSegment.atan2_api(dest, destOffset, src, srcOffset, x, xOffset);
    }

    /** {@link #atan2(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long atan2(long dest, long src, long x) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.atan2_unsafe(dest, src, x);
        atan2(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(x, 16L), 0L);
        return dest;
    }

    /**
     * Compute the cube root of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] cbrt(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.cbrt(src[srcOffset]);
        dest[destOffset + 1] = Math.cbrt(_selfy);
        dest[destOffset + 2] = Math.cbrt(_selfz);
        dest[destOffset + 3] = Math.cbrt(_selfw);
        return dest;
    }

    /** {@link #cbrt(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer cbrt(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.cbrt_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.cbrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cbrt(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer cbrt(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.cbrt_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.cbrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cbrt(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment cbrt(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            cbrt(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.cbrt_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.cbrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cbrt(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long cbrt(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.cbrt_unsafe(dest, src);
        cbrt(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the ceiling of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] ceil(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.ceil(src[srcOffset]);
        dest[destOffset + 1] = Math.ceil(_selfy);
        dest[destOffset + 2] = Math.ceil(_selfz);
        dest[destOffset + 3] = Math.ceil(_selfw);
        return dest;
    }

    /** {@link #ceil(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer ceil(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.ceil_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.ceil_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #ceil(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer ceil(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.ceil_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.ceil_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #ceil(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment ceil(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            ceil(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.ceil_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.ceil_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #ceil(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long ceil(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.ceil_unsafe(dest, src);
        ceil(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param min the lower bound
     * @param max the upper bound
     * @return {@code dest}
     */
    public static float[] clamp(float[] dest, int destOffset, float[] src, int srcOffset, float min, float max) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.clamp(dest, destOffset, src, srcOffset, min, max);
        return Float4OpsKernelsArray.clamp_scalar(dest, destOffset, src, srcOffset, min, max);
    }

    /** {@link #clamp(float[], int, float[], int, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer clamp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float min, float max) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.clamp_unsafe(dest, destOffset, src, srcOffset, min, max);
        return Float4OpsKernelsTypedBuffer.clamp_api(dest, destOffset, src, srcOffset, min, max);
    }

    /** {@link #clamp(float[], int, float[], int, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer clamp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float min, float max) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.clamp_unsafe(dest, destOffset, src, srcOffset, min, max);
        return Float4OpsKernelsByteBuffer.clamp_api(dest, destOffset, src, srcOffset, min, max);
    }

    /** {@link #clamp(float[], int, float[], int, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment clamp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float min, float max) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.clamp(dest, destOffset, src, srcOffset, min, max);
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            clamp(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), min, max);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.clamp_unsafe(dest, destOffset, src, srcOffset, min, max);
        return Float4OpsKernelsSegment.clamp_api(dest, destOffset, src, srcOffset, min, max);
    }

    /** {@link #clamp(float[], int, float[], int, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long clamp(long dest, long src, float min, float max) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.clamp_unsafe(dest, src, min, max);
        clamp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, min, max);
        return dest;
    }

    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ},
     * {@code minW}) and ({@code maxX}, {@code maxY}, {@code maxZ}, {@code maxW}) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: {@code (minX, minY, minZ, minW)} must not exceed
     * {@code (maxX, maxY, maxZ, maxW)} in any component.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param minX the {@code x} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minY the {@code y} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minZ the {@code z} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minW the {@code w} component of the vector {@code (minX, minY, minZ, minW)}
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxZ the {@code z} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxW the {@code w} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @return {@code dest}
     */
    public static float[] clamp(float[] dest, int destOffset, float[] src, int srcOffset, float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = java.lang.Math.min(java.lang.Math.max(src[srcOffset], minX), maxX);
        dest[destOffset + 1] = java.lang.Math.min(java.lang.Math.max(_selfy, minY), maxY);
        dest[destOffset + 2] = java.lang.Math.min(java.lang.Math.max(_selfz, minZ), maxZ);
        dest[destOffset + 3] = java.lang.Math.min(java.lang.Math.max(_selfw, minW), maxW);
        return dest;
    }

    /** {@link #clamp(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer clamp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.clamp_unsafe(dest, destOffset, src, srcOffset, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
        return Float4OpsKernelsTypedBuffer.clamp_api(dest, destOffset, src, srcOffset, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
    }

    /** {@link #clamp(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer clamp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.clamp_unsafe(dest, destOffset, src, srcOffset, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
        return Float4OpsKernelsByteBuffer.clamp_api(dest, destOffset, src, srcOffset, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
    }

    /** {@link #clamp(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment clamp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            clamp(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.clamp_unsafe(dest, destOffset, src, srcOffset, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
        return Float4OpsKernelsSegment.clamp_api(dest, destOffset, src, srcOffset, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
    }

    /** {@link #clamp(float[], int, float[], int, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long clamp(long dest, long src, float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.clamp_unsafe(dest, src, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
        clamp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, minX, minY, minZ, minW, maxX, maxY, maxZ, maxW);
        return dest;
    }

    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param min the storage holding the per-component lower bounds
     * @param minOffset the element index in {@code min} at which the vector starts
     * @param max the storage holding the per-component upper bounds
     * @param maxOffset the element index in {@code max} at which the vector starts
     * @return {@code dest}
     */
    public static float[] clamp(float[] dest, int destOffset, float[] src, int srcOffset, float[] min, int minOffset, float[] max, int maxOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.clamp(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
        return Float4OpsKernelsArray.clamp_scalar(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
    }

    /** {@link #clamp(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer clamp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer min, int minOffset, java.nio.FloatBuffer max, int maxOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && min.isDirect() && min.order() == java.nio.ByteOrder.nativeOrder() && max.isDirect() && max.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.clamp_unsafe(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
        return Float4OpsKernelsTypedBuffer.clamp_api(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
    }

    /** {@link #clamp(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer clamp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer min, int minOffset, java.nio.ByteBuffer max, int maxOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && min.isDirect() && min.order() == java.nio.ByteOrder.nativeOrder() && max.isDirect() && max.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.clamp_unsafe(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
        return Float4OpsKernelsByteBuffer.clamp_api(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
    }

    /** {@link #clamp(float[], int, float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment clamp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment min, long minOffset, java.lang.foreign.MemorySegment max, long maxOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.clamp(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
        float[] _destArray, _srcArray, _minArray, _maxArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_minArray = heapFloats(min, minOffset)) != null
                && (_maxArray = heapFloats(max, maxOffset)) != null) {
            clamp(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _minArray, heapIndex(min, minOffset, 2), _maxArray, heapIndex(max, maxOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && min.isNative() && max.isNative()) return Float4OpsKernelsSegment.clamp_unsafe(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
        return Float4OpsKernelsSegment.clamp_api(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
    }

    /** {@link #clamp(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long clamp(long dest, long src, long min, long max) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.clamp_unsafe(dest, src, min, max);
        clamp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(min, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(max, 16L), 0L);
        return dest;
    }

    /**
     * Compute the sum of all components of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return the sum of all components of this vector
     */
    public static float compAdd(float[] src, int srcOffset) {
        return src[srcOffset + 3] + (src[srcOffset + 2] + (src[srcOffset] + src[srcOffset + 1]));
    }

    /** {@link #compAdd(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float compAdd(java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.compAdd_unsafe(src, srcOffset);
        return Float4OpsKernelsTypedBuffer.compAdd_api(src, srcOffset);
    }

    /** {@link #compAdd(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float compAdd(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.compAdd_unsafe(src, srcOffset);
        return Float4OpsKernelsByteBuffer.compAdd_api(src, srcOffset);
    }

    /** {@link #compAdd(float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float compAdd(java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _srcArray;
        if ((_srcArray = heapFloats(src, srcOffset)) != null) {
            return compAdd(_srcArray, heapIndex(src, srcOffset, 2));
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float4OpsKernelsSegment.compAdd_unsafe(src, srcOffset);
        return Float4OpsKernelsSegment.compAdd_api(src, srcOffset);
    }

    /** {@link #compAdd(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float compAdd(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.compAdd_unsafe(src);
        return compAdd(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
    }

    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return the largest component of this vector
     */
    public static float compMax(float[] src, int srcOffset) {
        return java.lang.Math.max(java.lang.Math.max(java.lang.Math.max(src[srcOffset], src[srcOffset + 1]), src[srcOffset + 2]), src[srcOffset + 3]);
    }

    /** {@link #compMax(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float compMax(java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.compMax_unsafe(src, srcOffset);
        return Float4OpsKernelsTypedBuffer.compMax_api(src, srcOffset);
    }

    /** {@link #compMax(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float compMax(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.compMax_unsafe(src, srcOffset);
        return Float4OpsKernelsByteBuffer.compMax_api(src, srcOffset);
    }

    /** {@link #compMax(float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float compMax(java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _srcArray;
        if ((_srcArray = heapFloats(src, srcOffset)) != null) {
            return compMax(_srcArray, heapIndex(src, srcOffset, 2));
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float4OpsKernelsSegment.compMax_unsafe(src, srcOffset);
        return Float4OpsKernelsSegment.compMax_api(src, srcOffset);
    }

    /** {@link #compMax(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float compMax(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.compMax_unsafe(src);
        return compMax(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
    }

    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return the smallest component of this vector
     */
    public static float compMin(float[] src, int srcOffset) {
        return java.lang.Math.min(java.lang.Math.min(java.lang.Math.min(src[srcOffset], src[srcOffset + 1]), src[srcOffset + 2]), src[srcOffset + 3]);
    }

    /** {@link #compMin(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float compMin(java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.compMin_unsafe(src, srcOffset);
        return Float4OpsKernelsTypedBuffer.compMin_api(src, srcOffset);
    }

    /** {@link #compMin(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float compMin(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.compMin_unsafe(src, srcOffset);
        return Float4OpsKernelsByteBuffer.compMin_api(src, srcOffset);
    }

    /** {@link #compMin(float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float compMin(java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _srcArray;
        if ((_srcArray = heapFloats(src, srcOffset)) != null) {
            return compMin(_srcArray, heapIndex(src, srcOffset, 2));
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float4OpsKernelsSegment.compMin_unsafe(src, srcOffset);
        return Float4OpsKernelsSegment.compMin_api(src, srcOffset);
    }

    /** {@link #compMin(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float compMin(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.compMin_unsafe(src);
        return compMin(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
    }

    /**
     * Compute the product of all components of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return the product of all components of this vector
     */
    public static float compMul(float[] src, int srcOffset) {
        return src[srcOffset + 3] * src[srcOffset + 2] * src[srcOffset] * src[srcOffset + 1];
    }

    /** {@link #compMul(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float compMul(java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.compMul_unsafe(src, srcOffset);
        return Float4OpsKernelsTypedBuffer.compMul_api(src, srcOffset);
    }

    /** {@link #compMul(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float compMul(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.compMul_unsafe(src, srcOffset);
        return Float4OpsKernelsByteBuffer.compMul_api(src, srcOffset);
    }

    /** {@link #compMul(float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float compMul(java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _srcArray;
        if ((_srcArray = heapFloats(src, srcOffset)) != null) {
            return compMul(_srcArray, heapIndex(src, srcOffset, 2));
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float4OpsKernelsSegment.compMul_unsafe(src, srcOffset);
        return Float4OpsKernelsSegment.compMul_api(src, srcOffset);
    }

    /** {@link #compMul(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float compMul(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.compMul_unsafe(src);
        return compMul(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
    }

    /**
     * Copy the sign of {@code sign} onto each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param sign the value whose sign is copied
     * @return {@code dest}
     */
    public static float[] copySign(float[] dest, int destOffset, float[] src, int srcOffset, float sign) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.copySign(src[srcOffset], sign);
        dest[destOffset + 1] = Math.copySign(_selfy, sign);
        dest[destOffset + 2] = Math.copySign(_selfz, sign);
        dest[destOffset + 3] = Math.copySign(_selfw, sign);
        return dest;
    }

    /** {@link #copySign(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer copySign(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sign) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.copySign_unsafe(dest, destOffset, src, srcOffset, sign);
        return Float4OpsKernelsTypedBuffer.copySign_api(dest, destOffset, src, srcOffset, sign);
    }

    /** {@link #copySign(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer copySign(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sign) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.copySign_unsafe(dest, destOffset, src, srcOffset, sign);
        return Float4OpsKernelsByteBuffer.copySign_api(dest, destOffset, src, srcOffset, sign);
    }

    /** {@link #copySign(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment copySign(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sign) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            copySign(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), sign);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.copySign_unsafe(dest, destOffset, src, srcOffset, sign);
        return Float4OpsKernelsSegment.copySign_api(dest, destOffset, src, srcOffset, sign);
    }

    /** {@link #copySign(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long copySign(long dest, long src, float sign) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.copySign_unsafe(dest, src, sign);
        copySign(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, sign);
        return dest;
    }

    /**
     * Copy the sign of each component of ({@code signX}, {@code signY}, {@code signZ},
     * {@code signW}) onto the corresponding component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param signX the {@code x} component of the vector {@code (signX, signY, signZ, signW)}
     * @param signY the {@code y} component of the vector {@code (signX, signY, signZ, signW)}
     * @param signZ the {@code z} component of the vector {@code (signX, signY, signZ, signW)}
     * @param signW the {@code w} component of the vector {@code (signX, signY, signZ, signW)}
     * @return {@code dest}
     */
    public static float[] copySign(float[] dest, int destOffset, float[] src, int srcOffset, float signX, float signY, float signZ, float signW) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.copySign(src[srcOffset], signX);
        dest[destOffset + 1] = Math.copySign(_selfy, signY);
        dest[destOffset + 2] = Math.copySign(_selfz, signZ);
        dest[destOffset + 3] = Math.copySign(_selfw, signW);
        return dest;
    }

    /** {@link #copySign(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer copySign(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float signX, float signY, float signZ, float signW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.copySign_unsafe(dest, destOffset, src, srcOffset, signX, signY, signZ, signW);
        return Float4OpsKernelsTypedBuffer.copySign_api(dest, destOffset, src, srcOffset, signX, signY, signZ, signW);
    }

    /** {@link #copySign(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer copySign(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float signX, float signY, float signZ, float signW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.copySign_unsafe(dest, destOffset, src, srcOffset, signX, signY, signZ, signW);
        return Float4OpsKernelsByteBuffer.copySign_api(dest, destOffset, src, srcOffset, signX, signY, signZ, signW);
    }

    /** {@link #copySign(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment copySign(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float signX, float signY, float signZ, float signW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            copySign(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), signX, signY, signZ, signW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.copySign_unsafe(dest, destOffset, src, srcOffset, signX, signY, signZ, signW);
        return Float4OpsKernelsSegment.copySign_api(dest, destOffset, src, srcOffset, signX, signY, signZ, signW);
    }

    /** {@link #copySign(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long copySign(long dest, long src, float signX, float signY, float signZ, float signW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.copySign_unsafe(dest, src, signX, signY, signZ, signW);
        copySign(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, signX, signY, signZ, signW);
        return dest;
    }

    /**
     * Copy the sign of each component of {@code sign} onto the corresponding component of this
     * vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param sign the storage holding the value whose sign is copied
     * @param signOffset the element index in {@code sign} at which the vector starts
     * @return {@code dest}
     */
    public static float[] copySign(float[] dest, int destOffset, float[] src, int srcOffset, float[] sign, int signOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _signy = sign[signOffset + 1];
        float _signz = sign[signOffset + 2];
        float _signw = sign[signOffset + 3];
        dest[destOffset] = Math.copySign(src[srcOffset], sign[signOffset]);
        dest[destOffset + 1] = Math.copySign(_selfy, _signy);
        dest[destOffset + 2] = Math.copySign(_selfz, _signz);
        dest[destOffset + 3] = Math.copySign(_selfw, _signw);
        return dest;
    }

    /** {@link #copySign(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer copySign(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer sign, int signOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && sign.isDirect() && sign.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.copySign_unsafe(dest, destOffset, src, srcOffset, sign, signOffset);
        return Float4OpsKernelsTypedBuffer.copySign_api(dest, destOffset, src, srcOffset, sign, signOffset);
    }

    /** {@link #copySign(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer copySign(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer sign, int signOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && sign.isDirect() && sign.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.copySign_unsafe(dest, destOffset, src, srcOffset, sign, signOffset);
        return Float4OpsKernelsByteBuffer.copySign_api(dest, destOffset, src, srcOffset, sign, signOffset);
    }

    /** {@link #copySign(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment copySign(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment sign, long signOffset) {
        float[] _destArray, _srcArray, _signArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_signArray = heapFloats(sign, signOffset)) != null) {
            copySign(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _signArray, heapIndex(sign, signOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && sign.isNative()) return Float4OpsKernelsSegment.copySign_unsafe(dest, destOffset, src, srcOffset, sign, signOffset);
        return Float4OpsKernelsSegment.copySign_api(dest, destOffset, src, srcOffset, sign, signOffset);
    }

    /** {@link #copySign(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long copySign(long dest, long src, long sign) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.copySign_unsafe(dest, src, sign);
        copySign(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(sign, 16L), 0L);
        return dest;
    }

    /**
     * Compute the cosine of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] cos(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.cos(src[srcOffset]);
        dest[destOffset + 1] = Math.cos(_selfy);
        dest[destOffset + 2] = Math.cos(_selfz);
        dest[destOffset + 3] = Math.cos(_selfw);
        return dest;
    }

    /** {@link #cos(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer cos(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.cos_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.cos_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cos(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer cos(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.cos_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.cos_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cos(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment cos(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            cos(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.cos_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.cos_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cos(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long cos(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.cos_unsafe(dest, src);
        cos(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the hyperbolic cosine of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] cosh(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.cosh(src[srcOffset]);
        dest[destOffset + 1] = Math.cosh(_selfy);
        dest[destOffset + 2] = Math.cosh(_selfz);
        dest[destOffset + 3] = Math.cosh(_selfw);
        return dest;
    }

    /** {@link #cosh(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer cosh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.cosh_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.cosh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cosh(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer cosh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.cosh_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.cosh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cosh(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment cosh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            cosh(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.cosh_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.cosh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cosh(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long cosh(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.cosh_unsafe(dest, src);
        cosh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the four-dimensional cross product of this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}), in that order:
     * the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}) (the zero vector
     * when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param vX the {@code x} component of the second operand of the cross product
     *        {@code (vX, vY, vZ, vW)}
     * @param vY the {@code y} component of the second operand of the cross product
     *        {@code (vX, vY, vZ, vW)}
     * @param vZ the {@code z} component of the second operand of the cross product
     *        {@code (vX, vY, vZ, vW)}
     * @param vW the {@code w} component of the second operand of the cross product
     *        {@code (vX, vY, vZ, vW)}
     * @param wX the {@code x} component of the third operand of the cross product
     *        {@code (wX, wY, wZ, wW)}
     * @param wY the {@code y} component of the third operand of the cross product
     *        {@code (wX, wY, wZ, wW)}
     * @param wZ the {@code z} component of the third operand of the cross product
     *        {@code (wX, wY, wZ, wW)}
     * @param wW the {@code w} component of the third operand of the cross product
     *        {@code (wX, wY, wZ, wW)}
     * @return {@code dest}
     */
    public static float[] cross(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t12 = Math.fma(vY, wZ, -(vZ * wY));
        float _t13 = Math.fma(vZ, wW, -(vW * wZ));
        float _t14 = Math.fma(vY, wW, -(vW * wY));
        float _t15 = Math.fma(vX, wZ, -(vZ * wX));
        float _t16 = Math.fma(vX, wW, -(vW * wX));
        float _t17 = Math.fma(vX, wY, -(vY * wX));
        dest[destOffset] = Math.fma(_selfw, _t12, Math.fma(_selfy, _t13, -(_selfz * _t14)));
        dest[destOffset + 1] = Math.fma(-_selfw, _t15, Math.fma(_selfz, _t16, -(_selfx * _t13)));
        dest[destOffset + 2] = Math.fma(_selfw, _t17, Math.fma(_selfx, _t14, -(_selfy * _t16)));
        dest[destOffset + 3] = Math.fma(-_selfz, _t17, Math.fma(_selfy, _t15, -(_selfx * _t12)));
        return dest;
    }

    /** {@link #cross(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer cross(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.cross_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return Float4OpsKernelsTypedBuffer.cross_api(dest, destOffset, src, srcOffset, vX, vY, vZ, vW, wX, wY, wZ, wW);
    }

    /** {@link #cross(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer cross(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.cross_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return Float4OpsKernelsByteBuffer.cross_api(dest, destOffset, src, srcOffset, vX, vY, vZ, vW, wX, wY, wZ, wW);
    }

    /** {@link #cross(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment cross(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            cross(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), vX, vY, vZ, vW, wX, wY, wZ, wW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.cross_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return Float4OpsKernelsSegment.cross_api(dest, destOffset, src, srcOffset, vX, vY, vZ, vW, wX, wY, wZ, wW);
    }

    /** {@link #cross(float[], int, float[], int, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long cross(long dest, long src, float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.cross_unsafe(dest, src, vX, vY, vZ, vW, wX, wY, wZ, wW);
        cross(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, vX, vY, vZ, vW, wX, wY, wZ, wW);
        return dest;
    }

    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param v the storage holding the second operand of the cross product
     * @param vOffset the element index in {@code v} at which the vector starts
     * @param w the storage holding the third operand of the cross product
     * @param wOffset the element index in {@code w} at which the vector starts
     * @return {@code dest}
     */
    public static float[] cross(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset, float[] w, int wOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        float _vw = v[vOffset + 3];
        float _wx = w[wOffset];
        float _wy = w[wOffset + 1];
        float _wz = w[wOffset + 2];
        float _ww = w[wOffset + 3];
        float _t12 = Math.fma(_vy, _wz, -(_vz * _wy));
        float _t13 = Math.fma(_vz, _ww, -(_vw * _wz));
        float _t14 = Math.fma(_vy, _ww, -(_vw * _wy));
        float _t15 = Math.fma(_vx, _wz, -(_vz * _wx));
        float _t16 = Math.fma(_vx, _ww, -(_vw * _wx));
        float _t17 = Math.fma(_vx, _wy, -(_vy * _wx));
        dest[destOffset] = Math.fma(_selfw, _t12, Math.fma(_selfy, _t13, -(_selfz * _t14)));
        dest[destOffset + 1] = Math.fma(-_selfw, _t15, Math.fma(_selfz, _t16, -(_selfx * _t13)));
        dest[destOffset + 2] = Math.fma(_selfw, _t17, Math.fma(_selfx, _t14, -(_selfy * _t16)));
        dest[destOffset + 3] = Math.fma(-_selfz, _t17, Math.fma(_selfy, _t15, -(_selfx * _t12)));
        return dest;
    }

    /** {@link #cross(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer cross(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset, java.nio.FloatBuffer w, int wOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder() && w.isDirect() && w.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.cross_unsafe(dest, destOffset, src, srcOffset, v, vOffset, w, wOffset);
        return Float4OpsKernelsTypedBuffer.cross_api(dest, destOffset, src, srcOffset, v, vOffset, w, wOffset);
    }

    /** {@link #cross(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer cross(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset, java.nio.ByteBuffer w, int wOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder() && w.isDirect() && w.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.cross_unsafe(dest, destOffset, src, srcOffset, v, vOffset, w, wOffset);
        return Float4OpsKernelsByteBuffer.cross_api(dest, destOffset, src, srcOffset, v, vOffset, w, wOffset);
    }

    /** {@link #cross(float[], int, float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment cross(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset, java.lang.foreign.MemorySegment w, long wOffset) {
        float[] _destArray, _srcArray, _vArray, _wArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_vArray = heapFloats(v, vOffset)) != null
                && (_wArray = heapFloats(w, wOffset)) != null) {
            cross(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _vArray, heapIndex(v, vOffset, 2), _wArray, heapIndex(w, wOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && v.isNative() && w.isNative()) return Float4OpsKernelsSegment.cross_unsafe(dest, destOffset, src, srcOffset, v, vOffset, w, wOffset);
        return Float4OpsKernelsSegment.cross_api(dest, destOffset, src, srcOffset, v, vOffset, w, wOffset);
    }

    /** {@link #cross(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long cross(long dest, long src, long v, long w) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.cross_unsafe(dest, src, v, w);
        cross(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(w, 16L), 0L);
        return dest;
    }

    /**
     * Compute the value converted from radians to degrees of each component of this vector and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] degrees(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.toDegrees(src[srcOffset]);
        dest[destOffset + 1] = Math.toDegrees(_selfy);
        dest[destOffset + 2] = Math.toDegrees(_selfz);
        dest[destOffset + 3] = Math.toDegrees(_selfw);
        return dest;
    }

    /** {@link #degrees(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer degrees(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.degrees_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.degrees_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #degrees(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer degrees(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.degrees_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.degrees_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #degrees(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment degrees(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            degrees(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.degrees_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.degrees_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #degrees(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long degrees(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.degrees_unsafe(dest, src);
        degrees(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the distance between this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the distance between this vector and {@code other}
     */
    public static float distance(float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _t0 = src[srcOffset + 3] - otherW;
        float _t1 = src[srcOffset + 2] - otherZ;
        float _t2 = src[srcOffset] - otherX;
        float _t3 = src[srcOffset + 1] - otherY;
        return (float) java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
    }

    /** {@link #distance(float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float distance(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.distance_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsTypedBuffer.distance_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #distance(float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float distance(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.distance_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsByteBuffer.distance_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #distance(float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float distance(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float[] _srcArray;
        if ((_srcArray = heapFloats(src, srcOffset)) != null) {
            return distance(_srcArray, heapIndex(src, srcOffset, 2), otherX, otherY, otherZ, otherW);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float4OpsKernelsSegment.distance_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsSegment.distance_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #distance(float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float distance(long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.distance_unsafe(src, otherX, otherY, otherZ, otherW);
        return distance(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW);
    }

    /**
     * Compute the distance between this vector and {@code other}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param other the storage holding the vector to measure the distance to
     * @param otherOffset the element index in {@code other} at which the vector starts
     * @return the distance between this vector and {@code other}
     */
    public static float distance(float[] src, int srcOffset, float[] other, int otherOffset) {
        float _t0 = src[srcOffset + 3] - other[otherOffset + 3];
        float _t1 = src[srcOffset + 2] - other[otherOffset + 2];
        float _t2 = src[srcOffset] - other[otherOffset];
        float _t3 = src[srcOffset + 1] - other[otherOffset + 1];
        return (float) java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3))));
    }

    /** {@link #distance(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float distance(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.distance_unsafe(src, srcOffset, other, otherOffset);
        return Float4OpsKernelsTypedBuffer.distance_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #distance(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float distance(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.distance_unsafe(src, srcOffset, other, otherOffset);
        return Float4OpsKernelsByteBuffer.distance_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #distance(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float distance(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float[] _srcArray, _otherArray;
        if ((_srcArray = heapFloats(src, srcOffset)) != null
                && (_otherArray = heapFloats(other, otherOffset)) != null) {
            return distance(_srcArray, heapIndex(src, srcOffset, 2), _otherArray, heapIndex(other, otherOffset, 2));
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative()) return Float4OpsKernelsSegment.distance_unsafe(src, srcOffset, other, otherOffset);
        return Float4OpsKernelsSegment.distance_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #distance(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float distance(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.distance_unsafe(src, other);
        return distance(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
    }

    /**
     * Compute the squared distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the squared distance between this vector and {@code other}
     */
    public static float distanceSquared(float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _t0 = src[srcOffset + 3] - otherW;
        float _t1 = src[srcOffset + 2] - otherZ;
        float _t2 = src[srcOffset] - otherX;
        float _t3 = src[srcOffset + 1] - otherY;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3)));
    }

    /** {@link #distanceSquared(float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float distanceSquared(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.distanceSquared_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsTypedBuffer.distanceSquared_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #distanceSquared(float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float distanceSquared(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.distanceSquared_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsByteBuffer.distanceSquared_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #distanceSquared(float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float distanceSquared(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float[] _srcArray;
        if ((_srcArray = heapFloats(src, srcOffset)) != null) {
            return distanceSquared(_srcArray, heapIndex(src, srcOffset, 2), otherX, otherY, otherZ, otherW);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float4OpsKernelsSegment.distanceSquared_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsSegment.distanceSquared_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #distanceSquared(float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float distanceSquared(long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.distanceSquared_unsafe(src, otherX, otherY, otherZ, otherW);
        return distanceSquared(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW);
    }

    /**
     * Compute the squared distance between this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param other the storage holding the vector to measure the distance to
     * @param otherOffset the element index in {@code other} at which the vector starts
     * @return the squared distance between this vector and {@code other}
     */
    public static float distanceSquared(float[] src, int srcOffset, float[] other, int otherOffset) {
        float _t0 = src[srcOffset + 3] - other[otherOffset + 3];
        float _t1 = src[srcOffset + 2] - other[otherOffset + 2];
        float _t2 = src[srcOffset] - other[otherOffset];
        float _t3 = src[srcOffset + 1] - other[otherOffset + 1];
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3)));
    }

    /** {@link #distanceSquared(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float distanceSquared(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.distanceSquared_unsafe(src, srcOffset, other, otherOffset);
        return Float4OpsKernelsTypedBuffer.distanceSquared_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #distanceSquared(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float distanceSquared(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.distanceSquared_unsafe(src, srcOffset, other, otherOffset);
        return Float4OpsKernelsByteBuffer.distanceSquared_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #distanceSquared(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float distanceSquared(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float[] _srcArray, _otherArray;
        if ((_srcArray = heapFloats(src, srcOffset)) != null
                && (_otherArray = heapFloats(other, otherOffset)) != null) {
            return distanceSquared(_srcArray, heapIndex(src, srcOffset, 2), _otherArray, heapIndex(other, otherOffset, 2));
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative()) return Float4OpsKernelsSegment.distanceSquared_unsafe(src, srcOffset, other, otherOffset);
        return Float4OpsKernelsSegment.distanceSquared_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #distanceSquared(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float distanceSquared(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.distanceSquared_unsafe(src, other);
        return distanceSquared(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
    }

    /**
     * Compute the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the dot product of this vector and {@code other}
     */
    public static float dot(float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        return Math.fma(otherW, src[srcOffset + 3], Math.fma(otherZ, src[srcOffset + 2], Math.fma(otherX, src[srcOffset], otherY * src[srcOffset + 1])));
    }

    /** {@link #dot(float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float dot(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.dot_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsTypedBuffer.dot_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #dot(float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float dot(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.dot_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsByteBuffer.dot_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #dot(float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float dot(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float[] _srcArray;
        if ((_srcArray = heapFloats(src, srcOffset)) != null) {
            return dot(_srcArray, heapIndex(src, srcOffset, 2), otherX, otherY, otherZ, otherW);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float4OpsKernelsSegment.dot_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsSegment.dot_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #dot(float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float dot(long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.dot_unsafe(src, otherX, otherY, otherZ, otherW);
        return dot(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW);
    }

    /**
     * Compute the dot product of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param other the storage holding the other operand of the dot product
     * @param otherOffset the element index in {@code other} at which the vector starts
     * @return the dot product of this vector and {@code other}
     */
    public static float dot(float[] src, int srcOffset, float[] other, int otherOffset) {
        return Math.fma(other[otherOffset + 3], src[srcOffset + 3], Math.fma(other[otherOffset + 2], src[srcOffset + 2], Math.fma(other[otherOffset], src[srcOffset], other[otherOffset + 1] * src[srcOffset + 1])));
    }

    /** {@link #dot(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float dot(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.dot_unsafe(src, srcOffset, other, otherOffset);
        return Float4OpsKernelsTypedBuffer.dot_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #dot(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float dot(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.dot_unsafe(src, srcOffset, other, otherOffset);
        return Float4OpsKernelsByteBuffer.dot_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #dot(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float dot(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float[] _srcArray, _otherArray;
        if ((_srcArray = heapFloats(src, srcOffset)) != null
                && (_otherArray = heapFloats(other, otherOffset)) != null) {
            return dot(_srcArray, heapIndex(src, srcOffset, 2), _otherArray, heapIndex(other, otherOffset, 2));
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative()) return Float4OpsKernelsSegment.dot_unsafe(src, srcOffset, other, otherOffset);
        return Float4OpsKernelsSegment.dot_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #dot(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float dot(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.dot_unsafe(src, other);
        return dot(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
    }

    /**
     * Compute the base-e exponential of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] exp(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.exp(src[srcOffset]);
        dest[destOffset + 1] = Math.exp(_selfy);
        dest[destOffset + 2] = Math.exp(_selfz);
        dest[destOffset + 3] = Math.exp(_selfw);
        return dest;
    }

    /** {@link #exp(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer exp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.exp_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.exp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #exp(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer exp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.exp_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.exp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #exp(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment exp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            exp(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.exp_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.exp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #exp(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long exp(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.exp_unsafe(dest, src);
        exp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the base-2 exponential of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] exp2(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.pow(2.0f, src[srcOffset]);
        dest[destOffset + 1] = Math.pow(2.0f, _selfy);
        dest[destOffset + 2] = Math.pow(2.0f, _selfz);
        dest[destOffset + 3] = Math.pow(2.0f, _selfw);
        return dest;
    }

    /** {@link #exp2(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer exp2(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.exp2_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.exp2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #exp2(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer exp2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.exp2_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.exp2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #exp2(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment exp2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            exp2(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.exp2_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.exp2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #exp2(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long exp2(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.exp2_unsafe(dest, src);
        exp2(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the base-e exponential minus one of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] expm1(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.expm1(src[srcOffset]);
        dest[destOffset + 1] = Math.expm1(_selfy);
        dest[destOffset + 2] = Math.expm1(_selfz);
        dest[destOffset + 3] = Math.expm1(_selfw);
        return dest;
    }

    /** {@link #expm1(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer expm1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.expm1_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.expm1_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #expm1(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer expm1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.expm1_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.expm1_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #expm1(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment expm1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            expm1(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.expm1_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.expm1_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #expm1(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long expm1(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.expm1_unsafe(dest, src);
        expm1(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Return this vector unchanged when {@code dot((NrefX, NrefY, NrefZ, NrefW), (IX, IY, IZ, IW))}
     * is negative, and negated otherwise - orienting it against the incident direction ({@code IX},
     * {@code IY}, {@code IZ}, {@code IW}) as judged by the reference vector ({@code NrefX},
     * {@code NrefY}, {@code NrefZ}, {@code NrefW}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param IX the {@code x} component of the vector {@code (IX, IY, IZ, IW)}
     * @param IY the {@code y} component of the vector {@code (IX, IY, IZ, IW)}
     * @param IZ the {@code z} component of the vector {@code (IX, IY, IZ, IW)}
     * @param IW the {@code w} component of the vector {@code (IX, IY, IZ, IW)}
     * @param NrefX the {@code x} component of the vector {@code (NrefX, NrefY, NrefZ, NrefW)}
     * @param NrefY the {@code y} component of the vector {@code (NrefX, NrefY, NrefZ, NrefW)}
     * @param NrefZ the {@code z} component of the vector {@code (NrefX, NrefY, NrefZ, NrefW)}
     * @param NrefW the {@code w} component of the vector {@code (NrefX, NrefY, NrefZ, NrefW)}
     * @return {@code dest}
     */
    public static float[] faceforward(float[] dest, int destOffset, float[] src, int srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.faceforward(dest, destOffset, src, srcOffset, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        return Float4OpsKernelsArray.faceforward_scalar(dest, destOffset, src, srcOffset, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
    }

    /** {@link #faceforward(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer faceforward(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.faceforward_unsafe(dest, destOffset, src, srcOffset, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        return Float4OpsKernelsTypedBuffer.faceforward_api(dest, destOffset, src, srcOffset, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
    }

    /** {@link #faceforward(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer faceforward(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.faceforward_unsafe(dest, destOffset, src, srcOffset, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        return Float4OpsKernelsByteBuffer.faceforward_api(dest, destOffset, src, srcOffset, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
    }

    /** {@link #faceforward(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment faceforward(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.faceforward(dest, destOffset, src, srcOffset, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            faceforward(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.faceforward_unsafe(dest, destOffset, src, srcOffset, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        return Float4OpsKernelsSegment.faceforward_api(dest, destOffset, src, srcOffset, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
    }

    /** {@link #faceforward(float[], int, float[], int, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long faceforward(long dest, long src, float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.faceforward_unsafe(dest, src, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        faceforward(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW);
        return dest;
    }

    /**
     * Return this vector unchanged when {@code dot(Nref, I)} is negative, and negated otherwise -
     * orienting it against the incident direction {@code I} as judged by the reference vector
     * {@code Nref} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param I the storage holding the incident direction
     * @param IOffset the element index in {@code I} at which the vector starts
     * @param Nref the storage holding the reference vector the incident direction is tested against
     * @param NrefOffset the element index in {@code Nref} at which the vector starts
     * @return {@code dest}
     */
    public static float[] faceforward(float[] dest, int destOffset, float[] src, int srcOffset, float[] I, int IOffset, float[] Nref, int NrefOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.faceforward(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        return Float4OpsKernelsArray.faceforward_scalar(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
    }

    /** {@link #faceforward(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer faceforward(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer I, int IOffset, java.nio.FloatBuffer Nref, int NrefOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && I.isDirect() && I.order() == java.nio.ByteOrder.nativeOrder() && Nref.isDirect() && Nref.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.faceforward_unsafe(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        return Float4OpsKernelsTypedBuffer.faceforward_api(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
    }

    /** {@link #faceforward(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer faceforward(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer I, int IOffset, java.nio.ByteBuffer Nref, int NrefOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && I.isDirect() && I.order() == java.nio.ByteOrder.nativeOrder() && Nref.isDirect() && Nref.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.faceforward_unsafe(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        return Float4OpsKernelsByteBuffer.faceforward_api(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
    }

    /** {@link #faceforward(float[], int, float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment faceforward(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment I, long IOffset, java.lang.foreign.MemorySegment Nref, long NrefOffset) {
        float[] _destArray, _srcArray, _IArray, _NrefArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_IArray = heapFloats(I, IOffset)) != null
                && (_NrefArray = heapFloats(Nref, NrefOffset)) != null) {
            faceforward(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _IArray, heapIndex(I, IOffset, 2), _NrefArray, heapIndex(Nref, NrefOffset, 2));
            return dest;
        }
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.faceforward(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && I.isNative() && Nref.isNative()) return Float4OpsKernelsSegment.faceforward_unsafe(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        return Float4OpsKernelsSegment.faceforward_api(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
    }

    /** {@link #faceforward(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long faceforward(long dest, long src, long I, long Nref) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.faceforward_unsafe(dest, src, I, Nref);
        faceforward(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(I, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(Nref, 16L), 0L);
        return dest;
    }

    /**
     * Compute the floor of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] floor(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.floor(src[srcOffset]);
        dest[destOffset + 1] = Math.floor(_selfy);
        dest[destOffset + 2] = Math.floor(_selfz);
        dest[destOffset + 3] = Math.floor(_selfw);
        return dest;
    }

    /** {@link #floor(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer floor(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.floor_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.floor_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #floor(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer floor(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.floor_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.floor_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #floor(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment floor(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            floor(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.floor_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.floor_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #floor(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long floor(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.floor_unsafe(dest, src);
        floor(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the fractional part of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] fract(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = java.lang.Math.min(_selfx - Math.floor(_selfx), 0.99999994f);
        dest[destOffset + 1] = java.lang.Math.min(_selfy - Math.floor(_selfy), 0.99999994f);
        dest[destOffset + 2] = java.lang.Math.min(_selfz - Math.floor(_selfz), 0.99999994f);
        dest[destOffset + 3] = java.lang.Math.min(_selfw - Math.floor(_selfw), 0.99999994f);
        return dest;
    }

    /** {@link #fract(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer fract(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.fract_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.fract_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #fract(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer fract(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.fract_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.fract_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #fract(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment fract(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            fract(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.fract_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.fract_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #fract(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long fract(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.fract_unsafe(dest, src);
        fract(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} {@code y} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param y the other operand
     * @return {@code dest}
     */
    public static float[] hypot(float[] dest, int destOffset, float[] src, int srcOffset, float y) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.hypot(src[srcOffset], y);
        dest[destOffset + 1] = Math.hypot(_selfy, y);
        dest[destOffset + 2] = Math.hypot(_selfz, y);
        dest[destOffset + 3] = Math.hypot(_selfw, y);
        return dest;
    }

    /** {@link #hypot(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer hypot(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.hypot_unsafe(dest, destOffset, src, srcOffset, y);
        return Float4OpsKernelsTypedBuffer.hypot_api(dest, destOffset, src, srcOffset, y);
    }

    /** {@link #hypot(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hypot(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.hypot_unsafe(dest, destOffset, src, srcOffset, y);
        return Float4OpsKernelsByteBuffer.hypot_api(dest, destOffset, src, srcOffset, y);
    }

    /** {@link #hypot(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment hypot(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            hypot(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), y);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.hypot_unsafe(dest, destOffset, src, srcOffset, y);
        return Float4OpsKernelsSegment.hypot_api(dest, destOffset, src, srcOffset, y);
    }

    /** {@link #hypot(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long hypot(long dest, long src, float y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.hypot_unsafe(dest, src, y);
        hypot(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, y);
        return dest;
    }

    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} the corresponding component of ({@code yX}, {@code yY},
     * {@code yZ}, {@code yW}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param yX the {@code x} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yY the {@code y} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yZ the {@code z} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yW the {@code w} component of the vector {@code (yX, yY, yZ, yW)}
     * @return {@code dest}
     */
    public static float[] hypot(float[] dest, int destOffset, float[] src, int srcOffset, float yX, float yY, float yZ, float yW) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.hypot(src[srcOffset], yX);
        dest[destOffset + 1] = Math.hypot(_selfy, yY);
        dest[destOffset + 2] = Math.hypot(_selfz, yZ);
        dest[destOffset + 3] = Math.hypot(_selfw, yW);
        return dest;
    }

    /** {@link #hypot(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer hypot(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ, float yW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.hypot_unsafe(dest, destOffset, src, srcOffset, yX, yY, yZ, yW);
        return Float4OpsKernelsTypedBuffer.hypot_api(dest, destOffset, src, srcOffset, yX, yY, yZ, yW);
    }

    /** {@link #hypot(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hypot(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float yX, float yY, float yZ, float yW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.hypot_unsafe(dest, destOffset, src, srcOffset, yX, yY, yZ, yW);
        return Float4OpsKernelsByteBuffer.hypot_api(dest, destOffset, src, srcOffset, yX, yY, yZ, yW);
    }

    /** {@link #hypot(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment hypot(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY, float yZ, float yW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            hypot(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), yX, yY, yZ, yW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.hypot_unsafe(dest, destOffset, src, srcOffset, yX, yY, yZ, yW);
        return Float4OpsKernelsSegment.hypot_api(dest, destOffset, src, srcOffset, yX, yY, yZ, yW);
    }

    /** {@link #hypot(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long hypot(long dest, long src, float yX, float yY, float yZ, float yW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.hypot_unsafe(dest, src, yX, yY, yZ, yW);
        hypot(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, yX, yY, yZ, yW);
        return dest;
    }

    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} the corresponding component of {@code y} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param y the storage holding the vector of other operands, one per component
     * @param yOffset the element index in {@code y} at which the vector starts
     * @return {@code dest}
     */
    public static float[] hypot(float[] dest, int destOffset, float[] src, int srcOffset, float[] y, int yOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _yy = y[yOffset + 1];
        float _yz = y[yOffset + 2];
        float _yw = y[yOffset + 3];
        dest[destOffset] = Math.hypot(src[srcOffset], y[yOffset]);
        dest[destOffset + 1] = Math.hypot(_selfy, _yy);
        dest[destOffset + 2] = Math.hypot(_selfz, _yz);
        dest[destOffset + 3] = Math.hypot(_selfw, _yw);
        return dest;
    }

    /** {@link #hypot(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer hypot(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && y.isDirect() && y.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.hypot_unsafe(dest, destOffset, src, srcOffset, y, yOffset);
        return Float4OpsKernelsTypedBuffer.hypot_api(dest, destOffset, src, srcOffset, y, yOffset);
    }

    /** {@link #hypot(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hypot(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && y.isDirect() && y.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.hypot_unsafe(dest, destOffset, src, srcOffset, y, yOffset);
        return Float4OpsKernelsByteBuffer.hypot_api(dest, destOffset, src, srcOffset, y, yOffset);
    }

    /** {@link #hypot(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment hypot(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        float[] _destArray, _srcArray, _yArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_yArray = heapFloats(y, yOffset)) != null) {
            hypot(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _yArray, heapIndex(y, yOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && y.isNative()) return Float4OpsKernelsSegment.hypot_unsafe(dest, destOffset, src, srcOffset, y, yOffset);
        return Float4OpsKernelsSegment.hypot_api(dest, destOffset, src, srcOffset, y, yOffset);
    }

    /** {@link #hypot(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long hypot(long dest, long src, long y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.hypot_unsafe(dest, src, y);
        hypot(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(y, 16L), 0L);
        return dest;
    }

    /**
     * Compute the reciprocal {@code 1 / x} of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must be non-zero.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] inverse(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.inverse(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsArray.inverse_scalar(dest, destOffset, src, srcOffset);
    }

    /** {@link #inverse(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer inverse(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.inverse_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.inverse_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #inverse(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer inverse(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.inverse_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.inverse_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #inverse(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment inverse(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.inverse(dest, destOffset, src, srcOffset);
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            inverse(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.inverse_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.inverse_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #inverse(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long inverse(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.inverse_unsafe(dest, src);
        inverse(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the inverse square root of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] inverseSqrt(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = (1.0f / (float) java.lang.Math.sqrt(src[srcOffset]));
        dest[destOffset + 1] = (1.0f / (float) java.lang.Math.sqrt(_selfy));
        dest[destOffset + 2] = (1.0f / (float) java.lang.Math.sqrt(_selfz));
        dest[destOffset + 3] = (1.0f / (float) java.lang.Math.sqrt(_selfw));
        return dest;
    }

    /** {@link #inverseSqrt(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer inverseSqrt(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.inverseSqrt_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.inverseSqrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #inverseSqrt(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer inverseSqrt(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.inverseSqrt_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.inverseSqrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #inverseSqrt(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment inverseSqrt(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            inverseSqrt(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.inverseSqrt_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.inverseSqrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #inverseSqrt(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long inverseSqrt(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.inverseSqrt_unsafe(dest, src);
        inverseSqrt(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the length of this vector.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return the length of this vector
     */
    public static float length(float[] src, int srcOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        return (float) java.lang.Math.sqrt(Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy))));
    }

    /** {@link #length(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float length(java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.length_unsafe(src, srcOffset);
        return Float4OpsKernelsTypedBuffer.length_api(src, srcOffset);
    }

    /** {@link #length(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float length(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.length_unsafe(src, srcOffset);
        return Float4OpsKernelsByteBuffer.length_api(src, srcOffset);
    }

    /** {@link #length(float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float length(java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _srcArray;
        if ((_srcArray = heapFloats(src, srcOffset)) != null) {
            return length(_srcArray, heapIndex(src, srcOffset, 2));
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float4OpsKernelsSegment.length_unsafe(src, srcOffset);
        return Float4OpsKernelsSegment.length_api(src, srcOffset);
    }

    /** {@link #length(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float length(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.length_unsafe(src);
        return length(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
    }

    /**
     * Compute the squared length of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return the squared length of this vector
     */
    public static float lengthSquared(float[] src, int srcOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        return Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
    }

    /** {@link #lengthSquared(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float lengthSquared(java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.lengthSquared_unsafe(src, srcOffset);
        return Float4OpsKernelsTypedBuffer.lengthSquared_api(src, srcOffset);
    }

    /** {@link #lengthSquared(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float lengthSquared(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.lengthSquared_unsafe(src, srcOffset);
        return Float4OpsKernelsByteBuffer.lengthSquared_api(src, srcOffset);
    }

    /** {@link #lengthSquared(float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float lengthSquared(java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _srcArray;
        if ((_srcArray = heapFloats(src, srcOffset)) != null) {
            return lengthSquared(_srcArray, heapIndex(src, srcOffset, 2));
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float4OpsKernelsSegment.lengthSquared_unsafe(src, srcOffset);
        return Float4OpsKernelsSegment.lengthSquared_api(src, srcOffset);
    }

    /** {@link #lengthSquared(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float lengthSquared(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.lengthSquared_unsafe(src);
        return lengthSquared(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
    }

    /**
     * Compute the natural logarithm of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] log(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.log(src[srcOffset]);
        dest[destOffset + 1] = Math.log(_selfy);
        dest[destOffset + 2] = Math.log(_selfz);
        dest[destOffset + 3] = Math.log(_selfw);
        return dest;
    }

    /** {@link #log(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer log(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.log_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.log_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer log(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.log_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.log_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment log(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            log(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.log_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.log_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long log(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.log_unsafe(dest, src);
        log(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the base-10 logarithm of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] log10(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.log10(src[srcOffset]);
        dest[destOffset + 1] = Math.log10(_selfy);
        dest[destOffset + 2] = Math.log10(_selfz);
        dest[destOffset + 3] = Math.log10(_selfw);
        return dest;
    }

    /** {@link #log10(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer log10(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.log10_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.log10_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log10(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer log10(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.log10_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.log10_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log10(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment log10(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            log10(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.log10_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.log10_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log10(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long log10(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.log10_unsafe(dest, src);
        log10(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the natural logarithm of one plus the value of each component of this vector and
     * store the result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must lie in {@code (-1, Infinity)}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] log1p(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.log1p(src[srcOffset]);
        dest[destOffset + 1] = Math.log1p(_selfy);
        dest[destOffset + 2] = Math.log1p(_selfz);
        dest[destOffset + 3] = Math.log1p(_selfw);
        return dest;
    }

    /** {@link #log1p(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer log1p(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.log1p_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.log1p_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log1p(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer log1p(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.log1p_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.log1p_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log1p(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment log1p(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            log1p(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.log1p_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.log1p_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log1p(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long log1p(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.log1p_unsafe(dest, src);
        log1p(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the base-2 logarithm of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] log2(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.log2(src[srcOffset]);
        dest[destOffset + 1] = Math.log2(_selfy);
        dest[destOffset + 2] = Math.log2(_selfz);
        dest[destOffset + 3] = Math.log2(_selfw);
        return dest;
    }

    /** {@link #log2(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer log2(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.log2_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.log2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log2(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer log2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.log2_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.log2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log2(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment log2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            log2(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.log2_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.log2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log2(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long log2(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.log2_unsafe(dest, src);
        log2(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return the Manhattan distance between this vector and {@code other}
     */
    public static float manhattanDistance(float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        return java.lang.Math.abs(src[srcOffset] - otherX) + java.lang.Math.abs(src[srcOffset + 1] - otherY) + java.lang.Math.abs(src[srcOffset + 2] - otherZ) + java.lang.Math.abs(src[srcOffset + 3] - otherW);
    }

    /** {@link #manhattanDistance(float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float manhattanDistance(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.manhattanDistance_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsTypedBuffer.manhattanDistance_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #manhattanDistance(float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float manhattanDistance(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.manhattanDistance_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsByteBuffer.manhattanDistance_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #manhattanDistance(float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float manhattanDistance(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float[] _srcArray;
        if ((_srcArray = heapFloats(src, srcOffset)) != null) {
            return manhattanDistance(_srcArray, heapIndex(src, srcOffset, 2), otherX, otherY, otherZ, otherW);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float4OpsKernelsSegment.manhattanDistance_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsSegment.manhattanDistance_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #manhattanDistance(float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float manhattanDistance(long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.manhattanDistance_unsafe(src, otherX, otherY, otherZ, otherW);
        return manhattanDistance(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW);
    }

    /**
     * Compute the Manhattan distance between this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param other the storage holding the vector to measure the distance to
     * @param otherOffset the element index in {@code other} at which the vector starts
     * @return the Manhattan distance between this vector and {@code other}
     */
    public static float manhattanDistance(float[] src, int srcOffset, float[] other, int otherOffset) {
        return java.lang.Math.abs(src[srcOffset] - other[otherOffset]) + java.lang.Math.abs(src[srcOffset + 1] - other[otherOffset + 1]) + java.lang.Math.abs(src[srcOffset + 2] - other[otherOffset + 2]) + java.lang.Math.abs(src[srcOffset + 3] - other[otherOffset + 3]);
    }

    /** {@link #manhattanDistance(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float manhattanDistance(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.manhattanDistance_unsafe(src, srcOffset, other, otherOffset);
        return Float4OpsKernelsTypedBuffer.manhattanDistance_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #manhattanDistance(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float manhattanDistance(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.manhattanDistance_unsafe(src, srcOffset, other, otherOffset);
        return Float4OpsKernelsByteBuffer.manhattanDistance_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #manhattanDistance(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float manhattanDistance(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        float[] _srcArray, _otherArray;
        if ((_srcArray = heapFloats(src, srcOffset)) != null
                && (_otherArray = heapFloats(other, otherOffset)) != null) {
            return manhattanDistance(_srcArray, heapIndex(src, srcOffset, 2), _otherArray, heapIndex(other, otherOffset, 2));
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative()) return Float4OpsKernelsSegment.manhattanDistance_unsafe(src, srcOffset, other, otherOffset);
        return Float4OpsKernelsSegment.manhattanDistance_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #manhattanDistance(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float manhattanDistance(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.manhattanDistance_unsafe(src, other);
        return manhattanDistance(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
    }

    /**
     * Compute the Manhattan length (sum of the absolute components) of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return the Manhattan length (sum of the absolute components) of this vector
     */
    public static float manhattanLength(float[] src, int srcOffset) {
        return java.lang.Math.abs(src[srcOffset]) + java.lang.Math.abs(src[srcOffset + 1]) + java.lang.Math.abs(src[srcOffset + 2]) + java.lang.Math.abs(src[srcOffset + 3]);
    }

    /** {@link #manhattanLength(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float manhattanLength(java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.manhattanLength_unsafe(src, srcOffset);
        return Float4OpsKernelsTypedBuffer.manhattanLength_api(src, srcOffset);
    }

    /** {@link #manhattanLength(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float manhattanLength(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.manhattanLength_unsafe(src, srcOffset);
        return Float4OpsKernelsByteBuffer.manhattanLength_api(src, srcOffset);
    }

    /** {@link #manhattanLength(float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float manhattanLength(java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _srcArray;
        if ((_srcArray = heapFloats(src, srcOffset)) != null) {
            return manhattanLength(_srcArray, heapIndex(src, srcOffset, 2));
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return Float4OpsKernelsSegment.manhattanLength_unsafe(src, srcOffset);
        return Float4OpsKernelsSegment.manhattanLength_api(src, srcOffset);
    }

    /** {@link #manhattanLength(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float manhattanLength(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.manhattanLength_unsafe(src);
        return manhattanLength(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
    }

    /**
     * Set each component of this vector to the larger of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param scalar the value to take the component-wise maximum with
     * @return {@code dest}
     */
    public static float[] max(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.max(dest, destOffset, src, srcOffset, scalar);
        return Float4OpsKernelsArray.max_scalar(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #max(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer max(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.max_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float4OpsKernelsTypedBuffer.max_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #max(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer max(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.max_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float4OpsKernelsByteBuffer.max_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #max(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment max(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.max(dest, destOffset, src, srcOffset, scalar);
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            max(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), scalar);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.max_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float4OpsKernelsSegment.max_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #max(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long max(long dest, long src, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.max_unsafe(dest, src, scalar);
        max(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, scalar);
        return dest;
    }

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return {@code dest}
     */
    public static float[] max(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = java.lang.Math.max(src[srcOffset], otherX);
        dest[destOffset + 1] = java.lang.Math.max(_selfy, otherY);
        dest[destOffset + 2] = java.lang.Math.max(_selfz, otherZ);
        dest[destOffset + 3] = java.lang.Math.max(_selfw, otherW);
        return dest;
    }

    /** {@link #max(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer max(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.max_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsTypedBuffer.max_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #max(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer max(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.max_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsByteBuffer.max_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #max(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment max(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            max(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), otherX, otherY, otherZ, otherW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.max_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsSegment.max_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #max(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long max(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.max_unsafe(dest, src, otherX, otherY, otherZ, otherW);
        max(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW);
        return dest;
    }

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param other the storage holding the vector to take the component-wise maximum with
     * @param otherOffset the element index in {@code other} at which the vector starts
     * @return {@code dest}
     */
    public static float[] max(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.max(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsArray.max_scalar(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #max(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer max(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.max_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsTypedBuffer.max_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #max(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer max(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.max_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsByteBuffer.max_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #max(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment max(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.max(dest, destOffset, src, srcOffset, other, otherOffset);
        float[] _destArray, _srcArray, _otherArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_otherArray = heapFloats(other, otherOffset)) != null) {
            max(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _otherArray, heapIndex(other, otherOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Float4OpsKernelsSegment.max_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsSegment.max_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #max(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long max(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.max_unsafe(dest, src, other);
        max(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
        return dest;
    }

    /**
     * Set each component of this vector to the smaller of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param scalar the value to take the component-wise minimum with
     * @return {@code dest}
     */
    public static float[] min(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.min(dest, destOffset, src, srcOffset, scalar);
        return Float4OpsKernelsArray.min_scalar(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #min(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer min(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.min_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float4OpsKernelsTypedBuffer.min_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #min(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer min(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.min_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float4OpsKernelsByteBuffer.min_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #min(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment min(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.min(dest, destOffset, src, srcOffset, scalar);
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            min(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), scalar);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.min_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float4OpsKernelsSegment.min_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #min(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long min(long dest, long src, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.min_unsafe(dest, src, scalar);
        min(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, scalar);
        return dest;
    }

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @return {@code dest}
     */
    public static float[] min(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = java.lang.Math.min(src[srcOffset], otherX);
        dest[destOffset + 1] = java.lang.Math.min(_selfy, otherY);
        dest[destOffset + 2] = java.lang.Math.min(_selfz, otherZ);
        dest[destOffset + 3] = java.lang.Math.min(_selfw, otherW);
        return dest;
    }

    /** {@link #min(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer min(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.min_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsTypedBuffer.min_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #min(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer min(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.min_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsByteBuffer.min_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #min(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment min(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            min(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), otherX, otherY, otherZ, otherW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.min_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return Float4OpsKernelsSegment.min_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #min(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long min(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.min_unsafe(dest, src, otherX, otherY, otherZ, otherW);
        min(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW);
        return dest;
    }

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param other the storage holding the vector to take the component-wise minimum with
     * @param otherOffset the element index in {@code other} at which the vector starts
     * @return {@code dest}
     */
    public static float[] min(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.min(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsArray.min_scalar(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #min(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer min(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.min_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsTypedBuffer.min_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #min(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer min(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.min_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsByteBuffer.min_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #min(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment min(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.min(dest, destOffset, src, srcOffset, other, otherOffset);
        float[] _destArray, _srcArray, _otherArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_otherArray = heapFloats(other, otherOffset)) != null) {
            min(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _otherArray, heapIndex(other, otherOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return Float4OpsKernelsSegment.min_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float4OpsKernelsSegment.min_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #min(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long min(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.min_unsafe(dest, src, other);
        min(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
        return dest;
    }

    /**
     * Compute the component-wise floored modulo of this vector divided by {@code y} ({@code x % y},
     * plus {@code y} when that remainder is non-zero and its sign differs from {@code y}'s -
     * exactly Kotlin's {@code mod}) and store the result in {@code dest}.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * Valid input: {@code y} must be non-zero.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param y the divisor
     * @return {@code dest}
     */
    public static float[] mod(float[] dest, int destOffset, float[] src, int srcOffset, float y) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = flooredMod(_selfx, y);
        dest[destOffset + 1] = flooredMod(_selfy, y);
        dest[destOffset + 2] = flooredMod(_selfz, y);
        dest[destOffset + 3] = flooredMod(_selfw, y);
        return dest;
    }

    /** {@link #mod(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mod(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.mod_unsafe(dest, destOffset, src, srcOffset, y);
        return Float4OpsKernelsTypedBuffer.mod_api(dest, destOffset, src, srcOffset, y);
    }

    /** {@link #mod(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mod(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.mod_unsafe(dest, destOffset, src, srcOffset, y);
        return Float4OpsKernelsByteBuffer.mod_api(dest, destOffset, src, srcOffset, y);
    }

    /** {@link #mod(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment mod(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float y) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            mod(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), y);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.mod_unsafe(dest, destOffset, src, srcOffset, y);
        return Float4OpsKernelsSegment.mod_api(dest, destOffset, src, srcOffset, y);
    }

    /** {@link #mod(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mod(long dest, long src, float y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.mod_unsafe(dest, src, y);
        mod(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, y);
        return dest;
    }

    /**
     * Compute the component-wise floored modulo of this vector divided by ({@code yX}, {@code yY},
     * {@code yZ}, {@code yW}) ({@code x % y}, plus {@code y} when that remainder is non-zero and
     * its sign differs from {@code y}'s - exactly Kotlin's {@code mod}) and store the result in
     * {@code dest}.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * Valid input: each component of {@code (yX, yY, yZ, yW)} must be non-zero.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param yX the {@code x} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yY the {@code y} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yZ the {@code z} component of the vector {@code (yX, yY, yZ, yW)}
     * @param yW the {@code w} component of the vector {@code (yX, yY, yZ, yW)}
     * @return {@code dest}
     */
    public static float[] mod(float[] dest, int destOffset, float[] src, int srcOffset, float yX, float yY, float yZ, float yW) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = flooredMod(_selfx, yX);
        dest[destOffset + 1] = flooredMod(_selfy, yY);
        dest[destOffset + 2] = flooredMod(_selfz, yZ);
        dest[destOffset + 3] = flooredMod(_selfw, yW);
        return dest;
    }

    /** {@link #mod(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mod(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ, float yW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.mod_unsafe(dest, destOffset, src, srcOffset, yX, yY, yZ, yW);
        return Float4OpsKernelsTypedBuffer.mod_api(dest, destOffset, src, srcOffset, yX, yY, yZ, yW);
    }

    /** {@link #mod(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mod(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float yX, float yY, float yZ, float yW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.mod_unsafe(dest, destOffset, src, srcOffset, yX, yY, yZ, yW);
        return Float4OpsKernelsByteBuffer.mod_api(dest, destOffset, src, srcOffset, yX, yY, yZ, yW);
    }

    /** {@link #mod(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment mod(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float yX, float yY, float yZ, float yW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            mod(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), yX, yY, yZ, yW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.mod_unsafe(dest, destOffset, src, srcOffset, yX, yY, yZ, yW);
        return Float4OpsKernelsSegment.mod_api(dest, destOffset, src, srcOffset, yX, yY, yZ, yW);
    }

    /** {@link #mod(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mod(long dest, long src, float yX, float yY, float yZ, float yW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.mod_unsafe(dest, src, yX, yY, yZ, yW);
        mod(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, yX, yY, yZ, yW);
        return dest;
    }

    /**
     * Compute the component-wise floored modulo of this vector divided by {@code y} ({@code x % y},
     * plus {@code y} when that remainder is non-zero and its sign differs from {@code y}'s -
     * exactly Kotlin's {@code mod}) and store the result in {@code dest}.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * Valid input: each component of {@code y} must be non-zero.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param y the storage holding the vector of divisors, one per component
     * @param yOffset the element index in {@code y} at which the vector starts
     * @return {@code dest}
     */
    public static float[] mod(float[] dest, int destOffset, float[] src, int srcOffset, float[] y, int yOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _yx = y[yOffset];
        float _yy = y[yOffset + 1];
        float _yz = y[yOffset + 2];
        float _yw = y[yOffset + 3];
        dest[destOffset] = flooredMod(_selfx, _yx);
        dest[destOffset + 1] = flooredMod(_selfy, _yy);
        dest[destOffset + 2] = flooredMod(_selfz, _yz);
        dest[destOffset + 3] = flooredMod(_selfw, _yw);
        return dest;
    }

    /** {@link #mod(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mod(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && y.isDirect() && y.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.mod_unsafe(dest, destOffset, src, srcOffset, y, yOffset);
        return Float4OpsKernelsTypedBuffer.mod_api(dest, destOffset, src, srcOffset, y, yOffset);
    }

    /** {@link #mod(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mod(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && y.isDirect() && y.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.mod_unsafe(dest, destOffset, src, srcOffset, y, yOffset);
        return Float4OpsKernelsByteBuffer.mod_api(dest, destOffset, src, srcOffset, y, yOffset);
    }

    /** {@link #mod(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment mod(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment y, long yOffset) {
        float[] _destArray, _srcArray, _yArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_yArray = heapFloats(y, yOffset)) != null) {
            mod(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _yArray, heapIndex(y, yOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && y.isNative()) return Float4OpsKernelsSegment.mod_unsafe(dest, destOffset, src, srcOffset, y, yOffset);
        return Float4OpsKernelsSegment.mod_api(dest, destOffset, src, srcOffset, y, yOffset);
    }

    /** {@link #mod(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mod(long dest, long src, long y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.mod_unsafe(dest, src, y);
        mod(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(y, 16L), 0L);
        return dest;
    }

    /**
     * Compute the next representable value toward negative infinity of each component of this
     * vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] nextDown(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.nextDown(src[srcOffset]);
        dest[destOffset + 1] = Math.nextDown(_selfy);
        dest[destOffset + 2] = Math.nextDown(_selfz);
        dest[destOffset + 3] = Math.nextDown(_selfw);
        return dest;
    }

    /** {@link #nextDown(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer nextDown(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.nextDown_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.nextDown_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #nextDown(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer nextDown(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.nextDown_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.nextDown_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #nextDown(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment nextDown(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            nextDown(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.nextDown_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.nextDown_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #nextDown(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long nextDown(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.nextDown_unsafe(dest, src);
        nextDown(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the next representable value toward positive infinity of each component of this
     * vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] nextUp(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.nextUp(src[srcOffset]);
        dest[destOffset + 1] = Math.nextUp(_selfy);
        dest[destOffset + 2] = Math.nextUp(_selfz);
        dest[destOffset + 3] = Math.nextUp(_selfw);
        return dest;
    }

    /** {@link #nextUp(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer nextUp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.nextUp_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.nextUp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #nextUp(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer nextUp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.nextUp_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.nextUp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #nextUp(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment nextUp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            nextUp(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.nextUp_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.nextUp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #nextUp(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long nextUp(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.nextUp_unsafe(dest, src);
        nextUp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Normalize this vector to unit length (the zero vector yields the zero vector) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] normalize(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.normalize(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsArray.normalize_scalar(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalize(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer normalize(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.normalize_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.normalize_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalize(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer normalize(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.normalize_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.normalize_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalize(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment normalize(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.normalize(dest, destOffset, src, srcOffset);
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            normalize(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.normalize_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.normalize_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalize(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long normalize(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.normalize_unsafe(dest, src);
        normalize(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Normalize this vector and multiply the result by {@code length}, i.e. rescale it to that
     * length (the zero vector yields the zero vector) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param length the length to rescale to
     * @return {@code dest}
     */
    public static float[] normalizeMul(float[] dest, int destOffset, float[] src, int srcOffset, float length) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.normalizeMul(dest, destOffset, src, srcOffset, length);
        return Float4OpsKernelsArray.normalizeMul_scalar(dest, destOffset, src, srcOffset, length);
    }

    /** {@link #normalizeMul(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer normalizeMul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float length) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.normalizeMul_unsafe(dest, destOffset, src, srcOffset, length);
        return Float4OpsKernelsTypedBuffer.normalizeMul_api(dest, destOffset, src, srcOffset, length);
    }

    /** {@link #normalizeMul(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer normalizeMul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float length) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.normalizeMul_unsafe(dest, destOffset, src, srcOffset, length);
        return Float4OpsKernelsByteBuffer.normalizeMul_api(dest, destOffset, src, srcOffset, length);
    }

    /** {@link #normalizeMul(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment normalizeMul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float length) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            normalizeMul(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), length);
            return dest;
        }
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.normalizeMul(dest, destOffset, src, srcOffset, length);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.normalizeMul_unsafe(dest, destOffset, src, srcOffset, length);
        return Float4OpsKernelsSegment.normalizeMul_api(dest, destOffset, src, srcOffset, length);
    }

    /** {@link #normalizeMul(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long normalizeMul(long dest, long src, float length) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.normalizeMul_unsafe(dest, src, length);
        normalizeMul(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, length);
        return dest;
    }

    /**
     * Compute the outer product of this vector and ({@code rowX}, {@code rowY}, {@code rowZ},
     * {@code rowW}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param rowX the {@code x} component of the vector {@code (rowX, rowY, rowZ, rowW)}
     * @param rowY the {@code y} component of the vector {@code (rowX, rowY, rowZ, rowW)}
     * @param rowZ the {@code z} component of the vector {@code (rowX, rowY, rowZ, rowW)}
     * @param rowW the {@code w} component of the vector {@code (rowX, rowY, rowZ, rowW)}
     * @return {@code dest}
     */
    public static float[] outerProduct(float[] dest, int destOffset, float[] src, int srcOffset, float rowX, float rowY, float rowZ, float rowW) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.outerProduct(dest, destOffset, src, srcOffset, rowX, rowY, rowZ, rowW);
        return Float4OpsKernelsArray.outerProduct_scalar(dest, destOffset, src, srcOffset, rowX, rowY, rowZ, rowW);
    }

    /** {@link #outerProduct(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer outerProduct(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float rowX, float rowY, float rowZ, float rowW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.outerProduct_unsafe(dest, destOffset, src, srcOffset, rowX, rowY, rowZ, rowW);
        return Float4OpsKernelsTypedBuffer.outerProduct_api(dest, destOffset, src, srcOffset, rowX, rowY, rowZ, rowW);
    }

    /** {@link #outerProduct(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer outerProduct(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float rowX, float rowY, float rowZ, float rowW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.outerProduct_unsafe(dest, destOffset, src, srcOffset, rowX, rowY, rowZ, rowW);
        return Float4OpsKernelsByteBuffer.outerProduct_api(dest, destOffset, src, srcOffset, rowX, rowY, rowZ, rowW);
    }

    /** {@link #outerProduct(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment outerProduct(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float rowX, float rowY, float rowZ, float rowW) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.outerProduct(dest, destOffset, src, srcOffset, rowX, rowY, rowZ, rowW);
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            outerProduct(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), rowX, rowY, rowZ, rowW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.outerProduct_unsafe(dest, destOffset, src, srcOffset, rowX, rowY, rowZ, rowW);
        return Float4OpsKernelsSegment.outerProduct_api(dest, destOffset, src, srcOffset, rowX, rowY, rowZ, rowW);
    }

    /** {@link #outerProduct(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long outerProduct(long dest, long src, float rowX, float rowY, float rowZ, float rowW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.outerProduct_unsafe(dest, src, rowX, rowY, rowZ, rowW);
        outerProduct(VirtualMemoryHolder.virtualMemory().asSlice(dest, 64L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, rowX, rowY, rowZ, rowW);
        return dest;
    }

    /**
     * Compute the outer product of this vector and {@code row} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param row the storage holding the row vector (right operand)
     * @param rowOffset the element index in {@code row} at which the vector starts
     * @return {@code dest}
     */
    public static float[] outerProduct(float[] dest, int destOffset, float[] src, int srcOffset, float[] row, int rowOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.outerProduct(dest, destOffset, src, srcOffset, row, rowOffset);
        return Float4OpsKernelsArray.outerProduct_scalar(dest, destOffset, src, srcOffset, row, rowOffset);
    }

    /** {@link #outerProduct(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer outerProduct(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer row, int rowOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && row.isDirect() && row.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.outerProduct_unsafe(dest, destOffset, src, srcOffset, row, rowOffset);
        return Float4OpsKernelsTypedBuffer.outerProduct_api(dest, destOffset, src, srcOffset, row, rowOffset);
    }

    /** {@link #outerProduct(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer outerProduct(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer row, int rowOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && row.isDirect() && row.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.outerProduct_unsafe(dest, destOffset, src, srcOffset, row, rowOffset);
        return Float4OpsKernelsByteBuffer.outerProduct_api(dest, destOffset, src, srcOffset, row, rowOffset);
    }

    /** {@link #outerProduct(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment outerProduct(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment row, long rowOffset) {
        float[] _destArray, _srcArray, _rowArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_rowArray = heapFloats(row, rowOffset)) != null) {
            outerProduct(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _rowArray, heapIndex(row, rowOffset, 2));
            return dest;
        }
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.outerProduct(dest, destOffset, src, srcOffset, row, rowOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && row.isNative()) return Float4OpsKernelsSegment.outerProduct_unsafe(dest, destOffset, src, srcOffset, row, rowOffset);
        return Float4OpsKernelsSegment.outerProduct_api(dest, destOffset, src, srcOffset, row, rowOffset);
    }

    /** {@link #outerProduct(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long outerProduct(long dest, long src, long row) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.outerProduct_unsafe(dest, src, row);
        outerProduct(VirtualMemoryHolder.virtualMemory().asSlice(dest, 64L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(row, 16L), 0L);
        return dest;
    }

    /**
     * Raise each component of this vector to the power of {@code exponent} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param exponent the exponent
     * @return {@code dest}
     */
    public static float[] pow(float[] dest, int destOffset, float[] src, int srcOffset, float exponent) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.pow(src[srcOffset], exponent);
        dest[destOffset + 1] = Math.pow(_selfy, exponent);
        dest[destOffset + 2] = Math.pow(_selfz, exponent);
        dest[destOffset + 3] = Math.pow(_selfw, exponent);
        return dest;
    }

    /** {@link #pow(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer pow(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponent) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.pow_unsafe(dest, destOffset, src, srcOffset, exponent);
        return Float4OpsKernelsTypedBuffer.pow_api(dest, destOffset, src, srcOffset, exponent);
    }

    /** {@link #pow(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer pow(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float exponent) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.pow_unsafe(dest, destOffset, src, srcOffset, exponent);
        return Float4OpsKernelsByteBuffer.pow_api(dest, destOffset, src, srcOffset, exponent);
    }

    /** {@link #pow(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment pow(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float exponent) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            pow(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), exponent);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.pow_unsafe(dest, destOffset, src, srcOffset, exponent);
        return Float4OpsKernelsSegment.pow_api(dest, destOffset, src, srcOffset, exponent);
    }

    /** {@link #pow(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long pow(long dest, long src, float exponent) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.pow_unsafe(dest, src, exponent);
        pow(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, exponent);
        return dest;
    }

    /**
     * Raise each component of this vector to the power of ({@code exponentX}, {@code exponentY},
     * {@code exponentZ}, {@code exponentW}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param exponentX the {@code x} component of the vector
     *        {@code (exponentX, exponentY, exponentZ, exponentW)}
     * @param exponentY the {@code y} component of the vector
     *        {@code (exponentX, exponentY, exponentZ, exponentW)}
     * @param exponentZ the {@code z} component of the vector
     *        {@code (exponentX, exponentY, exponentZ, exponentW)}
     * @param exponentW the {@code w} component of the vector
     *        {@code (exponentX, exponentY, exponentZ, exponentW)}
     * @return {@code dest}
     */
    public static float[] pow(float[] dest, int destOffset, float[] src, int srcOffset, float exponentX, float exponentY, float exponentZ, float exponentW) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.pow(src[srcOffset], exponentX);
        dest[destOffset + 1] = Math.pow(_selfy, exponentY);
        dest[destOffset + 2] = Math.pow(_selfz, exponentZ);
        dest[destOffset + 3] = Math.pow(_selfw, exponentW);
        return dest;
    }

    /** {@link #pow(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer pow(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponentX, float exponentY, float exponentZ, float exponentW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.pow_unsafe(dest, destOffset, src, srcOffset, exponentX, exponentY, exponentZ, exponentW);
        return Float4OpsKernelsTypedBuffer.pow_api(dest, destOffset, src, srcOffset, exponentX, exponentY, exponentZ, exponentW);
    }

    /** {@link #pow(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer pow(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float exponentX, float exponentY, float exponentZ, float exponentW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.pow_unsafe(dest, destOffset, src, srcOffset, exponentX, exponentY, exponentZ, exponentW);
        return Float4OpsKernelsByteBuffer.pow_api(dest, destOffset, src, srcOffset, exponentX, exponentY, exponentZ, exponentW);
    }

    /** {@link #pow(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment pow(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float exponentX, float exponentY, float exponentZ, float exponentW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            pow(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), exponentX, exponentY, exponentZ, exponentW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.pow_unsafe(dest, destOffset, src, srcOffset, exponentX, exponentY, exponentZ, exponentW);
        return Float4OpsKernelsSegment.pow_api(dest, destOffset, src, srcOffset, exponentX, exponentY, exponentZ, exponentW);
    }

    /** {@link #pow(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long pow(long dest, long src, float exponentX, float exponentY, float exponentZ, float exponentW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.pow_unsafe(dest, src, exponentX, exponentY, exponentZ, exponentW);
        pow(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, exponentX, exponentY, exponentZ, exponentW);
        return dest;
    }

    /**
     * Raise each component of this vector to the power of {@code exponent} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param exponent the storage holding the exponent
     * @param exponentOffset the element index in {@code exponent} at which the vector starts
     * @return {@code dest}
     */
    public static float[] pow(float[] dest, int destOffset, float[] src, int srcOffset, float[] exponent, int exponentOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _exponenty = exponent[exponentOffset + 1];
        float _exponentz = exponent[exponentOffset + 2];
        float _exponentw = exponent[exponentOffset + 3];
        dest[destOffset] = Math.pow(src[srcOffset], exponent[exponentOffset]);
        dest[destOffset + 1] = Math.pow(_selfy, _exponenty);
        dest[destOffset + 2] = Math.pow(_selfz, _exponentz);
        dest[destOffset + 3] = Math.pow(_selfw, _exponentw);
        return dest;
    }

    /** {@link #pow(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer pow(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer exponent, int exponentOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && exponent.isDirect() && exponent.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.pow_unsafe(dest, destOffset, src, srcOffset, exponent, exponentOffset);
        return Float4OpsKernelsTypedBuffer.pow_api(dest, destOffset, src, srcOffset, exponent, exponentOffset);
    }

    /** {@link #pow(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer pow(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer exponent, int exponentOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && exponent.isDirect() && exponent.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.pow_unsafe(dest, destOffset, src, srcOffset, exponent, exponentOffset);
        return Float4OpsKernelsByteBuffer.pow_api(dest, destOffset, src, srcOffset, exponent, exponentOffset);
    }

    /** {@link #pow(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment pow(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment exponent, long exponentOffset) {
        float[] _destArray, _srcArray, _exponentArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_exponentArray = heapFloats(exponent, exponentOffset)) != null) {
            pow(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _exponentArray, heapIndex(exponent, exponentOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && exponent.isNative()) return Float4OpsKernelsSegment.pow_unsafe(dest, destOffset, src, srcOffset, exponent, exponentOffset);
        return Float4OpsKernelsSegment.pow_api(dest, destOffset, src, srcOffset, exponent, exponentOffset);
    }

    /** {@link #pow(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long pow(long dest, long src, long exponent) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.pow_unsafe(dest, src, exponent);
        pow(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(exponent, 16L), 0L);
        return dest;
    }

    /**
     * Project this vector onto ({@code ontoX}, {@code ontoY}, {@code ontoZ}, {@code ontoW}) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: {@code (ontoX, ontoY, ontoZ, ontoW)} must be non-zero.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param ontoX the {@code x} component of the vector {@code (ontoX, ontoY, ontoZ, ontoW)}
     * @param ontoY the {@code y} component of the vector {@code (ontoX, ontoY, ontoZ, ontoW)}
     * @param ontoZ the {@code z} component of the vector {@code (ontoX, ontoY, ontoZ, ontoW)}
     * @param ontoW the {@code w} component of the vector {@code (ontoX, ontoY, ontoZ, ontoW)}
     * @return {@code dest}
     */
    public static float[] project(float[] dest, int destOffset, float[] src, int srcOffset, float ontoX, float ontoY, float ontoZ, float ontoW) {
        float _t9 = Math.fma(ontoW, src[srcOffset + 3], Math.fma(ontoZ, src[srcOffset + 2], Math.fma(ontoX, src[srcOffset], ontoY * src[srcOffset + 1]))) / Math.fma(ontoW, ontoW, Math.fma(ontoZ, ontoZ, Math.fma(ontoX, ontoX, ontoY * ontoY)));
        dest[destOffset] = ontoX * _t9;
        dest[destOffset + 1] = ontoY * _t9;
        dest[destOffset + 2] = ontoZ * _t9;
        dest[destOffset + 3] = ontoW * _t9;
        return dest;
    }

    /** {@link #project(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer project(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float ontoX, float ontoY, float ontoZ, float ontoW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.project_unsafe(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ, ontoW);
        return Float4OpsKernelsTypedBuffer.project_api(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ, ontoW);
    }

    /** {@link #project(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer project(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float ontoX, float ontoY, float ontoZ, float ontoW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.project_unsafe(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ, ontoW);
        return Float4OpsKernelsByteBuffer.project_api(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ, ontoW);
    }

    /** {@link #project(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment project(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float ontoX, float ontoY, float ontoZ, float ontoW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            project(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), ontoX, ontoY, ontoZ, ontoW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.project_unsafe(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ, ontoW);
        return Float4OpsKernelsSegment.project_api(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ, ontoW);
    }

    /** {@link #project(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long project(long dest, long src, float ontoX, float ontoY, float ontoZ, float ontoW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.project_unsafe(dest, src, ontoX, ontoY, ontoZ, ontoW);
        project(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, ontoX, ontoY, ontoZ, ontoW);
        return dest;
    }

    /**
     * Project this vector onto {@code onto} and store the result in {@code dest}.
     * <p>
     * Valid input: {@code onto} must be non-zero.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param onto the storage holding the vector to project onto
     * @param ontoOffset the element index in {@code onto} at which the vector starts
     * @return {@code dest}
     */
    public static float[] project(float[] dest, int destOffset, float[] src, int srcOffset, float[] onto, int ontoOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.project(dest, destOffset, src, srcOffset, onto, ontoOffset);
        return Float4OpsKernelsArray.project_scalar(dest, destOffset, src, srcOffset, onto, ontoOffset);
    }

    /** {@link #project(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer project(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer onto, int ontoOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && onto.isDirect() && onto.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.project_unsafe(dest, destOffset, src, srcOffset, onto, ontoOffset);
        return Float4OpsKernelsTypedBuffer.project_api(dest, destOffset, src, srcOffset, onto, ontoOffset);
    }

    /** {@link #project(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer project(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer onto, int ontoOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && onto.isDirect() && onto.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.project_unsafe(dest, destOffset, src, srcOffset, onto, ontoOffset);
        return Float4OpsKernelsByteBuffer.project_api(dest, destOffset, src, srcOffset, onto, ontoOffset);
    }

    /** {@link #project(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment project(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment onto, long ontoOffset) {
        float[] _destArray, _srcArray, _ontoArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_ontoArray = heapFloats(onto, ontoOffset)) != null) {
            project(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _ontoArray, heapIndex(onto, ontoOffset, 2));
            return dest;
        }
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.project(dest, destOffset, src, srcOffset, onto, ontoOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && onto.isNative()) return Float4OpsKernelsSegment.project_unsafe(dest, destOffset, src, srcOffset, onto, ontoOffset);
        return Float4OpsKernelsSegment.project_api(dest, destOffset, src, srcOffset, onto, ontoOffset);
    }

    /** {@link #project(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long project(long dest, long src, long onto) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.project_unsafe(dest, src, onto);
        project(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(onto, 16L), 0L);
        return dest;
    }

    /**
     * Project this vector onto the plane with the given normal and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code (normalX, normalY, normalZ, normalW)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param normalX the {@code x} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalY the {@code y} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalZ the {@code z} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalW the {@code w} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @return {@code dest}
     */
    public static float[] projectOnPlane(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t3 = Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        dest[destOffset] = Math.fma(-normalX, _t3, _selfx);
        dest[destOffset + 1] = Math.fma(-normalY, _t3, _selfy);
        dest[destOffset + 2] = Math.fma(-normalZ, _t3, _selfz);
        dest[destOffset + 3] = Math.fma(-normalW, _t3, _selfw);
        return dest;
    }

    /** {@link #projectOnPlane(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer projectOnPlane(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.projectOnPlane_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
        return Float4OpsKernelsTypedBuffer.projectOnPlane_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
    }

    /** {@link #projectOnPlane(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer projectOnPlane(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.projectOnPlane_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
        return Float4OpsKernelsByteBuffer.projectOnPlane_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
    }

    /** {@link #projectOnPlane(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment projectOnPlane(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            projectOnPlane(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), normalX, normalY, normalZ, normalW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.projectOnPlane_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
        return Float4OpsKernelsSegment.projectOnPlane_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
    }

    /** {@link #projectOnPlane(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long projectOnPlane(long dest, long src, float normalX, float normalY, float normalZ, float normalW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.projectOnPlane_unsafe(dest, src, normalX, normalY, normalZ, normalW);
        projectOnPlane(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, normalX, normalY, normalZ, normalW);
        return dest;
    }

    /**
     * Project this vector onto the plane with the given normal and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param normal the storage holding the normal of the plane to project onto
     * @param normalOffset the element index in {@code normal} at which the vector starts
     * @return {@code dest}
     */
    public static float[] projectOnPlane(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.projectOnPlane_fma(dest, destOffset, src, srcOffset, normal, normalOffset);
            return Float4OpsSimd.projectOnPlane_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
        }
        return Float4OpsKernelsArray.projectOnPlane_scalar(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    /** {@link #projectOnPlane(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer projectOnPlane(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.projectOnPlane_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return Float4OpsKernelsTypedBuffer.projectOnPlane_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    /** {@link #projectOnPlane(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer projectOnPlane(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.projectOnPlane_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return Float4OpsKernelsByteBuffer.projectOnPlane_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    /** {@link #projectOnPlane(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment projectOnPlane(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        float[] _destArray, _srcArray, _normalArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_normalArray = heapFloats(normal, normalOffset)) != null) {
            projectOnPlane(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _normalArray, heapIndex(normal, normalOffset, 2));
            return dest;
        }
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.projectOnPlane(dest, destOffset, src, srcOffset, normal, normalOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && normal.isNative()) return Float4OpsKernelsSegment.projectOnPlane_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return Float4OpsKernelsSegment.projectOnPlane_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    /** {@link #projectOnPlane(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long projectOnPlane(long dest, long src, long normal) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.projectOnPlane_unsafe(dest, src, normal);
        projectOnPlane(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(normal, 16L), 0L);
        return dest;
    }

    /**
     * Compute the value converted from degrees to radians of each component of this vector and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] radians(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.toRadians(src[srcOffset]);
        dest[destOffset + 1] = Math.toRadians(_selfy);
        dest[destOffset + 2] = Math.toRadians(_selfz);
        dest[destOffset + 3] = Math.toRadians(_selfw);
        return dest;
    }

    /** {@link #radians(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer radians(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.radians_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.radians_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #radians(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer radians(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.radians_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.radians_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #radians(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment radians(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            radians(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.radians_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.radians_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #radians(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long radians(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.radians_unsafe(dest, src);
        radians(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Reflect this vector about the given normal and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (normalX, normalY, normalZ, normalW)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param normalX the {@code x} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalY the {@code y} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalZ the {@code z} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalW the {@code w} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @return {@code dest}
     */
    public static float[] reflect(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t4 = 2.0f * Math.fma(normalW, _selfw, Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy)));
        dest[destOffset] = Math.fma(-normalX, _t4, _selfx);
        dest[destOffset + 1] = Math.fma(-normalY, _t4, _selfy);
        dest[destOffset + 2] = Math.fma(-normalZ, _t4, _selfz);
        dest[destOffset + 3] = Math.fma(-normalW, _t4, _selfw);
        return dest;
    }

    /** {@link #reflect(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer reflect(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.reflect_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
        return Float4OpsKernelsTypedBuffer.reflect_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
    }

    /** {@link #reflect(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer reflect(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.reflect_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
        return Float4OpsKernelsByteBuffer.reflect_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
    }

    /** {@link #reflect(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment reflect(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            reflect(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), normalX, normalY, normalZ, normalW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.reflect_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
        return Float4OpsKernelsSegment.reflect_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW);
    }

    /** {@link #reflect(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long reflect(long dest, long src, float normalX, float normalY, float normalZ, float normalW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.reflect_unsafe(dest, src, normalX, normalY, normalZ, normalW);
        reflect(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, normalX, normalY, normalZ, normalW);
        return dest;
    }

    /**
     * Reflect this vector about the given normal and store the result in {@code dest}.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param normal the storage holding the normal of the plane to reflect about
     * @param normalOffset the element index in {@code normal} at which the vector starts
     * @return {@code dest}
     */
    public static float[] reflect(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.reflect_fma(dest, destOffset, src, srcOffset, normal, normalOffset);
            return Float4OpsSimd.reflect_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
        }
        return Float4OpsKernelsArray.reflect_scalar(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    /** {@link #reflect(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer reflect(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.reflect_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return Float4OpsKernelsTypedBuffer.reflect_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    /** {@link #reflect(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer reflect(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.reflect_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return Float4OpsKernelsByteBuffer.reflect_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    /** {@link #reflect(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment reflect(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        float[] _destArray, _srcArray, _normalArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_normalArray = heapFloats(normal, normalOffset)) != null) {
            reflect(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _normalArray, heapIndex(normal, normalOffset, 2));
            return dest;
        }
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.reflect(dest, destOffset, src, srcOffset, normal, normalOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && normal.isNative()) return Float4OpsKernelsSegment.reflect_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return Float4OpsKernelsSegment.reflect_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    /** {@link #reflect(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long reflect(long dest, long src, long normal) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.reflect_unsafe(dest, src, normal);
        reflect(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(normal, 16L), 0L);
        return dest;
    }

    /**
     * Refract this vector through the surface with the given normal, using the given ratio of
     * indices of refraction (the zero vector is returned on total internal reflection), and store
     * the result in {@code dest}.
     * <p>
     * As in GLSL, the normal must face against this vector ({@code dot(this, normal) <= 0}): a
     * normal on the far side of the surface bends the vector the wrong way, and with a ratio of 1
     * it comes back reversed. Negate the normal for a vector leaving through the surface.
     * <p>
     * Valid input: {@code (normalX, normalY, normalZ, normalW)} must have unit length; this vector
     * must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param normalX the {@code x} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalY the {@code y} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalZ the {@code z} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param normalW the {@code w} component of the vector
     *        {@code (normalX, normalY, normalZ, normalW)}
     * @param eta the ratio of indices of refraction, i.e. the source medium's divided by the
     *        destination medium's
     * @return {@code dest}
     */
    public static float[] refract(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.refract_fma(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
            return Float4OpsSimd.refract_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
        }
        return Float4OpsKernelsArray.refract_scalar(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
    }

    /** {@link #refract(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer refract(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.refract_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
        return Float4OpsKernelsTypedBuffer.refract_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
    }

    /** {@link #refract(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer refract(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.refract_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
        return Float4OpsKernelsByteBuffer.refract_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
    }

    /** {@link #refract(float[], int, float[], int, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment refract(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ, float normalW, float eta) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            refract(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), normalX, normalY, normalZ, normalW, eta);
            return dest;
        }
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.refract(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.refract_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
        return Float4OpsKernelsSegment.refract_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, normalW, eta);
    }

    /** {@link #refract(float[], int, float[], int, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long refract(long dest, long src, float normalX, float normalY, float normalZ, float normalW, float eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.refract_unsafe(dest, src, normalX, normalY, normalZ, normalW, eta);
        refract(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, normalX, normalY, normalZ, normalW, eta);
        return dest;
    }

    /**
     * Refract this vector through the surface with the given normal, using the given ratio of
     * indices of refraction (the zero vector is returned on total internal reflection), and store
     * the result in {@code dest}.
     * <p>
     * As in GLSL, the normal must face against this vector ({@code dot(this, normal) <= 0}): a
     * normal on the far side of the surface bends the vector the wrong way, and with a ratio of 1
     * it comes back reversed. Negate the normal for a vector leaving through the surface.
     * <p>
     * Valid input: {@code normal} must have unit length; this vector must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param normal the storage holding the normal of the refracting surface
     * @param normalOffset the element index in {@code normal} at which the vector starts
     * @param eta the ratio of indices of refraction, i.e. the source medium's divided by the
     *        destination medium's
     * @return {@code dest}
     */
    public static float[] refract(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset, float eta) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.refract_fma(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
            return Float4OpsSimd.refract_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        }
        return Float4OpsKernelsArray.refract_scalar(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
    }

    /** {@link #refract(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer refract(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset, float eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.refract_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return Float4OpsKernelsTypedBuffer.refract_api(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
    }

    /** {@link #refract(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer refract(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset, float eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.refract_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return Float4OpsKernelsByteBuffer.refract_api(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
    }

    /** {@link #refract(float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment refract(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset, float eta) {
        float[] _destArray, _srcArray, _normalArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_normalArray = heapFloats(normal, normalOffset)) != null) {
            refract(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _normalArray, heapIndex(normal, normalOffset, 2), eta);
            return dest;
        }
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.refract(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && normal.isNative()) return Float4OpsKernelsSegment.refract_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return Float4OpsKernelsSegment.refract_api(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
    }

    /** {@link #refract(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long refract(long dest, long src, long normal, float eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.refract_unsafe(dest, src, normal, eta);
        refract(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(normal, 16L), 0L, eta);
        return dest;
    }

    /**
     * Compute the value rounded to the nearest integer, ties to even ({@code Math.rint}) of each
     * component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] round(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.rint(src[srcOffset]);
        dest[destOffset + 1] = Math.rint(_selfy);
        dest[destOffset + 2] = Math.rint(_selfz);
        dest[destOffset + 3] = Math.rint(_selfw);
        return dest;
    }

    /** {@link #round(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer round(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.round_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.round_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #round(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer round(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.round_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.round_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #round(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment round(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            round(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.round_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.round_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #round(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long round(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.round_unsafe(dest, src);
        round(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the sign of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] sign(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.signum(src[srcOffset]);
        dest[destOffset + 1] = Math.signum(_selfy);
        dest[destOffset + 2] = Math.signum(_selfz);
        dest[destOffset + 3] = Math.signum(_selfw);
        return dest;
    }

    /** {@link #sign(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer sign(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.sign_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.sign_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sign(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sign(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.sign_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.sign_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sign(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment sign(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            sign(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.sign_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.sign_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sign(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long sign(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.sign_unsafe(dest, src);
        sign(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the sine of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] sin(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.sin(src[srcOffset]);
        dest[destOffset + 1] = Math.sin(_selfy);
        dest[destOffset + 2] = Math.sin(_selfz);
        dest[destOffset + 3] = Math.sin(_selfw);
        return dest;
    }

    /** {@link #sin(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer sin(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.sin_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.sin_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sin(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sin(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.sin_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.sin_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sin(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment sin(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            sin(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.sin_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.sin_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sin(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long sin(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.sin_unsafe(dest, src);
        sin(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the hyperbolic sine of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] sinh(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.sinh(src[srcOffset]);
        dest[destOffset + 1] = Math.sinh(_selfy);
        dest[destOffset + 2] = Math.sinh(_selfz);
        dest[destOffset + 3] = Math.sinh(_selfw);
        return dest;
    }

    /** {@link #sinh(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer sinh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.sinh_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.sinh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sinh(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sinh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.sinh_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.sinh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sinh(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment sinh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            sinh(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.sinh_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.sinh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sinh(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long sinh(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.sinh_unsafe(dest, src);
        sinh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge {@code edge0} and the upper edge {@code edge1}, yielding 0 at or below the lower
     * edge and 1 at or above the upper edge and store the result in {@code dest}.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param edge0 the lower edge
     * @param edge1 the upper edge
     * @return {@code dest}
     */
    public static float[] smoothstep(float[] dest, int destOffset, float[] src, int srcOffset, float edge0, float edge1) {
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t13 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src[srcOffset] - edge0) * _t0_inv));
        float _t14 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src[srcOffset + 1] - edge0) * _t0_inv));
        float _t15 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src[srcOffset + 2] - edge0) * _t0_inv));
        float _t16 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src[srcOffset + 3] - edge0) * _t0_inv));
        dest[destOffset] = Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13;
        dest[destOffset + 1] = Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14;
        dest[destOffset + 2] = Math.fma(-2.0f, _t15, 3.0f) * _t15 * _t15;
        dest[destOffset + 3] = Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16;
        return dest;
    }

    /** {@link #smoothstep(float[], int, float[], int, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer smoothstep(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0, float edge1) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0, edge1);
        return Float4OpsKernelsTypedBuffer.smoothstep_api(dest, destOffset, src, srcOffset, edge0, edge1);
    }

    /** {@link #smoothstep(float[], int, float[], int, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer smoothstep(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge0, float edge1) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0, edge1);
        return Float4OpsKernelsByteBuffer.smoothstep_api(dest, destOffset, src, srcOffset, edge0, edge1);
    }

    /** {@link #smoothstep(float[], int, float[], int, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment smoothstep(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0, float edge1) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            smoothstep(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), edge0, edge1);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0, edge1);
        return Float4OpsKernelsSegment.smoothstep_api(dest, destOffset, src, srcOffset, edge0, edge1);
    }

    /** {@link #smoothstep(float[], int, float[], int, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long smoothstep(long dest, long src, float edge0, float edge1) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.smoothstep_unsafe(dest, src, edge0, edge1);
        smoothstep(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, edge0, edge1);
        return dest;
    }

    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge ({@code edge0X}, {@code edge0Y}, {@code edge0Z}, {@code edge0W}) and the upper
     * edge ({@code edge1X}, {@code edge1Y}, {@code edge1Z}, {@code edge1W}), yielding 0 at or below
     * the lower edge and 1 at or above the upper edge and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (edge0X, edge0Y, edge0Z, edge0W)} and
     * {@code (edge1X, edge1Y, edge1Z, edge1W)} must differ in every component.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param edge0X the {@code x} component of the vector {@code (edge0X, edge0Y, edge0Z, edge0W)}
     * @param edge0Y the {@code y} component of the vector {@code (edge0X, edge0Y, edge0Z, edge0W)}
     * @param edge0Z the {@code z} component of the vector {@code (edge0X, edge0Y, edge0Z, edge0W)}
     * @param edge0W the {@code w} component of the vector {@code (edge0X, edge0Y, edge0Z, edge0W)}
     * @param edge1X the {@code x} component of the vector {@code (edge1X, edge1Y, edge1Z, edge1W)}
     * @param edge1Y the {@code y} component of the vector {@code (edge1X, edge1Y, edge1Z, edge1W)}
     * @param edge1Z the {@code z} component of the vector {@code (edge1X, edge1Y, edge1Z, edge1W)}
     * @param edge1W the {@code w} component of the vector {@code (edge1X, edge1Y, edge1Z, edge1W)}
     * @return {@code dest}
     */
    public static float[] smoothstep(float[] dest, int destOffset, float[] src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        float _t16 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src[srcOffset] - edge0X) / (edge1X - edge0X)));
        float _t17 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src[srcOffset + 1] - edge0Y) / (edge1Y - edge0Y)));
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src[srcOffset + 2] - edge0Z) / (edge1Z - edge0Z)));
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src[srcOffset + 3] - edge0W) / (edge1W - edge0W)));
        dest[destOffset] = Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16;
        dest[destOffset + 1] = Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17;
        dest[destOffset + 2] = Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18;
        dest[destOffset + 3] = Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19;
        return dest;
    }

    /** {@link #smoothstep(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer smoothstep(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        return Float4OpsKernelsTypedBuffer.smoothstep_api(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
    }

    /** {@link #smoothstep(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer smoothstep(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        return Float4OpsKernelsByteBuffer.smoothstep_api(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
    }

    /** {@link #smoothstep(float[], int, float[], int, float, float, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment smoothstep(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            smoothstep(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        return Float4OpsKernelsSegment.smoothstep_api(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
    }

    /** {@link #smoothstep(float[], int, float[], int, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long smoothstep(long dest, long src, float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.smoothstep_unsafe(dest, src, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        smoothstep(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W);
        return dest;
    }

    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge {@code edge0} and the upper edge {@code edge1}, yielding 0 at or below the lower
     * edge and 1 at or above the upper edge and store the result in {@code dest}.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ in every component.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param edge0 the storage holding the lower edge
     * @param edge0Offset the element index in {@code edge0} at which the vector starts
     * @param edge1 the storage holding the upper edge
     * @param edge1Offset the element index in {@code edge1} at which the vector starts
     * @return {@code dest}
     */
    public static float[] smoothstep(float[] dest, int destOffset, float[] src, int srcOffset, float[] edge0, int edge0Offset, float[] edge1, int edge1Offset) {
        float _edge0x = edge0[edge0Offset];
        float _edge0y = edge0[edge0Offset + 1];
        float _edge0z = edge0[edge0Offset + 2];
        float _edge0w = edge0[edge0Offset + 3];
        float _t16 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src[srcOffset] - _edge0x) / (edge1[edge1Offset] - _edge0x)));
        float _t17 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src[srcOffset + 1] - _edge0y) / (edge1[edge1Offset + 1] - _edge0y)));
        float _t18 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src[srcOffset + 2] - _edge0z) / (edge1[edge1Offset + 2] - _edge0z)));
        float _t19 = java.lang.Math.max(0.0f, java.lang.Math.min(1.0f, (src[srcOffset + 3] - _edge0w) / (edge1[edge1Offset + 3] - _edge0w)));
        dest[destOffset] = Math.fma(-2.0f, _t16, 3.0f) * _t16 * _t16;
        dest[destOffset + 1] = Math.fma(-2.0f, _t17, 3.0f) * _t17 * _t17;
        dest[destOffset + 2] = Math.fma(-2.0f, _t18, 3.0f) * _t18 * _t18;
        dest[destOffset + 3] = Math.fma(-2.0f, _t19, 3.0f) * _t19 * _t19;
        return dest;
    }

    /** {@link #smoothstep(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer smoothstep(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge0, int edge0Offset, java.nio.FloatBuffer edge1, int edge1Offset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && edge0.isDirect() && edge0.order() == java.nio.ByteOrder.nativeOrder() && edge1.isDirect() && edge1.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        return Float4OpsKernelsTypedBuffer.smoothstep_api(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
    }

    /** {@link #smoothstep(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer smoothstep(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge0, int edge0Offset, java.nio.ByteBuffer edge1, int edge1Offset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && edge0.isDirect() && edge0.order() == java.nio.ByteOrder.nativeOrder() && edge1.isDirect() && edge1.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        return Float4OpsKernelsByteBuffer.smoothstep_api(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
    }

    /** {@link #smoothstep(float[], int, float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment smoothstep(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge0, long edge0Offset, java.lang.foreign.MemorySegment edge1, long edge1Offset) {
        float[] _destArray, _srcArray, _edge0Array, _edge1Array;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_edge0Array = heapFloats(edge0, edge0Offset)) != null
                && (_edge1Array = heapFloats(edge1, edge1Offset)) != null) {
            smoothstep(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _edge0Array, heapIndex(edge0, edge0Offset, 2), _edge1Array, heapIndex(edge1, edge1Offset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && edge0.isNative() && edge1.isNative()) return Float4OpsKernelsSegment.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        return Float4OpsKernelsSegment.smoothstep_api(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
    }

    /** {@link #smoothstep(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long smoothstep(long dest, long src, long edge0, long edge1) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.smoothstep_unsafe(dest, src, edge0, edge1);
        smoothstep(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(edge0, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(edge1, 16L), 0L);
        return dest;
    }

    /**
     * Compute the square root of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] sqrt(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.sqrt(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsArray.sqrt_scalar(dest, destOffset, src, srcOffset);
    }

    /** {@link #sqrt(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer sqrt(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.sqrt_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.sqrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sqrt(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sqrt(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.sqrt_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.sqrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sqrt(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment sqrt(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.sqrt(dest, destOffset, src, srcOffset);
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            sqrt(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.sqrt_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.sqrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sqrt(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long sqrt(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.sqrt_unsafe(dest, src);
        sqrt(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Set each component of this vector to {@code 0} when it is smaller than {@code edge}, and to
     * {@code 1} otherwise and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param edge the edge to compare each component against
     * @return {@code dest}
     */
    public static float[] step(float[] dest, int destOffset, float[] src, int srcOffset, float edge) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = src[srcOffset] < edge ? 0.0f : 1.0f;
        dest[destOffset + 1] = _selfy < edge ? 0.0f : 1.0f;
        dest[destOffset + 2] = _selfz < edge ? 0.0f : 1.0f;
        dest[destOffset + 3] = _selfw < edge ? 0.0f : 1.0f;
        return dest;
    }

    /** {@link #step(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer step(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.step_unsafe(dest, destOffset, src, srcOffset, edge);
        return Float4OpsKernelsTypedBuffer.step_api(dest, destOffset, src, srcOffset, edge);
    }

    /** {@link #step(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer step(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.step_unsafe(dest, destOffset, src, srcOffset, edge);
        return Float4OpsKernelsByteBuffer.step_api(dest, destOffset, src, srcOffset, edge);
    }

    /** {@link #step(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment step(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edge) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            step(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), edge);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.step_unsafe(dest, destOffset, src, srcOffset, edge);
        return Float4OpsKernelsSegment.step_api(dest, destOffset, src, srcOffset, edge);
    }

    /** {@link #step(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long step(long dest, long src, float edge) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.step_unsafe(dest, src, edge);
        step(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, edge);
        return dest;
    }

    /**
     * Set each component of this vector to {@code 0} when it is smaller than the corresponding
     * component of the given edge, and to {@code 1} otherwise and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param edgeX the {@code x} component of the vector {@code (edgeX, edgeY, edgeZ, edgeW)}
     * @param edgeY the {@code y} component of the vector {@code (edgeX, edgeY, edgeZ, edgeW)}
     * @param edgeZ the {@code z} component of the vector {@code (edgeX, edgeY, edgeZ, edgeW)}
     * @param edgeW the {@code w} component of the vector {@code (edgeX, edgeY, edgeZ, edgeW)}
     * @return {@code dest}
     */
    public static float[] step(float[] dest, int destOffset, float[] src, int srcOffset, float edgeX, float edgeY, float edgeZ, float edgeW) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = src[srcOffset] < edgeX ? 0.0f : 1.0f;
        dest[destOffset + 1] = _selfy < edgeY ? 0.0f : 1.0f;
        dest[destOffset + 2] = _selfz < edgeZ ? 0.0f : 1.0f;
        dest[destOffset + 3] = _selfw < edgeW ? 0.0f : 1.0f;
        return dest;
    }

    /** {@link #step(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer step(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edgeX, float edgeY, float edgeZ, float edgeW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.step_unsafe(dest, destOffset, src, srcOffset, edgeX, edgeY, edgeZ, edgeW);
        return Float4OpsKernelsTypedBuffer.step_api(dest, destOffset, src, srcOffset, edgeX, edgeY, edgeZ, edgeW);
    }

    /** {@link #step(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer step(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edgeX, float edgeY, float edgeZ, float edgeW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.step_unsafe(dest, destOffset, src, srcOffset, edgeX, edgeY, edgeZ, edgeW);
        return Float4OpsKernelsByteBuffer.step_api(dest, destOffset, src, srcOffset, edgeX, edgeY, edgeZ, edgeW);
    }

    /** {@link #step(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment step(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float edgeX, float edgeY, float edgeZ, float edgeW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            step(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), edgeX, edgeY, edgeZ, edgeW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.step_unsafe(dest, destOffset, src, srcOffset, edgeX, edgeY, edgeZ, edgeW);
        return Float4OpsKernelsSegment.step_api(dest, destOffset, src, srcOffset, edgeX, edgeY, edgeZ, edgeW);
    }

    /** {@link #step(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long step(long dest, long src, float edgeX, float edgeY, float edgeZ, float edgeW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.step_unsafe(dest, src, edgeX, edgeY, edgeZ, edgeW);
        step(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, edgeX, edgeY, edgeZ, edgeW);
        return dest;
    }

    /**
     * Set each component of this vector to {@code 0} when it is smaller than the corresponding
     * component of the given edge, and to {@code 1} otherwise and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param edge the storage holding the edge to compare each component against
     * @param edgeOffset the element index in {@code edge} at which the vector starts
     * @return {@code dest}
     */
    public static float[] step(float[] dest, int destOffset, float[] src, int srcOffset, float[] edge, int edgeOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _edgey = edge[edgeOffset + 1];
        float _edgez = edge[edgeOffset + 2];
        float _edgew = edge[edgeOffset + 3];
        dest[destOffset] = src[srcOffset] < edge[edgeOffset] ? 0.0f : 1.0f;
        dest[destOffset + 1] = _selfy < _edgey ? 0.0f : 1.0f;
        dest[destOffset + 2] = _selfz < _edgez ? 0.0f : 1.0f;
        dest[destOffset + 3] = _selfw < _edgew ? 0.0f : 1.0f;
        return dest;
    }

    /** {@link #step(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer step(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge, int edgeOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && edge.isDirect() && edge.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.step_unsafe(dest, destOffset, src, srcOffset, edge, edgeOffset);
        return Float4OpsKernelsTypedBuffer.step_api(dest, destOffset, src, srcOffset, edge, edgeOffset);
    }

    /** {@link #step(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer step(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge, int edgeOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && edge.isDirect() && edge.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.step_unsafe(dest, destOffset, src, srcOffset, edge, edgeOffset);
        return Float4OpsKernelsByteBuffer.step_api(dest, destOffset, src, srcOffset, edge, edgeOffset);
    }

    /** {@link #step(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment step(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment edge, long edgeOffset) {
        float[] _destArray, _srcArray, _edgeArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_edgeArray = heapFloats(edge, edgeOffset)) != null) {
            step(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _edgeArray, heapIndex(edge, edgeOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && edge.isNative()) return Float4OpsKernelsSegment.step_unsafe(dest, destOffset, src, srcOffset, edge, edgeOffset);
        return Float4OpsKernelsSegment.step_api(dest, destOffset, src, srcOffset, edge, edgeOffset);
    }

    /** {@link #step(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long step(long dest, long src, long edge) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.step_unsafe(dest, src, edge);
        step(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(edge, 16L), 0L);
        return dest;
    }

    /**
     * Compute the tangent of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] tan(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.tan(src[srcOffset]);
        dest[destOffset + 1] = Math.tan(_selfy);
        dest[destOffset + 2] = Math.tan(_selfz);
        dest[destOffset + 3] = Math.tan(_selfw);
        return dest;
    }

    /** {@link #tan(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer tan(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.tan_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.tan_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #tan(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer tan(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.tan_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.tan_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #tan(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment tan(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            tan(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.tan_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.tan_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #tan(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long tan(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.tan_unsafe(dest, src);
        tan(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the hyperbolic tangent of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] tanh(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.tanh(src[srcOffset]);
        dest[destOffset + 1] = Math.tanh(_selfy);
        dest[destOffset + 2] = Math.tanh(_selfz);
        dest[destOffset + 3] = Math.tanh(_selfw);
        return dest;
    }

    /** {@link #tanh(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer tanh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.tanh_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.tanh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #tanh(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer tanh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.tanh_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.tanh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #tanh(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment tanh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            tanh(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.tanh_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.tanh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #tanh(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long tanh(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.tanh_unsafe(dest, src);
        tanh(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the truncated value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] trunc(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = _selfx >= 0.0f ? Math.floor(_selfx) : Math.ceil(_selfx);
        dest[destOffset + 1] = _selfy >= 0.0f ? Math.floor(_selfy) : Math.ceil(_selfy);
        dest[destOffset + 2] = _selfz >= 0.0f ? Math.floor(_selfz) : Math.ceil(_selfz);
        dest[destOffset + 3] = _selfw >= 0.0f ? Math.floor(_selfw) : Math.ceil(_selfw);
        return dest;
    }

    /** {@link #trunc(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer trunc(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.trunc_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.trunc_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #trunc(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer trunc(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.trunc_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.trunc_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #trunc(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment trunc(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            trunc(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.trunc_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.trunc_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #trunc(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long trunc(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.trunc_unsafe(dest, src);
        trunc(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the unit in the last place (ulp) of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] ulp(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.ulp(src[srcOffset]);
        dest[destOffset + 1] = Math.ulp(_selfy);
        dest[destOffset + 2] = Math.ulp(_selfz);
        dest[destOffset + 3] = Math.ulp(_selfw);
        return dest;
    }

    /** {@link #ulp(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer ulp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.ulp_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsTypedBuffer.ulp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #ulp(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer ulp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.ulp_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsByteBuffer.ulp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #ulp(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment ulp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            ulp(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.ulp_unsafe(dest, destOffset, src, srcOffset);
        return Float4OpsKernelsSegment.ulp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #ulp(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long ulp(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.ulp_unsafe(dest, src);
        ulp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Pre-multiply {@code mat} onto this vector, i.e. compute {@code mat * this} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param mat the storage holding the matrix to apply
     * @param matOffset the element index in {@code mat} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] preMul(float[] dest, int destOffset, float[] src, int srcOffset, float[] mat, int matOffset) {
        if (SimdSupport.VECTOR_API) {
            if (SimdSupport.USE_FMA) return Float4OpsSimd.preMul_fma(dest, destOffset, src, srcOffset, mat, matOffset);
            return Float4OpsSimd.preMul_mulAdd(dest, destOffset, src, srcOffset, mat, matOffset);
        }
        return Float4OpsKernelsArray.preMul_scalar(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMul(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preMul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.preMul_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Float4OpsKernelsTypedBuffer.preMul_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMul(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.preMul_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Float4OpsKernelsByteBuffer.preMul_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMul(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preMul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment mat, long matOffset) {
        float[] _destArray, _srcArray, _matArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_matArray = heapFloats(mat, matOffset)) != null) {
            preMul(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _matArray, heapIndex(mat, matOffset, 2));
            return dest;
        }
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.preMul(dest, destOffset, src, srcOffset, mat, matOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && mat.isNative()) return Float4OpsKernelsSegment.preMul_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Float4OpsKernelsSegment.preMul_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMul(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preMul(long dest, long src, long mat) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.preMul_unsafe(dest, src, mat);
        preMul(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(mat, 64L), 0L);
        return dest;
    }

    /**
     * Rotate the {@code (x, y, z)} components of this vector by the quaternion ({@code quatX},
     * {@code quatY}, {@code quatZ}, {@code quatW}), i.e. compute {@code q * this.xyz * q^-1},
     * leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (quatX, quatY, quatZ, quatW)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param quatX the {@code x} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatY the {@code y} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatZ the {@code z} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatW the {@code w} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @return {@code dest}
     */
    public static float[] rotate(float[] dest, int destOffset, float[] src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t9 = 2.0f * Math.fma(quatX, _selfy, -(quatY * _selfx));
        float _t10 = 2.0f * Math.fma(quatZ, _selfx, -(quatX * _selfz));
        float _t11 = 2.0f * Math.fma(quatY, _selfz, -(quatZ * _selfy));
        dest[destOffset] = Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, _selfx)));
        dest[destOffset + 1] = Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfy)));
        dest[destOffset + 2] = Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, _selfz)));
        dest[destOffset + 3] = _selfw;
        return dest;
    }

    /** {@link #rotate(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return Float4OpsKernelsTypedBuffer.rotate_api(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
    }

    /** {@link #rotate(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return Float4OpsKernelsByteBuffer.rotate_api(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
    }

    /** {@link #rotate(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            rotate(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), quatX, quatY, quatZ, quatW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.rotate_unsafe(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return Float4OpsKernelsSegment.rotate_api(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
    }

    /** {@link #rotate(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotate(long dest, long src, float quatX, float quatY, float quatZ, float quatW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.rotate_unsafe(dest, src, quatX, quatY, quatZ, quatW);
        rotate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, quatX, quatY, quatZ, quatW);
        return dest;
    }

    /**
     * Rotate the {@code (x, y, z)} components of this vector by the quaternion {@code quat}, i.e.
     * compute {@code q * this.xyz * q^-1}, leaving {@code w} unchanged, and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param quat the storage holding the rotation to apply
     * @param quatOffset the element index in {@code quat} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] rotate(float[] dest, int destOffset, float[] src, int srcOffset, float[] quat, int quatOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _quatx = quat[quatOffset];
        float _quaty = quat[quatOffset + 1];
        float _quatz = quat[quatOffset + 2];
        float _quatw = quat[quatOffset + 3];
        float _t9 = 2.0f * Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        float _t10 = 2.0f * Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        float _t11 = 2.0f * Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        dest[destOffset] = Math.fma(_quaty, _t9, Math.fma(-_quatz, _t10, Math.fma(_quatw, _t11, _selfx)));
        dest[destOffset + 1] = Math.fma(_quatz, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfy)));
        dest[destOffset + 2] = Math.fma(_quatx, _t10, Math.fma(-_quaty, _t11, Math.fma(_quatw, _t9, _selfz)));
        dest[destOffset + 3] = _selfw;
        return dest;
    }

    /** {@link #rotate(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && quat.isDirect() && quat.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, quat, quatOffset);
        return Float4OpsKernelsTypedBuffer.rotate_api(dest, destOffset, src, srcOffset, quat, quatOffset);
    }

    /** {@link #rotate(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && quat.isDirect() && quat.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, quat, quatOffset);
        return Float4OpsKernelsByteBuffer.rotate_api(dest, destOffset, src, srcOffset, quat, quatOffset);
    }

    /** {@link #rotate(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        float[] _destArray, _srcArray, _quatArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_quatArray = heapFloats(quat, quatOffset)) != null) {
            rotate(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _quatArray, heapIndex(quat, quatOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && quat.isNative()) return Float4OpsKernelsSegment.rotate_unsafe(dest, destOffset, src, srcOffset, quat, quatOffset);
        return Float4OpsKernelsSegment.rotate_api(dest, destOffset, src, srcOffset, quat, quatOffset);
    }

    /** {@link #rotate(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotate(long dest, long src, long quat) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.rotate_unsafe(dest, src, quat);
        rotate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(quat, 16L), 0L);
        return dest;
    }

    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the
     * axis ({@code axisX}, {@code axisY}, {@code axisZ}), leaving {@code w} unchanged, and store
     * the result in {@code dest}.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return {@code dest}
     */
    public static float[] rotateAxis(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t3 = 1.0f - _t1;
        float _t5 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        dest[destOffset] = Math.fma(_t3, axisX * _t5, Math.fma(_selfx, _t1, Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t0));
        dest[destOffset + 1] = Math.fma(_t3, axisY * _t5, Math.fma(_selfy, _t1, Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t0));
        dest[destOffset + 2] = Math.fma(_t3, axisZ * _t5, Math.fma(_selfz, _t1, Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t0));
        dest[destOffset + 3] = _selfw;
        return dest;
    }

    /** {@link #rotateAxis(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateAxis(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.rotateAxis_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return Float4OpsKernelsTypedBuffer.rotateAxis_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #rotateAxis(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAxis(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.rotateAxis_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return Float4OpsKernelsByteBuffer.rotateAxis_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #rotateAxis(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            rotateAxis(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), angle, axisX, axisY, axisZ);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.rotateAxis_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return Float4OpsKernelsSegment.rotateAxis_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #rotateAxis(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateAxis(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.rotateAxis_unsafe(dest, src, angle, axisX, axisY, axisZ);
        rotateAxis(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, angle, axisX, axisY, axisZ);
        return dest;
    }

    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the
     * axis {@code axis}, leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param axis the storage holding the rotation axis
     * @param axisOffset the element index in {@code axis} at which the vector starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] rotateAxis(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _axisx = axis[axisOffset];
        float _axisy = axis[axisOffset + 1];
        float _axisz = axis[axisOffset + 2];
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t3 = 1.0f - _t1;
        float _t5 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        dest[destOffset] = Math.fma(_t3, _axisx * _t5, Math.fma(_selfx, _t1, Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t0));
        dest[destOffset + 1] = Math.fma(_t3, _axisy * _t5, Math.fma(_selfy, _t1, Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t0));
        dest[destOffset + 2] = Math.fma(_t3, _axisz * _t5, Math.fma(_selfz, _t1, Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t0));
        dest[destOffset + 3] = _selfw;
        return dest;
    }

    /** {@link #rotateAxis(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateAxis(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.rotateAxis_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return Float4OpsKernelsTypedBuffer.rotateAxis_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@link #rotateAxis(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAxis(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.rotateAxis_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return Float4OpsKernelsByteBuffer.rotateAxis_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@link #rotateAxis(float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        float[] _destArray, _srcArray, _axisArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_axisArray = heapFloats(axis, axisOffset)) != null) {
            rotateAxis(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _axisArray, heapIndex(axis, axisOffset, 2), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && axis.isNative()) return Float4OpsKernelsSegment.rotateAxis_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return Float4OpsKernelsSegment.rotateAxis_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@link #rotateAxis(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateAxis(long dest, long src, long axis, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.rotateAxis_unsafe(dest, src, axis, angle);
        rotateAxis(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(axis, 12L), 0L, angle);
        return dest;
    }

    /**
     * Rotate the {@code (x, y, z)} components of this vector by the inverse of the given rotation,
     * leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (quatX, quatY, quatZ, quatW)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param quatX the {@code x} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatY the {@code y} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatZ the {@code z} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @param quatW the {@code w} component of the quaternion {@code (quatX, quatY, quatZ, quatW)}
     * @return {@code dest}
     */
    public static float[] rotateInverse(float[] dest, int destOffset, float[] src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t9 = 2.0f * Math.fma(quatX, _selfz, -(quatZ * _selfx));
        float _t10 = 2.0f * Math.fma(quatY, _selfx, -(quatX * _selfy));
        float _t11 = 2.0f * Math.fma(quatZ, _selfy, -(quatY * _selfz));
        dest[destOffset] = Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, _selfx)));
        dest[destOffset + 1] = Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, _selfy)));
        dest[destOffset + 2] = Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfz)));
        dest[destOffset + 3] = _selfw;
        return dest;
    }

    /** {@link #rotateInverse(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateInverse(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.rotateInverse_unsafe(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return Float4OpsKernelsTypedBuffer.rotateInverse_api(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
    }

    /** {@link #rotateInverse(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateInverse(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.rotateInverse_unsafe(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return Float4OpsKernelsByteBuffer.rotateInverse_api(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
    }

    /** {@link #rotateInverse(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateInverse(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            rotateInverse(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), quatX, quatY, quatZ, quatW);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.rotateInverse_unsafe(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return Float4OpsKernelsSegment.rotateInverse_api(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
    }

    /** {@link #rotateInverse(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateInverse(long dest, long src, float quatX, float quatY, float quatZ, float quatW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.rotateInverse_unsafe(dest, src, quatX, quatY, quatZ, quatW);
        rotateInverse(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, quatX, quatY, quatZ, quatW);
        return dest;
    }

    /**
     * Rotate the {@code (x, y, z)} components of this vector by the inverse of the given rotation,
     * leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param quat the storage holding the rotation whose inverse to apply
     * @param quatOffset the element index in {@code quat} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] rotateInverse(float[] dest, int destOffset, float[] src, int srcOffset, float[] quat, int quatOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _quatx = quat[quatOffset];
        float _quaty = quat[quatOffset + 1];
        float _quatz = quat[quatOffset + 2];
        float _quatw = quat[quatOffset + 3];
        float _t9 = 2.0f * Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        float _t10 = 2.0f * Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        float _t11 = 2.0f * Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        dest[destOffset] = Math.fma(_quatz, _t9, Math.fma(-_quaty, _t10, Math.fma(_quatw, _t11, _selfx)));
        dest[destOffset + 1] = Math.fma(_quatx, _t10, Math.fma(-_quatz, _t11, Math.fma(_quatw, _t9, _selfy)));
        dest[destOffset + 2] = Math.fma(_quaty, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfz)));
        dest[destOffset + 3] = _selfw;
        return dest;
    }

    /** {@link #rotateInverse(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateInverse(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && quat.isDirect() && quat.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.rotateInverse_unsafe(dest, destOffset, src, srcOffset, quat, quatOffset);
        return Float4OpsKernelsTypedBuffer.rotateInverse_api(dest, destOffset, src, srcOffset, quat, quatOffset);
    }

    /** {@link #rotateInverse(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateInverse(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && quat.isDirect() && quat.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.rotateInverse_unsafe(dest, destOffset, src, srcOffset, quat, quatOffset);
        return Float4OpsKernelsByteBuffer.rotateInverse_api(dest, destOffset, src, srcOffset, quat, quatOffset);
    }

    /** {@link #rotateInverse(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateInverse(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment quat, long quatOffset) {
        float[] _destArray, _srcArray, _quatArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null
                && (_quatArray = heapFloats(quat, quatOffset)) != null) {
            rotateInverse(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), _quatArray, heapIndex(quat, quatOffset, 2));
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && quat.isNative()) return Float4OpsKernelsSegment.rotateInverse_unsafe(dest, destOffset, src, srcOffset, quat, quatOffset);
        return Float4OpsKernelsSegment.rotateInverse_api(dest, destOffset, src, srcOffset, quat, quatOffset);
    }

    /** {@link #rotateInverse(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateInverse(long dest, long src, long quat) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.rotateInverse_unsafe(dest, src, quat);
        rotateInverse(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(quat, 16L), 0L);
        return dest;
    }

    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the X
     * axis, leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] rotateX(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = Math.fma(_selfy, _t1, -(_selfz * _t0));
        dest[destOffset + 2] = Math.fma(_selfy, _t0, _selfz * _t1);
        dest[destOffset + 3] = _selfw;
        return dest;
    }

    /** {@link #rotateX(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateX(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.rotateX_unsafe(dest, destOffset, src, srcOffset, angle);
        return Float4OpsKernelsTypedBuffer.rotateX_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateX(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.rotateX_unsafe(dest, destOffset, src, srcOffset, angle);
        return Float4OpsKernelsByteBuffer.rotateX_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateX(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            rotateX(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.rotateX_unsafe(dest, destOffset, src, srcOffset, angle);
        return Float4OpsKernelsSegment.rotateX_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateX(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateX(long dest, long src, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.rotateX_unsafe(dest, src, angle);
        rotateX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, angle);
        return dest;
    }

    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the Y
     * axis, leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] rotateY(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = Math.fma(_selfx, _t1, _selfz * _t0);
        dest[destOffset + 1] = _selfy;
        dest[destOffset + 2] = Math.fma(_selfz, _t1, -(_selfx * _t0));
        dest[destOffset + 3] = _selfw;
        return dest;
    }

    /** {@link #rotateY(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateY(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.rotateY_unsafe(dest, destOffset, src, srcOffset, angle);
        return Float4OpsKernelsTypedBuffer.rotateY_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateY(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.rotateY_unsafe(dest, destOffset, src, srcOffset, angle);
        return Float4OpsKernelsByteBuffer.rotateY_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateY(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            rotateY(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.rotateY_unsafe(dest, destOffset, src, srcOffset, angle);
        return Float4OpsKernelsSegment.rotateY_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateY(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateY(long dest, long src, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.rotateY_unsafe(dest, src, angle);
        rotateY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, angle);
        return dest;
    }

    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the Z
     * axis, leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] rotateZ(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = Math.fma(_selfx, _t1, -(_selfy * _t0));
        dest[destOffset + 1] = Math.fma(_selfx, _t0, _selfy * _t1);
        dest[destOffset + 2] = _selfz;
        dest[destOffset + 3] = _selfw;
        return dest;
    }

    /** {@link #rotateZ(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateZ(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsTypedBuffer.rotateZ_unsafe(dest, destOffset, src, srcOffset, angle);
        return Float4OpsKernelsTypedBuffer.rotateZ_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateZ(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsKernelsByteBuffer.rotateZ_unsafe(dest, destOffset, src, srcOffset, angle);
        return Float4OpsKernelsByteBuffer.rotateZ_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateZ(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            rotateZ(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), angle);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return Float4OpsKernelsSegment.rotateZ_unsafe(dest, destOffset, src, srcOffset, angle);
        return Float4OpsKernelsSegment.rotateZ_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateZ(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateZ(long dest, long src, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float4OpsKernelsAddress.rotateZ_unsafe(dest, src, angle);
        rotateZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, angle);
        return dest;
    }


    /**
     * Bulk out-of-place component-wise {@code add} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code add}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] add(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 128) return Float4OpsSimd.add(dest, destOffset, a, aOffset, b, bOffset, count);
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] + b[bOffset + _i];
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code add} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code add}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer add(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer a, int aOffset, java.nio.FloatBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, a.get(aOffset + _i) + b.get(bOffset + _i));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code add} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code add}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer add(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, a.getFloat(aOffset + _i * 4) + b.getFloat(bOffset + _i * 4));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code add} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code add}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.lang.foreign.MemorySegment add(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.add(dest, destOffset, a, aOffset, b, bOffset, count);
        float[] _destArray, _aArray, _bArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_aArray = heapFloats(a, aOffset)) != null
                && (_bArray = heapFloats(b, bOffset)) != null) {
            add(_destArray, heapIndex(dest, destOffset, 2), _aArray, heapIndex(a, aOffset, 2), _bArray, heapIndex(b, bOffset, 2), count);
            return dest;
        }
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + _i * 4L) + b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + _i * 4L));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code sub} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code sub}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] sub(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 128) return Float4OpsSimd.sub(dest, destOffset, a, aOffset, b, bOffset, count);
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] - b[bOffset + _i];
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code sub} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code sub}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer sub(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer a, int aOffset, java.nio.FloatBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, a.get(aOffset + _i) - b.get(bOffset + _i));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code sub} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code sub}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer sub(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, a.getFloat(aOffset + _i * 4) - b.getFloat(bOffset + _i * 4));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code sub} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code sub}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.lang.foreign.MemorySegment sub(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.sub(dest, destOffset, a, aOffset, b, bOffset, count);
        float[] _destArray, _aArray, _bArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_aArray = heapFloats(a, aOffset)) != null
                && (_bArray = heapFloats(b, bOffset)) != null) {
            sub(_destArray, heapIndex(dest, destOffset, 2), _aArray, heapIndex(a, aOffset, 2), _bArray, heapIndex(b, bOffset, 2), count);
            return dest;
        }
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + _i * 4L) - b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + _i * 4L));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code mul} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code mul}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] mul(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 128) return Float4OpsSimd.mul(dest, destOffset, a, aOffset, b, bOffset, count);
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] * b[bOffset + _i];
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code mul} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code mul}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer mul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer a, int aOffset, java.nio.FloatBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, a.get(aOffset + _i) * b.get(bOffset + _i));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code mul} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code mul}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, a.getFloat(aOffset + _i * 4) * b.getFloat(bOffset + _i * 4));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code mul} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code mul}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.mul(dest, destOffset, a, aOffset, b, bOffset, count);
        float[] _destArray, _aArray, _bArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_aArray = heapFloats(a, aOffset)) != null
                && (_bArray = heapFloats(b, bOffset)) != null) {
            mul(_destArray, heapIndex(dest, destOffset, 2), _aArray, heapIndex(a, aOffset, 2), _bArray, heapIndex(b, bOffset, 2), count);
            return dest;
        }
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + _i * 4L) * b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + _i * 4L));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code div} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code div}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] div(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 128) return Float4OpsSimd.div(dest, destOffset, a, aOffset, b, bOffset, count);
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] / b[bOffset + _i];
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code div} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code div}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer div(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer a, int aOffset, java.nio.FloatBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, a.get(aOffset + _i) / b.get(bOffset + _i));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code div} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code div}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer div(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, a.getFloat(aOffset + _i * 4) / b.getFloat(bOffset + _i * 4));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code div} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code div}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.lang.foreign.MemorySegment div(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.div(dest, destOffset, a, aOffset, b, bOffset, count);
        float[] _destArray, _aArray, _bArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_aArray = heapFloats(a, aOffset)) != null
                && (_bArray = heapFloats(b, bOffset)) != null) {
            div(_destArray, heapIndex(dest, destOffset, 2), _aArray, heapIndex(a, aOffset, 2), _bArray, heapIndex(b, bOffset, 2), count);
            return dest;
        }
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + _i * 4L) / b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + _i * 4L));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code min} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code min}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] min(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 128) return Float4OpsSimd.min(dest, destOffset, a, aOffset, b, bOffset, count);
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = java.lang.Math.min(a[aOffset + _i], b[bOffset + _i]);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code min} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code min}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer min(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer a, int aOffset, java.nio.FloatBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, java.lang.Math.min(a.get(aOffset + _i), b.get(bOffset + _i)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code min} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code min}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer min(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, java.lang.Math.min(a.getFloat(aOffset + _i * 4), b.getFloat(bOffset + _i * 4)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code min} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code min}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.lang.foreign.MemorySegment min(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.min(dest, destOffset, a, aOffset, b, bOffset, count);
        float[] _destArray, _aArray, _bArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_aArray = heapFloats(a, aOffset)) != null
                && (_bArray = heapFloats(b, bOffset)) != null) {
            min(_destArray, heapIndex(dest, destOffset, 2), _aArray, heapIndex(a, aOffset, 2), _bArray, heapIndex(b, bOffset, 2), count);
            return dest;
        }
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, java.lang.Math.min(a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + _i * 4L), b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + _i * 4L)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code max} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code max}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] max(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 128) return Float4OpsSimd.max(dest, destOffset, a, aOffset, b, bOffset, count);
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = java.lang.Math.max(a[aOffset + _i], b[bOffset + _i]);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code max} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code max}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer max(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer a, int aOffset, java.nio.FloatBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, java.lang.Math.max(a.get(aOffset + _i), b.get(bOffset + _i)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code max} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code max}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer max(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, java.lang.Math.max(a.getFloat(aOffset + _i * 4), b.getFloat(bOffset + _i * 4)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code max} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code max}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.lang.foreign.MemorySegment max(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.max(dest, destOffset, a, aOffset, b, bOffset, count);
        float[] _destArray, _aArray, _bArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_aArray = heapFloats(a, aOffset)) != null
                && (_bArray = heapFloats(b, bOffset)) != null) {
            max(_destArray, heapIndex(dest, destOffset, 2), _aArray, heapIndex(a, aOffset, 2), _bArray, heapIndex(b, bOffset, 2), count);
            return dest;
        }
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, java.lang.Math.max(a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + _i * 4L), b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + _i * 4L)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code negate} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code negate}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] negate(float[] dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 128) return Float4OpsSimd.negate(dest, destOffset, src, srcOffset, count);
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = -src[srcOffset + _i];
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code negate} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code negate}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer negate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, -src.get(srcOffset + _i));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code negate} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code negate}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer negate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, -src.getFloat(srcOffset + _i * 4));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code negate} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code negate}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.lang.foreign.MemorySegment negate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.negate(dest, destOffset, src, srcOffset, count);
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            negate(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), count);
            return dest;
        }
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code abs} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code abs}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] abs(float[] dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 128) return Float4OpsSimd.abs(dest, destOffset, src, srcOffset, count);
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = java.lang.Math.abs(src[srcOffset + _i]);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code abs} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code abs}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer abs(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, java.lang.Math.abs(src.get(srcOffset + _i)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code abs} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code abs}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer abs(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, java.lang.Math.abs(src.getFloat(srcOffset + _i * 4)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code abs} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code abs}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.lang.foreign.MemorySegment abs(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.abs(dest, destOffset, src, srcOffset, count);
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            abs(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), count);
            return dest;
        }
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, java.lang.Math.abs(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code lerp} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code lerp}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] lerp(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, float t, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 128) return Float4OpsSimd.lerp(dest, destOffset, a, aOffset, b, bOffset, t, count);
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] + t * (b[bOffset + _i] - a[aOffset + _i]);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code lerp} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code lerp}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer lerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer a, int aOffset, java.nio.FloatBuffer b, int bOffset, float t, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, a.get(aOffset + _i) + t * (b.get(bOffset + _i) - a.get(aOffset + _i)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code lerp} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code lerp}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, float t, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, a.getFloat(aOffset + _i * 4) + t * (b.getFloat(bOffset + _i * 4) - a.getFloat(aOffset + _i * 4)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code lerp} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code lerp}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, float t, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.lerp(dest, destOffset, a, aOffset, b, bOffset, t, count);
        float[] _destArray, _aArray, _bArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_aArray = heapFloats(a, aOffset)) != null
                && (_bArray = heapFloats(b, bOffset)) != null) {
            lerp(_destArray, heapIndex(dest, destOffset, 2), _aArray, heapIndex(a, aOffset, 2), _bArray, heapIndex(b, bOffset, 2), t, count);
            return dest;
        }
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + _i * 4L) + t * (b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + _i * 4L) - a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + _i * 4L)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code scale} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code scale}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] scale(float[] dest, int destOffset, float[] src, int srcOffset, float s, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 128) return Float4OpsSimd.scale(dest, destOffset, src, srcOffset, s, count);
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = src[srcOffset + _i] * s;
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code scale} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code scale}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer scale(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, src.get(srcOffset + _i) * s);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code scale} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code scale}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer scale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, src.getFloat(srcOffset + _i * 4) * s);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code scale} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code scale}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.lang.foreign.MemorySegment scale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.scale(dest, destOffset, src, srcOffset, s, count);
        float[] _destArray, _srcArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_srcArray = heapFloats(src, srcOffset)) != null) {
            scale(_destArray, heapIndex(dest, destOffset, 2), _srcArray, heapIndex(src, srcOffset, 2), s, count);
            return dest;
        }
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L) * s);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code fma} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code fma}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] fma(float[] dest, int destOffset, float[] self, int selfOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 128) return Float4OpsSimd.fma(dest, destOffset, self, selfOffset, a, aOffset, b, bOffset, count);
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = Math.fma(self[selfOffset + _i], a[aOffset + _i], b[bOffset + _i]);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code fma} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code fma}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer self, int selfOffset, java.nio.FloatBuffer a, int aOffset, java.nio.FloatBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, Math.fma(self.get(selfOffset + _i), a.get(aOffset + _i), b.get(bOffset + _i)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code fma} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code fma}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer self, int selfOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, Math.fma(self.getFloat(selfOffset + _i * 4), a.getFloat(aOffset + _i * 4), b.getFloat(bOffset + _i * 4)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code fma} over {@code count} consecutive Float4 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code fma}
     * overload of the same name, which processes exactly one Float4.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.lang.foreign.MemorySegment fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment self, long selfOffset, java.lang.foreign.MemorySegment a, long aOffset, java.lang.foreign.MemorySegment b, long bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.fma(dest, destOffset, self, selfOffset, a, aOffset, b, bOffset, count);
        float[] _destArray, _selfArray, _aArray, _bArray;
        if ((_destArray = heapFloats(dest, destOffset)) != null
                && (_selfArray = heapFloats(self, selfOffset)) != null
                && (_aArray = heapFloats(a, aOffset)) != null
                && (_bArray = heapFloats(b, bOffset)) != null) {
            fma(_destArray, heapIndex(dest, destOffset, 2), _selfArray, heapIndex(self, selfOffset, 2), _aArray, heapIndex(a, aOffset, 2), _bArray, heapIndex(b, bOffset, 2), count);
            return dest;
        }
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 4;
        for (int _i = 0; _i < n; _i++) {
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, Math.fma(self.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, selfOffset + _i * 4L), a.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, aOffset + _i * 4L), b.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, bOffset + _i * 4L)));
        }
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 536870911 ? -1 : count * 4), src.length);
            java.util.Objects.checkFromIndexSize(destOffset, (count > 536870911 ? -1 : count * 4), dest.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.FLOAT_ARRAY_BASE + srcOffset * 4L, dest, UnsafeCopy.FLOAT_ARRAY_BASE + destOffset * 4L, count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src[srcOffset + _i];
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 536870911 ? -1 : count * 4), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, dest, UnsafeCopy.FLOAT_ARRAY_BASE + destOffset * 4L, count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src.get(srcOffset + _i);
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 536870911 ? -1 : count * 4), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, dest, UnsafeCopy.FLOAT_ARRAY_BASE + destOffset * 4L, count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src.getFloat(srcOffset + _i * 4);
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 536870911 ? -1 : count * 4), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, src.address() + srcOffset, dest, UnsafeCopy.FLOAT_ARRAY_BASE + destOffset * 4L, count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L);
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, long src) {
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 536870911 ? -1 : count * 4), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest, UnsafeCopy.FLOAT_ARRAY_BASE + destOffset * 4L, count * 16L);
            return dest;
        }
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, count * 16L), 0L, count);
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 536870911 ? -1 : count * 4), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.FLOAT_ARRAY_BASE + srcOffset * 4L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src[srcOffset + _i]);
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src.get(srcOffset + _i));
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src.getFloat(srcOffset + _i * 4));
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isNative()) {
            UnsafeOpsHolder.U.copyMemory(null, src.address() + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L));
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, long src) {
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 4L, count * 16L);
            return dest;
        }
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, count * 16L), 0L, count);
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 536870911 ? -1 : count * 4), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.FLOAT_ARRAY_BASE + srcOffset * 4L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.putFloat(destOffset + _i * 4, src[srcOffset + _i]);
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.putFloat(destOffset + _i * 4, src.get(srcOffset + _i));
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.putFloat(destOffset + _i * 4, src.getFloat(srcOffset + _i * 4));
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isNative()) {
            UnsafeOpsHolder.U.copyMemory(null, src.address() + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.putFloat(destOffset + _i * 4, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L));
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, long src) {
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 16L);
            return dest;
        }
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, count * 16L), 0L, count);
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 536870911 ? -1 : count * 4), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.FLOAT_ARRAY_BASE + srcOffset * 4L, null, dest.address() + destOffset, count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, src[srcOffset + _i]);
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, null, dest.address() + destOffset, count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, src.get(srcOffset + _i));
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, dest.address() + destOffset, count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, src.getFloat(srcOffset + _i * 4));
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API) return Float4OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) {
            UnsafeOpsHolder.U.copyMemory(null, src.address() + srcOffset, null, dest.address() + destOffset, count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L));
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, long src) {
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, dest.address() + destOffset, count * 16L);
            return dest;
        }
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, count * 16L), 0L, count);
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, float[] src, int srcOffset) {
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, src, srcOffset);
        return dest;
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, float[] src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 536870911 ? -1 : count * 4), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.FLOAT_ARRAY_BASE + srcOffset * 4L, null, dest, count * 16L);
            return dest;
        }
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, count * 16L), 0L, src, srcOffset, count);
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.nio.FloatBuffer src, int srcOffset) {
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, src, srcOffset);
        return dest;
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 4L, null, dest, count * 16L);
            return dest;
        }
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, count * 16L), 0L, src, srcOffset, count);
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.nio.ByteBuffer src, int srcOffset) {
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, src, srcOffset);
        return dest;
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, dest, count * 16L);
            return dest;
        }
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, count * 16L), 0L, src, srcOffset, count);
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.lang.foreign.MemorySegment src, long srcOffset) {
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, src, srcOffset);
        return dest;
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) {
            UnsafeOpsHolder.U.copyMemory(null, src.address() + srcOffset, null, dest, count * 16L);
            return dest;
        }
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, count * 16L), 0L, src, srcOffset, count);
        return dest;
    }

    /**
     * Copy one Float4 (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, long src) {
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Bulk-copy {@code count} consecutive Float4 values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, dest, count * 16L);
            return dest;
        }
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, count * 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, count * 16L), 0L, count);
        return dest;
    }
    /** The float[] behind {@code s} when it is a writable heap segment over one whose byte
     *  {@code offset} starts a whole element; null otherwise (native, read-only, another array type,
     *  misaligned) - the segment path then serves the call. Neither the extent nor the segment's
     *  liveness or owner thread is checked: the caller guarantees all three. */
    private static float[] heapFloats(java.lang.foreign.MemorySegment s, long offset) {
        if (s.isNative() || ((s.address() + offset) & 3L) != 0) return null;
        return s.heapBase().orElse(null) instanceof float[] a ? a : null;
    }

    /** The array index of byte {@code offset} of a heap segment, for elements of {@code 1 << shift} bytes. */
    private static int heapIndex(java.lang.foreign.MemorySegment s, long offset, int shift) {
        return (int) ((s.address() + offset) >>> shift);
    }

    /**
     * The floored remainder of x and y, exactly kotlin.Float.mod: q = floor(x / y) is off by
     * at most one (too large) while it fits the mantissa, so x - y * q with one correction is
     * the floored remainder - a zero one with the sign of x, like x % y; % (a runtime call) only
     * when it does not fit or y is infinite.
     */
    private static float flooredMod(float x, float y) {
        float q = Math.floor(x / y);
        if (java.lang.Math.abs(q) < 0x1p24f && java.lang.Math.abs(y) <= Float.MAX_VALUE) {
            float r = java.lang.Math.fma(-y, q, x);
            if (r * java.lang.Math.signum(y) < 0) r = java.lang.Math.fma(-y, (q - 1.0f), x);
            return r == 0 ? java.lang.Math.copySign(r, x) : r;
        }
        float r = x % y;
        return r * java.lang.Math.signum(y) < 0 ? r + y : r;
    }
}
