// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import java.nio.ByteBuffer;

/**
 * A 3x4 matrix of single-precision {@code float} components.
 * <p>
 * Extends the read-only view {@link Float3x4R} with the mutating surface: in-place self-forms,
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
public interface Float3x4 extends Float3x4R {

    /**
     * Invert this affine matrix, i.e. compute the inverse of the implied square homogeneous matrix.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 invert() { return invert(Joml.RETURN_NEW ? Joml.float3x4() : this); }

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
    @Mutated default Float3x4 invertProduct(Float3x4R other) { return invertProduct(other, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Compute the inverse of the product of this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m03}, {@code m10}, {@code m11}, {@code m12}, {@code m13}, {@code m20}, {@code m21},
     * {@code m22}, {@code m23}).
     * <p>
     * The product is formed first and inverted afterwards, so the result is the inverse of the
     * rounded product: its accuracy is bounded by the condition number of the product, not by the
     * condition numbers of the two factors. For an ill-conditioned product (a near-singular factor,
     * or factors of very different scale) invert both factors separately and multiply the inverses
     * in reverse order instead.
     * <p>
     * Valid input: the product of this matrix and
     * {@code (m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23)} must be invertible.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param m23 the element in row 2, column 3 of the matrix
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 invertProduct(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23) { return invertProduct(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Add {@code other} to this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to add
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 add(Float3x4R other) { return add(other, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11},
     * {@code m12}, {@code m13}, {@code m20}, {@code m21}, {@code m22}, {@code m23}) to this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param m23 the element in row 2, column 3 of the matrix
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 add(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23) { return add(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Multiply each component of this matrix by {@code scalar}.
     * <p>
     * Only the stored elements take part: the implicit last row {@code (0, 0, 0, 1)} stays as it
     * is, so the result is still affine.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mul(float scalar) { return mul(scalar, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Negate this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 negate() { return negate(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Subtract {@code other} from this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to subtract
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 sub(Float3x4R other) { return sub(other, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Subtract ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11},
     * {@code m12}, {@code m13}, {@code m20}, {@code m21}, {@code m22}, {@code m23}) from this
     * matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param m23 the element in row 2, column 3 of the matrix
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 sub(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23) { return sub(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Set this matrix to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the matrix to copy
     * @return this
     */
    @Mutated Float3x4 set(Float3x4R v);

    /**
     * Set this matrix to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param m23 the element in row 2, column 3 of the matrix
     * @return this
     */
    @Mutated Float3x4 set(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23);

    /**
     * Set this matrix to the given 3x3 matrix, copying the overlapping cells and filling the rest
     * with identity.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to copy from
     * @return this
     */
    @Mutated Float3x4 set(Float3x3R m);

    /**
     * Set this matrix to the given 4x4 matrix, copying the overlapping cells and dropping the rest.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to copy from
     * @return this
     */
    @Mutated Float3x4 set(Float4x4R m);

    /**
     * Set the translation of this matrix, leaving every other element untouched.
     * <p>
     * Only the first three elements of the last column are written, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this overwrites the
     * translation instead of composing a translation onto the existing transformation.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the translation offsets
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 withTranslation(Float3R t) { return withTranslation(t, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Set the translation of this matrix, leaving every other element untouched.
     * <p>
     * Only the first three elements of the last column are written, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this overwrites the
     * translation instead of composing a translation onto the existing transformation.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the translation offsets {@code (x, y, z)}
     * @param y the {@code y} component of the translation offsets {@code (x, y, z)}
     * @param z the {@code z} component of the translation offsets {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 withTranslation(float x, float y, float z) { return withTranslation(x, y, z, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Convert this matrix to {@code double} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Double3x4} holding the result
     */
    default Double3x4 toDouble() { return toDouble(Joml.double3x4()); }

    /**
     * Set this matrix to the given rigid transform's {@code T * R} composition.
     * <p>
     * Valid input: the rotation of {@code r} must have unit length.
     *
     * @param r the rigid transform to convert
     * @return this
     */
    @Mutated Float3x4 makeFromRigid(FloatRigidR r);

    /**
     * Set this matrix to the given rigid transform's {@code T * R} composition.
     * <p>
     * Valid input: {@code (rX, rY, rZ, rW)} must have unit length.
     *
     * @param tX the {@code tX} component of the rigid transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW)}
     * @param tY the {@code tY} component of the rigid transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW)}
     * @param tZ the {@code tZ} component of the rigid transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW)}
     * @param rX the {@code rX} component of the rigid transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW)}
     * @param rY the {@code rY} component of the rigid transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW)}
     * @param rZ the {@code rZ} component of the rigid transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW)}
     * @param rW the {@code rW} component of the rigid transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW)}
     * @return this
     */
    @Mutated Float3x4 makeFromRigid(float tX, float tY, float tZ, float rX, float rY, float rZ, float rW);

    /**
     * Set this matrix to the given transform's {@code T * R * S} composition.
     * <p>
     * Valid input: the rotation of {@code t} must have unit length.
     *
     * @param t the transform to convert
     * @return this
     */
    @Mutated Float3x4 makeFromTransform(FloatTransformR t);

    /**
     * Set this matrix to the given transform's {@code T * R * S} composition.
     * <p>
     * Valid input: {@code (rX, rY, rZ, rW)} must have unit length.
     *
     * @param tX the {@code tX} component of the transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ)}
     * @param tY the {@code tY} component of the transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ)}
     * @param tZ the {@code tZ} component of the transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ)}
     * @param rX the {@code rX} component of the transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ)}
     * @param rY the {@code rY} component of the transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ)}
     * @param rZ the {@code rZ} component of the transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ)}
     * @param rW the {@code rW} component of the transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ)}
     * @param sX the {@code sX} component of the transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ)}
     * @param sY the {@code sY} component of the transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ)}
     * @param sZ the {@code sZ} component of the transform
     *        {@code (tX, tY, tZ, rX, rY, rZ, rW, sX, sY, sZ)}
     * @return this
     */
    @Mutated Float3x4 makeFromTransform(float tX, float tY, float tZ, float rX, float rY, float rZ, float rW, float sX, float sY, float sZ);

    /**
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeIdentity();

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
    @Mutated default Float3x4 lerp(Float3x4R other, float t) { return lerp(other, t, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m03}, {@code m10}, {@code m11}, {@code m12}, {@code m13}, {@code m20}, {@code m21},
     * {@code m22}, {@code m23}) using the interpolation factor {@code t}.
     * <p>
     * The interpolation starts at this matrix (interpolation factor {@code 0}) and ends at
     * ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11}, {@code m12},
     * {@code m13}, {@code m20}, {@code m21}, {@code m22}, {@code m23}) (interpolation factor
     * {@code 1}). Each linearly interpolated component is {@code this + (other - this) * t}, as in
     * JOML and glMatrix: monotone in {@code t} and exact at {@code 0}, but at {@code 1} exact only
     * up to the rounding of {@code other - this}, which shows when this component is much larger in
     * magnitude than the other one (in {@code float}, 1e8 towards 1 ends at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param m23 the element in row 2, column 3 of the matrix
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 lerp(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23, float t) { return lerp(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, t, Joml.RETURN_NEW ? Joml.float3x4() : this); }

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
    @Mutated default Float3x4 mul(Float3x4R right) { return mul(right, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Multiply this matrix by ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10},
     * {@code m11}, {@code m12}, {@code m13}, {@code m20}, {@code m21}, {@code m22}, {@code m23}).
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
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param m23 the element in row 2, column 3 of the matrix
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mul(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23) { return mul(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, Joml.RETURN_NEW ? Joml.float3x4() : this); }

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
    @Mutated default Float3x4 mul(Float2x2R right) { return mul(right, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Multiply this matrix by {@code right}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * The 2D affine operand acts in the xy-plane: its linear part fills the upper-left 2x2 block
     * and its translation the x and y translation, while z passes through unchanged.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mul(Float2x3R right) { return mul(right, Joml.RETURN_NEW ? Joml.float3x4() : this); }

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
    @Mutated default Float3x4 mul(Float3x3R right) { return mul(right, Joml.RETURN_NEW ? Joml.float3x4() : this); }

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
    @Mutated default Float3x4 preMul(Float3x4R other) { return preMul(other, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Pre-multiply the transformation ({@code m00}, {@code m01}, {@code m02}, {@code m03},
     * {@code m10}, {@code m11}, {@code m12}, {@code m13}, {@code m20}, {@code m21}, {@code m22},
     * {@code m23}) onto this matrix.
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
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param m23 the element in row 2, column 3 of the matrix
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 preMul(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23) { return preMul(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, Joml.RETURN_NEW ? Joml.float3x4() : this); }

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
    @Mutated default Float3x4 preMul(Float2x2R other) { return preMul(other, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Pre-multiply {@code other} onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the transformation of the operand will be applied last.
     * <p>
     * The 2D affine operand acts in the xy-plane: its linear part fills the upper-left 2x2 block
     * and its translation the x and y translation, while z passes through unchanged.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 preMul(Float2x3R other) { return preMul(other, Joml.RETURN_NEW ? Joml.float3x4() : this); }

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
    @Mutated default Float3x4 preMul(Float3x3R other) { return preMul(other, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Add {@code other} scaled by {@code weight} to this matrix.
     * <p>
     * Only the stored elements take part: the implicit last row {@code (0, 0, 0, 1)} stays as it
     * is, so the result is still affine.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 addScaled(Float3x4R other, float weight) { return addScaled(other, weight, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11},
     * {@code m12}, {@code m13}, {@code m20}, {@code m21}, {@code m22}, {@code m23}) scaled by
     * {@code weight} to this matrix.
     * <p>
     * Only the stored elements take part: the implicit last row {@code (0, 0, 0, 1)} stays as it
     * is, so the result is still affine.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m03 the element in row 0, column 3 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m13 the element in row 1, column 3 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param m23 the element in row 2, column 3 of the matrix
     * @param weight the factor to scale ({@code m00}, {@code m01}, {@code m02}, {@code m03},
     *        {@code m10}, {@code m11}, {@code m12}, {@code m13}, {@code m20}, {@code m21},
     *        {@code m22}, {@code m23}) by before adding
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 addScaled(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23, float weight) { return addScaled(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, weight, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Set this matrix to a transformation composed of the given translation, rotation and scale,
     * applied in scale-rotation-translation order.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param translation the translation
     * @param rotation the rotation
     * @param scale the scale factors
     * @return this
     */
    @Mutated Float3x4 composeTRS(Float3R translation, FloatQuatR rotation, Float3R scale);

    /**
     * Set this matrix to a transformation composed of the given translation, rotation and scale,
     * applied in scale-rotation-translation order.
     * <p>
     * Valid input: {@code (rotationX, rotationY, rotationZ, rotationW)} must have unit length.
     *
     * @param translationX the {@code x} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationY the {@code y} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationZ the {@code z} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param rotationX the {@code x} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationY the {@code y} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationZ the {@code z} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationW the {@code w} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param scaleX the {@code x} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @param scaleY the {@code y} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @param scaleZ the {@code z} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @return this
     */
    @Mutated Float3x4 composeTRS(float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ);

    /**
     * Set this matrix to a transformation that scales and rotates about the pivot point
     * {@code pivot} and then translates by {@code translation}, i.e.
     * {@code T(translation) * T(pivot) * R(rotation) * S(scale) * T(-pivot)}: the pivot point
     * itself only moves by the translation.
     * <p>
     * The translation part, {@code translation + pivot - R * S * pivot}, is evaluated so that it
     * keeps its accuracy - the given translation included - for pivots far from the origin.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param translation the translation
     * @param rotation the rotation
     * @param scale the scale factors
     * @param pivot the pivot point
     * @return this
     */
    @Mutated Float3x4 composeTRSAround(Float3R translation, FloatQuatR rotation, Float3R scale, Float3R pivot);

    /**
     * Set this matrix to a transformation that scales and rotates about the pivot point
     * ({@code pivotX}, {@code pivotY}, {@code pivotZ}) and then translates by
     * ({@code translationX}, {@code translationY}, {@code translationZ}), i.e.
     * {@code T(translation) * T(pivot) * R(rotation) * S(scale) * T(-pivot)}: the pivot point
     * itself only moves by the translation.
     * <p>
     * The translation part, {@code translation + pivot - R * S * pivot}, is evaluated so that it
     * keeps its accuracy - the given translation included - for pivots far from the origin.
     * <p>
     * Valid input: {@code (rotationX, rotationY, rotationZ, rotationW)} must have unit length.
     *
     * @param translationX the {@code x} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationY the {@code y} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationZ the {@code z} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param rotationX the {@code x} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationY the {@code y} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationZ the {@code z} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationW the {@code w} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param scaleX the {@code x} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @param scaleY the {@code y} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @param scaleZ the {@code z} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotZ the {@code z} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @return this
     */
    @Mutated Float3x4 composeTRSAround(float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ, float pivotX, float pivotY, float pivotZ);

    /**
     * Set this matrix to a transformation composed of the given translation, rotation and scale
     * (applied in scale-rotation-translation order), post-multiplied by the given matrix.
     * <p>
     * Valid input: {@code rotation} must have unit length.
     *
     * @param translation the translation
     * @param rotation the rotation
     * @param scale the scale factors
     * @param m the matrix to post-multiply the composed transformation by
     * @return this
     */
    @Mutated Float3x4 composeTRSMul(Float3R translation, FloatQuatR rotation, Float3R scale, Float3x4R m);

    /**
     * Set this matrix to a transformation composed of the given translation, rotation and scale
     * (applied in scale-rotation-translation order), post-multiplied by the given matrix.
     * <p>
     * Valid input: {@code (rotationX, rotationY, rotationZ, rotationW)} must have unit length.
     *
     * @param translationX the {@code x} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationY the {@code y} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param translationZ the {@code z} component of the vector
     *        {@code (translationX, translationY, translationZ)}
     * @param rotationX the {@code x} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationY the {@code y} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationZ the {@code z} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param rotationW the {@code w} component of the quaternion
     *        {@code (rotationX, rotationY, rotationZ, rotationW)}
     * @param scaleX the {@code x} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @param scaleY the {@code y} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @param scaleZ the {@code z} component of the vector {@code (scaleX, scaleY, scaleZ)}
     * @param m the matrix to post-multiply the composed transformation by
     * @return this
     */
    @Mutated Float3x4 composeTRSMul(float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ, Float3x4R m);

    /**
     * Apply a rotation transformation that makes {@code +z} point along {@code dir} to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code L} the "look along" matrix, then the new
     * matrix will be {@code M * L}. So when transforming a vector {@code v} with the new matrix by
     * using {@code M * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * write zero rows for degenerate input instead.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction the local {@code +z} axis is
     *        mapped to
     * @param up the direction of "up"
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 lookAlong(Float3R dir, Float3R up) { return lookAlong(dir, up, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation transformation that makes {@code +z} point along ({@code dirX},
     * {@code dirY}, {@code dirZ}) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code L} the "look along" matrix, then the new
     * matrix will be {@code M * L}. So when transforming a vector {@code v} with the new matrix by
     * using {@code M * L * v}, the "look along" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * write zero rows for degenerate input instead.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) { return lookAlong(dirX, dirY, dirZ, upX, upY, upZ, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a "look at" view transformation with the eye at {@code eye} looking at {@code center}
     * to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code L} the "look at" matrix, then the new matrix
     * will be {@code M * L}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * L * v}, the "look at" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * write zero rows for degenerate input instead.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param eye the position of the camera
     * @param center the point in space to look at
     * @param up the direction of "up"
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 lookAt(Float3R eye, Float3R center, Float3R up, Handedness handedness) { return lookAt(eye, center, up, handedness, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a "look at" view transformation with the eye at ({@code eyeX}, {@code eyeY},
     * {@code eyeZ}) looking at ({@code centerX}, {@code centerY}, {@code centerZ}) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code L} the "look at" matrix, then the new matrix
     * will be {@code M * L}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * L * v}, the "look at" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * write zero rows for degenerate input instead.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param eyeX the {@code x} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param eyeY the {@code y} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param eyeZ the {@code z} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param centerX the {@code x} component of the vector {@code (centerX, centerY, centerZ)}
     * @param centerY the {@code y} component of the vector {@code (centerX, centerY, centerZ)}
     * @param centerZ the {@code z} component of the vector {@code (centerX, centerY, centerZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 lookAt(float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, Handedness handedness) { return lookAt(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, handedness, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a "look at" view transformation with the eye at {@code eye} looking at {@code center}
     * to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code L} the "look at" matrix, then the new matrix
     * will be {@code M * L}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * L * v}, the "look at" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * write zero rows for degenerate input instead.)
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param eye the position of the camera
     * @param center the point in space to look at
     * @param up the direction of "up"
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 lookAt(Float3R eye, Float3R center, Float3R up) { return lookAt(eye, center, up, Handedness.RIGHT_HANDED, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a "look at" view transformation with the eye at ({@code eyeX}, {@code eyeY},
     * {@code eyeZ}) looking at ({@code centerX}, {@code centerY}, {@code centerZ}) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code L} the "look at" matrix, then the new matrix
     * will be {@code M * L}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * L * v}, the "look at" will be applied first.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * write zero rows for degenerate input instead.)
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param eyeX the {@code x} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param eyeY the {@code y} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param eyeZ the {@code z} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param centerX the {@code x} component of the vector {@code (centerX, centerY, centerZ)}
     * @param centerY the {@code y} component of the vector {@code (centerX, centerY, centerZ)}
     * @param centerZ the {@code z} component of the vector {@code (centerX, centerY, centerZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 lookAt(float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) { return lookAt(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, Handedness.RIGHT_HANDED, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Set this matrix to a cylindrical billboard transformation that rotates about the given axis
     * to face the camera.
     * <p>
     * Degenerate input still gives a proper rotation: a target on the up axis (to within about 64
     * units in the last place) or at the object's position leaves the rotation about that axis
     * free, and a direction perpendicular to {@code up} is chosen. The rotation is orthonormal to
     * working precision for a target however close to the axis. (The raw-storage {@code *Ops}
     * kernels write zero rows for degenerate input instead.)
     * <p>
     * Valid input: {@code up} must have unit length.
     *
     * @param objPos the position of the object to orient
     * @param targetPos the position to face (e.g. the camera position)
     * @param up the direction of "up"
     * @return this
     */
    @Mutated Float3x4 makeBillboardCylindrical(Float3R objPos, Float3R targetPos, Float3R up);

    /**
     * Set this matrix to a cylindrical billboard transformation that rotates about the given axis
     * to face the camera.
     * <p>
     * Degenerate input still gives a proper rotation: a target on the up axis (to within about 64
     * units in the last place) or at the object's position leaves the rotation about that axis
     * free, and a direction perpendicular to {@code up} is chosen. The rotation is orthonormal to
     * working precision for a target however close to the axis. (The raw-storage {@code *Ops}
     * kernels write zero rows for degenerate input instead.)
     * <p>
     * Valid input: {@code (upX, upY, upZ)} must have unit length.
     *
     * @param objPosX the {@code x} component of the vector {@code (objPosX, objPosY, objPosZ)}
     * @param objPosY the {@code y} component of the vector {@code (objPosX, objPosY, objPosZ)}
     * @param objPosZ the {@code z} component of the vector {@code (objPosX, objPosY, objPosZ)}
     * @param targetPosX the {@code x} component of the vector
     *        {@code (targetPosX, targetPosY, targetPosZ)}
     * @param targetPosY the {@code y} component of the vector
     *        {@code (targetPosX, targetPosY, targetPosZ)}
     * @param targetPosZ the {@code z} component of the vector
     *        {@code (targetPosX, targetPosY, targetPosZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @return this
     */
    @Mutated Float3x4 makeBillboardCylindrical(float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ, float upX, float upY, float upZ);

    /**
     * Set this matrix to a spherical billboard transformation that faces the camera.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * write zero rows for degenerate input instead.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param objPos the position of the object to orient
     * @param targetPos the position to face (e.g. the camera position)
     * @param up the direction of "up"
     * @return this
     */
    @Mutated Float3x4 makeBillboardSpherical(Float3R objPos, Float3R targetPos, Float3R up);

    /**
     * Set this matrix to a spherical billboard transformation that faces the camera.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * write zero rows for degenerate input instead.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param objPosX the {@code x} component of the vector {@code (objPosX, objPosY, objPosZ)}
     * @param objPosY the {@code y} component of the vector {@code (objPosX, objPosY, objPosZ)}
     * @param objPosZ the {@code z} component of the vector {@code (objPosX, objPosY, objPosZ)}
     * @param targetPosX the {@code x} component of the vector
     *        {@code (targetPosX, targetPosY, targetPosZ)}
     * @param targetPosY the {@code y} component of the vector
     *        {@code (targetPosX, targetPosY, targetPosZ)}
     * @param targetPosZ the {@code z} component of the vector
     *        {@code (targetPosX, targetPosY, targetPosZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @return this
     */
    @Mutated Float3x4 makeBillboardSpherical(float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ, float upX, float upY, float upZ);

    /**
     * Set this matrix to a spherical billboard transformation that faces the camera.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * write zero rows for degenerate input instead.)
     * <p>
     * Alias for {@code makeBillboardSpherical}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param objPos the position of the object to orient
     * @param targetPos the position to face (e.g. the camera position)
     * @param up the direction of "up"
     * @return this
     */
    @Mutated default Float3x4 targetTo(Float3R objPos, Float3R targetPos, Float3R up) { return makeBillboardSpherical(objPos, targetPos, up); }

    /**
     * Set this matrix to a spherical billboard transformation that faces the camera.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * write zero rows for degenerate input instead.)
     * <p>
     * Alias for {@code makeBillboardSpherical}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param objPosX the {@code x} component of the vector {@code (objPosX, objPosY, objPosZ)}
     * @param objPosY the {@code y} component of the vector {@code (objPosX, objPosY, objPosZ)}
     * @param objPosZ the {@code z} component of the vector {@code (objPosX, objPosY, objPosZ)}
     * @param targetPosX the {@code x} component of the vector
     *        {@code (targetPosX, targetPosY, targetPosZ)}
     * @param targetPosY the {@code y} component of the vector
     *        {@code (targetPosX, targetPosY, targetPosZ)}
     * @param targetPosZ the {@code z} component of the vector
     *        {@code (targetPosX, targetPosY, targetPosZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @return this
     */
    @Mutated default Float3x4 targetTo(float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ, float upX, float upY, float upZ) { return makeBillboardSpherical(objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ, upX, upY, upZ); }

    /**
     * Set this matrix to a spherical billboard transformation that faces the camera, using the
     * rotation of shortest arc.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param objPos the position of the object to orient
     * @param targetPos the position to face (e.g. the camera position)
     * @return this
     */
    @Mutated Float3x4 makeBillboardSphericalShortest(Float3R objPos, Float3R targetPos);

    /**
     * Set this matrix to a spherical billboard transformation that faces the camera, using the
     * rotation of shortest arc.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param objPosX the {@code x} component of the vector {@code (objPosX, objPosY, objPosZ)}
     * @param objPosY the {@code y} component of the vector {@code (objPosX, objPosY, objPosZ)}
     * @param objPosZ the {@code z} component of the vector {@code (objPosX, objPosY, objPosZ)}
     * @param targetPosX the {@code x} component of the vector
     *        {@code (targetPosX, targetPosY, targetPosZ)}
     * @param targetPosY the {@code y} component of the vector
     *        {@code (targetPosX, targetPosY, targetPosZ)}
     * @param targetPosZ the {@code z} component of the vector
     *        {@code (targetPosX, targetPosY, targetPosZ)}
     * @return this
     */
    @Mutated Float3x4 makeBillboardSphericalShortest(float objPosX, float objPosY, float objPosZ, float targetPosX, float targetPosY, float targetPosZ);

    /**
     * Set this matrix to the rigid transformation represented by the unit dual quaternion
     * {@code dq}.
     * <p>
     * Valid input: {@code dq} must be a unit dual quaternion.
     *
     * @param dq the dual quaternion to convert
     * @return this
     */
    @Mutated Float3x4 makeFromDualQuat(FloatDualQuatR dq);

    /**
     * Set this matrix to the rigid transformation represented by the unit dual quaternion
     * ({@code rX}, {@code rY}, {@code rZ}, {@code rW}, {@code dX}, {@code dY}, {@code dZ},
     * {@code dW}).
     * <p>
     * Valid input: {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)} must be a unit dual quaternion.
     *
     * @param rX the {@code rX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rY the {@code rY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rZ the {@code rZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param rW the {@code rW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dX the {@code dX} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dY the {@code dY} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dZ the {@code dZ} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @param dW the {@code dW} component of the dual quaternion
     *        {@code (rX, rY, rZ, rW, dX, dY, dZ, dW)}
     * @return this
     */
    @Mutated Float3x4 makeFromDualQuat(float rX, float rY, float rZ, float rW, float dX, float dY, float dZ, float dW);

    /**
     * Set this matrix to a "look at" view transformation with the eye at {@code eye} looking at
     * {@code center}.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * write zero rows for degenerate input instead.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param eye the position of the camera
     * @param center the point in space to look at
     * @param up the direction of "up"
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated Float3x4 makeLookAt(Float3R eye, Float3R center, Float3R up, Handedness handedness);

    /**
     * Set this matrix to a "look at" view transformation with the eye at ({@code eyeX},
     * {@code eyeY}, {@code eyeZ}) looking at ({@code centerX}, {@code centerY}, {@code centerZ}).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * write zero rows for degenerate input instead.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param eyeX the {@code x} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param eyeY the {@code y} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param eyeZ the {@code z} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param centerX the {@code x} component of the vector {@code (centerX, centerY, centerZ)}
     * @param centerY the {@code y} component of the vector {@code (centerX, centerY, centerZ)}
     * @param centerZ the {@code z} component of the vector {@code (centerX, centerY, centerZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated Float3x4 makeLookAt(float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, Handedness handedness);

    /**
     * Set this matrix to a "look at" view transformation with the eye at {@code eye} looking at
     * {@code center}.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * write zero rows for degenerate input instead.)
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param eye the position of the camera
     * @param center the point in space to look at
     * @param up the direction of "up"
     * @return this
     */
    @Mutated default Float3x4 makeLookAt(Float3R eye, Float3R center, Float3R up) { return makeLookAt(eye, center, up, Handedness.RIGHT_HANDED); }

    /**
     * Set this matrix to a "look at" view transformation with the eye at ({@code eyeX},
     * {@code eyeY}, {@code eyeZ}) looking at ({@code centerX}, {@code centerY}, {@code centerZ}).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * write zero rows for degenerate input instead.)
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param eyeX the {@code x} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param eyeY the {@code y} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param eyeZ the {@code z} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param centerX the {@code x} component of the vector {@code (centerX, centerY, centerZ)}
     * @param centerY the {@code y} component of the vector {@code (centerX, centerY, centerZ)}
     * @param centerZ the {@code z} component of the vector {@code (centerX, centerY, centerZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @return this
     */
    @Mutated default Float3x4 makeLookAt(float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) { return makeLookAt(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, Handedness.RIGHT_HANDED); }

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+x, +y, +z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingXYZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+x, +y, -z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingXYnZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+x, +z, +y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingXZY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+x, +z, -y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingXZnY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+x, -y, +z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingXnYZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+x, -y, -z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingXnYnZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+x, -z, +y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingXnZY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+x, -z, -y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingXnZnY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+y, +x, +z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingYXZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+y, +x, -z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingYXnZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+y, +z, +x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingYZX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+y, +z, -x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingYZnX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+y, -x, +z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingYnXZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+y, -x, -z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingYnXnZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+y, -z, +x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingYnZX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+y, -z, -x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingYnZnX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+z, +x, +y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingZXY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+z, +x, -y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingZXnY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+z, +y, +x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingZYX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+z, +y, -x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingZYnX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+z, -x, +y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingZnXY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+z, -x, -y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingZnXnY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+z, -y, +x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingZnYX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+z, -y, -x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingZnYnX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-x, +y, +z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnXYZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-x, +y, -z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnXYnZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-x, +z, +y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnXZY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-x, +z, -y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnXZnY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-x, -y, +z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnXnYZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-x, -y, -z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnXnYnZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-x, -z, +y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnXnZY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-x, -z, -y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnXnZnY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-y, +x, +z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnYXZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-y, +x, -z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnYXnZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-y, +z, +x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnYZX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-y, +z, -x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnYZnX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-y, -x, +z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnYnXZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-y, -x, -z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnYnXnZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-y, -z, +x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnYnZX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-y, -z, -x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnYnZnX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-z, +x, +y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnZXY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-z, +x, -y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnZXnY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-z, +y, +x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnZYX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-z, +y, -x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnZYnX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-z, -x, +y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnZnXY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-z, -x, -y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnZnXnY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-z, -y, +x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnZnYX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-z, -y, -x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Float3x4 makeMappingnZnYnX();

    /**
     * Set this matrix to a reflection transformation about the plane through the origin with the
     * normal {@code normal}.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the reflection plane
     * @return this
     */
    @Mutated Float3x4 makeReflection(Float3R normal);

    /**
     * Set this matrix to a reflection transformation about the plane through the origin with the
     * normal ({@code x}, {@code y}, {@code z}).
     * <p>
     * Valid input: {@code (x, y, z)} must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this
     */
    @Mutated Float3x4 makeReflection(float x, float y, float z);

    /**
     * Set this matrix to a rotation of {@code angle} radians about the axis {@code axis}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return this
     */
    @Mutated Float3x4 makeRotationAxis(float angle, Float3R axis);

    /**
     * Set this matrix to a rotation of {@code angle} radians about the axis ({@code x}, {@code y},
     * {@code z}).
     * <p>
     * Valid input: {@code (x, y, z)} must have unit length.
     *
     * @param angle the angle in radians
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this
     */
    @Mutated Float3x4 makeRotationAxis(float angle, float x, float y, float z);

    /**
     * Set this matrix to a rotation that makes {@code +z} point along {@code dir}.
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * write zero rows for degenerate input instead.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dir the direction to look along, i.e. the direction the local {@code +z} axis is
     *        mapped to
     * @param up the direction of "up"
     * @return this
     */
    @Mutated Float3x4 makeRotationLookAlong(Float3R dir, Float3R up);

    /**
     * Set this matrix to a rotation that makes {@code +z} point along ({@code dirX}, {@code dirY},
     * {@code dirZ}).
     * <p>
     * Degenerate input still gives a proper rotation: an up vector parallel to the view direction
     * (to within about 64 units in the last place) or zero is replaced by one perpendicular to it,
     * and a zero view direction (coinciding points) gives the identity orientation; NaN input gives
     * NaN. The rotation is orthonormal to working precision for vectors of any finite length, an up
     * vector however close to the view direction included. (The raw-storage {@code *Ops} kernels
     * write zero rows for degenerate input instead.)
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dirX the {@code x} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirY the {@code y} component of the vector {@code (dirX, dirY, dirZ)}
     * @param dirZ the {@code z} component of the vector {@code (dirX, dirY, dirZ)}
     * @param upX the {@code x} component of the vector {@code (upX, upY, upZ)}
     * @param upY the {@code y} component of the vector {@code (upX, upY, upZ)}
     * @param upZ the {@code z} component of the vector {@code (upX, upY, upZ)}
     * @return this
     */
    @Mutated Float3x4 makeRotationLookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ);

    /**
     * Set this matrix to the rotation represented by the quaternion {@code q}.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the rotation quaternion
     * @return this
     */
    @Mutated Float3x4 makeRotationQuat(FloatQuatR q);

    /**
     * Set this matrix to the rotation represented by the quaternion ({@code x}, {@code y},
     * {@code z}, {@code w}).
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @return this
     */
    @Mutated Float3x4 makeRotationQuat(float x, float y, float z, float w);

    /**
     * Set this matrix to a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated Float3x4 makeRotationX(float angle);

    /**
     * Set this matrix to a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians
     * about the X, Y and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a
     * vector is rotated about the Z axis first, then Y, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return this
     */
    @Mutated Float3x4 makeRotationXYZ(float angleX, float angleY, float angleZ);

    /**
     * Set this matrix to a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians
     * about the X, Z and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a
     * vector is rotated about the Y axis first, then Z, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this
     */
    @Mutated Float3x4 makeRotationXZY(float angleX, float angleZ, float angleY);

    /**
     * Set this matrix to a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated Float3x4 makeRotationY(float angle);

    /**
     * Set this matrix to a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians
     * about the Y, X and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a
     * vector is rotated about the Z axis first, then X, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return this
     */
    @Mutated Float3x4 makeRotationYXZ(float angleY, float angleX, float angleZ);

    /**
     * Set this matrix to a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians
     * about the Y, Z and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a
     * vector is rotated about the X axis first, then Z, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return this
     */
    @Mutated Float3x4 makeRotationYZX(float angleY, float angleZ, float angleX);

    /**
     * Set this matrix to a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated Float3x4 makeRotationZ(float angle);

    /**
     * Set this matrix to a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians
     * about the Z, X and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a
     * vector is rotated about the Y axis first, then X, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this
     */
    @Mutated Float3x4 makeRotationZXY(float angleZ, float angleX, float angleY);

    /**
     * Set this matrix to a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians
     * about the Z, Y and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a
     * vector is rotated about the X axis first, then Y, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return this
     */
    @Mutated Float3x4 makeRotationZYX(float angleZ, float angleY, float angleX);

    /**
     * Set this matrix to a scaling transformation that scales by {@code v}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @return this
     */
    @Mutated Float3x4 makeScaling(Float3R v);

    /**
     * Set this matrix to a scaling transformation that scales by ({@code x}, {@code y}, {@code z}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this
     */
    @Mutated Float3x4 makeScaling(float x, float y, float z);

    /**
     * Set this matrix to a scaling transformation that scales by {@code s}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @return this
     */
    @Mutated Float3x4 makeScaling(float s);

    /**
     * Set this matrix to a translation transformation that translates by {@code v}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @return this
     */
    @Mutated Float3x4 makeTranslation(Float3R v);

    /**
     * Set this matrix to a translation transformation that translates by ({@code x}, {@code y},
     * {@code z}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the translation offsets {@code (x, y, z)}
     * @param y the {@code y} component of the translation offsets {@code (x, y, z)}
     * @param z the {@code z} component of the translation offsets {@code (x, y, z)}
     * @return this
     */
    @Mutated Float3x4 makeTranslation(float x, float y, float z);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+x, +y, +z)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapXYZ() { return mapXYZ(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+x, +y, -z)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapXYnZ() { return mapXYnZ(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+x, +z, +y)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapXZY() { return mapXZY(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+x, +z, -y)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapXZnY() { return mapXZnY(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+x, -y, +z)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapXnYZ() { return mapXnYZ(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+x, -y, -z)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapXnYnZ() { return mapXnYnZ(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+x, -z, +y)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapXnZY() { return mapXnZY(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+x, -z, -y)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapXnZnY() { return mapXnZnY(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+y, +x, +z)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapYXZ() { return mapYXZ(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+y, +x, -z)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapYXnZ() { return mapYXnZ(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+y, +z, +x)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapYZX() { return mapYZX(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+y, +z, -x)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapYZnX() { return mapYZnX(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+y, -x, +z)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapYnXZ() { return mapYnXZ(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+y, -x, -z)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapYnXnZ() { return mapYnXnZ(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+y, -z, +x)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapYnZX() { return mapYnZX(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+y, -z, -x)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapYnZnX() { return mapYnZnX(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+z, +x, +y)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapZXY() { return mapZXY(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+z, +x, -y)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapZXnY() { return mapZXnY(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+z, +y, +x)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapZYX() { return mapZYX(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+z, +y, -x)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapZYnX() { return mapZYnX(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+z, -x, +y)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapZnXY() { return mapZnXY(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+z, -x, -y)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapZnXnY() { return mapZnXnY(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+z, -y, +x)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapZnYX() { return mapZnYX(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+z, -y, -x)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapZnYnX() { return mapZnYnX(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-x, +y, +z)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnXYZ() { return mapnXYZ(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-x, +y, -z)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnXYnZ() { return mapnXYnZ(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-x, +z, +y)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnXZY() { return mapnXZY(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-x, +z, -y)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnXZnY() { return mapnXZnY(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-x, -y, +z)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnXnYZ() { return mapnXnYZ(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-x, -y, -z)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnXnYnZ() { return mapnXnYnZ(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-x, -z, +y)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnXnZY() { return mapnXnZY(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-x, -z, -y)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnXnZnY() { return mapnXnZnY(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-y, +x, +z)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnYXZ() { return mapnYXZ(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-y, +x, -z)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnYXnZ() { return mapnYXnZ(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-y, +z, +x)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnYZX() { return mapnYZX(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-y, +z, -x)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnYZnX() { return mapnYZnX(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-y, -x, +z)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnYnXZ() { return mapnYnXZ(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-y, -x, -z)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnYnXnZ() { return mapnYnXnZ(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-y, -z, +x)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnYnZX() { return mapnYnZX(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-y, -z, -x)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnYnZnX() { return mapnYnZnX(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-z, +x, +y)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnZXY() { return mapnZXY(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-z, +x, -y)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnZXnY() { return mapnZXnY(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-z, +y, +x)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnZYX() { return mapnZYX(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-z, +y, -x)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnZYnX() { return mapnZYnX(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-z, -x, +y)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnZnXY() { return mapnZnXY(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-z, -x, -y)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnZnXnY() { return mapnZnXnY(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-z, -y, +x)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnZnYX() { return mapnZnYX(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-z, -y, -x)} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 mapnZnYnX() { return mapnZnYnX(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Pre-multiply the rotation {@code rot} about the pivot point {@code pivot} onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * The pivot sandwich {@code translate(pivot) * R * translate(-pivot)} is evaluated so that its
     * translation part, {@code pivot - R * pivot}, keeps its accuracy for pivots far from the
     * origin.
     * <p>
     * Valid input: {@code rot} must have unit length.
     *
     * @param rot the rotation to apply
     * @param pivot the pivot point
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 preRotateAround(FloatQuatR rot, Float3R pivot) { return preRotateAround(rot, pivot, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Pre-multiply the rotation ({@code rotX}, {@code rotY}, {@code rotZ}, {@code rotW}) about the
     * pivot point ({@code pivotX}, {@code pivotY}, {@code pivotZ}) onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * The pivot sandwich {@code translate(pivot) * R * translate(-pivot)} is evaluated so that its
     * translation part, {@code pivot - R * pivot}, keeps its accuracy for pivots far from the
     * origin.
     * <p>
     * Valid input: {@code (rotX, rotY, rotZ, rotW)} must have unit length.
     *
     * @param rotX the {@code x} component of the quaternion {@code (rotX, rotY, rotZ, rotW)}
     * @param rotY the {@code y} component of the quaternion {@code (rotX, rotY, rotZ, rotW)}
     * @param rotZ the {@code z} component of the quaternion {@code (rotX, rotY, rotZ, rotW)}
     * @param rotW the {@code w} component of the quaternion {@code (rotX, rotY, rotZ, rotW)}
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotZ the {@code z} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 preRotateAround(float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) { return preRotateAround(rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Pre-multiply a rotation of {@code angle} radians about the axis {@code axis} onto this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 preRotateAxis(float angle, Float3R axis) { return preRotateAxis(angle, axis, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Pre-multiply a rotation of {@code angle} radians about the axis ({@code x}, {@code y},
     * {@code z}) onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: {@code (x, y, z)} must have unit length.
     *
     * @param angle the angle in radians
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 preRotateAxis(float angle, float x, float y, float z) { return preRotateAxis(angle, x, y, z, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Pre-multiply the rotation represented by the quaternion {@code q} onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the rotation to apply
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 preRotateQuat(FloatQuatR q) { return preRotateQuat(q, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Pre-multiply the rotation represented by the quaternion ({@code x}, {@code y}, {@code z},
     * {@code w}) onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 preRotateQuat(float x, float y, float z, float w) { return preRotateQuat(x, y, z, w, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Pre-multiply a rotation of {@code angle} radians about the X axis onto this matrix.
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
    @Mutated default Float3x4 preRotateX(float angle) { return preRotateX(angle, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Pre-multiply a rotation of {@code angle} radians about the Y axis onto this matrix.
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
    @Mutated default Float3x4 preRotateY(float angle) { return preRotateY(angle, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Pre-multiply a rotation of {@code angle} radians about the Z axis onto this matrix.
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
    @Mutated default Float3x4 preRotateZ(float angle) { return preRotateZ(angle, Joml.RETURN_NEW ? Joml.float3x4() : this); }

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
    @Mutated default Float3x4 preScale(Float3R v) { return preScale(v, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Pre-multiply a scaling by ({@code x}, {@code y}, {@code z}) onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 preScale(float x, float y, float z) { return preScale(x, y, z, Joml.RETURN_NEW ? Joml.float3x4() : this); }

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
    @Mutated default Float3x4 preScale(float s) { return preScale(s, Joml.RETURN_NEW ? Joml.float3x4() : this); }

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
    @Mutated default Float3x4 preScaleAround(float s, Float3R pivot) { return preScaleAround(s, pivot, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Pre-multiply a scaling by {@code s} about the pivot point ({@code x}, {@code y}, {@code z})
     * onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 preScaleAround(float s, float x, float y, float z) { return preScaleAround(s, x, y, z, Joml.RETURN_NEW ? Joml.float3x4() : this); }

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
    @Mutated default Float3x4 preScaleAround(Float3R s, Float3R pivot) { return preScaleAround(s, pivot, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Pre-multiply a scaling by ({@code sX}, {@code sY}, {@code sZ}) about the pivot point
     * ({@code pivotX}, {@code pivotY}, {@code pivotZ}) onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sX the {@code x} component of the vector {@code (sX, sY, sZ)}
     * @param sY the {@code y} component of the vector {@code (sX, sY, sZ)}
     * @param sZ the {@code z} component of the vector {@code (sX, sY, sZ)}
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotZ the {@code z} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 preScaleAround(float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) { return preScaleAround(sX, sY, sZ, pivotX, pivotY, pivotZ, Joml.RETURN_NEW ? Joml.float3x4() : this); }

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
    @Mutated default Float3x4 preTranslate(Float3R v) { return preTranslate(v, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Pre-multiply a translation by ({@code x}, {@code y}, {@code z}) onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code T * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code T * M * v}, the translation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 preTranslate(float x, float y, float z) { return preTranslate(x, y, z, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a reflection about the plane through the origin with the normal {@code normal} to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the reflection matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the reflection will be applied first.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to reflect about
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 reflect(Float3R normal) { return reflect(normal, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a reflection about the plane through the origin with the normal ({@code x}, {@code y},
     * {@code z}) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the reflection matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the reflection will be applied first.
     * <p>
     * Valid input: {@code (x, y, z)} must have unit length.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 reflect(float x, float y, float z) { return reflect(x, y, z, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply the rotation {@code rot} about the pivot point {@code pivot} to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The pivot sandwich {@code translate(pivot) * R * translate(-pivot)} is evaluated so that its
     * translation part, {@code pivot - R * pivot}, keeps its accuracy for pivots far from the
     * origin.
     * <p>
     * Valid input: {@code rot} must have unit length.
     *
     * @param rot the rotation to apply
     * @param pivot the pivot point
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateAround(FloatQuatR rot, Float3R pivot) { return rotateAround(rot, pivot, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply the rotation ({@code rotX}, {@code rotY}, {@code rotZ}, {@code rotW}) about the pivot
     * point ({@code pivotX}, {@code pivotY}, {@code pivotZ}) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * The pivot sandwich {@code translate(pivot) * R * translate(-pivot)} is evaluated so that its
     * translation part, {@code pivot - R * pivot}, keeps its accuracy for pivots far from the
     * origin.
     * <p>
     * Valid input: {@code (rotX, rotY, rotZ, rotW)} must have unit length.
     *
     * @param rotX the {@code x} component of the quaternion {@code (rotX, rotY, rotZ, rotW)}
     * @param rotY the {@code y} component of the quaternion {@code (rotX, rotY, rotZ, rotW)}
     * @param rotZ the {@code z} component of the quaternion {@code (rotX, rotY, rotZ, rotW)}
     * @param rotW the {@code w} component of the quaternion {@code (rotX, rotY, rotZ, rotW)}
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotZ the {@code z} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateAround(float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) { return rotateAround(rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateAxis(float angle, Float3R axis) { return rotateAxis(angle, axis, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code x}, {@code y}, {@code z}) to
     * this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code (x, y, z)} must have unit length.
     *
     * @param angle the angle in radians
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateAxis(float angle, float x, float y, float z) { return rotateAxis(angle, x, y, z, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply the rotation represented by the quaternion {@code q} to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the rotation to apply
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateQuat(FloatQuatR q) { return rotateQuat(q, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply the rotation represented by the quaternion ({@code x}, {@code y}, {@code z}, {@code w})
     * to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code (x, y, z, w)} must have unit length.
     *
     * @param x the {@code x} component of the quaternion {@code (x, y, z, w)}
     * @param y the {@code y} component of the quaternion {@code (x, y, z, w)}
     * @param z the {@code z} component of the quaternion {@code (x, y, z, w)}
     * @param w the {@code w} component of the quaternion {@code (x, y, z, w)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateQuat(float x, float y, float z, float w) { return rotateQuat(x, y, z, w, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of {@code angle} radians about the X axis to this matrix.
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
    @Mutated default Float3x4 rotateX(float angle) { return rotateX(angle, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of 180 degrees about the X axis to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateX180() { return rotateX180(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of 270 degrees about the X axis to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateX270() { return rotateX270(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of 90 degrees about the X axis to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateX90() { return rotateX90(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X), to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateXYZ(float angleX, float angleY, float angleZ) { return rotateXYZ(angleX, angleY, angleZ, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X), to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateXZY(float angleX, float angleZ, float angleY) { return rotateXZY(angleX, angleZ, angleY, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of -180 degrees about the X axis to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateXn180() { return rotateXn180(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of -270 degrees about the X axis to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateXn270() { return rotateXn270(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of -90 degrees about the X axis to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateXn90() { return rotateXn90(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of {@code angle} radians about the Y axis to this matrix.
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
    @Mutated default Float3x4 rotateY(float angle) { return rotateY(angle, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of 180 degrees about the Y axis to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateY180() { return rotateY180(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of 270 degrees about the Y axis to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateY270() { return rotateY270(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of 90 degrees about the Y axis to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateY90() { return rotateY90(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y), to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateYXZ(float angleY, float angleX, float angleZ) { return rotateYXZ(angleY, angleX, angleZ, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y), to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateYZX(float angleY, float angleZ, float angleX) { return rotateYZX(angleY, angleZ, angleX, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of -180 degrees about the Y axis to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateYn180() { return rotateYn180(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of -270 degrees about the Y axis to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateYn270() { return rotateYn270(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of -90 degrees about the Y axis to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateYn90() { return rotateYn90(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of {@code angle} radians about the Z axis to this matrix.
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
    @Mutated default Float3x4 rotateZ(float angle) { return rotateZ(angle, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of 180 degrees about the Z axis to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateZ180() { return rotateZ180(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of 270 degrees about the Z axis to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateZ270() { return rotateZ270(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of 90 degrees about the Z axis to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateZ90() { return rotateZ90(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z), to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateZXY(float angleZ, float angleX, float angleY) { return rotateZXY(angleZ, angleX, angleY, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z), to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateZYX(float angleZ, float angleY, float angleX) { return rotateZYX(angleZ, angleY, angleX, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of -180 degrees about the Z axis to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateZn180() { return rotateZn180(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of -270 degrees about the Z axis to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateZn270() { return rotateZn270(Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a rotation of -90 degrees about the Z axis to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 rotateZn90() { return rotateZn90(Joml.RETURN_NEW ? Joml.float3x4() : this); }

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
    @Mutated default Float3x4 scale(Float3R v) { return scale(v, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a scaling by ({@code x}, {@code y}, {@code z}) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 scale(float x, float y, float z) { return scale(x, y, z, Joml.RETURN_NEW ? Joml.float3x4() : this); }

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
    @Mutated default Float3x4 scale(float s) { return scale(s, Joml.RETURN_NEW ? Joml.float3x4() : this); }

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
    @Mutated default Float3x4 scaleAround(float s, Float3R pivot) { return scaleAround(s, pivot, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a scaling by {@code s} about the pivot point ({@code x}, {@code y}, {@code z}) to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 scaleAround(float s, float x, float y, float z) { return scaleAround(s, x, y, z, Joml.RETURN_NEW ? Joml.float3x4() : this); }

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
    @Mutated default Float3x4 scaleAround(Float3R s, Float3R pivot) { return scaleAround(s, pivot, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a scaling by ({@code sX}, {@code sY}, {@code sZ}) about the pivot point
     * ({@code pivotX}, {@code pivotY}, {@code pivotZ}) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param sX the {@code x} component of the vector {@code (sX, sY, sZ)}
     * @param sY the {@code y} component of the vector {@code (sX, sY, sZ)}
     * @param sZ the {@code z} component of the vector {@code (sX, sY, sZ)}
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @param pivotZ the {@code z} component of the vector {@code (pivotX, pivotY, pivotZ)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 scaleAround(float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) { return scaleAround(sX, sY, sZ, pivotX, pivotY, pivotZ, Joml.RETURN_NEW ? Joml.float3x4() : this); }

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
    @Mutated default Float3x4 translate(Float3R v) { return translate(v, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Apply a translation by ({@code x}, {@code y}, {@code z}) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the translation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the translation offsets {@code (x, y, z)}
     * @param y the {@code y} component of the translation offsets {@code (x, y, z)}
     * @param z the {@code z} component of the translation offsets {@code (x, y, z)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Float3x4 translate(float x, float y, float z) { return translate(x, y, z, Joml.RETURN_NEW ? Joml.float3x4() : this); }

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Float3x4 loadCM(float[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Float3x4 loadCM(float[] src, int offset);

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
    @Mutated default Float3x4 loadCM(FloatBuffer src) { return loadCMAbsolute(src.position(), src); }

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
    @Mutated default Float3x4 loadCMAbsolute(FloatBuffer src) { return loadCMAbsolute(src.position(), src); }

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
    @Mutated Float3x4 loadCMAbsolute(int index, FloatBuffer src);

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
    @Mutated default Float3x4 loadCMRelative(FloatBuffer src) {
        if (src.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src);
        src.position(pos + 12);
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
    @Mutated default Float3x4 loadCM(ByteBuffer src) { return loadCMAbsolute(src.position(), src); }

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
    @Mutated default Float3x4 loadCMAbsolute(ByteBuffer src) { return loadCMAbsolute(src.position(), src); }

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
    @Mutated Float3x4 loadCMAbsolute(int index, ByteBuffer src);

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
    @Mutated default Float3x4 loadCMRelative(ByteBuffer src) {
        if (src.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src);
        src.position(pos + 48);
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
    @Mutated Float3x4 loadCMUnsafe(long address);

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Float3x4 loadCM(double[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Float3x4 loadCM(double[] src, int offset);

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
    @Mutated default Float3x4 loadCM(DoubleBuffer src) { return loadCMAbsolute(src.position(), src); }

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
    @Mutated default Float3x4 loadCMAbsolute(DoubleBuffer src) { return loadCMAbsolute(src.position(), src); }

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
    @Mutated Float3x4 loadCMAbsolute(int index, DoubleBuffer src);

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
    @Mutated default Float3x4 loadCMRelative(DoubleBuffer src) {
        if (src.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src);
        src.position(pos + 12);
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
    @Mutated default Float3x4 loadCMDouble(ByteBuffer src) { return loadCMDoubleAbsolute(src.position(), src); }

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
    @Mutated default Float3x4 loadCMDoubleAbsolute(ByteBuffer src) { return loadCMDoubleAbsolute(src.position(), src); }

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
    @Mutated Float3x4 loadCMDoubleAbsolute(int index, ByteBuffer src);

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
    @Mutated default Float3x4 loadCMDoubleRelative(ByteBuffer src) {
        if (src.remaining() < 96) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMDoubleAbsolute(pos, src);
        src.position(pos + 96);
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
    @Mutated Float3x4 loadCMDoubleUnsafe(long address);

    /**
     * Load the elements from the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Float3x4 loadRM(float[] src) { return loadRM(src, 0); }

    /**
     * Load the elements from the given array in row-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Float3x4 loadRM(float[] src, int offset);

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
    @Mutated default Float3x4 loadRM(FloatBuffer src) { return loadRMAbsolute(src.position(), src); }

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
    @Mutated default Float3x4 loadRMAbsolute(FloatBuffer src) { return loadRMAbsolute(src.position(), src); }

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
    @Mutated Float3x4 loadRMAbsolute(int index, FloatBuffer src);

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
    @Mutated default Float3x4 loadRMRelative(FloatBuffer src) {
        if (src.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src);
        src.position(pos + 12);
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
    @Mutated default Float3x4 loadRM(ByteBuffer src) { return loadRMAbsolute(src.position(), src); }

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
    @Mutated default Float3x4 loadRMAbsolute(ByteBuffer src) { return loadRMAbsolute(src.position(), src); }

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
    @Mutated Float3x4 loadRMAbsolute(int index, ByteBuffer src);

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
    @Mutated default Float3x4 loadRMRelative(ByteBuffer src) {
        if (src.remaining() < 48) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src);
        src.position(pos + 48);
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
    @Mutated Float3x4 loadRMUnsafe(long address);

    /**
     * Load the elements from the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Float3x4 loadRM(double[] src) { return loadRM(src, 0); }

    /**
     * Load the elements from the given array in row-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Float3x4 loadRM(double[] src, int offset);

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
    @Mutated default Float3x4 loadRM(DoubleBuffer src) { return loadRMAbsolute(src.position(), src); }

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
    @Mutated default Float3x4 loadRMAbsolute(DoubleBuffer src) { return loadRMAbsolute(src.position(), src); }

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
    @Mutated Float3x4 loadRMAbsolute(int index, DoubleBuffer src);

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
    @Mutated default Float3x4 loadRMRelative(DoubleBuffer src) {
        if (src.remaining() < 12) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src);
        src.position(pos + 12);
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
    @Mutated default Float3x4 loadRMDouble(ByteBuffer src) { return loadRMDoubleAbsolute(src.position(), src); }

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
    @Mutated default Float3x4 loadRMDoubleAbsolute(ByteBuffer src) { return loadRMDoubleAbsolute(src.position(), src); }

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
    @Mutated Float3x4 loadRMDoubleAbsolute(int index, ByteBuffer src);

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
    @Mutated default Float3x4 loadRMDoubleRelative(ByteBuffer src) {
        if (src.remaining() < 96) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMDoubleAbsolute(pos, src);
        src.position(pos + 96);
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
    @Mutated Float3x4 loadRMDoubleUnsafe(long address);

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
    @Mutated Float3x4 loadCM(float[] src, int offset, int stride);

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
    @Mutated default Float3x4 loadCM(FloatBuffer src, int stride) { return loadCMAbsolute(src.position(), src, stride); }

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
    @Mutated Float3x4 loadCMAbsolute(int index, FloatBuffer src, int stride);

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
    @Mutated default Float3x4 loadCMRelative(FloatBuffer src, int stride) {
        if (src.remaining() < (long) 4 * stride) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src, stride);
        src.position(pos + 4 * stride);
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
    @Mutated default Float3x4 loadCM(ByteBuffer src, int stride) { return loadCMAbsolute(src.position(), src, stride); }

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
    @Mutated Float3x4 loadCMAbsolute(int index, ByteBuffer src, int stride);

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
    @Mutated default Float3x4 loadCMRelative(ByteBuffer src, int stride) {
        if (src.remaining() < (long) 4 * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src, stride);
        src.position(pos + (4 * stride) * 4);
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
    @Mutated Float3x4 loadCMUnsafe(long address, int stride);

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
    @Mutated Float3x4 loadCM(double[] src, int offset, int stride);

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
    @Mutated default Float3x4 loadCM(DoubleBuffer src, int stride) { return loadCMAbsolute(src.position(), src, stride); }

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
    @Mutated Float3x4 loadCMAbsolute(int index, DoubleBuffer src, int stride);

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
    @Mutated default Float3x4 loadCMRelative(DoubleBuffer src, int stride) {
        if (src.remaining() < (long) 4 * stride) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src, stride);
        src.position(pos + 4 * stride);
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
    @Mutated default Float3x4 loadCMDouble(ByteBuffer src, int stride) { return loadCMDoubleAbsolute(src.position(), src, stride); }

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
    @Mutated Float3x4 loadCMDoubleAbsolute(int index, ByteBuffer src, int stride);

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
    @Mutated default Float3x4 loadCMDoubleRelative(ByteBuffer src, int stride) {
        if (src.remaining() < (long) 4 * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMDoubleAbsolute(pos, src, stride);
        src.position(pos + (4 * stride) * 8);
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
    @Mutated Float3x4 loadCMDoubleUnsafe(long address, int stride);

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
    @Mutated Float3x4 loadRM(float[] src, int offset, int stride);

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
    @Mutated default Float3x4 loadRM(FloatBuffer src, int stride) { return loadRMAbsolute(src.position(), src, stride); }

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
    @Mutated Float3x4 loadRMAbsolute(int index, FloatBuffer src, int stride);

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
    @Mutated default Float3x4 loadRMRelative(FloatBuffer src, int stride) {
        if (src.remaining() < (long) 3 * stride) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src, stride);
        src.position(pos + 3 * stride);
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
    @Mutated default Float3x4 loadRM(ByteBuffer src, int stride) { return loadRMAbsolute(src.position(), src, stride); }

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
    @Mutated Float3x4 loadRMAbsolute(int index, ByteBuffer src, int stride);

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
    @Mutated default Float3x4 loadRMRelative(ByteBuffer src, int stride) {
        if (src.remaining() < (long) 3 * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src, stride);
        src.position(pos + (3 * stride) * 4);
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
    @Mutated Float3x4 loadRMUnsafe(long address, int stride);

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
    @Mutated Float3x4 loadRM(double[] src, int offset, int stride);

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
    @Mutated default Float3x4 loadRM(DoubleBuffer src, int stride) { return loadRMAbsolute(src.position(), src, stride); }

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
    @Mutated Float3x4 loadRMAbsolute(int index, DoubleBuffer src, int stride);

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
    @Mutated default Float3x4 loadRMRelative(DoubleBuffer src, int stride) {
        if (src.remaining() < (long) 3 * stride) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src, stride);
        src.position(pos + 3 * stride);
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
    @Mutated default Float3x4 loadRMDouble(ByteBuffer src, int stride) { return loadRMDoubleAbsolute(src.position(), src, stride); }

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
    @Mutated Float3x4 loadRMDoubleAbsolute(int index, ByteBuffer src, int stride);

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
    @Mutated default Float3x4 loadRMDoubleRelative(ByteBuffer src, int stride) {
        if (src.remaining() < (long) 3 * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMDoubleAbsolute(pos, src, stride);
        src.position(pos + (3 * stride) * 8);
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
    @Mutated Float3x4 loadRMDoubleUnsafe(long address, int stride);

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Float3x4 load(float[] src) { return loadCM(src); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated default Float3x4 load(float[] src, int offset) { return loadCM(src, offset); }

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
    @Mutated default Float3x4 load(FloatBuffer src) { return loadCMAbsolute(src); }

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
    @Mutated default Float3x4 load(int index, FloatBuffer src) { return loadCMAbsolute(index, src); }

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
    @Mutated default Float3x4 loadRelative(FloatBuffer src) { return loadCMRelative(src); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Float3x4 load(double[] src) { return loadCM(src); }

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
    @Mutated default Float3x4 load(double[] src, int offset) { return loadCM(src, offset); }

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
    @Mutated default Float3x4 load(DoubleBuffer src) { return loadCMAbsolute(src); }

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
    @Mutated default Float3x4 load(int index, DoubleBuffer src) { return loadCMAbsolute(index, src); }

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
    @Mutated default Float3x4 loadRelative(DoubleBuffer src) { return loadCMRelative(src); }

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
    @Mutated default Float3x4 load(ByteBuffer src) { return loadCMAbsolute(src); }

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
    @Mutated default Float3x4 load(int index, ByteBuffer src) { return loadCMAbsolute(index, src); }

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
    @Mutated default Float3x4 loadRelative(ByteBuffer src) { return loadCMRelative(src); }

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
    @Mutated default Float3x4 loadUnsafe(long address) { return loadCMUnsafe(address); }

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
    @Mutated default Float3x4 load(float[] src, int offset, int stride) { return loadCM(src, offset, stride); }

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
    @Mutated default Float3x4 load(double[] src, int offset, int stride) { return loadCM(src, offset, stride); }

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
    @Mutated default Float3x4 load(int index, FloatBuffer src, int stride) { return loadCMAbsolute(index, src, stride); }

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
    @Mutated default Float3x4 loadRelative(FloatBuffer src, int stride) { return loadCMRelative(src, stride); }

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
    @Mutated default Float3x4 load(int index, DoubleBuffer src, int stride) { return loadCMAbsolute(index, src, stride); }

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
    @Mutated default Float3x4 loadRelative(DoubleBuffer src, int stride) { return loadCMRelative(src, stride); }

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
    @Mutated default Float3x4 load(int index, ByteBuffer src, int stride) { return loadCMAbsolute(index, src, stride); }

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
    @Mutated default Float3x4 loadRelative(ByteBuffer src, int stride) { return loadCMRelative(src, stride); }

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
    @Mutated default Float3x4 loadUnsafe(long address, int stride) { return loadCMUnsafe(address, stride); }
}
