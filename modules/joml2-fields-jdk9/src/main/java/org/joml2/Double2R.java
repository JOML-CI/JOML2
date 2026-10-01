// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.ByteBuffer;

/**
 * Read-only view of a 2D vector of double-precision {@code double} components.
 * <p>
 * Declares the accessors, queries, store methods and dest-form operations that read but never
 * mutate the receiver; the mutable counterpart is {@link Double2}. APIs that only read a 2D vector
 * should accept {@code Double2R}, so callers can pass mutable instances without exposing them to
 * modification.
 * <p>
 * Arguments of type {@code Double2R} must be instances created by the library ({@link Joml}
 * factories / the library's own types); the implementations read cached state through the library's
 * own classes, so foreign implementations of the {@code *R} interfaces are not supported as
 * arguments.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Double.doubleToLongBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN. {@code hashCode} is consistent with it (derived from the same bit patterns). Only instances
 * of this library's implementation compare equal to each other; the {@code equals} of a vector
 * never returns {@code true} for an object of another type.
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
 */
public interface Double2R {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 16;

    /** {@return a shared all-zero vector} Never mutate it - it is returned as the read-only view for that reason. <p>Valid input: any value, NaN and the infinities included. */
    public static Double2R ZERO() { return Holder.ZERO; }


    /**
     * Add {@code other} to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @param dest will hold the result
     * @return dest
     */
    Double2 add(Double2R other, @Mutated Double2 dest);

    /**
     * Add ({@code x}, {@code y}) to this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 add(double x, double y, @Mutated Double2 dest);

    /**
     * Divide each component of this vector by {@code scalar} and store the result in {@code dest}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @param dest will hold the result
     * @return dest
     */
    Double2 div(double scalar, @Mutated Double2 dest);

    /**
     * Divide this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @param dest will hold the result
     * @return dest
     */
    Double2 div(Double2R other, @Mutated Double2 dest);

    /**
     * Divide this vector component-wise by ({@code x}, {@code y}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of {@code (x, y)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 div(double x, double y, @Mutated Double2 dest);

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
    Double2 fma(double b, Double2R c, @Mutated Double2 dest);

    /**
     * Multiply this vector component-wise by {@code b} and add ({@code x}, {@code y}), i.e. compute
     * {@code this * b + (x, y)} per component and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 fma(double b, double x, double y, @Mutated Double2 dest);

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
    Double2 fma(Double2R b, Double2R c, @Mutated Double2 dest);

    /**
     * Multiply this vector component-wise by ({@code bX}, {@code bY}) and add ({@code cX},
     * {@code cY}), i.e. compute {@code this * (bX, bY) + (cX, cY)} per component and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY)}
     * @param bY the {@code y} component of the vector {@code (bX, bY)}
     * @param cX the {@code x} component of the vector {@code (cX, cY)}
     * @param cY the {@code y} component of the vector {@code (cX, cY)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 fma(double bX, double bY, double cX, double cY, @Mutated Double2 dest);

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
    Double2 mul(double scalar, @Mutated Double2 dest);

    /**
     * Multiply this vector component-wise by {@code other} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @param dest will hold the result
     * @return dest
     */
    Double2 mul(Double2R other, @Mutated Double2 dest);

    /**
     * Multiply this vector component-wise by ({@code x}, {@code y}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 mul(double x, double y, @Mutated Double2 dest);

    /**
     * Negate this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 negate(@Mutated Double2 dest);

    /**
     * Subtract {@code other} from this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @param dest will hold the result
     * @return dest
     */
    Double2 sub(Double2R other, @Mutated Double2 dest);

    /**
     * Subtract ({@code x}, {@code y}) from this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 sub(double x, double y, @Mutated Double2 dest);

    /**
     * Set this vector to {@code s} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @param dest will hold the result
     * @return dest
     */
    Double2 set(double s, @Mutated Double2 dest);

