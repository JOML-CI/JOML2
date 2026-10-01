// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import java.nio.ByteBuffer;
import java.lang.foreign.MemorySegment;

/**
 * Read-only view of a ray of single-precision {@code float} components.
 * <p>
 * Declares the accessors, queries, store methods and dest-form operations that read but never
 * mutate the receiver; the mutable counterpart is {@link FloatRay}. APIs that only read a ray
 * should accept {@code FloatRayR}, so callers can pass mutable instances without exposing them to
 * modification.
 * <p>
 * Arguments of type {@code FloatRayR} must be instances created by the library ({@link Joml}
 * factories / the library's own types); the implementations read cached state through the library's
 * own classes, so foreign implementations of the {@code *R} interfaces are not supported as
 * arguments.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Float.floatToIntBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns). Only instances
 * of this library's implementation compare equal to each other; the {@code equals} of a ray never
 * returns {@code true} for an object of another type.
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
 */
public interface FloatRayR {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 24;

    /** {@return a shared all-zero ray} Never mutate it - it is returned as the read-only view for that reason. <p>Valid input: any value, NaN and the infinities included. */
    public static FloatRayR ZERO() { return Holder.ZERO; }


    /**
     * Set the direction of this ray to {@code d} and store the result in {@code dest}.
     * <p>
     * Valid input: {@code d} must be non-zero.
     *
     * @param d the new direction
     * @param dest will hold the result
     * @return dest
     */
    FloatRay setDirection(Float3R d, @Mutated FloatRay dest);

    /**
     * Set the direction of this ray to {@code d} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code d} must be non-zero.
     *
     * @param d the new direction
     * @param dest will hold the result
     * @return dest
     */
    DoubleRay setDirection(Float3R d, @Mutated DoubleRay dest);

    /**
     * Set the direction of this ray to ({@code x}, {@code y}, {@code z}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code (x, y, z)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    FloatRay setDirection(float x, float y, float z, @Mutated FloatRay dest);

    /**
     * Set the direction of this ray to ({@code x}, {@code y}, {@code z}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (x, y, z)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleRay setDirection(float x, float y, float z, @Mutated DoubleRay dest);

    /**
     * Set the origin of this ray to {@code o} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the new origin
     * @param dest will hold the result
     * @return dest
     */
    FloatRay setOrigin(Float3R o, @Mutated FloatRay dest);

    /**
     * Set the origin of this ray to {@code o} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param o the new origin
     * @param dest will hold the result
     * @return dest
     */
    DoubleRay setOrigin(Float3R o, @Mutated DoubleRay dest);

    /**
     * Set the origin of this ray to ({@code x}, {@code y}, {@code z}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    FloatRay setOrigin(float x, float y, float z, @Mutated FloatRay dest);

    /**
     * Set the origin of this ray to ({@code x}, {@code y}, {@code z}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleRay setOrigin(float x, float y, float z, @Mutated DoubleRay dest);

    /**
     * Convert this ray to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleRay toDouble(@Mutated DoubleRay dest);

    /**
     * Transform this ray by {@code m} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    FloatRay transform(Float3x4R m, @Mutated FloatRay dest);

    /**
     * Transform this ray by {@code m} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    DoubleRay transform(Float3x4R m, @Mutated DoubleRay dest);

    /**
     * Transform this ray by {@code m} and store the result in {@code dest}.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    FloatRay transform(Float4x4R m, @Mutated FloatRay dest);

    /**
     * Transform this ray by {@code m} and store the result in {@code dest}.
     * <p>
     * Only the affine part of {@code m} is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the transformation matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    DoubleRay transform(Float4x4R m, @Mutated DoubleRay dest);

    /**
     * Compute the point on this ray at the parameter value {@code t} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the distance along the ray, as a multiple of the ray direction
     * @param dest will hold the result
     * @return dest
     */
    Float3 at(float t, @Mutated Float3 dest);

    /**
     * Compute the point on this ray at the parameter value {@code t} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the distance along the ray, as a multiple of the ray direction
     * @param dest will hold the result
     * @return dest
     */
    Double3 at(float t, @Mutated Double3 dest);

