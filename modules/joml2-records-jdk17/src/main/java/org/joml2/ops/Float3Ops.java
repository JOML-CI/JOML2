// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.ops;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.kernels.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Static, allocation-free operations on raw storage holding a {@link Float3}.
 *
 * <p>Each method takes one or more buffers ({@code float[]},
 * {@link java.nio.FloatBuffer}, or {@link java.nio.ByteBuffer})
 * plus an element/byte offset and operates
 * directly on that storage. No {@link Float3} instance is allocated.</p>
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
 * Elements are laid out in component order: what {@code store}/{@code load} of
 * Float3 write and read.</p>
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
 * <p>Each method summary below is the one the {@link Float3} API carries, so
 * the two can never describe the same operation differently: "this vector" there is the
 * vector held in {@code src} at {@code srcOffset}, and the result is written to
 * {@code dest} at {@code destOffset}. The full text sits on the {@code float[]} overload of
 * each method; the other storage overloads point at it, differing from it in
 * storage alone.</p>
 */
public final class Float3Ops {
    private Float3Ops() {}

    /**
     * Add ({@code otherX}, {@code otherY}, {@code otherZ}) to this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return {@code dest}
     */
    public static float[] add(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = otherX + _selfx;
        dest[destOffset + 1] = otherY + _selfy;
        dest[destOffset + 2] = otherZ + _selfz;
        return dest;
    }

    /** {@link #add(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer add(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.add_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsTypedBuffer.add_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #add(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer add(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.add_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsByteBuffer.add_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #add(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long add(long dest, long src, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.add_unsafe(dest, src, otherX, otherY, otherZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        dest[destOffset + 0] = _otherx + _selfx;
        dest[destOffset + 1] = _othery + _selfy;
        dest[destOffset + 2] = _otherz + _selfz;
        return dest;
    }

    /** {@link #add(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer add(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.add_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float3OpsKernelsTypedBuffer.add_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer add(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.add_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float3OpsKernelsByteBuffer.add_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long add(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.add_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = _selfx / scalar;
        dest[destOffset + 1] = _selfy / scalar;
        dest[destOffset + 2] = _selfz / scalar;
        return dest;
    }

    /** {@link #div(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer div(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.div_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float3OpsKernelsTypedBuffer.div_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #div(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer div(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.div_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float3OpsKernelsByteBuffer.div_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #div(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long div(long dest, long src, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.div_unsafe(dest, src, scalar);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Divide this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ}) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (otherX, otherY, otherZ)} must be non-zero.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return {@code dest}
     */
    public static float[] div(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = _selfx / otherX;
        dest[destOffset + 1] = _selfy / otherY;
        dest[destOffset + 2] = _selfz / otherZ;
        return dest;
    }

    /** {@link #div(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer div(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.div_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsTypedBuffer.div_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #div(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer div(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.div_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsByteBuffer.div_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #div(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long div(long dest, long src, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.div_unsafe(dest, src, otherX, otherY, otherZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        dest[destOffset + 0] = _selfx / _otherx;
        dest[destOffset + 1] = _selfy / _othery;
        dest[destOffset + 2] = _selfz / _otherz;
        return dest;
    }

    /** {@link #div(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer div(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.div_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float3OpsKernelsTypedBuffer.div_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #div(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer div(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.div_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float3OpsKernelsByteBuffer.div_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #div(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long div(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.div_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Multiply this vector component-wise by {@code b} and add ({@code cX}, {@code cY},
     * {@code cZ}), i.e. compute {@code this * b + (cX, cY, cZ)} per component and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param b the factor to multiply this vector by
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ)}
     * @return {@code dest}
     */
    public static float[] fma(float[] dest, int destOffset, float[] src, int srcOffset, float b, float cX, float cY, float cZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.fma(_selfx, b, cX);
        dest[destOffset + 1] = Math.fma(_selfy, b, cY);
        dest[destOffset + 2] = Math.fma(_selfz, b, cZ);
        return dest;
    }

    /** {@link #fma(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float b, float cX, float cY, float cZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.fma_unsafe(dest, destOffset, src, srcOffset, b, cX, cY, cZ);
        return Float3OpsKernelsTypedBuffer.fma_api(dest, destOffset, src, srcOffset, b, cX, cY, cZ);
    }

    /** {@link #fma(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float b, float cX, float cY, float cZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.fma_unsafe(dest, destOffset, src, srcOffset, b, cX, cY, cZ);
        return Float3OpsKernelsByteBuffer.fma_api(dest, destOffset, src, srcOffset, b, cX, cY, cZ);
    }

    /** {@link #fma(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long fma(long dest, long src, float b, float cX, float cY, float cZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.fma_unsafe(dest, src, b, cX, cY, cZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _cx = c[cOffset + 0];
        float _cy = c[cOffset + 1];
        float _cz = c[cOffset + 2];
        dest[destOffset + 0] = Math.fma(_selfx, b, _cx);
        dest[destOffset + 1] = Math.fma(_selfy, b, _cy);
        dest[destOffset + 2] = Math.fma(_selfz, b, _cz);
        return dest;
    }

    /** {@link #fma(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer c, int cOffset, float b) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && c.isDirect() && c.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.fma_unsafe(dest, destOffset, src, srcOffset, c, cOffset, b);
        return Float3OpsKernelsTypedBuffer.fma_api(dest, destOffset, src, srcOffset, c, cOffset, b);
    }

    /** {@link #fma(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer c, int cOffset, float b) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && c.isDirect() && c.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.fma_unsafe(dest, destOffset, src, srcOffset, c, cOffset, b);
        return Float3OpsKernelsByteBuffer.fma_api(dest, destOffset, src, srcOffset, c, cOffset, b);
    }

    /** {@link #fma(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long fma(long dest, long src, long c, float b) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.fma_unsafe(dest, src, c, b);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Multiply this vector component-wise by ({@code bX}, {@code bY}, {@code bZ}) and add
     * ({@code cX}, {@code cY}, {@code cZ}), i.e. compute {@code this * (bX, bY, bZ) + (cX, cY, cZ)}
     * per component and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ)}
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ)}
     * @return {@code dest}
     */
    public static float[] fma(float[] dest, int destOffset, float[] src, int srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.fma(_selfx, bX, cX);
        dest[destOffset + 1] = Math.fma(_selfy, bY, cY);
        dest[destOffset + 2] = Math.fma(_selfz, bZ, cZ);
        return dest;
    }