    /**
     * Convert this vector to {@code float} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float2 toFloat(@Mutated Float2 dest);

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
    Byte2 toByte(@Mutated Byte2 dest);

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
    Byte2 toByte(RoundingMode mode, @Mutated Byte2 dest);

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
    Short2 toShort(@Mutated Short2 dest);

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
    Short2 toShort(RoundingMode mode, @Mutated Short2 dest);

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
    Int2 toInt(@Mutated Int2 dest);

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
    Int2 toInt(RoundingMode mode, @Mutated Int2 dest);

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
    Long2 toLong(@Mutated Long2 dest);

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
    Long2 toLong(RoundingMode mode, @Mutated Long2 dest);

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
    Double2 bezier(Double2R p1, Double2R p2, Double2R p3, double t, @Mutated Double2 dest);

    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points ({@code p1X}, {@code p1Y}) and ({@code p2X}, {@code p2Y}) and ends at ({@code p3X},
     * {@code p3Y}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p3X}, {@code p3Y})
     * at {@code t = 1}; the control points ({@code p1X}, {@code p1Y}) and ({@code p2X},
     * {@code p2Y}) pull it towards themselves but are generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double2 bezier(double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t, @Mutated Double2 dest);

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
    Double2 bezier2(Double2R p1, Double2R p2, double t, @Mutated Double2 dest);

    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point ({@code p1X}, {@code p1Y}) and ends at ({@code p2X}, {@code p2Y}) and store the
     * result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p2X}, {@code p2Y})
     * at {@code t = 1}; the control point ({@code p1X}, {@code p1Y}) pulls it towards itself but is
     * generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double2 bezier2(double p1X, double p1Y, double p2X, double p2Y, double t, @Mutated Double2 dest);

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
    Double2 bezier2Tangent(Double2R p1, Double2R p2, double t, @Mutated Double2 dest);

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point
     * ({@code p1X}, {@code p1Y}) and ends at ({@code p2X}, {@code p2Y}) and store the result in
     * {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p2X}, {@code p2Y})
     * at {@code t = 1}; the control point ({@code p1X}, {@code p1Y}) pulls it towards itself but is
     * generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double2 bezier2Tangent(double p1X, double p1Y, double p2X, double p2Y, double t, @Mutated Double2 dest);

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
    Double2 bezierTangent(Double2R p1, Double2R p2, Double2R p3, double t, @Mutated Double2 dest);

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points ({@code p1X},
     * {@code p1Y}) and ({@code p2X}, {@code p2Y}) and ends at ({@code p3X}, {@code p3Y}) and store
     * the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code p3X}, {@code p3Y})
     * at {@code t = 1}; the control points ({@code p1X}, {@code p1Y}) and ({@code p2X},
     * {@code p2Y}) pull it towards themselves but are generally not on the curve.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double2 bezierTangent(double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t, @Mutated Double2 dest);

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
    Double2 catmullRom(Double2R p1, Double2R p2, Double2R p3, double t, @Mutated Double2 dest);

    /**
     * Interpolate along the Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}) to
     * ({@code p2X}, {@code p2Y}), with this vector as the control point before the segment and
     * ({@code p3X}, {@code p3Y}) as the control point after it and store the result in
     * {@code dest}.
     * <p>
     * The curve passes through ({@code p1X}, {@code p1Y}) at {@code t = 0} and through
     * ({@code p2X}, {@code p2Y}) at {@code t = 1}. This vector and ({@code p3X}, {@code p3Y}) are
     * the spline's neighbouring points, i.e. the point before ({@code p1X}, {@code p1Y}) and the
     * point after ({@code p2X}, {@code p2Y}): they only shape the tangents at the segment's two end
     * points and are not themselves on the segment. For a spline through the points
     * {@code p[0..n]}, the segment from {@code p[i]} to {@code p[i+1]} is therefore interpolated
     * with {@code p[i-1]} in the role of this vector and {@code p[i]}, {@code p[i+1]},
     * {@code p[i+2]} as the three given points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double2 catmullRom(double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t, @Mutated Double2 dest);

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
    Double2 catmullRomTangent(Double2R p1, Double2R p2, Double2R p3, double t, @Mutated Double2 dest);

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}) to ({@code p2X}, {@code p2Y}),
     * with this vector as the control point before the segment and ({@code p3X}, {@code p3Y}) as
     * the control point after it and store the result in {@code dest}.
     * <p>
     * The curve passes through ({@code p1X}, {@code p1Y}) at {@code t = 0} and through
     * ({@code p2X}, {@code p2Y}) at {@code t = 1}. This vector and ({@code p3X}, {@code p3Y}) are
     * the spline's neighbouring points, i.e. the point before ({@code p1X}, {@code p1Y}) and the
     * point after ({@code p2X}, {@code p2Y}): they only shape the tangents at the segment's two end
     * points and are not themselves on the segment. For a spline through the points
     * {@code p[0..n]}, the segment from {@code p[i]} to {@code p[i+1]} is therefore interpolated
     * with {@code p[i-1]} in the role of this vector and {@code p[i]}, {@code p[i+1]},
     * {@code p[i+2]} as the three given points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double2 catmullRomTangent(double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, double t, @Mutated Double2 dest);

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
    Double2 hermite(Double2R t0, Double2R v1, Double2R t1, double t, @Mutated Double2 dest);

    /**
     * Interpolate along the cubic Hermite curve that starts at this vector with the tangent
     * ({@code t0X}, {@code t0Y}) and ends at ({@code v1X}, {@code v1Y}) with the tangent
     * ({@code t1X}, {@code t1Y}) and store the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code v1X}, {@code v1Y})
     * at {@code t = 1}; the two tangents set its direction and speed at those end points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t0X the {@code x} component of the vector {@code (t0X, t0Y)}
     * @param t0Y the {@code y} component of the vector {@code (t0X, t0Y)}
     * @param v1X the {@code x} component of the vector {@code (v1X, v1Y)}
     * @param v1Y the {@code y} component of the vector {@code (v1X, v1Y)}
     * @param t1X the {@code x} component of the vector {@code (t1X, t1Y)}
     * @param t1Y the {@code y} component of the vector {@code (t1X, t1Y)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double2 hermite(double t0X, double t0Y, double v1X, double v1Y, double t1X, double t1Y, double t, @Mutated Double2 dest);

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
    Double2 hermiteTangent(Double2R t0, Double2R v1, Double2R t1, double t, @Mutated Double2 dest);

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Hermite curve that starts at this vector with the tangent ({@code t0X}, {@code t0Y})
     * and ends at ({@code v1X}, {@code v1Y}) with the tangent ({@code t1X}, {@code t1Y}) and store
     * the result in {@code dest}.
     * <p>
     * The curve passes through this vector at {@code t = 0} and through ({@code v1X}, {@code v1Y})
     * at {@code t = 1}; the two tangents set its direction and speed at those end points.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t0X the {@code x} component of the vector {@code (t0X, t0Y)}
     * @param t0Y the {@code y} component of the vector {@code (t0X, t0Y)}
     * @param v1X the {@code x} component of the vector {@code (v1X, v1Y)}
     * @param v1Y the {@code y} component of the vector {@code (v1X, v1Y)}
     * @param t1X the {@code x} component of the vector {@code (t1X, t1Y)}
     * @param t1Y the {@code y} component of the vector {@code (t1X, t1Y)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double2 hermiteTangent(double t0X, double t0Y, double v1X, double v1Y, double t1X, double t1Y, double t, @Mutated Double2 dest);

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
    Double2 lerp(Double2R other, double t, @Mutated Double2 dest);

    /**
     * Linearly interpolate between this vector and ({@code x}, {@code y}) using the interpolation
     * factor {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code x}, {@code y}) (interpolation factor {@code 1}). Each linearly interpolated component
     * is {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in {@code t} and
     * exact at {@code 0}, but at {@code 1} exact only up to the rounding of {@code other - this},
     * which shows when this component is much larger in magnitude than the other one (in
     * {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double2 lerp(double x, double y, double t, @Mutated Double2 dest);

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
    Double2 lerp(Double2R other, Double2R t, @Mutated Double2 dest);

    /**
     * Linearly interpolate between this vector and ({@code otherX}, {@code otherY}) using the
     * interpolation factor ({@code tX}, {@code tY}) and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code otherX}, {@code otherY}) (interpolation factor {@code 1}). Each linearly interpolated
     * component is {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in
     * {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the rounding of
     * {@code other - this}, which shows when this component is much larger in magnitude than the
     * other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param otherX the {@code x} component of the vector {@code (otherX, otherY)}
     * @param otherY the {@code y} component of the vector {@code (otherX, otherY)}
     * @param tX the {@code x} component of the vector {@code (tX, tY)}
     * @param tY the {@code y} component of the vector {@code (tX, tY)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 lerp(double otherX, double otherY, double tX, double tY, @Mutated Double2 dest);

    /**
     * Spherically interpolate between this vector and {@code other} using the interpolation factor
     * {@code t}: the direction turns at a constant rate along the shorter arc between the two
     * directions, and the length changes linearly between the two lengths and store the result in
     * {@code dest}.
     * <p>
     * For unit vectors this is the usual {@code slerp} of directions. A zero vector has no
     * direction, so the result is then the linear interpolation; for two vectors pointing in
     * opposite directions, whose arc lies in no particular plane, the direction turns through the
     * counter-clockwise perpendicular {@code (-y, x)} of this vector. The angle is computed with
     * {@code atan2}, and vectors of any finite length are handled: when their squared lengths leave
     * the {@code double} range, they are first scaled exactly by powers of two.
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
    Double2 slerp(Double2R other, double t, @Mutated Double2 dest);

    /**
     * Spherically interpolate between this vector and ({@code x}, {@code y}) using the
     * interpolation factor {@code t}: the direction turns at a constant rate along the shorter arc
     * between the two directions, and the length changes linearly between the two lengths and store
     * the result in {@code dest}.
     * <p>
     * For unit vectors this is the usual {@code slerp} of directions. A zero vector has no
     * direction, so the result is then the linear interpolation; for two vectors pointing in
     * opposite directions, whose arc lies in no particular plane, the direction turns through the
     * counter-clockwise perpendicular {@code (-y, x)} of this vector. The angle is computed with
     * {@code atan2}, and vectors of any finite length are handled: when their squared lengths leave
     * the {@code double} range, they are first scaled exactly by powers of two.
     * <p>
     * The interpolation starts at this vector (interpolation factor {@code 0}) and ends at
     * ({@code x}, {@code y}) (interpolation factor {@code 1}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    Double2 slerp(double x, double y, double t, @Mutated Double2 dest);

    /**
     * Compute the absolute value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 absolute(@Mutated Double2 dest);

    /**
     * Compute the arc cosine of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 acos(@Mutated Double2 dest);

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
    Double2 addScaled(Double2R b, double scalar, @Mutated Double2 dest);

    /**
     * Add ({@code x}, {@code y}) scaled by {@code scalar} to this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param scalar the factor to scale ({@code x}, {@code y}) by before adding
     * @param dest will hold the result
     * @return dest
     */
    Double2 addScaled(double x, double y, double scalar, @Mutated Double2 dest);

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
    Double2 addScaled(Double2R b, Double2R c, @Mutated Double2 dest);

