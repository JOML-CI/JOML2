// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import java.nio.ByteBuffer;

/**
 * A 2x3 matrix of single-precision {@code float} components.
 * <p>
 * Extends the read-only view {@link Float2x3R} with the mutating surface: in-place self-forms,
 * setters and load methods. A self-form operation writes its result to {@code this} and returns it
 * - unless the {@code joml.returnNew} mode is enabled, in which case it leaves {@code this}
 * unchanged and returns a freshly allocated instance.
 * <p>
 * Instances are created through the {@link Joml} factory methods.
 * <p>
 * Structural property bits: the matrix caches whether it is known to be the identity, a pure
 * translation, orthogonal (a proper rotation, with any translation) or affine, and the operations
 * dispatch to cheaper arms on those bits. The {@code make*} factories set the bits from what they
 * construct and the computing operations derive them from their operands' bits; the element-wise
 * {@code set} methods and the {@code load*} methods recompute them with
 * {@code determineProperties()}, which compares elements exactly against {@code 0} and {@code 1}
 * and infers identity, translation and affine only. A rotation loaded from a buffer or set from
 * scalars is therefore merely affine - never orthogonal - until it is rebuilt through a
 * {@code make*} factory.
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Float.floatToIntBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN; the cached structural property bits are ignored, so two matrix objects holding the same
 * elements are equal whatever either one has determined about itself. {@code hashCode} is
 * consistent with it (derived from the same bit patterns). Only instances of this library's
 * implementation compare equal to each other; the {@code equals} of a matrix never returns
 * {@code true} for an object of another type.
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
 */
public interface Float2x3 extends Float2x3R {

    /**
     * Invert this affine matrix, i.e. compute the inverse of the implied square homogeneous matrix.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 invert() { return invert(Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Compute the inverse of the product of this matrix and {@code other}, i.e.
     * {@code (this * other)^-1}.
     * <p>
     * The product is formed first and inverted afterwards, so the result is the inverse of the
     * rounded product: its accuracy is bounded by the condition number of {@code this * other}, not
     * by the condition numbers of the two factors. For an ill-conditioned product (a near-singular
     * factor, or factors of very different scale) invert both factors separately and multiply the
     * inverses in reverse order instead.
     * <p>
     * Valid input: the product of this matrix and {@code other} must be invertible.
     *
     * @param other the right factor of the product
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 invertProduct(Float2x3R other) { return invertProduct(other, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Compute the inverse of the product of this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m10}, {@code m11}, {@code m12}).
     * <p>
     * The product is formed first and inverted afterwards, so the result is the inverse of the
     * rounded product: its accuracy is bounded by the condition number of the product, not by the
     * condition numbers of the two factors. For an ill-conditioned product (a near-singular factor,
     * or factors of very different scale) invert both factors separately and multiply the inverses
     * in reverse order instead.
     * <p>
     * Valid input: the product of this matrix and {@code (m00, m01, m02, m10, m11, m12)} must be
     * invertible.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 invertProduct(float m00, float m01, float m02, float m10, float m11, float m12) { return invertProduct(m00, m01, m02, m10, m11, m12, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Add {@code other} to this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to add
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 add(Float2x3R other) { return add(other, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12}) to this
     * matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 add(float m00, float m01, float m02, float m10, float m11, float m12) { return add(m00, m01, m02, m10, m11, m12, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Multiply each component of this matrix by {@code scalar}.
     * <p>
     * Only the stored elements take part: the implicit last row {@code (0, 0, 1)} stays as it is,
     * so the result is still affine.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 mul(float scalar) { return mul(scalar, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Negate this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 negate() { return negate(Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Subtract {@code other} from this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to subtract
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 sub(Float2x3R other) { return sub(other, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Subtract ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12}) from
     * this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 sub(float m00, float m01, float m02, float m10, float m11, float m12) { return sub(m00, m01, m02, m10, m11, m12, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Set this matrix to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the matrix to copy
     * @return this
     */
    @Mutated Float2x3 set(Float2x3R v);

    /**
     * Set this matrix to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @return this
     */
    @Mutated Float2x3 set(float m00, float m01, float m02, float m10, float m11, float m12);