    /** {@link #fma(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.fma_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, cX, cY, cZ);
        return Float3OpsKernelsTypedBuffer.fma_api(dest, destOffset, src, srcOffset, bX, bY, bZ, cX, cY, cZ);
    }

    /** {@link #fma(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.fma_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, cX, cY, cZ);
        return Float3OpsKernelsByteBuffer.fma_api(dest, destOffset, src, srcOffset, bX, bY, bZ, cX, cY, cZ);
    }

    /** {@link #fma(float[], int, float[], int, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long fma(long dest, long src, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.fma_unsafe(dest, src, bX, bY, bZ, cX, cY, cZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _bx = b[bOffset + 0];
        float _by = b[bOffset + 1];
        float _bz = b[bOffset + 2];
        float _cx = c[cOffset + 0];
        float _cy = c[cOffset + 1];
        float _cz = c[cOffset + 2];
        dest[destOffset + 0] = Math.fma(_selfx, _bx, _cx);
        dest[destOffset + 1] = Math.fma(_selfy, _by, _cy);
        dest[destOffset + 2] = Math.fma(_selfz, _bz, _cz);
        return dest;
    }

    /** {@link #fma(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && b.isDirect() && b.order() == java.nio.ByteOrder.nativeOrder() && c.isDirect() && c.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.fma_unsafe(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return Float3OpsKernelsTypedBuffer.fma_api(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    /** {@link #fma(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && b.isDirect() && b.order() == java.nio.ByteOrder.nativeOrder() && c.isDirect() && c.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.fma_unsafe(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return Float3OpsKernelsByteBuffer.fma_api(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    /** {@link #fma(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long fma(long dest, long src, long b, long c) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.fma_unsafe(dest, src, b, c);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = scalar * _selfx;
        dest[destOffset + 1] = scalar * _selfy;
        dest[destOffset + 2] = scalar * _selfz;
        return dest;
    }

    /** {@link #mul(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.mul_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float3OpsKernelsTypedBuffer.mul_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.mul_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float3OpsKernelsByteBuffer.mul_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mul(long dest, long src, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.mul_unsafe(dest, src, scalar);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Multiply this vector component-wise by ({@code otherX}, {@code otherY}, {@code otherZ}) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return {@code dest}
     */
    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = otherX * _selfx;
        dest[destOffset + 1] = otherY * _selfy;
        dest[destOffset + 2] = otherZ * _selfz;
        return dest;
    }

    /** {@link #mul(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.mul_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsTypedBuffer.mul_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #mul(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.mul_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsByteBuffer.mul_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #mul(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mul(long dest, long src, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.mul_unsafe(dest, src, otherX, otherY, otherZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        dest[destOffset + 0] = _otherx * _selfx;
        dest[destOffset + 1] = _othery * _selfy;
        dest[destOffset + 2] = _otherz * _selfz;
        return dest;
    }

    /** {@link #mul(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.mul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float3OpsKernelsTypedBuffer.mul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #mul(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.mul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float3OpsKernelsByteBuffer.mul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #mul(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mul(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.mul_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = -_selfx;
        dest[destOffset + 1] = -_selfy;
        dest[destOffset + 2] = -_selfz;
        return dest;
    }

    /** {@link #negate(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer negate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.negate_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.negate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer negate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.negate_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.negate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long negate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.negate_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Subtract ({@code otherX}, {@code otherY}, {@code otherZ}) from this vector and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return {@code dest}
     */
    public static float[] sub(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = _selfx - otherX;
        dest[destOffset + 1] = _selfy - otherY;
        dest[destOffset + 2] = _selfz - otherZ;
        return dest;
    }

    /** {@link #sub(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer sub(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.sub_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsTypedBuffer.sub_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #sub(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sub(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.sub_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsByteBuffer.sub_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #sub(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long sub(long dest, long src, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.sub_unsafe(dest, src, otherX, otherY, otherZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        dest[destOffset + 0] = _selfx - _otherx;
        dest[destOffset + 1] = _selfy - _othery;
        dest[destOffset + 2] = _selfz - _otherz;
        return dest;
    }

    /** {@link #sub(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer sub(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.sub_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float3OpsKernelsTypedBuffer.sub_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sub(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.sub_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float3OpsKernelsByteBuffer.sub_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long sub(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.sub_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Set this vector to the unit vector {@code (r cos(2 PI v), r sin(2 PI v), 2u - 1)} with
     * {@code r = 2 sqrt(u (1 - u))}: samples uniformly distributed in {@code [0, 1)} give a
     * direction uniformly distributed on the unit sphere ({@code makeRandomDirection} draws them
     * from a {@link java.util.Random}).
     * <p>
     * Valid input: {@code u} must lie in {@code [0, 1]}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param u the sample that sets the height {@code z = 2u - 1}, uniformly distributed in
     *        {@code [0, 1)} for a uniformly distributed direction
     * @param v the fraction of a full turn about the z axis, counter-clockwise from the x axis,
     *        uniformly distributed in {@code [0, 1)} for a uniformly distributed direction
     * @return {@code dest}
     */
    public static float[] makeUniformDirection(float[] dest, int destOffset, float u, float v) {
        float _t1 = v * 6.2831855f;
        float _t2 = (float) Math.sin(_t1);
        float _t5 = 2.0f * (float) Math.sqrt(u * (1.0f - u));
        dest[destOffset + 0] = _t5 * (float) Math.cosFromSin(_t2, _t1);
        dest[destOffset + 1] = _t5 * _t2;
        dest[destOffset + 2] = Math.fma(2.0f, u, -1.0f);
        return dest;
    }

    /** {@link #makeUniformDirection(float[], int, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeUniformDirection(java.nio.FloatBuffer dest, int destOffset, float u, float v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.makeUniformDirection_unsafe(dest, destOffset, u, v);
        return Float3OpsKernelsTypedBuffer.makeUniformDirection_api(dest, destOffset, u, v);
    }

    /** {@link #makeUniformDirection(float[], int, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeUniformDirection(java.nio.ByteBuffer dest, int destOffset, float u, float v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.makeUniformDirection_unsafe(dest, destOffset, u, v);
        return Float3OpsKernelsByteBuffer.makeUniformDirection_api(dest, destOffset, u, v);
    }

    /** {@link #makeUniformDirection(float[], int, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long makeUniformDirection(long dest, float u, float v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.makeUniformDirection_unsafe(dest, u, v);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Set this vector to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return {@code dest}
     */
    public static float[] set(float[] dest, int destOffset, float vX, float vY, float vZ) {
        dest[destOffset + 0] = vX;
        dest[destOffset + 1] = vY;
        dest[destOffset + 2] = vZ;
        return dest;
    }

    /** {@link #set(float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer set(java.nio.FloatBuffer dest, int destOffset, float vX, float vY, float vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.set_unsafe(dest, destOffset, vX, vY, vZ);
        return Float3OpsKernelsTypedBuffer.set_api(dest, destOffset, vX, vY, vZ);
    }

    /** {@link #set(float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer set(java.nio.ByteBuffer dest, int destOffset, float vX, float vY, float vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.set_unsafe(dest, destOffset, vX, vY, vZ);
        return Float3OpsKernelsByteBuffer.set_api(dest, destOffset, vX, vY, vZ);
    }

    /** {@link #set(float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long set(long dest, float vX, float vY, float vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.set_unsafe(dest, vX, vY, vZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _vx = v[vOffset + 0];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        dest[destOffset + 0] = _vx;
        dest[destOffset + 1] = _vy;
        dest[destOffset + 2] = _vz;
        return dest;
    }

    /** {@link #set(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer set(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.set_unsafe(dest, destOffset, v, vOffset);
        return Float3OpsKernelsTypedBuffer.set_api(dest, destOffset, v, vOffset);
    }

    /** {@link #set(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer set(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.set_unsafe(dest, destOffset, v, vOffset);
        return Float3OpsKernelsByteBuffer.set_api(dest, destOffset, v, vOffset);
    }

    /** {@link #set(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long set(long dest, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.set_unsafe(dest, v);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        dest[destOffset + 0] = s;
        dest[destOffset + 1] = s;
        dest[destOffset + 2] = s;
        return dest;
    }

    /** {@link #set(float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer set(java.nio.FloatBuffer dest, int destOffset, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.set_unsafe(dest, destOffset, s);
        return Float3OpsKernelsTypedBuffer.set_api(dest, destOffset, s);
    }

    /** {@link #set(float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer set(java.nio.ByteBuffer dest, int destOffset, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.set_unsafe(dest, destOffset, s);
        return Float3OpsKernelsByteBuffer.set_api(dest, destOffset, s);
    }

    /** {@link #set(float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long set(long dest, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.set_unsafe(dest, s);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        dest[destOffset + 0] = 0.0f;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        return dest;
    }

    /** {@link #makeZero(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer makeZero(java.nio.FloatBuffer dest, int destOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.makeZero_unsafe(dest, destOffset);
        return Float3OpsKernelsTypedBuffer.makeZero_api(dest, destOffset);
    }

    /** {@link #makeZero(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeZero(java.nio.ByteBuffer dest, int destOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.makeZero_unsafe(dest, destOffset);
        return Float3OpsKernelsByteBuffer.makeZero_api(dest, destOffset);
    }

    /** {@link #makeZero(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long makeZero(long dest) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.makeZero_unsafe(dest);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points ({@code p1X}, {@code p1Y}, {@code p1Z}) and ({@code p2X}, {@code p2Y}, {@code p2Z})
     * and ends at ({@code p3X}, {@code p3Y}, {@code p3Z}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p3X}, {@code p3Y},
     * {@code p3Z}) at {@code t = 1}; the control points ({@code p1X}, {@code p1Y}, {@code p1Z}) and
     * ({@code p2X}, {@code p2Y}, {@code p2Z}) pull it towards themselves but are generally not on
     * the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param p3Z the {@code z} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] bezier(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest[destOffset + 0] = Math.fma(p1X, _t7, _selfx * _t8) + Math.fma(p2X, _t6, p3X * _t2);
        dest[destOffset + 1] = Math.fma(p1Y, _t7, _selfy * _t8) + Math.fma(p2Y, _t6, p3Y * _t2);
        dest[destOffset + 2] = Math.fma(p1Z, _t7, _selfz * _t8) + Math.fma(p2Z, _t6, p3Z * _t2);
        return dest;
    }

    /** {@link #bezier(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer bezier(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.bezier_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return Float3OpsKernelsTypedBuffer.bezier_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@link #bezier(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezier(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.bezier_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return Float3OpsKernelsByteBuffer.bezier_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@link #bezier(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long bezier(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.bezier_unsafe(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _p1x = p1[p1Offset + 0];
        float _p1y = p1[p1Offset + 1];
        float _p1z = p1[p1Offset + 2];
        float _p2x = p2[p2Offset + 0];
        float _p2y = p2[p2Offset + 1];
        float _p2z = p2[p2Offset + 2];
        float _p3x = p3[p3Offset + 0];
        float _p3y = p3[p3Offset + 1];
        float _p3z = p3[p3Offset + 2];
        float _t0 = 1.0f - t;
        float _t1 = t * t;
        float _t2 = t * _t1;
        float _t3 = _t0 * _t0;
        float _t6 = 3.0f * _t0 * _t1;
        float _t7 = 3.0f * t * _t3;
        float _t8 = _t0 * _t3;
        dest[destOffset + 0] = Math.fma(_p1x, _t7, _selfx * _t8) + Math.fma(_p2x, _t6, _p3x * _t2);
        dest[destOffset + 1] = Math.fma(_p1y, _t7, _selfy * _t8) + Math.fma(_p2y, _t6, _p3y * _t2);
        dest[destOffset + 2] = Math.fma(_p1z, _t7, _selfz * _t8) + Math.fma(_p2z, _t6, _p3z * _t2);
        return dest;
    }

    /** {@link #bezier(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer bezier(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.bezier_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Float3OpsKernelsTypedBuffer.bezier_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #bezier(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezier(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.bezier_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Float3OpsKernelsByteBuffer.bezier_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #bezier(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long bezier(long dest, long src, long p1, long p2, long p3, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.bezier_unsafe(dest, src, p1, p2, p3, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point ({@code p1X}, {@code p1Y}, {@code p1Z}) and ends at ({@code p2X}, {@code p2Y},
     * {@code p2Z}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p2X}, {@code p2Y},
     * {@code p2Z}) at {@code t = 1}; the control point ({@code p1X}, {@code p1Y}, {@code p1Z})
     * pulls it towards itself but is generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] bezier2(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest[destOffset + 0] = Math.fma(p2X, _t0, Math.fma(p1X, _t3, _selfx * _t4));
        dest[destOffset + 1] = Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4));
        dest[destOffset + 2] = Math.fma(p2Z, _t0, Math.fma(p1Z, _t3, _selfz * _t4));
        return dest;
    }

    /** {@link #bezier2(float[], int, float[], int, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer bezier2(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.bezier2_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return Float3OpsKernelsTypedBuffer.bezier2_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
    }

    /** {@link #bezier2(float[], int, float[], int, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezier2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.bezier2_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return Float3OpsKernelsByteBuffer.bezier2_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
    }

    /** {@link #bezier2(float[], int, float[], int, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long bezier2(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.bezier2_unsafe(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _p1x = p1[p1Offset + 0];
        float _p1y = p1[p1Offset + 1];
        float _p1z = p1[p1Offset + 2];
        float _p2x = p2[p2Offset + 0];
        float _p2y = p2[p2Offset + 1];
        float _p2z = p2[p2Offset + 2];
        float _t0 = t * t;
        float _t1 = 1.0f - t;
        float _t3 = (t + t) * _t1;
        float _t4 = _t1 * _t1;
        dest[destOffset + 0] = Math.fma(_p2x, _t0, Math.fma(_p1x, _t3, _selfx * _t4));
        dest[destOffset + 1] = Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4));
        dest[destOffset + 2] = Math.fma(_p2z, _t0, Math.fma(_p1z, _t3, _selfz * _t4));
        return dest;
    }

    /** {@link #bezier2(float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer bezier2(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.bezier2_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return Float3OpsKernelsTypedBuffer.bezier2_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    /** {@link #bezier2(float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezier2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.bezier2_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return Float3OpsKernelsByteBuffer.bezier2_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    /** {@link #bezier2(float[], int, float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long bezier2(long dest, long src, long p1, long p2, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.bezier2_unsafe(dest, src, p1, p2, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point
     * ({@code p1X}, {@code p1Y}, {@code p1Z}) and ends at ({@code p2X}, {@code p2Y}, {@code p2Z})
     * and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p2X}, {@code p2Y},
     * {@code p2Z}) at {@code t = 1}; the control point ({@code p1X}, {@code p1Y}, {@code p1Z})
     * pulls it towards itself but is generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] bezier2Tangent(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest[destOffset + 0] = Math.fma(p1X - _selfx, _t2, (p2X - p1X) * _t1);
        dest[destOffset + 1] = Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1);
        dest[destOffset + 2] = Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1);
        return dest;
    }

    /** {@link #bezier2Tangent(float[], int, float[], int, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer bezier2Tangent(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.bezier2Tangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return Float3OpsKernelsTypedBuffer.bezier2Tangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
    }

    /** {@link #bezier2Tangent(float[], int, float[], int, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezier2Tangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.bezier2Tangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return Float3OpsKernelsByteBuffer.bezier2Tangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
    }

    /** {@link #bezier2Tangent(float[], int, float[], int, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long bezier2Tangent(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.bezier2Tangent_unsafe(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _p1x = p1[p1Offset + 0];
        float _p1y = p1[p1Offset + 1];
        float _p1z = p1[p1Offset + 2];
        float _p2x = p2[p2Offset + 0];
        float _p2y = p2[p2Offset + 1];
        float _p2z = p2[p2Offset + 2];
        float _t1 = t + t;
        float _t2 = 2.0f * (1.0f - t);
        dest[destOffset + 0] = Math.fma(_p1x - _selfx, _t2, (_p2x - _p1x) * _t1);
        dest[destOffset + 1] = Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1);
        dest[destOffset + 2] = Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1);
        return dest;
    }

    /** {@link #bezier2Tangent(float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer bezier2Tangent(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.bezier2Tangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return Float3OpsKernelsTypedBuffer.bezier2Tangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    /** {@link #bezier2Tangent(float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezier2Tangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.bezier2Tangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return Float3OpsKernelsByteBuffer.bezier2Tangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    /** {@link #bezier2Tangent(float[], int, float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long bezier2Tangent(long dest, long src, long p1, long p2, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.bezier2Tangent_unsafe(dest, src, p1, p2, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points ({@code p1X},
     * {@code p1Y}, {@code p1Z}) and ({@code p2X}, {@code p2Y}, {@code p2Z}) and ends at
     * ({@code p3X}, {@code p3Y}, {@code p3Z}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p3X}, {@code p3Y},
     * {@code p3Z}) at {@code t = 1}; the control points ({@code p1X}, {@code p1Y}, {@code p1Z}) and
     * ({@code p2X}, {@code p2Y}, {@code p2Z}) pull it towards themselves but are generally not on
     * the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param p3Z the {@code z} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] bezierTangent(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest[destOffset + 0] = Math.fma(p3X - p2X, _t2, Math.fma(p1X - _selfx, _t6, (p2X - p1X) * _t5));
        dest[destOffset + 1] = Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5));
        dest[destOffset + 2] = Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5));
        return dest;
    }

    /** {@link #bezierTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer bezierTangent(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.bezierTangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return Float3OpsKernelsTypedBuffer.bezierTangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@link #bezierTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezierTangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.bezierTangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return Float3OpsKernelsByteBuffer.bezierTangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@link #bezierTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long bezierTangent(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.bezierTangent_unsafe(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _p1x = p1[p1Offset + 0];
        float _p1y = p1[p1Offset + 1];
        float _p1z = p1[p1Offset + 2];
        float _p2x = p2[p2Offset + 0];
        float _p2y = p2[p2Offset + 1];
        float _p2z = p2[p2Offset + 2];
        float _p3x = p3[p3Offset + 0];
        float _p3y = p3[p3Offset + 1];
        float _p3z = p3[p3Offset + 2];
        float _t1 = 1.0f - t;
        float _t2 = 3.0f * t * t;
        float _t5 = 6.0f * t * _t1;
        float _t6 = 3.0f * _t1 * _t1;
        dest[destOffset + 0] = Math.fma(_p3x - _p2x, _t2, Math.fma(_p1x - _selfx, _t6, (_p2x - _p1x) * _t5));
        dest[destOffset + 1] = Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5));
        dest[destOffset + 2] = Math.fma(_p3z - _p2z, _t2, Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5));
        return dest;
    }

    /** {@link #bezierTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer bezierTangent(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.bezierTangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Float3OpsKernelsTypedBuffer.bezierTangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #bezierTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezierTangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.bezierTangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Float3OpsKernelsByteBuffer.bezierTangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #bezierTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long bezierTangent(long dest, long src, long p1, long p2, long p3, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.bezierTangent_unsafe(dest, src, p1, p2, p3, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Interpolate along the Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z})
     * to ({@code p2X}, {@code p2Y}, {@code p2Z}), with this vector as the control point before the
     * segment and ({@code p3X}, {@code p3Y}, {@code p3Z}) as the control point after it and store
     * the result in {@code dest}.
     * <p>
     * The curve passes through ({@code p1X}, {@code p1Y}, {@code p1Z}) at {@code t = 0} and through
     * ({@code p2X}, {@code p2Y}, {@code p2Z}) at {@code t = 1}. This vector and ({@code p3X},
     * {@code p3Y}, {@code p3Z}) are the spline's neighbouring points, i.e. the point before
     * ({@code p1X}, {@code p1Y}, {@code p1Z}) and the point after ({@code p2X}, {@code p2Y},
     * {@code p2Z}): they only shape the tangents at the segment's two end points and are not
     * themselves on the segment. For a spline through the points {@code p[0..n]}, the segment from
     * {@code p[i]} to {@code p[i+1]} is therefore interpolated with {@code p[i-1]} in the role of
     * this vector and {@code p[i]}, {@code p[i+1]}, {@code p[i+2]} as the three given points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param p3Z the {@code z} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] catmullRom(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = t * t;
        float _t1 = t * _t0;
        dest[destOffset + 0] = 0.5f * (Math.fma(2.0f, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), _t0, Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)) * _t1));
        dest[destOffset + 1] = 0.5f * (Math.fma(2.0f, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), _t0, Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)) * _t1));
        dest[destOffset + 2] = 0.5f * (Math.fma(2.0f, p1Z, t * (p2Z - _selfz)) + Math.fma(Math.fma(-5.0f, p1Z, Math.fma(2.0f, _selfz, Math.fma(4.0f, p2Z, -p3Z))), _t0, Math.fma(-3.0f, p2Z, Math.fma(3.0f, p1Z, p3Z - _selfz)) * _t1));
        return dest;
    }

    /** {@link #catmullRom(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer catmullRom(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.catmullRom_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return Float3OpsKernelsTypedBuffer.catmullRom_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@link #catmullRom(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer catmullRom(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.catmullRom_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return Float3OpsKernelsByteBuffer.catmullRom_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@link #catmullRom(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long catmullRom(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.catmullRom_unsafe(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        return catmullRom(dest, destOffset, src, srcOffset, p1[p1Offset + 0], p1[p1Offset + 1], p1[p1Offset + 2], p2[p2Offset + 0], p2[p2Offset + 1], p2[p2Offset + 2], p3[p3Offset + 0], p3[p3Offset + 1], p3[p3Offset + 2], t);
    }

    /** {@link #catmullRom(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer catmullRom(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.catmullRom_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Float3OpsKernelsTypedBuffer.catmullRom_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #catmullRom(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer catmullRom(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.catmullRom_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Float3OpsKernelsByteBuffer.catmullRom_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #catmullRom(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long catmullRom(long dest, long src, long p1, long p2, long p3, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.catmullRom_unsafe(dest, src, p1, p2, p3, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z}) to ({@code p2X},
     * {@code p2Y}, {@code p2Z}), with this vector as the control point before the segment and
     * ({@code p3X}, {@code p3Y}, {@code p3Z}) as the control point after it and store the result in
     * {@code dest}.
     * <p>
     * The curve passes through ({@code p1X}, {@code p1Y}, {@code p1Z}) at {@code t = 0} and through
     * ({@code p2X}, {@code p2Y}, {@code p2Z}) at {@code t = 1}. This vector and ({@code p3X},
     * {@code p3Y}, {@code p3Z}) are the spline's neighbouring points, i.e. the point before
     * ({@code p1X}, {@code p1Y}, {@code p1Z}) and the point after ({@code p2X}, {@code p2Y},
     * {@code p2Z}): they only shape the tangents at the segment's two end points and are not
     * themselves on the segment. For a spline through the points {@code p[0..n]}, the segment from
     * {@code p[i]} to {@code p[i+1]} is therefore interpolated with {@code p[i-1]} in the role of
     * this vector and {@code p[i]}, {@code p[i+1]}, {@code p[i+2]} as the three given points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param p3Z the {@code z} component of the vector {@code (p3X, p3Y, p3Z)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] catmullRomTangent(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = t * t;
        dest[destOffset + 0] = 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1X, Math.fma(2.0f, _selfx, Math.fma(4.0f, p2X, -p3X))), Math.fma(3.0f * Math.fma(-3.0f, p2X, Math.fma(3.0f, p1X, p3X - _selfx)), _t0, p2X - _selfx));
        dest[destOffset + 1] = 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Y, Math.fma(2.0f, _selfy, Math.fma(4.0f, p2Y, -p3Y))), Math.fma(3.0f * Math.fma(-3.0f, p2Y, Math.fma(3.0f, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy));
        dest[destOffset + 2] = 0.5f * Math.fma(t, 2.0f * Math.fma(-5.0f, p1Z, Math.fma(2.0f, _selfz, Math.fma(4.0f, p2Z, -p3Z))), Math.fma(3.0f * Math.fma(-3.0f, p2Z, Math.fma(3.0f, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz));
        return dest;
    }

    /** {@link #catmullRomTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer catmullRomTangent(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.catmullRomTangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return Float3OpsKernelsTypedBuffer.catmullRomTangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@link #catmullRomTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer catmullRomTangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.catmullRomTangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return Float3OpsKernelsByteBuffer.catmullRomTangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@link #catmullRomTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long catmullRomTangent(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z, float p3X, float p3Y, float p3Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.catmullRomTangent_unsafe(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        return catmullRomTangent(dest, destOffset, src, srcOffset, p1[p1Offset + 0], p1[p1Offset + 1], p1[p1Offset + 2], p2[p2Offset + 0], p2[p2Offset + 1], p2[p2Offset + 2], p3[p3Offset + 0], p3[p3Offset + 1], p3[p3Offset + 2], t);
    }

    /** {@link #catmullRomTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer catmullRomTangent(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset, java.nio.FloatBuffer p3, int p3Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.catmullRomTangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Float3OpsKernelsTypedBuffer.catmullRomTangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #catmullRomTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer catmullRomTangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.catmullRomTangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Float3OpsKernelsByteBuffer.catmullRomTangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #catmullRomTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long catmullRomTangent(long dest, long src, long p1, long p2, long p3, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.catmullRomTangent_unsafe(dest, src, p1, p2, p3, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Interpolate along the cubic Hermite curve that starts at this vector with the tangent
     * ({@code t0X}, {@code t0Y}, {@code t0Z}) and ends at ({@code v1X}, {@code v1Y}, {@code v1Z})
     * with the tangent ({@code t1X}, {@code t1Y}, {@code t1Z}) and store the result in
     * {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code v1X}, {@code v1Y},
     * {@code v1Z}) at {@code t = 1}; the two tangents set its direction and speed at those end
     * points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param t0X the {@code x} component of the vector {@code (t0X, t0Y, t0Z)}
     * @param t0Y the {@code y} component of the vector {@code (t0X, t0Y, t0Z)}
     * @param t0Z the {@code z} component of the vector {@code (t0X, t0Y, t0Z)}
     * @param v1X the {@code x} component of the vector {@code (v1X, v1Y, v1Z)}
     * @param v1Y the {@code y} component of the vector {@code (v1X, v1Y, v1Z)}
     * @param v1Z the {@code z} component of the vector {@code (v1X, v1Y, v1Z)}
     * @param t1X the {@code x} component of the vector {@code (t1X, t1Y, t1Z)}
     * @param t1Y the {@code y} component of the vector {@code (t1X, t1Y, t1Z)}
     * @param t1Z the {@code z} component of the vector {@code (t1X, t1Y, t1Z)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] hermite(float[] dest, int destOffset, float[] src, int srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dest[destOffset + 0] = Math.fma(_selfx, _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9);
        dest[destOffset + 1] = Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9);
        dest[destOffset + 2] = Math.fma(_selfz, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9);
        return dest;
    }

    /** {@link #hermite(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer hermite(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.hermite_unsafe(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return Float3OpsKernelsTypedBuffer.hermite_api(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
    }

    /** {@link #hermite(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hermite(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.hermite_unsafe(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return Float3OpsKernelsByteBuffer.hermite_api(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
    }

    /** {@link #hermite(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long hermite(long dest, long src, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.hermite_unsafe(dest, src, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0x = t0[t0Offset + 0];
        float _t0y = t0[t0Offset + 1];
        float _t0z = t0[t0Offset + 2];
        float _v1x = v1[v1Offset + 0];
        float _v1y = v1[v1Offset + 1];
        float _v1z = v1[v1Offset + 2];
        float _t1x = t1[t1Offset + 0];
        float _t1y = t1[t1Offset + 1];
        float _t1z = t1[t1Offset + 2];
        float _t0 = t * t;
        float _t2 = t * _t0;
        float _t5 = t * Math.fma(t, t, -t);
        float _t7 = Math.fma(t - 2.0f, _t0, t);
        float _t9 = Math.fma(3.0f, _t0, -(_t2 + _t2));
        float _t10 = Math.fma(2.0f, _t2, Math.fma(-3.0f, _t0, 1.0f));
        dest[destOffset + 0] = Math.fma(_selfx, _t10, _t0x * _t7) + Math.fma(_t1x, _t5, _v1x * _t9);
        dest[destOffset + 1] = Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9);
        dest[destOffset + 2] = Math.fma(_selfz, _t10, _t0z * _t7) + Math.fma(_t1z, _t5, _v1z * _t9);
        return dest;
    }

    /** {@link #hermite(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer hermite(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && t0.isDirect() && t0.order() == java.nio.ByteOrder.nativeOrder() && v1.isDirect() && v1.order() == java.nio.ByteOrder.nativeOrder() && t1.isDirect() && t1.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.hermite_unsafe(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return Float3OpsKernelsTypedBuffer.hermite_api(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@link #hermite(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hermite(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && t0.isDirect() && t0.order() == java.nio.ByteOrder.nativeOrder() && v1.isDirect() && v1.order() == java.nio.ByteOrder.nativeOrder() && t1.isDirect() && t1.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.hermite_unsafe(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return Float3OpsKernelsByteBuffer.hermite_api(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@link #hermite(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long hermite(long dest, long src, long t0, long v1, long t1, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.hermite_unsafe(dest, src, t0, v1, t1, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Hermite curve that starts at this vector with the tangent ({@code t0X}, {@code t0Y},
     * {@code t0Z}) and ends at ({@code v1X}, {@code v1Y}, {@code v1Z}) with the tangent
     * ({@code t1X}, {@code t1Y}, {@code t1Z}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code v1X}, {@code v1Y},
     * {@code v1Z}) at {@code t = 1}; the two tangents set its direction and speed at those end
     * points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param t0X the {@code x} component of the vector {@code (t0X, t0Y, t0Z)}
     * @param t0Y the {@code y} component of the vector {@code (t0X, t0Y, t0Z)}
     * @param t0Z the {@code z} component of the vector {@code (t0X, t0Y, t0Z)}
     * @param v1X the {@code x} component of the vector {@code (v1X, v1Y, v1Z)}
     * @param v1Y the {@code y} component of the vector {@code (v1X, v1Y, v1Z)}
     * @param v1Z the {@code z} component of the vector {@code (v1X, v1Y, v1Z)}
     * @param t1X the {@code x} component of the vector {@code (t1X, t1Y, t1Z)}
     * @param t1Y the {@code y} component of the vector {@code (t1X, t1Y, t1Z)}
     * @param t1Z the {@code z} component of the vector {@code (t1X, t1Y, t1Z)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] hermiteTangent(float[] dest, int destOffset, float[] src, int srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dest[destOffset + 0] = Math.fma(_selfx, _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7);
        dest[destOffset + 1] = Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7);
        dest[destOffset + 2] = Math.fma(_selfz, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7);
        return dest;
    }

    /** {@link #hermiteTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer hermiteTangent(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.hermiteTangent_unsafe(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return Float3OpsKernelsTypedBuffer.hermiteTangent_api(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
    }

    /** {@link #hermiteTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hermiteTangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.hermiteTangent_unsafe(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return Float3OpsKernelsByteBuffer.hermiteTangent_api(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
    }

    /** {@link #hermiteTangent(float[], int, float[], int, float, float, float, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long hermiteTangent(long dest, long src, float t0X, float t0Y, float t0Z, float v1X, float v1Y, float v1Z, float t1X, float t1Y, float t1Z, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.hermiteTangent_unsafe(dest, src, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0x = t0[t0Offset + 0];
        float _t0y = t0[t0Offset + 1];
        float _t0z = t0[t0Offset + 2];
        float _v1x = v1[v1Offset + 0];
        float _v1y = v1[v1Offset + 1];
        float _v1z = v1[v1Offset + 2];
        float _t1x = t1[t1Offset + 0];
        float _t1y = t1[t1Offset + 1];
        float _t1z = t1[t1Offset + 2];
        float _t0 = t * t;
        float _t6 = 6.0f * Math.fma(t, t, -t);
        float _t7 = 6.0f * Math.fma(-t, t, t);
        float _t8 = Math.fma(3.0f, _t0, -(t + t));
        float _t9 = Math.fma(3.0f, _t0, Math.fma(-4.0f, t, 1.0f));
        dest[destOffset + 0] = Math.fma(_selfx, _t6, _t0x * _t9) + Math.fma(_t1x, _t8, _v1x * _t7);
        dest[destOffset + 1] = Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7);
        dest[destOffset + 2] = Math.fma(_selfz, _t6, _t0z * _t9) + Math.fma(_t1z, _t8, _v1z * _t7);
        return dest;
    }

    /** {@link #hermiteTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer hermiteTangent(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer t0, int t0Offset, java.nio.FloatBuffer v1, int v1Offset, java.nio.FloatBuffer t1, int t1Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && t0.isDirect() && t0.order() == java.nio.ByteOrder.nativeOrder() && v1.isDirect() && v1.order() == java.nio.ByteOrder.nativeOrder() && t1.isDirect() && t1.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.hermiteTangent_unsafe(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return Float3OpsKernelsTypedBuffer.hermiteTangent_api(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@link #hermiteTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hermiteTangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && t0.isDirect() && t0.order() == java.nio.ByteOrder.nativeOrder() && v1.isDirect() && v1.order() == java.nio.ByteOrder.nativeOrder() && t1.isDirect() && t1.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.hermiteTangent_unsafe(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return Float3OpsKernelsByteBuffer.hermiteTangent_api(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@link #hermiteTangent(float[], int, float[], int, float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long hermiteTangent(long dest, long src, long t0, long v1, long t1, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.hermiteTangent_unsafe(dest, src, t0, v1, t1, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Linearly interpolate between this vector and ({@code otherX}, {@code otherY}, {@code otherZ})
     * using the interpolation factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}, {@code otherZ}) (interpolation factor {@code 1}). Each
     * linearly interpolated component is {@code this + (other - this) * t}, as in JOML and
     * glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the
     * rounding of {@code other - this}, which shows when this component is much larger in magnitude
     * than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.fma(t, otherX - _selfx, _selfx);
        dest[destOffset + 1] = Math.fma(t, otherY - _selfy, _selfy);
        dest[destOffset + 2] = Math.fma(t, otherZ - _selfz, _selfz);
        return dest;
    }

    /** {@link #lerp(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer lerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return Float3OpsKernelsTypedBuffer.lerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    /** {@link #lerp(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return Float3OpsKernelsByteBuffer.lerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    /** {@link #lerp(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long lerp(long dest, long src, float otherX, float otherY, float otherZ, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.lerp_unsafe(dest, src, otherX, otherY, otherZ, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        dest[destOffset + 0] = Math.fma(t, _otherx - _selfx, _selfx);
        dest[destOffset + 1] = Math.fma(t, _othery - _selfy, _selfy);
        dest[destOffset + 2] = Math.fma(t, _otherz - _selfz, _selfz);
        return dest;
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer lerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float3OpsKernelsTypedBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float3OpsKernelsByteBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long lerp(long dest, long src, long other, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.lerp_unsafe(dest, src, other, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Linearly interpolate between this vector and ({@code otherX}, {@code otherY}, {@code otherZ})
     * using the interpolation factor ({@code tX}, {@code tY}, {@code tZ}) and store the result in
     * {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}, {@code otherZ}) (interpolation factor {@code 1}). Each
     * linearly interpolated component is {@code this + (other - this) * t}, as in JOML and
     * glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the
     * rounding of {@code other - this}, which shows when this component is much larger in magnitude
     * than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param tX the {@code x} component of the vector {@code (tX, tY, tZ)}
     * @param tY the {@code y} component of the vector {@code (tX, tY, tZ)}
     * @param tZ the {@code z} component of the vector {@code (tX, tY, tZ)}
     * @return {@code dest}
     */
    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float tX, float tY, float tZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.fma(tX, otherX - _selfx, _selfx);
        dest[destOffset + 1] = Math.fma(tY, otherY - _selfy, _selfy);
        dest[destOffset + 2] = Math.fma(tZ, otherZ - _selfz, _selfz);
        return dest;
    }

    /** {@link #lerp(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer lerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float tX, float tY, float tZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, tX, tY, tZ);
        return Float3OpsKernelsTypedBuffer.lerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, tX, tY, tZ);
    }

    /** {@link #lerp(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float tX, float tY, float tZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, tX, tY, tZ);
        return Float3OpsKernelsByteBuffer.lerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, tX, tY, tZ);
    }

    /** {@link #lerp(float[], int, float[], int, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long lerp(long dest, long src, float otherX, float otherY, float otherZ, float tX, float tY, float tZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.lerp_unsafe(dest, src, otherX, otherY, otherZ, tX, tY, tZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _tx = t[tOffset + 0];
        float _ty = t[tOffset + 1];
        float _tz = t[tOffset + 2];
        dest[destOffset + 0] = Math.fma(_tx, _otherx - _selfx, _selfx);
        dest[destOffset + 1] = Math.fma(_ty, _othery - _selfy, _selfy);
        dest[destOffset + 2] = Math.fma(_tz, _otherz - _selfz, _selfz);
        return dest;
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer lerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer t, int tOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder() && t.isDirect() && t.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        return Float3OpsKernelsTypedBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer t, int tOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder() && t.isDirect() && t.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        return Float3OpsKernelsByteBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
    }

    /** {@link #lerp(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long lerp(long dest, long src, long other, long t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.lerp_unsafe(dest, src, other, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Spherically interpolate between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}) using the interpolation factor {@code t}: the direction turns at a constant
     * rate along the shorter arc between the two directions, and the length changes linearly
     * between the two lengths and store the result in {@code dest}.
     * <p>
     * For unit vectors this is the usual {@code slerp} of directions. A zero vector has no
     * direction, so the result is then the linear interpolation; for two vectors pointing in
     * opposite directions, whose arc lies in no particular plane, the direction turns through a
     * perpendicular of this vector. The angle is computed with {@code atan2}, and vectors of any
     * finite length are handled: when their squared lengths leave the {@code float} range, they are
     * first scaled exactly by powers of two.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}, {@code otherZ}) (interpolation factor {@code 1}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return {@code dest}
     */
    public static float[] slerp(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t7 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        float _t8 = Math.fma(otherZ, otherZ, Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        float _t9 = (1.0f / (float) Math.sqrt(_t7));
        float _t12 = (1.0f / (float) Math.sqrt(_t8));
        float _t14 = _selfx * _t9;
        float _t17 = _selfz * _t9;
        float _t20 = _selfy * _t9;
        float _t26 = Math.fma(otherZ * _t12, _t17, Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        return slerp_s4a4dff28_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, _t14, _t17, _t20, t * (float) Math.sqrt(_t8) + (1.0f - t) * (float) Math.sqrt(_t7), _t26, Math.fma(otherZ, _t12, -(_t26 * _t17)), Math.fma(otherX, _t12, -(_t26 * _t14)), Math.fma(otherY, _t12, -(_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_s4a4dff28_1(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float t, float _t14, float _t17, float _t20, float _t24, float _t26, float _t33, float _t34, float _t35) {
        float _t39 = -Math.fma(_t33, _t17, Math.fma(_t34, _t14, _t35 * _t20));
        float _t40 = Math.fma(_t39, _t17, _t33);
        float _t41 = Math.fma(_t39, _t14, _t34);
        float _t42 = Math.fma(_t39, _t20, _t35);
        float _t46 = Math.fma(_t40, _t40, Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 1.4551915E-11f && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        float _t50 = t * (float) Math.atan2((float) Math.sqrt(_t46), _t26);
        float _sp0 = _t24 * (float) Math.sin(_t50) * (1.0f / (float) Math.sqrt(_t46));
        float _t55 = _t24 * (float) Math.cos(_t50);
        dest[destOffset + 0] = Math.fma(_t14, _t55, _sp0 * _t41);
        dest[destOffset + 1] = Math.fma(_t20, _t55, _sp0 * _t42);
        dest[destOffset + 2] = Math.fma(_t17, _t55, _sp0 * _t40);
        return dest;
    }

    /** {@link #slerp(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer slerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.slerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return Float3OpsKernelsTypedBuffer.slerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    /** {@link #slerp(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer slerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.slerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return Float3OpsKernelsByteBuffer.slerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    /** {@link #slerp(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long slerp(long dest, long src, float otherX, float otherY, float otherZ, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.slerp_unsafe(dest, src, otherX, otherY, otherZ, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Spherically interpolate between this vector and {@code other} using the interpolation factor
     * {@code t}: the direction turns at a constant rate along the shorter arc between the two
     * directions, and the length changes linearly between the two lengths and store the result in
     * {@code dest}.
     * <p>
     * For unit vectors this is the usual {@code slerp} of directions. A zero vector has no
     * direction, so the result is then the linear interpolation; for two vectors pointing in
     * opposite directions, whose arc lies in no particular plane, the direction turns through a
     * perpendicular of this vector. The angle is computed with {@code atan2}, and vectors of any
     * finite length are handled: when their squared lengths leave the {@code float} range, they are
     * first scaled exactly by powers of two.
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _t7 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t8 = Math.fma(_otherz, _otherz, Math.fma(_otherx, _otherx, _othery * _othery));
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t9 = (1.0f / (float) Math.sqrt(_t7));
        float _t12 = (1.0f / (float) Math.sqrt(_t8));
        float _t14 = _selfx * _t9;
        float _t17 = _selfz * _t9;
        float _t20 = _selfy * _t9;
        float _t26 = Math.fma(_otherz * _t12, _t17, Math.fma(_otherx * _t12, _t14, _othery * _t12 * _t20));
        return slerp_s21d687e7_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _t14, _t17, _t20, t * (float) Math.sqrt(_t8) + (1.0f - t) * (float) Math.sqrt(_t7), _t26, Math.fma(_otherz, _t12, -(_t26 * _t17)), Math.fma(_otherx, _t12, -(_t26 * _t14)), Math.fma(_othery, _t12, -(_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private static float[] slerp_s21d687e7_1(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t, float _t14, float _t17, float _t20, float _t24, float _t26, float _t33, float _t34, float _t35) {
        float _t39 = -Math.fma(_t33, _t17, Math.fma(_t34, _t14, _t35 * _t20));
        float _t40 = Math.fma(_t39, _t17, _t33);
        float _t41 = Math.fma(_t39, _t14, _t34);
        float _t42 = Math.fma(_t39, _t20, _t35);
        float _t46 = Math.fma(_t40, _t40, Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 1.4551915E-11f && _t46 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        float _t50 = t * (float) Math.atan2((float) Math.sqrt(_t46), _t26);
        float _sp0 = _t24 * (float) Math.sin(_t50) * (1.0f / (float) Math.sqrt(_t46));
        float _t55 = _t24 * (float) Math.cos(_t50);
        dest[destOffset + 0] = Math.fma(_t14, _t55, _sp0 * _t41);
        dest[destOffset + 1] = Math.fma(_t20, _t55, _sp0 * _t42);
        dest[destOffset + 2] = Math.fma(_t17, _t55, _sp0 * _t40);
        return dest;
    }

    /** {@link #slerp(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer slerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.slerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float3OpsKernelsTypedBuffer.slerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #slerp(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer slerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.slerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Float3OpsKernelsByteBuffer.slerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #slerp(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long slerp(long dest, long src, long other, float t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.slerp_unsafe(dest, src, other, t);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.abs(_selfx);
        dest[destOffset + 1] = Math.abs(_selfy);
        dest[destOffset + 2] = Math.abs(_selfz);
        return dest;
    }

    /** {@link #absolute(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer absolute(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.absolute_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.absolute_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #absolute(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer absolute(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.absolute_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.absolute_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #absolute(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long absolute(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.absolute_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.acos(_selfx);
        dest[destOffset + 1] = (float) Math.acos(_selfy);
        dest[destOffset + 2] = (float) Math.acos(_selfz);
        return dest;
    }

    /** {@link #acos(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer acos(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.acos_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.acos_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #acos(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer acos(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.acos_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.acos_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #acos(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long acos(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.acos_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Add ({@code bX}, {@code bY}, {@code bZ}) scaled by {@code scalar} to this vector and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ)}
     * @param scalar the factor to scale ({@code bX}, {@code bY}, {@code bZ}) by before adding
     * @return {@code dest}
     */
    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float bX, float bY, float bZ, float scalar) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.fma(scalar, bX, _selfx);
        dest[destOffset + 1] = Math.fma(scalar, bY, _selfy);
        dest[destOffset + 2] = Math.fma(scalar, bZ, _selfz);
        return dest;
    }

    /** {@link #addScaled(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer addScaled(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, scalar);
        return Float3OpsKernelsTypedBuffer.addScaled_api(dest, destOffset, src, srcOffset, bX, bY, bZ, scalar);
    }

    /** {@link #addScaled(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer addScaled(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float bZ, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, scalar);
        return Float3OpsKernelsByteBuffer.addScaled_api(dest, destOffset, src, srcOffset, bX, bY, bZ, scalar);
    }

    /** {@link #addScaled(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long addScaled(long dest, long src, float bX, float bY, float bZ, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.addScaled_unsafe(dest, src, bX, bY, bZ, scalar);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _bx = b[bOffset + 0];
        float _by = b[bOffset + 1];
        float _bz = b[bOffset + 2];
        dest[destOffset + 0] = Math.fma(scalar, _bx, _selfx);
        dest[destOffset + 1] = Math.fma(scalar, _by, _selfy);
        dest[destOffset + 2] = Math.fma(scalar, _bz, _selfz);
        return dest;
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer addScaled(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && b.isDirect() && b.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        return Float3OpsKernelsTypedBuffer.addScaled_api(dest, destOffset, src, srcOffset, b, bOffset, scalar);
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer addScaled(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && b.isDirect() && b.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        return Float3OpsKernelsByteBuffer.addScaled_api(dest, destOffset, src, srcOffset, b, bOffset, scalar);
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long addScaled(long dest, long src, long b, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.addScaled_unsafe(dest, src, b, scalar);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Add ({@code bX}, {@code bY}, {@code bZ}) scaled by ({@code cX}, {@code cY}, {@code cZ}) to
     * this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ)}
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ)}
     * @return {@code dest}
     */
    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.fma(bX, cX, _selfx);
        dest[destOffset + 1] = Math.fma(bY, cY, _selfy);
        dest[destOffset + 2] = Math.fma(bZ, cZ, _selfz);
        return dest;
    }

    /** {@link #addScaled(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer addScaled(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, cX, cY, cZ);
        return Float3OpsKernelsTypedBuffer.addScaled_api(dest, destOffset, src, srcOffset, bX, bY, bZ, cX, cY, cZ);
    }

    /** {@link #addScaled(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer addScaled(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, cX, cY, cZ);
        return Float3OpsKernelsByteBuffer.addScaled_api(dest, destOffset, src, srcOffset, bX, bY, bZ, cX, cY, cZ);
    }

    /** {@link #addScaled(float[], int, float[], int, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long addScaled(long dest, long src, float bX, float bY, float bZ, float cX, float cY, float cZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.addScaled_unsafe(dest, src, bX, bY, bZ, cX, cY, cZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _bx = b[bOffset + 0];
        float _by = b[bOffset + 1];
        float _bz = b[bOffset + 2];
        float _cx = c[cOffset + 0];
        float _cy = c[cOffset + 1];
        float _cz = c[cOffset + 2];
        dest[destOffset + 0] = Math.fma(_bx, _cx, _selfx);
        dest[destOffset + 1] = Math.fma(_by, _cy, _selfy);
        dest[destOffset + 2] = Math.fma(_bz, _cz, _selfz);
        return dest;
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer addScaled(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer b, int bOffset, java.nio.FloatBuffer c, int cOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && b.isDirect() && b.order() == java.nio.ByteOrder.nativeOrder() && c.isDirect() && c.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return Float3OpsKernelsTypedBuffer.addScaled_api(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer addScaled(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && b.isDirect() && b.order() == java.nio.ByteOrder.nativeOrder() && c.isDirect() && c.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return Float3OpsKernelsByteBuffer.addScaled_api(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    /** {@link #addScaled(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long addScaled(long dest, long src, long b, long c) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.addScaled_unsafe(dest, src, b, c);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the angle in radians between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
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
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the angle in radians between this vector and {@code other}
     */
    public static float angleBetween(float[] src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t6 = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        float _t7 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        float _t8 = Math.fma(otherZ, _selfx, -(otherX * _selfz));
        float _ct0 = Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsArray.angleBetween_degenerate(src, srcOffset, otherX, otherY, otherZ);
        return (float) Math.atan2((float) Math.sqrt(_ct0), Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy)));
    }

    /** {@link #angleBetween(float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float angleBetween(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.angleBetween_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsTypedBuffer.angleBetween_api(src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #angleBetween(float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float angleBetween(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.angleBetween_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsByteBuffer.angleBetween_api(src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #angleBetween(float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float angleBetween(long src, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.angleBetween_unsafe(src, otherX, otherY, otherZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _t6 = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        float _t7 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        float _t8 = Math.fma(_otherz, _selfx, -(_otherx * _selfz));
        float _ct0 = Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsArray.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return (float) Math.atan2((float) Math.sqrt(_ct0), Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy)));
    }

    /** {@link #angleBetween(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float angleBetween(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.angleBetween_unsafe(src, srcOffset, other, otherOffset);
        return Float3OpsKernelsTypedBuffer.angleBetween_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #angleBetween(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float angleBetween(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.angleBetween_unsafe(src, srcOffset, other, otherOffset);
        return Float3OpsKernelsByteBuffer.angleBetween_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #angleBetween(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float angleBetween(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.angleBetween_unsafe(src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.asin(_selfx);
        dest[destOffset + 1] = (float) Math.asin(_selfy);
        dest[destOffset + 2] = (float) Math.asin(_selfz);
        return dest;
    }

    /** {@link #asin(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer asin(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.asin_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.asin_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #asin(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer asin(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.asin_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.asin_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #asin(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long asin(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.asin_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.atan(_selfx);
        dest[destOffset + 1] = (float) Math.atan(_selfy);
        dest[destOffset + 2] = (float) Math.atan(_selfz);
        return dest;
    }

    /** {@link #atan(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer atan(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.atan_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.atan_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #atan(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer atan(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.atan_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.atan_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #atan(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long atan(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.atan_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.atan2(_selfx, x);
        dest[destOffset + 1] = (float) Math.atan2(_selfy, x);
        dest[destOffset + 2] = (float) Math.atan2(_selfz, x);
        return dest;
    }

    /** {@link #atan2(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer atan2(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float x) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.atan2_unsafe(dest, destOffset, src, srcOffset, x);
        return Float3OpsKernelsTypedBuffer.atan2_api(dest, destOffset, src, srcOffset, x);
    }

    /** {@link #atan2(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer atan2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float x) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.atan2_unsafe(dest, destOffset, src, srcOffset, x);
        return Float3OpsKernelsByteBuffer.atan2_api(dest, destOffset, src, srcOffset, x);
    }

    /** {@link #atan2(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long atan2(long dest, long src, float x) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.atan2_unsafe(dest, src, x);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} the corresponding component of ({@code xX},
     * {@code xY}, {@code xZ}) (the denominator) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param xX the {@code x} component of the vector {@code (xX, xY, xZ)}
     * @param xY the {@code y} component of the vector {@code (xX, xY, xZ)}
     * @param xZ the {@code z} component of the vector {@code (xX, xY, xZ)}
     * @return {@code dest}
     */
    public static float[] atan2(float[] dest, int destOffset, float[] src, int srcOffset, float xX, float xY, float xZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.atan2(_selfx, xX);
        dest[destOffset + 1] = (float) Math.atan2(_selfy, xY);
        dest[destOffset + 2] = (float) Math.atan2(_selfz, xZ);
        return dest;
    }

    /** {@link #atan2(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer atan2(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float xX, float xY, float xZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.atan2_unsafe(dest, destOffset, src, srcOffset, xX, xY, xZ);
        return Float3OpsKernelsTypedBuffer.atan2_api(dest, destOffset, src, srcOffset, xX, xY, xZ);
    }

    /** {@link #atan2(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer atan2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float xX, float xY, float xZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.atan2_unsafe(dest, destOffset, src, srcOffset, xX, xY, xZ);
        return Float3OpsKernelsByteBuffer.atan2_api(dest, destOffset, src, srcOffset, xX, xY, xZ);
    }

    /** {@link #atan2(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long atan2(long dest, long src, float xX, float xY, float xZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.atan2_unsafe(dest, src, xX, xY, xZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _xx = x[xOffset + 0];
        float _xy = x[xOffset + 1];
        float _xz = x[xOffset + 2];
        dest[destOffset + 0] = (float) Math.atan2(_selfx, _xx);
        dest[destOffset + 1] = (float) Math.atan2(_selfy, _xy);
        dest[destOffset + 2] = (float) Math.atan2(_selfz, _xz);
        return dest;
    }

    /** {@link #atan2(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer atan2(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer x, int xOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && x.isDirect() && x.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.atan2_unsafe(dest, destOffset, src, srcOffset, x, xOffset);
        return Float3OpsKernelsTypedBuffer.atan2_api(dest, destOffset, src, srcOffset, x, xOffset);
    }

    /** {@link #atan2(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer atan2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer x, int xOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && x.isDirect() && x.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.atan2_unsafe(dest, destOffset, src, srcOffset, x, xOffset);
        return Float3OpsKernelsByteBuffer.atan2_api(dest, destOffset, src, srcOffset, x, xOffset);
    }

    /** {@link #atan2(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long atan2(long dest, long src, long x) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.atan2_unsafe(dest, src, x);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.cbrt(_selfx);
        dest[destOffset + 1] = (float) Math.cbrt(_selfy);
        dest[destOffset + 2] = (float) Math.cbrt(_selfz);
        return dest;
    }

    /** {@link #cbrt(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer cbrt(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.cbrt_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.cbrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cbrt(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer cbrt(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.cbrt_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.cbrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cbrt(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long cbrt(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.cbrt_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.ceil(_selfx);
        dest[destOffset + 1] = (float) Math.ceil(_selfy);
        dest[destOffset + 2] = (float) Math.ceil(_selfz);
        return dest;
    }

    /** {@link #ceil(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer ceil(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.ceil_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.ceil_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #ceil(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer ceil(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.ceil_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.ceil_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #ceil(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long ceil(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.ceil_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.min(Math.max(_selfx, min), max);
        dest[destOffset + 1] = Math.min(Math.max(_selfy, min), max);
        dest[destOffset + 2] = Math.min(Math.max(_selfz, min), max);
        return dest;
    }

    /** {@link #clamp(float[], int, float[], int, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer clamp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float min, float max) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.clamp_unsafe(dest, destOffset, src, srcOffset, min, max);
        return Float3OpsKernelsTypedBuffer.clamp_api(dest, destOffset, src, srcOffset, min, max);
    }

    /** {@link #clamp(float[], int, float[], int, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer clamp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float min, float max) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.clamp_unsafe(dest, destOffset, src, srcOffset, min, max);
        return Float3OpsKernelsByteBuffer.clamp_api(dest, destOffset, src, srcOffset, min, max);
    }

    /** {@link #clamp(float[], int, float[], int, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long clamp(long dest, long src, float min, float max) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.clamp_unsafe(dest, src, min, max);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ}) and
     * ({@code maxX}, {@code maxY}, {@code maxZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any
     * component.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param minX the {@code x} component of the vector {@code (minX, minY, minZ)}
     * @param minY the {@code y} component of the vector {@code (minX, minY, minZ)}
     * @param minZ the {@code z} component of the vector {@code (minX, minY, minZ)}
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY, maxZ)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY, maxZ)}
     * @param maxZ the {@code z} component of the vector {@code (maxX, maxY, maxZ)}
     * @return {@code dest}
     */
    public static float[] clamp(float[] dest, int destOffset, float[] src, int srcOffset, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.min(Math.max(_selfx, minX), maxX);
        dest[destOffset + 1] = Math.min(Math.max(_selfy, minY), maxY);
        dest[destOffset + 2] = Math.min(Math.max(_selfz, minZ), maxZ);
        return dest;
    }

    /** {@link #clamp(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer clamp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.clamp_unsafe(dest, destOffset, src, srcOffset, minX, minY, minZ, maxX, maxY, maxZ);
        return Float3OpsKernelsTypedBuffer.clamp_api(dest, destOffset, src, srcOffset, minX, minY, minZ, maxX, maxY, maxZ);
    }

    /** {@link #clamp(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer clamp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.clamp_unsafe(dest, destOffset, src, srcOffset, minX, minY, minZ, maxX, maxY, maxZ);
        return Float3OpsKernelsByteBuffer.clamp_api(dest, destOffset, src, srcOffset, minX, minY, minZ, maxX, maxY, maxZ);
    }

    /** {@link #clamp(float[], int, float[], int, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long clamp(long dest, long src, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.clamp_unsafe(dest, src, minX, minY, minZ, maxX, maxY, maxZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _minx = min[minOffset + 0];
        float _miny = min[minOffset + 1];
        float _minz = min[minOffset + 2];
        float _maxx = max[maxOffset + 0];
        float _maxy = max[maxOffset + 1];
        float _maxz = max[maxOffset + 2];
        dest[destOffset + 0] = Math.min(Math.max(_selfx, _minx), _maxx);
        dest[destOffset + 1] = Math.min(Math.max(_selfy, _miny), _maxy);
        dest[destOffset + 2] = Math.min(Math.max(_selfz, _minz), _maxz);
        return dest;
    }

    /** {@link #clamp(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer clamp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer min, int minOffset, java.nio.FloatBuffer max, int maxOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && min.isDirect() && min.order() == java.nio.ByteOrder.nativeOrder() && max.isDirect() && max.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.clamp_unsafe(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
        return Float3OpsKernelsTypedBuffer.clamp_api(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
    }

    /** {@link #clamp(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer clamp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer min, int minOffset, java.nio.ByteBuffer max, int maxOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && min.isDirect() && min.order() == java.nio.ByteOrder.nativeOrder() && max.isDirect() && max.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.clamp_unsafe(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
        return Float3OpsKernelsByteBuffer.clamp_api(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
    }

    /** {@link #clamp(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long clamp(long dest, long src, long min, long max) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.clamp_unsafe(dest, src, min, max);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the point on the line segment between ({@code lineStartX}, {@code lineStartY},
     * {@code lineStartZ}) and ({@code lineEndX}, {@code lineEndY}, {@code lineEndZ}) that is
     * closest to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param lineStartX the {@code x} component of the vector
     *        {@code (lineStartX, lineStartY, lineStartZ)}
     * @param lineStartY the {@code y} component of the vector
     *        {@code (lineStartX, lineStartY, lineStartZ)}
     * @param lineStartZ the {@code z} component of the vector
     *        {@code (lineStartX, lineStartY, lineStartZ)}
     * @param lineEndX the {@code x} component of the vector {@code (lineEndX, lineEndY, lineEndZ)}
     * @param lineEndY the {@code y} component of the vector {@code (lineEndX, lineEndY, lineEndZ)}
     * @param lineEndZ the {@code z} component of the vector {@code (lineEndX, lineEndY, lineEndZ)}
     * @return {@code dest}
     */
    public static float[] closestPointOnLine(float[] dest, int destOffset, float[] src, int srcOffset, float lineStartX, float lineStartY, float lineStartZ, float lineEndX, float lineEndY, float lineEndZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = lineEndZ - lineStartZ;
        float _t1 = lineEndX - lineStartX;
        float _t2 = lineEndY - lineStartY;
        float _t10 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t14 = Math.max(0.0f, Math.min(1.0f, Math.fma(_t0, _selfz - lineStartZ, Math.fma(_t1, _selfx - lineStartX, _t2 * (_selfy - lineStartY))) / _t10));
        if (_t10 > 0.0f) {
            dest[destOffset + 0] = Math.fma(_t1, _t14, lineStartX);
            dest[destOffset + 1] = Math.fma(_t2, _t14, lineStartY);
            dest[destOffset + 2] = Math.fma(_t0, _t14, lineStartZ);
        } else {
            dest[destOffset + 0] = lineStartX;
            dest[destOffset + 1] = lineStartY;
            dest[destOffset + 2] = lineStartZ;
        }
        return dest;
    }

    /** {@link #closestPointOnLine(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer closestPointOnLine(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float lineStartX, float lineStartY, float lineStartZ, float lineEndX, float lineEndY, float lineEndZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.closestPointOnLine_unsafe(dest, destOffset, src, srcOffset, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
        return Float3OpsKernelsTypedBuffer.closestPointOnLine_api(dest, destOffset, src, srcOffset, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
    }

    /** {@link #closestPointOnLine(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer closestPointOnLine(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float lineStartX, float lineStartY, float lineStartZ, float lineEndX, float lineEndY, float lineEndZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.closestPointOnLine_unsafe(dest, destOffset, src, srcOffset, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
        return Float3OpsKernelsByteBuffer.closestPointOnLine_api(dest, destOffset, src, srcOffset, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
    }

    /** {@link #closestPointOnLine(float[], int, float[], int, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long closestPointOnLine(long dest, long src, float lineStartX, float lineStartY, float lineStartZ, float lineEndX, float lineEndY, float lineEndZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.closestPointOnLine_unsafe(dest, src, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the point on the line segment between {@code lineStart} and {@code lineEnd} that is
     * closest to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param lineStart the storage holding the start point of the line segment
     * @param lineStartOffset the element index in {@code lineStart} at which the vector starts
     * @param lineEnd the storage holding the end point of the line segment
     * @param lineEndOffset the element index in {@code lineEnd} at which the vector starts
     * @return {@code dest}
     */
    public static float[] closestPointOnLine(float[] dest, int destOffset, float[] src, int srcOffset, float[] lineStart, int lineStartOffset, float[] lineEnd, int lineEndOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _lineStartx = lineStart[lineStartOffset + 0];
        float _lineStarty = lineStart[lineStartOffset + 1];
        float _lineStartz = lineStart[lineStartOffset + 2];
        float _lineEndx = lineEnd[lineEndOffset + 0];
        float _lineEndy = lineEnd[lineEndOffset + 1];
        float _lineEndz = lineEnd[lineEndOffset + 2];
        float _t0 = _lineEndz - _lineStartz;
        float _t1 = _lineEndx - _lineStartx;
        float _t2 = _lineEndy - _lineStarty;
        float _t10 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t14 = Math.max(0.0f, Math.min(1.0f, Math.fma(_t0, _selfz - _lineStartz, Math.fma(_t1, _selfx - _lineStartx, _t2 * (_selfy - _lineStarty))) / _t10));
        if (_t10 > 0.0f) {
            dest[destOffset + 0] = Math.fma(_t1, _t14, _lineStartx);
            dest[destOffset + 1] = Math.fma(_t2, _t14, _lineStarty);
            dest[destOffset + 2] = Math.fma(_t0, _t14, _lineStartz);
        } else {
            dest[destOffset + 0] = _lineStartx;
            dest[destOffset + 1] = _lineStarty;
            dest[destOffset + 2] = _lineStartz;
        }
        return dest;
    }

    /** {@link #closestPointOnLine(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer closestPointOnLine(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer lineStart, int lineStartOffset, java.nio.FloatBuffer lineEnd, int lineEndOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && lineStart.isDirect() && lineStart.order() == java.nio.ByteOrder.nativeOrder() && lineEnd.isDirect() && lineEnd.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.closestPointOnLine_unsafe(dest, destOffset, src, srcOffset, lineStart, lineStartOffset, lineEnd, lineEndOffset);
        return Float3OpsKernelsTypedBuffer.closestPointOnLine_api(dest, destOffset, src, srcOffset, lineStart, lineStartOffset, lineEnd, lineEndOffset);
    }

    /** {@link #closestPointOnLine(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer closestPointOnLine(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer lineStart, int lineStartOffset, java.nio.ByteBuffer lineEnd, int lineEndOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && lineStart.isDirect() && lineStart.order() == java.nio.ByteOrder.nativeOrder() && lineEnd.isDirect() && lineEnd.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.closestPointOnLine_unsafe(dest, destOffset, src, srcOffset, lineStart, lineStartOffset, lineEnd, lineEndOffset);
        return Float3OpsKernelsByteBuffer.closestPointOnLine_api(dest, destOffset, src, srcOffset, lineStart, lineStartOffset, lineEnd, lineEndOffset);
    }

    /** {@link #closestPointOnLine(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long closestPointOnLine(long dest, long src, long lineStart, long lineEnd) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.closestPointOnLine_unsafe(dest, src, lineStart, lineEnd);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        return _selfz + (_selfx + _selfy);
    }

    /** {@link #compAdd(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float compAdd(java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.compAdd_unsafe(src, srcOffset);
        return Float3OpsKernelsTypedBuffer.compAdd_api(src, srcOffset);
    }

    /** {@link #compAdd(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float compAdd(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.compAdd_unsafe(src, srcOffset);
        return Float3OpsKernelsByteBuffer.compAdd_api(src, srcOffset);
    }

    /** {@link #compAdd(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float compAdd(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.compAdd_unsafe(src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        return Math.max(Math.max(_selfx, _selfy), _selfz);
    }

    /** {@link #compMax(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float compMax(java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.compMax_unsafe(src, srcOffset);
        return Float3OpsKernelsTypedBuffer.compMax_api(src, srcOffset);
    }

    /** {@link #compMax(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float compMax(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.compMax_unsafe(src, srcOffset);
        return Float3OpsKernelsByteBuffer.compMax_api(src, srcOffset);
    }

    /** {@link #compMax(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float compMax(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.compMax_unsafe(src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        return Math.min(Math.min(_selfx, _selfy), _selfz);
    }

    /** {@link #compMin(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float compMin(java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.compMin_unsafe(src, srcOffset);
        return Float3OpsKernelsTypedBuffer.compMin_api(src, srcOffset);
    }

    /** {@link #compMin(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float compMin(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.compMin_unsafe(src, srcOffset);
        return Float3OpsKernelsByteBuffer.compMin_api(src, srcOffset);
    }

    /** {@link #compMin(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float compMin(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.compMin_unsafe(src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        return _selfz * _selfx * _selfy;
    }

    /** {@link #compMul(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float compMul(java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.compMul_unsafe(src, srcOffset);
        return Float3OpsKernelsTypedBuffer.compMul_api(src, srcOffset);
    }

    /** {@link #compMul(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float compMul(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.compMul_unsafe(src, srcOffset);
        return Float3OpsKernelsByteBuffer.compMul_api(src, srcOffset);
    }

    /** {@link #compMul(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float compMul(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.compMul_unsafe(src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.copySign(_selfx, sign);
        dest[destOffset + 1] = Math.copySign(_selfy, sign);
        dest[destOffset + 2] = Math.copySign(_selfz, sign);
        return dest;
    }

    /** {@link #copySign(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer copySign(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float sign) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.copySign_unsafe(dest, destOffset, src, srcOffset, sign);
        return Float3OpsKernelsTypedBuffer.copySign_api(dest, destOffset, src, srcOffset, sign);
    }

    /** {@link #copySign(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer copySign(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float sign) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.copySign_unsafe(dest, destOffset, src, srcOffset, sign);
        return Float3OpsKernelsByteBuffer.copySign_api(dest, destOffset, src, srcOffset, sign);
    }

    /** {@link #copySign(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long copySign(long dest, long src, float sign) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.copySign_unsafe(dest, src, sign);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy the sign of each component of ({@code signX}, {@code signY}, {@code signZ}) onto the
     * corresponding component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param signX the {@code x} component of the vector {@code (signX, signY, signZ)}
     * @param signY the {@code y} component of the vector {@code (signX, signY, signZ)}
     * @param signZ the {@code z} component of the vector {@code (signX, signY, signZ)}
     * @return {@code dest}
     */
    public static float[] copySign(float[] dest, int destOffset, float[] src, int srcOffset, float signX, float signY, float signZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.copySign(_selfx, signX);
        dest[destOffset + 1] = Math.copySign(_selfy, signY);
        dest[destOffset + 2] = Math.copySign(_selfz, signZ);
        return dest;
    }

    /** {@link #copySign(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer copySign(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float signX, float signY, float signZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.copySign_unsafe(dest, destOffset, src, srcOffset, signX, signY, signZ);
        return Float3OpsKernelsTypedBuffer.copySign_api(dest, destOffset, src, srcOffset, signX, signY, signZ);
    }

    /** {@link #copySign(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer copySign(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float signX, float signY, float signZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.copySign_unsafe(dest, destOffset, src, srcOffset, signX, signY, signZ);
        return Float3OpsKernelsByteBuffer.copySign_api(dest, destOffset, src, srcOffset, signX, signY, signZ);
    }

    /** {@link #copySign(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long copySign(long dest, long src, float signX, float signY, float signZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.copySign_unsafe(dest, src, signX, signY, signZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _signx = sign[signOffset + 0];
        float _signy = sign[signOffset + 1];
        float _signz = sign[signOffset + 2];
        dest[destOffset + 0] = Math.copySign(_selfx, _signx);
        dest[destOffset + 1] = Math.copySign(_selfy, _signy);
        dest[destOffset + 2] = Math.copySign(_selfz, _signz);
        return dest;
    }

    /** {@link #copySign(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer copySign(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer sign, int signOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && sign.isDirect() && sign.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.copySign_unsafe(dest, destOffset, src, srcOffset, sign, signOffset);
        return Float3OpsKernelsTypedBuffer.copySign_api(dest, destOffset, src, srcOffset, sign, signOffset);
    }

    /** {@link #copySign(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer copySign(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer sign, int signOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && sign.isDirect() && sign.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.copySign_unsafe(dest, destOffset, src, srcOffset, sign, signOffset);
        return Float3OpsKernelsByteBuffer.copySign_api(dest, destOffset, src, srcOffset, sign, signOffset);
    }

    /** {@link #copySign(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long copySign(long dest, long src, long sign) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.copySign_unsafe(dest, src, sign);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.cos(_selfx);
        dest[destOffset + 1] = (float) Math.cos(_selfy);
        dest[destOffset + 2] = (float) Math.cos(_selfz);
        return dest;
    }

    /** {@link #cos(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer cos(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.cos_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.cos_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cos(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer cos(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.cos_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.cos_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cos(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long cos(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.cos_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.cosh(_selfx);
        dest[destOffset + 1] = (float) Math.cosh(_selfy);
        dest[destOffset + 2] = (float) Math.cosh(_selfz);
        return dest;
    }

    /** {@link #cosh(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer cosh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.cosh_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.cosh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cosh(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer cosh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.cosh_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.cosh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cosh(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long cosh(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.cosh_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the cross product of this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), in that order ({@code this x (otherX, otherY, otherZ)}) and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return {@code dest}
     */
    public static float[] cross(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        dest[destOffset + 1] = Math.fma(otherX, _selfz, -(otherZ * _selfx));
        dest[destOffset + 2] = Math.fma(otherY, _selfx, -(otherX * _selfy));
        return dest;
    }

    /** {@link #cross(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer cross(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.cross_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsTypedBuffer.cross_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #cross(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer cross(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.cross_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsByteBuffer.cross_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #cross(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long cross(long dest, long src, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.cross_unsafe(dest, src, otherX, otherY, otherZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the cross product of this vector and {@code other}, in that order
     * ({@code this x other}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param other the storage holding the right operand of the cross product
     * @param otherOffset the element index in {@code other} at which the vector starts
     * @return {@code dest}
     */
    public static float[] cross(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        dest[destOffset + 0] = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        dest[destOffset + 1] = Math.fma(_otherx, _selfz, -(_otherz * _selfx));
        dest[destOffset + 2] = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        return dest;
    }

    /** {@link #cross(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer cross(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.cross_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float3OpsKernelsTypedBuffer.cross_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #cross(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer cross(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.cross_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float3OpsKernelsByteBuffer.cross_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #cross(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long cross(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.cross_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.toDegrees(_selfx);
        dest[destOffset + 1] = (float) Math.toDegrees(_selfy);
        dest[destOffset + 2] = (float) Math.toDegrees(_selfz);
        return dest;
    }

    /** {@link #degrees(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer degrees(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.degrees_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.degrees_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #degrees(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer degrees(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.degrees_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.degrees_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #degrees(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long degrees(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.degrees_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the distance between this vector and {@code other}
     */
    public static float distance(float[] src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = _selfz - otherZ;
        float _t1 = _selfx - otherX;
        float _t2 = _selfy - otherY;
        return (float) Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2)));
    }

    /** {@link #distance(float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float distance(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.distance_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsTypedBuffer.distance_api(src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #distance(float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float distance(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.distance_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsByteBuffer.distance_api(src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #distance(float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float distance(long src, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.distance_unsafe(src, otherX, otherY, otherZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _t0 = _selfz - _otherz;
        float _t1 = _selfx - _otherx;
        float _t2 = _selfy - _othery;
        return (float) Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2)));
    }

    /** {@link #distance(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float distance(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.distance_unsafe(src, srcOffset, other, otherOffset);
        return Float3OpsKernelsTypedBuffer.distance_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #distance(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float distance(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.distance_unsafe(src, srcOffset, other, otherOffset);
        return Float3OpsKernelsByteBuffer.distance_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #distance(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float distance(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.distance_unsafe(src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the squared distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the squared distance between this vector and {@code other}
     */
    public static float distanceSquared(float[] src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = _selfz - otherZ;
        float _t1 = _selfx - otherX;
        float _t2 = _selfy - otherY;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
    }

    /** {@link #distanceSquared(float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float distanceSquared(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.distanceSquared_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsTypedBuffer.distanceSquared_api(src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #distanceSquared(float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float distanceSquared(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.distanceSquared_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsByteBuffer.distanceSquared_api(src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #distanceSquared(float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float distanceSquared(long src, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.distanceSquared_unsafe(src, otherX, otherY, otherZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _t0 = _selfz - _otherz;
        float _t1 = _selfx - _otherx;
        float _t2 = _selfy - _othery;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
    }

    /** {@link #distanceSquared(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float distanceSquared(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.distanceSquared_unsafe(src, srcOffset, other, otherOffset);
        return Float3OpsKernelsTypedBuffer.distanceSquared_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #distanceSquared(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float distanceSquared(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.distanceSquared_unsafe(src, srcOffset, other, otherOffset);
        return Float3OpsKernelsByteBuffer.distanceSquared_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #distanceSquared(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float distanceSquared(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.distanceSquared_unsafe(src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the dot product of this vector and ({@code otherX}, {@code otherY}, {@code otherZ}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the dot product of this vector and {@code other}
     */
    public static float dot(float[] src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        return Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy));
    }

    /** {@link #dot(float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float dot(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.dot_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsTypedBuffer.dot_api(src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #dot(float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float dot(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.dot_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsByteBuffer.dot_api(src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #dot(float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float dot(long src, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.dot_unsafe(src, otherX, otherY, otherZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        return Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy));
    }

    /** {@link #dot(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float dot(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.dot_unsafe(src, srcOffset, other, otherOffset);
        return Float3OpsKernelsTypedBuffer.dot_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #dot(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float dot(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.dot_unsafe(src, srcOffset, other, otherOffset);
        return Float3OpsKernelsByteBuffer.dot_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #dot(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float dot(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.dot_unsafe(src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.exp(_selfx);
        dest[destOffset + 1] = (float) Math.exp(_selfy);
        dest[destOffset + 2] = (float) Math.exp(_selfz);
        return dest;
    }

    /** {@link #exp(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer exp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.exp_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.exp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #exp(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer exp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.exp_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.exp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #exp(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long exp(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.exp_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.pow(2.0f, _selfx);
        dest[destOffset + 1] = (float) Math.pow(2.0f, _selfy);
        dest[destOffset + 2] = (float) Math.pow(2.0f, _selfz);
        return dest;
    }

    /** {@link #exp2(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer exp2(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.exp2_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.exp2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #exp2(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer exp2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.exp2_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.exp2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #exp2(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long exp2(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.exp2_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.expm1(_selfx);
        dest[destOffset + 1] = (float) Math.expm1(_selfy);
        dest[destOffset + 2] = (float) Math.expm1(_selfz);
        return dest;
    }

    /** {@link #expm1(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer expm1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.expm1_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.expm1_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #expm1(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer expm1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.expm1_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.expm1_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #expm1(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long expm1(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.expm1_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Return this vector unchanged when {@code dot((NrefX, NrefY, NrefZ), (IX, IY, IZ))} is
     * negative, and negated otherwise - orienting it against the incident direction ({@code IX},
     * {@code IY}, {@code IZ}) as judged by the reference vector ({@code NrefX}, {@code NrefY},
     * {@code NrefZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param IX the {@code x} component of the vector {@code (IX, IY, IZ)}
     * @param IY the {@code y} component of the vector {@code (IX, IY, IZ)}
     * @param IZ the {@code z} component of the vector {@code (IX, IY, IZ)}
     * @param NrefX the {@code x} component of the vector {@code (NrefX, NrefY, NrefZ)}
     * @param NrefY the {@code y} component of the vector {@code (NrefX, NrefY, NrefZ)}
     * @param NrefZ the {@code z} component of the vector {@code (NrefX, NrefY, NrefZ)}
     * @return {@code dest}
     */
    public static float[] faceforward(float[] dest, int destOffset, float[] src, int srcOffset, float IX, float IY, float IZ, float NrefX, float NrefY, float NrefZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        if (Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY)) < 0.0f) {
            dest[destOffset + 0] = _selfx;
            dest[destOffset + 1] = _selfy;
            dest[destOffset + 2] = _selfz;
        } else {
            dest[destOffset + 0] = -_selfx;
            dest[destOffset + 1] = -_selfy;
            dest[destOffset + 2] = -_selfz;
        }
        return dest;
    }

    /** {@link #faceforward(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer faceforward(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float IX, float IY, float IZ, float NrefX, float NrefY, float NrefZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.faceforward_unsafe(dest, destOffset, src, srcOffset, IX, IY, IZ, NrefX, NrefY, NrefZ);
        return Float3OpsKernelsTypedBuffer.faceforward_api(dest, destOffset, src, srcOffset, IX, IY, IZ, NrefX, NrefY, NrefZ);
    }

    /** {@link #faceforward(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer faceforward(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float IX, float IY, float IZ, float NrefX, float NrefY, float NrefZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.faceforward_unsafe(dest, destOffset, src, srcOffset, IX, IY, IZ, NrefX, NrefY, NrefZ);
        return Float3OpsKernelsByteBuffer.faceforward_api(dest, destOffset, src, srcOffset, IX, IY, IZ, NrefX, NrefY, NrefZ);
    }

    /** {@link #faceforward(float[], int, float[], int, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long faceforward(long dest, long src, float IX, float IY, float IZ, float NrefX, float NrefY, float NrefZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.faceforward_unsafe(dest, src, IX, IY, IZ, NrefX, NrefY, NrefZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _Ix = I[IOffset + 0];
        float _Iy = I[IOffset + 1];
        float _Iz = I[IOffset + 2];
        float _Nrefx = Nref[NrefOffset + 0];
        float _Nrefy = Nref[NrefOffset + 1];
        float _Nrefz = Nref[NrefOffset + 2];
        if (Math.fma(_Iz, _Nrefz, Math.fma(_Ix, _Nrefx, _Iy * _Nrefy)) < 0.0f) {
            dest[destOffset + 0] = _selfx;
            dest[destOffset + 1] = _selfy;
            dest[destOffset + 2] = _selfz;
        } else {
            dest[destOffset + 0] = -_selfx;
            dest[destOffset + 1] = -_selfy;
            dest[destOffset + 2] = -_selfz;
        }
        return dest;
    }

    /** {@link #faceforward(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer faceforward(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer I, int IOffset, java.nio.FloatBuffer Nref, int NrefOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && I.isDirect() && I.order() == java.nio.ByteOrder.nativeOrder() && Nref.isDirect() && Nref.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.faceforward_unsafe(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        return Float3OpsKernelsTypedBuffer.faceforward_api(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
    }

    /** {@link #faceforward(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer faceforward(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer I, int IOffset, java.nio.ByteBuffer Nref, int NrefOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && I.isDirect() && I.order() == java.nio.ByteOrder.nativeOrder() && Nref.isDirect() && Nref.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.faceforward_unsafe(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        return Float3OpsKernelsByteBuffer.faceforward_api(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
    }

    /** {@link #faceforward(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long faceforward(long dest, long src, long I, long Nref) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.faceforward_unsafe(dest, src, I, Nref);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.floor(_selfx);
        dest[destOffset + 1] = (float) Math.floor(_selfy);
        dest[destOffset + 2] = (float) Math.floor(_selfz);
        return dest;
    }

    /** {@link #floor(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer floor(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.floor_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.floor_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #floor(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer floor(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.floor_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.floor_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #floor(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long floor(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.floor_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.min(_selfx - (float) Math.floor(_selfx), 0.99999994f);
        dest[destOffset + 1] = Math.min(_selfy - (float) Math.floor(_selfy), 0.99999994f);
        dest[destOffset + 2] = Math.min(_selfz - (float) Math.floor(_selfz), 0.99999994f);
        return dest;
    }

    /** {@link #fract(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer fract(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.fract_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.fract_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #fract(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer fract(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.fract_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.fract_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #fract(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long fract(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.fract_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.hypot(_selfx, y);
        dest[destOffset + 1] = (float) Math.hypot(_selfy, y);
        dest[destOffset + 2] = (float) Math.hypot(_selfz, y);
        return dest;
    }

    /** {@link #hypot(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer hypot(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.hypot_unsafe(dest, destOffset, src, srcOffset, y);
        return Float3OpsKernelsTypedBuffer.hypot_api(dest, destOffset, src, srcOffset, y);
    }

    /** {@link #hypot(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hypot(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.hypot_unsafe(dest, destOffset, src, srcOffset, y);
        return Float3OpsKernelsByteBuffer.hypot_api(dest, destOffset, src, srcOffset, y);
    }

    /** {@link #hypot(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long hypot(long dest, long src, float y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.hypot_unsafe(dest, src, y);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} the corresponding component of ({@code yX}, {@code yY},
     * {@code yZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param yX the {@code x} component of the vector {@code (yX, yY, yZ)}
     * @param yY the {@code y} component of the vector {@code (yX, yY, yZ)}
     * @param yZ the {@code z} component of the vector {@code (yX, yY, yZ)}
     * @return {@code dest}
     */
    public static float[] hypot(float[] dest, int destOffset, float[] src, int srcOffset, float yX, float yY, float yZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.hypot(_selfx, yX);
        dest[destOffset + 1] = (float) Math.hypot(_selfy, yY);
        dest[destOffset + 2] = (float) Math.hypot(_selfz, yZ);
        return dest;
    }

    /** {@link #hypot(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer hypot(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.hypot_unsafe(dest, destOffset, src, srcOffset, yX, yY, yZ);
        return Float3OpsKernelsTypedBuffer.hypot_api(dest, destOffset, src, srcOffset, yX, yY, yZ);
    }

    /** {@link #hypot(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hypot(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float yX, float yY, float yZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.hypot_unsafe(dest, destOffset, src, srcOffset, yX, yY, yZ);
        return Float3OpsKernelsByteBuffer.hypot_api(dest, destOffset, src, srcOffset, yX, yY, yZ);
    }

    /** {@link #hypot(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long hypot(long dest, long src, float yX, float yY, float yZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.hypot_unsafe(dest, src, yX, yY, yZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _yx = y[yOffset + 0];
        float _yy = y[yOffset + 1];
        float _yz = y[yOffset + 2];
        dest[destOffset + 0] = (float) Math.hypot(_selfx, _yx);
        dest[destOffset + 1] = (float) Math.hypot(_selfy, _yy);
        dest[destOffset + 2] = (float) Math.hypot(_selfz, _yz);
        return dest;
    }

    /** {@link #hypot(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer hypot(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && y.isDirect() && y.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.hypot_unsafe(dest, destOffset, src, srcOffset, y, yOffset);
        return Float3OpsKernelsTypedBuffer.hypot_api(dest, destOffset, src, srcOffset, y, yOffset);
    }

    /** {@link #hypot(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hypot(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && y.isDirect() && y.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.hypot_unsafe(dest, destOffset, src, srcOffset, y, yOffset);
        return Float3OpsKernelsByteBuffer.hypot_api(dest, destOffset, src, srcOffset, y, yOffset);
    }

    /** {@link #hypot(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long hypot(long dest, long src, long y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.hypot_unsafe(dest, src, y);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = 1.0f / _selfx;
        dest[destOffset + 1] = 1.0f / _selfy;
        dest[destOffset + 2] = 1.0f / _selfz;
        return dest;
    }

    /** {@link #inverse(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer inverse(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.inverse_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.inverse_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #inverse(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer inverse(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.inverse_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.inverse_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #inverse(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long inverse(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.inverse_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (1.0f / (float) Math.sqrt(_selfx));
        dest[destOffset + 1] = (1.0f / (float) Math.sqrt(_selfy));
        dest[destOffset + 2] = (1.0f / (float) Math.sqrt(_selfz));
        return dest;
    }

    /** {@link #inverseSqrt(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer inverseSqrt(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.inverseSqrt_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.inverseSqrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #inverseSqrt(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer inverseSqrt(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.inverseSqrt_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.inverseSqrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #inverseSqrt(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long inverseSqrt(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.inverseSqrt_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        return (float) Math.sqrt(Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
    }

    /** {@link #length(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float length(java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.length_unsafe(src, srcOffset);
        return Float3OpsKernelsTypedBuffer.length_api(src, srcOffset);
    }

    /** {@link #length(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float length(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.length_unsafe(src, srcOffset);
        return Float3OpsKernelsByteBuffer.length_api(src, srcOffset);
    }

    /** {@link #length(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float length(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.length_unsafe(src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        return Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
    }

    /** {@link #lengthSquared(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float lengthSquared(java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.lengthSquared_unsafe(src, srcOffset);
        return Float3OpsKernelsTypedBuffer.lengthSquared_api(src, srcOffset);
    }

    /** {@link #lengthSquared(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float lengthSquared(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.lengthSquared_unsafe(src, srcOffset);
        return Float3OpsKernelsByteBuffer.lengthSquared_api(src, srcOffset);
    }

    /** {@link #lengthSquared(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float lengthSquared(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.lengthSquared_unsafe(src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.log(_selfx);
        dest[destOffset + 1] = (float) Math.log(_selfy);
        dest[destOffset + 2] = (float) Math.log(_selfz);
        return dest;
    }

    /** {@link #log(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer log(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.log_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.log_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer log(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.log_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.log_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long log(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.log_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.log10(_selfx);
        dest[destOffset + 1] = (float) Math.log10(_selfy);
        dest[destOffset + 2] = (float) Math.log10(_selfz);
        return dest;
    }

    /** {@link #log10(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer log10(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.log10_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.log10_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log10(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer log10(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.log10_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.log10_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log10(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long log10(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.log10_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.log1p(_selfx);
        dest[destOffset + 1] = (float) Math.log1p(_selfy);
        dest[destOffset + 2] = (float) Math.log1p(_selfz);
        return dest;
    }

    /** {@link #log1p(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer log1p(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.log1p_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.log1p_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log1p(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer log1p(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.log1p_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.log1p_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log1p(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long log1p(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.log1p_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.log2(_selfx);
        dest[destOffset + 1] = (float) Math.log2(_selfy);
        dest[destOffset + 2] = (float) Math.log2(_selfz);
        return dest;
    }

    /** {@link #log2(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer log2(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.log2_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.log2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log2(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer log2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.log2_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.log2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log2(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long log2(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.log2_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the Manhattan distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the Manhattan distance between this vector and {@code other}
     */
    public static float manhattanDistance(float[] src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        return Math.abs(_selfx - otherX) + Math.abs(_selfy - otherY) + Math.abs(_selfz - otherZ);
    }

    /** {@link #manhattanDistance(float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float manhattanDistance(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.manhattanDistance_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsTypedBuffer.manhattanDistance_api(src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #manhattanDistance(float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float manhattanDistance(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.manhattanDistance_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsByteBuffer.manhattanDistance_api(src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #manhattanDistance(float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float manhattanDistance(long src, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.manhattanDistance_unsafe(src, otherX, otherY, otherZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        return Math.abs(_selfx - _otherx) + Math.abs(_selfy - _othery) + Math.abs(_selfz - _otherz);
    }

    /** {@link #manhattanDistance(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float manhattanDistance(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.manhattanDistance_unsafe(src, srcOffset, other, otherOffset);
        return Float3OpsKernelsTypedBuffer.manhattanDistance_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #manhattanDistance(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float manhattanDistance(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.manhattanDistance_unsafe(src, srcOffset, other, otherOffset);
        return Float3OpsKernelsByteBuffer.manhattanDistance_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #manhattanDistance(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float manhattanDistance(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.manhattanDistance_unsafe(src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        return Math.abs(_selfx) + Math.abs(_selfy) + Math.abs(_selfz);
    }

    /** {@link #manhattanLength(float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float manhattanLength(java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.manhattanLength_unsafe(src, srcOffset);
        return Float3OpsKernelsTypedBuffer.manhattanLength_api(src, srcOffset);
    }

    /** {@link #manhattanLength(float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float manhattanLength(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.manhattanLength_unsafe(src, srcOffset);
        return Float3OpsKernelsByteBuffer.manhattanLength_api(src, srcOffset);
    }

    /** {@link #manhattanLength(float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float manhattanLength(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.manhattanLength_unsafe(src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.max(_selfx, scalar);
        dest[destOffset + 1] = Math.max(_selfy, scalar);
        dest[destOffset + 2] = Math.max(_selfz, scalar);
        return dest;
    }

    /** {@link #max(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer max(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.max_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float3OpsKernelsTypedBuffer.max_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #max(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer max(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.max_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float3OpsKernelsByteBuffer.max_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #max(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long max(long dest, long src, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.max_unsafe(dest, src, scalar);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return {@code dest}
     */
    public static float[] max(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.max(_selfx, otherX);
        dest[destOffset + 1] = Math.max(_selfy, otherY);
        dest[destOffset + 2] = Math.max(_selfz, otherZ);
        return dest;
    }

    /** {@link #max(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer max(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.max_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsTypedBuffer.max_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #max(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer max(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.max_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsByteBuffer.max_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #max(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long max(long dest, long src, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.max_unsafe(dest, src, otherX, otherY, otherZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        dest[destOffset + 0] = Math.max(_selfx, _otherx);
        dest[destOffset + 1] = Math.max(_selfy, _othery);
        dest[destOffset + 2] = Math.max(_selfz, _otherz);
        return dest;
    }

    /** {@link #max(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer max(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.max_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float3OpsKernelsTypedBuffer.max_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #max(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer max(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.max_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float3OpsKernelsByteBuffer.max_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #max(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long max(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.max_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.min(_selfx, scalar);
        dest[destOffset + 1] = Math.min(_selfy, scalar);
        dest[destOffset + 2] = Math.min(_selfz, scalar);
        return dest;
    }

    /** {@link #min(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer min(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.min_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float3OpsKernelsTypedBuffer.min_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #min(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer min(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.min_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Float3OpsKernelsByteBuffer.min_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #min(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long min(long dest, long src, float scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.min_unsafe(dest, src, scalar);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code otherX}, {@code otherY}, {@code otherZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return {@code dest}
     */
    public static float[] min(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.min(_selfx, otherX);
        dest[destOffset + 1] = Math.min(_selfy, otherY);
        dest[destOffset + 2] = Math.min(_selfz, otherZ);
        return dest;
    }

    /** {@link #min(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer min(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.min_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsTypedBuffer.min_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #min(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer min(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.min_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Float3OpsKernelsByteBuffer.min_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #min(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long min(long dest, long src, float otherX, float otherY, float otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.min_unsafe(dest, src, otherX, otherY, otherZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        dest[destOffset + 0] = Math.min(_selfx, _otherx);
        dest[destOffset + 1] = Math.min(_selfy, _othery);
        dest[destOffset + 2] = Math.min(_selfz, _otherz);
        return dest;
    }

    /** {@link #min(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer min(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.min_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float3OpsKernelsTypedBuffer.min_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #min(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer min(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.min_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Float3OpsKernelsByteBuffer.min_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #min(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long min(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.min_unsafe(dest, src, other);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = flooredMod(_selfx, y);
        dest[destOffset + 1] = flooredMod(_selfy, y);
        dest[destOffset + 2] = flooredMod(_selfz, y);
        return dest;
    }

    /** {@link #mod(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mod(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.mod_unsafe(dest, destOffset, src, srcOffset, y);
        return Float3OpsKernelsTypedBuffer.mod_api(dest, destOffset, src, srcOffset, y);
    }

    /** {@link #mod(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mod(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.mod_unsafe(dest, destOffset, src, srcOffset, y);
        return Float3OpsKernelsByteBuffer.mod_api(dest, destOffset, src, srcOffset, y);
    }

    /** {@link #mod(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mod(long dest, long src, float y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.mod_unsafe(dest, src, y);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the component-wise floored modulo of this vector divided by ({@code yX}, {@code yY},
     * {@code yZ}) ({@code x % y}, plus {@code y} when that remainder is non-zero and its sign
     * differs from {@code y}'s - exactly Kotlin's {@code mod}) and store the result in
     * {@code dest}.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * Valid input: each component of {@code (yX, yY, yZ)} must be non-zero.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param yX the {@code x} component of the vector {@code (yX, yY, yZ)}
     * @param yY the {@code y} component of the vector {@code (yX, yY, yZ)}
     * @param yZ the {@code z} component of the vector {@code (yX, yY, yZ)}
     * @return {@code dest}
     */
    public static float[] mod(float[] dest, int destOffset, float[] src, int srcOffset, float yX, float yY, float yZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = flooredMod(_selfx, yX);
        dest[destOffset + 1] = flooredMod(_selfy, yY);
        dest[destOffset + 2] = flooredMod(_selfz, yZ);
        return dest;
    }

    /** {@link #mod(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mod(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float yX, float yY, float yZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.mod_unsafe(dest, destOffset, src, srcOffset, yX, yY, yZ);
        return Float3OpsKernelsTypedBuffer.mod_api(dest, destOffset, src, srcOffset, yX, yY, yZ);
    }

    /** {@link #mod(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mod(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float yX, float yY, float yZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.mod_unsafe(dest, destOffset, src, srcOffset, yX, yY, yZ);
        return Float3OpsKernelsByteBuffer.mod_api(dest, destOffset, src, srcOffset, yX, yY, yZ);
    }

    /** {@link #mod(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mod(long dest, long src, float yX, float yY, float yZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.mod_unsafe(dest, src, yX, yY, yZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _yx = y[yOffset + 0];
        float _yy = y[yOffset + 1];
        float _yz = y[yOffset + 2];
        dest[destOffset + 0] = flooredMod(_selfx, _yx);
        dest[destOffset + 1] = flooredMod(_selfy, _yy);
        dest[destOffset + 2] = flooredMod(_selfz, _yz);
        return dest;
    }

    /** {@link #mod(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer mod(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer y, int yOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && y.isDirect() && y.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.mod_unsafe(dest, destOffset, src, srcOffset, y, yOffset);
        return Float3OpsKernelsTypedBuffer.mod_api(dest, destOffset, src, srcOffset, y, yOffset);
    }

    /** {@link #mod(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mod(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && y.isDirect() && y.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.mod_unsafe(dest, destOffset, src, srcOffset, y, yOffset);
        return Float3OpsKernelsByteBuffer.mod_api(dest, destOffset, src, srcOffset, y, yOffset);
    }

    /** {@link #mod(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mod(long dest, long src, long y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.mod_unsafe(dest, src, y);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.nextDown(_selfx);
        dest[destOffset + 1] = Math.nextDown(_selfy);
        dest[destOffset + 2] = Math.nextDown(_selfz);
        return dest;
    }

    /** {@link #nextDown(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer nextDown(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.nextDown_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.nextDown_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #nextDown(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer nextDown(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.nextDown_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.nextDown_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #nextDown(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long nextDown(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.nextDown_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.nextUp(_selfx);
        dest[destOffset + 1] = Math.nextUp(_selfy);
        dest[destOffset + 2] = Math.nextUp(_selfz);
        return dest;
    }

    /** {@link #nextUp(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer nextUp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.nextUp_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.nextUp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #nextUp(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer nextUp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.nextUp_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.nextUp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #nextUp(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long nextUp(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.nextUp_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t3 = (1.0f / (float) Math.sqrt(_t2));
        if (_t2 != 0.0f) {
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

    /** {@link #normalize(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer normalize(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.normalize_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.normalize_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalize(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer normalize(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.normalize_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.normalize_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalize(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long normalize(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.normalize_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t4 = length * (1.0f / (float) Math.sqrt(_t2));
        if (_t2 != 0.0f) {
            dest[destOffset + 0] = _selfx * _t4;
            dest[destOffset + 1] = _selfy * _t4;
            dest[destOffset + 2] = _selfz * _t4;
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    /** {@link #normalizeMul(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer normalizeMul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float length) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.normalizeMul_unsafe(dest, destOffset, src, srcOffset, length);
        return Float3OpsKernelsTypedBuffer.normalizeMul_api(dest, destOffset, src, srcOffset, length);
    }

    /** {@link #normalizeMul(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer normalizeMul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float length) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.normalizeMul_unsafe(dest, destOffset, src, srcOffset, length);
        return Float3OpsKernelsByteBuffer.normalizeMul_api(dest, destOffset, src, srcOffset, length);
    }

    /** {@link #normalizeMul(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long normalizeMul(long dest, long src, float length) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.normalizeMul_unsafe(dest, src, length);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the signed angle in radians between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), positive when the rotation from this vector to ({@code otherX},
     * {@code otherY}, {@code otherZ}) is counter-clockwise as seen from the direction of the given
     * normal.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code float} range, the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: {@code (normalX, normalY, normalZ)} must be non-zero.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @param normalX the {@code x} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalY the {@code y} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalZ the {@code z} component of the vector {@code (normalX, normalY, normalZ)}
     * @return the signed angle in radians between this vector and {@code other}, positive when the
     *        rotation from this vector to {@code other} is counter-clockwise as seen from the
     *        direction of the given normal
     */
    public static float orientedAngle(float[] src, int srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t7 = unitScale(normalX, normalY, normalZ);
        float _t9 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        float _t10 = Math.fma(otherX, _selfz, -(otherZ * _selfx));
        float _t11 = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        float _ct0 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsArray.orientedAngle_degenerate(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        float _t18 = (float) Math.atan2((float) Math.sqrt(_ct0), Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy)));
        return Math.fma(_t9, normalZ * _t7, Math.fma(_t10, normalY * _t7, _t11 * (normalX * _t7))) < 0.0f ? -_t18 : _t18;
    }

    /** {@link #orientedAngle(float[], int, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float orientedAngle(java.nio.FloatBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.orientedAngle_unsafe(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return Float3OpsKernelsTypedBuffer.orientedAngle_api(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    /** {@link #orientedAngle(float[], int, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float orientedAngle(java.nio.ByteBuffer src, int srcOffset, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.orientedAngle_unsafe(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return Float3OpsKernelsByteBuffer.orientedAngle_api(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    /** {@link #orientedAngle(float[], int, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float orientedAngle(long src, float otherX, float otherY, float otherZ, float normalX, float normalY, float normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.orientedAngle_unsafe(src, otherX, otherY, otherZ, normalX, normalY, normalZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the signed angle in radians between this vector and {@code other}, positive when the
     * rotation from this vector to {@code other} is counter-clockwise as seen from the direction of
     * the given normal.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code float} range, the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: {@code normal} must be non-zero.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param other the storage holding the vector to measure the signed angle to
     * @param otherOffset the element index in {@code other} at which the vector starts
     * @param normal the storage holding the reference axis that defines the sign of the angle
     * @param normalOffset the element index in {@code normal} at which the vector starts
     * @return the signed angle in radians between this vector and {@code other}, positive when the
     *        rotation from this vector to {@code other} is counter-clockwise as seen from the
     *        direction of the given normal
     */
    public static float orientedAngle(float[] src, int srcOffset, float[] other, int otherOffset, float[] normal, int normalOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _otherx = other[otherOffset + 0];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _normalx = normal[normalOffset + 0];
        float _normaly = normal[normalOffset + 1];
        float _normalz = normal[normalOffset + 2];
        float _t7 = unitScale(_normalx, _normaly, _normalz);
        float _t9 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        float _t10 = Math.fma(_otherx, _selfz, -(_otherz * _selfx));
        float _t11 = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        float _ct0 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsArray.orientedAngle_degenerate(src, srcOffset, other, otherOffset, normal, normalOffset);
        float _t18 = (float) Math.atan2((float) Math.sqrt(_ct0), Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy)));
        return Math.fma(_t9, _normalz * _t7, Math.fma(_t10, _normaly * _t7, _t11 * (_normalx * _t7))) < 0.0f ? -_t18 : _t18;
    }

    /** {@link #orientedAngle(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static float orientedAngle(java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer other, int otherOffset, java.nio.FloatBuffer normal, int normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.orientedAngle_unsafe(src, srcOffset, other, otherOffset, normal, normalOffset);
        return Float3OpsKernelsTypedBuffer.orientedAngle_api(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    /** {@link #orientedAngle(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static float orientedAngle(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer normal, int normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.orientedAngle_unsafe(src, srcOffset, other, otherOffset, normal, normalOffset);
        return Float3OpsKernelsByteBuffer.orientedAngle_api(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    /** {@link #orientedAngle(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static float orientedAngle(long src, long other, long normal) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.orientedAngle_unsafe(src, other, normal);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the outer product of this vector and ({@code rowX}, {@code rowY}, {@code rowZ}) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the matrix starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param rowX the {@code x} component of the vector {@code (rowX, rowY, rowZ)}
     * @param rowY the {@code y} component of the vector {@code (rowX, rowY, rowZ)}
     * @param rowZ the {@code z} component of the vector {@code (rowX, rowY, rowZ)}
     * @return {@code dest}
     */
    public static float[] outerProduct(float[] dest, int destOffset, float[] src, int srcOffset, float rowX, float rowY, float rowZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = rowX * _selfx;
        dest[destOffset + 1] = rowX * _selfy;
        dest[destOffset + 2] = rowX * _selfz;
        dest[destOffset + 3] = rowY * _selfx;
        dest[destOffset + 4] = rowY * _selfy;
        dest[destOffset + 5] = rowY * _selfz;
        dest[destOffset + 6] = rowZ * _selfx;
        dest[destOffset + 7] = rowZ * _selfy;
        dest[destOffset + 8] = rowZ * _selfz;
        return dest;
    }

    /** {@link #outerProduct(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer outerProduct(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float rowX, float rowY, float rowZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.outerProduct_unsafe(dest, destOffset, src, srcOffset, rowX, rowY, rowZ);
        return Float3OpsKernelsTypedBuffer.outerProduct_api(dest, destOffset, src, srcOffset, rowX, rowY, rowZ);
    }

    /** {@link #outerProduct(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer outerProduct(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float rowX, float rowY, float rowZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.outerProduct_unsafe(dest, destOffset, src, srcOffset, rowX, rowY, rowZ);
        return Float3OpsKernelsByteBuffer.outerProduct_api(dest, destOffset, src, srcOffset, rowX, rowY, rowZ);
    }

    /** {@link #outerProduct(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long outerProduct(long dest, long src, float rowX, float rowY, float rowZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.outerProduct_unsafe(dest, src, rowX, rowY, rowZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _rowx = row[rowOffset + 0];
        float _rowy = row[rowOffset + 1];
        float _rowz = row[rowOffset + 2];
        dest[destOffset + 0] = _rowx * _selfx;
        dest[destOffset + 1] = _rowx * _selfy;
        dest[destOffset + 2] = _rowx * _selfz;
        dest[destOffset + 3] = _rowy * _selfx;
        dest[destOffset + 4] = _rowy * _selfy;
        dest[destOffset + 5] = _rowy * _selfz;
        dest[destOffset + 6] = _rowz * _selfx;
        dest[destOffset + 7] = _rowz * _selfy;
        dest[destOffset + 8] = _rowz * _selfz;
        return dest;
    }

    /** {@link #outerProduct(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer outerProduct(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer row, int rowOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && row.isDirect() && row.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.outerProduct_unsafe(dest, destOffset, src, srcOffset, row, rowOffset);
        return Float3OpsKernelsTypedBuffer.outerProduct_api(dest, destOffset, src, srcOffset, row, rowOffset);
    }

    /** {@link #outerProduct(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer outerProduct(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer row, int rowOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && row.isDirect() && row.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.outerProduct_unsafe(dest, destOffset, src, srcOffset, row, rowOffset);
        return Float3OpsKernelsByteBuffer.outerProduct_api(dest, destOffset, src, srcOffset, row, rowOffset);
    }

    /** {@link #outerProduct(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long outerProduct(long dest, long src, long row) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.outerProduct_unsafe(dest, src, row);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute a vector perpendicular to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] perpendicular(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        if (Math.abs(_selfz) < Math.abs(_selfx)) {
            dest[destOffset + 0] = _selfy;
            dest[destOffset + 1] = -_selfx;
            dest[destOffset + 2] = 0.0f;
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = _selfz;
            dest[destOffset + 2] = -_selfy;
        }
        return dest;
    }

    /** {@link #perpendicular(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer perpendicular(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.perpendicular_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.perpendicular_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #perpendicular(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer perpendicular(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.perpendicular_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.perpendicular_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #perpendicular(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long perpendicular(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.perpendicular_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.pow(_selfx, exponent);
        dest[destOffset + 1] = (float) Math.pow(_selfy, exponent);
        dest[destOffset + 2] = (float) Math.pow(_selfz, exponent);
        return dest;
    }

    /** {@link #pow(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer pow(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponent) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.pow_unsafe(dest, destOffset, src, srcOffset, exponent);
        return Float3OpsKernelsTypedBuffer.pow_api(dest, destOffset, src, srcOffset, exponent);
    }

    /** {@link #pow(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer pow(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float exponent) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.pow_unsafe(dest, destOffset, src, srcOffset, exponent);
        return Float3OpsKernelsByteBuffer.pow_api(dest, destOffset, src, srcOffset, exponent);
    }

    /** {@link #pow(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long pow(long dest, long src, float exponent) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.pow_unsafe(dest, src, exponent);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Raise each component of this vector to the power of ({@code exponentX}, {@code exponentY},
     * {@code exponentZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param exponentX the {@code x} component of the vector
     *        {@code (exponentX, exponentY, exponentZ)}
     * @param exponentY the {@code y} component of the vector
     *        {@code (exponentX, exponentY, exponentZ)}
     * @param exponentZ the {@code z} component of the vector
     *        {@code (exponentX, exponentY, exponentZ)}
     * @return {@code dest}
     */
    public static float[] pow(float[] dest, int destOffset, float[] src, int srcOffset, float exponentX, float exponentY, float exponentZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.pow(_selfx, exponentX);
        dest[destOffset + 1] = (float) Math.pow(_selfy, exponentY);
        dest[destOffset + 2] = (float) Math.pow(_selfz, exponentZ);
        return dest;
    }

    /** {@link #pow(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer pow(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float exponentX, float exponentY, float exponentZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.pow_unsafe(dest, destOffset, src, srcOffset, exponentX, exponentY, exponentZ);
        return Float3OpsKernelsTypedBuffer.pow_api(dest, destOffset, src, srcOffset, exponentX, exponentY, exponentZ);
    }

    /** {@link #pow(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer pow(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float exponentX, float exponentY, float exponentZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.pow_unsafe(dest, destOffset, src, srcOffset, exponentX, exponentY, exponentZ);
        return Float3OpsKernelsByteBuffer.pow_api(dest, destOffset, src, srcOffset, exponentX, exponentY, exponentZ);
    }

    /** {@link #pow(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long pow(long dest, long src, float exponentX, float exponentY, float exponentZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.pow_unsafe(dest, src, exponentX, exponentY, exponentZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _exponentx = exponent[exponentOffset + 0];
        float _exponenty = exponent[exponentOffset + 1];
        float _exponentz = exponent[exponentOffset + 2];
        dest[destOffset + 0] = (float) Math.pow(_selfx, _exponentx);
        dest[destOffset + 1] = (float) Math.pow(_selfy, _exponenty);
        dest[destOffset + 2] = (float) Math.pow(_selfz, _exponentz);
        return dest;
    }

    /** {@link #pow(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer pow(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer exponent, int exponentOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && exponent.isDirect() && exponent.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.pow_unsafe(dest, destOffset, src, srcOffset, exponent, exponentOffset);
        return Float3OpsKernelsTypedBuffer.pow_api(dest, destOffset, src, srcOffset, exponent, exponentOffset);
    }

    /** {@link #pow(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer pow(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer exponent, int exponentOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && exponent.isDirect() && exponent.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.pow_unsafe(dest, destOffset, src, srcOffset, exponent, exponentOffset);
        return Float3OpsKernelsByteBuffer.pow_api(dest, destOffset, src, srcOffset, exponent, exponentOffset);
    }

    /** {@link #pow(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long pow(long dest, long src, long exponent) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.pow_unsafe(dest, src, exponent);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Project this vector onto ({@code ontoX}, {@code ontoY}, {@code ontoZ}) and store the result
     * in {@code dest}.
     * <p>
     * Valid input: {@code (ontoX, ontoY, ontoZ)} must be non-zero.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param ontoX the {@code x} component of the vector {@code (ontoX, ontoY, ontoZ)}
     * @param ontoY the {@code y} component of the vector {@code (ontoX, ontoY, ontoZ)}
     * @param ontoZ the {@code z} component of the vector {@code (ontoX, ontoY, ontoZ)}
     * @return {@code dest}
     */
    public static float[] project(float[] dest, int destOffset, float[] src, int srcOffset, float ontoX, float ontoY, float ontoZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t7 = Math.fma(ontoZ, _selfz, Math.fma(ontoX, _selfx, ontoY * _selfy)) / Math.fma(ontoZ, ontoZ, Math.fma(ontoX, ontoX, ontoY * ontoY));
        dest[destOffset + 0] = ontoX * _t7;
        dest[destOffset + 1] = ontoY * _t7;
        dest[destOffset + 2] = ontoZ * _t7;
        return dest;
    }

    /** {@link #project(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer project(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float ontoX, float ontoY, float ontoZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.project_unsafe(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ);
        return Float3OpsKernelsTypedBuffer.project_api(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ);
    }

    /** {@link #project(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer project(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float ontoX, float ontoY, float ontoZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.project_unsafe(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ);
        return Float3OpsKernelsByteBuffer.project_api(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ);
    }

    /** {@link #project(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long project(long dest, long src, float ontoX, float ontoY, float ontoZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.project_unsafe(dest, src, ontoX, ontoY, ontoZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _ontox = onto[ontoOffset + 0];
        float _ontoy = onto[ontoOffset + 1];
        float _ontoz = onto[ontoOffset + 2];
        float _t7 = Math.fma(_ontoz, _selfz, Math.fma(_ontox, _selfx, _ontoy * _selfy)) / Math.fma(_ontoz, _ontoz, Math.fma(_ontox, _ontox, _ontoy * _ontoy));
        dest[destOffset + 0] = _ontox * _t7;
        dest[destOffset + 1] = _ontoy * _t7;
        dest[destOffset + 2] = _ontoz * _t7;
        return dest;
    }

    /** {@link #project(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer project(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer onto, int ontoOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && onto.isDirect() && onto.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.project_unsafe(dest, destOffset, src, srcOffset, onto, ontoOffset);
        return Float3OpsKernelsTypedBuffer.project_api(dest, destOffset, src, srcOffset, onto, ontoOffset);
    }

    /** {@link #project(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer project(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer onto, int ontoOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && onto.isDirect() && onto.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.project_unsafe(dest, destOffset, src, srcOffset, onto, ontoOffset);
        return Float3OpsKernelsByteBuffer.project_api(dest, destOffset, src, srcOffset, onto, ontoOffset);
    }

    /** {@link #project(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long project(long dest, long src, long onto) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.project_unsafe(dest, src, onto);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Project this vector onto the plane with the given normal and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code (normalX, normalY, normalZ)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param normalX the {@code x} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalY the {@code y} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalZ the {@code z} component of the vector {@code (normalX, normalY, normalZ)}
     * @return {@code dest}
     */
    public static float[] projectOnPlane(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t2 = Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy));
        dest[destOffset + 0] = Math.fma(-normalX, _t2, _selfx);
        dest[destOffset + 1] = Math.fma(-normalY, _t2, _selfy);
        dest[destOffset + 2] = Math.fma(-normalZ, _t2, _selfz);
        return dest;
    }

    /** {@link #projectOnPlane(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer projectOnPlane(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.projectOnPlane_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return Float3OpsKernelsTypedBuffer.projectOnPlane_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
    }

    /** {@link #projectOnPlane(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer projectOnPlane(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.projectOnPlane_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return Float3OpsKernelsByteBuffer.projectOnPlane_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
    }

    /** {@link #projectOnPlane(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long projectOnPlane(long dest, long src, float normalX, float normalY, float normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.projectOnPlane_unsafe(dest, src, normalX, normalY, normalZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _normalx = normal[normalOffset + 0];
        float _normaly = normal[normalOffset + 1];
        float _normalz = normal[normalOffset + 2];
        float _t2 = Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy));
        dest[destOffset + 0] = Math.fma(-_normalx, _t2, _selfx);
        dest[destOffset + 1] = Math.fma(-_normaly, _t2, _selfy);
        dest[destOffset + 2] = Math.fma(-_normalz, _t2, _selfz);
        return dest;
    }

    /** {@link #projectOnPlane(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer projectOnPlane(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.projectOnPlane_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return Float3OpsKernelsTypedBuffer.projectOnPlane_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    /** {@link #projectOnPlane(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer projectOnPlane(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.projectOnPlane_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return Float3OpsKernelsByteBuffer.projectOnPlane_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    /** {@link #projectOnPlane(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long projectOnPlane(long dest, long src, long normal) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.projectOnPlane_unsafe(dest, src, normal);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.toRadians(_selfx);
        dest[destOffset + 1] = (float) Math.toRadians(_selfy);
        dest[destOffset + 2] = (float) Math.toRadians(_selfz);
        return dest;
    }

    /** {@link #radians(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer radians(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.radians_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.radians_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #radians(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer radians(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.radians_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.radians_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #radians(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long radians(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.radians_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Reflect this vector about the given normal and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (normalX, normalY, normalZ)} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param normalX the {@code x} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalY the {@code y} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalZ the {@code z} component of the vector {@code (normalX, normalY, normalZ)}
     * @return {@code dest}
     */
    public static float[] reflect(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t3 = 2.0f * Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy));
        dest[destOffset + 0] = Math.fma(-normalX, _t3, _selfx);
        dest[destOffset + 1] = Math.fma(-normalY, _t3, _selfy);
        dest[destOffset + 2] = Math.fma(-normalZ, _t3, _selfz);
        return dest;
    }

    /** {@link #reflect(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer reflect(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.reflect_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return Float3OpsKernelsTypedBuffer.reflect_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
    }

    /** {@link #reflect(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer reflect(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.reflect_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return Float3OpsKernelsByteBuffer.reflect_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
    }

    /** {@link #reflect(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long reflect(long dest, long src, float normalX, float normalY, float normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.reflect_unsafe(dest, src, normalX, normalY, normalZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _normalx = normal[normalOffset + 0];
        float _normaly = normal[normalOffset + 1];
        float _normalz = normal[normalOffset + 2];
        float _t3 = 2.0f * Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy));
        dest[destOffset + 0] = Math.fma(-_normalx, _t3, _selfx);
        dest[destOffset + 1] = Math.fma(-_normaly, _t3, _selfy);
        dest[destOffset + 2] = Math.fma(-_normalz, _t3, _selfz);
        return dest;
    }

    /** {@link #reflect(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer reflect(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.reflect_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return Float3OpsKernelsTypedBuffer.reflect_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    /** {@link #reflect(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer reflect(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.reflect_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return Float3OpsKernelsByteBuffer.reflect_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    /** {@link #reflect(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long reflect(long dest, long src, long normal) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.reflect_unsafe(dest, src, normal);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
     * Valid input: {@code (normalX, normalY, normalZ)} must have unit length; this vector must have
     * unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param normalX the {@code x} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalY the {@code y} component of the vector {@code (normalX, normalY, normalZ)}
     * @param normalZ the {@code z} component of the vector {@code (normalX, normalY, normalZ)}
     * @param eta the ratio of indices of refraction, i.e. the source medium's divided by the
     *        destination medium's
     * @return {@code dest}
     */
    public static float[] refract(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ, float eta) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t3 = Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy));
        float _t7 = Math.fma(-Math.fma(-_t3, _t3, 1.0f), eta * eta, 1.0f);
        float _t10 = Math.fma(eta, _t3, (float) Math.sqrt(Math.max(0.0f, _t7)));
        if (_t7 >= 0.0f) {
            dest[destOffset + 0] = Math.fma(eta, _selfx, -(normalX * _t10));
            dest[destOffset + 1] = Math.fma(eta, _selfy, -(normalY * _t10));
            dest[destOffset + 2] = Math.fma(eta, _selfz, -(normalZ * _t10));
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    /** {@link #refract(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer refract(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.refract_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, eta);
        return Float3OpsKernelsTypedBuffer.refract_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, eta);
    }

    /** {@link #refract(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer refract(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float normalX, float normalY, float normalZ, float eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.refract_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, eta);
        return Float3OpsKernelsByteBuffer.refract_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, eta);
    }

    /** {@link #refract(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long refract(long dest, long src, float normalX, float normalY, float normalZ, float eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.refract_unsafe(dest, src, normalX, normalY, normalZ, eta);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _normalx = normal[normalOffset + 0];
        float _normaly = normal[normalOffset + 1];
        float _normalz = normal[normalOffset + 2];
        float _t3 = Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy));
        float _t7 = Math.fma(-Math.fma(-_t3, _t3, 1.0f), eta * eta, 1.0f);
        float _t10 = Math.fma(eta, _t3, (float) Math.sqrt(Math.max(0.0f, _t7)));
        if (_t7 >= 0.0f) {
            dest[destOffset + 0] = Math.fma(eta, _selfx, -(_normalx * _t10));
            dest[destOffset + 1] = Math.fma(eta, _selfy, -(_normaly * _t10));
            dest[destOffset + 2] = Math.fma(eta, _selfz, -(_normalz * _t10));
        } else {
            dest[destOffset + 0] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        return dest;
    }

    /** {@link #refract(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer refract(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer normal, int normalOffset, float eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.refract_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return Float3OpsKernelsTypedBuffer.refract_api(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
    }

    /** {@link #refract(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer refract(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset, float eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.refract_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return Float3OpsKernelsByteBuffer.refract_api(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
    }

    /** {@link #refract(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long refract(long dest, long src, long normal, float eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.refract_unsafe(dest, src, normal, eta);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.rint(_selfx);
        dest[destOffset + 1] = (float) Math.rint(_selfy);
        dest[destOffset + 2] = (float) Math.rint(_selfz);
        return dest;
    }

    /** {@link #round(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer round(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.round_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.round_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #round(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer round(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.round_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.round_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #round(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long round(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.round_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.signum(_selfx);
        dest[destOffset + 1] = Math.signum(_selfy);
        dest[destOffset + 2] = Math.signum(_selfz);
        return dest;
    }

    /** {@link #sign(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer sign(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.sign_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.sign_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sign(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sign(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.sign_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.sign_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sign(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long sign(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.sign_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.sin(_selfx);
        dest[destOffset + 1] = (float) Math.sin(_selfy);
        dest[destOffset + 2] = (float) Math.sin(_selfz);
        return dest;
    }

    /** {@link #sin(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer sin(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.sin_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.sin_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sin(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sin(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.sin_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.sin_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sin(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long sin(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.sin_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.sinh(_selfx);
        dest[destOffset + 1] = (float) Math.sinh(_selfy);
        dest[destOffset + 2] = (float) Math.sinh(_selfz);
        return dest;
    }

    /** {@link #sinh(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer sinh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.sinh_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.sinh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sinh(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sinh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.sinh_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.sinh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sinh(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long sinh(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.sinh_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0_inv = 1.0f / (edge1 - edge0);
        float _t10 = Math.max(0.0f, Math.min(1.0f, (_selfx - edge0) * _t0_inv));
        float _t11 = Math.max(0.0f, Math.min(1.0f, (_selfy - edge0) * _t0_inv));
        float _t12 = Math.max(0.0f, Math.min(1.0f, (_selfz - edge0) * _t0_inv));
        dest[destOffset + 0] = Math.fma(-2.0f, _t10, 3.0f) * _t10 * _t10;
        dest[destOffset + 1] = Math.fma(-2.0f, _t11, 3.0f) * _t11 * _t11;
        dest[destOffset + 2] = Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12;
        return dest;
    }

    /** {@link #smoothstep(float[], int, float[], int, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer smoothstep(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0, float edge1) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0, edge1);
        return Float3OpsKernelsTypedBuffer.smoothstep_api(dest, destOffset, src, srcOffset, edge0, edge1);
    }

    /** {@link #smoothstep(float[], int, float[], int, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer smoothstep(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge0, float edge1) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0, edge1);
        return Float3OpsKernelsByteBuffer.smoothstep_api(dest, destOffset, src, srcOffset, edge0, edge1);
    }

    /** {@link #smoothstep(float[], int, float[], int, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long smoothstep(long dest, long src, float edge0, float edge1) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.smoothstep_unsafe(dest, src, edge0, edge1);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge ({@code edge0X}, {@code edge0Y}, {@code edge0Z}) and the upper edge
     * ({@code edge1X}, {@code edge1Y}, {@code edge1Z}), yielding 0 at or below the lower edge and 1
     * at or above the upper edge and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (edge0X, edge0Y, edge0Z)} and {@code (edge1X, edge1Y, edge1Z)} must
     * differ in every component.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param edge0X the {@code x} component of the vector {@code (edge0X, edge0Y, edge0Z)}
     * @param edge0Y the {@code y} component of the vector {@code (edge0X, edge0Y, edge0Z)}
     * @param edge0Z the {@code z} component of the vector {@code (edge0X, edge0Y, edge0Z)}
     * @param edge1X the {@code x} component of the vector {@code (edge1X, edge1Y, edge1Z)}
     * @param edge1Y the {@code y} component of the vector {@code (edge1X, edge1Y, edge1Z)}
     * @param edge1Z the {@code z} component of the vector {@code (edge1X, edge1Y, edge1Z)}
     * @return {@code dest}
     */
    public static float[] smoothstep(float[] dest, int destOffset, float[] src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge1X, float edge1Y, float edge1Z) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t12 = Math.max(0.0f, Math.min(1.0f, (_selfx - edge0X) / (edge1X - edge0X)));
        float _t13 = Math.max(0.0f, Math.min(1.0f, (_selfy - edge0Y) / (edge1Y - edge0Y)));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (_selfz - edge0Z) / (edge1Z - edge0Z)));
        dest[destOffset + 0] = Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12;
        dest[destOffset + 1] = Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13;
        dest[destOffset + 2] = Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14;
        return dest;
    }

    /** {@link #smoothstep(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer smoothstep(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge1X, float edge1Y, float edge1Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
        return Float3OpsKernelsTypedBuffer.smoothstep_api(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
    }

    /** {@link #smoothstep(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer smoothstep(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge0X, float edge0Y, float edge0Z, float edge1X, float edge1Y, float edge1Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
        return Float3OpsKernelsByteBuffer.smoothstep_api(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
    }

    /** {@link #smoothstep(float[], int, float[], int, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long smoothstep(long dest, long src, float edge0X, float edge0Y, float edge0Z, float edge1X, float edge1Y, float edge1Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.smoothstep_unsafe(dest, src, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _edge0x = edge0[edge0Offset + 0];
        float _edge0y = edge0[edge0Offset + 1];
        float _edge0z = edge0[edge0Offset + 2];
        float _edge1x = edge1[edge1Offset + 0];
        float _edge1y = edge1[edge1Offset + 1];
        float _edge1z = edge1[edge1Offset + 2];
        float _t12 = Math.max(0.0f, Math.min(1.0f, (_selfx - _edge0x) / (_edge1x - _edge0x)));
        float _t13 = Math.max(0.0f, Math.min(1.0f, (_selfy - _edge0y) / (_edge1y - _edge0y)));
        float _t14 = Math.max(0.0f, Math.min(1.0f, (_selfz - _edge0z) / (_edge1z - _edge0z)));
        dest[destOffset + 0] = Math.fma(-2.0f, _t12, 3.0f) * _t12 * _t12;
        dest[destOffset + 1] = Math.fma(-2.0f, _t13, 3.0f) * _t13 * _t13;
        dest[destOffset + 2] = Math.fma(-2.0f, _t14, 3.0f) * _t14 * _t14;
        return dest;
    }

    /** {@link #smoothstep(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer smoothstep(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge0, int edge0Offset, java.nio.FloatBuffer edge1, int edge1Offset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && edge0.isDirect() && edge0.order() == java.nio.ByteOrder.nativeOrder() && edge1.isDirect() && edge1.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        return Float3OpsKernelsTypedBuffer.smoothstep_api(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
    }

    /** {@link #smoothstep(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer smoothstep(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge0, int edge0Offset, java.nio.ByteBuffer edge1, int edge1Offset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && edge0.isDirect() && edge0.order() == java.nio.ByteOrder.nativeOrder() && edge1.isDirect() && edge1.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        return Float3OpsKernelsByteBuffer.smoothstep_api(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
    }

    /** {@link #smoothstep(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long smoothstep(long dest, long src, long edge0, long edge1) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.smoothstep_unsafe(dest, src, edge0, edge1);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.sqrt(_selfx);
        dest[destOffset + 1] = (float) Math.sqrt(_selfy);
        dest[destOffset + 2] = (float) Math.sqrt(_selfz);
        return dest;
    }

    /** {@link #sqrt(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer sqrt(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.sqrt_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.sqrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sqrt(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sqrt(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.sqrt_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.sqrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sqrt(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long sqrt(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.sqrt_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = _selfx < edge ? 0.0f : 1.0f;
        dest[destOffset + 1] = _selfy < edge ? 0.0f : 1.0f;
        dest[destOffset + 2] = _selfz < edge ? 0.0f : 1.0f;
        return dest;
    }

    /** {@link #step(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer step(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edge) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.step_unsafe(dest, destOffset, src, srcOffset, edge);
        return Float3OpsKernelsTypedBuffer.step_api(dest, destOffset, src, srcOffset, edge);
    }

    /** {@link #step(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer step(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edge) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.step_unsafe(dest, destOffset, src, srcOffset, edge);
        return Float3OpsKernelsByteBuffer.step_api(dest, destOffset, src, srcOffset, edge);
    }

    /** {@link #step(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long step(long dest, long src, float edge) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.step_unsafe(dest, src, edge);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
     * @param edgeX the {@code x} component of the vector {@code (edgeX, edgeY, edgeZ)}
     * @param edgeY the {@code y} component of the vector {@code (edgeX, edgeY, edgeZ)}
     * @param edgeZ the {@code z} component of the vector {@code (edgeX, edgeY, edgeZ)}
     * @return {@code dest}
     */
    public static float[] step(float[] dest, int destOffset, float[] src, int srcOffset, float edgeX, float edgeY, float edgeZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = _selfx < edgeX ? 0.0f : 1.0f;
        dest[destOffset + 1] = _selfy < edgeY ? 0.0f : 1.0f;
        dest[destOffset + 2] = _selfz < edgeZ ? 0.0f : 1.0f;
        return dest;
    }

    /** {@link #step(float[], int, float[], int, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer step(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float edgeX, float edgeY, float edgeZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.step_unsafe(dest, destOffset, src, srcOffset, edgeX, edgeY, edgeZ);
        return Float3OpsKernelsTypedBuffer.step_api(dest, destOffset, src, srcOffset, edgeX, edgeY, edgeZ);
    }

    /** {@link #step(float[], int, float[], int, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer step(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float edgeX, float edgeY, float edgeZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.step_unsafe(dest, destOffset, src, srcOffset, edgeX, edgeY, edgeZ);
        return Float3OpsKernelsByteBuffer.step_api(dest, destOffset, src, srcOffset, edgeX, edgeY, edgeZ);
    }

    /** {@link #step(float[], int, float[], int, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long step(long dest, long src, float edgeX, float edgeY, float edgeZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.step_unsafe(dest, src, edgeX, edgeY, edgeZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _edgex = edge[edgeOffset + 0];
        float _edgey = edge[edgeOffset + 1];
        float _edgez = edge[edgeOffset + 2];
        dest[destOffset + 0] = _selfx < _edgex ? 0.0f : 1.0f;
        dest[destOffset + 1] = _selfy < _edgey ? 0.0f : 1.0f;
        dest[destOffset + 2] = _selfz < _edgez ? 0.0f : 1.0f;
        return dest;
    }

    /** {@link #step(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer step(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer edge, int edgeOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && edge.isDirect() && edge.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.step_unsafe(dest, destOffset, src, srcOffset, edge, edgeOffset);
        return Float3OpsKernelsTypedBuffer.step_api(dest, destOffset, src, srcOffset, edge, edgeOffset);
    }

    /** {@link #step(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer step(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge, int edgeOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && edge.isDirect() && edge.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.step_unsafe(dest, destOffset, src, srcOffset, edge, edgeOffset);
        return Float3OpsKernelsByteBuffer.step_api(dest, destOffset, src, srcOffset, edge, edgeOffset);
    }

    /** {@link #step(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long step(long dest, long src, long edge) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.step_unsafe(dest, src, edge);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.tan(_selfx);
        dest[destOffset + 1] = (float) Math.tan(_selfy);
        dest[destOffset + 2] = (float) Math.tan(_selfz);
        return dest;
    }

    /** {@link #tan(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer tan(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.tan_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.tan_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #tan(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer tan(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.tan_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.tan_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #tan(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long tan(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.tan_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = (float) Math.tanh(_selfx);
        dest[destOffset + 1] = (float) Math.tanh(_selfy);
        dest[destOffset + 2] = (float) Math.tanh(_selfz);
        return dest;
    }

    /** {@link #tanh(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer tanh(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.tanh_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.tanh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #tanh(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer tanh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.tanh_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.tanh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #tanh(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long tanh(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.tanh_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the unit normal of the triangle spanned by this vector and the two given points, i.e.
     * {@code normalize(((p1X, p1Y, p1Z) - this) x ((p2X, p2Y, p2Z) - this))} - it points to the
     * side from which the vertices {@code this}, ({@code p1X}, {@code p1Y}, {@code p1Z}),
     * ({@code p2X}, {@code p2Y}, {@code p2Z}) appear counter-clockwise (a degenerate triangle
     * yields the zero vector) and store the result in {@code dest}.
     * <p>
     * It holds for triangles of any finite size and shape: when the squared length of the edges'
     * cross product would leave the {@code float} range, the edges are first scaled exactly by
     * powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z)}
     * @return {@code dest}
     */
    public static float[] triangleNormal(float[] dest, int destOffset, float[] src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = p1Y - _selfy;
        float _t1 = p2Z - _selfz;
        float _t2 = p1Z - _selfz;
        float _t3 = p2Y - _selfy;
        float _t4 = p1X - _selfx;
        float _t5 = p2X - _selfx;
        float _t12 = Math.fma(_t0, _t1, -(_t2 * _t3));
        float _t13 = Math.fma(_t4, _t3, -(_t0 * _t5));
        float _t14 = Math.fma(_t2, _t5, -(_t4 * _t1));
        float _ct0 = Math.fma(_t13, _t13, Math.fma(_t12, _t12, _t14 * _t14));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsArray.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        float _t19 = (1.0f / (float) Math.sqrt(_ct0));
        dest[destOffset + 0] = _t12 * _t19;
        dest[destOffset + 1] = _t14 * _t19;
        dest[destOffset + 2] = _t13 * _t19;
        return dest;
    }

    /** {@link #triangleNormal(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer triangleNormal(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.triangleNormal_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return Float3OpsKernelsTypedBuffer.triangleNormal_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
    }

    /** {@link #triangleNormal(float[], int, float[], int, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer triangleNormal(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.triangleNormal_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return Float3OpsKernelsByteBuffer.triangleNormal_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
    }

    /** {@link #triangleNormal(float[], int, float[], int, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long triangleNormal(long dest, long src, float p1X, float p1Y, float p1Z, float p2X, float p2Y, float p2Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.triangleNormal_unsafe(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the unit normal of the triangle spanned by this vector and the two given points, i.e.
     * {@code normalize((p1 - this) x (p2 - this))} - it points to the side from which the vertices
     * {@code this}, {@code p1}, {@code p2} appear counter-clockwise (a degenerate triangle yields
     * the zero vector) and store the result in {@code dest}.
     * <p>
     * It holds for triangles of any finite size and shape: when the squared length of the edges'
     * cross product would leave the {@code float} range, the edges are first scaled exactly by
     * powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param p1 the storage holding the second vertex of the triangle (this vector is the first)
     * @param p1Offset the element index in {@code p1} at which the vector starts
     * @param p2 the storage holding the third vertex of the triangle
     * @param p2Offset the element index in {@code p2} at which the vector starts
     * @return {@code dest}
     */
    public static float[] triangleNormal(float[] dest, int destOffset, float[] src, int srcOffset, float[] p1, int p1Offset, float[] p2, int p2Offset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _p1x = p1[p1Offset + 0];
        float _p1y = p1[p1Offset + 1];
        float _p1z = p1[p1Offset + 2];
        float _p2x = p2[p2Offset + 0];
        float _p2y = p2[p2Offset + 1];
        float _p2z = p2[p2Offset + 2];
        float _t0 = _p1y - _selfy;
        float _t1 = _p2z - _selfz;
        float _t2 = _p1z - _selfz;
        float _t3 = _p2y - _selfy;
        float _t4 = _p1x - _selfx;
        float _t5 = _p2x - _selfx;
        float _t12 = Math.fma(_t0, _t1, -(_t2 * _t3));
        float _t13 = Math.fma(_t4, _t3, -(_t0 * _t5));
        float _t14 = Math.fma(_t2, _t5, -(_t4 * _t1));
        float _ct0 = Math.fma(_t13, _t13, Math.fma(_t12, _t12, _t14 * _t14));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return Float3OpsKernelsArray.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        float _t19 = (1.0f / (float) Math.sqrt(_ct0));
        dest[destOffset + 0] = _t12 * _t19;
        dest[destOffset + 1] = _t14 * _t19;
        dest[destOffset + 2] = _t13 * _t19;
        return dest;
    }

    /** {@link #triangleNormal(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer triangleNormal(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer p1, int p1Offset, java.nio.FloatBuffer p2, int p2Offset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.triangleNormal_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return Float3OpsKernelsTypedBuffer.triangleNormal_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
    }

    /** {@link #triangleNormal(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer triangleNormal(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.triangleNormal_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return Float3OpsKernelsByteBuffer.triangleNormal_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
    }

    /** {@link #triangleNormal(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long triangleNormal(long dest, long src, long p1, long p2) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.triangleNormal_unsafe(dest, src, p1, p2);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = _selfx >= 0.0f ? (float) Math.floor(_selfx) : (float) Math.ceil(_selfx);
        dest[destOffset + 1] = _selfy >= 0.0f ? (float) Math.floor(_selfy) : (float) Math.ceil(_selfy);
        dest[destOffset + 2] = _selfz >= 0.0f ? (float) Math.floor(_selfz) : (float) Math.ceil(_selfz);
        return dest;
    }

    /** {@link #trunc(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer trunc(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.trunc_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.trunc_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #trunc(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer trunc(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.trunc_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.trunc_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #trunc(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long trunc(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.trunc_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = Math.ulp(_selfx);
        dest[destOffset + 1] = Math.ulp(_selfy);
        dest[destOffset + 2] = Math.ulp(_selfz);
        return dest;
    }

    /** {@link #ulp(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer ulp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.ulp_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.ulp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #ulp(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer ulp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.ulp_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.ulp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #ulp(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long ulp(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.ulp_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 0} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] xyz0(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = _selfx;
        dest[destOffset + 1] = _selfy;
        dest[destOffset + 2] = _selfz;
        dest[destOffset + 3] = 0.0f;
        return dest;
    }

    /** {@link #xyz0(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer xyz0(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.xyz0_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.xyz0_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #xyz0(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer xyz0(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.xyz0_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.xyz0_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #xyz0(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long xyz0(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.xyz0_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy the {@code x}, {@code y} and {@code z} components of this vector into a 4D vector with
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static float[] xyz1(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset + 0] = _selfx;
        dest[destOffset + 1] = _selfy;
        dest[destOffset + 2] = _selfz;
        dest[destOffset + 3] = 1.0f;
        return dest;
    }

    /** {@link #xyz1(float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer xyz1(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.xyz1_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsTypedBuffer.xyz1_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #xyz1(float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer xyz1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.xyz1_unsafe(dest, destOffset, src, srcOffset);
        return Float3OpsKernelsByteBuffer.xyz1_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #xyz1(float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long xyz1(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.xyz1_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _mat00 = mat[matOffset + 0];
        float _mat10 = mat[matOffset + 1];
        float _mat20 = mat[matOffset + 2];
        float _mat01 = mat[matOffset + 3];
        float _mat11 = mat[matOffset + 4];
        float _mat21 = mat[matOffset + 5];
        float _mat02 = mat[matOffset + 6];
        float _mat12 = mat[matOffset + 7];
        float _mat22 = mat[matOffset + 8];
        dest[destOffset + 0] = Math.fma(_mat02, _selfz, Math.fma(_mat00, _selfx, _mat01 * _selfy));
        dest[destOffset + 1] = Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        dest[destOffset + 2] = Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy));
        return dest;
    }

    /** {@link #preMul(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preMul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.preMul_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Float3OpsKernelsTypedBuffer.preMul_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMul(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.preMul_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Float3OpsKernelsByteBuffer.preMul_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMul(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preMul(long dest, long src, long mat) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.preMul_unsafe(dest, src, mat);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Pre-multiply {@code mat} onto this vector, treated as a direction with implicit {@code w = 0}
     * - i.e. compute {@code (mat * (this, 0)).xyz}, applying only rotation and scale and ignoring
     * translation and store the result in {@code dest}.
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
    public static float[] preMulDirectionMat3x4(float[] dest, int destOffset, float[] src, int srcOffset, float[] mat, int matOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _mat00 = mat[matOffset + 0];
        float _mat01 = mat[matOffset + 1];
        float _mat02 = mat[matOffset + 2];
        float _mat10 = mat[matOffset + 4];
        float _mat11 = mat[matOffset + 5];
        float _mat12 = mat[matOffset + 6];
        float _mat20 = mat[matOffset + 8];
        float _mat21 = mat[matOffset + 9];
        float _mat22 = mat[matOffset + 10];
        dest[destOffset + 0] = Math.fma(_mat02, _selfz, Math.fma(_mat00, _selfx, _mat01 * _selfy));
        dest[destOffset + 1] = Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        dest[destOffset + 2] = Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy));
        return dest;
    }

    /** {@link #preMulDirectionMat3x4(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preMulDirectionMat3x4(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.preMulDirectionMat3x4_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Float3OpsKernelsTypedBuffer.preMulDirectionMat3x4_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMulDirectionMat3x4(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMulDirectionMat3x4(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.preMulDirectionMat3x4_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Float3OpsKernelsByteBuffer.preMulDirectionMat3x4_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMulDirectionMat3x4(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preMulDirectionMat3x4(long dest, long src, long mat) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.preMulDirectionMat3x4_unsafe(dest, src, mat);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Pre-multiply {@code mat} onto this vector, treated as a direction with implicit {@code w = 0}
     * - i.e. compute {@code (mat * (this, 0)).xyz}, applying only rotation and scale and ignoring
     * translation and store the result in {@code dest}.
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
    public static float[] preMulDirectionMat4x4(float[] dest, int destOffset, float[] src, int srcOffset, float[] mat, int matOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _mat00 = mat[matOffset + 0];
        float _mat10 = mat[matOffset + 1];
        float _mat20 = mat[matOffset + 2];
        float _mat01 = mat[matOffset + 4];
        float _mat11 = mat[matOffset + 5];
        float _mat21 = mat[matOffset + 6];
        float _mat02 = mat[matOffset + 8];
        float _mat12 = mat[matOffset + 9];
        float _mat22 = mat[matOffset + 10];
        dest[destOffset + 0] = Math.fma(_mat02, _selfz, Math.fma(_mat00, _selfx, _mat01 * _selfy));
        dest[destOffset + 1] = Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        dest[destOffset + 2] = Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy));
        return dest;
    }

    /** {@link #preMulDirectionMat4x4(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preMulDirectionMat4x4(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.preMulDirectionMat4x4_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Float3OpsKernelsTypedBuffer.preMulDirectionMat4x4_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMulDirectionMat4x4(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMulDirectionMat4x4(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.preMulDirectionMat4x4_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Float3OpsKernelsByteBuffer.preMulDirectionMat4x4_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMulDirectionMat4x4(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preMulDirectionMat4x4(long dest, long src, long mat) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.preMulDirectionMat4x4_unsafe(dest, src, mat);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Pre-multiply {@code mat} onto this vector, treated as a position with implicit {@code w = 1}
     * - i.e. compute {@code (mat * (this, 1)).xyz}, applying the full affine transform including
     * translation and store the result in {@code dest}.
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
    public static float[] preMulPositionMat3x4(float[] dest, int destOffset, float[] src, int srcOffset, float[] mat, int matOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _mat00 = mat[matOffset + 0];
        float _mat01 = mat[matOffset + 1];
        float _mat02 = mat[matOffset + 2];
        float _mat03 = mat[matOffset + 3];
        float _mat10 = mat[matOffset + 4];
        float _mat11 = mat[matOffset + 5];
        float _mat12 = mat[matOffset + 6];
        float _mat13 = mat[matOffset + 7];
        float _mat20 = mat[matOffset + 8];
        float _mat21 = mat[matOffset + 9];
        float _mat22 = mat[matOffset + 10];
        float _mat23 = mat[matOffset + 11];
        dest[destOffset + 0] = Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, Math.fma(_mat02, _selfz, _mat03)));
        dest[destOffset + 1] = Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, Math.fma(_mat12, _selfz, _mat13)));
        dest[destOffset + 2] = Math.fma(_mat20, _selfx, Math.fma(_mat21, _selfy, Math.fma(_mat22, _selfz, _mat23)));
        return dest;
    }

    /** {@link #preMulPositionMat3x4(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preMulPositionMat3x4(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.preMulPositionMat3x4_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Float3OpsKernelsTypedBuffer.preMulPositionMat3x4_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMulPositionMat3x4(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMulPositionMat3x4(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.preMulPositionMat3x4_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Float3OpsKernelsByteBuffer.preMulPositionMat3x4_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMulPositionMat3x4(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preMulPositionMat3x4(long dest, long src, long mat) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.preMulPositionMat3x4_unsafe(dest, src, mat);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Pre-multiply {@code mat} onto this vector, treated as a position with implicit {@code w = 1}
     * - i.e. compute {@code (mat * (this, 1)).xyz}, applying the full affine transform including
     * translation and store the result in {@code dest}.
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
    public static float[] preMulPositionMat4x4(float[] dest, int destOffset, float[] src, int srcOffset, float[] mat, int matOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _mat00 = mat[matOffset + 0];
        float _mat10 = mat[matOffset + 1];
        float _mat20 = mat[matOffset + 2];
        float _mat01 = mat[matOffset + 4];
        float _mat11 = mat[matOffset + 5];
        float _mat21 = mat[matOffset + 6];
        float _mat02 = mat[matOffset + 8];
        float _mat12 = mat[matOffset + 9];
        float _mat22 = mat[matOffset + 10];
        float _mat03 = mat[matOffset + 12];
        float _mat13 = mat[matOffset + 13];
        float _mat23 = mat[matOffset + 14];
        dest[destOffset + 0] = Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, Math.fma(_mat02, _selfz, _mat03)));
        dest[destOffset + 1] = Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, Math.fma(_mat12, _selfz, _mat13)));
        dest[destOffset + 2] = Math.fma(_mat20, _selfx, Math.fma(_mat21, _selfy, Math.fma(_mat22, _selfz, _mat23)));
        return dest;
    }

    /** {@link #preMulPositionMat4x4(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preMulPositionMat4x4(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.preMulPositionMat4x4_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Float3OpsKernelsTypedBuffer.preMulPositionMat4x4_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMulPositionMat4x4(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMulPositionMat4x4(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.preMulPositionMat4x4_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Float3OpsKernelsByteBuffer.preMulPositionMat4x4_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMulPositionMat4x4(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preMulPositionMat4x4(long dest, long src, long mat) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.preMulPositionMat4x4_unsafe(dest, src, mat);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Pre-multiply {@code mat} onto this vector, treated as a position with implicit {@code w = 1},
     * then perform a perspective divide - i.e. compute {@code r = mat * (this, 1)} and return
     * {@code r.xyz / r.w} and store the result in {@code dest}.
     * <p>
     * Valid input: this vector must not be mapped to {@code w = 0} by {@code mat}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param mat the storage holding the matrix to apply
     * @param matOffset the element index in {@code mat} at which the matrix starts
     * @return {@code dest}
     */
    public static float[] preMulProject(float[] dest, int destOffset, float[] src, int srcOffset, float[] mat, int matOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _mat00 = mat[matOffset + 0];
        float _mat10 = mat[matOffset + 1];
        float _mat20 = mat[matOffset + 2];
        float _mat30 = mat[matOffset + 3];
        float _mat01 = mat[matOffset + 4];
        float _mat11 = mat[matOffset + 5];
        float _mat21 = mat[matOffset + 6];
        float _mat31 = mat[matOffset + 7];
        float _mat02 = mat[matOffset + 8];
        float _mat12 = mat[matOffset + 9];
        float _mat22 = mat[matOffset + 10];
        float _mat32 = mat[matOffset + 11];
        float _mat03 = mat[matOffset + 12];
        float _mat13 = mat[matOffset + 13];
        float _mat23 = mat[matOffset + 14];
        float _mat33 = mat[matOffset + 15];
        float _t2_inv = 1.0f / Math.fma(_mat30, _selfx, Math.fma(_mat31, _selfy, Math.fma(_mat32, _selfz, _mat33)));
        dest[destOffset + 0] = Math.fma(_mat00, _selfx, Math.fma(_mat01, _selfy, Math.fma(_mat02, _selfz, _mat03))) * _t2_inv;
        dest[destOffset + 1] = Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, Math.fma(_mat12, _selfz, _mat13))) * _t2_inv;
        dest[destOffset + 2] = Math.fma(_mat20, _selfx, Math.fma(_mat21, _selfy, Math.fma(_mat22, _selfz, _mat23))) * _t2_inv;
        return dest;
    }

    /** {@link #preMulProject(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer preMulProject(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.preMulProject_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Float3OpsKernelsTypedBuffer.preMulProject_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMulProject(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMulProject(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.preMulProject_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Float3OpsKernelsByteBuffer.preMulProject_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMulProject(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preMulProject(long dest, long src, long mat) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.preMulProject_unsafe(dest, src, mat);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Rotate this vector by the quaternion ({@code quatX}, {@code quatY}, {@code quatZ},
     * {@code quatW}), i.e. compute {@code q * this * q^-1} and store the result in {@code dest}.
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t9 = 2.0f * Math.fma(quatX, _selfy, -(quatY * _selfx));
        float _t10 = 2.0f * Math.fma(quatZ, _selfx, -(quatX * _selfz));
        float _t11 = 2.0f * Math.fma(quatY, _selfz, -(quatZ * _selfy));
        dest[destOffset + 0] = Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, _selfx)));
        dest[destOffset + 1] = Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfy)));
        dest[destOffset + 2] = Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, _selfz)));
        return dest;
    }

    /** {@link #rotate(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return Float3OpsKernelsTypedBuffer.rotate_api(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
    }

    /** {@link #rotate(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return Float3OpsKernelsByteBuffer.rotate_api(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
    }

    /** {@link #rotate(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotate(long dest, long src, float quatX, float quatY, float quatZ, float quatW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.rotate_unsafe(dest, src, quatX, quatY, quatZ, quatW);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Rotate this vector by the quaternion {@code quat}, i.e. compute {@code q * this * q^-1} and
     * store the result in {@code dest}.
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _quatx = quat[quatOffset + 0];
        float _quaty = quat[quatOffset + 1];
        float _quatz = quat[quatOffset + 2];
        float _quatw = quat[quatOffset + 3];
        float _t9 = 2.0f * Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        float _t10 = 2.0f * Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        float _t11 = 2.0f * Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        dest[destOffset + 0] = Math.fma(_quaty, _t9, Math.fma(-_quatz, _t10, Math.fma(_quatw, _t11, _selfx)));
        dest[destOffset + 1] = Math.fma(_quatz, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfy)));
        dest[destOffset + 2] = Math.fma(_quatx, _t10, Math.fma(-_quaty, _t11, Math.fma(_quatw, _t9, _selfz)));
        return dest;
    }

    /** {@link #rotate(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && quat.isDirect() && quat.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, quat, quatOffset);
        return Float3OpsKernelsTypedBuffer.rotate_api(dest, destOffset, src, srcOffset, quat, quatOffset);
    }

    /** {@link #rotate(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && quat.isDirect() && quat.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, quat, quatOffset);
        return Float3OpsKernelsByteBuffer.rotate_api(dest, destOffset, src, srcOffset, quat, quatOffset);
    }

    /** {@link #rotate(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotate(long dest, long src, long quat) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.rotate_unsafe(dest, src, quat);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Rotate this vector by the quaternion ({@code quatX}, {@code quatY}, {@code quatZ},
     * {@code quatW}) about the point ({@code pivotX}, {@code pivotY}, {@code pivotZ}), i.e. compute
     * {@code p + q * (this - p) * q^-1} for the point {@code p} and store the result in
     * {@code dest}.
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
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotZ the {@code z} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @return {@code dest}
     */
    public static float[] rotateAround(float[] dest, int destOffset, float[] src, int srcOffset, float quatX, float quatY, float quatZ, float quatW, float pivotX, float pivotY, float pivotZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = _selfy - pivotY;
        float _t1 = _selfx - pivotX;
        float _t2 = _selfz - pivotZ;
        float _t12 = 2.0f * Math.fma(quatX, _t0, -(quatY * _t1));
        float _t13 = 2.0f * Math.fma(quatZ, _t1, -(quatX * _t2));
        float _t14 = 2.0f * Math.fma(quatY, _t2, -(quatZ * _t0));
        dest[destOffset + 0] = Math.fma(quatY, _t12, Math.fma(-quatZ, _t13, Math.fma(quatW, _t14, _selfx)));
        dest[destOffset + 1] = Math.fma(quatZ, _t14, Math.fma(-quatX, _t12, Math.fma(quatW, _t13, _selfy)));
        dest[destOffset + 2] = Math.fma(quatX, _t13, Math.fma(-quatY, _t14, Math.fma(quatW, _t12, _selfz)));
        return dest;
    }

    /** {@link #rotateAround(float[], int, float[], int, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW, float pivotX, float pivotY, float pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.rotateAround_unsafe(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        return Float3OpsKernelsTypedBuffer.rotateAround_api(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
    }

    /** {@link #rotateAround(float[], int, float[], int, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW, float pivotX, float pivotY, float pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.rotateAround_unsafe(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        return Float3OpsKernelsByteBuffer.rotateAround_api(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
    }

    /** {@link #rotateAround(float[], int, float[], int, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateAround(long dest, long src, float quatX, float quatY, float quatZ, float quatW, float pivotX, float pivotY, float pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.rotateAround_unsafe(dest, src, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Rotate this vector by the quaternion {@code quat} about the point {@code pivot}, i.e. compute
     * {@code p + q * (this - p) * q^-1} for the point {@code p} and store the result in
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
     * @param pivot the storage holding the pivot point
     * @param pivotOffset the element index in {@code pivot} at which the vector starts
     * @return {@code dest}
     */
    public static float[] rotateAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] quat, int quatOffset, float[] pivot, int pivotOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _quatx = quat[quatOffset + 0];
        float _quaty = quat[quatOffset + 1];
        float _quatz = quat[quatOffset + 2];
        float _quatw = quat[quatOffset + 3];
        float _pivotx = pivot[pivotOffset + 0];
        float _pivoty = pivot[pivotOffset + 1];
        float _pivotz = pivot[pivotOffset + 2];
        float _t0 = _selfy - _pivoty;
        float _t1 = _selfx - _pivotx;
        float _t2 = _selfz - _pivotz;
        float _t12 = 2.0f * Math.fma(_quatx, _t0, -(_quaty * _t1));
        float _t13 = 2.0f * Math.fma(_quatz, _t1, -(_quatx * _t2));
        float _t14 = 2.0f * Math.fma(_quaty, _t2, -(_quatz * _t0));
        dest[destOffset + 0] = Math.fma(_quaty, _t12, Math.fma(-_quatz, _t13, Math.fma(_quatw, _t14, _selfx)));
        dest[destOffset + 1] = Math.fma(_quatz, _t14, Math.fma(-_quatx, _t12, Math.fma(_quatw, _t13, _selfy)));
        dest[destOffset + 2] = Math.fma(_quatx, _t13, Math.fma(-_quaty, _t14, Math.fma(_quatw, _t12, _selfz)));
        return dest;
    }

    /** {@link #rotateAround(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset, java.nio.FloatBuffer pivot, int pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && quat.isDirect() && quat.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.rotateAround_unsafe(dest, destOffset, src, srcOffset, quat, quatOffset, pivot, pivotOffset);
        return Float3OpsKernelsTypedBuffer.rotateAround_api(dest, destOffset, src, srcOffset, quat, quatOffset, pivot, pivotOffset);
    }

    /** {@link #rotateAround(float[], int, float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && quat.isDirect() && quat.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.rotateAround_unsafe(dest, destOffset, src, srcOffset, quat, quatOffset, pivot, pivotOffset);
        return Float3OpsKernelsByteBuffer.rotateAround_api(dest, destOffset, src, srcOffset, quat, quatOffset, pivot, pivotOffset);
    }

    /** {@link #rotateAround(float[], int, float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateAround(long dest, long src, long quat, long pivot) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.rotateAround_unsafe(dest, src, quat, pivot);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Rotate this vector by {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) and store the result in {@code dest}.
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = 1.0f - _t1;
        float _t5 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        dest[destOffset + 0] = Math.fma(_t3, axisX * _t5, Math.fma(_selfx, _t1, Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t0));
        dest[destOffset + 1] = Math.fma(_t3, axisY * _t5, Math.fma(_selfy, _t1, Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t0));
        dest[destOffset + 2] = Math.fma(_t3, axisZ * _t5, Math.fma(_selfz, _t1, Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t0));
        return dest;
    }

    /** {@link #rotateAxis(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateAxis(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.rotateAxis_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return Float3OpsKernelsTypedBuffer.rotateAxis_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #rotateAxis(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAxis(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.rotateAxis_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return Float3OpsKernelsByteBuffer.rotateAxis_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #rotateAxis(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateAxis(long dest, long src, float angle, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.rotateAxis_unsafe(dest, src, angle, axisX, axisY, axisZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Rotate this vector by {@code angle} radians about the axis {@code axis} and store the result
     * in {@code dest}.
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _axisx = axis[axisOffset + 0];
        float _axisy = axis[axisOffset + 1];
        float _axisz = axis[axisOffset + 2];
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t3 = 1.0f - _t1;
        float _t5 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        dest[destOffset + 0] = Math.fma(_t3, _axisx * _t5, Math.fma(_selfx, _t1, Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t0));
        dest[destOffset + 1] = Math.fma(_t3, _axisy * _t5, Math.fma(_selfy, _t1, Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t0));
        dest[destOffset + 2] = Math.fma(_t3, _axisz * _t5, Math.fma(_selfz, _t1, Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t0));
        return dest;
    }

    /** {@link #rotateAxis(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateAxis(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.rotateAxis_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return Float3OpsKernelsTypedBuffer.rotateAxis_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@link #rotateAxis(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAxis(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.rotateAxis_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return Float3OpsKernelsByteBuffer.rotateAxis_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@link #rotateAxis(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateAxis(long dest, long src, long axis, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.rotateAxis_unsafe(dest, src, axis, angle);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Rotate this vector by {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) through the point ({@code pivotX}, {@code pivotY}, {@code pivotZ}) and store
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
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotZ the {@code z} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @return {@code dest}
     */
    public static float[] rotateAxisAround(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ, float pivotX, float pivotY, float pivotZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _selfx - pivotX;
        float _t3 = _selfz - pivotZ;
        float _t4 = _selfy - pivotY;
        float _t5 = 1.0f - _t1;
        float _t8 = Math.fma(axisZ, _t3, Math.fma(axisX, _t2, axisY * _t4));
        dest[destOffset + 0] = Math.fma(_t2, _t1, Math.fma(Math.fma(axisY, _t3, -(axisZ * _t4)), _t0, Math.fma(_t5, axisX * _t8, pivotX)));
        dest[destOffset + 1] = Math.fma(_t4, _t1, Math.fma(Math.fma(axisZ, _t2, -(axisX * _t3)), _t0, Math.fma(_t5, axisY * _t8, pivotY)));
        dest[destOffset + 2] = Math.fma(_t3, _t1, Math.fma(Math.fma(axisX, _t4, -(axisY * _t2)), _t0, Math.fma(_t5, axisZ * _t8, pivotZ)));
        return dest;
    }

    /** {@link #rotateAxisAround(float[], int, float[], int, float, float, float, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateAxisAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ, float pivotX, float pivotY, float pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.rotateAxisAround_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        return Float3OpsKernelsTypedBuffer.rotateAxisAround_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
    }

    /** {@link #rotateAxisAround(float[], int, float[], int, float, float, float, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAxisAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float axisX, float axisY, float axisZ, float pivotX, float pivotY, float pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.rotateAxisAround_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        return Float3OpsKernelsByteBuffer.rotateAxisAround_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
    }

    /** {@link #rotateAxisAround(float[], int, float[], int, float, float, float, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateAxisAround(long dest, long src, float angle, float axisX, float axisY, float axisZ, float pivotX, float pivotY, float pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.rotateAxisAround_unsafe(dest, src, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Rotate this vector by {@code angle} radians about the axis {@code axis} through the point
     * {@code pivot} and store the result in {@code dest}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param axis the storage holding the rotation axis
     * @param axisOffset the element index in {@code axis} at which the vector starts
     * @param pivot the storage holding the pivot point
     * @param pivotOffset the element index in {@code pivot} at which the vector starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] rotateAxisAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float[] pivot, int pivotOffset, float angle) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _axisx = axis[axisOffset + 0];
        float _axisy = axis[axisOffset + 1];
        float _axisz = axis[axisOffset + 2];
        float _pivotx = pivot[pivotOffset + 0];
        float _pivoty = pivot[pivotOffset + 1];
        float _pivotz = pivot[pivotOffset + 2];
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _selfx - _pivotx;
        float _t3 = _selfz - _pivotz;
        float _t4 = _selfy - _pivoty;
        float _t5 = 1.0f - _t1;
        float _t8 = Math.fma(_axisz, _t3, Math.fma(_axisx, _t2, _axisy * _t4));
        dest[destOffset + 0] = Math.fma(_t2, _t1, Math.fma(Math.fma(_axisy, _t3, -(_axisz * _t4)), _t0, Math.fma(_t5, _axisx * _t8, _pivotx)));
        dest[destOffset + 1] = Math.fma(_t4, _t1, Math.fma(Math.fma(_axisz, _t2, -(_axisx * _t3)), _t0, Math.fma(_t5, _axisy * _t8, _pivoty)));
        dest[destOffset + 2] = Math.fma(_t3, _t1, Math.fma(Math.fma(_axisx, _t4, -(_axisy * _t2)), _t0, Math.fma(_t5, _axisz * _t8, _pivotz)));
        return dest;
    }

    /** {@link #rotateAxisAround(float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateAxisAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer axis, int axisOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.rotateAxisAround_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, pivot, pivotOffset, angle);
        return Float3OpsKernelsTypedBuffer.rotateAxisAround_api(dest, destOffset, src, srcOffset, axis, axisOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateAxisAround(float[], int, float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAxisAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.rotateAxisAround_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, pivot, pivotOffset, angle);
        return Float3OpsKernelsByteBuffer.rotateAxisAround_api(dest, destOffset, src, srcOffset, axis, axisOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateAxisAround(float[], int, float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateAxisAround(long dest, long src, long axis, long pivot, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.rotateAxisAround_unsafe(dest, src, axis, pivot, angle);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Rotate this vector by the inverse of the given rotation and store the result in {@code dest}.
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t9 = 2.0f * Math.fma(quatX, _selfz, -(quatZ * _selfx));
        float _t10 = 2.0f * Math.fma(quatY, _selfx, -(quatX * _selfy));
        float _t11 = 2.0f * Math.fma(quatZ, _selfy, -(quatY * _selfz));
        dest[destOffset + 0] = Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, _selfx)));
        dest[destOffset + 1] = Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, _selfy)));
        dest[destOffset + 2] = Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfz)));
        return dest;
    }

    /** {@link #rotateInverse(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateInverse(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.rotateInverse_unsafe(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return Float3OpsKernelsTypedBuffer.rotateInverse_api(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
    }

    /** {@link #rotateInverse(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateInverse(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float quatX, float quatY, float quatZ, float quatW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.rotateInverse_unsafe(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return Float3OpsKernelsByteBuffer.rotateInverse_api(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
    }

    /** {@link #rotateInverse(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateInverse(long dest, long src, float quatX, float quatY, float quatZ, float quatW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.rotateInverse_unsafe(dest, src, quatX, quatY, quatZ, quatW);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Rotate this vector by the inverse of the given rotation and store the result in {@code dest}.
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _quatx = quat[quatOffset + 0];
        float _quaty = quat[quatOffset + 1];
        float _quatz = quat[quatOffset + 2];
        float _quatw = quat[quatOffset + 3];
        float _t9 = 2.0f * Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        float _t10 = 2.0f * Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        float _t11 = 2.0f * Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        dest[destOffset + 0] = Math.fma(_quatz, _t9, Math.fma(-_quaty, _t10, Math.fma(_quatw, _t11, _selfx)));
        dest[destOffset + 1] = Math.fma(_quatx, _t10, Math.fma(-_quatz, _t11, Math.fma(_quatw, _t9, _selfy)));
        dest[destOffset + 2] = Math.fma(_quaty, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfz)));
        return dest;
    }

    /** {@link #rotateInverse(float[], int, float[], int, float[], int)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateInverse(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer quat, int quatOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && quat.isDirect() && quat.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.rotateInverse_unsafe(dest, destOffset, src, srcOffset, quat, quatOffset);
        return Float3OpsKernelsTypedBuffer.rotateInverse_api(dest, destOffset, src, srcOffset, quat, quatOffset);
    }

    /** {@link #rotateInverse(float[], int, float[], int, float[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateInverse(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && quat.isDirect() && quat.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.rotateInverse_unsafe(dest, destOffset, src, srcOffset, quat, quatOffset);
        return Float3OpsKernelsByteBuffer.rotateInverse_api(dest, destOffset, src, srcOffset, quat, quatOffset);
    }

    /** {@link #rotateInverse(float[], int, float[], int, float[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateInverse(long dest, long src, long quat) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.rotateInverse_unsafe(dest, src, quat);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Rotate this vector by {@code angle} radians about the X axis and store the result in
     * {@code dest}.
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest[destOffset + 0] = _selfx;
        dest[destOffset + 1] = Math.fma(_selfy, _t1, -(_selfz * _t0));
        dest[destOffset + 2] = Math.fma(_selfy, _t0, _selfz * _t1);
        return dest;
    }

    /** {@link #rotateX(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateX(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.rotateX_unsafe(dest, destOffset, src, srcOffset, angle);
        return Float3OpsKernelsTypedBuffer.rotateX_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateX(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.rotateX_unsafe(dest, destOffset, src, srcOffset, angle);
        return Float3OpsKernelsByteBuffer.rotateX_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateX(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateX(long dest, long src, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.rotateX_unsafe(dest, src, angle);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Rotate this vector by {@code angle} radians about the X axis through the point
     * ({@code pivotX}, {@code pivotY}, {@code pivotZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotZ the {@code z} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @return {@code dest}
     */
    public static float[] rotateXAround(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float pivotX, float pivotY, float pivotZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _selfy - pivotY;
        float _t3 = _selfz - pivotZ;
        dest[destOffset + 0] = _selfx;
        dest[destOffset + 1] = Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotY));
        dest[destOffset + 2] = Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotZ));
        return dest;
    }

    /** {@link #rotateXAround(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateXAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY, float pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.rotateXAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY, pivotZ);
        return Float3OpsKernelsTypedBuffer.rotateXAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY, pivotZ);
    }

    /** {@link #rotateXAround(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateXAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY, float pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.rotateXAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY, pivotZ);
        return Float3OpsKernelsByteBuffer.rotateXAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY, pivotZ);
    }

    /** {@link #rotateXAround(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateXAround(long dest, long src, float angle, float pivotX, float pivotY, float pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.rotateXAround_unsafe(dest, src, angle, pivotX, pivotY, pivotZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Rotate this vector by {@code angle} radians about the X axis through the point {@code pivot}
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param pivot the storage holding the pivot point
     * @param pivotOffset the element index in {@code pivot} at which the vector starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] rotateXAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float angle) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _pivoty = pivot[pivotOffset + 1];
        float _pivotz = pivot[pivotOffset + 2];
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _selfy - _pivoty;
        float _t3 = _selfz - _pivotz;
        dest[destOffset + 0] = _selfx;
        dest[destOffset + 1] = Math.fma(_t2, _t1, Math.fma(-_t3, _t0, _pivoty));
        dest[destOffset + 2] = Math.fma(_t2, _t0, Math.fma(_t3, _t1, _pivotz));
        return dest;
    }

    /** {@link #rotateXAround(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateXAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.rotateXAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Float3OpsKernelsTypedBuffer.rotateXAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateXAround(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateXAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.rotateXAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Float3OpsKernelsByteBuffer.rotateXAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateXAround(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateXAround(long dest, long src, long pivot, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.rotateXAround_unsafe(dest, src, pivot, angle);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Rotate this vector by {@code angle} radians about the Y axis and store the result in
     * {@code dest}.
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest[destOffset + 0] = Math.fma(_selfx, _t1, _selfz * _t0);
        dest[destOffset + 1] = _selfy;
        dest[destOffset + 2] = Math.fma(_selfz, _t1, -(_selfx * _t0));
        return dest;
    }

    /** {@link #rotateY(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateY(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.rotateY_unsafe(dest, destOffset, src, srcOffset, angle);
        return Float3OpsKernelsTypedBuffer.rotateY_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateY(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.rotateY_unsafe(dest, destOffset, src, srcOffset, angle);
        return Float3OpsKernelsByteBuffer.rotateY_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateY(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateY(long dest, long src, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.rotateY_unsafe(dest, src, angle);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Rotate this vector by {@code angle} radians about the Y axis through the point
     * ({@code pivotX}, {@code pivotY}, {@code pivotZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotZ the {@code z} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @return {@code dest}
     */
    public static float[] rotateYAround(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float pivotX, float pivotY, float pivotZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _selfx - pivotX;
        float _t3 = _selfz - pivotZ;
        dest[destOffset + 0] = Math.fma(_t2, _t1, Math.fma(_t3, _t0, pivotX));
        dest[destOffset + 1] = _selfy;
        dest[destOffset + 2] = Math.fma(_t3, _t1, Math.fma(-_t2, _t0, pivotZ));
        return dest;
    }

    /** {@link #rotateYAround(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateYAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY, float pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.rotateYAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY, pivotZ);
        return Float3OpsKernelsTypedBuffer.rotateYAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY, pivotZ);
    }

    /** {@link #rotateYAround(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateYAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY, float pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.rotateYAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY, pivotZ);
        return Float3OpsKernelsByteBuffer.rotateYAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY, pivotZ);
    }

    /** {@link #rotateYAround(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateYAround(long dest, long src, float angle, float pivotX, float pivotY, float pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.rotateYAround_unsafe(dest, src, angle, pivotX, pivotY, pivotZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Rotate this vector by {@code angle} radians about the Y axis through the point {@code pivot}
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param pivot the storage holding the pivot point
     * @param pivotOffset the element index in {@code pivot} at which the vector starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] rotateYAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float angle) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _pivotx = pivot[pivotOffset + 0];
        float _pivotz = pivot[pivotOffset + 2];
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _selfx - _pivotx;
        float _t3 = _selfz - _pivotz;
        dest[destOffset + 0] = Math.fma(_t2, _t1, Math.fma(_t3, _t0, _pivotx));
        dest[destOffset + 1] = _selfy;
        dest[destOffset + 2] = Math.fma(_t3, _t1, Math.fma(-_t2, _t0, _pivotz));
        return dest;
    }

    /** {@link #rotateYAround(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateYAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.rotateYAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Float3OpsKernelsTypedBuffer.rotateYAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateYAround(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateYAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.rotateYAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Float3OpsKernelsByteBuffer.rotateYAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateYAround(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateYAround(long dest, long src, long pivot, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.rotateYAround_unsafe(dest, src, pivot, angle);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Rotate this vector by {@code angle} radians about the Z axis and store the result in
     * {@code dest}.
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
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        dest[destOffset + 0] = Math.fma(_selfx, _t1, -(_selfy * _t0));
        dest[destOffset + 1] = Math.fma(_selfx, _t0, _selfy * _t1);
        dest[destOffset + 2] = _selfz;
        return dest;
    }

    /** {@link #rotateZ(float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateZ(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.rotateZ_unsafe(dest, destOffset, src, srcOffset, angle);
        return Float3OpsKernelsTypedBuffer.rotateZ_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateZ(float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.rotateZ_unsafe(dest, destOffset, src, srcOffset, angle);
        return Float3OpsKernelsByteBuffer.rotateZ_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateZ(float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateZ(long dest, long src, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.rotateZ_unsafe(dest, src, angle);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Rotate this vector by {@code angle} radians about the Z axis through the point
     * ({@code pivotX}, {@code pivotY}, {@code pivotZ}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param angle the angle in radians
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotZ the {@code z} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @return {@code dest}
     */
    public static float[] rotateZAround(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float pivotX, float pivotY, float pivotZ) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _selfx - pivotX;
        float _t3 = _selfy - pivotY;
        dest[destOffset + 0] = Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotX));
        dest[destOffset + 1] = Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotY));
        dest[destOffset + 2] = _selfz;
        return dest;
    }

    /** {@link #rotateZAround(float[], int, float[], int, float, float, float, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateZAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float angle, float pivotX, float pivotY, float pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.rotateZAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY, pivotZ);
        return Float3OpsKernelsTypedBuffer.rotateZAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY, pivotZ);
    }

    /** {@link #rotateZAround(float[], int, float[], int, float, float, float, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateZAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float angle, float pivotX, float pivotY, float pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.rotateZAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY, pivotZ);
        return Float3OpsKernelsByteBuffer.rotateZAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY, pivotZ);
    }

    /** {@link #rotateZAround(float[], int, float[], int, float, float, float, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateZAround(long dest, long src, float angle, float pivotX, float pivotY, float pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.rotateZAround_unsafe(dest, src, angle, pivotX, pivotY, pivotZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Rotate this vector by {@code angle} radians about the Z axis through the point {@code pivot}
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param pivot the storage holding the pivot point
     * @param pivotOffset the element index in {@code pivot} at which the vector starts
     * @param angle the angle in radians
     * @return {@code dest}
     */
    public static float[] rotateZAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float angle) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _pivotx = pivot[pivotOffset + 0];
        float _pivoty = pivot[pivotOffset + 1];
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _selfx - _pivotx;
        float _t3 = _selfy - _pivoty;
        dest[destOffset + 0] = Math.fma(_t2, _t1, Math.fma(-_t3, _t0, _pivotx));
        dest[destOffset + 1] = Math.fma(_t2, _t0, Math.fma(_t3, _t1, _pivoty));
        dest[destOffset + 2] = _selfz;
        return dest;
    }

    /** {@link #rotateZAround(float[], int, float[], int, float[], int, float)} on {@link java.nio.FloatBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.FloatBuffer rotateZAround(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, java.nio.FloatBuffer pivot, int pivotOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsTypedBuffer.rotateZAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Float3OpsKernelsTypedBuffer.rotateZAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateZAround(float[], int, float[], int, float[], int, float)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateZAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsKernelsByteBuffer.rotateZAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Float3OpsKernelsByteBuffer.rotateZAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateZAround(float[], int, float[], int, float[], int, float)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateZAround(long dest, long src, long pivot, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Float3OpsKernelsAddress.rotateZAround_unsafe(dest, src, pivot, angle);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }


    /**
     * Bulk out-of-place component-wise {@code add} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code add}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] add(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 171) return Float3OpsSimd.add(dest, destOffset, a, aOffset, b, bOffset, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] + b[bOffset + _i];
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code add} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code add}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer add(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer a, int aOffset, java.nio.FloatBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, a.get(aOffset + _i) + b.get(bOffset + _i));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code add} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code add}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer add(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, a.getFloat(aOffset + _i * 4) + b.getFloat(bOffset + _i * 4));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code sub} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code sub}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] sub(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 171) return Float3OpsSimd.sub(dest, destOffset, a, aOffset, b, bOffset, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] - b[bOffset + _i];
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code sub} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code sub}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer sub(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer a, int aOffset, java.nio.FloatBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, a.get(aOffset + _i) - b.get(bOffset + _i));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code sub} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code sub}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer sub(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, a.getFloat(aOffset + _i * 4) - b.getFloat(bOffset + _i * 4));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code mul} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code mul}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] mul(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 171) return Float3OpsSimd.mul(dest, destOffset, a, aOffset, b, bOffset, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] * b[bOffset + _i];
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code mul} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code mul}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer mul(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer a, int aOffset, java.nio.FloatBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, a.get(aOffset + _i) * b.get(bOffset + _i));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code mul} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code mul}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, a.getFloat(aOffset + _i * 4) * b.getFloat(bOffset + _i * 4));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code div} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code div}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] div(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 171) return Float3OpsSimd.div(dest, destOffset, a, aOffset, b, bOffset, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] / b[bOffset + _i];
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code div} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code div}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer div(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer a, int aOffset, java.nio.FloatBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, a.get(aOffset + _i) / b.get(bOffset + _i));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code div} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code div}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer div(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, a.getFloat(aOffset + _i * 4) / b.getFloat(bOffset + _i * 4));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code min} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code min}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] min(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 171) return Float3OpsSimd.min(dest, destOffset, a, aOffset, b, bOffset, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = Math.min(a[aOffset + _i], b[bOffset + _i]);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code min} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code min}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer min(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer a, int aOffset, java.nio.FloatBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, Math.min(a.get(aOffset + _i), b.get(bOffset + _i)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code min} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code min}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer min(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, Math.min(a.getFloat(aOffset + _i * 4), b.getFloat(bOffset + _i * 4)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code max} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code max}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] max(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 171) return Float3OpsSimd.max(dest, destOffset, a, aOffset, b, bOffset, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = Math.max(a[aOffset + _i], b[bOffset + _i]);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code max} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code max}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer max(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer a, int aOffset, java.nio.FloatBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, Math.max(a.get(aOffset + _i), b.get(bOffset + _i)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code max} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code max}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer max(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, Math.max(a.getFloat(aOffset + _i * 4), b.getFloat(bOffset + _i * 4)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code negate} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code negate}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] negate(float[] dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 171) return Float3OpsSimd.negate(dest, destOffset, src, srcOffset, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = -src[srcOffset + _i];
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code negate} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code negate}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer negate(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, -src.get(srcOffset + _i));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code negate} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code negate}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer negate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, -src.getFloat(srcOffset + _i * 4));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code abs} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code abs}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] abs(float[] dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 171) return Float3OpsSimd.abs(dest, destOffset, src, srcOffset, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = Math.abs(src[srcOffset + _i]);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code abs} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code abs}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer abs(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, Math.abs(src.get(srcOffset + _i)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code abs} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code abs}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer abs(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, Math.abs(src.getFloat(srcOffset + _i * 4)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code lerp} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code lerp}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] lerp(float[] dest, int destOffset, float[] a, int aOffset, float[] b, int bOffset, float t, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 171) return Float3OpsSimd.lerp(dest, destOffset, a, aOffset, b, bOffset, t, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] + t * (b[bOffset + _i] - a[aOffset + _i]);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code lerp} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code lerp}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer lerp(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer a, int aOffset, java.nio.FloatBuffer b, int bOffset, float t, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, a.get(aOffset + _i) + t * (b.get(bOffset + _i) - a.get(aOffset + _i)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code lerp} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code lerp}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, float t, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, a.getFloat(aOffset + _i * 4) + t * (b.getFloat(bOffset + _i * 4) - a.getFloat(aOffset + _i * 4)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code scale} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code scale}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] scale(float[] dest, int destOffset, float[] src, int srcOffset, float s, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 171) return Float3OpsSimd.scale(dest, destOffset, src, srcOffset, s, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = src[srcOffset + _i] * s;
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code scale} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code scale}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer scale(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, float s, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, src.get(srcOffset + _i) * s);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code scale} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code scale}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer scale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, float s, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, src.getFloat(srcOffset + _i * 4) * s);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code fma} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code fma}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static float[] fma(float[] dest, int destOffset, float[] self, int selfOffset, float[] a, int aOffset, float[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API && count < 171) return Float3OpsSimd.fma(dest, destOffset, self, selfOffset, a, aOffset, b, bOffset, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = Math.fma(self[selfOffset + _i], a[aOffset + _i], b[bOffset + _i]);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code fma} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code fma}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.FloatBuffer fma(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer self, int selfOffset, java.nio.FloatBuffer a, int aOffset, java.nio.FloatBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, Math.fma(self.get(selfOffset + _i), a.get(aOffset + _i), b.get(bOffset + _i)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code fma} over {@code count} consecutive Float3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code fma}
     * overload of the same name, which processes exactly one Float3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer self, int selfOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putFloat(destOffset + _i * 4, Math.fma(self.getFloat(selfOffset + _i * 4), a.getFloat(aOffset + _i * 4), b.getFloat(bOffset + _i * 4)));
        }
        return dest;
    }

    /**
     * Copy one Float3 (3 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return Float3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float3 values ({@code count * 3} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API) return Float3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 715827882 ? -1 : count * 3), src.length);
            java.util.Objects.checkFromIndexSize(destOffset, (count > 715827882 ? -1 : count * 3), dest.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.FLOAT_ARRAY_BASE + (long) srcOffset * 4L, dest, UnsafeCopy.FLOAT_ARRAY_BASE + (long) destOffset * 4L, (long) count * 12L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 3;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src[srcOffset + _i];
        return dest;
    }

    /**
     * Copy one Float3 (3 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float3 values ({@code count * 3} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 715827882 ? -1 : count * 3), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L, dest, UnsafeCopy.FLOAT_ARRAY_BASE + (long) destOffset * 4L, (long) count * 12L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 3;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src.get(srcOffset + _i);
        return dest;
    }

    /**
     * Copy one Float3 (3 floats) from {@code src}
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
     * Bulk-copy {@code count} consecutive Float3 values ({@code count * 3} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static float[] copy(float[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 715827882 ? -1 : count * 3), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, dest, UnsafeCopy.FLOAT_ARRAY_BASE + (long) destOffset * 4L, (long) count * 12L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src.getFloat(srcOffset + _i * 4);
        return dest;
    }

    /**
     * Copy one Float3 (3 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static float[] copy(float[] dest, int destOffset, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(destOffset, 3, dest.length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest, UnsafeCopy.FLOAT_ARRAY_BASE + (long) destOffset * 4L, 12L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Float3 values ({@code count * 3} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static float[] copy(float[] dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 715827882 ? -1 : count * 3), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest, UnsafeCopy.FLOAT_ARRAY_BASE + (long) destOffset * 4L, (long) count * 12L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Float3 (3 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Float3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float3 values ({@code count * 3} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Float3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 715827882 ? -1 : count * 3), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.FLOAT_ARRAY_BASE + (long) srcOffset * 4L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L, (long) count * 12L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 3;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src[srcOffset + _i]);
        return dest;
    }

    /**
     * Copy one Float3 (3 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Float3 values ({@code count * 3} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Float3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L, (long) count * 12L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 3;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src.get(srcOffset + _i));
        return dest;
    }

    /**
     * Copy one Float3 (3 floats) from {@code src}
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
     * Bulk-copy {@code count} consecutive Float3 values ({@code count * 3} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L, (long) count * 12L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src.getFloat(srcOffset + _i * 4));
        return dest;
    }

    /**
     * Copy one Float3 (3 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L, 12L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.hasArray() && dest.order() == java.nio.ByteOrder.nativeOrder() && destOffset >= 0 && destOffset <= dest.limit() - 3) {
            java.util.Objects.checkFromIndexSize(dest.arrayOffset() + destOffset, 3, dest.array().length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest.array(), UnsafeCopy.FLOAT_ARRAY_BASE + (long) (dest.arrayOffset() + destOffset) * 4L, 12L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            int n = 3;
            for (int _i = 0; _i < n; _i++)
                dest.put(destOffset + _i, UnsafeOpsHolder.U.getFloat(src + (long) _i * 4L));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Float3 values ({@code count * 3} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) destOffset * 4L, (long) count * 12L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.hasArray() && dest.order() == java.nio.ByteOrder.nativeOrder() && destOffset >= 0 && (count > 715827882 ? -1 : count * 3) >= 0 && destOffset <= dest.limit() - (count > 715827882 ? -1 : count * 3)) {
            java.util.Objects.checkFromIndexSize(dest.arrayOffset() + destOffset, (count > 715827882 ? -1 : count * 3), dest.array().length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest.array(), UnsafeCopy.FLOAT_ARRAY_BASE + (long) (dest.arrayOffset() + destOffset) * 4L, (long) count * 12L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            if (count < 0) return dest;
            if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
            int n = count * 3;
            for (int _i = 0; _i < n; _i++)
                dest.put(destOffset + _i, UnsafeOpsHolder.U.getFloat(src + (long) _i * 4L));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Float3 (3 floats) from {@code src}
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
     * Bulk-copy {@code count} consecutive Float3 values ({@code count * 3} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, float[] src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 715827882 ? -1 : count * 3), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.FLOAT_ARRAY_BASE + (long) srcOffset * 4L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, (long) count * 12L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++)
            dest.putFloat(destOffset + _i * 4, src[srcOffset + _i]);
        return dest;
    }

    /**
     * Copy one Float3 (3 floats) from {@code src}
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
     * Bulk-copy {@code count} consecutive Float3 values ({@code count * 3} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, (long) count * 12L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++)
            dest.putFloat(destOffset + _i * 4, src.get(srcOffset + _i));
        return dest;
    }

    /**
     * Copy one Float3 (3 floats) from {@code src}
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
     * Bulk-copy {@code count} consecutive Float3 values ({@code count * 3} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} and {@code srcOffset} are byte offsets, not element indices.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, (long) count * 12L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++)
            dest.putFloat(destOffset + _i * 4, src.getFloat(srcOffset + _i * 4));
        return dest;
    }

    /**
     * Copy one Float3 (3 floats) from {@code src}
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
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, 12L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.hasArray() && dest.order() == java.nio.ByteOrder.nativeOrder() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            java.util.Objects.checkFromIndexSize(dest.arrayOffset() + destOffset, 12, dest.array().length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest.array(), UnsafeCopy.BYTE_ARRAY_BASE + (long) (dest.arrayOffset() + destOffset), 12L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            int n = 3;
            for (int _i = 0; _i < n; _i++)
                dest.putFloat(destOffset + _i * 4, UnsafeOpsHolder.U.getFloat(src + (long) _i * 4L));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Float3 values ({@code count * 3} floats) from {@code src}
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
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, (long) count * 12L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.hasArray() && dest.order() == java.nio.ByteOrder.nativeOrder() && destOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && destOffset <= dest.limit() - (count > 178956970 ? -1 : count * 12)) {
            java.util.Objects.checkFromIndexSize(dest.arrayOffset() + destOffset, (count > 178956970 ? -1 : count * 12), dest.array().length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest.array(), UnsafeCopy.BYTE_ARRAY_BASE + (long) (dest.arrayOffset() + destOffset), (long) count * 12L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            if (count < 0) return dest;
            if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
            int n = count * 3;
            for (int _i = 0; _i < n; _i++)
                dest.putFloat(destOffset + _i * 4, UnsafeOpsHolder.U.getFloat(src + (long) _i * 4L));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Float3 (3 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, float[] src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(srcOffset, 3, src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.FLOAT_ARRAY_BASE + (long) srcOffset * 4L, null, dest, 12L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Float3 values ({@code count * 3} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, float[] src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 715827882 ? -1 : count * 3), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.FLOAT_ARRAY_BASE + (long) srcOffset * 4L, null, dest, (long) count * 12L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Float3 (3 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, java.nio.FloatBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L, null, dest, 12L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.hasArray() && src.order() == java.nio.ByteOrder.nativeOrder() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            java.util.Objects.checkFromIndexSize(src.arrayOffset() + srcOffset, 3, src.array().length);
            UnsafeOpsHolder.U.copyMemory(src.array(), UnsafeCopy.FLOAT_ARRAY_BASE + (long) (src.arrayOffset() + srcOffset) * 4L, null, dest, 12L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            int n = 3;
            for (int _i = 0; _i < n; _i++)
                UnsafeOpsHolder.U.putFloat(dest + (long) _i * 4L, src.get(srcOffset + _i));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Float3 values ({@code count * 3} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + (long) srcOffset * 4L, null, dest, (long) count * 12L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.hasArray() && src.order() == java.nio.ByteOrder.nativeOrder() && srcOffset >= 0 && (count > 715827882 ? -1 : count * 3) >= 0 && srcOffset <= src.limit() - (count > 715827882 ? -1 : count * 3)) {
            java.util.Objects.checkFromIndexSize(src.arrayOffset() + srcOffset, (count > 715827882 ? -1 : count * 3), src.array().length);
            UnsafeOpsHolder.U.copyMemory(src.array(), UnsafeCopy.FLOAT_ARRAY_BASE + (long) (src.arrayOffset() + srcOffset) * 4L, null, dest, (long) count * 12L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            if (count < 0) return dest;
            if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
            int n = count * 3;
            for (int _i = 0; _i < n; _i++)
                UnsafeOpsHolder.U.putFloat(dest + (long) _i * 4L, src.get(srcOffset + _i));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Float3 (3 floats) from {@code src}
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
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, dest, 12L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.hasArray() && src.order() == java.nio.ByteOrder.nativeOrder() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            java.util.Objects.checkFromIndexSize(src.arrayOffset() + srcOffset, 12, src.array().length);
            UnsafeOpsHolder.U.copyMemory(src.array(), UnsafeCopy.BYTE_ARRAY_BASE + (long) (src.arrayOffset() + srcOffset), null, dest, 12L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            int n = 3;
            for (int _i = 0; _i < n; _i++)
                UnsafeOpsHolder.U.putFloat(dest + (long) _i * 4L, src.getFloat(srcOffset + _i * 4));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Float3 values ({@code count * 3} floats) from {@code src}
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
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, dest, (long) count * 12L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.hasArray() && src.order() == java.nio.ByteOrder.nativeOrder() && srcOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && srcOffset <= src.limit() - (count > 178956970 ? -1 : count * 12)) {
            java.util.Objects.checkFromIndexSize(src.arrayOffset() + srcOffset, (count > 178956970 ? -1 : count * 12), src.array().length);
            UnsafeOpsHolder.U.copyMemory(src.array(), UnsafeCopy.BYTE_ARRAY_BASE + (long) (src.arrayOffset() + srcOffset), null, dest, (long) count * 12L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            if (count < 0) return dest;
            if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
            int n = count * 3;
            for (int _i = 0; _i < n; _i++)
                UnsafeOpsHolder.U.putFloat(dest + (long) _i * 4L, src.getFloat(srcOffset + _i * 4));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Float3 (3 floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, dest, 12L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Float3 values ({@code count * 3} floats) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, dest, (long) count * 12L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
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

    /**
     * The floored remainder of x and y, exactly kotlin.Float.mod: q = floor(x / y) is off by
     * at most one (too large) while it fits the mantissa, so x - y * q with one correction is
     * the floored remainder - a zero one with the sign of x, like x % y; % (a runtime call) only
     * when it does not fit or y is infinite.
     */
    private static float flooredMod(float x, float y) {
        float q = (float) Math.floor(x / y);
        if (java.lang.Math.abs(q) < 0x1p24f && java.lang.Math.abs(y) <= Float.MAX_VALUE) {
            float r = java.lang.Math.fma(-y, q, x);
            if (r * java.lang.Math.signum(y) < 0) r = java.lang.Math.fma(-y, (q - 1.0f), x);
            return r == 0 ? java.lang.Math.copySign(r, x) : r;
        }
        float r = x % y;
        return r * java.lang.Math.signum(y) < 0 ? r + y : r;
    }

    /** Double-precision twin of {@link #flooredMod(float, float)}. */
    private static double flooredMod(double x, double y) {
        double q = Math.floor(x / y);
        if (java.lang.Math.abs(q) < 0x1p53 && java.lang.Math.abs(y) <= Double.MAX_VALUE) {
            double r = java.lang.Math.fma(-y, q, x);
            if (r * java.lang.Math.signum(y) < 0) r = java.lang.Math.fma(-y, (q - 1.0), x);
            return r == 0 ? java.lang.Math.copySign(r, x) : r;
        }
        double r = x % y;
        return r * java.lang.Math.signum(y) < 0 ? r + y : r;
    }
}