    /**
     * Add ({@code bX}, {@code bY}) scaled by ({@code cX}, {@code cY}) to this vector and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param bX the {@code x} component of the vector {@code (bX, bY)}
     * @param bY the {@code y} component of the vector {@code (bX, bY)}
     * @param cX the {@code x} component of the vector {@code (cX, cY)}
     * @param cY the {@code y} component of the vector {@code (cX, cY)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 addScaled(double bX, double bY, double cX, double cY, @Mutated Double2 dest);

    /**
     * Compute the angle in radians between this vector and {@code other}.
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when their cross product would leave the {@code double} range,
     * the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the angle to
     * @return the angle in radians between this vector and {@code other}
     */
    double angleBetween(Double2R other);

    /**
     * Compute the angle in radians between this vector and ({@code x}, {@code y}).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when their cross product would leave the {@code double} range,
     * the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @return the angle in radians between this vector and ({@code x}, {@code y})
     */
    double angleBetween(double x, double y);

    /**
     * Compute the arc sine of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 asin(@Mutated Double2 dest);

    /**
     * Compute the arc tangent of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 atan(@Mutated Double2 dest);

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
    Double2 atan2(double x, @Mutated Double2 dest);

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
    Double2 atan2(Double2R x, @Mutated Double2 dest);

    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} the corresponding component of ({@code x},
     * {@code y}) (the denominator) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 atan2(double x, double y, @Mutated Double2 dest);

    /**
     * Compute the cube root of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 cbrt(@Mutated Double2 dest);

    /**
     * Compute the ceiling of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 ceil(@Mutated Double2 dest);

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
    Double2 clamp(double min, double max, @Mutated Double2 dest);

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
    Double2 clamp(Double2R min, Double2R max, @Mutated Double2 dest);

    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}) and ({@code maxX},
     * {@code maxY}) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (minX, minY)} must not exceed {@code (maxX, maxY)} in any component.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY)}
     * @param minY the {@code y} component of the vector {@code (minX, minY)}
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 clamp(double minX, double minY, double maxX, double maxY, @Mutated Double2 dest);

    /**
     * Compute the sum of all components of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the sum of all components of this vector
     */
    double compAdd();

