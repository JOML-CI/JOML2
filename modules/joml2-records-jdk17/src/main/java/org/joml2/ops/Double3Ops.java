// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.ops;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.kernels.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Static, allocation-free operations on raw storage holding a {@link Double3}.
 *
 * <p>Each method takes one or more buffers ({@code double[]},
 * {@link java.nio.DoubleBuffer}, or {@link java.nio.ByteBuffer})
 * plus an element/byte offset and operates
 * directly on that storage. No {@link Double3} instance is allocated.</p>
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
 * Double3 write and read.</p>
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
 * <p>Each method summary below is the one the {@link Double3} API carries, so
 * the two can never describe the same operation differently: "this vector" there is the
 * vector held in {@code src} at {@code srcOffset}, and the result is written to
 * {@code dest} at {@code destOffset}. The full text sits on the {@code double[]} overload of
 * each method; the other storage overloads point at it, differing from it in
 * storage alone.</p>
 */
public final class Double3Ops {
    private Double3Ops() {}

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
    public static double[] add(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = otherX + src[srcOffset];
        dest[destOffset + 1] = otherY + _selfy;
        dest[destOffset + 2] = otherZ + _selfz;
        return dest;
    }

    /** {@link #add(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer add(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.add_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsTypedBuffer.add_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #add(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer add(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.add_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsByteBuffer.add_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #add(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long add(long dest, long src, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.add_unsafe(dest, src, otherX, otherY, otherZ);
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
    public static double[] add(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        dest[destOffset] = other[otherOffset] + src[srcOffset];
        dest[destOffset + 1] = _othery + _selfy;
        dest[destOffset + 2] = _otherz + _selfz;
        return dest;
    }

    /** {@link #add(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer add(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.add_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3OpsKernelsTypedBuffer.add_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer add(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.add_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3OpsKernelsByteBuffer.add_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #add(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long add(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.add_unsafe(dest, src, other);
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
    public static double[] div(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = src[srcOffset] / scalar;
        dest[destOffset + 1] = _selfy / scalar;
        dest[destOffset + 2] = _selfz / scalar;
        return dest;
    }

    /** {@link #div(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer div(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.div_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Double3OpsKernelsTypedBuffer.div_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #div(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer div(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.div_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Double3OpsKernelsByteBuffer.div_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #div(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long div(long dest, long src, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.div_unsafe(dest, src, scalar);
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
    public static double[] div(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = src[srcOffset] / otherX;
        dest[destOffset + 1] = _selfy / otherY;
        dest[destOffset + 2] = _selfz / otherZ;
        return dest;
    }

    /** {@link #div(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer div(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.div_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsTypedBuffer.div_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #div(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer div(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.div_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsByteBuffer.div_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #div(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long div(long dest, long src, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.div_unsafe(dest, src, otherX, otherY, otherZ);
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
    public static double[] div(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        dest[destOffset] = src[srcOffset] / other[otherOffset];
        dest[destOffset + 1] = _selfy / _othery;
        dest[destOffset + 2] = _selfz / _otherz;
        return dest;
    }

    /** {@link #div(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer div(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.div_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3OpsKernelsTypedBuffer.div_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #div(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer div(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.div_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3OpsKernelsByteBuffer.div_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #div(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long div(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.div_unsafe(dest, src, other);
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
    public static double[] fma(double[] dest, int destOffset, double[] src, int srcOffset, double b, double cX, double cY, double cZ) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.fma(src[srcOffset], b, cX);
        dest[destOffset + 1] = Math.fma(_selfy, b, cY);
        dest[destOffset + 2] = Math.fma(_selfz, b, cZ);
        return dest;
    }

    /** {@link #fma(double[], int, double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double b, double cX, double cY, double cZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.fma_unsafe(dest, destOffset, src, srcOffset, b, cX, cY, cZ);
        return Double3OpsKernelsTypedBuffer.fma_api(dest, destOffset, src, srcOffset, b, cX, cY, cZ);
    }

    /** {@link #fma(double[], int, double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double b, double cX, double cY, double cZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.fma_unsafe(dest, destOffset, src, srcOffset, b, cX, cY, cZ);
        return Double3OpsKernelsByteBuffer.fma_api(dest, destOffset, src, srcOffset, b, cX, cY, cZ);
    }

    /** {@link #fma(double[], int, double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long fma(long dest, long src, double b, double cX, double cY, double cZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.fma_unsafe(dest, src, b, cX, cY, cZ);
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
    public static double[] fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] c, int cOffset, double b) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _cy = c[cOffset + 1];
        double _cz = c[cOffset + 2];
        dest[destOffset] = Math.fma(src[srcOffset], b, c[cOffset]);
        dest[destOffset + 1] = Math.fma(_selfy, b, _cy);
        dest[destOffset + 2] = Math.fma(_selfz, b, _cz);
        return dest;
    }

    /** {@link #fma(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer c, int cOffset, double b) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && c.isDirect() && c.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.fma_unsafe(dest, destOffset, src, srcOffset, c, cOffset, b);
        return Double3OpsKernelsTypedBuffer.fma_api(dest, destOffset, src, srcOffset, c, cOffset, b);
    }

    /** {@link #fma(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer c, int cOffset, double b) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && c.isDirect() && c.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.fma_unsafe(dest, destOffset, src, srcOffset, c, cOffset, b);
        return Double3OpsKernelsByteBuffer.fma_api(dest, destOffset, src, srcOffset, c, cOffset, b);
    }

    /** {@link #fma(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long fma(long dest, long src, long c, double b) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.fma_unsafe(dest, src, c, b);
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
    public static double[] fma(double[] dest, int destOffset, double[] src, int srcOffset, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.fma(src[srcOffset], bX, cX);
        dest[destOffset + 1] = Math.fma(_selfy, bY, cY);
        dest[destOffset + 2] = Math.fma(_selfz, bZ, cZ);
        return dest;
    }

    /** {@link #fma(double[], int, double[], int, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.fma_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, cX, cY, cZ);
        return Double3OpsKernelsTypedBuffer.fma_api(dest, destOffset, src, srcOffset, bX, bY, bZ, cX, cY, cZ);
    }

    /** {@link #fma(double[], int, double[], int, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.fma_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, cX, cY, cZ);
        return Double3OpsKernelsByteBuffer.fma_api(dest, destOffset, src, srcOffset, bX, bY, bZ, cX, cY, cZ);
    }

    /** {@link #fma(double[], int, double[], int, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long fma(long dest, long src, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.fma_unsafe(dest, src, bX, bY, bZ, cX, cY, cZ);
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
    public static double[] fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] b, int bOffset, double[] c, int cOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _by = b[bOffset + 1];
        double _bz = b[bOffset + 2];
        double _cy = c[cOffset + 1];
        double _cz = c[cOffset + 2];
        dest[destOffset] = Math.fma(src[srcOffset], b[bOffset], c[cOffset]);
        dest[destOffset + 1] = Math.fma(_selfy, _by, _cy);
        dest[destOffset + 2] = Math.fma(_selfz, _bz, _cz);
        return dest;
    }

    /** {@link #fma(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, java.nio.DoubleBuffer c, int cOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && b.isDirect() && b.order() == java.nio.ByteOrder.nativeOrder() && c.isDirect() && c.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.fma_unsafe(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return Double3OpsKernelsTypedBuffer.fma_api(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    /** {@link #fma(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && b.isDirect() && b.order() == java.nio.ByteOrder.nativeOrder() && c.isDirect() && c.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.fma_unsafe(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return Double3OpsKernelsByteBuffer.fma_api(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    /** {@link #fma(double[], int, double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long fma(long dest, long src, long b, long c) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.fma_unsafe(dest, src, b, c);
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
    public static double[] mul(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = scalar * src[srcOffset];
        dest[destOffset + 1] = scalar * _selfy;
        dest[destOffset + 2] = scalar * _selfz;
        return dest;
    }

    /** {@link #mul(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mul(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.mul_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Double3OpsKernelsTypedBuffer.mul_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.mul_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Double3OpsKernelsByteBuffer.mul_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #mul(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mul(long dest, long src, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.mul_unsafe(dest, src, scalar);
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
    public static double[] mul(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = otherX * src[srcOffset];
        dest[destOffset + 1] = otherY * _selfy;
        dest[destOffset + 2] = otherZ * _selfz;
        return dest;
    }

    /** {@link #mul(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mul(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.mul_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsTypedBuffer.mul_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #mul(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.mul_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsByteBuffer.mul_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #mul(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mul(long dest, long src, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.mul_unsafe(dest, src, otherX, otherY, otherZ);
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
    public static double[] mul(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        dest[destOffset] = other[otherOffset] * src[srcOffset];
        dest[destOffset + 1] = _othery * _selfy;
        dest[destOffset + 2] = _otherz * _selfz;
        return dest;
    }

    /** {@link #mul(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mul(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.mul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3OpsKernelsTypedBuffer.mul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #mul(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.mul_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3OpsKernelsByteBuffer.mul_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #mul(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mul(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.mul_unsafe(dest, src, other);
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
    public static double[] negate(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = -src[srcOffset];
        dest[destOffset + 1] = -_selfy;
        dest[destOffset + 2] = -_selfz;
        return dest;
    }

    /** {@link #negate(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer negate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.negate_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.negate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer negate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.negate_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.negate_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #negate(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long negate(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.negate_unsafe(dest, src);
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
    public static double[] sub(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = src[srcOffset] - otherX;
        dest[destOffset + 1] = _selfy - otherY;
        dest[destOffset + 2] = _selfz - otherZ;
        return dest;
    }

    /** {@link #sub(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer sub(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.sub_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsTypedBuffer.sub_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #sub(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sub(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.sub_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsByteBuffer.sub_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #sub(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long sub(long dest, long src, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.sub_unsafe(dest, src, otherX, otherY, otherZ);
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
    public static double[] sub(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        dest[destOffset] = src[srcOffset] - other[otherOffset];
        dest[destOffset + 1] = _selfy - _othery;
        dest[destOffset + 2] = _selfz - _otherz;
        return dest;
    }

    /** {@link #sub(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer sub(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.sub_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3OpsKernelsTypedBuffer.sub_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sub(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.sub_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3OpsKernelsByteBuffer.sub_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #sub(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long sub(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.sub_unsafe(dest, src, other);
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
    public static double[] makeUniformDirection(double[] dest, int destOffset, double u, double v) {
        double _t1 = v * 6.283185307179586;
        double _t2 = Math.sin(_t1);
        double _t5 = 2.0 * java.lang.Math.sqrt(u * (1.0 - u));
        dest[destOffset] = _t5 * Math.cosFromSin(_t2, _t1);
        dest[destOffset + 1] = _t5 * _t2;
        dest[destOffset + 2] = Math.fma(2.0, u, -1.0);
        return dest;
    }

    /** {@link #makeUniformDirection(double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeUniformDirection(java.nio.DoubleBuffer dest, int destOffset, double u, double v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.makeUniformDirection_unsafe(dest, destOffset, u, v);
        return Double3OpsKernelsTypedBuffer.makeUniformDirection_api(dest, destOffset, u, v);
    }

    /** {@link #makeUniformDirection(double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeUniformDirection(java.nio.ByteBuffer dest, int destOffset, double u, double v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.makeUniformDirection_unsafe(dest, destOffset, u, v);
        return Double3OpsKernelsByteBuffer.makeUniformDirection_api(dest, destOffset, u, v);
    }

    /** {@link #makeUniformDirection(double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long makeUniformDirection(long dest, double u, double v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.makeUniformDirection_unsafe(dest, u, v);
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
    public static double[] set(double[] dest, int destOffset, double vX, double vY, double vZ) {
        dest[destOffset] = vX;
        dest[destOffset + 1] = vY;
        dest[destOffset + 2] = vZ;
        return dest;
    }

    /** {@link #set(double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer set(java.nio.DoubleBuffer dest, int destOffset, double vX, double vY, double vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.set_unsafe(dest, destOffset, vX, vY, vZ);
        return Double3OpsKernelsTypedBuffer.set_api(dest, destOffset, vX, vY, vZ);
    }

    /** {@link #set(double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer set(java.nio.ByteBuffer dest, int destOffset, double vX, double vY, double vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.set_unsafe(dest, destOffset, vX, vY, vZ);
        return Double3OpsKernelsByteBuffer.set_api(dest, destOffset, vX, vY, vZ);
    }

    /** {@link #set(double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long set(long dest, double vX, double vY, double vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.set_unsafe(dest, vX, vY, vZ);
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
    public static double[] set(double[] dest, int destOffset, double[] v, int vOffset) {
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        dest[destOffset] = v[vOffset];
        dest[destOffset + 1] = _vy;
        dest[destOffset + 2] = _vz;
        return dest;
    }

    /** {@link #set(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer set(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.set_unsafe(dest, destOffset, v, vOffset);
        return Double3OpsKernelsTypedBuffer.set_api(dest, destOffset, v, vOffset);
    }

    /** {@link #set(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer set(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer v, int vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && v.isDirect() && v.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.set_unsafe(dest, destOffset, v, vOffset);
        return Double3OpsKernelsByteBuffer.set_api(dest, destOffset, v, vOffset);
    }

    /** {@link #set(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long set(long dest, long v) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.set_unsafe(dest, v);
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
    public static double[] set(double[] dest, int destOffset, double s) {
        dest[destOffset] = s;
        dest[destOffset + 1] = s;
        dest[destOffset + 2] = s;
        return dest;
    }

    /** {@link #set(double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer set(java.nio.DoubleBuffer dest, int destOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.set_unsafe(dest, destOffset, s);
        return Double3OpsKernelsTypedBuffer.set_api(dest, destOffset, s);
    }

    /** {@link #set(double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer set(java.nio.ByteBuffer dest, int destOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.set_unsafe(dest, destOffset, s);
        return Double3OpsKernelsByteBuffer.set_api(dest, destOffset, s);
    }

    /** {@link #set(double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long set(long dest, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.set_unsafe(dest, s);
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
    public static double[] makeZero(double[] dest, int destOffset) {
        dest[destOffset] = 0.0;
        dest[destOffset + 1] = 0.0;
        dest[destOffset + 2] = 0.0;
        return dest;
    }

    /** {@link #makeZero(double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer makeZero(java.nio.DoubleBuffer dest, int destOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.makeZero_unsafe(dest, destOffset);
        return Double3OpsKernelsTypedBuffer.makeZero_api(dest, destOffset);
    }

    /** {@link #makeZero(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer makeZero(java.nio.ByteBuffer dest, int destOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.makeZero_unsafe(dest, destOffset);
        return Double3OpsKernelsByteBuffer.makeZero_api(dest, destOffset);
    }

    /** {@link #makeZero(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long makeZero(long dest) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.makeZero_unsafe(dest);
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
    public static double[] bezier(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest[destOffset] = Math.fma(p1X, _t7, src[srcOffset] * _t8) + Math.fma(p2X, _t6, p3X * _t2);
        dest[destOffset + 1] = Math.fma(p1Y, _t7, _selfy * _t8) + Math.fma(p2Y, _t6, p3Y * _t2);
        dest[destOffset + 2] = Math.fma(p1Z, _t7, _selfz * _t8) + Math.fma(p2Z, _t6, p3Z * _t2);
        return dest;
    }

    /** {@link #bezier(double[], int, double[], int, double, double, double, double, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer bezier(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.bezier_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return Double3OpsKernelsTypedBuffer.bezier_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@link #bezier(double[], int, double[], int, double, double, double, double, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezier(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.bezier_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return Double3OpsKernelsByteBuffer.bezier_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@link #bezier(double[], int, double[], int, double, double, double, double, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long bezier(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.bezier_unsafe(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
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
    public static double[] bezier(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _p1y = p1[p1Offset + 1];
        double _p1z = p1[p1Offset + 2];
        double _p2y = p2[p2Offset + 1];
        double _p2z = p2[p2Offset + 2];
        double _p3y = p3[p3Offset + 1];
        double _p3z = p3[p3Offset + 2];
        double _t0 = 1.0 - t;
        double _t1 = t * t;
        double _t2 = t * _t1;
        double _t3 = _t0 * _t0;
        double _t6 = 3.0 * _t0 * _t1;
        double _t7 = 3.0 * t * _t3;
        double _t8 = _t0 * _t3;
        dest[destOffset] = Math.fma(p1[p1Offset], _t7, src[srcOffset] * _t8) + Math.fma(p2[p2Offset], _t6, p3[p3Offset] * _t2);
        dest[destOffset + 1] = Math.fma(_p1y, _t7, _selfy * _t8) + Math.fma(_p2y, _t6, _p3y * _t2);
        dest[destOffset + 2] = Math.fma(_p1z, _t7, _selfz * _t8) + Math.fma(_p2z, _t6, _p3z * _t2);
        return dest;
    }

    /** {@link #bezier(double[], int, double[], int, double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer bezier(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.bezier_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Double3OpsKernelsTypedBuffer.bezier_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #bezier(double[], int, double[], int, double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezier(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.bezier_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Double3OpsKernelsByteBuffer.bezier_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #bezier(double[], int, double[], int, double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long bezier(long dest, long src, long p1, long p2, long p3, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.bezier_unsafe(dest, src, p1, p2, p3, t);
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
    public static double[] bezier2(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest[destOffset] = Math.fma(p2X, _t0, Math.fma(p1X, _t3, src[srcOffset] * _t4));
        dest[destOffset + 1] = Math.fma(p2Y, _t0, Math.fma(p1Y, _t3, _selfy * _t4));
        dest[destOffset + 2] = Math.fma(p2Z, _t0, Math.fma(p1Z, _t3, _selfz * _t4));
        return dest;
    }

    /** {@link #bezier2(double[], int, double[], int, double, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer bezier2(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.bezier2_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return Double3OpsKernelsTypedBuffer.bezier2_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
    }

    /** {@link #bezier2(double[], int, double[], int, double, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezier2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.bezier2_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return Double3OpsKernelsByteBuffer.bezier2_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
    }

    /** {@link #bezier2(double[], int, double[], int, double, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long bezier2(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.bezier2_unsafe(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
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
    public static double[] bezier2(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _p1y = p1[p1Offset + 1];
        double _p1z = p1[p1Offset + 2];
        double _p2y = p2[p2Offset + 1];
        double _p2z = p2[p2Offset + 2];
        double _t0 = t * t;
        double _t1 = 1.0 - t;
        double _t3 = (t + t) * _t1;
        double _t4 = _t1 * _t1;
        dest[destOffset] = Math.fma(p2[p2Offset], _t0, Math.fma(p1[p1Offset], _t3, src[srcOffset] * _t4));
        dest[destOffset + 1] = Math.fma(_p2y, _t0, Math.fma(_p1y, _t3, _selfy * _t4));
        dest[destOffset + 2] = Math.fma(_p2z, _t0, Math.fma(_p1z, _t3, _selfz * _t4));
        return dest;
    }

    /** {@link #bezier2(double[], int, double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer bezier2(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.bezier2_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return Double3OpsKernelsTypedBuffer.bezier2_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    /** {@link #bezier2(double[], int, double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezier2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.bezier2_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return Double3OpsKernelsByteBuffer.bezier2_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    /** {@link #bezier2(double[], int, double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long bezier2(long dest, long src, long p1, long p2, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.bezier2_unsafe(dest, src, p1, p2, t);
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
    public static double[] bezier2Tangent(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest[destOffset] = Math.fma(p1X - src[srcOffset], _t2, (p2X - p1X) * _t1);
        dest[destOffset + 1] = Math.fma(p1Y - _selfy, _t2, (p2Y - p1Y) * _t1);
        dest[destOffset + 2] = Math.fma(p1Z - _selfz, _t2, (p2Z - p1Z) * _t1);
        return dest;
    }

    /** {@link #bezier2Tangent(double[], int, double[], int, double, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer bezier2Tangent(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.bezier2Tangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return Double3OpsKernelsTypedBuffer.bezier2Tangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
    }

    /** {@link #bezier2Tangent(double[], int, double[], int, double, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezier2Tangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.bezier2Tangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
        return Double3OpsKernelsByteBuffer.bezier2Tangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
    }

    /** {@link #bezier2Tangent(double[], int, double[], int, double, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long bezier2Tangent(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.bezier2Tangent_unsafe(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, t);
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
    public static double[] bezier2Tangent(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _p1x = p1[p1Offset];
        double _p1y = p1[p1Offset + 1];
        double _p1z = p1[p1Offset + 2];
        double _p2y = p2[p2Offset + 1];
        double _p2z = p2[p2Offset + 2];
        double _t1 = t + t;
        double _t2 = 2.0 * (1.0 - t);
        dest[destOffset] = Math.fma(_p1x - src[srcOffset], _t2, (p2[p2Offset] - _p1x) * _t1);
        dest[destOffset + 1] = Math.fma(_p1y - _selfy, _t2, (_p2y - _p1y) * _t1);
        dest[destOffset + 2] = Math.fma(_p1z - _selfz, _t2, (_p2z - _p1z) * _t1);
        return dest;
    }

    /** {@link #bezier2Tangent(double[], int, double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer bezier2Tangent(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.bezier2Tangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return Double3OpsKernelsTypedBuffer.bezier2Tangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    /** {@link #bezier2Tangent(double[], int, double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezier2Tangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.bezier2Tangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
        return Double3OpsKernelsByteBuffer.bezier2Tangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, t);
    }

    /** {@link #bezier2Tangent(double[], int, double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long bezier2Tangent(long dest, long src, long p1, long p2, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.bezier2Tangent_unsafe(dest, src, p1, p2, t);
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
    public static double[] bezierTangent(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest[destOffset] = Math.fma(p3X - p2X, _t2, Math.fma(p1X - src[srcOffset], _t6, (p2X - p1X) * _t5));
        dest[destOffset + 1] = Math.fma(p3Y - p2Y, _t2, Math.fma(p1Y - _selfy, _t6, (p2Y - p1Y) * _t5));
        dest[destOffset + 2] = Math.fma(p3Z - p2Z, _t2, Math.fma(p1Z - _selfz, _t6, (p2Z - p1Z) * _t5));
        return dest;
    }

    /** {@link #bezierTangent(double[], int, double[], int, double, double, double, double, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer bezierTangent(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.bezierTangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return Double3OpsKernelsTypedBuffer.bezierTangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@link #bezierTangent(double[], int, double[], int, double, double, double, double, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezierTangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.bezierTangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return Double3OpsKernelsByteBuffer.bezierTangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@link #bezierTangent(double[], int, double[], int, double, double, double, double, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long bezierTangent(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.bezierTangent_unsafe(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
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
    public static double[] bezierTangent(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _p1x = p1[p1Offset];
        double _p1y = p1[p1Offset + 1];
        double _p1z = p1[p1Offset + 2];
        double _p2x = p2[p2Offset];
        double _p2y = p2[p2Offset + 1];
        double _p2z = p2[p2Offset + 2];
        double _p3y = p3[p3Offset + 1];
        double _p3z = p3[p3Offset + 2];
        double _t1 = 1.0 - t;
        double _t2 = 3.0 * t * t;
        double _t5 = 6.0 * t * _t1;
        double _t6 = 3.0 * _t1 * _t1;
        dest[destOffset] = Math.fma(p3[p3Offset] - _p2x, _t2, Math.fma(_p1x - src[srcOffset], _t6, (_p2x - _p1x) * _t5));
        dest[destOffset + 1] = Math.fma(_p3y - _p2y, _t2, Math.fma(_p1y - _selfy, _t6, (_p2y - _p1y) * _t5));
        dest[destOffset + 2] = Math.fma(_p3z - _p2z, _t2, Math.fma(_p1z - _selfz, _t6, (_p2z - _p1z) * _t5));
        return dest;
    }

    /** {@link #bezierTangent(double[], int, double[], int, double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer bezierTangent(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.bezierTangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Double3OpsKernelsTypedBuffer.bezierTangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #bezierTangent(double[], int, double[], int, double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer bezierTangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.bezierTangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Double3OpsKernelsByteBuffer.bezierTangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #bezierTangent(double[], int, double[], int, double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long bezierTangent(long dest, long src, long p1, long p2, long p3, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.bezierTangent_unsafe(dest, src, p1, p2, p3, t);
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
    public static double[] catmullRom(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t0 = t * t;
        double _t1 = t * _t0;
        dest[destOffset] = 0.5 * (Math.fma(2.0, p1X, t * (p2X - _selfx)) + Math.fma(Math.fma(-5.0, p1X, Math.fma(2.0, _selfx, Math.fma(4.0, p2X, -p3X))), _t0, Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - _selfx)) * _t1));
        dest[destOffset + 1] = 0.5 * (Math.fma(2.0, p1Y, t * (p2Y - _selfy)) + Math.fma(Math.fma(-5.0, p1Y, Math.fma(2.0, _selfy, Math.fma(4.0, p2Y, -p3Y))), _t0, Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - _selfy)) * _t1));
        dest[destOffset + 2] = 0.5 * (Math.fma(2.0, p1Z, t * (p2Z - _selfz)) + Math.fma(Math.fma(-5.0, p1Z, Math.fma(2.0, _selfz, Math.fma(4.0, p2Z, -p3Z))), _t0, Math.fma(-3.0, p2Z, Math.fma(3.0, p1Z, p3Z - _selfz)) * _t1));
        return dest;
    }

    /** {@link #catmullRom(double[], int, double[], int, double, double, double, double, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer catmullRom(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.catmullRom_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return Double3OpsKernelsTypedBuffer.catmullRom_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@link #catmullRom(double[], int, double[], int, double, double, double, double, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer catmullRom(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.catmullRom_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return Double3OpsKernelsByteBuffer.catmullRom_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@link #catmullRom(double[], int, double[], int, double, double, double, double, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long catmullRom(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.catmullRom_unsafe(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
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
    public static double[] catmullRom(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        return catmullRom(dest, destOffset, src, srcOffset, p1[p1Offset], p1[p1Offset + 1], p1[p1Offset + 2], p2[p2Offset], p2[p2Offset + 1], p2[p2Offset + 2], p3[p3Offset], p3[p3Offset + 1], p3[p3Offset + 2], t);
    }

    /** {@link #catmullRom(double[], int, double[], int, double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer catmullRom(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.catmullRom_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Double3OpsKernelsTypedBuffer.catmullRom_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #catmullRom(double[], int, double[], int, double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer catmullRom(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.catmullRom_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Double3OpsKernelsByteBuffer.catmullRom_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #catmullRom(double[], int, double[], int, double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long catmullRom(long dest, long src, long p1, long p2, long p3, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.catmullRom_unsafe(dest, src, p1, p2, p3, t);
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
    public static double[] catmullRomTangent(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t0 = t * t;
        dest[destOffset] = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1X, Math.fma(2.0, _selfx, Math.fma(4.0, p2X, -p3X))), Math.fma(3.0 * Math.fma(-3.0, p2X, Math.fma(3.0, p1X, p3X - _selfx)), _t0, p2X - _selfx));
        dest[destOffset + 1] = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Y, Math.fma(2.0, _selfy, Math.fma(4.0, p2Y, -p3Y))), Math.fma(3.0 * Math.fma(-3.0, p2Y, Math.fma(3.0, p1Y, p3Y - _selfy)), _t0, p2Y - _selfy));
        dest[destOffset + 2] = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, p1Z, Math.fma(2.0, _selfz, Math.fma(4.0, p2Z, -p3Z))), Math.fma(3.0 * Math.fma(-3.0, p2Z, Math.fma(3.0, p1Z, p3Z - _selfz)), _t0, p2Z - _selfz));
        return dest;
    }

    /** {@link #catmullRomTangent(double[], int, double[], int, double, double, double, double, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer catmullRomTangent(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.catmullRomTangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return Double3OpsKernelsTypedBuffer.catmullRomTangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@link #catmullRomTangent(double[], int, double[], int, double, double, double, double, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer catmullRomTangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.catmullRomTangent_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
        return Double3OpsKernelsByteBuffer.catmullRomTangent_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
    }

    /** {@link #catmullRomTangent(double[], int, double[], int, double, double, double, double, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long catmullRomTangent(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z, double p3X, double p3Y, double p3Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.catmullRomTangent_unsafe(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z, p3X, p3Y, p3Z, t);
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
    public static double[] catmullRomTangent(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset, double[] p3, int p3Offset, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _p1x = p1[p1Offset];
        double _p1y = p1[p1Offset + 1];
        double _p1z = p1[p1Offset + 2];
        double _p2x = p2[p2Offset];
        double _p2y = p2[p2Offset + 1];
        double _p2z = p2[p2Offset + 2];
        double _p3x = p3[p3Offset];
        double _p3y = p3[p3Offset + 1];
        double _p3z = p3[p3Offset + 2];
        double _t0 = t * t;
        dest[destOffset] = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1x, Math.fma(2.0, _selfx, Math.fma(4.0, _p2x, -_p3x))), Math.fma(3.0 * Math.fma(-3.0, _p2x, Math.fma(3.0, _p1x, _p3x - _selfx)), _t0, _p2x - _selfx));
        dest[destOffset + 1] = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1y, Math.fma(2.0, _selfy, Math.fma(4.0, _p2y, -_p3y))), Math.fma(3.0 * Math.fma(-3.0, _p2y, Math.fma(3.0, _p1y, _p3y - _selfy)), _t0, _p2y - _selfy));
        return catmullRomTangent_sce27ea62_1(dest, destOffset, t, _selfz, _p1z, _p2z, _p3z, _t0);
    }

    /** Piece 2 of {@code catmullRomTangent}, split to fit the inline budget; reached only through it. */
    private static double[] catmullRomTangent_sce27ea62_1(double[] dest, int destOffset, double t, double _selfz, double _p1z, double _p2z, double _p3z, double _t0) {
        dest[destOffset + 2] = 0.5 * Math.fma(t, 2.0 * Math.fma(-5.0, _p1z, Math.fma(2.0, _selfz, Math.fma(4.0, _p2z, -_p3z))), Math.fma(3.0 * Math.fma(-3.0, _p2z, Math.fma(3.0, _p1z, _p3z - _selfz)), _t0, _p2z - _selfz));
        return dest;
    }

