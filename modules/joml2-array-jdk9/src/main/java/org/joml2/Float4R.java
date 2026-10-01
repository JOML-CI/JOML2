// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import java.nio.ByteBuffer;

/**
 * Read-only view of a 4D vector of single-precision {@code float} components.
 * <p>
 * Declares the accessors, queries, store methods and dest-form operations that read but never
 * mutate the receiver; the mutable counterpart is {@link Float4}. APIs that only read a 4D vector
 * should accept {@code Float4R}, so callers can pass mutable instances without exposing them to
 * modification.
 * <p>
 * Arguments of type {@code Float4R} must be instances created by the library ({@link Joml}
 * factories / the library's own types); the implementations read cached state through the library's
 * own classes, so foreign implementations of the {@code *R} interfaces are not supported as
 * arguments.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Float.floatToIntBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns). Only instances
 * of this library's implementation compare equal to each other; the {@code equals} of a vector
 * never returns {@code true} for an object of another type.
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
 */
public interface Float4R {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int SIZE_BYTES = 16;

    /** {@return a shared all-zero vector} Never mutate it - it is returned as the read-only view for that reason. <p>Valid input: any value, NaN and the infinities included. */
    public static Float4R ZERO() { return Holder.ZERO; }


    /**
     * Add {@code other} to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    Float4 add(Float4R other, @Mutated Float4 dest);

    /**
     * Add {@code other} to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    Double4 add(Float4R other, @Mutated Double4 dest);

    /**
     * Add ({@code x}, {@code y}, {@code z}, {@code w}) to this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 add(float x, float y, float z, float w, @Mutated Float4 dest);

    /**
     * Add ({@code x}, {@code y}, {@code z}, {@code w}) to this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 add(float x, float y, float z, float w, @Mutated Double4 dest);

    /**
     * Divide each component of this vector by {@code scalar} and store the result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Float4 div(float scalar, @Mutated Float4 dest);

    /**
     * Divide each component of this vector by {@code scalar} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Double4 div(float scalar, @Mutated Double4 dest);

    /**
     * Divide this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Float4 div(Float4R other, @Mutated Float4 dest);

    /**
     * Divide this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Double4 div(Float4R other, @Mutated Double4 dest);

    /**
     * Divide this vector component-wise by ({@code x}, {@code y}, {@code z}, {@code w}) and store
     * the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 div(float x, float y, float z, float w, @Mutated Float4 dest);

    /**
     * Divide this vector component-wise by ({@code x}, {@code y}, {@code z}, {@code w}) and store
     * the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 div(float x, float y, float z, float w, @Mutated Double4 dest);

    /**
     * Multiply this vector component-wise by {@code b} and add {@code c}, i.e. compute
     * {@code this * b + c} per component and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param c the vector to add
     * @param dest will hold the result
     * @return dest
     */
    Float4 fma(float b, Float4R c, @Mutated Float4 dest);

    /**
     * Multiply this vector component-wise by {@code b} and add {@code c}, i.e. compute
     * {@code this * b + c} per component and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param c the vector to add
     * @param dest will hold the result
     * @return dest
     */
    Double4 fma(float b, Float4R c, @Mutated Double4 dest);

    /**
     * Multiply this vector component-wise by {@code b} and add ({@code x}, {@code y}, {@code z},
     * {@code w}), i.e. compute {@code this * b + (x, y, z, w)} per component and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 fma(float b, float x, float y, float z, float w, @Mutated Float4 dest);

    /**
     * Multiply this vector component-wise by {@code b} and add ({@code x}, {@code y}, {@code z},
     * {@code w}), i.e. compute {@code this * b + (x, y, z, w)} per component and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 fma(float b, float x, float y, float z, float w, @Mutated Double4 dest);

    /**
     * Multiply this vector component-wise by {@code b} and add {@code c}, i.e. compute
     * {@code this * b + c} per component and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param c the vector to add
     * @param dest will hold the result
     * @return dest
     */
    Float4 fma(Float4R b, Float4R c, @Mutated Float4 dest);

    /**
     * Multiply this vector component-wise by {@code b} and add {@code c}, i.e. compute
     * {@code this * b + c} per component and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param c the vector to add
     * @param dest will hold the result
     * @return dest
     */
    Double4 fma(Float4R b, Float4R c, @Mutated Double4 dest);

    /**
     * Multiply this vector component-wise by ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) and
     * add ({@code cX}, {@code cY}, {@code cZ}, {@code cW}), i.e. compute
     * {@code this * (bX, bY, bZ, bW) + (cX, cY, cZ, cW)} per component and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bW the {@code w} component of the vector {@code (bX, bY, bZ, bW)}
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cW the {@code w} component of the vector {@code (cX, cY, cZ, cW)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 fma(float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW, @Mutated Float4 dest);

    /**
     * Multiply this vector component-wise by ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) and
     * add ({@code cX}, {@code cY}, {@code cZ}, {@code cW}), i.e. compute
     * {@code this * (bX, bY, bZ, bW) + (cX, cY, cZ, cW)} per component and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bW the {@code w} component of the vector {@code (bX, bY, bZ, bW)}
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cW the {@code w} component of the vector {@code (cX, cY, cZ, cW)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 fma(float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW, @Mutated Double4 dest);

    /**
     * Multiply each component of this vector by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    Float4 mul(float scalar, @Mutated Float4 dest);

    /**
     * Multiply each component of this vector by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    Double4 mul(float scalar, @Mutated Double4 dest);

    /**
     * Multiply this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    Float4 mul(Float4R other, @Mutated Float4 dest);

    /**
     * Multiply this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    Double4 mul(Float4R other, @Mutated Double4 dest);

    /**
     * Multiply this vector component-wise by ({@code x}, {@code y}, {@code z}, {@code w}) and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 mul(float x, float y, float z, float w, @Mutated Float4 dest);

    /**
     * Multiply this vector component-wise by ({@code x}, {@code y}, {@code z}, {@code w}) and store
     * the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 mul(float x, float y, float z, float w, @Mutated Double4 dest);

    /**
     * Negate this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 negate(@Mutated Float4 dest);

    /**
     * Negate this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 negate(@Mutated Double4 dest);

    /**
     * Subtract {@code other} from this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    Float4 sub(Float4R other, @Mutated Float4 dest);

    /**
     * Subtract {@code other} from this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    Double4 sub(Float4R other, @Mutated Double4 dest);

    /**
     * Subtract ({@code x}, {@code y}, {@code z}, {@code w}) from this vector and store the result
     * in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 sub(float x, float y, float z, float w, @Mutated Float4 dest);

    /**
     * Subtract ({@code x}, {@code y}, {@code z}, {@code w}) from this vector and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 sub(float x, float y, float z, float w, @Mutated Double4 dest);

    /**
     * Set this vector to {@code s} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    Float4 set(float s, @Mutated Float4 dest);

    /**
     * Set this vector to {@code s} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    Double4 set(float s, @Mutated Double4 dest);

    /**
     * Convert this vector to {@code double} precision and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 toDouble(@Mutated Double4 dest);

    /**
     * Convert this vector to {@code byte} precision and store the result in {@code dest}.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Byte4 toByte(@Mutated Byte4 dest);

    /**
     * Convert this vector to {@code byte} precision and store the result in {@code dest}.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mode the rounding mode to use
     * @param dest will hold the result
     * @return dest
     */
    Byte4 toByte(RoundingMode mode, @Mutated Byte4 dest);

    /**
     * Convert this vector to {@code short} precision and store the result in {@code dest}.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Short4 toShort(@Mutated Short4 dest);

    /**
     * Convert this vector to {@code short} precision and store the result in {@code dest}.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mode the rounding mode to use
     * @param dest will hold the result
     * @return dest
     */
    Short4 toShort(RoundingMode mode, @Mutated Short4 dest);

    /**
     * Convert this vector to {@code int} precision and store the result in {@code dest}.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Int4 toInt(@Mutated Int4 dest);

    /**
     * Convert this vector to {@code int} precision and store the result in {@code dest}.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mode the rounding mode to use
     * @param dest will hold the result
     * @return dest
     */
    Int4 toInt(RoundingMode mode, @Mutated Int4 dest);