    /**
     * Compute the largest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the largest component of this vector
     */
    double compMax();

    /**
     * Compute the smallest component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the smallest component of this vector
     */
    double compMin();

    /**
     * Compute the product of all components of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the product of all components of this vector
     */
    double compMul();

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
    Double2 copySign(double sign, @Mutated Double2 dest);

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
    Double2 copySign(Double2R sign, @Mutated Double2 dest);

    /**
     * Copy the sign of each component of ({@code x}, {@code y}) onto the corresponding component of
     * this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 copySign(double x, double y, @Mutated Double2 dest);

    /**
     * Compute the cosine of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 cos(@Mutated Double2 dest);

    /**
     * Compute the hyperbolic cosine of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 cosh(@Mutated Double2 dest);

    /**
     * Compute the 2D cross product of this vector and {@code other}, in that order.
     * <p>
     * It is the z component of the cross product of the two vectors extended by {@code z = 0}, i.e.
     * the signed area of the parallelogram they span: positive when {@code other} points
     * counter-clockwise of this vector (with the x axis pointing right and the y axis pointing up).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the right operand of the cross product
     * @return the 2D cross product of this vector and {@code other}, in that order
     */
    double cross(Double2R other);

    /**
     * Compute the 2D cross product of this vector and ({@code x}, {@code y}), in that order.
     * <p>
     * It is the z component of the cross product of the two vectors extended by {@code z = 0}, i.e.
     * the signed area of the parallelogram they span: positive when ({@code x}, {@code y}) points
     * counter-clockwise of this vector (with the x axis pointing right and the y axis pointing up).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @return the 2D cross product of this vector and ({@code x}, {@code y}), in that order
     */
    double cross(double x, double y);