    /**
     * Compute the point on this ray closest to the given point, i.e. the orthogonal projection of
     * the point onto the ray's line, or the origin when that projection lies behind the origin. The
     * direction need not be of unit length but must not be zero.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    Float3 closestPointToPoint(Float3R p, @Mutated Float3 dest);

    /**
     * Compute the point on this ray closest to the given point, i.e. the orthogonal projection of
     * the point onto the ray's line, or the origin when that projection lies behind the origin. The
     * direction need not be of unit length but must not be zero.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param p the point to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    Double3 closestPointToPoint(Float3R p, @Mutated Double3 dest);

    /**
     * Compute the point on this ray closest to the given point, i.e. the orthogonal projection of
     * the point onto the ray's line, or the origin when that projection lies behind the origin. The
     * direction need not be of unit length but must not be zero.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param x the {@code x} component of the point {@code (x, y, z)} to find the closest point to
     * @param y the {@code y} component of the point {@code (x, y, z)} to find the closest point to
     * @param z the {@code z} component of the point {@code (x, y, z)} to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    Float3 closestPointToPoint(float x, float y, float z, @Mutated Float3 dest);

    /**
     * Compute the point on this ray closest to the given point, i.e. the orthogonal projection of
     * the point onto the ray's line, or the origin when that projection lies behind the origin. The
     * direction need not be of unit length but must not be zero.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param x the {@code x} component of the point {@code (x, y, z)} to find the closest point to
     * @param y the {@code y} component of the point {@code (x, y, z)} to find the closest point to
     * @param z the {@code z} component of the point {@code (x, y, z)} to find the closest point to
     * @param dest will hold the result
     * @return dest
     */
    Double3 closestPointToPoint(float x, float y, float z, @Mutated Double3 dest);

    /**
     * Compute the point on this ray closest to the given point, i.e. the orthogonal projection of
     * the point onto the ray's line, or the origin when that projection lies behind the origin. The
     * direction need not be of unit length but must not be zero.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param p the point to find the closest point to (also receives the result)
     * @return {@code p}
     */
    default Float3 closestPointToPoint(@Mutated Float3 p) { return closestPointToPoint(p, p); }

    /**
     * Compute the squared distance between this ray and the given point, i.e. the squared distance
     * from the point to the closest point on the ray (the ray starts at its origin and extends only
     * along its direction). The direction need not be of unit length but must not be zero.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param p the point to measure the distance to
     * @return the squared distance between this ray and the given point, i.e. the squared distance
     *        from the point to the closest point on the ray (the ray starts at its origin and
     *        extends only along its direction). The direction need not be of unit length but must
     *        not be zero
     */
    float distanceSquaredToPoint(Float3R p);

    /**
     * Compute the squared distance between this ray and the given point, i.e. the squared distance
     * from the point to the closest point on the ray (the ray starts at its origin and extends only
     * along its direction). The direction need not be of unit length but must not be zero.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param x the {@code x} component of the point {@code (x, y, z)} to measure the distance to
     * @param y the {@code y} component of the point {@code (x, y, z)} to measure the distance to
     * @param z the {@code z} component of the point {@code (x, y, z)} to measure the distance to
     * @return the squared distance between this ray and the given point, i.e. the squared distance
     *        from the point to the closest point on the ray (the ray starts at its origin and
     *        extends only along its direction). The direction need not be of unit length but must
     *        not be zero
     */
    float distanceSquaredToPoint(float x, float y, float z);

    /**
     * Compute the distance between this ray and the given point, i.e. the distance from the point
     * to the closest point on the ray (the ray starts at its origin and extends only along its
     * direction). The direction need not be of unit length but must not be zero.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param p the point to measure the distance to
     * @return the distance between this ray and the given point, i.e. the distance from the point
     *        to the closest point on the ray (the ray starts at its origin and extends only along
     *        its direction). The direction need not be of unit length but must not be zero
     */
    float distanceToPoint(Float3R p);

    /**
     * Compute the distance between this ray and the given point, i.e. the distance from the point
     * to the closest point on the ray (the ray starts at its origin and extends only along its
     * direction). The direction need not be of unit length but must not be zero.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; the
     * direction of this ray must be non-zero.
     *
     * @param x the {@code x} component of the point {@code (x, y, z)} to measure the distance to
     * @param y the {@code y} component of the point {@code (x, y, z)} to measure the distance to
     * @param z the {@code z} component of the point {@code (x, y, z)} to measure the distance to
     * @return the distance between this ray and the given point, i.e. the distance from the point
     *        to the closest point on the ray (the ray starts at its origin and extends only along
     *        its direction). The direction need not be of unit length but must not be zero
     */
    float distanceToPoint(float x, float y, float z);

    /**
     * Get the direction of this ray and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 getDirection(@Mutated Float3 dest);

    /**
     * Get the direction of this ray and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getDirection(@Mutated Double3 dest);

    /**
     * Get the origin of this ray and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 getOrigin(@Mutated Float3 dest);

    /**
     * Get the origin of this ray and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getOrigin(@Mutated Double3 dest);
    /** {@return the value of the {@code oX} component} <p>Valid input: any value, NaN and the infinities included. */
    float oX();
    /** {@return the value of the {@code oY} component} <p>Valid input: any value, NaN and the infinities included. */
    float oY();
    /** {@return the value of the {@code oZ} component} <p>Valid input: any value, NaN and the infinities included. */
    float oZ();
    /** {@return the value of the {@code dX} component} <p>Valid input: any value, NaN and the infinities included. */
    float dX();
    /** {@return the value of the {@code dY} component} <p>Valid input: any value, NaN and the infinities included. */
    float dY();
    /** {@return the value of the {@code dZ} component} <p>Valid input: any value, NaN and the infinities included. */
    float dZ();