    /**
     * Convert this vector to {@code long} precision and store the result in {@code dest}.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Long4 toLong(@Mutated Long4 dest);

    /**
     * Convert this vector to {@code long} precision and store the result in {@code dest}.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mode the rounding mode to use
     * @param dest will hold the result
     * @return dest
     */
    Long4 toLong(RoundingMode mode, @Mutated Long4 dest);

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
     * @param p1 the first control point
     * @param p2 the second control point
     * @param p3 the end point of the curve
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Float4 bezier(Float4R p1, Float4R p2, Float4R p3, float t, @Mutated Float4 dest);

    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points {@code p1} and {@code p2} and ends at {@code p3} and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code p3} at
     * {@code t = 1}; the control points {@code p1} and {@code p2} pull it towards themselves but
     * are generally not on the curve.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1 the first control point
     * @param p2 the second control point
     * @param p3 the end point of the curve
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double4 bezier(Float4R p1, Float4R p2, Float4R p3, float t, @Mutated Double4 dest);

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
     * @param dest will hold the result
     * @return dest
     */
    Float4 bezier(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Float4 dest);

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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
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
     * @param dest will hold the result
     * @return dest
     */
    Double4 bezier(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Double4 dest);

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
     * @param p1 the control point
     * @param p2 the end point of the curve
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Float4 bezier2(Float4R p1, Float4R p2, float t, @Mutated Float4 dest);

    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point {@code p1} and ends at {@code p2} and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code p2} at
     * {@code t = 1}; the control point {@code p1} pulls it towards itself but is generally not on
     * the curve.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1 the control point
     * @param p2 the end point of the curve
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double4 bezier2(Float4R p1, Float4R p2, float t, @Mutated Double4 dest);

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
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Float4 bezier2(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t, @Mutated Float4 dest);

    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ends at ({@code p2X},
     * {@code p2Y}, {@code p2Z}, {@code p2W}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}) at {@code t = 1}; the control point ({@code p1X}, {@code p1Y},
     * {@code p1Z}, {@code p1W}) pulls it towards itself but is generally not on the curve.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double4 bezier2(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t, @Mutated Double4 dest);

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
     * @param p1 the control point
     * @param p2 the end point of the curve
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Float4 bezier2Tangent(Float4R p1, Float4R p2, float t, @Mutated Float4 dest);

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point {@code p1}
     * and ends at {@code p2} and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code p2} at
     * {@code t = 1}; the control point {@code p1} pulls it towards itself but is generally not on
     * the curve.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1 the control point
     * @param p2 the end point of the curve
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double4 bezier2Tangent(Float4R p1, Float4R p2, float t, @Mutated Double4 dest);

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
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Float4 bezier2Tangent(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t, @Mutated Float4 dest);

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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1Z the {@code z} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p1W the {@code w} component of the vector {@code (p1X, p1Y, p1Z, p1W)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2Z the {@code z} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param p2W the {@code w} component of the vector {@code (p2X, p2Y, p2Z, p2W)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double4 bezier2Tangent(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t, @Mutated Double4 dest);

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
     * @param p1 the first control point
     * @param p2 the second control point
     * @param p3 the end point of the curve
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Float4 bezierTangent(Float4R p1, Float4R p2, Float4R p3, float t, @Mutated Float4 dest);

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points {@code p1} and
     * {@code p2} and ends at {@code p3} and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code p3} at
     * {@code t = 1}; the control points {@code p1} and {@code p2} pull it towards themselves but
     * are generally not on the curve.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1 the first control point
     * @param p2 the second control point
     * @param p3 the end point of the curve
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double4 bezierTangent(Float4R p1, Float4R p2, Float4R p3, float t, @Mutated Double4 dest);

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
     * @param dest will hold the result
     * @return dest
     */
    Float4 bezierTangent(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Float4 dest);

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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
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
     * @param dest will hold the result
     * @return dest
     */
    Double4 bezierTangent(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Double4 dest);

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
     * @param p1 the start point of the interpolated segment
     * @param p2 the end point of the interpolated segment
     * @param p3 the control point after the segment, i.e. the spline point following {@code p2}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Float4 catmullRom(Float4R p1, Float4R p2, Float4R p3, float t, @Mutated Float4 dest);

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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1 the start point of the interpolated segment
     * @param p2 the end point of the interpolated segment
     * @param p3 the control point after the segment, i.e. the spline point following {@code p2}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double4 catmullRom(Float4R p1, Float4R p2, Float4R p3, float t, @Mutated Double4 dest);

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
     * @param dest will hold the result
     * @return dest
     */
    Float4 catmullRom(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Float4 dest);

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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
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
     * @param dest will hold the result
     * @return dest
     */
    Double4 catmullRom(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Double4 dest);

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
     * @param p1 the start point of the interpolated segment
     * @param p2 the end point of the interpolated segment
     * @param p3 the control point after the segment, i.e. the spline point following {@code p2}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Float4 catmullRomTangent(Float4R p1, Float4R p2, Float4R p3, float t, @Mutated Float4 dest);

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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1 the start point of the interpolated segment
     * @param p2 the end point of the interpolated segment
     * @param p3 the control point after the segment, i.e. the spline point following {@code p2}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double4 catmullRomTangent(Float4R p1, Float4R p2, Float4R p3, float t, @Mutated Double4 dest);

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
     * @param dest will hold the result
     * @return dest
     */
    Float4 catmullRomTangent(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Float4 dest);

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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
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
     * @param dest will hold the result
     * @return dest
     */
    Double4 catmullRomTangent(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t, @Mutated Double4 dest);

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
     * @param t0 the tangent at the start point, i.e. at this vector
     * @param v1 the end point of the curve
     * @param t1 the tangent at the end point {@code v1}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Float4 hermite(Float4R t0, Float4R v1, Float4R t1, float t, @Mutated Float4 dest);

    /**
     * Interpolate along the cubic Hermite curve that starts at this vector with the tangent
     * {@code t0} and ends at {@code v1} with the tangent {@code t1} and store the result in
     * {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code v1} at
     * {@code t = 1}; the two tangents set its direction and speed at those end points.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t0 the tangent at the start point, i.e. at this vector
     * @param v1 the end point of the curve
     * @param t1 the tangent at the end point {@code v1}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double4 hermite(Float4R t0, Float4R v1, Float4R t1, float t, @Mutated Double4 dest);

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
     * @param dest will hold the result
     * @return dest
     */
    Float4 hermite(float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t, @Mutated Float4 dest);

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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
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
     * @param dest will hold the result
     * @return dest
     */
    Double4 hermite(float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t, @Mutated Double4 dest);

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
     * @param t0 the tangent at the start point, i.e. at this vector
     * @param v1 the end point of the curve
     * @param t1 the tangent at the end point {@code v1}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Float4 hermiteTangent(Float4R t0, Float4R v1, Float4R t1, float t, @Mutated Float4 dest);

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Hermite curve that starts at this vector with the tangent {@code t0} and ends at
     * {@code v1} with the tangent {@code t1} and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through {@code v1} at
     * {@code t = 1}; the two tangents set its direction and speed at those end points.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t0 the tangent at the start point, i.e. at this vector
     * @param v1 the end point of the curve
     * @param t1 the tangent at the end point {@code v1}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double4 hermiteTangent(Float4R t0, Float4R v1, Float4R t1, float t, @Mutated Double4 dest);

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
     * @param dest will hold the result
     * @return dest
     */
    Float4 hermiteTangent(float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t, @Mutated Float4 dest);

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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
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
     * @param dest will hold the result
     * @return dest
     */
    Double4 hermiteTangent(float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t, @Mutated Double4 dest);

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
     * @param other the vector to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Float4 lerp(Float4R other, float t, @Mutated Float4 dest);

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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double4 lerp(Float4R other, float t, @Mutated Double4 dest);

    /**
     * Linearly interpolate between this vector and ({@code x}, {@code y}, {@code z}, {@code w})
     * using the interpolation factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code x}, {@code y}, {@code z}, {@code w}) (interpolation factor {@code 1}). Each linearly
     * interpolated component is {@code this + (other - this) * t}, as in JOML and glMatrix:
     * monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the rounding
     * of {@code other - this}, which shows when this component is much larger in magnitude than the
     * other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Float4 lerp(float x, float y, float z, float w, float t, @Mutated Float4 dest);

    /**
     * Linearly interpolate between this vector and ({@code x}, {@code y}, {@code z}, {@code w})
     * using the interpolation factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code x}, {@code y}, {@code z}, {@code w}) (interpolation factor {@code 1}). Each linearly
     * interpolated component is {@code this + (other - this) * t}, as in JOML and glMatrix:
     * monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the rounding
     * of {@code other - this}, which shows when this component is much larger in magnitude than the
     * other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double4 lerp(float x, float y, float z, float w, float t, @Mutated Double4 dest);

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
     * @param other the vector to interpolate towards
     * @param t the per-component interpolation factors, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Float4 lerp(Float4R other, Float4R t, @Mutated Float4 dest);

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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to interpolate towards
     * @param t the per-component interpolation factors, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double4 lerp(Float4R other, Float4R t, @Mutated Double4 dest);

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
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param tX the {@code x} component of the vector {@code (tX, tY, tZ, tW)}
     * @param tY the {@code y} component of the vector {@code (tX, tY, tZ, tW)}
     * @param tZ the {@code z} component of the vector {@code (tX, tY, tZ, tW)}
     * @param tW the {@code w} component of the vector {@code (tX, tY, tZ, tW)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 lerp(float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW, @Mutated Float4 dest);

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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherZ the {@code z} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param otherW the {@code w} component of the vector {@code (otherX, otherY, otherZ, otherW)}
     * @param tX the {@code x} component of the vector {@code (tX, tY, tZ, tW)}
     * @param tY the {@code y} component of the vector {@code (tX, tY, tZ, tW)}
     * @param tZ the {@code z} component of the vector {@code (tX, tY, tZ, tW)}
     * @param tW the {@code w} component of the vector {@code (tX, tY, tZ, tW)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 lerp(float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW, @Mutated Double4 dest);

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
     * @param other the vector to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Float4 slerp(Float4R other, float t, @Mutated Float4 dest);

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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double4 slerp(Float4R other, float t, @Mutated Double4 dest);

    /**
     * Spherically interpolate between this vector and ({@code x}, {@code y}, {@code z}, {@code w})
     * using the interpolation factor {@code t}: the direction turns at a constant rate along the
     * shorter arc between the two directions, and the length changes linearly between the two
     * lengths and store the result in {@code dest}.
     * <p>
     * For unit vectors this is the usual {@code slerp} of directions. A zero vector has no
     * direction, so the result is then the linear interpolation; for two vectors pointing in
     * opposite directions, whose arc lies in no particular plane, the direction turns through the
     * perpendicular {@code (-y, x, -w, z)} of this vector. The angle is computed with
     * {@code atan2}, and vectors of any finite length are handled: when their squared lengths leave
     * the {@code float} range, they are first scaled exactly by powers of two.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code x}, {@code y}, {@code z}, {@code w}) (interpolation factor {@code 1}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Float4 slerp(float x, float y, float z, float w, float t, @Mutated Float4 dest);

    /**
     * Spherically interpolate between this vector and ({@code x}, {@code y}, {@code z}, {@code w})
     * using the interpolation factor {@code t}: the direction turns at a constant rate along the
     * shorter arc between the two directions, and the length changes linearly between the two
     * lengths and store the result in {@code dest}.
     * <p>
     * For unit vectors this is the usual {@code slerp} of directions. A zero vector has no
     * direction, so the result is then the linear interpolation; for two vectors pointing in
     * opposite directions, whose arc lies in no particular plane, the direction turns through the
     * perpendicular {@code (-y, x, -w, z)} of this vector. The angle is computed with
     * {@code atan2}, and vectors of any finite length are handled: when their squared lengths leave
     * the {@code float} range, they are first scaled exactly by powers of two.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code x}, {@code y}, {@code z}, {@code w}) (interpolation factor {@code 1}).
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double4 slerp(float x, float y, float z, float w, float t, @Mutated Double4 dest);

    /**
     * Compute the absolute value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 absolute(@Mutated Float4 dest);

    /**
     * Compute the absolute value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 absolute(@Mutated Double4 dest);

    /**
     * Compute the arc cosine of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 acos(@Mutated Float4 dest);

    /**
     * Compute the arc cosine of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 acos(@Mutated Double4 dest);

    /**
     * Add {@code b} scaled by {@code scalar} to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the vector to scale and add
     * @param scalar the factor to scale {@code b} by before adding
     * @param dest will hold the result
     * @return dest
     */
    Float4 addScaled(Float4R b, float scalar, @Mutated Float4 dest);

    /**
     * Add {@code b} scaled by {@code scalar} to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the vector to scale and add
     * @param scalar the factor to scale {@code b} by before adding
     * @param dest will hold the result
     * @return dest
     */
    Double4 addScaled(Float4R b, float scalar, @Mutated Double4 dest);

    /**
     * Add ({@code x}, {@code y}, {@code z}, {@code w}) scaled by {@code scalar} to this vector and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param scalar the factor to scale ({@code x}, {@code y}, {@code z}, {@code w}) by before
     *        adding
     * @param dest will hold the result
     * @return dest
     */
    Float4 addScaled(float x, float y, float z, float w, float scalar, @Mutated Float4 dest);

    /**
     * Add ({@code x}, {@code y}, {@code z}, {@code w}) scaled by {@code scalar} to this vector and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param scalar the factor to scale ({@code x}, {@code y}, {@code z}, {@code w}) by before
     *        adding
     * @param dest will hold the result
     * @return dest
     */
    Double4 addScaled(float x, float y, float z, float w, float scalar, @Mutated Double4 dest);

    /**
     * Add {@code b} scaled by {@code c} to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the vector to scale and add
     * @param c the per-component factors to scale {@code b} by before adding
     * @param dest will hold the result
     * @return dest
     */
    Float4 addScaled(Float4R b, Float4R c, @Mutated Float4 dest);

    /**
     * Add {@code b} scaled by {@code c} to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the vector to scale and add
     * @param c the per-component factors to scale {@code b} by before adding
     * @param dest will hold the result
     * @return dest
     */
    Double4 addScaled(Float4R b, Float4R c, @Mutated Double4 dest);

    /**
     * Add ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) scaled by ({@code cX}, {@code cY},
     * {@code cZ}, {@code cW}) to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bW the {@code w} component of the vector {@code (bX, bY, bZ, bW)}
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cW the {@code w} component of the vector {@code (cX, cY, cZ, cW)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 addScaled(float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW, @Mutated Float4 dest);

    /**
     * Add ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) scaled by ({@code cX}, {@code cY},
     * {@code cZ}, {@code cW}) to this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bY the {@code y} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bZ the {@code z} component of the vector {@code (bX, bY, bZ, bW)}
     * @param bW the {@code w} component of the vector {@code (bX, bY, bZ, bW)}
     * @param cX the {@code x} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cY the {@code y} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cZ the {@code z} component of the vector {@code (cX, cY, cZ, cW)}
     * @param cW the {@code w} component of the vector {@code (cX, cY, cZ, cW)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 addScaled(float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW, @Mutated Double4 dest);

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
     * @param other the vector to measure the angle to
     * @return the angle in radians between this vector and {@code other}
     */
    float angleBetween(Float4R other);

    /**
     * Compute the angle in radians between this vector and ({@code x}, {@code y}, {@code z},
     * {@code w}).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when the squared length of their cross product would leave the
     * {@code float} range, the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return the angle in radians between this vector and ({@code x}, {@code y}, {@code z},
     *        {@code w})
     */
    float angleBetween(float x, float y, float z, float w);

    /**
     * Compute the arc sine of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 asin(@Mutated Float4 dest);

    /**
     * Compute the arc sine of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 asin(@Mutated Double4 dest);

    /**
     * Compute the arc tangent of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 atan(@Mutated Float4 dest);

    /**
     * Compute the arc tangent of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 atan(@Mutated Double4 dest);

    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} {@code x} (the denominator) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the value to take the arc tangent over (the denominator)
     * @param dest will hold the result
     * @return dest
     */
    Float4 atan2(float x, @Mutated Float4 dest);

    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} {@code x} (the denominator) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the value to take the arc tangent over (the denominator)
     * @param dest will hold the result
     * @return dest
     */
    Double4 atan2(float x, @Mutated Double4 dest);

    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} the corresponding component of {@code x} (the
     * denominator) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the vector of denominators, one per component
     * @param dest will hold the result
     * @return dest
     */
    Float4 atan2(Float4R x, @Mutated Float4 dest);

    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} the corresponding component of {@code x} (the
     * denominator) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the vector of denominators, one per component
     * @param dest will hold the result
     * @return dest
     */
    Double4 atan2(Float4R x, @Mutated Double4 dest);

    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} the corresponding component of ({@code x},
     * {@code y}, {@code z}, {@code w}) (the denominator) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 atan2(float x, float y, float z, float w, @Mutated Float4 dest);

    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} the corresponding component of ({@code x},
     * {@code y}, {@code z}, {@code w}) (the denominator) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 atan2(float x, float y, float z, float w, @Mutated Double4 dest);

    /**
     * Compute the cube root of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 cbrt(@Mutated Float4 dest);

    /**
     * Compute the cube root of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 cbrt(@Mutated Double4 dest);

    /**
     * Compute the ceiling of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 ceil(@Mutated Float4 dest);

    /**
     * Compute the ceiling of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 ceil(@Mutated Double4 dest);

    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @param dest will hold the result
     * @return dest
     */
    Float4 clamp(float min, float max, @Mutated Float4 dest);

    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @param dest will hold the result
     * @return dest
     */
    Double4 clamp(float min, float max, @Mutated Double4 dest);

    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @param dest will hold the result
     * @return dest
     */
    Float4 clamp(Float4R min, Float4R max, @Mutated Float4 dest);

    /**
     * Clamp each component of this vector between {@code min} and {@code max} and store the result
     * in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @param dest will hold the result
     * @return dest
     */
    Double4 clamp(Float4R min, Float4R max, @Mutated Double4 dest);

    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ},
     * {@code minW}) and ({@code maxX}, {@code maxY}, {@code maxZ}, {@code maxW}) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: {@code (minX, minY, minZ, minW)} must not exceed
     * {@code (maxX, maxY, maxZ, maxW)} in any component.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minY the {@code y} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minZ the {@code z} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minW the {@code w} component of the vector {@code (minX, minY, minZ, minW)}
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxZ the {@code z} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxW the {@code w} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 clamp(float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW, @Mutated Float4 dest);

    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ},
     * {@code minW}) and ({@code maxX}, {@code maxY}, {@code maxZ}, {@code maxW}) and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (minX, minY, minZ, minW)} must not exceed
     * {@code (maxX, maxY, maxZ, maxW)} in any component.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minY the {@code y} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minZ the {@code z} component of the vector {@code (minX, minY, minZ, minW)}
     * @param minW the {@code w} component of the vector {@code (minX, minY, minZ, minW)}
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxZ the {@code z} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param maxW the {@code w} component of the vector {@code (maxX, maxY, maxZ, maxW)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 clamp(float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW, @Mutated Double4 dest);

    /**
     * Compute the sum of all components of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the sum of all components of this vector
     */
    float compAdd();

    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    float compMax();

    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    float compMin();

    /**
     * Compute the product of all components of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the product of all components of this vector
     */
    float compMul();

    /**
     * Copy the sign of {@code sign} onto each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sign the value whose sign is copied
     * @param dest will hold the result
     * @return dest
     */
    Float4 copySign(float sign, @Mutated Float4 dest);

    /**
     * Copy the sign of {@code sign} onto each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sign the value whose sign is copied
     * @param dest will hold the result
     * @return dest
     */
    Double4 copySign(float sign, @Mutated Double4 dest);

    /**
     * Copy the sign of each component of {@code sign} onto the corresponding component of this
     * vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sign the value whose sign is copied
     * @param dest will hold the result
     * @return dest
     */
    Float4 copySign(Float4R sign, @Mutated Float4 dest);

    /**
     * Copy the sign of each component of {@code sign} onto the corresponding component of this
     * vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sign the value whose sign is copied
     * @param dest will hold the result
     * @return dest
     */
    Double4 copySign(Float4R sign, @Mutated Double4 dest);

    /**
     * Copy the sign of each component of ({@code x}, {@code y}, {@code z}, {@code w}) onto the
     * corresponding component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 copySign(float x, float y, float z, float w, @Mutated Float4 dest);

    /**
     * Copy the sign of each component of ({@code x}, {@code y}, {@code z}, {@code w}) onto the
     * corresponding component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 copySign(float x, float y, float z, float w, @Mutated Double4 dest);

    /**
     * Compute the cosine of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 cos(@Mutated Float4 dest);

    /**
     * Compute the cosine of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 cos(@Mutated Double4 dest);

    /**
     * Compute the hyperbolic cosine of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 cosh(@Mutated Float4 dest);

    /**
     * Compute the hyperbolic cosine of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 cosh(@Mutated Double4 dest);

    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    Float4 cross(Float4R v, Float4R w, @Mutated Float4 dest);

    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @param dest will hold the result
     * @return dest
     */
    Double4 cross(Float4R v, Float4R w, @Mutated Double4 dest);

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
     * @param dest will hold the result
     * @return dest
     */
    Float4 cross(float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW, @Mutated Float4 dest);

    /**
     * Compute the four-dimensional cross product of this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}), in that order:
     * the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}) (the zero vector
     * when the three are linearly dependent) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
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
     * @param dest will hold the result
     * @return dest
     */
    Double4 cross(float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW, @Mutated Double4 dest);

    /**
     * Compute the value converted from radians to degrees of each component of this vector and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 degrees(@Mutated Float4 dest);

    /**
     * Compute the value converted from radians to degrees of each component of this vector and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 degrees(@Mutated Double4 dest);

    /**
     * Compute the distance between this vector and {@code other}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param other the vector to measure the distance to
     * @return the distance between this vector and {@code other}
     */
    float distance(Float4R other);

    /**
     * Compute the distance between this vector and ({@code x}, {@code y}, {@code z}, {@code w}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return the distance between this vector and ({@code x}, {@code y}, {@code z}, {@code w})
     */
    float distance(float x, float y, float z, float w);

    /**
     * Compute the squared distance between this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the squared distance between this vector and {@code other}
     */
    float distanceSquared(Float4R other);

    /**
     * Compute the squared distance between this vector and ({@code x}, {@code y}, {@code z},
     * {@code w}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return the squared distance between this vector and ({@code x}, {@code y}, {@code z},
     *        {@code w})
     */
    float distanceSquared(float x, float y, float z, float w);

    /**
     * Compute the dot product of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the dot product
     * @return the dot product of this vector and {@code other}
     */
    float dot(Float4R other);

    /**
     * Compute the dot product of this vector and ({@code x}, {@code y}, {@code z}, {@code w}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return the dot product of this vector and ({@code x}, {@code y}, {@code z}, {@code w})
     */
    float dot(float x, float y, float z, float w);

    /**
     * Compute the base-e exponential of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 exp(@Mutated Float4 dest);

    /**
     * Compute the base-e exponential of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 exp(@Mutated Double4 dest);

    /**
     * Compute the base-2 exponential of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 exp2(@Mutated Float4 dest);

    /**
     * Compute the base-2 exponential of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 exp2(@Mutated Double4 dest);

    /**
     * Compute the base-e exponential minus one of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 expm1(@Mutated Float4 dest);

    /**
     * Compute the base-e exponential minus one of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 expm1(@Mutated Double4 dest);

    /**
     * Return this vector unchanged when {@code dot(Nref, I)} is negative, and negated otherwise -
     * orienting it against the incident direction {@code I} as judged by the reference vector
     * {@code Nref} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param I the incident direction
     * @param Nref the reference vector the incident direction is tested against
     * @param dest will hold the result
     * @return dest
     */
    Float4 faceforward(Float4R I, Float4R Nref, @Mutated Float4 dest);

    /**
     * Return this vector unchanged when {@code dot(Nref, I)} is negative, and negated otherwise -
     * orienting it against the incident direction {@code I} as judged by the reference vector
     * {@code Nref} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param I the incident direction
     * @param Nref the reference vector the incident direction is tested against
     * @param dest will hold the result
     * @return dest
     */
    Double4 faceforward(Float4R I, Float4R Nref, @Mutated Double4 dest);

    /**
     * Return this vector unchanged when {@code dot((NrefX, NrefY, NrefZ, NrefW), (IX, IY, IZ, IW))}
     * is negative, and negated otherwise - orienting it against the incident direction ({@code IX},
     * {@code IY}, {@code IZ}, {@code IW}) as judged by the reference vector ({@code NrefX},
     * {@code NrefY}, {@code NrefZ}, {@code NrefW}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param IX the {@code x} component of the vector {@code (IX, IY, IZ, IW)}
     * @param IY the {@code y} component of the vector {@code (IX, IY, IZ, IW)}
     * @param IZ the {@code z} component of the vector {@code (IX, IY, IZ, IW)}
     * @param IW the {@code w} component of the vector {@code (IX, IY, IZ, IW)}
     * @param NrefX the {@code x} component of the vector {@code (NrefX, NrefY, NrefZ, NrefW)}
     * @param NrefY the {@code y} component of the vector {@code (NrefX, NrefY, NrefZ, NrefW)}
     * @param NrefZ the {@code z} component of the vector {@code (NrefX, NrefY, NrefZ, NrefW)}
     * @param NrefW the {@code w} component of the vector {@code (NrefX, NrefY, NrefZ, NrefW)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 faceforward(float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW, @Mutated Float4 dest);

    /**
     * Return this vector unchanged when {@code dot((NrefX, NrefY, NrefZ, NrefW), (IX, IY, IZ, IW))}
     * is negative, and negated otherwise - orienting it against the incident direction ({@code IX},
     * {@code IY}, {@code IZ}, {@code IW}) as judged by the reference vector ({@code NrefX},
     * {@code NrefY}, {@code NrefZ}, {@code NrefW}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param IX the {@code x} component of the vector {@code (IX, IY, IZ, IW)}
     * @param IY the {@code y} component of the vector {@code (IX, IY, IZ, IW)}
     * @param IZ the {@code z} component of the vector {@code (IX, IY, IZ, IW)}
     * @param IW the {@code w} component of the vector {@code (IX, IY, IZ, IW)}
     * @param NrefX the {@code x} component of the vector {@code (NrefX, NrefY, NrefZ, NrefW)}
     * @param NrefY the {@code y} component of the vector {@code (NrefX, NrefY, NrefZ, NrefW)}
     * @param NrefZ the {@code z} component of the vector {@code (NrefX, NrefY, NrefZ, NrefW)}
     * @param NrefW the {@code w} component of the vector {@code (NrefX, NrefY, NrefZ, NrefW)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 faceforward(float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW, @Mutated Double4 dest);

    /**
     * Compute the floor of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 floor(@Mutated Float4 dest);

    /**
     * Compute the floor of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 floor(@Mutated Double4 dest);

    /**
     * Compute the fractional part of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 fract(@Mutated Float4 dest);

    /**
     * Compute the fractional part of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 fract(@Mutated Double4 dest);

    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} {@code y} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param y the other operand
     * @param dest will hold the result
     * @return dest
     */
    Float4 hypot(float y, @Mutated Float4 dest);

    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} {@code y} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param y the other operand
     * @param dest will hold the result
     * @return dest
     */
    Double4 hypot(float y, @Mutated Double4 dest);

    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} the corresponding component of {@code y} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param y the vector of other operands, one per component
     * @param dest will hold the result
     * @return dest
     */
    Float4 hypot(Float4R y, @Mutated Float4 dest);

    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} the corresponding component of {@code y} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param y the vector of other operands, one per component
     * @param dest will hold the result
     * @return dest
     */
    Double4 hypot(Float4R y, @Mutated Double4 dest);

    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} the corresponding component of ({@code x}, {@code y}, {@code z},
     * {@code w}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 hypot(float x, float y, float z, float w, @Mutated Float4 dest);

    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} the corresponding component of ({@code x}, {@code y}, {@code z},
     * {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 hypot(float x, float y, float z, float w, @Mutated Double4 dest);

    /**
     * Compute the reciprocal {@code 1 / x} of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 inverse(@Mutated Float4 dest);

    /**
     * Compute the reciprocal {@code 1 / x} of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 inverse(@Mutated Double4 dest);

    /**
     * Compute the inverse square root of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 inverseSqrt(@Mutated Float4 dest);

    /**
     * Compute the inverse square root of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 inverseSqrt(@Mutated Double4 dest);

    /**
     * Compute the length of this vector.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the length of this vector
     */
    float length();

    /**
     * Compute the squared length of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this vector
     */
    float lengthSquared();

    /**
     * Compute the natural logarithm of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 log(@Mutated Float4 dest);

    /**
     * Compute the natural logarithm of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 log(@Mutated Double4 dest);

    /**
     * Compute the base-10 logarithm of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 log10(@Mutated Float4 dest);

    /**
     * Compute the base-10 logarithm of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 log10(@Mutated Double4 dest);

    /**
     * Compute the natural logarithm of one plus the value of each component of this vector and
     * store the result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must lie in {@code (-1, Infinity)}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 log1p(@Mutated Float4 dest);

    /**
     * Compute the natural logarithm of one plus the value of each component of this vector and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must lie in {@code (-1, Infinity)}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 log1p(@Mutated Double4 dest);

    /**
     * Compute the base-2 logarithm of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 log2(@Mutated Float4 dest);

    /**
     * Compute the base-2 logarithm of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 log2(@Mutated Double4 dest);

    /**
     * Compute the Manhattan distance between this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the Manhattan distance between this vector and {@code other}
     */
    float manhattanDistance(Float4R other);

    /**
     * Compute the Manhattan distance between this vector and ({@code x}, {@code y}, {@code z},
     * {@code w}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return the Manhattan distance between this vector and ({@code x}, {@code y}, {@code z},
     *        {@code w})
     */
    float manhattanDistance(float x, float y, float z, float w);

    /**
     * Compute the Manhattan length (sum of the absolute components) of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Manhattan length (sum of the absolute components) of this vector
     */
    float manhattanLength();

    /**
     * Set each component of this vector to the larger of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    Float4 max(float scalar, @Mutated Float4 dest);

    /**
     * Set each component of this vector to the larger of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param scalar the value to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    Double4 max(float scalar, @Mutated Double4 dest);

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    Float4 max(Float4R other, @Mutated Float4 dest);

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the vector to take the component-wise maximum with
     * @param dest will hold the result
     * @return dest
     */
    Double4 max(Float4R other, @Mutated Double4 dest);

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code x}, {@code y}, {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 max(float x, float y, float z, float w, @Mutated Float4 dest);

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code x}, {@code y}, {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 max(float x, float y, float z, float w, @Mutated Double4 dest);

    /**
     * Set each component of this vector to the smaller of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    Float4 min(float scalar, @Mutated Float4 dest);

    /**
     * Set each component of this vector to the smaller of itself and {@code scalar} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param scalar the value to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    Double4 min(float scalar, @Mutated Double4 dest);

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    Float4 min(Float4R other, @Mutated Float4 dest);

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the vector to take the component-wise minimum with
     * @param dest will hold the result
     * @return dest
     */
    Double4 min(Float4R other, @Mutated Double4 dest);

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code x}, {@code y}, {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 min(float x, float y, float z, float w, @Mutated Float4 dest);

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code x}, {@code y}, {@code z}, {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 min(float x, float y, float z, float w, @Mutated Double4 dest);

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
     * @param y the divisor
     * @param dest will hold the result
     * @return dest
     */
    Float4 mod(float y, @Mutated Float4 dest);

    /**
     * Compute the component-wise floored modulo of this vector divided by {@code y} ({@code x % y},
     * plus {@code y} when that remainder is non-zero and its sign differs from {@code y}'s -
     * exactly Kotlin's {@code mod}) and store the result in {@code dest}.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code y} must be non-zero.
     *
     * @param y the divisor
     * @param dest will hold the result
     * @return dest
     */
    Double4 mod(float y, @Mutated Double4 dest);

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
     * @param y the vector of divisors, one per component
     * @param dest will hold the result
     * @return dest
     */
    Float4 mod(Float4R y, @Mutated Float4 dest);

    /**
     * Compute the component-wise floored modulo of this vector divided by {@code y} ({@code x % y},
     * plus {@code y} when that remainder is non-zero and its sign differs from {@code y}'s -
     * exactly Kotlin's {@code mod}) and store the result in {@code dest}.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code y} must be non-zero.
     *
     * @param y the vector of divisors, one per component
     * @param dest will hold the result
     * @return dest
     */
    Double4 mod(Float4R y, @Mutated Double4 dest);

    /**
     * Compute the component-wise floored modulo of this vector divided by ({@code x}, {@code y},
     * {@code z}, {@code w}) ({@code x % y}, plus {@code y} when that remainder is non-zero and its
     * sign differs from {@code y}'s - exactly Kotlin's {@code mod}) and store the result in
     * {@code dest}.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 mod(float x, float y, float z, float w, @Mutated Float4 dest);

    /**
     * Compute the component-wise floored modulo of this vector divided by ({@code x}, {@code y},
     * {@code z}, {@code w}) ({@code x % y}, plus {@code y} when that remainder is non-zero and its
     * sign differs from {@code y}'s - exactly Kotlin's {@code mod}) and store the result in
     * {@code dest}.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 mod(float x, float y, float z, float w, @Mutated Double4 dest);

    /**
     * Compute the next representable value toward negative infinity of each component of this
     * vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 nextDown(@Mutated Float4 dest);

    /**
     * Compute the next representable value toward negative infinity of each component of this
     * vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 nextDown(@Mutated Double4 dest);

    /**
     * Compute the next representable value toward positive infinity of each component of this
     * vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 nextUp(@Mutated Float4 dest);

    /**
     * Compute the next representable value toward positive infinity of each component of this
     * vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 nextUp(@Mutated Double4 dest);

    /**
     * Normalize this vector to unit length (the zero vector yields the zero vector) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 normalize(@Mutated Float4 dest);

    /**
     * Normalize this vector to unit length (the zero vector yields the zero vector) and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 normalize(@Mutated Double4 dest);

    /**
     * Normalize this vector and multiply the result by {@code length}, i.e. rescale it to that
     * length (the zero vector yields the zero vector) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param length the length to rescale to
     * @param dest will hold the result
     * @return dest
     */
    Float4 normalizeMul(float length, @Mutated Float4 dest);

    /**
     * Normalize this vector and multiply the result by {@code length}, i.e. rescale it to that
     * length (the zero vector yields the zero vector) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param length the length to rescale to
     * @param dest will hold the result
     * @return dest
     */
    Double4 normalizeMul(float length, @Mutated Double4 dest);

    /**
     * Compute the outer product of this vector and {@code row} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param row the row vector (right operand)
     * @param dest will hold the result
     * @return dest
     */
    Float4x4 outerProduct(Float4R row, @Mutated Float4x4 dest);

    /**
     * Compute the outer product of this vector and {@code row} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param row the row vector (right operand)
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 outerProduct(Float4R row, @Mutated Double4x4 dest);

    /**
     * Compute the outer product of this vector and ({@code x}, {@code y}, {@code z}, {@code w}) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Float4x4 outerProduct(float x, float y, float z, float w, @Mutated Float4x4 dest);

    /**
     * Compute the outer product of this vector and ({@code x}, {@code y}, {@code z}, {@code w}) and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 outerProduct(float x, float y, float z, float w, @Mutated Double4x4 dest);

    /**
     * Raise each component of this vector to the power of {@code exponent} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponent the exponent
     * @param dest will hold the result
     * @return dest
     */
    Float4 pow(float exponent, @Mutated Float4 dest);

    /**
     * Raise each component of this vector to the power of {@code exponent} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponent the exponent
     * @param dest will hold the result
     * @return dest
     */
    Double4 pow(float exponent, @Mutated Double4 dest);

    /**
     * Raise each component of this vector to the power of {@code exponent} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponent the exponent
     * @param dest will hold the result
     * @return dest
     */
    Float4 pow(Float4R exponent, @Mutated Float4 dest);

    /**
     * Raise each component of this vector to the power of {@code exponent} and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponent the exponent
     * @param dest will hold the result
     * @return dest
     */
    Double4 pow(Float4R exponent, @Mutated Double4 dest);

    /**
     * Raise each component of this vector to the power of ({@code x}, {@code y}, {@code z},
     * {@code w}) and store the result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 pow(float x, float y, float z, float w, @Mutated Float4 dest);

    /**
     * Raise each component of this vector to the power of ({@code x}, {@code y}, {@code z},
     * {@code w}) and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 pow(float x, float y, float z, float w, @Mutated Double4 dest);

    /**
     * Project this vector onto {@code onto} and store the result in {@code dest}.
     * <p>
     * Valid input: {@code onto} must be non-zero.
     *
     * @param onto the vector to project onto
     * @param dest will hold the result
     * @return dest
     */
    Float4 project(Float4R onto, @Mutated Float4 dest);

    /**
     * Project this vector onto {@code onto} and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code onto} must be non-zero.
     *
     * @param onto the vector to project onto
     * @param dest will hold the result
     * @return dest
     */
    Double4 project(Float4R onto, @Mutated Double4 dest);

    /**
     * Project this vector onto ({@code x}, {@code y}, {@code z}, {@code w}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 project(float x, float y, float z, float w, @Mutated Float4 dest);

    /**
     * Project this vector onto ({@code x}, {@code y}, {@code z}, {@code w}) and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 project(float x, float y, float z, float w, @Mutated Double4 dest);

    /**
     * Project this vector onto the plane with the given normal and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to project onto
     * @param dest will hold the result
     * @return dest
     */
    Float4 projectOnPlane(Float4R normal, @Mutated Float4 dest);

    /**
     * Project this vector onto the plane with the given normal and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to project onto
     * @param dest will hold the result
     * @return dest
     */
    Double4 projectOnPlane(Float4R normal, @Mutated Double4 dest);

    /**
     * Project this vector onto the plane with the given normal and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 projectOnPlane(float x, float y, float z, float w, @Mutated Float4 dest);

    /**
     * Project this vector onto the plane with the given normal and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 projectOnPlane(float x, float y, float z, float w, @Mutated Double4 dest);

    /**
     * Compute the value converted from degrees to radians of each component of this vector and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 radians(@Mutated Float4 dest);

    /**
     * Compute the value converted from degrees to radians of each component of this vector and
     * store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 radians(@Mutated Double4 dest);

    /**
     * Reflect this vector about the given normal and store the result in {@code dest}.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to reflect about
     * @param dest will hold the result
     * @return dest
     */
    Float4 reflect(Float4R normal, @Mutated Float4 dest);

    /**
     * Reflect this vector about the given normal and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to reflect about
     * @param dest will hold the result
     * @return dest
     */
    Double4 reflect(Float4R normal, @Mutated Double4 dest);

    /**
     * Reflect this vector about the given normal and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 reflect(float x, float y, float z, float w, @Mutated Float4 dest);

    /**
     * Reflect this vector about the given normal and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 reflect(float x, float y, float z, float w, @Mutated Double4 dest);

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
     * @param normal the normal of the refracting surface
     * @param eta the ratio of indices of refraction, i.e. the source medium's divided by the
     *        destination medium's
     * @param dest will hold the result
     * @return dest
     */
    Float4 refract(Float4R normal, float eta, @Mutated Float4 dest);

    /**
     * Refract this vector through the surface with the given normal, using the given ratio of
     * indices of refraction (the zero vector is returned on total internal reflection), and store
     * the result in {@code dest}.
     * <p>
     * As in GLSL, the normal must face against this vector ({@code dot(this, normal) <= 0}): a
     * normal on the far side of the surface bends the vector the wrong way, and with a ratio of 1
     * it comes back reversed. Negate the normal for a vector leaving through the surface.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code normal} must have unit length; this vector must have unit length.
     *
     * @param normal the normal of the refracting surface
     * @param eta the ratio of indices of refraction, i.e. the source medium's divided by the
     *        destination medium's
     * @param dest will hold the result
     * @return dest
     */
    Double4 refract(Float4R normal, float eta, @Mutated Double4 dest);

    /**
     * Refract this vector through the surface with the given normal, using the given ratio of
     * indices of refraction (the zero vector is returned on total internal reflection), and store
     * the result in {@code dest}.
     * <p>
     * As in GLSL, the normal must face against this vector ({@code dot(this, normal) <= 0}): a
     * normal on the far side of the surface bends the vector the wrong way, and with a ratio of 1
     * it comes back reversed. Negate the normal for a vector leaving through the surface.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length; this vector must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param eta the ratio of indices of refraction, i.e. the source medium's divided by the
     *        destination medium's
     * @param dest will hold the result
     * @return dest
     */
    Float4 refract(float x, float y, float z, float w, float eta, @Mutated Float4 dest);

    /**
     * Refract this vector through the surface with the given normal, using the given ratio of
     * indices of refraction (the zero vector is returned on total internal reflection), and store
     * the result in {@code dest}.
     * <p>
     * As in GLSL, the normal must face against this vector ({@code dot(this, normal) <= 0}): a
     * normal on the far side of the surface bends the vector the wrong way, and with a ratio of 1
     * it comes back reversed. Negate the normal for a vector leaving through the surface.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length; this vector must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param eta the ratio of indices of refraction, i.e. the source medium's divided by the
     *        destination medium's
     * @param dest will hold the result
     * @return dest
     */
    Double4 refract(float x, float y, float z, float w, float eta, @Mutated Double4 dest);

    /**
     * Compute the value rounded to the nearest integer, ties to even ({@code Math.rint}) of each
     * component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 round(@Mutated Float4 dest);

    /**
     * Compute the value rounded to the nearest integer, ties to even ({@code Math.rint}) of each
     * component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 round(@Mutated Double4 dest);

    /**
     * Compute the sign of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 sign(@Mutated Float4 dest);

    /**
     * Compute the sign of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 sign(@Mutated Double4 dest);

    /**
     * Compute the sine of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 sin(@Mutated Float4 dest);

    /**
     * Compute the sine of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 sin(@Mutated Double4 dest);

    /**
     * Compute the hyperbolic sine of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 sinh(@Mutated Float4 dest);

    /**
     * Compute the hyperbolic sine of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 sinh(@Mutated Double4 dest);

    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge {@code edge0} and the upper edge {@code edge1}, yielding 0 at or below the lower
     * edge and 1 at or above the upper edge and store the result in {@code dest}.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ.
     *
     * @param edge0 the lower edge
     * @param edge1 the upper edge
     * @param dest will hold the result
     * @return dest
     */
    Float4 smoothstep(float edge0, float edge1, @Mutated Float4 dest);

    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge {@code edge0} and the upper edge {@code edge1}, yielding 0 at or below the lower
     * edge and 1 at or above the upper edge and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ.
     *
     * @param edge0 the lower edge
     * @param edge1 the upper edge
     * @param dest will hold the result
     * @return dest
     */
    Double4 smoothstep(float edge0, float edge1, @Mutated Double4 dest);

    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge {@code edge0} and the upper edge {@code edge1}, yielding 0 at or below the lower
     * edge and 1 at or above the upper edge and store the result in {@code dest}.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ in every component.
     *
     * @param edge0 the lower edge
     * @param edge1 the upper edge
     * @param dest will hold the result
     * @return dest
     */
    Float4 smoothstep(Float4R edge0, Float4R edge1, @Mutated Float4 dest);

    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge {@code edge0} and the upper edge {@code edge1}, yielding 0 at or below the lower
     * edge and 1 at or above the upper edge and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ in every component.
     *
     * @param edge0 the lower edge
     * @param edge1 the upper edge
     * @param dest will hold the result
     * @return dest
     */
    Double4 smoothstep(Float4R edge0, Float4R edge1, @Mutated Double4 dest);

    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge ({@code edge0X}, {@code edge0Y}, {@code edge0Z}, {@code edge0W}) and the upper
     * edge ({@code edge1X}, {@code edge1Y}, {@code edge1Z}, {@code edge1W}), yielding 0 at or below
     * the lower edge and 1 at or above the upper edge and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (edge0X, edge0Y, edge0Z, edge0W)} and
     * {@code (edge1X, edge1Y, edge1Z, edge1W)} must differ in every component.
     *
     * @param edge0X the {@code x} component of the vector {@code (edge0X, edge0Y, edge0Z, edge0W)}
     * @param edge0Y the {@code y} component of the vector {@code (edge0X, edge0Y, edge0Z, edge0W)}
     * @param edge0Z the {@code z} component of the vector {@code (edge0X, edge0Y, edge0Z, edge0W)}
     * @param edge0W the {@code w} component of the vector {@code (edge0X, edge0Y, edge0Z, edge0W)}
     * @param edge1X the {@code x} component of the vector {@code (edge1X, edge1Y, edge1Z, edge1W)}
     * @param edge1Y the {@code y} component of the vector {@code (edge1X, edge1Y, edge1Z, edge1W)}
     * @param edge1Z the {@code z} component of the vector {@code (edge1X, edge1Y, edge1Z, edge1W)}
     * @param edge1W the {@code w} component of the vector {@code (edge1X, edge1Y, edge1Z, edge1W)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 smoothstep(float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W, @Mutated Float4 dest);

    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge ({@code edge0X}, {@code edge0Y}, {@code edge0Z}, {@code edge0W}) and the upper
     * edge ({@code edge1X}, {@code edge1Y}, {@code edge1Z}, {@code edge1W}), yielding 0 at or below
     * the lower edge and 1 at or above the upper edge and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (edge0X, edge0Y, edge0Z, edge0W)} and
     * {@code (edge1X, edge1Y, edge1Z, edge1W)} must differ in every component.
     *
     * @param edge0X the {@code x} component of the vector {@code (edge0X, edge0Y, edge0Z, edge0W)}
     * @param edge0Y the {@code y} component of the vector {@code (edge0X, edge0Y, edge0Z, edge0W)}
     * @param edge0Z the {@code z} component of the vector {@code (edge0X, edge0Y, edge0Z, edge0W)}
     * @param edge0W the {@code w} component of the vector {@code (edge0X, edge0Y, edge0Z, edge0W)}
     * @param edge1X the {@code x} component of the vector {@code (edge1X, edge1Y, edge1Z, edge1W)}
     * @param edge1Y the {@code y} component of the vector {@code (edge1X, edge1Y, edge1Z, edge1W)}
     * @param edge1Z the {@code z} component of the vector {@code (edge1X, edge1Y, edge1Z, edge1W)}
     * @param edge1W the {@code w} component of the vector {@code (edge1X, edge1Y, edge1Z, edge1W)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 smoothstep(float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W, @Mutated Double4 dest);

    /**
     * Compute the square root of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 sqrt(@Mutated Float4 dest);

    /**
     * Compute the square root of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 sqrt(@Mutated Double4 dest);

    /**
     * Set each component of this vector to {@code 0} when it is smaller than {@code edge}, and to
     * {@code 1} otherwise and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edge the edge to compare each component against
     * @param dest will hold the result
     * @return dest
     */
    Float4 step(float edge, @Mutated Float4 dest);

    /**
     * Set each component of this vector to {@code 0} when it is smaller than {@code edge}, and to
     * {@code 1} otherwise and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edge the edge to compare each component against
     * @param dest will hold the result
     * @return dest
     */
    Double4 step(float edge, @Mutated Double4 dest);

    /**
     * Set each component of this vector to {@code 0} when it is smaller than the corresponding
     * component of the given edge, and to {@code 1} otherwise and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edge the edge to compare each component against
     * @param dest will hold the result
     * @return dest
     */
    Float4 step(Float4R edge, @Mutated Float4 dest);

    /**
     * Set each component of this vector to {@code 0} when it is smaller than the corresponding
     * component of the given edge, and to {@code 1} otherwise and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edge the edge to compare each component against
     * @param dest will hold the result
     * @return dest
     */
    Double4 step(Float4R edge, @Mutated Double4 dest);

    /**
     * Set each component of this vector to {@code 0} when it is smaller than the corresponding
     * component of the given edge, and to {@code 1} otherwise and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 step(float x, float y, float z, float w, @Mutated Float4 dest);

    /**
     * Set each component of this vector to {@code 0} when it is smaller than the corresponding
     * component of the given edge, and to {@code 1} otherwise and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 step(float x, float y, float z, float w, @Mutated Double4 dest);

    /**
     * Compute the tangent of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 tan(@Mutated Float4 dest);

    /**
     * Compute the tangent of each component of this vector and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 tan(@Mutated Double4 dest);

    /**
     * Compute the hyperbolic tangent of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 tanh(@Mutated Float4 dest);

    /**
     * Compute the hyperbolic tangent of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 tanh(@Mutated Double4 dest);

    /**
     * Compute the truncated value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 trunc(@Mutated Float4 dest);

    /**
     * Compute the truncated value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 trunc(@Mutated Double4 dest);

    /**
     * Compute the unit in the last place (ulp) of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 ulp(@Mutated Float4 dest);

    /**
     * Compute the unit in the last place (ulp) of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 ulp(@Mutated Double4 dest);

    /**
     * Pre-multiply {@code mat} onto this vector, i.e. compute {@code mat * this} and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    Float4 preMul(Float4x4R mat, @Mutated Float4 dest);

    /**
     * Pre-multiply {@code mat} onto this vector, i.e. compute {@code mat * this} and store the
     * result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    Double4 preMul(Float4x4R mat, @Mutated Double4 dest);

    /**
     * Rotate the {@code (x, y, z)} components of this vector by the quaternion {@code quat}, i.e.
     * compute {@code q * this.xyz * q^-1}, leaving {@code w} unchanged, and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param quat the rotation to apply
     * @param dest will hold the result
     * @return dest
     */
    Float4 rotate(FloatQuatR quat, @Mutated Float4 dest);

    /**
     * Rotate the {@code (x, y, z)} components of this vector by the quaternion {@code quat}, i.e.
     * compute {@code q * this.xyz * q^-1}, leaving {@code w} unchanged, and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param quat the rotation to apply
     * @param dest will hold the result
     * @return dest
     */
    Double4 rotate(FloatQuatR quat, @Mutated Double4 dest);

    /**
     * Rotate the {@code (x, y, z)} components of this vector by the quaternion ({@code x},
     * {@code y}, {@code z}, {@code w}), i.e. compute {@code q * this.xyz * q^-1}, leaving {@code w}
     * unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 rotate(float x, float y, float z, float w, @Mutated Float4 dest);

    /**
     * Rotate the {@code (x, y, z)} components of this vector by the quaternion ({@code x},
     * {@code y}, {@code z}, {@code w}), i.e. compute {@code q * this.xyz * q^-1}, leaving {@code w}
     * unchanged, and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 rotate(float x, float y, float z, float w, @Mutated Double4 dest);

    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the
     * axis {@code axis}, leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    Float4 rotateAxis(float angle, Float3R axis, @Mutated Float4 dest);

    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the
     * axis {@code axis}, leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    Double4 rotateAxis(float angle, Float3R axis, @Mutated Double4 dest);

    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the
     * axis ({@code x}, {@code y}, {@code z}), leaving {@code w} unchanged, and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code (x, y, z)} must have unit length.
     *
     * @param angle the angle in radians
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 rotateAxis(float angle, float x, float y, float z, @Mutated Float4 dest);

    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the
     * axis ({@code x}, {@code y}, {@code z}), leaving {@code w} unchanged, and store the result in
     * {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (x, y, z)} must have unit length.
     *
     * @param angle the angle in radians
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 rotateAxis(float angle, float x, float y, float z, @Mutated Double4 dest);

    /**
     * Rotate the {@code (x, y, z)} components of this vector by the inverse of the given rotation,
     * leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param quat the rotation whose inverse to apply
     * @param dest will hold the result
     * @return dest
     */
    Float4 rotateInverse(FloatQuatR quat, @Mutated Float4 dest);

    /**
     * Rotate the {@code (x, y, z)} components of this vector by the inverse of the given rotation,
     * leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param quat the rotation whose inverse to apply
     * @param dest will hold the result
     * @return dest
     */
    Double4 rotateInverse(FloatQuatR quat, @Mutated Double4 dest);

    /**
     * Rotate the {@code (x, y, z)} components of this vector by the inverse of the given rotation,
     * leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Float4 rotateInverse(float x, float y, float z, float w, @Mutated Float4 dest);

    /**
     * Rotate the {@code (x, y, z)} components of this vector by the inverse of the given rotation,
     * leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @param dest will hold the result
     * @return dest
     */
    Double4 rotateInverse(float x, float y, float z, float w, @Mutated Double4 dest);

    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the X
     * axis, leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    Float4 rotateX(float angle, @Mutated Float4 dest);

    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the X
     * axis, leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    Double4 rotateX(float angle, @Mutated Double4 dest);

    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the Y
     * axis, leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    Float4 rotateY(float angle, @Mutated Float4 dest);

    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the Y
     * axis, leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    Double4 rotateY(float angle, @Mutated Double4 dest);

    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the Z
     * axis, leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    Float4 rotateZ(float angle, @Mutated Float4 dest);

    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the Z
     * axis, leaving {@code w} unchanged, and store the result in {@code dest}.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    Double4 rotateZ(float angle, @Mutated Double4 dest);
    /** {@return the value of the {@code x} component} <p>Valid input: any value, NaN and the infinities included. */
    float x();
    /** {@return the value of the {@code y} component} <p>Valid input: any value, NaN and the infinities included. */
    float y();
    /** {@return the value of the {@code z} component} <p>Valid input: any value, NaN and the infinities included. */
    float z();
    /** {@return the value of the {@code w} component} <p>Valid input: any value, NaN and the infinities included. */
    float w();

    /**
     * Swizzle: copy ({@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 xx(@Mutated Float2 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 xy(@Mutated Float2 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 xz(@Mutated Float2 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 xw(@Mutated Float2 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 yx(@Mutated Float2 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 yy(@Mutated Float2 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 yz(@Mutated Float2 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 yw(@Mutated Float2 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 zx(@Mutated Float2 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 zy(@Mutated Float2 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 zz(@Mutated Float2 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 zw(@Mutated Float2 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 wx(@Mutated Float2 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 wy(@Mutated Float2 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 wz(@Mutated Float2 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 ww(@Mutated Float2 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 xxx(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 xxy(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 xxz(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 xxw(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 xyx(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 xyy(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 xyz(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 xyw(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 xzx(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 xzy(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 xzz(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 xzw(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 xwx(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 xwy(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 xwz(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 xww(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 yxx(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 yxy(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 yxz(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 yxw(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 yyx(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 yyy(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 yyz(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 yyw(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 yzx(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 yzy(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 yzz(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 yzw(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 ywx(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 ywy(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 ywz(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 yww(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 zxx(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 zxy(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 zxz(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 zxw(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 zyx(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 zyy(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 zyz(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 zyw(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 zzx(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 zzy(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 zzz(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 zzw(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 zwx(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 zwy(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 zwz(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 zww(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 wxx(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 wxy(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 wxz(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 wxw(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 wyx(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 wyy(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 wyz(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 wyw(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 wzx(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 wzy(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 wzz(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 wzw(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 wwx(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 wwy(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 wwz(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float3 www(@Mutated Float3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xxxx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xxxy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xxxz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xxxw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xxyx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xxyy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xxyz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xxyw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xxzx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xxzy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xxzz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xxzw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xxwx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xxwy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xxwz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xxww(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xyxx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xyxy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xyxz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xyxw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xyyx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xyyy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xyyz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xyyw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xyzx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xyzy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xyzz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xyzw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xywx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xywy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xywz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xyww(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xzxx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xzxy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xzxz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xzxw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xzyx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xzyy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xzyz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xzyw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xzzx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xzzy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xzzz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xzzw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xzwx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xzwy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xzwz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code z}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xzww(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xwxx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xwxy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xwxz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xwxw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xwyx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xwyy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xwyz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xwyw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xwzx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xwzy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xwzz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xwzw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xwwx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xwwy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xwwz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code w}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 xwww(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yxxx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yxxy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yxxz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yxxw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yxyx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yxyy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yxyz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yxyw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yxzx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yxzy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yxzz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yxzw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yxwx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yxwy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yxwz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yxww(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yyxx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yyxy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yyxz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yyxw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yyyx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yyyy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yyyz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yyyw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yyzx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yyzy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yyzz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yyzw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yywx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yywy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yywz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yyww(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yzxx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yzxy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yzxz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yzxw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yzyx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yzyy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yzyz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yzyw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yzzx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yzzy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yzzz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yzzw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yzwx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yzwy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yzwz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code z}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 yzww(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 ywxx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 ywxy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 ywxz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 ywxw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 ywyx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 ywyy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 ywyz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 ywyw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 ywzx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 ywzy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 ywzz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 ywzw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 ywwx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 ywwy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 ywwz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code w}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 ywww(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zxxx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zxxy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zxxz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zxxw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zxyx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zxyy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zxyz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zxyw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zxzx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zxzy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zxzz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zxzw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zxwx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zxwy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zxwz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code x}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zxww(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zyxx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zyxy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zyxz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zyxw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zyyx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zyyy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zyyz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zyyw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zyzx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zyzy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zyzz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zyzw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zywx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zywy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zywz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code y}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zyww(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zzxx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zzxy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zzxz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zzxw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zzyx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zzyy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zzyz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zzyw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zzzx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zzzy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zzzz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zzzw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zzwx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zzwy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zzwz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code z}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zzww(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zwxx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zwxy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zwxz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zwxw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zwyx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zwyy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zwyz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zwyw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zwzx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zwzy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zwzz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zwzw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zwwx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zwwy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zwwz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code z}, {@code w}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 zwww(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wxxx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wxxy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wxxz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wxxw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wxyx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wxyy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wxyz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wxyw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wxzx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wxzy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wxzz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wxzw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wxwx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wxwy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wxwz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code x}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wxww(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wyxx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wyxy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wyxz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wyxw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wyyx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wyyy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wyyz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wyyw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wyzx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wyzy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wyzz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wyzw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wywx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wywy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wywz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code y}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wyww(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wzxx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wzxy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wzxz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wzxw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wzyx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wzyy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wzyz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wzyw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wzzx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wzzy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wzzz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wzzw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wzwx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wzwy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wzwz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code z}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wzww(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wwxx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wwxy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code x}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wwxz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code x}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wwxw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wwyx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wwyy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code y}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wwyz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code y}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wwyw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code z}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wwzx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code z}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wwzy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code z}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wwzz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code z}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wwzw(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code w}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wwwx(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code w}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wwwy(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code w}, {@code z}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wwwz(@Mutated Float4 dest);

    /**
     * Swizzle: copy ({@code w}, {@code w}, {@code w}, {@code w}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4 wwww(@Mutated Float4 dest);

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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default FloatBuffer storeRelative(@Mutated FloatBuffer dest) {
        if (dest.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 4);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer, starting at its current position (the position
     * is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeRelative(@Mutated ByteBuffer dest) {
        if (dest.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 16);
        return dest;
    }

    /**
     * Store the elements into the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    Float4 storeUnsafe(long address);

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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default DoubleBuffer storeRelative(@Mutated DoubleBuffer dest) {
        if (dest.remaining() < 4) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 4);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code double},
     * starting at its current position (the position is not modified).
     * <p>
     * A buffer in native byte order takes the fast path; any other byte order is honoured through
     * the slower API path.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeDoubleRelative(@Mutated ByteBuffer dest) {
        if (dest.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeDoubleAbsolute(pos, dest);
        dest.position(pos + 32);
        return dest;
    }

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    Float4 storeDoubleUnsafe(long address);

    /** {@return whether all components of this vector are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    boolean isFinite();

    /** {@return whether any component of this vector is NaN} <p>Valid input: any value, NaN and the infinities included. */
    boolean isNaN();

    /**
     * Compare this vector component-wise against {@code other}, allowing a difference
     * of at most {@code epsilon} per component.
     * <p>
     * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
     * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and
     * a NaN component never compares equal to anything.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the vector to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    boolean equalsEpsilon(Float4R other, float epsilon);

    /**
     * Compare this vector with the given object for element-wise equality.
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
     * @return {@code true} if {@code obj} is a vector of this library with element-wise equal
     *        components, {@code false} otherwise
     */
    boolean equals(@org.jspecify.annotations.Nullable Object obj);

    /**
     * Compute a hash code consistent with {@link #equals}: it is derived from the components via
     * {@code Float.floatToIntBits} alone.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return the hash code of this vector
     */
    int hashCode();

    /** Backs {@code ZERO()}: defers the shared instance's
     *  allocation to first use, avoiding a class-initialization cycle with the
     *  implementation class. Not part of the public API. */
    final class Holder {
        private Holder() {}
        static final Float4R ZERO = Joml.float4(0, 0, 0, 0);
    }
}