    /**
     * Compute the value converted from radians to degrees of each component of this vector and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 degrees(@Mutated Double2 dest);

    /**
     * Compute the distance between this vector and {@code other}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param other the vector to measure the distance to
     * @return the distance between this vector and {@code other}
     */
    double distance(Double2R other);

    /**
     * Compute the distance between this vector and ({@code x}, {@code y}).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @return the distance between this vector and ({@code x}, {@code y})
     */
    double distance(double x, double y);

    /**
     * Compute the squared distance between this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the squared distance between this vector and {@code other}
     */
    double distanceSquared(Double2R other);

    /**
     * Compute the squared distance between this vector and ({@code x}, {@code y}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @return the squared distance between this vector and ({@code x}, {@code y})
     */
    double distanceSquared(double x, double y);

    /**
     * Compute the dot product of this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the other operand of the dot product
     * @return the dot product of this vector and {@code other}
     */
    double dot(Double2R other);

    /**
     * Compute the dot product of this vector and ({@code x}, {@code y}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @return the dot product of this vector and ({@code x}, {@code y})
     */
    double dot(double x, double y);

    /**
     * Compute the base-e exponential of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 exp(@Mutated Double2 dest);

    /**
     * Compute the base-2 exponential of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 exp2(@Mutated Double2 dest);

    /**
     * Compute the base-e exponential minus one of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 expm1(@Mutated Double2 dest);

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
    Double2 faceforward(Double2R I, Double2R Nref, @Mutated Double2 dest);

    /**
     * Return this vector unchanged when {@code dot((NrefX, NrefY), (IX, IY))} is negative, and
     * negated otherwise - orienting it against the incident direction ({@code IX}, {@code IY}) as
     * judged by the reference vector ({@code NrefX}, {@code NrefY}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param IX the {@code x} component of the vector {@code (IX, IY)}
     * @param IY the {@code y} component of the vector {@code (IX, IY)}
     * @param NrefX the {@code x} component of the vector {@code (NrefX, NrefY)}
     * @param NrefY the {@code y} component of the vector {@code (NrefX, NrefY)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 faceforward(double IX, double IY, double NrefX, double NrefY, @Mutated Double2 dest);

    /**
     * Compute the floor of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 floor(@Mutated Double2 dest);

    /**
     * Compute the fractional part of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 fract(@Mutated Double2 dest);

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
    Double2 hypot(double y, @Mutated Double2 dest);

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
    Double2 hypot(Double2R y, @Mutated Double2 dest);

    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} the corresponding component of ({@code x}, {@code y}) and store
     * the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 hypot(double x, double y, @Mutated Double2 dest);

    /**
     * Compute the reciprocal {@code 1 / x} of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must be non-zero.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 inverse(@Mutated Double2 dest);

    /**
     * Compute the inverse square root of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 inverseSqrt(@Mutated Double2 dest);

    /**
     * Compute the length of this vector.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the length of this vector
     */
    double length();

    /**
     * Compute the squared length of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this vector
     */
    double lengthSquared();

