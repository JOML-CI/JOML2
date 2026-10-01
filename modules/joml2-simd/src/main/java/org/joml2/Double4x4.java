// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.ByteBuffer;
import java.lang.foreign.MemorySegment;

/**
 * A 4x4 matrix of double-precision {@code double} components.
 * <p>
 * Extends the read-only view {@link Double4x4R} with the mutating surface: in-place self-forms,
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
 * {@code Double.doubleToLongBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
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
public interface Double4x4 extends Double4x4R {

    /**
     * Compute the cofactor matrix of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 cofactor() { return cofactor(Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Invert this matrix.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 invert() { return invert(Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Invert this matrix, which must be a perspective frustum projection as built by makeFrustum or
     * frustum (also an off-center or symmetric one): only the elements such a matrix can have
     * nonzero are read, a few divisions instead of a general 4x4 inverse. For any other matrix the
     * result is undefined; a singular one gives infinite or NaN elements. For a symmetric
     * perspective, invertPerspective reads fewer elements.
     * <p>
     * Valid input: this matrix must be a perspective projection.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 invertFrustum() { return invertFrustum(Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Invert this matrix, which must be an orthographic projection as built by makeOrtho or ortho
     * (also an off-center or 2D one): only its diagonal and translation are read, three divisions
     * instead of a general 4x4 inverse. For any other matrix the result is undefined; a singular
     * one gives infinite or NaN elements.
     * <p>
     * Valid input: this matrix must be an orthographic projection.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 invertOrtho() { return invertOrtho(Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Invert this matrix, which must be a symmetric perspective projection as built by
     * makePerspective or perspective (any handedness, depth range and near/far plane, also at
     * infinity): only the elements such a matrix can have nonzero are read, which makes this a
     * handful of divisions instead of a general 4x4 inverse. For any other matrix the result is
     * undefined; a singular one (a zero field of view, near equal to far) gives infinite or NaN
     * elements.
     * <p>
     * Valid input: this matrix must be a symmetric perspective projection.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 invertPerspective() { return invertPerspective(Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Compute the inverse of this matrix multiplied by view, this being a symmetric perspective
     * projection (see invertPerspective) and view a rotation and translation without scaling - a
     * camera from lookAt: no general 4x4 inverse is needed. The same as mul(view).invert() for such
     * matrices; for any other the result is undefined.
     * <p>
     * Valid input: this matrix must be a symmetric perspective projection; {@code view} must be
     * free of scaling and shear.
     *
     * @param view the camera's view transformation: rotation and translation only
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 invertPerspectiveView(Double3x4R view) { return invertPerspectiveView(view, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Compute the inverse of this matrix multiplied by view, this being a symmetric perspective
     * projection (see invertPerspective) and view an affine transformation with a unit-length
     * rotation - a camera from lookAt, or any rotation and translation without scaling: its inverse
     * is its transposed rotation and back-rotated negated translation, so no general 4x4 inverse is
     * needed. The same as mul(view).invert() for such matrices; for any other the result is
     * undefined.
     * <p>
     * Valid input: this matrix must be a symmetric perspective projection; {@code view} must be
     * free of scaling and shear.
     *
     * @param view the camera's view transformation: affine, rotation and translation only
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 invertPerspectiveView(Double4x4R view) { return invertPerspectiveView(view, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 invertProduct(Double4x4R other) { return invertProduct(other, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Compute the inverse of the product of this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m03}, {@code m10}, {@code m11}, {@code m12}, {@code m13}, {@code m20}, {@code m21},
     * {@code m22}, {@code m23}, {@code m30}, {@code m31}, {@code m32}, {@code m33}).
     * <p>
     * The product is formed first and inverted afterwards, so the result is the inverse of the
     * rounded product: its accuracy is bounded by the condition number of the product, not by the
     * condition numbers of the two factors. For an ill-conditioned product (a near-singular factor,
     * or factors of very different scale) invert both factors separately and multiply the inverses
     * in reverse order instead.
     * <p>
     * Valid input: the product of this matrix and
     * {@code (m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33)} must
     * be invertible.
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
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param m32 the element in row 3, column 2 of the matrix
     * @param m33 the element in row 3, column 3 of the matrix
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 invertProduct(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33) { return invertProduct(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Compute the normal matrix of this matrix, i.e. the transpose of its inverse.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 normal() { return normal(Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Transpose this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 transpose() { return transpose(Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Add {@code other} to this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to add
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 add(Double4x4R other) { return add(other, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11},
     * {@code m12}, {@code m13}, {@code m20}, {@code m21}, {@code m22}, {@code m23}, {@code m30},
     * {@code m31}, {@code m32}, {@code m33}) to this matrix.
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
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param m32 the element in row 3, column 2 of the matrix
     * @param m33 the element in row 3, column 3 of the matrix
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 add(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33) { return add(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Multiply each component of this matrix by {@code scalar}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 mul(double scalar) { return mul(scalar, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Negate this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 negate() { return negate(Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Subtract {@code other} from this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to subtract
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 sub(Double4x4R other) { return sub(other, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Subtract ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11},
     * {@code m12}, {@code m13}, {@code m20}, {@code m21}, {@code m22}, {@code m23}, {@code m30},
     * {@code m31}, {@code m32}, {@code m33}) from this matrix.
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
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param m32 the element in row 3, column 2 of the matrix
     * @param m33 the element in row 3, column 3 of the matrix
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 sub(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33) { return sub(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Set this matrix to the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the matrix to copy
     * @return this
     */
    @Mutated Double4x4 set(Double4x4R v);

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
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param m32 the element in row 3, column 2 of the matrix
     * @param m33 the element in row 3, column 3 of the matrix
     * @return this
     */
    @Mutated Double4x4 set(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33);

    /**
     * Set this matrix to the given 3x3 matrix, copying the overlapping cells and filling the rest
     * with identity.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to copy from
     * @return this
     */
    @Mutated Double4x4 set(Double3x3R m);

    /**
     * Set this matrix to the given 3x4 matrix, copying the overlapping cells and filling the rest
     * with identity.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to copy from
     * @return this
     */
    @Mutated Double4x4 set(Double3x4R m);

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
    @Mutated default Double4x4 withTranslation(Double3R t) { return withTranslation(t, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 withTranslation(double x, double y, double z) { return withTranslation(x, y, z, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Convert this matrix to {@code float} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Float4x4} holding the result
     */
    default Float4x4 toFloat() { return toFloat(Joml.float4x4()); }

    /**
     * Set this matrix to the given rigid transform's {@code T * R} composition.
     * <p>
     * Valid input: the rotation of {@code r} must have unit length.
     *
     * @param r the rigid transform to convert
     * @return this
     */
    @Mutated Double4x4 makeFromRigid(DoubleRigidR r);

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
    @Mutated Double4x4 makeFromRigid(double tX, double tY, double tZ, double rX, double rY, double rZ, double rW);

    /**
     * Set this matrix to the given transform's {@code T * R * S} composition.
     * <p>
     * Valid input: the rotation of {@code t} must have unit length.
     *
     * @param t the transform to convert
     * @return this
     */
    @Mutated Double4x4 makeFromTransform(DoubleTransformR t);

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
    @Mutated Double4x4 makeFromTransform(double tX, double tY, double tZ, double rX, double rY, double rZ, double rW, double sX, double sY, double sZ);

    /**
     * Set this matrix to the identity.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeIdentity();

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
    @Mutated default Double4x4 lerp(Double4x4R other, double t) { return lerp(other, t, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m03}, {@code m10}, {@code m11}, {@code m12}, {@code m13}, {@code m20}, {@code m21},
     * {@code m22}, {@code m23}, {@code m30}, {@code m31}, {@code m32}, {@code m33}) using the
     * interpolation factor {@code t}.
     * <p>
     * The interpolation starts at this matrix (interpolation factor {@code 0}) and ends at
     * ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11}, {@code m12},
     * {@code m13}, {@code m20}, {@code m21}, {@code m22}, {@code m23}, {@code m30}, {@code m31},
     * {@code m32}, {@code m33}) (interpolation factor {@code 1}). Each linearly interpolated
     * component is {@code this + (other - this) * t}, as in JOML and glMatrix: monotone in
     * {@code t} and exact at {@code 0}, but at {@code 1} exact only up to the rounding of
     * {@code other - this}, which shows when this component is much larger in magnitude than the
     * other one (in {@code float}, 1e8 towards 1 ends at 0).
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
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param m32 the element in row 3, column 2 of the matrix
     * @param m33 the element in row 3, column 3 of the matrix
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 lerp(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33, double t) { return lerp(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33, t, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Multiply this matrix by {@code right}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 mul(Double4x4R right) { return mul(right, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Multiply this matrix by ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10},
     * {@code m11}, {@code m12}, {@code m13}, {@code m20}, {@code m21}, {@code m22}, {@code m23},
     * {@code m30}, {@code m31}, {@code m32}, {@code m33}).
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
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
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param m32 the element in row 3, column 2 of the matrix
     * @param m33 the element in row 3, column 3 of the matrix
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 mul(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33) { return mul(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mul(Double2x2R right) { return mul(right, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mul(Double2x3R right) { return mul(right, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mul(Double3x3R right) { return mul(right, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mul(Double3x4R right) { return mul(right, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preMul(Double4x4R other) { return preMul(other, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Pre-multiply the transformation ({@code m00}, {@code m01}, {@code m02}, {@code m03},
     * {@code m10}, {@code m11}, {@code m12}, {@code m13}, {@code m20}, {@code m21}, {@code m22},
     * {@code m23}, {@code m30}, {@code m31}, {@code m32}, {@code m33}) onto this matrix.
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
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param m32 the element in row 3, column 2 of the matrix
     * @param m33 the element in row 3, column 3 of the matrix
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 preMul(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33) { return preMul(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preMul(Double2x2R other) { return preMul(other, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preMul(Double2x3R other) { return preMul(other, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preMul(Double3x3R other) { return preMul(other, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preMul(Double3x4R other) { return preMul(other, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Add {@code other} scaled by {@code weight} to this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 addScaled(Double4x4R other, double weight) { return addScaled(other, weight, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11},
     * {@code m12}, {@code m13}, {@code m20}, {@code m21}, {@code m22}, {@code m23}, {@code m30},
     * {@code m31}, {@code m32}, {@code m33}) scaled by {@code weight} to this matrix.
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
     * @param m30 the element in row 3, column 0 of the matrix
     * @param m31 the element in row 3, column 1 of the matrix
     * @param m32 the element in row 3, column 2 of the matrix
     * @param m33 the element in row 3, column 3 of the matrix
     * @param weight the factor to scale ({@code m00}, {@code m01}, {@code m02}, {@code m03},
     *        {@code m10}, {@code m11}, {@code m12}, {@code m13}, {@code m20}, {@code m21},
     *        {@code m22}, {@code m23}, {@code m30}, {@code m31}, {@code m32}, {@code m33}) by
     *        before adding
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 addScaled(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33, double weight) { return addScaled(m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33, weight, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Set this matrix to the outer product of {@code col} and {@code row}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param col the column vector (left operand)
     * @param row the row vector (right operand)
     * @return this
     */
    @Mutated Double4x4 makeOuterProduct(Double4R col, Double4R row);

    /**
     * Set this matrix to the outer product of ({@code colX}, {@code colY}, {@code colZ},
     * {@code colW}) and ({@code rowX}, {@code rowY}, {@code rowZ}, {@code rowW}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param colX the {@code x} component of the vector {@code (colX, colY, colZ, colW)}
     * @param colY the {@code y} component of the vector {@code (colX, colY, colZ, colW)}
     * @param colZ the {@code z} component of the vector {@code (colX, colY, colZ, colW)}
     * @param colW the {@code w} component of the vector {@code (colX, colY, colZ, colW)}
     * @param rowX the {@code x} component of the vector {@code (rowX, rowY, rowZ, rowW)}
     * @param rowY the {@code y} component of the vector {@code (rowX, rowY, rowZ, rowW)}
     * @param rowZ the {@code z} component of the vector {@code (rowX, rowY, rowZ, rowW)}
     * @param rowW the {@code w} component of the vector {@code (rowX, rowY, rowZ, rowW)}
     * @return this
     */
    @Mutated Double4x4 makeOuterProduct(double colX, double colY, double colZ, double colW, double rowX, double rowY, double rowZ, double rowW);

    /**
     * Apply an arcball view transformation about the given center to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code A} the arcball view matrix, then the new
     * matrix will be {@code M * A}. So when transforming a vector {@code v} with the new matrix by
     * using {@code M * A * v}, the arcball view will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param radius the distance of the eye from the center
     * @param center the center of the arcball, i.e. the point the eye orbits around
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 arcball(double radius, Double3R center, double angleX, double angleY) { return arcball(radius, center, angleX, angleY, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply an arcball view transformation about the given center to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code A} the arcball view matrix, then the new
     * matrix will be {@code M * A}. So when transforming a vector {@code v} with the new matrix by
     * using {@code M * A * v}, the arcball view will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param radius the distance of the eye from the center
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 arcball(double radius, double x, double y, double z, double angleX, double angleY) { return arcball(radius, x, y, z, angleX, angleY, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply an axonometric dimetric viewing rotation (compose with an orthographic projection for
     * the full transform) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param alpha the tilt angle in radians about the X axis (at {@code atan(1/sqrt(2))} the
     *        result is isometric)
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 axonometricDimetric(double alpha) { return axonometricDimetric(alpha, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply an axonometric isometric viewing rotation (compose with an orthographic projection for
     * the full transform) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 axonometricIsometric() { return axonometricIsometric(Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply an axonometric trimetric viewing rotation (compose with an orthographic projection for
     * the full transform) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param alphaX the rotation angle in radians about the X axis
     * @param alphaY the rotation angle in radians about the Y axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 axonometricTrimetric(double alphaX, double alphaY) { return axonometricTrimetric(alphaX, alphaY, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated Double4x4 composeTRS(Double3R translation, DoubleQuatR rotation, Double3R scale);

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
    @Mutated Double4x4 composeTRS(double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ);

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
    @Mutated Double4x4 composeTRSAround(Double3R translation, DoubleQuatR rotation, Double3R scale, Double3R pivot);

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
    @Mutated Double4x4 composeTRSAround(double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ, double pivotX, double pivotY, double pivotZ);

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
    @Mutated Double4x4 composeTRSMul(Double3R translation, DoubleQuatR rotation, Double3R scale, Double4x4R m);

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
    @Mutated Double4x4 composeTRSMul(double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ, Double4x4R m);

    /**
     * Apply an arbitrary perspective projection frustum transformation to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code P} the perspective projection matrix, then the
     * new matrix will be {@code M * P}. So when transforming a vector {@code v} with the new matrix
     * by using {@code M * P * v}, the perspective projection will be applied first.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ; {@code zNear} must be positive; {@code zFar} must be positive; {@code zNear} and
     * {@code zFar} must differ; {@code zFar} may be {@code +Infinity}.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param zNear the distance to the near clip plane (left/right/bottom/top are measured at the
     *        near plane, so it cannot be at infinity here - the angle-based builders support an
     *        infinite near plane)
     * @param zFar the distance to the far clip plane (positive infinity gives an infinite far
     *        plane)
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 frustum(double left, double right, double bottom, double top, double zNear, double zFar, Handedness handedness, DepthRange depthRange) { return frustum(left, right, bottom, top, zNear, zFar, handedness, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply an arbitrary perspective projection frustum transformation to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code P} the perspective projection matrix, then the
     * new matrix will be {@code M * P}. So when transforming a vector {@code v} with the new matrix
     * by using {@code M * P * v}, the perspective projection will be applied first.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ; {@code zNear} must be positive; {@code zFar} must be positive; {@code zNear} and
     * {@code zFar} must differ; {@code zFar} may be {@code +Infinity}.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param zNear the distance to the near clip plane (left/right/bottom/top are measured at the
     *        near plane, so it cannot be at infinity here - the angle-based builders support an
     *        infinite near plane)
     * @param zFar the distance to the far clip plane (positive infinity gives an infinite far
     *        plane)
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 frustum(double left, double right, double bottom, double top, double zNear, double zFar, DepthRange depthRange) { return frustum(left, right, bottom, top, zNear, zFar, Handedness.RIGHT_HANDED, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply an arbitrary perspective projection frustum transformation to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code P} the perspective projection matrix, then the
     * new matrix will be {@code M * P}. So when transforming a vector {@code v} with the new matrix
     * by using {@code M * P * v}, the perspective projection will be applied first.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ; {@code zNear} must be positive; {@code zFar} must be positive; {@code zNear} and
     * {@code zFar} must differ; {@code zFar} may be {@code +Infinity}.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param zNear the distance to the near clip plane (left/right/bottom/top are measured at the
     *        near plane, so it cannot be at infinity here - the angle-based builders support an
     *        infinite near plane)
     * @param zFar the distance to the far clip plane (positive infinity gives an infinite far
     *        plane)
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 frustum(double left, double right, double bottom, double top, double zNear, double zFar, Handedness handedness) { return frustum(left, right, bottom, top, zNear, zFar, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply an arbitrary perspective projection frustum transformation to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code P} the perspective projection matrix, then the
     * new matrix will be {@code M * P}. So when transforming a vector {@code v} with the new matrix
     * by using {@code M * P * v}, the perspective projection will be applied first.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ; {@code zNear} must be positive; {@code zFar} must be positive; {@code zNear} and
     * {@code zFar} must differ; {@code zFar} may be {@code +Infinity}.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param zNear the distance to the near clip plane (left/right/bottom/top are measured at the
     *        near plane, so it cannot be at infinity here - the angle-based builders support an
     *        infinite near plane)
     * @param zFar the distance to the far clip plane (positive infinity gives an infinite far
     *        plane)
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 frustum(double left, double right, double bottom, double top, double zNear, double zFar) { return frustum(left, right, bottom, top, zNear, zFar, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 lookAlong(Double3R dir, Double3R up) { return lookAlong(dir, up, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) { return lookAlong(dirX, dirY, dirZ, upX, upY, upZ, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 lookAt(Double3R eye, Double3R center, Double3R up, Handedness handedness) { return lookAt(eye, center, up, handedness, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 lookAt(double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ, Handedness handedness) { return lookAt(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, handedness, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 lookAt(Double3R eye, Double3R center, Double3R up) { return lookAt(eye, center, up, Handedness.RIGHT_HANDED, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 lookAt(double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) { return lookAt(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, Handedness.RIGHT_HANDED, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Set this matrix to an arcball view transformation about the given center.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param radius the distance of the eye from the center
     * @param center the center of the arcball, i.e. the point the eye orbits around
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this
     */
    @Mutated Double4x4 makeArcball(double radius, Double3R center, double angleX, double angleY);

    /**
     * Set this matrix to an arcball view transformation about the given center.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param radius the distance of the eye from the center
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return this
     */
    @Mutated Double4x4 makeArcball(double radius, double x, double y, double z, double angleX, double angleY);

    /**
     * Set this matrix to an axonometric dimetric viewing rotation (compose with an orthographic
     * projection for the full transform).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param alpha the tilt angle in radians about the X axis (at {@code atan(1/sqrt(2))} the
     *        result is isometric)
     * @return this
     */
    @Mutated Double4x4 makeAxonometricDimetric(double alpha);

    /**
     * Set this matrix to an axonometric isometric viewing rotation (compose with an orthographic
     * projection for the full transform).
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeAxonometricIsometric();

    /**
     * Set this matrix to an axonometric trimetric viewing rotation (compose with an orthographic
     * projection for the full transform).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param alphaX the rotation angle in radians about the X axis
     * @param alphaY the rotation angle in radians about the Y axis
     * @return this
     */
    @Mutated Double4x4 makeAxonometricTrimetric(double alphaX, double alphaY);

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
    @Mutated Double4x4 makeBillboardCylindrical(Double3R objPos, Double3R targetPos, Double3R up);

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
    @Mutated Double4x4 makeBillboardCylindrical(double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ, double upX, double upY, double upZ);

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
    @Mutated Double4x4 makeBillboardSpherical(Double3R objPos, Double3R targetPos, Double3R up);

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
    @Mutated Double4x4 makeBillboardSpherical(double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ, double upX, double upY, double upZ);

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
    @Mutated default Double4x4 targetTo(Double3R objPos, Double3R targetPos, Double3R up) { return makeBillboardSpherical(objPos, targetPos, up); }

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
    @Mutated default Double4x4 targetTo(double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ, double upX, double upY, double upZ) { return makeBillboardSpherical(objPosX, objPosY, objPosZ, targetPosX, targetPosY, targetPosZ, upX, upY, upZ); }

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
    @Mutated Double4x4 makeBillboardSphericalShortest(Double3R objPos, Double3R targetPos);

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
    @Mutated Double4x4 makeBillboardSphericalShortest(double objPosX, double objPosY, double objPosZ, double targetPosX, double targetPosY, double targetPosZ);

    /**
     * Set this matrix to the rigid transformation represented by the unit dual quaternion
     * {@code dq}.
     * <p>
     * Valid input: {@code dq} must be a unit dual quaternion.
     *
     * @param dq the dual quaternion to convert
     * @return this
     */
    @Mutated Double4x4 makeFromDualQuat(DoubleDualQuatR dq);

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
    @Mutated Double4x4 makeFromDualQuat(double rX, double rY, double rZ, double rW, double dX, double dY, double dZ, double dW);

    /**
     * Set this matrix to an arbitrary perspective projection frustum transformation.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ; {@code zNear} must be positive; {@code zFar} must be positive; {@code zNear} and
     * {@code zFar} must differ; {@code zFar} may be {@code +Infinity}.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param zNear the distance to the near clip plane (left/right/bottom/top are measured at the
     *        near plane, so it cannot be at infinity here - the angle-based builders support an
     *        infinite near plane)
     * @param zFar the distance to the far clip plane (positive infinity gives an infinite far
     *        plane)
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this
     */
    @Mutated Double4x4 makeFrustum(double left, double right, double bottom, double top, double zNear, double zFar, Handedness handedness, DepthRange depthRange);

    /**
     * Set this matrix to an arbitrary perspective projection frustum transformation.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ; {@code zNear} must be positive; {@code zFar} must be positive; {@code zNear} and
     * {@code zFar} must differ; {@code zFar} may be {@code +Infinity}.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param zNear the distance to the near clip plane (left/right/bottom/top are measured at the
     *        near plane, so it cannot be at infinity here - the angle-based builders support an
     *        infinite near plane)
     * @param zFar the distance to the far clip plane (positive infinity gives an infinite far
     *        plane)
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this
     */
    @Mutated default Double4x4 makeFrustum(double left, double right, double bottom, double top, double zNear, double zFar, DepthRange depthRange) { return makeFrustum(left, right, bottom, top, zNear, zFar, Handedness.RIGHT_HANDED, depthRange); }

    /**
     * Set this matrix to an arbitrary perspective projection frustum transformation.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ; {@code zNear} must be positive; {@code zFar} must be positive; {@code zNear} and
     * {@code zFar} must differ; {@code zFar} may be {@code +Infinity}.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param zNear the distance to the near clip plane (left/right/bottom/top are measured at the
     *        near plane, so it cannot be at infinity here - the angle-based builders support an
     *        infinite near plane)
     * @param zFar the distance to the far clip plane (positive infinity gives an infinite far
     *        plane)
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated default Double4x4 makeFrustum(double left, double right, double bottom, double top, double zNear, double zFar, Handedness handedness) { return makeFrustum(left, right, bottom, top, zNear, zFar, handedness, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Set this matrix to an arbitrary perspective projection frustum transformation.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ; {@code zNear} must be positive; {@code zFar} must be positive; {@code zNear} and
     * {@code zFar} must differ; {@code zFar} may be {@code +Infinity}.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param zNear the distance to the near clip plane (left/right/bottom/top are measured at the
     *        near plane, so it cannot be at infinity here - the angle-based builders support an
     *        infinite near plane)
     * @param zFar the distance to the far clip plane (positive infinity gives an infinite far
     *        plane)
     * @return this
     */
    @Mutated default Double4x4 makeFrustum(double left, double right, double bottom, double top, double zNear, double zFar) { return makeFrustum(left, right, bottom, top, zNear, zFar, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE); }

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
    @Mutated Double4x4 makeLookAt(Double3R eye, Double3R center, Double3R up, Handedness handedness);

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
    @Mutated Double4x4 makeLookAt(double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ, Handedness handedness);

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
    @Mutated default Double4x4 makeLookAt(Double3R eye, Double3R center, Double3R up) { return makeLookAt(eye, center, up, Handedness.RIGHT_HANDED); }

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
    @Mutated default Double4x4 makeLookAt(double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) { return makeLookAt(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, Handedness.RIGHT_HANDED); }

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+x, +y, +z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingXYZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+x, +y, -z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingXYnZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+x, +z, +y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingXZY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+x, +z, -y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingXZnY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+x, -y, +z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingXnYZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+x, -y, -z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingXnYnZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+x, -z, +y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingXnZY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+x, -z, -y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingXnZnY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+y, +x, +z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingYXZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+y, +x, -z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingYXnZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+y, +z, +x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingYZX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+y, +z, -x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingYZnX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+y, -x, +z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingYnXZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+y, -x, -z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingYnXnZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+y, -z, +x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingYnZX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+y, -z, -x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingYnZnX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+z, +x, +y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingZXY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+z, +x, -y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingZXnY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+z, +y, +x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingZYX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+z, +y, -x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingZYnX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+z, -x, +y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingZnXY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+z, -x, -y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingZnXnY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+z, -y, +x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingZnYX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (+z, -y, -x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingZnYnX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-x, +y, +z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnXYZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-x, +y, -z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnXYnZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-x, +z, +y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnXZY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-x, +z, -y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnXZnY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-x, -y, +z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnXnYZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-x, -y, -z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnXnYnZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-x, -z, +y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnXnZY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-x, -z, -y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnXnZnY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-y, +x, +z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnYXZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-y, +x, -z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnYXnZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-y, +z, +x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnYZX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-y, +z, -x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnYZnX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-y, -x, +z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnYnXZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-y, -x, -z)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnYnXnZ();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-y, -z, +x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnYnZX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-y, -z, -x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnYnZnX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-z, +x, +y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnZXY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-z, +x, -y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnZXnY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-z, +y, +x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnZYX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-z, +y, -x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnZYnX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-z, -x, +y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnZnXY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-z, -x, -y)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnZnXnY();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-z, -y, +x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnZnYX();

    /**
     * Set this matrix to a transformation that maps the axes {@code (x, y, z)} to
     * {@code (-z, -y, -x)}.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return this
     */
    @Mutated Double4x4 makeMappingnZnYnX();

    /**
     * Set this matrix to an oblique cabinet projection shear (compose with an orthographic
     * projection for the full transform).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated Double4x4 makeObliqueCabinet(double angle);

    /**
     * Set this matrix to an oblique cavalier projection shear (compose with an orthographic
     * projection for the full transform).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated Double4x4 makeObliqueCavalier(double angle);

    /**
     * Set this matrix to an oblique projection shear drawing the XZ plane true shape and the Y axis
     * at {@code angle}, depth {@code -y} (the military projection of a Y-up scene seen from below
     * by a right-handed orthographic projection, or the cavalier projection of a Z-up scene;
     * {@code obliquePlanometric} shows a Y-up scene from above; compose with an orthographic
     * projection for the full transform).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle, in radians, at which the Y axis is drawn from the screen's +x axis
     * @return this
     */
    @Mutated Double4x4 makeObliqueMilitary(double angle);

    /**
     * Set this matrix to a military (planometric) projection of a Y-up scene seen from above: the
     * XZ plan true shape turned by {@code angle}, verticals drawn straight up at full length, depth
     * {@code y} (compose with an orthographic projection for the full transform).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle, in radians, by which the XZ plan is turned counter-clockwise on
     *        screen
     * @return this
     */
    @Mutated Double4x4 makeObliquePlanometric(double angle);

    /**
     * Set this matrix to an orthographic projection transformation.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ; {@code zNear} and {@code zFar} must differ.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param zNear the distance to the near clip plane
     * @param zFar the distance to the far clip plane
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this
     */
    @Mutated Double4x4 makeOrtho(double left, double right, double bottom, double top, double zNear, double zFar, Handedness handedness, DepthRange depthRange);

    /**
     * Set this matrix to an orthographic projection transformation.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ; {@code zNear} and {@code zFar} must differ.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param zNear the distance to the near clip plane
     * @param zFar the distance to the far clip plane
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this
     */
    @Mutated default Double4x4 makeOrtho(double left, double right, double bottom, double top, double zNear, double zFar, DepthRange depthRange) { return makeOrtho(left, right, bottom, top, zNear, zFar, Handedness.RIGHT_HANDED, depthRange); }

    /**
     * Set this matrix to an orthographic projection transformation.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ; {@code zNear} and {@code zFar} must differ.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param zNear the distance to the near clip plane
     * @param zFar the distance to the far clip plane
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated default Double4x4 makeOrtho(double left, double right, double bottom, double top, double zNear, double zFar, Handedness handedness) { return makeOrtho(left, right, bottom, top, zNear, zFar, handedness, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Set this matrix to an orthographic projection transformation.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ; {@code zNear} and {@code zFar} must differ.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param zNear the distance to the near clip plane
     * @param zFar the distance to the far clip plane
     * @return this
     */
    @Mutated default Double4x4 makeOrtho(double left, double right, double bottom, double top, double zNear, double zFar) { return makeOrtho(left, right, bottom, top, zNear, zFar, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Set this matrix to a 2D orthographic projection transformation.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this
     */
    @Mutated Double4x4 makeOrtho2D(double left, double right, double bottom, double top, Handedness handedness, DepthRange depthRange);

    /**
     * Set this matrix to a 2D orthographic projection transformation.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this
     */
    @Mutated default Double4x4 makeOrtho2D(double left, double right, double bottom, double top, DepthRange depthRange) { return makeOrtho2D(left, right, bottom, top, Handedness.RIGHT_HANDED, depthRange); }

    /**
     * Set this matrix to a 2D orthographic projection transformation.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated default Double4x4 makeOrtho2D(double left, double right, double bottom, double top, Handedness handedness) { return makeOrtho2D(left, right, bottom, top, handedness, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Set this matrix to a 2D orthographic projection transformation.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @return this
     */
    @Mutated default Double4x4 makeOrtho2D(double left, double right, double bottom, double top) { return makeOrtho2D(left, right, bottom, top, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Set this matrix to a symmetric perspective projection frustum transformation.
     * <p>
     * Valid input: {@code fovy} must lie in {@code (0, PI)}; {@code aspect} must be positive;
     * {@code near} must be positive; {@code far} must be positive; {@code near} and {@code far}
     * must differ; {@code near} may be {@code +Infinity}; {@code far} may be {@code +Infinity}.
     *
     * @param fovy the vertical field of view in radians
     * @param aspect the aspect ratio of the view (i.e. width / height)
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this
     */
    @Mutated Double4x4 makePerspective(double fovy, double aspect, double near, double far, Handedness handedness, DepthRange depthRange);

    /**
     * Set this matrix to a symmetric perspective projection frustum transformation.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code fovy} must lie in {@code (0, PI)}; {@code aspect} must be positive;
     * {@code near} must be positive; {@code far} must be positive; {@code near} and {@code far}
     * must differ; {@code near} may be {@code +Infinity}; {@code far} may be {@code +Infinity}.
     *
     * @param fovy the vertical field of view in radians
     * @param aspect the aspect ratio of the view (i.e. width / height)
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this
     */
    @Mutated default Double4x4 makePerspective(double fovy, double aspect, double near, double far, DepthRange depthRange) { return makePerspective(fovy, aspect, near, far, Handedness.RIGHT_HANDED, depthRange); }

    /**
     * Set this matrix to a symmetric perspective projection frustum transformation.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code fovy} must lie in {@code (0, PI)}; {@code aspect} must be positive;
     * {@code near} must be positive; {@code far} must be positive; {@code near} and {@code far}
     * must differ; {@code near} may be {@code +Infinity}; {@code far} may be {@code +Infinity}.
     *
     * @param fovy the vertical field of view in radians
     * @param aspect the aspect ratio of the view (i.e. width / height)
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated default Double4x4 makePerspective(double fovy, double aspect, double near, double far, Handedness handedness) { return makePerspective(fovy, aspect, near, far, handedness, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Set this matrix to a symmetric perspective projection frustum transformation.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code fovy} must lie in {@code (0, PI)}; {@code aspect} must be positive;
     * {@code near} must be positive; {@code far} must be positive; {@code near} and {@code far}
     * must differ; {@code near} may be {@code +Infinity}; {@code far} may be {@code +Infinity}.
     *
     * @param fovy the vertical field of view in radians
     * @param aspect the aspect ratio of the view (i.e. width / height)
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @return this
     */
    @Mutated default Double4x4 makePerspective(double fovy, double aspect, double near, double far) { return makePerspective(fovy, aspect, near, far, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Set this matrix to a perspective projection frustum transformation for the given vertical
     * field-of-view range.
     * <p>
     * Valid input: {@code angleMin} must lie in {@code (-PI/2, PI/2)}; {@code angleMax} must lie in
     * {@code (-PI/2, PI/2)}; {@code angleMin} must be less than {@code angleMax}; {@code aspect}
     * must be positive; {@code near} must be positive; {@code far} must be positive; {@code near}
     * and {@code far} must differ; {@code near} may be {@code +Infinity}; {@code far} may be
     * {@code +Infinity}.
     *
     * @param angleMin the minimum vertical field-of-view angle in radians
     * @param angleMax the maximum vertical field-of-view angle in radians
     * @param aspect the aspect ratio of the view (i.e. width / height)
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this
     */
    @Mutated Double4x4 makePerspectiveFovRange(double angleMin, double angleMax, double aspect, double near, double far, Handedness handedness, DepthRange depthRange);

    /**
     * Set this matrix to a perspective projection frustum transformation for the given vertical
     * field-of-view range.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code angleMin} must lie in {@code (-PI/2, PI/2)}; {@code angleMax} must lie in
     * {@code (-PI/2, PI/2)}; {@code angleMin} must be less than {@code angleMax}; {@code aspect}
     * must be positive; {@code near} must be positive; {@code far} must be positive; {@code near}
     * and {@code far} must differ; {@code near} may be {@code +Infinity}; {@code far} may be
     * {@code +Infinity}.
     *
     * @param angleMin the minimum vertical field-of-view angle in radians
     * @param angleMax the maximum vertical field-of-view angle in radians
     * @param aspect the aspect ratio of the view (i.e. width / height)
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this
     */
    @Mutated default Double4x4 makePerspectiveFovRange(double angleMin, double angleMax, double aspect, double near, double far, DepthRange depthRange) { return makePerspectiveFovRange(angleMin, angleMax, aspect, near, far, Handedness.RIGHT_HANDED, depthRange); }

    /**
     * Set this matrix to a perspective projection frustum transformation for the given vertical
     * field-of-view range.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code angleMin} must lie in {@code (-PI/2, PI/2)}; {@code angleMax} must lie in
     * {@code (-PI/2, PI/2)}; {@code angleMin} must be less than {@code angleMax}; {@code aspect}
     * must be positive; {@code near} must be positive; {@code far} must be positive; {@code near}
     * and {@code far} must differ; {@code near} may be {@code +Infinity}; {@code far} may be
     * {@code +Infinity}.
     *
     * @param angleMin the minimum vertical field-of-view angle in radians
     * @param angleMax the maximum vertical field-of-view angle in radians
     * @param aspect the aspect ratio of the view (i.e. width / height)
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated default Double4x4 makePerspectiveFovRange(double angleMin, double angleMax, double aspect, double near, double far, Handedness handedness) { return makePerspectiveFovRange(angleMin, angleMax, aspect, near, far, handedness, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Set this matrix to a perspective projection frustum transformation for the given vertical
     * field-of-view range.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code angleMin} must lie in {@code (-PI/2, PI/2)}; {@code angleMax} must lie in
     * {@code (-PI/2, PI/2)}; {@code angleMin} must be less than {@code angleMax}; {@code aspect}
     * must be positive; {@code near} must be positive; {@code far} must be positive; {@code near}
     * and {@code far} must differ; {@code near} may be {@code +Infinity}; {@code far} may be
     * {@code +Infinity}.
     *
     * @param angleMin the minimum vertical field-of-view angle in radians
     * @param angleMax the maximum vertical field-of-view angle in radians
     * @param aspect the aspect ratio of the view (i.e. width / height)
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @return this
     */
    @Mutated default Double4x4 makePerspectiveFovRange(double angleMin, double angleMax, double aspect, double near, double far) { return makePerspectiveFovRange(angleMin, angleMax, aspect, near, far, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Set this matrix to an asymmetric perspective projection frustum transformation with the
     * frustum sides given as view-axis angles.
     * <p>
     * Valid input: {@code angleLeft} must lie in {@code (-PI/2, PI/2)}; {@code angleRight} must lie
     * in {@code (-PI/2, PI/2)}; {@code angleDown} must lie in {@code (-PI/2, PI/2)};
     * {@code angleUp} must lie in {@code (-PI/2, PI/2)}; {@code angleLeft} must be less than
     * {@code angleRight}; {@code angleDown} must be less than {@code angleUp}; {@code near} must be
     * positive; {@code far} must be positive; {@code near} and {@code far} must differ;
     * {@code near} may be {@code +Infinity}; {@code far} may be {@code +Infinity}.
     *
     * @param angleLeft the angle in radians from the view axis to the left frustum edge (negative
     *        for a frustum extending to the left)
     * @param angleRight the angle in radians from the view axis to the right frustum edge
     * @param angleDown the angle in radians from the view axis to the bottom frustum edge (negative
     *        for a frustum extending downwards)
     * @param angleUp the angle in radians from the view axis to the top frustum edge
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this
     */
    @Mutated Double4x4 makePerspectiveOffCenterFov(double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far, Handedness handedness, DepthRange depthRange);

    /**
     * Set this matrix to an asymmetric perspective projection frustum transformation with the
     * frustum sides given as view-axis angles.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code angleLeft} must lie in {@code (-PI/2, PI/2)}; {@code angleRight} must lie
     * in {@code (-PI/2, PI/2)}; {@code angleDown} must lie in {@code (-PI/2, PI/2)};
     * {@code angleUp} must lie in {@code (-PI/2, PI/2)}; {@code angleLeft} must be less than
     * {@code angleRight}; {@code angleDown} must be less than {@code angleUp}; {@code near} must be
     * positive; {@code far} must be positive; {@code near} and {@code far} must differ;
     * {@code near} may be {@code +Infinity}; {@code far} may be {@code +Infinity}.
     *
     * @param angleLeft the angle in radians from the view axis to the left frustum edge (negative
     *        for a frustum extending to the left)
     * @param angleRight the angle in radians from the view axis to the right frustum edge
     * @param angleDown the angle in radians from the view axis to the bottom frustum edge (negative
     *        for a frustum extending downwards)
     * @param angleUp the angle in radians from the view axis to the top frustum edge
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this
     */
    @Mutated default Double4x4 makePerspectiveOffCenterFov(double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far, DepthRange depthRange) { return makePerspectiveOffCenterFov(angleLeft, angleRight, angleDown, angleUp, near, far, Handedness.RIGHT_HANDED, depthRange); }

    /**
     * Set this matrix to an asymmetric perspective projection frustum transformation with the
     * frustum sides given as view-axis angles.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code angleLeft} must lie in {@code (-PI/2, PI/2)}; {@code angleRight} must lie
     * in {@code (-PI/2, PI/2)}; {@code angleDown} must lie in {@code (-PI/2, PI/2)};
     * {@code angleUp} must lie in {@code (-PI/2, PI/2)}; {@code angleLeft} must be less than
     * {@code angleRight}; {@code angleDown} must be less than {@code angleUp}; {@code near} must be
     * positive; {@code far} must be positive; {@code near} and {@code far} must differ;
     * {@code near} may be {@code +Infinity}; {@code far} may be {@code +Infinity}.
     *
     * @param angleLeft the angle in radians from the view axis to the left frustum edge (negative
     *        for a frustum extending to the left)
     * @param angleRight the angle in radians from the view axis to the right frustum edge
     * @param angleDown the angle in radians from the view axis to the bottom frustum edge (negative
     *        for a frustum extending downwards)
     * @param angleUp the angle in radians from the view axis to the top frustum edge
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated default Double4x4 makePerspectiveOffCenterFov(double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far, Handedness handedness) { return makePerspectiveOffCenterFov(angleLeft, angleRight, angleDown, angleUp, near, far, handedness, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Set this matrix to an asymmetric perspective projection frustum transformation with the
     * frustum sides given as view-axis angles.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code angleLeft} must lie in {@code (-PI/2, PI/2)}; {@code angleRight} must lie
     * in {@code (-PI/2, PI/2)}; {@code angleDown} must lie in {@code (-PI/2, PI/2)};
     * {@code angleUp} must lie in {@code (-PI/2, PI/2)}; {@code angleLeft} must be less than
     * {@code angleRight}; {@code angleDown} must be less than {@code angleUp}; {@code near} must be
     * positive; {@code far} must be positive; {@code near} and {@code far} must differ;
     * {@code near} may be {@code +Infinity}; {@code far} may be {@code +Infinity}.
     *
     * @param angleLeft the angle in radians from the view axis to the left frustum edge (negative
     *        for a frustum extending to the left)
     * @param angleRight the angle in radians from the view axis to the right frustum edge
     * @param angleDown the angle in radians from the view axis to the bottom frustum edge (negative
     *        for a frustum extending downwards)
     * @param angleUp the angle in radians from the view axis to the top frustum edge
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @return this
     */
    @Mutated default Double4x4 makePerspectiveOffCenterFov(double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) { return makePerspectiveOffCenterFov(angleLeft, angleRight, angleDown, angleUp, near, far, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Set this matrix to an asymmetric perspective projection frustum transformation for the given
     * near-plane rectangle.
     * <p>
     * Valid input: {@code x} and {@code y} must not be parallel; {@code eye} must not lie in the
     * plane through {@code p} spanned by {@code x} and {@code y}; {@code nearFarDist} must be
     * non-zero.
     *
     * @param eye the position of the camera
     * @param p the bottom-left corner of the near-plane rectangle
     * @param x the direction and length of the rectangle's local X edge
     * @param y the direction and length of the rectangle's local Y edge
     * @param nearFarDist the distance between the near and far planes (must be positive and finite)
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this
     */
    @Mutated Double4x4 makePerspectiveOffCenterRectangleProj(Double3R eye, Double3R p, Double3R x, Double3R y, double nearFarDist, Handedness handedness, DepthRange depthRange);

    /**
     * Set this matrix to an asymmetric perspective projection frustum transformation for the given
     * near-plane rectangle.
     * <p>
     * Valid input: {@code (xX, xY, xZ)} and {@code (yX, yY, yZ)} must not be parallel;
     * {@code (eyeX, eyeY, eyeZ)} must not lie in the plane through {@code (pX, pY, pZ)} spanned by
     * {@code (xX, xY, xZ)} and {@code (yX, yY, yZ)}; {@code nearFarDist} must be non-zero.
     *
     * @param eyeX the {@code x} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param eyeY the {@code y} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param eyeZ the {@code z} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param pX the {@code x} component of the bottom-left corner of the near-plane rectangle
     *        {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the bottom-left corner of the near-plane rectangle
     *        {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the bottom-left corner of the near-plane rectangle
     *        {@code (pX, pY, pZ)}
     * @param xX the {@code x} component of the direction and length of the rectangle's local X edge
     *        {@code (xX, xY, xZ)}
     * @param xY the {@code y} component of the direction and length of the rectangle's local X edge
     *        {@code (xX, xY, xZ)}
     * @param xZ the {@code z} component of the direction and length of the rectangle's local X edge
     *        {@code (xX, xY, xZ)}
     * @param yX the {@code x} component of the direction and length of the rectangle's local Y edge
     *        {@code (yX, yY, yZ)}
     * @param yY the {@code y} component of the direction and length of the rectangle's local Y edge
     *        {@code (yX, yY, yZ)}
     * @param yZ the {@code z} component of the direction and length of the rectangle's local Y edge
     *        {@code (yX, yY, yZ)}
     * @param nearFarDist the distance between the near and far planes (must be positive and finite)
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this
     */
    @Mutated Double4x4 makePerspectiveOffCenterRectangleProj(double eyeX, double eyeY, double eyeZ, double pX, double pY, double pZ, double xX, double xY, double xZ, double yX, double yY, double yZ, double nearFarDist, Handedness handedness, DepthRange depthRange);

    /**
     * Set this matrix to an asymmetric perspective projection frustum transformation for the given
     * near-plane rectangle.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code x} and {@code y} must not be parallel; {@code eye} must not lie in the
     * plane through {@code p} spanned by {@code x} and {@code y}; {@code nearFarDist} must be
     * non-zero.
     *
     * @param eye the position of the camera
     * @param p the bottom-left corner of the near-plane rectangle
     * @param x the direction and length of the rectangle's local X edge
     * @param y the direction and length of the rectangle's local Y edge
     * @param nearFarDist the distance between the near and far planes (must be positive and finite)
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this
     */
    @Mutated default Double4x4 makePerspectiveOffCenterRectangleProj(Double3R eye, Double3R p, Double3R x, Double3R y, double nearFarDist, DepthRange depthRange) { return makePerspectiveOffCenterRectangleProj(eye, p, x, y, nearFarDist, Handedness.RIGHT_HANDED, depthRange); }

    /**
     * Set this matrix to an asymmetric perspective projection frustum transformation for the given
     * near-plane rectangle.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code (xX, xY, xZ)} and {@code (yX, yY, yZ)} must not be parallel;
     * {@code (eyeX, eyeY, eyeZ)} must not lie in the plane through {@code (pX, pY, pZ)} spanned by
     * {@code (xX, xY, xZ)} and {@code (yX, yY, yZ)}; {@code nearFarDist} must be non-zero.
     *
     * @param eyeX the {@code x} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param eyeY the {@code y} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param eyeZ the {@code z} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param pX the {@code x} component of the bottom-left corner of the near-plane rectangle
     *        {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the bottom-left corner of the near-plane rectangle
     *        {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the bottom-left corner of the near-plane rectangle
     *        {@code (pX, pY, pZ)}
     * @param xX the {@code x} component of the direction and length of the rectangle's local X edge
     *        {@code (xX, xY, xZ)}
     * @param xY the {@code y} component of the direction and length of the rectangle's local X edge
     *        {@code (xX, xY, xZ)}
     * @param xZ the {@code z} component of the direction and length of the rectangle's local X edge
     *        {@code (xX, xY, xZ)}
     * @param yX the {@code x} component of the direction and length of the rectangle's local Y edge
     *        {@code (yX, yY, yZ)}
     * @param yY the {@code y} component of the direction and length of the rectangle's local Y edge
     *        {@code (yX, yY, yZ)}
     * @param yZ the {@code z} component of the direction and length of the rectangle's local Y edge
     *        {@code (yX, yY, yZ)}
     * @param nearFarDist the distance between the near and far planes (must be positive and finite)
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this
     */
    @Mutated default Double4x4 makePerspectiveOffCenterRectangleProj(double eyeX, double eyeY, double eyeZ, double pX, double pY, double pZ, double xX, double xY, double xZ, double yX, double yY, double yZ, double nearFarDist, DepthRange depthRange) { return makePerspectiveOffCenterRectangleProj(eyeX, eyeY, eyeZ, pX, pY, pZ, xX, xY, xZ, yX, yY, yZ, nearFarDist, Handedness.RIGHT_HANDED, depthRange); }

    /**
     * Set this matrix to an asymmetric perspective projection frustum transformation for the given
     * near-plane rectangle.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code x} and {@code y} must not be parallel; {@code eye} must not lie in the
     * plane through {@code p} spanned by {@code x} and {@code y}; {@code nearFarDist} must be
     * non-zero.
     *
     * @param eye the position of the camera
     * @param p the bottom-left corner of the near-plane rectangle
     * @param x the direction and length of the rectangle's local X edge
     * @param y the direction and length of the rectangle's local Y edge
     * @param nearFarDist the distance between the near and far planes (must be positive and finite)
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated default Double4x4 makePerspectiveOffCenterRectangleProj(Double3R eye, Double3R p, Double3R x, Double3R y, double nearFarDist, Handedness handedness) { return makePerspectiveOffCenterRectangleProj(eye, p, x, y, nearFarDist, handedness, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Set this matrix to an asymmetric perspective projection frustum transformation for the given
     * near-plane rectangle.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code (xX, xY, xZ)} and {@code (yX, yY, yZ)} must not be parallel;
     * {@code (eyeX, eyeY, eyeZ)} must not lie in the plane through {@code (pX, pY, pZ)} spanned by
     * {@code (xX, xY, xZ)} and {@code (yX, yY, yZ)}; {@code nearFarDist} must be non-zero.
     *
     * @param eyeX the {@code x} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param eyeY the {@code y} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param eyeZ the {@code z} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param pX the {@code x} component of the bottom-left corner of the near-plane rectangle
     *        {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the bottom-left corner of the near-plane rectangle
     *        {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the bottom-left corner of the near-plane rectangle
     *        {@code (pX, pY, pZ)}
     * @param xX the {@code x} component of the direction and length of the rectangle's local X edge
     *        {@code (xX, xY, xZ)}
     * @param xY the {@code y} component of the direction and length of the rectangle's local X edge
     *        {@code (xX, xY, xZ)}
     * @param xZ the {@code z} component of the direction and length of the rectangle's local X edge
     *        {@code (xX, xY, xZ)}
     * @param yX the {@code x} component of the direction and length of the rectangle's local Y edge
     *        {@code (yX, yY, yZ)}
     * @param yY the {@code y} component of the direction and length of the rectangle's local Y edge
     *        {@code (yX, yY, yZ)}
     * @param yZ the {@code z} component of the direction and length of the rectangle's local Y edge
     *        {@code (yX, yY, yZ)}
     * @param nearFarDist the distance between the near and far planes (must be positive and finite)
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated default Double4x4 makePerspectiveOffCenterRectangleProj(double eyeX, double eyeY, double eyeZ, double pX, double pY, double pZ, double xX, double xY, double xZ, double yX, double yY, double yZ, double nearFarDist, Handedness handedness) { return makePerspectiveOffCenterRectangleProj(eyeX, eyeY, eyeZ, pX, pY, pZ, xX, xY, xZ, yX, yY, yZ, nearFarDist, handedness, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Set this matrix to an asymmetric perspective projection frustum transformation for the given
     * near-plane rectangle.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code x} and {@code y} must not be parallel; {@code eye} must not lie in the
     * plane through {@code p} spanned by {@code x} and {@code y}; {@code nearFarDist} must be
     * non-zero.
     *
     * @param eye the position of the camera
     * @param p the bottom-left corner of the near-plane rectangle
     * @param x the direction and length of the rectangle's local X edge
     * @param y the direction and length of the rectangle's local Y edge
     * @param nearFarDist the distance between the near and far planes (must be positive and finite)
     * @return this
     */
    @Mutated default Double4x4 makePerspectiveOffCenterRectangleProj(Double3R eye, Double3R p, Double3R x, Double3R y, double nearFarDist) { return makePerspectiveOffCenterRectangleProj(eye, p, x, y, nearFarDist, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Set this matrix to an asymmetric perspective projection frustum transformation for the given
     * near-plane rectangle.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code (xX, xY, xZ)} and {@code (yX, yY, yZ)} must not be parallel;
     * {@code (eyeX, eyeY, eyeZ)} must not lie in the plane through {@code (pX, pY, pZ)} spanned by
     * {@code (xX, xY, xZ)} and {@code (yX, yY, yZ)}; {@code nearFarDist} must be non-zero.
     *
     * @param eyeX the {@code x} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param eyeY the {@code y} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param eyeZ the {@code z} component of the vector {@code (eyeX, eyeY, eyeZ)}
     * @param pX the {@code x} component of the bottom-left corner of the near-plane rectangle
     *        {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the bottom-left corner of the near-plane rectangle
     *        {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the bottom-left corner of the near-plane rectangle
     *        {@code (pX, pY, pZ)}
     * @param xX the {@code x} component of the direction and length of the rectangle's local X edge
     *        {@code (xX, xY, xZ)}
     * @param xY the {@code y} component of the direction and length of the rectangle's local X edge
     *        {@code (xX, xY, xZ)}
     * @param xZ the {@code z} component of the direction and length of the rectangle's local X edge
     *        {@code (xX, xY, xZ)}
     * @param yX the {@code x} component of the direction and length of the rectangle's local Y edge
     *        {@code (yX, yY, yZ)}
     * @param yY the {@code y} component of the direction and length of the rectangle's local Y edge
     *        {@code (yX, yY, yZ)}
     * @param yZ the {@code z} component of the direction and length of the rectangle's local Y edge
     *        {@code (yX, yY, yZ)}
     * @param nearFarDist the distance between the near and far planes (must be positive and finite)
     * @return this
     */
    @Mutated default Double4x4 makePerspectiveOffCenterRectangleProj(double eyeX, double eyeY, double eyeZ, double pX, double pY, double pZ, double xX, double xY, double xZ, double yX, double yY, double yZ, double nearFarDist) { return makePerspectiveOffCenterRectangleProj(eyeX, eyeY, eyeZ, pX, pY, pZ, xX, xY, xZ, yX, yY, yZ, nearFarDist, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Set this matrix to a view transformation for the given near-plane rectangle.
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
     * @param p the bottom-left corner of the near-plane rectangle
     * @param x the direction and length of the rectangle's local X edge
     * @param y the direction and length of the rectangle's local Y edge
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated Double4x4 makePerspectiveOffCenterRectangleView(Double3R eye, Double3R p, Double3R x, Double3R y, Handedness handedness);

    /**
     * Set this matrix to a view transformation for the given near-plane rectangle.
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
     * @param pX the {@code x} component of the bottom-left corner of the near-plane rectangle
     *        {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the bottom-left corner of the near-plane rectangle
     *        {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the bottom-left corner of the near-plane rectangle
     *        {@code (pX, pY, pZ)}
     * @param xX the {@code x} component of the direction and length of the rectangle's local X edge
     *        {@code (xX, xY, xZ)}
     * @param xY the {@code y} component of the direction and length of the rectangle's local X edge
     *        {@code (xX, xY, xZ)}
     * @param xZ the {@code z} component of the direction and length of the rectangle's local X edge
     *        {@code (xX, xY, xZ)}
     * @param yX the {@code x} component of the direction and length of the rectangle's local Y edge
     *        {@code (yX, yY, yZ)}
     * @param yY the {@code y} component of the direction and length of the rectangle's local Y edge
     *        {@code (yX, yY, yZ)}
     * @param yZ the {@code z} component of the direction and length of the rectangle's local Y edge
     *        {@code (yX, yY, yZ)}
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated Double4x4 makePerspectiveOffCenterRectangleView(double eyeX, double eyeY, double eyeZ, double pX, double pY, double pZ, double xX, double xY, double xZ, double yX, double yY, double yZ, Handedness handedness);

    /**
     * Set this matrix to a view transformation for the given near-plane rectangle.
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
     * @param p the bottom-left corner of the near-plane rectangle
     * @param x the direction and length of the rectangle's local X edge
     * @param y the direction and length of the rectangle's local Y edge
     * @return this
     */
    @Mutated default Double4x4 makePerspectiveOffCenterRectangleView(Double3R eye, Double3R p, Double3R x, Double3R y) { return makePerspectiveOffCenterRectangleView(eye, p, x, y, Handedness.RIGHT_HANDED); }

    /**
     * Set this matrix to a view transformation for the given near-plane rectangle.
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
     * @param pX the {@code x} component of the bottom-left corner of the near-plane rectangle
     *        {@code (pX, pY, pZ)}
     * @param pY the {@code y} component of the bottom-left corner of the near-plane rectangle
     *        {@code (pX, pY, pZ)}
     * @param pZ the {@code z} component of the bottom-left corner of the near-plane rectangle
     *        {@code (pX, pY, pZ)}
     * @param xX the {@code x} component of the direction and length of the rectangle's local X edge
     *        {@code (xX, xY, xZ)}
     * @param xY the {@code y} component of the direction and length of the rectangle's local X edge
     *        {@code (xX, xY, xZ)}
     * @param xZ the {@code z} component of the direction and length of the rectangle's local X edge
     *        {@code (xX, xY, xZ)}
     * @param yX the {@code x} component of the direction and length of the rectangle's local Y edge
     *        {@code (yX, yY, yZ)}
     * @param yY the {@code y} component of the direction and length of the rectangle's local Y edge
     *        {@code (yX, yY, yZ)}
     * @param yZ the {@code z} component of the direction and length of the rectangle's local Y edge
     *        {@code (yX, yY, yZ)}
     * @return this
     */
    @Mutated default Double4x4 makePerspectiveOffCenterRectangleView(double eyeX, double eyeY, double eyeZ, double pX, double pY, double pZ, double xX, double xY, double xZ, double yX, double yY, double yZ) { return makePerspectiveOffCenterRectangleView(eyeX, eyeY, eyeZ, pX, pY, pZ, xX, xY, xZ, yX, yY, yZ, Handedness.RIGHT_HANDED); }

    /**
     * Set this matrix to a picking transformation restricting the view to the given picking region.
     * <p>
     * Valid input: {@code deltaX} must be non-zero; {@code deltaY} must be non-zero; {@code vpW}
     * must be non-zero; {@code vpH} must be non-zero.
     *
     * @param centerX the x coordinate of the picking-region center in window coordinates
     * @param centerY the y coordinate of the picking-region center in window coordinates
     * @param deltaX the width of the picking region in window coordinates
     * @param deltaY the height of the picking region in window coordinates
     * @param vpX the x coordinate of the viewport origin
     * @param vpY the y coordinate of the viewport origin
     * @param vpW the width of the viewport
     * @param vpH the height of the viewport
     * @return this
     */
    @Mutated Double4x4 makePickMatrix(double centerX, double centerY, double deltaX, double deltaY, double vpX, double vpY, double vpW, double vpH);

    /**
     * Set this matrix to a reflection transformation about the plane through the origin with the
     * normal {@code normal}.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the reflection plane
     * @return this
     */
    @Mutated Double4x4 makeReflection(Double3R normal);

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
    @Mutated Double4x4 makeReflection(double x, double y, double z);

    /**
     * Set this matrix to a rotation of {@code angle} radians about the axis {@code axis}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return this
     */
    @Mutated Double4x4 makeRotationAxis(double angle, Double3R axis);

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
    @Mutated Double4x4 makeRotationAxis(double angle, double x, double y, double z);

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
    @Mutated Double4x4 makeRotationLookAlong(Double3R dir, Double3R up);

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
    @Mutated Double4x4 makeRotationLookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ);

    /**
     * Set this matrix to the rotation represented by the quaternion {@code q}.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the rotation quaternion
     * @return this
     */
    @Mutated Double4x4 makeRotationQuat(DoubleQuatR q);

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
    @Mutated Double4x4 makeRotationQuat(double x, double y, double z, double w);

    /**
     * Set this matrix to a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated Double4x4 makeRotationX(double angle);

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
    @Mutated Double4x4 makeRotationXYZ(double angleX, double angleY, double angleZ);

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
    @Mutated Double4x4 makeRotationXZY(double angleX, double angleZ, double angleY);

    /**
     * Set this matrix to a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated Double4x4 makeRotationY(double angle);

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
    @Mutated Double4x4 makeRotationYXZ(double angleY, double angleX, double angleZ);

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
    @Mutated Double4x4 makeRotationYZX(double angleY, double angleZ, double angleX);

    /**
     * Set this matrix to a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this
     */
    @Mutated Double4x4 makeRotationZ(double angle);

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
    @Mutated Double4x4 makeRotationZXY(double angleZ, double angleX, double angleY);

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
    @Mutated Double4x4 makeRotationZYX(double angleZ, double angleY, double angleX);

    /**
     * Set this matrix to a scaling transformation that scales by {@code v}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @return this
     */
    @Mutated Double4x4 makeScaling(Double3R v);

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
    @Mutated Double4x4 makeScaling(double x, double y, double z);

    /**
     * Set this matrix to a scaling transformation that scales by {@code s}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @return this
     */
    @Mutated Double4x4 makeScaling(double s);

    /**
     * Set this matrix to a projection shadow transformation that projects geometry onto the given
     * plane, cast from the given light source (a direction when {@code light.w} is {@code 0}, a
     * position when it is {@code 1}).
     * <p>
     * Valid input: the normal of {@code plane} must be non-zero; {@code light} must not lie in
     * {@code plane}.
     *
     * @param light the light direction or position ({@code w = 0} for a directional light,
     *        {@code w = 1} for a point light)
     * @param plane the plane equation {@code (a, b, c, d)} with {@code ax + by + cz + d = 0}
     * @return this
     */
    @Mutated Double4x4 makeShadow(Double4R light, Double4R plane);

    /**
     * Set this matrix to a projection shadow transformation that projects geometry onto the given
     * plane, cast from the given light source (a direction when {@code lightW} is {@code 0}, a
     * position when it is {@code 1}).
     * <p>
     * Valid input: the normal of {@code (planeX, planeY, planeZ, planeW)} must be non-zero;
     * {@code (lightX, lightY, lightZ, lightW)} must not lie in
     * {@code (planeX, planeY, planeZ, planeW)}.
     *
     * @param lightX the {@code x} component of the vector {@code (lightX, lightY, lightZ, lightW)}
     * @param lightY the {@code y} component of the vector {@code (lightX, lightY, lightZ, lightW)}
     * @param lightZ the {@code z} component of the vector {@code (lightX, lightY, lightZ, lightW)}
     * @param lightW the {@code w} component of the vector {@code (lightX, lightY, lightZ, lightW)}
     * @param planeX the {@code x} component of the vector {@code (planeX, planeY, planeZ, planeW)}
     * @param planeY the {@code y} component of the vector {@code (planeX, planeY, planeZ, planeW)}
     * @param planeZ the {@code z} component of the vector {@code (planeX, planeY, planeZ, planeW)}
     * @param planeW the {@code w} component of the vector {@code (planeX, planeY, planeZ, planeW)}
     * @return this
     */
    @Mutated Double4x4 makeShadow(double lightX, double lightY, double lightZ, double lightW, double planeX, double planeY, double planeZ, double planeW);

    /**
     * Set this matrix to a shearing transformation.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param xy the shear of x proportional to y
     * @param xz the shear of x proportional to z
     * @param yx the shear of y proportional to x
     * @param yz the shear of y proportional to z
     * @param zx the shear of z proportional to x
     * @param zy the shear of z proportional to y
     * @return this
     */
    @Mutated Double4x4 makeShear(double xy, double xz, double yx, double yz, double zx, double zy);

    /**
     * Set this matrix to a transformation that restricts the view to one tile of a regular grid.
     * <p>
     * Valid input: {@code x} must not be negative; {@code x} must be less than {@code w}; {@code y}
     * must not be negative; {@code y} must be less than {@code h}; {@code w} must be positive;
     * {@code h} must be positive.
     *
     * @param x the column index of the tile, in {@code [0, w)}
     * @param y the row index of the tile, in {@code [0, h)}
     * @param w the number of tiles along the x axis
     * @param h the number of tiles along the y axis
     * @return this
     */
    @Mutated Double4x4 makeTile(double x, double y, double w, double h);

    /**
     * Set this matrix to a translation transformation that translates by {@code v}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @return this
     */
    @Mutated Double4x4 makeTranslation(Double3R v);

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
    @Mutated Double4x4 makeTranslation(double x, double y, double z);

    /**
     * Set this matrix to a trapezoid-crop transformation mapping the given trapezoid onto the
     * {@code [-1, +1]} clip square.
     * <p>
     * Valid input: {@code p0}, {@code p1}, {@code p2} and {@code p3} must be the corners of a
     * trapezoid, in that order, with the edge from {@code p0} to {@code p1} the shorter of its
     * parallel edges.
     *
     * @param p0 the left corner of the shorter parallel edge of the trapezoid
     * @param p1 the right corner of the shorter parallel edge of the trapezoid
     * @param p2 the right corner of the longer parallel edge of the trapezoid
     * @param p3 the left corner of the longer parallel edge of the trapezoid
     * @return this
     */
    @Mutated Double4x4 makeTrapezoidCrop(Double2R p0, Double2R p1, Double2R p2, Double2R p3);

    /**
     * Set this matrix to a trapezoid-crop transformation mapping the given trapezoid onto the
     * {@code [-1, +1]} clip square.
     * <p>
     * Valid input: {@code (p0X, p0Y)}, {@code (p1X, p1Y)}, {@code (p2X, p2Y)} and
     * {@code (p3X, p3Y)} must be the corners of a trapezoid, in that order, with the edge from
     * {@code (p0X, p0Y)} to {@code (p1X, p1Y)} the shorter of its parallel edges.
     *
     * @param p0X the {@code x} component of the vector {@code (p0X, p0Y)}
     * @param p0Y the {@code y} component of the vector {@code (p0X, p0Y)}
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y)}
     * @return this
     */
    @Mutated Double4x4 makeTrapezoidCrop(double p0X, double p0Y, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y);

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
    @Mutated default Double4x4 mapXYZ() { return mapXYZ(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapXYnZ() { return mapXYnZ(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapXZY() { return mapXZY(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapXZnY() { return mapXZnY(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapXnYZ() { return mapXnYZ(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapXnYnZ() { return mapXnYnZ(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapXnZY() { return mapXnZY(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapXnZnY() { return mapXnZnY(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapYXZ() { return mapYXZ(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapYXnZ() { return mapYXnZ(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapYZX() { return mapYZX(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapYZnX() { return mapYZnX(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapYnXZ() { return mapYnXZ(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapYnXnZ() { return mapYnXnZ(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapYnZX() { return mapYnZX(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapYnZnX() { return mapYnZnX(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapZXY() { return mapZXY(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapZXnY() { return mapZXnY(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapZYX() { return mapZYX(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapZYnX() { return mapZYnX(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapZnXY() { return mapZnXY(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapZnXnY() { return mapZnXnY(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapZnYX() { return mapZnYX(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapZnYnX() { return mapZnYnX(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnXYZ() { return mapnXYZ(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnXYnZ() { return mapnXYnZ(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnXZY() { return mapnXZY(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnXZnY() { return mapnXZnY(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnXnYZ() { return mapnXnYZ(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnXnYnZ() { return mapnXnYnZ(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnXnZY() { return mapnXnZY(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnXnZnY() { return mapnXnZnY(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnYXZ() { return mapnYXZ(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnYXnZ() { return mapnYXnZ(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnYZX() { return mapnYZX(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnYZnX() { return mapnYZnX(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnYnXZ() { return mapnYnXZ(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnYnXnZ() { return mapnYnXnZ(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnYnZX() { return mapnYnZX(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnYnZnX() { return mapnYnZnX(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnZXY() { return mapnZXY(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnZXnY() { return mapnZXnY(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnZYX() { return mapnZYX(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnZYnX() { return mapnZYnX(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnZnXY() { return mapnZnXY(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnZnXnY() { return mapnZnXnY(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnZnYX() { return mapnZnYX(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 mapnZnYnX() { return mapnZnYnX(Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply an oblique cabinet projection shear (compose with an orthographic projection for the
     * full transform) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code O} the oblique shear matrix, then the new
     * matrix will be {@code M * O}. So when transforming a vector {@code v} with the new matrix by
     * using {@code M * O * v}, the oblique shear will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 obliqueCabinet(double angle) { return obliqueCabinet(angle, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply an oblique cavalier projection shear (compose with an orthographic projection for the
     * full transform) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code O} the oblique shear matrix, then the new
     * matrix will be {@code M * O}. So when transforming a vector {@code v} with the new matrix by
     * using {@code M * O * v}, the oblique shear will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 obliqueCavalier(double angle) { return obliqueCavalier(angle, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply an oblique projection shear drawing the XZ plane true shape and the Y axis at
     * {@code angle}, depth {@code -y} (the military projection of a Y-up scene seen from below by a
     * right-handed orthographic projection, or the cavalier projection of a Z-up scene;
     * {@code obliquePlanometric} shows a Y-up scene from above; compose with an orthographic
     * projection for the full transform) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code O} the oblique shear matrix, then the new
     * matrix will be {@code M * O}. So when transforming a vector {@code v} with the new matrix by
     * using {@code M * O * v}, the oblique shear will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle, in radians, at which the Y axis is drawn from the screen's +x axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 obliqueMilitary(double angle) { return obliqueMilitary(angle, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply a military (planometric) projection of a Y-up scene seen from above: the XZ plan true
     * shape turned by {@code angle}, verticals drawn straight up at full length, depth {@code y}
     * (compose with an orthographic projection for the full transform) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code O} the oblique shear matrix, then the new
     * matrix will be {@code M * O}. So when transforming a vector {@code v} with the new matrix by
     * using {@code M * O * v}, the oblique shear will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle, in radians, by which the XZ plan is turned counter-clockwise on
     *        screen
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 obliquePlanometric(double angle) { return obliquePlanometric(angle, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * Valid input: this matrix must be a perspective projection with finite clip planes; the normal
     * of {@code plane} must be non-zero; the constant of {@code plane} must be negative;
     * {@code plane} must not contain the far frustum corner its normal points to.
     *
     * @param plane the clip plane {@code (a, b, c, d)} in camera space, with the normal pointing
     *        into the visible half-space
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 obliqueZ(Double4R plane, Handedness handedness, DepthRange depthRange) { return obliqueZ(plane, handedness, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * Valid input: this matrix must be a perspective projection with finite clip planes; the normal
     * of {@code (x, y, z, w)} must be non-zero; the constant of {@code (x, y, z, w)} must be
     * negative; {@code (x, y, z, w)} must not contain the far frustum corner its normal points to.
     *
     * @param x the {@code x} component of the clip plane {@code (a, b, c, d)} in camera space, with
     *        the normal pointing into the visible half-space
     * @param y the {@code y} component of the clip plane {@code (a, b, c, d)} in camera space, with
     *        the normal pointing into the visible half-space
     * @param z the {@code z} component of the clip plane {@code (a, b, c, d)} in camera space, with
     *        the normal pointing into the visible half-space
     * @param w the {@code w} component of the clip plane {@code (a, b, c, d)} in camera space, with
     *        the normal pointing into the visible half-space
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 obliqueZ(double x, double y, double z, double w, Handedness handedness, DepthRange depthRange) { return obliqueZ(x, y, z, w, handedness, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * Valid input: this matrix must be a perspective projection with finite clip planes; the normal
     * of {@code plane} must be non-zero; the constant of {@code plane} must be negative;
     * {@code plane} must not contain the far frustum corner its normal points to.
     *
     * @param plane the clip plane {@code (a, b, c, d)} in camera space, with the normal pointing
     *        into the visible half-space
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 obliqueZ(DoublePlaneR plane, Handedness handedness, DepthRange depthRange) { return obliqueZ(plane.a(), plane.b(), plane.c(), plane.d(), handedness, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: this matrix must be a perspective projection with finite clip planes; the normal
     * of {@code plane} must be non-zero; the constant of {@code plane} must be negative;
     * {@code plane} must not contain the far frustum corner its normal points to.
     *
     * @param plane the clip plane {@code (a, b, c, d)} in camera space, with the normal pointing
     *        into the visible half-space
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 obliqueZ(Double4R plane, DepthRange depthRange) { return obliqueZ(plane, Handedness.RIGHT_HANDED, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: this matrix must be a perspective projection with finite clip planes; the normal
     * of {@code (x, y, z, w)} must be non-zero; the constant of {@code (x, y, z, w)} must be
     * negative; {@code (x, y, z, w)} must not contain the far frustum corner its normal points to.
     *
     * @param x the {@code x} component of the clip plane {@code (a, b, c, d)} in camera space, with
     *        the normal pointing into the visible half-space
     * @param y the {@code y} component of the clip plane {@code (a, b, c, d)} in camera space, with
     *        the normal pointing into the visible half-space
     * @param z the {@code z} component of the clip plane {@code (a, b, c, d)} in camera space, with
     *        the normal pointing into the visible half-space
     * @param w the {@code w} component of the clip plane {@code (a, b, c, d)} in camera space, with
     *        the normal pointing into the visible half-space
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 obliqueZ(double x, double y, double z, double w, DepthRange depthRange) { return obliqueZ(x, y, z, w, Handedness.RIGHT_HANDED, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a perspective projection with finite clip planes; the normal
     * of {@code plane} must be non-zero; the constant of {@code plane} must be negative;
     * {@code plane} must not contain the far frustum corner its normal points to.
     *
     * @param plane the clip plane {@code (a, b, c, d)} in camera space, with the normal pointing
     *        into the visible half-space
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 obliqueZ(Double4R plane, Handedness handedness) { return obliqueZ(plane, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a perspective projection with finite clip planes; the normal
     * of {@code (x, y, z, w)} must be non-zero; the constant of {@code (x, y, z, w)} must be
     * negative; {@code (x, y, z, w)} must not contain the far frustum corner its normal points to.
     *
     * @param x the {@code x} component of the clip plane {@code (a, b, c, d)} in camera space, with
     *        the normal pointing into the visible half-space
     * @param y the {@code y} component of the clip plane {@code (a, b, c, d)} in camera space, with
     *        the normal pointing into the visible half-space
     * @param z the {@code z} component of the clip plane {@code (a, b, c, d)} in camera space, with
     *        the normal pointing into the visible half-space
     * @param w the {@code w} component of the clip plane {@code (a, b, c, d)} in camera space, with
     *        the normal pointing into the visible half-space
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 obliqueZ(double x, double y, double z, double w, Handedness handedness) { return obliqueZ(x, y, z, w, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a perspective projection with finite clip planes; the normal
     * of {@code plane} must be non-zero; the constant of {@code plane} must be negative;
     * {@code plane} must not contain the far frustum corner its normal points to.
     *
     * @param plane the clip plane {@code (a, b, c, d)} in camera space, with the normal pointing
     *        into the visible half-space
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 obliqueZ(Double4R plane) { return obliqueZ(plane, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a perspective projection with finite clip planes; the normal
     * of {@code (x, y, z, w)} must be non-zero; the constant of {@code (x, y, z, w)} must be
     * negative; {@code (x, y, z, w)} must not contain the far frustum corner its normal points to.
     *
     * @param x the {@code x} component of the clip plane {@code (a, b, c, d)} in camera space, with
     *        the normal pointing into the visible half-space
     * @param y the {@code y} component of the clip plane {@code (a, b, c, d)} in camera space, with
     *        the normal pointing into the visible half-space
     * @param z the {@code z} component of the clip plane {@code (a, b, c, d)} in camera space, with
     *        the normal pointing into the visible half-space
     * @param w the {@code w} component of the clip plane {@code (a, b, c, d)} in camera space, with
     *        the normal pointing into the visible half-space
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 obliqueZ(double x, double y, double z, double w) { return obliqueZ(x, y, z, w, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: this matrix must be a perspective projection with finite clip planes; the normal
     * of {@code plane} must be non-zero; the constant of {@code plane} must be negative;
     * {@code plane} must not contain the far frustum corner its normal points to.
     *
     * @param plane the clip plane {@code (a, b, c, d)} in camera space, with the normal pointing
     *        into the visible half-space
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 obliqueZ(DoublePlaneR plane, DepthRange depthRange) { return obliqueZ(plane, Handedness.RIGHT_HANDED, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a perspective projection with finite clip planes; the normal
     * of {@code plane} must be non-zero; the constant of {@code plane} must be negative;
     * {@code plane} must not contain the far frustum corner its normal points to.
     *
     * @param plane the clip plane {@code (a, b, c, d)} in camera space, with the normal pointing
     *        into the visible half-space
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 obliqueZ(DoublePlaneR plane, Handedness handedness) { return obliqueZ(plane, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a perspective projection with finite clip planes; the normal
     * of {@code plane} must be non-zero; the constant of {@code plane} must be negative;
     * {@code plane} must not contain the far frustum corner its normal points to.
     *
     * @param plane the clip plane {@code (a, b, c, d)} in camera space, with the normal pointing
     *        into the visible half-space
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 obliqueZ(DoublePlaneR plane) { return obliqueZ(plane, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply an orthographic projection transformation to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code O} the orthographic projection matrix, then
     * the new matrix will be {@code M * O}. So when transforming a vector {@code v} with the new
     * matrix by using {@code M * O * v}, the orthographic projection will be applied first.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ; {@code zNear} and {@code zFar} must differ.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param zNear the distance to the near clip plane
     * @param zFar the distance to the far clip plane
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 ortho(double left, double right, double bottom, double top, double zNear, double zFar, Handedness handedness, DepthRange depthRange) { return ortho(left, right, bottom, top, zNear, zFar, handedness, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply an orthographic projection transformation to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code O} the orthographic projection matrix, then
     * the new matrix will be {@code M * O}. So when transforming a vector {@code v} with the new
     * matrix by using {@code M * O * v}, the orthographic projection will be applied first.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ; {@code zNear} and {@code zFar} must differ.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param zNear the distance to the near clip plane
     * @param zFar the distance to the far clip plane
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 ortho(double left, double right, double bottom, double top, double zNear, double zFar, DepthRange depthRange) { return ortho(left, right, bottom, top, zNear, zFar, Handedness.RIGHT_HANDED, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply an orthographic projection transformation to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code O} the orthographic projection matrix, then
     * the new matrix will be {@code M * O}. So when transforming a vector {@code v} with the new
     * matrix by using {@code M * O * v}, the orthographic projection will be applied first.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ; {@code zNear} and {@code zFar} must differ.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param zNear the distance to the near clip plane
     * @param zFar the distance to the far clip plane
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 ortho(double left, double right, double bottom, double top, double zNear, double zFar, Handedness handedness) { return ortho(left, right, bottom, top, zNear, zFar, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply an orthographic projection transformation to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code O} the orthographic projection matrix, then
     * the new matrix will be {@code M * O}. So when transforming a vector {@code v} with the new
     * matrix by using {@code M * O * v}, the orthographic projection will be applied first.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ; {@code zNear} and {@code zFar} must differ.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param zNear the distance to the near clip plane
     * @param zFar the distance to the far clip plane
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 ortho(double left, double right, double bottom, double top, double zNear, double zFar) { return ortho(left, right, bottom, top, zNear, zFar, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply a 2D orthographic projection transformation to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code O} the orthographic projection matrix, then
     * the new matrix will be {@code M * O}. So when transforming a vector {@code v} with the new
     * matrix by using {@code M * O * v}, the orthographic projection will be applied first.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 ortho2D(double left, double right, double bottom, double top, Handedness handedness, DepthRange depthRange) { return ortho2D(left, right, bottom, top, handedness, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply a 2D orthographic projection transformation to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code O} the orthographic projection matrix, then
     * the new matrix will be {@code M * O}. So when transforming a vector {@code v} with the new
     * matrix by using {@code M * O * v}, the orthographic projection will be applied first.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 ortho2D(double left, double right, double bottom, double top, DepthRange depthRange) { return ortho2D(left, right, bottom, top, Handedness.RIGHT_HANDED, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply a 2D orthographic projection transformation to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code O} the orthographic projection matrix, then
     * the new matrix will be {@code M * O}. So when transforming a vector {@code v} with the new
     * matrix by using {@code M * O * v}, the orthographic projection will be applied first.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 ortho2D(double left, double right, double bottom, double top, Handedness handedness) { return ortho2D(left, right, bottom, top, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply a 2D orthographic projection transformation to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code O} the orthographic projection matrix, then
     * the new matrix will be {@code M * O}. So when transforming a vector {@code v} with the new
     * matrix by using {@code M * O * v}, the orthographic projection will be applied first.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ.
     *
     * @param left the distance to the left frustum edge
     * @param right the distance to the right frustum edge
     * @param bottom the distance to the bottom frustum edge
     * @param top the distance to the top frustum edge
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 ortho2D(double left, double right, double bottom, double top) { return ortho2D(left, right, bottom, top, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Build an orthographic projection that tightly fits the view-projection frustum represented by
     * {@code this} matrix into the given affine {@code view} transformation.
     * <p>
     * {@code this} must be the inverse of the combined camera view-projection matrix; {@code view}
     * is typically the light's view matrix (e.g. from {@code lookAt}). Typical use is fitting a
     * shadow-map projection around the camera frustum.
     * <p>
     * {@code depthRange} does double duty here: it selects the produced orthographic projection's
     * own clip-space depth range AND the source frustum's NDC z extent that "the whole frustum"
     * means - {@code [-1, 1]} for {@code NEGATIVE_ONE_TO_ONE}, {@code [0, 1]} for
     * {@code ZERO_TO_ONE}. That assumes the camera projection and the produced orthographic
     * projection share a convention. When they do not, pass the source's own NDC bounds explicitly
     * to the {@code minZ}/{@code maxZ} overload, whose bounds are independent of
     * {@code depthRange}.
     * <p>
     * Valid input: this matrix must be the inverse of a view-projection matrix with finite clip
     * planes; {@code view} must be affine; the frustum slice must have a non-zero extent along each
     * axis of {@code view}.
     *
     * @param view the light view transformation (must be affine)
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 orthoCrop(Double4x4R view, Handedness handedness, DepthRange depthRange) { return orthoCrop(view, handedness, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Build an orthographic projection that tightly fits the view-projection frustum represented by
     * {@code this} matrix into the given affine {@code view} transformation.
     * <p>
     * {@code this} must be the inverse of the combined camera view-projection matrix; {@code view}
     * is typically the light's view matrix (e.g. from {@code lookAt}). Typical use is fitting a
     * shadow-map projection around the camera frustum.
     * <p>
     * {@code depthRange} does double duty here: it selects the produced orthographic projection's
     * own clip-space depth range AND the source frustum's NDC z extent that "the whole frustum"
     * means - {@code [-1, 1]} for {@code NEGATIVE_ONE_TO_ONE}, {@code [0, 1]} for
     * {@code ZERO_TO_ONE}. That assumes the camera projection and the produced orthographic
     * projection share a convention. When they do not, pass the source's own NDC bounds explicitly
     * to the {@code minZ}/{@code maxZ} overload, whose bounds are independent of
     * {@code depthRange}.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: this matrix must be the inverse of a view-projection matrix with finite clip
     * planes; {@code view} must be affine; the frustum slice must have a non-zero extent along each
     * axis of {@code view}.
     *
     * @param view the light view transformation (must be affine)
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 orthoCrop(Double4x4R view, DepthRange depthRange) { return orthoCrop(view, Handedness.RIGHT_HANDED, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Build an orthographic projection that tightly fits the view-projection frustum represented by
     * {@code this} matrix into the given affine {@code view} transformation.
     * <p>
     * {@code this} must be the inverse of the combined camera view-projection matrix; {@code view}
     * is typically the light's view matrix (e.g. from {@code lookAt}). Typical use is fitting a
     * shadow-map projection around the camera frustum.
     * <p>
     * {@code depthRange} does double duty here: it selects the produced orthographic projection's
     * own clip-space depth range AND the source frustum's NDC z extent that "the whole frustum"
     * means - {@code [-1, 1]} for {@code NEGATIVE_ONE_TO_ONE}, {@code [0, 1]} for
     * {@code ZERO_TO_ONE}. That assumes the camera projection and the produced orthographic
     * projection share a convention. When they do not, pass the source's own NDC bounds explicitly
     * to the {@code minZ}/{@code maxZ} overload, whose bounds are independent of
     * {@code depthRange}.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be the inverse of a view-projection matrix with finite clip
     * planes; {@code view} must be affine; the frustum slice must have a non-zero extent along each
     * axis of {@code view}.
     *
     * @param view the light view transformation (must be affine)
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 orthoCrop(Double4x4R view, Handedness handedness) { return orthoCrop(view, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Build an orthographic projection that tightly fits the view-projection frustum represented by
     * {@code this} matrix into the given affine {@code view} transformation.
     * <p>
     * {@code this} must be the inverse of the combined camera view-projection matrix; {@code view}
     * is typically the light's view matrix (e.g. from {@code lookAt}). Typical use is fitting a
     * shadow-map projection around the camera frustum.
     * <p>
     * {@code depthRange} does double duty here: it selects the produced orthographic projection's
     * own clip-space depth range AND the source frustum's NDC z extent that "the whole frustum"
     * means - {@code [-1, 1]} for {@code NEGATIVE_ONE_TO_ONE}, {@code [0, 1]} for
     * {@code ZERO_TO_ONE}. That assumes the camera projection and the produced orthographic
     * projection share a convention. When they do not, pass the source's own NDC bounds explicitly
     * to the {@code minZ}/{@code maxZ} overload, whose bounds are independent of
     * {@code depthRange}.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be the inverse of a view-projection matrix with finite clip
     * planes; {@code view} must be affine; the frustum slice must have a non-zero extent along each
     * axis of {@code view}.
     *
     * @param view the light view transformation (must be affine)
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 orthoCrop(Double4x4R view) { return orthoCrop(view, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Build an orthographic projection that tightly fits a depth sub-range of the view-projection
     * frustum represented by {@code this} matrix into the given affine {@code view} transformation.
     * <p>
     * {@code this} must be the inverse of the combined camera view-projection matrix; {@code view}
     * is typically the light's view matrix. {@code minZ} and {@code maxZ} delimit the frustum slice
     * in the source projection's normalized device coordinates (order-independent), e.g. the NDC z
     * of a shadow cascade's near and far split planes. They are independent of {@code depthRange},
     * which selects the produced orthographic projection's own clip-space depth range.
     * <p>
     * Valid input: this matrix must be the inverse of a view-projection matrix with finite clip
     * planes; {@code view} must be affine; {@code minZ} and {@code maxZ} must differ; the frustum
     * slice must have a non-zero extent along each axis of {@code view}.
     *
     * @param view the light view transformation (must be affine)
     * @param minZ the near depth bound of the slice, in source-projection NDC z
     * @param maxZ the far depth bound of the slice, in source-projection NDC z
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 orthoCrop(Double4x4R view, double minZ, double maxZ, Handedness handedness, DepthRange depthRange) { return orthoCrop(view, minZ, maxZ, handedness, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Build an orthographic projection that tightly fits a depth sub-range of the view-projection
     * frustum represented by {@code this} matrix into the given affine {@code view} transformation.
     * <p>
     * {@code this} must be the inverse of the combined camera view-projection matrix; {@code view}
     * is typically the light's view matrix. {@code minZ} and {@code maxZ} delimit the frustum slice
     * in the source projection's normalized device coordinates (order-independent), e.g. the NDC z
     * of a shadow cascade's near and far split planes. They are independent of {@code depthRange},
     * which selects the produced orthographic projection's own clip-space depth range.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: this matrix must be the inverse of a view-projection matrix with finite clip
     * planes; {@code view} must be affine; {@code minZ} and {@code maxZ} must differ; the frustum
     * slice must have a non-zero extent along each axis of {@code view}.
     *
     * @param view the light view transformation (must be affine)
     * @param minZ the near depth bound of the slice, in source-projection NDC z
     * @param maxZ the far depth bound of the slice, in source-projection NDC z
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 orthoCrop(Double4x4R view, double minZ, double maxZ, DepthRange depthRange) { return orthoCrop(view, minZ, maxZ, Handedness.RIGHT_HANDED, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Build an orthographic projection that tightly fits a depth sub-range of the view-projection
     * frustum represented by {@code this} matrix into the given affine {@code view} transformation.
     * <p>
     * {@code this} must be the inverse of the combined camera view-projection matrix; {@code view}
     * is typically the light's view matrix. {@code minZ} and {@code maxZ} delimit the frustum slice
     * in the source projection's normalized device coordinates (order-independent), e.g. the NDC z
     * of a shadow cascade's near and far split planes. They are independent of {@code depthRange},
     * which selects the produced orthographic projection's own clip-space depth range.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be the inverse of a view-projection matrix with finite clip
     * planes; {@code view} must be affine; {@code minZ} and {@code maxZ} must differ; the frustum
     * slice must have a non-zero extent along each axis of {@code view}.
     *
     * @param view the light view transformation (must be affine)
     * @param minZ the near depth bound of the slice, in source-projection NDC z
     * @param maxZ the far depth bound of the slice, in source-projection NDC z
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 orthoCrop(Double4x4R view, double minZ, double maxZ, Handedness handedness) { return orthoCrop(view, minZ, maxZ, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Build an orthographic projection that tightly fits a depth sub-range of the view-projection
     * frustum represented by {@code this} matrix into the given affine {@code view} transformation.
     * <p>
     * {@code this} must be the inverse of the combined camera view-projection matrix; {@code view}
     * is typically the light's view matrix. {@code minZ} and {@code maxZ} delimit the frustum slice
     * in the source projection's normalized device coordinates (order-independent), e.g. the NDC z
     * of a shadow cascade's near and far split planes. They are independent of {@code depthRange},
     * which selects the produced orthographic projection's own clip-space depth range.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be the inverse of a view-projection matrix with finite clip
     * planes; {@code view} must be affine; {@code minZ} and {@code maxZ} must differ; the frustum
     * slice must have a non-zero extent along each axis of {@code view}.
     *
     * @param view the light view transformation (must be affine)
     * @param minZ the near depth bound of the slice, in source-projection NDC z
     * @param maxZ the far depth bound of the slice, in source-projection NDC z
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 orthoCrop(Double4x4R view, double minZ, double maxZ) { return orthoCrop(view, minZ, maxZ, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply a symmetric perspective projection frustum transformation to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code P} the perspective projection matrix, then the
     * new matrix will be {@code M * P}. So when transforming a vector {@code v} with the new matrix
     * by using {@code M * P * v}, the perspective projection will be applied first.
     * <p>
     * Valid input: {@code fovy} must lie in {@code (0, PI)}; {@code aspect} must be positive;
     * {@code near} must be positive; {@code far} must be positive; {@code near} and {@code far}
     * must differ; {@code near} may be {@code +Infinity}; {@code far} may be {@code +Infinity}.
     *
     * @param fovy the vertical field of view in radians
     * @param aspect the aspect ratio of the view (i.e. width / height)
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 perspective(double fovy, double aspect, double near, double far, Handedness handedness, DepthRange depthRange) { return perspective(fovy, aspect, near, far, handedness, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply a symmetric perspective projection frustum transformation to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code P} the perspective projection matrix, then the
     * new matrix will be {@code M * P}. So when transforming a vector {@code v} with the new matrix
     * by using {@code M * P * v}, the perspective projection will be applied first.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code fovy} must lie in {@code (0, PI)}; {@code aspect} must be positive;
     * {@code near} must be positive; {@code far} must be positive; {@code near} and {@code far}
     * must differ; {@code near} may be {@code +Infinity}; {@code far} may be {@code +Infinity}.
     *
     * @param fovy the vertical field of view in radians
     * @param aspect the aspect ratio of the view (i.e. width / height)
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 perspective(double fovy, double aspect, double near, double far, DepthRange depthRange) { return perspective(fovy, aspect, near, far, Handedness.RIGHT_HANDED, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply a symmetric perspective projection frustum transformation to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code P} the perspective projection matrix, then the
     * new matrix will be {@code M * P}. So when transforming a vector {@code v} with the new matrix
     * by using {@code M * P * v}, the perspective projection will be applied first.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code fovy} must lie in {@code (0, PI)}; {@code aspect} must be positive;
     * {@code near} must be positive; {@code far} must be positive; {@code near} and {@code far}
     * must differ; {@code near} may be {@code +Infinity}; {@code far} may be {@code +Infinity}.
     *
     * @param fovy the vertical field of view in radians
     * @param aspect the aspect ratio of the view (i.e. width / height)
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 perspective(double fovy, double aspect, double near, double far, Handedness handedness) { return perspective(fovy, aspect, near, far, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply a symmetric perspective projection frustum transformation to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code P} the perspective projection matrix, then the
     * new matrix will be {@code M * P}. So when transforming a vector {@code v} with the new matrix
     * by using {@code M * P * v}, the perspective projection will be applied first.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code fovy} must lie in {@code (0, PI)}; {@code aspect} must be positive;
     * {@code near} must be positive; {@code far} must be positive; {@code near} and {@code far}
     * must differ; {@code near} may be {@code +Infinity}; {@code far} may be {@code +Infinity}.
     *
     * @param fovy the vertical field of view in radians
     * @param aspect the aspect ratio of the view (i.e. width / height)
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 perspective(double fovy, double aspect, double near, double far) { return perspective(fovy, aspect, near, far, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply a perspective projection frustum transformation for the given vertical field-of-view
     * range to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code P} the perspective projection matrix, then the
     * new matrix will be {@code M * P}. So when transforming a vector {@code v} with the new matrix
     * by using {@code M * P * v}, the perspective projection will be applied first.
     * <p>
     * Valid input: {@code angleMin} must lie in {@code (-PI/2, PI/2)}; {@code angleMax} must lie in
     * {@code (-PI/2, PI/2)}; {@code angleMin} must be less than {@code angleMax}; {@code aspect}
     * must be positive; {@code near} must be positive; {@code far} must be positive; {@code near}
     * and {@code far} must differ; {@code near} may be {@code +Infinity}; {@code far} may be
     * {@code +Infinity}.
     *
     * @param angleMin the minimum vertical field-of-view angle in radians
     * @param angleMax the maximum vertical field-of-view angle in radians
     * @param aspect the aspect ratio of the view (i.e. width / height)
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 perspectiveFovRange(double angleMin, double angleMax, double aspect, double near, double far, Handedness handedness, DepthRange depthRange) { return perspectiveFovRange(angleMin, angleMax, aspect, near, far, handedness, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply a perspective projection frustum transformation for the given vertical field-of-view
     * range to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code P} the perspective projection matrix, then the
     * new matrix will be {@code M * P}. So when transforming a vector {@code v} with the new matrix
     * by using {@code M * P * v}, the perspective projection will be applied first.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code angleMin} must lie in {@code (-PI/2, PI/2)}; {@code angleMax} must lie in
     * {@code (-PI/2, PI/2)}; {@code angleMin} must be less than {@code angleMax}; {@code aspect}
     * must be positive; {@code near} must be positive; {@code far} must be positive; {@code near}
     * and {@code far} must differ; {@code near} may be {@code +Infinity}; {@code far} may be
     * {@code +Infinity}.
     *
     * @param angleMin the minimum vertical field-of-view angle in radians
     * @param angleMax the maximum vertical field-of-view angle in radians
     * @param aspect the aspect ratio of the view (i.e. width / height)
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 perspectiveFovRange(double angleMin, double angleMax, double aspect, double near, double far, DepthRange depthRange) { return perspectiveFovRange(angleMin, angleMax, aspect, near, far, Handedness.RIGHT_HANDED, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply a perspective projection frustum transformation for the given vertical field-of-view
     * range to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code P} the perspective projection matrix, then the
     * new matrix will be {@code M * P}. So when transforming a vector {@code v} with the new matrix
     * by using {@code M * P * v}, the perspective projection will be applied first.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code angleMin} must lie in {@code (-PI/2, PI/2)}; {@code angleMax} must lie in
     * {@code (-PI/2, PI/2)}; {@code angleMin} must be less than {@code angleMax}; {@code aspect}
     * must be positive; {@code near} must be positive; {@code far} must be positive; {@code near}
     * and {@code far} must differ; {@code near} may be {@code +Infinity}; {@code far} may be
     * {@code +Infinity}.
     *
     * @param angleMin the minimum vertical field-of-view angle in radians
     * @param angleMax the maximum vertical field-of-view angle in radians
     * @param aspect the aspect ratio of the view (i.e. width / height)
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 perspectiveFovRange(double angleMin, double angleMax, double aspect, double near, double far, Handedness handedness) { return perspectiveFovRange(angleMin, angleMax, aspect, near, far, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply a perspective projection frustum transformation for the given vertical field-of-view
     * range to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code P} the perspective projection matrix, then the
     * new matrix will be {@code M * P}. So when transforming a vector {@code v} with the new matrix
     * by using {@code M * P * v}, the perspective projection will be applied first.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code angleMin} must lie in {@code (-PI/2, PI/2)}; {@code angleMax} must lie in
     * {@code (-PI/2, PI/2)}; {@code angleMin} must be less than {@code angleMax}; {@code aspect}
     * must be positive; {@code near} must be positive; {@code far} must be positive; {@code near}
     * and {@code far} must differ; {@code near} may be {@code +Infinity}; {@code far} may be
     * {@code +Infinity}.
     *
     * @param angleMin the minimum vertical field-of-view angle in radians
     * @param angleMax the maximum vertical field-of-view angle in radians
     * @param aspect the aspect ratio of the view (i.e. width / height)
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 perspectiveFovRange(double angleMin, double angleMax, double aspect, double near, double far) { return perspectiveFovRange(angleMin, angleMax, aspect, near, far, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Change the near and far clip plane distances of an existing perspective frustum
     * transformation and leave everything else unchanged.
     * <p>
     * Only meaningful when this matrix is a perspective projection obtained via {@code perspective}
     * or {@code frustum}: all elements except the depth row are copied through, and that row is
     * recomputed for the new {@code near}/{@code far}. Pass the same {@code handedness} and
     * {@code depthRange} the matrix was built with.
     * <p>
     * Valid input: this matrix must be a perspective projection; {@code near} must be positive;
     * {@code far} must be positive; {@code near} and {@code far} must differ; {@code near} may be
     * {@code +Infinity}; {@code far} may be {@code +Infinity}.
     *
     * @param near the new distance to the near clip plane (pass positive infinity to re-slice to an
     *        infinite near plane - equivalent to an infinite far plane with the depth range
     *        reversed)
     * @param far the new distance to the far clip plane (pass positive infinity to re-slice to an
     *        infinite far plane)
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 perspectiveFrustumSlice(double near, double far, Handedness handedness, DepthRange depthRange) { return perspectiveFrustumSlice(near, far, handedness, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Change the near and far clip plane distances of an existing perspective frustum
     * transformation and leave everything else unchanged.
     * <p>
     * Only meaningful when this matrix is a perspective projection obtained via {@code perspective}
     * or {@code frustum}: all elements except the depth row are copied through, and that row is
     * recomputed for the new {@code near}/{@code far}. Pass the same {@code handedness} and
     * {@code depthRange} the matrix was built with.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: this matrix must be a perspective projection; {@code near} must be positive;
     * {@code far} must be positive; {@code near} and {@code far} must differ; {@code near} may be
     * {@code +Infinity}; {@code far} may be {@code +Infinity}.
     *
     * @param near the new distance to the near clip plane (pass positive infinity to re-slice to an
     *        infinite near plane - equivalent to an infinite far plane with the depth range
     *        reversed)
     * @param far the new distance to the far clip plane (pass positive infinity to re-slice to an
     *        infinite far plane)
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 perspectiveFrustumSlice(double near, double far, DepthRange depthRange) { return perspectiveFrustumSlice(near, far, Handedness.RIGHT_HANDED, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Change the near and far clip plane distances of an existing perspective frustum
     * transformation and leave everything else unchanged.
     * <p>
     * Only meaningful when this matrix is a perspective projection obtained via {@code perspective}
     * or {@code frustum}: all elements except the depth row are copied through, and that row is
     * recomputed for the new {@code near}/{@code far}. Pass the same {@code handedness} and
     * {@code depthRange} the matrix was built with.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a perspective projection; {@code near} must be positive;
     * {@code far} must be positive; {@code near} and {@code far} must differ; {@code near} may be
     * {@code +Infinity}; {@code far} may be {@code +Infinity}.
     *
     * @param near the new distance to the near clip plane (pass positive infinity to re-slice to an
     *        infinite near plane - equivalent to an infinite far plane with the depth range
     *        reversed)
     * @param far the new distance to the far clip plane (pass positive infinity to re-slice to an
     *        infinite far plane)
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 perspectiveFrustumSlice(double near, double far, Handedness handedness) { return perspectiveFrustumSlice(near, far, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Change the near and far clip plane distances of an existing perspective frustum
     * transformation and leave everything else unchanged.
     * <p>
     * Only meaningful when this matrix is a perspective projection obtained via {@code perspective}
     * or {@code frustum}: all elements except the depth row are copied through, and that row is
     * recomputed for the new {@code near}/{@code far}. Pass the same {@code handedness} and
     * {@code depthRange} the matrix was built with.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a perspective projection; {@code near} must be positive;
     * {@code far} must be positive; {@code near} and {@code far} must differ; {@code near} may be
     * {@code +Infinity}; {@code far} may be {@code +Infinity}.
     *
     * @param near the new distance to the near clip plane (pass positive infinity to re-slice to an
     *        infinite near plane - equivalent to an infinite far plane with the depth range
     *        reversed)
     * @param far the new distance to the far clip plane (pass positive infinity to re-slice to an
     *        infinite far plane)
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 perspectiveFrustumSlice(double near, double far) { return perspectiveFrustumSlice(near, far, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply an asymmetric perspective projection frustum transformation with the frustum sides
     * given as view-axis angles to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code P} the perspective projection matrix, then the
     * new matrix will be {@code M * P}. So when transforming a vector {@code v} with the new matrix
     * by using {@code M * P * v}, the perspective projection will be applied first.
     * <p>
     * Valid input: {@code angleLeft} must lie in {@code (-PI/2, PI/2)}; {@code angleRight} must lie
     * in {@code (-PI/2, PI/2)}; {@code angleDown} must lie in {@code (-PI/2, PI/2)};
     * {@code angleUp} must lie in {@code (-PI/2, PI/2)}; {@code angleLeft} must be less than
     * {@code angleRight}; {@code angleDown} must be less than {@code angleUp}; {@code near} must be
     * positive; {@code far} must be positive; {@code near} and {@code far} must differ;
     * {@code near} may be {@code +Infinity}; {@code far} may be {@code +Infinity}.
     *
     * @param angleLeft the angle in radians from the view axis to the left frustum edge (negative
     *        for a frustum extending to the left)
     * @param angleRight the angle in radians from the view axis to the right frustum edge
     * @param angleDown the angle in radians from the view axis to the bottom frustum edge (negative
     *        for a frustum extending downwards)
     * @param angleUp the angle in radians from the view axis to the top frustum edge
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 perspectiveOffCenterFov(double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far, Handedness handedness, DepthRange depthRange) { return perspectiveOffCenterFov(angleLeft, angleRight, angleDown, angleUp, near, far, handedness, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply an asymmetric perspective projection frustum transformation with the frustum sides
     * given as view-axis angles to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code P} the perspective projection matrix, then the
     * new matrix will be {@code M * P}. So when transforming a vector {@code v} with the new matrix
     * by using {@code M * P * v}, the perspective projection will be applied first.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: {@code angleLeft} must lie in {@code (-PI/2, PI/2)}; {@code angleRight} must lie
     * in {@code (-PI/2, PI/2)}; {@code angleDown} must lie in {@code (-PI/2, PI/2)};
     * {@code angleUp} must lie in {@code (-PI/2, PI/2)}; {@code angleLeft} must be less than
     * {@code angleRight}; {@code angleDown} must be less than {@code angleUp}; {@code near} must be
     * positive; {@code far} must be positive; {@code near} and {@code far} must differ;
     * {@code near} may be {@code +Infinity}; {@code far} may be {@code +Infinity}.
     *
     * @param angleLeft the angle in radians from the view axis to the left frustum edge (negative
     *        for a frustum extending to the left)
     * @param angleRight the angle in radians from the view axis to the right frustum edge
     * @param angleDown the angle in radians from the view axis to the bottom frustum edge (negative
     *        for a frustum extending downwards)
     * @param angleUp the angle in radians from the view axis to the top frustum edge
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @param depthRange the clip-space depth range the projection maps onto
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 perspectiveOffCenterFov(double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far, DepthRange depthRange) { return perspectiveOffCenterFov(angleLeft, angleRight, angleDown, angleUp, near, far, Handedness.RIGHT_HANDED, depthRange, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply an asymmetric perspective projection frustum transformation with the frustum sides
     * given as view-axis angles to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code P} the perspective projection matrix, then the
     * new matrix will be {@code M * P}. So when transforming a vector {@code v} with the new matrix
     * by using {@code M * P * v}, the perspective projection will be applied first.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code angleLeft} must lie in {@code (-PI/2, PI/2)}; {@code angleRight} must lie
     * in {@code (-PI/2, PI/2)}; {@code angleDown} must lie in {@code (-PI/2, PI/2)};
     * {@code angleUp} must lie in {@code (-PI/2, PI/2)}; {@code angleLeft} must be less than
     * {@code angleRight}; {@code angleDown} must be less than {@code angleUp}; {@code near} must be
     * positive; {@code far} must be positive; {@code near} and {@code far} must differ;
     * {@code near} may be {@code +Infinity}; {@code far} may be {@code +Infinity}.
     *
     * @param angleLeft the angle in radians from the view axis to the left frustum edge (negative
     *        for a frustum extending to the left)
     * @param angleRight the angle in radians from the view axis to the right frustum edge
     * @param angleDown the angle in radians from the view axis to the bottom frustum edge (negative
     *        for a frustum extending downwards)
     * @param angleUp the angle in radians from the view axis to the top frustum edge
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 perspectiveOffCenterFov(double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far, Handedness handedness) { return perspectiveOffCenterFov(angleLeft, angleRight, angleDown, angleUp, near, far, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply an asymmetric perspective projection frustum transformation with the frustum sides
     * given as view-axis angles to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code P} the perspective projection matrix, then the
     * new matrix will be {@code M * P}. So when transforming a vector {@code v} with the new matrix
     * by using {@code M * P * v}, the perspective projection will be applied first.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code angleLeft} must lie in {@code (-PI/2, PI/2)}; {@code angleRight} must lie
     * in {@code (-PI/2, PI/2)}; {@code angleDown} must lie in {@code (-PI/2, PI/2)};
     * {@code angleUp} must lie in {@code (-PI/2, PI/2)}; {@code angleLeft} must be less than
     * {@code angleRight}; {@code angleDown} must be less than {@code angleUp}; {@code near} must be
     * positive; {@code far} must be positive; {@code near} and {@code far} must differ;
     * {@code near} may be {@code +Infinity}; {@code far} may be {@code +Infinity}.
     *
     * @param angleLeft the angle in radians from the view axis to the left frustum edge (negative
     *        for a frustum extending to the left)
     * @param angleRight the angle in radians from the view axis to the right frustum edge
     * @param angleDown the angle in radians from the view axis to the bottom frustum edge (negative
     *        for a frustum extending downwards)
     * @param angleUp the angle in radians from the view axis to the top frustum edge
     * @param near the distance to the near clip plane (positive infinity gives an infinite near
     *        plane - equivalent to an infinite far plane with the depth range reversed)
     * @param far the distance to the far clip plane (positive infinity gives an infinite far plane)
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 perspectiveOffCenterFov(double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far) { return perspectiveOffCenterFov(angleLeft, angleRight, angleDown, angleUp, near, far, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply a picking transformation restricting the view to the given picking region to this
     * matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code P} the picking matrix, then the new matrix
     * will be {@code M * P}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * P * v}, the picking will be applied first.
     * <p>
     * Valid input: {@code deltaX} must be non-zero; {@code deltaY} must be non-zero; {@code vpW}
     * must be non-zero; {@code vpH} must be non-zero.
     *
     * @param centerX the x coordinate of the picking-region center in window coordinates
     * @param centerY the y coordinate of the picking-region center in window coordinates
     * @param deltaX the width of the picking region in window coordinates
     * @param deltaY the height of the picking region in window coordinates
     * @param vpX the x coordinate of the viewport origin
     * @param vpY the y coordinate of the viewport origin
     * @param vpW the width of the viewport
     * @param vpH the height of the viewport
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 pickMatrix(double centerX, double centerY, double deltaX, double deltaY, double vpX, double vpY, double vpW, double vpH) { return pickMatrix(centerX, centerY, deltaX, deltaY, vpX, vpY, vpW, vpH, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preRotateAround(DoubleQuatR rot, Double3R pivot) { return preRotateAround(rot, pivot, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preRotateAround(double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) { return preRotateAround(rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preRotateAxis(double angle, Double3R axis) { return preRotateAxis(angle, axis, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preRotateAxis(double angle, double x, double y, double z) { return preRotateAxis(angle, x, y, z, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preRotateQuat(DoubleQuatR q) { return preRotateQuat(q, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preRotateQuat(double x, double y, double z, double w) { return preRotateQuat(x, y, z, w, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preRotateX(double angle) { return preRotateX(angle, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preRotateY(double angle) { return preRotateY(angle, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preRotateZ(double angle) { return preRotateZ(angle, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preScale(Double3R v) { return preScale(v, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preScale(double x, double y, double z) { return preScale(x, y, z, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preScale(double s) { return preScale(s, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preScaleAround(double s, Double3R pivot) { return preScaleAround(s, pivot, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preScaleAround(double s, double x, double y, double z) { return preScaleAround(s, x, y, z, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preScaleAround(Double3R s, Double3R pivot) { return preScaleAround(s, pivot, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preScaleAround(double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) { return preScaleAround(sX, sY, sZ, pivotX, pivotY, pivotZ, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preTranslate(Double3R v) { return preTranslate(v, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 preTranslate(double x, double y, double z) { return preTranslate(x, y, z, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 reflect(Double3R normal) { return reflect(normal, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 reflect(double x, double y, double z) { return reflect(x, y, z, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateAround(DoubleQuatR rot, Double3R pivot) { return rotateAround(rot, pivot, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateAround(double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) { return rotateAround(rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateAxis(double angle, Double3R axis) { return rotateAxis(angle, axis, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateAxis(double angle, double x, double y, double z) { return rotateAxis(angle, x, y, z, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateQuat(DoubleQuatR q) { return rotateQuat(q, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateQuat(double x, double y, double z, double w) { return rotateQuat(x, y, z, w, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateX(double angle) { return rotateX(angle, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateX180() { return rotateX180(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateX270() { return rotateX270(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateX90() { return rotateX90(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateXYZ(double angleX, double angleY, double angleZ) { return rotateXYZ(angleX, angleY, angleZ, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateXZY(double angleX, double angleZ, double angleY) { return rotateXZY(angleX, angleZ, angleY, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateXn180() { return rotateXn180(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateXn270() { return rotateXn270(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateXn90() { return rotateXn90(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateY(double angle) { return rotateY(angle, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateY180() { return rotateY180(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateY270() { return rotateY270(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateY90() { return rotateY90(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateYXZ(double angleY, double angleX, double angleZ) { return rotateYXZ(angleY, angleX, angleZ, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateYZX(double angleY, double angleZ, double angleX) { return rotateYZX(angleY, angleZ, angleX, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateYn180() { return rotateYn180(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateYn270() { return rotateYn270(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateYn90() { return rotateYn90(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateZ(double angle) { return rotateZ(angle, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateZ180() { return rotateZ180(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateZ270() { return rotateZ270(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateZ90() { return rotateZ90(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateZXY(double angleZ, double angleX, double angleY) { return rotateZXY(angleZ, angleX, angleY, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateZYX(double angleZ, double angleY, double angleX) { return rotateZYX(angleZ, angleY, angleX, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateZn180() { return rotateZn180(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateZn270() { return rotateZn270(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 rotateZn90() { return rotateZn90(Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 scale(Double3R v) { return scale(v, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 scale(double x, double y, double z) { return scale(x, y, z, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 scale(double s) { return scale(s, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 scaleAround(double s, Double3R pivot) { return scaleAround(s, pivot, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 scaleAround(double s, double x, double y, double z) { return scaleAround(s, x, y, z, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 scaleAround(Double3R s, Double3R pivot) { return scaleAround(s, pivot, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 scaleAround(double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) { return scaleAround(sX, sY, sZ, pivotX, pivotY, pivotZ, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply a projection shadow transformation that projects geometry onto the given plane, cast
     * from the given light source (a direction when {@code light.w} is {@code 0}, a position when
     * it is {@code 1}) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the shadow matrix, then the new matrix will
     * be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the shadow will be applied first.
     * <p>
     * Valid input: the normal of {@code plane} must be non-zero; {@code light} must not lie in
     * {@code plane}.
     *
     * @param light the light direction or position ({@code w = 0} for a directional light,
     *        {@code w = 1} for a point light)
     * @param plane the plane equation {@code (a, b, c, d)} with {@code ax + by + cz + d = 0}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 shadow(Double4R light, Double4R plane) { return shadow(light, plane, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply a projection shadow transformation that projects geometry onto the given plane, cast
     * from the given light source (a direction when {@code lightW} is {@code 0}, a position when it
     * is {@code 1}) to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the shadow matrix, then the new matrix will
     * be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the shadow will be applied first.
     * <p>
     * Valid input: the normal of {@code (planeX, planeY, planeZ, planeW)} must be non-zero;
     * {@code (lightX, lightY, lightZ, lightW)} must not lie in
     * {@code (planeX, planeY, planeZ, planeW)}.
     *
     * @param lightX the {@code x} component of the vector {@code (lightX, lightY, lightZ, lightW)}
     * @param lightY the {@code y} component of the vector {@code (lightX, lightY, lightZ, lightW)}
     * @param lightZ the {@code z} component of the vector {@code (lightX, lightY, lightZ, lightW)}
     * @param lightW the {@code w} component of the vector {@code (lightX, lightY, lightZ, lightW)}
     * @param planeX the {@code x} component of the vector {@code (planeX, planeY, planeZ, planeW)}
     * @param planeY the {@code y} component of the vector {@code (planeX, planeY, planeZ, planeW)}
     * @param planeZ the {@code z} component of the vector {@code (planeX, planeY, planeZ, planeW)}
     * @param planeW the {@code w} component of the vector {@code (planeX, planeY, planeZ, planeW)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 shadow(double lightX, double lightY, double lightZ, double lightW, double planeX, double planeY, double planeZ, double planeW) { return shadow(lightX, lightY, lightZ, lightW, planeX, planeY, planeZ, planeW, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply a shearing transformation to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the shearing matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the shearing will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param xy the shear of x proportional to y
     * @param xz the shear of x proportional to z
     * @param yx the shear of y proportional to x
     * @param yz the shear of y proportional to z
     * @param zx the shear of z proportional to x
     * @param zy the shear of z proportional to y
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 shear(double xy, double xz, double yx, double yz, double zx, double zy) { return shear(xy, xz, yx, yz, zx, zy, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Apply a transformation that restricts the view to one tile of a regular grid to this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the tile matrix, then the new matrix will
     * be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the tile will be applied first.
     * <p>
     * Valid input: {@code x} must not be negative; {@code x} must be less than {@code w}; {@code y}
     * must not be negative; {@code y} must be less than {@code h}; {@code w} must be positive;
     * {@code h} must be positive.
     *
     * @param x the column index of the tile, in {@code [0, w)}
     * @param y the row index of the tile, in {@code [0, h)}
     * @param w the number of tiles along the x axis
     * @param h the number of tiles along the y axis
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 tile(double x, double y, double w, double h) { return tile(x, y, w, h, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 translate(Double3R v) { return translate(v, Joml.RETURN_NEW ? Joml.double4x4() : this); }

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
    @Mutated default Double4x4 translate(double x, double y, double z) { return translate(x, y, z, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Pre-multiply a trapezoid-crop transformation mapping the given trapezoid onto the
     * {@code [-1, +1]} clip square onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code C} the crop matrix, then the new matrix will
     * be {@code C * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code C * M * v}, the crop will be applied last.
     * <p>
     * Valid input: {@code p0}, {@code p1}, {@code p2} and {@code p3} must be the corners of a
     * trapezoid, in that order, with the edge from {@code p0} to {@code p1} the shorter of its
     * parallel edges.
     *
     * @param p0 the left corner of the shorter parallel edge of the trapezoid
     * @param p1 the right corner of the shorter parallel edge of the trapezoid
     * @param p2 the right corner of the longer parallel edge of the trapezoid
     * @param p3 the left corner of the longer parallel edge of the trapezoid
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 trapezoidCrop(Double2R p0, Double2R p1, Double2R p2, Double2R p3) { return trapezoidCrop(p0, p1, p2, p3, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Pre-multiply a trapezoid-crop transformation mapping the given trapezoid onto the
     * {@code [-1, +1]} clip square onto this matrix.
     * <p>
     * If {@code M} is {@code this} matrix and {@code C} the crop matrix, then the new matrix will
     * be {@code C * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code C * M * v}, the crop will be applied last.
     * <p>
     * Valid input: {@code (p0X, p0Y)}, {@code (p1X, p1Y)}, {@code (p2X, p2Y)} and
     * {@code (p3X, p3Y)} must be the corners of a trapezoid, in that order, with the edge from
     * {@code (p0X, p0Y)} to {@code (p1X, p1Y)} the shorter of its parallel edges.
     *
     * @param p0X the {@code x} component of the vector {@code (p0X, p0Y)}
     * @param p0Y the {@code y} component of the vector {@code (p0X, p0Y)}
     * @param p1X the {@code x} component of the vector {@code (p1X, p1Y)}
     * @param p1Y the {@code y} component of the vector {@code (p1X, p1Y)}
     * @param p2X the {@code x} component of the vector {@code (p2X, p2Y)}
     * @param p2Y the {@code y} component of the vector {@code (p2X, p2Y)}
     * @param p3X the {@code x} component of the vector {@code (p3X, p3Y)}
     * @param p3Y the {@code y} component of the vector {@code (p3X, p3Y)}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated default Double4x4 trapezoidCrop(double p0X, double p0Y, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y) { return trapezoidCrop(p0X, p0Y, p1X, p1Y, p2X, p2Y, p3X, p3Y, Joml.RETURN_NEW ? Joml.double4x4() : this); }

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Double4x4 loadCM(double[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Double4x4 loadCM(double[] src, int offset);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified).
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Double4x4 loadCM(DoubleBuffer src) { return loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified).
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Double4x4 loadCMAbsolute(DoubleBuffer src) { return loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified).
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
     * @param src the source buffer
     * @return this
     */
    @Mutated Double4x4 loadCMAbsolute(int index, DoubleBuffer src);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
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
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadCMRelative(DoubleBuffer src) {
        if (src.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src);
        src.position(pos + 16);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position (the position is not modified).
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Double4x4 loadCM(ByteBuffer src) { return loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position (the position is not modified).
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Double4x4 loadCMAbsolute(ByteBuffer src) { return loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified).
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated Double4x4 loadCMAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
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
     * @param src the source byte buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadCMRelative(ByteBuffer src) {
        if (src.remaining() < 128) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src);
        src.position(pos + 128);
        return this;
    }

    /**
     * Load the elements from the given memory segment in column-major order.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @return this
     */
    @Mutated default Double4x4 loadCM(MemorySegment src) { return loadCM(0L, src); }

    /**
     * Load the elements from the given memory segment in column-major order, starting at the given
     * offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return this
     */
    @Mutated Double4x4 loadCM(long offset, MemorySegment src);

    /**
     * Load the elements from the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    @Mutated Double4x4 loadCMUnsafe(long address);

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Double4x4 loadCM(float[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Double4x4 loadCM(float[] src, int offset);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified).
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Double4x4 loadCM(FloatBuffer src) { return loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified).
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Double4x4 loadCMAbsolute(FloatBuffer src) { return loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified).
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
     * @param src the source buffer
     * @return this
     */
    @Mutated Double4x4 loadCMAbsolute(int index, FloatBuffer src);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
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
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadCMRelative(FloatBuffer src) {
        if (src.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src);
        src.position(pos + 16);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at its current position (the position is not modified).
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Double4x4 loadCMFloat(ByteBuffer src) { return loadCMFloatAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at its current position (the position is not modified).
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Double4x4 loadCMFloatAbsolute(ByteBuffer src) { return loadCMFloatAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at the given absolute index (the position is not used or
     * modified).
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated Double4x4 loadCMFloatAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at its current position and advancing the position accordingly.
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
     * @param src the source byte buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadCMFloatRelative(ByteBuffer src) {
        if (src.remaining() < 64) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMFloatAbsolute(pos, src);
        src.position(pos + 64);
        return this;
    }

    /**
     * Load the elements from the given memory segment in column-major order, converting each
     * element from {@code float}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @return this
     */
    @Mutated default Double4x4 loadCMFloat(MemorySegment src) { return loadCMFloat(0L, src); }

    /**
     * Load the elements from the given memory segment in column-major order, converting each
     * element from {@code float}, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return this
     */
    @Mutated Double4x4 loadCMFloat(long offset, MemorySegment src);

    /**
     * Load the elements from the given raw memory address in column-major order, converting each
     * element from {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    @Mutated Double4x4 loadCMFloatUnsafe(long address);

    /**
     * Load the elements from the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Double4x4 loadRM(double[] src) { return loadRM(src, 0); }

    /**
     * Load the elements from the given array in row-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Double4x4 loadRM(double[] src, int offset);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified).
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Double4x4 loadRM(DoubleBuffer src) { return loadRMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified).
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Double4x4 loadRMAbsolute(DoubleBuffer src) { return loadRMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in row-major order, starting at the given absolute
     * index (the position is not used or modified).
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
     * @param src the source buffer
     * @return this
     */
    @Mutated Double4x4 loadRMAbsolute(int index, DoubleBuffer src);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * and advancing the position accordingly.
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
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadRMRelative(DoubleBuffer src) {
        if (src.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src);
        src.position(pos + 16);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at its current
     * position (the position is not modified).
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Double4x4 loadRM(ByteBuffer src) { return loadRMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at its current
     * position (the position is not modified).
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Double4x4 loadRMAbsolute(ByteBuffer src) { return loadRMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at the given
     * absolute index (the position is not used or modified).
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated Double4x4 loadRMAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer in row-major order, starting at its current
     * position and advancing the position accordingly.
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
     * @param src the source byte buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadRMRelative(ByteBuffer src) {
        if (src.remaining() < 128) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src);
        src.position(pos + 128);
        return this;
    }

    /**
     * Load the elements from the given memory segment in row-major order.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @return this
     */
    @Mutated default Double4x4 loadRM(MemorySegment src) { return loadRM(0L, src); }

    /**
     * Load the elements from the given memory segment in row-major order, starting at the given
     * offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return this
     */
    @Mutated Double4x4 loadRM(long offset, MemorySegment src);

    /**
     * Load the elements from the given raw memory address in row-major order. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    @Mutated Double4x4 loadRMUnsafe(long address);

    /**
     * Load the elements from the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Double4x4 loadRM(float[] src) { return loadRM(src, 0); }

    /**
     * Load the elements from the given array in row-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated Double4x4 loadRM(float[] src, int offset);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified).
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Double4x4 loadRM(FloatBuffer src) { return loadRMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified).
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Double4x4 loadRMAbsolute(FloatBuffer src) { return loadRMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in row-major order, starting at the given absolute
     * index (the position is not used or modified).
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
     * @param src the source buffer
     * @return this
     */
    @Mutated Double4x4 loadRMAbsolute(int index, FloatBuffer src);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * and advancing the position accordingly.
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
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadRMRelative(FloatBuffer src) {
        if (src.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src);
        src.position(pos + 16);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified).
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Double4x4 loadRMFloat(ByteBuffer src) { return loadRMFloatAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified).
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Double4x4 loadRMFloatAbsolute(ByteBuffer src) { return loadRMFloatAbsolute(src.position(), src); }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified).
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated Double4x4 loadRMFloatAbsolute(int index, ByteBuffer src);

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly.
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
     * @param src the source byte buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadRMFloatRelative(ByteBuffer src) {
        if (src.remaining() < 64) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMFloatAbsolute(pos, src);
        src.position(pos + 64);
        return this;
    }

    /**
     * Load the elements from the given memory segment in row-major order, converting each element
     * from {@code float}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @return this
     */
    @Mutated default Double4x4 loadRMFloat(MemorySegment src) { return loadRMFloat(0L, src); }

    /**
     * Load the elements from the given memory segment in row-major order, converting each element
     * from {@code float}, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return this
     */
    @Mutated Double4x4 loadRMFloat(long offset, MemorySegment src);

    /**
     * Load the elements from the given raw memory address in row-major order, converting each
     * element from {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    @Mutated Double4x4 loadRMFloatUnsafe(long address);

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
    @Mutated Double4x4 loadCM(double[] src, int offset, int stride);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive columns.
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
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Double4x4 loadCM(DoubleBuffer src, int stride) { return loadCMAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive columns.
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
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated Double4x4 loadCMAbsolute(int index, DoubleBuffer src, int stride);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
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
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadCMRelative(DoubleBuffer src, int stride) {
        if (src.remaining() < 4L * stride) throw new java.nio.BufferUnderflowException();
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Double4x4 loadCM(ByteBuffer src, int stride) { return loadCMAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive columns.
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
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated Double4x4 loadCMAbsolute(int index, ByteBuffer src, int stride);

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
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
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadCMRelative(ByteBuffer src, int stride) {
        if (src.remaining() < 4L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src, stride);
        src.position(pos + (4 * stride) * 8);
        return this;
    }

    /**
     * Load the elements from the given memory segment in column-major order, with {@code stride}
     * elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Double4x4 loadCM(MemorySegment src, int stride) { return loadCM(0L, src, stride); }

    /**
     * Load the elements from the given memory segment in column-major order, starting at the given
     * offset, with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated Double4x4 loadCM(long offset, MemorySegment src, int stride);

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
     */
    @Mutated Double4x4 loadCMUnsafe(long address, int stride);

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
    @Mutated Double4x4 loadCM(float[] src, int offset, int stride);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified), with {@code stride} elements between the starts of
     * consecutive columns.
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
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Double4x4 loadCM(FloatBuffer src, int stride) { return loadCMAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive columns.
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
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated Double4x4 loadCMAbsolute(int index, FloatBuffer src, int stride);

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
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
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadCMRelative(FloatBuffer src, int stride) {
        if (src.remaining() < 4L * stride) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMAbsolute(pos, src, stride);
        src.position(pos + 4 * stride);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at its current position (the position is not modified), with
     * {@code stride} elements between the starts of consecutive columns.
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
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Double4x4 loadCMFloat(ByteBuffer src, int stride) { return loadCMFloatAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at the given absolute index (the position is not used or
     * modified), with {@code stride} elements between the starts of consecutive columns.
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
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated Double4x4 loadCMFloatAbsolute(int index, ByteBuffer src, int stride);

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at its current position and advancing the position accordingly,
     * with {@code stride} elements between the starts of consecutive columns.
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
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadCMFloatRelative(ByteBuffer src, int stride) {
        if (src.remaining() < 4L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadCMFloatAbsolute(pos, src, stride);
        src.position(pos + (4 * stride) * 4);
        return this;
    }

    /**
     * Load the elements from the given memory segment in column-major order, converting each
     * element from {@code float}, with {@code stride} elements between the starts of consecutive
     * columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Double4x4 loadCMFloat(MemorySegment src, int stride) { return loadCMFloat(0L, src, stride); }

    /**
     * Load the elements from the given memory segment in column-major order, converting each
     * element from {@code float}, starting at the given offset, with {@code stride} elements
     * between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated Double4x4 loadCMFloat(long offset, MemorySegment src, int stride);

    /**
     * Load the elements from the given raw memory address in column-major order, converting each
     * element from {@code float}, with {@code stride} elements between the starts of consecutive
     * columns. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated Double4x4 loadCMFloatUnsafe(long address, int stride);

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
    @Mutated Double4x4 loadRM(double[] src, int offset, int stride);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified), with {@code stride} elements between the starts of
     * consecutive rows.
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
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated default Double4x4 loadRM(DoubleBuffer src, int stride) { return loadRMAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given buffer in row-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive rows.
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
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated Double4x4 loadRMAbsolute(int index, DoubleBuffer src, int stride);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * and advancing the position accordingly, with {@code stride} elements between the starts of
     * consecutive rows.
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
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadRMRelative(DoubleBuffer src, int stride) {
        if (src.remaining() < 4L * stride) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src, stride);
        src.position(pos + 4 * stride);
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
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated default Double4x4 loadRM(ByteBuffer src, int stride) { return loadRMAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given byte buffer in row-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive rows.
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
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated Double4x4 loadRMAbsolute(int index, ByteBuffer src, int stride);

    /**
     * Load the elements from the given byte buffer in row-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive rows.
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
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadRMRelative(ByteBuffer src, int stride) {
        if (src.remaining() < 4L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src, stride);
        src.position(pos + (4 * stride) * 8);
        return this;
    }

    /**
     * Load the elements from the given memory segment in row-major order, with {@code stride}
     * elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated default Double4x4 loadRM(MemorySegment src, int stride) { return loadRM(0L, src, stride); }

    /**
     * Load the elements from the given memory segment in row-major order, starting at the given
     * offset, with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated Double4x4 loadRM(long offset, MemorySegment src, int stride);

    /**
     * Load the elements from the given raw memory address in row-major order, with {@code stride}
     * elements between the starts of consecutive rows. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated Double4x4 loadRMUnsafe(long address, int stride);

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
    @Mutated Double4x4 loadRM(float[] src, int offset, int stride);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * (the position is not modified), with {@code stride} elements between the starts of
     * consecutive rows.
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
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated default Double4x4 loadRM(FloatBuffer src, int stride) { return loadRMAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given buffer in row-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive rows.
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
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated Double4x4 loadRMAbsolute(int index, FloatBuffer src, int stride);

    /**
     * Load the elements from the given buffer in row-major order, starting at its current position
     * and advancing the position accordingly, with {@code stride} elements between the starts of
     * consecutive rows.
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
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadRMRelative(FloatBuffer src, int stride) {
        if (src.remaining() < 4L * stride) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMAbsolute(pos, src, stride);
        src.position(pos + 4 * stride);
        return this;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified), with
     * {@code stride} elements between the starts of consecutive rows.
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
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated default Double4x4 loadRMFloat(ByteBuffer src, int stride) { return loadRMFloatAbsolute(src.position(), src, stride); }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified),
     * with {@code stride} elements between the starts of consecutive rows.
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
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated Double4x4 loadRMFloatAbsolute(int index, ByteBuffer src, int stride);

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly, with
     * {@code stride} elements between the starts of consecutive rows.
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
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadRMFloatRelative(ByteBuffer src, int stride) {
        if (src.remaining() < 4L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = src.position();
        loadRMFloatAbsolute(pos, src, stride);
        src.position(pos + (4 * stride) * 4);
        return this;
    }

    /**
     * Load the elements from the given memory segment in row-major order, converting each element
     * from {@code float}, with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated default Double4x4 loadRMFloat(MemorySegment src, int stride) { return loadRMFloat(0L, src, stride); }

    /**
     * Load the elements from the given memory segment in row-major order, converting each element
     * from {@code float}, starting at the given offset, with {@code stride} elements between the
     * starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated Double4x4 loadRMFloat(long offset, MemorySegment src, int stride);

    /**
     * Load the elements from the given raw memory address in row-major order, converting each
     * element from {@code float}, with {@code stride} elements between the starts of consecutive
     * rows. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    @Mutated Double4x4 loadRMFloatUnsafe(long address, int stride);

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Double4x4 load(double[] src) { return loadCM(src); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated default Double4x4 load(double[] src, int offset) { return loadCM(src, offset); }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position (the position is not modified).
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Double4x4 load(DoubleBuffer src) { return loadCMAbsolute(src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified).
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Double4x4 load(int index, DoubleBuffer src) { return loadCMAbsolute(index, src); }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
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
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadRelative(DoubleBuffer src) { return loadCMRelative(src); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return this
     */
    @Mutated default Double4x4 load(float[] src) { return loadCM(src); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return this
     */
    @Mutated default Double4x4 load(float[] src, int offset) { return loadCM(src, offset); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified).
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Double4x4 load(FloatBuffer src) { return loadCMAbsolute(src); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified).
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
     * @param src the source buffer
     * @return this
     */
    @Mutated default Double4x4 load(int index, FloatBuffer src) { return loadCMAbsolute(index, src); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly.
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
     * @param src the source buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadRelative(FloatBuffer src) { return loadCMRelative(src); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position (the position is not modified).
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Double4x4 load(ByteBuffer src) { return loadCMAbsolute(src); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified).
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
     * @param src the source byte buffer
     * @return this
     */
    @Mutated default Double4x4 load(int index, ByteBuffer src) { return loadCMAbsolute(index, src); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly.
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
     * @param src the source byte buffer
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadRelative(ByteBuffer src) { return loadCMRelative(src); }

    /**
     * Load the elements from the given memory segment in column-major order.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @return this
     */
    @Mutated default Double4x4 load(MemorySegment src) { return loadCM(src); }

    /**
     * Load the elements from the given memory segment in column-major order, starting at the given
     * offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return this
     */
    @Mutated default Double4x4 load(long offset, MemorySegment src) { return loadCM(offset, src); }

    /**
     * Load the elements from the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    @Mutated default Double4x4 loadUnsafe(long address) { return loadCMUnsafe(address); }

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
    @Mutated default Double4x4 load(double[] src, int offset, int stride) { return loadCM(src, offset, stride); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}, starting at the given offset, with {@code stride} elements between the starts
     * of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Double4x4 load(float[] src, int offset, int stride) { return loadCM(src, offset, stride); }

    /**
     * Load the elements from the given buffer in column-major order, starting at the given absolute
     * index (the position is not used or modified), with {@code stride} elements between the starts
     * of consecutive columns.
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
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Double4x4 load(int index, DoubleBuffer src, int stride) { return loadCMAbsolute(index, src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
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
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadRelative(DoubleBuffer src, int stride) { return loadCMRelative(src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified),
     * with {@code stride} elements between the starts of consecutive columns.
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
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Double4x4 load(int index, FloatBuffer src, int stride) { return loadCMAbsolute(index, src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly, with
     * {@code stride} elements between the starts of consecutive columns.
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
     * @param src the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadRelative(FloatBuffer src, int stride) { return loadCMRelative(src, stride); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at the given
     * absolute index (the position is not used or modified), with {@code stride} elements between
     * the starts of consecutive columns.
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
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Double4x4 load(int index, ByteBuffer src, int stride) { return loadCMAbsolute(index, src, stride); }

    /**
     * Load the elements from the given byte buffer in column-major order, starting at its current
     * position and advancing the position accordingly, with {@code stride} elements between the
     * starts of consecutive columns.
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
     * @param src the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    @Mutated default Double4x4 loadRelative(ByteBuffer src, int stride) { return loadCMRelative(src, stride); }

    /**
     * Load the elements from the given memory segment in column-major order, starting at the given
     * offset, with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    @Mutated default Double4x4 load(long offset, MemorySegment src, int stride) { return loadCM(offset, src, stride); }

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
     */
    @Mutated default Double4x4 loadUnsafe(long address, int stride) { return loadCMUnsafe(address, stride); }
}
