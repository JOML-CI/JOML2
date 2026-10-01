// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.ByteBuffer;
import java.lang.foreign.MemorySegment;

/**
 * Read-only view of a 4x4 matrix of double-precision {@code double} components.
 * <p>
 * Declares the accessors, queries, store methods and dest-form operations that read but never
 * mutate the receiver; the mutable counterpart is {@link Double4x4}. APIs that only read a 4x4
 * matrix should accept {@code Double4x4R}, so callers can pass mutable instances without exposing
 * them to modification.
 * <p>
 * Arguments of type {@code Double4x4R} must be instances created by the library ({@link Joml}
 * factories / the library's own types); the implementations read cached state through the library's
 * own classes, so foreign implementations of the {@code *R} interfaces are not supported as
 * arguments.
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
public interface Double4x4R {
    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int SIZE_BYTES = 128;

    /** The number of rows - the tight stride of the column-major ({@code storeCM}/{@code loadCM}) strided overloads. */
    public static final int ROWS = 4;
    /** The number of columns - the tight stride of the row-major ({@code storeRM}/{@code loadRM}) strided overloads. */
    public static final int COLUMNS = 4;

    /** {@return a shared identity matrix} Never mutate it - it is returned as the read-only view for that reason. <p>Valid input: any value, NaN and the infinities included. */
    public static Double4x4R IDENTITY() { return Holder.IDENTITY; }
    /** {@return a shared all-zero matrix} Never mutate it - it is returned as the read-only view for that reason. <p>Valid input: any value, NaN and the infinities included. */
    public static Double4x4R ZERO() { return Holder.ZERO; }


    /**
     * Get the column at the given index of this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param col the column index
     * @param dest will hold the result
     * @return dest
     * @throws IndexOutOfBoundsException if {@code col} is not in {@code [0, COLUMNS)}
     */
    Double4 getColumn(int col, @Mutated Double4 dest);

    /**
     * Get the Euler angles in radians of this matrix, to be applied about the X, Y and Z axes, in
     * that order and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getEulerAnglesXYZ(@Mutated Double3 dest);

    /**
     * Get the Euler angles in radians of this matrix, to be applied about the X, Z and Y axes, in
     * that order and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getEulerAnglesXZY(@Mutated Double3 dest);

    /**
     * Get the Euler angles in radians of this matrix, to be applied about the Y, X and Z axes, in
     * that order and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getEulerAnglesYXZ(@Mutated Double3 dest);

    /**
     * Get the Euler angles in radians of this matrix, to be applied about the Y, Z and X axes, in
     * that order and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getEulerAnglesYZX(@Mutated Double3 dest);

    /**
     * Get the Euler angles in radians of this matrix, to be applied about the Z, X and Y axes, in
     * that order and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getEulerAnglesZXY(@Mutated Double3 dest);

    /**
     * Get the Euler angles in radians of this matrix, to be applied about the Z, Y and X axes, in
     * that order and store the result in {@code dest}.
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
     * {@code double} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getEulerAnglesZYX(@Mutated Double3 dest);

    /**
     * Extract the rotation of this matrix as a quaternion, column-normalizing the linear block
     * first to strip scale (skew is not removed: a sheared block yields a quaternion that is not
     * unit length) and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat getNormalizedRotation(@Mutated DoubleQuat dest);

    /**
     * Get the row at the given index of this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param row the row index
     * @param dest will hold the result
     * @return dest
     * @throws IndexOutOfBoundsException if {@code row} is not in {@code [0, ROWS)}
     */
    Double4 getRow(int row, @Mutated Double4 dest);

    /**
     * Get the scaling factors of this matrix, as the lengths of its basis columns (always
     * non-negative; skew is ignored) and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getScale(@Mutated Double3 dest);

    /**
     * Get the translation of this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 getTranslation(@Mutated Double3 dest);

    /**
     * Extract the rotation of this matrix as a quaternion directly from the linear block, without
     * normalizing it and store the result in {@code dest}.
     * <p>
     * Valid input: this matrix must be a rotation matrix.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat getUnnormalizedRotation(@Mutated DoubleQuat dest);

    /**
     * Obtain the direction of {@code -X} before the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * The direction is the cross product of the other two rows of the upper-left 3x3, i.e. the
     * inverse's column up to the determinant, whose sign is kept: for a mirroring matrix (negative
     * determinant) the result points the opposite way to the inverse applied to {@code -X}, as in
     * JOML. The {@code invNormalized} variant, which assumes an orthogonal matrix, reads the row
     * itself: there the inverse's column, mirrored or not.
     * <p>
     * It holds for any finite matrix: when the cross product of the two rows the direction is
     * formed from would leave the {@code double} range, it is recomputed from those rows scaled
     * exactly by powers of two. When that cross product is exactly zero (a zero row, for instance),
     * the result is the zero vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invNegativeX(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code -Y} before the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * The direction is the cross product of the other two rows of the upper-left 3x3, i.e. the
     * inverse's column up to the determinant, whose sign is kept: for a mirroring matrix (negative
     * determinant) the result points the opposite way to the inverse applied to {@code -Y}, as in
     * JOML. The {@code invNormalized} variant, which assumes an orthogonal matrix, reads the row
     * itself: there the inverse's column, mirrored or not.
     * <p>
     * It holds for any finite matrix: when the cross product of the two rows the direction is
     * formed from would leave the {@code double} range, it is recomputed from those rows scaled
     * exactly by powers of two. When that cross product is exactly zero (a zero row, for instance),
     * the result is the zero vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invNegativeY(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code -Z} before the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * The direction is the cross product of the other two rows of the upper-left 3x3, i.e. the
     * inverse's column up to the determinant, whose sign is kept: for a mirroring matrix (negative
     * determinant) the result points the opposite way to the inverse applied to {@code -Z}, as in
     * JOML. The {@code invNormalized} variant, which assumes an orthogonal matrix, reads the row
     * itself: there the inverse's column, mirrored or not.
     * <p>
     * It holds for any finite matrix: when the cross product of the two rows the direction is
     * formed from would leave the {@code double} range, it is recomputed from those rows scaled
     * exactly by powers of two. When that cross product is exactly zero (a zero row, for instance),
     * the result is the zero vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invNegativeZ(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code -X} before the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this matrix must be free of scaling and shear.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invNormalizedNegativeX(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code -Y} before the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this matrix must be free of scaling and shear.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invNormalizedNegativeY(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code -Z} before the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this matrix must be free of scaling and shear.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invNormalizedNegativeZ(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +X} before the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this matrix must be free of scaling and shear.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invNormalizedPositiveX(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +Y} before the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this matrix must be free of scaling and shear.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invNormalizedPositiveY(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +Z} before the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this matrix must be free of scaling and shear.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invNormalizedPositiveZ(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +X} before the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * The direction is the cross product of the other two rows of the upper-left 3x3, i.e. the
     * inverse's column up to the determinant, whose sign is kept: for a mirroring matrix (negative
     * determinant) the result points the opposite way to the inverse applied to {@code +X}, as in
     * JOML. The {@code invNormalized} variant, which assumes an orthogonal matrix, reads the row
     * itself: there the inverse's column, mirrored or not.
     * <p>
     * It holds for any finite matrix: when the cross product of the two rows the direction is
     * formed from would leave the {@code double} range, it is recomputed from those rows scaled
     * exactly by powers of two. When that cross product is exactly zero (a zero row, for instance),
     * the result is the zero vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invPositiveX(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +Y} before the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * The direction is the cross product of the other two rows of the upper-left 3x3, i.e. the
     * inverse's column up to the determinant, whose sign is kept: for a mirroring matrix (negative
     * determinant) the result points the opposite way to the inverse applied to {@code +Y}, as in
     * JOML. The {@code invNormalized} variant, which assumes an orthogonal matrix, reads the row
     * itself: there the inverse's column, mirrored or not.
     * <p>
     * It holds for any finite matrix: when the cross product of the two rows the direction is
     * formed from would leave the {@code double} range, it is recomputed from those rows scaled
     * exactly by powers of two. When that cross product is exactly zero (a zero row, for instance),
     * the result is the zero vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invPositiveY(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +Z} before the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * The direction is the cross product of the other two rows of the upper-left 3x3, i.e. the
     * inverse's column up to the determinant, whose sign is kept: for a mirroring matrix (negative
     * determinant) the result points the opposite way to the inverse applied to {@code +Z}, as in
     * JOML. The {@code invNormalized} variant, which assumes an orthogonal matrix, reads the row
     * itself: there the inverse's column, mirrored or not.
     * <p>
     * It holds for any finite matrix: when the cross product of the two rows the direction is
     * formed from would leave the {@code double} range, it is recomputed from those rows scaled
     * exactly by powers of two. When that cross product is exactly zero (a zero row, for instance),
     * the result is the zero vector.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 invPositiveZ(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code -X} after the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 negativeX(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code -Y} after the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 negativeY(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code -Z} after the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 negativeZ(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code -X} after the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this matrix must be free of scaling and shear.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 normalizedNegativeX(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code -Y} after the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this matrix must be free of scaling and shear.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 normalizedNegativeY(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code -Z} after the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this matrix must be free of scaling and shear.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 normalizedNegativeZ(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +X} after the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this matrix must be free of scaling and shear.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 normalizedPositiveX(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +Y} after the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this matrix must be free of scaling and shear.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 normalizedPositiveY(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +Z} after the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * This method skips the normalization the plain variant performs.
     * <p>
     * Valid input: this matrix must be free of scaling and shear.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 normalizedPositiveZ(@Mutated Double3 dest);

    /**
     * Obtain the position that is transformed to the origin by this matrix (a rotation-translation
     * or view matrix) and store the result in {@code dest}.
     * <p>
     * Valid input: this matrix must be free of scaling and shear.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 origin(@Mutated Double3 dest);

    /**
     * Obtain the position that is transformed to the origin by this matrix, for an arbitrary
     * invertible linear block (scale and skew included) and store the result in {@code dest}.
     * <p>
     * Unlike {@code origin}, which evaluates -R^T*t and is therefore only correct for an
     * orthonormal upper-left 3x3 block, this form divides through by the determinant and stays
     * correct under scale and skew. A singular matrix has no such point: dividing by its zero
     * determinant yields a non-finite result, {@code NaN} or an infinity per component.
     * <p>
     * Valid input: this matrix must be affine; this matrix must be invertible.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 originAffine(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +X} after the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 positiveX(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +Y} after the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 positiveY(@Mutated Double3 dest);

    /**
     * Obtain the direction of {@code +Z} after the transformation represented by this matrix is
     * applied and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 positiveZ(@Mutated Double3 dest);

    /**
     * Compute the cofactor matrix of this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 cofactor(@Mutated Double4x4 dest);

    /**
     * Compute the determinant of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the determinant of this matrix
     */
    double determinant();

    /**
     * Compute the Frobenius norm of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Frobenius norm of this matrix
     */
    double frobeniusNorm();

    /**
     * Invert this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 invert(@Mutated Double4x4 dest);

    /**
     * Invert this matrix, which must be a perspective frustum projection as built by makeFrustum or
     * frustum (also an off-center or symmetric one): only the elements such a matrix can have
     * nonzero are read, a few divisions instead of a general 4x4 inverse. For any other matrix the
     * result is undefined; a singular one gives infinite or NaN elements. For a symmetric
     * perspective, invertPerspective reads fewer elements.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: this matrix must be a perspective projection.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 invertFrustum(@Mutated Double4x4 dest);

    /**
     * Invert this matrix, which must be an orthographic projection as built by makeOrtho or ortho
     * (also an off-center or 2D one): only its diagonal and translation are read, three divisions
     * instead of a general 4x4 inverse. For any other matrix the result is undefined; a singular
     * one gives infinite or NaN elements.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: this matrix must be an orthographic projection.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 invertOrtho(@Mutated Double4x4 dest);

    /**
     * Invert this matrix, which must be a symmetric perspective projection as built by
     * makePerspective or perspective (any handedness, depth range and near/far plane, also at
     * infinity): only the elements such a matrix can have nonzero are read, which makes this a
     * handful of divisions instead of a general 4x4 inverse. For any other matrix the result is
     * undefined; a singular one (a zero field of view, near equal to far) gives infinite or NaN
     * elements.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: this matrix must be a symmetric perspective projection.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 invertPerspective(@Mutated Double4x4 dest);

    /**
     * Compute the inverse of this matrix multiplied by view, this being a symmetric perspective
     * projection (see invertPerspective) and view a rotation and translation without scaling - a
     * camera from lookAt: no general 4x4 inverse is needed. The same as mul(view).invert() for such
     * matrices; for any other the result is undefined.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: this matrix must be a symmetric perspective projection; {@code view} must be
     * free of scaling and shear.
     *
     * @param view the camera's view transformation: rotation and translation only
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 invertPerspectiveView(Double3x4R view, @Mutated Double4x4 dest);

    /**
     * Compute the inverse of this matrix multiplied by view, this being a symmetric perspective
     * projection (see invertPerspective) and view an affine transformation with a unit-length
     * rotation - a camera from lookAt, or any rotation and translation without scaling: its inverse
     * is its transposed rotation and back-rotated negated translation, so no general 4x4 inverse is
     * needed. The same as mul(view).invert() for such matrices; for any other the result is
     * undefined.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: this matrix must be a symmetric perspective projection; {@code view} must be
     * free of scaling and shear.
     *
     * @param view the camera's view transformation: affine, rotation and translation only
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 invertPerspectiveView(Double4x4R view, @Mutated Double4x4 dest);

    /**
     * Compute the inverse of the product of this matrix and {@code other}, i.e.
     * {@code (this * other)^-1} and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 invertProduct(Double4x4R other, @Mutated Double4x4 dest);

    /**
     * Compute the inverse of the product of this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m03}, {@code m10}, {@code m11}, {@code m12}, {@code m13}, {@code m20}, {@code m21},
     * {@code m22}, {@code m23}, {@code m30}, {@code m31}, {@code m32}, {@code m33}) and store the
     * result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 invertProduct(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33, @Mutated Double4x4 dest);

    /**
     * Compute the normal matrix of this matrix, i.e. the transpose of its inverse and store the
     * result in {@code dest}.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 normal(@Mutated Double4x4 dest);

    /**
     * Compute the trace of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the trace of this matrix
     */
    double trace();

    /**
     * Transpose this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 transpose(@Mutated Double4x4 dest);

    /**
     * Add {@code other} to this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to add
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 add(Double4x4R other, @Mutated Double4x4 dest);

    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11},
     * {@code m12}, {@code m13}, {@code m20}, {@code m21}, {@code m22}, {@code m23}, {@code m30},
     * {@code m31}, {@code m32}, {@code m33}) to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 add(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33, @Mutated Double4x4 dest);

    /**
     * Multiply each component of this matrix by {@code scalar} and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mul(double scalar, @Mutated Double4x4 dest);

    /**
     * Negate this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 negate(@Mutated Double4x4 dest);

    /**
     * Subtract {@code other} from this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to subtract
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 sub(Double4x4R other, @Mutated Double4x4 dest);

    /**
     * Subtract ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11},
     * {@code m12}, {@code m13}, {@code m20}, {@code m21}, {@code m22}, {@code m23}, {@code m30},
     * {@code m31}, {@code m32}, {@code m33}) from this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 sub(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33, @Mutated Double4x4 dest);

    /**
     * Set the translation of this matrix, leaving every other element untouched.
     * <p>
     * Only the first three elements of the last column are written, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this overwrites the
     * translation instead of composing a translation onto the existing transformation.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 withTranslation(Double3R t, @Mutated Double4x4 dest);

    /**
     * Set the translation of this matrix, leaving every other element untouched.
     * <p>
     * Only the first three elements of the last column are written, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this overwrites the
     * translation instead of composing a translation onto the existing transformation.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the translation offsets {@code (x, y, z)}
     * @param y the {@code y} component of the translation offsets {@code (x, y, z)}
     * @param z the {@code z} component of the translation offsets {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 withTranslation(double x, double y, double z, @Mutated Double4x4 dest);

    /**
     * Convert this matrix to {@code float} precision and store the result in {@code dest}.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Float4x4 toFloat(@Mutated Float4x4 dest);

    /**
     * Extract the upper-left 3x3 block of this matrix (dropping the translation column and the last
     * row) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3x3 to3x3(@Mutated Double3x3 dest);

    /**
     * Truncate this matrix to a 3x4 matrix, dropping the last row (assumed {@code 0, 0, 0, 1}) and
     * store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3x4 to3x4(@Mutated Double3x4 dest);

    /**
     * Convert this matrix (a rigid transform) to a dual quaternion and store the result in
     * {@code dest}.
     * <p>
     * Valid input: this matrix must be a rotation matrix.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleDualQuat toDualQuat(@Mutated DoubleDualQuat dest);

    /**
     * Decompose this matrix into a rigid transform: translation from the last column, rotation from
     * the column-normalized upper-left 3x3 block (scale is removed by normalizing the columns, but
     * shear is not removed: a sheared block yields a rotation quaternion that is not unit length)
     * and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleRigid toRigid(@Mutated DoubleRigid dest);

    /**
     * Decompose this matrix into a TRS transform: translation from the last column, scale from the
     * column lengths of the upper-left 3x3 block, rotation from the column-normalized block (scale
     * is removed by normalizing the columns, but shear is not removed: a sheared block yields a
     * rotation quaternion that is not unit length) and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleTransform toTransform(@Mutated DoubleTransform dest);

    /**
     * Extract the rotation part of this matrix and store the result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; this
     * matrix must be affine.
     *
     * @param dest will hold the result
     * @return dest
     */
    DoubleQuat decomposeRotation(@Mutated DoubleQuat dest);

    /**
     * Extract the scaling factors of this matrix via Gram-Schmidt orthogonalization (skew-aware;
     * the x factor carries the sign of a reflection when the determinant is negative) and store the
     * result in {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; this
     * matrix must be affine.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 decomposeScale(@Mutated Double3 dest);

    /**
     * Extract the shear (skew) factors of this matrix via Gram-Schmidt orthogonalization, as
     * {@code (skewYZ, skewXZ, skewXY)} (all zero for a shear-free matrix) and store the result in
     * {@code dest}.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; this
     * matrix must be affine; this matrix must be invertible.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double3 decomposeSkew(@Mutated Double3 dest);

    /**
     * Decompose this matrix into its translation, rotation and scale components, storing them in
     * {@code translation}, {@code rotation} and {@code scale} respectively.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; this
     * matrix must be affine.
     *
     * @param translation will hold the translation
     * @param rotation will hold the rotation
     * @param scale will hold the scale
     * @return this
     */
    Double4x4 decomposeTRS(@Mutated Double3 translation, @Mutated DoubleQuat rotation, @Mutated Double3 scale);

    /**
     * Compute the axis-aligned box enclosing the frustum of this matrix, interpreted as a
     * projection or combined view-projection matrix and store the result in {@code dest}.
     * <p>
     * The box encloses all eight frustum corners, so it is the tightest axis-aligned bound on the
     * frustum but not on the geometry inside it. A projection whose far plane is at infinity has an
     * unbounded frustum, and the box is then infinite too.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix with finite clip
     * planes.
     *
     * @param depthRange the clip-space depth range the projection maps onto
     * @param dest will hold the result
     * @return dest
     */
    DoubleAABB frustumAabb(DepthRange depthRange, @Mutated DoubleAABB dest);

    /**
     * Compute the axis-aligned box enclosing the frustum of this matrix, interpreted as a
     * projection or combined view-projection matrix and store the result in {@code dest}.
     * <p>
     * The box encloses all eight frustum corners, so it is the tightest axis-aligned bound on the
     * frustum but not on the geometry inside it. A projection whose far plane is at infinity has an
     * unbounded frustum, and the box is then infinite too.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix with finite clip
     * planes.
     *
     * @param dest will hold the result
     * @return dest
     */
    default DoubleAABB frustumAabb(@Mutated DoubleAABB dest) { return frustumAabb(DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Compute the given frustum corner of this matrix, interpreted as a projection or combined
     * view-projection matrix and store the result in {@code dest}.
     * <p>
     * Each constant names a corner of the clip-space cube by the sign its three coordinates take
     * there, so {@code NXNYNZ} is the bottom-left corner of the near plane and {@code PXPYPZ} the
     * top-right corner of the far plane. A corner on a depth plane at infinity is infinite, with
     * each component's sign giving the side the frustum recedes to.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix with finite clip
     * planes.
     *
     * @param corner the frustum corner to compute
     * @param depthRange the clip-space depth range the projection maps onto
     * @param dest will hold the result
     * @return dest
     */
    Double3 frustumCorner(FrustumCorner corner, DepthRange depthRange, @Mutated Double3 dest);

    /**
     * Compute the given frustum corner of this matrix, interpreted as a projection or combined
     * view-projection matrix and store the result in {@code dest}.
     * <p>
     * Each constant names a corner of the clip-space cube by the sign its three coordinates take
     * there, so {@code NXNYNZ} is the bottom-left corner of the near plane and {@code PXPYPZ} the
     * top-right corner of the far plane. A corner on a depth plane at infinity is infinite, with
     * each component's sign giving the side the frustum recedes to.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix with finite clip
     * planes.
     *
     * @param corner the frustum corner to compute
     * @param dest will hold the result
     * @return dest
     */
    default Double3 frustumCorner(FrustumCorner corner, @Mutated Double3 dest) { return frustumCorner(corner, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Compute the given frustum plane of this matrix, interpreted as a projection or combined
     * view-projection matrix and store the result in {@code dest}.
     * <p>
     * The plane equation is {@code a*x + b*y + c*z + d}, with the normal {@code (a, b, c)} pointing
     * into the frustum, so a point is inside the frustum when it evaluates to a non-negative value
     * against all six planes. The coefficients are not normalized: {@code (a, b, c)} has an
     * arbitrary length and {@code d} is therefore not a metric distance. Normalize the result to
     * compare plane-point distances.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix.
     *
     * @param plane the frustum plane to compute
     * @param depthRange the clip-space depth range the projection maps onto
     * @param dest will hold the result
     * @return dest
     */
    Double4 frustumPlane(FrustumPlane plane, DepthRange depthRange, @Mutated Double4 dest);

    /**
     * Compute the given frustum plane of this matrix, interpreted as a projection or combined
     * view-projection matrix and store the result in {@code dest}.
     * <p>
     * The plane equation is {@code a*x + b*y + c*z + d}, with the normal {@code (a, b, c)} pointing
     * into the frustum, so a point is inside the frustum when it evaluates to a non-negative value
     * against all six planes. The coefficients are not normalized: {@code (a, b, c)} has an
     * arbitrary length and {@code d} is therefore not a metric distance. Normalize the result to
     * compare plane-point distances.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix.
     *
     * @param plane the frustum plane to compute
     * @param dest will hold the result
     * @return dest
     */
    default Double4 frustumPlane(FrustumPlane plane, @Mutated Double4 dest) { return frustumPlane(plane, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Compute the direction of the view ray through the frustum of this matrix at the given
     * horizontal and vertical interpolation factors.
     * <p>
     * {@code (0, 0)} is the bottom-left and {@code (1, 1)} the top-right frustum corner. The result
     * is not normalized: it is the difference between the far and the near frustum corner along
     * that ray, so the near corner plus the result lies on the far plane. For a projection whose
     * far plane is at infinity the result is a finite direction along the ray of unspecified
     * length. A far plane whose homogeneous w is at most {@code 2^-40} times the near plane's is
     * treated as being at infinity. A projection whose near plane is at infinity (the reversed-Z
     * projection) turns the result around: it then points from the far plane toward the infinite
     * near plane, away from the camera, again a finite direction of unspecified length; a near
     * plane whose homogeneous w is at most {@code 2^-40} times the far plane's counts as infinite.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix; {@code x} must lie
     * in {@code [0, 1]}; {@code y} must lie in {@code [0, 1]}.
     *
     * @param x the horizontal frustum interpolation factor in {@code [0, 1]}
     * @param y the vertical frustum interpolation factor in {@code [0, 1]}
     * @param depthRange the clip-space depth range the projection maps onto
     * @param dest will hold the result
     * @return dest
     */
    Double3 frustumRayDir(double x, double y, DepthRange depthRange, @Mutated Double3 dest);

    /**
     * Compute the direction of the view ray through the frustum of this matrix at the given
     * horizontal and vertical interpolation factors.
     * <p>
     * {@code (0, 0)} is the bottom-left and {@code (1, 1)} the top-right frustum corner. The result
     * is not normalized: it is the difference between the far and the near frustum corner along
     * that ray, so the near corner plus the result lies on the far plane. For a projection whose
     * far plane is at infinity the result is a finite direction along the ray of unspecified
     * length. A far plane whose homogeneous w is at most {@code 2^-40} times the near plane's is
     * treated as being at infinity. A projection whose near plane is at infinity (the reversed-Z
     * projection) turns the result around: it then points from the far plane toward the infinite
     * near plane, away from the camera, again a finite direction of unspecified length; a near
     * plane whose homogeneous w is at most {@code 2^-40} times the far plane's counts as infinite.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix; {@code x} must lie
     * in {@code [0, 1]}; {@code y} must lie in {@code [0, 1]}.
     *
     * @param x the horizontal frustum interpolation factor in {@code [0, 1]}
     * @param y the vertical frustum interpolation factor in {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    default Double3 frustumRayDir(double x, double y, @Mutated Double3 dest) { return frustumRayDir(x, y, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Determine whether the given axis-aligned box is partly or completely inside the frustum
     * defined by this matrix.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix; {@code min} must not
     * exceed {@code max} in any component; {@code min} may be infinite; {@code max} may be
     * infinite.
     *
     * @param min the minimum corner of the box
     * @param max the maximum corner of the box
     * @param depthRange the clip-space depth range the projection maps onto
     * @return {@code true} if the given axis-aligned box is partly or completely inside the frustum
     *        defined by this matrix, {@code false} otherwise
     */
    boolean testAabb(Double3R min, Double3R max, DepthRange depthRange);

    /**
     * Determine whether the given axis-aligned box is partly or completely inside the frustum
     * defined by this matrix.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix;
     * {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any component;
     * {@code (minX, minY, minZ)} may be infinite; {@code (maxX, maxY, maxZ)} may be infinite.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY, minZ)}
     * @param minY the {@code y} component of the vector {@code (minX, minY, minZ)}
     * @param minZ the {@code z} component of the vector {@code (minX, minY, minZ)}
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY, maxZ)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY, maxZ)}
     * @param maxZ the {@code z} component of the vector {@code (maxX, maxY, maxZ)}
     * @param depthRange the clip-space depth range the projection maps onto
     * @return {@code true} if the given axis-aligned box is partly or completely inside the frustum
     *        defined by this matrix, {@code false} otherwise
     */
    boolean testAabb(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, DepthRange depthRange);

    /**
     * Determine whether the given axis-aligned box is partly or completely inside the frustum
     * defined by this matrix.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix; the minimum corner
     * of {@code aabb} must not exceed the maximum corner of {@code aabb} in any component; the
     * minimum corner of {@code aabb} may be infinite; the maximum corner of {@code aabb} may be
     * infinite.
     *
     * @param aabb the axis-aligned box to test
     * @param depthRange the clip-space depth range the projection maps onto
     * @return {@code true} if the given axis-aligned box is partly or completely inside the frustum
     *        defined by this matrix, {@code false} otherwise
     */
    boolean testAabb(DoubleAABBR aabb, DepthRange depthRange);

    /**
     * Determine whether the given axis-aligned box is partly or completely inside the frustum
     * defined by this matrix.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix; {@code min} must not
     * exceed {@code max} in any component; {@code min} may be infinite; {@code max} may be
     * infinite.
     *
     * @param min the minimum corner of the box
     * @param max the maximum corner of the box
     * @return {@code true} if the given axis-aligned box is partly or completely inside the frustum
     *        defined by this matrix, {@code false} otherwise
     */
    default boolean testAabb(Double3R min, Double3R max) { return testAabb(min, max, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Determine whether the given axis-aligned box is partly or completely inside the frustum
     * defined by this matrix.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix;
     * {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any component;
     * {@code (minX, minY, minZ)} may be infinite; {@code (maxX, maxY, maxZ)} may be infinite.
     *
     * @param minX the {@code x} component of the vector {@code (minX, minY, minZ)}
     * @param minY the {@code y} component of the vector {@code (minX, minY, minZ)}
     * @param minZ the {@code z} component of the vector {@code (minX, minY, minZ)}
     * @param maxX the {@code x} component of the vector {@code (maxX, maxY, maxZ)}
     * @param maxY the {@code y} component of the vector {@code (maxX, maxY, maxZ)}
     * @param maxZ the {@code z} component of the vector {@code (maxX, maxY, maxZ)}
     * @return {@code true} if the given axis-aligned box is partly or completely inside the frustum
     *        defined by this matrix, {@code false} otherwise
     */
    default boolean testAabb(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) { return testAabb(minX, minY, minZ, maxX, maxY, maxZ, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Determine whether the given axis-aligned box is partly or completely inside the frustum
     * defined by this matrix.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix; the minimum corner
     * of {@code aabb} must not exceed the maximum corner of {@code aabb} in any component; the
     * minimum corner of {@code aabb} may be infinite; the maximum corner of {@code aabb} may be
     * infinite.
     *
     * @param aabb the axis-aligned box to test
     * @return {@code true} if the given axis-aligned box is partly or completely inside the frustum
     *        defined by this matrix, {@code false} otherwise
     */
    default boolean testAabb(DoubleAABBR aabb) { return testAabb(aabb, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Determine whether the given point lies inside the frustum defined by this matrix.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix.
     *
     * @param point the point to test
     * @param depthRange the clip-space depth range the projection maps onto
     * @return {@code true} if the given point lies inside the frustum defined by this matrix,
     *        {@code false} otherwise
     */
    boolean testPoint(Double3R point, DepthRange depthRange);

    /**
     * Determine whether the given point lies inside the frustum defined by this matrix.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param depthRange the clip-space depth range the projection maps onto
     * @return {@code true} if the given point lies inside the frustum defined by this matrix,
     *        {@code false} otherwise
     */
    boolean testPoint(double x, double y, double z, DepthRange depthRange);

    /**
     * Determine whether the given point lies inside the frustum defined by this matrix.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix.
     *
     * @param point the point to test
     * @return {@code true} if the given point lies inside the frustum defined by this matrix,
     *        {@code false} otherwise
     */
    default boolean testPoint(Double3R point) { return testPoint(point, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Determine whether the given point lies inside the frustum defined by this matrix.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @return {@code true} if the given point lies inside the frustum defined by this matrix,
     *        {@code false} otherwise
     */
    default boolean testPoint(double x, double y, double z) { return testPoint(x, y, z, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Determine whether the given sphere is partly or completely inside the frustum defined by this
     * matrix.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix; {@code radius} must
     * not be negative.
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     * @param depthRange the clip-space depth range the projection maps onto
     * @return {@code true} if the given sphere is partly or completely inside the frustum defined
     *        by this matrix, {@code false} otherwise
     */
    boolean testSphere(Double3R center, double radius, DepthRange depthRange);

    /**
     * Determine whether the given sphere is partly or completely inside the frustum defined by this
     * matrix.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix; {@code radius} must
     * not be negative.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param radius the radius of the sphere
     * @param depthRange the clip-space depth range the projection maps onto
     * @return {@code true} if the given sphere is partly or completely inside the frustum defined
     *        by this matrix, {@code false} otherwise
     */
    boolean testSphere(double x, double y, double z, double radius, DepthRange depthRange);

    /**
     * Determine whether the given sphere is partly or completely inside the frustum defined by this
     * matrix.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix; the radius of
     * {@code sph} must not be negative.
     *
     * @param sph the sphere to test
     * @param depthRange the clip-space depth range the projection maps onto
     * @return {@code true} if the given sphere is partly or completely inside the frustum defined
     *        by this matrix, {@code false} otherwise
     */
    boolean testSphere(DoubleSphereR sph, DepthRange depthRange);

    /**
     * Determine whether the given sphere is partly or completely inside the frustum defined by this
     * matrix.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix; {@code radius} must
     * not be negative.
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     * @return {@code true} if the given sphere is partly or completely inside the frustum defined
     *        by this matrix, {@code false} otherwise
     */
    default boolean testSphere(Double3R center, double radius) { return testSphere(center, radius, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Determine whether the given sphere is partly or completely inside the frustum defined by this
     * matrix.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix; {@code radius} must
     * not be negative.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param radius the radius of the sphere
     * @return {@code true} if the given sphere is partly or completely inside the frustum defined
     *        by this matrix, {@code false} otherwise
     */
    default boolean testSphere(double x, double y, double z, double radius) { return testSphere(x, y, z, radius, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Determine whether the given sphere is partly or completely inside the frustum defined by this
     * matrix.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix; the radius of
     * {@code sph} must not be negative.
     *
     * @param sph the sphere to test
     * @return {@code true} if the given sphere is partly or completely inside the frustum defined
     *        by this matrix, {@code false} otherwise
     */
    default boolean testSphere(DoubleSphereR sph) { return testSphere(sph, DepthRange.NEGATIVE_ONE_TO_ONE); }

    /**
     * Linearly interpolate between this matrix and {@code other} using the interpolation factor
     * {@code t} and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 lerp(Double4x4R other, double t, @Mutated Double4x4 dest);

    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m03}, {@code m10}, {@code m11}, {@code m12}, {@code m13}, {@code m20}, {@code m21},
     * {@code m22}, {@code m23}, {@code m30}, {@code m31}, {@code m32}, {@code m33}) using the
     * interpolation factor {@code t} and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 lerp(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33, double t, @Mutated Double4x4 dest);

    /**
     * Multiply this matrix by {@code right} and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mul(Double4x4R right, @Mutated Double4x4 dest);

    /**
     * Multiply this matrix by ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10},
     * {@code m11}, {@code m12}, {@code m13}, {@code m20}, {@code m21}, {@code m22}, {@code m23},
     * {@code m30}, {@code m31}, {@code m32}, {@code m33}) and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mul(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33, @Mutated Double4x4 dest);

    /**
     * Multiply this matrix by {@code right} and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mul(Double2x2R right, @Mutated Double4x4 dest);

    /**
     * Multiply this matrix by {@code right} and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mul(Double2x3R right, @Mutated Double4x4 dest);

    /**
     * Multiply this matrix by {@code right} and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mul(Double3x3R right, @Mutated Double4x4 dest);

    /**
     * Multiply this matrix by {@code right} and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mul(Double3x4R right, @Mutated Double4x4 dest);

    /**
     * Pre-multiply the transformation {@code other} onto this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the given transformation matrix, then the
     * new matrix will be {@code T * M}. So when transforming a vector {@code v} with the new matrix
     * by using {@code T * M * v}, the given transformation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preMul(Double4x4R other, @Mutated Double4x4 dest);

    /**
     * Pre-multiply the transformation ({@code m00}, {@code m01}, {@code m02}, {@code m03},
     * {@code m10}, {@code m11}, {@code m12}, {@code m13}, {@code m20}, {@code m21}, {@code m22},
     * {@code m23}, {@code m30}, {@code m31}, {@code m32}, {@code m33}) onto this matrix and store
     * the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preMul(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33, @Mutated Double4x4 dest);

    /**
     * Pre-multiply {@code other} onto this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preMul(Double2x2R other, @Mutated Double4x4 dest);

    /**
     * Pre-multiply {@code other} onto this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preMul(Double2x3R other, @Mutated Double4x4 dest);

    /**
     * Pre-multiply {@code other} onto this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preMul(Double3x3R other, @Mutated Double4x4 dest);

    /**
     * Pre-multiply {@code other} onto this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preMul(Double3x4R other, @Mutated Double4x4 dest);

    /**
     * Add {@code other} scaled by {@code weight} to this matrix and store the result in
     * {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 addScaled(Double4x4R other, double weight, @Mutated Double4x4 dest);

    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m03}, {@code m10}, {@code m11},
     * {@code m12}, {@code m13}, {@code m20}, {@code m21}, {@code m22}, {@code m23}, {@code m30},
     * {@code m31}, {@code m32}, {@code m33}) scaled by {@code weight} to this matrix and store the
     * result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 addScaled(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33, double weight, @Mutated Double4x4 dest);

    /**
     * Apply an arcball view transformation about the given center to this matrix and store the
     * result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 arcball(double radius, Double3R center, double angleX, double angleY, @Mutated Double4x4 dest);

    /**
     * Apply an arcball view transformation about the given center to this matrix and store the
     * result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 arcball(double radius, double x, double y, double z, double angleX, double angleY, @Mutated Double4x4 dest);

    /**
     * Apply an axonometric dimetric viewing rotation (compose with an orthographic projection for
     * the full transform) to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param alpha the tilt angle in radians about the X axis (at {@code atan(1/sqrt(2))} the
     *        result is isometric)
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 axonometricDimetric(double alpha, @Mutated Double4x4 dest);

    /**
     * Apply an axonometric isometric viewing rotation (compose with an orthographic projection for
     * the full transform) to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 axonometricIsometric(@Mutated Double4x4 dest);

    /**
     * Apply an axonometric trimetric viewing rotation (compose with an orthographic projection for
     * the full transform) to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param alphaX the rotation angle in radians about the X axis
     * @param alphaY the rotation angle in radians about the Y axis
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 axonometricTrimetric(double alphaX, double alphaY, @Mutated Double4x4 dest);

    /**
     * Apply an arbitrary perspective projection frustum transformation to this matrix and store the
     * result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 frustum(double left, double right, double bottom, double top, double zNear, double zFar, Handedness handedness, DepthRange depthRange, @Mutated Double4x4 dest);

    /**
     * Apply an arbitrary perspective projection frustum transformation to this matrix and store the
     * result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 frustum(double left, double right, double bottom, double top, double zNear, double zFar, DepthRange depthRange, @Mutated Double4x4 dest) { return frustum(left, right, bottom, top, zNear, zFar, Handedness.RIGHT_HANDED, depthRange, dest); }

    /**
     * Apply an arbitrary perspective projection frustum transformation to this matrix and store the
     * result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 frustum(double left, double right, double bottom, double top, double zNear, double zFar, Handedness handedness, @Mutated Double4x4 dest) { return frustum(left, right, bottom, top, zNear, zFar, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Apply an arbitrary perspective projection frustum transformation to this matrix and store the
     * result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 frustum(double left, double right, double bottom, double top, double zNear, double zFar, @Mutated Double4x4 dest) { return frustum(left, right, bottom, top, zNear, zFar, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Apply a rotation transformation that makes {@code +z} point along {@code dir} to this matrix
     * and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 lookAlong(Double3R dir, Double3R up, @Mutated Double4x4 dest);

    /**
     * Apply a rotation transformation that makes {@code +z} point along ({@code dirX},
     * {@code dirY}, {@code dirZ}) to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ, @Mutated Double4x4 dest);

    /**
     * Apply a "look at" view transformation with the eye at {@code eye} looking at {@code center}
     * to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 lookAt(Double3R eye, Double3R center, Double3R up, Handedness handedness, @Mutated Double4x4 dest);

    /**
     * Apply a "look at" view transformation with the eye at ({@code eyeX}, {@code eyeY},
     * {@code eyeZ}) looking at ({@code centerX}, {@code centerY}, {@code centerZ}) to this matrix
     * and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 lookAt(double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ, Handedness handedness, @Mutated Double4x4 dest);

    /**
     * Apply a "look at" view transformation with the eye at {@code eye} looking at {@code center}
     * to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 lookAt(Double3R eye, Double3R center, Double3R up, @Mutated Double4x4 dest) { return lookAt(eye, center, up, Handedness.RIGHT_HANDED, dest); }

    /**
     * Apply a "look at" view transformation with the eye at ({@code eyeX}, {@code eyeY},
     * {@code eyeZ}) looking at ({@code centerX}, {@code centerY}, {@code centerZ}) to this matrix
     * and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 lookAt(double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ, @Mutated Double4x4 dest) { return lookAt(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, Handedness.RIGHT_HANDED, dest); }

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+x, +y, +z)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapXYZ(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+x, +y, -z)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapXYnZ(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+x, +z, +y)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapXZY(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+x, +z, -y)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapXZnY(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+x, -y, +z)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapXnYZ(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+x, -y, -z)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapXnYnZ(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+x, -z, +y)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapXnZY(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+x, -z, -y)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapXnZnY(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+y, +x, +z)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapYXZ(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+y, +x, -z)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapYXnZ(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+y, +z, +x)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapYZX(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+y, +z, -x)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapYZnX(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+y, -x, +z)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapYnXZ(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+y, -x, -z)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapYnXnZ(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+y, -z, +x)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapYnZX(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+y, -z, -x)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapYnZnX(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+z, +x, +y)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapZXY(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+z, +x, -y)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapZXnY(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+z, +y, +x)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapZYX(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+z, +y, -x)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapZYnX(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+z, -x, +y)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapZnXY(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+z, -x, -y)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapZnXnY(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+z, -y, +x)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapZnYX(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (+z, -y, -x)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapZnYnX(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-x, +y, +z)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnXYZ(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-x, +y, -z)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnXYnZ(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-x, +z, +y)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnXZY(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-x, +z, -y)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnXZnY(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-x, -y, +z)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnXnYZ(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-x, -y, -z)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnXnYnZ(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-x, -z, +y)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnXnZY(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-x, -z, -y)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnXnZnY(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-y, +x, +z)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnYXZ(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-y, +x, -z)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnYXnZ(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-y, +z, +x)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnYZX(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-y, +z, -x)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnYZnX(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-y, -x, +z)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnYnXZ(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-y, -x, -z)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnYnXnZ(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-y, -z, +x)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnYnZX(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-y, -z, -x)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnYnZnX(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-z, +x, +y)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnZXY(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-z, +x, -y)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnZXnY(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-z, +y, +x)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnZYX(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-z, +y, -x)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnZYnX(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-z, -x, +y)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnZnXY(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-z, -x, -y)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnZnXnY(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-z, -y, +x)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnZnYX(@Mutated Double4x4 dest);

    /**
     * Apply a transformation that maps the axes {@code (x, y, z)} to {@code (-z, -y, -x)} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the mapping matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the mapping will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 mapnZnYnX(@Mutated Double4x4 dest);

    /**
     * Apply an oblique cabinet projection shear (compose with an orthographic projection for the
     * full transform) to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code O} the oblique shear matrix, then the new
     * matrix will be {@code M * O}. So when transforming a vector {@code v} with the new matrix by
     * using {@code M * O * v}, the oblique shear will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 obliqueCabinet(double angle, @Mutated Double4x4 dest);

    /**
     * Apply an oblique cavalier projection shear (compose with an orthographic projection for the
     * full transform) to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code O} the oblique shear matrix, then the new
     * matrix will be {@code M * O}. So when transforming a vector {@code v} with the new matrix by
     * using {@code M * O * v}, the oblique shear will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 obliqueCavalier(double angle, @Mutated Double4x4 dest);

    /**
     * Apply an oblique projection shear drawing the XZ plane true shape and the Y axis at
     * {@code angle}, depth {@code -y} (the military projection of a Y-up scene seen from below by a
     * right-handed orthographic projection, or the cavalier projection of a Z-up scene;
     * {@code obliquePlanometric} shows a Y-up scene from above; compose with an orthographic
     * projection for the full transform) to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code O} the oblique shear matrix, then the new
     * matrix will be {@code M * O}. So when transforming a vector {@code v} with the new matrix by
     * using {@code M * O * v}, the oblique shear will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle, in radians, at which the Y axis is drawn from the screen's +x axis
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 obliqueMilitary(double angle, @Mutated Double4x4 dest);

    /**
     * Apply a military (planometric) projection of a Y-up scene seen from above: the XZ plan true
     * shape turned by {@code angle}, verticals drawn straight up at full length, depth {@code y}
     * (compose with an orthographic projection for the full transform) to this matrix and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code O} the oblique shear matrix, then the new
     * matrix will be {@code M * O}. So when transforming a vector {@code v} with the new matrix by
     * using {@code M * O * v}, the oblique shear will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle, in radians, by which the XZ plan is turned counter-clockwise on
     *        screen
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 obliquePlanometric(double angle, @Mutated Double4x4 dest);

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: this matrix must be a perspective projection with finite clip planes; the normal
     * of {@code plane} must be non-zero; the constant of {@code plane} must be negative;
     * {@code plane} must not contain the far frustum corner its normal points to.
     *
     * @param plane the clip plane {@code (a, b, c, d)} in camera space, with the normal pointing
     *        into the visible half-space
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 obliqueZ(Double4R plane, Handedness handedness, DepthRange depthRange, @Mutated Double4x4 dest);

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 obliqueZ(double x, double y, double z, double w, Handedness handedness, DepthRange depthRange, @Mutated Double4x4 dest);

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: this matrix must be a perspective projection with finite clip planes; the normal
     * of {@code plane} must be non-zero; the constant of {@code plane} must be negative;
     * {@code plane} must not contain the far frustum corner its normal points to.
     *
     * @param plane the clip plane {@code (a, b, c, d)} in camera space, with the normal pointing
     *        into the visible half-space
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 obliqueZ(DoublePlaneR plane, Handedness handedness, DepthRange depthRange, @Mutated Double4x4 dest);

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 obliqueZ(Double4R plane, DepthRange depthRange, @Mutated Double4x4 dest) { return obliqueZ(plane, Handedness.RIGHT_HANDED, depthRange, dest); }

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 obliqueZ(double x, double y, double z, double w, DepthRange depthRange, @Mutated Double4x4 dest) { return obliqueZ(x, y, z, w, Handedness.RIGHT_HANDED, depthRange, dest); }

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 obliqueZ(Double4R plane, Handedness handedness, @Mutated Double4x4 dest) { return obliqueZ(plane, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 obliqueZ(double x, double y, double z, double w, Handedness handedness, @Mutated Double4x4 dest) { return obliqueZ(x, y, z, w, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 obliqueZ(Double4R plane, @Mutated Double4x4 dest) { return obliqueZ(plane, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 obliqueZ(double x, double y, double z, double w, @Mutated Double4x4 dest) { return obliqueZ(x, y, z, w, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 obliqueZ(DoublePlaneR plane, DepthRange depthRange, @Mutated Double4x4 dest) { return obliqueZ(plane, Handedness.RIGHT_HANDED, depthRange, dest); }

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 obliqueZ(DoublePlaneR plane, Handedness handedness, @Mutated Double4x4 dest) { return obliqueZ(plane, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Modify this perspective projection matrix to use an oblique near clip plane (the Lengyel
     * method): the near clip plane is replaced by the given clip plane in camera/view space, and
     * the far plane is adjusted to preserve depth precision.
     * <p>
     * The handedness and depth range must be the ones this perspective projection was built with:
     * they decide where the near and far clip planes sit in clip space and which way the projective
     * row points, which the closed-form solution depends on.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 obliqueZ(DoublePlaneR plane, @Mutated Double4x4 dest) { return obliqueZ(plane, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Apply an orthographic projection transformation to this matrix and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 ortho(double left, double right, double bottom, double top, double zNear, double zFar, Handedness handedness, DepthRange depthRange, @Mutated Double4x4 dest);

    /**
     * Apply an orthographic projection transformation to this matrix and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 ortho(double left, double right, double bottom, double top, double zNear, double zFar, DepthRange depthRange, @Mutated Double4x4 dest) { return ortho(left, right, bottom, top, zNear, zFar, Handedness.RIGHT_HANDED, depthRange, dest); }

    /**
     * Apply an orthographic projection transformation to this matrix and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 ortho(double left, double right, double bottom, double top, double zNear, double zFar, Handedness handedness, @Mutated Double4x4 dest) { return ortho(left, right, bottom, top, zNear, zFar, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Apply an orthographic projection transformation to this matrix and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 ortho(double left, double right, double bottom, double top, double zNear, double zFar, @Mutated Double4x4 dest) { return ortho(left, right, bottom, top, zNear, zFar, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Apply a 2D orthographic projection transformation to this matrix and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 ortho2D(double left, double right, double bottom, double top, Handedness handedness, DepthRange depthRange, @Mutated Double4x4 dest);

    /**
     * Apply a 2D orthographic projection transformation to this matrix and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 ortho2D(double left, double right, double bottom, double top, DepthRange depthRange, @Mutated Double4x4 dest) { return ortho2D(left, right, bottom, top, Handedness.RIGHT_HANDED, depthRange, dest); }

    /**
     * Apply a 2D orthographic projection transformation to this matrix and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 ortho2D(double left, double right, double bottom, double top, Handedness handedness, @Mutated Double4x4 dest) { return ortho2D(left, right, bottom, top, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Apply a 2D orthographic projection transformation to this matrix and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 ortho2D(double left, double right, double bottom, double top, @Mutated Double4x4 dest) { return ortho2D(left, right, bottom, top, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

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
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Valid input: this matrix must be the inverse of a view-projection matrix with finite clip
     * planes; {@code view} must be affine; the frustum slice must have a non-zero extent along each
     * axis of {@code view}.
     *
     * @param view the light view transformation (must be affine)
     * @param handedness the handedness of the coordinate system to map into
     * @param depthRange the clip-space depth range the projection maps onto
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 orthoCrop(Double4x4R view, Handedness handedness, DepthRange depthRange, @Mutated Double4x4 dest);

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
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness}.
     * <p>
     * Valid input: this matrix must be the inverse of a view-projection matrix with finite clip
     * planes; {@code view} must be affine; the frustum slice must have a non-zero extent along each
     * axis of {@code view}.
     *
     * @param view the light view transformation (must be affine)
     * @param depthRange the clip-space depth range the projection maps onto
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 orthoCrop(Double4x4R view, DepthRange depthRange, @Mutated Double4x4 dest) { return orthoCrop(view, Handedness.RIGHT_HANDED, depthRange, dest); }

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
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be the inverse of a view-projection matrix with finite clip
     * planes; {@code view} must be affine; the frustum slice must have a non-zero extent along each
     * axis of {@code view}.
     *
     * @param view the light view transformation (must be affine)
     * @param handedness the handedness of the coordinate system to map into
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 orthoCrop(Double4x4R view, Handedness handedness, @Mutated Double4x4 dest) { return orthoCrop(view, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

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
     * The result is stored in {@code dest}; {@code this} is not modified.
     * <p>
     * Uses {@link Handedness#RIGHT_HANDED} for {@code handedness} and
     * {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be the inverse of a view-projection matrix with finite clip
     * planes; {@code view} must be affine; the frustum slice must have a non-zero extent along each
     * axis of {@code view}.
     *
     * @param view the light view transformation (must be affine)
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 orthoCrop(Double4x4R view, @Mutated Double4x4 dest) { return orthoCrop(view, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

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
     * The result is stored in {@code dest}; {@code this} is not modified.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 orthoCrop(Double4x4R view, double minZ, double maxZ, Handedness handedness, DepthRange depthRange, @Mutated Double4x4 dest);

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
     * The result is stored in {@code dest}; {@code this} is not modified.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 orthoCrop(Double4x4R view, double minZ, double maxZ, DepthRange depthRange, @Mutated Double4x4 dest) { return orthoCrop(view, minZ, maxZ, Handedness.RIGHT_HANDED, depthRange, dest); }

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
     * The result is stored in {@code dest}; {@code this} is not modified.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 orthoCrop(Double4x4R view, double minZ, double maxZ, Handedness handedness, @Mutated Double4x4 dest) { return orthoCrop(view, minZ, maxZ, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

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
     * The result is stored in {@code dest}; {@code this} is not modified.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 orthoCrop(Double4x4R view, double minZ, double maxZ, @Mutated Double4x4 dest) { return orthoCrop(view, minZ, maxZ, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Apply a symmetric perspective projection frustum transformation to this matrix and store the
     * result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 perspective(double fovy, double aspect, double near, double far, Handedness handedness, DepthRange depthRange, @Mutated Double4x4 dest);

    /**
     * Apply a symmetric perspective projection frustum transformation to this matrix and store the
     * result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 perspective(double fovy, double aspect, double near, double far, DepthRange depthRange, @Mutated Double4x4 dest) { return perspective(fovy, aspect, near, far, Handedness.RIGHT_HANDED, depthRange, dest); }

    /**
     * Apply a symmetric perspective projection frustum transformation to this matrix and store the
     * result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 perspective(double fovy, double aspect, double near, double far, Handedness handedness, @Mutated Double4x4 dest) { return perspective(fovy, aspect, near, far, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Apply a symmetric perspective projection frustum transformation to this matrix and store the
     * result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 perspective(double fovy, double aspect, double near, double far, @Mutated Double4x4 dest) { return perspective(fovy, aspect, near, far, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Apply a perspective projection frustum transformation for the given vertical field-of-view
     * range to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 perspectiveFovRange(double angleMin, double angleMax, double aspect, double near, double far, Handedness handedness, DepthRange depthRange, @Mutated Double4x4 dest);

    /**
     * Apply a perspective projection frustum transformation for the given vertical field-of-view
     * range to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 perspectiveFovRange(double angleMin, double angleMax, double aspect, double near, double far, DepthRange depthRange, @Mutated Double4x4 dest) { return perspectiveFovRange(angleMin, angleMax, aspect, near, far, Handedness.RIGHT_HANDED, depthRange, dest); }

    /**
     * Apply a perspective projection frustum transformation for the given vertical field-of-view
     * range to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 perspectiveFovRange(double angleMin, double angleMax, double aspect, double near, double far, Handedness handedness, @Mutated Double4x4 dest) { return perspectiveFovRange(angleMin, angleMax, aspect, near, far, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Apply a perspective projection frustum transformation for the given vertical field-of-view
     * range to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 perspectiveFovRange(double angleMin, double angleMax, double aspect, double near, double far, @Mutated Double4x4 dest) { return perspectiveFovRange(angleMin, angleMax, aspect, near, far, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Change the near and far clip plane distances of an existing perspective frustum
     * transformation and leave everything else unchanged.
     * <p>
     * Only meaningful when this matrix is a perspective projection obtained via {@code perspective}
     * or {@code frustum}: all elements except the depth row are copied through, and that row is
     * recomputed for the new {@code near}/{@code far}. Pass the same {@code handedness} and
     * {@code depthRange} the matrix was built with.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 perspectiveFrustumSlice(double near, double far, Handedness handedness, DepthRange depthRange, @Mutated Double4x4 dest);

    /**
     * Change the near and far clip plane distances of an existing perspective frustum
     * transformation and leave everything else unchanged.
     * <p>
     * Only meaningful when this matrix is a perspective projection obtained via {@code perspective}
     * or {@code frustum}: all elements except the depth row are copied through, and that row is
     * recomputed for the new {@code near}/{@code far}. Pass the same {@code handedness} and
     * {@code depthRange} the matrix was built with.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 perspectiveFrustumSlice(double near, double far, DepthRange depthRange, @Mutated Double4x4 dest) { return perspectiveFrustumSlice(near, far, Handedness.RIGHT_HANDED, depthRange, dest); }

    /**
     * Change the near and far clip plane distances of an existing perspective frustum
     * transformation and leave everything else unchanged.
     * <p>
     * Only meaningful when this matrix is a perspective projection obtained via {@code perspective}
     * or {@code frustum}: all elements except the depth row are copied through, and that row is
     * recomputed for the new {@code near}/{@code far}. Pass the same {@code handedness} and
     * {@code depthRange} the matrix was built with.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 perspectiveFrustumSlice(double near, double far, Handedness handedness, @Mutated Double4x4 dest) { return perspectiveFrustumSlice(near, far, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Change the near and far clip plane distances of an existing perspective frustum
     * transformation and leave everything else unchanged.
     * <p>
     * Only meaningful when this matrix is a perspective projection obtained via {@code perspective}
     * or {@code frustum}: all elements except the depth row are copied through, and that row is
     * recomputed for the new {@code near}/{@code far}. Pass the same {@code handedness} and
     * {@code depthRange} the matrix was built with.
     * <p>
     * The result is stored in {@code dest}; {@code this} is not modified.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 perspectiveFrustumSlice(double near, double far, @Mutated Double4x4 dest) { return perspectiveFrustumSlice(near, far, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Apply an asymmetric perspective projection frustum transformation with the frustum sides
     * given as view-axis angles to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 perspectiveOffCenterFov(double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far, Handedness handedness, DepthRange depthRange, @Mutated Double4x4 dest);

    /**
     * Apply an asymmetric perspective projection frustum transformation with the frustum sides
     * given as view-axis angles to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 perspectiveOffCenterFov(double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far, DepthRange depthRange, @Mutated Double4x4 dest) { return perspectiveOffCenterFov(angleLeft, angleRight, angleDown, angleUp, near, far, Handedness.RIGHT_HANDED, depthRange, dest); }

    /**
     * Apply an asymmetric perspective projection frustum transformation with the frustum sides
     * given as view-axis angles to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 perspectiveOffCenterFov(double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far, Handedness handedness, @Mutated Double4x4 dest) { return perspectiveOffCenterFov(angleLeft, angleRight, angleDown, angleUp, near, far, handedness, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Apply an asymmetric perspective projection frustum transformation with the frustum sides
     * given as view-axis angles to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    default Double4x4 perspectiveOffCenterFov(double angleLeft, double angleRight, double angleDown, double angleUp, double near, double far, @Mutated Double4x4 dest) { return perspectiveOffCenterFov(angleLeft, angleRight, angleDown, angleUp, near, far, Handedness.RIGHT_HANDED, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Apply a picking transformation restricting the view to the given picking region to this
     * matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 pickMatrix(double centerX, double centerY, double deltaX, double deltaY, double vpX, double vpY, double vpW, double vpH, @Mutated Double4x4 dest);

    /**
     * Pre-multiply the rotation {@code rot} about the pivot point {@code pivot} onto this matrix
     * and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preRotateAround(DoubleQuatR rot, Double3R pivot, @Mutated Double4x4 dest);

    /**
     * Pre-multiply the rotation ({@code rotX}, {@code rotY}, {@code rotZ}, {@code rotW}) about the
     * pivot point ({@code pivotX}, {@code pivotY}, {@code pivotZ}) onto this matrix and store the
     * result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preRotateAround(double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ, @Mutated Double4x4 dest);

    /**
     * Pre-multiply a rotation of {@code angle} radians about the axis {@code axis} onto this matrix
     * and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preRotateAxis(double angle, Double3R axis, @Mutated Double4x4 dest);

    /**
     * Pre-multiply a rotation of {@code angle} radians about the axis ({@code x}, {@code y},
     * {@code z}) onto this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preRotateAxis(double angle, double x, double y, double z, @Mutated Double4x4 dest);

    /**
     * Pre-multiply the rotation represented by the quaternion {@code q} onto this matrix and store
     * the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the rotation to apply
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preRotateQuat(DoubleQuatR q, @Mutated Double4x4 dest);

    /**
     * Pre-multiply the rotation represented by the quaternion ({@code x}, {@code y}, {@code z},
     * {@code w}) onto this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preRotateQuat(double x, double y, double z, double w, @Mutated Double4x4 dest);

    /**
     * Pre-multiply a rotation of {@code angle} radians about the X axis onto this matrix and store
     * the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preRotateX(double angle, @Mutated Double4x4 dest);

    /**
     * Pre-multiply a rotation of {@code angle} radians about the Y axis onto this matrix and store
     * the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preRotateY(double angle, @Mutated Double4x4 dest);

    /**
     * Pre-multiply a rotation of {@code angle} radians about the Z axis onto this matrix and store
     * the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preRotateZ(double angle, @Mutated Double4x4 dest);

    /**
     * Pre-multiply a scaling by {@code v} onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code p} with the new matrix by using
     * {@code S * M * p}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preScale(Double3R v, @Mutated Double4x4 dest);

    /**
     * Pre-multiply a scaling by ({@code x}, {@code y}, {@code z}) onto this matrix and store the
     * result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preScale(double x, double y, double z, @Mutated Double4x4 dest);

    /**
     * Pre-multiply a scaling by {@code s} onto this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preScale(double s, @Mutated Double4x4 dest);

    /**
     * Pre-multiply a scaling by {@code s} about the pivot point {@code pivot} onto this matrix and
     * store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preScaleAround(double s, Double3R pivot, @Mutated Double4x4 dest);

    /**
     * Pre-multiply a scaling by {@code s} about the pivot point ({@code x}, {@code y}, {@code z})
     * onto this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preScaleAround(double s, double x, double y, double z, @Mutated Double4x4 dest);

    /**
     * Pre-multiply a scaling by {@code s} about the pivot point {@code pivot} onto this matrix and
     * store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the scale factors
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preScaleAround(Double3R s, Double3R pivot, @Mutated Double4x4 dest);

    /**
     * Pre-multiply a scaling by ({@code sX}, {@code sY}, {@code sZ}) about the pivot point
     * ({@code pivotX}, {@code pivotY}, {@code pivotZ}) onto this matrix and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preScaleAround(double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ, @Mutated Double4x4 dest);

    /**
     * Pre-multiply a translation by {@code v} onto this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code T * M}. So when transforming a vector {@code p} with the new matrix by using
     * {@code T * M * p}, the translation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preTranslate(Double3R v, @Mutated Double4x4 dest);

    /**
     * Pre-multiply a translation by ({@code x}, {@code y}, {@code z}) onto this matrix and store
     * the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 preTranslate(double x, double y, double z, @Mutated Double4x4 dest);

    /**
     * Project the given position onto window coordinates using this matrix and the given viewport
     * and store the result in {@code dest}.
     * <p>
     * Valid input: {@code obj} must not be mapped to {@code w = 0} by this matrix.
     *
     * @param obj the object-space position to project
     * @param viewport the viewport {@code [x, y, width, height]}
     * @param depthRange the clip-space depth range the projection maps onto
     * @param dest will hold the result
     * @return dest
     */
    Double3 project(Double3R obj, Double4R viewport, DepthRange depthRange, @Mutated Double3 dest);

    /**
     * Project the given position onto window coordinates using this matrix and the given viewport
     * and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (objX, objY, objZ)} must not be mapped to {@code w = 0} by this matrix.
     *
     * @param objX the {@code x} component of the object-space position {@code (objX, objY, objZ)}
     *        to project
     * @param objY the {@code y} component of the object-space position {@code (objX, objY, objZ)}
     *        to project
     * @param objZ the {@code z} component of the object-space position {@code (objX, objY, objZ)}
     *        to project
     * @param viewportX the {@code x} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportY the {@code y} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportZ the {@code z} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportW the {@code w} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param depthRange the clip-space depth range the projection maps onto
     * @param dest will hold the result
     * @return dest
     */
    Double3 project(double objX, double objY, double objZ, double viewportX, double viewportY, double viewportZ, double viewportW, DepthRange depthRange, @Mutated Double3 dest);

    /**
     * Project the given position onto window coordinates using this matrix and the given viewport
     * and store the result in {@code dest}.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code obj} must not be mapped to {@code w = 0} by this matrix.
     *
     * @param obj the object-space position to project
     * @param viewport the viewport {@code [x, y, width, height]}
     * @param dest will hold the result
     * @return dest
     */
    default Double3 project(Double3R obj, Double4R viewport, @Mutated Double3 dest) { return project(obj, viewport, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Project the given position onto window coordinates using this matrix and the given viewport
     * and store the result in {@code dest}.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: {@code (objX, objY, objZ)} must not be mapped to {@code w = 0} by this matrix.
     *
     * @param objX the {@code x} component of the object-space position {@code (objX, objY, objZ)}
     *        to project
     * @param objY the {@code y} component of the object-space position {@code (objX, objY, objZ)}
     *        to project
     * @param objZ the {@code z} component of the object-space position {@code (objX, objY, objZ)}
     *        to project
     * @param viewportX the {@code x} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportY the {@code y} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportZ the {@code z} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportW the {@code w} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param dest will hold the result
     * @return dest
     */
    default Double3 project(double objX, double objY, double objZ, double viewportX, double viewportY, double viewportZ, double viewportW, @Mutated Double3 dest) { return project(objX, objY, objZ, viewportX, viewportY, viewportZ, viewportW, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Apply a reflection about the plane through the origin with the normal {@code normal} to this
     * matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the reflection matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the reflection will be applied first.
     * <p>
     * Valid input: {@code normal} must have unit length.
     *
     * @param normal the normal of the plane to reflect about
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 reflect(Double3R normal, @Mutated Double4x4 dest);

    /**
     * Apply a reflection about the plane through the origin with the normal ({@code x}, {@code y},
     * {@code z}) to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 reflect(double x, double y, double z, @Mutated Double4x4 dest);

    /**
     * Apply the rotation {@code rot} about the pivot point {@code pivot} to this matrix and store
     * the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateAround(DoubleQuatR rot, Double3R pivot, @Mutated Double4x4 dest);

    /**
     * Apply the rotation ({@code rotX}, {@code rotY}, {@code rotZ}, {@code rotW}) about the pivot
     * point ({@code pivotX}, {@code pivotY}, {@code pivotZ}) to this matrix and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateAround(double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ, @Mutated Double4x4 dest);

    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this matrix and
     * store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateAxis(double angle, Double3R axis, @Mutated Double4x4 dest);

    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code x}, {@code y}, {@code z}) to
     * this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateAxis(double angle, double x, double y, double z, @Mutated Double4x4 dest);

    /**
     * Apply the rotation represented by the quaternion {@code q} to this matrix and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the rotation to apply
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateQuat(DoubleQuatR q, @Mutated Double4x4 dest);

    /**
     * Apply the rotation represented by the quaternion ({@code x}, {@code y}, {@code z}, {@code w})
     * to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateQuat(double x, double y, double z, double w, @Mutated Double4x4 dest);

    /**
     * Apply a rotation of {@code angle} radians about the X axis to this matrix and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateX(double angle, @Mutated Double4x4 dest);

    /**
     * Apply a rotation of 180 degrees about the X axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateX180(@Mutated Double4x4 dest);

    /**
     * Apply a rotation of 270 degrees about the X axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateX270(@Mutated Double4x4 dest);

    /**
     * Apply a rotation of 90 degrees about the X axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateX90(@Mutated Double4x4 dest);

    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X), to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateXYZ(double angleX, double angleY, double angleZ, @Mutated Double4x4 dest);

    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X), to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateXZY(double angleX, double angleZ, double angleY, @Mutated Double4x4 dest);

    /**
     * Apply a rotation of -180 degrees about the X axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateXn180(@Mutated Double4x4 dest);

    /**
     * Apply a rotation of -270 degrees about the X axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateXn270(@Mutated Double4x4 dest);

    /**
     * Apply a rotation of -90 degrees about the X axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateXn90(@Mutated Double4x4 dest);

    /**
     * Apply a rotation of {@code angle} radians about the Y axis to this matrix and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateY(double angle, @Mutated Double4x4 dest);

    /**
     * Apply a rotation of 180 degrees about the Y axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateY180(@Mutated Double4x4 dest);

    /**
     * Apply a rotation of 270 degrees about the Y axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateY270(@Mutated Double4x4 dest);

    /**
     * Apply a rotation of 90 degrees about the Y axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateY90(@Mutated Double4x4 dest);

    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y), to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateYXZ(double angleY, double angleX, double angleZ, @Mutated Double4x4 dest);

    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y), to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateYZX(double angleY, double angleZ, double angleX, @Mutated Double4x4 dest);

    /**
     * Apply a rotation of -180 degrees about the Y axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateYn180(@Mutated Double4x4 dest);

    /**
     * Apply a rotation of -270 degrees about the Y axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateYn270(@Mutated Double4x4 dest);

    /**
     * Apply a rotation of -90 degrees about the Y axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateYn90(@Mutated Double4x4 dest);

    /**
     * Apply a rotation of {@code angle} radians about the Z axis to this matrix and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateZ(double angle, @Mutated Double4x4 dest);

    /**
     * Apply a rotation of 180 degrees about the Z axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateZ180(@Mutated Double4x4 dest);

    /**
     * Apply a rotation of 270 degrees about the Z axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateZ270(@Mutated Double4x4 dest);

    /**
     * Apply a rotation of 90 degrees about the Z axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateZ90(@Mutated Double4x4 dest);

    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z), to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateZXY(double angleZ, double angleX, double angleY, @Mutated Double4x4 dest);

    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z), to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateZYX(double angleZ, double angleY, double angleX, @Mutated Double4x4 dest);

    /**
     * Apply a rotation of -180 degrees about the Z axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateZn180(@Mutated Double4x4 dest);

    /**
     * Apply a rotation of -270 degrees about the Z axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateZn270(@Mutated Double4x4 dest);

    /**
     * Apply a rotation of -90 degrees about the Z axis to this matrix and store the result in
     * {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 rotateZn90(@Mutated Double4x4 dest);

    /**
     * Apply a scaling by {@code v} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code p} with the new matrix by using
     * {@code M * S * p}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 scale(Double3R v, @Mutated Double4x4 dest);

    /**
     * Apply a scaling by ({@code x}, {@code y}, {@code z}) to this matrix and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 scale(double x, double y, double z, @Mutated Double4x4 dest);

    /**
     * Apply a scaling by {@code s} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 scale(double s, @Mutated Double4x4 dest);

    /**
     * Apply a scaling by {@code s} about the pivot point {@code pivot} to this matrix and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 scaleAround(double s, Double3R pivot, @Mutated Double4x4 dest);

    /**
     * Apply a scaling by {@code s} about the pivot point ({@code x}, {@code y}, {@code z}) to this
     * matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 scaleAround(double s, double x, double y, double z, @Mutated Double4x4 dest);

    /**
     * Apply a scaling by {@code s} about the pivot point {@code pivot} to this matrix and store the
     * result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the scale factors
     * @param pivot the pivot point
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 scaleAround(Double3R s, Double3R pivot, @Mutated Double4x4 dest);

    /**
     * Apply a scaling by ({@code sX}, {@code sY}, {@code sZ}) about the pivot point
     * ({@code pivotX}, {@code pivotY}, {@code pivotZ}) to this matrix and store the result in
     * {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 scaleAround(double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ, @Mutated Double4x4 dest);

    /**
     * Apply a projection shadow transformation that projects geometry onto the given plane, cast
     * from the given light source (a direction when {@code light.w} is {@code 0}, a position when
     * it is {@code 1}) to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 shadow(Double4R light, Double4R plane, @Mutated Double4x4 dest);

    /**
     * Apply a projection shadow transformation that projects geometry onto the given plane, cast
     * from the given light source (a direction when {@code lightW} is {@code 0}, a position when it
     * is {@code 1}) to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 shadow(double lightX, double lightY, double lightZ, double lightW, double planeX, double planeY, double planeZ, double planeW, @Mutated Double4x4 dest);

    /**
     * Apply a shearing transformation to this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 shear(double xy, double xz, double yx, double yz, double zx, double zy, @Mutated Double4x4 dest);

    /**
     * Apply a transformation that restricts the view to one tile of a regular grid to this matrix
     * and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 tile(double x, double y, double w, double h, @Mutated Double4x4 dest);

    /**
     * Apply a translation by {@code v} to this matrix and store the result in {@code dest}.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code p} with the new matrix by using
     * {@code M * T * p}, the translation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 translate(Double3R v, @Mutated Double4x4 dest);

    /**
     * Apply a translation by ({@code x}, {@code y}, {@code z}) to this matrix and store the result
     * in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 translate(double x, double y, double z, @Mutated Double4x4 dest);

    /**
     * Pre-multiply a trapezoid-crop transformation mapping the given trapezoid onto the
     * {@code [-1, +1]} clip square onto this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 trapezoidCrop(Double2R p0, Double2R p1, Double2R p2, Double2R p3, @Mutated Double4x4 dest);

    /**
     * Pre-multiply a trapezoid-crop transformation mapping the given trapezoid onto the
     * {@code [-1, +1]} clip square onto this matrix and store the result in {@code dest}.
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
     * @param dest will hold the result
     * @return dest
     */
    Double4x4 trapezoidCrop(double p0X, double p0Y, double p1X, double p1Y, double p2X, double p2Y, double p3X, double p3Y, @Mutated Double4x4 dest);

    /**
     * Unproject the given window coordinates into object space using this matrix (which is inverted
     * internally) and the given viewport and store the result in {@code dest}.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix with finite clip
     * planes; each component of the size of {@code viewport} must be non-zero; {@code winCoords}
     * must lie within {@code viewport}.
     *
     * @param winCoords the window coordinates {@code (x, y, depth)} to unproject
     * @param viewport the viewport {@code [x, y, width, height]}
     * @param depthRange the clip-space depth range the projection maps onto
     * @param dest will hold the result
     * @return dest
     */
    Double3 unproject(Double3R winCoords, Double4R viewport, DepthRange depthRange, @Mutated Double3 dest);

    /**
     * Unproject the given window coordinates into object space using this matrix (which is inverted
     * internally) and the given viewport and store the result in {@code dest}.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix with finite clip
     * planes; each component of the size of {@code (viewportX, viewportY, viewportZ, viewportW)}
     * must be non-zero; {@code (winCoordsX, winCoordsY, winCoordsZ)} must lie within
     * {@code (viewportX, viewportY, viewportZ, viewportW)}.
     *
     * @param winCoordsX the {@code x} component of the window coordinates {@code (x, y, depth)} to
     *        unproject
     * @param winCoordsY the {@code y} component of the window coordinates {@code (x, y, depth)} to
     *        unproject
     * @param winCoordsZ the {@code z} component of the window coordinates {@code (x, y, depth)} to
     *        unproject
     * @param viewportX the {@code x} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportY the {@code y} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportZ the {@code z} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportW the {@code w} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param depthRange the clip-space depth range the projection maps onto
     * @param dest will hold the result
     * @return dest
     */
    Double3 unproject(double winCoordsX, double winCoordsY, double winCoordsZ, double viewportX, double viewportY, double viewportZ, double viewportW, DepthRange depthRange, @Mutated Double3 dest);

    /**
     * Unproject the given window coordinates into object space using this matrix (which is inverted
     * internally) and the given viewport and store the result in {@code dest}.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix with finite clip
     * planes; each component of the size of {@code viewport} must be non-zero; {@code winCoords}
     * must lie within {@code viewport}.
     *
     * @param winCoords the window coordinates {@code (x, y, depth)} to unproject
     * @param viewport the viewport {@code [x, y, width, height]}
     * @param dest will hold the result
     * @return dest
     */
    default Double3 unproject(Double3R winCoords, Double4R viewport, @Mutated Double3 dest) { return unproject(winCoords, viewport, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Unproject the given window coordinates into object space using this matrix (which is inverted
     * internally) and the given viewport and store the result in {@code dest}.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix with finite clip
     * planes; each component of the size of {@code (viewportX, viewportY, viewportZ, viewportW)}
     * must be non-zero; {@code (winCoordsX, winCoordsY, winCoordsZ)} must lie within
     * {@code (viewportX, viewportY, viewportZ, viewportW)}.
     *
     * @param winCoordsX the {@code x} component of the window coordinates {@code (x, y, depth)} to
     *        unproject
     * @param winCoordsY the {@code y} component of the window coordinates {@code (x, y, depth)} to
     *        unproject
     * @param winCoordsZ the {@code z} component of the window coordinates {@code (x, y, depth)} to
     *        unproject
     * @param viewportX the {@code x} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportY the {@code y} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportZ the {@code z} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportW the {@code w} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param dest will hold the result
     * @return dest
     */
    default Double3 unproject(double winCoordsX, double winCoordsY, double winCoordsZ, double viewportX, double viewportY, double viewportZ, double viewportW, @Mutated Double3 dest) { return unproject(winCoordsX, winCoordsY, winCoordsZ, viewportX, viewportY, viewportZ, viewportW, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Unproject the given window coordinates into object space using this matrix (which is assumed
     * to be the inverse of a projection-view matrix) and the given viewport and store the result in
     * {@code dest}.
     * <p>
     * Valid input: this matrix must be the inverse of a view-projection matrix with finite clip
     * planes; each component of the size of {@code viewport} must be non-zero; {@code winCoords}
     * must lie within {@code viewport}.
     *
     * @param winCoords the window coordinates {@code (x, y, depth)} to unproject
     * @param viewport the viewport {@code [x, y, width, height]}
     * @param depthRange the clip-space depth range the projection maps onto
     * @param dest will hold the result
     * @return dest
     */
    Double3 unprojectInv(Double3R winCoords, Double4R viewport, DepthRange depthRange, @Mutated Double3 dest);

    /**
     * Unproject the given window coordinates into object space using this matrix (which is assumed
     * to be the inverse of a projection-view matrix) and the given viewport and store the result in
     * {@code dest}.
     * <p>
     * Valid input: this matrix must be the inverse of a view-projection matrix with finite clip
     * planes; each component of the size of {@code (viewportX, viewportY, viewportZ, viewportW)}
     * must be non-zero; {@code (winCoordsX, winCoordsY, winCoordsZ)} must lie within
     * {@code (viewportX, viewportY, viewportZ, viewportW)}.
     *
     * @param winCoordsX the {@code x} component of the window coordinates {@code (x, y, depth)} to
     *        unproject
     * @param winCoordsY the {@code y} component of the window coordinates {@code (x, y, depth)} to
     *        unproject
     * @param winCoordsZ the {@code z} component of the window coordinates {@code (x, y, depth)} to
     *        unproject
     * @param viewportX the {@code x} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportY the {@code y} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportZ the {@code z} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportW the {@code w} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param depthRange the clip-space depth range the projection maps onto
     * @param dest will hold the result
     * @return dest
     */
    Double3 unprojectInv(double winCoordsX, double winCoordsY, double winCoordsZ, double viewportX, double viewportY, double viewportZ, double viewportW, DepthRange depthRange, @Mutated Double3 dest);

    /**
     * Unproject the given window coordinates into object space using this matrix (which is assumed
     * to be the inverse of a projection-view matrix) and the given viewport and store the result in
     * {@code dest}.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be the inverse of a view-projection matrix with finite clip
     * planes; each component of the size of {@code viewport} must be non-zero; {@code winCoords}
     * must lie within {@code viewport}.
     *
     * @param winCoords the window coordinates {@code (x, y, depth)} to unproject
     * @param viewport the viewport {@code [x, y, width, height]}
     * @param dest will hold the result
     * @return dest
     */
    default Double3 unprojectInv(Double3R winCoords, Double4R viewport, @Mutated Double3 dest) { return unprojectInv(winCoords, viewport, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Unproject the given window coordinates into object space using this matrix (which is assumed
     * to be the inverse of a projection-view matrix) and the given viewport and store the result in
     * {@code dest}.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be the inverse of a view-projection matrix with finite clip
     * planes; each component of the size of {@code (viewportX, viewportY, viewportZ, viewportW)}
     * must be non-zero; {@code (winCoordsX, winCoordsY, winCoordsZ)} must lie within
     * {@code (viewportX, viewportY, viewportZ, viewportW)}.
     *
     * @param winCoordsX the {@code x} component of the window coordinates {@code (x, y, depth)} to
     *        unproject
     * @param winCoordsY the {@code y} component of the window coordinates {@code (x, y, depth)} to
     *        unproject
     * @param winCoordsZ the {@code z} component of the window coordinates {@code (x, y, depth)} to
     *        unproject
     * @param viewportX the {@code x} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportY the {@code y} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportZ the {@code z} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportW the {@code w} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param dest will hold the result
     * @return dest
     */
    default Double3 unprojectInv(double winCoordsX, double winCoordsY, double winCoordsZ, double viewportX, double viewportY, double viewportZ, double viewportW, @Mutated Double3 dest) { return unprojectInv(winCoordsX, winCoordsY, winCoordsZ, viewportX, viewportY, viewportZ, viewportW, DepthRange.NEGATIVE_ONE_TO_ONE, dest); }

    /**
     * Unproject the given window coordinates into a ray in object space using this matrix (which is
     * assumed to be the inverse of a projection-view matrix) and the given viewport, storing the
     * ray origin in {@code rayOrigin} and the ray direction in {@code rayDir}.
     * <p>
     * A projection whose far plane is at infinity is supported: the far point is then a point at
     * infinity and the ray direction is taken from it as a finite direction. A far plane whose
     * homogeneous w is at most {@code 2^-40} times the near plane's is treated as being at
     * infinity. A projection whose near plane is at infinity (the reversed-Z projection) turns the
     * ray around: it then starts on the far plane and points toward the infinite near plane, away
     * from the camera - the opposite sense of a finite near plane's near-to-far ray. A near plane
     * whose homogeneous w is at most {@code 2^-40} times the far plane's counts as infinite.
     * <p>
     * Valid input: this matrix must be the inverse of a view-projection matrix; each component of
     * the size of {@code viewport} must be non-zero; {@code winCoords} must lie within
     * {@code viewport}.
     *
     * @param winCoords the window coordinates {@code (x, y)} to unproject
     * @param viewport the viewport {@code [x, y, width, height]}
     * @param depthRange the clip-space depth range the projection maps onto
     * @param rayOrigin will hold the origin of the ray
     * @param rayDir will hold the direction of the ray
     * @return this
     */
    Double4x4 unprojectInvRay(Double2R winCoords, Double4R viewport, DepthRange depthRange, @Mutated Double3 rayOrigin, @Mutated Double3 rayDir);

    /**
     * Unproject the given window coordinates into a ray in object space using this matrix (which is
     * assumed to be the inverse of a projection-view matrix) and the given viewport, storing the
     * ray origin in {@code rayOrigin} and the ray direction in {@code rayDir}.
     * <p>
     * A projection whose far plane is at infinity is supported: the far point is then a point at
     * infinity and the ray direction is taken from it as a finite direction. A far plane whose
     * homogeneous w is at most {@code 2^-40} times the near plane's is treated as being at
     * infinity. A projection whose near plane is at infinity (the reversed-Z projection) turns the
     * ray around: it then starts on the far plane and points toward the infinite near plane, away
     * from the camera - the opposite sense of a finite near plane's near-to-far ray. A near plane
     * whose homogeneous w is at most {@code 2^-40} times the far plane's counts as infinite.
     * <p>
     * Valid input: this matrix must be the inverse of a view-projection matrix; each component of
     * the size of {@code (viewportX, viewportY, viewportZ, viewportW)} must be non-zero;
     * {@code (winCoordsX, winCoordsY)} must lie within
     * {@code (viewportX, viewportY, viewportZ, viewportW)}.
     *
     * @param winCoordsX the {@code x} component of the window coordinates {@code (x, y)} to
     *        unproject
     * @param winCoordsY the {@code y} component of the window coordinates {@code (x, y)} to
     *        unproject
     * @param viewportX the {@code x} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportY the {@code y} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportZ the {@code z} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportW the {@code w} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param depthRange the clip-space depth range the projection maps onto
     * @param rayOrigin will hold the origin of the ray
     * @param rayDir will hold the direction of the ray
     * @return this
     */
    Double4x4 unprojectInvRay(double winCoordsX, double winCoordsY, double viewportX, double viewportY, double viewportZ, double viewportW, DepthRange depthRange, @Mutated Double3 rayOrigin, @Mutated Double3 rayDir);

    /**
     * Unproject the given window coordinates into a ray in object space using this matrix (which is
     * assumed to be the inverse of a projection-view matrix) and the given viewport, storing the
     * ray origin in {@code rayOrigin} and the ray direction in {@code rayDir}.
     * <p>
     * A projection whose far plane is at infinity is supported: the far point is then a point at
     * infinity and the ray direction is taken from it as a finite direction. A far plane whose
     * homogeneous w is at most {@code 2^-40} times the near plane's is treated as being at
     * infinity. A projection whose near plane is at infinity (the reversed-Z projection) turns the
     * ray around: it then starts on the far plane and points toward the infinite near plane, away
     * from the camera - the opposite sense of a finite near plane's near-to-far ray. A near plane
     * whose homogeneous w is at most {@code 2^-40} times the far plane's counts as infinite.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be the inverse of a view-projection matrix; each component of
     * the size of {@code viewport} must be non-zero; {@code winCoords} must lie within
     * {@code viewport}.
     *
     * @param winCoords the window coordinates {@code (x, y)} to unproject
     * @param viewport the viewport {@code [x, y, width, height]}
     * @param rayOrigin will hold the origin of the ray
     * @param rayDir will hold the direction of the ray
     * @return this
     */
    default Double4x4 unprojectInvRay(Double2R winCoords, Double4R viewport, @Mutated Double3 rayOrigin, @Mutated Double3 rayDir) { return unprojectInvRay(winCoords, viewport, DepthRange.NEGATIVE_ONE_TO_ONE, rayOrigin, rayDir); }

    /**
     * Unproject the given window coordinates into a ray in object space using this matrix (which is
     * assumed to be the inverse of a projection-view matrix) and the given viewport, storing the
     * ray origin in {@code rayOrigin} and the ray direction in {@code rayDir}.
     * <p>
     * A projection whose far plane is at infinity is supported: the far point is then a point at
     * infinity and the ray direction is taken from it as a finite direction. A far plane whose
     * homogeneous w is at most {@code 2^-40} times the near plane's is treated as being at
     * infinity. A projection whose near plane is at infinity (the reversed-Z projection) turns the
     * ray around: it then starts on the far plane and points toward the infinite near plane, away
     * from the camera - the opposite sense of a finite near plane's near-to-far ray. A near plane
     * whose homogeneous w is at most {@code 2^-40} times the far plane's counts as infinite.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be the inverse of a view-projection matrix; each component of
     * the size of {@code (viewportX, viewportY, viewportZ, viewportW)} must be non-zero;
     * {@code (winCoordsX, winCoordsY)} must lie within
     * {@code (viewportX, viewportY, viewportZ, viewportW)}.
     *
     * @param winCoordsX the {@code x} component of the window coordinates {@code (x, y)} to
     *        unproject
     * @param winCoordsY the {@code y} component of the window coordinates {@code (x, y)} to
     *        unproject
     * @param viewportX the {@code x} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportY the {@code y} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportZ the {@code z} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportW the {@code w} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param rayOrigin will hold the origin of the ray
     * @param rayDir will hold the direction of the ray
     * @return this
     */
    default Double4x4 unprojectInvRay(double winCoordsX, double winCoordsY, double viewportX, double viewportY, double viewportZ, double viewportW, @Mutated Double3 rayOrigin, @Mutated Double3 rayDir) { return unprojectInvRay(winCoordsX, winCoordsY, viewportX, viewportY, viewportZ, viewportW, DepthRange.NEGATIVE_ONE_TO_ONE, rayOrigin, rayDir); }

    /**
     * Unproject the given window coordinates into a ray in object space using this matrix (which is
     * inverted internally) and the given viewport, storing the ray origin in {@code rayOrigin} and
     * the ray direction in {@code rayDir}.
     * <p>
     * A projection whose far plane is at infinity is supported: the far point is then a point at
     * infinity and the ray direction is taken from it as a finite direction. A far plane whose
     * homogeneous w is at most {@code 2^-40} times the near plane's is treated as being at
     * infinity. A projection whose near plane is at infinity (the reversed-Z projection) turns the
     * ray around: it then starts on the far plane and points toward the infinite near plane, away
     * from the camera - the opposite sense of a finite near plane's near-to-far ray. A near plane
     * whose homogeneous w is at most {@code 2^-40} times the far plane's counts as infinite.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix; each component of
     * the size of {@code viewport} must be non-zero; {@code winCoords} must lie within
     * {@code viewport}.
     *
     * @param winCoords the window coordinates {@code (x, y)} to unproject
     * @param viewport the viewport {@code [x, y, width, height]}
     * @param depthRange the clip-space depth range the projection maps onto
     * @param rayOrigin will hold the origin of the ray
     * @param rayDir will hold the direction of the ray
     * @return this
     */
    Double4x4 unprojectRay(Double2R winCoords, Double4R viewport, DepthRange depthRange, @Mutated Double3 rayOrigin, @Mutated Double3 rayDir);

    /**
     * Unproject the given window coordinates into a ray in object space using this matrix (which is
     * inverted internally) and the given viewport, storing the ray origin in {@code rayOrigin} and
     * the ray direction in {@code rayDir}.
     * <p>
     * A projection whose far plane is at infinity is supported: the far point is then a point at
     * infinity and the ray direction is taken from it as a finite direction. A far plane whose
     * homogeneous w is at most {@code 2^-40} times the near plane's is treated as being at
     * infinity. A projection whose near plane is at infinity (the reversed-Z projection) turns the
     * ray around: it then starts on the far plane and points toward the infinite near plane, away
     * from the camera - the opposite sense of a finite near plane's near-to-far ray. A near plane
     * whose homogeneous w is at most {@code 2^-40} times the far plane's counts as infinite.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix; each component of
     * the size of {@code (viewportX, viewportY, viewportZ, viewportW)} must be non-zero;
     * {@code (winCoordsX, winCoordsY)} must lie within
     * {@code (viewportX, viewportY, viewportZ, viewportW)}.
     *
     * @param winCoordsX the {@code x} component of the window coordinates {@code (x, y)} to
     *        unproject
     * @param winCoordsY the {@code y} component of the window coordinates {@code (x, y)} to
     *        unproject
     * @param viewportX the {@code x} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportY the {@code y} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportZ the {@code z} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportW the {@code w} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param depthRange the clip-space depth range the projection maps onto
     * @param rayOrigin will hold the origin of the ray
     * @param rayDir will hold the direction of the ray
     * @return this
     */
    Double4x4 unprojectRay(double winCoordsX, double winCoordsY, double viewportX, double viewportY, double viewportZ, double viewportW, DepthRange depthRange, @Mutated Double3 rayOrigin, @Mutated Double3 rayDir);

    /**
     * Unproject the given window coordinates into a ray in object space using this matrix (which is
     * inverted internally) and the given viewport, storing the ray origin in {@code rayOrigin} and
     * the ray direction in {@code rayDir}.
     * <p>
     * A projection whose far plane is at infinity is supported: the far point is then a point at
     * infinity and the ray direction is taken from it as a finite direction. A far plane whose
     * homogeneous w is at most {@code 2^-40} times the near plane's is treated as being at
     * infinity. A projection whose near plane is at infinity (the reversed-Z projection) turns the
     * ray around: it then starts on the far plane and points toward the infinite near plane, away
     * from the camera - the opposite sense of a finite near plane's near-to-far ray. A near plane
     * whose homogeneous w is at most {@code 2^-40} times the far plane's counts as infinite.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix; each component of
     * the size of {@code viewport} must be non-zero; {@code winCoords} must lie within
     * {@code viewport}.
     *
     * @param winCoords the window coordinates {@code (x, y)} to unproject
     * @param viewport the viewport {@code [x, y, width, height]}
     * @param rayOrigin will hold the origin of the ray
     * @param rayDir will hold the direction of the ray
     * @return this
     */
    default Double4x4 unprojectRay(Double2R winCoords, Double4R viewport, @Mutated Double3 rayOrigin, @Mutated Double3 rayDir) { return unprojectRay(winCoords, viewport, DepthRange.NEGATIVE_ONE_TO_ONE, rayOrigin, rayDir); }

    /**
     * Unproject the given window coordinates into a ray in object space using this matrix (which is
     * inverted internally) and the given viewport, storing the ray origin in {@code rayOrigin} and
     * the ray direction in {@code rayDir}.
     * <p>
     * A projection whose far plane is at infinity is supported: the far point is then a point at
     * infinity and the ray direction is taken from it as a finite direction. A far plane whose
     * homogeneous w is at most {@code 2^-40} times the near plane's is treated as being at
     * infinity. A projection whose near plane is at infinity (the reversed-Z projection) turns the
     * ray around: it then starts on the far plane and points toward the infinite near plane, away
     * from the camera - the opposite sense of a finite near plane's near-to-far ray. A near plane
     * whose homogeneous w is at most {@code 2^-40} times the far plane's counts as infinite.
     * <p>
     * Uses {@link DepthRange#NEGATIVE_ONE_TO_ONE} for {@code depthRange}.
     * <p>
     * Valid input: this matrix must be a projection or view-projection matrix; each component of
     * the size of {@code (viewportX, viewportY, viewportZ, viewportW)} must be non-zero;
     * {@code (winCoordsX, winCoordsY)} must lie within
     * {@code (viewportX, viewportY, viewportZ, viewportW)}.
     *
     * @param winCoordsX the {@code x} component of the window coordinates {@code (x, y)} to
     *        unproject
     * @param winCoordsY the {@code y} component of the window coordinates {@code (x, y)} to
     *        unproject
     * @param viewportX the {@code x} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportY the {@code y} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportZ the {@code z} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param viewportW the {@code w} component of the vector
     *        {@code (viewportX, viewportY, viewportZ, viewportW)}
     * @param rayOrigin will hold the origin of the ray
     * @param rayDir will hold the direction of the ray
     * @return this
     */
    default Double4x4 unprojectRay(double winCoordsX, double winCoordsY, double viewportX, double viewportY, double viewportZ, double viewportW, @Mutated Double3 rayOrigin, @Mutated Double3 rayDir) { return unprojectRay(winCoordsX, winCoordsY, viewportX, viewportY, viewportZ, viewportW, DepthRange.NEGATIVE_ONE_TO_ONE, rayOrigin, rayDir); }

    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v} and store the result in {@code dest}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the right operand of the product
     * @param dest will hold the result
     * @return dest
     */
    Double4 mul(Double4R v, @Mutated Double4 dest);

    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v} and store the result in {@code dest}.
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
    Double4 mul(double x, double y, double z, double w, @Mutated Double4 dest);

    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v} and store the result back into {@code v}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the right operand of the product (also receives the result)
     * @return {@code v}
     */
    default Double4 mul(@Mutated Double4 v) { return mul(v, v); }

    /**
     * Transform the given axis-aligned box by this matrix and compute the axis-aligned box
     * enclosing the result and store the result in {@code dest}.
     * <p>
     * Only the affine part of this matrix is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the minimum corner of {@code aabb} must not exceed the maximum corner of
     * {@code aabb} in any component.
     *
     * @param aabb the axis-aligned box to transform
     * @param dest will hold the result
     * @return dest
     */
    DoubleAABB transformAabb(DoubleAABBR aabb, @Mutated DoubleAABB dest);

    /**
     * Transform the given axis-aligned box by this matrix and compute the axis-aligned box
     * enclosing the result and store the result in {@code dest}.
     * <p>
     * Only the affine part of this matrix is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: {@code (minX, minY, minZ)} must not exceed {@code (maxX, maxY, maxZ)} in any
     * component.
     *
     * @param minX the {@code minX} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param minY the {@code minY} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param minZ the {@code minZ} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param maxX the {@code maxX} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param maxY the {@code maxY} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param maxZ the {@code maxZ} component of the axis-aligned bounding box
     *        {@code (minX, minY, minZ, maxX, maxY, maxZ)}
     * @param dest will hold the result
     * @return dest
     */
    DoubleAABB transformAabb(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, @Mutated DoubleAABB dest);

    /**
     * Transform the given axis-aligned box by this matrix and compute the axis-aligned box
     * enclosing the result and store the result in {@code dest}.
     * <p>
     * Only the affine part of this matrix is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: {@code min} must not exceed {@code max} in any component.
     *
     * @param min the minimum corner of the box
     * @param max the maximum corner of the box
     * @param dest will hold the result
     * @return dest
     */
    DoubleAABB transformAabb(Double3R min, Double3R max, @Mutated DoubleAABB dest);

    /**
     * Transform the given axis-aligned box by this matrix and compute the axis-aligned box
     * enclosing the result and store the result back into {@code aabb}.
     * <p>
     * Only the affine part of this matrix is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the minimum corner of {@code aabb} must not exceed the maximum corner of
     * {@code aabb} in any component.
     *
     * @param aabb the axis-aligned box to transform (also receives the result)
     * @return {@code aabb}
     */
    default DoubleAABB transformAabb(@Mutated DoubleAABB aabb) { return transformAabb(aabb, aabb); }

    /**
     * Transform the given direction by this matrix, ignoring any translation and store the result
     * in {@code dest}.
     * <p>
     * Only the affine part of this matrix is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the direction to transform
     * @param dest will hold the result
     * @return dest
     */
    Double3 transformDirection(Double3R v, @Mutated Double3 dest);

    /**
     * Transform the given direction by this matrix, ignoring any translation and store the result
     * in {@code dest}.
     * <p>
     * Only the affine part of this matrix is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Double3 transformDirection(double x, double y, double z, @Mutated Double3 dest);

    /**
     * Transform the given direction by this matrix, ignoring any translation and store the result
     * back into {@code v}.
     * <p>
     * Only the affine part of this matrix is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the direction to transform (also receives the result)
     * @return {@code v}
     */
    default Double3 transformDirection(@Mutated Double3 v) { return transformDirection(v, v); }

    /**
     * Transform the given position by this matrix, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * Only the affine part of this matrix is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the position to transform
     * @param dest will hold the result
     * @return dest
     */
    Double3 transformPosition(Double3R v, @Mutated Double3 dest);

    /**
     * Transform the given position by this matrix, treating it as a point with an implicit
     * {@code w = 1} and store the result in {@code dest}.
     * <p>
     * Only the affine part of this matrix is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Double3 transformPosition(double x, double y, double z, @Mutated Double3 dest);

    /**
     * Transform the given position by this matrix, treating it as a point with an implicit
     * {@code w = 1} and store the result back into {@code v}.
     * <p>
     * Only the affine part of this matrix is used: the last row is assumed to be
     * {@code (0, 0, 0, 1)}, so any projective component is ignored.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the position to transform (also receives the result)
     * @return {@code v}
     */
    default Double3 transformPosition(@Mutated Double3 v) { return transformPosition(v, v); }

    /**
     * Transform {@code v} by this matrix and perform perspective division and store the result in
     * {@code dest}.
     * <p>
     * Valid input: {@code v} must not be mapped to {@code w = 0} by this matrix.
     *
     * @param v the vector to transform
     * @param dest will hold the result
     * @return dest
     */
    Double3 transformProject(Double3R v, @Mutated Double3 dest);

    /**
     * Transform ({@code x}, {@code y}, {@code z}) by this matrix and perform perspective division
     * and store the result in {@code dest}.
     * <p>
     * Valid input: {@code (x, y, z)} must not be mapped to {@code w = 0} by this matrix.
     *
     * @param x the {@code x} component of the vector {@code (x, y, z)}
     * @param y the {@code y} component of the vector {@code (x, y, z)}
     * @param z the {@code z} component of the vector {@code (x, y, z)}
     * @param dest will hold the result
     * @return dest
     */
    Double3 transformProject(double x, double y, double z, @Mutated Double3 dest);

    /**
     * Transform {@code v} by this matrix and perform perspective division and store the result back
     * into {@code v}.
     * <p>
     * Valid input: {@code v} must not be mapped to {@code w = 0} by this matrix.
     *
     * @param v the vector to transform (also receives the result)
     * @return {@code v}
     */
    default Double3 transformProject(@Mutated Double3 v) { return transformProject(v, v); }
    /** {@return the value of the {@code m00} element} <p>Valid input: any value, NaN and the infinities included. */
    double m00();
    /** {@return the value of the {@code m01} element} <p>Valid input: any value, NaN and the infinities included. */
    double m01();
    /** {@return the value of the {@code m02} element} <p>Valid input: any value, NaN and the infinities included. */
    double m02();
    /** {@return the value of the {@code m03} element} <p>Valid input: any value, NaN and the infinities included. */
    double m03();
    /** {@return the value of the {@code m10} element} <p>Valid input: any value, NaN and the infinities included. */
    double m10();
    /** {@return the value of the {@code m11} element} <p>Valid input: any value, NaN and the infinities included. */
    double m11();
    /** {@return the value of the {@code m12} element} <p>Valid input: any value, NaN and the infinities included. */
    double m12();
    /** {@return the value of the {@code m13} element} <p>Valid input: any value, NaN and the infinities included. */
    double m13();
    /** {@return the value of the {@code m20} element} <p>Valid input: any value, NaN and the infinities included. */
    double m20();
    /** {@return the value of the {@code m21} element} <p>Valid input: any value, NaN and the infinities included. */
    double m21();
    /** {@return the value of the {@code m22} element} <p>Valid input: any value, NaN and the infinities included. */
    double m22();
    /** {@return the value of the {@code m23} element} <p>Valid input: any value, NaN and the infinities included. */
    double m23();
    /** {@return the value of the {@code m30} element} <p>Valid input: any value, NaN and the infinities included. */
    double m30();
    /** {@return the value of the {@code m31} element} <p>Valid input: any value, NaN and the infinities included. */
    double m31();
    /** {@return the value of the {@code m32} element} <p>Valid input: any value, NaN and the infinities included. */
    double m32();
    /** {@return the value of the {@code m33} element} <p>Valid input: any value, NaN and the infinities included. */
    double m33();

    /**
     * Store the elements into the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    default double[] storeCM(@Mutated double[] dest) { return storeCM(dest, 0); }

    /**
     * Store the elements into the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    double[] storeCM(@Mutated double[] dest, int offset);

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
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
     * @param dest the destination buffer
     * @return dest
     */
    default DoubleBuffer storeCM(@Mutated DoubleBuffer dest) { return storeCMAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
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
     * @param dest the destination buffer
     * @return dest
     */
    default DoubleBuffer storeCMAbsolute(@Mutated DoubleBuffer dest) { return storeCMAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer in column-major order, starting at the given
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
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer dest);

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
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
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default DoubleBuffer storeCMRelative(@Mutated DoubleBuffer dest) {
        if (dest.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeCMAbsolute(pos, dest);
        dest.position(pos + 16);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
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
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeCM(@Mutated ByteBuffer dest) { return storeCMAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
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
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeCMAbsolute(@Mutated ByteBuffer dest) { return storeCMAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at the given
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
     * @param dest the destination byte buffer
     * @return dest
     */
    ByteBuffer storeCMAbsolute(int index, @Mutated ByteBuffer dest);

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
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
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeCMRelative(@Mutated ByteBuffer dest) {
        if (dest.remaining() < 128) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeCMAbsolute(pos, dest);
        dest.position(pos + 128);
        return dest;
    }

    /**
     * Store the elements into the given memory segment in column-major order.
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
    default MemorySegment storeCM(@Mutated MemorySegment dest) { return storeCM(0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, starting at the given
     * offset.
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
    MemorySegment storeCM(long offset, @Mutated MemorySegment dest);

    /**
     * Store the elements into the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    Double4x4 storeCMUnsafe(long address);

    /**
     * Store the elements into the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    default float[] storeCM(@Mutated float[] dest) { return storeCM(dest, 0); }

    /**
     * Store the elements into the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    float[] storeCM(@Mutated float[] dest, int offset);

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
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
     * @param dest the destination buffer
     * @return dest
     */
    default FloatBuffer storeCM(@Mutated FloatBuffer dest) { return storeCMAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
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
     * @param dest the destination buffer
     * @return dest
     */
    default FloatBuffer storeCMAbsolute(@Mutated FloatBuffer dest) { return storeCMAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer in column-major order, starting at the given
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
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer dest);

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
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
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default FloatBuffer storeCMRelative(@Mutated FloatBuffer dest) {
        if (dest.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeCMAbsolute(pos, dest);
        dest.position(pos + 16);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at its current position (the position is not modified).
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
    default ByteBuffer storeCMFloat(@Mutated ByteBuffer dest) { return storeCMFloatAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at its current position (the position is not modified).
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
    default ByteBuffer storeCMFloatAbsolute(@Mutated ByteBuffer dest) { return storeCMFloatAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at the given absolute index (the position is not used or
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
     * @param dest the destination byte buffer
     * @return dest
     */
    ByteBuffer storeCMFloatAbsolute(int index, @Mutated ByteBuffer dest);

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at its current position and advancing the position accordingly.
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
    default ByteBuffer storeCMFloatRelative(@Mutated ByteBuffer dest) {
        if (dest.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeCMFloatAbsolute(pos, dest);
        dest.position(pos + 64);
        return dest;
    }

    /**
     * Store the elements into the given memory segment in column-major order, converting each
     * element to {@code float}.
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
    default MemorySegment storeCMFloat(@Mutated MemorySegment dest) { return storeCMFloat(0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, converting each
     * element to {@code float}, starting at the given offset.
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
    MemorySegment storeCMFloat(long offset, @Mutated MemorySegment dest);

    /**
     * Store the elements into the given raw memory address in column-major order, converting each
     * element to {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    Double4x4 storeCMFloatUnsafe(long address);

    /**
     * Store the elements into the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    default double[] storeRM(@Mutated double[] dest) { return storeRM(dest, 0); }

    /**
     * Store the elements into the given array in row-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    double[] storeRM(@Mutated double[] dest, int offset);

    /**
     * Store the elements into the given buffer in row-major order, starting at its current position
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
     * @param dest the destination buffer
     * @return dest
     */
    default DoubleBuffer storeRM(@Mutated DoubleBuffer dest) { return storeRMAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer in row-major order, starting at its current position
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
     * @param dest the destination buffer
     * @return dest
     */
    default DoubleBuffer storeRMAbsolute(@Mutated DoubleBuffer dest) { return storeRMAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer in row-major order, starting at the given absolute
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
     * @param dest the destination buffer
     * @return dest
     */
    DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer dest);

    /**
     * Store the elements into the given buffer in row-major order, starting at its current position
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
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default DoubleBuffer storeRMRelative(@Mutated DoubleBuffer dest) {
        if (dest.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeRMAbsolute(pos, dest);
        dest.position(pos + 16);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, starting at its current
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
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeRM(@Mutated ByteBuffer dest) { return storeRMAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer in row-major order, starting at its current
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
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeRMAbsolute(@Mutated ByteBuffer dest) { return storeRMAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer in row-major order, starting at the given
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
     * @param dest the destination byte buffer
     * @return dest
     */
    ByteBuffer storeRMAbsolute(int index, @Mutated ByteBuffer dest);

    /**
     * Store the elements into the given byte buffer in row-major order, starting at its current
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
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeRMRelative(@Mutated ByteBuffer dest) {
        if (dest.remaining() < 128) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeRMAbsolute(pos, dest);
        dest.position(pos + 128);
        return dest;
    }

    /**
     * Store the elements into the given memory segment in row-major order.
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
    default MemorySegment storeRM(@Mutated MemorySegment dest) { return storeRM(0L, dest); }

    /**
     * Store the elements into the given memory segment in row-major order, starting at the given
     * offset.
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
    MemorySegment storeRM(long offset, @Mutated MemorySegment dest);

    /**
     * Store the elements into the given raw memory address in row-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    Double4x4 storeRMUnsafe(long address);

    /**
     * Store the elements into the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    default float[] storeRM(@Mutated float[] dest) { return storeRM(dest, 0); }

    /**
     * Store the elements into the given array in row-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    float[] storeRM(@Mutated float[] dest, int offset);

    /**
     * Store the elements into the given buffer in row-major order, starting at its current position
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
     * @param dest the destination buffer
     * @return dest
     */
    default FloatBuffer storeRM(@Mutated FloatBuffer dest) { return storeRMAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer in row-major order, starting at its current position
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
     * @param dest the destination buffer
     * @return dest
     */
    default FloatBuffer storeRMAbsolute(@Mutated FloatBuffer dest) { return storeRMAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given buffer in row-major order, starting at the given absolute
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
     * @param dest the destination buffer
     * @return dest
     */
    FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer dest);

    /**
     * Store the elements into the given buffer in row-major order, starting at its current position
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
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default FloatBuffer storeRMRelative(@Mutated FloatBuffer dest) {
        if (dest.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeRMAbsolute(pos, dest);
        dest.position(pos + 16);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
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
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeRMFloat(@Mutated ByteBuffer dest) { return storeRMFloatAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
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
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer storeRMFloatAbsolute(@Mutated ByteBuffer dest) { return storeRMFloatAbsolute(dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
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
     * @param dest the destination byte buffer
     * @return dest
     */
    ByteBuffer storeRMFloatAbsolute(int index, @Mutated ByteBuffer dest);

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
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
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeRMFloatRelative(@Mutated ByteBuffer dest) {
        if (dest.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeRMFloatAbsolute(pos, dest);
        dest.position(pos + 64);
        return dest;
    }

    /**
     * Store the elements into the given memory segment in row-major order, converting each element
     * to {@code float}.
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
    default MemorySegment storeRMFloat(@Mutated MemorySegment dest) { return storeRMFloat(0L, dest); }

    /**
     * Store the elements into the given memory segment in row-major order, converting each element
     * to {@code float}, starting at the given offset.
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
    MemorySegment storeRMFloat(long offset, @Mutated MemorySegment dest);

    /**
     * Store the elements into the given raw memory address in row-major order, converting each
     * element to {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    Double4x4 storeRMFloatUnsafe(long address);

    /**
     * Store the elements into the given array in column-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    double[] storeCM(@Mutated double[] dest, int offset, int stride);

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
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
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    default DoubleBuffer storeCM(@Mutated DoubleBuffer dest, int stride) { return storeCMAbsolute(dest.position(), dest, stride); }

    /**
     * Store the elements into the given buffer in column-major order, starting at the given
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
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    DoubleBuffer storeCMAbsolute(int index, @Mutated DoubleBuffer dest, int stride);

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
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
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default DoubleBuffer storeCMRelative(@Mutated DoubleBuffer dest, int stride) {
        if (dest.remaining() < (long) 4 * stride) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeCMAbsolute(pos, dest, stride);
        dest.position(pos + 4 * stride);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
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
     * @param dest the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    default ByteBuffer storeCM(@Mutated ByteBuffer dest, int stride) { return storeCMAbsolute(dest.position(), dest, stride); }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at the given
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
     * @param dest the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    ByteBuffer storeCMAbsolute(int index, @Mutated ByteBuffer dest, int stride);

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
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
     * @param dest the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeCMRelative(@Mutated ByteBuffer dest, int stride) {
        if (dest.remaining() < (long) 4 * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeCMAbsolute(pos, dest, stride);
        dest.position(pos + (4 * stride) * 8);
        return dest;
    }

    /**
     * Store the elements into the given memory segment in column-major order, with {@code stride}
     * elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    default MemorySegment storeCM(@Mutated MemorySegment dest, int stride) { return storeCM(0L, dest, stride); }

    /**
     * Store the elements into the given memory segment in column-major order, starting at the given
     * offset, with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    MemorySegment storeCM(long offset, @Mutated MemorySegment dest, int stride);

    /**
     * Store the elements into the given raw memory address in column-major order, with
     * {@code stride} elements between the starts of consecutive columns. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    Double4x4 storeCMUnsafe(long address, int stride);

    /**
     * Store the elements into the given array in column-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    float[] storeCM(@Mutated float[] dest, int offset, int stride);

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
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
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    default FloatBuffer storeCM(@Mutated FloatBuffer dest, int stride) { return storeCMAbsolute(dest.position(), dest, stride); }

    /**
     * Store the elements into the given buffer in column-major order, starting at the given
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
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    FloatBuffer storeCMAbsolute(int index, @Mutated FloatBuffer dest, int stride);

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
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
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default FloatBuffer storeCMRelative(@Mutated FloatBuffer dest, int stride) {
        if (dest.remaining() < (long) 4 * stride) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeCMAbsolute(pos, dest, stride);
        dest.position(pos + 4 * stride);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at its current position (the position is not modified), with
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
     * @param dest the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    default ByteBuffer storeCMFloat(@Mutated ByteBuffer dest, int stride) { return storeCMFloatAbsolute(dest.position(), dest, stride); }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at the given absolute index (the position is not used or
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
     * @param dest the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    ByteBuffer storeCMFloatAbsolute(int index, @Mutated ByteBuffer dest, int stride);

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at its current position and advancing the position accordingly,
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
     * @param dest the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeCMFloatRelative(@Mutated ByteBuffer dest, int stride) {
        if (dest.remaining() < (long) 4 * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeCMFloatAbsolute(pos, dest, stride);
        dest.position(pos + (4 * stride) * 4);
        return dest;
    }

    /**
     * Store the elements into the given memory segment in column-major order, converting each
     * element to {@code float}, with {@code stride} elements between the starts of consecutive
     * columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    default MemorySegment storeCMFloat(@Mutated MemorySegment dest, int stride) { return storeCMFloat(0L, dest, stride); }

    /**
     * Store the elements into the given memory segment in column-major order, converting each
     * element to {@code float}, starting at the given offset, with {@code stride} elements between
     * the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    MemorySegment storeCMFloat(long offset, @Mutated MemorySegment dest, int stride);

    /**
     * Store the elements into the given raw memory address in column-major order, converting each
     * element to {@code float}, with {@code stride} elements between the starts of consecutive
     * columns. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    Double4x4 storeCMFloatUnsafe(long address, int stride);

    /**
     * Store the elements into the given array in row-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    double[] storeRM(@Mutated double[] dest, int offset, int stride);

    /**
     * Store the elements into the given buffer in row-major order, starting at its current position
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
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    default DoubleBuffer storeRM(@Mutated DoubleBuffer dest, int stride) { return storeRMAbsolute(dest.position(), dest, stride); }

    /**
     * Store the elements into the given buffer in row-major order, starting at the given absolute
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
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    DoubleBuffer storeRMAbsolute(int index, @Mutated DoubleBuffer dest, int stride);

    /**
     * Store the elements into the given buffer in row-major order, starting at its current position
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
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default DoubleBuffer storeRMRelative(@Mutated DoubleBuffer dest, int stride) {
        if (dest.remaining() < (long) 4 * stride) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeRMAbsolute(pos, dest, stride);
        dest.position(pos + 4 * stride);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, starting at its current
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
     * @param dest the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    default ByteBuffer storeRM(@Mutated ByteBuffer dest, int stride) { return storeRMAbsolute(dest.position(), dest, stride); }

    /**
     * Store the elements into the given byte buffer in row-major order, starting at the given
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
     * @param dest the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    ByteBuffer storeRMAbsolute(int index, @Mutated ByteBuffer dest, int stride);

    /**
     * Store the elements into the given byte buffer in row-major order, starting at its current
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
     * @param dest the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeRMRelative(@Mutated ByteBuffer dest, int stride) {
        if (dest.remaining() < (long) 4 * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeRMAbsolute(pos, dest, stride);
        dest.position(pos + (4 * stride) * 8);
        return dest;
    }

    /**
     * Store the elements into the given memory segment in row-major order, with {@code stride}
     * elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    default MemorySegment storeRM(@Mutated MemorySegment dest, int stride) { return storeRM(0L, dest, stride); }

    /**
     * Store the elements into the given memory segment in row-major order, starting at the given
     * offset, with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    MemorySegment storeRM(long offset, @Mutated MemorySegment dest, int stride);

    /**
     * Store the elements into the given raw memory address in row-major order, with {@code stride}
     * elements between the starts of consecutive rows. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    Double4x4 storeRMUnsafe(long address, int stride);

    /**
     * Store the elements into the given array in row-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    float[] storeRM(@Mutated float[] dest, int offset, int stride);

    /**
     * Store the elements into the given buffer in row-major order, starting at its current position
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
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    default FloatBuffer storeRM(@Mutated FloatBuffer dest, int stride) { return storeRMAbsolute(dest.position(), dest, stride); }

    /**
     * Store the elements into the given buffer in row-major order, starting at the given absolute
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
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    FloatBuffer storeRMAbsolute(int index, @Mutated FloatBuffer dest, int stride);

    /**
     * Store the elements into the given buffer in row-major order, starting at its current position
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
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default FloatBuffer storeRMRelative(@Mutated FloatBuffer dest, int stride) {
        if (dest.remaining() < (long) 4 * stride) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeRMAbsolute(pos, dest, stride);
        dest.position(pos + 4 * stride);
        return dest;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
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
     * @param dest the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    default ByteBuffer storeRMFloat(@Mutated ByteBuffer dest, int stride) { return storeRMFloatAbsolute(dest.position(), dest, stride); }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
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
     * @param dest the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    ByteBuffer storeRMFloatAbsolute(int index, @Mutated ByteBuffer dest, int stride);

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
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
     * @param dest the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeRMFloatRelative(@Mutated ByteBuffer dest, int stride) {
        if (dest.remaining() < (long) 4 * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = dest.position();
        storeRMFloatAbsolute(pos, dest, stride);
        dest.position(pos + (4 * stride) * 4);
        return dest;
    }

    /**
     * Store the elements into the given memory segment in row-major order, converting each element
     * to {@code float}, with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    default MemorySegment storeRMFloat(@Mutated MemorySegment dest, int stride) { return storeRMFloat(0L, dest, stride); }

    /**
     * Store the elements into the given memory segment in row-major order, converting each element
     * to {@code float}, starting at the given offset, with {@code stride} elements between the
     * starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    MemorySegment storeRMFloat(long offset, @Mutated MemorySegment dest, int stride);

    /**
     * Store the elements into the given raw memory address in row-major order, converting each
     * element to {@code float}, with {@code stride} elements between the starts of consecutive
     * rows. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    Double4x4 storeRMFloatUnsafe(long address, int stride);

    /** {@return whether all components of this matrix are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    boolean isFinite();

    /** {@return whether any component of this matrix is NaN} <p>Valid input: any value, NaN and the infinities included. */
    boolean isNaN();

    /**
     * Compare this matrix component-wise against {@code other}, allowing a difference
     * of at most {@code epsilon} per component.
     * <p>
     * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
     * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and
     * a NaN component never compares equal to anything.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the matrix to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    boolean equalsEpsilon(Double4x4R other, double epsilon);

    /**
     * Compare this matrix with the given object for element-wise equality.
     * <p>
     * Each component is compared bitwise, as by {@code Double.doubleToLongBits}: {@code 0.0} and
     * {@code -0.0} are not equal, and NaN is equal to NaN. Use {@link #equalsEpsilon} for a
     * tolerant comparison.
     * <p>
     * The cached structural property bits are ignored: two matrix objects holding the same elements
     * are equal whatever either one has determined about itself.
     * <p>
     * Only instances of this library's implementation compare equal to each other; any other object
     * yields {@code false}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param obj the object to compare with
     * @return {@code true} if {@code obj} is a matrix of this library with element-wise equal
     *        components, {@code false} otherwise
     */
    boolean equals(@org.jspecify.annotations.Nullable Object obj);

    /**
     * Compute a hash code consistent with {@link #equals}: it is derived from the components via
     * {@code Double.doubleToLongBits} alone, ignoring the cached structural property bits.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return the hash code of this matrix
     */
    int hashCode();

    /**
     * Numerically determine the structural properties of this matrix (identity, translation,
     * affinity) and return them as property bits.
     * <p>
     * The comparison is exact: an element counts as {@code 0} or {@code 1} only when it is exactly
     * that value (as by {@code ==}), with no tolerance. A {@code double} element {@code 1 + 1e-8}
     * is therefore not an identity element, while the {@code float} literal {@code 1 + 1e-8f}
     * already rounds to {@code 1.0f} and is.
     * <p>
     * Only identity, translation and affine are inferred (the identity and a pure translation carry
     * the orthogonal bit they imply); a general rotation block is never recognised as orthogonal. A
     * rotation loaded from a buffer or set from scalars therefore takes the affine dispatch arms
     * until it is rebuilt through a {@code make*} factory, which sets the bits from what it
     * constructs.
     * <p>
     * This is a pure query: it does not update this matrix's cached property bits. The mutating
     * operations refresh the cache themselves.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return the determined property bits
     */
    int determineProperties();
    /** {@return whether this matrix is known to be the identity} O(1) read of the cached property bits; conservative. <p>Valid input: any value, NaN and the infinities included. */
    boolean isIdentity();
    /** {@return whether this matrix is known to be a pure translation} O(1) read of the cached property bits; conservative. <p>Valid input: any value, NaN and the infinities included. */
    boolean isTranslation();
    /** {@return whether this matrix is known to be orthogonal, i.e. its upper-left block is orthonormal with positive determinant (a proper rotation; a reflection is affine, not orthogonal)} O(1) read of the cached property bits; conservative. <p>Valid input: any value, NaN and the infinities included. */
    boolean isOrthogonal();
    /** {@return whether this matrix is known to be affine} O(1) read of the cached property bits; conservative. <p>Valid input: any value, NaN and the infinities included. */
    boolean isAffine();

    /**
     * Store the elements into the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    default double[] store(@Mutated double[] dest) { return storeCM(dest); }

    /**
     * Store the elements into the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    default double[] store(@Mutated double[] dest, int offset) { return storeCM(dest, offset); }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
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
     * @param dest the destination buffer
     * @return dest
     */
    default DoubleBuffer store(@Mutated DoubleBuffer dest) { return storeCMAbsolute(dest); }

    /**
     * Store the elements into the given buffer in column-major order, starting at the given
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
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    default DoubleBuffer store(int index, @Mutated DoubleBuffer dest) { return storeCMAbsolute(index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
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
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default DoubleBuffer storeRelative(@Mutated DoubleBuffer dest) { return storeCMRelative(dest); }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    default float[] store(@Mutated float[] dest) { return storeCM(dest); }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    default float[] store(@Mutated float[] dest, int offset) { return storeCM(dest, offset); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
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
     * @param dest the destination buffer
     * @return dest
     */
    default FloatBuffer store(@Mutated FloatBuffer dest) { return storeCMAbsolute(dest); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
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
     * @param dest the destination buffer
     * @return dest
     */
    default FloatBuffer store(int index, @Mutated FloatBuffer dest) { return storeCMAbsolute(index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
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
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default FloatBuffer storeRelative(@Mutated FloatBuffer dest) { return storeCMRelative(dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
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
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer store(@Mutated ByteBuffer dest) { return storeCMAbsolute(dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at the given
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
     * @param dest the destination byte buffer
     * @return dest
     */
    default ByteBuffer store(int index, @Mutated ByteBuffer dest) { return storeCMAbsolute(index, dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
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
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeRelative(@Mutated ByteBuffer dest) { return storeCMRelative(dest); }

    /**
     * Store the elements into the given memory segment in column-major order.
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
    default MemorySegment store(@Mutated MemorySegment dest) { return storeCM(dest); }

    /**
     * Store the elements into the given memory segment in column-major order, starting at the given
     * offset.
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
    default MemorySegment store(long offset, @Mutated MemorySegment dest) { return storeCM(offset, dest); }

    /**
     * Store the elements into the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    default Double4x4 storeUnsafe(long address) { return storeCMUnsafe(address); }

    /**
     * Store the elements into the given array in column-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    default double[] store(@Mutated double[] dest, int offset, int stride) { return storeCM(dest, offset, stride); }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code float}, starting at the given offset, with {@code stride} elements between the starts
     * of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    default float[] store(@Mutated float[] dest, int offset, int stride) { return storeCM(dest, offset, stride); }

    /**
     * Store the elements into the given buffer in column-major order, starting at the given
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
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    default DoubleBuffer store(int index, @Mutated DoubleBuffer dest, int stride) { return storeCMAbsolute(index, dest, stride); }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
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
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default DoubleBuffer storeRelative(@Mutated DoubleBuffer dest, int stride) { return storeCMRelative(dest, stride); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
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
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    default FloatBuffer store(int index, @Mutated FloatBuffer dest, int stride) { return storeCMAbsolute(index, dest, stride); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
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
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default FloatBuffer storeRelative(@Mutated FloatBuffer dest, int stride) { return storeCMRelative(dest, stride); }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at the given
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
     * @param dest the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    default ByteBuffer store(int index, @Mutated ByteBuffer dest, int stride) { return storeCMAbsolute(index, dest, stride); }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
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
     * @param dest the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    default ByteBuffer storeRelative(@Mutated ByteBuffer dest, int stride) { return storeCMRelative(dest, stride); }

    /**
     * Store the elements into the given memory segment in column-major order, starting at the given
     * offset, with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param dest the destination memory segment
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    default MemorySegment store(long offset, @Mutated MemorySegment dest, int stride) { return storeCM(offset, dest, stride); }

    /**
     * Store the elements into the given raw memory address in column-major order, with
     * {@code stride} elements between the starts of consecutive columns. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    default Double4x4 storeUnsafe(long address, int stride) { return storeCMUnsafe(address, stride); }

    /** Backs {@code ZERO()} and {@code IDENTITY()}: defers the shared instances'
     *  allocation to first use, avoiding a class-initialization cycle with the
     *  implementation class. Not part of the public API. */
    final class Holder {
        private Holder() {}
        static final Double4x4R IDENTITY = Joml.double4x4();
        static final Double4x4R ZERO = Joml.double4x4(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }
}