    /**
     * Compute the natural logarithm of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 log(@Mutated Double2 dest);

    /**
     * Compute the base-10 logarithm of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 log10(@Mutated Double2 dest);

    /**
     * Compute the natural logarithm of one plus the value of each component of this vector and
     * store the result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must lie in {@code (-1, Infinity)}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 log1p(@Mutated Double2 dest);

    /**
     * Compute the base-2 logarithm of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 log2(@Mutated Double2 dest);

    /**
     * Compute the Manhattan distance between this vector and {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the distance to
     * @return the Manhattan distance between this vector and {@code other}
     */
    double manhattanDistance(Double2R other);

    /**
     * Compute the Manhattan distance between this vector and ({@code x}, {@code y}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @return the Manhattan distance between this vector and ({@code x}, {@code y})
     */
    double manhattanDistance(double x, double y);

    /**
     * Compute the Manhattan length (sum of the absolute components) of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Manhattan length (sum of the absolute components) of this vector
     */
    double manhattanLength();

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
    Double2 max(double scalar, @Mutated Double2 dest);

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
    Double2 max(Double2R other, @Mutated Double2 dest);

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code x}, {@code y}) and store the result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 max(double x, double y, @Mutated Double2 dest);

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
    Double2 min(double scalar, @Mutated Double2 dest);

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
    Double2 min(Double2R other, @Mutated Double2 dest);

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code x}, {@code y}) and store the result in {@code dest}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 min(double x, double y, @Mutated Double2 dest);

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
    Double2 mod(double y, @Mutated Double2 dest);

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
    Double2 mod(Double2R y, @Mutated Double2 dest);

    /**
     * Compute the component-wise floored modulo of this vector divided by ({@code x}, {@code y})
     * ({@code x % y}, plus {@code y} when that remainder is non-zero and its sign differs from
     * {@code y}'s - exactly Kotlin's {@code mod}) and store the result in {@code dest}.
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * Valid input: each component of {@code (x, y)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 mod(double x, double y, @Mutated Double2 dest);

    /**
     * Compute the next representable value toward negative infinity of each component of this
     * vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 nextDown(@Mutated Double2 dest);

    /**
     * Compute the next representable value toward positive infinity of each component of this
     * vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 nextUp(@Mutated Double2 dest);

    /**
     * Normalize this vector to unit length (the zero vector yields the zero vector) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 normalize(@Mutated Double2 dest);

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
    Double2 normalizeMul(double length, @Mutated Double2 dest);

    /**
     * Compute the signed angle in radians between this vector and {@code other}, positive when the
     * rotation from this vector to {@code other} is counter-clockwise (with the x axis pointing
     * right and the y axis pointing up).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when their cross product would leave the {@code double} range,
     * the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to measure the signed angle to
     * @return the signed angle in radians between this vector and {@code other}, positive when the
     *        rotation from this vector to {@code other} is counter-clockwise (with the x axis
     *        pointing right and the y axis pointing up)
     */
    double orientedAngle(Double2R other);

    /**
     * Compute the signed angle in radians between this vector and ({@code x}, {@code y}), positive
     * when the rotation from this vector to ({@code x}, {@code y}) is counter-clockwise (with the x
     * axis pointing right and the y axis pointing up).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code double} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles). It holds for
     * vectors of any finite length: when their cross product would leave the {@code double} range,
     * the vectors are first scaled exactly by powers of two.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @return the signed angle in radians between this vector and ({@code x}, {@code y}), positive
     *        when the rotation from this vector to ({@code x}, {@code y}) is counter-clockwise
     *        (with the x axis pointing right and the y axis pointing up)
     */
    double orientedAngle(double x, double y);

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
    Double2x2 outerProduct(Double2R row, @Mutated Double2x2 dest);

    /**
     * Compute the outer product of this vector and ({@code x}, {@code y}) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Double2x2 outerProduct(double x, double y, @Mutated Double2x2 dest);

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
    Double2 pow(double exponent, @Mutated Double2 dest);

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
    Double2 pow(Double2R exponent, @Mutated Double2 dest);

    /**
     * Raise each component of this vector to the power of ({@code x}, {@code y}) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 pow(double x, double y, @Mutated Double2 dest);

    /**
     * Project this vector onto {@code onto} and store the result in {@code dest}.
     * <p>
     * Valid input: {@code onto} must be non-zero.
     *
     * @param onto the vector to project onto
     * @param dest will hold the result
     * @return dest
     */
    Double2 project(Double2R onto, @Mutated Double2 dest);

    /**
     * Project this vector onto ({@code x}, {@code y}) and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (x, y)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 project(double x, double y, @Mutated Double2 dest);

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
    Double2 projectOnPlane(Double2R normal, @Mutated Double2 dest);

    /**
     * Project this vector onto the plane with the given normal and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code (x, y)} must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 projectOnPlane(double x, double y, @Mutated Double2 dest);

    /**
     * Compute the value converted from degrees to radians of each component of this vector and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 radians(@Mutated Double2 dest);

    /**
     * Reflect this vector about the given normal and store the result in {@code dest}.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to reflect about
     * @param dest will hold the result
     * @return dest
     */
    Double2 reflect(Double2R normal, @Mutated Double2 dest);

    /**
     * Reflect this vector about the given normal and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (x, y)} must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 reflect(double x, double y, @Mutated Double2 dest);

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
    Double2 refract(Double2R normal, double eta, @Mutated Double2 dest);

    /**
     * Refract this vector through the surface with the given normal, using the given ratio of
     * indices of refraction (the zero vector is returned on total internal reflection), and store
     * the result in {@code dest}.
     * <p>
     * As in GLSL, the normal must face against this vector ({@code dot(this, normal) <= 0}): a
     * normal on the far side of the surface bends the vector the wrong way, and with a ratio of 1
     * it comes back reversed. Negate the normal for a vector leaving through the surface.
     * <p>
     * Valid input: {@code (x, y)} must have unit length; this vector must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param eta the ratio of indices of refraction, i.e. the source medium's divided by the
     *        destination medium's
     * @param dest will hold the result
     * @return dest
     */
    Double2 refract(double x, double y, double eta, @Mutated Double2 dest);

    /**
     * Compute the value rounded to the nearest integer, ties to even ({@code Math.rint}) of each
     * component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 round(@Mutated Double2 dest);

    /**
     * Compute the sign of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 sign(@Mutated Double2 dest);

    /**
     * Compute the sine of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 sin(@Mutated Double2 dest);

    /**
     * Compute the hyperbolic sine of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 sinh(@Mutated Double2 dest);

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
    Double2 smoothstep(double edge0, double edge1, @Mutated Double2 dest);

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
    Double2 smoothstep(Double2R edge0, Double2R edge1, @Mutated Double2 dest);

    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge ({@code edge0X}, {@code edge0Y}) and the upper edge ({@code edge1X},
     * {@code edge1Y}), yielding 0 at or below the lower edge and 1 at or above the upper edge and
     * store the result in {@code dest}.
     * <p>
     * Valid input: {@code (edge0X, edge0Y)} and {@code (edge1X, edge1Y)} must differ in every
     * component.
     *
     * @param edge0X the {@code x} component of the vector {@code (edge0X, edge0Y)}
     * @param edge0Y the {@code y} component of the vector {@code (edge0X, edge0Y)}
     * @param edge1X the {@code x} component of the vector {@code (edge1X, edge1Y)}
     * @param edge1Y the {@code y} component of the vector {@code (edge1X, edge1Y)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 smoothstep(double edge0X, double edge0Y, double edge1X, double edge1Y, @Mutated Double2 dest);

    /**
     * Compute the square root of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 sqrt(@Mutated Double2 dest);

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
    Double2 step(double edge, @Mutated Double2 dest);

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
    Double2 step(Double2R edge, @Mutated Double2 dest);

    /**
     * Set each component of this vector to {@code 0} when it is smaller than the corresponding
     * component of the given edge, and to {@code 1} otherwise and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 step(double x, double y, @Mutated Double2 dest);

    /**
     * Compute the tangent of each component of this vector and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 tan(@Mutated Double2 dest);

    /**
     * Compute the hyperbolic tangent of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 tanh(@Mutated Double2 dest);

    /**
     * Compute the truncated value of each component of this vector and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 trunc(@Mutated Double2 dest);

    /**
     * Compute the unit in the last place (ulp) of each component of this vector and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 ulp(@Mutated Double2 dest);

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
    Double2 preMul(Double2x2R mat, @Mutated Double2 dest);

    /**
     * Pre-multiply {@code mat} onto this vector, treated as a direction with implicit {@code w = 0}
     * - i.e. compute {@code (mat * (this, 0)).xy}, applying only rotation and scale and ignoring
     * translation and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    Double2 preMulDirection(Double2x3R mat, @Mutated Double2 dest);

    /**
     * Pre-multiply {@code mat} onto this vector, treated as a direction with implicit {@code w = 0}
     * - i.e. compute {@code (mat * (this, 0)).xy}, applying only rotation and scale and ignoring
     * translation and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    Double2 preMulDirection(Double3x3R mat, @Mutated Double2 dest);

    /**
     * Pre-multiply {@code mat} onto this vector, treated as the point {@code (x, y, 0, 1)} of the
     * xy-plane - i.e. compute {@code (mat * (this, 0, 1)).xy}, applying the full affine transform
     * including translation and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    Double2 preMulPosition(Double4x4R mat, @Mutated Double2 dest);

    /**
     * Pre-multiply {@code mat} onto this vector, treated as a position with implicit {@code w = 1}
     * - i.e. compute {@code (mat * (this, 1)).xy}, applying the full affine transform including
     * translation and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    Double2 preMulPosition(Double2x3R mat, @Mutated Double2 dest);

    /**
     * Pre-multiply {@code mat} onto this vector, treated as a position with implicit {@code w = 1}
     * - i.e. compute {@code (mat * (this, 1)).xy}, applying the full affine transform including
     * translation and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @param dest will hold the result
     * @return dest
     */
    Double2 preMulPosition(Double3x3R mat, @Mutated Double2 dest);

    /**
     * Rotate this vector counter-clockwise about the origin by {@code angle} radians and store the
     * result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    Double2 rotate(double angle, @Mutated Double2 dest);

    /**
     * Rotate this vector counter-clockwise by {@code angle} radians about the point {@code pivot}
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    Double2 rotateAround(double angle, Double2R pivot, @Mutated Double2 dest);

    /**
     * Rotate this vector counter-clockwise by {@code angle} radians about the point ({@code x},
     * {@code y}) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @param dest will hold the result
     * @return dest
     */
    Double2 rotateAround(double angle, double x, double y, @Mutated Double2 dest);
    /** {@return the value of the {@code x} component} <p>Valid input: any value, NaN and the infinities included. */
    double x();
    /** {@return the value of the {@code y} component} <p>Valid input: any value, NaN and the infinities included. */
    double y();

    /**
     * Swizzle: copy ({@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 xx(@Mutated Double2 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 xy(@Mutated Double2 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 yx(@Mutated Double2 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double2 yy(@Mutated Double2 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 xxx(@Mutated Double3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 xxy(@Mutated Double3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 xyx(@Mutated Double3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 xyy(@Mutated Double3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 yxx(@Mutated Double3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 yxy(@Mutated Double3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 yyx(@Mutated Double3 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 yyy(@Mutated Double3 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 xxxx(@Mutated Double4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 xxxy(@Mutated Double4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 xxyx(@Mutated Double4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code x}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 xxyy(@Mutated Double4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 xyxx(@Mutated Double4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 xyxy(@Mutated Double4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 xyyx(@Mutated Double4 dest);

    /**
     * Swizzle: copy ({@code x}, {@code y}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 xyyy(@Mutated Double4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 yxxx(@Mutated Double4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 yxxy(@Mutated Double4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 yxyx(@Mutated Double4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code x}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 yxyy(@Mutated Double4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code x}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 yyxx(@Mutated Double4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code x}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 yyxy(@Mutated Double4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code y}, {@code x}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 yyyx(@Mutated Double4 dest);

    /**
     * Swizzle: copy ({@code y}, {@code y}, {@code y}, {@code y}) of this vector into {@code dest}, in that order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4 yyyy(@Mutated Double4 dest);

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
        if (dest.remaining() < 2) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 2);
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
    Double2 storeUnsafe(long address);

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
        if (dest.remaining() < 2) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeAbsolute(pos, dest);
        dest.position(pos + 2);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
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
    default ByteBuffer storeFloat(@Mutated ByteBuffer dest) { return storeFloatAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
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
    default ByteBuffer storeFloatAbsolute(@Mutated ByteBuffer dest) { return storeFloatAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
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
    ByteBuffer storeFloatAbsolute(int index, @Mutated ByteBuffer dest);

    /**
     * Store the elements into the given byte buffer, converting each element to {@code float},
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
    default ByteBuffer storeFloatRelative(@Mutated ByteBuffer dest) {
        if (dest.remaining() < 8) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeFloatAbsolute(pos, dest);
        dest.position(pos + 8);
        return dest;
    }

    /**
     * Store the elements into the given raw memory address, converting each element to
     * {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    Double2 storeFloatUnsafe(long address);

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
    boolean equalsEpsilon(Double2R other, double epsilon);

    /**
     * Compare this vector with the given object for element-wise equality.
     * <p>
     * Each component is compared bitwise, as by {@code Double.doubleToLongBits}: {@code 0.0} and
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
     * {@code Double.doubleToLongBits} alone.
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
        static final Double2R ZERO = Joml.double2(0, 0);
    }
}