    /** {@link #catmullRomTangent(double[], int, double[], int, double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer catmullRomTangent(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset, java.nio.DoubleBuffer p3, int p3Offset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.catmullRomTangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Double3OpsKernelsTypedBuffer.catmullRomTangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #catmullRomTangent(double[], int, double[], int, double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer catmullRomTangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset, java.nio.ByteBuffer p3, int p3Offset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder() && p3.isDirect() && p3.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.catmullRomTangent_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
        return Double3OpsKernelsByteBuffer.catmullRomTangent_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset, p3, p3Offset, t);
    }

    /** {@link #catmullRomTangent(double[], int, double[], int, double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long catmullRomTangent(long dest, long src, long p1, long p2, long p3, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.catmullRomTangent_unsafe(dest, src, p1, p2, p3, t);
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
    public static double[] hermite(double[] dest, int destOffset, double[] src, int srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        dest[destOffset] = Math.fma(src[srcOffset], _t10, t0X * _t7) + Math.fma(t1X, _t5, v1X * _t9);
        dest[destOffset + 1] = Math.fma(_selfy, _t10, t0Y * _t7) + Math.fma(t1Y, _t5, v1Y * _t9);
        dest[destOffset + 2] = Math.fma(_selfz, _t10, t0Z * _t7) + Math.fma(t1Z, _t5, v1Z * _t9);
        return dest;
    }

    /** {@link #hermite(double[], int, double[], int, double, double, double, double, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer hermite(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.hermite_unsafe(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return Double3OpsKernelsTypedBuffer.hermite_api(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
    }

    /** {@link #hermite(double[], int, double[], int, double, double, double, double, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hermite(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.hermite_unsafe(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return Double3OpsKernelsByteBuffer.hermite_api(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
    }

    /** {@link #hermite(double[], int, double[], int, double, double, double, double, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long hermite(long dest, long src, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.hermite_unsafe(dest, src, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
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
    public static double[] hermite(double[] dest, int destOffset, double[] src, int srcOffset, double[] t0, int t0Offset, double[] v1, int v1Offset, double[] t1, int t1Offset, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t0y = t0[t0Offset + 1];
        double _t0z = t0[t0Offset + 2];
        double _v1y = v1[v1Offset + 1];
        double _v1z = v1[v1Offset + 2];
        double _t1y = t1[t1Offset + 1];
        double _t1z = t1[t1Offset + 2];
        double _t0 = t * t;
        double _t2 = t * _t0;
        double _t5 = t * Math.fma(t, t, -t);
        double _t7 = Math.fma(t - 2.0, _t0, t);
        double _t9 = Math.fma(3.0, _t0, -(_t2 + _t2));
        double _t10 = Math.fma(2.0, _t2, Math.fma(-3.0, _t0, 1.0));
        dest[destOffset] = Math.fma(src[srcOffset], _t10, t0[t0Offset] * _t7) + Math.fma(t1[t1Offset], _t5, v1[v1Offset] * _t9);
        dest[destOffset + 1] = Math.fma(_selfy, _t10, _t0y * _t7) + Math.fma(_t1y, _t5, _v1y * _t9);
        dest[destOffset + 2] = Math.fma(_selfz, _t10, _t0z * _t7) + Math.fma(_t1z, _t5, _v1z * _t9);
        return dest;
    }

    /** {@link #hermite(double[], int, double[], int, double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer hermite(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && t0.isDirect() && t0.order() == java.nio.ByteOrder.nativeOrder() && v1.isDirect() && v1.order() == java.nio.ByteOrder.nativeOrder() && t1.isDirect() && t1.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.hermite_unsafe(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return Double3OpsKernelsTypedBuffer.hermite_api(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@link #hermite(double[], int, double[], int, double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hermite(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && t0.isDirect() && t0.order() == java.nio.ByteOrder.nativeOrder() && v1.isDirect() && v1.order() == java.nio.ByteOrder.nativeOrder() && t1.isDirect() && t1.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.hermite_unsafe(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return Double3OpsKernelsByteBuffer.hermite_api(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@link #hermite(double[], int, double[], int, double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long hermite(long dest, long src, long t0, long v1, long t1, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.hermite_unsafe(dest, src, t0, v1, t1, t);
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
    public static double[] hermiteTangent(double[] dest, int destOffset, double[] src, int srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        dest[destOffset] = Math.fma(src[srcOffset], _t6, t0X * _t9) + Math.fma(t1X, _t8, v1X * _t7);
        dest[destOffset + 1] = Math.fma(_selfy, _t6, t0Y * _t9) + Math.fma(t1Y, _t8, v1Y * _t7);
        dest[destOffset + 2] = Math.fma(_selfz, _t6, t0Z * _t9) + Math.fma(t1Z, _t8, v1Z * _t7);
        return dest;
    }

    /** {@link #hermiteTangent(double[], int, double[], int, double, double, double, double, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer hermiteTangent(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.hermiteTangent_unsafe(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return Double3OpsKernelsTypedBuffer.hermiteTangent_api(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
    }

    /** {@link #hermiteTangent(double[], int, double[], int, double, double, double, double, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hermiteTangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.hermiteTangent_unsafe(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
        return Double3OpsKernelsByteBuffer.hermiteTangent_api(dest, destOffset, src, srcOffset, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
    }

    /** {@link #hermiteTangent(double[], int, double[], int, double, double, double, double, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long hermiteTangent(long dest, long src, double t0X, double t0Y, double t0Z, double v1X, double v1Y, double v1Z, double t1X, double t1Y, double t1Z, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.hermiteTangent_unsafe(dest, src, t0X, t0Y, t0Z, v1X, v1Y, v1Z, t1X, t1Y, t1Z, t);
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
    public static double[] hermiteTangent(double[] dest, int destOffset, double[] src, int srcOffset, double[] t0, int t0Offset, double[] v1, int v1Offset, double[] t1, int t1Offset, double t) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t0y = t0[t0Offset + 1];
        double _t0z = t0[t0Offset + 2];
        double _v1y = v1[v1Offset + 1];
        double _v1z = v1[v1Offset + 2];
        double _t1y = t1[t1Offset + 1];
        double _t1z = t1[t1Offset + 2];
        double _t0 = t * t;
        double _t6 = 6.0 * Math.fma(t, t, -t);
        double _t7 = 6.0 * Math.fma(-t, t, t);
        double _t8 = Math.fma(3.0, _t0, -(t + t));
        double _t9 = Math.fma(3.0, _t0, Math.fma(-4.0, t, 1.0));
        dest[destOffset] = Math.fma(src[srcOffset], _t6, t0[t0Offset] * _t9) + Math.fma(t1[t1Offset], _t8, v1[v1Offset] * _t7);
        dest[destOffset + 1] = Math.fma(_selfy, _t6, _t0y * _t9) + Math.fma(_t1y, _t8, _v1y * _t7);
        dest[destOffset + 2] = Math.fma(_selfz, _t6, _t0z * _t9) + Math.fma(_t1z, _t8, _v1z * _t7);
        return dest;
    }

    /** {@link #hermiteTangent(double[], int, double[], int, double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer hermiteTangent(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer t0, int t0Offset, java.nio.DoubleBuffer v1, int v1Offset, java.nio.DoubleBuffer t1, int t1Offset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && t0.isDirect() && t0.order() == java.nio.ByteOrder.nativeOrder() && v1.isDirect() && v1.order() == java.nio.ByteOrder.nativeOrder() && t1.isDirect() && t1.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.hermiteTangent_unsafe(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return Double3OpsKernelsTypedBuffer.hermiteTangent_api(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@link #hermiteTangent(double[], int, double[], int, double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hermiteTangent(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer t0, int t0Offset, java.nio.ByteBuffer v1, int v1Offset, java.nio.ByteBuffer t1, int t1Offset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && t0.isDirect() && t0.order() == java.nio.ByteOrder.nativeOrder() && v1.isDirect() && v1.order() == java.nio.ByteOrder.nativeOrder() && t1.isDirect() && t1.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.hermiteTangent_unsafe(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
        return Double3OpsKernelsByteBuffer.hermiteTangent_api(dest, destOffset, src, srcOffset, t0, t0Offset, v1, v1Offset, t1, t1Offset, t);
    }

    /** {@link #hermiteTangent(double[], int, double[], int, double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long hermiteTangent(long dest, long src, long t0, long v1, long t1, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.hermiteTangent_unsafe(dest, src, t0, v1, t1, t);
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
    public static double[] lerp(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.fma(t, otherX - _selfx, _selfx);
        dest[destOffset + 1] = Math.fma(t, otherY - _selfy, _selfy);
        dest[destOffset + 2] = Math.fma(t, otherZ - _selfz, _selfz);
        return dest;
    }

    /** {@link #lerp(double[], int, double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer lerp(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return Double3OpsKernelsTypedBuffer.lerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    /** {@link #lerp(double[], int, double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return Double3OpsKernelsByteBuffer.lerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    /** {@link #lerp(double[], int, double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long lerp(long dest, long src, double otherX, double otherY, double otherZ, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.lerp_unsafe(dest, src, otherX, otherY, otherZ, t);
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
    public static double[] lerp(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        dest[destOffset] = Math.fma(t, other[otherOffset] - _selfx, _selfx);
        dest[destOffset + 1] = Math.fma(t, _othery - _selfy, _selfy);
        dest[destOffset + 2] = Math.fma(t, _otherz - _selfz, _selfz);
        return dest;
    }

    /** {@link #lerp(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer lerp(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Double3OpsKernelsTypedBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Double3OpsKernelsByteBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #lerp(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long lerp(long dest, long src, long other, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.lerp_unsafe(dest, src, other, t);
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
    public static double[] lerp(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ, double tX, double tY, double tZ) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.fma(tX, otherX - _selfx, _selfx);
        dest[destOffset + 1] = Math.fma(tY, otherY - _selfy, _selfy);
        dest[destOffset + 2] = Math.fma(tZ, otherZ - _selfz, _selfz);
        return dest;
    }

    /** {@link #lerp(double[], int, double[], int, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer lerp(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double tX, double tY, double tZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, tX, tY, tZ);
        return Double3OpsKernelsTypedBuffer.lerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, tX, tY, tZ);
    }

    /** {@link #lerp(double[], int, double[], int, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double tX, double tY, double tZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, tX, tY, tZ);
        return Double3OpsKernelsByteBuffer.lerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, tX, tY, tZ);
    }

    /** {@link #lerp(double[], int, double[], int, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long lerp(long dest, long src, double otherX, double otherY, double otherZ, double tX, double tY, double tZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.lerp_unsafe(dest, src, otherX, otherY, otherZ, tX, tY, tZ);
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
    public static double[] lerp(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double[] t, int tOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _ty = t[tOffset + 1];
        double _tz = t[tOffset + 2];
        dest[destOffset] = Math.fma(t[tOffset], other[otherOffset] - _selfx, _selfx);
        dest[destOffset + 1] = Math.fma(_ty, _othery - _selfy, _selfy);
        dest[destOffset + 2] = Math.fma(_tz, _otherz - _selfz, _selfz);
        return dest;
    }

    /** {@link #lerp(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer lerp(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer t, int tOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder() && t.isDirect() && t.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        return Double3OpsKernelsTypedBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
    }

    /** {@link #lerp(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer t, int tOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder() && t.isDirect() && t.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.lerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
        return Double3OpsKernelsByteBuffer.lerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t, tOffset);
    }

    /** {@link #lerp(double[], int, double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long lerp(long dest, long src, long other, long t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.lerp_unsafe(dest, src, other, t);
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
     * finite length are handled: when their squared lengths leave the {@code double} range, they
     * are first scaled exactly by powers of two.
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
    public static double[] slerp(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t7 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        double _t8 = Math.fma(otherZ, otherZ, Math.fma(otherX, otherX, otherY * otherY));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = Math.fma(otherZ * _t12, _t17, Math.fma(otherX * _t12, _t14, otherY * _t12 * _t20));
        return slerp_s80ee2cca_1(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, Math.fma(otherZ, _t12, -(_t26 * _t17)), Math.fma(otherX, _t12, -(_t26 * _t14)), Math.fma(otherY, _t12, -(_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private static double[] slerp_s80ee2cca_1(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ, double t, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34, double _t35) {
        double _t39 = -Math.fma(_t33, _t17, Math.fma(_t34, _t14, _t35 * _t20));
        double _t40 = Math.fma(_t39, _t17, _t33);
        double _t41 = Math.fma(_t39, _t14, _t34);
        double _t42 = Math.fma(_t39, _t20, _t35);
        double _t46 = Math.fma(_t40, _t40, Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _sp0 = _t24 * Math.sin(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        double _t55 = _t24 * Math.cos(_t50);
        dest[destOffset] = Math.fma(_t14, _t55, _sp0 * _t41);
        dest[destOffset + 1] = Math.fma(_t20, _t55, _sp0 * _t42);
        dest[destOffset + 2] = Math.fma(_t17, _t55, _sp0 * _t40);
        return dest;
    }

    /** {@link #slerp(double[], int, double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer slerp(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.slerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return Double3OpsKernelsTypedBuffer.slerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    /** {@link #slerp(double[], int, double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer slerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.slerp_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
        return Double3OpsKernelsByteBuffer.slerp_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, t);
    }

    /** {@link #slerp(double[], int, double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long slerp(long dest, long src, double otherX, double otherY, double otherZ, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.slerp_unsafe(dest, src, otherX, otherY, otherZ, t);
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
     * finite length are handled: when their squared lengths leave the {@code double} range, they
     * are first scaled exactly by powers of two.
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
    public static double[] slerp(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _t7 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t8 = Math.fma(_otherz, _otherz, Math.fma(_otherx, _otherx, _othery * _othery));
        if (!(_t8 > 2.2250738585072014E-308 && _t8 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t9 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t12 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t14 = _selfx * _t9;
        double _t17 = _selfz * _t9;
        double _t20 = _selfy * _t9;
        double _t26 = Math.fma(_otherz * _t12, _t17, Math.fma(_otherx * _t12, _t14, _othery * _t12 * _t20));
        return slerp_sfd63dc4f_1(dest, destOffset, src, srcOffset, other, otherOffset, t, _t14, _t17, _t20, t * java.lang.Math.sqrt(_t8) + (1.0 - t) * java.lang.Math.sqrt(_t7), _t26, Math.fma(_otherz, _t12, -(_t26 * _t17)), Math.fma(_otherx, _t12, -(_t26 * _t14)), Math.fma(_othery, _t12, -(_t26 * _t20)));
    }

    /** Piece 2 of {@code slerp}, split to fit the inline budget; reached only through it. */
    private static double[] slerp_sfd63dc4f_1(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t, double _t14, double _t17, double _t20, double _t24, double _t26, double _t33, double _t34, double _t35) {
        double _t39 = -Math.fma(_t33, _t17, Math.fma(_t34, _t14, _t35 * _t20));
        double _t40 = Math.fma(_t39, _t17, _t33);
        double _t41 = Math.fma(_t39, _t14, _t34);
        double _t42 = Math.fma(_t39, _t20, _t35);
        double _t46 = Math.fma(_t40, _t40, Math.fma(_t41, _t41, _t42 * _t42));
        if (!(_t46 > 5.048709793414476E-29 && _t46 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, other, otherOffset, t);
        double _t50 = t * Math.atan2(java.lang.Math.sqrt(_t46), _t26);
        double _sp0 = _t24 * Math.sin(_t50) * (1.0 / java.lang.Math.sqrt(_t46));
        double _t55 = _t24 * Math.cos(_t50);
        dest[destOffset] = Math.fma(_t14, _t55, _sp0 * _t41);
        dest[destOffset + 1] = Math.fma(_t20, _t55, _sp0 * _t42);
        dest[destOffset + 2] = Math.fma(_t17, _t55, _sp0 * _t40);
        return dest;
    }

    /** {@link #slerp(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer slerp(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.slerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Double3OpsKernelsTypedBuffer.slerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #slerp(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer slerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.slerp_unsafe(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return Double3OpsKernelsByteBuffer.slerp_api(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    /** {@link #slerp(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long slerp(long dest, long src, long other, double t) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.slerp_unsafe(dest, src, other, t);
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
    public static double[] absolute(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = java.lang.Math.abs(src[srcOffset]);
        dest[destOffset + 1] = java.lang.Math.abs(_selfy);
        dest[destOffset + 2] = java.lang.Math.abs(_selfz);
        return dest;
    }

    /** {@link #absolute(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer absolute(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.absolute_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.absolute_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #absolute(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer absolute(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.absolute_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.absolute_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #absolute(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long absolute(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.absolute_unsafe(dest, src);
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
    public static double[] acos(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.acos(src[srcOffset]);
        dest[destOffset + 1] = Math.acos(_selfy);
        dest[destOffset + 2] = Math.acos(_selfz);
        return dest;
    }

    /** {@link #acos(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer acos(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.acos_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.acos_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #acos(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer acos(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.acos_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.acos_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #acos(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long acos(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.acos_unsafe(dest, src);
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
    public static double[] addScaled(double[] dest, int destOffset, double[] src, int srcOffset, double bX, double bY, double bZ, double scalar) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.fma(scalar, bX, src[srcOffset]);
        dest[destOffset + 1] = Math.fma(scalar, bY, _selfy);
        dest[destOffset + 2] = Math.fma(scalar, bZ, _selfz);
        return dest;
    }

    /** {@link #addScaled(double[], int, double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer addScaled(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, scalar);
        return Double3OpsKernelsTypedBuffer.addScaled_api(dest, destOffset, src, srcOffset, bX, bY, bZ, scalar);
    }

    /** {@link #addScaled(double[], int, double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer addScaled(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double bZ, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, scalar);
        return Double3OpsKernelsByteBuffer.addScaled_api(dest, destOffset, src, srcOffset, bX, bY, bZ, scalar);
    }

    /** {@link #addScaled(double[], int, double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long addScaled(long dest, long src, double bX, double bY, double bZ, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.addScaled_unsafe(dest, src, bX, bY, bZ, scalar);
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
    public static double[] addScaled(double[] dest, int destOffset, double[] src, int srcOffset, double[] b, int bOffset, double scalar) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _by = b[bOffset + 1];
        double _bz = b[bOffset + 2];
        dest[destOffset] = Math.fma(scalar, b[bOffset], src[srcOffset]);
        dest[destOffset + 1] = Math.fma(scalar, _by, _selfy);
        dest[destOffset + 2] = Math.fma(scalar, _bz, _selfz);
        return dest;
    }

    /** {@link #addScaled(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer addScaled(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && b.isDirect() && b.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        return Double3OpsKernelsTypedBuffer.addScaled_api(dest, destOffset, src, srcOffset, b, bOffset, scalar);
    }

    /** {@link #addScaled(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer addScaled(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && b.isDirect() && b.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, b, bOffset, scalar);
        return Double3OpsKernelsByteBuffer.addScaled_api(dest, destOffset, src, srcOffset, b, bOffset, scalar);
    }

    /** {@link #addScaled(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long addScaled(long dest, long src, long b, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.addScaled_unsafe(dest, src, b, scalar);
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
    public static double[] addScaled(double[] dest, int destOffset, double[] src, int srcOffset, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.fma(bX, cX, src[srcOffset]);
        dest[destOffset + 1] = Math.fma(bY, cY, _selfy);
        dest[destOffset + 2] = Math.fma(bZ, cZ, _selfz);
        return dest;
    }

    /** {@link #addScaled(double[], int, double[], int, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer addScaled(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, cX, cY, cZ);
        return Double3OpsKernelsTypedBuffer.addScaled_api(dest, destOffset, src, srcOffset, bX, bY, bZ, cX, cY, cZ);
    }

    /** {@link #addScaled(double[], int, double[], int, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer addScaled(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, bX, bY, bZ, cX, cY, cZ);
        return Double3OpsKernelsByteBuffer.addScaled_api(dest, destOffset, src, srcOffset, bX, bY, bZ, cX, cY, cZ);
    }

    /** {@link #addScaled(double[], int, double[], int, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long addScaled(long dest, long src, double bX, double bY, double bZ, double cX, double cY, double cZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.addScaled_unsafe(dest, src, bX, bY, bZ, cX, cY, cZ);
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
    public static double[] addScaled(double[] dest, int destOffset, double[] src, int srcOffset, double[] b, int bOffset, double[] c, int cOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _by = b[bOffset + 1];
        double _bz = b[bOffset + 2];
        double _cy = c[cOffset + 1];
        double _cz = c[cOffset + 2];
        dest[destOffset] = Math.fma(b[bOffset], c[cOffset], src[srcOffset]);
        dest[destOffset + 1] = Math.fma(_by, _cy, _selfy);
        dest[destOffset + 2] = Math.fma(_bz, _cz, _selfz);
        return dest;
    }

    /** {@link #addScaled(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer addScaled(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer b, int bOffset, java.nio.DoubleBuffer c, int cOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && b.isDirect() && b.order() == java.nio.ByteOrder.nativeOrder() && c.isDirect() && c.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return Double3OpsKernelsTypedBuffer.addScaled_api(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    /** {@link #addScaled(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer addScaled(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer b, int bOffset, java.nio.ByteBuffer c, int cOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && b.isDirect() && b.order() == java.nio.ByteOrder.nativeOrder() && c.isDirect() && c.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.addScaled_unsafe(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
        return Double3OpsKernelsByteBuffer.addScaled_api(dest, destOffset, src, srcOffset, b, bOffset, c, cOffset);
    }

    /** {@link #addScaled(double[], int, double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long addScaled(long dest, long src, long b, long c) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.addScaled_unsafe(dest, src, b, c);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the angle in radians between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code double} range, the vectors are first scaled exactly by powers of two.
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
    public static double angleBetween(double[] src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t6 = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        double _t7 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        double _t8 = Math.fma(otherZ, _selfx, -(otherX * _selfz));
        double _ct0 = Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsArray.angleBetween_degenerate(src, srcOffset, otherX, otherY, otherZ);
        return Math.atan2(java.lang.Math.sqrt(_ct0), Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy)));
    }

    /** {@link #angleBetween(double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double angleBetween(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.angleBetween_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsTypedBuffer.angleBetween_api(src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #angleBetween(double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double angleBetween(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.angleBetween_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsByteBuffer.angleBetween_api(src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #angleBetween(double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double angleBetween(long src, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.angleBetween_unsafe(src, otherX, otherY, otherZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the angle in radians between this vector and {@code other}.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code double} range, the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param other the storage holding the vector to measure the angle to
     * @param otherOffset the element index in {@code other} at which the vector starts
     * @return the angle in radians between this vector and {@code other}
     */
    public static double angleBetween(double[] src, int srcOffset, double[] other, int otherOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _t6 = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        double _t7 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        double _t8 = Math.fma(_otherz, _selfx, -(_otherx * _selfz));
        double _ct0 = Math.fma(_t6, _t6, Math.fma(_t7, _t7, _t8 * _t8));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsArray.angleBetween_degenerate(src, srcOffset, other, otherOffset);
        return Math.atan2(java.lang.Math.sqrt(_ct0), Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy)));
    }

    /** {@link #angleBetween(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double angleBetween(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.angleBetween_unsafe(src, srcOffset, other, otherOffset);
        return Double3OpsKernelsTypedBuffer.angleBetween_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #angleBetween(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double angleBetween(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.angleBetween_unsafe(src, srcOffset, other, otherOffset);
        return Double3OpsKernelsByteBuffer.angleBetween_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #angleBetween(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double angleBetween(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.angleBetween_unsafe(src, other);
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
    public static double[] asin(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.asin(src[srcOffset]);
        dest[destOffset + 1] = Math.asin(_selfy);
        dest[destOffset + 2] = Math.asin(_selfz);
        return dest;
    }

    /** {@link #asin(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer asin(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.asin_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.asin_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #asin(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer asin(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.asin_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.asin_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #asin(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long asin(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.asin_unsafe(dest, src);
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
    public static double[] atan(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.atan(src[srcOffset]);
        dest[destOffset + 1] = Math.atan(_selfy);
        dest[destOffset + 2] = Math.atan(_selfz);
        return dest;
    }

    /** {@link #atan(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer atan(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.atan_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.atan_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #atan(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer atan(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.atan_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.atan_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #atan(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long atan(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.atan_unsafe(dest, src);
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
    public static double[] atan2(double[] dest, int destOffset, double[] src, int srcOffset, double x) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.atan2(src[srcOffset], x);
        dest[destOffset + 1] = Math.atan2(_selfy, x);
        dest[destOffset + 2] = Math.atan2(_selfz, x);
        return dest;
    }

    /** {@link #atan2(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer atan2(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double x) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.atan2_unsafe(dest, destOffset, src, srcOffset, x);
        return Double3OpsKernelsTypedBuffer.atan2_api(dest, destOffset, src, srcOffset, x);
    }

    /** {@link #atan2(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer atan2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double x) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.atan2_unsafe(dest, destOffset, src, srcOffset, x);
        return Double3OpsKernelsByteBuffer.atan2_api(dest, destOffset, src, srcOffset, x);
    }

    /** {@link #atan2(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long atan2(long dest, long src, double x) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.atan2_unsafe(dest, src, x);
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
    public static double[] atan2(double[] dest, int destOffset, double[] src, int srcOffset, double xX, double xY, double xZ) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.atan2(src[srcOffset], xX);
        dest[destOffset + 1] = Math.atan2(_selfy, xY);
        dest[destOffset + 2] = Math.atan2(_selfz, xZ);
        return dest;
    }

    /** {@link #atan2(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer atan2(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double xX, double xY, double xZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.atan2_unsafe(dest, destOffset, src, srcOffset, xX, xY, xZ);
        return Double3OpsKernelsTypedBuffer.atan2_api(dest, destOffset, src, srcOffset, xX, xY, xZ);
    }

    /** {@link #atan2(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer atan2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double xX, double xY, double xZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.atan2_unsafe(dest, destOffset, src, srcOffset, xX, xY, xZ);
        return Double3OpsKernelsByteBuffer.atan2_api(dest, destOffset, src, srcOffset, xX, xY, xZ);
    }

    /** {@link #atan2(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long atan2(long dest, long src, double xX, double xY, double xZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.atan2_unsafe(dest, src, xX, xY, xZ);
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
    public static double[] atan2(double[] dest, int destOffset, double[] src, int srcOffset, double[] x, int xOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _xy = x[xOffset + 1];
        double _xz = x[xOffset + 2];
        dest[destOffset] = Math.atan2(src[srcOffset], x[xOffset]);
        dest[destOffset + 1] = Math.atan2(_selfy, _xy);
        dest[destOffset + 2] = Math.atan2(_selfz, _xz);
        return dest;
    }

    /** {@link #atan2(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer atan2(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer x, int xOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && x.isDirect() && x.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.atan2_unsafe(dest, destOffset, src, srcOffset, x, xOffset);
        return Double3OpsKernelsTypedBuffer.atan2_api(dest, destOffset, src, srcOffset, x, xOffset);
    }

    /** {@link #atan2(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer atan2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer x, int xOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && x.isDirect() && x.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.atan2_unsafe(dest, destOffset, src, srcOffset, x, xOffset);
        return Double3OpsKernelsByteBuffer.atan2_api(dest, destOffset, src, srcOffset, x, xOffset);
    }

    /** {@link #atan2(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long atan2(long dest, long src, long x) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.atan2_unsafe(dest, src, x);
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
    public static double[] cbrt(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.cbrt(src[srcOffset]);
        dest[destOffset + 1] = Math.cbrt(_selfy);
        dest[destOffset + 2] = Math.cbrt(_selfz);
        return dest;
    }

    /** {@link #cbrt(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer cbrt(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.cbrt_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.cbrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cbrt(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer cbrt(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.cbrt_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.cbrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cbrt(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long cbrt(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.cbrt_unsafe(dest, src);
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
    public static double[] ceil(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.ceil(src[srcOffset]);
        dest[destOffset + 1] = Math.ceil(_selfy);
        dest[destOffset + 2] = Math.ceil(_selfz);
        return dest;
    }

    /** {@link #ceil(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer ceil(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.ceil_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.ceil_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #ceil(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer ceil(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.ceil_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.ceil_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #ceil(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long ceil(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.ceil_unsafe(dest, src);
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
    public static double[] clamp(double[] dest, int destOffset, double[] src, int srcOffset, double min, double max) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = java.lang.Math.min(java.lang.Math.max(src[srcOffset], min), max);
        dest[destOffset + 1] = java.lang.Math.min(java.lang.Math.max(_selfy, min), max);
        dest[destOffset + 2] = java.lang.Math.min(java.lang.Math.max(_selfz, min), max);
        return dest;
    }

    /** {@link #clamp(double[], int, double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer clamp(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double min, double max) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.clamp_unsafe(dest, destOffset, src, srcOffset, min, max);
        return Double3OpsKernelsTypedBuffer.clamp_api(dest, destOffset, src, srcOffset, min, max);
    }

    /** {@link #clamp(double[], int, double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer clamp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double min, double max) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.clamp_unsafe(dest, destOffset, src, srcOffset, min, max);
        return Double3OpsKernelsByteBuffer.clamp_api(dest, destOffset, src, srcOffset, min, max);
    }

    /** {@link #clamp(double[], int, double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long clamp(long dest, long src, double min, double max) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.clamp_unsafe(dest, src, min, max);
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
    public static double[] clamp(double[] dest, int destOffset, double[] src, int srcOffset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = java.lang.Math.min(java.lang.Math.max(src[srcOffset], minX), maxX);
        dest[destOffset + 1] = java.lang.Math.min(java.lang.Math.max(_selfy, minY), maxY);
        dest[destOffset + 2] = java.lang.Math.min(java.lang.Math.max(_selfz, minZ), maxZ);
        return dest;
    }

    /** {@link #clamp(double[], int, double[], int, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer clamp(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.clamp_unsafe(dest, destOffset, src, srcOffset, minX, minY, minZ, maxX, maxY, maxZ);
        return Double3OpsKernelsTypedBuffer.clamp_api(dest, destOffset, src, srcOffset, minX, minY, minZ, maxX, maxY, maxZ);
    }

    /** {@link #clamp(double[], int, double[], int, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer clamp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.clamp_unsafe(dest, destOffset, src, srcOffset, minX, minY, minZ, maxX, maxY, maxZ);
        return Double3OpsKernelsByteBuffer.clamp_api(dest, destOffset, src, srcOffset, minX, minY, minZ, maxX, maxY, maxZ);
    }

    /** {@link #clamp(double[], int, double[], int, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long clamp(long dest, long src, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.clamp_unsafe(dest, src, minX, minY, minZ, maxX, maxY, maxZ);
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
    public static double[] clamp(double[] dest, int destOffset, double[] src, int srcOffset, double[] min, int minOffset, double[] max, int maxOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _miny = min[minOffset + 1];
        double _minz = min[minOffset + 2];
        double _maxy = max[maxOffset + 1];
        double _maxz = max[maxOffset + 2];
        dest[destOffset] = java.lang.Math.min(java.lang.Math.max(src[srcOffset], min[minOffset]), max[maxOffset]);
        dest[destOffset + 1] = java.lang.Math.min(java.lang.Math.max(_selfy, _miny), _maxy);
        dest[destOffset + 2] = java.lang.Math.min(java.lang.Math.max(_selfz, _minz), _maxz);
        return dest;
    }

    /** {@link #clamp(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer clamp(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer min, int minOffset, java.nio.DoubleBuffer max, int maxOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && min.isDirect() && min.order() == java.nio.ByteOrder.nativeOrder() && max.isDirect() && max.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.clamp_unsafe(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
        return Double3OpsKernelsTypedBuffer.clamp_api(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
    }

    /** {@link #clamp(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer clamp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer min, int minOffset, java.nio.ByteBuffer max, int maxOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && min.isDirect() && min.order() == java.nio.ByteOrder.nativeOrder() && max.isDirect() && max.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.clamp_unsafe(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
        return Double3OpsKernelsByteBuffer.clamp_api(dest, destOffset, src, srcOffset, min, minOffset, max, maxOffset);
    }

    /** {@link #clamp(double[], int, double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long clamp(long dest, long src, long min, long max) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.clamp_unsafe(dest, src, min, max);
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
    public static double[] closestPointOnLine(double[] dest, int destOffset, double[] src, int srcOffset, double lineStartX, double lineStartY, double lineStartZ, double lineEndX, double lineEndY, double lineEndZ) {
        double _t0 = lineEndZ - lineStartZ;
        double _t1 = lineEndX - lineStartX;
        double _t2 = lineEndY - lineStartY;
        double _t10 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t14 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, Math.fma(_t0, src[srcOffset + 2] - lineStartZ, Math.fma(_t1, src[srcOffset] - lineStartX, _t2 * (src[srcOffset + 1] - lineStartY))) / _t10));
        if (_t10 > 0.0) {
            dest[destOffset] = Math.fma(_t1, _t14, lineStartX);
            dest[destOffset + 1] = Math.fma(_t2, _t14, lineStartY);
            dest[destOffset + 2] = Math.fma(_t0, _t14, lineStartZ);
        } else {
            dest[destOffset] = lineStartX;
            dest[destOffset + 1] = lineStartY;
            dest[destOffset + 2] = lineStartZ;
        }
        return dest;
    }

    /** {@link #closestPointOnLine(double[], int, double[], int, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer closestPointOnLine(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double lineStartX, double lineStartY, double lineStartZ, double lineEndX, double lineEndY, double lineEndZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.closestPointOnLine_unsafe(dest, destOffset, src, srcOffset, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
        return Double3OpsKernelsTypedBuffer.closestPointOnLine_api(dest, destOffset, src, srcOffset, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
    }

    /** {@link #closestPointOnLine(double[], int, double[], int, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer closestPointOnLine(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double lineStartX, double lineStartY, double lineStartZ, double lineEndX, double lineEndY, double lineEndZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.closestPointOnLine_unsafe(dest, destOffset, src, srcOffset, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
        return Double3OpsKernelsByteBuffer.closestPointOnLine_api(dest, destOffset, src, srcOffset, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
    }

    /** {@link #closestPointOnLine(double[], int, double[], int, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long closestPointOnLine(long dest, long src, double lineStartX, double lineStartY, double lineStartZ, double lineEndX, double lineEndY, double lineEndZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.closestPointOnLine_unsafe(dest, src, lineStartX, lineStartY, lineStartZ, lineEndX, lineEndY, lineEndZ);
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
    public static double[] closestPointOnLine(double[] dest, int destOffset, double[] src, int srcOffset, double[] lineStart, int lineStartOffset, double[] lineEnd, int lineEndOffset) {
        double _lineStartx = lineStart[lineStartOffset];
        double _lineStarty = lineStart[lineStartOffset + 1];
        double _lineStartz = lineStart[lineStartOffset + 2];
        double _t0 = lineEnd[lineEndOffset + 2] - _lineStartz;
        double _t1 = lineEnd[lineEndOffset] - _lineStartx;
        double _t2 = lineEnd[lineEndOffset + 1] - _lineStarty;
        double _t10 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t14 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, Math.fma(_t0, src[srcOffset + 2] - _lineStartz, Math.fma(_t1, src[srcOffset] - _lineStartx, _t2 * (src[srcOffset + 1] - _lineStarty))) / _t10));
        if (_t10 > 0.0) {
            dest[destOffset] = Math.fma(_t1, _t14, _lineStartx);
            dest[destOffset + 1] = Math.fma(_t2, _t14, _lineStarty);
            dest[destOffset + 2] = Math.fma(_t0, _t14, _lineStartz);
        } else {
            dest[destOffset] = _lineStartx;
            dest[destOffset + 1] = _lineStarty;
            dest[destOffset + 2] = _lineStartz;
        }
        return dest;
    }

    /** {@link #closestPointOnLine(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer closestPointOnLine(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer lineStart, int lineStartOffset, java.nio.DoubleBuffer lineEnd, int lineEndOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && lineStart.isDirect() && lineStart.order() == java.nio.ByteOrder.nativeOrder() && lineEnd.isDirect() && lineEnd.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.closestPointOnLine_unsafe(dest, destOffset, src, srcOffset, lineStart, lineStartOffset, lineEnd, lineEndOffset);
        return Double3OpsKernelsTypedBuffer.closestPointOnLine_api(dest, destOffset, src, srcOffset, lineStart, lineStartOffset, lineEnd, lineEndOffset);
    }

    /** {@link #closestPointOnLine(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer closestPointOnLine(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer lineStart, int lineStartOffset, java.nio.ByteBuffer lineEnd, int lineEndOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && lineStart.isDirect() && lineStart.order() == java.nio.ByteOrder.nativeOrder() && lineEnd.isDirect() && lineEnd.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.closestPointOnLine_unsafe(dest, destOffset, src, srcOffset, lineStart, lineStartOffset, lineEnd, lineEndOffset);
        return Double3OpsKernelsByteBuffer.closestPointOnLine_api(dest, destOffset, src, srcOffset, lineStart, lineStartOffset, lineEnd, lineEndOffset);
    }

    /** {@link #closestPointOnLine(double[], int, double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long closestPointOnLine(long dest, long src, long lineStart, long lineEnd) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.closestPointOnLine_unsafe(dest, src, lineStart, lineEnd);
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
    public static double compAdd(double[] src, int srcOffset) {
        return src[srcOffset + 2] + (src[srcOffset] + src[srcOffset + 1]);
    }

    /** {@link #compAdd(double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double compAdd(java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.compAdd_unsafe(src, srcOffset);
        return Double3OpsKernelsTypedBuffer.compAdd_api(src, srcOffset);
    }

    /** {@link #compAdd(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double compAdd(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.compAdd_unsafe(src, srcOffset);
        return Double3OpsKernelsByteBuffer.compAdd_api(src, srcOffset);
    }

    /** {@link #compAdd(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double compAdd(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.compAdd_unsafe(src);
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
    public static double compMax(double[] src, int srcOffset) {
        return java.lang.Math.max(java.lang.Math.max(src[srcOffset], src[srcOffset + 1]), src[srcOffset + 2]);
    }

    /** {@link #compMax(double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double compMax(java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.compMax_unsafe(src, srcOffset);
        return Double3OpsKernelsTypedBuffer.compMax_api(src, srcOffset);
    }

    /** {@link #compMax(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double compMax(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.compMax_unsafe(src, srcOffset);
        return Double3OpsKernelsByteBuffer.compMax_api(src, srcOffset);
    }

    /** {@link #compMax(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double compMax(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.compMax_unsafe(src);
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
    public static double compMin(double[] src, int srcOffset) {
        return java.lang.Math.min(java.lang.Math.min(src[srcOffset], src[srcOffset + 1]), src[srcOffset + 2]);
    }

    /** {@link #compMin(double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double compMin(java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.compMin_unsafe(src, srcOffset);
        return Double3OpsKernelsTypedBuffer.compMin_api(src, srcOffset);
    }

    /** {@link #compMin(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double compMin(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.compMin_unsafe(src, srcOffset);
        return Double3OpsKernelsByteBuffer.compMin_api(src, srcOffset);
    }

    /** {@link #compMin(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double compMin(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.compMin_unsafe(src);
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
    public static double compMul(double[] src, int srcOffset) {
        return src[srcOffset + 2] * src[srcOffset] * src[srcOffset + 1];
    }

    /** {@link #compMul(double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double compMul(java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.compMul_unsafe(src, srcOffset);
        return Double3OpsKernelsTypedBuffer.compMul_api(src, srcOffset);
    }

    /** {@link #compMul(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double compMul(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.compMul_unsafe(src, srcOffset);
        return Double3OpsKernelsByteBuffer.compMul_api(src, srcOffset);
    }

    /** {@link #compMul(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double compMul(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.compMul_unsafe(src);
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
    public static double[] copySign(double[] dest, int destOffset, double[] src, int srcOffset, double sign) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.copySign(src[srcOffset], sign);
        dest[destOffset + 1] = Math.copySign(_selfy, sign);
        dest[destOffset + 2] = Math.copySign(_selfz, sign);
        return dest;
    }

    /** {@link #copySign(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer copySign(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double sign) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.copySign_unsafe(dest, destOffset, src, srcOffset, sign);
        return Double3OpsKernelsTypedBuffer.copySign_api(dest, destOffset, src, srcOffset, sign);
    }

    /** {@link #copySign(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer copySign(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double sign) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.copySign_unsafe(dest, destOffset, src, srcOffset, sign);
        return Double3OpsKernelsByteBuffer.copySign_api(dest, destOffset, src, srcOffset, sign);
    }

    /** {@link #copySign(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long copySign(long dest, long src, double sign) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.copySign_unsafe(dest, src, sign);
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
    public static double[] copySign(double[] dest, int destOffset, double[] src, int srcOffset, double signX, double signY, double signZ) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.copySign(src[srcOffset], signX);
        dest[destOffset + 1] = Math.copySign(_selfy, signY);
        dest[destOffset + 2] = Math.copySign(_selfz, signZ);
        return dest;
    }

    /** {@link #copySign(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer copySign(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double signX, double signY, double signZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.copySign_unsafe(dest, destOffset, src, srcOffset, signX, signY, signZ);
        return Double3OpsKernelsTypedBuffer.copySign_api(dest, destOffset, src, srcOffset, signX, signY, signZ);
    }

    /** {@link #copySign(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer copySign(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double signX, double signY, double signZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.copySign_unsafe(dest, destOffset, src, srcOffset, signX, signY, signZ);
        return Double3OpsKernelsByteBuffer.copySign_api(dest, destOffset, src, srcOffset, signX, signY, signZ);
    }

    /** {@link #copySign(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long copySign(long dest, long src, double signX, double signY, double signZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.copySign_unsafe(dest, src, signX, signY, signZ);
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
    public static double[] copySign(double[] dest, int destOffset, double[] src, int srcOffset, double[] sign, int signOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _signy = sign[signOffset + 1];
        double _signz = sign[signOffset + 2];
        dest[destOffset] = Math.copySign(src[srcOffset], sign[signOffset]);
        dest[destOffset + 1] = Math.copySign(_selfy, _signy);
        dest[destOffset + 2] = Math.copySign(_selfz, _signz);
        return dest;
    }

    /** {@link #copySign(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer copySign(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer sign, int signOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && sign.isDirect() && sign.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.copySign_unsafe(dest, destOffset, src, srcOffset, sign, signOffset);
        return Double3OpsKernelsTypedBuffer.copySign_api(dest, destOffset, src, srcOffset, sign, signOffset);
    }

    /** {@link #copySign(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer copySign(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer sign, int signOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && sign.isDirect() && sign.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.copySign_unsafe(dest, destOffset, src, srcOffset, sign, signOffset);
        return Double3OpsKernelsByteBuffer.copySign_api(dest, destOffset, src, srcOffset, sign, signOffset);
    }

    /** {@link #copySign(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long copySign(long dest, long src, long sign) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.copySign_unsafe(dest, src, sign);
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
    public static double[] cos(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.cos(src[srcOffset]);
        dest[destOffset + 1] = Math.cos(_selfy);
        dest[destOffset + 2] = Math.cos(_selfz);
        return dest;
    }

    /** {@link #cos(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer cos(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.cos_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.cos_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cos(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer cos(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.cos_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.cos_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cos(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long cos(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.cos_unsafe(dest, src);
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
    public static double[] cosh(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.cosh(src[srcOffset]);
        dest[destOffset + 1] = Math.cosh(_selfy);
        dest[destOffset + 2] = Math.cosh(_selfz);
        return dest;
    }

    /** {@link #cosh(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer cosh(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.cosh_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.cosh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cosh(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer cosh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.cosh_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.cosh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #cosh(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long cosh(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.cosh_unsafe(dest, src);
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
    public static double[] cross(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        dest[destOffset + 1] = Math.fma(otherX, _selfz, -(otherZ * _selfx));
        dest[destOffset + 2] = Math.fma(otherY, _selfx, -(otherX * _selfy));
        return dest;
    }

    /** {@link #cross(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer cross(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.cross_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsTypedBuffer.cross_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #cross(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer cross(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.cross_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsByteBuffer.cross_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #cross(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long cross(long dest, long src, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.cross_unsafe(dest, src, otherX, otherY, otherZ);
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
    public static double[] cross(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        dest[destOffset] = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        dest[destOffset + 1] = Math.fma(_otherx, _selfz, -(_otherz * _selfx));
        dest[destOffset + 2] = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        return dest;
    }

    /** {@link #cross(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer cross(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.cross_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3OpsKernelsTypedBuffer.cross_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #cross(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer cross(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.cross_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3OpsKernelsByteBuffer.cross_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #cross(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long cross(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.cross_unsafe(dest, src, other);
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
    public static double[] degrees(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.toDegrees(src[srcOffset]);
        dest[destOffset + 1] = Math.toDegrees(_selfy);
        dest[destOffset + 2] = Math.toDegrees(_selfz);
        return dest;
    }

    /** {@link #degrees(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer degrees(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.degrees_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.degrees_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #degrees(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer degrees(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.degrees_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.degrees_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #degrees(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long degrees(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.degrees_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the distance between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ)}
     * @return the distance between this vector and {@code other}
     */
    public static double distance(double[] src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _t0 = src[srcOffset + 2] - otherZ;
        double _t1 = src[srcOffset] - otherX;
        double _t2 = src[srcOffset + 1] - otherY;
        return java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2)));
    }

    /** {@link #distance(double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double distance(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.distance_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsTypedBuffer.distance_api(src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #distance(double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double distance(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.distance_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsByteBuffer.distance_api(src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #distance(double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double distance(long src, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.distance_unsafe(src, otherX, otherY, otherZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the distance between this vector and {@code other}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @param other the storage holding the vector to measure the distance to
     * @param otherOffset the element index in {@code other} at which the vector starts
     * @return the distance between this vector and {@code other}
     */
    public static double distance(double[] src, int srcOffset, double[] other, int otherOffset) {
        double _t0 = src[srcOffset + 2] - other[otherOffset + 2];
        double _t1 = src[srcOffset] - other[otherOffset];
        double _t2 = src[srcOffset + 1] - other[otherOffset + 1];
        return java.lang.Math.sqrt(Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2)));
    }

    /** {@link #distance(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double distance(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.distance_unsafe(src, srcOffset, other, otherOffset);
        return Double3OpsKernelsTypedBuffer.distance_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #distance(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double distance(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.distance_unsafe(src, srcOffset, other, otherOffset);
        return Double3OpsKernelsByteBuffer.distance_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #distance(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double distance(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.distance_unsafe(src, other);
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
    public static double distanceSquared(double[] src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _t0 = src[srcOffset + 2] - otherZ;
        double _t1 = src[srcOffset] - otherX;
        double _t2 = src[srcOffset + 1] - otherY;
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
    }

    /** {@link #distanceSquared(double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double distanceSquared(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.distanceSquared_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsTypedBuffer.distanceSquared_api(src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #distanceSquared(double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double distanceSquared(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.distanceSquared_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsByteBuffer.distanceSquared_api(src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #distanceSquared(double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double distanceSquared(long src, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.distanceSquared_unsafe(src, otherX, otherY, otherZ);
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
    public static double distanceSquared(double[] src, int srcOffset, double[] other, int otherOffset) {
        double _t0 = src[srcOffset + 2] - other[otherOffset + 2];
        double _t1 = src[srcOffset] - other[otherOffset];
        double _t2 = src[srcOffset + 1] - other[otherOffset + 1];
        return Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
    }

    /** {@link #distanceSquared(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double distanceSquared(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.distanceSquared_unsafe(src, srcOffset, other, otherOffset);
        return Double3OpsKernelsTypedBuffer.distanceSquared_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #distanceSquared(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double distanceSquared(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.distanceSquared_unsafe(src, srcOffset, other, otherOffset);
        return Double3OpsKernelsByteBuffer.distanceSquared_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #distanceSquared(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double distanceSquared(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.distanceSquared_unsafe(src, other);
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
    public static double dot(double[] src, int srcOffset, double otherX, double otherY, double otherZ) {
        return Math.fma(otherZ, src[srcOffset + 2], Math.fma(otherX, src[srcOffset], otherY * src[srcOffset + 1]));
    }

    /** {@link #dot(double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double dot(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.dot_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsTypedBuffer.dot_api(src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #dot(double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double dot(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.dot_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsByteBuffer.dot_api(src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #dot(double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double dot(long src, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.dot_unsafe(src, otherX, otherY, otherZ);
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
    public static double dot(double[] src, int srcOffset, double[] other, int otherOffset) {
        return Math.fma(other[otherOffset + 2], src[srcOffset + 2], Math.fma(other[otherOffset], src[srcOffset], other[otherOffset + 1] * src[srcOffset + 1]));
    }

    /** {@link #dot(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double dot(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.dot_unsafe(src, srcOffset, other, otherOffset);
        return Double3OpsKernelsTypedBuffer.dot_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #dot(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double dot(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.dot_unsafe(src, srcOffset, other, otherOffset);
        return Double3OpsKernelsByteBuffer.dot_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #dot(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double dot(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.dot_unsafe(src, other);
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
    public static double[] exp(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.exp(src[srcOffset]);
        dest[destOffset + 1] = Math.exp(_selfy);
        dest[destOffset + 2] = Math.exp(_selfz);
        return dest;
    }

    /** {@link #exp(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer exp(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.exp_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.exp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #exp(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer exp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.exp_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.exp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #exp(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long exp(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.exp_unsafe(dest, src);
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
    public static double[] exp2(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.pow(2.0, src[srcOffset]);
        dest[destOffset + 1] = Math.pow(2.0, _selfy);
        dest[destOffset + 2] = Math.pow(2.0, _selfz);
        return dest;
    }

    /** {@link #exp2(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer exp2(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.exp2_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.exp2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #exp2(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer exp2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.exp2_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.exp2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #exp2(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long exp2(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.exp2_unsafe(dest, src);
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
    public static double[] expm1(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.expm1(src[srcOffset]);
        dest[destOffset + 1] = Math.expm1(_selfy);
        dest[destOffset + 2] = Math.expm1(_selfz);
        return dest;
    }

    /** {@link #expm1(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer expm1(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.expm1_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.expm1_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #expm1(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer expm1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.expm1_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.expm1_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #expm1(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long expm1(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.expm1_unsafe(dest, src);
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
    public static double[] faceforward(double[] dest, int destOffset, double[] src, int srcOffset, double IX, double IY, double IZ, double NrefX, double NrefY, double NrefZ) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t3 = Math.fma(IZ, NrefZ, Math.fma(IX, NrefX, IY * NrefY)) < 0.0 ? 1.0 : -1.0;
        dest[destOffset] = src[srcOffset] * _t3;
        dest[destOffset + 1] = _selfy * _t3;
        dest[destOffset + 2] = _selfz * _t3;
        return dest;
    }

    /** {@link #faceforward(double[], int, double[], int, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer faceforward(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double IX, double IY, double IZ, double NrefX, double NrefY, double NrefZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.faceforward_unsafe(dest, destOffset, src, srcOffset, IX, IY, IZ, NrefX, NrefY, NrefZ);
        return Double3OpsKernelsTypedBuffer.faceforward_api(dest, destOffset, src, srcOffset, IX, IY, IZ, NrefX, NrefY, NrefZ);
    }

    /** {@link #faceforward(double[], int, double[], int, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer faceforward(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double IX, double IY, double IZ, double NrefX, double NrefY, double NrefZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.faceforward_unsafe(dest, destOffset, src, srcOffset, IX, IY, IZ, NrefX, NrefY, NrefZ);
        return Double3OpsKernelsByteBuffer.faceforward_api(dest, destOffset, src, srcOffset, IX, IY, IZ, NrefX, NrefY, NrefZ);
    }

    /** {@link #faceforward(double[], int, double[], int, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long faceforward(long dest, long src, double IX, double IY, double IZ, double NrefX, double NrefY, double NrefZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.faceforward_unsafe(dest, src, IX, IY, IZ, NrefX, NrefY, NrefZ);
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
    public static double[] faceforward(double[] dest, int destOffset, double[] src, int srcOffset, double[] I, int IOffset, double[] Nref, int NrefOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t3 = Math.fma(I[IOffset + 2], Nref[NrefOffset + 2], Math.fma(I[IOffset], Nref[NrefOffset], I[IOffset + 1] * Nref[NrefOffset + 1])) < 0.0 ? 1.0 : -1.0;
        dest[destOffset] = src[srcOffset] * _t3;
        dest[destOffset + 1] = _selfy * _t3;
        dest[destOffset + 2] = _selfz * _t3;
        return dest;
    }

    /** {@link #faceforward(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer faceforward(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer I, int IOffset, java.nio.DoubleBuffer Nref, int NrefOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && I.isDirect() && I.order() == java.nio.ByteOrder.nativeOrder() && Nref.isDirect() && Nref.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.faceforward_unsafe(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        return Double3OpsKernelsTypedBuffer.faceforward_api(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
    }

    /** {@link #faceforward(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer faceforward(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer I, int IOffset, java.nio.ByteBuffer Nref, int NrefOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && I.isDirect() && I.order() == java.nio.ByteOrder.nativeOrder() && Nref.isDirect() && Nref.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.faceforward_unsafe(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
        return Double3OpsKernelsByteBuffer.faceforward_api(dest, destOffset, src, srcOffset, I, IOffset, Nref, NrefOffset);
    }

    /** {@link #faceforward(double[], int, double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long faceforward(long dest, long src, long I, long Nref) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.faceforward_unsafe(dest, src, I, Nref);
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
    public static double[] floor(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.floor(src[srcOffset]);
        dest[destOffset + 1] = Math.floor(_selfy);
        dest[destOffset + 2] = Math.floor(_selfz);
        return dest;
    }

    /** {@link #floor(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer floor(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.floor_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.floor_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #floor(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer floor(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.floor_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.floor_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #floor(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long floor(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.floor_unsafe(dest, src);
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
    public static double[] fract(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = java.lang.Math.min(_selfx - Math.floor(_selfx), 0.9999999999999999);
        dest[destOffset + 1] = java.lang.Math.min(_selfy - Math.floor(_selfy), 0.9999999999999999);
        dest[destOffset + 2] = java.lang.Math.min(_selfz - Math.floor(_selfz), 0.9999999999999999);
        return dest;
    }

    /** {@link #fract(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer fract(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.fract_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.fract_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #fract(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer fract(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.fract_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.fract_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #fract(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long fract(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.fract_unsafe(dest, src);
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
    public static double[] hypot(double[] dest, int destOffset, double[] src, int srcOffset, double y) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.hypot(src[srcOffset], y);
        dest[destOffset + 1] = Math.hypot(_selfy, y);
        dest[destOffset + 2] = Math.hypot(_selfz, y);
        return dest;
    }

    /** {@link #hypot(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer hypot(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.hypot_unsafe(dest, destOffset, src, srcOffset, y);
        return Double3OpsKernelsTypedBuffer.hypot_api(dest, destOffset, src, srcOffset, y);
    }

    /** {@link #hypot(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hypot(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.hypot_unsafe(dest, destOffset, src, srcOffset, y);
        return Double3OpsKernelsByteBuffer.hypot_api(dest, destOffset, src, srcOffset, y);
    }

    /** {@link #hypot(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long hypot(long dest, long src, double y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.hypot_unsafe(dest, src, y);
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
    public static double[] hypot(double[] dest, int destOffset, double[] src, int srcOffset, double yX, double yY, double yZ) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.hypot(src[srcOffset], yX);
        dest[destOffset + 1] = Math.hypot(_selfy, yY);
        dest[destOffset + 2] = Math.hypot(_selfz, yZ);
        return dest;
    }

    /** {@link #hypot(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer hypot(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double yX, double yY, double yZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.hypot_unsafe(dest, destOffset, src, srcOffset, yX, yY, yZ);
        return Double3OpsKernelsTypedBuffer.hypot_api(dest, destOffset, src, srcOffset, yX, yY, yZ);
    }

    /** {@link #hypot(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hypot(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double yX, double yY, double yZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.hypot_unsafe(dest, destOffset, src, srcOffset, yX, yY, yZ);
        return Double3OpsKernelsByteBuffer.hypot_api(dest, destOffset, src, srcOffset, yX, yY, yZ);
    }

    /** {@link #hypot(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long hypot(long dest, long src, double yX, double yY, double yZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.hypot_unsafe(dest, src, yX, yY, yZ);
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
    public static double[] hypot(double[] dest, int destOffset, double[] src, int srcOffset, double[] y, int yOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _yy = y[yOffset + 1];
        double _yz = y[yOffset + 2];
        dest[destOffset] = Math.hypot(src[srcOffset], y[yOffset]);
        dest[destOffset + 1] = Math.hypot(_selfy, _yy);
        dest[destOffset + 2] = Math.hypot(_selfz, _yz);
        return dest;
    }

    /** {@link #hypot(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer hypot(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer y, int yOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && y.isDirect() && y.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.hypot_unsafe(dest, destOffset, src, srcOffset, y, yOffset);
        return Double3OpsKernelsTypedBuffer.hypot_api(dest, destOffset, src, srcOffset, y, yOffset);
    }

    /** {@link #hypot(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer hypot(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && y.isDirect() && y.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.hypot_unsafe(dest, destOffset, src, srcOffset, y, yOffset);
        return Double3OpsKernelsByteBuffer.hypot_api(dest, destOffset, src, srcOffset, y, yOffset);
    }

    /** {@link #hypot(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long hypot(long dest, long src, long y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.hypot_unsafe(dest, src, y);
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
    public static double[] inverse(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = 1.0 / src[srcOffset];
        dest[destOffset + 1] = 1.0 / _selfy;
        dest[destOffset + 2] = 1.0 / _selfz;
        return dest;
    }

    /** {@link #inverse(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer inverse(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.inverse_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.inverse_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #inverse(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer inverse(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.inverse_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.inverse_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #inverse(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long inverse(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.inverse_unsafe(dest, src);
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
    public static double[] inverseSqrt(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = (1.0 / java.lang.Math.sqrt(src[srcOffset]));
        dest[destOffset + 1] = (1.0 / java.lang.Math.sqrt(_selfy));
        dest[destOffset + 2] = (1.0 / java.lang.Math.sqrt(_selfz));
        return dest;
    }

    /** {@link #inverseSqrt(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer inverseSqrt(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.inverseSqrt_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.inverseSqrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #inverseSqrt(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer inverseSqrt(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.inverseSqrt_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.inverseSqrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #inverseSqrt(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long inverseSqrt(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.inverseSqrt_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the length of this vector.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return the length of this vector
     */
    public static double length(double[] src, int srcOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        return java.lang.Math.sqrt(Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
    }

    /** {@link #length(double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double length(java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.length_unsafe(src, srcOffset);
        return Double3OpsKernelsTypedBuffer.length_api(src, srcOffset);
    }

    /** {@link #length(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double length(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.length_unsafe(src, srcOffset);
        return Double3OpsKernelsByteBuffer.length_api(src, srcOffset);
    }

    /** {@link #length(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double length(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.length_unsafe(src);
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
    public static double lengthSquared(double[] src, int srcOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        return Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
    }

    /** {@link #lengthSquared(double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double lengthSquared(java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.lengthSquared_unsafe(src, srcOffset);
        return Double3OpsKernelsTypedBuffer.lengthSquared_api(src, srcOffset);
    }

    /** {@link #lengthSquared(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double lengthSquared(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.lengthSquared_unsafe(src, srcOffset);
        return Double3OpsKernelsByteBuffer.lengthSquared_api(src, srcOffset);
    }

    /** {@link #lengthSquared(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double lengthSquared(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.lengthSquared_unsafe(src);
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
    public static double[] log(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.log(src[srcOffset]);
        dest[destOffset + 1] = Math.log(_selfy);
        dest[destOffset + 2] = Math.log(_selfz);
        return dest;
    }

    /** {@link #log(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer log(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.log_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.log_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer log(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.log_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.log_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long log(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.log_unsafe(dest, src);
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
    public static double[] log10(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.log10(src[srcOffset]);
        dest[destOffset + 1] = Math.log10(_selfy);
        dest[destOffset + 2] = Math.log10(_selfz);
        return dest;
    }

    /** {@link #log10(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer log10(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.log10_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.log10_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log10(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer log10(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.log10_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.log10_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log10(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long log10(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.log10_unsafe(dest, src);
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
    public static double[] log1p(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.log1p(src[srcOffset]);
        dest[destOffset + 1] = Math.log1p(_selfy);
        dest[destOffset + 2] = Math.log1p(_selfz);
        return dest;
    }

    /** {@link #log1p(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer log1p(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.log1p_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.log1p_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log1p(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer log1p(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.log1p_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.log1p_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log1p(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long log1p(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.log1p_unsafe(dest, src);
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
    public static double[] log2(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.log2(src[srcOffset]);
        dest[destOffset + 1] = Math.log2(_selfy);
        dest[destOffset + 2] = Math.log2(_selfz);
        return dest;
    }

    /** {@link #log2(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer log2(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.log2_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.log2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log2(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer log2(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.log2_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.log2_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #log2(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long log2(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.log2_unsafe(dest, src);
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
    public static double manhattanDistance(double[] src, int srcOffset, double otherX, double otherY, double otherZ) {
        return java.lang.Math.abs(src[srcOffset] - otherX) + java.lang.Math.abs(src[srcOffset + 1] - otherY) + java.lang.Math.abs(src[srcOffset + 2] - otherZ);
    }

    /** {@link #manhattanDistance(double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double manhattanDistance(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.manhattanDistance_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsTypedBuffer.manhattanDistance_api(src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #manhattanDistance(double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double manhattanDistance(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.manhattanDistance_unsafe(src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsByteBuffer.manhattanDistance_api(src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #manhattanDistance(double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double manhattanDistance(long src, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.manhattanDistance_unsafe(src, otherX, otherY, otherZ);
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
    public static double manhattanDistance(double[] src, int srcOffset, double[] other, int otherOffset) {
        return java.lang.Math.abs(src[srcOffset] - other[otherOffset]) + java.lang.Math.abs(src[srcOffset + 1] - other[otherOffset + 1]) + java.lang.Math.abs(src[srcOffset + 2] - other[otherOffset + 2]);
    }

    /** {@link #manhattanDistance(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double manhattanDistance(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.manhattanDistance_unsafe(src, srcOffset, other, otherOffset);
        return Double3OpsKernelsTypedBuffer.manhattanDistance_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #manhattanDistance(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double manhattanDistance(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.manhattanDistance_unsafe(src, srcOffset, other, otherOffset);
        return Double3OpsKernelsByteBuffer.manhattanDistance_api(src, srcOffset, other, otherOffset);
    }

    /** {@link #manhattanDistance(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double manhattanDistance(long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.manhattanDistance_unsafe(src, other);
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
    public static double manhattanLength(double[] src, int srcOffset) {
        return java.lang.Math.abs(src[srcOffset]) + java.lang.Math.abs(src[srcOffset + 1]) + java.lang.Math.abs(src[srcOffset + 2]);
    }

    /** {@link #manhattanLength(double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double manhattanLength(java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.manhattanLength_unsafe(src, srcOffset);
        return Double3OpsKernelsTypedBuffer.manhattanLength_api(src, srcOffset);
    }

    /** {@link #manhattanLength(double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double manhattanLength(java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.manhattanLength_unsafe(src, srcOffset);
        return Double3OpsKernelsByteBuffer.manhattanLength_api(src, srcOffset);
    }

    /** {@link #manhattanLength(double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double manhattanLength(long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.manhattanLength_unsafe(src);
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
    public static double[] max(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = java.lang.Math.max(src[srcOffset], scalar);
        dest[destOffset + 1] = java.lang.Math.max(_selfy, scalar);
        dest[destOffset + 2] = java.lang.Math.max(_selfz, scalar);
        return dest;
    }

    /** {@link #max(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer max(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.max_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Double3OpsKernelsTypedBuffer.max_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #max(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer max(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.max_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Double3OpsKernelsByteBuffer.max_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #max(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long max(long dest, long src, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.max_unsafe(dest, src, scalar);
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
    public static double[] max(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = java.lang.Math.max(src[srcOffset], otherX);
        dest[destOffset + 1] = java.lang.Math.max(_selfy, otherY);
        dest[destOffset + 2] = java.lang.Math.max(_selfz, otherZ);
        return dest;
    }

    /** {@link #max(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer max(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.max_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsTypedBuffer.max_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #max(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer max(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.max_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsByteBuffer.max_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #max(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long max(long dest, long src, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.max_unsafe(dest, src, otherX, otherY, otherZ);
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
    public static double[] max(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        dest[destOffset] = java.lang.Math.max(src[srcOffset], other[otherOffset]);
        dest[destOffset + 1] = java.lang.Math.max(_selfy, _othery);
        dest[destOffset + 2] = java.lang.Math.max(_selfz, _otherz);
        return dest;
    }

    /** {@link #max(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer max(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.max_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3OpsKernelsTypedBuffer.max_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #max(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer max(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.max_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3OpsKernelsByteBuffer.max_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #max(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long max(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.max_unsafe(dest, src, other);
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
    public static double[] min(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = java.lang.Math.min(src[srcOffset], scalar);
        dest[destOffset + 1] = java.lang.Math.min(_selfy, scalar);
        dest[destOffset + 2] = java.lang.Math.min(_selfz, scalar);
        return dest;
    }

    /** {@link #min(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer min(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.min_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Double3OpsKernelsTypedBuffer.min_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #min(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer min(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.min_unsafe(dest, destOffset, src, srcOffset, scalar);
        return Double3OpsKernelsByteBuffer.min_api(dest, destOffset, src, srcOffset, scalar);
    }

    /** {@link #min(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long min(long dest, long src, double scalar) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.min_unsafe(dest, src, scalar);
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
    public static double[] min(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = java.lang.Math.min(src[srcOffset], otherX);
        dest[destOffset + 1] = java.lang.Math.min(_selfy, otherY);
        dest[destOffset + 2] = java.lang.Math.min(_selfz, otherZ);
        return dest;
    }

    /** {@link #min(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer min(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.min_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsTypedBuffer.min_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #min(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer min(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.min_unsafe(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
        return Double3OpsKernelsByteBuffer.min_api(dest, destOffset, src, srcOffset, otherX, otherY, otherZ);
    }

    /** {@link #min(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long min(long dest, long src, double otherX, double otherY, double otherZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.min_unsafe(dest, src, otherX, otherY, otherZ);
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
    public static double[] min(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        dest[destOffset] = java.lang.Math.min(src[srcOffset], other[otherOffset]);
        dest[destOffset + 1] = java.lang.Math.min(_selfy, _othery);
        dest[destOffset + 2] = java.lang.Math.min(_selfz, _otherz);
        return dest;
    }

    /** {@link #min(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer min(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.min_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3OpsKernelsTypedBuffer.min_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #min(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer min(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.min_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return Double3OpsKernelsByteBuffer.min_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    /** {@link #min(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long min(long dest, long src, long other) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.min_unsafe(dest, src, other);
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
    public static double[] mod(double[] dest, int destOffset, double[] src, int srcOffset, double y) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = flooredMod(_selfx, y);
        dest[destOffset + 1] = flooredMod(_selfy, y);
        dest[destOffset + 2] = flooredMod(_selfz, y);
        return dest;
    }

    /** {@link #mod(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mod(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.mod_unsafe(dest, destOffset, src, srcOffset, y);
        return Double3OpsKernelsTypedBuffer.mod_api(dest, destOffset, src, srcOffset, y);
    }

    /** {@link #mod(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mod(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.mod_unsafe(dest, destOffset, src, srcOffset, y);
        return Double3OpsKernelsByteBuffer.mod_api(dest, destOffset, src, srcOffset, y);
    }

    /** {@link #mod(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mod(long dest, long src, double y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.mod_unsafe(dest, src, y);
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
    public static double[] mod(double[] dest, int destOffset, double[] src, int srcOffset, double yX, double yY, double yZ) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = flooredMod(_selfx, yX);
        dest[destOffset + 1] = flooredMod(_selfy, yY);
        dest[destOffset + 2] = flooredMod(_selfz, yZ);
        return dest;
    }

    /** {@link #mod(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mod(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double yX, double yY, double yZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.mod_unsafe(dest, destOffset, src, srcOffset, yX, yY, yZ);
        return Double3OpsKernelsTypedBuffer.mod_api(dest, destOffset, src, srcOffset, yX, yY, yZ);
    }

    /** {@link #mod(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mod(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double yX, double yY, double yZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.mod_unsafe(dest, destOffset, src, srcOffset, yX, yY, yZ);
        return Double3OpsKernelsByteBuffer.mod_api(dest, destOffset, src, srcOffset, yX, yY, yZ);
    }

    /** {@link #mod(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mod(long dest, long src, double yX, double yY, double yZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.mod_unsafe(dest, src, yX, yY, yZ);
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
    public static double[] mod(double[] dest, int destOffset, double[] src, int srcOffset, double[] y, int yOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _yx = y[yOffset];
        double _yy = y[yOffset + 1];
        double _yz = y[yOffset + 2];
        dest[destOffset] = flooredMod(_selfx, _yx);
        dest[destOffset + 1] = flooredMod(_selfy, _yy);
        dest[destOffset + 2] = flooredMod(_selfz, _yz);
        return dest;
    }

    /** {@link #mod(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer mod(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer y, int yOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && y.isDirect() && y.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.mod_unsafe(dest, destOffset, src, srcOffset, y, yOffset);
        return Double3OpsKernelsTypedBuffer.mod_api(dest, destOffset, src, srcOffset, y, yOffset);
    }

    /** {@link #mod(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer mod(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer y, int yOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && y.isDirect() && y.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.mod_unsafe(dest, destOffset, src, srcOffset, y, yOffset);
        return Double3OpsKernelsByteBuffer.mod_api(dest, destOffset, src, srcOffset, y, yOffset);
    }

    /** {@link #mod(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long mod(long dest, long src, long y) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.mod_unsafe(dest, src, y);
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
    public static double[] nextDown(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.nextDown(src[srcOffset]);
        dest[destOffset + 1] = Math.nextDown(_selfy);
        dest[destOffset + 2] = Math.nextDown(_selfz);
        return dest;
    }

    /** {@link #nextDown(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer nextDown(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.nextDown_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.nextDown_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #nextDown(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer nextDown(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.nextDown_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.nextDown_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #nextDown(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long nextDown(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.nextDown_unsafe(dest, src);
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
    public static double[] nextUp(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.nextUp(src[srcOffset]);
        dest[destOffset + 1] = Math.nextUp(_selfy);
        dest[destOffset + 2] = Math.nextUp(_selfz);
        return dest;
    }

    /** {@link #nextUp(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer nextUp(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.nextUp_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.nextUp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #nextUp(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer nextUp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.nextUp_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.nextUp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #nextUp(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long nextUp(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.nextUp_unsafe(dest, src);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Normalize this vector to unit length (the zero vector yields the zero vector) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @param destOffset the element index in {@code dest} at which the vector starts
     * @param src the storage holding the vector
     * @param srcOffset the element index in {@code src} at which the vector starts
     * @return {@code dest}
     */
    public static double[] normalize(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            dest[destOffset] = _selfx * _t3;
            dest[destOffset + 1] = _selfy * _t3;
            dest[destOffset + 2] = _selfz * _t3;
        } else {
            dest[destOffset] = 0.0;
            dest[destOffset + 1] = 0.0;
            dest[destOffset + 2] = 0.0;
        }
        return dest;
    }

    /** {@link #normalize(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer normalize(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.normalize_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.normalize_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalize(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer normalize(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.normalize_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.normalize_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #normalize(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long normalize(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.normalize_unsafe(dest, src);
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
    public static double[] normalizeMul(double[] dest, int destOffset, double[] src, int srcOffset, double length) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t4 = length * (1.0 / java.lang.Math.sqrt(_t2));
        if (_t2 != 0.0) {
            dest[destOffset] = _selfx * _t4;
            dest[destOffset + 1] = _selfy * _t4;
            dest[destOffset + 2] = _selfz * _t4;
        } else {
            dest[destOffset] = 0.0;
            dest[destOffset + 1] = 0.0;
            dest[destOffset + 2] = 0.0;
        }
        return dest;
    }

    /** {@link #normalizeMul(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer normalizeMul(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double length) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.normalizeMul_unsafe(dest, destOffset, src, srcOffset, length);
        return Double3OpsKernelsTypedBuffer.normalizeMul_api(dest, destOffset, src, srcOffset, length);
    }

    /** {@link #normalizeMul(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer normalizeMul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double length) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.normalizeMul_unsafe(dest, destOffset, src, srcOffset, length);
        return Double3OpsKernelsByteBuffer.normalizeMul_api(dest, destOffset, src, srcOffset, length);
    }

    /** {@link #normalizeMul(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long normalizeMul(long dest, long src, double length) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.normalizeMul_unsafe(dest, src, length);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the signed angle in radians between this vector and ({@code otherX}, {@code otherY},
     * {@code otherZ}), positive when the rotation from this vector to ({@code otherX},
     * {@code otherY}, {@code otherZ}) is counter-clockwise as seen from the direction of the given
     * normal.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code double} range, the vectors are first scaled exactly by powers of two.
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
    public static double orientedAngle(double[] src, int srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t7 = unitScale(normalX, normalY, normalZ);
        double _t9 = Math.fma(otherY, _selfx, -(otherX * _selfy));
        double _t10 = Math.fma(otherX, _selfz, -(otherZ * _selfx));
        double _t11 = Math.fma(otherZ, _selfy, -(otherY * _selfz));
        double _ct0 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsArray.orientedAngle_degenerate(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        double _t18 = Math.atan2(java.lang.Math.sqrt(_ct0), Math.fma(otherZ, _selfz, Math.fma(otherX, _selfx, otherY * _selfy)));
        return Math.fma(_t9, normalZ * _t7, Math.fma(_t10, normalY * _t7, _t11 * (normalX * _t7))) < 0.0 ? -_t18 : _t18;
    }

    /** {@link #orientedAngle(double[], int, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double orientedAngle(java.nio.DoubleBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.orientedAngle_unsafe(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return Double3OpsKernelsTypedBuffer.orientedAngle_api(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    /** {@link #orientedAngle(double[], int, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double orientedAngle(java.nio.ByteBuffer src, int srcOffset, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.orientedAngle_unsafe(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
        return Double3OpsKernelsByteBuffer.orientedAngle_api(src, srcOffset, otherX, otherY, otherZ, normalX, normalY, normalZ);
    }

    /** {@link #orientedAngle(double[], int, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double orientedAngle(long src, double otherX, double otherY, double otherZ, double normalX, double normalY, double normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.orientedAngle_unsafe(src, otherX, otherY, otherZ, normalX, normalY, normalZ);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the signed angle in radians between this vector and {@code other}, positive when the
     * rotation from this vector to {@code other} is counter-clockwise as seen from the direction of
     * the given normal.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code double} range, the vectors are first scaled exactly by powers of two.
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
    public static double orientedAngle(double[] src, int srcOffset, double[] other, int otherOffset, double[] normal, int normalOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _otherx = other[otherOffset];
        double _othery = other[otherOffset + 1];
        double _otherz = other[otherOffset + 2];
        double _normalx = normal[normalOffset];
        double _normaly = normal[normalOffset + 1];
        double _normalz = normal[normalOffset + 2];
        double _t7 = unitScale(_normalx, _normaly, _normalz);
        double _t9 = Math.fma(_othery, _selfx, -(_otherx * _selfy));
        double _t10 = Math.fma(_otherx, _selfz, -(_otherz * _selfx));
        double _t11 = Math.fma(_otherz, _selfy, -(_othery * _selfz));
        double _ct0 = Math.fma(_t9, _t9, Math.fma(_t10, _t10, _t11 * _t11));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsArray.orientedAngle_degenerate(src, srcOffset, other, otherOffset, normal, normalOffset);
        double _t18 = Math.atan2(java.lang.Math.sqrt(_ct0), Math.fma(_otherz, _selfz, Math.fma(_otherx, _selfx, _othery * _selfy)));
        return Math.fma(_t9, _normalz * _t7, Math.fma(_t10, _normaly * _t7, _t11 * (_normalx * _t7))) < 0.0 ? -_t18 : _t18;
    }

    /** {@link #orientedAngle(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static double orientedAngle(java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer other, int otherOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.orientedAngle_unsafe(src, srcOffset, other, otherOffset, normal, normalOffset);
        return Double3OpsKernelsTypedBuffer.orientedAngle_api(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    /** {@link #orientedAngle(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static double orientedAngle(java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer other, int otherOffset, java.nio.ByteBuffer normal, int normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && other.isDirect() && other.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.orientedAngle_unsafe(src, srcOffset, other, otherOffset, normal, normalOffset);
        return Double3OpsKernelsByteBuffer.orientedAngle_api(src, srcOffset, other, otherOffset, normal, normalOffset);
    }

    /** {@link #orientedAngle(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static double orientedAngle(long src, long other, long normal) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.orientedAngle_unsafe(src, other, normal);
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
    public static double[] outerProduct(double[] dest, int destOffset, double[] src, int srcOffset, double rowX, double rowY, double rowZ) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = rowX * _selfx;
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

    /** {@link #outerProduct(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer outerProduct(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double rowX, double rowY, double rowZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.outerProduct_unsafe(dest, destOffset, src, srcOffset, rowX, rowY, rowZ);
        return Double3OpsKernelsTypedBuffer.outerProduct_api(dest, destOffset, src, srcOffset, rowX, rowY, rowZ);
    }

    /** {@link #outerProduct(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer outerProduct(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double rowX, double rowY, double rowZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.outerProduct_unsafe(dest, destOffset, src, srcOffset, rowX, rowY, rowZ);
        return Double3OpsKernelsByteBuffer.outerProduct_api(dest, destOffset, src, srcOffset, rowX, rowY, rowZ);
    }

    /** {@link #outerProduct(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long outerProduct(long dest, long src, double rowX, double rowY, double rowZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.outerProduct_unsafe(dest, src, rowX, rowY, rowZ);
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
    public static double[] outerProduct(double[] dest, int destOffset, double[] src, int srcOffset, double[] row, int rowOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _rowx = row[rowOffset];
        double _rowy = row[rowOffset + 1];
        double _rowz = row[rowOffset + 2];
        dest[destOffset] = _rowx * _selfx;
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

    /** {@link #outerProduct(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer outerProduct(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer row, int rowOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && row.isDirect() && row.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.outerProduct_unsafe(dest, destOffset, src, srcOffset, row, rowOffset);
        return Double3OpsKernelsTypedBuffer.outerProduct_api(dest, destOffset, src, srcOffset, row, rowOffset);
    }

    /** {@link #outerProduct(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer outerProduct(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer row, int rowOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && row.isDirect() && row.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.outerProduct_unsafe(dest, destOffset, src, srcOffset, row, rowOffset);
        return Double3OpsKernelsByteBuffer.outerProduct_api(dest, destOffset, src, srcOffset, row, rowOffset);
    }

    /** {@link #outerProduct(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long outerProduct(long dest, long src, long row) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.outerProduct_unsafe(dest, src, row);
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
    public static double[] perpendicular(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        if (java.lang.Math.abs(_selfz) < java.lang.Math.abs(_selfx)) {
            dest[destOffset] = _selfy;
            dest[destOffset + 1] = -_selfx;
            dest[destOffset + 2] = 0.0;
        } else {
            dest[destOffset] = 0.0;
            dest[destOffset + 1] = _selfz;
            dest[destOffset + 2] = -_selfy;
        }
        return dest;
    }

    /** {@link #perpendicular(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer perpendicular(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.perpendicular_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.perpendicular_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #perpendicular(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer perpendicular(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.perpendicular_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.perpendicular_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #perpendicular(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long perpendicular(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.perpendicular_unsafe(dest, src);
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
    public static double[] pow(double[] dest, int destOffset, double[] src, int srcOffset, double exponent) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.pow(src[srcOffset], exponent);
        dest[destOffset + 1] = Math.pow(_selfy, exponent);
        dest[destOffset + 2] = Math.pow(_selfz, exponent);
        return dest;
    }

    /** {@link #pow(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer pow(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double exponent) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.pow_unsafe(dest, destOffset, src, srcOffset, exponent);
        return Double3OpsKernelsTypedBuffer.pow_api(dest, destOffset, src, srcOffset, exponent);
    }

    /** {@link #pow(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer pow(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double exponent) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.pow_unsafe(dest, destOffset, src, srcOffset, exponent);
        return Double3OpsKernelsByteBuffer.pow_api(dest, destOffset, src, srcOffset, exponent);
    }

    /** {@link #pow(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long pow(long dest, long src, double exponent) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.pow_unsafe(dest, src, exponent);
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
    public static double[] pow(double[] dest, int destOffset, double[] src, int srcOffset, double exponentX, double exponentY, double exponentZ) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.pow(src[srcOffset], exponentX);
        dest[destOffset + 1] = Math.pow(_selfy, exponentY);
        dest[destOffset + 2] = Math.pow(_selfz, exponentZ);
        return dest;
    }

    /** {@link #pow(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer pow(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double exponentX, double exponentY, double exponentZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.pow_unsafe(dest, destOffset, src, srcOffset, exponentX, exponentY, exponentZ);
        return Double3OpsKernelsTypedBuffer.pow_api(dest, destOffset, src, srcOffset, exponentX, exponentY, exponentZ);
    }

    /** {@link #pow(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer pow(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double exponentX, double exponentY, double exponentZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.pow_unsafe(dest, destOffset, src, srcOffset, exponentX, exponentY, exponentZ);
        return Double3OpsKernelsByteBuffer.pow_api(dest, destOffset, src, srcOffset, exponentX, exponentY, exponentZ);
    }

    /** {@link #pow(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long pow(long dest, long src, double exponentX, double exponentY, double exponentZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.pow_unsafe(dest, src, exponentX, exponentY, exponentZ);
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
    public static double[] pow(double[] dest, int destOffset, double[] src, int srcOffset, double[] exponent, int exponentOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _exponenty = exponent[exponentOffset + 1];
        double _exponentz = exponent[exponentOffset + 2];
        dest[destOffset] = Math.pow(src[srcOffset], exponent[exponentOffset]);
        dest[destOffset + 1] = Math.pow(_selfy, _exponenty);
        dest[destOffset + 2] = Math.pow(_selfz, _exponentz);
        return dest;
    }

    /** {@link #pow(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer pow(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer exponent, int exponentOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && exponent.isDirect() && exponent.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.pow_unsafe(dest, destOffset, src, srcOffset, exponent, exponentOffset);
        return Double3OpsKernelsTypedBuffer.pow_api(dest, destOffset, src, srcOffset, exponent, exponentOffset);
    }

    /** {@link #pow(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer pow(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer exponent, int exponentOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && exponent.isDirect() && exponent.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.pow_unsafe(dest, destOffset, src, srcOffset, exponent, exponentOffset);
        return Double3OpsKernelsByteBuffer.pow_api(dest, destOffset, src, srcOffset, exponent, exponentOffset);
    }

    /** {@link #pow(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long pow(long dest, long src, long exponent) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.pow_unsafe(dest, src, exponent);
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
    public static double[] project(double[] dest, int destOffset, double[] src, int srcOffset, double ontoX, double ontoY, double ontoZ) {
        double _t7 = Math.fma(ontoZ, src[srcOffset + 2], Math.fma(ontoX, src[srcOffset], ontoY * src[srcOffset + 1])) / Math.fma(ontoZ, ontoZ, Math.fma(ontoX, ontoX, ontoY * ontoY));
        dest[destOffset] = ontoX * _t7;
        dest[destOffset + 1] = ontoY * _t7;
        dest[destOffset + 2] = ontoZ * _t7;
        return dest;
    }

    /** {@link #project(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer project(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double ontoX, double ontoY, double ontoZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.project_unsafe(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ);
        return Double3OpsKernelsTypedBuffer.project_api(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ);
    }

    /** {@link #project(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer project(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double ontoX, double ontoY, double ontoZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.project_unsafe(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ);
        return Double3OpsKernelsByteBuffer.project_api(dest, destOffset, src, srcOffset, ontoX, ontoY, ontoZ);
    }

    /** {@link #project(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long project(long dest, long src, double ontoX, double ontoY, double ontoZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.project_unsafe(dest, src, ontoX, ontoY, ontoZ);
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
    public static double[] project(double[] dest, int destOffset, double[] src, int srcOffset, double[] onto, int ontoOffset) {
        double _ontox = onto[ontoOffset];
        double _ontoy = onto[ontoOffset + 1];
        double _ontoz = onto[ontoOffset + 2];
        double _t7 = Math.fma(_ontoz, src[srcOffset + 2], Math.fma(_ontox, src[srcOffset], _ontoy * src[srcOffset + 1])) / Math.fma(_ontoz, _ontoz, Math.fma(_ontox, _ontox, _ontoy * _ontoy));
        dest[destOffset] = _ontox * _t7;
        dest[destOffset + 1] = _ontoy * _t7;
        dest[destOffset + 2] = _ontoz * _t7;
        return dest;
    }

    /** {@link #project(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer project(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer onto, int ontoOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && onto.isDirect() && onto.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.project_unsafe(dest, destOffset, src, srcOffset, onto, ontoOffset);
        return Double3OpsKernelsTypedBuffer.project_api(dest, destOffset, src, srcOffset, onto, ontoOffset);
    }

    /** {@link #project(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer project(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer onto, int ontoOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && onto.isDirect() && onto.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.project_unsafe(dest, destOffset, src, srcOffset, onto, ontoOffset);
        return Double3OpsKernelsByteBuffer.project_api(dest, destOffset, src, srcOffset, onto, ontoOffset);
    }

    /** {@link #project(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long project(long dest, long src, long onto) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.project_unsafe(dest, src, onto);
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
    public static double[] projectOnPlane(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t2 = Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy));
        dest[destOffset] = Math.fma(-normalX, _t2, _selfx);
        dest[destOffset + 1] = Math.fma(-normalY, _t2, _selfy);
        dest[destOffset + 2] = Math.fma(-normalZ, _t2, _selfz);
        return dest;
    }

    /** {@link #projectOnPlane(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer projectOnPlane(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.projectOnPlane_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return Double3OpsKernelsTypedBuffer.projectOnPlane_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
    }

    /** {@link #projectOnPlane(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer projectOnPlane(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY, double normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.projectOnPlane_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return Double3OpsKernelsByteBuffer.projectOnPlane_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
    }

    /** {@link #projectOnPlane(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long projectOnPlane(long dest, long src, double normalX, double normalY, double normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.projectOnPlane_unsafe(dest, src, normalX, normalY, normalZ);
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
    public static double[] projectOnPlane(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _normalx = normal[normalOffset];
        double _normaly = normal[normalOffset + 1];
        double _normalz = normal[normalOffset + 2];
        double _t2 = Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy));
        dest[destOffset] = Math.fma(-_normalx, _t2, _selfx);
        dest[destOffset + 1] = Math.fma(-_normaly, _t2, _selfy);
        dest[destOffset + 2] = Math.fma(-_normalz, _t2, _selfz);
        return dest;
    }

    /** {@link #projectOnPlane(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer projectOnPlane(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.projectOnPlane_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return Double3OpsKernelsTypedBuffer.projectOnPlane_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    /** {@link #projectOnPlane(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer projectOnPlane(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.projectOnPlane_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return Double3OpsKernelsByteBuffer.projectOnPlane_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    /** {@link #projectOnPlane(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long projectOnPlane(long dest, long src, long normal) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.projectOnPlane_unsafe(dest, src, normal);
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
    public static double[] radians(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.toRadians(src[srcOffset]);
        dest[destOffset + 1] = Math.toRadians(_selfy);
        dest[destOffset + 2] = Math.toRadians(_selfz);
        return dest;
    }

    /** {@link #radians(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer radians(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.radians_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.radians_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #radians(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer radians(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.radians_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.radians_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #radians(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long radians(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.radians_unsafe(dest, src);
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
    public static double[] reflect(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t3 = 2.0 * Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy));
        dest[destOffset] = Math.fma(-normalX, _t3, _selfx);
        dest[destOffset + 1] = Math.fma(-normalY, _t3, _selfy);
        dest[destOffset + 2] = Math.fma(-normalZ, _t3, _selfz);
        return dest;
    }

    /** {@link #reflect(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer reflect(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.reflect_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return Double3OpsKernelsTypedBuffer.reflect_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
    }

    /** {@link #reflect(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer reflect(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY, double normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.reflect_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return Double3OpsKernelsByteBuffer.reflect_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
    }

    /** {@link #reflect(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long reflect(long dest, long src, double normalX, double normalY, double normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.reflect_unsafe(dest, src, normalX, normalY, normalZ);
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
    public static double[] reflect(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _normalx = normal[normalOffset];
        double _normaly = normal[normalOffset + 1];
        double _normalz = normal[normalOffset + 2];
        double _t3 = 2.0 * Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy));
        dest[destOffset] = Math.fma(-_normalx, _t3, _selfx);
        dest[destOffset + 1] = Math.fma(-_normaly, _t3, _selfy);
        dest[destOffset + 2] = Math.fma(-_normalz, _t3, _selfz);
        return dest;
    }

    /** {@link #reflect(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer reflect(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.reflect_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return Double3OpsKernelsTypedBuffer.reflect_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    /** {@link #reflect(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer reflect(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.reflect_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return Double3OpsKernelsByteBuffer.reflect_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    /** {@link #reflect(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long reflect(long dest, long src, long normal) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.reflect_unsafe(dest, src, normal);
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
    public static double[] refract(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ, double eta) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t3 = Math.fma(normalZ, _selfz, Math.fma(normalX, _selfx, normalY * _selfy));
        double _t7 = Math.fma(-Math.fma(-_t3, _t3, 1.0), eta * eta, 1.0);
        double _t10 = Math.fma(eta, _t3, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t7)));
        if (_t7 >= 0.0) {
            dest[destOffset] = Math.fma(eta, _selfx, -(normalX * _t10));
            dest[destOffset + 1] = Math.fma(eta, _selfy, -(normalY * _t10));
            dest[destOffset + 2] = Math.fma(eta, _selfz, -(normalZ * _t10));
        } else {
            dest[destOffset] = 0.0;
            dest[destOffset + 1] = 0.0;
            dest[destOffset + 2] = 0.0;
        }
        return dest;
    }

    /** {@link #refract(double[], int, double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer refract(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.refract_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, eta);
        return Double3OpsKernelsTypedBuffer.refract_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, eta);
    }

    /** {@link #refract(double[], int, double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer refract(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double normalX, double normalY, double normalZ, double eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.refract_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, eta);
        return Double3OpsKernelsByteBuffer.refract_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, eta);
    }

    /** {@link #refract(double[], int, double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long refract(long dest, long src, double normalX, double normalY, double normalZ, double eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.refract_unsafe(dest, src, normalX, normalY, normalZ, eta);
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
    public static double[] refract(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset, double eta) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _normalx = normal[normalOffset];
        double _normaly = normal[normalOffset + 1];
        double _normalz = normal[normalOffset + 2];
        double _t3 = Math.fma(_normalz, _selfz, Math.fma(_normalx, _selfx, _normaly * _selfy));
        double _t7 = Math.fma(-Math.fma(-_t3, _t3, 1.0), eta * eta, 1.0);
        double _t10 = Math.fma(eta, _t3, java.lang.Math.sqrt(java.lang.Math.max(0.0, _t7)));
        if (_t7 >= 0.0) {
            dest[destOffset] = Math.fma(eta, _selfx, -(_normalx * _t10));
            dest[destOffset + 1] = Math.fma(eta, _selfy, -(_normaly * _t10));
            dest[destOffset + 2] = Math.fma(eta, _selfz, -(_normalz * _t10));
        } else {
            dest[destOffset] = 0.0;
            dest[destOffset + 1] = 0.0;
            dest[destOffset + 2] = 0.0;
        }
        return dest;
    }

    /** {@link #refract(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer refract(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer normal, int normalOffset, double eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.refract_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return Double3OpsKernelsTypedBuffer.refract_api(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
    }

    /** {@link #refract(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer refract(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer normal, int normalOffset, double eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && normal.isDirect() && normal.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.refract_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
        return Double3OpsKernelsByteBuffer.refract_api(dest, destOffset, src, srcOffset, normal, normalOffset, eta);
    }

    /** {@link #refract(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long refract(long dest, long src, long normal, double eta) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.refract_unsafe(dest, src, normal, eta);
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
    public static double[] round(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.rint(src[srcOffset]);
        dest[destOffset + 1] = Math.rint(_selfy);
        dest[destOffset + 2] = Math.rint(_selfz);
        return dest;
    }

    /** {@link #round(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer round(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.round_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.round_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #round(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer round(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.round_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.round_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #round(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long round(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.round_unsafe(dest, src);
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
    public static double[] sign(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.signum(src[srcOffset]);
        dest[destOffset + 1] = Math.signum(_selfy);
        dest[destOffset + 2] = Math.signum(_selfz);
        return dest;
    }

    /** {@link #sign(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer sign(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.sign_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.sign_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sign(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sign(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.sign_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.sign_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sign(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long sign(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.sign_unsafe(dest, src);
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
    public static double[] sin(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.sin(src[srcOffset]);
        dest[destOffset + 1] = Math.sin(_selfy);
        dest[destOffset + 2] = Math.sin(_selfz);
        return dest;
    }

    /** {@link #sin(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer sin(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.sin_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.sin_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sin(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sin(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.sin_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.sin_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sin(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long sin(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.sin_unsafe(dest, src);
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
    public static double[] sinh(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.sinh(src[srcOffset]);
        dest[destOffset + 1] = Math.sinh(_selfy);
        dest[destOffset + 2] = Math.sinh(_selfz);
        return dest;
    }

    /** {@link #sinh(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer sinh(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.sinh_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.sinh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sinh(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sinh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.sinh_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.sinh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sinh(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long sinh(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.sinh_unsafe(dest, src);
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
    public static double[] smoothstep(double[] dest, int destOffset, double[] src, int srcOffset, double edge0, double edge1) {
        double _t0_inv = 1.0 / (edge1 - edge0);
        double _t10 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src[srcOffset] - edge0) * _t0_inv));
        double _t11 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src[srcOffset + 1] - edge0) * _t0_inv));
        double _t12 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src[srcOffset + 2] - edge0) * _t0_inv));
        dest[destOffset] = Math.fma(-2.0, _t10, 3.0) * _t10 * _t10;
        dest[destOffset + 1] = Math.fma(-2.0, _t11, 3.0) * _t11 * _t11;
        dest[destOffset + 2] = Math.fma(-2.0, _t12, 3.0) * _t12 * _t12;
        return dest;
    }

    /** {@link #smoothstep(double[], int, double[], int, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer smoothstep(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0, double edge1) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0, edge1);
        return Double3OpsKernelsTypedBuffer.smoothstep_api(dest, destOffset, src, srcOffset, edge0, edge1);
    }

    /** {@link #smoothstep(double[], int, double[], int, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer smoothstep(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge0, double edge1) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0, edge1);
        return Double3OpsKernelsByteBuffer.smoothstep_api(dest, destOffset, src, srcOffset, edge0, edge1);
    }

    /** {@link #smoothstep(double[], int, double[], int, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long smoothstep(long dest, long src, double edge0, double edge1) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.smoothstep_unsafe(dest, src, edge0, edge1);
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
    public static double[] smoothstep(double[] dest, int destOffset, double[] src, int srcOffset, double edge0X, double edge0Y, double edge0Z, double edge1X, double edge1Y, double edge1Z) {
        double _t12 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src[srcOffset] - edge0X) / (edge1X - edge0X)));
        double _t13 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src[srcOffset + 1] - edge0Y) / (edge1Y - edge0Y)));
        double _t14 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src[srcOffset + 2] - edge0Z) / (edge1Z - edge0Z)));
        dest[destOffset] = Math.fma(-2.0, _t12, 3.0) * _t12 * _t12;
        dest[destOffset + 1] = Math.fma(-2.0, _t13, 3.0) * _t13 * _t13;
        dest[destOffset + 2] = Math.fma(-2.0, _t14, 3.0) * _t14 * _t14;
        return dest;
    }

    /** {@link #smoothstep(double[], int, double[], int, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer smoothstep(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge0X, double edge0Y, double edge0Z, double edge1X, double edge1Y, double edge1Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
        return Double3OpsKernelsTypedBuffer.smoothstep_api(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
    }

    /** {@link #smoothstep(double[], int, double[], int, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer smoothstep(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge0X, double edge0Y, double edge0Z, double edge1X, double edge1Y, double edge1Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
        return Double3OpsKernelsByteBuffer.smoothstep_api(dest, destOffset, src, srcOffset, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
    }

    /** {@link #smoothstep(double[], int, double[], int, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long smoothstep(long dest, long src, double edge0X, double edge0Y, double edge0Z, double edge1X, double edge1Y, double edge1Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.smoothstep_unsafe(dest, src, edge0X, edge0Y, edge0Z, edge1X, edge1Y, edge1Z);
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
    public static double[] smoothstep(double[] dest, int destOffset, double[] src, int srcOffset, double[] edge0, int edge0Offset, double[] edge1, int edge1Offset) {
        double _edge0x = edge0[edge0Offset];
        double _edge0y = edge0[edge0Offset + 1];
        double _edge0z = edge0[edge0Offset + 2];
        double _t12 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src[srcOffset] - _edge0x) / (edge1[edge1Offset] - _edge0x)));
        double _t13 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src[srcOffset + 1] - _edge0y) / (edge1[edge1Offset + 1] - _edge0y)));
        double _t14 = java.lang.Math.max(0.0, java.lang.Math.min(1.0, (src[srcOffset + 2] - _edge0z) / (edge1[edge1Offset + 2] - _edge0z)));
        dest[destOffset] = Math.fma(-2.0, _t12, 3.0) * _t12 * _t12;
        dest[destOffset + 1] = Math.fma(-2.0, _t13, 3.0) * _t13 * _t13;
        dest[destOffset + 2] = Math.fma(-2.0, _t14, 3.0) * _t14 * _t14;
        return dest;
    }

    /** {@link #smoothstep(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer smoothstep(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge0, int edge0Offset, java.nio.DoubleBuffer edge1, int edge1Offset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && edge0.isDirect() && edge0.order() == java.nio.ByteOrder.nativeOrder() && edge1.isDirect() && edge1.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        return Double3OpsKernelsTypedBuffer.smoothstep_api(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
    }

    /** {@link #smoothstep(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer smoothstep(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge0, int edge0Offset, java.nio.ByteBuffer edge1, int edge1Offset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && edge0.isDirect() && edge0.order() == java.nio.ByteOrder.nativeOrder() && edge1.isDirect() && edge1.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.smoothstep_unsafe(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
        return Double3OpsKernelsByteBuffer.smoothstep_api(dest, destOffset, src, srcOffset, edge0, edge0Offset, edge1, edge1Offset);
    }

    /** {@link #smoothstep(double[], int, double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long smoothstep(long dest, long src, long edge0, long edge1) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.smoothstep_unsafe(dest, src, edge0, edge1);
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
    public static double[] sqrt(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = java.lang.Math.sqrt(src[srcOffset]);
        dest[destOffset + 1] = java.lang.Math.sqrt(_selfy);
        dest[destOffset + 2] = java.lang.Math.sqrt(_selfz);
        return dest;
    }

    /** {@link #sqrt(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer sqrt(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.sqrt_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.sqrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sqrt(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer sqrt(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.sqrt_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.sqrt_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #sqrt(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long sqrt(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.sqrt_unsafe(dest, src);
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
    public static double[] step(double[] dest, int destOffset, double[] src, int srcOffset, double edge) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = src[srcOffset] < edge ? 0.0 : 1.0;
        dest[destOffset + 1] = _selfy < edge ? 0.0 : 1.0;
        dest[destOffset + 2] = _selfz < edge ? 0.0 : 1.0;
        return dest;
    }

    /** {@link #step(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer step(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edge) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.step_unsafe(dest, destOffset, src, srcOffset, edge);
        return Double3OpsKernelsTypedBuffer.step_api(dest, destOffset, src, srcOffset, edge);
    }

    /** {@link #step(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer step(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edge) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.step_unsafe(dest, destOffset, src, srcOffset, edge);
        return Double3OpsKernelsByteBuffer.step_api(dest, destOffset, src, srcOffset, edge);
    }

    /** {@link #step(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long step(long dest, long src, double edge) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.step_unsafe(dest, src, edge);
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
    public static double[] step(double[] dest, int destOffset, double[] src, int srcOffset, double edgeX, double edgeY, double edgeZ) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = src[srcOffset] < edgeX ? 0.0 : 1.0;
        dest[destOffset + 1] = _selfy < edgeY ? 0.0 : 1.0;
        dest[destOffset + 2] = _selfz < edgeZ ? 0.0 : 1.0;
        return dest;
    }

    /** {@link #step(double[], int, double[], int, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer step(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double edgeX, double edgeY, double edgeZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.step_unsafe(dest, destOffset, src, srcOffset, edgeX, edgeY, edgeZ);
        return Double3OpsKernelsTypedBuffer.step_api(dest, destOffset, src, srcOffset, edgeX, edgeY, edgeZ);
    }

    /** {@link #step(double[], int, double[], int, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer step(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double edgeX, double edgeY, double edgeZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.step_unsafe(dest, destOffset, src, srcOffset, edgeX, edgeY, edgeZ);
        return Double3OpsKernelsByteBuffer.step_api(dest, destOffset, src, srcOffset, edgeX, edgeY, edgeZ);
    }

    /** {@link #step(double[], int, double[], int, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long step(long dest, long src, double edgeX, double edgeY, double edgeZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.step_unsafe(dest, src, edgeX, edgeY, edgeZ);
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
    public static double[] step(double[] dest, int destOffset, double[] src, int srcOffset, double[] edge, int edgeOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _edgey = edge[edgeOffset + 1];
        double _edgez = edge[edgeOffset + 2];
        dest[destOffset] = src[srcOffset] < edge[edgeOffset] ? 0.0 : 1.0;
        dest[destOffset + 1] = _selfy < _edgey ? 0.0 : 1.0;
        dest[destOffset + 2] = _selfz < _edgez ? 0.0 : 1.0;
        return dest;
    }

    /** {@link #step(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer step(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer edge, int edgeOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && edge.isDirect() && edge.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.step_unsafe(dest, destOffset, src, srcOffset, edge, edgeOffset);
        return Double3OpsKernelsTypedBuffer.step_api(dest, destOffset, src, srcOffset, edge, edgeOffset);
    }

    /** {@link #step(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer step(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer edge, int edgeOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && edge.isDirect() && edge.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.step_unsafe(dest, destOffset, src, srcOffset, edge, edgeOffset);
        return Double3OpsKernelsByteBuffer.step_api(dest, destOffset, src, srcOffset, edge, edgeOffset);
    }

    /** {@link #step(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long step(long dest, long src, long edge) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.step_unsafe(dest, src, edge);
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
    public static double[] tan(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.tan(src[srcOffset]);
        dest[destOffset + 1] = Math.tan(_selfy);
        dest[destOffset + 2] = Math.tan(_selfz);
        return dest;
    }

    /** {@link #tan(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer tan(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.tan_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.tan_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #tan(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer tan(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.tan_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.tan_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #tan(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long tan(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.tan_unsafe(dest, src);
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
    public static double[] tanh(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.tanh(src[srcOffset]);
        dest[destOffset + 1] = Math.tanh(_selfy);
        dest[destOffset + 2] = Math.tanh(_selfz);
        return dest;
    }

    /** {@link #tanh(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer tanh(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.tanh_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.tanh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #tanh(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer tanh(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.tanh_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.tanh_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #tanh(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long tanh(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.tanh_unsafe(dest, src);
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
     * cross product would leave the {@code double} range, the edges are first scaled exactly by
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
    public static double[] triangleNormal(double[] dest, int destOffset, double[] src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t0 = p1Y - _selfy;
        double _t1 = p2Z - _selfz;
        double _t2 = p1Z - _selfz;
        double _t3 = p2Y - _selfy;
        double _t4 = p1X - _selfx;
        double _t5 = p2X - _selfx;
        double _t12 = Math.fma(_t0, _t1, -(_t2 * _t3));
        double _t13 = Math.fma(_t4, _t3, -(_t0 * _t5));
        double _t14 = Math.fma(_t2, _t5, -(_t4 * _t1));
        double _ct0 = Math.fma(_t13, _t13, Math.fma(_t12, _t12, _t14 * _t14));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsArray.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        double _t19 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest[destOffset] = _t12 * _t19;
        dest[destOffset + 1] = _t14 * _t19;
        dest[destOffset + 2] = _t13 * _t19;
        return dest;
    }

    /** {@link #triangleNormal(double[], int, double[], int, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer triangleNormal(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.triangleNormal_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return Double3OpsKernelsTypedBuffer.triangleNormal_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
    }

    /** {@link #triangleNormal(double[], int, double[], int, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer triangleNormal(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.triangleNormal_unsafe(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        return Double3OpsKernelsByteBuffer.triangleNormal_api(dest, destOffset, src, srcOffset, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
    }

    /** {@link #triangleNormal(double[], int, double[], int, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long triangleNormal(long dest, long src, double p1X, double p1Y, double p1Z, double p2X, double p2Y, double p2Z) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.triangleNormal_unsafe(dest, src, p1X, p1Y, p1Z, p2X, p2Y, p2Z);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }

    /**
     * Compute the unit normal of the triangle spanned by this vector and the two given points, i.e.
     * {@code normalize((p1 - this) x (p2 - this))} - it points to the side from which the vertices
     * {@code this}, {@code p1}, {@code p2} appear counter-clockwise (a degenerate triangle yields
     * the zero vector) and store the result in {@code dest}.
     * <p>
     * It holds for triangles of any finite size and shape: when the squared length of the edges'
     * cross product would leave the {@code double} range, the edges are first scaled exactly by
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
    public static double[] triangleNormal(double[] dest, int destOffset, double[] src, int srcOffset, double[] p1, int p1Offset, double[] p2, int p2Offset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t0 = p1[p1Offset + 1] - _selfy;
        double _t1 = p2[p2Offset + 2] - _selfz;
        double _t2 = p1[p1Offset + 2] - _selfz;
        double _t3 = p2[p2Offset + 1] - _selfy;
        double _t4 = p1[p1Offset] - _selfx;
        double _t5 = p2[p2Offset] - _selfx;
        double _t12 = Math.fma(_t0, _t1, -(_t2 * _t3));
        double _t13 = Math.fma(_t4, _t3, -(_t0 * _t5));
        double _t14 = Math.fma(_t2, _t5, -(_t4 * _t1));
        double _ct0 = Math.fma(_t13, _t13, Math.fma(_t12, _t12, _t14 * _t14));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return Double3OpsKernelsArray.triangleNormal_degenerate(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        double _t19 = (1.0 / java.lang.Math.sqrt(_ct0));
        dest[destOffset] = _t12 * _t19;
        dest[destOffset + 1] = _t14 * _t19;
        dest[destOffset + 2] = _t13 * _t19;
        return dest;
    }

    /** {@link #triangleNormal(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer triangleNormal(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer p1, int p1Offset, java.nio.DoubleBuffer p2, int p2Offset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.triangleNormal_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return Double3OpsKernelsTypedBuffer.triangleNormal_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
    }

    /** {@link #triangleNormal(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer triangleNormal(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer p1, int p1Offset, java.nio.ByteBuffer p2, int p2Offset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && p1.isDirect() && p1.order() == java.nio.ByteOrder.nativeOrder() && p2.isDirect() && p2.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.triangleNormal_unsafe(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
        return Double3OpsKernelsByteBuffer.triangleNormal_api(dest, destOffset, src, srcOffset, p1, p1Offset, p2, p2Offset);
    }

    /** {@link #triangleNormal(double[], int, double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long triangleNormal(long dest, long src, long p1, long p2) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.triangleNormal_unsafe(dest, src, p1, p2);
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
    public static double[] trunc(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = _selfx >= 0.0 ? Math.floor(_selfx) : Math.ceil(_selfx);
        dest[destOffset + 1] = _selfy >= 0.0 ? Math.floor(_selfy) : Math.ceil(_selfy);
        dest[destOffset + 2] = _selfz >= 0.0 ? Math.floor(_selfz) : Math.ceil(_selfz);
        return dest;
    }

    /** {@link #trunc(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer trunc(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.trunc_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.trunc_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #trunc(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer trunc(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.trunc_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.trunc_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #trunc(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long trunc(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.trunc_unsafe(dest, src);
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
    public static double[] ulp(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = Math.ulp(src[srcOffset]);
        dest[destOffset + 1] = Math.ulp(_selfy);
        dest[destOffset + 2] = Math.ulp(_selfz);
        return dest;
    }

    /** {@link #ulp(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer ulp(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.ulp_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.ulp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #ulp(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer ulp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.ulp_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.ulp_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #ulp(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long ulp(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.ulp_unsafe(dest, src);
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
    public static double[] xyz0(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _selfy;
        dest[destOffset + 2] = _selfz;
        dest[destOffset + 3] = 0.0;
        return dest;
    }

    /** {@link #xyz0(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer xyz0(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.xyz0_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.xyz0_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #xyz0(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer xyz0(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.xyz0_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.xyz0_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #xyz0(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long xyz0(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.xyz0_unsafe(dest, src);
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
    public static double[] xyz1(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _selfy;
        dest[destOffset + 2] = _selfz;
        dest[destOffset + 3] = 1.0;
        return dest;
    }

    /** {@link #xyz1(double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer xyz1(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.xyz1_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsTypedBuffer.xyz1_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #xyz1(double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer xyz1(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.xyz1_unsafe(dest, destOffset, src, srcOffset);
        return Double3OpsKernelsByteBuffer.xyz1_api(dest, destOffset, src, srcOffset);
    }

    /** {@link #xyz1(double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long xyz1(long dest, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.xyz1_unsafe(dest, src);
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
    public static double[] preMul(double[] dest, int destOffset, double[] src, int srcOffset, double[] mat, int matOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _mat10 = mat[matOffset + 1];
        double _mat20 = mat[matOffset + 2];
        double _mat11 = mat[matOffset + 4];
        double _mat21 = mat[matOffset + 5];
        double _mat12 = mat[matOffset + 7];
        double _mat22 = mat[matOffset + 8];
        dest[destOffset] = Math.fma(mat[matOffset + 6], _selfz, Math.fma(mat[matOffset], _selfx, mat[matOffset + 3] * _selfy));
        dest[destOffset + 1] = Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        dest[destOffset + 2] = Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy));
        return dest;
    }

    /** {@link #preMul(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preMul(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.preMul_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Double3OpsKernelsTypedBuffer.preMul_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMul(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.preMul_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Double3OpsKernelsByteBuffer.preMul_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMul(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preMul(long dest, long src, long mat) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.preMul_unsafe(dest, src, mat);
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
    public static double[] preMulDirectionMat3x4(double[] dest, int destOffset, double[] src, int srcOffset, double[] mat, int matOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _mat10 = mat[matOffset + 4];
        double _mat11 = mat[matOffset + 5];
        double _mat12 = mat[matOffset + 6];
        double _mat20 = mat[matOffset + 8];
        double _mat21 = mat[matOffset + 9];
        double _mat22 = mat[matOffset + 10];
        dest[destOffset] = Math.fma(mat[matOffset + 2], _selfz, Math.fma(mat[matOffset], _selfx, mat[matOffset + 1] * _selfy));
        dest[destOffset + 1] = Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        dest[destOffset + 2] = Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy));
        return dest;
    }

    /** {@link #preMulDirectionMat3x4(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preMulDirectionMat3x4(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.preMulDirectionMat3x4_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Double3OpsKernelsTypedBuffer.preMulDirectionMat3x4_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMulDirectionMat3x4(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMulDirectionMat3x4(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.preMulDirectionMat3x4_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Double3OpsKernelsByteBuffer.preMulDirectionMat3x4_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMulDirectionMat3x4(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preMulDirectionMat3x4(long dest, long src, long mat) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.preMulDirectionMat3x4_unsafe(dest, src, mat);
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
    public static double[] preMulDirectionMat4x4(double[] dest, int destOffset, double[] src, int srcOffset, double[] mat, int matOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _mat10 = mat[matOffset + 1];
        double _mat20 = mat[matOffset + 2];
        double _mat11 = mat[matOffset + 5];
        double _mat21 = mat[matOffset + 6];
        double _mat12 = mat[matOffset + 9];
        double _mat22 = mat[matOffset + 10];
        dest[destOffset] = Math.fma(mat[matOffset + 8], _selfz, Math.fma(mat[matOffset], _selfx, mat[matOffset + 4] * _selfy));
        dest[destOffset + 1] = Math.fma(_mat12, _selfz, Math.fma(_mat10, _selfx, _mat11 * _selfy));
        dest[destOffset + 2] = Math.fma(_mat22, _selfz, Math.fma(_mat20, _selfx, _mat21 * _selfy));
        return dest;
    }

    /** {@link #preMulDirectionMat4x4(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preMulDirectionMat4x4(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.preMulDirectionMat4x4_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Double3OpsKernelsTypedBuffer.preMulDirectionMat4x4_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMulDirectionMat4x4(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMulDirectionMat4x4(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.preMulDirectionMat4x4_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Double3OpsKernelsByteBuffer.preMulDirectionMat4x4_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMulDirectionMat4x4(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preMulDirectionMat4x4(long dest, long src, long mat) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.preMulDirectionMat4x4_unsafe(dest, src, mat);
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
    public static double[] preMulPositionMat3x4(double[] dest, int destOffset, double[] src, int srcOffset, double[] mat, int matOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _mat10 = mat[matOffset + 4];
        double _mat11 = mat[matOffset + 5];
        double _mat12 = mat[matOffset + 6];
        double _mat13 = mat[matOffset + 7];
        double _mat20 = mat[matOffset + 8];
        double _mat21 = mat[matOffset + 9];
        double _mat22 = mat[matOffset + 10];
        double _mat23 = mat[matOffset + 11];
        dest[destOffset] = Math.fma(mat[matOffset], _selfx, Math.fma(mat[matOffset + 1], _selfy, Math.fma(mat[matOffset + 2], _selfz, mat[matOffset + 3])));
        dest[destOffset + 1] = Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, Math.fma(_mat12, _selfz, _mat13)));
        dest[destOffset + 2] = Math.fma(_mat20, _selfx, Math.fma(_mat21, _selfy, Math.fma(_mat22, _selfz, _mat23)));
        return dest;
    }

    /** {@link #preMulPositionMat3x4(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preMulPositionMat3x4(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.preMulPositionMat3x4_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Double3OpsKernelsTypedBuffer.preMulPositionMat3x4_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMulPositionMat3x4(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMulPositionMat3x4(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.preMulPositionMat3x4_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Double3OpsKernelsByteBuffer.preMulPositionMat3x4_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMulPositionMat3x4(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preMulPositionMat3x4(long dest, long src, long mat) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.preMulPositionMat3x4_unsafe(dest, src, mat);
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
    public static double[] preMulPositionMat4x4(double[] dest, int destOffset, double[] src, int srcOffset, double[] mat, int matOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _mat10 = mat[matOffset + 1];
        double _mat20 = mat[matOffset + 2];
        double _mat11 = mat[matOffset + 5];
        double _mat21 = mat[matOffset + 6];
        double _mat12 = mat[matOffset + 9];
        double _mat22 = mat[matOffset + 10];
        double _mat13 = mat[matOffset + 13];
        double _mat23 = mat[matOffset + 14];
        dest[destOffset] = Math.fma(mat[matOffset], _selfx, Math.fma(mat[matOffset + 4], _selfy, Math.fma(mat[matOffset + 8], _selfz, mat[matOffset + 12])));
        dest[destOffset + 1] = Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, Math.fma(_mat12, _selfz, _mat13)));
        dest[destOffset + 2] = Math.fma(_mat20, _selfx, Math.fma(_mat21, _selfy, Math.fma(_mat22, _selfz, _mat23)));
        return dest;
    }

    /** {@link #preMulPositionMat4x4(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preMulPositionMat4x4(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.preMulPositionMat4x4_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Double3OpsKernelsTypedBuffer.preMulPositionMat4x4_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMulPositionMat4x4(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMulPositionMat4x4(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.preMulPositionMat4x4_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Double3OpsKernelsByteBuffer.preMulPositionMat4x4_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMulPositionMat4x4(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preMulPositionMat4x4(long dest, long src, long mat) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.preMulPositionMat4x4_unsafe(dest, src, mat);
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
    public static double[] preMulProject(double[] dest, int destOffset, double[] src, int srcOffset, double[] mat, int matOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _mat10 = mat[matOffset + 1];
        double _mat20 = mat[matOffset + 2];
        double _mat11 = mat[matOffset + 5];
        double _mat21 = mat[matOffset + 6];
        double _mat12 = mat[matOffset + 9];
        double _mat22 = mat[matOffset + 10];
        double _mat13 = mat[matOffset + 13];
        double _mat23 = mat[matOffset + 14];
        double _t2_inv = 1.0 / Math.fma(mat[matOffset + 3], _selfx, Math.fma(mat[matOffset + 7], _selfy, Math.fma(mat[matOffset + 11], _selfz, mat[matOffset + 15])));
        dest[destOffset] = Math.fma(mat[matOffset], _selfx, Math.fma(mat[matOffset + 4], _selfy, Math.fma(mat[matOffset + 8], _selfz, mat[matOffset + 12]))) * _t2_inv;
        dest[destOffset + 1] = Math.fma(_mat10, _selfx, Math.fma(_mat11, _selfy, Math.fma(_mat12, _selfz, _mat13))) * _t2_inv;
        dest[destOffset + 2] = Math.fma(_mat20, _selfx, Math.fma(_mat21, _selfy, Math.fma(_mat22, _selfz, _mat23))) * _t2_inv;
        return dest;
    }

    /** {@link #preMulProject(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer preMulProject(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.preMulProject_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Double3OpsKernelsTypedBuffer.preMulProject_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMulProject(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer preMulProject(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer mat, int matOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && mat.isDirect() && mat.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.preMulProject_unsafe(dest, destOffset, src, srcOffset, mat, matOffset);
        return Double3OpsKernelsByteBuffer.preMulProject_api(dest, destOffset, src, srcOffset, mat, matOffset);
    }

    /** {@link #preMulProject(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long preMulProject(long dest, long src, long mat) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.preMulProject_unsafe(dest, src, mat);
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
    public static double[] rotate(double[] dest, int destOffset, double[] src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t9 = 2.0 * Math.fma(quatX, _selfy, -(quatY * _selfx));
        double _t10 = 2.0 * Math.fma(quatZ, _selfx, -(quatX * _selfz));
        double _t11 = 2.0 * Math.fma(quatY, _selfz, -(quatZ * _selfy));
        dest[destOffset] = Math.fma(quatY, _t9, Math.fma(-quatZ, _t10, Math.fma(quatW, _t11, _selfx)));
        dest[destOffset + 1] = Math.fma(quatZ, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfy)));
        dest[destOffset + 2] = Math.fma(quatX, _t10, Math.fma(-quatY, _t11, Math.fma(quatW, _t9, _selfz)));
        return dest;
    }

    /** {@link #rotate(double[], int, double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return Double3OpsKernelsTypedBuffer.rotate_api(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
    }

    /** {@link #rotate(double[], int, double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return Double3OpsKernelsByteBuffer.rotate_api(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
    }

    /** {@link #rotate(double[], int, double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotate(long dest, long src, double quatX, double quatY, double quatZ, double quatW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.rotate_unsafe(dest, src, quatX, quatY, quatZ, quatW);
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
    public static double[] rotate(double[] dest, int destOffset, double[] src, int srcOffset, double[] quat, int quatOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _quatx = quat[quatOffset];
        double _quaty = quat[quatOffset + 1];
        double _quatz = quat[quatOffset + 2];
        double _quatw = quat[quatOffset + 3];
        double _t9 = 2.0 * Math.fma(_quatx, _selfy, -(_quaty * _selfx));
        double _t10 = 2.0 * Math.fma(_quatz, _selfx, -(_quatx * _selfz));
        double _t11 = 2.0 * Math.fma(_quaty, _selfz, -(_quatz * _selfy));
        dest[destOffset] = Math.fma(_quaty, _t9, Math.fma(-_quatz, _t10, Math.fma(_quatw, _t11, _selfx)));
        dest[destOffset + 1] = Math.fma(_quatz, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfy)));
        dest[destOffset + 2] = Math.fma(_quatx, _t10, Math.fma(-_quaty, _t11, Math.fma(_quatw, _t9, _selfz)));
        return dest;
    }

    /** {@link #rotate(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && quat.isDirect() && quat.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, quat, quatOffset);
        return Double3OpsKernelsTypedBuffer.rotate_api(dest, destOffset, src, srcOffset, quat, quatOffset);
    }

    /** {@link #rotate(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && quat.isDirect() && quat.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.rotate_unsafe(dest, destOffset, src, srcOffset, quat, quatOffset);
        return Double3OpsKernelsByteBuffer.rotate_api(dest, destOffset, src, srcOffset, quat, quatOffset);
    }

    /** {@link #rotate(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotate(long dest, long src, long quat) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.rotate_unsafe(dest, src, quat);
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
    public static double[] rotateAround(double[] dest, int destOffset, double[] src, int srcOffset, double quatX, double quatY, double quatZ, double quatW, double pivotX, double pivotY, double pivotZ) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t0 = _selfy - pivotY;
        double _t1 = _selfx - pivotX;
        double _t2 = _selfz - pivotZ;
        double _t12 = 2.0 * Math.fma(quatX, _t0, -(quatY * _t1));
        double _t13 = 2.0 * Math.fma(quatZ, _t1, -(quatX * _t2));
        double _t14 = 2.0 * Math.fma(quatY, _t2, -(quatZ * _t0));
        dest[destOffset] = Math.fma(quatY, _t12, Math.fma(-quatZ, _t13, Math.fma(quatW, _t14, _selfx)));
        dest[destOffset + 1] = Math.fma(quatZ, _t14, Math.fma(-quatX, _t12, Math.fma(quatW, _t13, _selfy)));
        dest[destOffset + 2] = Math.fma(quatX, _t13, Math.fma(-quatY, _t14, Math.fma(quatW, _t12, _selfz)));
        return dest;
    }

    /** {@link #rotateAround(double[], int, double[], int, double, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW, double pivotX, double pivotY, double pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.rotateAround_unsafe(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        return Double3OpsKernelsTypedBuffer.rotateAround_api(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
    }

    /** {@link #rotateAround(double[], int, double[], int, double, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW, double pivotX, double pivotY, double pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.rotateAround_unsafe(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
        return Double3OpsKernelsByteBuffer.rotateAround_api(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
    }

    /** {@link #rotateAround(double[], int, double[], int, double, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateAround(long dest, long src, double quatX, double quatY, double quatZ, double quatW, double pivotX, double pivotY, double pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.rotateAround_unsafe(dest, src, quatX, quatY, quatZ, quatW, pivotX, pivotY, pivotZ);
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
    public static double[] rotateAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] quat, int quatOffset, double[] pivot, int pivotOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _quatx = quat[quatOffset];
        double _quaty = quat[quatOffset + 1];
        double _quatz = quat[quatOffset + 2];
        double _quatw = quat[quatOffset + 3];
        double _t0 = _selfy - pivot[pivotOffset + 1];
        double _t1 = _selfx - pivot[pivotOffset];
        double _t2 = _selfz - pivot[pivotOffset + 2];
        double _t12 = 2.0 * Math.fma(_quatx, _t0, -(_quaty * _t1));
        double _t13 = 2.0 * Math.fma(_quatz, _t1, -(_quatx * _t2));
        double _t14 = 2.0 * Math.fma(_quaty, _t2, -(_quatz * _t0));
        dest[destOffset] = Math.fma(_quaty, _t12, Math.fma(-_quatz, _t13, Math.fma(_quatw, _t14, _selfx)));
        dest[destOffset + 1] = Math.fma(_quatz, _t14, Math.fma(-_quatx, _t12, Math.fma(_quatw, _t13, _selfy)));
        dest[destOffset + 2] = Math.fma(_quatx, _t13, Math.fma(-_quaty, _t14, Math.fma(_quatw, _t12, _selfz)));
        return dest;
    }

    /** {@link #rotateAround(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset, java.nio.DoubleBuffer pivot, int pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && quat.isDirect() && quat.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.rotateAround_unsafe(dest, destOffset, src, srcOffset, quat, quatOffset, pivot, pivotOffset);
        return Double3OpsKernelsTypedBuffer.rotateAround_api(dest, destOffset, src, srcOffset, quat, quatOffset, pivot, pivotOffset);
    }

    /** {@link #rotateAround(double[], int, double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset, java.nio.ByteBuffer pivot, int pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && quat.isDirect() && quat.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.rotateAround_unsafe(dest, destOffset, src, srcOffset, quat, quatOffset, pivot, pivotOffset);
        return Double3OpsKernelsByteBuffer.rotateAround_api(dest, destOffset, src, srcOffset, quat, quatOffset, pivot, pivotOffset);
    }

    /** {@link #rotateAround(double[], int, double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateAround(long dest, long src, long quat, long pivot) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.rotateAround_unsafe(dest, src, quat, pivot);
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
    public static double[] rotateAxis(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t3 = 1.0 - _t1;
        double _t5 = Math.fma(axisZ, _selfz, Math.fma(axisX, _selfx, axisY * _selfy));
        dest[destOffset] = Math.fma(_t3, axisX * _t5, Math.fma(_selfx, _t1, Math.fma(axisY, _selfz, -(axisZ * _selfy)) * _t0));
        dest[destOffset + 1] = Math.fma(_t3, axisY * _t5, Math.fma(_selfy, _t1, Math.fma(axisZ, _selfx, -(axisX * _selfz)) * _t0));
        dest[destOffset + 2] = Math.fma(_t3, axisZ * _t5, Math.fma(_selfz, _t1, Math.fma(axisX, _selfy, -(axisY * _selfx)) * _t0));
        return dest;
    }

    /** {@link #rotateAxis(double[], int, double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateAxis(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.rotateAxis_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return Double3OpsKernelsTypedBuffer.rotateAxis_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #rotateAxis(double[], int, double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAxis(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.rotateAxis_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return Double3OpsKernelsByteBuffer.rotateAxis_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    /** {@link #rotateAxis(double[], int, double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateAxis(long dest, long src, double angle, double axisX, double axisY, double axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.rotateAxis_unsafe(dest, src, angle, axisX, axisY, axisZ);
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
    public static double[] rotateAxis(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _axisx = axis[axisOffset];
        double _axisy = axis[axisOffset + 1];
        double _axisz = axis[axisOffset + 2];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t3 = 1.0 - _t1;
        double _t5 = Math.fma(_axisz, _selfz, Math.fma(_axisx, _selfx, _axisy * _selfy));
        dest[destOffset] = Math.fma(_t3, _axisx * _t5, Math.fma(_selfx, _t1, Math.fma(_axisy, _selfz, -(_axisz * _selfy)) * _t0));
        dest[destOffset + 1] = Math.fma(_t3, _axisy * _t5, Math.fma(_selfy, _t1, Math.fma(_axisz, _selfx, -(_axisx * _selfz)) * _t0));
        dest[destOffset + 2] = Math.fma(_t3, _axisz * _t5, Math.fma(_selfz, _t1, Math.fma(_axisx, _selfy, -(_axisy * _selfx)) * _t0));
        return dest;
    }

    /** {@link #rotateAxis(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateAxis(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.rotateAxis_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return Double3OpsKernelsTypedBuffer.rotateAxis_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@link #rotateAxis(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAxis(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.rotateAxis_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return Double3OpsKernelsByteBuffer.rotateAxis_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    /** {@link #rotateAxis(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateAxis(long dest, long src, long axis, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.rotateAxis_unsafe(dest, src, axis, angle);
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
    public static double[] rotateAxisAround(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ, double pivotX, double pivotY, double pivotZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = src[srcOffset] - pivotX;
        double _t3 = src[srcOffset + 2] - pivotZ;
        double _t4 = src[srcOffset + 1] - pivotY;
        double _t5 = 1.0 - _t1;
        double _t8 = Math.fma(axisZ, _t3, Math.fma(axisX, _t2, axisY * _t4));
        dest[destOffset] = Math.fma(_t2, _t1, Math.fma(Math.fma(axisY, _t3, -(axisZ * _t4)), _t0, Math.fma(_t5, axisX * _t8, pivotX)));
        dest[destOffset + 1] = Math.fma(_t4, _t1, Math.fma(Math.fma(axisZ, _t2, -(axisX * _t3)), _t0, Math.fma(_t5, axisY * _t8, pivotY)));
        dest[destOffset + 2] = Math.fma(_t3, _t1, Math.fma(Math.fma(axisX, _t4, -(axisY * _t2)), _t0, Math.fma(_t5, axisZ * _t8, pivotZ)));
        return dest;
    }

    /** {@link #rotateAxisAround(double[], int, double[], int, double, double, double, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateAxisAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ, double pivotX, double pivotY, double pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.rotateAxisAround_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        return Double3OpsKernelsTypedBuffer.rotateAxisAround_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
    }

    /** {@link #rotateAxisAround(double[], int, double[], int, double, double, double, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAxisAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double axisX, double axisY, double axisZ, double pivotX, double pivotY, double pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.rotateAxisAround_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
        return Double3OpsKernelsByteBuffer.rotateAxisAround_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
    }

    /** {@link #rotateAxisAround(double[], int, double[], int, double, double, double, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateAxisAround(long dest, long src, double angle, double axisX, double axisY, double axisZ, double pivotX, double pivotY, double pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.rotateAxisAround_unsafe(dest, src, angle, axisX, axisY, axisZ, pivotX, pivotY, pivotZ);
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
    public static double[] rotateAxisAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double[] pivot, int pivotOffset, double angle) {
        double _axisx = axis[axisOffset];
        double _axisy = axis[axisOffset + 1];
        double _axisz = axis[axisOffset + 2];
        double _pivotx = pivot[pivotOffset];
        double _pivoty = pivot[pivotOffset + 1];
        double _pivotz = pivot[pivotOffset + 2];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = src[srcOffset] - _pivotx;
        double _t3 = src[srcOffset + 2] - _pivotz;
        double _t4 = src[srcOffset + 1] - _pivoty;
        double _t5 = 1.0 - _t1;
        double _t8 = Math.fma(_axisz, _t3, Math.fma(_axisx, _t2, _axisy * _t4));
        dest[destOffset] = Math.fma(_t2, _t1, Math.fma(Math.fma(_axisy, _t3, -(_axisz * _t4)), _t0, Math.fma(_t5, _axisx * _t8, _pivotx)));
        dest[destOffset + 1] = Math.fma(_t4, _t1, Math.fma(Math.fma(_axisz, _t2, -(_axisx * _t3)), _t0, Math.fma(_t5, _axisy * _t8, _pivoty)));
        dest[destOffset + 2] = Math.fma(_t3, _t1, Math.fma(Math.fma(_axisx, _t4, -(_axisy * _t2)), _t0, Math.fma(_t5, _axisz * _t8, _pivotz)));
        return dest;
    }

    /** {@link #rotateAxisAround(double[], int, double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateAxisAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer axis, int axisOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.rotateAxisAround_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, pivot, pivotOffset, angle);
        return Double3OpsKernelsTypedBuffer.rotateAxisAround_api(dest, destOffset, src, srcOffset, axis, axisOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateAxisAround(double[], int, double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateAxisAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer axis, int axisOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && axis.isDirect() && axis.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.rotateAxisAround_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, pivot, pivotOffset, angle);
        return Double3OpsKernelsByteBuffer.rotateAxisAround_api(dest, destOffset, src, srcOffset, axis, axisOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateAxisAround(double[], int, double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateAxisAround(long dest, long src, long axis, long pivot, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.rotateAxisAround_unsafe(dest, src, axis, pivot, angle);
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
    public static double[] rotateInverse(double[] dest, int destOffset, double[] src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t9 = 2.0 * Math.fma(quatX, _selfz, -(quatZ * _selfx));
        double _t10 = 2.0 * Math.fma(quatY, _selfx, -(quatX * _selfy));
        double _t11 = 2.0 * Math.fma(quatZ, _selfy, -(quatY * _selfz));
        dest[destOffset] = Math.fma(quatZ, _t9, Math.fma(-quatY, _t10, Math.fma(quatW, _t11, _selfx)));
        dest[destOffset + 1] = Math.fma(quatX, _t10, Math.fma(-quatZ, _t11, Math.fma(quatW, _t9, _selfy)));
        dest[destOffset + 2] = Math.fma(quatY, _t11, Math.fma(-quatX, _t9, Math.fma(quatW, _t10, _selfz)));
        return dest;
    }

    /** {@link #rotateInverse(double[], int, double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateInverse(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.rotateInverse_unsafe(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return Double3OpsKernelsTypedBuffer.rotateInverse_api(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
    }

    /** {@link #rotateInverse(double[], int, double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateInverse(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double quatX, double quatY, double quatZ, double quatW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.rotateInverse_unsafe(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
        return Double3OpsKernelsByteBuffer.rotateInverse_api(dest, destOffset, src, srcOffset, quatX, quatY, quatZ, quatW);
    }

    /** {@link #rotateInverse(double[], int, double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateInverse(long dest, long src, double quatX, double quatY, double quatZ, double quatW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.rotateInverse_unsafe(dest, src, quatX, quatY, quatZ, quatW);
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
    public static double[] rotateInverse(double[] dest, int destOffset, double[] src, int srcOffset, double[] quat, int quatOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _quatx = quat[quatOffset];
        double _quaty = quat[quatOffset + 1];
        double _quatz = quat[quatOffset + 2];
        double _quatw = quat[quatOffset + 3];
        double _t9 = 2.0 * Math.fma(_quatx, _selfz, -(_quatz * _selfx));
        double _t10 = 2.0 * Math.fma(_quaty, _selfx, -(_quatx * _selfy));
        double _t11 = 2.0 * Math.fma(_quatz, _selfy, -(_quaty * _selfz));
        dest[destOffset] = Math.fma(_quatz, _t9, Math.fma(-_quaty, _t10, Math.fma(_quatw, _t11, _selfx)));
        dest[destOffset + 1] = Math.fma(_quatx, _t10, Math.fma(-_quatz, _t11, Math.fma(_quatw, _t9, _selfy)));
        dest[destOffset + 2] = Math.fma(_quaty, _t11, Math.fma(-_quatx, _t9, Math.fma(_quatw, _t10, _selfz)));
        return dest;
    }

    /** {@link #rotateInverse(double[], int, double[], int, double[], int)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateInverse(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer quat, int quatOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && quat.isDirect() && quat.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.rotateInverse_unsafe(dest, destOffset, src, srcOffset, quat, quatOffset);
        return Double3OpsKernelsTypedBuffer.rotateInverse_api(dest, destOffset, src, srcOffset, quat, quatOffset);
    }

    /** {@link #rotateInverse(double[], int, double[], int, double[], int)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateInverse(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer quat, int quatOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && quat.isDirect() && quat.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.rotateInverse_unsafe(dest, destOffset, src, srcOffset, quat, quatOffset);
        return Double3OpsKernelsByteBuffer.rotateInverse_api(dest, destOffset, src, srcOffset, quat, quatOffset);
    }

    /** {@link #rotateInverse(double[], int, double[], int, double[], int)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateInverse(long dest, long src, long quat) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.rotateInverse_unsafe(dest, src, quat);
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
    public static double[] rotateX(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = Math.fma(_selfy, _t1, -(_selfz * _t0));
        dest[destOffset + 2] = Math.fma(_selfy, _t0, _selfz * _t1);
        return dest;
    }

    /** {@link #rotateX(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateX(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.rotateX_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3OpsKernelsTypedBuffer.rotateX_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateX(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateX(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.rotateX_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3OpsKernelsByteBuffer.rotateX_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateX(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateX(long dest, long src, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.rotateX_unsafe(dest, src, angle);
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
    public static double[] rotateXAround(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double pivotX, double pivotY, double pivotZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = src[srcOffset + 1] - pivotY;
        double _t3 = src[srcOffset + 2] - pivotZ;
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotY));
        dest[destOffset + 2] = Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotZ));
        return dest;
    }

    /** {@link #rotateXAround(double[], int, double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateXAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY, double pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.rotateXAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotY, pivotZ);
        return Double3OpsKernelsTypedBuffer.rotateXAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY, pivotZ);
    }

    /** {@link #rotateXAround(double[], int, double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateXAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY, double pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.rotateXAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotY, pivotZ);
        return Double3OpsKernelsByteBuffer.rotateXAround_api(dest, destOffset, src, srcOffset, angle, pivotY, pivotZ);
    }

    /** {@link #rotateXAround(double[], int, double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateXAround(long dest, long src, double angle, double pivotX, double pivotY, double pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.rotateXAround_unsafe(dest, src, angle, pivotY, pivotZ);
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
    public static double[] rotateXAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double angle) {
        double _pivoty = pivot[pivotOffset + 1];
        double _pivotz = pivot[pivotOffset + 2];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = src[srcOffset + 1] - _pivoty;
        double _t3 = src[srcOffset + 2] - _pivotz;
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = Math.fma(_t2, _t1, Math.fma(-_t3, _t0, _pivoty));
        dest[destOffset + 2] = Math.fma(_t2, _t0, Math.fma(_t3, _t1, _pivotz));
        return dest;
    }

    /** {@link #rotateXAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateXAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.rotateXAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Double3OpsKernelsTypedBuffer.rotateXAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateXAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateXAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.rotateXAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Double3OpsKernelsByteBuffer.rotateXAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateXAround(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateXAround(long dest, long src, long pivot, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.rotateXAround_unsafe(dest, src, pivot, angle);
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
    public static double[] rotateY(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = Math.fma(_selfx, _t1, _selfz * _t0);
        dest[destOffset + 1] = _selfy;
        dest[destOffset + 2] = Math.fma(_selfz, _t1, -(_selfx * _t0));
        return dest;
    }

    /** {@link #rotateY(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateY(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.rotateY_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3OpsKernelsTypedBuffer.rotateY_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateY(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateY(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.rotateY_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3OpsKernelsByteBuffer.rotateY_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateY(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateY(long dest, long src, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.rotateY_unsafe(dest, src, angle);
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
    public static double[] rotateYAround(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double pivotX, double pivotY, double pivotZ) {
        double _selfy = src[srcOffset + 1];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = src[srcOffset] - pivotX;
        double _t3 = src[srcOffset + 2] - pivotZ;
        dest[destOffset] = Math.fma(_t2, _t1, Math.fma(_t3, _t0, pivotX));
        dest[destOffset + 1] = _selfy;
        dest[destOffset + 2] = Math.fma(_t3, _t1, Math.fma(-_t2, _t0, pivotZ));
        return dest;
    }

    /** {@link #rotateYAround(double[], int, double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateYAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY, double pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.rotateYAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotZ);
        return Double3OpsKernelsTypedBuffer.rotateYAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY, pivotZ);
    }

    /** {@link #rotateYAround(double[], int, double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateYAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY, double pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.rotateYAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotZ);
        return Double3OpsKernelsByteBuffer.rotateYAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotZ);
    }

    /** {@link #rotateYAround(double[], int, double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateYAround(long dest, long src, double angle, double pivotX, double pivotY, double pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.rotateYAround_unsafe(dest, src, angle, pivotX, pivotZ);
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
    public static double[] rotateYAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double angle) {
        double _selfy = src[srcOffset + 1];
        double _pivotx = pivot[pivotOffset];
        double _pivotz = pivot[pivotOffset + 2];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = src[srcOffset] - _pivotx;
        double _t3 = src[srcOffset + 2] - _pivotz;
        dest[destOffset] = Math.fma(_t2, _t1, Math.fma(_t3, _t0, _pivotx));
        dest[destOffset + 1] = _selfy;
        dest[destOffset + 2] = Math.fma(_t3, _t1, Math.fma(-_t2, _t0, _pivotz));
        return dest;
    }

    /** {@link #rotateYAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateYAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.rotateYAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Double3OpsKernelsTypedBuffer.rotateYAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateYAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateYAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.rotateYAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Double3OpsKernelsByteBuffer.rotateYAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateYAround(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateYAround(long dest, long src, long pivot, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.rotateYAround_unsafe(dest, src, pivot, angle);
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
    public static double[] rotateZ(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        dest[destOffset] = Math.fma(_selfx, _t1, -(_selfy * _t0));
        dest[destOffset + 1] = Math.fma(_selfx, _t0, _selfy * _t1);
        dest[destOffset + 2] = _selfz;
        return dest;
    }

    /** {@link #rotateZ(double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateZ(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.rotateZ_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3OpsKernelsTypedBuffer.rotateZ_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateZ(double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateZ(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.rotateZ_unsafe(dest, destOffset, src, srcOffset, angle);
        return Double3OpsKernelsByteBuffer.rotateZ_api(dest, destOffset, src, srcOffset, angle);
    }

    /** {@link #rotateZ(double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateZ(long dest, long src, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.rotateZ_unsafe(dest, src, angle);
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
    public static double[] rotateZAround(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double pivotX, double pivotY, double pivotZ) {
        double _selfz = src[srcOffset + 2];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = src[srcOffset] - pivotX;
        double _t3 = src[srcOffset + 1] - pivotY;
        dest[destOffset] = Math.fma(_t2, _t1, Math.fma(-_t3, _t0, pivotX));
        dest[destOffset + 1] = Math.fma(_t2, _t0, Math.fma(_t3, _t1, pivotY));
        dest[destOffset + 2] = _selfz;
        return dest;
    }

    /** {@link #rotateZAround(double[], int, double[], int, double, double, double, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateZAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double angle, double pivotX, double pivotY, double pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.rotateZAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return Double3OpsKernelsTypedBuffer.rotateZAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY, pivotZ);
    }

    /** {@link #rotateZAround(double[], int, double[], int, double, double, double, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateZAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double angle, double pivotX, double pivotY, double pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.rotateZAround_unsafe(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
        return Double3OpsKernelsByteBuffer.rotateZAround_api(dest, destOffset, src, srcOffset, angle, pivotX, pivotY);
    }

    /** {@link #rotateZAround(double[], int, double[], int, double, double, double, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateZAround(long dest, long src, double angle, double pivotX, double pivotY, double pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.rotateZAround_unsafe(dest, src, angle, pivotX, pivotY);
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
    public static double[] rotateZAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double angle) {
        double _selfz = src[srcOffset + 2];
        double _pivotx = pivot[pivotOffset];
        double _pivoty = pivot[pivotOffset + 1];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = src[srcOffset] - _pivotx;
        double _t3 = src[srcOffset + 1] - _pivoty;
        dest[destOffset] = Math.fma(_t2, _t1, Math.fma(-_t3, _t0, _pivotx));
        dest[destOffset + 1] = Math.fma(_t2, _t0, Math.fma(_t3, _t1, _pivoty));
        dest[destOffset + 2] = _selfz;
        return dest;
    }

    /** {@link #rotateZAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.DoubleBuffer} storage. <p>Valid input: as for that overload. */
    public static java.nio.DoubleBuffer rotateZAround(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, java.nio.DoubleBuffer pivot, int pivotOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsTypedBuffer.rotateZAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Double3OpsKernelsTypedBuffer.rotateZAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateZAround(double[], int, double[], int, double[], int, double)} on {@link java.nio.ByteBuffer} storage; the {@code *Offset} parameters are byte offsets, not element indices. <p>Valid input: as for that overload. */
    public static java.nio.ByteBuffer rotateZAround(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, java.nio.ByteBuffer pivot, int pivotOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder() && pivot.isDirect() && pivot.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsKernelsByteBuffer.rotateZAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
        return Double3OpsKernelsByteBuffer.rotateZAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, angle);
    }

    /** {@link #rotateZAround(double[], int, double[], int, double[], int, double)} on storage addressed by a raw native address - each address points at the first element, so there are no offsets.
     * <p>
     * Valid input: as for that overload.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only) */
    public static long rotateZAround(long dest, long src, long pivot, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) return Double3OpsKernelsAddress.rotateZAround_unsafe(dest, src, pivot, angle);
        throw new UnsupportedOperationException("raw long address transform requires storeLoadBackend=UNSAFE");
    }


    /**
     * Bulk out-of-place component-wise {@code add} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code add}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static double[] add(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Double3OpsSimd.add(dest, destOffset, a, aOffset, b, bOffset, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] + b[bOffset + _i];
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code add} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code add}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.DoubleBuffer add(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer a, int aOffset, java.nio.DoubleBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, a.get(aOffset + _i) + b.get(bOffset + _i));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code add} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code add}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer add(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putDouble(destOffset + _i * 8, a.getDouble(aOffset + _i * 8) + b.getDouble(bOffset + _i * 8));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code sub} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code sub}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static double[] sub(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Double3OpsSimd.sub(dest, destOffset, a, aOffset, b, bOffset, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] - b[bOffset + _i];
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code sub} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code sub}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.DoubleBuffer sub(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer a, int aOffset, java.nio.DoubleBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, a.get(aOffset + _i) - b.get(bOffset + _i));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code sub} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code sub}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer sub(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putDouble(destOffset + _i * 8, a.getDouble(aOffset + _i * 8) - b.getDouble(bOffset + _i * 8));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code mul} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code mul}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static double[] mul(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Double3OpsSimd.mul(dest, destOffset, a, aOffset, b, bOffset, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] * b[bOffset + _i];
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code mul} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code mul}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.DoubleBuffer mul(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer a, int aOffset, java.nio.DoubleBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, a.get(aOffset + _i) * b.get(bOffset + _i));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code mul} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code mul}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer mul(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putDouble(destOffset + _i * 8, a.getDouble(aOffset + _i * 8) * b.getDouble(bOffset + _i * 8));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code div} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code div}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static double[] div(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Double3OpsSimd.div(dest, destOffset, a, aOffset, b, bOffset, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] / b[bOffset + _i];
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code div} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code div}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.DoubleBuffer div(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer a, int aOffset, java.nio.DoubleBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, a.get(aOffset + _i) / b.get(bOffset + _i));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code div} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code div}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer div(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putDouble(destOffset + _i * 8, a.getDouble(aOffset + _i * 8) / b.getDouble(bOffset + _i * 8));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code min} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code min}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static double[] min(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Double3OpsSimd.min(dest, destOffset, a, aOffset, b, bOffset, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = java.lang.Math.min(a[aOffset + _i], b[bOffset + _i]);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code min} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code min}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.DoubleBuffer min(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer a, int aOffset, java.nio.DoubleBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, java.lang.Math.min(a.get(aOffset + _i), b.get(bOffset + _i)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code min} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code min}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer min(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putDouble(destOffset + _i * 8, java.lang.Math.min(a.getDouble(aOffset + _i * 8), b.getDouble(bOffset + _i * 8)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code max} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code max}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static double[] max(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Double3OpsSimd.max(dest, destOffset, a, aOffset, b, bOffset, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = java.lang.Math.max(a[aOffset + _i], b[bOffset + _i]);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code max} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code max}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.DoubleBuffer max(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer a, int aOffset, java.nio.DoubleBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, java.lang.Math.max(a.get(aOffset + _i), b.get(bOffset + _i)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code max} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code max}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer max(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putDouble(destOffset + _i * 8, java.lang.Math.max(a.getDouble(aOffset + _i * 8), b.getDouble(bOffset + _i * 8)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code negate} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code negate}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static double[] negate(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Double3OpsSimd.negate(dest, destOffset, src, srcOffset, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = -src[srcOffset + _i];
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code negate} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code negate}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.DoubleBuffer negate(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, -src.get(srcOffset + _i));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code negate} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code negate}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer negate(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putDouble(destOffset + _i * 8, -src.getDouble(srcOffset + _i * 8));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code abs} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code abs}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static double[] abs(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Double3OpsSimd.abs(dest, destOffset, src, srcOffset, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = java.lang.Math.abs(src[srcOffset + _i]);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code abs} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code abs}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.DoubleBuffer abs(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, java.lang.Math.abs(src.get(srcOffset + _i)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code abs} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code abs}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer abs(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putDouble(destOffset + _i * 8, java.lang.Math.abs(src.getDouble(srcOffset + _i * 8)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code lerp} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code lerp}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static double[] lerp(double[] dest, int destOffset, double[] a, int aOffset, double[] b, int bOffset, double t, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Double3OpsSimd.lerp(dest, destOffset, a, aOffset, b, bOffset, t, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = a[aOffset + _i] + t * (b[bOffset + _i] - a[aOffset + _i]);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code lerp} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code lerp}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.DoubleBuffer lerp(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer a, int aOffset, java.nio.DoubleBuffer b, int bOffset, double t, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, a.get(aOffset + _i) + t * (b.get(bOffset + _i) - a.get(aOffset + _i)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code lerp} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code lerp}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer lerp(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, double t, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putDouble(destOffset + _i * 8, a.getDouble(aOffset + _i * 8) + t * (b.getDouble(bOffset + _i * 8) - a.getDouble(aOffset + _i * 8)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code scale} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code scale}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static double[] scale(double[] dest, int destOffset, double[] src, int srcOffset, double s, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Double3OpsSimd.scale(dest, destOffset, src, srcOffset, s, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = src[srcOffset + _i] * s;
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code scale} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code scale}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.DoubleBuffer scale(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, double s, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, src.get(srcOffset + _i) * s);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code scale} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code scale}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer scale(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, double s, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putDouble(destOffset + _i * 8, src.getDouble(srcOffset + _i * 8) * s);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code fma} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code fma}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for the single-value overload, for each element.
     */
    public static double[] fma(double[] dest, int destOffset, double[] self, int selfOffset, double[] a, int aOffset, double[] b, int bOffset, int count) {
        if (count < 0) return dest;
        if (SimdSupport.VECTOR_API) return Double3OpsSimd.fma(dest, destOffset, self, selfOffset, a, aOffset, b, bOffset, count);
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest[destOffset + _i] = Math.fma(self[selfOffset + _i], a[aOffset + _i], b[bOffset + _i]);
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code fma} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code fma}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.DoubleBuffer fma(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer self, int selfOffset, java.nio.DoubleBuffer a, int aOffset, java.nio.DoubleBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.put(destOffset + _i, Math.fma(self.get(selfOffset + _i), a.get(aOffset + _i), b.get(bOffset + _i)));
        }
        return dest;
    }

    /**
     * Bulk out-of-place component-wise {@code fma} over {@code count} consecutive Double3 values: reads the
     * source buffer(s) and writes {@code dest} (which may also be one of the sources, at the
     * same offset). This batched form is distinct from the single-value {@code fma}
     * overload of the same name, which processes exactly one Double3.
     * <p>
     * Valid input: as for that overload.
     */
    public static java.nio.ByteBuffer fma(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer self, int selfOffset, java.nio.ByteBuffer a, int aOffset, java.nio.ByteBuffer b, int bOffset, int count) {
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++) {
            dest.putDouble(destOffset + _i * 8, Math.fma(self.getDouble(selfOffset + _i * 8), a.getDouble(aOffset + _i * 8), b.getDouble(bOffset + _i * 8)));
        }
        return dest;
    }

    /**
     * Copy one Double3 (3 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API) return Double3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3 values ({@code count * 3} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API) return Double3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 715827882 ? -1 : count * 3), src.length);
            java.util.Objects.checkFromIndexSize(destOffset, (count > 715827882 ? -1 : count * 3), dest.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.DOUBLE_ARRAY_BASE + srcOffset * 8L, dest, UnsafeCopy.DOUBLE_ARRAY_BASE + destOffset * 8L, count * 24L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 3;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src[srcOffset + _i];
        return dest;
    }

    /**
     * Copy one Double3 (3 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3 values ({@code count * 3} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 715827882 ? -1 : count * 3), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, dest, UnsafeCopy.DOUBLE_ARRAY_BASE + destOffset * 8L, count * 24L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 3;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src.get(srcOffset + _i);
        return dest;
    }

    /**
     * Copy one Double3 (3 doubles) from {@code src}
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
     * Bulk-copy {@code count} consecutive Double3 values ({@code count * 3} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static double[] copy(double[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 715827882 ? -1 : count * 3), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, dest, UnsafeCopy.DOUBLE_ARRAY_BASE + destOffset * 8L, count * 24L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++)
            dest[destOffset + _i] = src.getDouble(srcOffset + _i * 8);
        return dest;
    }

    /**
     * Copy one Double3 (3 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static double[] copy(double[] dest, int destOffset, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(destOffset, 3, dest.length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest, UnsafeCopy.DOUBLE_ARRAY_BASE + destOffset * 8L, 24L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Double3 values ({@code count * 3} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static double[] copy(double[] dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(destOffset, (count > 715827882 ? -1 : count * 3), dest.length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest, UnsafeCopy.DOUBLE_ARRAY_BASE + destOffset * 8L, count * 24L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Double3 (3 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Double3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3 values ({@code count * 3} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly()) return Double3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 715827882 ? -1 : count * 3), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.DOUBLE_ARRAY_BASE + srcOffset * 8L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, count * 24L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 3;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src[srcOffset + _i]);
        return dest;
    }

    /**
     * Copy one Double3 (3 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsSimd.copy(dest, destOffset, src, srcOffset);
        return copy(dest, destOffset, src, srcOffset, 1);
    }

    /**
     * Bulk-copy {@code count} consecutive Double3 values ({@code count * 3} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        if (SimdSupport.VECTOR_API && dest.order() == java.nio.ByteOrder.nativeOrder() && !dest.isReadOnly() && src.order() == java.nio.ByteOrder.nativeOrder()) return Double3OpsSimd.copy(dest, destOffset, src, srcOffset, count);
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, count * 24L);
            return dest;
        }
        if (count < 0) return dest;
        int n = count * 3;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src.get(srcOffset + _i));
        return dest;
    }

    /**
     * Copy one Double3 (3 doubles) from {@code src}
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
     * Bulk-copy {@code count} consecutive Double3 values ({@code count * 3} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code srcOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, count * 24L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++)
            dest.put(destOffset + _i, src.getDouble(srcOffset + _i * 8));
        return dest;
    }

    /**
     * Copy one Double3 (3 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, long src) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.hasArray() && dest.order() == java.nio.ByteOrder.nativeOrder() && destOffset >= 0 && destOffset <= dest.limit() - 3) {
            java.util.Objects.checkFromIndexSize(dest.arrayOffset() + destOffset, 3, dest.array().length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest.array(), UnsafeCopy.DOUBLE_ARRAY_BASE + (dest.arrayOffset() + destOffset) * 8L, 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            for (int _i = 0; _i < 3; _i++)
                dest.put(destOffset + _i, UnsafeOpsHolder.U.getDouble(src + _i * 8L));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Double3 values ({@code count * 3} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, long src, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, src, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset * 8L, count * 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.hasArray() && dest.order() == java.nio.ByteOrder.nativeOrder() && destOffset >= 0 && (count > 715827882 ? -1 : count * 3) >= 0 && destOffset <= dest.limit() - (count > 715827882 ? -1 : count * 3)) {
            java.util.Objects.checkFromIndexSize(dest.arrayOffset() + destOffset, (count > 715827882 ? -1 : count * 3), dest.array().length);
            UnsafeOpsHolder.U.copyMemory(null, src, dest.array(), UnsafeCopy.DOUBLE_ARRAY_BASE + (dest.arrayOffset() + destOffset) * 8L, count * 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            if (count < 0) return dest;
            if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
            int n = count * 3;
            for (int _i = 0; _i < n; _i++)
                dest.put(destOffset + _i, UnsafeOpsHolder.U.getDouble(src + _i * 8L));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Double3 (3 doubles) from {@code src}
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
     * Bulk-copy {@code count} consecutive Double3 values ({@code count * 3} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, double[] src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder()) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 715827882 ? -1 : count * 3), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.DOUBLE_ARRAY_BASE + srcOffset * 8L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 24L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++)
            dest.putDouble(destOffset + _i * 8, src[srcOffset + _i]);
        return dest;
    }

    /**
     * Copy one Double3 (3 doubles) from {@code src}
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
     * Bulk-copy {@code count} consecutive Double3 values ({@code count * 3} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * {@code destOffset} is a byte offset, not an element index.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isDirect() && !dest.isReadOnly() && dest.order() == java.nio.ByteOrder.nativeOrder() && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, null, UnsafeOpsHolder.U.getLong(dest, UnsafeCopy.BB_ADDRESS_OFFSET) + destOffset, count * 24L);
            return dest;
        }
        if (count < 0) return dest;
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++)
            dest.putDouble(destOffset + _i * 8, src.get(srcOffset + _i));
        return dest;
    }

    /**
     * Copy one Double3 (3 doubles) from {@code src}
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
     * Bulk-copy {@code count} consecutive Double3 values ({@code count * 3} doubles) from {@code src}
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
        if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
        int n = count * 3;
        for (int _i = 0; _i < n; _i++)
            dest.putDouble(destOffset + _i * 8, src.getDouble(srcOffset + _i * 8));
        return dest;
    }

    /**
     * Copy one Double3 (3 doubles) from {@code src}
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
            for (int _i = 0; _i < 3; _i++)
                dest.putDouble(destOffset + _i * 8, UnsafeOpsHolder.U.getDouble(src + _i * 8L));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Double3 values ({@code count * 3} doubles) from {@code src}
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
            if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
            int n = count * 3;
            for (int _i = 0; _i < n; _i++)
                dest.putDouble(destOffset + _i * 8, UnsafeOpsHolder.U.getDouble(src + _i * 8L));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Double3 (3 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, double[] src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(srcOffset, 3, src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.DOUBLE_ARRAY_BASE + srcOffset * 8L, null, dest, 24L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Double3 values ({@code count * 3} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, double[] src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            java.util.Objects.checkFromIndexSize(srcOffset, (count > 715827882 ? -1 : count * 3), src.length);
            UnsafeOpsHolder.U.copyMemory(src, UnsafeCopy.DOUBLE_ARRAY_BASE + srcOffset * 8L, null, dest, count * 24L);
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Double3 (3 doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, java.nio.DoubleBuffer src, int srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, null, dest, 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.hasArray() && src.order() == java.nio.ByteOrder.nativeOrder() && srcOffset >= 0 && srcOffset <= src.limit() - 3) {
            java.util.Objects.checkFromIndexSize(src.arrayOffset() + srcOffset, 3, src.array().length);
            UnsafeOpsHolder.U.copyMemory(src.array(), UnsafeCopy.DOUBLE_ARRAY_BASE + (src.arrayOffset() + srcOffset) * 8L, null, dest, 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            for (int _i = 0; _i < 3; _i++)
                UnsafeOpsHolder.U.putDouble(dest + _i * 8L, src.get(srcOffset + _i));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Double3 values ({@code count * 3} doubles) from {@code src}
     * to {@code dest}, translating between their storage backings. Returns {@code dest}.
     * The source and destination ranges must not overlap unless they are identical.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17 variants only)
     */
    public static long copy(long dest, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isDirect() && src.order() == java.nio.ByteOrder.nativeOrder()) {
            UnsafeOpsHolder.U.copyMemory(null, UnsafeOpsHolder.U.getLong(src, UnsafeCopy.BB_ADDRESS_OFFSET) + srcOffset * 8L, null, dest, count * 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.hasArray() && src.order() == java.nio.ByteOrder.nativeOrder() && srcOffset >= 0 && (count > 715827882 ? -1 : count * 3) >= 0 && srcOffset <= src.limit() - (count > 715827882 ? -1 : count * 3)) {
            java.util.Objects.checkFromIndexSize(src.arrayOffset() + srcOffset, (count > 715827882 ? -1 : count * 3), src.array().length);
            UnsafeOpsHolder.U.copyMemory(src.array(), UnsafeCopy.DOUBLE_ARRAY_BASE + (src.arrayOffset() + srcOffset) * 8L, null, dest, count * 24L);
            return dest;
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE) {
            if (count < 0) return dest;
            if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
            int n = count * 3;
            for (int _i = 0; _i < n; _i++)
                UnsafeOpsHolder.U.putDouble(dest + _i * 8L, src.get(srcOffset + _i));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Double3 (3 doubles) from {@code src}
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
            for (int _i = 0; _i < 3; _i++)
                UnsafeOpsHolder.U.putDouble(dest + _i * 8L, src.getDouble(srcOffset + _i * 8));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Bulk-copy {@code count} consecutive Double3 values ({@code count * 3} doubles) from {@code src}
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
            if (count > 715827882) throw new IndexOutOfBoundsException("count " + count + " exceeds the addressable range");
            int n = count * 3;
            for (int _i = 0; _i < n; _i++)
                UnsafeOpsHolder.U.putDouble(dest + _i * 8L, src.getDouble(srcOffset + _i * 8));
            return dest;
        }
        throw new UnsupportedOperationException("raw long address copy requires storeLoadBackend=UNSAFE");
    }

    /**
     * Copy one Double3 (3 doubles) from {@code src}
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
     * Bulk-copy {@code count} consecutive Double3 values ({@code count * 3} doubles) from {@code src}
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

    /**
     * The floored remainder of x and y, exactly kotlin.Double.mod: q = floor(x / y) is off by
     * at most one (too large) while it fits the mantissa, so x - y * q with one correction is
     * the floored remainder - a zero one with the sign of x, like x % y; % (a runtime call) only
     * when it does not fit or y is infinite.
     */
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
