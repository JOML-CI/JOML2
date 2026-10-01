// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.ops;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.kernels.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Static, allocation-free operations on raw storage holding a {@link FloatQuat}.
 *
 * <p>Each method takes one or more buffers ({@code float[]},
 * {@link java.nio.FloatBuffer}, {@link java.nio.ByteBuffer}, or
 * {@link java.lang.foreign.MemorySegment}) plus an element/byte offset and operates
 * directly on that storage. No {@link FloatQuat} instance is allocated;
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
 * FloatQuat write and read.</p>
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
 * <p>Each method summary below is the one the {@link FloatQuat} API carries, so
 * the two can never describe the same operation differently: "this quaternion" there is the
 * quaternion held in {@code src} at {@code srcOffset}, and the result is written to
 * {@code dest} at {@code destOffset}. The full text sits on the {@code float[]} overload of
 * each method; the other storage overloads point at it, differing from it in
 * storage alone.</p>
 */
public final class FloatQuatOps {
    private FloatQuatOps() {}

    /**
     * Invert this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] invert(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t3_inv = 1.0f / Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        dest[destOffset + 0] = -(_selfx * _t3_inv);
        dest[destOffset + 1] = -(_selfy * _t3_inv);
        dest[destOffset + 2] = -(_selfz * _t3_inv);
        dest[destOffset + 3] = _selfw * _t3_inv;
        return dest;
    }

    /** {@link #invert(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer invert(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.invert_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.invert_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invert(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invert(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.invert_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.invert_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invert(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment invert(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.invert_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.invert_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invert(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long invert(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.invert_unsafe(dest, src);
        invert(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the inverse of the product of this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}), i.e. {@code (this * other)^-1} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: this quaternion must be non-zero; {@code (otherX, otherY, otherZ, otherW)} must
     * be non-zero.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @return {@code dest}
     */
    public static float[] invertProduct(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t20 = Math.fma(otherX, _selfw, otherW * _selfx) + Math.fma(otherZ, _selfy, -(otherY * _selfz));
        float _t21 = Math.fma(otherW, _selfw, -(otherX * _selfx)) - Math.fma(otherY, _selfy, otherZ * _selfz);
        float _t22 = Math.fma(otherY, _selfx, otherZ * _selfw) + Math.fma(otherW, _selfz, -(otherX * _selfy));
        float _t23 = Math.fma(otherX, _selfz, otherW * _selfy) + Math.fma(otherY, _selfw, -(otherZ * _selfx));
        float _t27_inv = 1.0f / Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t20 * _t20)));
        dest[destOffset + 0] = -(_t20 * _t27_inv);
        dest[destOffset + 1] = -(_t23 * _t27_inv);
        dest[destOffset + 2] = -(_t22 * _t27_inv);
        dest[destOffset + 3] = _t21 * _t27_inv;
        return dest;
    }

    /** {@link #invertProduct(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer invertProduct(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.invertProduct_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsTypedBuffer.invertProduct_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #invertProduct(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invertProduct(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.invertProduct_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsByteBuffer.invertProduct_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #invertProduct(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment invertProduct(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.invertProduct_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsSegment.invertProduct_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #invertProduct(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long invertProduct(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.invertProduct_unsafe(dest, src, otherX, otherY, otherZ, otherW);
        invertProduct(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW);
        return dest;
    }

    /**
     * Compute the inverse of the product of this quaternion and {@code other}, i.e.
     * {@code (this * other)^-1} and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must be non-zero; {@code other} must be non-zero.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param other the storage holding the right factor of the product
     * @param otherOffset the element index in {@code other} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] invertProduct(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        float _t20 = Math.fma(_otherx, _selfw, _otherw * _selfx) + Math.fma(_otherz, _selfy, -(_othery * _selfz));
        float _t21 = Math.fma(_otherw, _selfw, -(_otherx * _selfx)) - Math.fma(_othery, _selfy, _otherz * _selfz);
        float _t22 = Math.fma(_othery, _selfx, _otherz * _selfw) + Math.fma(_otherw, _selfz, -(_otherx * _selfy));
        float _t23 = Math.fma(_otherx, _selfz, _otherw * _selfy) + Math.fma(_othery, _selfw, -(_otherz * _selfx));
        float _t27_inv = 1.0f / Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t20 * _t20)));
        dest[destOffset + 0] = -(_t20 * _t27_inv);
        dest[destOffset + 1] = -(_t23 * _t27_inv);
        dest[destOffset + 2] = -(_t22 * _t27_inv);
        dest[destOffset + 3] = _t21 * _t27_inv;
        return dest;
    }

    /** {@link #invertProduct(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer invertProduct(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.invertProduct_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsTypedBuffer.invertProduct_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #invertProduct(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invertProduct(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.invertProduct_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsByteBuffer.invertProduct_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #invertProduct(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment invertProduct(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return FloatQuatOpsKernelsSegment.invertProduct_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsSegment.invertProduct_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #invertProduct(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long invertProduct(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.invertProduct_unsafe(dest, src, other);
        invertProduct(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
        return dest;
    }

    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) to this quaternion and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @return {@code dest}
     */
    public static float[] add(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset + 0] = otherX + _selfx;
        dest[destOffset + 1] = otherY + _selfy;
        dest[destOffset + 2] = otherZ + _selfz;
        dest[destOffset + 3] = otherW + _selfw;
        return dest;
    }

    /** {@link #add(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer add(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.add_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsTypedBuffer.add_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #add(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer add(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.add_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsByteBuffer.add_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #add(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment add(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.add_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsSegment.add_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #add(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long add(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.add_unsafe(dest, src, otherX, otherY, otherZ, otherW);
        add(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW);
        return dest;
    }

    /**
     * Add {@code other} to this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param other the storage holding the quaternion to add
     * @param otherOffset the element index in {@code other} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] add(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.add(dest, destOffset, src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsArray.add_scalar(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer add(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.add_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsTypedBuffer.add_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer add(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.add_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsByteBuffer.add_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment add(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.add(dest, destOffset, src, srcOffset, other, otherOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return FloatQuatOpsKernelsSegment.add_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsSegment.add_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long add(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.add_unsafe(dest, src, other);
        add(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
        return dest;
    }

    /**
     * Multiply each component of this quaternion by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param scalar the factor to multiply each component by
     * @return {@code dest}
     */
    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.mul(dest, destOffset, src, srcOffset, scalar);
        return FloatQuatOpsKernelsArray.mul_scalar(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.mul_unsafe(dest, destOffset, src, srcOffset, scalar);
        return FloatQuatOpsKernelsTypedBuffer.mul_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.mul_unsafe(dest, destOffset, src, srcOffset, scalar);
        return FloatQuatOpsKernelsByteBuffer.mul_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.mul(dest, destOffset, src, srcOffset, scalar);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.mul_unsafe(dest, destOffset, src, srcOffset, scalar);
        return FloatQuatOpsKernelsSegment.mul_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mul(long dest, long src, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.mul_unsafe(dest, src, scalar);
        mul(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, scalar);
        return dest;
    }

    /**
     * Negate this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] negate(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.negate(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsArray.negate_scalar(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer negate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.negate_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.negate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer negate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.negate_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.negate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment negate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.negate(dest, destOffset, src, srcOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.negate_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.negate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long negate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.negate_unsafe(dest, src);
        negate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) from this
     * quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @return {@code dest}
     */
    public static float[] sub(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset + 0] = _selfx - otherX;
        dest[destOffset + 1] = _selfy - otherY;
        dest[destOffset + 2] = _selfz - otherZ;
        dest[destOffset + 3] = _selfw - otherW;
        return dest;
    }

    /** {@link #sub(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer sub(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.sub_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsTypedBuffer.sub_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #sub(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sub(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.sub_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsByteBuffer.sub_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #sub(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment sub(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.sub_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsSegment.sub_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #sub(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long sub(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.sub_unsafe(dest, src, otherX, otherY, otherZ, otherW);
        sub(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW);
        return dest;
    }

    /**
     * Subtract {@code other} from this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param other the storage holding the quaternion to subtract
     * @param otherOffset the element index in {@code other} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] sub(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.sub(dest, destOffset, src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsArray.sub_scalar(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer sub(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.sub_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsTypedBuffer.sub_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sub(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.sub_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsByteBuffer.sub_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment sub(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.sub(dest, destOffset, src, srcOffset, other, otherOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return FloatQuatOpsKernelsSegment.sub_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsSegment.sub_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long sub(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.sub_unsafe(dest, src, other);
        sub(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
        return dest;
    }

    /**
     * Set this quaternion to the unit quaternion
     * {@code (sqrt(1 - u1) sin(2 PI u2), sqrt(1 - u1) cos(2 PI u2), sqrt(u1) sin(2 PI u3), sqrt(u1) cos(2 PI u3))},
     * Shoemake's construction: samples uniformly distributed in {@code [0, 1)} give a rotation
     * uniformly distributed over all rotations ({@code makeRandomRotation} draws them from a
     * {@link java.util.Random}).
     * <p>
     * Valid input: {@code u1} must lie in {@code [0, 1]}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param u1 the sample that splits the unit length between {@code (x, y)} and {@code (z, w)},
     *        uniformly distributed in {@code [0, 1)} for a uniformly distributed rotation
     * @param u2 the fraction of a full turn of {@code (x, y)}, uniformly distributed in
     *        {@code [0, 1)} for a uniformly distributed rotation
     * @param u3 the fraction of a full turn of {@code (z, w)}, uniformly distributed in
     *        {@code [0, 1)} for a uniformly distributed rotation
     * @return {@code dest}
     */
    public static float[] makeUniformRotation(float[] dest, int destOffset, float u1, float u2, float u3) {
        float _t0 = (float) Math.sqrt(u1);
        float _t1 = u2 * 6.2831855f;
        float _t3 = u3 * 6.2831855f;
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sqrt(1.0f - u1);
        float _t6 = (float) Math.sin(_t3);
        dest[destOffset + 0] = _t4 * _t5;
        dest[destOffset + 1] = (float) Math.cosFromSin(_t4, _t1) * _t5;
        dest[destOffset + 2] = _t6 * _t0;
        dest[destOffset + 3] = (float) Math.cosFromSin(_t6, _t3) * _t0;
        return dest;
    }

    /** {@link #makeUniformRotation(float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeUniformRotation(java.nio.FloatBuffer dest, int destOffset, float u1, float u2, float u3) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeUniformRotation_unsafe(dest, destOffset, u1, u2, u3);
        return FloatQuatOpsKernelsTypedBuffer.makeUniformRotation_api(dest, destOffset, u1, u2, u3);
    }

    /** {@link #makeUniformRotation(float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeUniformRotation(java.nio.ByteBuffer dest, int destOffset, float u1, float u2, float u3) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeUniformRotation_unsafe(dest, destOffset, u1, u2, u3);
        return FloatQuatOpsKernelsByteBuffer.makeUniformRotation_api(dest, destOffset, u1, u2, u3);
    }

    /** {@link #makeUniformRotation(float[], int, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeUniformRotation(java.lang.foreign.MemorySegment dest, long destOffset, float u1, float u2, float u3) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.makeUniformRotation_unsafe(dest, destOffset, u1, u2, u3);
        return FloatQuatOpsKernelsSegment.makeUniformRotation_api(dest, destOffset, u1, u2, u3);
    }

    /** {@link #makeUniformRotation(float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeUniformRotation(long dest, float u1, float u2, float u3) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeUniformRotation_unsafe(dest, u1, u2, u3);
        makeUniformRotation(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, u1, u2, u3);
        return dest;
    }

    /**
     * Set this quaternion to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param vX the {@code x} component of the quaternion {@code (vX, vY, vZ, vW)}
     * @param vY the {@code y} component of the quaternion {@code (vX, vY, vZ, vW)}
     * @param vZ the {@code z} component of the quaternion {@code (vX, vY, vZ, vW)}
     * @param vW the {@code w} component of the quaternion {@code (vX, vY, vZ, vW)}
     * @return {@code dest}
     */
    public static float[] set(float[] dest, int destOffset, float vX, float vY, float vZ, float vW) {
        dest[destOffset + 0] = vX;
        dest[destOffset + 1] = vY;
        dest[destOffset + 2] = vZ;
        dest[destOffset + 3] = vW;
        return dest;
    }

    /** {@link #set(float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer set(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ, float vW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.set_unsafe(dest, destOffset, vX, vY, vZ, vW);
        return FloatQuatOpsKernelsTypedBuffer.set_api(dest, destOffset, vX, vY, vZ, vW);
    }

    /** {@link #set(float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer set(java.nio.ByteBuffer dest, int destOffset, float vX, float vY, float vZ, float vW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.set_unsafe(dest, destOffset, vX, vY, vZ, vW);
        return FloatQuatOpsKernelsByteBuffer.set_api(dest, destOffset, vX, vY, vZ, vW);
    }

    /** {@link #set(float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment set(java.lang.foreign.MemorySegment dest, long destOffset, float vX, float vY, float vZ, float vW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.set_unsafe(dest, destOffset, vX, vY, vZ, vW);
        return FloatQuatOpsKernelsSegment.set_api(dest, destOffset, vX, vY, vZ, vW);
    }

    /** {@link #set(float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long set(long dest, float vX, float vY, float vZ, float vW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.set_unsafe(dest, vX, vY, vZ, vW);
        set(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, vX, vY, vZ, vW);
        return dest;
    }

    /**
     * Set this quaternion to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param v the storage holding the quaternion to copy
     * @param vOffset the element index in {@code v} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] set(float[] dest, int destOffset, float[] v, int vOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.set(dest, destOffset, v, vOffset);
        return FloatQuatOpsKernelsArray.set_scalar(dest, destOffset, v, vOffset);
    }

    /** {@link #set(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer set(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.set_unsafe(dest, destOffset, v, vOffset);
        return FloatQuatOpsKernelsTypedBuffer.set_api(dest, destOffset, v, vOffset);
    }

    /** {@link #set(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer set(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.set_unsafe(dest, destOffset, v, vOffset);
        return FloatQuatOpsKernelsByteBuffer.set_api(dest, destOffset, v, vOffset);
    }

    /** {@link #set(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment set(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.set(dest, destOffset, v, vOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && v.isNative()) return FloatQuatOpsKernelsSegment.set_unsafe(dest, destOffset, v, vOffset);
        return FloatQuatOpsKernelsSegment.set_api(dest, destOffset, v, vOffset);
    }

    /** {@link #set(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long set(long dest, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.set_unsafe(dest, v);
        set(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 16L), 0L);
        return dest;
    }

    /**
     * Set this quaternion to the rotation (real) part of the unit dual quaternion ({@code dqRX},
     * {@code dqRY}, {@code dqRZ}, {@code dqRW}, {@code dqDX}, {@code dqDY}, {@code dqDZ},
     * {@code dqDW}).
     * <p>
     * Valid input: {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)} must be a unit dual
     * quaternion.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
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
    public static float[] makeFromDualQuat(float[] dest, int destOffset, float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        dest[destOffset + 0] = dqRX;
        dest[destOffset + 1] = dqRY;
        dest[destOffset + 2] = dqRZ;
        dest[destOffset + 3] = dqRW;
        return dest;
    }

    /** {@link #makeFromDualQuat(float[], int, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeFromDualQuat(java.nio.FloatBuffer dest, int destOffset, float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeFromDualQuat_unsafe(dest, destOffset, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
        return FloatQuatOpsKernelsTypedBuffer.makeFromDualQuat_api(dest, destOffset, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
    }

    /** {@link #makeFromDualQuat(float[], int, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeFromDualQuat(java.nio.ByteBuffer dest, int destOffset, float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeFromDualQuat_unsafe(dest, destOffset, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
        return FloatQuatOpsKernelsByteBuffer.makeFromDualQuat_api(dest, destOffset, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
    }

    /** {@link #makeFromDualQuat(float[], int, float, float, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeFromDualQuat(java.lang.foreign.MemorySegment dest, long destOffset, float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.makeFromDualQuat_unsafe(dest, destOffset, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
        return FloatQuatOpsKernelsSegment.makeFromDualQuat_api(dest, destOffset, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
    }

    /** {@link #makeFromDualQuat(float[], int, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeFromDualQuat(long dest, float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeFromDualQuat_unsafe(dest, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
        makeFromDualQuat(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW);
        return dest;
    }

    /**
     * Set this quaternion to the rotation represented by the given matrix (which must be a
     * rotation: orthonormal, with determinant +1 - a scaled or sheared block gives a wrong
     * quaternion, not a longer one; {@code getNormalizedRotation} strips scale first).
     * <p>
     * Valid input: {@code m} must be a rotation matrix.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param m the storage holding the matrix to convert
     * @param mOffset the element index in {@code m} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] makeFromMatrixMat3x3(float[] dest, int destOffset, float[] m, int mOffset) {
        float _m00 = m[mOffset + 0];
        float _m10 = m[mOffset + 1];
        float _m20 = m[mOffset + 2];
        float _m01 = m[mOffset + 3];
        float _m11 = m[mOffset + 4];
        float _m21 = m[mOffset + 5];
        float _m02 = m[mOffset + 6];
        float _m12 = m[mOffset + 7];
        float _m22 = m[mOffset + 8];
        float _t0 = _m00 + _m11;
        float _t10 = _m22 + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (_m00 - (_m11 + _m22));
        float _t16 = 1.0f + (_m11 - (_m00 + _m22));
        float _t17 = 1.0f + (_m22 - _t0);
        return makeFromMatrixMat3x3_s172ce120_1(dest, destOffset, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) Math.sqrt(_t14)), 0.5f * (1.0f / (float) Math.sqrt(_t16)), 0.5f * (1.0f / (float) Math.sqrt(_t17)), 0.5f * (1.0f / (float) Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat3x3}, split to fit the inline budget; reached only through it. */
    private static float[] makeFromMatrixMat3x3_s172ce120_1(float[] dest, int destOffset, float _m00, float _m11, float _m22, float _t1, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t10 > 0.0f) {
            dest[destOffset + 0] = _sp0 * _t1;
            dest[destOffset + 1] = _sp0 * _t7;
            dest[destOffset + 2] = _sp0 * _t9;
            dest[destOffset + 3] = 0.5f * (float) Math.sqrt(_t14);
        } else {
            if (_m00 > Math.max(_m11, _m22)) {
                dest[destOffset + 0] = 0.5f * (float) Math.sqrt(_t15);
                dest[destOffset + 1] = _sp3 * _t4;
                dest[destOffset + 2] = _sp3 * _t6;
                dest[destOffset + 3] = _sp3 * _t1;
            } else {
                if (_m11 > _m22) {
                    dest[destOffset + 0] = _sp1 * _t4;
                    dest[destOffset + 1] = 0.5f * (float) Math.sqrt(_t16);
                    dest[destOffset + 2] = _sp1 * _t8;
                    dest[destOffset + 3] = _sp1 * _t7;
                } else {
                    dest[destOffset + 0] = _sp2 * _t6;
                    dest[destOffset + 1] = _sp2 * _t8;
                    dest[destOffset + 2] = 0.5f * (float) Math.sqrt(_t17);
                    dest[destOffset + 3] = _sp2 * _t9;
                }
            }
        }
        return dest;
    }

    /** {@link #makeFromMatrixMat3x3(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeFromMatrixMat3x3(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeFromMatrixMat3x3_unsafe(dest, destOffset, m, mOffset);
        return FloatQuatOpsKernelsTypedBuffer.makeFromMatrixMat3x3_api(dest, destOffset, m, mOffset);
    }

    /** {@link #makeFromMatrixMat3x3(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeFromMatrixMat3x3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeFromMatrixMat3x3_unsafe(dest, destOffset, m, mOffset);
        return FloatQuatOpsKernelsByteBuffer.makeFromMatrixMat3x3_api(dest, destOffset, m, mOffset);
    }

    /** {@link #makeFromMatrixMat3x3(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeFromMatrixMat3x3(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && m.isNative()) return FloatQuatOpsKernelsSegment.makeFromMatrixMat3x3_unsafe(dest, destOffset, m, mOffset);
        return FloatQuatOpsKernelsSegment.makeFromMatrixMat3x3_api(dest, destOffset, m, mOffset);
    }

    /** {@link #makeFromMatrixMat3x3(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeFromMatrixMat3x3(long dest, long m) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeFromMatrixMat3x3_unsafe(dest, m);
        makeFromMatrixMat3x3(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(m, 36L), 0L);
        return dest;
    }

    /**
     * Set this quaternion to the rotation represented by the given matrix (which must be a
     * rotation: orthonormal, with determinant +1 - a scaled or sheared block gives a wrong
     * quaternion, not a longer one; {@code getNormalizedRotation} strips scale first).
     * <p>
     * Valid input: {@code m} must be a rotation matrix.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param m the storage holding the matrix to convert
     * @param mOffset the element index in {@code m} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] makeFromMatrixMat3x4(float[] dest, int destOffset, float[] m, int mOffset) {
        float _m00 = m[mOffset + 0];
        float _m01 = m[mOffset + 1];
        float _m02 = m[mOffset + 2];
        float _m10 = m[mOffset + 4];
        float _m11 = m[mOffset + 5];
        float _m12 = m[mOffset + 6];
        float _m20 = m[mOffset + 8];
        float _m21 = m[mOffset + 9];
        float _m22 = m[mOffset + 10];
        float _t0 = _m00 + _m11;
        float _t10 = _m22 + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (_m00 - (_m11 + _m22));
        float _t16 = 1.0f + (_m11 - (_m00 + _m22));
        float _t17 = 1.0f + (_m22 - _t0);
        return makeFromMatrixMat3x4_sfde6fd33_1(dest, destOffset, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) Math.sqrt(_t14)), 0.5f * (1.0f / (float) Math.sqrt(_t16)), 0.5f * (1.0f / (float) Math.sqrt(_t17)), 0.5f * (1.0f / (float) Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat3x4}, split to fit the inline budget; reached only through it. */
    private static float[] makeFromMatrixMat3x4_sfde6fd33_1(float[] dest, int destOffset, float _m00, float _m11, float _m22, float _t1, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t10 > 0.0f) {
            dest[destOffset + 0] = _sp0 * _t1;
            dest[destOffset + 1] = _sp0 * _t7;
            dest[destOffset + 2] = _sp0 * _t9;
            dest[destOffset + 3] = 0.5f * (float) Math.sqrt(_t14);
        } else {
            if (_m00 > Math.max(_m11, _m22)) {
                dest[destOffset + 0] = 0.5f * (float) Math.sqrt(_t15);
                dest[destOffset + 1] = _sp3 * _t4;
                dest[destOffset + 2] = _sp3 * _t6;
                dest[destOffset + 3] = _sp3 * _t1;
            } else {
                if (_m11 > _m22) {
                    dest[destOffset + 0] = _sp1 * _t4;
                    dest[destOffset + 1] = 0.5f * (float) Math.sqrt(_t16);
                    dest[destOffset + 2] = _sp1 * _t8;
                    dest[destOffset + 3] = _sp1 * _t7;
                } else {
                    dest[destOffset + 0] = _sp2 * _t6;
                    dest[destOffset + 1] = _sp2 * _t8;
                    dest[destOffset + 2] = 0.5f * (float) Math.sqrt(_t17);
                    dest[destOffset + 3] = _sp2 * _t9;
                }
            }
        }
        return dest;
    }

    /** {@link #makeFromMatrixMat3x4(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeFromMatrixMat3x4(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeFromMatrixMat3x4_unsafe(dest, destOffset, m, mOffset);
        return FloatQuatOpsKernelsTypedBuffer.makeFromMatrixMat3x4_api(dest, destOffset, m, mOffset);
    }

    /** {@link #makeFromMatrixMat3x4(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeFromMatrixMat3x4(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeFromMatrixMat3x4_unsafe(dest, destOffset, m, mOffset);
        return FloatQuatOpsKernelsByteBuffer.makeFromMatrixMat3x4_api(dest, destOffset, m, mOffset);
    }

    /** {@link #makeFromMatrixMat3x4(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeFromMatrixMat3x4(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && m.isNative()) return FloatQuatOpsKernelsSegment.makeFromMatrixMat3x4_unsafe(dest, destOffset, m, mOffset);
        return FloatQuatOpsKernelsSegment.makeFromMatrixMat3x4_api(dest, destOffset, m, mOffset);
    }

    /** {@link #makeFromMatrixMat3x4(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeFromMatrixMat3x4(long dest, long m) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeFromMatrixMat3x4_unsafe(dest, m);
        makeFromMatrixMat3x4(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(m, 48L), 0L);
        return dest;
    }

    /**
     * Set this quaternion to the rotation represented by the given matrix (which must be a
     * rotation: orthonormal, with determinant +1 - a scaled or sheared block gives a wrong
     * quaternion, not a longer one; {@code getNormalizedRotation} strips scale first).
     * <p>
     * Valid input: {@code m} must be a rotation matrix.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param m the storage holding the matrix to convert
     * @param mOffset the element index in {@code m} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] makeFromMatrixMat4x4(float[] dest, int destOffset, float[] m, int mOffset) {
        float _m00 = m[mOffset + 0];
        float _m10 = m[mOffset + 1];
        float _m20 = m[mOffset + 2];
        float _m01 = m[mOffset + 4];
        float _m11 = m[mOffset + 5];
        float _m21 = m[mOffset + 6];
        float _m02 = m[mOffset + 8];
        float _m12 = m[mOffset + 9];
        float _m22 = m[mOffset + 10];
        float _t0 = _m00 + _m11;
        float _t10 = _m22 + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (_m00 - (_m11 + _m22));
        float _t16 = 1.0f + (_m11 - (_m00 + _m22));
        float _t17 = 1.0f + (_m22 - _t0);
        return makeFromMatrixMat4x4_sd012cc48_1(dest, destOffset, _m00, _m11, _m22, _m21 - _m12, _m01 + _m10, _m02 + _m20, _m02 - _m20, _m12 + _m21, _m10 - _m01, _t10, _t14, _t15, _t16, _t17, 0.5f * (1.0f / (float) Math.sqrt(_t14)), 0.5f * (1.0f / (float) Math.sqrt(_t16)), 0.5f * (1.0f / (float) Math.sqrt(_t17)), 0.5f * (1.0f / (float) Math.sqrt(_t15)));
    }

    /** Piece 2 of {@code makeFromMatrixMat4x4}, split to fit the inline budget; reached only through it. */
    private static float[] makeFromMatrixMat4x4_sd012cc48_1(float[] dest, int destOffset, float _m00, float _m11, float _m22, float _t1, float _t4, float _t6, float _t7, float _t8, float _t9, float _t10, float _t14, float _t15, float _t16, float _t17, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (_t10 > 0.0f) {
            dest[destOffset + 0] = _sp0 * _t1;
            dest[destOffset + 1] = _sp0 * _t7;
            dest[destOffset + 2] = _sp0 * _t9;
            dest[destOffset + 3] = 0.5f * (float) Math.sqrt(_t14);
        } else {
            if (_m00 > Math.max(_m11, _m22)) {
                dest[destOffset + 0] = 0.5f * (float) Math.sqrt(_t15);
                dest[destOffset + 1] = _sp3 * _t4;
                dest[destOffset + 2] = _sp3 * _t6;
                dest[destOffset + 3] = _sp3 * _t1;
            } else {
                if (_m11 > _m22) {
                    dest[destOffset + 0] = _sp1 * _t4;
                    dest[destOffset + 1] = 0.5f * (float) Math.sqrt(_t16);
                    dest[destOffset + 2] = _sp1 * _t8;
                    dest[destOffset + 3] = _sp1 * _t7;
                } else {
                    dest[destOffset + 0] = _sp2 * _t6;
                    dest[destOffset + 1] = _sp2 * _t8;
                    dest[destOffset + 2] = 0.5f * (float) Math.sqrt(_t17);
                    dest[destOffset + 3] = _sp2 * _t9;
                }
            }
        }
        return dest;
    }

    /** {@link #makeFromMatrixMat4x4(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeFromMatrixMat4x4(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeFromMatrixMat4x4_unsafe(dest, destOffset, m, mOffset);
        return FloatQuatOpsKernelsTypedBuffer.makeFromMatrixMat4x4_api(dest, destOffset, m, mOffset);
    }

    /** {@link #makeFromMatrixMat4x4(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeFromMatrixMat4x4(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer m, int mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && m.isDirect() && m.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeFromMatrixMat4x4_unsafe(dest, destOffset, m, mOffset);
        return FloatQuatOpsKernelsByteBuffer.makeFromMatrixMat4x4_api(dest, destOffset, m, mOffset);
    }

    /** {@link #makeFromMatrixMat4x4(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeFromMatrixMat4x4(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && m.isNative()) return FloatQuatOpsKernelsSegment.makeFromMatrixMat4x4_unsafe(dest, destOffset, m, mOffset);
        return FloatQuatOpsKernelsSegment.makeFromMatrixMat4x4_api(dest, destOffset, m, mOffset);
    }

    /** {@link #makeFromMatrixMat4x4(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeFromMatrixMat4x4(long dest, long m) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeFromMatrixMat4x4_unsafe(dest, m);
        makeFromMatrixMat4x4(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(m, 64L), 0L);
        return dest;
    }

    /**
     * Convert this quaternion to a pure-rotation dual quaternion (zero dual part) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the dual quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] toDualQuat(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.toDualQuat(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsArray.toDualQuat_scalar(dest, destOffset, src, srcOffset);
    }

    /** {@link #toDualQuat(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer toDualQuat(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.toDualQuat_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.toDualQuat_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toDualQuat(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer toDualQuat(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.toDualQuat_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.toDualQuat_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toDualQuat(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment toDualQuat(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.toDualQuat(dest, destOffset, src, srcOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.toDualQuat_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.toDualQuat_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toDualQuat(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long toDualQuat(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.toDualQuat_unsafe(dest, src);
        toDualQuat(VirtualMemoryHolder.virtualMemory().asSlice(dest, 32L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the matrix representation of this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] toMatrix(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest[destOffset + 0] = Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f);
        dest[destOffset + 1] = 2.0f * Math.fma(_selfx, _selfy, _t1);
        dest[destOffset + 2] = 2.0f * Math.fma(_selfx, _selfz, -_t2);
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 2.0f * Math.fma(_selfx, _selfy, -_t1);
        dest[destOffset + 5] = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f);
        dest[destOffset + 6] = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 2.0f * Math.fma(_selfx, _selfz, _t2);
        dest[destOffset + 9] = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        dest[destOffset + 10] = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        dest[destOffset + 11] = 0.0f;
        dest[destOffset + 12] = 0.0f;
        dest[destOffset + 13] = 0.0f;
        dest[destOffset + 14] = 0.0f;
        dest[destOffset + 15] = 1.0f;
        return dest;
    }

    /** {@link #toMatrix(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer toMatrix(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.toMatrix_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.toMatrix_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toMatrix(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer toMatrix(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.toMatrix_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.toMatrix_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toMatrix(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment toMatrix(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.toMatrix_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.toMatrix_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toMatrix(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long toMatrix(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.toMatrix_unsafe(dest, src);
        toMatrix(VirtualMemoryHolder.virtualMemory().asSlice(dest, 64L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the 3x3 rotation matrix representation of this quaternion and store the result in
     * {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] toMatrix3x3(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest[destOffset + 0] = Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f);
        dest[destOffset + 1] = 2.0f * Math.fma(_selfx, _selfy, _t1);
        dest[destOffset + 2] = 2.0f * Math.fma(_selfx, _selfz, -_t2);
        dest[destOffset + 3] = 2.0f * Math.fma(_selfx, _selfy, -_t1);
        dest[destOffset + 4] = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f);
        dest[destOffset + 5] = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        dest[destOffset + 6] = 2.0f * Math.fma(_selfx, _selfz, _t2);
        dest[destOffset + 7] = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        dest[destOffset + 8] = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        return dest;
    }

    /** {@link #toMatrix3x3(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer toMatrix3x3(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.toMatrix3x3_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.toMatrix3x3_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toMatrix3x3(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer toMatrix3x3(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.toMatrix3x3_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.toMatrix3x3_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toMatrix3x3(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment toMatrix3x3(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.toMatrix3x3_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.toMatrix3x3_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toMatrix3x3(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long toMatrix3x3(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.toMatrix3x3_unsafe(dest, src);
        toMatrix3x3(VirtualMemoryHolder.virtualMemory().asSlice(dest, 36L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the 3x4 matrix representation of this quaternion (the omitted last row is implicitly
     * {@code 0, 0, 0, 1}) and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] toMatrix3x4(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = _selfz * _selfz;
        float _t1 = _selfz * _selfw;
        float _t2 = _selfy * _selfw;
        dest[destOffset + 0] = Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f);
        dest[destOffset + 1] = 2.0f * Math.fma(_selfx, _selfy, -_t1);
        dest[destOffset + 2] = 2.0f * Math.fma(_selfx, _selfz, _t2);
        dest[destOffset + 3] = 0.0f;
        dest[destOffset + 4] = 2.0f * Math.fma(_selfx, _selfy, _t1);
        dest[destOffset + 5] = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f);
        dest[destOffset + 6] = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        dest[destOffset + 7] = 0.0f;
        dest[destOffset + 8] = 2.0f * Math.fma(_selfx, _selfz, -_t2);
        dest[destOffset + 9] = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        dest[destOffset + 10] = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        dest[destOffset + 11] = 0.0f;
        return dest;
    }

    /** {@link #toMatrix3x4(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer toMatrix3x4(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.toMatrix3x4_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.toMatrix3x4_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toMatrix3x4(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer toMatrix3x4(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.toMatrix3x4_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.toMatrix3x4_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toMatrix3x4(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment toMatrix3x4(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.toMatrix3x4_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.toMatrix3x4_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #toMatrix3x4(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long toMatrix3x4(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.toMatrix3x4_unsafe(dest, src);
        toMatrix3x4(VirtualMemoryHolder.virtualMemory().asSlice(dest, 48L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Decompose this quaternion into a swing about an axis perpendicular to ({@code axisX},
     * {@code axisY}, {@code axisZ}) followed by a twist about ({@code axisX}, {@code axisY},
     * {@code axisZ}), storing them in {@code swing} and {@code twist} respectively, such that
     * {@code swing * twist} is this rotation.
     * <p>
     * Equivalent to calling {@code getSwing} and {@code getTwist} separately, but shares the work.
     * The twist is the identity when the rotation is a pure swing, including the 180-degree
     * perpendicular case where it is undefined.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length; this quaternion must have
     * unit length.
     *
     * @param swing will hold the swing
     * @param swingOffset the element index in {@code swing} at which the quaternion starts
     * @param twist will hold the twist
     * @param twistOffset the element index in {@code twist} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return {@code swing}
     */
    public static float[] decomposeSwingTwist(float[] swing, int swingOffset, float[] twist, int twistOffset, float[] src, int srcOffset, float axisX, float axisY, float axisZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t2 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        float _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = _selfw * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        swing[swingOffset + 0] = Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14));
        swing[swingOffset + 1] = Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12));
        swing[swingOffset + 2] = Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14));
        swing[swingOffset + 3] = Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13));
        twist[twistOffset + 0] = _t12;
        twist[twistOffset + 1] = _t13;
        twist[twistOffset + 2] = _t14;
        twist[twistOffset + 3] = _t11;
        return swing;
    }

    /** {@link #decomposeSwingTwist(float[], int, float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer decomposeSwingTwist(java.nio.FloatBuffer swing, int swingOffset, java.nio.FloatBuffer twist, int twistOffset, java.nio.FloatBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && swing.isDirect() && !swing.isReadOnly() && swing.order() == java.nio.ByteOrder.nativeOrder() && twist.isDirect() && !twist.isReadOnly() && twist.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.decomposeSwingTwist_unsafe(swing, swingOffset, twist, twistOffset, src, srcOffset, axisX, axisY, axisZ);
        return FloatQuatOpsKernelsTypedBuffer.decomposeSwingTwist_api(swing, swingOffset, twist, twistOffset, src, srcOffset, axisX, axisY, axisZ);
    }

    /** {@link #decomposeSwingTwist(float[], int, float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer decomposeSwingTwist(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, java.nio.ByteBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && swing.isDirect() && !swing.isReadOnly() && swing.order() == java.nio.ByteOrder.nativeOrder() && twist.isDirect() && !twist.isReadOnly() && twist.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.decomposeSwingTwist_unsafe(swing, swingOffset, twist, twistOffset, src, srcOffset, axisX, axisY, axisZ);
        return FloatQuatOpsKernelsByteBuffer.decomposeSwingTwist_api(swing, swingOffset, twist, twistOffset, src, srcOffset, axisX, axisY, axisZ);
    }

    /** {@link #decomposeSwingTwist(float[], int, float[], int, float[], int, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment decomposeSwingTwist(java.lang.foreign.MemorySegment swing, long swingOffset, java.lang.foreign.MemorySegment twist, long twistOffset, java.lang.foreign.MemorySegment src, long srcOffset, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && swing.isNative() && !swing.isReadOnly() && twist.isNative() && !twist.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.decomposeSwingTwist_unsafe(swing, swingOffset, twist, twistOffset, src, srcOffset, axisX, axisY, axisZ);
        return FloatQuatOpsKernelsSegment.decomposeSwingTwist_api(swing, swingOffset, twist, twistOffset, src, srcOffset, axisX, axisY, axisZ);
    }

    /** {@link #decomposeSwingTwist(float[], int, float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long decomposeSwingTwist(long swing, long twist, long src, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.decomposeSwingTwist_unsafe(swing, twist, src, axisX, axisY, axisZ);
        decomposeSwingTwist(VirtualMemoryHolder.virtualMemory().asSlice(swing, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(twist, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, axisX, axisY, axisZ);
        return swing;
    }

    /**
     * Decompose this quaternion into a swing about an axis perpendicular to {@code axis} followed
     * by a twist about {@code axis}, storing them in {@code swing} and {@code twist} respectively,
     * such that {@code swing * twist} is this rotation.
     * <p>
     * Equivalent to calling {@code getSwing} and {@code getTwist} separately, but shares the work.
     * The twist is the identity when the rotation is a pure swing, including the 180-degree
     * perpendicular case where it is undefined.
     * <p>
     * Valid input: {@code axis} must have unit length; this quaternion must have unit length.
     *
     * @param swing will hold the swing
     * @param swingOffset the element index in {@code swing} at which the quaternion starts
     * @param twist will hold the twist
     * @param twistOffset the element index in {@code twist} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param axis the storage holding the rotation axis
     * @param axisOffset the element index in {@code axis} at which the vector starts
     * @return {@code swing}
     */
    public static float[] decomposeSwingTwist(float[] swing, int swingOffset, float[] twist, int twistOffset, float[] src, int srcOffset, float[] axis, int axisOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _axisx = axis[axisOffset + 0];
        float _axisy = axis[axisOffset + 1];
        float _axisz = axis[axisOffset + 2];
        float _t2 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        float _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = _selfw * _t5;
            _t12 = _axisx * _t7;
            _t13 = _axisy * _t7;
            _t14 = _axisz * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        swing[swingOffset + 0] = Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14));
        swing[swingOffset + 1] = Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12));
        swing[swingOffset + 2] = Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14));
        return decomposeSwingTwist_sa6172cdd_1(swing, swingOffset, twist, twistOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t13, _t14);
    }

    /** Piece 2 of {@code decomposeSwingTwist}, split to fit the inline budget; reached only through it. */
    private static float[] decomposeSwingTwist_sa6172cdd_1(float[] swing, int swingOffset, float[] twist, int twistOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14) {
        swing[swingOffset + 3] = Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13));
        twist[twistOffset + 0] = _t12;
        twist[twistOffset + 1] = _t13;
        twist[twistOffset + 2] = _t14;
        twist[twistOffset + 3] = _t11;
        return swing;
    }

    /** {@link #decomposeSwingTwist(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer decomposeSwingTwist(java.nio.FloatBuffer swing, int swingOffset, java.nio.FloatBuffer twist, int twistOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && swing.isDirect() && !swing.isReadOnly() && swing.order() == java.nio.ByteOrder.nativeOrder() && twist.isDirect() && !twist.isReadOnly() && twist.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.decomposeSwingTwist_unsafe(swing, swingOffset, twist, twistOffset, src, srcOffset, axis, axisOffset);
        return FloatQuatOpsKernelsTypedBuffer.decomposeSwingTwist_api(swing, swingOffset, twist, twistOffset, src, srcOffset, axis, axisOffset);
    }

    /** {@link #decomposeSwingTwist(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer decomposeSwingTwist(java.nio.ByteBuffer swing, int swingOffset, java.nio.ByteBuffer twist, int twistOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && swing.isDirect() && !swing.isReadOnly() && swing.order() == java.nio.ByteOrder.nativeOrder() && twist.isDirect() && !twist.isReadOnly() && twist.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.decomposeSwingTwist_unsafe(swing, swingOffset, twist, twistOffset, src, srcOffset, axis, axisOffset);
        return FloatQuatOpsKernelsByteBuffer.decomposeSwingTwist_api(swing, swingOffset, twist, twistOffset, src, srcOffset, axis, axisOffset);
    }

    /** {@link #decomposeSwingTwist(float[], int, float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment decomposeSwingTwist(java.lang.foreign.MemorySegment swing, long swingOffset, java.lang.foreign.MemorySegment twist, long twistOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && swing.isNative() && !swing.isReadOnly() && twist.isNative() && !twist.isReadOnly() && src.isNative() && axis.isNative()) return FloatQuatOpsKernelsSegment.decomposeSwingTwist_unsafe(swing, swingOffset, twist, twistOffset, src, srcOffset, axis, axisOffset);
        return FloatQuatOpsKernelsSegment.decomposeSwingTwist_api(swing, swingOffset, twist, twistOffset, src, srcOffset, axis, axisOffset);
    }

    /** {@link #decomposeSwingTwist(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long decomposeSwingTwist(long swing, long twist, long src, long axis) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.decomposeSwingTwist_unsafe(swing, twist, src, axis);
        decomposeSwingTwist(VirtualMemoryHolder.virtualMemory().asSlice(swing, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(twist, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(axis, 12L), 0L);
        return swing;
    }

    /**
     * Extract the swing component of this quaternion: the rotation perpendicular to ({@code axisX},
     * {@code axisY}, {@code axisZ}) in the swing-twist decomposition and store the result in
     * {@code dest}.
     * <p>
     * The swing carries the rotation that tilts the axis itself; its own axis is perpendicular to
     * the given one.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length; this quaternion must have
     * unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return {@code dest}
     */
    public static float[] getSwing(float[] dest, int destOffset, float[] src, int srcOffset, float axisX, float axisY, float axisZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t2 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        float _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = _selfw * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        dest[destOffset + 0] = Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14));
        dest[destOffset + 1] = Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12));
        dest[destOffset + 2] = Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14));
        dest[destOffset + 3] = Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13));
        return dest;
    }

    /** {@link #getSwing(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer getSwing(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.getSwing_unsafe(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
        return FloatQuatOpsKernelsTypedBuffer.getSwing_api(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
    }

    /** {@link #getSwing(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getSwing(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.getSwing_unsafe(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
        return FloatQuatOpsKernelsByteBuffer.getSwing_api(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
    }

    /** {@link #getSwing(float[], int, float[], int, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getSwing(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.getSwing_unsafe(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
        return FloatQuatOpsKernelsSegment.getSwing_api(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
    }

    /** {@link #getSwing(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getSwing(long dest, long src, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.getSwing_unsafe(dest, src, axisX, axisY, axisZ);
        getSwing(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, axisX, axisY, axisZ);
        return dest;
    }

    /**
     * Extract the swing component of this quaternion: the rotation perpendicular to {@code axis} in
     * the swing-twist decomposition and store the result in {@code dest}.
     * <p>
     * The swing carries the rotation that tilts the axis itself; its own axis is perpendicular to
     * the given one.
     * <p>
     * Valid input: {@code axis} must have unit length; this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param axis the storage holding the rotation axis
     * @param axisOffset the element index in {@code axis} at which the vector starts
     * @return {@code dest}
     */
    public static float[] getSwing(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _axisx = axis[axisOffset + 0];
        float _axisy = axis[axisOffset + 1];
        float _axisz = axis[axisOffset + 2];
        float _t2 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        float _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = _selfw * _t5;
            _t12 = _axisx * _t7;
            _t13 = _axisy * _t7;
            _t14 = _axisz * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        dest[destOffset + 0] = Math.fma(_selfx, _t11, -(_selfw * _t12)) + Math.fma(_selfz, _t13, -(_selfy * _t14));
        dest[destOffset + 1] = Math.fma(_selfx, _t14, -(_selfw * _t13)) + Math.fma(_selfy, _t11, -(_selfz * _t12));
        dest[destOffset + 2] = Math.fma(_selfy, _t12, _selfz * _t11) + Math.fma(-_selfx, _t13, -(_selfw * _t14));
        dest[destOffset + 3] = Math.fma(_selfx, _t12, _selfw * _t11) - Math.fma(-_selfz, _t14, -(_selfy * _t13));
        return dest;
    }

    /** {@link #getSwing(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer getSwing(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.getSwing_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset);
        return FloatQuatOpsKernelsTypedBuffer.getSwing_api(dest, destOffset, src, srcOffset, axis, axisOffset);
    }

    /** {@link #getSwing(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getSwing(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.getSwing_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset);
        return FloatQuatOpsKernelsByteBuffer.getSwing_api(dest, destOffset, src, srcOffset, axis, axisOffset);
    }

    /** {@link #getSwing(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getSwing(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && axis.isNative()) return FloatQuatOpsKernelsSegment.getSwing_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset);
        return FloatQuatOpsKernelsSegment.getSwing_api(dest, destOffset, src, srcOffset, axis, axisOffset);
    }

    /** {@link #getSwing(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getSwing(long dest, long src, long axis) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.getSwing_unsafe(dest, src, axis);
        getSwing(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(axis, 12L), 0L);
        return dest;
    }

    /**
     * Extract the twist component of this quaternion: the rotation about ({@code axisX},
     * {@code axisY}, {@code axisZ}) in the swing-twist decomposition and store the result in
     * {@code dest}.
     * <p>
     * The twist is the rotation about the axis alone; it is the identity when the rotation is a
     * pure swing, including the 180-degree perpendicular case where the twist angle is undefined.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return {@code dest}
     */
    public static float[] getTwist(float[] dest, int destOffset, float[] src, int srcOffset, float axisX, float axisY, float axisZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t2 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        float _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            dest[destOffset + 0] = axisX * _t6;
            dest[destOffset + 1] = axisY * _t6;
            dest[destOffset + 2] = axisZ * _t6;
            dest[destOffset + 3] = _selfw * _t5;
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
            dest[destOffset + 3] = 1.0f;
        }
        return dest;
    }

    /** {@link #getTwist(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer getTwist(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.getTwist_unsafe(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
        return FloatQuatOpsKernelsTypedBuffer.getTwist_api(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
    }

    /** {@link #getTwist(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getTwist(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.getTwist_unsafe(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
        return FloatQuatOpsKernelsByteBuffer.getTwist_api(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
    }

    /** {@link #getTwist(float[], int, float[], int, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getTwist(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.getTwist_unsafe(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
        return FloatQuatOpsKernelsSegment.getTwist_api(dest, destOffset, src, srcOffset, axisX, axisY, axisZ);
    }

    /** {@link #getTwist(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getTwist(long dest, long src, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.getTwist_unsafe(dest, src, axisX, axisY, axisZ);
        getTwist(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, axisX, axisY, axisZ);
        return dest;
    }

    /**
     * Extract the twist component of this quaternion: the rotation about {@code axis} in the
     * swing-twist decomposition and store the result in {@code dest}.
     * <p>
     * The twist is the rotation about the axis alone; it is the identity when the rotation is a
     * pure swing, including the 180-degree perpendicular case where the twist angle is undefined.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param axis the storage holding the rotation axis
     * @param axisOffset the element index in {@code axis} at which the vector starts
     * @return {@code dest}
     */
    public static float[] getTwist(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _axisx = axis[axisOffset + 0];
        float _axisy = axis[axisOffset + 1];
        float _axisz = axis[axisOffset + 2];
        float _t2 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        float _t4 = Math.fma(_selfw, _selfw, _t2 * _t2);
        float _t5 = (1.0f / (float) Math.sqrt(_t4));
        float _t6 = _t2 * _t5;
        if (_t4 > 1.0E-14f) {
            dest[destOffset + 0] = _axisx * _t6;
            dest[destOffset + 1] = _axisy * _t6;
            dest[destOffset + 2] = _axisz * _t6;
            dest[destOffset + 3] = _selfw * _t5;
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
            dest[destOffset + 3] = 1.0f;
        }
        return dest;
    }

    /** {@link #getTwist(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer getTwist(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.getTwist_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset);
        return FloatQuatOpsKernelsTypedBuffer.getTwist_api(dest, destOffset, src, srcOffset, axis, axisOffset);
    }

    /** {@link #getTwist(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getTwist(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.getTwist_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset);
        return FloatQuatOpsKernelsByteBuffer.getTwist_api(dest, destOffset, src, srcOffset, axis, axisOffset);
    }

    /** {@link #getTwist(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getTwist(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && axis.isNative()) return FloatQuatOpsKernelsSegment.getTwist_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset);
        return FloatQuatOpsKernelsSegment.getTwist_api(dest, destOffset, src, srcOffset, axis, axisOffset);
    }

    /** {@link #getTwist(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getTwist(long dest, long src, long axis) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.getTwist_unsafe(dest, src, axis);
        getTwist(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(axis, 12L), 0L);
        return dest;
    }

    /**
     * Set this quaternion to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] makeIdentity(float[] dest, int destOffset) {
        dest[destOffset + 0] = 0.0f;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 1.0f;
        return dest;
    }

    /** {@link #makeIdentity(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeIdentity(java.nio.FloatBuffer dest, int destOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeIdentity_unsafe(dest, destOffset);
        return FloatQuatOpsKernelsTypedBuffer.makeIdentity_api(dest, destOffset);
    }

    /** {@link #makeIdentity(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeIdentity(java.nio.ByteBuffer dest, int destOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeIdentity_unsafe(dest, destOffset);
        return FloatQuatOpsKernelsByteBuffer.makeIdentity_api(dest, destOffset);
    }

    /** {@link #makeIdentity(float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeIdentity(java.lang.foreign.MemorySegment dest, long destOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.makeIdentity_unsafe(dest, destOffset);
        return FloatQuatOpsKernelsSegment.makeIdentity_api(dest, destOffset);
    }

    /** {@link #makeIdentity(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeIdentity(long dest) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeIdentity_unsafe(dest);
        makeIdentity(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L);
        return dest;
    }

    /**
     * Set all components of this quaternion to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] makeZero(float[] dest, int destOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.makeZero(dest, destOffset);
        return FloatQuatOpsKernelsArray.makeZero_scalar(dest, destOffset);
    }

    /** {@link #makeZero(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeZero(java.nio.FloatBuffer dest, int destOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeZero_unsafe(dest, destOffset);
        return FloatQuatOpsKernelsTypedBuffer.makeZero_api(dest, destOffset);
    }

    /** {@link #makeZero(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeZero(java.nio.ByteBuffer dest, int destOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeZero_unsafe(dest, destOffset);
        return FloatQuatOpsKernelsByteBuffer.makeZero_api(dest, destOffset);
    }

    /** {@link #makeZero(float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeZero(java.lang.foreign.MemorySegment dest, long destOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.makeZero(dest, destOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.makeZero_unsafe(dest, destOffset);
        return FloatQuatOpsKernelsSegment.makeZero_api(dest, destOffset);
    }

    /** {@link #makeZero(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeZero(long dest) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeZero_unsafe(dest);
        makeZero(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L);
        return dest;
    }

    /**
     * Linearly interpolate between this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}) using the interpolation factor {@code t} and store the result
     * in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) (interpolation factor
     * {@code 1}). Each linearly interpolated component is {@code this + (other - this) * t}, as in
     * JOML and glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only
     * up to the rounding of {@code other - this}, which shows when this component is much larger in
     * magnitude than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.lerp(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return FloatQuatOpsKernelsArray.lerp_scalar(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@link #lerp(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer lerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return FloatQuatOpsKernelsTypedBuffer.lerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@link #lerp(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return FloatQuatOpsKernelsByteBuffer.lerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@link #lerp(float[], int, float[], int, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.lerp(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.lerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return FloatQuatOpsKernelsSegment.lerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    /** {@link #lerp(float[], int, float[], int, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long lerp(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.lerp_unsafe(dest, src, otherX, otherY, otherZ, otherW, t);
        lerp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW, t);
        return dest;
    }

    /**
     * Linearly interpolate between this quaternion and {@code other} using the interpolation factor
     * {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code other} (interpolation factor {@code 1}). Each linearly interpolated component is
     * {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and exact
     * at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this}, which
     * shows when this component is much larger in magnitude than the other one (in {@code float},
     * 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param other the storage holding the quaternion to interpolate towards
     * @param otherOffset the element index in {@code other} at which the quaternion starts
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.lerp(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return FloatQuatOpsKernelsArray.lerp_scalar(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer lerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return FloatQuatOpsKernelsTypedBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return FloatQuatOpsKernelsByteBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.lerp(dest, destOffset, src, srcOffset, other, otherOffset, t);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return FloatQuatOpsKernelsSegment.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return FloatQuatOpsKernelsSegment.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long lerp(long dest, long src, long other, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.lerp_unsafe(dest, src, other, t);
        lerp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L, t);
        return dest;
    }

    /**
     * Interpolate between this quaternion and ({@code targetX}, {@code targetY}, {@code targetZ},
     * {@code targetW}) using the interpolation factor {@code alpha} and normalize the result and
     * store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code targetX}, {@code targetY}, {@code targetZ}, {@code targetW}) (interpolation factor
     * {@code 1}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] nlerp(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t4 = Math.fma(alpha, targetW - _selfw, _selfw);
        float _t5 = Math.fma(alpha, targetZ - _selfz, _selfz);
        float _t6 = Math.fma(alpha, targetX - _selfx, _selfx);
        float _t7 = Math.fma(alpha, targetY - _selfy, _selfy);
        float _t11 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, Math.fma(_t6, _t6, _t7 * _t7)));
        float _t12 = (1.0f / (float) Math.sqrt(_t11));
        if (_t11 != 0.0f) {
            dest[destOffset + 0] = _t6 * _t12;
            dest[destOffset + 1] = _t7 * _t12;
            dest[destOffset + 2] = _t5 * _t12;
            dest[destOffset + 3] = _t4 * _t12;
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
            dest[destOffset + 3] = 0.0f;
        }
        return dest;
    }

    /** {@link #nlerp(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer nlerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.nlerp_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return FloatQuatOpsKernelsTypedBuffer.nlerp_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@link #nlerp(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer nlerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.nlerp_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return FloatQuatOpsKernelsByteBuffer.nlerp_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@link #nlerp(float[], int, float[], int, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment nlerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.nlerp_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return FloatQuatOpsKernelsSegment.nlerp_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@link #nlerp(float[], int, float[], int, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long nlerp(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.nlerp_unsafe(dest, src, targetX, targetY, targetZ, targetW, alpha);
        nlerp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    /**
     * Interpolate between this quaternion and {@code target} using the interpolation factor
     * {@code alpha} and normalize the result and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param target the storage holding the target rotation
     * @param targetOffset the element index in {@code target} at which the quaternion starts
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] nlerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.nlerp(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return FloatQuatOpsKernelsArray.nlerp_scalar(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@link #nlerp(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer nlerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && target.isDirect() && target.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.nlerp_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return FloatQuatOpsKernelsTypedBuffer.nlerp_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@link #nlerp(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer nlerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && target.isDirect() && target.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.nlerp_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return FloatQuatOpsKernelsByteBuffer.nlerp_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@link #nlerp(float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment nlerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.nlerp(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && target.isNative()) return FloatQuatOpsKernelsSegment.nlerp_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return FloatQuatOpsKernelsSegment.nlerp_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@link #nlerp(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long nlerp(long dest, long src, long target, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.nlerp_unsafe(dest, src, target, alpha);
        nlerp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(target, 16L), 0L, alpha);
        return dest;
    }

    /**
     * Interpolate along the shortest path between this quaternion and ({@code targetX},
     * {@code targetY}, {@code targetZ}, {@code targetW}) using the interpolation factor
     * {@code alpha} and normalize the result and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code targetX}, {@code targetY}, {@code targetZ}, {@code targetW}) (interpolation factor
     * {@code 1}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] nlerpShortest(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t17, _t18, _t19, _t20;
        if (-Math.fma(_selfw, targetW, Math.fma(_selfz, targetZ, Math.fma(_selfx, targetX, _selfy * targetY))) > 0.0f) {
            _t17 = Math.fma(alpha, -targetW - _selfw, _selfw);
            _t18 = Math.fma(alpha, -targetZ - _selfz, _selfz);
            _t19 = Math.fma(alpha, -targetX - _selfx, _selfx);
            _t20 = Math.fma(alpha, -targetY - _selfy, _selfy);
        } else {
            _t17 = Math.fma(alpha, targetW - _selfw, _selfw);
            _t18 = Math.fma(alpha, targetZ - _selfz, _selfz);
            _t19 = Math.fma(alpha, targetX - _selfx, _selfx);
            _t20 = Math.fma(alpha, targetY - _selfy, _selfy);
        }
        float _t24 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        float _t25 = (1.0f / (float) Math.sqrt(_t24));
        if (_t24 != 0.0f) {
            dest[destOffset + 0] = _t19 * _t25;
            dest[destOffset + 1] = _t20 * _t25;
            dest[destOffset + 2] = _t18 * _t25;
            dest[destOffset + 3] = _t17 * _t25;
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
            dest[destOffset + 3] = 0.0f;
        }
        return dest;
    }

    /** {@link #nlerpShortest(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer nlerpShortest(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.nlerpShortest_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return FloatQuatOpsKernelsTypedBuffer.nlerpShortest_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@link #nlerpShortest(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer nlerpShortest(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.nlerpShortest_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return FloatQuatOpsKernelsByteBuffer.nlerpShortest_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@link #nlerpShortest(float[], int, float[], int, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment nlerpShortest(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.nlerpShortest_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return FloatQuatOpsKernelsSegment.nlerpShortest_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@link #nlerpShortest(float[], int, float[], int, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long nlerpShortest(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.nlerpShortest_unsafe(dest, src, targetX, targetY, targetZ, targetW, alpha);
        nlerpShortest(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    /**
     * Interpolate along the shortest path between this quaternion and {@code target} using the
     * interpolation factor {@code alpha} and normalize the result and store the result in
     * {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param target the storage holding the target rotation
     * @param targetOffset the element index in {@code target} at which the quaternion starts
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] nlerpShortest(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.nlerpShortest(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return FloatQuatOpsKernelsArray.nlerpShortest_scalar(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@link #nlerpShortest(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer nlerpShortest(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && target.isDirect() && target.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.nlerpShortest_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return FloatQuatOpsKernelsTypedBuffer.nlerpShortest_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@link #nlerpShortest(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer nlerpShortest(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && target.isDirect() && target.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.nlerpShortest_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return FloatQuatOpsKernelsByteBuffer.nlerpShortest_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@link #nlerpShortest(float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment nlerpShortest(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.nlerpShortest(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && target.isNative()) return FloatQuatOpsKernelsSegment.nlerpShortest_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return FloatQuatOpsKernelsSegment.nlerpShortest_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@link #nlerpShortest(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long nlerpShortest(long dest, long src, long target, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.nlerpShortest_unsafe(dest, src, target, alpha);
        nlerpShortest(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(target, 16L), 0L, alpha);
        return dest;
    }

    /**
     * Spherically interpolate between this quaternion and ({@code targetX}, {@code targetY},
     * {@code targetZ}, {@code targetW}) using the interpolation factor {@code alpha} and store the
     * result in {@code dest}.
     * <p>
     * This method interpolates along the arc as given: when the two quaternions' dot product is
     * negative, the longer path around the sphere is taken. Use {@link #slerpShortest} (or negate
     * one operand) to always interpolate along the shorter arc.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code targetX}, {@code targetY}, {@code targetZ}, {@code targetW}) (interpolation factor
     * {@code 1}).
     * <p>
     * Valid input: {@code (targetX, targetY, targetZ, targetW)} must have unit length; this
     * quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] slerp(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.slerp(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return FloatQuatOpsKernelsArray.slerp_scalar(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@link #slerp(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer slerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.slerp_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return FloatQuatOpsKernelsTypedBuffer.slerp_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@link #slerp(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer slerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.slerp_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return FloatQuatOpsKernelsByteBuffer.slerp_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@link #slerp(float[], int, float[], int, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment slerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.slerp(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.slerp_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return FloatQuatOpsKernelsSegment.slerp_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@link #slerp(float[], int, float[], int, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long slerp(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.slerp_unsafe(dest, src, targetX, targetY, targetZ, targetW, alpha);
        slerp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    /**
     * Spherically interpolate between this quaternion and {@code target} using the interpolation
     * factor {@code alpha} and store the result in {@code dest}.
     * <p>
     * This method interpolates along the arc as given: when the two quaternions' dot product is
     * negative, the longer path around the sphere is taken. Use {@link #slerpShortest} (or negate
     * one operand) to always interpolate along the shorter arc.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * Valid input: {@code target} must have unit length; this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param target the storage holding the target rotation
     * @param targetOffset the element index in {@code target} at which the quaternion starts
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] slerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.slerp(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return FloatQuatOpsKernelsArray.slerp_scalar(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@link #slerp(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer slerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && target.isDirect() && target.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.slerp_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return FloatQuatOpsKernelsTypedBuffer.slerp_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@link #slerp(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer slerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && target.isDirect() && target.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.slerp_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return FloatQuatOpsKernelsByteBuffer.slerp_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@link #slerp(float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment slerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.slerp(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && target.isNative()) return FloatQuatOpsKernelsSegment.slerp_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return FloatQuatOpsKernelsSegment.slerp_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@link #slerp(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long slerp(long dest, long src, long target, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.slerp_unsafe(dest, src, target, alpha);
        slerp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(target, 16L), 0L, alpha);
        return dest;
    }

    /**
     * Spherically interpolate along the shortest path between this quaternion and ({@code targetX},
     * {@code targetY}, {@code targetZ}, {@code targetW}) using the interpolation factor
     * {@code alpha} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * ({@code targetX}, {@code targetY}, {@code targetZ}, {@code targetW}) (interpolation factor
     * {@code 1}).
     * <p>
     * Valid input: {@code (targetX, targetY, targetZ, targetW)} must have unit length; this
     * quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] slerpShortest(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = 1.0f - alpha;
        float _t12 = Math.fma(_selfw, targetW, Math.fma(_selfz, targetZ, Math.fma(_selfx, targetX, _selfy * targetY)));
        float _t16 = (float) Math.acos(Math.min(1.0f, Math.abs(_t12)));
        float _t17 = (float) Math.sin(_t16);
        float _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0f) {
            _t21 = -targetW;
            _t22 = -targetZ;
            _t23 = -targetX;
            _t24 = -targetY;
        } else {
            _t21 = targetW;
            _t22 = targetZ;
            _t23 = targetX;
            _t24 = targetY;
        }
        return slerpShortest_s268a8c2d_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, _t0, _t17, 1.0f / _t17, (float) Math.sin(alpha * _t16), _t21, _t22, _t23, _t24, (float) Math.sin(_t0 * _t16));
    }

    /** Piece 2 of {@code slerpShortest}, split to fit the inline budget; reached only through it. */
    private static float[] slerpShortest_s268a8c2d_1(float[] dest, int destOffset, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t0, float _t17, float _t17_inv, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t42, _t43, _t44, _t45;
        if (_t17 > 0.0f) {
            _t42 = Math.fma(_selfw, _t25, _t19 * _t21) * _t17_inv;
            _t43 = Math.fma(_selfz, _t25, _t19 * _t22) * _t17_inv;
            _t44 = Math.fma(_selfx, _t25, _t19 * _t23) * _t17_inv;
            _t45 = Math.fma(_selfy, _t25, _t19 * _t24) * _t17_inv;
        } else {
            _t42 = Math.fma(alpha, _t21, _selfw * _t0);
            _t43 = Math.fma(alpha, _t22, _selfz * _t0);
            _t44 = Math.fma(alpha, _t23, _selfx * _t0);
            _t45 = Math.fma(alpha, _t24, _selfy * _t0);
        }
        float _t49 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, Math.fma(_t44, _t44, _t45 * _t45)));
        float _t50 = (1.0f / (float) Math.sqrt(_t49));
        if (_t49 != 0.0f) {
            dest[destOffset + 0] = _t50 * _t44;
            dest[destOffset + 1] = _t50 * _t45;
            dest[destOffset + 2] = _t50 * _t43;
            dest[destOffset + 3] = _t50 * _t42;
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
            dest[destOffset + 3] = 0.0f;
        }
        return dest;
    }

    /** {@link #slerpShortest(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer slerpShortest(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.slerpShortest_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return FloatQuatOpsKernelsTypedBuffer.slerpShortest_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@link #slerpShortest(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer slerpShortest(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.slerpShortest_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return FloatQuatOpsKernelsByteBuffer.slerpShortest_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@link #slerpShortest(float[], int, float[], int, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment slerpShortest(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.slerpShortest_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return FloatQuatOpsKernelsSegment.slerpShortest_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    /** {@link #slerpShortest(float[], int, float[], int, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long slerpShortest(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.slerpShortest_unsafe(dest, src, targetX, targetY, targetZ, targetW, alpha);
        slerpShortest(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, targetX, targetY, targetZ, targetW, alpha);
        return dest;
    }

    /**
     * Spherically interpolate along the shortest path between this quaternion and {@code target}
     * using the interpolation factor {@code alpha} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
     * {@code target} (interpolation factor {@code 1}).
     * <p>
     * Valid input: {@code target} must have unit length; this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param target the storage holding the target rotation
     * @param targetOffset the element index in {@code target} at which the quaternion starts
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] slerpShortest(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.slerpShortest(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return FloatQuatOpsKernelsArray.slerpShortest_scalar(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@link #slerpShortest(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer slerpShortest(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && target.isDirect() && target.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.slerpShortest_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return FloatQuatOpsKernelsTypedBuffer.slerpShortest_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@link #slerpShortest(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer slerpShortest(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && target.isDirect() && target.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.slerpShortest_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return FloatQuatOpsKernelsByteBuffer.slerpShortest_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@link #slerpShortest(float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment slerpShortest(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.slerpShortest(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && target.isNative()) return FloatQuatOpsKernelsSegment.slerpShortest_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return FloatQuatOpsKernelsSegment.slerpShortest_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    /** {@link #slerpShortest(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long slerpShortest(long dest, long src, long target, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.slerpShortest_unsafe(dest, src, target, alpha);
        slerpShortest(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(target, 16L), 0L, alpha);
        return dest;
    }

    /**
     * Perform spherical quadrangle interpolation (SQUAD) between this quaternion (the start
     * rotation) and the target rotation, shaped by the two inner control quaternions and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length;
     * {@code (control0X, control0Y, control0Z, control0W)} must have unit length;
     * {@code (control1X, control1Y, control1Z, control1W)} must have unit length;
     * {@code (targetX, targetY, targetZ, targetW)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param control0X the {@code x} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control0Y the {@code y} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control0Z the {@code z} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control0W the {@code w} component of the quaternion
     *        {@code (control0X, control0Y, control0Z, control0W)}
     * @param control1X the {@code x} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param control1Y the {@code y} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param control1Z the {@code z} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param control1W the {@code w} component of the quaternion
     *        {@code (control1X, control1Y, control1Z, control1W)}
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] squad(float[] dest, int destOffset, float[] src, int srcOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
        float _t0 = 1.0f - t;
        float _t3 = control0W + control1W;
        float _t4 = control0Z + control1Z;
        float _t5 = control0X + control1X;
        float _t6 = control0Y + control1Y;
        float _t7 = t < 0.5f ? 1.0f : 0.0f;
        float _t12 = 1.0f - _t7;
        float _t25 = Math.min(4.0f, Math.fma(_t3, _t3, Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6))));
        float _t27 = quatArcAngle(_t25);
        float _t29 = 4.0f - _t25;
        float _t41 = (float) Math.sqrt(_t29 * _t25);
        float _t45 = 2.0f / _t41;
        float _t55, _t57;
        if (_t41 > 2.0E-6f) {
            _t55 = _t45 * (float) Math.sin(t * _t27);
            _t57 = _t45 * (float) Math.sin(_t0 * _t27);
        } else {
            if (_t25 > _t29) {
                _t55 = t;
                _t57 = _t0;
            } else {
                _t55 = _t12;
                _t57 = _t7;
            }
        }
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t1 = t + t;
        return squad_s58b06335_1(dest, destOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, _t0, _t7, _t12, _t55, _t57, _selfx, _selfy, _selfz, _selfw, _t1, _selfw + targetW, _selfz + targetZ, _selfx + targetX, _selfy + targetY, _t0 * _t1);
    }

    /** Piece 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private static float[] squad_s58b06335_1(float[] dest, int destOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, float _t0, float _t7, float _t12, float _t55, float _t57, float _selfx, float _selfy, float _selfz, float _selfw, float _t1, float _t8, float _t9, float _t10, float _t11, float _t13) {
        float _t26 = Math.min(4.0f, Math.fma(_t8, _t8, Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11))));
        float _t28 = quatArcAngle(_t26);
        float _t30 = 4.0f - _t26;
        float _t43 = (float) Math.sqrt(_t30 * _t26);
        float _t46 = 2.0f / _t43;
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
            _t56 = _t46 * (float) Math.sin(t * _t28);
            _t58 = _t46 * (float) Math.sin(_t0 * _t28);
        } else {
            if (_t26 > _t30) {
                _t56 = t;
                _t58 = _t0;
            } else {
                _t56 = _t12;
                _t58 = _t7;
            }
        }
        float _t67 = Math.fma(control0X, _t57, control1X * _t55);
        float _t68 = Math.fma(control0W, _t57, control1W * _t55);
        float _t69 = Math.fma(_selfw, _t58, targetW * _t56);
        float _t70 = Math.fma(control0Z, _t57, control1Z * _t55);
        float _t71 = Math.fma(_selfz, _t58, targetZ * _t56);
        float _t72 = Math.fma(_selfx, _t58, targetX * _t56);
        return squad_s58b06335_2(dest, destOffset, _t13, Math.fma(-_t0, _t1, 1.0f), _t13 < 0.5f ? 1.0f : 0.0f, _t67, _t68, _t69, _t70, _t71, _t72, Math.fma(control0Y, _t57, control1Y * _t55), Math.fma(_selfy, _t58, targetY * _t56), _t68 + _t69, _t70 + _t71, _t67 + _t72);
    }

    /** Piece 3 of {@code squad}, split to fit the inline budget; reached only through it. */
    private static float[] squad_s58b06335_2(float[] dest, int destOffset, float _t13, float _t14, float _t17, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t73, float _t74, float _t75, float _t76, float _t77) {
        float _t78 = _t73 + _t74;
        float _t83 = Math.min(4.0f, Math.fma(_t75, _t75, Math.fma(_t76, _t76, Math.fma(_t77, _t77, _t78 * _t78))));
        float _t84 = quatArcAngle(_t83);
        float _t85 = 4.0f - _t83;
        float _t91 = (float) Math.sqrt(_t85 * _t83);
        float _t93 = 2.0f / _t91;
        float _t98, _t99;
        if (_t91 > 2.0E-6f) {
            _t98 = _t93 * (float) Math.sin(_t13 * _t84);
            _t99 = _t93 * (float) Math.sin(_t14 * _t84);
        } else {
            if (_t83 > _t85) {
                _t98 = _t13;
                _t99 = _t14;
            } else {
                _t98 = 1.0f - _t17;
                _t99 = _t17;
            }
        }
        dest[destOffset + 0] = Math.fma(_t67, _t98, _t72 * _t99);
        dest[destOffset + 1] = Math.fma(_t73, _t98, _t74 * _t99);
        dest[destOffset + 2] = Math.fma(_t70, _t98, _t71 * _t99);
        dest[destOffset + 3] = Math.fma(_t68, _t98, _t69 * _t99);
        return dest;
    }

    /** {@link #squad(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer squad(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.squad_unsafe(dest, destOffset, src, srcOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
        return FloatQuatOpsKernelsTypedBuffer.squad_api(dest, destOffset, src, srcOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
    }

    /** {@link #squad(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer squad(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.squad_unsafe(dest, destOffset, src, srcOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
        return FloatQuatOpsKernelsByteBuffer.squad_api(dest, destOffset, src, srcOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
    }

    /** {@link #squad(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment squad(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.squad_unsafe(dest, destOffset, src, srcOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
        return FloatQuatOpsKernelsSegment.squad_api(dest, destOffset, src, srcOffset, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
    }

    /** {@link #squad(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long squad(long dest, long src, float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.squad_unsafe(dest, src, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
        squad(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t);
        return dest;
    }

    /**
     * Perform spherical quadrangle interpolation (SQUAD) between this quaternion (the start
     * rotation) and the target rotation, shaped by the two inner control quaternions and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length; {@code control0} must have unit length;
     * {@code control1} must have unit length; {@code target} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param control0 the storage holding the inner control quaternion associated with the start
     *        rotation
     * @param control0Offset the element index in {@code control0} at which the quaternion starts
     * @param control1 the storage holding the inner control quaternion associated with the end
     *        rotation
     * @param control1Offset the element index in {@code control1} at which the quaternion starts
     * @param target the storage holding the target rotation
     * @param targetOffset the element index in {@code target} at which the quaternion starts
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] squad(float[] dest, int destOffset, float[] src, int srcOffset, float[] control0, int control0Offset, float[] control1, int control1Offset, float[] target, int targetOffset, float t) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _control0x = control0[control0Offset + 0];
        float _control0y = control0[control0Offset + 1];
        float _control0z = control0[control0Offset + 2];
        float _control0w = control0[control0Offset + 3];
        float _control1x = control1[control1Offset + 0];
        float _control1y = control1[control1Offset + 1];
        float _control1z = control1[control1Offset + 2];
        float _control1w = control1[control1Offset + 3];
        float _targetx = target[targetOffset + 0];
        float _targety = target[targetOffset + 1];
        float _targetz = target[targetOffset + 2];
        float _targetw = target[targetOffset + 3];
        float _t0 = 1.0f - t;
        float _t1 = t + t;
        float _t7 = t < 0.5f ? 1.0f : 0.0f;
        float _t13 = _t0 * _t1;
        return squad_s803bd8eb_1(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t0, _control0w + _control1w, _control0z + _control1z, _control0x + _control1x, _control0y + _control1y, _t7, _selfw + _targetw, _selfz + _targetz, _selfx + _targetx, _selfy + _targety, 1.0f - _t7, _t13, Math.fma(-_t0, _t1, 1.0f), _t13 < 0.5f ? 1.0f : 0.0f);
    }

    /** Piece 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private static float[] squad_s803bd8eb_1(float[] dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t0, float _t3, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _t17) {
        float _t25 = Math.min(4.0f, Math.fma(_t3, _t3, Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6))));
        float _t26 = Math.min(4.0f, Math.fma(_t8, _t8, Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11))));
        float _t27 = quatArcAngle(_t25);
        float _t29 = 4.0f - _t25;
        float _t30 = 4.0f - _t26;
        float _t41 = (float) Math.sqrt(_t29 * _t25);
        float _t43 = (float) Math.sqrt(_t30 * _t26);
        float _t45 = 2.0f / _t41;
        float _t55, _t57;
        if (_t41 > 2.0E-6f) {
            _t55 = _t45 * (float) Math.sin(t * _t27);
            _t57 = _t45 * (float) Math.sin(_t0 * _t27);
        } else {
            if (_t25 > _t29) {
                _t55 = t;
                _t57 = _t0;
            } else {
                _t55 = _t12;
                _t57 = _t7;
            }
        }
        return squad_s803bd8eb_2(dest, destOffset, t, _selfx, _selfy, _selfz, _selfw, _control0x, _control0y, _control0z, _control0w, _control1x, _control1y, _control1z, _control1w, _targetx, _targety, _targetz, _targetw, _t0, _t7, _t12, _t13, _t14, _t17, _t26, quatArcAngle(_t26), _t30, _t43, 2.0f / _t43, _t55, _t57);
    }

    /** Piece 3 of {@code squad}, split to fit the inline budget; reached only through it. */
    private static float[] squad_s803bd8eb_2(float[] dest, int destOffset, float t, float _selfx, float _selfy, float _selfz, float _selfw, float _control0x, float _control0y, float _control0z, float _control0w, float _control1x, float _control1y, float _control1z, float _control1w, float _targetx, float _targety, float _targetz, float _targetw, float _t0, float _t7, float _t12, float _t13, float _t14, float _t17, float _t26, float _t28, float _t30, float _t43, float _t46, float _t55, float _t57) {
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
            _t56 = _t46 * (float) Math.sin(t * _t28);
            _t58 = _t46 * (float) Math.sin(_t0 * _t28);
        } else {
            if (_t26 > _t30) {
                _t56 = t;
                _t58 = _t0;
            } else {
                _t56 = _t12;
                _t58 = _t7;
            }
        }
        float _t67 = Math.fma(_control0x, _t57, _control1x * _t55);
        float _t68 = Math.fma(_control0w, _t57, _control1w * _t55);
        float _t69 = Math.fma(_selfw, _t58, _targetw * _t56);
        float _t70 = Math.fma(_control0z, _t57, _control1z * _t55);
        float _t71 = Math.fma(_selfz, _t58, _targetz * _t56);
        float _t72 = Math.fma(_selfx, _t58, _targetx * _t56);
        float _t73 = Math.fma(_control0y, _t57, _control1y * _t55);
        float _t74 = Math.fma(_selfy, _t58, _targety * _t56);
        float _t75 = _t68 + _t69;
        float _t76 = _t70 + _t71;
        float _t77 = _t67 + _t72;
        float _t78 = _t73 + _t74;
        float _t83 = Math.min(4.0f, Math.fma(_t75, _t75, Math.fma(_t76, _t76, Math.fma(_t77, _t77, _t78 * _t78))));
        float _t85 = 4.0f - _t83;
        float _t91 = (float) Math.sqrt(_t85 * _t83);
        return squad_s803bd8eb_3(dest, destOffset, _t13, _t14, _t17, _t67, _t68, _t69, _t70, _t71, _t72, _t73, _t74, _t83, quatArcAngle(_t83), _t85, _t91, 2.0f / _t91);
    }

    /** Piece 4 of {@code squad}, split to fit the inline budget; reached only through it. */
    private static float[] squad_s803bd8eb_3(float[] dest, int destOffset, float _t13, float _t14, float _t17, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t73, float _t74, float _t83, float _t84, float _t85, float _t91, float _t93) {
        float _t98, _t99;
        if (_t91 > 2.0E-6f) {
            _t98 = _t93 * (float) Math.sin(_t13 * _t84);
            _t99 = _t93 * (float) Math.sin(_t14 * _t84);
        } else {
            if (_t83 > _t85) {
                _t98 = _t13;
                _t99 = _t14;
            } else {
                _t98 = 1.0f - _t17;
                _t99 = _t17;
            }
        }
        dest[destOffset + 0] = Math.fma(_t67, _t98, _t72 * _t99);
        dest[destOffset + 1] = Math.fma(_t73, _t98, _t74 * _t99);
        dest[destOffset + 2] = Math.fma(_t70, _t98, _t71 * _t99);
        dest[destOffset + 3] = Math.fma(_t68, _t98, _t69 * _t99);
        return dest;
    }

    /** {@link #squad(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer squad(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer control0, int control0Offset, java.nio.FloatBuffer control1, int control1Offset, java.nio.FloatBuffer target, int targetOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && control0.isDirect() && control0.order() == java.nio.ByteOrder.nativeOrder() && control1.isDirect() && control1.order() == java.nio.ByteOrder.nativeOrder() && target.isDirect() && target.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.squad_unsafe(dest, destOffset, src, srcOffset, control0, control0Offset, control1, control1Offset, target, targetOffset, t);
        return FloatQuatOpsKernelsTypedBuffer.squad_api(dest, destOffset, src, srcOffset, control0, control0Offset, control1, control1Offset, target, targetOffset, t);
    }

    /** {@link #squad(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer squad(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer control0, int control0Offset, java.nio.ByteBuffer control1, int control1Offset, java.nio.ByteBuffer target, int targetOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && control0.isDirect() && control0.order() == java.nio.ByteOrder.nativeOrder() && control1.isDirect() && control1.order() == java.nio.ByteOrder.nativeOrder() && target.isDirect() && target.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.squad_unsafe(dest, destOffset, src, srcOffset, control0, control0Offset, control1, control1Offset, target, targetOffset, t);
        return FloatQuatOpsKernelsByteBuffer.squad_api(dest, destOffset, src, srcOffset, control0, control0Offset, control1, control1Offset, target, targetOffset, t);
    }

    /** {@link #squad(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment squad(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment control0, long control0Offset, java.lang.foreign.MemorySegment control1, long control1Offset, java.lang.foreign.MemorySegment target, long targetOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && control0.isNative() && control1.isNative() && target.isNative()) return FloatQuatOpsKernelsSegment.squad_unsafe(dest, destOffset, src, srcOffset, control0, control0Offset, control1, control1Offset, target, targetOffset, t);
        return FloatQuatOpsKernelsSegment.squad_api(dest, destOffset, src, srcOffset, control0, control0Offset, control1, control1Offset, target, targetOffset, t);
    }

    /** {@link #squad(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long squad(long dest, long src, long control0, long control1, long target, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.squad_unsafe(dest, src, control0, control1, target, t);
        squad(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(control0, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(control1, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(target, 16L), 0L, t);
        return dest;
    }

    /**
     * Multiply this quaternion by ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW})
     * and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code Q * R}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code Q * R * v}, the transformation of the operand will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @return {@code dest}
     */
    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset + 0] = Math.fma(otherX, _selfw, otherW * _selfx) + Math.fma(otherZ, _selfy, -(otherY * _selfz));
        dest[destOffset + 1] = Math.fma(otherX, _selfz, otherW * _selfy) + Math.fma(otherY, _selfw, -(otherZ * _selfx));
        dest[destOffset + 2] = Math.fma(otherY, _selfx, otherZ * _selfw) + Math.fma(otherW, _selfz, -(otherX * _selfy));
        dest[destOffset + 3] = Math.fma(otherW, _selfw, -(otherX * _selfx)) - Math.fma(otherY, _selfy, otherZ * _selfz);
        return dest;
    }

    /** {@link #mul(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.mul_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsTypedBuffer.mul_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #mul(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.mul_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsByteBuffer.mul_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #mul(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.mul_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsSegment.mul_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #mul(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mul(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.mul_unsafe(dest, src, otherX, otherY, otherZ, otherW);
        mul(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW);
        return dest;
    }

    /**
     * Multiply this quaternion by {@code other} and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code Q * R}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code Q * R * v}, the transformation of the operand will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param other the storage holding the right operand
     * @param otherOffset the element index in {@code other} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        dest[destOffset + 0] = Math.fma(_otherx, _selfw, _otherw * _selfx) + Math.fma(_otherz, _selfy, -(_othery * _selfz));
        dest[destOffset + 1] = Math.fma(_otherx, _selfz, _otherw * _selfy) + Math.fma(_othery, _selfw, -(_otherz * _selfx));
        dest[destOffset + 2] = Math.fma(_othery, _selfx, _otherz * _selfw) + Math.fma(_otherw, _selfz, -(_otherx * _selfy));
        dest[destOffset + 3] = Math.fma(_otherw, _selfw, -(_otherx * _selfx)) - Math.fma(_othery, _selfy, _otherz * _selfz);
        return dest;
    }

    /** {@link #mul(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.mul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsTypedBuffer.mul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #mul(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.mul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsByteBuffer.mul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #mul(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return FloatQuatOpsKernelsSegment.mul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsSegment.mul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #mul(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long mul(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.mul_unsafe(dest, src, other);
        mul(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
        return dest;
    }

    /**
     * Pre-multiply the transformation ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) onto this quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code T} the given transformation quaternion,
     * then the new quaternion will be {@code T * Q}. So when transforming a vector {@code v} with
     * the new quaternion by using {@code T * Q * v}, the given transformation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @return {@code dest}
     */
    public static float[] preMul(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset + 0] = Math.fma(otherX, _selfw, otherW * _selfx) + Math.fma(otherY, _selfz, -(otherZ * _selfy));
        dest[destOffset + 1] = Math.fma(otherY, _selfw, otherZ * _selfx) + Math.fma(otherW, _selfy, -(otherX * _selfz));
        dest[destOffset + 2] = Math.fma(otherX, _selfy, otherW * _selfz) + Math.fma(otherZ, _selfw, -(otherY * _selfx));
        dest[destOffset + 3] = Math.fma(otherW, _selfw, -(otherX * _selfx)) - Math.fma(otherY, _selfy, otherZ * _selfz);
        return dest;
    }

    /** {@link #preMul(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preMul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.preMul_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsTypedBuffer.preMul_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #preMul(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.preMul_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsByteBuffer.preMul_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #preMul(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preMul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.preMul_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsSegment.preMul_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #preMul(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preMul(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.preMul_unsafe(dest, src, otherX, otherY, otherZ, otherW);
        preMul(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW);
        return dest;
    }

    /**
     * Pre-multiply the transformation {@code other} onto this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code T} the given transformation quaternion,
     * then the new quaternion will be {@code T * Q}. So when transforming a vector {@code v} with
     * the new quaternion by using {@code T * Q * v}, the given transformation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param other the storage holding the left operand
     * @param otherOffset the element index in {@code other} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] preMul(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        dest[destOffset + 0] = Math.fma(_otherx, _selfw, _otherw * _selfx) + Math.fma(_othery, _selfz, -(_otherz * _selfy));
        dest[destOffset + 1] = Math.fma(_othery, _selfw, _otherz * _selfx) + Math.fma(_otherw, _selfy, -(_otherx * _selfz));
        dest[destOffset + 2] = Math.fma(_otherx, _selfy, _otherw * _selfz) + Math.fma(_otherz, _selfw, -(_othery * _selfx));
        dest[destOffset + 3] = Math.fma(_otherw, _selfw, -(_otherx * _selfx)) - Math.fma(_othery, _selfy, _otherz * _selfz);
        return dest;
    }

    /** {@link #preMul(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preMul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.preMul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsTypedBuffer.preMul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMul(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.preMul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsByteBuffer.preMul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMul(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preMul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return FloatQuatOpsKernelsSegment.preMul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsSegment.preMul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #preMul(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preMul(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.preMul_unsafe(dest, src, other);
        preMul(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
        return dest;
    }

    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) scaled by {@code weight}
     * to this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param weight the factor to scale ({@code otherX}, {@code otherY}, {@code otherZ},
     *        {@code otherW}) by before adding
     * @return {@code dest}
     */
    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float weight) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset + 0] = Math.fma(weight, otherX, _selfx);
        dest[destOffset + 1] = Math.fma(weight, otherY, _selfy);
        dest[destOffset + 2] = Math.fma(weight, otherZ, _selfz);
        dest[destOffset + 3] = Math.fma(weight, otherW, _selfw);
        return dest;
    }

    /** {@link #addScaled(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer addScaled(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float weight) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, weight);
        return FloatQuatOpsKernelsTypedBuffer.addScaled_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, weight);
    }

    /** {@link #addScaled(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer addScaled(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float weight) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, weight);
        return FloatQuatOpsKernelsByteBuffer.addScaled_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, weight);
    }

    /** {@link #addScaled(float[], int, float[], int, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment addScaled(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float weight) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.addScaled_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, weight);
        return FloatQuatOpsKernelsSegment.addScaled_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, weight);
    }

    /** {@link #addScaled(float[], int, float[], int, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long addScaled(long dest, long src, float otherX, float otherY, float otherZ, float otherW, float weight) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.addScaled_unsafe(dest, src, otherX, otherY, otherZ, otherW, weight);
        addScaled(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW, weight);
        return dest;
    }

    /**
     * Add {@code other} scaled by {@code weight} to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param other the storage holding the quaternion to scale and add
     * @param otherOffset the element index in {@code other} at which the quaternion starts
     * @param weight the factor to scale the given quaternion by before adding
     * @return {@code dest}
     */
    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.addScaled(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return FloatQuatOpsKernelsArray.addScaled_scalar(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer addScaled(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float weight) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return FloatQuatOpsKernelsTypedBuffer.addScaled_api(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer addScaled(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float weight) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return FloatQuatOpsKernelsByteBuffer.addScaled_api(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment addScaled(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.addScaled(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return FloatQuatOpsKernelsSegment.addScaled_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return FloatQuatOpsKernelsSegment.addScaled_api(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long addScaled(long dest, long src, long other, float weight) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.addScaled_unsafe(dest, src, other, weight);
        addScaled(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L, weight);
        return dest;
    }

    /**
     * Compute the rotation angle in radians of this quaternion, within {@code [0, 2*PI]}.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return the rotation angle in radians of this quaternion, within {@code [0, 2*PI]}
     */
    public static float angle(float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        return 2.0f * (float) Math.atan2((float) Math.sqrt(Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy))), _selfw);
    }

    /** {@link #angle(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float angle(java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.angle_unsafe(src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.angle_api(src, srcOffset);
    }

    /** {@link #angle(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float angle(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.angle_unsafe(src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.angle_api(src, srcOffset);
    }

    /** {@link #angle(float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float angle(java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return FloatQuatOpsKernelsSegment.angle_unsafe(src, srcOffset);
        return FloatQuatOpsKernelsSegment.angle_api(src, srcOffset);
    }

    /** {@link #angle(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float angle(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.angle_unsafe(src);
        return angle(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
    }

    /**
     * Compute the angle in radians between this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles).
     * <p>
     * Valid input: this quaternion must have unit length; {@code (otherX, otherY, otherZ, otherW)}
     * must have unit length.
     *
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @return the angle in radians between this quaternion and {@code other}
     */
    public static float angleTo(float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t9, _t10, _t11, _t12;
        if (-Math.fma(otherW, _selfw, Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy))) > 0.0f) {
            _t9 = -otherW;
            _t10 = -otherZ;
            _t11 = -otherX;
            _t12 = -otherY;
        } else {
            _t9 = otherW;
            _t10 = otherZ;
            _t11 = otherX;
            _t12 = otherY;
        }
        float _t13 = _selfw - _t9;
        float _t14 = _selfz - _t10;
        float _t15 = _selfx - _t11;
        float _t16 = _selfy - _t12;
        float _t17 = _selfw + _t9;
        float _t18 = _selfz + _t10;
        float _t19 = _selfx + _t11;
        float _t20 = _selfy + _t12;
        return 4.0f * (float) Math.atan2((float) Math.sqrt(Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, _t16 * _t16)))), (float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    /** {@link #angleTo(float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float angleTo(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.angleTo_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsTypedBuffer.angleTo_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #angleTo(float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float angleTo(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.angleTo_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsByteBuffer.angleTo_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #angleTo(float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float angleTo(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return FloatQuatOpsKernelsSegment.angleTo_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsSegment.angleTo_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #angleTo(float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float angleTo(long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.angleTo_unsafe(src, otherX, otherY, otherZ, otherW);
        return angleTo(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW);
    }

    /**
     * Compute the angle in radians between this quaternion and {@code other}.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles).
     * <p>
     * Valid input: this quaternion must have unit length; {@code other} must have unit length.
     *
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param other the storage holding the quaternion to measure the angle to
     * @param otherOffset the element index in {@code other} at which the quaternion starts
     * @return the angle in radians between this quaternion and {@code other}
     */
    public static float angleTo(float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        float _t9, _t10, _t11, _t12;
        if (-Math.fma(_otherw, _selfw, Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy))) > 0.0f) {
            _t9 = -_otherw;
            _t10 = -_otherz;
            _t11 = -_otherx;
            _t12 = -_othery;
        } else {
            _t9 = _otherw;
            _t10 = _otherz;
            _t11 = _otherx;
            _t12 = _othery;
        }
        float _t13 = _selfw - _t9;
        float _t14 = _selfz - _t10;
        float _t15 = _selfx - _t11;
        float _t16 = _selfy - _t12;
        float _t17 = _selfw + _t9;
        float _t18 = _selfz + _t10;
        float _t19 = _selfx + _t11;
        float _t20 = _selfy + _t12;
        return 4.0f * (float) Math.atan2((float) Math.sqrt(Math.fma(_t13, _t13, Math.fma(_t14, _t14, Math.fma(_t15, _t15, _t16 * _t16)))), (float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    /** {@link #angleTo(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float angleTo(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.angleTo_unsafe(src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsTypedBuffer.angleTo_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #angleTo(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float angleTo(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.angleTo_unsafe(src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsByteBuffer.angleTo_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #angleTo(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float angleTo(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative()) return FloatQuatOpsKernelsSegment.angleTo_unsafe(src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsSegment.angleTo_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #angleTo(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float angleTo(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.angleTo_unsafe(src, other);
        return angleTo(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
    }

    /**
     * Get the normalized rotation axis of this quaternion (zero when the rotation angle is zero)
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] axis(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        if (_t2 > 0.0f) {
            dest[destOffset + 0] = _selfx * _t3;
            dest[destOffset + 1] = _selfy * _t3;
            dest[destOffset + 2] = _selfz * _t3;
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    /** {@link #axis(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer axis(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.axis_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.axis_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #axis(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer axis(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.axis_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.axis_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #axis(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment axis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.axis_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.axis_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #axis(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long axis(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.axis_unsafe(dest, src);
        axis(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Recompute the {@code w} component of this quaternion from {@code x}, {@code y} and {@code z},
     * assuming unit length (the positive square root is chosen) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] calculateW(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.calculateW(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsArray.calculateW_scalar(dest, destOffset, src, srcOffset);
    }

    /** {@link #calculateW(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer calculateW(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.calculateW_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.calculateW_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #calculateW(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer calculateW(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.calculateW_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.calculateW_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #calculateW(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment calculateW(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.calculateW(dest, destOffset, src, srcOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.calculateW_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.calculateW_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #calculateW(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long calculateW(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.calculateW_unsafe(dest, src);
        calculateW(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Conjugate this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] conjugate(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset + 0] = -_selfx;
        dest[destOffset + 1] = -_selfy;
        dest[destOffset + 2] = -_selfz;
        dest[destOffset + 3] = _selfw;
        return dest;
    }

    /** {@link #conjugate(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer conjugate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.conjugate_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.conjugate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #conjugate(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer conjugate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.conjugate_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.conjugate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #conjugate(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment conjugate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.conjugate_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.conjugate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #conjugate(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long conjugate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.conjugate_unsafe(dest, src);
        conjugate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Conjugate this quaternion by ({@code qX}, {@code qY}, {@code qZ}, {@code qW}), i.e. compute
     * {@code q * this * conj(q)} where {@code q} is the given quaternion (equal to
     * {@code q * this * q^-1} when it has unit length) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (qX, qY, qZ, qW)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param qX the {@code x} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qY the {@code y} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qZ the {@code z} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qW the {@code w} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @return {@code dest}
     */
    public static float[] conjugateBy(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t20 = Math.fma(qX, _selfy, qW * _selfz) + Math.fma(qZ, _selfw, -(qY * _selfx));
        float _t21 = Math.fma(qY, _selfw, qZ * _selfx) + Math.fma(qW, _selfy, -(qX * _selfz));
        float _t22 = Math.fma(qX, _selfw, qW * _selfx) + Math.fma(qY, _selfz, -(qZ * _selfy));
        float _t23 = Math.fma(qW, _selfw, -(qX * _selfx)) - Math.fma(qY, _selfy, qZ * _selfz);
        dest[destOffset + 0] = Math.fma(qY, _t20, -(qZ * _t21)) + Math.fma(qW, _t22, -(qX * _t23));
        dest[destOffset + 1] = Math.fma(qZ, _t22, -(qY * _t23)) + Math.fma(qW, _t21, -(qX * _t20));
        dest[destOffset + 2] = Math.fma(qX, _t21, qW * _t20) + Math.fma(-qY, _t22, -(qZ * _t23));
        dest[destOffset + 3] = Math.fma(qX, _t22, qW * _t23) - Math.fma(-qZ, _t20, -(qY * _t21));
        return dest;
    }

    /** {@link #conjugateBy(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer conjugateBy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float qX, float qY, float qZ, float qW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.conjugateBy_unsafe(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return FloatQuatOpsKernelsTypedBuffer.conjugateBy_api(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    /** {@link #conjugateBy(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer conjugateBy(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float qX, float qY, float qZ, float qW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.conjugateBy_unsafe(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return FloatQuatOpsKernelsByteBuffer.conjugateBy_api(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    /** {@link #conjugateBy(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment conjugateBy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qX, float qY, float qZ, float qW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.conjugateBy_unsafe(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return FloatQuatOpsKernelsSegment.conjugateBy_api(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    /** {@link #conjugateBy(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long conjugateBy(long dest, long src, float qX, float qY, float qZ, float qW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.conjugateBy_unsafe(dest, src, qX, qY, qZ, qW);
        conjugateBy(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, qX, qY, qZ, qW);
        return dest;
    }

    /**
     * Conjugate this quaternion by {@code q}, i.e. compute {@code q * this * conj(q)} where
     * {@code q} is the given quaternion (equal to {@code q * this * q^-1} when it has unit length)
     * and store the result in {@code dest}.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param q the storage holding the quaternion to conjugate by
     * @param qOffset the element index in {@code q} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] conjugateBy(float[] dest, int destOffset, float[] src, int srcOffset, float[] q, int qOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _qx = q[qOffset + 0];
        float _qy = q[qOffset + 1];
        float _qz = q[qOffset + 2];
        float _qw = q[qOffset + 3];
        float _t20 = Math.fma(_qx, _selfy, _qw * _selfz) + Math.fma(_qz, _selfw, -(_qy * _selfx));
        float _t21 = Math.fma(_qy, _selfw, _qz * _selfx) + Math.fma(_qw, _selfy, -(_qx * _selfz));
        float _t22 = Math.fma(_qx, _selfw, _qw * _selfx) + Math.fma(_qy, _selfz, -(_qz * _selfy));
        float _t23 = Math.fma(_qw, _selfw, -(_qx * _selfx)) - Math.fma(_qy, _selfy, _qz * _selfz);
        dest[destOffset + 0] = Math.fma(_qy, _t20, -(_qz * _t21)) + Math.fma(_qw, _t22, -(_qx * _t23));
        dest[destOffset + 1] = Math.fma(_qz, _t22, -(_qy * _t23)) + Math.fma(_qw, _t21, -(_qx * _t20));
        dest[destOffset + 2] = Math.fma(_qx, _t21, _qw * _t20) + Math.fma(-_qy, _t22, -(_qz * _t23));
        dest[destOffset + 3] = Math.fma(_qx, _t22, _qw * _t23) - Math.fma(-_qz, _t20, -(_qy * _t21));
        return dest;
    }

    /** {@link #conjugateBy(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer conjugateBy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer q, int qOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && q.isDirect() && q.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.conjugateBy_unsafe(dest, destOffset, src, srcOffset, q, qOffset);
        return FloatQuatOpsKernelsTypedBuffer.conjugateBy_api(dest, destOffset, src, srcOffset, q, qOffset);
    }

    /** {@link #conjugateBy(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer conjugateBy(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer q, int qOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && q.isDirect() && q.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.conjugateBy_unsafe(dest, destOffset, src, srcOffset, q, qOffset);
        return FloatQuatOpsKernelsByteBuffer.conjugateBy_api(dest, destOffset, src, srcOffset, q, qOffset);
    }

    /** {@link #conjugateBy(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment conjugateBy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && q.isNative()) return FloatQuatOpsKernelsSegment.conjugateBy_unsafe(dest, destOffset, src, srcOffset, q, qOffset);
        return FloatQuatOpsKernelsSegment.conjugateBy_api(dest, destOffset, src, srcOffset, q, qOffset);
    }

    /** {@link #conjugateBy(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long conjugateBy(long dest, long src, long q) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.conjugateBy_unsafe(dest, src, q);
        conjugateBy(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(q, 16L), 0L);
        return dest;
    }

    /**
     * Compute the difference between this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}), i.e. the rotation {@code D} with
     * {@code this * D = (otherX, otherY, otherZ, otherW)}, that is
     * {@code D = this^-1 * (otherX, otherY, otherZ, otherW)} and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @return {@code dest}
     */
    public static float[] difference(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t3_inv = 1.0f / Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        dest[destOffset + 0] = (Math.fma(otherX, _selfw, -(otherW * _selfx)) + Math.fma(otherY, _selfz, -(otherZ * _selfy))) * _t3_inv;
        dest[destOffset + 1] = -(otherW * _sp0) - otherX * _sp1 + Math.fma(otherY, _selfw, otherZ * _selfx) * _t3_inv;
        dest[destOffset + 2] = (Math.fma(otherX, _selfy, -(otherW * _selfz)) + Math.fma(otherZ, _selfw, -(otherY * _selfx))) * _t3_inv;
        dest[destOffset + 3] = Math.fma(otherX, _selfx, otherW * _selfw) * _t3_inv - (-(otherY * _sp0) - otherZ * _sp1);
        return dest;
    }

    /** {@link #difference(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer difference(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.difference_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsTypedBuffer.difference_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #difference(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer difference(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.difference_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsByteBuffer.difference_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #difference(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment difference(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.difference_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsSegment.difference_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #difference(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long difference(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.difference_unsafe(dest, src, otherX, otherY, otherZ, otherW);
        difference(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW);
        return dest;
    }

    /**
     * Compute the difference between this quaternion and {@code other}, i.e. the rotation {@code D}
     * with {@code this * D = other}, that is {@code D = this^-1 * other} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param other the storage holding the target quaternion, reached by composing this quaternion
     *        with the result
     * @param otherOffset the element index in {@code other} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] difference(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        float _t3_inv = 1.0f / Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _sp1 = _t3_inv * _selfz;
        float _sp0 = _selfy * _t3_inv;
        dest[destOffset + 0] = (Math.fma(_otherx, _selfw, -(_otherw * _selfx)) + Math.fma(_othery, _selfz, -(_otherz * _selfy))) * _t3_inv;
        dest[destOffset + 1] = -(_otherw * _sp0) - _otherx * _sp1 + Math.fma(_othery, _selfw, _otherz * _selfx) * _t3_inv;
        dest[destOffset + 2] = (Math.fma(_otherx, _selfy, -(_otherw * _selfz)) + Math.fma(_otherz, _selfw, -(_othery * _selfx))) * _t3_inv;
        dest[destOffset + 3] = Math.fma(_otherx, _selfx, _otherw * _selfw) * _t3_inv - (-(_othery * _sp0) - _otherz * _sp1);
        return dest;
    }

    /** {@link #difference(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer difference(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.difference_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsTypedBuffer.difference_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #difference(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer difference(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.difference_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsByteBuffer.difference_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #difference(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment difference(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && other.isNative()) return FloatQuatOpsKernelsSegment.difference_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsSegment.difference_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #difference(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long difference(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.difference_unsafe(dest, src, other);
        difference(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
        return dest;
    }

    /**
     * Compute the dot product of this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @return the dot product of this quaternion and {@code other}
     */
    public static float dot(float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        return Math.fma(otherW, _selfw, Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy)));
    }

    /** {@link #dot(float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float dot(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.dot_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsTypedBuffer.dot_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #dot(float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float dot(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.dot_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsByteBuffer.dot_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #dot(float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float dot(java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return FloatQuatOpsKernelsSegment.dot_unsafe(src, srcOffset, otherX, otherY, otherZ, otherW);
        return FloatQuatOpsKernelsSegment.dot_api(src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #dot(float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float dot(long src, float otherX, float otherY, float otherZ, float otherW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.dot_unsafe(src, otherX, otherY, otherZ, otherW);
        return dot(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, otherX, otherY, otherZ, otherW);
    }

    /**
     * Compute the dot product of this quaternion and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param other the storage holding the other operand of the dot product
     * @param otherOffset the element index in {@code other} at which the quaternion starts
     * @return the dot product of this quaternion and {@code other}
     */
    public static float dot(float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        return Math.fma(_otherw, _selfw, Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy)));
    }

    /** {@link #dot(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float dot(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.dot_unsafe(src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsTypedBuffer.dot_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #dot(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float dot(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.dot_unsafe(src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsByteBuffer.dot_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #dot(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float dot(java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && other.isNative()) return FloatQuatOpsKernelsSegment.dot_unsafe(src, srcOffset, other, otherOffset);
        return FloatQuatOpsKernelsSegment.dot_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #dot(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float dot(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.dot_unsafe(src, other);
        return dot(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(other, 16L), 0L);
    }

    /**
     * Compute the exponential of this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] exp(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.exp(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsArray.exp_scalar(dest, destOffset, src, srcOffset);
    }

    /** {@link #exp(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer exp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.exp_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.exp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #exp(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer exp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.exp_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.exp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #exp(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment exp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.exp(dest, destOffset, src, srcOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.exp_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.exp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #exp(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long exp(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.exp_unsafe(dest, src);
        exp(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the X, Y and Z axes,
     * in that order and store the result in {@code dest}.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] getEulerAnglesXYZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t1 = _selfy * _selfz;
        float _t3 = _selfz * _selfz;
        float _t8 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, -_t1);
        float _t10 = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < Math.fma(_t8, _t8, _t12) * 1.0E-7f) {
            dest[destOffset + 0] = (float) Math.atan2(2.0f * Math.fma(_selfx, _selfw, _t1), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t3), 1.0f));
            dest[destOffset + 2] = 0.0f;
        } else {
            dest[destOffset + 0] = (float) Math.atan2(_t9, _t10);
            dest[destOffset + 2] = (float) Math.atan2(2.0f * Math.fma(_selfz, _selfw, -(_selfx * _selfy)), Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t3), 1.0f));
        }
        dest[destOffset + 1] = (float) Math.atan2(_t8, (float) Math.sqrt(_t12));
        return dest;
    }

    /** {@link #getEulerAnglesXYZ(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer getEulerAnglesXYZ(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.getEulerAnglesXYZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.getEulerAnglesXYZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesXYZ(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getEulerAnglesXYZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.getEulerAnglesXYZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.getEulerAnglesXYZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesXYZ(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getEulerAnglesXYZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.getEulerAnglesXYZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.getEulerAnglesXYZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesXYZ(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getEulerAnglesXYZ(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.getEulerAnglesXYZ_unsafe(dest, src);
        getEulerAnglesXYZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the X, Z and Y axes,
     * in that order and store the result in {@code dest}.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] getEulerAnglesXZY(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = _selfz * _selfz;
        float _t1 = _selfy * _selfz;
        float _t7 = 2.0f * Math.fma(_selfx, _selfw, _t1);
        float _t8 = 2.0f * Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        float _t9 = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            dest[destOffset + 0] = (float) Math.atan2(2.0f * Math.fma(_selfx, _selfw, -_t1), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
            dest[destOffset + 1] = 0.0f;
        } else {
            dest[destOffset + 0] = (float) Math.atan2(_t7, _t9);
            dest[destOffset + 1] = (float) Math.atan2(2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw), Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f));
        }
        dest[destOffset + 2] = (float) Math.atan2(_t8, (float) Math.sqrt(_t11));
        return dest;
    }

    /** {@link #getEulerAnglesXZY(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer getEulerAnglesXZY(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.getEulerAnglesXZY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.getEulerAnglesXZY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesXZY(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getEulerAnglesXZY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.getEulerAnglesXZY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.getEulerAnglesXZY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesXZY(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getEulerAnglesXZY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.getEulerAnglesXZY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.getEulerAnglesXZY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesXZY(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getEulerAnglesXZY(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.getEulerAnglesXZY_unsafe(dest, src);
        getEulerAnglesXZY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Y, X and Z axes,
     * in that order and store the result in {@code dest}.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] getEulerAnglesYXZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t3 = _selfz * _selfz;
        float _t8 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, -(_selfy * _selfz));
        float _t10 = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        float _t12 = Math.fma(_t10, _t10, _t8 * _t8);
        if (_t12 < Math.fma(_t9, _t9, _t12) * 1.0E-7f) {
            dest[destOffset + 1] = (float) Math.atan2(2.0f * Math.fma(_selfy, _selfw, -(_selfx * _selfz)), Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t3), 1.0f));
            dest[destOffset + 2] = 0.0f;
        } else {
            dest[destOffset + 1] = (float) Math.atan2(_t8, _t10);
            dest[destOffset + 2] = (float) Math.atan2(2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t3), 1.0f));
        }
        dest[destOffset + 0] = (float) Math.atan2(_t9, (float) Math.sqrt(_t12));
        return dest;
    }

    /** {@link #getEulerAnglesYXZ(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer getEulerAnglesYXZ(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.getEulerAnglesYXZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.getEulerAnglesYXZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesYXZ(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getEulerAnglesYXZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.getEulerAnglesYXZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.getEulerAnglesYXZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesYXZ(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getEulerAnglesYXZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.getEulerAnglesYXZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.getEulerAnglesYXZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesYXZ(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getEulerAnglesYXZ(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.getEulerAnglesYXZ_unsafe(dest, src);
        getEulerAnglesYXZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Y, Z and X axes,
     * in that order and store the result in {@code dest}.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] getEulerAnglesYZX(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = _selfz * _selfz;
        float _t7 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t8 = 2.0f * Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        float _t9 = Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = (float) Math.atan2(2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
        } else {
            dest[destOffset + 0] = (float) Math.atan2(2.0f * Math.fma(_selfx, _selfw, -(_selfy * _selfz)), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f));
            dest[destOffset + 1] = (float) Math.atan2(_t8, _t9);
        }
        dest[destOffset + 2] = (float) Math.atan2(_t7, (float) Math.sqrt(_t11));
        return dest;
    }

    /** {@link #getEulerAnglesYZX(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer getEulerAnglesYZX(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.getEulerAnglesYZX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.getEulerAnglesYZX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesYZX(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getEulerAnglesYZX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.getEulerAnglesYZX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.getEulerAnglesYZX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesYZX(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getEulerAnglesYZX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.getEulerAnglesYZX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.getEulerAnglesYZX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesYZX(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getEulerAnglesYZX(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.getEulerAnglesYZX_unsafe(dest, src);
        getEulerAnglesYZX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Z, X and Y axes,
     * in that order and store the result in {@code dest}.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] getEulerAnglesZXY(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t1 = _selfz * _selfz;
        float _t7 = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t8 = 2.0f * Math.fma(_selfz, _selfw, -(_selfx * _selfy));
        float _t9 = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t1), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = (float) Math.atan2(2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw), Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t1), 1.0f));
        } else {
            dest[destOffset + 1] = (float) Math.atan2(2.0f * Math.fma(_selfy, _selfw, -(_selfx * _selfz)), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
            dest[destOffset + 2] = (float) Math.atan2(_t8, _t9);
        }
        dest[destOffset + 0] = (float) Math.atan2(_t7, (float) Math.sqrt(_t11));
        return dest;
    }

    /** {@link #getEulerAnglesZXY(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer getEulerAnglesZXY(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.getEulerAnglesZXY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.getEulerAnglesZXY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesZXY(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getEulerAnglesZXY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.getEulerAnglesZXY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.getEulerAnglesZXY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesZXY(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getEulerAnglesZXY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.getEulerAnglesZXY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.getEulerAnglesZXY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesZXY(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getEulerAnglesZXY(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.getEulerAnglesZXY_unsafe(dest, src);
        getEulerAnglesZXY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Get the Euler angles in radians of this quaternion, to be applied about the Z, Y and X axes,
     * in that order and store the result in {@code dest}.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] getEulerAnglesZYX(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = _selfz * _selfz;
        float _t7 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t8 = 2.0f * Math.fma(_selfy, _selfw, -(_selfx * _selfz));
        float _t9 = Math.fma(-2.0f, Math.fma(_selfy, _selfy, _t0), 1.0f);
        float _t11 = Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 2] = (float) Math.atan2(2.0f * Math.fma(_selfz, _selfw, -(_selfx * _selfy)), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _t0), 1.0f));
        } else {
            dest[destOffset + 0] = (float) Math.atan2(2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz), Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f));
            dest[destOffset + 2] = (float) Math.atan2(_t7, _t9);
        }
        dest[destOffset + 1] = (float) Math.atan2(_t8, (float) Math.sqrt(_t11));
        return dest;
    }

    /** {@link #getEulerAnglesZYX(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer getEulerAnglesZYX(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.getEulerAnglesZYX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.getEulerAnglesZYX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesZYX(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer getEulerAnglesZYX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.getEulerAnglesZYX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.getEulerAnglesZYX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesZYX(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment getEulerAnglesZYX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.getEulerAnglesZYX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.getEulerAnglesZYX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #getEulerAnglesZYX(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long getEulerAnglesZYX(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.getEulerAnglesZYX_unsafe(dest, src);
        getEulerAnglesZYX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Integrate the given angular velocity over the given time step and apply the resulting
     * rotation to this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param angularVelX the {@code x} component of the vector
     *        {@code (angularVelX, angularVelY, angularVelZ)}
     * @param angularVelY the {@code y} component of the vector
     *        {@code (angularVelX, angularVelY, angularVelZ)}
     * @param angularVelZ the {@code z} component of the vector
     *        {@code (angularVelX, angularVelY, angularVelZ)}
     * @param dt the time step
     * @return {@code dest}
     */
    public static float[] integrate(float[] dest, int destOffset, float[] src, int srcOffset, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = 0.5f * dt;
        float _t1 = angularVelZ * _t0;
        float _t2 = angularVelX * _t0;
        float _t3 = angularVelY * _t0;
        float _t6 = Math.fma(_t1, _t1, Math.fma(_t2, _t2, _t3 * _t3));
        float _t7 = (float) Math.sqrt(_t6);
        float _t9 = (float) Math.sin(_t7);
        float _t10 = (float) Math.cosFromSin(_t9, _t7);
        float _t11 = _t9 / _t7;
        float _t15, _t16, _t17;
        if (_t6 > 0.0f) {
            _t15 = _t2 * _t11;
            _t16 = _t3 * _t11;
            _t17 = _t1 * _t11;
        } else {
            _t15 = 0.0f;
            _t16 = 0.0f;
            _t17 = 0.0f;
        }
        dest[destOffset + 0] = Math.fma(_selfx, _t10, _selfw * _t15) + Math.fma(_selfz, _t16, -(_selfy * _t17));
        dest[destOffset + 1] = Math.fma(_selfx, _t17, _selfw * _t16) + Math.fma(_selfy, _t10, -(_selfz * _t15));
        dest[destOffset + 2] = Math.fma(_selfy, _t15, _selfz * _t10) + Math.fma(_selfw, _t17, -(_selfx * _t16));
        dest[destOffset + 3] = Math.fma(_selfw, _t10, -(_selfx * _t15)) - Math.fma(_selfy, _t16, _selfz * _t17);
        return dest;
    }

    /** {@link #integrate(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer integrate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.integrate_unsafe(dest, destOffset, src, srcOffset, angularVelX, angularVelY, angularVelZ, dt);
        return FloatQuatOpsKernelsTypedBuffer.integrate_api(dest, destOffset, src, srcOffset, angularVelX, angularVelY, angularVelZ, dt);
    }

    /** {@link #integrate(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer integrate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.integrate_unsafe(dest, destOffset, src, srcOffset, angularVelX, angularVelY, angularVelZ, dt);
        return FloatQuatOpsKernelsByteBuffer.integrate_api(dest, destOffset, src, srcOffset, angularVelX, angularVelY, angularVelZ, dt);
    }

    /** {@link #integrate(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment integrate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.integrate_unsafe(dest, destOffset, src, srcOffset, angularVelX, angularVelY, angularVelZ, dt);
        return FloatQuatOpsKernelsSegment.integrate_api(dest, destOffset, src, srcOffset, angularVelX, angularVelY, angularVelZ, dt);
    }

    /** {@link #integrate(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long integrate(long dest, long src, float angularVelX, float angularVelY, float angularVelZ, float dt) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.integrate_unsafe(dest, src, angularVelX, angularVelY, angularVelZ, dt);
        integrate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, angularVelX, angularVelY, angularVelZ, dt);
        return dest;
    }

    /**
     * Integrate the given angular velocity over the given time step and apply the resulting
     * rotation to this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param angularVel the storage holding the angular velocity, in radians per second, applied in
     *        the reference frame
     * @param angularVelOffset the element index in {@code angularVel} at which the vector starts
     * @param dt the time step
     * @return {@code dest}
     */
    public static float[] integrate(float[] dest, int destOffset, float[] src, int srcOffset, float[] angularVel, int angularVelOffset, float dt) {
        return integrate(dest, destOffset, src, srcOffset, angularVel[angularVelOffset + 0], angularVel[angularVelOffset + 1], angularVel[angularVelOffset + 2], dt);
    }

    /** {@link #integrate(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer integrate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer angularVel, int angularVelOffset, float dt) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && angularVel.isDirect() && angularVel.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.integrate_unsafe(dest, destOffset, src, srcOffset, angularVel, angularVelOffset, dt);
        return FloatQuatOpsKernelsTypedBuffer.integrate_api(dest, destOffset, src, srcOffset, angularVel, angularVelOffset, dt);
    }

    /** {@link #integrate(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer integrate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer angularVel, int angularVelOffset, float dt) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && angularVel.isDirect() && angularVel.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.integrate_unsafe(dest, destOffset, src, srcOffset, angularVel, angularVelOffset, dt);
        return FloatQuatOpsKernelsByteBuffer.integrate_api(dest, destOffset, src, srcOffset, angularVel, angularVelOffset, dt);
    }

    /** {@link #integrate(float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment integrate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment angularVel, long angularVelOffset, float dt) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && angularVel.isNative()) return FloatQuatOpsKernelsSegment.integrate_unsafe(dest, destOffset, src, srcOffset, angularVel, angularVelOffset, dt);
        return FloatQuatOpsKernelsSegment.integrate_api(dest, destOffset, src, srcOffset, angularVel, angularVelOffset, dt);
    }

    /** {@link #integrate(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long integrate(long dest, long src, long angularVel, float dt) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.integrate_unsafe(dest, src, angularVel, dt);
        integrate(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(angularVel, 12L), 0L, dt);
        return dest;
    }

    /**
     * Obtain the direction of {@code -X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] invNegativeX(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t9 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest[destOffset + 0] = -(_t12 * _t16);
            dest[destOffset + 1] = -(_t10 * _t16);
            dest[destOffset + 2] = -(_t9 * _t16);
        } else {
            dest[destOffset + 0] = -0.0f;
            dest[destOffset + 1] = -0.0f;
            dest[destOffset + 2] = -0.0f;
        }
        return dest;
    }

    /** {@link #invNegativeX(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer invNegativeX(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.invNegativeX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.invNegativeX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNegativeX(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invNegativeX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.invNegativeX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.invNegativeX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNegativeX(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment invNegativeX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.invNegativeX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.invNegativeX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNegativeX(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long invNegativeX(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.invNegativeX_unsafe(dest, src);
        invNegativeX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code -Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] invNegativeY(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t9 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest[destOffset + 0] = -(_t9 * _t16);
            dest[destOffset + 1] = -(_t12 * _t16);
            dest[destOffset + 2] = -(_t10 * _t16);
        } else {
            dest[destOffset + 0] = -0.0f;
            dest[destOffset + 1] = -0.0f;
            dest[destOffset + 2] = -0.0f;
        }
        return dest;
    }

    /** {@link #invNegativeY(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer invNegativeY(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.invNegativeY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.invNegativeY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNegativeY(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invNegativeY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.invNegativeY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.invNegativeY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNegativeY(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment invNegativeY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.invNegativeY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.invNegativeY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNegativeY(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long invNegativeY(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.invNegativeY_unsafe(dest, src);
        invNegativeY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code -Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] invNegativeZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest[destOffset + 0] = -(_t10 * _t16);
            dest[destOffset + 1] = -(_t9 * _t16);
            dest[destOffset + 2] = -(_t12 * _t16);
        } else {
            dest[destOffset + 0] = -0.0f;
            dest[destOffset + 1] = -0.0f;
            dest[destOffset + 2] = -0.0f;
        }
        return dest;
    }

    /** {@link #invNegativeZ(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer invNegativeZ(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.invNegativeZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.invNegativeZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNegativeZ(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invNegativeZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.invNegativeZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.invNegativeZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNegativeZ(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment invNegativeZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.invNegativeZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.invNegativeZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNegativeZ(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long invNegativeZ(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.invNegativeZ_unsafe(dest, src);
        invNegativeZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code -X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] invNormalizedNegativeX(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset + 0] = Math.fma(2.0f, Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0f);
        dest[destOffset + 1] = -(2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
        dest[destOffset + 2] = -(2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw));
        return dest;
    }

    /** {@link #invNormalizedNegativeX(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer invNormalizedNegativeX(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.invNormalizedNegativeX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.invNormalizedNegativeX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNormalizedNegativeX(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invNormalizedNegativeX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.invNormalizedNegativeX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.invNormalizedNegativeX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNormalizedNegativeX(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment invNormalizedNegativeX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.invNormalizedNegativeX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.invNormalizedNegativeX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNormalizedNegativeX(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long invNormalizedNegativeX(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.invNormalizedNegativeX_unsafe(dest, src);
        invNormalizedNegativeX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code -Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] invNormalizedNegativeY(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset + 0] = -(2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw));
        dest[destOffset + 1] = Math.fma(2.0f, Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0f);
        dest[destOffset + 2] = -(2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        return dest;
    }

    /** {@link #invNormalizedNegativeY(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer invNormalizedNegativeY(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.invNormalizedNegativeY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.invNormalizedNegativeY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNormalizedNegativeY(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invNormalizedNegativeY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.invNormalizedNegativeY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.invNormalizedNegativeY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNormalizedNegativeY(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment invNormalizedNegativeY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.invNormalizedNegativeY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.invNormalizedNegativeY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNormalizedNegativeY(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long invNormalizedNegativeY(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.invNormalizedNegativeY_unsafe(dest, src);
        invNormalizedNegativeY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code -Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] invNormalizedNegativeZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset + 0] = -(2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
        dest[destOffset + 1] = -(2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz));
        dest[destOffset + 2] = Math.fma(2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0f);
        return dest;
    }

    /** {@link #invNormalizedNegativeZ(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer invNormalizedNegativeZ(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.invNormalizedNegativeZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.invNormalizedNegativeZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNormalizedNegativeZ(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invNormalizedNegativeZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.invNormalizedNegativeZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.invNormalizedNegativeZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNormalizedNegativeZ(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment invNormalizedNegativeZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.invNormalizedNegativeZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.invNormalizedNegativeZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNormalizedNegativeZ(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long invNormalizedNegativeZ(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.invNormalizedNegativeZ_unsafe(dest, src);
        invNormalizedNegativeZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code +X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] invNormalizedPositiveX(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset + 0] = Math.fma(-2.0f, Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f);
        dest[destOffset + 1] = 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        dest[destOffset + 2] = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        return dest;
    }

    /** {@link #invNormalizedPositiveX(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer invNormalizedPositiveX(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.invNormalizedPositiveX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.invNormalizedPositiveX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNormalizedPositiveX(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invNormalizedPositiveX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.invNormalizedPositiveX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.invNormalizedPositiveX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNormalizedPositiveX(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment invNormalizedPositiveX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.invNormalizedPositiveX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.invNormalizedPositiveX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNormalizedPositiveX(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long invNormalizedPositiveX(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.invNormalizedPositiveX_unsafe(dest, src);
        invNormalizedPositiveX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code +Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] invNormalizedPositiveY(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset + 0] = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        dest[destOffset + 1] = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f);
        dest[destOffset + 2] = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        return dest;
    }

    /** {@link #invNormalizedPositiveY(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer invNormalizedPositiveY(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.invNormalizedPositiveY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.invNormalizedPositiveY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNormalizedPositiveY(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invNormalizedPositiveY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.invNormalizedPositiveY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.invNormalizedPositiveY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNormalizedPositiveY(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment invNormalizedPositiveY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.invNormalizedPositiveY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.invNormalizedPositiveY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNormalizedPositiveY(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long invNormalizedPositiveY(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.invNormalizedPositiveY_unsafe(dest, src);
        invNormalizedPositiveY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code +Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] invNormalizedPositiveZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset + 0] = 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        dest[destOffset + 1] = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        dest[destOffset + 2] = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        return dest;
    }

    /** {@link #invNormalizedPositiveZ(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer invNormalizedPositiveZ(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.invNormalizedPositiveZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.invNormalizedPositiveZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNormalizedPositiveZ(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invNormalizedPositiveZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.invNormalizedPositiveZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.invNormalizedPositiveZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNormalizedPositiveZ(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment invNormalizedPositiveZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.invNormalizedPositiveZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.invNormalizedPositiveZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invNormalizedPositiveZ(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long invNormalizedPositiveZ(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.invNormalizedPositiveZ_unsafe(dest, src);
        invNormalizedPositiveZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code +X} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] invPositiveX(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t9 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest[destOffset + 0] = _t12 * _t16;
            dest[destOffset + 1] = _t10 * _t16;
            dest[destOffset + 2] = _t9 * _t16;
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    /** {@link #invPositiveX(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer invPositiveX(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.invPositiveX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.invPositiveX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invPositiveX(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invPositiveX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.invPositiveX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.invPositiveX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invPositiveX(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment invPositiveX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.invPositiveX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.invPositiveX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invPositiveX(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long invPositiveX(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.invPositiveX_unsafe(dest, src);
        invPositiveX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code +Y} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] invPositiveY(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t9 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest[destOffset + 0] = _t9 * _t16;
            dest[destOffset + 1] = _t12 * _t16;
            dest[destOffset + 2] = _t10 * _t16;
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    /** {@link #invPositiveY(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer invPositiveY(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.invPositiveY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.invPositiveY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invPositiveY(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invPositiveY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.invPositiveY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.invPositiveY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invPositiveY(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment invPositiveY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.invPositiveY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.invPositiveY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invPositiveY(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long invPositiveY(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.invPositiveY_unsafe(dest, src);
        invPositiveY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code +Z} before the transformation represented by this quaternion
     * is applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] invPositiveZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest[destOffset + 0] = _t10 * _t16;
            dest[destOffset + 1] = _t9 * _t16;
            dest[destOffset + 2] = _t12 * _t16;
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    /** {@link #invPositiveZ(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer invPositiveZ(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.invPositiveZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.invPositiveZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invPositiveZ(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer invPositiveZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.invPositiveZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.invPositiveZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invPositiveZ(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment invPositiveZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.invPositiveZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.invPositiveZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #invPositiveZ(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long invPositiveZ(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.invPositiveZ_unsafe(dest, src);
        invPositiveZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Compute the length of this quaternion.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return the length of this quaternion
     */
    public static float length(float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        return (float) Math.sqrt(Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy))));
    }

    /** {@link #length(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float length(java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.length_unsafe(src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.length_api(src, srcOffset);
    }

    /** {@link #length(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float length(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.length_unsafe(src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.length_api(src, srcOffset);
    }

    /** {@link #length(float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float length(java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return FloatQuatOpsKernelsSegment.length_unsafe(src, srcOffset);
        return FloatQuatOpsKernelsSegment.length_api(src, srcOffset);
    }

    /** {@link #length(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float length(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.length_unsafe(src);
        return length(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
    }

    /**
     * Compute the squared length of this quaternion.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return the squared length of this quaternion
     */
    public static float lengthSquared(float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        return Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
    }

    /** {@link #lengthSquared(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float lengthSquared(java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.lengthSquared_unsafe(src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.lengthSquared_api(src, srcOffset);
    }

    /** {@link #lengthSquared(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float lengthSquared(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.lengthSquared_unsafe(src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.lengthSquared_api(src, srcOffset);
    }

    /** {@link #lengthSquared(float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float lengthSquared(java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return FloatQuatOpsKernelsSegment.lengthSquared_unsafe(src, srcOffset);
        return FloatQuatOpsKernelsSegment.lengthSquared_api(src, srcOffset);
    }

    /** {@link #lengthSquared(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static float lengthSquared(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.lengthSquared_unsafe(src);
        return lengthSquared(VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
    }

    /**
     * Compute the natural logarithm of this quaternion and store the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: this quaternion must be non-zero.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] log(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.log(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsArray.log_scalar(dest, destOffset, src, srcOffset);
    }

    /** {@link #log(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer log(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.log_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.log_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer log(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.log_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.log_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment log(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.log(dest, destOffset, src, srcOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.log_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.log_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long log(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.log_unsafe(dest, src);
        log(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code -X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] negativeX(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t9 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest[destOffset + 0] = -(_t12 * _t16);
            dest[destOffset + 1] = -(_t9 * _t16);
            dest[destOffset + 2] = -(_t10 * _t16);
        } else {
            dest[destOffset + 0] = -0.0f;
            dest[destOffset + 1] = -0.0f;
            dest[destOffset + 2] = -0.0f;
        }
        return dest;
    }

    /** {@link #negativeX(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer negativeX(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.negativeX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.negativeX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negativeX(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer negativeX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.negativeX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.negativeX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negativeX(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment negativeX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.negativeX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.negativeX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negativeX(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long negativeX(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.negativeX_unsafe(dest, src);
        negativeX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code -Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] negativeY(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest[destOffset + 0] = -(_t10 * _t16);
            dest[destOffset + 1] = -(_t12 * _t16);
            dest[destOffset + 2] = -(_t9 * _t16);
        } else {
            dest[destOffset + 0] = -0.0f;
            dest[destOffset + 1] = -0.0f;
            dest[destOffset + 2] = -0.0f;
        }
        return dest;
    }

    /** {@link #negativeY(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer negativeY(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.negativeY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.negativeY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negativeY(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer negativeY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.negativeY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.negativeY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negativeY(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment negativeY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.negativeY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.negativeY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negativeY(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long negativeY(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.negativeY_unsafe(dest, src);
        negativeY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code -Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] negativeZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t9 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        float _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest[destOffset + 0] = -(_t9 * _t16);
            dest[destOffset + 1] = -(_t10 * _t16);
            dest[destOffset + 2] = -(_t12 * _t16);
        } else {
            dest[destOffset + 0] = -0.0f;
            dest[destOffset + 1] = -0.0f;
            dest[destOffset + 2] = -0.0f;
        }
        return dest;
    }

    /** {@link #negativeZ(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer negativeZ(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.negativeZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.negativeZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negativeZ(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer negativeZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.negativeZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.negativeZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negativeZ(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment negativeZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.negativeZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.negativeZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negativeZ(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long negativeZ(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.negativeZ_unsafe(dest, src);
        negativeZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Normalize this quaternion to unit length and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] normalize(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.normalize(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsArray.normalize_scalar(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalize(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer normalize(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.normalize_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.normalize_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalize(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer normalize(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.normalize_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.normalize_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalize(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment normalize(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.normalize(dest, destOffset, src, srcOffset);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.normalize_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.normalize_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalize(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long normalize(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.normalize_unsafe(dest, src);
        normalize(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code -X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] normalizedNegativeX(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset + 0] = Math.fma(2.0f, Math.fma(_selfy, _selfy, _selfz * _selfz), -1.0f);
        dest[destOffset + 1] = -(2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw));
        dest[destOffset + 2] = -(2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw)));
        return dest;
    }

    /** {@link #normalizedNegativeX(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer normalizedNegativeX(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.normalizedNegativeX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.normalizedNegativeX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalizedNegativeX(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer normalizedNegativeX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.normalizedNegativeX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.normalizedNegativeX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalizedNegativeX(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment normalizedNegativeX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.normalizedNegativeX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.normalizedNegativeX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalizedNegativeX(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long normalizedNegativeX(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.normalizedNegativeX_unsafe(dest, src);
        normalizedNegativeX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code -Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] normalizedNegativeY(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset + 0] = -(2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw)));
        dest[destOffset + 1] = Math.fma(2.0f, Math.fma(_selfx, _selfx, _selfz * _selfz), -1.0f);
        dest[destOffset + 2] = -(2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz));
        return dest;
    }

    /** {@link #normalizedNegativeY(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer normalizedNegativeY(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.normalizedNegativeY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.normalizedNegativeY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalizedNegativeY(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer normalizedNegativeY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.normalizedNegativeY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.normalizedNegativeY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalizedNegativeY(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment normalizedNegativeY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.normalizedNegativeY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.normalizedNegativeY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalizedNegativeY(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long normalizedNegativeY(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.normalizedNegativeY_unsafe(dest, src);
        normalizedNegativeY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code -Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] normalizedNegativeZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset + 0] = -(2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw));
        dest[destOffset + 1] = -(2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw)));
        dest[destOffset + 2] = Math.fma(2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), -1.0f);
        return dest;
    }

    /** {@link #normalizedNegativeZ(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer normalizedNegativeZ(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.normalizedNegativeZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.normalizedNegativeZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalizedNegativeZ(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer normalizedNegativeZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.normalizedNegativeZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.normalizedNegativeZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalizedNegativeZ(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment normalizedNegativeZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.normalizedNegativeZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.normalizedNegativeZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalizedNegativeZ(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long normalizedNegativeZ(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.normalizedNegativeZ_unsafe(dest, src);
        normalizedNegativeZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code +X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] normalizedPositiveX(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset + 0] = Math.fma(-2.0f, Math.fma(_selfy, _selfy, _selfz * _selfz), 1.0f);
        dest[destOffset + 1] = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        dest[destOffset + 2] = 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        return dest;
    }

    /** {@link #normalizedPositiveX(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer normalizedPositiveX(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.normalizedPositiveX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.normalizedPositiveX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalizedPositiveX(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer normalizedPositiveX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.normalizedPositiveX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.normalizedPositiveX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalizedPositiveX(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment normalizedPositiveX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.normalizedPositiveX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.normalizedPositiveX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalizedPositiveX(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long normalizedPositiveX(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.normalizedPositiveX_unsafe(dest, src);
        normalizedPositiveX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code +Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] normalizedPositiveY(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset + 0] = 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        dest[destOffset + 1] = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfz * _selfz), 1.0f);
        dest[destOffset + 2] = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        return dest;
    }

    /** {@link #normalizedPositiveY(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer normalizedPositiveY(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.normalizedPositiveY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.normalizedPositiveY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalizedPositiveY(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer normalizedPositiveY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.normalizedPositiveY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.normalizedPositiveY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalizedPositiveY(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment normalizedPositiveY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.normalizedPositiveY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.normalizedPositiveY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalizedPositiveY(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long normalizedPositiveY(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.normalizedPositiveY_unsafe(dest, src);
        normalizedPositiveY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code +Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] normalizedPositiveZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset + 0] = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        dest[destOffset + 1] = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        dest[destOffset + 2] = Math.fma(-2.0f, Math.fma(_selfx, _selfx, _selfy * _selfy), 1.0f);
        return dest;
    }

    /** {@link #normalizedPositiveZ(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer normalizedPositiveZ(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.normalizedPositiveZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.normalizedPositiveZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalizedPositiveZ(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer normalizedPositiveZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.normalizedPositiveZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.normalizedPositiveZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalizedPositiveZ(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment normalizedPositiveZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.normalizedPositiveZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.normalizedPositiveZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalizedPositiveZ(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long normalizedPositiveZ(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.normalizedPositiveZ_unsafe(dest, src);
        normalizedPositiveZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code +X} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] positiveX(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t9 = 2.0f * Math.fma(_selfx, _selfy, _selfz * _selfw);
        float _t10 = 2.0f * Math.fma(_selfx, _selfz, -(_selfy * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfx, _selfx, _selfw * _selfw)));
        float _t15 = Math.fma(_t10, _t10, Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest[destOffset + 0] = _t12 * _t16;
            dest[destOffset + 1] = _t9 * _t16;
            dest[destOffset + 2] = _t10 * _t16;
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    /** {@link #positiveX(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer positiveX(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.positiveX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.positiveX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #positiveX(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer positiveX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.positiveX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.positiveX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #positiveX(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment positiveX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.positiveX_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.positiveX_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #positiveX(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long positiveX(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.positiveX_unsafe(dest, src);
        positiveX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code +Y} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] positiveY(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t9 = 2.0f * Math.fma(_selfx, _selfw, _selfy * _selfz);
        float _t10 = 2.0f * Math.fma(_selfx, _selfy, -(_selfz * _selfw));
        float _t12 = Math.fma(-_selfz, _selfz, Math.fma(_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t9, _t9, Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest[destOffset + 0] = _t10 * _t16;
            dest[destOffset + 1] = _t12 * _t16;
            dest[destOffset + 2] = _t9 * _t16;
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    /** {@link #positiveY(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer positiveY(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.positiveY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.positiveY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #positiveY(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer positiveY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.positiveY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.positiveY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #positiveY(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment positiveY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.positiveY_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.positiveY_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #positiveY(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long positiveY(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.positiveY_unsafe(dest, src);
        positiveY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Obtain the direction of {@code +Z} after the transformation represented by this quaternion is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] positiveZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t9 = 2.0f * Math.fma(_selfx, _selfz, _selfy * _selfw);
        float _t10 = 2.0f * Math.fma(_selfy, _selfz, -(_selfx * _selfw));
        float _t12 = Math.fma(_selfz, _selfz, Math.fma(-_selfy, _selfy, Math.fma(_selfw, _selfw, -(_selfx * _selfx))));
        float _t15 = Math.fma(_t12, _t12, Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            dest[destOffset + 0] = _t9 * _t16;
            dest[destOffset + 1] = _t10 * _t16;
            dest[destOffset + 2] = _t12 * _t16;
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    /** {@link #positiveZ(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer positiveZ(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.positiveZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsTypedBuffer.positiveZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #positiveZ(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer positiveZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.positiveZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsByteBuffer.positiveZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #positiveZ(float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment positiveZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.positiveZ_unsafe(dest, destOffset, src, srcOffset);
        return FloatQuatOpsKernelsSegment.positiveZ_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #positiveZ(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long positiveZ(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.positiveZ_unsafe(dest, src);
        positiveZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
        return dest;
    }

    /**
     * Raise this quaternion to the power of {@code t}, i.e. compute {@code exp(t * log(this))} and
     * store the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param t the exponent
     * @return {@code dest}
     */
    public static float[] pow(float[] dest, int destOffset, float[] src, int srcOffset, float t) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t12 = Math.min((float) Math.exp(t == 0.0f ? 0.0f : t * (float) Math.log((float) Math.sqrt(Math.fma(_selfw, _selfw, _t2)))), 3.4028235E38f);
        float _t13 = (float) Math.atan2((float) Math.sqrt(_t2), _selfw) * (1.0f / (float) Math.sqrt(_t2));
        float _t20, _t21, _t22;
        if (_t2 > 0.0f) {
            _t20 = t * _selfz * _t13;
            _t21 = t * _selfx * _t13;
            _t22 = t * _selfy * _t13;
        } else {
            _t20 = t * 0.0f;
            _t21 = t * 0.0f;
            _t22 = t * 0.0f;
        }
        float _t25 = Math.fma(_t20, _t20, Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (float) Math.sqrt(_t25);
        float _t28 = (float) Math.sin(_t26);
        float _t31 = _t12 * (_t28 / _t26);
        if (_t25 > 0.0f) {
            dest[destOffset + 0] = _t21 * _t31;
            dest[destOffset + 1] = _t22 * _t31;
            dest[destOffset + 2] = _t20 * _t31;
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        dest[destOffset + 3] = (float) Math.cosFromSin(_t28, _t26) * _t12;
        return dest;
    }

    /** {@link #pow(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer pow(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.pow_unsafe(dest, destOffset, src, srcOffset, t);
        return FloatQuatOpsKernelsTypedBuffer.pow_api(dest, destOffset, src, srcOffset, t);
    }

    /** {@link #pow(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer pow(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.pow_unsafe(dest, destOffset, src, srcOffset, t);
        return FloatQuatOpsKernelsByteBuffer.pow_api(dest, destOffset, src, srcOffset, t);
    }

    /** {@link #pow(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment pow(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.pow_unsafe(dest, destOffset, src, srcOffset, t);
        return FloatQuatOpsKernelsSegment.pow_api(dest, destOffset, src, srcOffset, t);
    }

    /** {@link #pow(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long pow(long dest, long src, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.pow_unsafe(dest, src, t);
        pow(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, t);
        return dest;
    }

    /**
     * Pre-multiply ({@code otherX}, {@code otherY}, {@code otherZ}, {@code otherW}) onto this
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code R * Q}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code R * Q * v}, the transformation of the operand will be applied last.
     * <p>
     * Identical to {@link #preMul}; the lower-case spelling is kept for JOML 1 source
     * compatibility.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param otherX the {@code x} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the quaternion
     *        {@code (otherX, otherY, otherZ, otherW)}
     * @return {@code dest}
     */
    public static float[] premul(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        return FloatQuatOps.preMul(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #premul(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer premul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        return FloatQuatOps.preMul(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #premul(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer premul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        return FloatQuatOps.preMul(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #premul(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment premul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW) {
        return FloatQuatOps.preMul(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW);
    }

    /** {@link #premul(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long premul(long dest, long src, float otherX, float otherY, float otherZ, float otherW) {
        return FloatQuatOps.preMul(dest, src, otherX, otherY, otherZ, otherW);
    }

    /**
     * Pre-multiply {@code other} onto this quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the operand, then the new quaternion
     * will be {@code R * Q}. So when transforming a vector {@code v} with the new quaternion by
     * using {@code R * Q * v}, the transformation of the operand will be applied last.
     * <p>
     * Identical to {@link #preMul}; the lower-case spelling is kept for JOML 1 source
     * compatibility.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param other the storage holding the left operand
     * @param otherOffset the element index in {@code other} at which the quaternion starts
     * @return {@code dest}
     */
    public static float[] premul(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        return FloatQuatOps.preMul(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #premul(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer premul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        return FloatQuatOps.preMul(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #premul(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer premul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        return FloatQuatOps.preMul(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #premul(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment premul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        return FloatQuatOps.preMul(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #premul(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long premul(long dest, long src, long other) {
        return FloatQuatOps.preMul(dest, src, other);
    }

    /**
     * Rotate this quaternion towards ({@code targetX}, {@code targetY}, {@code targetZ},
     * {@code targetW}), by at most the given maximum angle and store the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: this quaternion must have unit length;
     * {@code (targetX, targetY, targetZ, targetW)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param targetX the {@code x} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetY the {@code y} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetZ the {@code z} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param targetW the {@code w} component of the quaternion
     *        {@code (targetX, targetY, targetZ, targetW)}
     * @param step the maximum rotation angle in radians
     * @return {@code dest}
     */
    public static float[] rotateTowards(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t7 = Math.fma(_selfw, targetW, Math.fma(_selfz, targetZ, Math.fma(_selfx, targetX, _selfy * targetY)));
        float _t11 = (float) Math.acos(Math.min(1.0f, Math.abs(_t7)));
        float _t12 = (float) Math.sin(_t11);
        float _t13, _t14, _t15, _t16;
        if (-_t7 > 0.0f) {
            _t13 = -targetW;
            _t14 = -targetZ;
            _t15 = -targetX;
            _t16 = -targetY;
        } else {
            _t13 = targetW;
            _t14 = targetZ;
            _t15 = targetX;
            _t16 = targetY;
        }
        float _t17 = _selfw - _t13;
        float _t18 = _selfz - _t14;
        float _t19 = _selfx - _t15;
        float _t20 = _selfy - _t16;
        float _t21 = _selfw + _t13;
        float _t22 = _selfz + _t14;
        float _t23 = _selfx + _t15;
        float _t24 = _selfy + _t16;
        return rotateTowards_s5d9f5ed0_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t11, _t12, 1.0f / _t12, _t13, _t14, _t15, _t16, 4.0f * (float) Math.atan2((float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), (float) Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24))))));
    }

    /** Piece 2 of {@code rotateTowards}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTowards_s5d9f5ed0_1(float[] dest, int destOffset, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t12_inv, float _t13, float _t14, float _t15, float _t16, float _t36) {
        float _t39 = _t36 > 0.0f ? Math.min(1.0f, step / _t36) : 0.0f;
        float _t40 = 1.0f - _t39;
        float _t42 = (float) Math.sin(_t11 * _t39);
        float _t44 = (float) Math.sin(_t40 * _t11);
        float _t65, _t66, _t67, _t68;
        if (_t12 > 0.0f) {
            _t65 = Math.fma(_selfw, _t44, _t42 * _t13) * _t12_inv;
            _t66 = Math.fma(_selfz, _t44, _t42 * _t14) * _t12_inv;
            _t67 = Math.fma(_selfx, _t44, _t42 * _t15) * _t12_inv;
            _t68 = Math.fma(_selfy, _t44, _t42 * _t16) * _t12_inv;
        } else {
            _t65 = Math.fma(_selfw, _t40, _t13 * _t39);
            _t66 = Math.fma(_selfz, _t40, _t14 * _t39);
            _t67 = Math.fma(_selfx, _t40, _t15 * _t39);
            _t68 = Math.fma(_selfy, _t40, _t16 * _t39);
        }
        float _t72 = Math.fma(_t65, _t65, Math.fma(_t66, _t66, Math.fma(_t67, _t67, _t68 * _t68)));
        float _t73 = (1.0f / (float) Math.sqrt(_t72));
        if (_t72 != 0.0f) {
            dest[destOffset + 0] = _t73 * _t67;
            dest[destOffset + 1] = _t73 * _t68;
            dest[destOffset + 2] = _t73 * _t66;
            dest[destOffset + 3] = _t73 * _t65;
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
            dest[destOffset + 3] = 0.0f;
        }
        return dest;
    }

    /** {@link #rotateTowards(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateTowards(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.rotateTowards_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, step);
        return FloatQuatOpsKernelsTypedBuffer.rotateTowards_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, step);
    }

    /** {@link #rotateTowards(float[], int, float[], int, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateTowards(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.rotateTowards_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, step);
        return FloatQuatOpsKernelsByteBuffer.rotateTowards_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, step);
    }

    /** {@link #rotateTowards(float[], int, float[], int, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateTowards(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float step) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.rotateTowards_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, step);
        return FloatQuatOpsKernelsSegment.rotateTowards_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, step);
    }

    /** {@link #rotateTowards(float[], int, float[], int, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateTowards(long dest, long src, float targetX, float targetY, float targetZ, float targetW, float step) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.rotateTowards_unsafe(dest, src, targetX, targetY, targetZ, targetW, step);
        rotateTowards(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, targetX, targetY, targetZ, targetW, step);
        return dest;
    }

    /**
     * Rotate this quaternion towards {@code target}, by at most the given maximum angle and store
     * the result in {@code dest}.
     * <p>
     * The rotation angle is recovered with {@code atan2}, so it keeps full {@code float} resolution
     * down to 0 - small rotations are not truncated.
     * <p>
     * Valid input: this quaternion must have unit length; {@code target} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param target the storage holding the target rotation
     * @param targetOffset the element index in {@code target} at which the quaternion starts
     * @param step the maximum rotation angle in radians
     * @return {@code dest}
     */
    public static float[] rotateTowards(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float step) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.rotateTowards(dest, destOffset, src, srcOffset, target, targetOffset, step);
        return FloatQuatOpsKernelsArray.rotateTowards_scalar(dest, destOffset, src, srcOffset, target, targetOffset, step);
    }

    /** {@link #rotateTowards(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateTowards(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer target, int targetOffset, float step) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && target.isDirect() && target.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.rotateTowards_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, step);
        return FloatQuatOpsKernelsTypedBuffer.rotateTowards_api(dest, destOffset, src, srcOffset, target, targetOffset, step);
    }

    /** {@link #rotateTowards(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateTowards(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer target, int targetOffset, float step) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && target.isDirect() && target.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.rotateTowards_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, step);
        return FloatQuatOpsKernelsByteBuffer.rotateTowards_api(dest, destOffset, src, srcOffset, target, targetOffset, step);
    }

    /** {@link #rotateTowards(float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateTowards(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float step) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.rotateTowards(dest, destOffset, src, srcOffset, target, targetOffset, step);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && target.isNative()) return FloatQuatOpsKernelsSegment.rotateTowards_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, step);
        return FloatQuatOpsKernelsSegment.rotateTowards_api(dest, destOffset, src, srcOffset, target, targetOffset, step);
    }

    /** {@link #rotateTowards(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateTowards(long dest, long src, long target, float step) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.rotateTowards_unsafe(dest, src, target, step);
        rotateTowards(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(target, 16L), 0L, step);
        return dest;
    }

    /**
     * Apply a rotation transformation that makes {@code +z} point along ({@code dirX},
     * {@code dirY}, {@code dirZ}) to this quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Valid input: {@code (dirX, dirY, dirZ)} and {@code (upX, upY, upZ)} must not be parallel.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @return {@code dest}
     */
    public static float[] lookAlong(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        float _t19 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t20 = Math.fma(_t19, _t12, upX);
        float _t21 = Math.fma(_t19, _t13, upY);
        float _t22 = Math.fma(_t19, _t11, upZ);
        float _t29 = Math.fma(_t20, _t13, -(_t21 * _t12));
        float _t30 = Math.fma(_t21, _t11, -(_t22 * _t13));
        float _t31 = Math.fma(_t22, _t12, -(_t20 * _t11));
        float _t34 = Math.fma(_t29, _t29, Math.fma(_t30, _t30, _t31 * _t31));
        return lookAlong_s52a71213_1(dest, destOffset, upX, upY, upZ, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t11, _t12, _t13, _t29, _t30, _t31, _t34, (1.0f / (float) Math.sqrt(_t34)));
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_s52a71213_1(float[] dest, int destOffset, float upX, float upY, float upZ, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t29, float _t30, float _t31, float _t34, float _t35) {
        float _t39, _t40, _t41;
        if (_t34 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t39 = _t30 * _t35;
            _t40 = _t29 * _t35;
            _t41 = _t31 * _t35;
        } else {
            _t39 = 0.0f;
            _t40 = 0.0f;
            _t41 = 0.0f;
        }
        float _t42 = -_t40;
        float _t43 = -_t39;
        float _t45 = 1.0f + _t39;
        float _t73 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11));
        float _t74 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0f - _t39 - _t11));
        return lookAlong_s52a71213_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t39, _t42, _t12 - _t40, _t40 + _t12, Math.fma(_t39, _t11, -(_t40 * _t12)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13)), Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41)), Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41)), Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11)), _t73, _t74, Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0f + _t11 - _t39)), 0.5f * (1.0f / (float) Math.sqrt(_t73)), 0.5f * (1.0f / (float) Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_s52a71213_2(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t39, float _t42, float _t49, float _t50, float _t61, float _t66, float _t68, float _t69, float _t70, float _t72, float _t73, float _t74, float _t75, float _sp1, float _sp2) {
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t75));
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t72));
        float _t120, _t121, _t122, _t123;
        if (Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t39 + _t11)) > 0.0f) {
            _t120 = _sp0 * _t69;
            _t121 = _sp0 * _t49;
            _t122 = 0.5f * (float) Math.sqrt(_t72);
            _t123 = _sp0 * _t68;
        } else {
            if (_t39 > Math.max(_t61, _t11)) {
                _t120 = _sp1 * _t50;
                _t121 = _sp1 * _t70;
                _t122 = _sp1 * _t68;
                _t123 = 0.5f * (float) Math.sqrt(_t73);
            } else {
                if (_t61 > _t11) {
                    _t120 = _sp2 * _t66;
                    _t121 = 0.5f * (float) Math.sqrt(_t74);
                    _t122 = _sp2 * _t49;
                    _t123 = _sp2 * _t70;
                } else {
                    _t120 = 0.5f * (float) Math.sqrt(_t75);
                    _t121 = _sp3 * _t66;
                    _t122 = _sp3 * _t69;
                    _t123 = _sp3 * _t50;
                }
            }
        }
        dest[destOffset + 0] = Math.fma(_selfx, _t122, _selfw * _t123) + Math.fma(_selfy, _t120, -(_selfz * _t121));
        dest[destOffset + 1] = Math.fma(_selfy, _t122, _selfz * _t123) + Math.fma(_selfw, _t121, -(_selfx * _t120));
        return lookAlong_s52a71213_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t120, _t121, _t122, _t123);
    }

    /** Piece 4 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_s52a71213_3(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t120, float _t121, float _t122, float _t123) {
        dest[destOffset + 2] = Math.fma(_selfx, _t121, _selfw * _t120) + Math.fma(_selfz, _t122, -(_selfy * _t123));
        dest[destOffset + 3] = Math.fma(_selfw, _t122, -(_selfx * _t123)) - Math.fma(_selfy, _t121, _selfz * _t120);
        return dest;
    }

    /** {@link #lookAlong(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer lookAlong(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.lookAlong_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return FloatQuatOpsKernelsTypedBuffer.lookAlong_api(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@link #lookAlong(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lookAlong(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.lookAlong_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return FloatQuatOpsKernelsByteBuffer.lookAlong_api(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@link #lookAlong(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment lookAlong(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.lookAlong_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return FloatQuatOpsKernelsSegment.lookAlong_api(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@link #lookAlong(float[], int, float[], int, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long lookAlong(long dest, long src, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.lookAlong_unsafe(dest, src, dirX, dirY, dirZ, upX, upY, upZ);
        lookAlong(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    /**
     * Apply a rotation transformation that makes {@code +z} point along {@code dir} to this
     * quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code L} the "look along" quaternion, then the
     * new quaternion will be {@code Q * L}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * L * v}, the "look along" will be applied first.
     * <p>
     * Valid input: {@code dir} and {@code up} must not be parallel.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param dir the storage holding the direction to look along, i.e. the direction the local
     *        {@code +z} axis is mapped to
     * @param dirOffset the element index in {@code dir} at which the vector starts
     * @param up the storage holding the direction of "up"
     * @param upOffset the element index in {@code up} at which the vector starts
     * @return {@code dest}
     */
    public static float[] lookAlong(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirx = dir[dirOffset + 0];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset + 0];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
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
        float _t19 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t20 = Math.fma(_t19, _t12, _upx);
        float _t21 = Math.fma(_t19, _t13, _upy);
        float _t22 = Math.fma(_t19, _t11, _upz);
        float _t29 = Math.fma(_t20, _t13, -(_t21 * _t12));
        float _t30 = Math.fma(_t21, _t11, -(_t22 * _t13));
        float _t31 = Math.fma(_t22, _t12, -(_t20 * _t11));
        float _t34 = Math.fma(_t29, _t29, Math.fma(_t30, _t30, _t31 * _t31));
        return lookAlong_sf15ad91f_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _upx, _upy, _upz, _t11, _t12, _t13, _t29, _t30, _t31, _t34, (1.0f / (float) Math.sqrt(_t34)));
    }

    /** Piece 2 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_sf15ad91f_1(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t29, float _t30, float _t31, float _t34, float _t35) {
        float _t39, _t40, _t41;
        if (_t34 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t39 = _t30 * _t35;
            _t40 = _t29 * _t35;
            _t41 = _t31 * _t35;
        } else {
            _t39 = 0.0f;
            _t40 = 0.0f;
            _t41 = 0.0f;
        }
        float _t42 = -_t40;
        float _t43 = -_t39;
        float _t45 = 1.0f + _t39;
        float _t73 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11));
        float _t74 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0f - _t39 - _t11));
        return lookAlong_sf15ad91f_2(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t11, _t12, _t39, _t42, _t12 - _t40, _t40 + _t12, Math.fma(_t39, _t11, -(_t40 * _t12)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13)), Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41)), Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41)), Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11)), _t73, _t74, Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0f + _t11 - _t39)), 0.5f * (1.0f / (float) Math.sqrt(_t73)), 0.5f * (1.0f / (float) Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_sf15ad91f_2(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t39, float _t42, float _t49, float _t50, float _t61, float _t66, float _t68, float _t69, float _t70, float _t72, float _t73, float _t74, float _t75, float _sp1, float _sp2) {
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t75));
        float _sp0 = 0.5f * (1.0f / (float) Math.sqrt(_t72));
        float _t120, _t121, _t122, _t123;
        if (Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t39 + _t11)) > 0.0f) {
            _t120 = _sp0 * _t69;
            _t121 = _sp0 * _t49;
            _t122 = 0.5f * (float) Math.sqrt(_t72);
            _t123 = _sp0 * _t68;
        } else {
            if (_t39 > Math.max(_t61, _t11)) {
                _t120 = _sp1 * _t50;
                _t121 = _sp1 * _t70;
                _t122 = _sp1 * _t68;
                _t123 = 0.5f * (float) Math.sqrt(_t73);
            } else {
                if (_t61 > _t11) {
                    _t120 = _sp2 * _t66;
                    _t121 = 0.5f * (float) Math.sqrt(_t74);
                    _t122 = _sp2 * _t49;
                    _t123 = _sp2 * _t70;
                } else {
                    _t120 = 0.5f * (float) Math.sqrt(_t75);
                    _t121 = _sp3 * _t66;
                    _t122 = _sp3 * _t69;
                    _t123 = _sp3 * _t50;
                }
            }
        }
        dest[destOffset + 0] = Math.fma(_selfx, _t122, _selfw * _t123) + Math.fma(_selfy, _t120, -(_selfz * _t121));
        dest[destOffset + 1] = Math.fma(_selfy, _t122, _selfz * _t123) + Math.fma(_selfw, _t121, -(_selfx * _t120));
        return lookAlong_sf15ad91f_3(dest, destOffset, _selfx, _selfy, _selfz, _selfw, _t120, _t121, _t122, _t123);
    }

    /** Piece 4 of {@code lookAlong}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_sf15ad91f_3(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t120, float _t121, float _t122, float _t123) {
        dest[destOffset + 2] = Math.fma(_selfx, _t121, _selfw * _t120) + Math.fma(_selfz, _t122, -(_selfy * _t123));
        dest[destOffset + 3] = Math.fma(_selfw, _t122, -(_selfx * _t123)) - Math.fma(_selfy, _t121, _selfz * _t120);
        return dest;
    }

    /** {@link #lookAlong(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer lookAlong(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.lookAlong_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return FloatQuatOpsKernelsTypedBuffer.lookAlong_api(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@link #lookAlong(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lookAlong(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.lookAlong_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return FloatQuatOpsKernelsByteBuffer.lookAlong_api(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@link #lookAlong(float[], int, float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment lookAlong(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && dir.isNative() && up.isNative()) return FloatQuatOpsKernelsSegment.lookAlong_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return FloatQuatOpsKernelsSegment.lookAlong_api(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    /** {@link #lookAlong(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long lookAlong(long dest, long src, long dir, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.lookAlong_unsafe(dest, src, dir, up);
        lookAlong(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 12L), 0L);
        return dest;
    }

    /**
     * Set this quaternion to a rotation of {@code angle} radians about the axis ({@code axisX},
     * {@code axisY}, {@code axisZ}).
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return {@code dest}
     */
    public static float[] makeRotationAxis(float[] dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.makeRotationAxis(dest, destOffset, angle, axisX, axisY, axisZ);
        return FloatQuatOpsKernelsArray.makeRotationAxis_scalar(dest, destOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #makeRotationAxis(float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeRotationAxis(java.nio.FloatBuffer dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationAxis_unsafe(dest, destOffset, angle, axisX, axisY, axisZ);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationAxis_api(dest, destOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #makeRotationAxis(float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationAxis(java.nio.ByteBuffer dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationAxis_unsafe(dest, destOffset, angle, axisX, axisY, axisZ);
        return FloatQuatOpsKernelsByteBuffer.makeRotationAxis_api(dest, destOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #makeRotationAxis(float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationAxis(java.lang.foreign.MemorySegment dest, long destOffset, float angle, float axisX, float axisY, float axisZ) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.makeRotationAxis(dest, destOffset, angle, axisX, axisY, axisZ);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.makeRotationAxis_unsafe(dest, destOffset, angle, axisX, axisY, axisZ);
        return FloatQuatOpsKernelsSegment.makeRotationAxis_api(dest, destOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #makeRotationAxis(float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationAxis(long dest, float angle, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationAxis_unsafe(dest, angle, axisX, axisY, axisZ);
        makeRotationAxis(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, angle, axisX, axisY, axisZ);
        return dest;
    }

    /**
     * Set this quaternion to a rotation of {@code angle} radians about the axis {@code axis}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param axis the storage holding the rotation axis
     * @param axisOffset the element index in {@code axis} at which the vector starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] makeRotationAxis(float[] dest, int destOffset, float[] axis, int axisOffset, float angle) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.makeRotationAxis(dest, destOffset, axis, axisOffset, angle);
        return FloatQuatOpsKernelsArray.makeRotationAxis_scalar(dest, destOffset, axis, axisOffset, angle);
    }

    /** {@link #makeRotationAxis(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeRotationAxis(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationAxis_unsafe(dest, destOffset, axis, axisOffset, angle);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationAxis_api(dest, destOffset, axis, axisOffset, angle);
    }

    /** {@link #makeRotationAxis(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationAxis(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationAxis_unsafe(dest, destOffset, axis, axisOffset, angle);
        return FloatQuatOpsKernelsByteBuffer.makeRotationAxis_api(dest, destOffset, axis, axisOffset, angle);
    }

    /** {@link #makeRotationAxis(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.makeRotationAxis(dest, destOffset, axis, axisOffset, angle);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && axis.isNative()) return FloatQuatOpsKernelsSegment.makeRotationAxis_unsafe(dest, destOffset, axis, axisOffset, angle);
        return FloatQuatOpsKernelsSegment.makeRotationAxis_api(dest, destOffset, axis, axisOffset, angle);
    }

    /** {@link #makeRotationAxis(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationAxis(long dest, long axis, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationAxis_unsafe(dest, axis, angle);
        makeRotationAxis(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(axis, 12L), 0L, angle);
        return dest;
    }

    /**
     * Set this quaternion to a rotation that makes {@code +z} point along ({@code dirX},
     * {@code dirY}, {@code dirZ}).
     * <p>
     * Valid input: {@code (dirX, dirY, dirZ)} and {@code (upX, upY, upZ)} must not be parallel.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @return {@code dest}
     */
    public static float[] makeRotationLookAlong(float[] dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        float _t19 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t20 = Math.fma(_t19, _t12, upX);
        float _t21 = Math.fma(_t19, _t13, upY);
        float _t22 = Math.fma(_t19, _t11, upZ);
        float _t29 = Math.fma(_t20, _t13, -(_t21 * _t12));
        float _t30 = Math.fma(_t21, _t11, -(_t22 * _t13));
        float _t31 = Math.fma(_t22, _t12, -(_t20 * _t11));
        float _t34 = Math.fma(_t29, _t29, Math.fma(_t30, _t30, _t31 * _t31));
        float _t35 = (1.0f / (float) Math.sqrt(_t34));
        float _t39, _t40, _t41;
        if (_t34 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t39 = _t30 * _t35;
            _t40 = _t29 * _t35;
            _t41 = _t31 * _t35;
        } else {
            _t39 = 0.0f;
            _t40 = 0.0f;
            _t41 = 0.0f;
        }
        return makeRotationLookAlong_s4b25651f_1(dest, destOffset, _t11, _t12, _t13, _t39, _t40, _t41, -_t40, -_t39, 1.0f + _t39, _t40 + _t12);
    }

    /** Piece 2 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_s4b25651f_1(float[] dest, int destOffset, float _t11, float _t12, float _t13, float _t39, float _t40, float _t41, float _t42, float _t43, float _t45, float _t49) {
        float _t72 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11));
        float _t73 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11));
        float _t74 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0f - _t39 - _t11));
        float _t75 = Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0f + _t11 - _t39));
        return makeRotationLookAlong_s4b25651f_2(dest, destOffset, _t11, _t12, _t39, _t42, _t49, _t12 - _t40, Math.fma(_t39, _t11, -(_t40 * _t12)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13)), Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41)), Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41)), _t72, _t73, _t74, _t75, 0.5f * (1.0f / (float) Math.sqrt(_t72)), 0.5f * (1.0f / (float) Math.sqrt(_t74)), 0.5f * (1.0f / (float) Math.sqrt(_t75)), 0.5f * (1.0f / (float) Math.sqrt(_t73)));
    }

    /** Piece 3 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_s4b25651f_2(float[] dest, int destOffset, float _t11, float _t12, float _t39, float _t42, float _t49, float _t50, float _t61, float _t66, float _t67, float _t69, float _t70, float _t72, float _t73, float _t74, float _t75, float _sp0, float _sp1, float _sp2, float _sp3) {
        if (Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t39 + _t11)) > 0.0f) {
            dest[destOffset + 0] = _sp0 * _t67;
            dest[destOffset + 1] = _sp0 * _t50;
            dest[destOffset + 2] = _sp0 * _t70;
            dest[destOffset + 3] = 0.5f * (float) Math.sqrt(_t72);
        } else {
            if (_t39 > Math.max(_t61, _t11)) {
                dest[destOffset + 0] = 0.5f * (float) Math.sqrt(_t73);
                dest[destOffset + 1] = _sp3 * _t69;
                dest[destOffset + 2] = _sp3 * _t49;
                dest[destOffset + 3] = _sp3 * _t67;
            } else {
                if (_t61 > _t11) {
                    dest[destOffset + 0] = _sp1 * _t69;
                    dest[destOffset + 1] = 0.5f * (float) Math.sqrt(_t74);
                    dest[destOffset + 2] = _sp1 * _t66;
                    dest[destOffset + 3] = _sp1 * _t50;
                } else {
                    dest[destOffset + 0] = _sp2 * _t49;
                    dest[destOffset + 1] = _sp2 * _t66;
                    dest[destOffset + 2] = 0.5f * (float) Math.sqrt(_t75);
                    dest[destOffset + 3] = _sp2 * _t70;
                }
            }
        }
        return dest;
    }

    /** {@link #makeRotationLookAlong(float[], int, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeRotationLookAlong(java.nio.FloatBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_api(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@link #makeRotationLookAlong(float[], int, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationLookAlong(java.nio.ByteBuffer dest, int destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationLookAlong_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return FloatQuatOpsKernelsByteBuffer.makeRotationLookAlong_api(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@link #makeRotationLookAlong(float[], int, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationLookAlong(java.lang.foreign.MemorySegment dest, long destOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.makeRotationLookAlong_unsafe(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return FloatQuatOpsKernelsSegment.makeRotationLookAlong_api(dest, destOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** {@link #makeRotationLookAlong(float[], int, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationLookAlong(long dest, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationLookAlong_unsafe(dest, dirX, dirY, dirZ, upX, upY, upZ);
        makeRotationLookAlong(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, dirX, dirY, dirZ, upX, upY, upZ);
        return dest;
    }

    /**
     * Set this quaternion to a rotation that makes {@code +z} point along {@code dir}.
     * <p>
     * Valid input: {@code dir} and {@code up} must not be parallel.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param dir the storage holding the direction to look along, i.e. the direction the local
     *        {@code +z} axis is mapped to
     * @param dirOffset the element index in {@code dir} at which the vector starts
     * @param up the storage holding the direction of "up"
     * @param upOffset the element index in {@code up} at which the vector starts
     * @return {@code dest}
     */
    public static float[] makeRotationLookAlong(float[] dest, int destOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirx = dir[dirOffset + 0];
        float _diry = dir[dirOffset + 1];
        float _dirz = dir[dirOffset + 2];
        float _upx = up[upOffset + 0];
        float _upy = up[upOffset + 1];
        float _upz = up[upOffset + 2];
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
        float _t19 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t20 = Math.fma(_t19, _t12, _upx);
        float _t21 = Math.fma(_t19, _t13, _upy);
        float _t22 = Math.fma(_t19, _t11, _upz);
        float _t29 = Math.fma(_t20, _t13, -(_t21 * _t12));
        float _t30 = Math.fma(_t21, _t11, -(_t22 * _t13));
        float _t31 = Math.fma(_t22, _t12, -(_t20 * _t11));
        float _t34 = Math.fma(_t29, _t29, Math.fma(_t30, _t30, _t31 * _t31));
        return makeRotationLookAlong_s88bc2f13_1(dest, destOffset, _upx, _upy, _upz, _t11, _t12, _t13, _t29, _t30, _t31, _t34, (1.0f / (float) Math.sqrt(_t34)));
    }

    /** Piece 2 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_s88bc2f13_1(float[] dest, int destOffset, float _upx, float _upy, float _upz, float _t11, float _t12, float _t13, float _t29, float _t30, float _t31, float _t34, float _t35) {
        float _t39, _t40, _t41;
        if (_t34 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t39 = _t30 * _t35;
            _t40 = _t29 * _t35;
            _t41 = _t31 * _t35;
        } else {
            _t39 = 0.0f;
            _t40 = 0.0f;
            _t41 = 0.0f;
        }
        float _t42 = -_t40;
        float _t43 = -_t39;
        float _t45 = 1.0f + _t39;
        float _t72 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t45 + _t11));
        float _t74 = Math.fma(_t39, _t11, Math.fma(_t42, _t12, 1.0f - _t39 - _t11));
        return makeRotationLookAlong_s88bc2f13_2(dest, destOffset, _t11, _t12, _t39, _t42, _t40 + _t12, _t12 - _t40, Math.fma(_t39, _t11, -(_t40 * _t12)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, _t13)), Math.fma(_t41, _t12, Math.fma(_t43, _t13, -_t13)), Math.fma(_t40, _t13, Math.fma(-_t41, _t11, _t41)), Math.fma(_t42, _t13, Math.fma(_t41, _t11, _t41)), _t72, Math.fma(_t43, _t11, Math.fma(_t40, _t12, _t45 - _t11)), _t74, Math.fma(_t43, _t11, Math.fma(_t40, _t12, 1.0f + _t11 - _t39)), 0.5f * (1.0f / (float) Math.sqrt(_t72)), 0.5f * (1.0f / (float) Math.sqrt(_t74)));
    }

    /** Piece 3 of {@code makeRotationLookAlong}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationLookAlong_s88bc2f13_2(float[] dest, int destOffset, float _t11, float _t12, float _t39, float _t42, float _t49, float _t50, float _t61, float _t66, float _t67, float _t69, float _t70, float _t72, float _t73, float _t74, float _t75, float _sp0, float _sp1) {
        float _sp2 = 0.5f * (1.0f / (float) Math.sqrt(_t75));
        float _sp3 = 0.5f * (1.0f / (float) Math.sqrt(_t73));
        if (Math.fma(_t39, _t11, Math.fma(_t42, _t12, _t39 + _t11)) > 0.0f) {
            dest[destOffset + 0] = _sp0 * _t67;
            dest[destOffset + 1] = _sp0 * _t50;
            dest[destOffset + 2] = _sp0 * _t70;
            dest[destOffset + 3] = 0.5f * (float) Math.sqrt(_t72);
        } else {
            if (_t39 > Math.max(_t61, _t11)) {
                dest[destOffset + 0] = 0.5f * (float) Math.sqrt(_t73);
                dest[destOffset + 1] = _sp3 * _t69;
                dest[destOffset + 2] = _sp3 * _t49;
                dest[destOffset + 3] = _sp3 * _t67;
            } else {
                if (_t61 > _t11) {
                    dest[destOffset + 0] = _sp1 * _t69;
                    dest[destOffset + 1] = 0.5f * (float) Math.sqrt(_t74);
                    dest[destOffset + 2] = _sp1 * _t66;
                    dest[destOffset + 3] = _sp1 * _t50;
                } else {
                    dest[destOffset + 0] = _sp2 * _t49;
                    dest[destOffset + 1] = _sp2 * _t66;
                    dest[destOffset + 2] = 0.5f * (float) Math.sqrt(_t75);
                    dest[destOffset + 3] = _sp2 * _t70;
                }
            }
        }
        return dest;
    }

    /** {@link #makeRotationLookAlong(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeRotationLookAlong(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer dir, int dirOffset, java.nio.FloatBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_unsafe(dest, destOffset, dir, dirOffset, up, upOffset);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationLookAlong_api(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@link #makeRotationLookAlong(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationLookAlong(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer dir, int dirOffset, java.nio.ByteBuffer up, int upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && dir.isDirect() && dir.order() == java.nio.ByteOrder.nativeOrder() && up.isDirect() && up.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationLookAlong_unsafe(dest, destOffset, dir, dirOffset, up, upOffset);
        return FloatQuatOpsKernelsByteBuffer.makeRotationLookAlong_api(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@link #makeRotationLookAlong(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationLookAlong(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && dir.isNative() && up.isNative()) return FloatQuatOpsKernelsSegment.makeRotationLookAlong_unsafe(dest, destOffset, dir, dirOffset, up, upOffset);
        return FloatQuatOpsKernelsSegment.makeRotationLookAlong_api(dest, destOffset, dir, dirOffset, up, upOffset);
    }

    /** {@link #makeRotationLookAlong(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationLookAlong(long dest, long dir, long up) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationLookAlong_unsafe(dest, dir, up);
        makeRotationLookAlong(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(dir, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(up, 12L), 0L);
        return dest;
    }

    /**
     * Set this quaternion to the rotation that rotates ({@code fromDirX}, {@code fromDirY},
     * {@code fromDirZ}) onto ({@code toDirX}, {@code toDirY}, {@code toDirZ}) (for opposite vectors
     * an arbitrary perpendicular rotation axis is chosen).
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 6e-8}
     * (about 3.5e-4 radians, 0.02 degrees, from opposite); only there is the perpendicular axis
     * chosen arbitrarily, and the result is then off by at most that angle.
     * <p>
     * Valid input: {@code (fromDirX, fromDirY, fromDirZ)} must have unit length;
     * {@code (toDirX, toDirY, toDirZ)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param fromDirX the {@code x} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirY the {@code y} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirZ the {@code z} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param toDirX the {@code x} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirY the {@code y} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirZ the {@code z} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @return {@code dest}
     */
    public static float[] makeRotationTo(float[] dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (Math.abs(fromDirZ) < Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t16 = Math.fma(fromDirX, toDirY, -(fromDirY * toDirX));
        float _t17 = Math.fma(fromDirZ, toDirX, -(fromDirX * toDirZ));
        float _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t24 = 0.5f * _t23;
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        float _t30 = (1.0f / (float) Math.sqrt(_t29));
        float _t32 = (1.0f / (float) Math.sqrt(Math.fma(0.25f, _t23 * _t23, Math.fma(_t16, _t16, Math.fma(_t15, _t15, _t17 * _t17)))));
        if (_t24 > 6.0E-8f) {
            dest[destOffset + 0] = _t15 * _t32;
            dest[destOffset + 1] = _t17 * _t32;
            dest[destOffset + 2] = _t16 * _t32;
            dest[destOffset + 3] = _t24 * _t32;
        } else {
            if (_t29 != 0.0f) {
                dest[destOffset + 0] = _t30 * _t13;
                dest[destOffset + 1] = _t30 * _t19;
                dest[destOffset + 2] = _t30 * _t18;
                dest[destOffset + 3] = 0.0f;
            } else {
                dest[destOffset + 0] = 0.0f;
                dest[destOffset + 1] = 0.0f;
                dest[destOffset + 2] = 0.0f;
                dest[destOffset + 3] = 0.0f;
            }
        }
        return dest;
    }

    /** {@link #makeRotationTo(float[], int, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeRotationTo(java.nio.FloatBuffer dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationTo_unsafe(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationTo_api(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
    }

    /** {@link #makeRotationTo(float[], int, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationTo(java.nio.ByteBuffer dest, int destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationTo_unsafe(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return FloatQuatOpsKernelsByteBuffer.makeRotationTo_api(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
    }

    /** {@link #makeRotationTo(float[], int, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationTo(java.lang.foreign.MemorySegment dest, long destOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.makeRotationTo_unsafe(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return FloatQuatOpsKernelsSegment.makeRotationTo_api(dest, destOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
    }

    /** {@link #makeRotationTo(float[], int, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationTo(long dest, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationTo_unsafe(dest, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        makeRotationTo(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return dest;
    }

    /**
     * Set this quaternion to the rotation that rotates {@code fromDir} onto {@code toDir} (for
     * opposite vectors an arbitrary perpendicular rotation axis is chosen).
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 6e-8}
     * (about 3.5e-4 radians, 0.02 degrees, from opposite); only there is the perpendicular axis
     * chosen arbitrarily, and the result is then off by at most that angle.
     * <p>
     * Valid input: {@code fromDir} must have unit length; {@code toDir} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param fromDir the storage holding the direction to rotate from (must be a unit vector)
     * @param fromDirOffset the element index in {@code fromDir} at which the vector starts
     * @param toDir the storage holding the direction to rotate onto (must be a unit vector)
     * @param toDirOffset the element index in {@code toDir} at which the vector starts
     * @return {@code dest}
     */
    public static float[] makeRotationTo(float[] dest, int destOffset, float[] fromDir, int fromDirOffset, float[] toDir, int toDirOffset) {
        float _fromDirx = fromDir[fromDirOffset + 0];
        float _fromDiry = fromDir[fromDirOffset + 1];
        float _fromDirz = fromDir[fromDirOffset + 2];
        float _toDirx = toDir[toDirOffset + 0];
        float _toDiry = toDir[toDirOffset + 1];
        float _toDirz = toDir[toDirOffset + 2];
        float _t4 = _fromDirz + _toDirz;
        float _t5 = _fromDirx + _toDirx;
        float _t6 = _fromDiry + _toDiry;
        float _t13, _t18, _t19;
        if (Math.abs(_fromDirz) < Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0f;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0f;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        float _t15 = Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry));
        float _t16 = Math.fma(_fromDirx, _toDiry, -(_fromDiry * _toDirx));
        float _t17 = Math.fma(_fromDirz, _toDirx, -(_fromDirx * _toDirz));
        float _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return makeRotationTo_s8cf1cd18_1(dest, destOffset, _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) Math.sqrt(_t29)), (1.0f / (float) Math.sqrt(Math.fma(0.25f, _t23 * _t23, Math.fma(_t16, _t16, Math.fma(_t15, _t15, _t17 * _t17))))));
    }

    /** Piece 2 of {@code makeRotationTo}, split to fit the inline budget; reached only through it. */
    private static float[] makeRotationTo_s8cf1cd18_1(float[] dest, int destOffset, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t32) {
        if (_t24 > 6.0E-8f) {
            dest[destOffset + 0] = _t15 * _t32;
            dest[destOffset + 1] = _t17 * _t32;
            dest[destOffset + 2] = _t16 * _t32;
            dest[destOffset + 3] = _t24 * _t32;
        } else {
            if (_t29 != 0.0f) {
                dest[destOffset + 0] = _t30 * _t13;
                dest[destOffset + 1] = _t30 * _t19;
                dest[destOffset + 2] = _t30 * _t18;
                dest[destOffset + 3] = 0.0f;
            } else {
                dest[destOffset + 0] = 0.0f;
                dest[destOffset + 1] = 0.0f;
                dest[destOffset + 2] = 0.0f;
                dest[destOffset + 3] = 0.0f;
            }
        }
        return dest;
    }

    /** {@link #makeRotationTo(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeRotationTo(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && fromDir.isDirect() && fromDir.order() == java.nio.ByteOrder.nativeOrder() && toDir.isDirect() && toDir.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationTo_unsafe(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationTo_api(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset);
    }

    /** {@link #makeRotationTo(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationTo(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && fromDir.isDirect() && fromDir.order() == java.nio.ByteOrder.nativeOrder() && toDir.isDirect() && toDir.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationTo_unsafe(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset);
        return FloatQuatOpsKernelsByteBuffer.makeRotationTo_api(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset);
    }

    /** {@link #makeRotationTo(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationTo(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment fromDir, long fromDirOffset, java.lang.foreign.MemorySegment toDir, long toDirOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && fromDir.isNative() && toDir.isNative()) return FloatQuatOpsKernelsSegment.makeRotationTo_unsafe(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset);
        return FloatQuatOpsKernelsSegment.makeRotationTo_api(dest, destOffset, fromDir, fromDirOffset, toDir, toDirOffset);
    }

    /** {@link #makeRotationTo(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationTo(long dest, long fromDir, long toDir) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationTo_unsafe(dest, fromDir, toDir);
        makeRotationTo(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(fromDir, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(toDir, 12L), 0L);
        return dest;
    }

    /**
     * Set this quaternion to a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] makeRotationX(float[] dest, int destOffset, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        dest[destOffset + 0] = _t1;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = (float) Math.cosFromSin(_t1, _t0);
        return dest;
    }

    /** {@link #makeRotationX(float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeRotationX(java.nio.FloatBuffer dest, int destOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationX_unsafe(dest, destOffset, angle);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationX_api(dest, destOffset, angle);
    }

    /** {@link #makeRotationX(float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationX(java.nio.ByteBuffer dest, int destOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationX_unsafe(dest, destOffset, angle);
        return FloatQuatOpsKernelsByteBuffer.makeRotationX_api(dest, destOffset, angle);
    }

    /** {@link #makeRotationX(float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationX(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.makeRotationX_unsafe(dest, destOffset, angle);
        return FloatQuatOpsKernelsSegment.makeRotationX_api(dest, destOffset, angle);
    }

    /** {@link #makeRotationX(float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationX(long dest, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationX_unsafe(dest, angle);
        makeRotationX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, angle);
        return dest;
    }

    /**
     * Set this quaternion to a rotation of {@code angleX}, {@code angleY} and {@code angleZ}
     * radians about the X, Y and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so
     * a vector is rotated about the Z axis first, then Y, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return {@code dest}
     */
    public static float[] makeRotationXYZ(float[] dest, int destOffset, float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t4, _t1);
        float _t7 = (float) Math.cosFromSin(_t5, _t2);
        float _t8 = (float) Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        dest[destOffset + 0] = Math.fma(_t10, _t7, _t11 * _t5);
        dest[destOffset + 1] = Math.fma(_t11, _t7, -(_t10 * _t5));
        dest[destOffset + 2] = Math.fma(_t9, _t7, _t12 * _t5);
        dest[destOffset + 3] = Math.fma(_t12, _t7, -(_t9 * _t5));
        return dest;
    }

    /** {@link #makeRotationXYZ(float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeRotationXYZ(java.nio.FloatBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationXYZ_unsafe(dest, destOffset, angleX, angleY, angleZ);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationXYZ_api(dest, destOffset, angleX, angleY, angleZ);
    }

    /** {@link #makeRotationXYZ(float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationXYZ(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleY, float angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationXYZ_unsafe(dest, destOffset, angleX, angleY, angleZ);
        return FloatQuatOpsKernelsByteBuffer.makeRotationXYZ_api(dest, destOffset, angleX, angleY, angleZ);
    }

    /** {@link #makeRotationXYZ(float[], int, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationXYZ(java.lang.foreign.MemorySegment dest, long destOffset, float angleX, float angleY, float angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.makeRotationXYZ_unsafe(dest, destOffset, angleX, angleY, angleZ);
        return FloatQuatOpsKernelsSegment.makeRotationXYZ_api(dest, destOffset, angleX, angleY, angleZ);
    }

    /** {@link #makeRotationXYZ(float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationXYZ(long dest, float angleX, float angleY, float angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationXYZ_unsafe(dest, angleX, angleY, angleZ);
        makeRotationXYZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, angleX, angleY, angleZ);
        return dest;
    }

    /**
     * Set this quaternion to a rotation of {@code angleX}, {@code angleZ} and {@code angleY}
     * radians about the X, Z and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so
     * a vector is rotated about the Y axis first, then Z, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return {@code dest}
     */
    public static float[] makeRotationXZY(float[] dest, int destOffset, float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t4, _t1);
        float _t7 = (float) Math.cosFromSin(_t5, _t2);
        float _t8 = (float) Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        dest[destOffset + 0] = Math.fma(_t10, _t7, -(_t11 * _t5));
        dest[destOffset + 1] = Math.fma(_t12, _t5, -(_t9 * _t7));
        dest[destOffset + 2] = Math.fma(_t10, _t5, _t11 * _t7);
        dest[destOffset + 3] = Math.fma(_t9, _t5, _t12 * _t7);
        return dest;
    }

    /** {@link #makeRotationXZY(float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeRotationXZY(java.nio.FloatBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationXZY_unsafe(dest, destOffset, angleX, angleZ, angleY);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationXZY_api(dest, destOffset, angleX, angleZ, angleY);
    }

    /** {@link #makeRotationXZY(float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationXZY(java.nio.ByteBuffer dest, int destOffset, float angleX, float angleZ, float angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationXZY_unsafe(dest, destOffset, angleX, angleZ, angleY);
        return FloatQuatOpsKernelsByteBuffer.makeRotationXZY_api(dest, destOffset, angleX, angleZ, angleY);
    }

    /** {@link #makeRotationXZY(float[], int, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationXZY(java.lang.foreign.MemorySegment dest, long destOffset, float angleX, float angleZ, float angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.makeRotationXZY_unsafe(dest, destOffset, angleX, angleZ, angleY);
        return FloatQuatOpsKernelsSegment.makeRotationXZY_api(dest, destOffset, angleX, angleZ, angleY);
    }

    /** {@link #makeRotationXZY(float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationXZY(long dest, float angleX, float angleZ, float angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationXZY_unsafe(dest, angleX, angleZ, angleY);
        makeRotationXZY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, angleX, angleZ, angleY);
        return dest;
    }

    /**
     * Set this quaternion to a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] makeRotationY(float[] dest, int destOffset, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        dest[destOffset + 0] = 0.0f;
        dest[destOffset + 1] = _t1;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = (float) Math.cosFromSin(_t1, _t0);
        return dest;
    }

    /** {@link #makeRotationY(float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeRotationY(java.nio.FloatBuffer dest, int destOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationY_unsafe(dest, destOffset, angle);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationY_api(dest, destOffset, angle);
    }

    /** {@link #makeRotationY(float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationY(java.nio.ByteBuffer dest, int destOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationY_unsafe(dest, destOffset, angle);
        return FloatQuatOpsKernelsByteBuffer.makeRotationY_api(dest, destOffset, angle);
    }

    /** {@link #makeRotationY(float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationY(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.makeRotationY_unsafe(dest, destOffset, angle);
        return FloatQuatOpsKernelsSegment.makeRotationY_api(dest, destOffset, angle);
    }

    /** {@link #makeRotationY(float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationY(long dest, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationY_unsafe(dest, angle);
        makeRotationY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, angle);
        return dest;
    }

    /**
     * Set this quaternion to a rotation of {@code angleY}, {@code angleX} and {@code angleZ}
     * radians about the Y, X and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so
     * a vector is rotated about the Z axis first, then X, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return {@code dest}
     */
    public static float[] makeRotationYXZ(float[] dest, int destOffset, float angleY, float angleX, float angleZ) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.makeRotationYXZ(dest, destOffset, angleY, angleX, angleZ);
        return FloatQuatOpsKernelsArray.makeRotationYXZ_scalar(dest, destOffset, angleY, angleX, angleZ);
    }

    /** {@link #makeRotationYXZ(float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeRotationYXZ(java.nio.FloatBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationYXZ_unsafe(dest, destOffset, angleY, angleX, angleZ);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationYXZ_api(dest, destOffset, angleY, angleX, angleZ);
    }

    /** {@link #makeRotationYXZ(float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationYXZ(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleX, float angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationYXZ_unsafe(dest, destOffset, angleY, angleX, angleZ);
        return FloatQuatOpsKernelsByteBuffer.makeRotationYXZ_api(dest, destOffset, angleY, angleX, angleZ);
    }

    /** {@link #makeRotationYXZ(float[], int, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationYXZ(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleX, float angleZ) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.makeRotationYXZ(dest, destOffset, angleY, angleX, angleZ);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.makeRotationYXZ_unsafe(dest, destOffset, angleY, angleX, angleZ);
        return FloatQuatOpsKernelsSegment.makeRotationYXZ_api(dest, destOffset, angleY, angleX, angleZ);
    }

    /** {@link #makeRotationYXZ(float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationYXZ(long dest, float angleY, float angleX, float angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationYXZ_unsafe(dest, angleY, angleX, angleZ);
        makeRotationYXZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, angleY, angleX, angleZ);
        return dest;
    }

    /**
     * Set this quaternion to a rotation of {@code angleY}, {@code angleZ} and {@code angleX}
     * radians about the Y, Z and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so
     * a vector is rotated about the X axis first, then Z, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return {@code dest}
     */
    public static float[] makeRotationYZX(float[] dest, int destOffset, float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t5, _t2);
        float _t7 = (float) Math.cosFromSin(_t3, _t0);
        float _t8 = (float) Math.cosFromSin(_t4, _t1);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t8;
        float _t11 = _t4 * _t7;
        float _t12 = _t7 * _t8;
        dest[destOffset + 0] = Math.fma(_t9, _t6, _t12 * _t5);
        dest[destOffset + 1] = Math.fma(_t10, _t6, _t11 * _t5);
        dest[destOffset + 2] = Math.fma(_t11, _t6, -(_t10 * _t5));
        dest[destOffset + 3] = Math.fma(_t12, _t6, -(_t9 * _t5));
        return dest;
    }

    /** {@link #makeRotationYZX(float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeRotationYZX(java.nio.FloatBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationYZX_unsafe(dest, destOffset, angleY, angleZ, angleX);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationYZX_api(dest, destOffset, angleY, angleZ, angleX);
    }

    /** {@link #makeRotationYZX(float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationYZX(java.nio.ByteBuffer dest, int destOffset, float angleY, float angleZ, float angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationYZX_unsafe(dest, destOffset, angleY, angleZ, angleX);
        return FloatQuatOpsKernelsByteBuffer.makeRotationYZX_api(dest, destOffset, angleY, angleZ, angleX);
    }

    /** {@link #makeRotationYZX(float[], int, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationYZX(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleZ, float angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.makeRotationYZX_unsafe(dest, destOffset, angleY, angleZ, angleX);
        return FloatQuatOpsKernelsSegment.makeRotationYZX_api(dest, destOffset, angleY, angleZ, angleX);
    }

    /** {@link #makeRotationYZX(float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationYZX(long dest, float angleY, float angleZ, float angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationYZX_unsafe(dest, angleY, angleZ, angleX);
        makeRotationYZX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, angleY, angleZ, angleX);
        return dest;
    }

    /**
     * Set this quaternion to a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] makeRotationZ(float[] dest, int destOffset, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        dest[destOffset + 0] = 0.0f;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = _t1;
        dest[destOffset + 3] = (float) Math.cosFromSin(_t1, _t0);
        return dest;
    }

    /** {@link #makeRotationZ(float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeRotationZ(java.nio.FloatBuffer dest, int destOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationZ_unsafe(dest, destOffset, angle);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationZ_api(dest, destOffset, angle);
    }

    /** {@link #makeRotationZ(float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationZ(java.nio.ByteBuffer dest, int destOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationZ_unsafe(dest, destOffset, angle);
        return FloatQuatOpsKernelsByteBuffer.makeRotationZ_api(dest, destOffset, angle);
    }

    /** {@link #makeRotationZ(float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationZ(java.lang.foreign.MemorySegment dest, long destOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.makeRotationZ_unsafe(dest, destOffset, angle);
        return FloatQuatOpsKernelsSegment.makeRotationZ_api(dest, destOffset, angle);
    }

    /** {@link #makeRotationZ(float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationZ(long dest, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationZ_unsafe(dest, angle);
        makeRotationZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, angle);
        return dest;
    }

    /**
     * Set this quaternion to a rotation of {@code angleZ}, {@code angleX} and {@code angleY}
     * radians about the Z, X and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so
     * a vector is rotated about the Y axis first, then X, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return {@code dest}
     */
    public static float[] makeRotationZXY(float[] dest, int destOffset, float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t4, _t1);
        float _t7 = (float) Math.cosFromSin(_t5, _t2);
        float _t8 = (float) Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        dest[destOffset + 0] = Math.fma(_t10, _t7, -(_t11 * _t5));
        dest[destOffset + 1] = Math.fma(_t9, _t7, _t12 * _t5);
        dest[destOffset + 2] = Math.fma(_t10, _t5, _t11 * _t7);
        dest[destOffset + 3] = Math.fma(_t12, _t7, -(_t9 * _t5));
        return dest;
    }

    /** {@link #makeRotationZXY(float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeRotationZXY(java.nio.FloatBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationZXY_unsafe(dest, destOffset, angleZ, angleX, angleY);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationZXY_api(dest, destOffset, angleZ, angleX, angleY);
    }

    /** {@link #makeRotationZXY(float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationZXY(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleX, float angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationZXY_unsafe(dest, destOffset, angleZ, angleX, angleY);
        return FloatQuatOpsKernelsByteBuffer.makeRotationZXY_api(dest, destOffset, angleZ, angleX, angleY);
    }

    /** {@link #makeRotationZXY(float[], int, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationZXY(java.lang.foreign.MemorySegment dest, long destOffset, float angleZ, float angleX, float angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.makeRotationZXY_unsafe(dest, destOffset, angleZ, angleX, angleY);
        return FloatQuatOpsKernelsSegment.makeRotationZXY_api(dest, destOffset, angleZ, angleX, angleY);
    }

    /** {@link #makeRotationZXY(float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationZXY(long dest, float angleZ, float angleX, float angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationZXY_unsafe(dest, angleZ, angleX, angleY);
        makeRotationZXY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, angleZ, angleX, angleY);
        return dest;
    }

    /**
     * Set this quaternion to a rotation of {@code angleZ}, {@code angleY} and {@code angleX}
     * radians about the Z, Y and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so
     * a vector is rotated about the X axis first, then Y, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return {@code dest}
     */
    public static float[] makeRotationZYX(float[] dest, int destOffset, float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        dest[destOffset + 0] = Math.fma(_t12, _t5, -(_t9 * _t8));
        dest[destOffset + 1] = Math.fma(_t10, _t8, _t11 * _t5);
        dest[destOffset + 2] = Math.fma(_t11, _t8, -(_t10 * _t5));
        dest[destOffset + 3] = Math.fma(_t9, _t5, _t12 * _t8);
        return dest;
    }

    /** {@link #makeRotationZYX(float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeRotationZYX(java.nio.FloatBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.makeRotationZYX_unsafe(dest, destOffset, angleZ, angleY, angleX);
        return FloatQuatOpsKernelsTypedBuffer.makeRotationZYX_api(dest, destOffset, angleZ, angleY, angleX);
    }

    /** {@link #makeRotationZYX(float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeRotationZYX(java.nio.ByteBuffer dest, int destOffset, float angleZ, float angleY, float angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.makeRotationZYX_unsafe(dest, destOffset, angleZ, angleY, angleX);
        return FloatQuatOpsKernelsByteBuffer.makeRotationZYX_api(dest, destOffset, angleZ, angleY, angleX);
    }

    /** {@link #makeRotationZYX(float[], int, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment makeRotationZYX(java.lang.foreign.MemorySegment dest, long destOffset, float angleZ, float angleY, float angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) return FloatQuatOpsKernelsSegment.makeRotationZYX_unsafe(dest, destOffset, angleZ, angleY, angleX);
        return FloatQuatOpsKernelsSegment.makeRotationZYX_api(dest, destOffset, angleZ, angleY, angleX);
    }

    /** {@link #makeRotationZYX(float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long makeRotationZYX(long dest, float angleZ, float angleY, float angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.makeRotationZYX_unsafe(dest, angleZ, angleY, angleX);
        makeRotationZYX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, angleZ, angleY, angleX);
        return dest;
    }

    /**
     * Pre-multiply a rotation of {@code angle} radians about the X axis onto this quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] preRotateX(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        dest[destOffset + 0] = Math.fma(_selfx, _t2, _selfw * _t1);
        dest[destOffset + 1] = Math.fma(_selfy, _t2, -(_selfz * _t1));
        dest[destOffset + 2] = Math.fma(_selfy, _t1, _selfz * _t2);
        dest[destOffset + 3] = Math.fma(_selfw, _t2, -(_selfx * _t1));
        return dest;
    }

    /** {@link #preRotateX(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preRotateX(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.preRotateX_unsafe(dest, destOffset, src, srcOffset, angle);
        return FloatQuatOpsKernelsTypedBuffer.preRotateX_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotateX(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preRotateX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.preRotateX_unsafe(dest, destOffset, src, srcOffset, angle);
        return FloatQuatOpsKernelsByteBuffer.preRotateX_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotateX(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preRotateX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.preRotateX_unsafe(dest, destOffset, src, srcOffset, angle);
        return FloatQuatOpsKernelsSegment.preRotateX_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotateX(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preRotateX(long dest, long src, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.preRotateX_unsafe(dest, src, angle);
        preRotateX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, angle);
        return dest;
    }

    /**
     * Pre-multiply a rotation of {@code angle} radians about the Y axis onto this quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] preRotateY(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        dest[destOffset + 0] = Math.fma(_selfx, _t2, _selfz * _t1);
        dest[destOffset + 1] = Math.fma(_selfy, _t2, _selfw * _t1);
        dest[destOffset + 2] = Math.fma(_selfz, _t2, -(_selfx * _t1));
        dest[destOffset + 3] = Math.fma(_selfw, _t2, -(_selfy * _t1));
        return dest;
    }

    /** {@link #preRotateY(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preRotateY(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.preRotateY_unsafe(dest, destOffset, src, srcOffset, angle);
        return FloatQuatOpsKernelsTypedBuffer.preRotateY_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotateY(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preRotateY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.preRotateY_unsafe(dest, destOffset, src, srcOffset, angle);
        return FloatQuatOpsKernelsByteBuffer.preRotateY_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotateY(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preRotateY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.preRotateY_unsafe(dest, destOffset, src, srcOffset, angle);
        return FloatQuatOpsKernelsSegment.preRotateY_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotateY(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preRotateY(long dest, long src, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.preRotateY_unsafe(dest, src, angle);
        preRotateY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, angle);
        return dest;
    }

    /**
     * Pre-multiply a rotation of {@code angle} radians about the Z axis onto this quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code R * Q}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code R * Q * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] preRotateZ(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        dest[destOffset + 0] = Math.fma(_selfx, _t2, -(_selfy * _t1));
        dest[destOffset + 1] = Math.fma(_selfx, _t1, _selfy * _t2);
        dest[destOffset + 2] = Math.fma(_selfz, _t2, _selfw * _t1);
        dest[destOffset + 3] = Math.fma(_selfw, _t2, -(_selfz * _t1));
        return dest;
    }

    /** {@link #preRotateZ(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preRotateZ(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.preRotateZ_unsafe(dest, destOffset, src, srcOffset, angle);
        return FloatQuatOpsKernelsTypedBuffer.preRotateZ_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotateZ(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preRotateZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.preRotateZ_unsafe(dest, destOffset, src, srcOffset, angle);
        return FloatQuatOpsKernelsByteBuffer.preRotateZ_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotateZ(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment preRotateZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.preRotateZ_unsafe(dest, destOffset, src, srcOffset, angle);
        return FloatQuatOpsKernelsSegment.preRotateZ_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #preRotateZ(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long preRotateZ(long dest, long src, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.preRotateZ_unsafe(dest, src, angle);
        preRotateZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, angle);
        return dest;
    }

    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) to this quaternion and store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return {@code dest}
     */
    public static float[] rotateAxis(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = (float) Math.cosFromSin(_t1, _t0);
        dest[destOffset + 0] = Math.fma(_selfx, _t5, _selfw * _t2) + Math.fma(_selfy, _t3, -(_selfz * _t4));
        dest[destOffset + 1] = Math.fma(_selfy, _t5, _selfz * _t2) + Math.fma(_selfw, _t4, -(_selfx * _t3));
        dest[destOffset + 2] = Math.fma(_selfx, _t4, _selfw * _t3) + Math.fma(_selfz, _t5, -(_selfy * _t2));
        dest[destOffset + 3] = Math.fma(_selfw, _t5, -(_selfx * _t2)) - Math.fma(_selfy, _t4, _selfz * _t3);
        return dest;
    }

    /** {@link #rotateAxis(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateAxis(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.rotateAxis_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return FloatQuatOpsKernelsTypedBuffer.rotateAxis_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #rotateAxis(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAxis(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.rotateAxis_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return FloatQuatOpsKernelsByteBuffer.rotateAxis_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #rotateAxis(float[], int, float[], int, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.rotateAxis_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return FloatQuatOpsKernelsSegment.rotateAxis_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #rotateAxis(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateAxis(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.rotateAxis_unsafe(dest, src, angle, axisX, axisY, axisZ);
        rotateAxis(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, angle, axisX, axisY, axisZ);
        return dest;
    }

    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this quaternion and
     * store the result in {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param axis the storage holding the rotation axis
     * @param axisOffset the element index in {@code axis} at which the vector starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] rotateAxis(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _axisx = axis[axisOffset + 0];
        float _axisy = axis[axisOffset + 1];
        float _axisz = axis[axisOffset + 2];
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = _axisx * _t1;
        float _t3 = _axisz * _t1;
        float _t4 = _axisy * _t1;
        float _t5 = (float) Math.cosFromSin(_t1, _t0);
        dest[destOffset + 0] = Math.fma(_selfx, _t5, _selfw * _t2) + Math.fma(_selfy, _t3, -(_selfz * _t4));
        dest[destOffset + 1] = Math.fma(_selfy, _t5, _selfz * _t2) + Math.fma(_selfw, _t4, -(_selfx * _t3));
        dest[destOffset + 2] = Math.fma(_selfx, _t4, _selfw * _t3) + Math.fma(_selfz, _t5, -(_selfy * _t2));
        dest[destOffset + 3] = Math.fma(_selfw, _t5, -(_selfx * _t2)) - Math.fma(_selfy, _t4, _selfz * _t3);
        return dest;
    }

    /** {@link #rotateAxis(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateAxis(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.rotateAxis_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return FloatQuatOpsKernelsTypedBuffer.rotateAxis_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@link #rotateAxis(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAxis(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.rotateAxis_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return FloatQuatOpsKernelsByteBuffer.rotateAxis_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@link #rotateAxis(float[], int, float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && axis.isNative()) return FloatQuatOpsKernelsSegment.rotateAxis_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return FloatQuatOpsKernelsSegment.rotateAxis_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@link #rotateAxis(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateAxis(long dest, long src, long axis, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.rotateAxis_unsafe(dest, src, axis, angle);
        rotateAxis(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(axis, 12L), 0L, angle);
        return dest;
    }

    /**
     * Apply the rotation that rotates ({@code fromDirX}, {@code fromDirY}, {@code fromDirZ}) onto
     * ({@code toDirX}, {@code toDirY}, {@code toDirZ}) (for opposite vectors an arbitrary
     * perpendicular rotation axis is chosen) to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 6e-8}
     * (about 3.5e-4 radians, 0.02 degrees, from opposite); only there is the perpendicular axis
     * chosen arbitrarily, and the result is then off by at most that angle.
     * <p>
     * Valid input: {@code (fromDirX, fromDirY, fromDirZ)} must have unit length;
     * {@code (toDirX, toDirY, toDirZ)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param fromDirX the {@code x} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirY the {@code y} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param fromDirZ the {@code z} component of the vector {@code (fromDirX, fromDirY, fromDirZ)}
     * @param toDirX the {@code x} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirY the {@code y} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @param toDirZ the {@code z} component of the vector {@code (toDirX, toDirY, toDirZ)}
     * @return {@code dest}
     */
    public static float[] rotateTo(float[] dest, int destOffset, float[] src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (Math.abs(fromDirZ) < Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = Math.fma(fromDirX, toDirY, -(fromDirY * toDirX));
        float _t16 = Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t17 = Math.fma(fromDirZ, toDirX, -(fromDirX * toDirZ));
        float _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_s8dde370e_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) Math.sqrt(_t29)), (1.0f / (float) Math.sqrt(Math.fma(0.25f, _t23 * _t23, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTo_s8dde370e_1(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
        float _t44, _t45, _t46, _t47;
        if (_t24 > 6.0E-8f) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        dest[destOffset + 0] = Math.fma(_selfx, _t44, _selfw * _t45) + Math.fma(_selfy, _t46, -(_selfz * _t47));
        dest[destOffset + 1] = Math.fma(_selfy, _t44, _selfz * _t45) + Math.fma(_selfw, _t47, -(_selfx * _t46));
        dest[destOffset + 2] = Math.fma(_selfx, _t47, _selfw * _t46) + Math.fma(_selfz, _t44, -(_selfy * _t45));
        dest[destOffset + 3] = Math.fma(_selfw, _t44, -(_selfx * _t45)) - Math.fma(_selfy, _t47, _selfz * _t46);
        return dest;
    }

    /** {@link #rotateTo(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateTo(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.rotateTo_unsafe(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return FloatQuatOpsKernelsTypedBuffer.rotateTo_api(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
    }

    /** {@link #rotateTo(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateTo(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.rotateTo_unsafe(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return FloatQuatOpsKernelsByteBuffer.rotateTo_api(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
    }

    /** {@link #rotateTo(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateTo(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.rotateTo_unsafe(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return FloatQuatOpsKernelsSegment.rotateTo_api(dest, destOffset, src, srcOffset, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
    }

    /** {@link #rotateTo(float[], int, float[], int, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateTo(long dest, long src, float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.rotateTo_unsafe(dest, src, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        rotateTo(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        return dest;
    }

    /**
     * Apply the rotation that rotates {@code fromDir} onto {@code toDir} (for opposite vectors an
     * arbitrary perpendicular rotation axis is chosen) to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * The half-vector form stays accurate for nearly antiparallel inputs down to the 180-degree
     * fallback, which is taken when {@code 1 + dot(fromDir, toDir)} is no larger than {@code 6e-8}
     * (about 3.5e-4 radians, 0.02 degrees, from opposite); only there is the perpendicular axis
     * chosen arbitrarily, and the result is then off by at most that angle.
     * <p>
     * Valid input: {@code fromDir} must have unit length; {@code toDir} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param fromDir the storage holding the direction to rotate from (must be a unit vector)
     * @param fromDirOffset the element index in {@code fromDir} at which the vector starts
     * @param toDir the storage holding the direction to rotate onto (must be a unit vector)
     * @param toDirOffset the element index in {@code toDir} at which the vector starts
     * @return {@code dest}
     */
    public static float[] rotateTo(float[] dest, int destOffset, float[] src, int srcOffset, float[] fromDir, int fromDirOffset, float[] toDir, int toDirOffset) {
        float _fromDirx = fromDir[fromDirOffset + 0];
        float _fromDiry = fromDir[fromDirOffset + 1];
        float _fromDirz = fromDir[fromDirOffset + 2];
        float _toDirx = toDir[toDirOffset + 0];
        float _toDiry = toDir[toDirOffset + 1];
        float _toDirz = toDir[toDirOffset + 2];
        float _t4 = _fromDirz + _toDirz;
        float _t5 = _fromDirx + _toDirx;
        float _t6 = _fromDiry + _toDiry;
        float _t13, _t18, _t19;
        if (Math.abs(_fromDirz) < Math.abs(_fromDirx)) {
            _t13 = _fromDiry;
            _t18 = 0.0f;
            _t19 = -_fromDirx;
        } else {
            _t13 = 0.0f;
            _t18 = -_fromDiry;
            _t19 = _fromDirz;
        }
        float _t15 = Math.fma(_fromDirx, _toDiry, -(_fromDiry * _toDirx));
        float _t16 = Math.fma(_fromDiry, _toDirz, -(_fromDirz * _toDiry));
        float _t17 = Math.fma(_fromDirz, _toDirx, -(_fromDirx * _toDirz));
        float _t23 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t13, _t13, _t19 * _t19));
        return rotateTo_s71de7975_1(dest, destOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t13, _t18, _t19, _t15, _t16, _t17, 0.5f * _t23, _t29, (1.0f / (float) Math.sqrt(_t29)), (1.0f / (float) Math.sqrt(Math.fma(0.25f, _t23 * _t23, Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17))))));
    }

    /** Piece 2 of {@code rotateTo}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTo_s71de7975_1(float[] dest, int destOffset, float _selfx, float _selfy, float _selfz, float _selfw, float _t13, float _t18, float _t19, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t35) {
        float _t44, _t45, _t46, _t47;
        if (_t24 > 6.0E-8f) {
            _t44 = _t24 * _t35;
            _t45 = _t16 * _t35;
            _t46 = _t15 * _t35;
            _t47 = _t17 * _t35;
        } else {
            if (_t29 != 0.0f) {
                _t44 = 0.0f;
                _t45 = _t30 * _t13;
                _t46 = _t30 * _t18;
                _t47 = _t30 * _t19;
            } else {
                _t44 = 0.0f;
                _t45 = 0.0f;
                _t46 = 0.0f;
                _t47 = 0.0f;
            }
        }
        dest[destOffset + 0] = Math.fma(_selfx, _t44, _selfw * _t45) + Math.fma(_selfy, _t46, -(_selfz * _t47));
        dest[destOffset + 1] = Math.fma(_selfy, _t44, _selfz * _t45) + Math.fma(_selfw, _t47, -(_selfx * _t46));
        dest[destOffset + 2] = Math.fma(_selfx, _t47, _selfw * _t46) + Math.fma(_selfz, _t44, -(_selfy * _t45));
        dest[destOffset + 3] = Math.fma(_selfw, _t44, -(_selfx * _t45)) - Math.fma(_selfy, _t47, _selfz * _t46);
        return dest;
    }

    /** {@link #rotateTo(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateTo(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer fromDir, int fromDirOffset, java.nio.FloatBuffer toDir, int toDirOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && fromDir.isDirect() && fromDir.order() == java.nio.ByteOrder.nativeOrder() && toDir.isDirect() && toDir.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.rotateTo_unsafe(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset);
        return FloatQuatOpsKernelsTypedBuffer.rotateTo_api(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset);
    }

    /** {@link #rotateTo(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateTo(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer fromDir, int fromDirOffset, java.nio.ByteBuffer toDir, int toDirOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && fromDir.isDirect() && fromDir.order() == java.nio.ByteOrder.nativeOrder() && toDir.isDirect() && toDir.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.rotateTo_unsafe(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset);
        return FloatQuatOpsKernelsByteBuffer.rotateTo_api(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset);
    }

    /** {@link #rotateTo(float[], int, float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateTo(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment fromDir, long fromDirOffset, java.lang.foreign.MemorySegment toDir, long toDirOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && fromDir.isNative() && toDir.isNative()) return FloatQuatOpsKernelsSegment.rotateTo_unsafe(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset);
        return FloatQuatOpsKernelsSegment.rotateTo_api(dest, destOffset, src, srcOffset, fromDir, fromDirOffset, toDir, toDirOffset);
    }

    /** {@link #rotateTo(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateTo(long dest, long src, long fromDir, long toDir) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.rotateTo_unsafe(dest, src, fromDir, toDir);
        rotateTo(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(fromDir, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(toDir, 12L), 0L);
        return dest;
    }

    /**
     * Rotate this quaternion by {@code angle} radians about the local X axis and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] rotateX(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        dest[destOffset + 0] = Math.fma(_selfx, _t2, _selfw * _t1);
        dest[destOffset + 1] = Math.fma(_selfy, _t2, _selfz * _t1);
        dest[destOffset + 2] = Math.fma(_selfz, _t2, -(_selfy * _t1));
        dest[destOffset + 3] = Math.fma(_selfw, _t2, -(_selfx * _t1));
        return dest;
    }

    /** {@link #rotateX(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateX(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.rotateX_unsafe(dest, destOffset, src, srcOffset, angle);
        return FloatQuatOpsKernelsTypedBuffer.rotateX_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateX(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.rotateX_unsafe(dest, destOffset, src, srcOffset, angle);
        return FloatQuatOpsKernelsByteBuffer.rotateX_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateX(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.rotateX_unsafe(dest, destOffset, src, srcOffset, angle);
        return FloatQuatOpsKernelsSegment.rotateX_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateX(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateX(long dest, long src, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.rotateX_unsafe(dest, src, angle);
        rotateX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, angle);
        return dest;
    }

    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return {@code dest}
     */
    public static float[] rotateXYZ(float[] dest, int destOffset, float[] src, int srcOffset, float angleX, float angleY, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t14 = _t6 * _t7;
        float _t19 = Math.fma(_t10, _t8, _t11 * _t5);
        float _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        dest[destOffset + 0] = Math.fma(_selfx, _t21, _selfw * _t19) + Math.fma(_selfy, _t20, -(_selfz * _t22));
        dest[destOffset + 1] = Math.fma(_selfy, _t21, _selfz * _t19) + Math.fma(_selfw, _t22, -(_selfx * _t20));
        dest[destOffset + 2] = Math.fma(_selfx, _t22, _selfw * _t20) + Math.fma(_selfz, _t21, -(_selfy * _t19));
        dest[destOffset + 3] = Math.fma(_selfw, _t21, -(_selfx * _t19)) - Math.fma(_selfy, _t22, _selfz * _t20);
        return dest;
    }

    /** {@link #rotateXYZ(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateXYZ(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.rotateXYZ_unsafe(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return FloatQuatOpsKernelsTypedBuffer.rotateXYZ_api(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
    }

    /** {@link #rotateXYZ(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateXYZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleY, float angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.rotateXYZ_unsafe(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return FloatQuatOpsKernelsByteBuffer.rotateXYZ_api(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
    }

    /** {@link #rotateXYZ(float[], int, float[], int, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateXYZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleY, float angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.rotateXYZ_unsafe(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return FloatQuatOpsKernelsSegment.rotateXYZ_api(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
    }

    /** {@link #rotateXYZ(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateXYZ(long dest, long src, float angleX, float angleY, float angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.rotateXYZ_unsafe(dest, src, angleX, angleY, angleZ);
        rotateXYZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, angleX, angleY, angleZ);
        return dest;
    }

    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return {@code dest}
     */
    public static float[] rotateXZY(float[] dest, int destOffset, float[] src, int srcOffset, float angleX, float angleZ, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t10, _t5, _t11 * _t8);
        float _t21 = Math.fma(_t10, _t8, -(_t11 * _t5));
        float _t22 = Math.fma(_t12, _t5, -(_t9 * _t8));
        dest[destOffset + 0] = Math.fma(_selfx, _t19, _selfw * _t21) + Math.fma(_selfy, _t20, -(_selfz * _t22));
        dest[destOffset + 1] = Math.fma(_selfy, _t19, _selfz * _t21) + Math.fma(_selfw, _t22, -(_selfx * _t20));
        dest[destOffset + 2] = Math.fma(_selfx, _t22, _selfw * _t20) + Math.fma(_selfz, _t19, -(_selfy * _t21));
        dest[destOffset + 3] = Math.fma(_selfw, _t19, -(_selfx * _t21)) - Math.fma(_selfy, _t22, _selfz * _t20);
        return dest;
    }

    /** {@link #rotateXZY(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateXZY(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.rotateXZY_unsafe(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return FloatQuatOpsKernelsTypedBuffer.rotateXZY_api(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
    }

    /** {@link #rotateXZY(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateXZY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleX, float angleZ, float angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.rotateXZY_unsafe(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return FloatQuatOpsKernelsByteBuffer.rotateXZY_api(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
    }

    /** {@link #rotateXZY(float[], int, float[], int, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateXZY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleX, float angleZ, float angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.rotateXZY_unsafe(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return FloatQuatOpsKernelsSegment.rotateXZY_api(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
    }

    /** {@link #rotateXZY(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateXZY(long dest, long src, float angleX, float angleZ, float angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.rotateXZY_unsafe(dest, src, angleX, angleZ, angleY);
        rotateXZY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, angleX, angleZ, angleY);
        return dest;
    }

    /**
     * Rotate this quaternion by {@code angle} radians about the local Y axis and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] rotateY(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        dest[destOffset + 0] = Math.fma(_selfx, _t2, -(_selfz * _t1));
        dest[destOffset + 1] = Math.fma(_selfy, _t2, _selfw * _t1);
        dest[destOffset + 2] = Math.fma(_selfx, _t1, _selfz * _t2);
        dest[destOffset + 3] = Math.fma(_selfw, _t2, -(_selfy * _t1));
        return dest;
    }

    /** {@link #rotateY(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateY(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.rotateY_unsafe(dest, destOffset, src, srcOffset, angle);
        return FloatQuatOpsKernelsTypedBuffer.rotateY_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateY(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.rotateY_unsafe(dest, destOffset, src, srcOffset, angle);
        return FloatQuatOpsKernelsByteBuffer.rotateY_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateY(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.rotateY_unsafe(dest, destOffset, src, srcOffset, angle);
        return FloatQuatOpsKernelsSegment.rotateY_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateY(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateY(long dest, long src, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.rotateY_unsafe(dest, src, angle);
        rotateY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, angle);
        return dest;
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return {@code dest}
     */
    public static float[] rotateYXZ(float[] dest, int destOffset, float[] src, int srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t12 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t10, _t8, _t11 * _t5);
        float _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        float _t22 = Math.fma(_t11, _t8, -(_t10 * _t5));
        dest[destOffset + 0] = Math.fma(_selfx, _t19, _selfw * _t20) + Math.fma(_selfy, _t21, -(_selfz * _t22));
        dest[destOffset + 1] = Math.fma(_selfy, _t19, _selfz * _t20) + Math.fma(_selfw, _t22, -(_selfx * _t21));
        dest[destOffset + 2] = Math.fma(_selfx, _t22, _selfw * _t21) + Math.fma(_selfz, _t19, -(_selfy * _t20));
        dest[destOffset + 3] = Math.fma(_selfw, _t19, -(_selfx * _t20)) - Math.fma(_selfy, _t22, _selfz * _t21);
        return dest;
    }

    /** {@link #rotateYXZ(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateYXZ(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.rotateYXZ_unsafe(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return FloatQuatOpsKernelsTypedBuffer.rotateYXZ_api(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    /** {@link #rotateYXZ(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateYXZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleX, float angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.rotateYXZ_unsafe(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return FloatQuatOpsKernelsByteBuffer.rotateYXZ_api(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    /** {@link #rotateYXZ(float[], int, float[], int, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateYXZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleX, float angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.rotateYXZ_unsafe(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return FloatQuatOpsKernelsSegment.rotateYXZ_api(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    /** {@link #rotateYXZ(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateYXZ(long dest, long src, float angleY, float angleX, float angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.rotateYXZ_unsafe(dest, src, angleY, angleX, angleZ);
        rotateYXZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, angleY, angleX, angleZ);
        return dest;
    }

    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return {@code dest}
     */
    public static float[] rotateYZX(float[] dest, int destOffset, float[] src, int srcOffset, float angleY, float angleZ, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t4 * _t6;
        float _t11 = _t3 * _t7;
        float _t14 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        dest[destOffset + 0] = Math.fma(_selfx, _t21, _selfw * _t19) + Math.fma(_selfy, _t22, -(_selfz * _t20));
        dest[destOffset + 1] = Math.fma(_selfy, _t21, _selfz * _t19) + Math.fma(_selfw, _t20, -(_selfx * _t22));
        dest[destOffset + 2] = Math.fma(_selfx, _t20, _selfw * _t22) + Math.fma(_selfz, _t21, -(_selfy * _t19));
        dest[destOffset + 3] = Math.fma(_selfw, _t21, -(_selfx * _t19)) - Math.fma(_selfy, _t20, _selfz * _t22);
        return dest;
    }

    /** {@link #rotateYZX(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateYZX(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.rotateYZX_unsafe(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return FloatQuatOpsKernelsTypedBuffer.rotateYZX_api(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
    }

    /** {@link #rotateYZX(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateYZX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleY, float angleZ, float angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.rotateYZX_unsafe(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return FloatQuatOpsKernelsByteBuffer.rotateYZX_api(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
    }

    /** {@link #rotateYZX(float[], int, float[], int, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateYZX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleZ, float angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.rotateYZX_unsafe(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return FloatQuatOpsKernelsSegment.rotateYZX_api(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
    }

    /** {@link #rotateYZX(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateYZX(long dest, long src, float angleY, float angleZ, float angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.rotateYZX_unsafe(dest, src, angleY, angleZ, angleX);
        rotateYZX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, angleY, angleZ, angleX);
        return dest;
    }

    /**
     * Rotate this quaternion by {@code angle} radians about the local Z axis and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] rotateZ(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        float _t2 = (float) Math.cosFromSin(_t1, _t0);
        dest[destOffset + 0] = Math.fma(_selfx, _t2, _selfy * _t1);
        dest[destOffset + 1] = Math.fma(_selfy, _t2, -(_selfx * _t1));
        dest[destOffset + 2] = Math.fma(_selfz, _t2, _selfw * _t1);
        dest[destOffset + 3] = Math.fma(_selfw, _t2, -(_selfz * _t1));
        return dest;
    }

    /** {@link #rotateZ(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateZ(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.rotateZ_unsafe(dest, destOffset, src, srcOffset, angle);
        return FloatQuatOpsKernelsTypedBuffer.rotateZ_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateZ(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.rotateZ_unsafe(dest, destOffset, src, srcOffset, angle);
        return FloatQuatOpsKernelsByteBuffer.rotateZ_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateZ(float[], int, float[], int, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.rotateZ_unsafe(dest, destOffset, src, srcOffset, angle);
        return FloatQuatOpsKernelsSegment.rotateZ_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateZ(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateZ(long dest, long src, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.rotateZ_unsafe(dest, src, angle);
        rotateZ(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, angle);
        return dest;
    }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return {@code dest}
     */
    public static float[] rotateZXY(float[] dest, int destOffset, float[] src, int srcOffset, float angleZ, float angleX, float angleY) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleY;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t7;
        float _t11 = _t4 * _t6;
        float _t14 = _t6 * _t7;
        float _t19 = Math.fma(_t10, _t5, _t11 * _t8);
        float _t20 = Math.fma(_t9, _t8, _t14 * _t5);
        float _t21 = Math.fma(_t14, _t8, -(_t9 * _t5));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        dest[destOffset + 0] = Math.fma(_selfx, _t21, _selfw * _t22) + Math.fma(_selfy, _t19, -(_selfz * _t20));
        dest[destOffset + 1] = Math.fma(_selfy, _t21, _selfz * _t22) + Math.fma(_selfw, _t20, -(_selfx * _t19));
        dest[destOffset + 2] = Math.fma(_selfx, _t20, _selfw * _t19) + Math.fma(_selfz, _t21, -(_selfy * _t22));
        dest[destOffset + 3] = Math.fma(_selfw, _t21, -(_selfx * _t22)) - Math.fma(_selfy, _t20, _selfz * _t19);
        return dest;
    }

    /** {@link #rotateZXY(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateZXY(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.rotateZXY_unsafe(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return FloatQuatOpsKernelsTypedBuffer.rotateZXY_api(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
    }

    /** {@link #rotateZXY(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateZXY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleX, float angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.rotateZXY_unsafe(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return FloatQuatOpsKernelsByteBuffer.rotateZXY_api(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
    }

    /** {@link #rotateZXY(float[], int, float[], int, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateZXY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleX, float angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.rotateZXY_unsafe(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return FloatQuatOpsKernelsSegment.rotateZXY_api(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
    }

    /** {@link #rotateZXY(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateZXY(long dest, long src, float angleZ, float angleX, float angleY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.rotateZXY_unsafe(dest, src, angleZ, angleX, angleY);
        rotateZXY(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, angleZ, angleX, angleY);
        return dest;
    }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z), to this quaternion and store the result in
     * {@code dest}.
     * <p>
     * If {@code Q} is {@code this} quaternion and {@code R} the rotation quaternion, then the new
     * quaternion will be {@code Q * R}. So when transforming a vector {@code v} with the new
     * quaternion by using {@code Q * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the quaternion starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return {@code dest}
     */
    public static float[] rotateZYX(float[] dest, int destOffset, float[] src, int srcOffset, float angleZ, float angleY, float angleX) {
        float _t0 = 0.5f * angleY;
        float _t1 = 0.5f * angleZ;
        float _t2 = 0.5f * angleX;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t6 = (float) Math.cosFromSin(_t3, _t0);
        float _t7 = (float) Math.cosFromSin(_t4, _t1);
        float _t8 = (float) Math.cosFromSin(_t5, _t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t4 * _t6;
        float _t11 = _t3 * _t7;
        float _t12 = _t6 * _t7;
        float _t19 = Math.fma(_t9, _t5, _t12 * _t8);
        float _t20 = Math.fma(_t11, _t8, _t10 * _t5);
        float _t21 = Math.fma(_t12, _t5, -(_t9 * _t8));
        float _t22 = Math.fma(_t10, _t8, -(_t11 * _t5));
        dest[destOffset + 0] = Math.fma(_selfx, _t19, _selfw * _t21) + Math.fma(_selfy, _t22, -(_selfz * _t20));
        dest[destOffset + 1] = Math.fma(_selfy, _t19, _selfz * _t21) + Math.fma(_selfw, _t20, -(_selfx * _t22));
        dest[destOffset + 2] = Math.fma(_selfx, _t20, _selfw * _t22) + Math.fma(_selfz, _t19, -(_selfy * _t21));
        dest[destOffset + 3] = Math.fma(_selfw, _t19, -(_selfx * _t21)) - Math.fma(_selfy, _t20, _selfz * _t22);
        return dest;
    }

    /** {@link #rotateZYX(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateZYX(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.rotateZYX_unsafe(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return FloatQuatOpsKernelsTypedBuffer.rotateZYX_api(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
    }

    /** {@link #rotateZYX(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateZYX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angleZ, float angleY, float angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.rotateZYX_unsafe(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return FloatQuatOpsKernelsByteBuffer.rotateZYX_api(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
    }

    /** {@link #rotateZYX(float[], int, float[], int, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment rotateZYX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleZ, float angleY, float angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.rotateZYX_unsafe(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return FloatQuatOpsKernelsSegment.rotateZYX_api(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
    }

    /** {@link #rotateZYX(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long rotateZYX(long dest, long src, float angleZ, float angleY, float angleX) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.rotateZYX_unsafe(dest, src, angleZ, angleY, angleX);
        rotateZYX(VirtualMemoryHolder.virtualMemory().asSlice(dest, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, angleZ, angleY, angleX);
        return dest;
    }

    /**
     * Transform ({@code vX}, {@code vY}, {@code vZ}) by this quaternion and store the result in
     * {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return {@code dest}
     */
    public static float[] transform(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t9 = 2.0f * Math.fma(_selfx, vY, -(_selfy * vX));
        float _t10 = 2.0f * Math.fma(_selfz, vX, -(_selfx * vZ));
        float _t11 = 2.0f * Math.fma(_selfy, vZ, -(_selfz * vY));
        dest[destOffset + 0] = Math.fma(_selfy, _t9, Math.fma(-_selfz, _t10, Math.fma(_selfw, _t11, vX)));
        dest[destOffset + 1] = Math.fma(_selfz, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, vY)));
        dest[destOffset + 2] = Math.fma(_selfx, _t10, Math.fma(-_selfy, _t11, Math.fma(_selfw, _t9, vZ)));
        return dest;
    }

    /** {@link #transform(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer transform(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.transform_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return FloatQuatOpsKernelsTypedBuffer.transform_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    /** {@link #transform(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer transform(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.transform_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return FloatQuatOpsKernelsByteBuffer.transform_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    /** {@link #transform(float[], int, float[], int, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment transform(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.transform_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return FloatQuatOpsKernelsSegment.transform_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    /** {@link #transform(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long transform(long dest, long src, float vX, float vY, float vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.transform_unsafe(dest, src, vX, vY, vZ);
        transform(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, vX, vY, vZ);
        return dest;
    }

    /**
     * Transform {@code v} by this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param v the storage holding the vector to transform
     * @param vOffset the element index in {@code v} at which the vector starts
     * @return {@code dest}
     */
    public static float[] transform(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _vx = v[vOffset + 0];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        float _t9 = 2.0f * Math.fma(_selfx, _vy, -(_selfy * _vx));
        float _t10 = 2.0f * Math.fma(_selfz, _vx, -(_selfx * _vz));
        float _t11 = 2.0f * Math.fma(_selfy, _vz, -(_selfz * _vy));
        dest[destOffset + 0] = Math.fma(_selfy, _t9, Math.fma(-_selfz, _t10, Math.fma(_selfw, _t11, _vx)));
        dest[destOffset + 1] = Math.fma(_selfz, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, _vy)));
        dest[destOffset + 2] = Math.fma(_selfx, _t10, Math.fma(-_selfy, _t11, Math.fma(_selfw, _t9, _vz)));
        return dest;
    }

    /** {@link #transform(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer transform(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.transform_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return FloatQuatOpsKernelsTypedBuffer.transform_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #transform(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer transform(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.transform_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return FloatQuatOpsKernelsByteBuffer.transform_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #transform(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment transform(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && v.isNative()) return FloatQuatOpsKernelsSegment.transform_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return FloatQuatOpsKernelsSegment.transform_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #transform(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long transform(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.transform_unsafe(dest, src, v);
        transform(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 12L), 0L);
        return dest;
    }

    /**
     * Transform ({@code vX}, {@code vY}, {@code vZ}) by the inverse of this quaternion and store
     * the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return {@code dest}
     */
    public static float[] transformInverse(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t9 = 2.0f * Math.fma(_selfx, vZ, -(_selfz * vX));
        float _t10 = 2.0f * Math.fma(_selfy, vX, -(_selfx * vY));
        float _t11 = 2.0f * Math.fma(_selfz, vY, -(_selfy * vZ));
        dest[destOffset + 0] = Math.fma(_selfz, _t9, Math.fma(-_selfy, _t10, Math.fma(_selfw, _t11, vX)));
        dest[destOffset + 1] = Math.fma(_selfx, _t10, Math.fma(-_selfz, _t11, Math.fma(_selfw, _t9, vY)));
        dest[destOffset + 2] = Math.fma(_selfy, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, vZ)));
        return dest;
    }

    /** {@link #transformInverse(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer transformInverse(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.transformInverse_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return FloatQuatOpsKernelsTypedBuffer.transformInverse_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    /** {@link #transformInverse(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer transformInverse(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float vX, float vY, float vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.transformInverse_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return FloatQuatOpsKernelsByteBuffer.transformInverse_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    /** {@link #transformInverse(float[], int, float[], int, float, float, float)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment transformInverse(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return FloatQuatOpsKernelsSegment.transformInverse_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return FloatQuatOpsKernelsSegment.transformInverse_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    /** {@link #transformInverse(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long transformInverse(long dest, long src, float vX, float vY, float vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.transformInverse_unsafe(dest, src, vX, vY, vZ);
        transformInverse(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, vX, vY, vZ);
        return dest;
    }

    /**
     * Transform {@code v} by the inverse of this quaternion and store the result in {@code dest}.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the quaternion
     * @param srcOffset the element index in {@code src} at which the quaternion starts
     * @param v the storage holding the vector to transform
     * @param vOffset the element index in {@code v} at which the vector starts
     * @return {@code dest}
     */
    public static float[] transformInverse(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _vx = v[vOffset + 0];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        float _t9 = 2.0f * Math.fma(_selfx, _vz, -(_selfz * _vx));
        float _t10 = 2.0f * Math.fma(_selfy, _vx, -(_selfx * _vy));
        float _t11 = 2.0f * Math.fma(_selfz, _vy, -(_selfy * _vz));
        dest[destOffset + 0] = Math.fma(_selfz, _t9, Math.fma(-_selfy, _t10, Math.fma(_selfw, _t11, _vx)));
        dest[destOffset + 1] = Math.fma(_selfx, _t10, Math.fma(-_selfz, _t11, Math.fma(_selfw, _t9, _vy)));
        dest[destOffset + 2] = Math.fma(_selfy, _t11, Math.fma(-_selfx, _t9, Math.fma(_selfw, _t10, _vz)));
        return dest;
    }

    /** {@link #transformInverse(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer transformInverse(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsTypedBuffer.transformInverse_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return FloatQuatOpsKernelsTypedBuffer.transformInverse_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #transformInverse(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer transformInverse(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsKernelsByteBuffer.transformInverse_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return FloatQuatOpsKernelsByteBuffer.transformInverse_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #transformInverse(float[], int, float[], int, float[], int)} on {@link java.lang.foreign.MemorySegment} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.lang.foreign.MemorySegment transformInverse(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && v.isNative()) return FloatQuatOpsKernelsSegment.transformInverse_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return FloatQuatOpsKernelsSegment.transformInverse_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    /** {@link #transformInverse(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets. <p>Valid input: as for that overload. */
    public static long transformInverse(long dest, long src, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return FloatQuatOpsKernelsAddress.transformInverse_unsafe(dest, src, v);
        transformInverse(VirtualMemoryHolder.virtualMemory().asSlice(dest, 12L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(v, 12L), 0L);
        return dest;
    }


    /**
     * Copy one FloatQuat (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 536870911 ? -1 : count * 4), src.length);
            java.util.Objects.checkFromIndexSize(destOffset, (count > 536870911 ? -1 : count * 4), dest.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.FLOAT_ARRAY_BASE + (long) srcOffset * 4L, dest, UnsafeCopy.FLOAT_ARRAY_BASE + (long) destOffset * 4L, (long) count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src[srcOffset + _i];
        return dest;
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 536870911 ? -1 : count * 4), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L, dest, UnsafeCopy.FLOAT_ARRAY_BASE + (long) destOffset * 4L, (long) count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src.get(srcOffset + _i);
        return dest;
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 536870911 ? -1 : count * 4), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, dest, UnsafeCopy.FLOAT_ARRAY_BASE + (long) destOffset * 4L, (long) count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src.getFloat(srcOffset + _i * 4);
        return dest;
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 536870911 ? -1 : count * 4), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, src.address() + srcOffset, dest, UnsafeCopy.FLOAT_ARRAY_BASE + (long) destOffset * 4L, (long) count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + (long) _i * 4L);
        return dest;
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, long src) {
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
    }

    /**
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 536870911 ? -1 : count * 4), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest, UnsafeCopy.FLOAT_ARRAY_BASE + (long) destOffset * 4L, (long) count * 16L);
            return dest;
        }
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, (long) count * 16L), 0L, count);
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 536870911 ? -1 : count * 4), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.FLOAT_ARRAY_BASE + (long) srcOffset * 4L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L, (long) count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src[srcOffset + _i]);
        return dest;
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L, (long) count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src.get(srcOffset + _i));
        return dest;
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L, (long) count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src.getFloat(srcOffset + _i * 4));
        return dest;
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isNative()) {
            UnsafeOpsHolder.U.copyMemory(null, src.address() + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L, (long) count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + (long) _i * 4L));
        return dest;
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, long src) {
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, 16L), 0L);
    }

    /**
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L, (long) count * 16L);
            return dest;
        }
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, (long) count * 16L), 0L, count);
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 536870911 ? -1 : count * 4), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.FLOAT_ARRAY_BASE + (long) srcOffset * 4L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, (long) count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.putFloat(destOffset + _i * 4, src[srcOffset + _i]);
        return dest;
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, (long) count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.putFloat(destOffset + _i * 4, src.get(srcOffset + _i));
        return dest;
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, (long) count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.putFloat(destOffset + _i * 4, src.getFloat(srcOffset + _i * 4));
        return dest;
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isNative()) {
            UnsafeOpsHolder.U.copyMemory(null, src.address() + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, (long) count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.putFloat(destOffset + _i * 4, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + (long) _i * 4L));
        return dest;
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
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
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, (long) count * 16L);
            return dest;
        }
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, (long) count * 16L), 0L, count);
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 536870911 ? -1 : count * 4), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.FLOAT_ARRAY_BASE + (long) srcOffset * 4L, null, dest.address() + destOffset, (long) count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (long) _i * 4L, src[srcOffset + _i]);
        return dest;
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L, null, dest.address() + destOffset, (long) count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (long) _i * 4L, src.get(srcOffset + _i));
        return dest;
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, dest.address() + destOffset, (long) count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (long) _i * 4L, src.getFloat(srcOffset + _i * 4));
        return dest;
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count > 536870911) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API) return FloatQuatOpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) {
            UnsafeOpsHolder.U.copyMemory(null, src.address() + srcOffset, null, dest.address() + destOffset, (long) count * 16L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 4;
        for (int _i = 0; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (long) _i * 4L, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + (long) _i * 4L));
        return dest;
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
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
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, dest.address() + destOffset, (long) count * 16L);
            return dest;
        }
        return copy(dest, destOffset, VirtualMemoryHolder.virtualMemory().asSlice(src, (long) count * 16L), 0L, count);
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
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
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, float[] src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 536870911 ? -1 : count * 4), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.FLOAT_ARRAY_BASE + (long) srcOffset * 4L, null, dest, (long) count * 16L);
            return dest;
        }
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, (long) count * 16L), 0L, src, srcOffset, count);
        return dest;
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
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
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L, null, dest, (long) count * 16L);
            return dest;
        }
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, (long) count * 16L), 0L, src, srcOffset, count);
        return dest;
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
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
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, dest, (long) count * 16L);
            return dest;
        }
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, (long) count * 16L), 0L, src, srcOffset, count);
        return dest;
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
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
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) {
            UnsafeOpsHolder.U.copyMemory(null, src.address() + srcOffset, null, dest, (long) count * 16L);
            return dest;
        }
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, (long) count * 16L), 0L, src, srcOffset, count);
        return dest;
    }

    /**
     * Copy one FloatQuat (4 floats) from {@code src}
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
     * Bulk-copy {@code count} consecutive FloatQuat values ({@code count * 4} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static long copy(long dest, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, dest, (long) count * 16L);
            return dest;
        }
        copy(VirtualMemoryHolder.virtualMemory().asSlice(dest, (long) count * 16L), 0L, VirtualMemoryHolder.virtualMemory().asSlice(src, (long) count * 16L), 0L, count);
        return dest;
    }
    /**
     * The angle between two unit quaternions a and b from s = |a+b|^2, clamped to [0, 4]:
     * 2 asin(|a-b|/2) up to pi/2 and pi - 2 asin(|a+b|/2) beyond, so asin always sees an
     * argument of at most sqrt(2)/2 and the angle stays accurate at both ends.
     */
    private static float quatArcAngle(float s) {
        float d = 4.0f - s;
        return s > d ? 2.0f * (float) Math.asin(0.5f * (float) Math.sqrt(d))
                : (float) Math.PI - 2.0f * (float) Math.asin(0.5f * (float) Math.sqrt(s));
    }

    /** Double-precision twin of {@link #quatArcAngle(float)}. */
    private static double quatArcAngle(double s) {
        double d = 4.0 - s;
        return s > d ? 2.0 * Math.asin(0.5 * Math.sqrt(d)) : Math.PI - 2.0 * Math.asin(0.5 * Math.sqrt(s));
    }
}