    /**
     * Set this matrix to the given 2x2 matrix, copying the overlapping cells and filling the rest
     * with identity.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to copy from
     * @return this
     */
    @Mutated Float2x3 set(Float2x2R m);

    /**
     * Set this matrix to the given 3x3 matrix, copying the overlapping cells and dropping the rest.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to copy from
     * @return this
     */
    @Mutated Float2x3 set(Float3x3R m);

    /**
     * Set the translation of this matrix, leaving every other element untouched.
     * <p>
     * Only the first two elements of the last column are written, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this overwrites the
     * translation instead of composing a translation onto the existing transformation.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the translation offsets
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 withTranslation(Float2R t) { return withTranslation(t, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Set the translation of this matrix, leaving every other element untouched.
     * <p>
     * Only the first two elements of the last column are written, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this overwrites the
     * translation instead of composing a translation onto the existing transformation.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the translation offsets {@code (x, y)}
     * @param y the {@code y} component of the translation offsets {@code (x, y)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 withTranslation(float x, float y) { return withTranslation(x, y, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Convert this matrix to {@code double} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Double2x3} holding the result
     */
    default Double2x3 toDouble() { return toDouble(Joml.double2x3()); }

    /**
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float2x3 makeIdentity();

    /**
     * Linearly interpolate between this matrix and {@code other} using the interpolation factor
     * {@code t}.
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
     * @param other the matrix to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 lerp(Float2x3R other, float t) { return lerp(other, t, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m10}, {@code m11}, {@code m12}) using the interpolation factor {@code t}.
     * <p>
     * The interpolation starts at this matrix (interpolation factor {@code 0}) and ends at
     * ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12}) (interpolation
     * factor {@code 1}). Each linearly interpolated component is {@code this + (other - this) * t},
     * as in JOML and glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact
     * only up to the rounding of {@code other - this}, which shows when this component is much
     * larger in magnitude than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 lerp(float m00, float m01, float m02, float m10, float m11, float m12, float t) { return lerp(m00, m01, m02, m10, m11, m12, t, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Multiply this matrix by {@code right}.
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
     * @param right the right operand
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 mul(Float2x3R right) { return mul(right, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Multiply this matrix by ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11},
     * {@code m12}).
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
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 mul(float m00, float m01, float m02, float m10, float m11, float m12) { return mul(m00, m01, m02, m10, m11, m12, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Multiply this matrix by {@code right}.
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
     * @param right the right operand
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 mul(Float2x2R right) { return mul(right, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Pre-multiply the transformation {@code other} onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the given transformation matrix, then the
     * new matrix will be {@code T * M}. So when transforming a vector {@code v} with the new matrix
     * by using {@code T * M * v}, the given transformation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 preMul(Float2x3R other) { return preMul(other, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Pre-multiply the transformation ({@code m00}, {@code m01}, {@code m02}, {@code m10},
     * {@code m11}, {@code m12}) onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the given transformation matrix, then the
     * new matrix will be {@code T * M}. So when transforming a vector {@code v} with the new matrix
     * by using {@code T * M * v}, the given transformation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 preMul(float m00, float m01, float m02, float m10, float m11, float m12) { return preMul(m00, m01, m02, m10, m11, m12, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Pre-multiply {@code other} onto this matrix.
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
     * @param other the left operand
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 preMul(Float2x2R other) { return preMul(other, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Add {@code other} scaled by {@code weight} to this matrix.
     * <p>
     * Only the stored elements take part: the implicit last row {@code (0, 0, 1)} stays as it is,
     * so the result is still affine.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 addScaled(Float2x3R other, float weight) { return addScaled(other, weight, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12}) scaled by
     * {@code weight} to this matrix.
     * <p>
     * Only the stored elements take part: the implicit last row {@code (0, 0, 1)} stays as it is,
     * so the result is still affine.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param weight the factor to scale ({@code m00}, {@code m01}, {@code m02}, {@code m10},
     *        {@code m11}, {@code m12}) by before adding
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 addScaled(float m00, float m01, float m02, float m10, float m11, float m12, float weight) { return addScaled(m00, m01, m02, m10, m11, m12, weight, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Set this matrix to a rotation by {@code angle}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated Float2x3 makeRotation(float angle);

    /**
     * Set this matrix to a scaling transformation that scales by {@code v}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @return this
     */
    @Mutated Float2x3 makeScaling(Float2R v);