    /**
     * Store the elements into the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    default float[] store(@Mutated float[] dest) { return store(dest, 0); }

    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    float[] store(@Mutated float[] dest, int offset);

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination buffer
     * @return dest
     */
    default FloatBuffer store(@Mutated FloatBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination buffer
     * @return dest
     */
    default FloatBuffer storeAbsolute(@Mutated FloatBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    FloatBuffer storeAbsolute(int index, @Mutated FloatBuffer dest);

    /**
     * Store the elements into the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default FloatBuffer storeRelative(@Mutated FloatBuffer dest) {
        if (dest.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 6);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer store(@Mutated ByteBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeAbsolute(@Mutated ByteBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, starting at the given absolute index (the
     * position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param dest the destination byte buffer
     * @return dest
     */
    ByteBuffer storeAbsolute(int index, @Mutated ByteBuffer dest);

    /**
     * Store the elements into the given byte buffer, starting at its current position and advancing
     * the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeRelative(@Mutated ByteBuffer dest) {
        if (dest.remaining() < 24) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 24);
        return dest;
    }

    /**
     * Store the elements into the given memory segment.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    default MemorySegment store(@Mutated MemorySegment dest) { return store(0L, dest); }

    /**
     * Store the elements into the given memory segment, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    MemorySegment store(long offset, @Mutated MemorySegment dest);

    /**
     * Store the elements into the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    FloatRay storeUnsafe(long address);

    /**
     * Store the elements into the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    default double[] store(@Mutated double[] dest) { return store(dest, 0); }

    /**
     * Store the elements into the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    double[] store(@Mutated double[] dest, int offset);

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination buffer
     * @return dest
     */
    default DoubleBuffer store(@Mutated DoubleBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer, starting at its current position (the position is
     * not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination buffer
     * @return dest
     */
    default DoubleBuffer storeAbsolute(@Mutated DoubleBuffer dest) { return storeAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer, starting at the given absolute index (the position
     * is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    DoubleBuffer storeAbsolute(int index, @Mutated DoubleBuffer dest);

    /**
     * Store the elements into the given buffer, starting at its current position and advancing the
     * position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default DoubleBuffer storeRelative(@Mutated DoubleBuffer dest) {
        if (dest.remaining() < 6) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 6);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
     * starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeDouble(@Mutated ByteBuffer dest) { return storeDoubleAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
     * starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeDoubleAbsolute(@Mutated ByteBuffer dest) { return storeDoubleAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
     * starting at the given absolute index (the position is not used or modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param dest the destination byte buffer
     * @return dest
     */
    ByteBuffer storeDoubleAbsolute(int index, @Mutated ByteBuffer dest);

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
     * starting at its current position and advancing the position accordingly.
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeDoubleRelative(@Mutated ByteBuffer dest) {
        if (dest.remaining() < 48) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeDoubleAbsolute(pos, dest);
        dest.position(pos + 48);
        return dest;
    }

    /**
     * Store the elements into the given memory segment, converting each element to {@code double}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @return dest
     */
    default MemorySegment storeDouble(@Mutated MemorySegment dest) { return storeDouble(0L, dest); }

    /**
     * Store the elements into the given memory segment, converting each element to {@code double},
     * starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @return dest
     */
    MemorySegment storeDouble(long offset, @Mutated MemorySegment dest);

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    FloatRay storeDoubleUnsafe(long address);

    /** {@return whether all components of this ray are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    boolean isFinite();

    /** {@return whether any component of this ray is NaN} <p>Valid input: any value, NaN and the infinities included. */
    boolean isNaN();

    /**
     * Compare this ray component-wise against {@code other}, allowing a difference
     * of at most {@code epsilon} per component.
     * <p>
     * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
     * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and
     * a NaN component never compares equal to anything.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the ray to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    boolean equalsEpsilon(FloatRayR other, float epsilon);

    /**
     * Compare this ray with the given object for element-wise equality.
     * <p>
     * Each component is compared bitwise, as by {@code Float.floatToIntBits}: {@code 0.0} and
     * {@code -0.0} are not equal, and NaN is equal to NaN. Use {@link #equalsEpsilon} for a
     * tolerant comparison.
     * <p>
     * Only instances of this library's implementation compare equal to each other; any other object
     * yields {@code false}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param obj the object to compare with
     * @return {@code true} if {@code obj} is a ray of this library with element-wise equal
     *        components, {@code false} otherwise
     */
    boolean equals(@org.jspecify.annotations.Nullable Object obj);

    /**
     * Compute a hash code consistent with {@link #equals}: it is derived from the components via
     * {@code Float.floatToIntBits} alone.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return the hash code of this ray
     */
    int hashCode();

    /** Backs {@code ZERO()}: defers the shared instance's
     *  allocation to first use, avoiding a class-initialization cycle with the
     *  implementation class. Not part of the public API. */
    final class Holder {
        private Holder() {}
        static final FloatRayR ZERO = Joml.floatRay(0, 0, 0, 0, 0, 0);
    }
}
