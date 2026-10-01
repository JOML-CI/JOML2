// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import java.nio.ByteBuffer;

/**
 * A 4D vector of single-precision {@code float} components.
 * <p>
 * Extends the read-only view {@link Float4R} with the mutating surface: in-place self-forms,
 * setters and load methods. A self-form operation writes its result to {@code this} and returns it
 * - unless the {@code joml.returnNew} mode is enabled, in which case it leaves {@code this}
 * unchanged and returns a freshly allocated instance.
 * <p>
 * Instances are created through the {@link Joml} factory methods.
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
public interface Float4 extends Float4R {

    /**
     * Add {@code other} to this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to add
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 add(Float4R other) { return add(other, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Add ({@code x}, {@code y}, {@code z}, {@code w}) to this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 add(float x, float y, float z, float w) { return add(x, y, z, w, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Divide each component of this vector by {@code scalar}.
     * <p>
     * Valid input: {@code scalar} must be non-zero.
     *
     * @param scalar the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 div(float scalar) { return div(scalar, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Divide this vector component-wise by {@code other}.
     * <p>
     * Valid input: each component of {@code other} must be non-zero.
     *
     * @param other the vector of per-component divisors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 div(Float4R other) { return div(other, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Divide this vector component-wise by ({@code x}, {@code y}, {@code z}, {@code w}).
     * <p>
     * Valid input: each component of {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 div(float x, float y, float z, float w) { return div(x, y, z, w, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Multiply this vector component-wise by {@code b} and add {@code c}, i.e. compute
     * {@code this * b + c} per component.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param c the vector to add
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 fma(float b, Float4R c) { return fma(b, c, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Multiply this vector component-wise by {@code b} and add ({@code x}, {@code y}, {@code z},
     * {@code w}), i.e. compute {@code this * b + (x, y, z, w)} per component.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 fma(float b, float x, float y, float z, float w) { return fma(b, x, y, z, w, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Multiply this vector component-wise by {@code b} and add {@code c}, i.e. compute
     * {@code this * b + c} per component.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the factor to multiply this vector by
     * @param c the vector to add
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 fma(Float4R b, Float4R c) { return fma(b, c, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Multiply this vector component-wise by ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) and
     * add ({@code cX}, {@code cY}, {@code cZ}, {@code cW}), i.e. compute
     * {@code this * (bX, bY, bZ, bW) + (cX, cY, cZ, cW)} per component.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 fma(float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) { return fma(bX, bY, bZ, bW, cX, cY, cZ, cW, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Multiply each component of this vector by {@code scalar}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 mul(float scalar) { return mul(scalar, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Multiply this vector component-wise by {@code other}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector of per-component factors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 mul(Float4R other) { return mul(other, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Multiply this vector component-wise by ({@code x}, {@code y}, {@code z}, {@code w}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 mul(float x, float y, float z, float w) { return mul(x, y, z, w, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Negate this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 negate() { return negate(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Subtract {@code other} from this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the vector to subtract
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 sub(Float4R other) { return sub(other, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Subtract ({@code x}, {@code y}, {@code z}, {@code w}) from this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 sub(float x, float y, float z, float w) { return sub(x, y, z, w, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Set this vector to the unit vector
     * {@code (sqrt(1 - u) cos(2 PI v), sqrt(1 - u) sin(2 PI v), sqrt(u) cos(2 PI w), sqrt(u) sin(2 PI w))}:
     * samples uniformly distributed in {@code [0, 1)} give a direction uniformly distributed on the
     * unit sphere of four dimensions ({@code makeRandomDirection} draws them from a
     * {@link java.util.Random}).
     * <p>
     * Valid input: {@code u} must lie in {@code [0, 1]}.
     *
     * @param u the sample that splits the unit length between {@code (x, y)} and {@code (z, w)},
     *        uniformly distributed in {@code [0, 1)} for a uniformly distributed direction
     * @param v the fraction of a full turn of {@code (x, y)}, uniformly distributed in
     *        {@code [0, 1)} for a uniformly distributed direction
     * @param w the fraction of a full turn of {@code (z, w)}, uniformly distributed in
     *        {@code [0, 1)} for a uniformly distributed direction
     * @return this
     */
    @Mutated Float4 makeUniformDirection(float u, float v, float w);

    /**
     * Set this vector to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the vector to copy
     * @return this
     */
    @Mutated Float4 set(Float4R v);

    /**
     * Set this vector to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this
     */
    @Mutated Float4 set(float x, float y, float z, float w);

    /**
     * Set this vector to {@code s}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the value assigned to every component
     * @return this
     */
    @Mutated default Float4 set(float s) { return set(s, this); }

    /**
     * Convert this vector to {@code double} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Double4} holding the result
     */
    default Double4 toDouble() { return toDouble(Joml.double4()); }

    /**
     * Convert this vector to {@code byte} precision, returning the result as a new instance.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Byte4} holding the result
     */
    default Byte4 toByte() { return toByte(Joml.byte4()); }

    /**
     * Convert this vector to {@code byte} precision, returning the result as a new instance.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mode the rounding mode to use
     * @return a new {@code Byte4} holding the result
     */
    default Byte4 toByte(RoundingMode mode) { return toByte(mode, Joml.byte4()); }

    /**
     * Convert this vector to {@code short} precision, returning the result as a new instance.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Short4} holding the result
     */
    default Short4 toShort() { return toShort(Joml.short4()); }

    /**
     * Convert this vector to {@code short} precision, returning the result as a new instance.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mode the rounding mode to use
     * @return a new {@code Short4} holding the result
     */
    default Short4 toShort(RoundingMode mode) { return toShort(mode, Joml.short4()); }

    /**
     * Convert this vector to {@code int} precision, returning the result as a new instance.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Int4} holding the result
     */
    default Int4 toInt() { return toInt(Joml.int4()); }

    /**
     * Convert this vector to {@code int} precision, returning the result as a new instance.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mode the rounding mode to use
     * @return a new {@code Int4} holding the result
     */
    default Int4 toInt(RoundingMode mode) { return toInt(mode, Joml.int4()); }

    /**
     * Convert this vector to {@code long} precision, returning the result as a new instance.
     * <p>
     * Each component is converted by a primitive cast, truncating toward zero.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Long4} holding the result
     */
    default Long4 toLong() { return toLong(Joml.long4()); }

    /**
     * Convert this vector to {@code long} precision, returning the result as a new instance.
     * <p>
     * Each component is rounded according to the given rounding mode.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mode the rounding mode to use
     * @return a new {@code Long4} holding the result
     */
    default Long4 toLong(RoundingMode mode) { return toLong(mode, Joml.long4()); }

    /**
     * Set all components of this vector to zero.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float4 makeZero();

    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points {@code p1} and {@code p2} and ends at {@code p3}.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 bezier(Float4R p1, Float4R p2, Float4R p3, float t) { return bezier(p1, p2, p3, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Interpolate along the cubic Bézier curve that starts at this vector, is shaped by the control
     * points ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}) and ends at ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}).
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 bezier(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) { return bezier(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point {@code p1} and ends at {@code p2}.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 bezier2(Float4R p1, Float4R p2, float t) { return bezier2(p1, p2, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Interpolate along the quadratic Bézier curve that starts at this vector, is shaped by the
     * control point ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ends at ({@code p2X},
     * {@code p2Y}, {@code p2Z}, {@code p2W}).
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 bezier2(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) { return bezier2(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point {@code p1}
     * and ends at {@code p2}.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 bezier2Tangent(Float4R p1, Float4R p2, float t) { return bezier2Tangent(p1, p2, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * quadratic Bézier curve that starts at this vector, is shaped by the control point
     * ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) and ends at ({@code p2X}, {@code p2Y},
     * {@code p2Z}, {@code p2W}).
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 bezier2Tangent(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float t) { return bezier2Tangent(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points {@code p1} and
     * {@code p2} and ends at {@code p3}.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 bezierTangent(Float4R p1, Float4R p2, Float4R p3, float t) { return bezierTangent(p1, p2, p3, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Bézier curve that starts at this vector, is shaped by the control points ({@code p1X},
     * {@code p1Y}, {@code p1Z}, {@code p1W}) and ({@code p2X}, {@code p2Y}, {@code p2Z},
     * {@code p2W}) and ends at ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}).
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 bezierTangent(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) { return bezierTangent(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Interpolate along the Catmull-Rom spline segment from {@code p1} to {@code p2}, with this
     * vector as the control point before the segment and {@code p3} as the control point after it.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 catmullRom(Float4R p1, Float4R p2, Float4R p3, float t) { return catmullRom(p1, p2, p3, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Interpolate along the Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z},
     * {@code p1W}) to ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}), with this vector as the
     * control point before the segment and ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) as
     * the control point after it.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 catmullRom(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) { return catmullRom(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from {@code p1} to {@code p2}, with this vector as the control
     * point before the segment and {@code p3} as the control point after it.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 catmullRomTangent(Float4R p1, Float4R p2, Float4R p3, float t) { return catmullRomTangent(p1, p2, p3, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * Catmull-Rom spline segment from ({@code p1X}, {@code p1Y}, {@code p1Z}, {@code p1W}) to
     * ({@code p2X}, {@code p2Y}, {@code p2Z}, {@code p2W}), with this vector as the control point
     * before the segment and ({@code p3X}, {@code p3Y}, {@code p3Z}, {@code p3W}) as the control
     * point after it.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 catmullRomTangent(float p1X, float p1Y, float p1Z, float p1W, float p2X, float p2Y, float p2Z, float p2W, float p3X, float p3Y, float p3Z, float p3W, float t) { return catmullRomTangent(p1X, p1Y, p1Z, p1W, p2X, p2Y, p2Z, p2W, p3X, p3Y, p3Z, p3W, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Interpolate along the cubic Hermite curve that starts at this vector with the tangent
     * {@code t0} and ends at {@code v1} with the tangent {@code t1}.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 hermite(Float4R t0, Float4R v1, Float4R t1, float t) { return hermite(t0, v1, t1, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Interpolate along the cubic Hermite curve that starts at this vector with the tangent
     * ({@code t0X}, {@code t0Y}, {@code t0Z}, {@code t0W}) and ends at ({@code v1X}, {@code v1Y},
     * {@code v1Z}, {@code v1W}) with the tangent ({@code t1X}, {@code t1Y}, {@code t1Z},
     * {@code t1W}).
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 hermite(float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) { return hermite(t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Hermite curve that starts at this vector with the tangent {@code t0} and ends at
     * {@code v1} with the tangent {@code t1}.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 hermiteTangent(Float4R t0, Float4R v1, Float4R t1, float t) { return hermiteTangent(t0, v1, t1, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the tangent (the unnormalized first derivative) at the parameter {@code t} of the
     * cubic Hermite curve that starts at this vector with the tangent ({@code t0X}, {@code t0Y},
     * {@code t0Z}, {@code t0W}) and ends at ({@code v1X}, {@code v1Y}, {@code v1Z}, {@code v1W})
     * with the tangent ({@code t1X}, {@code t1Y}, {@code t1Z}, {@code t1W}).
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 hermiteTangent(float t0X, float t0Y, float t0Z, float t0W, float v1X, float v1Y, float v1Z, float v1W, float t1X, float t1Y, float t1Z, float t1W, float t) { return hermiteTangent(t0X, t0Y, t0Z, t0W, v1X, v1Y, v1Z, v1W, t1X, t1Y, t1Z, t1W, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Linearly interpolate between this vector and {@code other} using the interpolation factor
     * {@code t}.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 lerp(Float4R other, float t) { return lerp(other, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Linearly interpolate between this vector and ({@code x}, {@code y}, {@code z}, {@code w})
     * using the interpolation factor {@code t}.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 lerp(float x, float y, float z, float w, float t) { return lerp(x, y, z, w, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Linearly interpolate between this vector and {@code other} using the interpolation factor
     * {@code t}.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 lerp(Float4R other, Float4R t) { return lerp(other, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Linearly interpolate between this vector and ({@code otherX}, {@code otherY}, {@code otherZ},
     * {@code otherW}) using the interpolation factor ({@code tX}, {@code tY}, {@code tZ},
     * {@code tW}).
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 lerp(float otherX, float otherY, float otherZ, float otherW, float tX, float tY, float tZ, float tW) { return lerp(otherX, otherY, otherZ, otherW, tX, tY, tZ, tW, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Spherically interpolate between this vector and {@code other} using the interpolation factor
     * {@code t}: the direction turns at a constant rate along the shorter arc between the two
     * directions, and the length changes linearly between the two lengths.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 slerp(Float4R other, float t) { return slerp(other, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Spherically interpolate between this vector and ({@code x}, {@code y}, {@code z}, {@code w})
     * using the interpolation factor {@code t}: the direction turns at a constant rate along the
     * shorter arc between the two directions, and the length changes linearly between the two
     * lengths.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 slerp(float x, float y, float z, float w, float t) { return slerp(x, y, z, w, t, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the absolute value of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 absolute() { return absolute(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the arc cosine of each component of this vector.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 acos() { return acos(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Add {@code b} scaled by {@code scalar} to this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the vector to scale and add
     * @param scalar the factor to scale {@code b} by before adding
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 addScaled(Float4R b, float scalar) { return addScaled(b, scalar, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Add ({@code x}, {@code y}, {@code z}, {@code w}) scaled by {@code scalar} to this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @param scalar the factor to scale ({@code x}, {@code y}, {@code z}, {@code w}) by before
     *        adding
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 addScaled(float x, float y, float z, float w, float scalar) { return addScaled(x, y, z, w, scalar, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Add {@code b} scaled by {@code c} to this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param b the vector to scale and add
     * @param c the per-component factors to scale {@code b} by before adding
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 addScaled(Float4R b, Float4R c) { return addScaled(b, c, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Add ({@code bX}, {@code bY}, {@code bZ}, {@code bW}) scaled by ({@code cX}, {@code cY},
     * {@code cZ}, {@code cW}) to this vector.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 addScaled(float bX, float bY, float bZ, float bW, float cX, float cY, float cZ, float cW) { return addScaled(bX, bY, bZ, bW, cX, cY, cZ, cW, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the arc sine of each component of this vector.
     * <p>
     * Valid input: each component of this vector must lie in {@code [-1, 1]}.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 asin() { return asin(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the arc tangent of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 atan() { return atan(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} {@code x} (the denominator).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the value to take the arc tangent over (the denominator)
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 atan2(float x) { return atan2(x, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} the corresponding component of {@code x} (the
     * denominator).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the vector of denominators, one per component
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 atan2(Float4R x) { return atan2(x, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the component-wise arc tangent {@code atan2(a, b)} with {@code a} each component of
     * this vector (the numerator) and {@code b} the corresponding component of ({@code x},
     * {@code y}, {@code z}, {@code w}) (the denominator).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 atan2(float x, float y, float z, float w) { return atan2(x, y, z, w, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the cube root of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 cbrt() { return cbrt(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the ceiling of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 ceil() { return ceil(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Clamp each component of this vector between {@code min} and {@code max}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the lower bound
     * @param max the upper bound
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 clamp(float min, float max) { return clamp(min, max, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Clamp each component of this vector between {@code min} and {@code max}.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the per-component lower bounds
     * @param max the per-component upper bounds
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 clamp(Float4R min, Float4R max) { return clamp(min, max, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Clamp each component of this vector between ({@code minX}, {@code minY}, {@code minZ},
     * {@code minW}) and ({@code maxX}, {@code maxY}, {@code maxZ}, {@code maxW}).
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 clamp(float minX, float minY, float minZ, float minW, float maxX, float maxY, float maxZ, float maxW) { return clamp(minX, minY, minZ, minW, maxX, maxY, maxZ, maxW, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Copy the sign of {@code sign} onto each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sign the value whose sign is copied
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 copySign(float sign) { return copySign(sign, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Copy the sign of each component of {@code sign} onto the corresponding component of this
     * vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sign the value whose sign is copied
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 copySign(Float4R sign) { return copySign(sign, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Copy the sign of each component of ({@code x}, {@code y}, {@code z}, {@code w}) onto the
     * corresponding component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 copySign(float x, float y, float z, float w) { return copySign(x, y, z, w, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the cosine of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 cos() { return cos(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the hyperbolic cosine of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 cosh() { return cosh(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the four-dimensional cross product of this vector, {@code v} and {@code w}, in that
     * order: the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, {@code v} and {@code w} (the
     * zero vector when the three are linearly dependent).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the second operand of the cross product
     * @param w the third operand of the cross product
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 cross(Float4R v, Float4R w) { return cross(v, w, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the four-dimensional cross product of this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}), in that order:
     * the vector orthogonal to all three whose dot product with any vector {@code x} is the
     * determinant of the matrix with the rows {@code x}, this vector, ({@code vX}, {@code vY},
     * {@code vZ}, {@code vW}) and ({@code wX}, {@code wY}, {@code wZ}, {@code wW}) (the zero vector
     * when the three are linearly dependent).
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 cross(float vX, float vY, float vZ, float vW, float wX, float wY, float wZ, float wW) { return cross(vX, vY, vZ, vW, wX, wY, wZ, wW, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the value converted from radians to degrees of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 degrees() { return degrees(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the base-e exponential of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 exp() { return exp(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the base-2 exponential of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 exp2() { return exp2(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the base-e exponential minus one of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 expm1() { return expm1(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Return this vector unchanged when {@code dot(Nref, I)} is negative, and negated otherwise -
     * orienting it against the incident direction {@code I} as judged by the reference vector
     * {@code Nref}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param I the incident direction
     * @param Nref the reference vector the incident direction is tested against
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 faceforward(Float4R I, Float4R Nref) { return faceforward(I, Nref, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Return this vector unchanged when {@code dot((NrefX, NrefY, NrefZ, NrefW), (IX, IY, IZ, IW))}
     * is negative, and negated otherwise - orienting it against the incident direction ({@code IX},
     * {@code IY}, {@code IZ}, {@code IW}) as judged by the reference vector ({@code NrefX},
     * {@code NrefY}, {@code NrefZ}, {@code NrefW}).
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 faceforward(float IX, float IY, float IZ, float IW, float NrefX, float NrefY, float NrefZ, float NrefW) { return faceforward(IX, IY, IZ, IW, NrefX, NrefY, NrefZ, NrefW, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the floor of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 floor() { return floor(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the fractional part of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 fract() { return fract(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} {@code y}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param y the other operand
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 hypot(float y) { return hypot(y, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} the corresponding component of {@code y}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param y the vector of other operands, one per component
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 hypot(Float4R y) { return hypot(y, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the component-wise Euclidean norm {@code sqrt(a² + b²)} with {@code a} each component
     * of this vector and {@code b} the corresponding component of ({@code x}, {@code y}, {@code z},
     * {@code w}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 hypot(float x, float y, float z, float w) { return hypot(x, y, z, w, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the reciprocal {@code 1 / x} of each component of this vector.
     * <p>
     * Valid input: each component of this vector must be non-zero.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 inverse() { return inverse(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the inverse square root of each component of this vector.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 inverseSqrt() { return inverseSqrt(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the natural logarithm of each component of this vector.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 log() { return log(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the base-10 logarithm of each component of this vector.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 log10() { return log10(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the natural logarithm of one plus the value of each component of this vector.
     * <p>
     * Valid input: each component of this vector must lie in {@code (-1, Infinity)}.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 log1p() { return log1p(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the base-2 logarithm of each component of this vector.
     * <p>
     * Valid input: each component of this vector must be positive.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 log2() { return log2(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Set each component of this vector to the larger of itself and {@code scalar}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param scalar the value to take the component-wise maximum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 max(float scalar) { return max(scalar, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * {@code other}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the vector to take the component-wise maximum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 max(Float4R other) { return max(other, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Set each component of this vector to the larger of itself and the corresponding component of
     * ({@code x}, {@code y}, {@code z}, {@code w}).
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 max(float x, float y, float z, float w) { return max(x, y, z, w, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Set each component of this vector to the smaller of itself and {@code scalar}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param scalar the value to take the component-wise minimum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 min(float scalar) { return min(scalar, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * {@code other}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the vector to take the component-wise minimum with
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 min(Float4R other) { return min(other, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Set each component of this vector to the smaller of itself and the corresponding component of
     * ({@code x}, {@code y}, {@code z}, {@code w}).
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 min(float x, float y, float z, float w) { return min(x, y, z, w, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the component-wise floored modulo of this vector divided by {@code y} ({@code x % y},
     * plus {@code y} when that remainder is non-zero and its sign differs from {@code y}'s -
     * exactly Kotlin's {@code mod}).
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * Valid input: {@code y} must be non-zero.
     *
     * @param y the divisor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 mod(float y) { return mod(y, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the component-wise floored modulo of this vector divided by {@code y} ({@code x % y},
     * plus {@code y} when that remainder is non-zero and its sign differs from {@code y}'s -
     * exactly Kotlin's {@code mod}).
     * <p>
     * The result takes the sign of the divisor, unlike Java's {@code %} operator, which follows the
     * dividend.
     * <p>
     * Valid input: each component of {@code y} must be non-zero.
     *
     * @param y the vector of divisors, one per component
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 mod(Float4R y) { return mod(y, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the component-wise floored modulo of this vector divided by ({@code x}, {@code y},
     * {@code z}, {@code w}) ({@code x % y}, plus {@code y} when that remainder is non-zero and its
     * sign differs from {@code y}'s - exactly Kotlin's {@code mod}).
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 mod(float x, float y, float z, float w) { return mod(x, y, z, w, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the next representable value toward negative infinity of each component of this
     * vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 nextDown() { return nextDown(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the next representable value toward positive infinity of each component of this
     * vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 nextUp() { return nextUp(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Normalize this vector to unit length (the zero vector yields the zero vector).
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 normalize() { return normalize(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Normalize this vector and multiply the result by {@code length}, i.e. rescale it to that
     * length (the zero vector yields the zero vector).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param length the length to rescale to
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 normalizeMul(float length) { return normalizeMul(length, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Raise each component of this vector to the power of {@code exponent}.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponent the exponent
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 pow(float exponent) { return pow(exponent, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Raise each component of this vector to the power of {@code exponent}.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param exponent the exponent
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 pow(Float4R exponent) { return pow(exponent, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Raise each component of this vector to the power of ({@code x}, {@code y}, {@code z},
     * {@code w}).
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 pow(float x, float y, float z, float w) { return pow(x, y, z, w, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Project this vector onto {@code onto}.
     * <p>
     * Valid input: {@code onto} must be non-zero.
     *
     * @param onto the vector to project onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 project(Float4R onto) { return project(onto, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Project this vector onto ({@code x}, {@code y}, {@code z}, {@code w}).
     * <p>
     * Valid input: {@code (x, y, z, w)} must be non-zero.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 project(float x, float y, float z, float w) { return project(x, y, z, w, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Project this vector onto the plane with the given normal.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to project onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 projectOnPlane(Float4R normal) { return projectOnPlane(normal, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Project this vector onto the plane with the given normal.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 projectOnPlane(float x, float y, float z, float w) { return projectOnPlane(x, y, z, w, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the value converted from degrees to radians of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 radians() { return radians(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Reflect this vector about the given normal.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to reflect about
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 reflect(Float4R normal) { return reflect(normal, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Reflect this vector about the given normal.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 reflect(float x, float y, float z, float w) { return reflect(x, y, z, w, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Refract this vector through the surface with the given normal, using the given ratio of
     * indices of refraction (the zero vector is returned on total internal reflection).
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 refract(Float4R normal, float eta) { return refract(normal, eta, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Refract this vector through the surface with the given normal, using the given ratio of
     * indices of refraction (the zero vector is returned on total internal reflection).
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 refract(float x, float y, float z, float w, float eta) { return refract(x, y, z, w, eta, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the value rounded to the nearest integer, ties to even ({@code Math.rint}) of each
     * component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 round() { return round(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the sign of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 sign() { return sign(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the sine of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 sin() { return sin(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the hyperbolic sine of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 sinh() { return sinh(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge {@code edge0} and the upper edge {@code edge1}, yielding 0 at or below the lower
     * edge and 1 at or above the upper edge.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ.
     *
     * @param edge0 the lower edge
     * @param edge1 the upper edge
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 smoothstep(float edge0, float edge1) { return smoothstep(edge0, edge1, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge {@code edge0} and the upper edge {@code edge1}, yielding 0 at or below the lower
     * edge and 1 at or above the upper edge.
     * <p>
     * Valid input: {@code edge0} and {@code edge1} must differ in every component.
     *
     * @param edge0 the lower edge
     * @param edge1 the upper edge
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 smoothstep(Float4R edge0, Float4R edge1) { return smoothstep(edge0, edge1, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the smooth Hermite step of each component of this vector as it ramps between the
     * lower edge ({@code edge0X}, {@code edge0Y}, {@code edge0Z}, {@code edge0W}) and the upper
     * edge ({@code edge1X}, {@code edge1Y}, {@code edge1Z}, {@code edge1W}), yielding 0 at or below
     * the lower edge and 1 at or above the upper edge.
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
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 smoothstep(float edge0X, float edge0Y, float edge0Z, float edge0W, float edge1X, float edge1Y, float edge1Z, float edge1W) { return smoothstep(edge0X, edge0Y, edge0Z, edge0W, edge1X, edge1Y, edge1Z, edge1W, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the square root of each component of this vector.
     * <p>
     * Valid input: each component of this vector must not be negative.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 sqrt() { return sqrt(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Set each component of this vector to {@code 0} when it is smaller than {@code edge}, and to
     * {@code 1} otherwise.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edge the edge to compare each component against
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 step(float edge) { return step(edge, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Set each component of this vector to {@code 0} when it is smaller than the corresponding
     * component of the given edge, and to {@code 1} otherwise.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param edge the edge to compare each component against
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 step(Float4R edge) { return step(edge, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Set each component of this vector to {@code 0} when it is smaller than the corresponding
     * component of the given edge, and to {@code 1} otherwise.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z, w)}
     * @param y the {@code y} component of the vector {@code (x, y, z, w)}
     * @param z the {@code z} component of the vector {@code (x, y, z, w)}
     * @param w the {@code w} component of the vector {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 step(float x, float y, float z, float w) { return step(x, y, z, w, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the tangent of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 tan() { return tan(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the hyperbolic tangent of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 tanh() { return tanh(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the truncated value of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 trunc() { return trunc(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Compute the unit in the last place (ulp) of each component of this vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 ulp() { return ulp(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Pre-multiply {@code mat} onto this vector, i.e. compute {@code mat * this}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param mat the matrix to apply
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 preMul(Float4x4R mat) { return preMul(mat, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Rotate the {@code (x, y, z)} components of this vector by the quaternion {@code quat}, i.e.
     * compute {@code q * this.xyz * q^-1}, leaving {@code w} unchanged.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param quat the rotation to apply
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 rotate(FloatQuatR quat) { return rotate(quat, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Rotate the {@code (x, y, z)} components of this vector by the quaternion ({@code x},
     * {@code y}, {@code z}, {@code w}), i.e. compute {@code q * this.xyz * q^-1}, leaving {@code w}
     * unchanged.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 rotate(float x, float y, float z, float w) { return rotate(x, y, z, w, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the
     * axis {@code axis}, leaving {@code w} unchanged.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 rotateAxis(float angle, Float3R axis) { return rotateAxis(angle, axis, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the
     * axis ({@code x}, {@code y}, {@code z}), leaving {@code w} unchanged.
     * <p>
     * Valid input: {@code (x, y, z)} must have unit length.
     *
     * @param angle the angle in radians
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 rotateAxis(float angle, float x, float y, float z) { return rotateAxis(angle, x, y, z, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Rotate the {@code (x, y, z)} components of this vector by the inverse of the given rotation,
     * leaving {@code w} unchanged.
     * <p>
     * Valid input: {@code quat} must have unit length.
     *
     * @param quat the rotation whose inverse to apply
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 rotateInverse(FloatQuatR quat) { return rotateInverse(quat, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Rotate the {@code (x, y, z)} components of this vector by the inverse of the given rotation,
     * leaving {@code w} unchanged.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 rotateInverse(float x, float y, float z, float w) { return rotateInverse(x, y, z, w, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the X
     * axis, leaving {@code w} unchanged.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 rotateX(float angle) { return rotateX(angle, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the Y
     * axis, leaving {@code w} unchanged.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 rotateY(float angle) { return rotateY(angle, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Rotate the {@code (x, y, z)} components of this vector by {@code angle} radians about the Z
     * axis, leaving {@code w} unchanged.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 rotateZ(float angle) { return rotateZ(angle, Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Set this vector to a direction uniformly distributed on the unit sphere of four dimensions,
     * drawing the 3 samples of {@code makeUniformDirection} from {@code rng}, each with
     * {@code rng.nextFloat()}, in parameter order.
     * <p>
     * Valid input: any value.
     *
     * @param rng the random number generator to draw the 3 samples from
     * @return this
     */
    @Mutated default Float4 makeRandomDirection(java.util.Random rng) { return makeUniformDirection(rng.nextFloat(), rng.nextFloat(), rng.nextFloat()); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xxxx() { return xxxx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xxxy() { return xxxy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xxxz() { return xxxz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xxxw() { return xxxw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xxyx() { return xxyx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xxyy() { return xxyy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xxyz() { return xxyz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xxyw() { return xxyw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xxzx() { return xxzx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xxzy() { return xxzy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xxzz() { return xxzz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xxzw() { return xxzw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xxwx() { return xxwx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xxwy() { return xxwy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xxwz() { return xxwz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code x}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xxww() { return xxww(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xyxx() { return xyxx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xyxy() { return xyxy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xyxz() { return xyxz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xyxw() { return xyxw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xyyx() { return xyyx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xyyy() { return xyyy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xyyz() { return xyyz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xyyw() { return xyyw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xyzx() { return xyzx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xyzy() { return xyzy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xyzz() { return xyzz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xyzw() { return xyzw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xywx() { return xywx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xywy() { return xywy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xywz() { return xywz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code y}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xyww() { return xyww(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xzxx() { return xzxx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xzxy() { return xzxy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xzxz() { return xzxz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xzxw() { return xzxw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xzyx() { return xzyx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xzyy() { return xzyy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xzyz() { return xzyz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xzyw() { return xzyw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xzzx() { return xzzx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xzzy() { return xzzy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xzzz() { return xzzz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xzzw() { return xzzw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xzwx() { return xzwx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xzwy() { return xzwy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xzwz() { return xzwz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code z}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xzww() { return xzww(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xwxx() { return xwxx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xwxy() { return xwxy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xwxz() { return xwxz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xwxw() { return xwxw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xwyx() { return xwyx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xwyy() { return xwyy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xwyz() { return xwyz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xwyw() { return xwyw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xwzx() { return xwzx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xwzy() { return xwzy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xwzz() { return xwzz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xwzw() { return xwzw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xwwx() { return xwwx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xwwy() { return xwwy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xwwz() { return xwwz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code x}, {@code w}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 xwww() { return xwww(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yxxx() { return yxxx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yxxy() { return yxxy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yxxz() { return yxxz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yxxw() { return yxxw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yxyx() { return yxyx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yxyy() { return yxyy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yxyz() { return yxyz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yxyw() { return yxyw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yxzx() { return yxzx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yxzy() { return yxzy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yxzz() { return yxzz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yxzw() { return yxzw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yxwx() { return yxwx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yxwy() { return yxwy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yxwz() { return yxwz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code x}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yxww() { return yxww(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yyxx() { return yyxx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yyxy() { return yyxy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yyxz() { return yyxz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yyxw() { return yyxw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yyyx() { return yyyx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yyyy() { return yyyy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yyyz() { return yyyz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yyyw() { return yyyw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yyzx() { return yyzx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yyzy() { return yyzy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yyzz() { return yyzz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yyzw() { return yyzw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yywx() { return yywx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yywy() { return yywy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yywz() { return yywz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code y}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yyww() { return yyww(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yzxx() { return yzxx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yzxy() { return yzxy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yzxz() { return yzxz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yzxw() { return yzxw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yzyx() { return yzyx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yzyy() { return yzyy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yzyz() { return yzyz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yzyw() { return yzyw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yzzx() { return yzzx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yzzy() { return yzzy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yzzz() { return yzzz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yzzw() { return yzzw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yzwx() { return yzwx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yzwy() { return yzwy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yzwz() { return yzwz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code z}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 yzww() { return yzww(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 ywxx() { return ywxx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 ywxy() { return ywxy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 ywxz() { return ywxz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 ywxw() { return ywxw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 ywyx() { return ywyx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 ywyy() { return ywyy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 ywyz() { return ywyz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 ywyw() { return ywyw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 ywzx() { return ywzx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 ywzy() { return ywzy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 ywzz() { return ywzz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 ywzw() { return ywzw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 ywwx() { return ywwx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 ywwy() { return ywwy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 ywwz() { return ywwz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code y}, {@code w}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 ywww() { return ywww(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zxxx() { return zxxx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zxxy() { return zxxy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zxxz() { return zxxz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zxxw() { return zxxw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zxyx() { return zxyx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zxyy() { return zxyy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zxyz() { return zxyz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zxyw() { return zxyw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zxzx() { return zxzx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zxzy() { return zxzy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zxzz() { return zxzz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zxzw() { return zxzw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zxwx() { return zxwx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zxwy() { return zxwy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zxwz() { return zxwz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code x}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zxww() { return zxww(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zyxx() { return zyxx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zyxy() { return zyxy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zyxz() { return zyxz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zyxw() { return zyxw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zyyx() { return zyyx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zyyy() { return zyyy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zyyz() { return zyyz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zyyw() { return zyyw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zyzx() { return zyzx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zyzy() { return zyzy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zyzz() { return zyzz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zyzw() { return zyzw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zywx() { return zywx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zywy() { return zywy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zywz() { return zywz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code y}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zyww() { return zyww(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zzxx() { return zzxx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zzxy() { return zzxy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zzxz() { return zzxz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zzxw() { return zzxw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zzyx() { return zzyx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zzyy() { return zzyy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zzyz() { return zzyz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zzyw() { return zzyw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zzzx() { return zzzx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zzzy() { return zzzy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zzzz() { return zzzz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zzzw() { return zzzw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zzwx() { return zzwx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zzwy() { return zzwy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zzwz() { return zzwz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code z}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zzww() { return zzww(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zwxx() { return zwxx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zwxy() { return zwxy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zwxz() { return zwxz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zwxw() { return zwxw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zwyx() { return zwyx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zwyy() { return zwyy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zwyz() { return zwyz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zwyw() { return zwyw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zwzx() { return zwzx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zwzy() { return zwzy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zwzz() { return zwzz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zwzw() { return zwzw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zwwx() { return zwwx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zwwy() { return zwwy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zwwz() { return zwwz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code z}, {@code w}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 zwww() { return zwww(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wxxx() { return wxxx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wxxy() { return wxxy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wxxz() { return wxxz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wxxw() { return wxxw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wxyx() { return wxyx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wxyy() { return wxyy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wxyz() { return wxyz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wxyw() { return wxyw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wxzx() { return wxzx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wxzy() { return wxzy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wxzz() { return wxzz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wxzw() { return wxzw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wxwx() { return wxwx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wxwy() { return wxwy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wxwz() { return wxwz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code x}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wxww() { return wxww(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wyxx() { return wyxx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wyxy() { return wyxy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wyxz() { return wyxz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wyxw() { return wyxw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wyyx() { return wyyx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wyyy() { return wyyy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wyyz() { return wyyz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wyyw() { return wyyw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wyzx() { return wyzx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wyzy() { return wyzy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wyzz() { return wyzz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wyzw() { return wyzw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wywx() { return wywx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wywy() { return wywy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wywz() { return wywz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code y}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wyww() { return wyww(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wzxx() { return wzxx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wzxy() { return wzxy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wzxz() { return wzxz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wzxw() { return wzxw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wzyx() { return wzyx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wzyy() { return wzyy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wzyz() { return wzyz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wzyw() { return wzyw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wzzx() { return wzzx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wzzy() { return wzzy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wzzz() { return wzzz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wzzw() { return wzzw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wzwx() { return wzwx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wzwy() { return wzwy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wzwz() { return wzwz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code z}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wzww() { return wzww(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code x}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wwxx() { return wwxx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code x}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wwxy() { return wwxy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code x}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wwxz() { return wwxz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code x}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wwxw() { return wwxw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code y}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wwyx() { return wwyx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code y}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wwyy() { return wwyy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code y}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wwyz() { return wwyz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code y}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wwyw() { return wwyw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code z}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wwzx() { return wwzx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code z}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wwzy() { return wwzy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code z}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wwzz() { return wwzz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code z}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wwzw() { return wwzw(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code w}, {@code x}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wwwx() { return wwwx(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code w}, {@code y}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wwwy() { return wwwy(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code w}, {@code z}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wwwz() { return wwwz(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Swizzle: rearrange this vector's components to ({@code w}, {@code w}, {@code w}, {@code w}), in place.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float4 wwww() { return wwww(Joml.RETURN_NEW ? Joml.float4() : this); }

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Float4 load(float[] src) { return load(src, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Float4 load(float[] src, int offset);

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Float4 load(FloatBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Float4 loadAbsolute(FloatBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer, starting at the given absolute index (the position
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
     * @param src the source buffer
     * @return this
     */
    @Mutated Float4 loadAbsolute(int index, FloatBuffer src);

    /**
     * Load the elements from the given buffer, starting at its current position and advancing the
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
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4 loadRelative(FloatBuffer src) {
        if (src.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadAbsolute(pos, src);
        src.position(pos + 4);
        return this;
    }

    /**
     * Load the elements from the given byte buffer, starting at its current position (the position
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Float4 load(ByteBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, starting at its current position (the position
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Float4 loadAbsolute(ByteBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, starting at the given absolute index (the
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated Float4 loadAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer, starting at its current position and advancing
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
     * @param src the source byte buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4 loadRelative(ByteBuffer src) {
        if (src.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadAbsolute(pos, src);
        src.position(pos + 16);
        return this;
    }

    /**
     * Load the elements from the given raw memory address. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Float4 loadUnsafe(long address);

    /**
     * Load the elements from the given array.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Float4 load(double[] src) { return load(src, 0); }

    /**
     * Load the elements from the given array, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Float4 load(double[] src, int offset);

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Float4 load(DoubleBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer, starting at its current position (the position is
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Float4 loadAbsolute(DoubleBuffer src) { return loadAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer, starting at the given absolute index (the position
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
     * @param src the source buffer
     * @return this
     */
    @Mutated Float4 loadAbsolute(int index, DoubleBuffer src);

    /**
     * Load the elements from the given buffer, starting at its current position and advancing the
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
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4 loadRelative(DoubleBuffer src) {
        if (src.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadAbsolute(pos, src);
        src.position(pos + 4);
        return this;
    }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Float4 loadDouble(ByteBuffer src) { return loadDoubleAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Float4 loadDoubleAbsolute(ByteBuffer src) { return loadDoubleAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated Float4 loadDoubleAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer, converting each element from {@code double},
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
     * @param src the source byte buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float4 loadDoubleRelative(ByteBuffer src) {
        if (src.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadDoubleAbsolute(pos, src);
        src.position(pos + 32);
        return this;
    }

    /**
     * Load the elements from the given raw memory address, converting each element from
     * {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Float4 loadDoubleUnsafe(long address);
}