    /**
     * Set this matrix to a scaling transformation that scales by ({@code x}, {@code y}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @return this
     */
    @Mutated Float2x3 makeScaling(float x, float y);

    /**
     * Set this matrix to a scaling transformation that scales by {@code s}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @return this
     */
    @Mutated Float2x3 makeScaling(float s);

    /**
     * Set this matrix to a translation transformation that translates by {@code v}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @return this
     */
    @Mutated Float2x3 makeTranslation(Float2R v);

    /**
     * Set this matrix to a translation transformation that translates by ({@code x}, {@code y}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the translation offsets {@code (x, y)}
     * @param y the {@code y} component of the translation offsets {@code (x, y)}
     * @return this
     */
    @Mutated Float2x3 makeTranslation(float x, float y);

    /**
     * Set this matrix to the 2D view transformation that maps the rectangle
     * {@code [left, right] x [bottom, top]} onto {@code [-1, +1] x [-1, +1]}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ.
     *
     * @param left the distance to the left edge of the view rectangle
     * @param right the distance to the right edge of the view rectangle
     * @param bottom the distance to the bottom edge of the view rectangle
     * @param top the distance to the top edge of the view rectangle
     * @return this
     */
    @Mutated Float2x3 makeView(float left, float right, float bottom, float top);

    /**
     * Pre-multiply a rotation by {@code angle} onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 preRotate(float angle) { return preRotate(angle, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Pre-multiply the rotation {@code angle} about the pivot point {@code pivot} onto this matrix.
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
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 preRotateAround(float angle, Float2R pivot) { return preRotateAround(angle, pivot, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Pre-multiply the rotation {@code angle} about the pivot point ({@code x}, {@code y}) onto
     * this matrix.
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
     * @param angle the angle in radians
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 preRotateAround(float angle, float x, float y) { return preRotateAround(angle, x, y, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Pre-multiply a scaling by {@code v} onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code p} with the new matrix by using
     * {@code S * M * p}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 preScale(Float2R v) { return preScale(v, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Pre-multiply a scaling by ({@code x}, {@code y}) onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 preScale(float x, float y) { return preScale(x, y, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Pre-multiply a scaling by {@code s} onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 preScale(float s) { return preScale(s, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Pre-multiply a scaling by {@code s} about the pivot point {@code pivot} onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivot the pivot point
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 preScaleAround(float s, Float2R pivot) { return preScaleAround(s, pivot, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Pre-multiply a scaling by {@code s} about the pivot point ({@code x}, {@code y}) onto this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 preScaleAround(float s, float x, float y) { return preScaleAround(s, x, y, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Pre-multiply a scaling by {@code s} about the pivot point {@code pivot} onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the scale factors
     * @param pivot the pivot point
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 preScaleAround(Float2R s, Float2R pivot) { return preScaleAround(s, pivot, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Pre-multiply a scaling by ({@code sX}, {@code sY}) about the pivot point ({@code pivotX},
     * {@code pivotY}) onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sX the {@code x} component of the vector {@code (sX, sY)}
     * @param sY the {@code y} component of the vector {@code (sX, sY)}
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 preScaleAround(float sX, float sY, float pivotX, float pivotY) { return preScaleAround(sX, sY, pivotX, pivotY, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Pre-multiply a translation by {@code v} onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code T * M}. So when transforming a vector {@code p} with the new matrix by using
     * {@code T * M * p}, the translation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 preTranslate(Float2R v) { return preTranslate(v, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Pre-multiply a translation by ({@code x}, {@code y}) onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code T * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code T * M * v}, the translation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 preTranslate(float x, float y) { return preTranslate(x, y, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Apply a rotation by {@code angle} to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 rotate(float angle) { return rotate(angle, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Apply the rotation {@code angle} about the pivot point {@code pivot} to this matrix.
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
     * @param angle the angle in radians
     * @param pivot the pivot point
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 rotateAround(float angle, Float2R pivot) { return rotateAround(angle, pivot, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Apply the rotation {@code angle} about the pivot point ({@code x}, {@code y}) to this matrix.
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
     * @param angle the angle in radians
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 rotateAround(float angle, float x, float y) { return rotateAround(angle, x, y, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Apply a scaling by {@code v} to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code p} with the new matrix by using
     * {@code M * S * p}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 scale(Float2R v) { return scale(v, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Apply a scaling by ({@code x}, {@code y}) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 scale(float x, float y) { return scale(x, y, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Apply a scaling by {@code s} to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 scale(float s) { return scale(s, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Apply a scaling by {@code s} about the pivot point {@code pivot} to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivot the pivot point
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 scaleAround(float s, Float2R pivot) { return scaleAround(s, pivot, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Apply a scaling by {@code s} about the pivot point ({@code x}, {@code y}) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param x the {@code x} component of the vector {@code (x, y)}
     * @param y the {@code y} component of the vector {@code (x, y)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 scaleAround(float s, float x, float y) { return scaleAround(s, x, y, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Apply a scaling by {@code s} about the pivot point {@code pivot} to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the scale factors
     * @param pivot the pivot point
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 scaleAround(Float2R s, Float2R pivot) { return scaleAround(s, pivot, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Apply a scaling by ({@code sX}, {@code sY}) about the pivot point ({@code pivotX},
     * {@code pivotY}) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sX the {@code x} component of the vector {@code (sX, sY)}
     * @param sY the {@code y} component of the vector {@code (sX, sY)}
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 scaleAround(float sX, float sY, float pivotX, float pivotY) { return scaleAround(sX, sY, pivotX, pivotY, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Apply a translation by {@code v} to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code p} with the new matrix by using
     * {@code M * T * p}, the translation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 translate(Float2R v) { return translate(v, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Apply a translation by ({@code x}, {@code y}) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the translation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the translation offsets {@code (x, y)}
     * @param y the {@code y} component of the translation offsets {@code (x, y)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 translate(float x, float y) { return translate(x, y, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Apply a 2D view transformation that maps the rectangle {@code [left, right] x [bottom, top]}
     * onto {@code [-1, +1] x [-1, +1]} to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code V} the view matrix, then the new matrix will
     * be {@code M * V}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * V * v}, the view will be applied first.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ.
     *
     * @param left the distance to the left edge of the view rectangle
     * @param right the distance to the right edge of the view rectangle
     * @param bottom the distance to the bottom edge of the view rectangle
     * @param top the distance to the top edge of the view rectangle
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float2x3 view(float left, float right, float bottom, float top) { return view(left, right, bottom, top, Joml.RETURN_NEW ? Joml.float2x3() : this); }

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Float2x3 loadCM(float[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Float2x3 loadCM(float[] src, int offset);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified).
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
    @Mutated default Float2x3 loadCM(FloatBuffer src) { return loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified).
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
    @Mutated default Float2x3 loadCMAbsolute(FloatBuffer src) { return loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified).
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
    @Mutated Float2x3 loadCMAbsolute(int index, FloatBuffer src);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
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
    @Mutated default Float2x3 loadCMRelative(FloatBuffer src) {
        if (src.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src);
        src.position(pos + 6);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position (the position is not modified).
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
    @Mutated default Float2x3 loadCM(ByteBuffer src) { return loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position (the position is not modified).
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
    @Mutated default Float2x3 loadCMAbsolute(ByteBuffer src) { return loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified).
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
    @Mutated Float2x3 loadCMAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
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
    @Mutated default Float2x3 loadCMRelative(ByteBuffer src) {
        if (src.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src);
        src.position(pos + 24);
        return this;
    }

    /**
     * Load the elements from the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Float2x3 loadCMUnsafe(long address);

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Float2x3 loadCM(double[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Float2x3 loadCM(double[] src, int offset);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified).
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
    @Mutated default Float2x3 loadCM(DoubleBuffer src) { return loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified).
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
    @Mutated default Float2x3 loadCMAbsolute(DoubleBuffer src) { return loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified).
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
    @Mutated Float2x3 loadCMAbsolute(int index, DoubleBuffer src);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
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
    @Mutated default Float2x3 loadCMRelative(DoubleBuffer src) {
        if (src.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src);
        src.position(pos + 6);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at its current position (the position is not modified).
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
    @Mutated default Float2x3 loadCMDouble(ByteBuffer src) { return loadCMDoubleAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at its current position (the position is not modified).
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
    @Mutated default Float2x3 loadCMDoubleAbsolute(ByteBuffer src) { return loadCMDoubleAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at the given absolute index (the position is not used or
     * modified).
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
    @Mutated Float2x3 loadCMDoubleAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at its current position and advancing the position accordingly.
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
    @Mutated default Float2x3 loadCMDoubleRelative(ByteBuffer src) {
        if (src.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMDoubleAbsolute(pos, src);
        src.position(pos + 48);
        return this;
    }

    /**
     * Load the elements from the given raw memory address in column-major order, converting each
     * element from {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Float2x3 loadCMDoubleUnsafe(long address);

    /**
     * Load the elements from the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Float2x3 loadRM(float[] src) { return loadRM(src, 0); }

    /**
     * Load the elements from the given array in row-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Float2x3 loadRM(float[] src, int offset);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified).
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
    @Mutated default Float2x3 loadRM(FloatBuffer src) { return loadRMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified).
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
    @Mutated default Float2x3 loadRMAbsolute(FloatBuffer src) { return loadRMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in row-major order, starting at the given absolute
     * index (the position is not used or modified).
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
    @Mutated Float2x3 loadRMAbsolute(int index, FloatBuffer src);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * and advancing the position accordingly.
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
    @Mutated default Float2x3 loadRMRelative(FloatBuffer src) {
        if (src.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src);
        src.position(pos + 6);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at its current
     * position (the position is not modified).
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
    @Mutated default Float2x3 loadRM(ByteBuffer src) { return loadRMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at its current
     * position (the position is not modified).
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
    @Mutated default Float2x3 loadRMAbsolute(ByteBuffer src) { return loadRMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at the given
     * absolute index (the position is not used or modified).
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
    @Mutated Float2x3 loadRMAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer in row-major order, starting at its current
     * position and advancing the position accordingly.
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
    @Mutated default Float2x3 loadRMRelative(ByteBuffer src) {
        if (src.remaining() < 24) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src);
        src.position(pos + 24);
        return this;
    }

    /**
     * Load the elements from the given raw memory address in row-major order. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Float2x3 loadRMUnsafe(long address);

    /**
     * Load the elements from the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Float2x3 loadRM(double[] src) { return loadRM(src, 0); }

    /**
     * Load the elements from the given array in row-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Float2x3 loadRM(double[] src, int offset);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified).
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
    @Mutated default Float2x3 loadRM(DoubleBuffer src) { return loadRMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified).
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
    @Mutated default Float2x3 loadRMAbsolute(DoubleBuffer src) { return loadRMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in row-major order, starting at the given absolute
     * index (the position is not used or modified).
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
    @Mutated Float2x3 loadRMAbsolute(int index, DoubleBuffer src);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * and advancing the position accordingly.
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
    @Mutated default Float2x3 loadRMRelative(DoubleBuffer src) {
        if (src.remaining() < 6) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src);
        src.position(pos + 6);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified).
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
    @Mutated default Float2x3 loadRMDouble(ByteBuffer src) { return loadRMDoubleAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified).
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
    @Mutated default Float2x3 loadRMDoubleAbsolute(ByteBuffer src) { return loadRMDoubleAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified).
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
    @Mutated Float2x3 loadRMDoubleAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly.
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
    @Mutated default Float2x3 loadRMDoubleRelative(ByteBuffer src) {
        if (src.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMDoubleAbsolute(pos, src);
        src.position(pos + 48);
        return this;
    }

    /**
     * Load the elements from the given raw memory address in row-major order, converting each
     * element from {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Float2x3 loadRMDoubleUnsafe(long address);

    /**
     * Load the elements from the given array in column-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated Float2x3 loadCM(float[] src, int offset, int stride);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive columns.
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
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Float2x3 loadCM(FloatBuffer src, int stride) { return loadCMAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive columns.
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
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated Float2x3 loadCMAbsolute(int index, FloatBuffer src, int stride);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
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
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float2x3 loadCMRelative(FloatBuffer src, int stride) {
        if (src.remaining() < 3L * stride) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src, stride);
        src.position(pos + 3 * stride);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive columns.
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
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Float2x3 loadCM(ByteBuffer src, int stride) { return loadCMAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive columns.
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
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated Float2x3 loadCMAbsolute(int index, ByteBuffer src, int stride);

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
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
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float2x3 loadCMRelative(ByteBuffer src, int stride) {
        if (src.remaining() < 3L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src, stride);
        src.position(pos + (3 * stride) * 4);
        return this;
    }

    /**
     * Load the elements from the given raw memory address in column-major order, with
     * {@code stride} elements between the starts of consecutive columns. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Float2x3 loadCMUnsafe(long address, int stride);

    /**
     * Load the elements from the given array in column-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated Float2x3 loadCM(double[] src, int offset, int stride);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive columns.
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
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Float2x3 loadCM(DoubleBuffer src, int stride) { return loadCMAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive columns.
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
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated Float2x3 loadCMAbsolute(int index, DoubleBuffer src, int stride);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
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
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float2x3 loadCMRelative(DoubleBuffer src, int stride) {
        if (src.remaining() < 3L * stride) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src, stride);
        src.position(pos + 3 * stride);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at its current position (the position is not modified), with
     * {@code stride} elements between the starts of consecutive columns.
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
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Float2x3 loadCMDouble(ByteBuffer src, int stride) { return loadCMDoubleAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at the given absolute index (the position is not used or
     * modified), with {@code stride} elements between the starts of consecutive columns.
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
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated Float2x3 loadCMDoubleAbsolute(int index, ByteBuffer src, int stride);

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at its current position and advancing the position accordingly,
     * with {@code stride} elements between the starts of consecutive columns.
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
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float2x3 loadCMDoubleRelative(ByteBuffer src, int stride) {
        if (src.remaining() < 3L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMDoubleAbsolute(pos, src, stride);
        src.position(pos + (3 * stride) * 8);
        return this;
    }

    /**
     * Load the elements from the given raw memory address in column-major order, converting each
     * element from {@code double}, with {@code stride} elements between the starts of consecutive
     * columns. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Float2x3 loadCMDoubleUnsafe(long address, int stride);

    /**
     * Load the elements from the given array in row-major order, starting at the given offset, with
     * {@code stride} elements between the starts of consecutive rows.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated Float2x3 loadRM(float[] src, int offset, int stride);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified), with {@code stride} elements between the starts of
     * consecutive rows.
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
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated default Float2x3 loadRM(FloatBuffer src, int stride) { return loadRMAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given buffer in row-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive rows.
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
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated Float2x3 loadRMAbsolute(int index, FloatBuffer src, int stride);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * and advancing the position accordingly, with {@code stride} elements between the starts of
     * consecutive rows.
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
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float2x3 loadRMRelative(FloatBuffer src, int stride) {
        if (src.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src, stride);
        src.position(pos + 2 * stride);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive rows.
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
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated default Float2x3 loadRM(ByteBuffer src, int stride) { return loadRMAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive rows.
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
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated Float2x3 loadRMAbsolute(int index, ByteBuffer src, int stride);

    /**
     * Load the elements from the given byte buffer in row-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive rows.
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
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float2x3 loadRMRelative(ByteBuffer src, int stride) {
        if (src.remaining() < 2L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src, stride);
        src.position(pos + (2 * stride) * 4);
        return this;
    }

    /**
     * Load the elements from the given raw memory address in row-major order, with {@code stride}
     * elements between the starts of consecutive rows. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Float2x3 loadRMUnsafe(long address, int stride);

    /**
     * Load the elements from the given array in row-major order, starting at the given offset, with
     * {@code stride} elements between the starts of consecutive rows.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated Float2x3 loadRM(double[] src, int offset, int stride);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified), with {@code stride} elements between the starts of
     * consecutive rows.
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
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated default Float2x3 loadRM(DoubleBuffer src, int stride) { return loadRMAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given buffer in row-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive rows.
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
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated Float2x3 loadRMAbsolute(int index, DoubleBuffer src, int stride);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * and advancing the position accordingly, with {@code stride} elements between the starts of
     * consecutive rows.
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
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float2x3 loadRMRelative(DoubleBuffer src, int stride) {
        if (src.remaining() < 2L * stride) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src, stride);
        src.position(pos + 2 * stride);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified), with
     * {@code stride} elements between the starts of consecutive rows.
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
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated default Float2x3 loadRMDouble(ByteBuffer src, int stride) { return loadRMDoubleAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified),
     * with {@code stride} elements between the starts of consecutive rows.
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
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated Float2x3 loadRMDoubleAbsolute(int index, ByteBuffer src, int stride);

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly, with
     * {@code stride} elements between the starts of consecutive rows.
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
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float2x3 loadRMDoubleRelative(ByteBuffer src, int stride) {
        if (src.remaining() < 2L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMDoubleAbsolute(pos, src, stride);
        src.position(pos + (2 * stride) * 8);
        return this;
    }

    /**
     * Load the elements from the given raw memory address in row-major order, converting each
     * element from {@code double}, with {@code stride} elements between the starts of consecutive
     * rows. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated Float2x3 loadRMDoubleUnsafe(long address, int stride);

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Float2x3 load(float[] src) { return loadCM(src); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated default Float2x3 load(float[] src, int offset) { return loadCM(src, offset); }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified).
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
    @Mutated default Float2x3 load(FloatBuffer src) { return loadCMAbsolute(src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified).
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
    @Mutated default Float2x3 load(int index, FloatBuffer src) { return loadCMAbsolute(index, src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
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
    @Mutated default Float2x3 loadRelative(FloatBuffer src) { return loadCMRelative(src); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Float2x3 load(double[] src) { return loadCM(src); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code double}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated default Float2x3 load(double[] src, int offset) { return loadCM(src, offset); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified).
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
    @Mutated default Float2x3 load(DoubleBuffer src) { return loadCMAbsolute(src); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified).
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
    @Mutated default Float2x3 load(int index, DoubleBuffer src) { return loadCMAbsolute(index, src); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly.
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
    @Mutated default Float2x3 loadRelative(DoubleBuffer src) { return loadCMRelative(src); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position (the position is not modified).
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
    @Mutated default Float2x3 load(ByteBuffer src) { return loadCMAbsolute(src); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified).
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
    @Mutated default Float2x3 load(int index, ByteBuffer src) { return loadCMAbsolute(index, src); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
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
    @Mutated default Float2x3 loadRelative(ByteBuffer src) { return loadCMRelative(src); }

    /**
     * Load the elements from the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated default Float2x3 loadUnsafe(long address) { return loadCMUnsafe(address); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Float2x3 load(float[] src, int offset, int stride) { return loadCM(src, offset, stride); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code double}, starting at the given offset, with {@code stride} elements between the starts
     * of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Float2x3 load(double[] src, int offset, int stride) { return loadCM(src, offset, stride); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive columns.
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
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Float2x3 load(int index, FloatBuffer src, int stride) { return loadCMAbsolute(index, src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
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
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float2x3 loadRelative(FloatBuffer src, int stride) { return loadCMRelative(src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified),
     * with {@code stride} elements between the starts of consecutive columns.
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
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Float2x3 load(int index, DoubleBuffer src, int stride) { return loadCMAbsolute(index, src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly, with
     * {@code stride} elements between the starts of consecutive columns.
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
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float2x3 loadRelative(DoubleBuffer src, int stride) { return loadCMRelative(src, stride); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive columns.
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
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Float2x3 load(int index, ByteBuffer src, int stride) { return loadCMAbsolute(index, src, stride); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
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
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Float2x3 loadRelative(ByteBuffer src, int stride) { return loadCMRelative(src, stride); }

    /**
     * Load the elements from the given raw memory address in column-major order, with
     * {@code stride} elements between the starts of consecutive columns. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    @Mutated default Float2x3 loadUnsafe(long address, int stride) { return loadCMUnsafe(address, stride); }
}
