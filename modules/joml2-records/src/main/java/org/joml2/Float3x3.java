// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import org.joml2.internal.storeload.*;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;

/**
 * Immutable 3x3 matrix of single-precision {@code float} components.
 * <p>
 * All operations leave the receiver unchanged and return their result as a value. An operation
 * whose result equals one of its operands may return that operand instead of allocating a new
 * instance.
 * <p>
 * Structural property bits: the matrix caches whether it is known to be the identity, a pure
 * translation, orthogonal (a proper rotation, with any translation) or affine, and the operations
 * dispatch to cheaper arms on those bits. The {@code make*} factories set the bits from what they
 * construct and the computing operations derive them from their operands' bits; the element
 * constructors and the {@code load*} factories recompute them with {@code determineProperties()},
 * which compares elements exactly against {@code 0} and {@code 1} and infers identity, translation
 * and affine only. A rotation loaded from a buffer or set from scalars is therefore merely affine -
 * never orthogonal - until it is rebuilt through a {@code make*} factory. The bits read this 3x3
 * matrix homogeneously, as a 2D transform whose last row is {@code (0, 0, 1)}: a 3D rotation held
 * in a 3x3 matrix gets no bits at all (its last row is not {@code (0, 0, 1)}), and only a rotation
 * about the homogeneous axis can carry the orthogonal bit (from its factory).
 * <p>
 * {@code equals} compares the components element-wise and bitwise, as by
 * {@code Float.floatToIntBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
 * NaN; the cached structural property bits are ignored, so two matrix objects holding the same
 * elements are equal whatever either one has determined about itself. {@code hashCode} is
 * consistent with it (derived from the same bit patterns).
 * <p>
 * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
 * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and a
 * NaN component never compares equal to anything.
 *
 * @param m00 the element in row 0, column 0
 * @param m01 the element in row 0, column 1
 * @param m02 the element in row 0, column 2
 * @param m10 the element in row 1, column 0
 * @param m11 the element in row 1, column 1
 * @param m12 the element in row 1, column 2
 * @param m20 the element in row 2, column 0
 * @param m21 the element in row 2, column 1
 * @param m22 the element in row 2, column 2
 * @param properties the cached structural property bits
 */
public record Float3x3(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, int properties) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 36;

    /** The number of rows - the tight stride of the column-major ({@code storeCM}/{@code loadCM}) strided overloads. */
    public static final int ROWS = 3;
    /** The number of columns - the tight stride of the row-major ({@code storeRM}/{@code loadRM}) strided overloads. */
    public static final int COLUMNS = 3;

    /** The zero matrix (all components 0). */
    public static final Float3x3 ZERO = new Float3x3(0, 0, 0, 0, 0, 0, 0, 0, 0);

    /** The identity matrix. */
    public static final Float3x3 IDENTITY = new Float3x3();

    /**
     * Canonical constructor, taking the cached property bits as given.
     * <p>
     * The bits are trusted as-is and never validated: wrong bits produce wrong results from every
     * dispatched operation. Prefer the {@code make*} factories, or the element constructor, which
     * computes the bits itself.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m02 the element in row 0, column 2
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m12 the element in row 1, column 2
     * @param m20 the element in row 2, column 0
     * @param m21 the element in row 2, column 1
     * @param m22 the element in row 2, column 2
     * @param properties the cached property bits, taken as given
     */
    public Float3x3(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, int properties) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
        this.properties = properties;
    }

    /**
     * Create a new instance initialized to the identity.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     */
    public Float3x3() {
        this(1, 0, 0, 0, 1, 0, 0, 0, 1, Joml.BIT_IDENTITY);
    }

    /**
     * Create a matrix from the given elements, computing the cached property bits.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param m00 the element in row 0, column 0
     * @param m01 the element in row 0, column 1
     * @param m02 the element in row 0, column 2
     * @param m10 the element in row 1, column 0
     * @param m11 the element in row 1, column 1
     * @param m12 the element in row 1, column 2
     * @param m20 the element in row 2, column 0
     * @param m21 the element in row 2, column 1
     * @param m22 the element in row 2, column 2
     */
    public Float3x3(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
        this(m00, m01, m02, m10, m11, m12, m20, m21, m22, props(m00, m01, m02, m10, m11, m12, m20, m21, m22));
    }

    /**
     * Create a matrix from the given column vectors.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     */
    public Float3x3(Float3 c0, Float3 c1, Float3 c2) {
        this(c0.x(), c1.x(), c2.x(), c0.y(), c1.y(), c2.y(), c0.z(), c1.z(), c2.z());
    }

    /**
     * Create a matrix from the given column vectors and precomputed property bits (no
     * recomputation).
     * <p>
     * The bits are trusted as-is and never validated: wrong bits produce wrong results from every
     * dispatched operation. Prefer the {@code make*} factories, or the element constructor, which
     * computes the bits itself.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param c0 the first column
     * @param c1 the second column
     * @param c2 the third column
     * @param properties the cached property bits, taken as given
     */
    public Float3x3(Float3 c0, Float3 c1, Float3 c2, int properties) {
        this(c0.x(), c1.x(), c2.x(), c0.y(), c1.y(), c2.y(), c0.z(), c1.z(), c2.z(), properties);
    }

    /**
     * Create a matrix by identity-extending {@code src} to this square shape.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public Float3x3(Float2x3 src) {
        this(src.m00(), src.m01(), src.m02(), src.m10(), src.m11(), src.m12(), 0, 0, 1);
    }

    /**
     * Create a matrix by truncating {@code src} to the overlapping cells.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public Float3x3(Float4x4 src) {
        this(src.m00(), src.m01(), src.m02(), src.m10(), src.m11(), src.m12(), src.m20(), src.m21(), src.m22());
    }

    /**
     * Create a matrix by truncating {@code src} to the overlapping cells.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public Float3x3(Float3x4 src) {
        this(src.m00(), src.m01(), src.m02(), src.m10(), src.m11(), src.m12(), src.m20(), src.m21(), src.m22());
    }

    /**
     * Create a matrix by identity-extending {@code src} to this square shape.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public Float3x3(Float2x2 src) {
        this(src.m00(), src.m01(), 0, src.m10(), src.m11(), 0, 0, 0, 1);
    }

    /** {@return the element in row 0, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public float m00() { return m00; }
    /** {@return the element in row 0, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public float m01() { return m01; }
    /** {@return the element in row 0, column 2} <p>Valid input: any value, NaN and the infinities included. */
    public float m02() { return m02; }
    /** {@return the element in row 1, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public float m10() { return m10; }
    /** {@return the element in row 1, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public float m11() { return m11; }
    /** {@return the element in row 1, column 2} <p>Valid input: any value, NaN and the infinities included. */
    public float m12() { return m12; }
    /** {@return the element in row 2, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public float m20() { return m20; }
    /** {@return the element in row 2, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public float m21() { return m21; }
    /** {@return the element in row 2, column 2} <p>Valid input: any value, NaN and the infinities included. */
    public float m22() { return m22; }
    /** {@return the cached structural property bits} <p>Valid input: any value, NaN and the infinities included. */
    public int properties() { return properties; }

    private static int props(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
        if (m20 != 0 || m21 != 0 || m22 != 1) return 0;
        if (m00 != 1 || m01 != 0 || m10 != 0 || m11 != 1) return 1;
        if (m02 != 0 || m12 != 0) return 7;
        return 15;
    }

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
     * The bits read this 3x3 matrix homogeneously, as a 2D transform whose last row is
     * {@code (0, 0, 1)}: a 3D rotation held in a 3x3 matrix gets no bits at all (its last row is
     * not {@code (0, 0, 1)}), and only a rotation about the homogeneous axis can carry the
     * orthogonal bit (from its factory).
     * <p>
     * This is a pure query: it does not update this matrix's cached property bits.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @return the determined property bits
     */
    public int determineProperties() {
        if (this.m20 != 0 || this.m21 != 0 || this.m22 != 1) return 0;
        if (this.m00 != 1 || this.m01 != 0 || this.m10 != 0 || this.m11 != 1) return 1;
        if (this.m02 != 0 || this.m12 != 0) return 7;
        return 15;
    }

    /** {@return whether this matrix is known to be the identity} O(1) read of the cached property bits; conservative. <p>Valid input: any value, NaN and the infinities included. */
    public boolean isIdentity() { return (this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY; }
    /** {@return whether this matrix is known to be a pure translation} O(1) read of the cached property bits; conservative. <p>Valid input: any value, NaN and the infinities included. */
    public boolean isTranslation() { return (this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION; }
    /** {@return whether this matrix is known to be orthogonal, i.e. its upper-left block is orthonormal with positive determinant (a proper rotation; a reflection is affine, not orthogonal)} O(1) read of the cached property bits; conservative. <p>Valid input: any value, NaN and the infinities included. */
    public boolean isOrthogonal() { return (this.properties & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL; }
    /** {@return whether this matrix is known to be affine} O(1) read of the cached property bits; conservative. <p>Valid input: any value, NaN and the infinities included. */
    public boolean isAffine() { return (this.properties & Joml.BIT_AFFINE) == Joml.BIT_AFFINE; }


    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code getColumn} and {@code getRow}; reached only through them.
     */
    private Float3 getColumn_identity(int col) {
        return switch (col) {
            case 0 -> new Float3(1.0f, 0.0f, 0.0f);
            case 1 -> new Float3(0.0f, 1.0f, 0.0f);
            case 2 -> new Float3(0.0f, 0.0f, 1.0f);
            default -> throw new IndexOutOfBoundsException("Index out of range: " + col);
        };
    }


    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties; reached only
     * through the public {@code getColumn} dispatcher.
     */
    private Float3 getColumn_translation(int col) {
        return switch (col) {
            case 0 -> new Float3(1.0f, 0.0f, 0.0f);
            case 1 -> new Float3(0.0f, 1.0f, 0.0f);
            case 2 -> new Float3(this.m02, this.m12, 1.0f);
            default -> throw new IndexOutOfBoundsException("Index out of range: " + col);
        };
    }


    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties; reached only
     * through the public {@code getColumn} dispatcher.
     */
    private Float3 getColumn_general(int col) {
        return switch (col) {
            case 0 -> new Float3(this.m00, this.m10, this.m20);
            case 1 -> new Float3(this.m01, this.m11, this.m21);
            case 2 -> new Float3(this.m02, this.m12, this.m22);
            default -> throw new IndexOutOfBoundsException("Index out of range: " + col);
        };
    }


    /**
     * Get the column at the given index of this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param col the column index
     * @return the resulting vector
     * @throws IndexOutOfBoundsException if {@code col} is not in {@code [0, COLUMNS)}
     */
    public Float3 getColumn(int col) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getColumn_identity(col);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getColumn_translation(col);
        return getColumn_general(col);
    }


    /**
     * Private body of {@code getEulerAnglesXYZ}, specialized by runtime matrix properties. Shared
     * by the identical private paths of {@code getEulerAnglesXYZ}, {@code getEulerAnglesYXZ},
     * {@code getEulerAnglesYZX}, {@code getEulerAnglesZXY}, {@code getEulerAnglesZYX},
     * {@code decomposeSkew} and {@code getEulerAnglesXZY}; reached only through them.
     */
    private Float3 getEulerAnglesXYZ_identity() {
        return Float3.ZERO;
    }


    /**
     * Private body of {@code getEulerAnglesXYZ}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesXYZ} dispatcher.
     */
    private Float3 getEulerAnglesXYZ_translation() {
        float _t0 = Math.fma(this.m12, this.m12, 1.0f);
        return new Float3(_t0 < Math.fma(this.m12, this.m12, Math.fma(this.m02, this.m02, 1.0f)) * 1.0E-7f ? 0.0f : Math.atan2(-this.m12, 1.0f), Math.atan2(this.m02, (float) java.lang.Math.sqrt(_t0)), 0.0f);
    }


    /**
     * Private body of {@code getEulerAnglesXYZ}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesXYZ} dispatcher.
     */
    private Float3 getEulerAnglesXYZ_general() {
        float _t1 = Math.fma(this.m12, this.m12, this.m22 * this.m22);
        if (_t1 < Math.fma(this.m02, this.m02, _t1) * 1.0E-7f) {
            return new Float3(Math.atan2(this.m21, this.m11), Math.atan2(this.m02, (float) java.lang.Math.sqrt(_t1)), 0.0f);
        } else {
            return new Float3(Math.atan2(-this.m12, this.m22), Math.atan2(this.m02, (float) java.lang.Math.sqrt(_t1)), Math.atan2(-this.m01, this.m00));
        }
    }


    /**
     * Get the Euler angles in radians of this matrix, to be applied about the X, Y and Z axes, in
     * that order, returning the result as a value.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesXYZ() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getEulerAnglesXYZ_identity();
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getEulerAnglesXYZ_translation();
        return getEulerAnglesXYZ_general();
    }


    /**
     * Private body of {@code getEulerAnglesXZY}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesXZY} dispatcher.
     */
    private Float3 getEulerAnglesXZY_translation() {
        return new Float3(0.0f, Math.atan2(this.m02, 1.0f), 0.0f);
    }


    /**
     * Private body of {@code getEulerAnglesXZY}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesXZY} dispatcher.
     */
    private Float3 getEulerAnglesXZY_general() {
        float _t1 = Math.fma(this.m11, this.m11, this.m21 * this.m21);
        if (_t1 < Math.fma(this.m01, this.m01, _t1) * 1.0E-7f) {
            return new Float3(Math.atan2(-this.m12, this.m22), 0.0f, Math.atan2(-this.m01, (float) java.lang.Math.sqrt(_t1)));
        } else {
            return new Float3(Math.atan2(this.m21, this.m11), Math.atan2(this.m02, this.m00), Math.atan2(-this.m01, (float) java.lang.Math.sqrt(_t1)));
        }
    }


    /**
     * Get the Euler angles in radians of this matrix, to be applied about the X, Z and Y axes, in
     * that order, returning the result as a value.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesXZY() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getEulerAnglesXYZ_identity();
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getEulerAnglesXZY_translation();
        return getEulerAnglesXZY_general();
    }


    /**
     * Private body of {@code getEulerAnglesYXZ}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesYXZ} dispatcher.
     */
    private Float3 getEulerAnglesYXZ_translation() {
        float _t0 = Math.fma(this.m02, this.m02, 1.0f);
        return new Float3(Math.atan2(-this.m12, (float) java.lang.Math.sqrt(_t0)), _t0 < Math.fma(this.m02, this.m02, Math.fma(this.m12, this.m12, 1.0f)) * 1.0E-7f ? 0.0f : Math.atan2(this.m02, 1.0f), 0.0f);
    }


    /**
     * Private body of {@code getEulerAnglesYXZ}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesYXZ} dispatcher.
     */
    private Float3 getEulerAnglesYXZ_general() {
        float _t1 = Math.fma(this.m02, this.m02, this.m22 * this.m22);
        if (_t1 < Math.fma(this.m12, this.m12, _t1) * 1.0E-7f) {
            return new Float3(Math.atan2(-this.m12, (float) java.lang.Math.sqrt(_t1)), Math.atan2(-this.m20, this.m00), 0.0f);
        } else {
            return new Float3(Math.atan2(-this.m12, (float) java.lang.Math.sqrt(_t1)), Math.atan2(this.m02, this.m22), Math.atan2(this.m10, this.m11));
        }
    }


    /**
     * Get the Euler angles in radians of this matrix, to be applied about the Y, X and Z axes, in
     * that order, returning the result as a value.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesYXZ() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getEulerAnglesXYZ_identity();
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getEulerAnglesYXZ_translation();
        return getEulerAnglesYXZ_general();
    }


    /**
     * Private body of {@code getEulerAnglesYZX}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesYZX} dispatcher.
     */
    private Float3 getEulerAnglesYZX_translation() {
        return new Float3(Math.atan2(-this.m12, 1.0f), 0.0f, 0.0f);
    }


    /**
     * Private body of {@code getEulerAnglesYZX}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesYZX} dispatcher.
     */
    private Float3 getEulerAnglesYZX_general() {
        float _t1 = Math.fma(this.m11, this.m11, this.m12 * this.m12);
        if (_t1 < Math.fma(this.m10, this.m10, _t1) * 1.0E-7f) {
            return new Float3(0.0f, Math.atan2(this.m02, this.m22), Math.atan2(this.m10, (float) java.lang.Math.sqrt(_t1)));
        } else {
            return new Float3(Math.atan2(-this.m12, this.m11), Math.atan2(-this.m20, this.m00), Math.atan2(this.m10, (float) java.lang.Math.sqrt(_t1)));
        }
    }


    /**
     * Get the Euler angles in radians of this matrix, to be applied about the Y, Z and X axes, in
     * that order, returning the result as a value.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesYZX() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getEulerAnglesXYZ_identity();
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getEulerAnglesYZX_translation();
        return getEulerAnglesYZX_general();
    }


    /**
     * Private body of {@code getEulerAnglesZXY}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesZXY} dispatcher.
     */
    private Float3 getEulerAnglesZXY_affine() {
        return new Float3(Math.atan2(0.0f, (float) java.lang.Math.sqrt(Math.fma(this.m01, this.m01, this.m11 * this.m11))), 0.0f, Math.atan2(-this.m01, this.m11));
    }


    /**
     * Private body of {@code getEulerAnglesZXY}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesZXY} dispatcher.
     */
    private Float3 getEulerAnglesZXY_general() {
        float _t1 = Math.fma(this.m01, this.m01, this.m11 * this.m11);
        if (_t1 < Math.fma(this.m21, this.m21, _t1) * 1.0E-7f) {
            return new Float3(Math.atan2(this.m21, (float) java.lang.Math.sqrt(_t1)), 0.0f, Math.atan2(this.m10, this.m00));
        } else {
            return new Float3(Math.atan2(this.m21, (float) java.lang.Math.sqrt(_t1)), Math.atan2(-this.m20, this.m22), Math.atan2(-this.m01, this.m11));
        }
    }


    /**
     * Get the Euler angles in radians of this matrix, to be applied about the Z, X and Y axes, in
     * that order, returning the result as a value.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesZXY() {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getEulerAnglesXYZ_identity();
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Float3(0.0f, 0.0f, Math.atan2(-this.m01, this.m11));
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return getEulerAnglesZXY_affine();
        return getEulerAnglesZXY_general();
    }


    /**
     * Private body of {@code getEulerAnglesZYX}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesZYX} dispatcher.
     */
    private Float3 getEulerAnglesZYX_orthogonal() {
        return new Float3(0.0f, 0.0f, Math.atan2(this.m10, this.m00));
    }


    /**
     * Private body of {@code getEulerAnglesZYX}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesZYX} dispatcher.
     */
    private Float3 getEulerAnglesZYX_general() {
        float _t1 = Math.fma(this.m21, this.m21, this.m22 * this.m22);
        if (_t1 < Math.fma(this.m20, this.m20, _t1) * 1.0E-7f) {
            return new Float3(0.0f, Math.atan2(-this.m20, (float) java.lang.Math.sqrt(_t1)), Math.atan2(-this.m01, this.m11));
        } else {
            return new Float3(Math.atan2(this.m21, this.m22), Math.atan2(-this.m20, (float) java.lang.Math.sqrt(_t1)), Math.atan2(this.m10, this.m00));
        }
    }


    /**
     * Get the Euler angles in radians of this matrix, to be applied about the Z, Y and X axes, in
     * that order, returning the result as a value.
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The angles are read from ratios of the raw elements of the upper-left 3x3, so a uniform scale
     * cancels out, but a non-uniform scale or shear yields wrong angles rather than the angles of
     * its rotation part.
     * <p>
     * Valid input: this matrix must be a rotation matrix, possibly scaled uniformly.
     *
     * @return the Euler angles in radians: about X in {@code x}, about Y in {@code y}, about Z in
     *        {@code z}
     */
    public Float3 getEulerAnglesZYX() {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getEulerAnglesXYZ_identity();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return getEulerAnglesZYX_orthogonal();
        return getEulerAnglesZYX_general();
    }


    /**
     * Private body of {@code getNormalizedRotation}, specialized by runtime matrix properties.
     * Shared by the identical private paths of {@code getNormalizedRotation},
     * {@code decomposeRotation} and {@code getUnnormalizedRotation}; reached only through them.
     */
    private FloatQuat getNormalizedRotation_identity() {
        return new FloatQuat(0.0f, 0.0f, 0.0f, 1.0f);
    }


    /**
     * Private body of {@code getNormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getNormalizedRotation} dispatcher.
     */
    private FloatQuat getNormalizedRotation_translation() {
        float _t2 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(this.m02, this.m02, Math.fma(this.m12, this.m12, 1.0f))));
        float _t4 = 1.0f + (2.0f + _t2);
        float _sp1 = (1.0f / (float) java.lang.Math.sqrt(_t4)) * 0.5f * _t2;
        return new FloatQuat(-(_sp1 * this.m12), _sp1 * this.m02, 0.0f, 0.5f * (float) java.lang.Math.sqrt(_t4));
    }


    /**
     * Private body of {@code getNormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getNormalizedRotation} dispatcher.
     */
    private FloatQuat getNormalizedRotation_general() {
        float _t6 = Math.fma(this.m21, this.m21, Math.fma(this.m01, this.m01, this.m11 * this.m11));
        float _t7 = Math.fma(this.m22, this.m22, Math.fma(this.m02, this.m02, this.m12 * this.m12));
        float _t8 = Math.fma(this.m20, this.m20, Math.fma(this.m00, this.m00, this.m10 * this.m10));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t6));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t11 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t21, _t23, _t27;
        if (_t6 != 0.0f) {
            _t21 = this.m01 * _t9;
            _t23 = this.m11 * _t9;
            _t27 = this.m21 * _t9;
        } else {
            _t21 = 0.0f;
            _t23 = 0.0f;
            _t27 = 0.0f;
        }
        float _t22, _t24, _t26;
        if (_t7 != 0.0f) {
            _t22 = this.m12 * _t10;
            _t24 = this.m02 * _t10;
            _t26 = this.m22 * _t10;
        } else {
            _t22 = 0.0f;
            _t24 = 0.0f;
            _t26 = 0.0f;
        }
        float _t25, _t28, _t29;
        if (_t8 != 0.0f) {
            _t25 = this.m20 * _t11;
            _t28 = this.m00 * _t11;
            _t29 = this.m10 * _t11;
        } else {
            _t25 = 0.0f;
            _t28 = 0.0f;
            _t29 = 0.0f;
        }
        return getNormalizedRotation_general_seaf28661_1(_t21, _t23, _t27, _t22, _t24, _t26, _t25, _t28, _t29, _t27 - _t22, _t27 + _t22);
    }

    /** Piece 2 of {@code getNormalizedRotation_general}, split to fit the inline budget; reached only through it. */
    private FloatQuat getNormalizedRotation_general_seaf28661_1(float _t21, float _t23, float _t27, float _t22, float _t24, float _t26, float _t25, float _t28, float _t29, float _t36, float _t39) {
        float _t49, _t50, _t51;
        if (Math.fma(Math.fma(_t21, _t22, -(_t23 * _t24)), _t25, Math.fma(Math.fma(_t23, _t26, -(_t27 * _t22)), _t28, Math.fma(_t27, _t24, -(_t21 * _t26)) * _t29)) < 0.0f) {
            _t49 = -_t28;
            _t50 = -_t29;
            _t51 = -_t25;
        } else {
            _t49 = _t28;
            _t50 = _t29;
            _t51 = _t25;
        }
        float _t52 = _t49 + _t23;
        float _t58 = _t52 + _t26;
        float _t62 = 1.0f + _t58;
        float _t63 = 1.0f + (_t49 - (_t23 + _t26));
        float _t64 = 1.0f + (_t23 - (_t49 + _t26));
        float _t65 = 1.0f + (_t26 - _t52);
        return getNormalizedRotation_general_seaf28661_2(_t23, _t26, _t36, _t39, _t49, _t50 + _t21, _t51 + _t24, _t24 - _t51, _t50 - _t21, _t58, _t62, _t63, _t64, _t65, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t62)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63)));
    }

    /** Piece 3 of {@code getNormalizedRotation_general}, split to fit the inline budget; reached only through it. */
    private FloatQuat getNormalizedRotation_general_seaf28661_2(float _t23, float _t26, float _t36, float _t39, float _t49, float _t53, float _t55, float _t56, float _t57, float _t58, float _t62, float _t63, float _t64, float _t65, float _sp0, float _sp1, float _sp2, float _sp3) {
        float _sfx0, _sfx1, _sfx2, _sfx3;
        if (_t58 > 0.0f) {
            _sfx0 = _sp0 * _t36;
            _sfx1 = _sp0 * _t56;
            _sfx2 = _sp0 * _t57;
            _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t62);
        } else {
            if (_t49 > java.lang.Math.max(_t23, _t26)) {
                _sfx0 = 0.5f * (float) java.lang.Math.sqrt(_t63);
                _sfx1 = _sp3 * _t53;
                _sfx2 = _sp3 * _t55;
                _sfx3 = _sp3 * _t36;
            } else {
                if (_t23 > _t26) {
                    _sfx0 = _sp1 * _t53;
                    _sfx1 = 0.5f * (float) java.lang.Math.sqrt(_t64);
                    _sfx2 = _sp1 * _t39;
                    _sfx3 = _sp1 * _t56;
                } else {
                    _sfx0 = _sp2 * _t55;
                    _sfx1 = _sp2 * _t39;
                    _sfx2 = 0.5f * (float) java.lang.Math.sqrt(_t65);
                    _sfx3 = _sp2 * _t57;
                }
            }
        }
        return new FloatQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }


    /**
     * Extract the rotation of this matrix as a quaternion, column-normalizing the linear block
     * first to strip scale (skew is not removed: a sheared block yields a quaternion that is not
     * unit length), returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting quaternion
     */
    public FloatQuat getNormalizedRotation() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getNormalizedRotation_identity();
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getNormalizedRotation_translation();
        return getNormalizedRotation_general();
    }


    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Float3 getRow_translation(int row) {
        return switch (row) {
            case 0 -> new Float3(1.0f, 0.0f, this.m02);
            case 1 -> new Float3(0.0f, 1.0f, this.m12);
            case 2 -> new Float3(0.0f, 0.0f, 1.0f);
            default -> throw new IndexOutOfBoundsException("Index out of range: " + row);
        };
    }


    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Float3 getRow_general(int row) {
        return switch (row) {
            case 0 -> new Float3(this.m00, this.m01, this.m02);
            case 1 -> new Float3(this.m10, this.m11, this.m12);
            case 2 -> new Float3(this.m20, this.m21, this.m22);
            default -> throw new IndexOutOfBoundsException("Index out of range: " + row);
        };
    }


    /**
     * Get the row at the given index of this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param row the row index
     * @return the resulting vector
     * @throws IndexOutOfBoundsException if {@code row} is not in {@code [0, ROWS)}
     */
    public Float3 getRow(int row) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getColumn_identity(row);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getRow_translation(row);
        return getRow_general(row);
    }


    /**
     * Private body of {@code getScale}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code getScale} and {@code decomposeScale}; reached only through
     * them.
     */
    private Float3 getScale_identity() {
        return new Float3(1.0f, 1.0f, 1.0f);
    }


    /**
     * Private body of {@code getScale}, specialized by runtime matrix properties; reached only
     * through the public {@code getScale} dispatcher.
     */
    private Float3 getScale_translation() {
        return new Float3(1.0f, 1.0f, (float) java.lang.Math.sqrt(Math.fma(this.m02, this.m02, Math.fma(this.m12, this.m12, 1.0f))));
    }


    /**
     * Private body of {@code getScale}, specialized by runtime matrix properties; reached only
     * through the public {@code getScale} dispatcher.
     */
    private Float3 getScale_general() {
        return new Float3((float) java.lang.Math.sqrt(Math.fma(this.m20, this.m20, Math.fma(this.m00, this.m00, this.m10 * this.m10))), (float) java.lang.Math.sqrt(Math.fma(this.m21, this.m21, Math.fma(this.m01, this.m01, this.m11 * this.m11))), (float) java.lang.Math.sqrt(Math.fma(this.m22, this.m22, Math.fma(this.m02, this.m02, this.m12 * this.m12))));
    }


    /**
     * Get the scaling factors of this matrix, as the lengths of its basis columns (always
     * non-negative; skew is ignored), returning the result as a value.
     * <p>
     * For a 2D homogeneous 3x3 matrix the third factor is simply the length of the third column -
     * {@code sqrt(m02² + m12² + 1)} for a 2D affine transform, not a scale of anything.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting vector
     */
    public Float3 getScale() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getScale_identity();
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return getScale_translation();
        return getScale_general();
    }


    /**
     * Get the translation of this matrix, read from its last column as {@code (m02, m12)} (the 2D
     * homogeneous convention), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting vector
     */
    public Float2 getTranslation() {
        if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return Float2.ZERO;
        return new Float2(this.m02, this.m12);
    }

    /** Private tail of {@code getUnnormalizedRotation_orthogonal}; reached only through it. */
    private FloatQuat getUnnormalizedRotation_orthogonal_s0_tail(float _t11, float _t6, float _sp0, float _t14, float _t0, float _sp2, float _t15, float _sp1, float _t16, float _t12, float _sp3, float _t13, float _t10) {
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_t11));
        if (_t6 > 0.0f) {
            return new FloatQuat(-(_sp0 * _t14), _sp1 * _t14, _sp3 * _t14, 0.5f * (float) java.lang.Math.sqrt(_t10));
        } else {
            if (this.m00 > _t0) {
                return new FloatQuat(0.5f * (float) java.lang.Math.sqrt(_t11), _sp2 * _t17, _sp1 * _t17, -(_sp0 * _t17));
            } else {
                if (this.m11 > 1.0f) {
                    return new FloatQuat(_sp2 * _t15, 0.5f * (float) java.lang.Math.sqrt(_t12), _sp0 * _t15, _sp1 * _t15);
                } else {
                    return new FloatQuat(_sp1 * _t16, _sp0 * _t16, 0.5f * (float) java.lang.Math.sqrt(_t13), _sp3 * _t16);
                }
            }
        }
    }


    /**
     * Private body of {@code getUnnormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getUnnormalizedRotation} dispatcher.
     */
    private FloatQuat getUnnormalizedRotation_orthogonal() {
        float _t3 = this.m00 + this.m11;
        float _t6 = 1.0f + _t3;
        float _t10 = 1.0f + _t6;
        float _t12 = 1.0f + (this.m11 - (1.0f + this.m00));
        float _t13 = 1.0f + (1.0f - _t3);
        return getUnnormalizedRotation_orthogonal_s0_tail(1.0f + (this.m00 - (1.0f + this.m11)), _t6, 0.5f * this.m12, (1.0f / (float) java.lang.Math.sqrt(_t10)), java.lang.Math.max(this.m11, 1.0f), 0.5f * (this.m01 + this.m10), (1.0f / (float) java.lang.Math.sqrt(_t12)), 0.5f * this.m02, (1.0f / (float) java.lang.Math.sqrt(_t13)), _t12, 0.5f * (this.m10 - this.m01), _t13, _t10);
    }

    /** Private tail of {@code getUnnormalizedRotation_general}; reached only through it. */
    private FloatQuat getUnnormalizedRotation_general_s0_tail(float _t10, float _sp0, float _t1, float _t2, float _t15, float _sp1, float _t4, float _sp2, float _t6, float _t7, float _sp3, float _t16, float _t8, float _t9, float _t17, float _t14) {
        float _sfx0, _sfx1, _sfx2;
        if (_t10 > 0.0f) {
            _sfx0 = _sp0 * _t1;
            _sfx1 = _sp0 * _t7;
            _sfx2 = _sp0 * _t9;
        } else {
            if (this.m00 > _t2) {
                _sfx0 = 0.5f * (float) java.lang.Math.sqrt(_t15);
                _sfx1 = _sp3 * _t4;
                _sfx2 = _sp3 * _t6;
            } else {
                if (this.m11 > this.m22) {
                    _sfx0 = _sp1 * _t4;
                    _sfx1 = 0.5f * (float) java.lang.Math.sqrt(_t16);
                    _sfx2 = _sp1 * _t8;
                } else {
                    _sfx0 = _sp2 * _t6;
                    _sfx1 = _sp2 * _t8;
                    _sfx2 = 0.5f * (float) java.lang.Math.sqrt(_t17);
                }
            }
        }
        return getUnnormalizedRotation_general_s0_tail2(_t10, _t14, _t2, _sp3, _t1, _sp1, _t7, _sp2, _t9, _sfx0, _sfx1, _sfx2);
    }

    /** Private tail of {@code getUnnormalizedRotation_general}; reached only through it. */
    private FloatQuat getUnnormalizedRotation_general_s0_tail2(float _t10, float _t14, float _t2, float _sp3, float _t1, float _sp1, float _t7, float _sp2, float _t9, float _sfx0, float _sfx1, float _sfx2) {
        float _sfx3 = _t10 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t14) : this.m00 > _t2 ? _sp3 * _t1 : this.m11 > this.m22 ? _sp1 * _t7 : _sp2 * _t9;
        return new FloatQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }


    /**
     * Private body of {@code getUnnormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getUnnormalizedRotation} dispatcher.
     */
    private FloatQuat getUnnormalizedRotation_general() {
        float _t0 = this.m00 + this.m11;
        float _t10 = this.m22 + _t0;
        float _t14 = 1.0f + _t10;
        float _t15 = 1.0f + (this.m00 - (this.m11 + this.m22));
        float _t16 = 1.0f + (this.m11 - (this.m00 + this.m22));
        float _t17 = 1.0f + (this.m22 - _t0);
        return getUnnormalizedRotation_general_s0_tail(_t10, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), this.m21 - this.m12, java.lang.Math.max(this.m11, this.m22), _t15, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), this.m01 + this.m10, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), this.m02 + this.m20, this.m02 - this.m20, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)), _t16, this.m12 + this.m21, this.m10 - this.m01, _t17, _t14);
    }


    /**
     * Extract the rotation of this matrix as a quaternion directly from the linear block, without
     * normalizing it, returning the result as a value.
     * <p>
     * Valid input: this matrix must be a rotation matrix.
     *
     * @return the resulting quaternion
     */
    public FloatQuat getUnnormalizedRotation() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getNormalizedRotation_identity();
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new FloatQuat(-(0.25f * this.m12), 0.25f * this.m02, 0.0f, 1.0f);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return getUnnormalizedRotation_orthogonal();
        return getUnnormalizedRotation_general();
    }


    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code cofactor} and {@code normal}; reached only through them.
     */
    private Float3x3 cofactor_orthogonal() {
        return new Float3x3(this.m11, -this.m10, 0.0f, this.m10, this.m11, 0.0f, Math.fma(-this.m02, this.m11, -(this.m10 * this.m12)), Math.fma(this.m02, this.m10, -(this.m11 * this.m12)), 1.0f, 0);
    }


    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties; reached only
     * through the public {@code cofactor} dispatcher.
     */
    private Float3x3 cofactor_affine() {
        return new Float3x3(this.m11, -this.m10, 0.0f, -this.m01, this.m00, 0.0f, Math.fma(this.m01, this.m12, -(this.m02 * this.m11)), Math.fma(this.m02, this.m10, -(this.m00 * this.m12)), Math.fma(this.m00, this.m11, -(this.m01 * this.m10)), 0);
    }


    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties; reached only
     * through the public {@code cofactor} dispatcher.
     */
    private Float3x3 cofactor_general() {
        return new Float3x3(Math.fma(this.m11, this.m22, -(this.m12 * this.m21)), Math.fma(this.m12, this.m20, -(this.m10 * this.m22)), Math.fma(this.m10, this.m21, -(this.m11 * this.m20)), Math.fma(this.m02, this.m21, -(this.m01 * this.m22)), Math.fma(this.m00, this.m22, -(this.m02 * this.m20)), Math.fma(this.m01, this.m20, -(this.m00 * this.m21)), Math.fma(this.m01, this.m12, -(this.m02 * this.m11)), Math.fma(this.m02, this.m10, -(this.m00 * this.m12)), Math.fma(this.m00, this.m11, -(this.m01 * this.m10)), 0);
    }


    /**
     * Compute the cofactor matrix of this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 cofactor() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, -this.m02, -this.m12, 1.0f, 0);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return cofactor_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return cofactor_affine();
        return cofactor_general();
    }


    /**
     * Compute the determinant of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the determinant of this matrix
     */
    public float determinant() {
        return Math.fma(this.m02, Math.fma(this.m10, this.m21, -(this.m11 * this.m20)), Math.fma(this.m00, Math.fma(this.m11, this.m22, -(this.m12 * this.m21)), -(this.m01 * Math.fma(this.m10, this.m22, -(this.m12 * this.m20)))));
    }


    /**
     * Compute the Frobenius norm of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Frobenius norm of this matrix
     */
    public float frobeniusNorm() {
        return (float) java.lang.Math.sqrt(Math.fma(this.m00, this.m00, this.m01 * this.m01) + Math.fma(this.m02, this.m02, this.m10 * this.m10) + (Math.fma(this.m11, this.m11, this.m12 * this.m12) + Math.fma(this.m20, this.m20, Math.fma(this.m21, this.m21, this.m22 * this.m22))));
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float3x3 invert_orthogonal() {
        return new Float3x3(this.m11, this.m10, Math.fma(-this.m02, this.m11, -(this.m10 * this.m12)), -this.m10, this.m11, Math.fma(this.m02, this.m10, -(this.m11 * this.m12)), 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float3x3 invert_affine() {
        float _t3 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(java.lang.Math.abs(_t3) > 1.1754944E-38f && java.lang.Math.abs(_t3) < 8.507059E37f)) return invert_degenerate();
        float _t3_inv = 1.0f / _t3;
        return new Float3x3(this.m11 * _t3_inv, -(this.m01 * _t3_inv), Math.fma(this.m01, this.m12, -(this.m02 * this.m11)) * _t3_inv, -(this.m10 * _t3_inv), this.m00 * _t3_inv, Math.fma(this.m02, this.m10, -(this.m00 * this.m12)) * _t3_inv, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }

    /**
     * Private per-column body of {@code invert_general}. Shared by the identical private paths of
     * {@code invert} and {@code invertProduct}; reached only through them.
     */
    private float[] invert_general_s0_c0(float _t6, float _t13_inv, float _t7) {
        return new float[] {_t6 * _t13_inv, Math.fma(this.m12, this.m20, -(this.m10 * this.m22)) * _t13_inv, _t7 * _t13_inv};
    }

    /**
     * Private per-column body of {@code invert_general}. Shared by the identical private paths of
     * {@code invert} and {@code invertProduct}; reached only through them.
     */
    private float[] invert_general_s0_c1(float _t13_inv) {
        return new float[] {Math.fma(this.m02, this.m21, -(this.m01 * this.m22)) * _t13_inv, Math.fma(this.m00, this.m22, -(this.m02 * this.m20)) * _t13_inv, Math.fma(this.m01, this.m20, -(this.m00 * this.m21)) * _t13_inv};
    }

    /**
     * Private per-column body of {@code invert_general}. Shared by the identical private paths of
     * {@code invert} and {@code invertProduct}; reached only through them.
     */
    private float[] invert_general_s0_c2(float _t13_inv) {
        return new float[] {Math.fma(this.m01, this.m12, -(this.m02 * this.m11)) * _t13_inv, Math.fma(this.m02, this.m10, -(this.m00 * this.m12)) * _t13_inv, Math.fma(this.m00, this.m11, -(this.m01 * this.m10)) * _t13_inv};
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Float3x3 invert_general() {
        float _t6 = Math.fma(this.m11, this.m22, -(this.m12 * this.m21));
        float _t7 = Math.fma(this.m10, this.m21, -(this.m11 * this.m20));
        float _t13 = Math.fma(this.m02, _t7, Math.fma(this.m00, _t6, -(this.m01 * Math.fma(this.m10, this.m22, -(this.m12 * this.m20)))));
        if (!(java.lang.Math.abs(_t13) > 1.1754944E-38f && java.lang.Math.abs(_t13) < 8.507059E37f)) return invert_degenerate();
        float _t13_inv = 1.0f / _t13;
        float[] _col0 = invert_general_s0_c0(_t6, _t13_inv, _t7);
        float[] _col1 = invert_general_s0_c1(_t13_inv);
        float[] _col2 = invert_general_s0_c2(_t13_inv);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Invert this matrix, returning the result as a value.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @return the resulting matrix
     */
    public Float3x3 invert() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(1.0f, 0.0f, -this.m02, 0.0f, 1.0f, -this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invert_affine();
        return invert_general();
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 invert_degenerate_translation() {
        float _t0 = unitScale(1.0f, 0.0f, this.m02);
        float _t1 = unitScale(0.0f, 1.0f, this.m12);
        float _t2_inv = 1.0f / _t0;
        float _t3_inv = 1.0f / _t1;
        return new Float3x3(_t0 * _t2_inv, 0.0f, -(this.m02 * _t0 * _t2_inv), 0.0f, _t1 * _t3_inv, -(this.m12 * _t1 * _t3_inv), 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 invert_degenerate_orthogonal() {
        float _t0 = unitScale(this.m10, this.m11, this.m12);
        float _t1 = unitScale(this.m00, this.m01, this.m02);
        float _t8 = this.m11 * _t0;
        float _t9 = this.m00 * _t1;
        float _t10 = this.m01 * _t1;
        float _t11 = this.m10 * _t0;
        float _t12 = this.m12 * _t0;
        float _t13 = this.m02 * _t1;
        float _t16_inv = 1.0f / Math.fma(_t9, _t8, -(_t10 * _t11));
        float _sp1 = _t0 * _t16_inv;
        float _sp0 = _t1 * _t16_inv;
        return new Float3x3(_t8 * _sp0, -(_t10 * _sp1), Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv, -(_t11 * _sp0), _t9 * _sp1, Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 invert_degenerate_affine() {
        float _t0 = unitScale(this.m10, this.m11, this.m12);
        float _t1 = unitScale(this.m00, this.m01, this.m02);
        float _t8 = this.m11 * _t0;
        float _t9 = this.m00 * _t1;
        float _t10 = this.m01 * _t1;
        float _t11 = this.m10 * _t0;
        float _t12 = this.m12 * _t0;
        float _t13 = this.m02 * _t1;
        float _t16_inv = 1.0f / Math.fma(_t9, _t8, -(_t10 * _t11));
        float _sp1 = _t0 * _t16_inv;
        float _sp0 = _t1 * _t16_inv;
        return new Float3x3(_t8 * _sp0, -(_t10 * _sp1), Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv, -(_t11 * _sp0), _t9 * _sp1, Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }

    /**
     * Private per-column body of {@code invert_degenerate_general_s0_tail}. Shared by the identical
     * private paths of {@code invert} and {@code invertProduct}; reached only through them.
     */
    private float[] invert_degenerate_general_s0_tail_sda9ffc5_c0(float _t27, float _sp0, float _t14, float _t17, float _t16, float _t13, float _t28) {
        return new float[] {_t27 * _sp0, Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0, _t28 * _sp0};
    }

    /**
     * Private per-column body of {@code invert_degenerate_general_s0_tail}. Shared by the identical
     * private paths of {@code invert} and {@code invertProduct}; reached only through them.
     */
    private float[] invert_degenerate_general_s0_tail_sda9ffc5_c1(float _t18, float _t15, float _t20, float _t13, float _sp1, float _t19, float _t17) {
        return new float[] {Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1, Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1, Math.fma(_t20, _t17, -(_t19 * _t15)) * _sp1};
    }

    /**
     * Private per-column body of {@code invert_degenerate_general_s0_tail}. Shared by the identical
     * private paths of {@code invert} and {@code invertProduct}; reached only through them.
     */
    private float[] invert_degenerate_general_s0_tail_sda9ffc5_c2(float _t20, float _t14, float _t18, float _t12, float _sp2, float _t16, float _t19) {
        return new float[] {Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2, Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2, Math.fma(_t19, _t12, -(_t20 * _t16)) * _sp2};
    }

    /**
     * Private tail of {@code invert_degenerate_general}. Shared by the identical private paths of
     * {@code invert} and {@code invertProduct}; reached only through them.
     */
    private Float3x3 invert_degenerate_general_s0_tail(float _t2, float _t33_inv, float _t27, float _t18, float _t15, float _t20, float _t13, float _sp1, float _t14, float _t12, float _sp2, float _t17, float _t16, float _t19, float _t28, int _props) {
        float[] _col0 = invert_degenerate_general_s0_tail_sda9ffc5_c0(_t27, _t2 * _t33_inv, _t14, _t17, _t16, _t13, _t28);
        float[] _col1 = invert_degenerate_general_s0_tail_sda9ffc5_c1(_t18, _t15, _t20, _t13, _sp1, _t19, _t17);
        float[] _col2 = invert_degenerate_general_s0_tail_sda9ffc5_c2(_t20, _t14, _t18, _t12, _sp2, _t16, _t19);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN). Shared by the identical private paths of {@code invert} and {@code invertProduct};
     * reached only through them.
     */
    private Float3x3 invert_degenerate_general() {
        float _t0 = unitScale(this.m10, this.m11, this.m12);
        float _t1 = unitScale(this.m20, this.m21, this.m22);
        float _t2 = unitScale(this.m00, this.m01, this.m02);
        float _t12 = this.m11 * _t0;
        float _t13 = this.m22 * _t1;
        float _t14 = this.m12 * _t0;
        float _t15 = this.m21 * _t1;
        float _t16 = this.m10 * _t0;
        float _t17 = this.m20 * _t1;
        float _t18 = this.m02 * _t2;
        float _t19 = this.m00 * _t2;
        float _t20 = this.m01 * _t2;
        float _t27 = Math.fma(_t12, _t13, -(_t14 * _t15));
        float _t28 = Math.fma(_t16, _t15, -(_t12 * _t17));
        float _t33_inv = 1.0f / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        return invert_degenerate_general_s0_tail(_t2, _t33_inv, _t27, _t18, _t15, _t20, _t13, _t0 * _t33_inv, _t14, _t12, _t1 * _t33_inv, _t17, _t16, _t19, _t28, 0);
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 invert_degenerate() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_degenerate_translation();
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_degenerate_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invert_degenerate_affine();
        return invert_degenerate_general();
    }

    /**
     * Private per-column body of {@code invertProduct_general_s55dc17e2_tail}. Shared by 8
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private float[] invertProduct_general_s55dc17e2_tail_s6cbd0da2_c1(float _t20, float _t22, float _t26, float _t19, float _t40_inv, float _t25, float _t24) {
        return new float[] {Math.fma(_t20, _t22, -(_t26 * _t19)) * _t40_inv, Math.fma(_t25, _t19, -(_t24 * _t22)) * _t40_inv, Math.fma(_t24, _t26, -(_t25 * _t20)) * _t40_inv};
    }

    /** Private tail of {@code invertProduct_general}; reached only through it. */
    private Float3x3 invertProduct_general_s55dc17e2_tail(Float3x3 other, float _t18, float _t19, float _t20, float _t21, float _t23, float _t24, float _t22, int _props) {
        float _t25 = Math.fma(other.m20(), this.m02, Math.fma(other.m00(), this.m00, other.m10() * this.m01));
        float _t26 = Math.fma(other.m21(), this.m02, Math.fma(other.m01(), this.m00, other.m11() * this.m01));
        float _t33 = Math.fma(_t18, _t19, -(_t20 * _t21));
        float _t34 = Math.fma(_t23, _t20, -(_t24 * _t18));
        float _t40 = Math.fma(_t22, _t34, Math.fma(_t25, _t33, -(_t26 * Math.fma(_t23, _t19, -(_t24 * _t21)))));
        if (!(java.lang.Math.abs(_t40) > 1.1754944E-38f && java.lang.Math.abs(_t40) < 8.507059E37f)) return null;
        float _t40_inv = 1.0f / _t40;
        float[] _col0 = invert_degenerate_general_s0_tail_sda9ffc5_c0(_t33, _t40_inv, _t24, _t21, _t23, _t19, _t34);
        float[] _col1 = invertProduct_general_s55dc17e2_tail_s6cbd0da2_c1(_t20, _t22, _t26, _t19, _t40_inv, _t25, _t24);
        float[] _col2 = invertProduct_general_s55dc17e2_tail_s6cbd0da2_c1(_t26, _t21, _t18, _t22, _t40_inv, _t23, _t25);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_general(Float3x3 other) {
        Float3x3 _r = invertProduct_general_s55dc17e2_tail(other, Math.fma(other.m21(), this.m12, Math.fma(other.m01(), this.m10, other.m11() * this.m11)), Math.fma(other.m22(), this.m22, Math.fma(other.m02(), this.m20, other.m12() * this.m21)), Math.fma(other.m21(), this.m22, Math.fma(other.m01(), this.m20, other.m11() * this.m21)), Math.fma(other.m22(), this.m12, Math.fma(other.m02(), this.m10, other.m12() * this.m11)), Math.fma(other.m20(), this.m12, Math.fma(other.m00(), this.m10, other.m10() * this.m11)), Math.fma(other.m20(), this.m22, Math.fma(other.m00(), this.m20, other.m10() * this.m21)), Math.fma(other.m22(), this.m02, Math.fma(other.m02(), this.m00, other.m12() * this.m01)), 0);
        return _r != null ? _r : invertProduct_degenerate(other);
    }

    /** Private per-column body of {@code invertProduct_identity}; reached only through it. */
    private float[] invertProduct_identity_s55dc17e2_c0(float _t6, float _t13_inv, Float3x3 other, float _t7) {
        return new float[] {_t6 * _t13_inv, Math.fma(other.m12(), other.m20(), -(other.m10() * other.m22())) * _t13_inv, _t7 * _t13_inv};
    }

    /** Private per-column body of {@code invertProduct_identity}; reached only through it. */
    private float[] invertProduct_identity_s55dc17e2_c1(Float3x3 other, float _t13_inv) {
        return new float[] {Math.fma(other.m02(), other.m21(), -(other.m01() * other.m22())) * _t13_inv, Math.fma(other.m00(), other.m22(), -(other.m02() * other.m20())) * _t13_inv, Math.fma(other.m01(), other.m20(), -(other.m00() * other.m21())) * _t13_inv};
    }

    /** Private per-column body of {@code invertProduct_identity}; reached only through it. */
    private float[] invertProduct_identity_s55dc17e2_c2(Float3x3 other, float _t13_inv) {
        return new float[] {Math.fma(other.m01(), other.m12(), -(other.m02() * other.m11())) * _t13_inv, Math.fma(other.m02(), other.m10(), -(other.m00() * other.m12())) * _t13_inv, Math.fma(other.m00(), other.m11(), -(other.m01() * other.m10())) * _t13_inv};
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_identity(Float3x3 other) {
        float _t6 = Math.fma(other.m11(), other.m22(), -(other.m12() * other.m21()));
        float _t7 = Math.fma(other.m10(), other.m21(), -(other.m11() * other.m20()));
        float _t13 = Math.fma(other.m02(), _t7, Math.fma(other.m00(), _t6, -(other.m01() * Math.fma(other.m10(), other.m22(), -(other.m12() * other.m20())))));
        if (!(java.lang.Math.abs(_t13) > 1.1754944E-38f && java.lang.Math.abs(_t13) < 8.507059E37f)) return invertProduct_degenerate(other);
        float _t13_inv = 1.0f / _t13;
        float[] _col0 = invertProduct_identity_s55dc17e2_c0(_t6, _t13_inv, other, _t7);
        float[] _col1 = invertProduct_identity_s55dc17e2_c1(other, _t13_inv);
        float[] _col2 = invertProduct_identity_s55dc17e2_c2(other, _t13_inv);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], other.properties());
    }

    /**
     * Private per-column body of {@code invertProduct_translation}. Shared by 2 identical private
     * paths of {@code invertProduct}; reached only through it.
     */
    private float[] invertProduct_translation_s55dc17e2_c0(float _t12, float _t19_inv, Float3x3 other, float _t1, float _t3, float _t13) {
        return new float[] {_t12 * _t19_inv, Math.fma(other.m20(), _t1, -(other.m22() * _t3)) * _t19_inv, _t13 * _t19_inv};
    }

    /**
     * Private per-column body of {@code invertProduct_translation}. Shared by 2 identical private
     * paths of {@code invertProduct}; reached only through it.
     */
    private float[] invertProduct_translation_s55dc17e2_c1(Float3x3 other, float _t2, float _t5, float _t19_inv, float _t4) {
        return new float[] {Math.fma(other.m21(), _t2, -(other.m22() * _t5)) * _t19_inv, Math.fma(other.m22(), _t4, -(other.m20() * _t2)) * _t19_inv, Math.fma(other.m20(), _t5, -(other.m21() * _t4)) * _t19_inv};
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_translation(Float3x3 other) {
        float _t0 = Math.fma(other.m21(), this.m12, other.m11());
        float _t1 = Math.fma(other.m22(), this.m12, other.m12());
        float _t2 = Math.fma(other.m22(), this.m02, other.m02());
        float _t3 = Math.fma(other.m20(), this.m12, other.m10());
        float _t4 = Math.fma(other.m20(), this.m02, other.m00());
        float _t5 = Math.fma(other.m21(), this.m02, other.m01());
        float _t12 = Math.fma(other.m22(), _t0, -(other.m21() * _t1));
        float _t13 = Math.fma(other.m21(), _t3, -(other.m20() * _t0));
        float _t19 = Math.fma(_t2, _t13, Math.fma(_t4, _t12, -(_t5 * Math.fma(other.m22(), _t3, -(other.m20() * _t1)))));
        if (!(java.lang.Math.abs(_t19) > 1.1754944E-38f && java.lang.Math.abs(_t19) < 8.507059E37f)) return invertProduct_degenerate(other);
        float _t19_inv = 1.0f / _t19;
        float[] _col0 = invertProduct_translation_s55dc17e2_c0(_t12, _t19_inv, other, _t1, _t3, _t13);
        float[] _col1 = invertProduct_translation_s55dc17e2_c1(other, _t2, _t5, _t19_inv, _t4);
        float[] _col2 = invert_degenerate_general_s0_tail_sda9ffc5_c2(_t5, _t1, _t2, _t0, _t19_inv, _t3, _t4);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], Joml.BIT_TRANSLATION & other.properties());
    }

    /** Private tail of {@code invertProduct_orthogonal}; reached only through it. */
    private Float3x3 invertProduct_orthogonal_s6f0f1b18_tail(float _t14, float _t25, float _t16, float _t24, float _t17, Float3x3 other, float _t15, float _t13, float _t12, int _props) {
        float _t31 = Math.fma(_t14, _t25, Math.fma(_t16, _t24, -(_t17 * Math.fma(other.m22(), _t15, -(other.m20() * _t13)))));
        if (!(java.lang.Math.abs(_t31) > 1.1754944E-38f && java.lang.Math.abs(_t31) < 8.507059E37f)) return null;
        float _t31_inv = 1.0f / _t31;
        float[] _col0 = invertProduct_translation_s55dc17e2_c0(_t24, _t31_inv, other, _t13, _t15, _t25);
        float[] _col1 = invertProduct_translation_s55dc17e2_c1(other, _t14, _t17, _t31_inv, _t16);
        float[] _col2 = invertProduct_general_s55dc17e2_tail_s6cbd0da2_c1(_t17, _t13, _t12, _t14, _t31_inv, _t15, _t16);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_orthogonal(Float3x3 other, int _props) {
        float _t12 = Math.fma(other.m21(), this.m12, Math.fma(other.m01(), this.m10, other.m11() * this.m11));
        float _t13 = Math.fma(other.m22(), this.m12, Math.fma(other.m02(), this.m10, other.m12() * this.m11));
        float _t15 = Math.fma(other.m20(), this.m12, Math.fma(other.m00(), this.m10, other.m10() * this.m11));
        Float3x3 _r = invertProduct_orthogonal_s6f0f1b18_tail(Math.fma(other.m22(), this.m02, Math.fma(other.m02(), this.m00, other.m12() * this.m01)), Math.fma(other.m21(), _t15, -(other.m20() * _t12)), Math.fma(other.m20(), this.m02, Math.fma(other.m00(), this.m00, other.m10() * this.m01)), Math.fma(other.m22(), _t12, -(other.m21() * _t13)), Math.fma(other.m21(), this.m02, Math.fma(other.m01(), this.m00, other.m11() * this.m01)), other, _t15, _t13, _t12, _props);
        return _r != null ? _r : invertProduct_degenerate(other);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_identity_translation(Float3x3 other) {
        return new Float3x3(1.0f, 0.0f, -other.m02(), 0.0f, 1.0f, -other.m12(), 0.0f, 0.0f, 1.0f, other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_identity_affine(Float3x3 other) {
        float _t3 = Math.fma(other.m00(), other.m11(), -(other.m01() * other.m10()));
        if (!(java.lang.Math.abs(_t3) > 1.1754944E-38f && java.lang.Math.abs(_t3) < 8.507059E37f)) return invertProduct_degenerate(other);
        float _t3_inv = 1.0f / _t3;
        return new Float3x3(other.m11() * _t3_inv, -(other.m01() * _t3_inv), Math.fma(other.m01(), other.m12(), -(other.m02() * other.m11())) * _t3_inv, -(other.m10() * _t3_inv), other.m00() * _t3_inv, Math.fma(other.m02(), other.m10(), -(other.m00() * other.m12())) * _t3_inv, 0.0f, 0.0f, 1.0f, other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_translation_identity(Float3x3 other) {
        return new Float3x3(1.0f, 0.0f, -this.m02, 0.0f, 1.0f, -this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_translation_translation(Float3x3 other) {
        return new Float3x3(1.0f, 0.0f, -(other.m02() + this.m02), 0.0f, 1.0f, -(other.m12() + this.m12), 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_translation_affine(Float3x3 other) {
        float _t1 = other.m12() + this.m12;
        float _t2 = other.m02() + this.m02;
        float _t5 = Math.fma(other.m00(), other.m11(), -(other.m01() * other.m10()));
        if (!(java.lang.Math.abs(_t5) > 1.1754944E-38f && java.lang.Math.abs(_t5) < 8.507059E37f)) return invertProduct_degenerate(other);
        float _t5_inv = 1.0f / _t5;
        return new Float3x3(other.m11() * _t5_inv, -(other.m01() * _t5_inv), Math.fma(other.m01(), _t1, -(other.m11() * _t2)) * _t5_inv, -(other.m10() * _t5_inv), other.m00() * _t5_inv, Math.fma(other.m10(), _t2, -(other.m00() * _t1)) * _t5_inv, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_orthogonal_identity(Float3x3 other) {
        return new Float3x3(this.m11, this.m10, Math.fma(-this.m02, this.m11, -(this.m10 * this.m12)), -this.m10, this.m11, Math.fma(this.m02, this.m10, -(this.m11 * this.m12)), 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_orthogonal_translation(Float3x3 other) {
        float _t0 = -this.m10;
        return new Float3x3(this.m11, this.m10, Math.fma(_t0, this.m12, Math.fma(-this.m02, this.m11, -other.m02())), _t0, this.m11, Math.fma(this.m02, this.m10, Math.fma(-this.m11, this.m12, -other.m12())), 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_orthogonal_affine(Float3x3 other, int _props) {
        float _t6 = Math.fma(other.m01(), this.m10, other.m11() * this.m11);
        float _t7 = Math.fma(other.m00(), this.m00, other.m10() * this.m01);
        float _t8 = Math.fma(other.m00(), this.m10, other.m10() * this.m11);
        float _t9 = Math.fma(other.m01(), this.m00, other.m11() * this.m01);
        float _t10 = Math.fma(other.m02(), this.m10, Math.fma(other.m12(), this.m11, this.m12));
        float _t11 = Math.fma(other.m02(), this.m00, Math.fma(other.m12(), this.m01, this.m02));
        float _t15 = Math.fma(_t7, _t6, -(_t8 * _t9));
        if (!(java.lang.Math.abs(_t15) > 1.1754944E-38f && java.lang.Math.abs(_t15) < 8.507059E37f)) return invertProduct_degenerate(other);
        float _t15_inv = 1.0f / _t15;
        return new Float3x3(_t6 * _t15_inv, -(_t9 * _t15_inv), Math.fma(_t10, _t9, -(_t11 * _t6)) * _t15_inv, -(_t8 * _t15_inv), _t7 * _t15_inv, Math.fma(_t11, _t8, -(_t10 * _t7)) * _t15_inv, 0.0f, 0.0f, 1.0f, _props);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_affine_identity(Float3x3 other) {
        float _t3 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(java.lang.Math.abs(_t3) > 1.1754944E-38f && java.lang.Math.abs(_t3) < 8.507059E37f)) return invertProduct_degenerate(other);
        float _t3_inv = 1.0f / _t3;
        return new Float3x3(this.m11 * _t3_inv, -(this.m01 * _t3_inv), Math.fma(this.m01, this.m12, -(this.m02 * this.m11)) * _t3_inv, -(this.m10 * _t3_inv), this.m00 * _t3_inv, Math.fma(this.m02, this.m10, -(this.m00 * this.m12)) * _t3_inv, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_affine_translation(Float3x3 other) {
        float _t5 = Math.fma(other.m02(), this.m10, Math.fma(other.m12(), this.m11, this.m12));
        float _t6 = Math.fma(other.m02(), this.m00, Math.fma(other.m12(), this.m01, this.m02));
        float _t7 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(java.lang.Math.abs(_t7) > 1.1754944E-38f && java.lang.Math.abs(_t7) < 8.507059E37f)) return invertProduct_degenerate(other);
        float _t7_inv = 1.0f / _t7;
        return new Float3x3(this.m11 * _t7_inv, -(this.m01 * _t7_inv), Math.fma(this.m01, _t5, -(this.m11 * _t6)) * _t7_inv, -(this.m10 * _t7_inv), this.m00 * _t7_inv, Math.fma(this.m10, _t6, -(this.m00 * _t5)) * _t7_inv, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_general_identity(Float3x3 other) {
        float _t6 = Math.fma(this.m11, this.m22, -(this.m12 * this.m21));
        float _t7 = Math.fma(this.m10, this.m21, -(this.m11 * this.m20));
        float _t13 = Math.fma(this.m02, _t7, Math.fma(this.m00, _t6, -(this.m01 * Math.fma(this.m10, this.m22, -(this.m12 * this.m20)))));
        if (!(java.lang.Math.abs(_t13) > 1.1754944E-38f && java.lang.Math.abs(_t13) < 8.507059E37f)) return invertProduct_degenerate(other);
        float _t13_inv = 1.0f / _t13;
        float[] _col0 = invert_general_s0_c0(_t6, _t13_inv, _t7);
        float[] _col1 = invert_general_s0_c1(_t13_inv);
        float[] _col2 = invert_general_s0_c2(_t13_inv);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }

    /** Private per-column body of {@code invertProduct_general_translation}; reached only through it. */
    private float[] invertProduct_general_translation_s55dc17e2_c0(float _t13, float _t19_inv, float _t7, float _t6, float _t5) {
        return new float[] {_t13 * _t19_inv, Math.fma(this.m20, _t7, -(this.m10 * _t6)) * _t19_inv, _t5 * _t19_inv};
    }

    /** Private per-column body of {@code invertProduct_general_translation}; reached only through it. */
    private float[] invertProduct_general_translation_s55dc17e2_c1(float _t8, float _t6, float _t19_inv) {
        return new float[] {Math.fma(this.m21, _t8, -(this.m01 * _t6)) * _t19_inv, Math.fma(this.m00, _t6, -(this.m20 * _t8)) * _t19_inv, Math.fma(this.m01, this.m20, -(this.m00 * this.m21)) * _t19_inv};
    }

    /** Private per-column body of {@code invertProduct_general_translation}; reached only through it. */
    private float[] invertProduct_general_translation_s55dc17e2_c2(float _t7, float _t8, float _t19_inv) {
        return new float[] {Math.fma(this.m01, _t7, -(this.m11 * _t8)) * _t19_inv, Math.fma(this.m10, _t8, -(this.m00 * _t7)) * _t19_inv, Math.fma(this.m00, this.m11, -(this.m01 * this.m10)) * _t19_inv};
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_general_translation(Float3x3 other) {
        float _t5 = Math.fma(this.m10, this.m21, -(this.m11 * this.m20));
        float _t6 = Math.fma(other.m02(), this.m20, Math.fma(other.m12(), this.m21, this.m22));
        float _t7 = Math.fma(other.m02(), this.m10, Math.fma(other.m12(), this.m11, this.m12));
        float _t8 = Math.fma(other.m02(), this.m00, Math.fma(other.m12(), this.m01, this.m02));
        float _t13 = Math.fma(this.m11, _t6, -(this.m21 * _t7));
        float _t19 = Math.fma(_t8, _t5, Math.fma(this.m00, _t13, -(this.m01 * Math.fma(this.m10, _t6, -(this.m20 * _t7)))));
        if (!(java.lang.Math.abs(_t19) > 1.1754944E-38f && java.lang.Math.abs(_t19) < 8.507059E37f)) return invertProduct_degenerate(other);
        float _t19_inv = 1.0f / _t19;
        float[] _col0 = invertProduct_general_translation_s55dc17e2_c0(_t13, _t19_inv, _t7, _t6, _t5);
        float[] _col1 = invertProduct_general_translation_s55dc17e2_c1(_t8, _t6, _t19_inv);
        float[] _col2 = invertProduct_general_translation_s55dc17e2_c2(_t7, _t8, _t19_inv);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }

    /**
     * Private per-column body of {@code invertProduct_general_affine_s55dc17e2_tail}. Shared by 4
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private float[] invertProduct_general_affine_s55dc17e2_tail_s7228f69b_c1(float _t17, float _t10, float _t15, float _t14, float _t31_inv, float _t13, float _t12) {
        return new float[] {Math.fma(_t17, _t10, -(_t15 * _t14)) * _t31_inv, Math.fma(_t15, _t13, -(_t17 * _t12)) * _t31_inv, Math.fma(_t12, _t14, -(_t13 * _t10)) * _t31_inv};
    }

    /** Private tail of {@code invertProduct_general_affine}; reached only through it. */
    private Float3x3 invertProduct_general_affine_s55dc17e2_tail(float _t17, float _t24, float _t13, float _t25, float _t14, float _t15, float _t11, float _t16, float _t12, float _t10, float _t9, int _props) {
        float _t31 = Math.fma(_t17, _t24, Math.fma(_t13, _t25, -(_t14 * Math.fma(_t15, _t11, -(_t16 * _t12)))));
        if (!(java.lang.Math.abs(_t31) > 1.1754944E-38f && java.lang.Math.abs(_t31) < 8.507059E37f)) return null;
        float _t31_inv = 1.0f / _t31;
        float[] _col0 = invert_degenerate_general_s0_tail_sda9ffc5_c0(_t25, _t31_inv, _t16, _t12, _t15, _t11, _t24);
        float[] _col1 = invertProduct_general_affine_s55dc17e2_tail_s7228f69b_c1(_t17, _t10, _t15, _t14, _t31_inv, _t13, _t12);
        float[] _col2 = invertProduct_general_affine_s55dc17e2_tail_s7228f69b_c1(_t16, _t14, _t17, _t9, _t31_inv, _t11, _t13);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Float3x3 invertProduct_general_affine(Float3x3 other) {
        float _t9 = Math.fma(other.m01(), this.m10, other.m11() * this.m11);
        float _t10 = Math.fma(other.m01(), this.m20, other.m11() * this.m21);
        float _t11 = Math.fma(other.m00(), this.m10, other.m10() * this.m11);
        float _t12 = Math.fma(other.m00(), this.m20, other.m10() * this.m21);
        float _t15 = Math.fma(other.m02(), this.m20, Math.fma(other.m12(), this.m21, this.m22));
        float _t16 = Math.fma(other.m02(), this.m10, Math.fma(other.m12(), this.m11, this.m12));
        Float3x3 _r = invertProduct_general_affine_s55dc17e2_tail(Math.fma(other.m02(), this.m00, Math.fma(other.m12(), this.m01, this.m02)), Math.fma(_t11, _t10, -(_t12 * _t9)), Math.fma(other.m00(), this.m00, other.m10() * this.m01), Math.fma(_t15, _t9, -(_t16 * _t10)), Math.fma(other.m01(), this.m00, other.m11() * this.m01), _t15, _t11, _t16, _t12, _t10, _t9, 0);
        return _r != null ? _r : invertProduct_degenerate(other);
    }


    /**
     * Compute the inverse of the product of this matrix and {@code other}, i.e.
     * {@code (this * other)^-1}, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Float3x3 invertProduct(Float3x3 other) {
        int p = this.properties;
        int q = other.properties();
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, other.properties());
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_identity_translation(other);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_identity_affine(other);
            return invertProduct_identity(other);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_translation_identity(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_translation_translation(other);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_translation_affine(other);
            return invertProduct_translation(other);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_orthogonal_identity(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_orthogonal_translation(other);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_orthogonal_affine(other, Joml.BIT_ORTHOGONAL & q);
            return invertProduct_orthogonal(other, Joml.BIT_ORTHOGONAL & q);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_affine_identity(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_affine_translation(other);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_orthogonal_affine(other, Joml.BIT_AFFINE & q);
            return invertProduct_orthogonal(other, Joml.BIT_AFFINE & q);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_general_identity(other);
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_general_translation(other);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_general_affine(other);
        return invertProduct_general(other);
    }

    /** Private tail of {@code invertProduct}; reached only through it. */
    private Float3x3 invertProduct_s5cd2a89_tail(float _t23, float _t20, float _t24, float _t18, float _t22, float _t25, float _t33, float _t26, float _t19, float _t21, int _props) {
        float _t34 = Math.fma(_t23, _t20, -(_t24 * _t18));
        float _t40 = Math.fma(_t22, _t34, Math.fma(_t25, _t33, -(_t26 * Math.fma(_t23, _t19, -(_t24 * _t21)))));
        if (!(java.lang.Math.abs(_t40) > 1.1754944E-38f && java.lang.Math.abs(_t40) < 8.507059E37f)) return null;
        float _t40_inv = 1.0f / _t40;
        float[] _col0 = invert_degenerate_general_s0_tail_sda9ffc5_c0(_t33, _t40_inv, _t24, _t21, _t23, _t19, _t34);
        float[] _col1 = invertProduct_general_s55dc17e2_tail_s6cbd0da2_c1(_t20, _t22, _t26, _t19, _t40_inv, _t25, _t24);
        float[] _col2 = invertProduct_general_s55dc17e2_tail_s6cbd0da2_c1(_t26, _t21, _t18, _t22, _t40_inv, _t23, _t25);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Compute the inverse of the product of this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m10}, {@code m11}, {@code m12}, {@code m20}, {@code m21}, {@code m22}), returning the
     * result as a value.
     * <p>
     * The product is formed first and inverted afterwards, so the result is the inverse of the
     * rounded product: its accuracy is bounded by the condition number of the product, not by the
     * condition numbers of the two factors. For an ill-conditioned product (a near-singular factor,
     * or factors of very different scale) invert both factors separately and multiply the inverses
     * in reverse order instead.
     * <p>
     * Valid input: the product of this matrix and
     * {@code (m00, m01, m02, m10, m11, m12, m20, m21, m22)} must be invertible.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @return the resulting matrix
     */
    public Float3x3 invertProduct(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
        float _t18 = Math.fma(m21, this.m12, Math.fma(m01, this.m10, m11 * this.m11));
        float _t19 = Math.fma(m22, this.m22, Math.fma(m02, this.m20, m12 * this.m21));
        float _t20 = Math.fma(m21, this.m22, Math.fma(m01, this.m20, m11 * this.m21));
        float _t21 = Math.fma(m22, this.m12, Math.fma(m02, this.m10, m12 * this.m11));
        Float3x3 _r = invertProduct_s5cd2a89_tail(Math.fma(m20, this.m12, Math.fma(m00, this.m10, m10 * this.m11)), _t20, Math.fma(m20, this.m22, Math.fma(m00, this.m20, m10 * this.m21)), _t18, Math.fma(m22, this.m02, Math.fma(m02, this.m00, m12 * this.m01)), Math.fma(m20, this.m02, Math.fma(m00, this.m00, m10 * this.m01)), Math.fma(_t18, _t19, -(_t20 * _t21)), Math.fma(m21, this.m02, Math.fma(m01, this.m00, m11 * this.m01)), _t19, _t21, 0);
        return _r != null ? _r : invertProduct_degenerate(m00, m01, m02, m10, m11, m12, m20, m21, m22);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_general(Float3x3 other) {
        float[] _bundle0 = invertProduct_degenerate_general_saf227b42_1(other);
        float[] _bundle1 = invertProduct_degenerate_general_saf227b42_2(other, _bundle0);
        float[] _bundle2 = invertProduct_degenerate_general_saf227b42_3(_bundle0[1], _bundle0[2], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle1[4], _bundle1[5], _bundle1[6], _bundle1[7], _bundle1[8], _bundle1[9]);
        return new Float3x3(_bundle2[0], _bundle2[1], _bundle2[2], _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], 0);
    }

    /** Part 1 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private float[] invertProduct_degenerate_general_saf227b42_1(Float3x3 other) {
        return new float[] {Math.fma(other.m21(), this.m12, Math.fma(other.m01(), this.m10, other.m11() * this.m11)), Math.fma(other.m20(), this.m12, Math.fma(other.m00(), this.m10, other.m10() * this.m11)), Math.fma(other.m22(), this.m12, Math.fma(other.m02(), this.m10, other.m12() * this.m11)), Math.fma(other.m22(), this.m22, Math.fma(other.m02(), this.m20, other.m12() * this.m21))};
    }

    /** Part 2 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private float[] invertProduct_degenerate_general_saf227b42_2(Float3x3 other, float[] _bundle0) {
        float _t18 = _bundle0[0];
        float _t21 = _bundle0[3];
        float _t22 = Math.fma(other.m20(), this.m22, Math.fma(other.m00(), this.m20, other.m10() * this.m21));
        float _t23 = Math.fma(other.m21(), this.m22, Math.fma(other.m01(), this.m20, other.m11() * this.m21));
        float _t24 = Math.fma(other.m20(), this.m02, Math.fma(other.m00(), this.m00, other.m10() * this.m01));
        float _t25 = Math.fma(other.m21(), this.m02, Math.fma(other.m01(), this.m00, other.m11() * this.m01));
        float _t26 = Math.fma(other.m22(), this.m02, Math.fma(other.m02(), this.m00, other.m12() * this.m01));
        float _t27 = unitScale(_bundle0[1], _t18, _bundle0[2]);
        float _t28 = unitScale(_t22, _t23, _t21);
        return new float[] {_t22, _t24, _t25, _t26, _t27, _t28, unitScale(_t24, _t25, _t26), _t18 * _t27, _t21 * _t28, _t23 * _t28};
    }

    /** Part 3 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private float[] invertProduct_degenerate_general_saf227b42_3(float _t19, float _t20, float _t22, float _t24, float _t25, float _t26, float _t27, float _t28, float _t29, float _t39, float _t40, float _t41) {
        float _t42 = _t20 * _t27;
        float _t43 = _t19 * _t27;
        float _t44 = _t22 * _t28;
        float _t45 = _t26 * _t29;
        float _t46 = _t24 * _t29;
        float _t47 = _t25 * _t29;
        float _t54 = Math.fma(_t39, _t40, -(_t41 * _t42));
        float _t55 = Math.fma(_t43, _t41, -(_t44 * _t39));
        float _t60_inv = 1.0f / Math.fma(_t55, _t45, Math.fma(_t54, _t46, -(Math.fma(_t43, _t40, -(_t44 * _t42)) * _t47)));
        float _sp2 = _t28 * _t60_inv;
        float _sp1 = _t27 * _t60_inv;
        float _sp0 = _t29 * _t60_inv;
        return new float[] {_t54 * _sp0, Math.fma(_t41, _t45, -(_t47 * _t40)) * _sp1, Math.fma(_t47, _t42, -(_t39 * _t45)) * _sp2, Math.fma(_t44, _t42, -(_t43 * _t40)) * _sp0, Math.fma(_t46, _t40, -(_t44 * _t45)) * _sp1, Math.fma(_t43, _t45, -(_t46 * _t42)) * _sp2, _t55 * _sp0, Math.fma(_t44, _t47, -(_t46 * _t41)) * _sp1, Math.fma(_t46, _t39, -(_t43 * _t47)) * _sp2};
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_identity(Float3x3 other) {
        float _t0 = unitScale(other.m10(), other.m11(), other.m12());
        float _t1 = unitScale(other.m20(), other.m21(), other.m22());
        float _t2 = unitScale(other.m00(), other.m01(), other.m02());
        float _t12 = other.m11() * _t0;
        float _t13 = other.m22() * _t1;
        float _t14 = other.m12() * _t0;
        float _t15 = other.m21() * _t1;
        float _t16 = other.m10() * _t0;
        float _t17 = other.m20() * _t1;
        float _t18 = other.m02() * _t2;
        float _t19 = other.m00() * _t2;
        float _t20 = other.m01() * _t2;
        float _t27 = Math.fma(_t12, _t13, -(_t14 * _t15));
        float _t28 = Math.fma(_t16, _t15, -(_t12 * _t17));
        float _t33_inv = 1.0f / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        return invert_degenerate_general_s0_tail(_t2, _t33_inv, _t27, _t18, _t15, _t20, _t13, _t0 * _t33_inv, _t14, _t12, _t1 * _t33_inv, _t17, _t16, _t19, _t28, Joml.BIT_ORTHOGONAL & other.properties());
    }

    /** Private tail of {@code invertProduct_degenerate_translation}; reached only through it. */
    private Float3x3 invertProduct_degenerate_translation_s55dc17e2_tail(float _t34, float _t24, float _t33, float _t25, float _t10, float _t23, float _t12, float _t22, float _t26, float _t0, float _t13, float _t14, float _t11, float _t21, int _props) {
        float _t39_inv = 1.0f / Math.fma(_t34, _t24, Math.fma(_t33, _t25, -(Math.fma(_t10, _t23, -(_t12 * _t22)) * _t26)));
        float[] _col0 = invert_degenerate_general_s0_tail_sda9ffc5_c0(_t33, _t14 * _t39_inv, _t12, _t22, _t10, _t23, _t34);
        float[] _col1 = invert_degenerate_general_s0_tail_sda9ffc5_c2(_t11, _t24, _t10, _t26, _t13 * _t39_inv, _t25, _t12);
        float[] _col2 = invert_degenerate_general_s0_tail_sda9ffc5_c2(_t26, _t22, _t24, _t21, _t0 * _t39_inv, _t23, _t25);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_translation(Float3x3 other) {
        float _t0 = unitScale(other.m20(), other.m21(), other.m22());
        float _t1 = Math.fma(other.m21(), this.m12, other.m11());
        float _t2 = Math.fma(other.m20(), this.m12, other.m10());
        float _t3 = Math.fma(other.m22(), this.m12, other.m12());
        float _t4 = Math.fma(other.m20(), this.m02, other.m00());
        float _t5 = Math.fma(other.m21(), this.m02, other.m01());
        float _t6 = Math.fma(other.m22(), this.m02, other.m02());
        float _t10 = other.m22() * _t0;
        float _t11 = other.m21() * _t0;
        float _t12 = other.m20() * _t0;
        float _t13 = unitScale(_t2, _t1, _t3);
        float _t14 = unitScale(_t4, _t5, _t6);
        float _t21 = _t1 * _t13;
        float _t22 = _t3 * _t13;
        float _t23 = _t2 * _t13;
        return invertProduct_degenerate_translation_s55dc17e2_tail(Math.fma(_t11, _t23, -(_t12 * _t21)), _t6 * _t14, Math.fma(_t10, _t21, -(_t11 * _t22)), _t4 * _t14, _t10, _t23, _t12, _t22, _t5 * _t14, _t0, _t13, _t14, _t11, _t21, Joml.BIT_ORTHOGONAL & other.properties());
    }

    /** Private tail of {@code invertProduct_degenerate_orthogonal}; reached only through it. */
    private Float3x3 invertProduct_degenerate_orthogonal_s6f0f1b18_tail(float _t20, float _t19, float _t21, float _t22, float _t23, float _t24, float _t16, float _t17, float _t18, float _t6, int _props) {
        float _t25 = unitScale(_t20, _t19, _t21);
        float _t26 = unitScale(_t22, _t23, _t24);
        float _t33 = _t19 * _t25;
        float _t34 = _t21 * _t25;
        float _t35 = _t20 * _t25;
        float _t36 = _t24 * _t26;
        float _t37 = _t22 * _t26;
        float _t38 = _t23 * _t26;
        float _t45 = Math.fma(_t16, _t33, -(_t17 * _t34));
        float _t46 = Math.fma(_t17, _t35, -(_t18 * _t33));
        float _t51_inv = 1.0f / Math.fma(_t46, _t36, Math.fma(_t45, _t37, -(Math.fma(_t16, _t35, -(_t18 * _t34)) * _t38)));
        float[] _col0 = invert_degenerate_general_s0_tail_sda9ffc5_c0(_t45, _t26 * _t51_inv, _t18, _t34, _t16, _t35, _t46);
        float[] _col1 = invert_degenerate_general_s0_tail_sda9ffc5_c2(_t17, _t36, _t16, _t38, _t25 * _t51_inv, _t37, _t18);
        float[] _col2 = invertProduct_general_s55dc17e2_tail_s6cbd0da2_c1(_t38, _t34, _t33, _t36, _t6 * _t51_inv, _t35, _t37);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_orthogonal(Float3x3 other, int _props) {
        float _t6 = unitScale(other.m20(), other.m21(), other.m22());
        return invertProduct_degenerate_orthogonal_s6f0f1b18_tail(Math.fma(other.m20(), this.m12, Math.fma(other.m00(), this.m10, other.m10() * this.m11)), Math.fma(other.m21(), this.m12, Math.fma(other.m01(), this.m10, other.m11() * this.m11)), Math.fma(other.m22(), this.m12, Math.fma(other.m02(), this.m10, other.m12() * this.m11)), Math.fma(other.m20(), this.m02, Math.fma(other.m00(), this.m00, other.m10() * this.m01)), Math.fma(other.m21(), this.m02, Math.fma(other.m01(), this.m00, other.m11() * this.m01)), Math.fma(other.m22(), this.m02, Math.fma(other.m02(), this.m00, other.m12() * this.m01)), other.m22() * _t6, other.m21() * _t6, other.m20() * _t6, _t6, _props);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_identity_translation(Float3x3 other) {
        float _t0 = unitScale(1.0f, 0.0f, other.m02());
        float _t1 = unitScale(0.0f, 1.0f, other.m12());
        float _t2_inv = 1.0f / _t0;
        float _t3_inv = 1.0f / _t1;
        return new Float3x3(_t0 * _t2_inv, 0.0f, -(other.m02() * _t0 * _t2_inv), 0.0f, _t1 * _t3_inv, -(other.m12() * _t1 * _t3_inv), 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_identity_affine(Float3x3 other) {
        float _t0 = unitScale(other.m10(), other.m11(), other.m12());
        float _t1 = unitScale(other.m00(), other.m01(), other.m02());
        float _t8 = other.m11() * _t0;
        float _t9 = other.m00() * _t1;
        float _t10 = other.m01() * _t1;
        float _t11 = other.m10() * _t0;
        float _t12 = other.m12() * _t0;
        float _t13 = other.m02() * _t1;
        float _t16_inv = 1.0f / Math.fma(_t9, _t8, -(_t10 * _t11));
        float _sp1 = _t0 * _t16_inv;
        float _sp0 = _t1 * _t16_inv;
        return new Float3x3(_t8 * _sp0, -(_t10 * _sp1), Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv, -(_t11 * _sp0), _t9 * _sp1, Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_translation_identity(Float3x3 other) {
        float _t0 = unitScale(1.0f, 0.0f, this.m02);
        float _t1 = unitScale(0.0f, 1.0f, this.m12);
        float _t2_inv = 1.0f / _t0;
        float _t3_inv = 1.0f / _t1;
        return new Float3x3(_t0 * _t2_inv, 0.0f, -(this.m02 * _t0 * _t2_inv), 0.0f, _t1 * _t3_inv, -(this.m12 * _t1 * _t3_inv), 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_translation_translation(Float3x3 other) {
        float _t0 = other.m02() + this.m02;
        float _t1 = other.m12() + this.m12;
        float _t2 = unitScale(1.0f, 0.0f, _t0);
        float _t3 = unitScale(0.0f, 1.0f, _t1);
        float _t4_inv = 1.0f / _t2;
        float _t5_inv = 1.0f / _t3;
        return new Float3x3(_t2 * _t4_inv, 0.0f, -(_t0 * _t2 * _t4_inv), 0.0f, _t3 * _t5_inv, -(_t1 * _t3 * _t5_inv), 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_translation_affine(Float3x3 other) {
        float _t0 = other.m12() + this.m12;
        float _t1 = other.m02() + this.m02;
        float _t2 = unitScale(other.m10(), other.m11(), _t0);
        float _t3 = unitScale(other.m00(), other.m01(), _t1);
        float _t8 = other.m11() * _t2;
        float _t9 = other.m00() * _t3;
        float _t10 = other.m01() * _t3;
        float _t11 = other.m10() * _t2;
        float _t14 = _t0 * _t2;
        float _t15 = _t1 * _t3;
        float _t18_inv = 1.0f / Math.fma(_t9, _t8, -(_t10 * _t11));
        float _sp1 = _t2 * _t18_inv;
        float _sp0 = _t3 * _t18_inv;
        return new Float3x3(_t8 * _sp0, -(_t10 * _sp1), Math.fma(_t10, _t14, -(_t8 * _t15)) * _t18_inv, -(_t11 * _sp0), _t9 * _sp1, Math.fma(_t11, _t15, -(_t9 * _t14)) * _t18_inv, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_orthogonal_identity(int _props) {
        float _t0 = unitScale(this.m10, this.m11, this.m12);
        float _t1 = unitScale(this.m00, this.m01, this.m02);
        float _t8 = this.m11 * _t0;
        float _t9 = this.m00 * _t1;
        float _t10 = this.m01 * _t1;
        float _t11 = this.m10 * _t0;
        float _t12 = this.m12 * _t0;
        float _t13 = this.m02 * _t1;
        float _t16_inv = 1.0f / Math.fma(_t9, _t8, -(_t10 * _t11));
        float _sp1 = _t0 * _t16_inv;
        float _sp0 = _t1 * _t16_inv;
        return new Float3x3(_t8 * _sp0, -(_t10 * _sp1), Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv, -(_t11 * _sp0), _t9 * _sp1, Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv, 0.0f, 0.0f, 1.0f, _props);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_orthogonal_translation(Float3x3 other, int _props) {
        float _t2 = Math.fma(other.m02(), this.m10, Math.fma(other.m12(), this.m11, this.m12));
        float _t3 = Math.fma(other.m02(), this.m00, Math.fma(other.m12(), this.m01, this.m02));
        float _t4 = unitScale(this.m10, this.m11, _t2);
        float _t5 = unitScale(this.m00, this.m01, _t3);
        float _t10 = this.m11 * _t4;
        float _t11 = this.m00 * _t5;
        float _t12 = this.m01 * _t5;
        float _t13 = this.m10 * _t4;
        float _t16 = _t2 * _t4;
        float _t17 = _t3 * _t5;
        float _t20_inv = 1.0f / Math.fma(_t11, _t10, -(_t12 * _t13));
        float _sp1 = _t4 * _t20_inv;
        float _sp0 = _t5 * _t20_inv;
        return new Float3x3(_t10 * _sp0, -(_t12 * _sp1), Math.fma(_t12, _t16, -(_t10 * _t17)) * _t20_inv, -(_t13 * _sp0), _t11 * _sp1, Math.fma(_t13, _t17, -(_t11 * _t16)) * _t20_inv, 0.0f, 0.0f, 1.0f, _props);
    }

    /** Private per-column body of {@code invertProduct_degenerate_orthogonal_affine_s6f0f1b18_tail}; reached only through it. */
    private float[] invertProduct_degenerate_orthogonal_affine_s6f0f1b18_tail_s7b289d02_c0(float _t18, float _sp0, float _t20) {
        return new float[] {_t18 * _sp0, -(_t20 * _sp0), 0.0f};
    }

    /** Private per-column body of {@code invertProduct_degenerate_orthogonal_affine_s6f0f1b18_tail}; reached only through it. */
    private float[] invertProduct_degenerate_orthogonal_affine_s6f0f1b18_tail_s7b289d02_c2(float _t24, float _t21, float _t25, float _t18, float _t28_inv, float _t20, float _t19) {
        return new float[] {Math.fma(_t24, _t21, -(_t25 * _t18)) * _t28_inv, Math.fma(_t25, _t20, -(_t24 * _t19)) * _t28_inv, 1.0f};
    }

    /** Private tail of {@code invertProduct_degenerate_orthogonal_affine}; reached only through it. */
    private Float3x3 invertProduct_degenerate_orthogonal_affine_s6f0f1b18_tail(float _t13, float _t28_inv, float _t18, float _t21, float _sp1, float _t24, float _t25, float _t20, float _t19, int _props) {
        float[] _col0 = invertProduct_degenerate_orthogonal_affine_s6f0f1b18_tail_s7b289d02_c0(_t18, _t13 * _t28_inv, _t20);
        float[] _col1 = new float[] {-(_t21 * _sp1), _t19 * _sp1, 0.0f};
        float[] _col2 = invertProduct_degenerate_orthogonal_affine_s6f0f1b18_tail_s7b289d02_c2(_t24, _t21, _t25, _t18, _t28_inv, _t20, _t19);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_orthogonal_affine(Float3x3 other, int _props) {
        float _t6 = Math.fma(other.m01(), this.m10, other.m11() * this.m11);
        float _t7 = Math.fma(other.m00(), this.m10, other.m10() * this.m11);
        float _t8 = Math.fma(other.m00(), this.m00, other.m10() * this.m01);
        float _t9 = Math.fma(other.m01(), this.m00, other.m11() * this.m01);
        float _t10 = Math.fma(other.m02(), this.m10, Math.fma(other.m12(), this.m11, this.m12));
        float _t11 = Math.fma(other.m02(), this.m00, Math.fma(other.m12(), this.m01, this.m02));
        float _t12 = unitScale(_t7, _t6, _t10);
        float _t13 = unitScale(_t8, _t9, _t11);
        float _t18 = _t6 * _t12;
        float _t19 = _t8 * _t13;
        float _t20 = _t7 * _t12;
        float _t21 = _t9 * _t13;
        float _t28_inv = 1.0f / Math.fma(_t19, _t18, -(_t20 * _t21));
        return invertProduct_degenerate_orthogonal_affine_s6f0f1b18_tail(_t13, _t28_inv, _t18, _t21, _t12 * _t28_inv, _t10 * _t12, _t11 * _t13, _t20, _t19, _props);
    }

    /** Private per-column body of {@code invertProduct_degenerate_general_translation_s55dc17e2_tail}; reached only through it. */
    private float[] invertProduct_degenerate_general_translation_s55dc17e2_tail_sbf90b87_c1(float _t16, float _t26, float _t20, float _t24, float _sp1, float _t19, float _t18) {
        return new float[] {Math.fma(_t16, _t26, -(_t20 * _t24)) * _sp1, Math.fma(_t19, _t24, -(_t18 * _t26)) * _sp1, Math.fma(_t20, _t18, -(_t19 * _t16)) * _sp1};
    }

    /** Private per-column body of {@code invertProduct_degenerate_general_translation_s55dc17e2_tail}; reached only through it. */
    private float[] invertProduct_degenerate_general_translation_s55dc17e2_tail_sbf90b87_c2(float _t20, float _t25, float _t15, float _t26, float _sp2, float _t17, float _t19) {
        return new float[] {Math.fma(_t20, _t25, -(_t15 * _t26)) * _sp2, Math.fma(_t17, _t26, -(_t19 * _t25)) * _sp2, Math.fma(_t19, _t15, -(_t20 * _t17)) * _sp2};
    }

    /** Private tail of {@code invertProduct_degenerate_general_translation}; reached only through it. */
    private Float3x3 invertProduct_degenerate_general_translation_s55dc17e2_tail(float _t33, float _t26, float _t34, float _t19, float _t17, float _t24, float _t18, float _t25, float _t20, float _t7, float _t6, float _t8, float _t16, float _t15, int _props) {
        float _t39_inv = 1.0f / Math.fma(_t33, _t26, Math.fma(_t34, _t19, -(Math.fma(_t17, _t24, -(_t18 * _t25)) * _t20)));
        float[] _col0 = invert_degenerate_general_s0_tail_sda9ffc5_c0(_t34, _t8 * _t39_inv, _t18, _t25, _t17, _t24, _t33);
        float[] _col1 = invertProduct_degenerate_general_translation_s55dc17e2_tail_sbf90b87_c1(_t16, _t26, _t20, _t24, _t6 * _t39_inv, _t19, _t18);
        float[] _col2 = invertProduct_degenerate_general_translation_s55dc17e2_tail_sbf90b87_c2(_t20, _t25, _t15, _t26, _t7 * _t39_inv, _t17, _t19);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_general_translation(Float3x3 other) {
        float _t3 = Math.fma(other.m02(), this.m10, Math.fma(other.m12(), this.m11, this.m12));
        float _t4 = Math.fma(other.m02(), this.m20, Math.fma(other.m12(), this.m21, this.m22));
        float _t5 = Math.fma(other.m02(), this.m00, Math.fma(other.m12(), this.m01, this.m02));
        float _t6 = unitScale(this.m10, this.m11, _t3);
        float _t7 = unitScale(this.m20, this.m21, _t4);
        float _t8 = unitScale(this.m00, this.m01, _t5);
        float _t15 = this.m11 * _t6;
        float _t16 = this.m21 * _t7;
        float _t17 = this.m10 * _t6;
        float _t18 = this.m20 * _t7;
        float _t24 = _t4 * _t7;
        float _t25 = _t3 * _t6;
        return invertProduct_degenerate_general_translation_s55dc17e2_tail(Math.fma(_t17, _t16, -(_t15 * _t18)), _t5 * _t8, Math.fma(_t15, _t24, -(_t16 * _t25)), this.m00 * _t8, _t17, _t24, _t18, _t25, this.m01 * _t8, _t7, _t6, _t8, _t16, _t15, 0);
    }

    /** Private tail of {@code invertProduct_degenerate_general_affine}; reached only through it. */
    private Float3x3 invertProduct_degenerate_general_affine_s55dc17e2_tail(float _t10, float _t18, float _t12, float _t19, float _t9, float _t13, float _t20, float _t14, float _t15, float _t16, float _t17, float _t27, int _props) {
        float _t28 = _t10 * _t18;
        float _t29 = _t12 * _t19;
        float _t30 = _t9 * _t18;
        float _t31 = _t13 * _t20;
        float _t32 = _t14 * _t20;
        float _t36 = _t15 * _t18;
        float _t37 = _t16 * _t19;
        float _t38 = _t17 * _t20;
        float _t45 = Math.fma(_t29, _t28, -(_t30 * _t27));
        float _t46 = Math.fma(_t36, _t27, -(_t37 * _t28));
        float _t51_inv = 1.0f / Math.fma(_t45, _t38, Math.fma(_t46, _t31, -(Math.fma(_t36, _t29, -(_t37 * _t30)) * _t32)));
        float[] _col0 = invert_degenerate_general_s0_tail_sda9ffc5_c0(_t46, _t20 * _t51_inv, _t37, _t30, _t36, _t29, _t45);
        float[] _col1 = invertProduct_general_affine_s55dc17e2_tail_s7228f69b_c1(_t38, _t28, _t36, _t32, _t19 * _t51_inv, _t31, _t30);
        float[] _col2 = invertProduct_general_affine_s55dc17e2_tail_s7228f69b_c1(_t37, _t32, _t38, _t27, _t18 * _t51_inv, _t29, _t31);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate_general_affine(Float3x3 other) {
        float _t9 = Math.fma(other.m00(), this.m20, other.m10() * this.m21);
        float _t10 = Math.fma(other.m01(), this.m20, other.m11() * this.m21);
        float _t11 = Math.fma(other.m01(), this.m10, other.m11() * this.m11);
        float _t12 = Math.fma(other.m00(), this.m10, other.m10() * this.m11);
        float _t13 = Math.fma(other.m00(), this.m00, other.m10() * this.m01);
        float _t14 = Math.fma(other.m01(), this.m00, other.m11() * this.m01);
        float _t15 = Math.fma(other.m02(), this.m20, Math.fma(other.m12(), this.m21, this.m22));
        float _t16 = Math.fma(other.m02(), this.m10, Math.fma(other.m12(), this.m11, this.m12));
        float _t17 = Math.fma(other.m02(), this.m00, Math.fma(other.m12(), this.m01, this.m02));
        float _t19 = unitScale(_t12, _t11, _t16);
        return invertProduct_degenerate_general_affine_s55dc17e2_tail(_t10, unitScale(_t9, _t10, _t15), _t12, _t19, _t9, _t13, unitScale(_t13, _t14, _t17), _t14, _t15, _t16, _t17, _t11 * _t19, 0);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate(Float3x3 other) {
        int p = this.properties;
        int q = other.properties();
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, other.properties());
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_identity_translation(other);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_identity_affine(other);
            return invertProduct_degenerate_identity(other);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_translation_identity(other);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_translation_translation(other);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_translation_affine(other);
            return invertProduct_degenerate_translation(other);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invertProduct_degenerate_orthogonal_identity(((p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE) & q);
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_orthogonal_translation(other, ((p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE) & q);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_orthogonal_affine(other, ((p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE) & q);
            return invertProduct_degenerate_orthogonal(other, ((p & Joml.UNIQUE_ORTHOGONAL) | Joml.BIT_AFFINE) & q);
        }
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return invert_degenerate_general();
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invertProduct_degenerate_general_translation(other);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invertProduct_degenerate_general_affine(other);
        return invertProduct_degenerate_general(other);
    }

    /** Private tail of {@code invertProduct_degenerate}; reached only through it. */
    private Float3x3 invertProduct_degenerate_s5cd2a89_tail(float _t22, float _t23, float _t21, float _t24, float _t25, float _t26, float _t18, float _t27, float _t20, float _t19, int _props) {
        float _t28 = unitScale(_t22, _t23, _t21);
        float _t29 = unitScale(_t24, _t25, _t26);
        float _t39 = _t18 * _t27;
        float _t40 = _t21 * _t28;
        float _t41 = _t23 * _t28;
        float _t42 = _t20 * _t27;
        float _t43 = _t19 * _t27;
        float _t44 = _t22 * _t28;
        float _t45 = _t26 * _t29;
        float _t46 = _t24 * _t29;
        float _t47 = _t25 * _t29;
        float _t54 = Math.fma(_t39, _t40, -(_t41 * _t42));
        float _t55 = Math.fma(_t43, _t41, -(_t44 * _t39));
        float _t60_inv = 1.0f / Math.fma(_t55, _t45, Math.fma(_t54, _t46, -(Math.fma(_t43, _t40, -(_t44 * _t42)) * _t47)));
        float[] _col0 = invert_degenerate_general_s0_tail_sda9ffc5_c0(_t54, _t29 * _t60_inv, _t44, _t42, _t43, _t40, _t55);
        float[] _col1 = invertProduct_general_s55dc17e2_tail_s6cbd0da2_c1(_t41, _t45, _t47, _t40, _t27 * _t60_inv, _t46, _t44);
        float[] _col2 = invertProduct_general_s55dc17e2_tail_s6cbd0da2_c1(_t47, _t42, _t39, _t45, _t28 * _t60_inv, _t43, _t46);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Float3x3 invertProduct_degenerate(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
        float _t18 = Math.fma(m21, this.m12, Math.fma(m01, this.m10, m11 * this.m11));
        float _t19 = Math.fma(m20, this.m12, Math.fma(m00, this.m10, m10 * this.m11));
        float _t20 = Math.fma(m22, this.m12, Math.fma(m02, this.m10, m12 * this.m11));
        return invertProduct_degenerate_s5cd2a89_tail(Math.fma(m20, this.m22, Math.fma(m00, this.m20, m10 * this.m21)), Math.fma(m21, this.m22, Math.fma(m01, this.m20, m11 * this.m21)), Math.fma(m22, this.m22, Math.fma(m02, this.m20, m12 * this.m21)), Math.fma(m20, this.m02, Math.fma(m00, this.m00, m10 * this.m01)), Math.fma(m21, this.m02, Math.fma(m01, this.m00, m11 * this.m01)), Math.fma(m22, this.m02, Math.fma(m02, this.m00, m12 * this.m01)), _t18, unitScale(_t19, _t18, _t20), _t20, _t19, 0);
    }


    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Float3x3 normal_affine() {
        float _t3 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(java.lang.Math.abs(_t3) > 1.1754944E-38f && java.lang.Math.abs(_t3) < 8.507059E37f)) return normal_degenerate();
        float _t3_inv = 1.0f / _t3;
        return new Float3x3(this.m11 * _t3_inv, -(this.m10 * _t3_inv), 0.0f, -(this.m01 * _t3_inv), this.m00 * _t3_inv, 0.0f, Math.fma(this.m01, this.m12, -(this.m02 * this.m11)) * _t3_inv, Math.fma(this.m02, this.m10, -(this.m00 * this.m12)) * _t3_inv, 1.0f, 0);
    }

    /** Private per-column body of {@code normal_general}; reached only through it. */
    private float[] normal_general_s0_c0(float _t6, float _t13_inv) {
        return new float[] {_t6 * _t13_inv, Math.fma(this.m02, this.m21, -(this.m01 * this.m22)) * _t13_inv, Math.fma(this.m01, this.m12, -(this.m02 * this.m11)) * _t13_inv};
    }

    /** Private per-column body of {@code normal_general}; reached only through it. */
    private float[] normal_general_s0_c1(float _t13_inv) {
        return new float[] {Math.fma(this.m12, this.m20, -(this.m10 * this.m22)) * _t13_inv, Math.fma(this.m00, this.m22, -(this.m02 * this.m20)) * _t13_inv, Math.fma(this.m02, this.m10, -(this.m00 * this.m12)) * _t13_inv};
    }

    /** Private per-column body of {@code normal_general}; reached only through it. */
    private float[] normal_general_s0_c2(float _t7, float _t13_inv) {
        return new float[] {_t7 * _t13_inv, Math.fma(this.m01, this.m20, -(this.m00 * this.m21)) * _t13_inv, Math.fma(this.m00, this.m11, -(this.m01 * this.m10)) * _t13_inv};
    }


    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Float3x3 normal_general() {
        float _t6 = Math.fma(this.m11, this.m22, -(this.m12 * this.m21));
        float _t7 = Math.fma(this.m10, this.m21, -(this.m11 * this.m20));
        float _t13 = Math.fma(this.m02, _t7, Math.fma(this.m00, _t6, -(this.m01 * Math.fma(this.m10, this.m22, -(this.m12 * this.m20)))));
        if (!(java.lang.Math.abs(_t13) > 1.1754944E-38f && java.lang.Math.abs(_t13) < 8.507059E37f)) return normal_degenerate();
        float _t13_inv = 1.0f / _t13;
        float[] _col0 = normal_general_s0_c0(_t6, _t13_inv);
        float[] _col1 = normal_general_s0_c1(_t13_inv);
        float[] _col2 = normal_general_s0_c2(_t7, _t13_inv);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Compute the normal matrix of this matrix, i.e. the transpose of its inverse, returning the
     * result as a value.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @return the resulting matrix
     */
    public Float3x3 normal() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, -this.m02, -this.m12, 1.0f, 0);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return cofactor_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return normal_affine();
        return normal_general();
    }


    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 normal_degenerate_translation() {
        float _t0 = unitScale(1.0f, 0.0f, this.m02);
        float _t1 = unitScale(0.0f, 1.0f, this.m12);
        float _t2_inv = 1.0f / _t0;
        float _t3_inv = 1.0f / _t1;
        return new Float3x3(_t0 * _t2_inv, 0.0f, 0.0f, 0.0f, _t1 * _t3_inv, 0.0f, -(this.m02 * _t0 * _t2_inv), -(this.m12 * _t1 * _t3_inv), 1.0f, 0);
    }


    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 normal_degenerate_orthogonal() {
        float _t0 = unitScale(this.m10, this.m11, this.m12);
        float _t1 = unitScale(this.m00, this.m01, this.m02);
        float _t8 = this.m11 * _t0;
        float _t9 = this.m00 * _t1;
        float _t10 = this.m01 * _t1;
        float _t11 = this.m10 * _t0;
        float _t12 = this.m12 * _t0;
        float _t13 = this.m02 * _t1;
        float _t16_inv = 1.0f / Math.fma(_t9, _t8, -(_t10 * _t11));
        float _sp1 = _t0 * _t16_inv;
        float _sp0 = _t1 * _t16_inv;
        return new Float3x3(_t8 * _sp0, -(_t11 * _sp0), 0.0f, -(_t10 * _sp1), _t9 * _sp1, 0.0f, Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv, Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv, 1.0f, 0);
    }

    /**
     * Private per-column body of {@code normal_degenerate_general_s0_tail}. Shared by 2 identical
     * private paths of {@code normal}; reached only through it.
     */
    private float[] normal_degenerate_general_s0_tail_s699de245_c0(float _t27, float _sp0, float _t18, float _t15, float _t20, float _t13, float _sp1, float _t14, float _t12, float _sp2) {
        return new float[] {_t27 * _sp0, Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1, Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2};
    }

    /** Private per-column body of {@code normal_degenerate_general_s0_tail}; reached only through it. */
    private float[] normal_degenerate_general_s0_tail_s699de245_c1(float _t14, float _t17, float _t16, float _t13, float _sp0, float _t19, float _t18, float _sp1, float _sp2) {
        return new float[] {Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0, Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1, Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2};
    }

    /** Private tail of {@code normal_degenerate_general}; reached only through it. */
    private Float3x3 normal_degenerate_general_s0_tail(float _t2, float _t33_inv, float _t27, float _t14, float _t17, float _t16, float _t13, float _t28, float _t18, float _t15, float _t20, float _sp1, float _t19, float _t12, float _sp2, int _props) {
        float _sp0 = _t2 * _t33_inv;
        float[] _col0 = normal_degenerate_general_s0_tail_s699de245_c0(_t27, _sp0, _t18, _t15, _t20, _t13, _sp1, _t14, _t12, _sp2);
        float[] _col1 = normal_degenerate_general_s0_tail_s699de245_c1(_t14, _t17, _t16, _t13, _sp0, _t19, _t18, _sp1, _sp2);
        float[] _col2 = normal_degenerate_general_s0_tail_s699de245_c0(_t28, _sp0, _t20, _t17, _t19, _t15, _sp1, _t12, _t16, _sp2);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 normal_degenerate_general() {
        float _t0 = unitScale(this.m10, this.m11, this.m12);
        float _t1 = unitScale(this.m20, this.m21, this.m22);
        float _t2 = unitScale(this.m00, this.m01, this.m02);
        float _t12 = this.m11 * _t0;
        float _t13 = this.m22 * _t1;
        float _t14 = this.m12 * _t0;
        float _t15 = this.m21 * _t1;
        float _t16 = this.m10 * _t0;
        float _t17 = this.m20 * _t1;
        float _t18 = this.m02 * _t2;
        float _t19 = this.m00 * _t2;
        float _t20 = this.m01 * _t2;
        float _t27 = Math.fma(_t12, _t13, -(_t14 * _t15));
        float _t28 = Math.fma(_t16, _t15, -(_t12 * _t17));
        float _t33_inv = 1.0f / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        return normal_degenerate_general_s0_tail(_t2, _t33_inv, _t27, _t14, _t17, _t16, _t13, _t28, _t18, _t15, _t20, _t0 * _t33_inv, _t19, _t12, _t1 * _t33_inv, 0);
    }


    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Float3x3 normal_degenerate() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return normal_degenerate_translation();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return normal_degenerate_orthogonal();
        return normal_degenerate_general();
    }


    /**
     * Compute the trace of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the trace of this matrix
     */
    public float trace() {
        return this.m22 + (this.m00 + this.m11);
    }


    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Float3x3 transpose_orthogonal() {
        return new Float3x3(this.m00, this.m10, 0.0f, this.m01, this.m11, 0.0f, this.m02, this.m12, 1.0f, 0);
    }


    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Float3x3 transpose_general() {
        return new Float3x3(this.m00, this.m10, this.m20, this.m01, this.m11, this.m21, this.m02, this.m12, this.m22, 0);
    }


    /**
     * Transpose this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 transpose() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, this.m02, this.m12, 1.0f, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return transpose_orthogonal();
        return transpose_general();
    }


    /**
     * Add {@code other} to this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to add
     * @return the resulting matrix
     */
    public Float3x3 add(Float3x3 other) {
        return new Float3x3(other.m00() + this.m00, other.m01() + this.m01, other.m02() + this.m02, other.m10() + this.m10, other.m11() + this.m11, other.m12() + this.m12, other.m20() + this.m20, other.m21() + this.m21, other.m22() + this.m22, 0);
    }


    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12},
     * {@code m20}, {@code m21}, {@code m22}) to this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @return the resulting matrix
     */
    public Float3x3 add(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
        return new Float3x3(m00 + this.m00, m01 + this.m01, m02 + this.m02, m10 + this.m10, m11 + this.m11, m12 + this.m12, m20 + this.m20, m21 + this.m21, m22 + this.m22, 0);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal(float scalar) {
        return new Float3x3(scalar * this.m00, scalar * this.m01, scalar * this.m02, scalar * this.m10, scalar * this.m11, scalar * this.m12, 0.0f, 0.0f, scalar, 0);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general(float scalar) {
        return new Float3x3(scalar * this.m00, scalar * this.m01, scalar * this.m02, scalar * this.m10, scalar * this.m11, scalar * this.m12, scalar * this.m20, scalar * this.m21, scalar * this.m22, 0);
    }


    /**
     * Multiply each component of this matrix by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return the resulting matrix
     */
    public Float3x3 mul(float scalar) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(scalar, 0.0f, 0.0f, 0.0f, scalar, 0.0f, 0.0f, 0.0f, scalar, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(scalar, 0.0f, scalar * this.m02, 0.0f, scalar, scalar * this.m12, 0.0f, 0.0f, scalar, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_orthogonal(scalar);
        return mul_general(scalar);
    }


    /**
     * Negate this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 negate() {
        return new Float3x3(-this.m00, -this.m01, -this.m02, -this.m10, -this.m11, -this.m12, -this.m20, -this.m21, -this.m22, 0);
    }


    /**
     * Subtract {@code other} from this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to subtract
     * @return the resulting matrix
     */
    public Float3x3 sub(Float3x3 other) {
        return new Float3x3(this.m00 - other.m00(), this.m01 - other.m01(), this.m02 - other.m02(), this.m10 - other.m10(), this.m11 - other.m11(), this.m12 - other.m12(), this.m20 - other.m20(), this.m21 - other.m21(), this.m22 - other.m22(), 0);
    }


    /**
     * Subtract ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12},
     * {@code m20}, {@code m21}, {@code m22}) from this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @return the resulting matrix
     */
    public Float3x3 sub(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
        return new Float3x3(this.m00 - m00, this.m01 - m01, this.m02 - m02, this.m10 - m10, this.m11 - m11, this.m12 - m12, this.m20 - m20, this.m21 - m21, this.m22 - m22, 0);
    }


    /**
     * Create a new matrix from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the matrix to copy
     * @return the resulting matrix
     */
    public Float3x3 set(Float3x3 v) {
        return v;
    }


    /**
     * Create a new matrix from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @return the resulting matrix
     */
    public Float3x3 set(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
        return new Float3x3(m00, m01, m02, m10, m11, m12, m20, m21, m22);
    }


    /**
     * Create a new matrix from the given 2x2 matrix, copying the overlapping cells and filling the
     * rest with identity.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to copy from
     * @return the resulting matrix
     */
    public Float3x3 set(Float2x2 m) {
        return new Float3x3(m.m00(), m.m01(), 0.0f, m.m10(), m.m11(), 0.0f, 0.0f, 0.0f, 1.0f);
    }


    /**
     * Create a new matrix from the given 2x3 matrix, copying the overlapping cells and filling the
     * rest with identity.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to copy from
     * @return the resulting matrix
     */
    public Float3x3 set(Float2x3 m) {
        return new Float3x3(m.m00(), m.m01(), m.m02(), m.m10(), m.m11(), m.m12(), 0.0f, 0.0f, 1.0f);
    }


    /**
     * Create a new matrix from the given 3x4 matrix, copying the overlapping cells and dropping the
     * rest.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to copy from
     * @return the resulting matrix
     */
    public Float3x3 set(Float3x4 m) {
        return new Float3x3(m.m00(), m.m01(), m.m02(), m.m10(), m.m11(), m.m12(), m.m20(), m.m21(), m.m22());
    }


    /**
     * Create a new matrix from the given 4x4 matrix, copying the overlapping cells and dropping the
     * rest.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m the matrix to copy from
     * @return the resulting matrix
     */
    public Float3x3 set(Float4x4 m) {
        return new Float3x3(m.m00(), m.m01(), m.m02(), m.m10(), m.m11(), m.m12(), m.m20(), m.m21(), m.m22());
    }


    /**
     * Set the translation of this matrix, leaving every other element untouched.
     * <p>
     * Only the first two elements of the last column are written, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this overwrites the
     * translation instead of composing a translation onto the existing transformation.
     * <p>
     * The result is returned as a value; {@code this} is not modified.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param t the translation offsets
     * @return the resulting matrix
     */
    public Float3x3 withTranslation(Float2 t) {
        float tX = t.x();
        float tY = t.y();
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(1.0f, 0.0f, tX, 0.0f, 1.0f, tY, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Float3x3(this.m00, this.m01, tX, this.m10, this.m11, tY, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return new Float3x3(this.m00, this.m01, tX, this.m10, this.m11, tY, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        return withTranslation_general(tX, tY);
    }


    /**
     * Private body of {@code withTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code withTranslation} dispatcher.
     */
    private Float3x3 withTranslation_general(float tX, float tY) {
        return new Float3x3(this.m00, this.m01, tX, this.m10, this.m11, tY, this.m20, this.m21, this.m22, 0);
    }


    /**
     * Set the translation of this matrix, leaving every other element untouched.
     * <p>
     * Only the first two elements of the last column are written, so a square matrix keeps the
     * projective element that sits below them. Unlike {@code translate}, this overwrites the
     * translation instead of composing a translation onto the existing transformation.
     * <p>
     * The result is returned as a value; {@code this} is not modified.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param tX the {@code x} component of the translation offsets {@code (tX, tY)}
     * @param tY the {@code y} component of the translation offsets {@code (tX, tY)}
     * @return the resulting matrix
     */
    public Float3x3 withTranslation(float tX, float tY) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(1.0f, 0.0f, tX, 0.0f, 1.0f, tY, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Float3x3(this.m00, this.m01, tX, this.m10, this.m11, tY, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return new Float3x3(this.m00, this.m01, tX, this.m10, this.m11, tY, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        return withTranslation_general(tX, tY);
    }


    /**
     * Convert this matrix to {@code double} precision, returning the result as a new instance.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Double3x3} holding the result
     */
    public Double3x3 toDouble() {
        return new Double3x3(this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, this.m20, this.m21, this.m22);
    }


    /**
     * Create the given rigid transform's rotation block (the translation is dropped).
     * <p>
     * Valid input: the rotation of {@code r} must have unit length.
     *
     * @param r the rigid transform to convert
     * @return the resulting matrix
     */
    public static Float3x3 makeFromRigid(FloatRigid r) {
        float rRX = r.rX();
        float rRY = r.rY();
        float rRZ = r.rZ();
        float rRW = r.rW();
        float _t0 = rRZ * rRZ;
        float _t1 = rRZ * rRW;
        float _t2 = rRY * rRW;
        return new Float3x3(Math.fma(-2.0f, Math.fma(rRY, rRY, _t0), 1.0f), 2.0f * Math.fma(rRX, rRY, -_t1), 2.0f * Math.fma(rRX, rRZ, _t2), 2.0f * Math.fma(rRX, rRY, _t1), Math.fma(-2.0f, Math.fma(rRX, rRX, _t0), 1.0f), 2.0f * Math.fma(rRY, rRZ, -(rRX * rRW)), 2.0f * Math.fma(rRX, rRZ, -_t2), 2.0f * Math.fma(rRX, rRW, rRY * rRZ), Math.fma(-2.0f, Math.fma(rRX, rRX, rRY * rRY), 1.0f), 0);
    }


    /**
     * Create the given rigid transform's rotation block (the translation is dropped).
     * <p>
     * Valid input: {@code (rRX, rRY, rRZ, rRW)} must have unit length.
     *
     * @param rTX the {@code tX} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @param rTY the {@code tY} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @param rTZ the {@code tZ} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @param rRX the {@code rX} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @param rRY the {@code rY} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @param rRZ the {@code rZ} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @param rRW the {@code rW} component of the rigid transform
     *        {@code (rTX, rTY, rTZ, rRX, rRY, rRZ, rRW)}
     * @return the resulting matrix
     */
    public static Float3x3 makeFromRigid(float rTX, float rTY, float rTZ, float rRX, float rRY, float rRZ, float rRW) {
        float _t0 = rRZ * rRZ;
        float _t1 = rRZ * rRW;
        float _t2 = rRY * rRW;
        return new Float3x3(Math.fma(-2.0f, Math.fma(rRY, rRY, _t0), 1.0f), 2.0f * Math.fma(rRX, rRY, -_t1), 2.0f * Math.fma(rRX, rRZ, _t2), 2.0f * Math.fma(rRX, rRY, _t1), Math.fma(-2.0f, Math.fma(rRX, rRX, _t0), 1.0f), 2.0f * Math.fma(rRY, rRZ, -(rRX * rRW)), 2.0f * Math.fma(rRX, rRZ, -_t2), 2.0f * Math.fma(rRX, rRW, rRY * rRZ), Math.fma(-2.0f, Math.fma(rRX, rRX, rRY * rRY), 1.0f), 0);
    }


    /**
     * Create the given transform's linear block {@code R * S} (the translation is dropped).
     * <p>
     * Valid input: the rotation of {@code t} must have unit length.
     *
     * @param t the transform to convert
     * @return the resulting matrix
     */
    public static Float3x3 makeFromTransform(FloatTransform t) {
        float tRX = t.rX();
        float tRY = t.rY();
        float tRZ = t.rZ();
        float tRW = t.rW();
        float tSX = t.sX();
        float tSY = t.sY();
        float tSZ = t.sZ();
        float _t0 = tSX + tSX;
        float _t1 = tSY + tSY;
        float _t2 = tSZ + tSZ;
        float _t3 = tRZ * tRZ;
        float _t4 = tRZ * tRW;
        float _t5 = tRY * tRW;
        return new Float3x3(Math.fma(-Math.fma(tRY, tRY, _t3), _t0, tSX), Math.fma(tRX, tRY, -_t4) * _t1, Math.fma(tRX, tRZ, _t5) * _t2, Math.fma(tRX, tRY, _t4) * _t0, Math.fma(-Math.fma(tRX, tRX, _t3), _t1, tSY), Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2, Math.fma(tRX, tRZ, -_t5) * _t0, Math.fma(tRX, tRW, tRY * tRZ) * _t1, Math.fma(-Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ), 0);
    }


    /**
     * Create the given transform's linear block {@code R * S} (the translation is dropped).
     * <p>
     * Valid input: {@code (tRX, tRY, tRZ, tRW)} must have unit length.
     *
     * @param tTX the {@code tX} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tTY the {@code tY} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tTZ the {@code tZ} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tRX the {@code rX} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tRY the {@code rY} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tRZ the {@code rZ} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tRW the {@code rW} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tSX the {@code sX} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tSY the {@code sY} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @param tSZ the {@code sZ} component of the transform
     *        {@code (tTX, tTY, tTZ, tRX, tRY, tRZ, tRW, tSX, tSY, tSZ)}
     * @return the resulting matrix
     */
    public static Float3x3 makeFromTransform(float tTX, float tTY, float tTZ, float tRX, float tRY, float tRZ, float tRW, float tSX, float tSY, float tSZ) {
        float _t0 = tSX + tSX;
        float _t1 = tSY + tSY;
        float _t2 = tSZ + tSZ;
        float _t3 = tRZ * tRZ;
        float _t4 = tRZ * tRW;
        float _t5 = tRY * tRW;
        return new Float3x3(Math.fma(-Math.fma(tRY, tRY, _t3), _t0, tSX), Math.fma(tRX, tRY, -_t4) * _t1, Math.fma(tRX, tRZ, _t5) * _t2, Math.fma(tRX, tRY, _t4) * _t0, Math.fma(-Math.fma(tRX, tRX, _t3), _t1, tSY), Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2, Math.fma(tRX, tRZ, -_t5) * _t0, Math.fma(tRX, tRW, tRY * tRZ) * _t1, Math.fma(-Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ), 0);
    }


    /**
     * Private body of {@code to2x2}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x2} dispatcher.
     */
    private Float2x2 to2x2_general() {
        return new Float2x2(this.m00, this.m01, this.m10, this.m11, 0);
    }


    /**
     * Extract the upper-left 2x2 linear block of this matrix (dropping the translation column and
     * the last row), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float2x2 to2x2() {
        if ((this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float2x2(1.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_IDENTITY);
        return to2x2_general();
    }


    /**
     * Truncate this matrix to a 2x3 matrix, dropping the last row (assumed {@code 0, 0, 1}),
     * returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float2x3 to2x3() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float2x3(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float2x3(1.0f, 0.0f, this.m02, 0.0f, 1.0f, this.m12, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Float2x3(this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, Joml.BIT_ORTHOGONAL);
        return new Float2x3(this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code to3x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x4} dispatcher.
     */
    private Float3x4 to3x4_orthogonal() {
        return new Float3x4(this.m00, this.m01, this.m02, 0.0f, this.m10, this.m11, this.m12, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code to3x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x4} dispatcher.
     */
    private Float3x4 to3x4_general() {
        return new Float3x4(this.m00, this.m01, this.m02, 0.0f, this.m10, this.m11, this.m12, 0.0f, this.m20, this.m21, this.m22, 0.0f, Joml.BIT_AFFINE);
    }


    /**
     * Extend this matrix to a 3x4 matrix with a zero translation column, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x4 to3x4() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x4(1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x4(1.0f, 0.0f, this.m02, 0.0f, 0.0f, 1.0f, this.m12, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return to3x4_orthogonal();
        return to3x4_general();
    }


    /**
     * Private body of {@code to4x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to4x4} dispatcher.
     */
    private Float4x4 to4x4_orthogonal() {
        return new Float4x4(this.m00, this.m01, this.m02, 0.0f, this.m10, this.m11, this.m12, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code to4x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to4x4} dispatcher.
     */
    private Float4x4 to4x4_general() {
        return new Float4x4(this.m00, this.m01, this.m02, 0.0f, this.m10, this.m11, this.m12, 0.0f, this.m20, this.m21, this.m22, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Extend this matrix to a 4x4 matrix, filling the missing cells with identity, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float4x4 to4x4() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float4x4(1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float4x4(1.0f, 0.0f, this.m02, 0.0f, 0.0f, 1.0f, this.m12, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return to4x4_orthogonal();
        return to4x4_general();
    }

    /** Private tail of {@code toDualQuat_orthogonal}; reached only through it. */
    private FloatDualQuat toDualQuat_orthogonal_s0_tail(float _t9, float _sp1, float _t13, float _t0, float _sp2, float _t8, float _t5, float _sp0, float _t14, float _sp3, float _t7, float _t12, float _t11, float _sfx0) {
        float _sfx1, _sfx2, _sfx3;
        if (_t9 > 0.0f) {
            _sfx1 = _sp1 * _t13;
            _sfx2 = _sp3 * _t13;
            _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t11);
        } else {
            if (this.m00 > _t0) {
                _sfx1 = _sp2 * _t8;
                _sfx2 = _sp1 * _t8;
                _sfx3 = -(_sp0 * _t8);
            } else {
                if (this.m11 > 1.0f) {
                    _sfx1 = 0.5f * (float) java.lang.Math.sqrt(_t5);
                    _sfx2 = _sp0 * _t7;
                    _sfx3 = _sp1 * _t7;
                } else {
                    _sfx1 = _sp0 * _t14;
                    _sfx2 = 0.5f * (float) java.lang.Math.sqrt(_t12);
                    _sfx3 = _sp3 * _t14;
                }
            }
        }
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Private body of {@code toDualQuat}, specialized by runtime matrix properties; reached only
     * through the public {@code toDualQuat} dispatcher.
     */
    private FloatDualQuat toDualQuat_orthogonal() {
        float _sp1 = 0.5f * this.m02;
        float _sp0 = 0.5f * this.m12;
        float _t0 = java.lang.Math.max(this.m11, 1.0f);
        float _t3 = this.m00 - this.m11;
        float _sp2 = 0.5f * (this.m01 + this.m10);
        float _t5 = this.m11 - this.m00;
        float _t7 = (1.0f / (float) java.lang.Math.sqrt(_t5));
        float _t9 = 1.0f + (this.m00 + this.m11);
        float _t11 = 1.0f + _t9;
        float _t12 = 1.0f + (1.0f - this.m00 - this.m11);
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t11));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t12));
        float _sfx0 = _t9 > 0.0f ? -(_sp0 * _t13) : this.m00 > _t0 ? 0.5f * (float) java.lang.Math.sqrt(_t3) : this.m11 > 1.0f ? _sp2 * _t7 : _sp1 * _t14;
        return toDualQuat_orthogonal_s0_tail(_t9, _sp1, _t13, _t0, _sp2, (1.0f / (float) java.lang.Math.sqrt(_t3)), _t5, _sp0, _t14, 0.5f * (this.m10 - this.m01), _t7, _t12, _t11, _sfx0);
    }

    /** Private tail of {@code toDualQuat_general}; reached only through it. */
    private FloatDualQuat toDualQuat_general_s0_tail(float _t13, float _sp0, float _t3, float _t4, float _t15, float _sp1, float _t5, float _sp2, float _t6, float _t7, float _sp3, float _t16, float _t8, float _t9, float _t17, float _t14) {
        float _sfx0, _sfx1, _sfx2;
        if (_t13 > 0.0f) {
            _sfx0 = _sp0 * _t3;
            _sfx1 = _sp0 * _t7;
            _sfx2 = _sp0 * _t9;
        } else {
            if (this.m00 > _t4) {
                _sfx0 = 0.5f * (float) java.lang.Math.sqrt(_t15);
                _sfx1 = _sp3 * _t5;
                _sfx2 = _sp3 * _t6;
            } else {
                if (this.m11 > this.m22) {
                    _sfx0 = _sp1 * _t5;
                    _sfx1 = 0.5f * (float) java.lang.Math.sqrt(_t16);
                    _sfx2 = _sp1 * _t8;
                } else {
                    _sfx0 = _sp2 * _t6;
                    _sfx1 = _sp2 * _t8;
                    _sfx2 = 0.5f * (float) java.lang.Math.sqrt(_t17);
                }
            }
        }
        return toDualQuat_general_s0_tail2(_t13, _t14, _t4, _sp3, _t3, _sp1, _t7, _sp2, _t9, _sfx0, _sfx1, _sfx2);
    }

    /** Private tail of {@code toDualQuat_general}; reached only through it. */
    private FloatDualQuat toDualQuat_general_s0_tail2(float _t13, float _t14, float _t4, float _sp3, float _t3, float _sp1, float _t7, float _sp2, float _t9, float _sfx0, float _sfx1, float _sfx2) {
        float _sfx3 = _t13 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t14) : this.m00 > _t4 ? _sp3 * _t3 : this.m11 > this.m22 ? _sp1 * _t7 : _sp2 * _t9;
        return new FloatDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0f, 0.0f, 0.0f, 0.0f);
    }


    /**
     * Private body of {@code toDualQuat}, specialized by runtime matrix properties; reached only
     * through the public {@code toDualQuat} dispatcher.
     */
    private FloatDualQuat toDualQuat_general() {
        float _t1 = 1.0f - this.m00;
        float _t13 = this.m22 + (this.m00 + this.m11);
        float _t14 = 1.0f + _t13;
        float _t15 = this.m00 + (1.0f - this.m11 - this.m22);
        float _t16 = this.m11 + (_t1 - this.m22);
        float _t17 = this.m22 + (_t1 - this.m11);
        return toDualQuat_general_s0_tail(_t13, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), this.m21 - this.m12, java.lang.Math.max(this.m11, this.m22), _t15, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), this.m01 + this.m10, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), this.m02 + this.m20, this.m02 - this.m20, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)), _t16, this.m12 + this.m21, this.m10 - this.m01, _t17, _t14);
    }


    /**
     * Convert this matrix (assumed orthonormal) to a pure-rotation dual quaternion, returning the
     * result as a value.
     * <p>
     * Valid input: this matrix must be a rotation matrix.
     *
     * @return the resulting dual quaternion
     */
    public FloatDualQuat toDualQuat() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new FloatDualQuat(0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new FloatDualQuat(-(0.25f * this.m12), 0.25f * this.m02, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return toDualQuat_orthogonal();
        return toDualQuat_general();
    }


    /**
     * Private body of {@code toRigid}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code toRigid}; reached only through it.
     */
    private FloatRigid toRigid_identity() {
        return new FloatRigid(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }

    /**
     * Private tail of {@code toRigid_translation}. Shared by 2 identical private paths of
     * {@code toRigid}; reached only through it.
     */
    private FloatRigid toRigid_translation_s0_tail(float _t18, float _t13, float _sp0, float _t19, float _t8, float _t4, float _t11, float _t3, float _sp1, float _t15, float _t12, float _t16, float _t17) {
        float _t20 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        if (_t13 > 0.0f) {
            return new FloatRigid(0.0f, 0.0f, 0.0f, -(_sp0 * _t19), _sp1 * _t19, 0.0f, 0.5f * (float) java.lang.Math.sqrt(_t17));
        } else {
            if (_t8 > _t4) {
                return new FloatRigid(0.0f, 0.0f, 0.0f, 0.5f * (float) java.lang.Math.sqrt(_t11), 0.0f, _sp1 * _t12, -(_sp0 * _t12));
            } else {
                if (1.0f > _t3) {
                    return new FloatRigid(0.0f, 0.0f, 0.0f, 0.0f, 0.5f * (float) java.lang.Math.sqrt(_t15), _sp0 * _t16, _sp1 * _t16);
                } else {
                    return new FloatRigid(0.0f, 0.0f, 0.0f, _sp1 * _t20, _sp0 * _t20, 0.5f * (float) java.lang.Math.sqrt(_t18), 0.0f);
                }
            }
        }
    }


    /**
     * Private body of {@code toRigid}, specialized by runtime matrix properties; reached only
     * through the public {@code toRigid} dispatcher.
     */
    private FloatRigid toRigid_translation() {
        float _ct0 = Math.fma(this.m02, this.m02, Math.fma(this.m12, this.m12, 1.0f));
        if (!(_ct0 > 1.1754944E-38f && _ct0 < Float.POSITIVE_INFINITY)) return toRigid_degenerate();
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t8 = _t3 < 0.0f ? -1.0f : 1.0f;
        float _t11 = _t8 - _t3;
        float _t13 = 1.0f + _t8 + _t3;
        float _t15 = 2.0f - _t8 - _t3;
        float _t17 = 1.0f + _t13;
        return toRigid_translation_s0_tail(1.0f + _t3 - _t8 - 1.0f, _t13, 0.5f * this.m12 * _t3, (1.0f / (float) java.lang.Math.sqrt(_t17)), _t8, java.lang.Math.max(1.0f, _t3), _t11, _t3, 0.5f * this.m02 * _t3, _t15, (1.0f / (float) java.lang.Math.sqrt(_t11)), (1.0f / (float) java.lang.Math.sqrt(_t15)), _t17);
    }


    /**
     * Private body of {@code toRigid}, specialized by runtime matrix properties; reached only
     * through the public {@code toRigid} dispatcher.
     */
    private FloatRigid toRigid_general() {
        float _ct1 = Math.fma(this.m21, this.m21, Math.fma(this.m01, this.m01, this.m11 * this.m11));
        if (!(_ct1 > 1.1754944E-38f && _ct1 < Float.POSITIVE_INFINITY)) return toRigid_degenerate();
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_ct1));
        float _ct2 = Math.fma(this.m22, this.m22, Math.fma(this.m02, this.m02, this.m12 * this.m12));
        if (!(_ct2 > 1.1754944E-38f && _ct2 < Float.POSITIVE_INFINITY)) return toRigid_degenerate();
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_ct2));
        float _ct3 = Math.fma(this.m20, this.m20, Math.fma(this.m00, this.m00, this.m10 * this.m10));
        if (!(_ct3 > 1.1754944E-38f && _ct3 < Float.POSITIVE_INFINITY)) return toRigid_degenerate();
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_ct3));
        float _t20 = this.m12 * _t16;
        float _t23 = this.m21 * _t15;
        return toRigid_general_sd4c4a30_1(_t15, _t16, -this.m11, -this.m22, this.m10 * _t17, this.m22 * _t16, _t20, this.m20 * _t17, _t23, this.m11 * _t15, this.m00 * _t17, Math.fma(this.m12, _t16, _t23), Math.fma(this.m21, _t15, -_t20));
    }

    /** Piece 2 of {@code toRigid_general}, split to fit the inline budget; reached only through it. */
    private FloatRigid toRigid_general_sd4c4a30_1(float _t15, float _t16, float _t0, float _t1, float _t18, float _t19, float _t20, float _t21, float _t23, float _t24, float _t26, float _t31, float _t35) {
        float _t47, _t48, _t49;
        if (Math.fma(-Math.fma(_t18, _t19, -(_t20 * _t21)), this.m01 * _t15, Math.fma(Math.fma(_t18, _t23, -(_t24 * _t21)), this.m02 * _t16, Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0f) {
            _t47 = -_t26;
            _t48 = -_t18;
            _t49 = -_t21;
        } else {
            _t47 = _t26;
            _t48 = _t18;
            _t49 = _t21;
        }
        float _t51 = 1.0f + _t47;
        float _t52 = 1.0f - _t47;
        float _t63 = Math.fma(this.m11, _t15, Math.fma(this.m22, _t16, _t51));
        float _t65 = Math.fma(this.m11, _t15, Math.fma(_t1, _t16, _t52));
        float _t66 = Math.fma(this.m22, _t16, Math.fma(_t0, _t15, _t52));
        float _t67 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51));
        return toRigid_general_sd4c4a30_2(_t15, _t16, _t19, _t24, _t31, _t35, _t47, Math.fma(this.m01, _t15, _t48), Math.fma(this.m02, _t16, _t49), Math.fma(this.m02, _t16, -_t49), Math.fma(-this.m01, _t15, _t48), _t63, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t63)), _t65, _t66, _t67, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t65)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67)), 0.0f, 0.0f, 0.0f);
    }

    /** Piece 3 of {@code toRigid_general}, split to fit the inline budget; reached only through it. */
    private FloatRigid toRigid_general_sd4c4a30_2(float _t15, float _t16, float _t19, float _t24, float _t31, float _t35, float _t47, float _t54, float _t55, float _t56, float _t57, float _t63, float _sp0, float _t65, float _t66, float _t67, float _sp1, float _sp2, float _sp3, float _sfx0, float _sfx1, float _sfx2) {
        float _sfx3, _sfx4, _sfx5, _sfx6;
        if (Math.fma(this.m11, _t15, Math.fma(this.m22, _t16, _t47)) > 0.0f) {
            _sfx3 = _sp0 * _t35;
            _sfx4 = _sp0 * _t56;
            _sfx5 = _sp0 * _t57;
            _sfx6 = 0.5f * (float) java.lang.Math.sqrt(_t63);
        } else {
            if (_t47 > java.lang.Math.max(_t24, _t19)) {
                _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t67);
                _sfx4 = _sp3 * _t54;
                _sfx5 = _sp3 * _t55;
                _sfx6 = _sp3 * _t35;
            } else {
                if (_t24 > _t19) {
                    _sfx3 = _sp1 * _t54;
                    _sfx4 = 0.5f * (float) java.lang.Math.sqrt(_t65);
                    _sfx5 = _sp1 * _t31;
                    _sfx6 = _sp1 * _t56;
                } else {
                    _sfx3 = _sp2 * _t55;
                    _sfx4 = _sp2 * _t31;
                    _sfx5 = 0.5f * (float) java.lang.Math.sqrt(_t66);
                    _sfx6 = _sp2 * _t57;
                }
            }
        }
        return new FloatRigid(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6);
    }


    /**
     * Extract this matrix's rotation into a rigid transform with zero translation (scale is removed
     * by normalizing the columns, but shear is not removed: a sheared block yields a rotation
     * quaternion that is not unit length), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting rigid transform
     */
    public FloatRigid toRigid() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return toRigid_identity();
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return toRigid_translation();
        return toRigid_general();
    }


    /**
     * Degenerate-input path of {@code toRigid}: its methods leave here when a column of the linear
     * block is zero or its squared length leaves the normal floating-point range (or is NaN);
     * reached only through them.
     */
    private FloatRigid toRigid_degenerate_translation() {
        float _t0 = unitScale(this.m02, this.m12, 1.0f);
        float _t4 = this.m02 * _t0;
        float _t5 = this.m12 * _t0;
        float _t8 = Math.fma(_t0, _t0, Math.fma(_t4, _t4, _t5 * _t5));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t13, _sp0, _sp1;
        if (_t8 <= 0.0f) {
            _t13 = 1.0f;
            _sp0 = 0.0f;
            _sp1 = 0.0f;
        } else {
            _t13 = _t9 * _t0;
            _sp0 = 0.5f * _t9 * _t5;
            _sp1 = 0.5f * _t9 * _t4;
        }
        float _t18 = _t13 < 0.0f ? -1.0f : 1.0f;
        float _t21 = _t18 - _t13;
        float _t23 = 1.0f + _t18 + _t13;
        float _t25 = 2.0f - _t18 - _t13;
        float _t27 = 1.0f + _t23;
        return toRigid_translation_s0_tail(1.0f + _t13 - _t18 - 1.0f, _t23, _sp0, (1.0f / (float) java.lang.Math.sqrt(_t27)), _t18, java.lang.Math.max(1.0f, _t13), _t21, _t13, _sp1, _t25, (1.0f / (float) java.lang.Math.sqrt(_t21)), (1.0f / (float) java.lang.Math.sqrt(_t25)), _t27);
    }

    /** Private tail of {@code toRigid_degenerate_general}; reached only through it. */
    private FloatRigid toRigid_degenerate_general_s0_tail(float _t31, float _t15, float _t16, float _t32, float _t13, float _t12, float _t14, float _t33, float _t34, float _t35, float _t36, float _t27, float _t28, float _t29) {
        float _t37 = _t31 * _t15;
        float _t38 = _t31 * _t16;
        float _t39 = _t32 * _t13;
        float _t40 = _t32 * _t12;
        float _t41 = _t32 * _t14;
        float _t50 = java.lang.Math.abs(_t40);
        float _t51 = java.lang.Math.abs(_t39);
        float _t72, _t75, _t87;
        if (java.lang.Math.abs(_t33) < java.lang.Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0f;
            _t87 = -_t34;
        } else {
            _t72 = 0.0f;
            _t75 = -_t35;
            _t87 = _t33;
        }
        float _t73, _t76, _t88;
        if (java.lang.Math.abs(_t37) < java.lang.Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0f;
            _t88 = -_t38;
        } else {
            _t73 = 0.0f;
            _t76 = -_t36;
            _t88 = _t37;
        }
        float _t74, _t77;
        if (_t50 < _t51) {
            _t74 = _t41;
            _t77 = 0.0f;
        } else {
            _t74 = 0.0f;
            _t77 = -_t41;
        }
        return toRigid_degenerate_general_s0_tail2(_t50, _t51, _t39, _t40, _t75, _t87, _t72, _t76, _t88, _t73, _t77, _t74, _t27, _t28, _t29, _t36, _t37, _t33, _t35, _t34, _t38, _t41);
    }

    /** Private tail of {@code toRigid_degenerate_general}; reached only through it. */
    private FloatRigid toRigid_degenerate_general_s0_tail2(float _t50, float _t51, float _t39, float _t40, float _t75, float _t87, float _t72, float _t76, float _t88, float _t73, float _t77, float _t74, float _t27, float _t28, float _t29, float _t36, float _t37, float _t33, float _t35, float _t34, float _t38, float _t41) {
        float _t89 = _t50 < _t51 ? -_t39 : _t40;
        float _t99 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        float _t100 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        float _t101 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
        return toRigid_degenerate_general_s0_tail3(_t27, _t28, _t29, _t99 * _t72, _t36, _t100 * _t76, _t37, _t100 * _t88, _t33, _t35, _t39, _t101 * _t89, _t34, _t99 * _t75, _t40, _t99 * _t87, _t100 * _t73, _t38, _t41, _t101 * _t74, _t101 * _t77);
    }

    /** Private tail of {@code toRigid_degenerate_general}; reached only through it. */
    private FloatRigid toRigid_degenerate_general_s0_tail3(float _t27, float _t28, float _t29, float _t102, float _t36, float _t105, float _t37, float _t114, float _t33, float _t35, float _t39, float _t115, float _t34, float _t106, float _t40, float _t116, float _t103, float _t38, float _t41, float _t104, float _t107) {
        float _t165, _t166;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t165 = 0.0f;
                    _t166 = 0.0f;
                } else {
                    _t165 = _t102;
                    _t166 = Math.fma(_t33, _t102, -(_t34 * _t106));
                }
            } else {
                _t165 = _t29 <= 0.0f ? Math.fma(_t36, _t105, -(_t37 * _t114)) : Math.fma(_t33, _t36, -(_t35 * _t37));
                _t166 = _t36;
            }
        } else {
            _t165 = _t39;
            _t166 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t115 : Math.fma(_t33, _t39, -(_t34 * _t40)) : _t36;
        }
        return toRigid_degenerate_general_s0_tail4(_t27, _t28, _t29, _t116, _t37, _t103, _t38, _t105, _t34, _t33, _t41, _t104, _t35, _t106, _t40, _t39, _t115, _t36, _t114, _t107, _t102, _t166, _t165);
    }

    /** Private tail of {@code toRigid_degenerate_general}; reached only through it. */
    private FloatRigid toRigid_degenerate_general_s0_tail4(float _t27, float _t28, float _t29, float _t116, float _t37, float _t103, float _t38, float _t105, float _t34, float _t33, float _t41, float _t104, float _t35, float _t106, float _t40, float _t39, float _t115, float _t36, float _t114, float _t107, float _t102, float _t166, float _t165) {
        float _t167, _t168;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t167 = 1.0f;
                    _t168 = 0.0f;
                } else {
                    _t167 = _t116;
                    _t168 = Math.fma(_t35, _t106, -(_t33 * _t116));
                }
            } else {
                _t167 = _t29 <= 0.0f ? Math.fma(_t37, _t103, -(_t38 * _t105)) : Math.fma(_t34, _t37, -(_t33 * _t38));
                _t168 = _t38;
            }
        } else {
            _t167 = _t41;
            _t168 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t104 : Math.fma(_t35, _t40, -(_t33 * _t41)) : _t38;
        }
        return toRigid_degenerate_general_s0_tail5(_t29, _t27, _t28, _t105, _t39, _t115, _t41, _t104, _t36, _t38, _t33, _t106, _t114, _t103, _t35, _t34, _t40, _t107, _t116, _t102, _t37, _t166, _t167, _t165, _t168);
    }

    /** Private tail of {@code toRigid_degenerate_general}; reached only through it. */
    private FloatRigid toRigid_degenerate_general_s0_tail5(float _t29, float _t27, float _t28, float _t105, float _t39, float _t115, float _t41, float _t104, float _t36, float _t38, float _t33, float _t106, float _t114, float _t103, float _t35, float _t34, float _t40, float _t107, float _t116, float _t102, float _t37, float _t166, float _t167, float _t165, float _t168) {
        float _t169, _t170;
        if (_t29 <= 0.0f) {
            if (_t27 <= 0.0f) {
                if (_t28 <= 0.0f) {
                    _t169 = 0.0f;
                    _t170 = 0.0f;
                } else {
                    _t169 = _t105;
                    _t170 = Math.fma(_t38, _t114, -(_t36 * _t103));
                }
            } else {
                _t169 = _t28 <= 0.0f ? Math.fma(_t39, _t115, -(_t41 * _t104)) : Math.fma(_t39, _t36, -(_t41 * _t38));
                _t170 = _t40;
            }
        } else {
            _t169 = _t33;
            _t170 = _t27 <= 0.0f ? _t28 <= 0.0f ? _t106 : Math.fma(_t35, _t38, -(_t34 * _t36)) : _t40;
        }
        return toRigid_degenerate_general_s0_tail6(_t28, _t29, _t27, _t107, _t34, _t116, _t35, _t102, _t41, _t39, _t37, _t114, _t40, _t104, _t38, _t103, _t115, _t36, _t170, _t166, _t167, _t165, _t168, _t169);
    }

    /** Private tail of {@code toRigid_degenerate_general}; reached only through it. */
    private FloatRigid toRigid_degenerate_general_s0_tail6(float _t28, float _t29, float _t27, float _t107, float _t34, float _t116, float _t35, float _t102, float _t41, float _t39, float _t37, float _t114, float _t40, float _t104, float _t38, float _t103, float _t115, float _t36, float _t170, float _t166, float _t167, float _t165, float _t168, float _t169) {
        float _t171, _t172;
        if (_t28 <= 0.0f) {
            if (_t29 <= 0.0f) {
                if (_t27 <= 0.0f) {
                    _t171 = 1.0f;
                    _t172 = 0.0f;
                } else {
                    _t171 = _t107;
                    _t172 = Math.fma(_t40, _t104, -(_t39 * _t107));
                }
            } else {
                _t171 = _t27 <= 0.0f ? Math.fma(_t34, _t116, -(_t35 * _t102)) : Math.fma(_t34, _t41, -(_t35 * _t39));
                _t172 = _t35;
            }
        } else {
            _t171 = _t37;
            _t172 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t114 : Math.fma(_t40, _t38, -(_t39 * _t37)) : _t35;
        }
        return toRigid_degenerate_general_s0_tail7(_t29, _t27, _t28, _t103, _t41, _t107, _t40, _t115, _t37, _t36, _t34, _t170, _t166, _t167, _t171, _t165, _t168, _t169, _t172);
    }

    /** Private tail of {@code toRigid_degenerate_general}; reached only through it. */
    private FloatRigid toRigid_degenerate_general_s0_tail7(float _t29, float _t27, float _t28, float _t103, float _t41, float _t107, float _t40, float _t115, float _t37, float _t36, float _t34, float _t170, float _t166, float _t167, float _t171, float _t165, float _t168, float _t169, float _t172) {
        float _t173 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t28 <= 0.0f ? 1.0f : _t103 : _t28 <= 0.0f ? Math.fma(_t41, _t107, -(_t40 * _t115)) : Math.fma(_t41, _t37, -(_t40 * _t36)) : _t34;
        float _t194, _t195, _t196;
        if (Math.fma(Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, Math.fma(Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0f) {
            _t194 = -_t173;
            _t195 = -_t172;
            _t196 = -_t169;
        } else {
            _t194 = _t173;
            _t195 = _t172;
            _t196 = _t169;
        }
        return toRigid_degenerate_general_s0_tail8(_t195, _t165, _t194, _t167, _t171, _t170 - _t166, java.lang.Math.max(_t167, _t171), _t195 + _t165, _t196 + _t168, _t168 - _t196, _t170 + _t166);
    }

    /** Private tail of {@code toRigid_degenerate_general}; reached only through it. */
    private FloatRigid toRigid_degenerate_general_s0_tail8(float _t195, float _t165, float _t194, float _t167, float _t171, float _t182, float _t183, float _t199, float _t200, float _t201, float _t184) {
        float _t206 = _t194 + _t167 + _t171;
        float _t207 = 1.0f + _t206;
        float _t208 = 1.0f + _t194 - _t167 - _t171;
        float _t209 = 1.0f + _t167 - _t194 - _t171;
        float _t210 = 1.0f + _t171 - _t194 - _t167;
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t207));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t209));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sfx3 = _t206 > 0.0f ? _sp0 * _t182 : _t194 > _t183 ? 0.5f * (float) java.lang.Math.sqrt(_t208) : _t167 > _t171 ? _sp1 * _t199 : _sp2 * _t200;
        return toRigid_degenerate_general_s0_tail9(_t206, _sp0, _t201, _t194, _t183, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t208)), _t199, _t167, _t171, _t209, _sp2, _t184, _t195 - _t165, _t200, _sp1, _t210, _t207, _t182, 0.0f, 0.0f, 0.0f, _sfx3);
    }

    /** Private tail of {@code toRigid_degenerate_general}; reached only through it. */
    private FloatRigid toRigid_degenerate_general_s0_tail9(float _t206, float _sp0, float _t201, float _t194, float _t183, float _sp3, float _t199, float _t167, float _t171, float _t209, float _sp2, float _t184, float _t202, float _t200, float _sp1, float _t210, float _t207, float _t182, float _sfx0, float _sfx1, float _sfx2, float _sfx3) {
        float _sfx4, _sfx5, _sfx6;
        if (_t206 > 0.0f) {
            _sfx4 = _sp0 * _t201;
            _sfx5 = _sp0 * _t202;
            _sfx6 = 0.5f * (float) java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > _t183) {
                _sfx4 = _sp3 * _t199;
                _sfx5 = _sp3 * _t200;
                _sfx6 = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    _sfx4 = 0.5f * (float) java.lang.Math.sqrt(_t209);
                    _sfx5 = _sp1 * _t184;
                    _sfx6 = _sp1 * _t201;
                } else {
                    _sfx4 = _sp2 * _t184;
                    _sfx5 = 0.5f * (float) java.lang.Math.sqrt(_t210);
                    _sfx6 = _sp2 * _t202;
                }
            }
        }
        return new FloatRigid(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6);
    }


    /**
     * Degenerate-input path of {@code toRigid}: its methods leave here when a column of the linear
     * block is zero or its squared length leaves the normal floating-point range (or is NaN);
     * reached only through them.
     */
    private FloatRigid toRigid_degenerate_general() {
        float _t0 = unitScale(this.m01, this.m11, this.m21);
        float _t1 = unitScale(this.m02, this.m12, this.m22);
        float _t2 = unitScale(this.m00, this.m10, this.m20);
        float _t12 = this.m21 * _t0;
        float _t13 = this.m01 * _t0;
        float _t14 = this.m11 * _t0;
        float _t15 = this.m22 * _t1;
        float _t16 = this.m02 * _t1;
        float _t17 = this.m12 * _t1;
        float _t18 = this.m20 * _t2;
        float _t19 = this.m00 * _t2;
        float _t20 = this.m10 * _t2;
        float _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        float _t31 = (1.0f / (float) java.lang.Math.sqrt(_t28));
        return toRigid_degenerate_general_s0_tail(_t31, _t15, _t16, (1.0f / (float) java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t30 * _t20, _t31 * _t17, _t27, _t28, _t29);
    }


    /**
     * Degenerate-input path of {@code toRigid}: its methods leave here when a column of the linear
     * block is zero or its squared length leaves the normal floating-point range (or is NaN);
     * reached only through them.
     */
    private FloatRigid toRigid_degenerate() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return toRigid_identity();
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return toRigid_degenerate_translation();
        return toRigid_degenerate_general();
    }


    /**
     * Private body of {@code toTransform}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code toTransform}; reached only through it.
     */
    private FloatTransform toTransform_identity() {
        return new FloatTransform(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    /** Private tail of {@code toTransform_translation}; reached only through it. */
    private FloatTransform toTransform_translation_s0_tail(float _t18, float _t13, float _sp0, float _t19, float _t8, float _t4, float _t11, float _t3, float _sp1, float _t15, float _t12, float _t16, float _t17, float _t2) {
        float _t20 = (1.0f / (float) java.lang.Math.sqrt(_t18));
        if (_t13 > 0.0f) {
            return new FloatTransform(0.0f, 0.0f, 0.0f, -(_sp0 * _t19), _sp1 * _t19, 0.0f, 0.5f * (float) java.lang.Math.sqrt(_t17), _t8, 1.0f, (float) java.lang.Math.sqrt(_t2));
        } else {
            if (_t8 > _t4) {
                return new FloatTransform(0.0f, 0.0f, 0.0f, 0.5f * (float) java.lang.Math.sqrt(_t11), 0.0f, _sp1 * _t12, -(_sp0 * _t12), _t8, 1.0f, (float) java.lang.Math.sqrt(_t2));
            } else {
                if (1.0f > _t3) {
                    return new FloatTransform(0.0f, 0.0f, 0.0f, 0.0f, 0.5f * (float) java.lang.Math.sqrt(_t15), _sp0 * _t16, _sp1 * _t16, _t8, 1.0f, (float) java.lang.Math.sqrt(_t2));
                } else {
                    return new FloatTransform(0.0f, 0.0f, 0.0f, _sp1 * _t20, _sp0 * _t20, 0.5f * (float) java.lang.Math.sqrt(_t18), 0.0f, _t8, 1.0f, (float) java.lang.Math.sqrt(_t2));
                }
            }
        }
    }


    /**
     * Private body of {@code toTransform}, specialized by runtime matrix properties; reached only
     * through the public {@code toTransform} dispatcher.
     */
    private FloatTransform toTransform_translation() {
        float _t2 = Math.fma(this.m02, this.m02, Math.fma(this.m12, this.m12, 1.0f));
        if (!(_t2 > 1.1754944E-38f && _t2 < Float.POSITIVE_INFINITY)) return toTransform_degenerate();
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        float _t8 = _t3 < 0.0f ? -1.0f : 1.0f;
        float _t11 = _t8 - _t3;
        float _t13 = 1.0f + _t8 + _t3;
        float _t15 = 2.0f - _t8 - _t3;
        float _t17 = 1.0f + _t13;
        return toTransform_translation_s0_tail(1.0f + _t3 - _t8 - 1.0f, _t13, 0.5f * this.m12 * _t3, (1.0f / (float) java.lang.Math.sqrt(_t17)), _t8, java.lang.Math.max(1.0f, _t3), _t11, _t3, 0.5f * this.m02 * _t3, _t15, (1.0f / (float) java.lang.Math.sqrt(_t11)), (1.0f / (float) java.lang.Math.sqrt(_t15)), _t17, _t2);
    }


    /**
     * Private body of {@code toTransform}, specialized by runtime matrix properties; reached only
     * through the public {@code toTransform} dispatcher.
     */
    private FloatTransform toTransform_general() {
        float _t12 = Math.fma(this.m21, this.m21, Math.fma(this.m01, this.m01, this.m11 * this.m11));
        if (!(_t12 > 1.1754944E-38f && _t12 < Float.POSITIVE_INFINITY)) return toTransform_degenerate();
        float _t13 = Math.fma(this.m22, this.m22, Math.fma(this.m02, this.m02, this.m12 * this.m12));
        if (!(_t13 > 1.1754944E-38f && _t13 < Float.POSITIVE_INFINITY)) return toTransform_degenerate();
        float _t14 = Math.fma(this.m20, this.m20, Math.fma(this.m00, this.m00, this.m10 * this.m10));
        if (!(_t14 > 1.1754944E-38f && _t14 < Float.POSITIVE_INFINITY)) return toTransform_degenerate();
        float _t18 = (float) java.lang.Math.sqrt(_t14);
        float _t17 = 1.0f / _t18;
        float _sfx8 = (float) java.lang.Math.sqrt(_t12);
        float _t15 = 1.0f / _sfx8;
        float _t24 = this.m21 * _t15;
        float _sfx9 = (float) java.lang.Math.sqrt(_t13);
        float _t16 = 1.0f / _sfx9;
        float _t21 = this.m12 * _t16;
        return toTransform_general_s7465a3e1_1(-this.m11, -this.m22, _t18, this.m10 * _t17, this.m20 * _t17, this.m00 * _t17, 0.0f, 0.0f, 0.0f, _sfx8, _t15, _t24, this.m11 * _t15, _sfx9, _t16, this.m22 * _t16, _t21, Math.fma(this.m12, _t16, _t24), Math.fma(this.m21, _t15, -_t21));
    }

    /** Piece 2 of {@code toTransform_general}, split to fit the inline budget; reached only through it. */
    private FloatTransform toTransform_general_s7465a3e1_1(float _t0, float _t1, float _t18, float _t19, float _t22, float _t27, float _sfx0, float _sfx1, float _sfx2, float _sfx8, float _t15, float _t24, float _t25, float _sfx9, float _t16, float _t20, float _t21, float _t32, float _t36) {
        float _t48, _t49, _t50, _sfx7;
        if (Math.fma(-Math.fma(_t19, _t20, -(_t21 * _t22)), this.m01 * _t15, Math.fma(Math.fma(_t19, _t24, -(_t25 * _t22)), this.m02 * _t16, Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27)) < 0.0f) {
            _t48 = -_t27;
            _t49 = -_t19;
            _t50 = -_t22;
            _sfx7 = -_t18;
        } else {
            _t48 = _t27;
            _t49 = _t19;
            _t50 = _t22;
            _sfx7 = _t18;
        }
        float _t52 = 1.0f + _t48;
        float _t53 = 1.0f - _t48;
        float _t64 = Math.fma(this.m11, _t15, Math.fma(this.m22, _t16, _t52));
        float _t66 = Math.fma(this.m11, _t15, Math.fma(_t1, _t16, _t53));
        float _t67 = Math.fma(this.m22, _t16, Math.fma(_t0, _t15, _t53));
        return toTransform_general_s7465a3e1_2(_sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, _t32, _t36, _t48, _sfx7, Math.fma(this.m01, _t15, _t49), Math.fma(this.m02, _t16, _t50), Math.fma(this.m02, _t16, -_t50), Math.fma(-this.m01, _t15, _t49), _t64, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t64)), _t66, _t67, Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t66)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t67)));
    }

    /** Piece 3 of {@code toTransform_general}, split to fit the inline budget; reached only through it. */
    private FloatTransform toTransform_general_s7465a3e1_2(float _sfx0, float _sfx1, float _sfx2, float _sfx8, float _t15, float _t25, float _sfx9, float _t16, float _t20, float _t32, float _t36, float _t48, float _sfx7, float _t55, float _t56, float _t57, float _t58, float _t64, float _sp0, float _t66, float _t67, float _t68, float _sp1, float _sp2) {
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t68));
        float _sfx3, _sfx4, _sfx5, _sfx6;
        if (Math.fma(this.m11, _t15, Math.fma(this.m22, _t16, _t48)) > 0.0f) {
            _sfx3 = _sp0 * _t36;
            _sfx4 = _sp0 * _t57;
            _sfx5 = _sp0 * _t58;
            _sfx6 = 0.5f * (float) java.lang.Math.sqrt(_t64);
        } else {
            if (_t48 > java.lang.Math.max(_t25, _t20)) {
                _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t68);
                _sfx4 = _sp3 * _t55;
                _sfx5 = _sp3 * _t56;
                _sfx6 = _sp3 * _t36;
            } else {
                if (_t25 > _t20) {
                    _sfx3 = _sp1 * _t55;
                    _sfx4 = 0.5f * (float) java.lang.Math.sqrt(_t66);
                    _sfx5 = _sp1 * _t32;
                    _sfx6 = _sp1 * _t57;
                } else {
                    _sfx3 = _sp2 * _t56;
                    _sfx4 = _sp2 * _t32;
                    _sfx5 = 0.5f * (float) java.lang.Math.sqrt(_t67);
                    _sfx6 = _sp2 * _t58;
                }
            }
        }
        return new FloatTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }


    /**
     * Decompose this matrix's linear {@code R * S} block into a TRS transform with zero translation
     * (scale is removed by normalizing the columns, but shear is not removed: a sheared block
     * yields a rotation quaternion that is not unit length), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting transform
     */
    public FloatTransform toTransform() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return toTransform_identity();
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return toTransform_translation();
        return toTransform_general();
    }

    /** Private tail of {@code toTransform_degenerate_translation}; reached only through it. */
    private FloatTransform toTransform_degenerate_translation_s0_tail(float _t28, float _t23, float _sp0, float _t29, float _t18, float _t14, float _t21, float _t13, float _sp1, float _t25, float _t22, float _t26, float _t27, float _t8, float _t0) {
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t28));
        return new FloatTransform(0.0f, 0.0f, 0.0f, _t23 > 0.0f ? -(_sp0 * _t29) : _t18 > _t14 ? 0.5f * (float) java.lang.Math.sqrt(_t21) : 1.0f > _t13 ? 0.0f : _sp1 * _t30, _t23 > 0.0f ? _sp1 * _t29 : _t18 > _t14 ? 0.0f : 1.0f > _t13 ? 0.5f * (float) java.lang.Math.sqrt(_t25) : _sp0 * _t30, _t23 > 0.0f ? 0.0f : _t18 > _t14 ? _sp1 * _t22 : 1.0f > _t13 ? _sp0 * _t26 : 0.5f * (float) java.lang.Math.sqrt(_t28), _t23 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t27) : _t18 > _t14 ? -(_sp0 * _t22) : 1.0f > _t13 ? _sp1 * _t26 : 0.0f, _t18, 1.0f, _t8 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t8) / _t0);
    }


    /**
     * Degenerate-input path of {@code toTransform}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private FloatTransform toTransform_degenerate_translation() {
        float _t0 = unitScale(this.m02, this.m12, 1.0f);
        float _t4 = this.m02 * _t0;
        float _t5 = this.m12 * _t0;
        float _t8 = Math.fma(_t0, _t0, Math.fma(_t4, _t4, _t5 * _t5));
        float _t9 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t13, _sp0, _sp1;
        if (_t8 <= 0.0f) {
            _t13 = 1.0f;
            _sp0 = 0.0f;
            _sp1 = 0.0f;
        } else {
            _t13 = _t9 * _t0;
            _sp0 = 0.5f * _t9 * _t5;
            _sp1 = 0.5f * _t9 * _t4;
        }
        float _t18 = _t13 < 0.0f ? -1.0f : 1.0f;
        float _t21 = _t18 - _t13;
        float _t23 = 1.0f + _t18 + _t13;
        float _t25 = 2.0f - _t18 - _t13;
        float _t27 = 1.0f + _t23;
        return toTransform_degenerate_translation_s0_tail(1.0f + _t13 - _t18 - 1.0f, _t23, _sp0, (1.0f / (float) java.lang.Math.sqrt(_t27)), _t18, java.lang.Math.max(1.0f, _t13), _t21, _t13, _sp1, _t25, (1.0f / (float) java.lang.Math.sqrt(_t21)), (1.0f / (float) java.lang.Math.sqrt(_t25)), _t27, _t8, _t0);
    }

    /** Private tail of {@code toTransform_degenerate_general}; reached only through it. */
    private FloatTransform toTransform_degenerate_general_s0_tail(float _t31, float _t17, float _t15, float _t16, float _t32, float _t13, float _t12, float _t14, float _t35, float _t36, float _t29, float _t2, float _t37, float _t27, float _t28, float _t0, float _t1) {
        float _t38 = _t31 * _t17;
        float _t39 = _t31 * _t15;
        float _t40 = _t31 * _t16;
        float _t41 = _t32 * _t13;
        float _t42 = _t32 * _t12;
        float _t43 = _t32 * _t14;
        float _t44 = java.lang.Math.abs(_t35);
        float _t45 = java.lang.Math.abs(_t36);
        float _t48 = java.lang.Math.abs(_t39);
        float _t49 = java.lang.Math.abs(_t40);
        float _t52 = java.lang.Math.abs(_t42);
        float _t53 = java.lang.Math.abs(_t41);
        float _t75, _t78;
        if (_t44 < _t45) {
            _t75 = _t37;
            _t78 = 0.0f;
        } else {
            _t75 = 0.0f;
            _t78 = -_t37;
        }
        float _t76, _t79;
        if (_t48 < _t49) {
            _t76 = _t38;
            _t79 = 0.0f;
        } else {
            _t76 = 0.0f;
            _t79 = -_t38;
        }
        float _t77, _t80;
        if (_t52 < _t53) {
            _t77 = _t43;
            _t80 = 0.0f;
        } else {
            _t77 = 0.0f;
            _t80 = -_t43;
        }
        return toTransform_degenerate_general_s0_tail2(_t44, _t45, _t36, _t35, _t48, _t49, _t40, _t39, _t52, _t53, _t41, _t42, _t78, _t75, _t79, _t76, _t80, _t77, _t27, _t28, _t29, _t38, _t37, _t43, _t29 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t29) / _t2, _t0, _t1);
    }

    /** Private tail of {@code toTransform_degenerate_general}; reached only through it. */
    private FloatTransform toTransform_degenerate_general_s0_tail2(float _t44, float _t45, float _t36, float _t35, float _t48, float _t49, float _t40, float _t39, float _t52, float _t53, float _t41, float _t42, float _t78, float _t75, float _t79, float _t76, float _t80, float _t77, float _t27, float _t28, float _t29, float _t38, float _t37, float _t43, float _t56, float _t0, float _t1) {
        float _t90 = _t44 < _t45 ? -_t36 : _t35;
        float _t91 = _t48 < _t49 ? -_t40 : _t39;
        float _t92 = _t52 < _t53 ? -_t41 : _t42;
        float _t102 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t78, _t78, Math.fma(_t90, _t90, _t75 * _t75))));
        float _t103 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t79, _t79, Math.fma(_t91, _t91, _t76 * _t76))));
        float _t104 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t80, _t80, Math.fma(_t92, _t92, _t77 * _t77))));
        return toTransform_degenerate_general_s0_tail3(_t27, _t28, _t29, _t102 * _t75, _t38, _t103 * _t79, _t39, _t103 * _t91, _t35, _t37, _t41, _t104 * _t92, _t36, _t102 * _t78, _t42, _t102 * _t90, _t103 * _t76, _t40, _t43, _t104 * _t77, _t104 * _t80, _t56, _t0, _t1);
    }

    /** Private tail of {@code toTransform_degenerate_general}; reached only through it. */
    private FloatTransform toTransform_degenerate_general_s0_tail3(float _t27, float _t28, float _t29, float _t105, float _t38, float _t108, float _t39, float _t117, float _t35, float _t37, float _t41, float _t118, float _t36, float _t109, float _t42, float _t119, float _t106, float _t40, float _t43, float _t107, float _t110, float _t56, float _t0, float _t1) {
        float _t168, _t169;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t168 = 0.0f;
                    _t169 = 0.0f;
                } else {
                    _t168 = _t105;
                    _t169 = Math.fma(_t35, _t105, -(_t36 * _t109));
                }
            } else {
                _t168 = _t29 <= 0.0f ? Math.fma(_t38, _t108, -(_t39 * _t117)) : Math.fma(_t35, _t38, -(_t37 * _t39));
                _t169 = _t38;
            }
        } else {
            _t168 = _t41;
            _t169 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t118 : Math.fma(_t35, _t41, -(_t36 * _t42)) : _t38;
        }
        return toTransform_degenerate_general_s0_tail4(_t27, _t28, _t29, _t119, _t39, _t106, _t40, _t108, _t36, _t35, _t43, _t107, _t37, _t109, _t42, _t41, _t118, _t38, _t117, _t110, _t105, _t169, _t168, _t56, _t0, _t1);
    }

    /** Private tail of {@code toTransform_degenerate_general}; reached only through it. */
    private FloatTransform toTransform_degenerate_general_s0_tail4(float _t27, float _t28, float _t29, float _t119, float _t39, float _t106, float _t40, float _t108, float _t36, float _t35, float _t43, float _t107, float _t37, float _t109, float _t42, float _t41, float _t118, float _t38, float _t117, float _t110, float _t105, float _t169, float _t168, float _t56, float _t0, float _t1) {
        float _t170, _t171;
        if (_t27 <= 0.0f) {
            if (_t28 <= 0.0f) {
                if (_t29 <= 0.0f) {
                    _t170 = 1.0f;
                    _t171 = 0.0f;
                } else {
                    _t170 = _t119;
                    _t171 = Math.fma(_t37, _t109, -(_t35 * _t119));
                }
            } else {
                _t170 = _t29 <= 0.0f ? Math.fma(_t39, _t106, -(_t40 * _t108)) : Math.fma(_t36, _t39, -(_t35 * _t40));
                _t171 = _t40;
            }
        } else {
            _t170 = _t43;
            _t171 = _t28 <= 0.0f ? _t29 <= 0.0f ? _t107 : Math.fma(_t37, _t42, -(_t35 * _t43)) : _t40;
        }
        return toTransform_degenerate_general_s0_tail5(_t29, _t27, _t28, _t108, _t41, _t118, _t43, _t107, _t38, _t40, _t35, _t109, _t117, _t106, _t37, _t36, _t42, _t110, _t119, _t105, _t39, _t169, _t170, _t168, _t171, _t56, _t0, _t1);
    }

    /** Private tail of {@code toTransform_degenerate_general}; reached only through it. */
    private FloatTransform toTransform_degenerate_general_s0_tail5(float _t29, float _t27, float _t28, float _t108, float _t41, float _t118, float _t43, float _t107, float _t38, float _t40, float _t35, float _t109, float _t117, float _t106, float _t37, float _t36, float _t42, float _t110, float _t119, float _t105, float _t39, float _t169, float _t170, float _t168, float _t171, float _t56, float _t0, float _t1) {
        float _t172, _t173;
        if (_t29 <= 0.0f) {
            if (_t27 <= 0.0f) {
                if (_t28 <= 0.0f) {
                    _t172 = 0.0f;
                    _t173 = 0.0f;
                } else {
                    _t172 = _t108;
                    _t173 = Math.fma(_t40, _t117, -(_t38 * _t106));
                }
            } else {
                _t172 = _t28 <= 0.0f ? Math.fma(_t41, _t118, -(_t43 * _t107)) : Math.fma(_t41, _t38, -(_t43 * _t40));
                _t173 = _t42;
            }
        } else {
            _t172 = _t35;
            _t173 = _t27 <= 0.0f ? _t28 <= 0.0f ? _t109 : Math.fma(_t37, _t40, -(_t36 * _t38)) : _t42;
        }
        return toTransform_degenerate_general_s0_tail6(_t28, _t29, _t27, _t110, _t36, _t119, _t37, _t105, _t43, _t41, _t39, _t117, _t42, _t107, _t40, _t106, _t118, _t38, _t173, _t169, _t170, _t168, _t171, _t172, _t56, _t0, _t1);
    }

    /** Private tail of {@code toTransform_degenerate_general}; reached only through it. */
    private FloatTransform toTransform_degenerate_general_s0_tail6(float _t28, float _t29, float _t27, float _t110, float _t36, float _t119, float _t37, float _t105, float _t43, float _t41, float _t39, float _t117, float _t42, float _t107, float _t40, float _t106, float _t118, float _t38, float _t173, float _t169, float _t170, float _t168, float _t171, float _t172, float _t56, float _t0, float _t1) {
        float _t174, _t175;
        if (_t28 <= 0.0f) {
            if (_t29 <= 0.0f) {
                if (_t27 <= 0.0f) {
                    _t174 = 1.0f;
                    _t175 = 0.0f;
                } else {
                    _t174 = _t110;
                    _t175 = Math.fma(_t42, _t107, -(_t41 * _t110));
                }
            } else {
                _t174 = _t27 <= 0.0f ? Math.fma(_t36, _t119, -(_t37 * _t105)) : Math.fma(_t36, _t43, -(_t37 * _t41));
                _t175 = _t37;
            }
        } else {
            _t174 = _t39;
            _t175 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t117 : Math.fma(_t42, _t40, -(_t41 * _t39)) : _t37;
        }
        return toTransform_degenerate_general_s0_tail7(_t29, _t27, _t28, _t106, _t43, _t110, _t42, _t118, _t39, _t38, _t36, _t173, _t169, _t170, _t174, _t168, _t171, _t172, _t175, _t56, _t0, _t1);
    }

    /** Private tail of {@code toTransform_degenerate_general}; reached only through it. */
    private FloatTransform toTransform_degenerate_general_s0_tail7(float _t29, float _t27, float _t28, float _t106, float _t43, float _t110, float _t42, float _t118, float _t39, float _t38, float _t36, float _t173, float _t169, float _t170, float _t174, float _t168, float _t171, float _t172, float _t175, float _t56, float _t0, float _t1) {
        float _t176 = _t29 <= 0.0f ? _t27 <= 0.0f ? _t28 <= 0.0f ? 1.0f : _t106 : _t28 <= 0.0f ? Math.fma(_t43, _t110, -(_t42 * _t118)) : Math.fma(_t43, _t39, -(_t42 * _t38)) : _t36;
        float _t196 = Math.fma(Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, Math.fma(Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
        float _t197, _t198, _t199;
        if (_t196 < 0.0f) {
            _t197 = -_t176;
            _t198 = -_t175;
            _t199 = -_t172;
        } else {
            _t197 = _t176;
            _t198 = _t175;
            _t199 = _t172;
        }
        return toTransform_degenerate_general_s0_tail8(_t199, _t171, _t198, _t168, _t197, _t170, _t174, _t173 - _t169, java.lang.Math.max(_t170, _t174), _t198 + _t168, _t173 + _t169, _t196, _t56, _t27, _t0, _t28, _t1);
    }

    /** Private tail of {@code toTransform_degenerate_general}; reached only through it. */
    private FloatTransform toTransform_degenerate_general_s0_tail8(float _t199, float _t171, float _t198, float _t168, float _t197, float _t170, float _t174, float _t185, float _t186, float _t202, float _t187, float _t196, float _t56, float _t27, float _t0, float _t28, float _t1) {
        float _t203 = _t199 + _t171;
        float _t209 = _t197 + _t170 + _t174;
        float _t210 = 1.0f + _t209;
        float _t211 = 1.0f + _t197 - _t170 - _t174;
        float _t212 = 1.0f + _t170 - _t197 - _t174;
        float _t213 = 1.0f + _t174 - _t197 - _t170;
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t210));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t212));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t213));
        float _sfx3 = _t209 > 0.0f ? _sp0 * _t185 : _t197 > _t186 ? 0.5f * (float) java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        return toTransform_degenerate_general_s0_tail9(_t209, _sp0, _t171 - _t199, _t197, _t186, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t211)), _t202, _t170, _t174, _t212, _sp2, _t187, _t198 - _t168, _t203, _sp1, _t213, _t210, _t185, _t196, _t56, _t27, _t0, _t28, _t1, 0.0f, 0.0f, 0.0f, _sfx3);
    }

    /** Private tail of {@code toTransform_degenerate_general}; reached only through it. */
    private FloatTransform toTransform_degenerate_general_s0_tail9(float _t209, float _sp0, float _t204, float _t197, float _t186, float _sp3, float _t202, float _t170, float _t174, float _t212, float _sp2, float _t187, float _t205, float _t203, float _sp1, float _t213, float _t210, float _t185, float _t196, float _t56, float _t27, float _t0, float _t28, float _t1, float _sfx0, float _sfx1, float _sfx2, float _sfx3) {
        float _sfx4, _sfx5, _sfx6;
        if (_t209 > 0.0f) {
            _sfx4 = _sp0 * _t204;
            _sfx5 = _sp0 * _t205;
            _sfx6 = 0.5f * (float) java.lang.Math.sqrt(_t210);
        } else {
            if (_t197 > _t186) {
                _sfx4 = _sp3 * _t202;
                _sfx5 = _sp3 * _t203;
                _sfx6 = _sp3 * _t185;
            } else {
                if (_t170 > _t174) {
                    _sfx4 = 0.5f * (float) java.lang.Math.sqrt(_t212);
                    _sfx5 = _sp1 * _t187;
                    _sfx6 = _sp1 * _t204;
                } else {
                    _sfx4 = _sp2 * _t187;
                    _sfx5 = 0.5f * (float) java.lang.Math.sqrt(_t213);
                    _sfx6 = _sp2 * _t205;
                }
            }
        }
        float _sfx7 = _t196 < 0.0f ? -_t56 : _t56;
        float _sfx8 = _t27 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t27) / _t0;
        float _sfx9 = _t28 <= 0.0f ? 0.0f : (float) java.lang.Math.sqrt(_t28) / _t1;
        return new FloatTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }


    /**
     * Degenerate-input path of {@code toTransform}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private FloatTransform toTransform_degenerate_general() {
        float _t0 = unitScale(this.m01, this.m11, this.m21);
        float _t1 = unitScale(this.m02, this.m12, this.m22);
        float _t2 = unitScale(this.m00, this.m10, this.m20);
        float _t12 = this.m21 * _t0;
        float _t13 = this.m01 * _t0;
        float _t14 = this.m11 * _t0;
        float _t15 = this.m22 * _t1;
        float _t16 = this.m02 * _t1;
        float _t17 = this.m12 * _t1;
        float _t18 = this.m20 * _t2;
        float _t19 = this.m00 * _t2;
        float _t20 = this.m10 * _t2;
        float _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        float _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        float _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        return toTransform_degenerate_general_s0_tail((1.0f / (float) java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0f / (float) java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t29, _t2, _t30 * _t20, _t27, _t28, _t0, _t1);
    }


    /**
     * Degenerate-input path of {@code toTransform}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private FloatTransform toTransform_degenerate() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return toTransform_identity();
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return toTransform_degenerate_translation();
        return toTransform_degenerate_general();
    }


    /**
     * Private body of {@code decomposeRotation}, specialized by runtime matrix properties; reached
     * only through the public {@code decomposeRotation} dispatcher.
     */
    private FloatQuat decomposeRotation_general() {
        float _t2 = Math.fma(this.m20, this.m20, Math.fma(this.m00, this.m00, this.m10 * this.m10));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        float _t7, _t8, _t9;
        if (_t2 != 0.0f) {
            _t7 = this.m20 * _t3;
            _t8 = this.m00 * _t3;
            _t9 = this.m10 * _t3;
        } else {
            _t7 = 0.0f;
            _t8 = 0.0f;
            _t9 = 0.0f;
        }
        float _t19 = -Math.fma(this.m21, _t7, Math.fma(this.m01, _t8, this.m11 * _t9));
        float _t20 = -Math.fma(this.m22, _t7, Math.fma(this.m02, _t8, this.m12 * _t9));
        float _t21 = Math.fma(_t19, _t7, this.m21);
        float _t22 = Math.fma(_t19, _t8, this.m01);
        float _t23 = Math.fma(_t19, _t9, this.m11);
        float _t29 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23));
        float _t30 = (1.0f / (float) java.lang.Math.sqrt(_t29));
        float _t34, _t35, _t36;
        if (_t29 != 0.0f) {
            _t34 = _t22 * _t30;
            _t35 = _t21 * _t30;
            _t36 = _t23 * _t30;
        } else {
            _t34 = 0.0f;
            _t35 = 0.0f;
            _t36 = 0.0f;
        }
        return decomposeRotation_general_s2273f2d9_1(_t7, _t8, _t9, _t20, _t34, _t35, _t36, -Math.fma(Math.fma(_t20, _t7, this.m22), _t35, Math.fma(Math.fma(_t20, _t8, this.m02), _t34, Math.fma(_t20, _t9, this.m12) * _t36)));
    }

    /** Piece 2 of {@code decomposeRotation_general}, split to fit the inline budget; reached only through it. */
    private FloatQuat decomposeRotation_general_s2273f2d9_1(float _t7, float _t8, float _t9, float _t20, float _t34, float _t35, float _t36, float _t40) {
        float _t44 = Math.fma(_t20, _t7, Math.fma(_t40, _t35, this.m22));
        float _t45 = Math.fma(_t20, _t8, Math.fma(_t40, _t34, this.m02));
        float _t46 = Math.fma(_t20, _t9, Math.fma(_t40, _t36, this.m12));
        float _t49 = Math.fma(_t44, _t44, Math.fma(_t45, _t45, _t46 * _t46));
        float _t50 = (1.0f / (float) java.lang.Math.sqrt(_t49));
        float _t54, _t55, _t56;
        if (_t49 != 0.0f) {
            _t54 = _t46 * _t50;
            _t55 = _t45 * _t50;
            _t56 = _t44 * _t50;
        } else {
            _t54 = 0.0f;
            _t55 = 0.0f;
            _t56 = 0.0f;
        }
        float _t73, _t74, _t75;
        if (Math.fma(Math.fma(_t34, _t54, -(_t36 * _t55)), _t7, Math.fma(Math.fma(_t36, _t56, -(_t35 * _t54)), _t8, Math.fma(_t35, _t55, -(_t34 * _t56)) * _t9)) < 0.0f) {
            _t73 = -_t8;
            _t74 = -_t9;
            _t75 = -_t7;
        } else {
            _t73 = _t8;
            _t74 = _t9;
            _t75 = _t7;
        }
        float _t76 = _t73 + _t36;
        float _t82 = _t76 + _t56;
        float _t86 = 1.0f + _t82;
        return decomposeRotation_general_s2273f2d9_2(_t36, _t56, _t35 - _t54, _t35 + _t54, _t73, _t74 + _t34, _t74 - _t34, _t75 + _t55, _t55 - _t75, _t82, _t86, 1.0f + (_t73 - (_t36 + _t56)), 1.0f + (_t36 - (_t73 + _t56)), 1.0f + (_t56 - _t76), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t86)));
    }

    /** Piece 3 of {@code decomposeRotation_general}, split to fit the inline budget; reached only through it. */
    private FloatQuat decomposeRotation_general_s2273f2d9_2(float _t36, float _t56, float _t60, float _t63, float _t73, float _t77, float _t78, float _t80, float _t81, float _t82, float _t86, float _t87, float _t88, float _t89, float _sp0) {
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t88));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t89));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t87));
        float _sfx0, _sfx1, _sfx2, _sfx3;
        if (_t82 > 0.0f) {
            _sfx0 = _sp0 * _t60;
            _sfx1 = _sp0 * _t81;
            _sfx2 = _sp0 * _t78;
            _sfx3 = 0.5f * (float) java.lang.Math.sqrt(_t86);
        } else {
            if (_t73 > java.lang.Math.max(_t36, _t56)) {
                _sfx0 = 0.5f * (float) java.lang.Math.sqrt(_t87);
                _sfx1 = _sp3 * _t77;
                _sfx2 = _sp3 * _t80;
                _sfx3 = _sp3 * _t60;
            } else {
                if (_t36 > _t56) {
                    _sfx0 = _sp1 * _t77;
                    _sfx1 = 0.5f * (float) java.lang.Math.sqrt(_t88);
                    _sfx2 = _sp1 * _t63;
                    _sfx3 = _sp1 * _t81;
                } else {
                    _sfx0 = _sp2 * _t80;
                    _sfx1 = _sp2 * _t63;
                    _sfx2 = 0.5f * (float) java.lang.Math.sqrt(_t89);
                    _sfx3 = _sp2 * _t78;
                }
            }
        }
        return new FloatQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }


    /**
     * Extract the rotation part of this matrix, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting quaternion
     */
    public FloatQuat decomposeRotation() {
        if ((this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getNormalizedRotation_identity();
        return decomposeRotation_general();
    }

    /** Private tail of {@code decomposeScale_general}; reached only through it. */
    private Float3 decomposeScale_general_s0_tail(float _t27, float _t20, float _t28, float _t19, float _t21, float _t18, float _t8, float _t9, float _t10, float _t4) {
        float _t32, _t33, _t34;
        if (_t27 != 0.0f) {
            _t32 = _t20 * _t28;
            _t33 = _t19 * _t28;
            _t34 = _t21 * _t28;
        } else {
            _t32 = 0.0f;
            _t33 = 0.0f;
            _t34 = 0.0f;
        }
        float _t38 = -Math.fma(Math.fma(_t18, _t8, this.m22), _t33, Math.fma(Math.fma(_t18, _t9, this.m02), _t32, Math.fma(_t18, _t10, this.m12) * _t34));
        float _t42 = Math.fma(_t18, _t8, Math.fma(_t38, _t33, this.m22));
        float _t43 = Math.fma(_t18, _t9, Math.fma(_t38, _t32, this.m02));
        float _t44 = Math.fma(_t18, _t10, Math.fma(_t38, _t34, this.m12));
        float _t47 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44));
        return decomposeScale_general_s0_tail2(_t47, _t44, (1.0f / (float) java.lang.Math.sqrt(_t47)), _t43, _t42, _t32, _t34, _t8, _t33, _t9, _t10, _t4, _t27);
    }

    /** Private tail of {@code decomposeScale_general}; reached only through it. */
    private Float3 decomposeScale_general_s0_tail2(float _t47, float _t44, float _t48, float _t43, float _t42, float _t32, float _t34, float _t8, float _t33, float _t9, float _t10, float _t4, float _t27) {
        float _t52, _t53, _t54;
        if (_t47 != 0.0f) {
            _t52 = _t44 * _t48;
            _t53 = _t43 * _t48;
            _t54 = _t42 * _t48;
        } else {
            _t52 = 0.0f;
            _t53 = 0.0f;
            _t54 = 0.0f;
        }
        float _sfx0 = Math.fma(Math.fma(_t32, _t52, -(_t34 * _t53)), _t8, Math.fma(Math.fma(_t34, _t54, -(_t33 * _t52)), _t9, Math.fma(_t33, _t53, -(_t32 * _t54)) * _t10)) < 0.0f ? -_t4 : _t4;
        float _sfx1 = (float) java.lang.Math.sqrt(_t27);
        float _sfx2 = (float) java.lang.Math.sqrt(_t47);
        return new Float3(_sfx0, _sfx1, _sfx2);
    }


    /**
     * Private body of {@code decomposeScale}, specialized by runtime matrix properties; reached
     * only through the public {@code decomposeScale} dispatcher.
     */
    private Float3 decomposeScale_general() {
        float _t2 = Math.fma(this.m20, this.m20, Math.fma(this.m00, this.m00, this.m10 * this.m10));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        float _t8, _t9, _t10;
        if (_t2 != 0.0f) {
            _t8 = this.m20 * _t3;
            _t9 = this.m00 * _t3;
            _t10 = this.m10 * _t3;
        } else {
            _t8 = 0.0f;
            _t9 = 0.0f;
            _t10 = 0.0f;
        }
        float _t17 = -Math.fma(this.m21, _t8, Math.fma(this.m01, _t9, this.m11 * _t10));
        float _t19 = Math.fma(_t17, _t8, this.m21);
        float _t20 = Math.fma(_t17, _t9, this.m01);
        float _t21 = Math.fma(_t17, _t10, this.m11);
        float _t27 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
        return decomposeScale_general_s0_tail(_t27, _t20, (1.0f / (float) java.lang.Math.sqrt(_t27)), _t19, _t21, -Math.fma(this.m22, _t8, Math.fma(this.m02, _t9, this.m12 * _t10)), _t8, _t9, _t10, (float) java.lang.Math.sqrt(_t2));
    }


    /**
     * Extract the scaling factors of this matrix via Gram-Schmidt orthogonalization (skew-aware;
     * the x factor carries the sign of a reflection when the determinant is negative), returning
     * the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the resulting vector
     */
    public Float3 decomposeScale() {
        if ((this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getScale_identity();
        return decomposeScale_general();
    }


    /**
     * Private body of {@code decomposeSkew}, specialized by runtime matrix properties; reached only
     * through the public {@code decomposeSkew} dispatcher.
     */
    private Float3 decomposeSkew_translation() {
        return new Float3(this.m12, this.m02, 0.0f);
    }

    /** Private tail of {@code decomposeSkew_general}; reached only through it. */
    private Float3 decomposeSkew_general_s0_tail(float _t26, float _t19, float _t27, float _t20, float _t21, float _t16, float _t7, float _t8, float _t9, float _t14, float _t28) {
        float _t32, _t33, _t34;
        if (_t26 != 0.0f) {
            _t32 = _t19 * _t27;
            _t33 = _t20 * _t27;
            _t34 = _t21 * _t27;
        } else {
            _t32 = 0.0f;
            _t33 = 0.0f;
            _t34 = 0.0f;
        }
        float _t37 = Math.fma(Math.fma(_t16, _t7, this.m22), _t32, Math.fma(Math.fma(_t16, _t8, this.m02), _t33, Math.fma(_t16, _t9, this.m12) * _t34));
        float _t38 = -_t37;
        float _t42 = Math.fma(_t16, _t7, Math.fma(_t38, _t32, this.m22));
        float _t43 = Math.fma(_t16, _t8, Math.fma(_t38, _t33, this.m02));
        float _t44 = Math.fma(_t16, _t9, Math.fma(_t38, _t34, this.m12));
        float _t47 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44));
        float _t48 = (1.0f / (float) java.lang.Math.sqrt(_t47));
        return decomposeSkew_general_s0_tail2(_t47, _t44, _t48, _t43, _t42, _t33, _t34, _t7, _t32, _t8, _t9, _t37, _t14 * _t48, _t28);
    }

    /** Private tail of {@code decomposeSkew_general}; reached only through it. */
    private Float3 decomposeSkew_general_s0_tail2(float _t47, float _t44, float _t48, float _t43, float _t42, float _t33, float _t34, float _t7, float _t32, float _t8, float _t9, float _t37, float _t49, float _t28) {
        float _t53, _t54, _t55;
        if (_t47 != 0.0f) {
            _t53 = _t44 * _t48;
            _t54 = _t43 * _t48;
            _t55 = _t42 * _t48;
        } else {
            _t53 = 0.0f;
            _t54 = 0.0f;
            _t55 = 0.0f;
        }
        float _sfx0 = _t37 * _t48;
        float _sfx1, _sfx2;
        if (Math.fma(Math.fma(_t33, _t53, -(_t34 * _t54)), _t7, Math.fma(Math.fma(_t34, _t55, -(_t32 * _t53)), _t8, Math.fma(_t32, _t54, -(_t33 * _t55)) * _t9)) < 0.0f) {
            _sfx1 = -_t49;
            _sfx2 = -_t28;
        } else {
            _sfx1 = _t49;
            _sfx2 = _t28;
        }
        return new Float3(_sfx0, _sfx1, _sfx2);
    }


    /**
     * Private body of {@code decomposeSkew}, specialized by runtime matrix properties; reached only
     * through the public {@code decomposeSkew} dispatcher.
     */
    private Float3 decomposeSkew_general() {
        float _t2 = Math.fma(this.m20, this.m20, Math.fma(this.m00, this.m00, this.m10 * this.m10));
        float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
        float _t7, _t8, _t9;
        if (_t2 != 0.0f) {
            _t7 = this.m20 * _t3;
            _t8 = this.m00 * _t3;
            _t9 = this.m10 * _t3;
        } else {
            _t7 = 0.0f;
            _t8 = 0.0f;
            _t9 = 0.0f;
        }
        float _t14 = Math.fma(this.m22, _t7, Math.fma(this.m02, _t8, this.m12 * _t9));
        float _t15 = Math.fma(this.m21, _t7, Math.fma(this.m01, _t8, this.m11 * _t9));
        float _t17 = -_t15;
        float _t19 = Math.fma(_t17, _t7, this.m21);
        float _t20 = Math.fma(_t17, _t8, this.m01);
        float _t21 = Math.fma(_t17, _t9, this.m11);
        float _t26 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
        float _t27 = (1.0f / (float) java.lang.Math.sqrt(_t26));
        return decomposeSkew_general_s0_tail(_t26, _t19, _t27, _t20, _t21, -_t14, _t7, _t8, _t9, _t14, _t15 * _t27);
    }


    /**
     * Extract the shear (skew) factors of this matrix via Gram-Schmidt orthogonalization, as
     * {@code (skewYZ, skewXZ, skewXY)} (all zero for a shear-free matrix), returning the result as
     * a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}; this matrix
     * must be invertible.
     *
     * @return the resulting vector
     */
    public Float3 decomposeSkew() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getEulerAnglesXYZ_identity();
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return decomposeSkew_translation();
        return decomposeSkew_general();
    }


    /**
     * Create an identity matrix.
     * <p>
     * Valid input: the method reads no input.
     *
     * @return the resulting matrix
     */
    public static Float3x3 makeIdentity() {
        return new Float3x3(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_IDENTITY);
    }


    /**
     * Linearly interpolate between this matrix and {@code other} using the interpolation factor
     * {@code t}, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Float3x3 lerp(Float3x3 other, float t) {
        return new Float3x3(Math.fma(t, other.m00() - this.m00, this.m00), Math.fma(t, other.m01() - this.m01, this.m01), Math.fma(t, other.m02() - this.m02, this.m02), Math.fma(t, other.m10() - this.m10, this.m10), Math.fma(t, other.m11() - this.m11, this.m11), Math.fma(t, other.m12() - this.m12, this.m12), Math.fma(t, other.m20() - this.m20, this.m20), Math.fma(t, other.m21() - this.m21, this.m21), Math.fma(t, other.m22() - this.m22, this.m22), ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & other.properties()) | ((Joml.UNIQUE_TRANSLATION & this.properties & other.properties()) >> 1));
    }


    /**
     * Linearly interpolate between this matrix and ({@code m00}, {@code m01}, {@code m02},
     * {@code m10}, {@code m11}, {@code m12}, {@code m20}, {@code m21}, {@code m22}) using the
     * interpolation factor {@code t}, returning the result as a value.
     * <p>
     * The interpolation starts at this matrix (interpolation factor {@code 0}) and ends at
     * ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12}, {@code m20},
     * {@code m21}, {@code m22}) (interpolation factor {@code 1}). Each linearly interpolated
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
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @return the resulting matrix
     */
    public Float3x3 lerp(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float t) {
        return new Float3x3(Math.fma(t, m00 - this.m00, this.m00), Math.fma(t, m01 - this.m01, this.m01), Math.fma(t, m02 - this.m02, this.m02), Math.fma(t, m10 - this.m10, this.m10), Math.fma(t, m11 - this.m11, this.m11), Math.fma(t, m12 - this.m12, this.m12), Math.fma(t, m20 - this.m20, this.m20), Math.fma(t, m21 - this.m21, this.m21), Math.fma(t, m22 - this.m22, this.m22), 0);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general(Float3x3 right) {
        return new Float3x3(Math.fma(right.m20(), this.m02, Math.fma(right.m00(), this.m00, right.m10() * this.m01)), Math.fma(right.m21(), this.m02, Math.fma(right.m01(), this.m00, right.m11() * this.m01)), Math.fma(right.m22(), this.m02, Math.fma(right.m02(), this.m00, right.m12() * this.m01)), Math.fma(right.m20(), this.m12, Math.fma(right.m00(), this.m10, right.m10() * this.m11)), Math.fma(right.m21(), this.m12, Math.fma(right.m01(), this.m10, right.m11() * this.m11)), Math.fma(right.m22(), this.m12, Math.fma(right.m02(), this.m10, right.m12() * this.m11)), Math.fma(right.m20(), this.m22, Math.fma(right.m00(), this.m20, right.m10() * this.m21)), Math.fma(right.m21(), this.m22, Math.fma(right.m01(), this.m20, right.m11() * this.m21)), Math.fma(right.m22(), this.m22, Math.fma(right.m02(), this.m20, right.m12() * this.m21)), 0);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation(Float3x3 right) {
        return new Float3x3(Math.fma(right.m20(), this.m02, right.m00()), Math.fma(right.m21(), this.m02, right.m01()), Math.fma(right.m22(), this.m02, right.m02()), Math.fma(right.m20(), this.m12, right.m10()), Math.fma(right.m21(), this.m12, right.m11()), Math.fma(right.m22(), this.m12, right.m12()), right.m20(), right.m21(), right.m22(), Joml.BIT_TRANSLATION & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal(Float3x3 right) {
        return new Float3x3(Math.fma(right.m20(), this.m02, Math.fma(right.m00(), this.m11, -(right.m10() * this.m10))), Math.fma(right.m21(), this.m02, Math.fma(right.m01(), this.m11, -(right.m11() * this.m10))), Math.fma(right.m22(), this.m02, Math.fma(right.m02(), this.m11, -(right.m12() * this.m10))), Math.fma(right.m20(), this.m12, Math.fma(right.m00(), this.m10, right.m10() * this.m11)), Math.fma(right.m21(), this.m12, Math.fma(right.m01(), this.m10, right.m11() * this.m11)), Math.fma(right.m22(), this.m12, Math.fma(right.m02(), this.m10, right.m12() * this.m11)), right.m20(), right.m21(), right.m22(), Joml.BIT_ORTHOGONAL & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_affine(Float3x3 right) {
        return new Float3x3(Math.fma(right.m20(), this.m02, Math.fma(right.m00(), this.m00, right.m10() * this.m01)), Math.fma(right.m21(), this.m02, Math.fma(right.m01(), this.m00, right.m11() * this.m01)), Math.fma(right.m22(), this.m02, Math.fma(right.m02(), this.m00, right.m12() * this.m01)), Math.fma(right.m20(), this.m12, Math.fma(right.m00(), this.m10, right.m10() * this.m11)), Math.fma(right.m21(), this.m12, Math.fma(right.m01(), this.m10, right.m11() * this.m11)), Math.fma(right.m22(), this.m12, Math.fma(right.m02(), this.m10, right.m12() * this.m11)), right.m20(), right.m21(), right.m22(), Joml.BIT_AFFINE & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation_translation(Float3x3 right) {
        return new Float3x3(1.0f, 0.0f, right.m02() + this.m02, 0.0f, 1.0f, right.m12() + this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation_affine(Float3x3 right) {
        return new Float3x3(right.m00(), right.m01(), right.m02() + this.m02, right.m10(), right.m11(), right.m12() + this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal_translation(Float3x3 right, int _props) {
        return new Float3x3(this.m00, this.m01, Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02)), this.m10, this.m11, Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), 0.0f, 0.0f, 1.0f, _props);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal_affine(Float3x3 right) {
        return new Float3x3(Math.fma(right.m00(), this.m11, -(right.m10() * this.m10)), Math.fma(right.m01(), this.m11, -(right.m11() * this.m10)), Math.fma(-right.m12(), this.m10, Math.fma(right.m02(), this.m11, this.m02)), Math.fma(right.m00(), this.m10, right.m10() * this.m11), Math.fma(right.m01(), this.m10, right.m11() * this.m11), Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_affine_affine(Float3x3 right) {
        return new Float3x3(Math.fma(right.m00(), this.m00, right.m10() * this.m01), Math.fma(right.m01(), this.m00, right.m11() * this.m01), Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02)), Math.fma(right.m00(), this.m10, right.m10() * this.m11), Math.fma(right.m01(), this.m10, right.m11() * this.m11), Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general_translation(Float3x3 right) {
        return new Float3x3(this.m00, this.m01, Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02)), this.m10, this.m11, Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), this.m20, this.m21, Math.fma(right.m02(), this.m20, Math.fma(right.m12(), this.m21, this.m22)), 0);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general_affine(Float3x3 right) {
        return new Float3x3(Math.fma(right.m00(), this.m00, right.m10() * this.m01), Math.fma(right.m01(), this.m00, right.m11() * this.m01), Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02)), Math.fma(right.m00(), this.m10, right.m10() * this.m11), Math.fma(right.m01(), this.m10, right.m11() * this.m11), Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), Math.fma(right.m00(), this.m20, right.m10() * this.m21), Math.fma(right.m01(), this.m20, right.m11() * this.m21), Math.fma(right.m02(), this.m20, Math.fma(right.m12(), this.m21, this.m22)), 0);
    }


    /**
     * Multiply this matrix by {@code right}, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the operand, then the new matrix will be
     * {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the transformation of the operand will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param right the right operand
     * @return the resulting matrix
     */
    public Float3x3 mul(Float3x3 right) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return right;
        int q = right.properties();
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_translation_affine(right);
            return mul_translation(right);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation(right, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_orthogonal_affine(right);
            return mul_orthogonal(right);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation(right, Joml.BIT_AFFINE & q);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_affine_affine(right);
            return mul_affine(right);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_general_translation(right);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_general_affine(right);
        return mul_general(right);
    }


    /**
     * Multiply this matrix by ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11},
     * {@code m12}, {@code m20}, {@code m21}, {@code m22}), returning the result as a value.
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
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @return the resulting matrix
     */
    public Float3x3 mul(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
        return new Float3x3(Math.fma(m20, this.m02, Math.fma(m00, this.m00, m10 * this.m01)), Math.fma(m21, this.m02, Math.fma(m01, this.m00, m11 * this.m01)), Math.fma(m22, this.m02, Math.fma(m02, this.m00, m12 * this.m01)), Math.fma(m20, this.m12, Math.fma(m00, this.m10, m10 * this.m11)), Math.fma(m21, this.m12, Math.fma(m01, this.m10, m11 * this.m11)), Math.fma(m22, this.m12, Math.fma(m02, this.m10, m12 * this.m11)), Math.fma(m20, this.m22, Math.fma(m00, this.m20, m10 * this.m21)), Math.fma(m21, this.m22, Math.fma(m01, this.m20, m11 * this.m21)), Math.fma(m22, this.m22, Math.fma(m02, this.m20, m12 * this.m21)), 0);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_identity(Float2x2 right) {
        return new Float3x3(right.m00(), right.m01(), 0.0f, right.m10(), right.m11(), 0.0f, 0.0f, 0.0f, 1.0f, (right.properties() & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation(Float2x2 right) {
        return new Float3x3(right.m00(), right.m01(), this.m02, right.m10(), right.m11(), this.m12, 0.0f, 0.0f, 1.0f, (right.properties() & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal(Float2x2 right) {
        return new Float3x3(Math.fma(right.m00(), this.m11, -(right.m10() * this.m10)), Math.fma(right.m01(), this.m11, -(right.m11() * this.m10)), this.m02, Math.fma(right.m00(), this.m10, right.m10() * this.m11), Math.fma(right.m01(), this.m10, right.m11() * this.m11), this.m12, 0.0f, 0.0f, 1.0f, (right.properties() & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_affine(Float2x2 right) {
        return new Float3x3(Math.fma(right.m00(), this.m00, right.m10() * this.m01), Math.fma(right.m01(), this.m00, right.m11() * this.m01), this.m02, Math.fma(right.m00(), this.m10, right.m10() * this.m11), Math.fma(right.m01(), this.m10, right.m11() * this.m11), this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general(Float2x2 right) {
        return new Float3x3(Math.fma(right.m00(), this.m00, right.m10() * this.m01), Math.fma(right.m01(), this.m00, right.m11() * this.m01), this.m02, Math.fma(right.m00(), this.m10, right.m10() * this.m11), Math.fma(right.m01(), this.m10, right.m11() * this.m11), this.m12, Math.fma(right.m00(), this.m20, right.m10() * this.m21), Math.fma(right.m01(), this.m20, right.m11() * this.m21), this.m22, 0);
    }


    /**
     * Multiply this matrix by {@code right}, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Float3x3 mul(Float2x2 right) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return mul_identity(right);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation(right);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return mul_orthogonal(right);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return mul_affine(right);
        return mul_general(right);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general(Float2x3 right) {
        return new Float3x3(Math.fma(right.m00(), this.m00, right.m10() * this.m01), Math.fma(right.m01(), this.m00, right.m11() * this.m01), Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02)), Math.fma(right.m00(), this.m10, right.m10() * this.m11), Math.fma(right.m01(), this.m10, right.m11() * this.m11), Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), Math.fma(right.m00(), this.m20, right.m10() * this.m21), Math.fma(right.m01(), this.m20, right.m11() * this.m21), Math.fma(right.m02(), this.m20, Math.fma(right.m12(), this.m21, this.m22)), 0);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_identity(Float2x3 right) {
        return new Float3x3(right.m00(), right.m01(), right.m02(), right.m10(), right.m11(), right.m12(), 0.0f, 0.0f, 1.0f, right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation(Float2x3 right) {
        return new Float3x3(right.m00(), right.m01(), right.m02() + this.m02, right.m10(), right.m11(), right.m12() + this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal(Float2x3 right) {
        return new Float3x3(Math.fma(right.m00(), this.m11, -(right.m10() * this.m10)), Math.fma(right.m01(), this.m11, -(right.m11() * this.m10)), Math.fma(-right.m12(), this.m10, Math.fma(right.m02(), this.m11, this.m02)), Math.fma(right.m00(), this.m10, right.m10() * this.m11), Math.fma(right.m01(), this.m10, right.m11() * this.m11), Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_affine(Float2x3 right) {
        return new Float3x3(Math.fma(right.m00(), this.m00, right.m10() * this.m01), Math.fma(right.m01(), this.m00, right.m11() * this.m01), Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02)), Math.fma(right.m00(), this.m10, right.m10() * this.m11), Math.fma(right.m01(), this.m10, right.m11() * this.m11), Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_translation_translation(Float2x3 right) {
        return new Float3x3(1.0f, 0.0f, right.m02() + this.m02, 0.0f, 1.0f, right.m12() + this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_orthogonal_translation(Float2x3 right, int _props) {
        return new Float3x3(this.m00, this.m01, Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02)), this.m10, this.m11, Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), 0.0f, 0.0f, 1.0f, _props);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3x3 mul_general_translation(Float2x3 right) {
        return new Float3x3(this.m00, this.m01, Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02)), this.m10, this.m11, Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), this.m20, this.m21, Math.fma(right.m02(), this.m20, Math.fma(right.m12(), this.m21, this.m22)), 0);
    }


    /**
     * Multiply this matrix by {@code right}, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Float3x3 mul(Float2x3 right) {
        int p = this.properties;
        int q = right.properties();
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(1.0f, 0.0f, right.m02(), 0.0f, 1.0f, right.m12(), 0.0f, 0.0f, 1.0f, right.properties());
            return mul_identity(right);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_translation_translation(right);
            return mul_translation(right);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation(right, Joml.BIT_ORTHOGONAL & q);
            return mul_orthogonal(right);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_orthogonal_translation(right, Joml.BIT_AFFINE & q);
            return mul_affine(right);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return mul_general_translation(right);
        return mul_general(right);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general(Float3x3 other) {
        return new Float3x3(Math.fma(other.m02(), this.m20, Math.fma(other.m00(), this.m00, other.m01() * this.m10)), Math.fma(other.m02(), this.m21, Math.fma(other.m00(), this.m01, other.m01() * this.m11)), Math.fma(other.m02(), this.m22, Math.fma(other.m00(), this.m02, other.m01() * this.m12)), Math.fma(other.m12(), this.m20, Math.fma(other.m10(), this.m00, other.m11() * this.m10)), Math.fma(other.m12(), this.m21, Math.fma(other.m10(), this.m01, other.m11() * this.m11)), Math.fma(other.m12(), this.m22, Math.fma(other.m10(), this.m02, other.m11() * this.m12)), Math.fma(other.m22(), this.m20, Math.fma(other.m20(), this.m00, other.m21() * this.m10)), Math.fma(other.m22(), this.m21, Math.fma(other.m20(), this.m01, other.m21() * this.m11)), Math.fma(other.m22(), this.m22, Math.fma(other.m20(), this.m02, other.m21() * this.m12)), 0);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation(Float3x3 other) {
        return new Float3x3(other.m00(), other.m01(), Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02())), other.m10(), other.m11(), Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12())), other.m20(), other.m21(), Math.fma(other.m20(), this.m02, Math.fma(other.m21(), this.m12, other.m22())), Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal(Float3x3 other) {
        return new Float3x3(Math.fma(other.m00(), this.m11, other.m01() * this.m10), Math.fma(other.m01(), this.m11, -(other.m00() * this.m10)), Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02())), Math.fma(other.m10(), this.m11, other.m11() * this.m10), Math.fma(other.m11(), this.m11, -(other.m10() * this.m10)), Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12())), Math.fma(other.m20(), this.m11, other.m21() * this.m10), Math.fma(other.m21(), this.m11, -(other.m20() * this.m10)), Math.fma(other.m20(), this.m02, Math.fma(other.m21(), this.m12, other.m22())), Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_affine(Float3x3 other) {
        return new Float3x3(Math.fma(other.m00(), this.m00, other.m01() * this.m10), Math.fma(other.m00(), this.m01, other.m01() * this.m11), Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02())), Math.fma(other.m10(), this.m00, other.m11() * this.m10), Math.fma(other.m10(), this.m01, other.m11() * this.m11), Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12())), Math.fma(other.m20(), this.m00, other.m21() * this.m10), Math.fma(other.m20(), this.m01, other.m21() * this.m11), Math.fma(other.m20(), this.m02, Math.fma(other.m21(), this.m12, other.m22())), Joml.BIT_AFFINE & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation_translation(Float3x3 other) {
        return new Float3x3(1.0f, 0.0f, other.m02() + this.m02, 0.0f, 1.0f, other.m12() + this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation_affine(Float3x3 other) {
        return new Float3x3(other.m00(), other.m01(), Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02())), other.m10(), other.m11(), Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12())), 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal_translation(Float3x3 other, int _props) {
        return new Float3x3(this.m00, this.m01, other.m02() + this.m02, this.m10, this.m11, other.m12() + this.m12, 0.0f, 0.0f, 1.0f, _props);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal_affine(Float3x3 other) {
        return new Float3x3(Math.fma(other.m00(), this.m11, other.m01() * this.m10), Math.fma(other.m01(), this.m11, -(other.m00() * this.m10)), Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02())), Math.fma(other.m10(), this.m11, other.m11() * this.m10), Math.fma(other.m11(), this.m11, -(other.m10() * this.m10)), Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12())), 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_affine_affine(Float3x3 other) {
        return new Float3x3(Math.fma(other.m00(), this.m00, other.m01() * this.m10), Math.fma(other.m00(), this.m01, other.m01() * this.m11), Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02())), Math.fma(other.m10(), this.m00, other.m11() * this.m10), Math.fma(other.m10(), this.m01, other.m11() * this.m11), Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12())), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general_translation(Float3x3 other) {
        return new Float3x3(Math.fma(other.m02(), this.m20, this.m00), Math.fma(other.m02(), this.m21, this.m01), Math.fma(other.m02(), this.m22, this.m02), Math.fma(other.m12(), this.m20, this.m10), Math.fma(other.m12(), this.m21, this.m11), Math.fma(other.m12(), this.m22, this.m12), this.m20, this.m21, this.m22, 0);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general_affine(Float3x3 other) {
        return new Float3x3(Math.fma(other.m02(), this.m20, Math.fma(other.m00(), this.m00, other.m01() * this.m10)), Math.fma(other.m02(), this.m21, Math.fma(other.m00(), this.m01, other.m01() * this.m11)), Math.fma(other.m02(), this.m22, Math.fma(other.m00(), this.m02, other.m01() * this.m12)), Math.fma(other.m12(), this.m20, Math.fma(other.m10(), this.m00, other.m11() * this.m10)), Math.fma(other.m12(), this.m21, Math.fma(other.m10(), this.m01, other.m11() * this.m11)), Math.fma(other.m12(), this.m22, Math.fma(other.m10(), this.m02, other.m11() * this.m12)), this.m20, this.m21, this.m22, 0);
    }


    /**
     * Pre-multiply the transformation {@code other} onto this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the given transformation matrix, then the
     * new matrix will be {@code T * M}. So when transforming a vector {@code v} with the new matrix
     * by using {@code T * M * v}, the given transformation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the left operand
     * @return the resulting matrix
     */
    public Float3x3 preMul(Float3x3 other) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return other;
        int q = other.properties();
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_translation_affine(other);
            return preMul_translation(other);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, Joml.BIT_ORTHOGONAL & q);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_orthogonal_affine(other);
            return preMul_orthogonal(other);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, Joml.BIT_AFFINE & q);
            if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_affine_affine(other);
            return preMul_affine(other);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_general_translation(other);
        if ((q & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_general_affine(other);
        return preMul_general(other);
    }


    /**
     * Pre-multiply the transformation ({@code m00}, {@code m01}, {@code m02}, {@code m10},
     * {@code m11}, {@code m12}, {@code m20}, {@code m21}, {@code m22}) onto this matrix, returning
     * the result as a value.
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
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @return the resulting matrix
     */
    public Float3x3 preMul(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
        return new Float3x3(Math.fma(m02, this.m20, Math.fma(m00, this.m00, m01 * this.m10)), Math.fma(m02, this.m21, Math.fma(m00, this.m01, m01 * this.m11)), Math.fma(m02, this.m22, Math.fma(m00, this.m02, m01 * this.m12)), Math.fma(m12, this.m20, Math.fma(m10, this.m00, m11 * this.m10)), Math.fma(m12, this.m21, Math.fma(m10, this.m01, m11 * this.m11)), Math.fma(m12, this.m22, Math.fma(m10, this.m02, m11 * this.m12)), Math.fma(m22, this.m20, Math.fma(m20, this.m00, m21 * this.m10)), Math.fma(m22, this.m21, Math.fma(m20, this.m01, m21 * this.m11)), Math.fma(m22, this.m22, Math.fma(m20, this.m02, m21 * this.m12)), 0);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_identity(Float2x2 other) {
        return new Float3x3(other.m00(), other.m01(), 0.0f, other.m10(), other.m11(), 0.0f, 0.0f, 0.0f, 1.0f, (other.properties() & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation(Float2x2 other) {
        return new Float3x3(other.m00(), other.m01(), Math.fma(other.m00(), this.m02, other.m01() * this.m12), other.m10(), other.m11(), Math.fma(other.m10(), this.m02, other.m11() * this.m12), 0.0f, 0.0f, 1.0f, (other.properties() & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal(Float2x2 other) {
        return new Float3x3(Math.fma(other.m00(), this.m11, other.m01() * this.m10), Math.fma(other.m01(), this.m11, -(other.m00() * this.m10)), Math.fma(other.m00(), this.m02, other.m01() * this.m12), Math.fma(other.m10(), this.m11, other.m11() * this.m10), Math.fma(other.m11(), this.m11, -(other.m10() * this.m10)), Math.fma(other.m10(), this.m02, other.m11() * this.m12), 0.0f, 0.0f, 1.0f, (other.properties() & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_affine(Float2x2 other) {
        return new Float3x3(Math.fma(other.m00(), this.m00, other.m01() * this.m10), Math.fma(other.m00(), this.m01, other.m01() * this.m11), Math.fma(other.m00(), this.m02, other.m01() * this.m12), Math.fma(other.m10(), this.m00, other.m11() * this.m10), Math.fma(other.m10(), this.m01, other.m11() * this.m11), Math.fma(other.m10(), this.m02, other.m11() * this.m12), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general(Float2x2 other) {
        return new Float3x3(Math.fma(other.m00(), this.m00, other.m01() * this.m10), Math.fma(other.m00(), this.m01, other.m01() * this.m11), Math.fma(other.m00(), this.m02, other.m01() * this.m12), Math.fma(other.m10(), this.m00, other.m11() * this.m10), Math.fma(other.m10(), this.m01, other.m11() * this.m11), Math.fma(other.m10(), this.m02, other.m11() * this.m12), this.m20, this.m21, this.m22, 0);
    }


    /**
     * Pre-multiply {@code other} onto this matrix, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Float3x3 preMul(Float2x2 other) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preMul_identity(other);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation(other);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preMul_orthogonal(other);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preMul_affine(other);
        return preMul_general(other);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general(Float2x3 other) {
        return new Float3x3(Math.fma(other.m02(), this.m20, Math.fma(other.m00(), this.m00, other.m01() * this.m10)), Math.fma(other.m02(), this.m21, Math.fma(other.m00(), this.m01, other.m01() * this.m11)), Math.fma(other.m02(), this.m22, Math.fma(other.m00(), this.m02, other.m01() * this.m12)), Math.fma(other.m12(), this.m20, Math.fma(other.m10(), this.m00, other.m11() * this.m10)), Math.fma(other.m12(), this.m21, Math.fma(other.m10(), this.m01, other.m11() * this.m11)), Math.fma(other.m12(), this.m22, Math.fma(other.m10(), this.m02, other.m11() * this.m12)), this.m20, this.m21, this.m22, 0);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_identity(Float2x3 other) {
        return new Float3x3(other.m00(), other.m01(), other.m02(), other.m10(), other.m11(), other.m12(), 0.0f, 0.0f, 1.0f, other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation(Float2x3 other) {
        return new Float3x3(other.m00(), other.m01(), Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02())), other.m10(), other.m11(), Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12())), 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal(Float2x3 other) {
        return new Float3x3(Math.fma(other.m00(), this.m11, other.m01() * this.m10), Math.fma(other.m01(), this.m11, -(other.m00() * this.m10)), Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02())), Math.fma(other.m10(), this.m11, other.m11() * this.m10), Math.fma(other.m11(), this.m11, -(other.m10() * this.m10)), Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12())), 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_affine(Float2x3 other) {
        return new Float3x3(Math.fma(other.m00(), this.m00, other.m01() * this.m10), Math.fma(other.m00(), this.m01, other.m01() * this.m11), Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02())), Math.fma(other.m10(), this.m00, other.m11() * this.m10), Math.fma(other.m10(), this.m01, other.m11() * this.m11), Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12())), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_translation_translation(Float2x3 other) {
        return new Float3x3(1.0f, 0.0f, other.m02() + this.m02, 0.0f, 1.0f, other.m12() + this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_orthogonal_translation(Float2x3 other, int _props) {
        return new Float3x3(this.m00, this.m01, other.m02() + this.m02, this.m10, this.m11, other.m12() + this.m12, 0.0f, 0.0f, 1.0f, _props);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Float3x3 preMul_general_translation(Float2x3 other) {
        return new Float3x3(Math.fma(other.m02(), this.m20, this.m00), Math.fma(other.m02(), this.m21, this.m01), Math.fma(other.m02(), this.m22, this.m02), Math.fma(other.m12(), this.m20, this.m10), Math.fma(other.m12(), this.m21, this.m11), Math.fma(other.m12(), this.m22, this.m12), this.m20, this.m21, this.m22, 0);
    }


    /**
     * Pre-multiply {@code other} onto this matrix, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Float3x3 preMul(Float2x3 other) {
        int p = this.properties;
        int q = other.properties();
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(1.0f, 0.0f, other.m02(), 0.0f, 1.0f, other.m12(), 0.0f, 0.0f, 1.0f, other.properties());
            return preMul_identity(other);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_translation_translation(other);
            return preMul_translation(other);
        }
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, Joml.BIT_ORTHOGONAL & q);
            return preMul_orthogonal(other);
        }
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_orthogonal_translation(other, Joml.BIT_AFFINE & q);
            return preMul_affine(other);
        }
        if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preMul_general_translation(other);
        return preMul_general(other);
    }


    /**
     * Add {@code other} scaled by {@code weight} to this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to scale and add
     * @param weight the factor to scale {@code other} by before adding
     * @return the resulting matrix
     */
    public Float3x3 addScaled(Float3x3 other, float weight) {
        return new Float3x3(Math.fma(weight, other.m00(), this.m00), Math.fma(weight, other.m01(), this.m01), Math.fma(weight, other.m02(), this.m02), Math.fma(weight, other.m10(), this.m10), Math.fma(weight, other.m11(), this.m11), Math.fma(weight, other.m12(), this.m12), Math.fma(weight, other.m20(), this.m20), Math.fma(weight, other.m21(), this.m21), Math.fma(weight, other.m22(), this.m22), 0);
    }


    /**
     * Add ({@code m00}, {@code m01}, {@code m02}, {@code m10}, {@code m11}, {@code m12},
     * {@code m20}, {@code m21}, {@code m22}) scaled by {@code weight} to this matrix, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param m00 the element in row 0, column 0 of the matrix
     * @param m01 the element in row 0, column 1 of the matrix
     * @param m02 the element in row 0, column 2 of the matrix
     * @param m10 the element in row 1, column 0 of the matrix
     * @param m11 the element in row 1, column 1 of the matrix
     * @param m12 the element in row 1, column 2 of the matrix
     * @param m20 the element in row 2, column 0 of the matrix
     * @param m21 the element in row 2, column 1 of the matrix
     * @param m22 the element in row 2, column 2 of the matrix
     * @param weight the factor to scale ({@code m00}, {@code m01}, {@code m02}, {@code m10},
     *        {@code m11}, {@code m12}, {@code m20}, {@code m21}, {@code m22}) by before adding
     * @return the resulting matrix
     */
    public Float3x3 addScaled(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22, float weight) {
        return new Float3x3(Math.fma(weight, m00, this.m00), Math.fma(weight, m01, this.m01), Math.fma(weight, m02, this.m02), Math.fma(weight, m10, this.m10), Math.fma(weight, m11, this.m11), Math.fma(weight, m12, this.m12), Math.fma(weight, m20, this.m20), Math.fma(weight, m21, this.m21), Math.fma(weight, m22, this.m22), 0);
    }


    /**
     * Create the outer product of {@code col} and {@code row}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param col the column vector (left operand)
     * @param row the row vector (right operand)
     * @return the resulting matrix
     */
    public static Float3x3 makeOuterProduct(Float3 col, Float3 row) {
        float colX = col.x();
        float colY = col.y();
        float colZ = col.z();
        float rowX = row.x();
        float rowY = row.y();
        float rowZ = row.z();
        return new Float3x3(colX * rowX, colX * rowY, colX * rowZ, colY * rowX, colY * rowY, colY * rowZ, colZ * rowX, colZ * rowY, colZ * rowZ, 0);
    }


    /**
     * Create the outer product of ({@code colX}, {@code colY}, {@code colZ}) and ({@code rowX},
     * {@code rowY}, {@code rowZ}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param colX the {@code x} component of the vector {@code (colX, colY, colZ)}
     * @param colY the {@code y} component of the vector {@code (colX, colY, colZ)}
     * @param colZ the {@code z} component of the vector {@code (colX, colY, colZ)}
     * @param rowX the {@code x} component of the vector {@code (rowX, rowY, rowZ)}
     * @param rowY the {@code y} component of the vector {@code (rowX, rowY, rowZ)}
     * @param rowZ the {@code z} component of the vector {@code (rowX, rowY, rowZ)}
     * @return the resulting matrix
     */
    public static Float3x3 makeOuterProduct(float colX, float colY, float colZ, float rowX, float rowY, float rowZ) {
        return new Float3x3(colX * rowX, colX * rowY, colX * rowZ, colY * rowX, colY * rowY, colY * rowZ, colZ * rowX, colZ * rowY, colZ * rowZ, 0);
    }


    /**
     * Apply a rotation transformation that makes {@code +z} point along {@code dir} to this matrix,
     * returning the result as a value.
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
     * @return the resulting matrix
     */
    public Float3x3 lookAlong(Float3 dir, Float3 up) {
        float dirX = dir.x();
        float dirY = dir.y();
        float dirZ = dir.z();
        float upX = up.x();
        float upY = up.y();
        float upZ = up.z();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return lookAlong_identity(dirX, dirY, dirZ, upX, upY, upZ);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return lookAlong_translation(dirX, dirY, dirZ, upX, upY, upZ);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return lookAlong_orthogonal(dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_general(dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** Private per-column body of {@code lookAlong_identity_s524747ee_tail}; reached only through it. */
    private float[] lookAlong_identity_s524747ee_tail_s721f8c61_c1(float _t15, float _t40, float _t16, float _t41, float _t39, float _t17) {
        return new float[] {Math.fma(_t15, _t40, -(_t16 * _t41)), Math.fma(_t16, _t39, -(_t17 * _t40)), Math.fma(_t17, _t41, -(_t15 * _t39))};
    }

    /** Private tail of {@code lookAlong_identity}; reached only through it. */
    private Float3x3 lookAlong_identity_s524747ee_tail(float dirZ, float _t14, float dirX, float _t31, float _t38, float _t32, float _t33, float _t15, int _props) {
        float _t16 = dirZ * _t14;
        float _t17 = dirX * _t14;
        float _t39 = _t31 * _t38;
        float _t40 = _t32 * _t38;
        float _t41 = _t33 * _t38;
        float[] _col0 = new float[] {_t39, _t41, _t40};
        float[] _col1 = lookAlong_identity_s524747ee_tail_s721f8c61_c1(_t15, _t40, _t16, _t41, _t39, _t17);
        float[] _col2 = new float[] {_t17, _t15, _t16};
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Private body of {@code lookAlong}, specialized by runtime matrix properties; reached only
     * through the public {@code lookAlong} dispatcher.
     */
    private Float3x3 lookAlong_identity(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t22 = Math.fma(-dirY, _sp0, upY);
        float _t23 = Math.fma(-dirZ, _sp0, upZ);
        float _t24 = Math.fma(-dirX, _sp0, upX);
        float _t31 = Math.fma(dirZ, _t22, -(dirY * _t23));
        float _t32 = Math.fma(dirY, _t24, -(dirX * _t22));
        float _t33 = Math.fma(dirX, _t23, -(dirZ * _t24));
        float _t38 = Math.fma(_t32, _t32, Math.fma(_t33, _t33, _t31 * _t31));
        if (!(_t38 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _t38 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_identity_s524747ee_tail(dirZ, _t14, dirX, _t31, (1.0f / (float) java.lang.Math.sqrt(_t38)), _t32, _t33, dirY * _t14, 0);
    }

    /** Private per-column body of {@code lookAlong_translation_s524747ee_tail}; reached only through it. */
    private float[] lookAlong_translation_s524747ee_tail_s6772785_c1(float _t44, float _t16, float _t39, float _t17, float _t41, float _t40, float _t15) {
        return new float[] {Math.fma(this.m02, _t44, Math.fma(_t16, _t39, -(_t17 * _t41))), Math.fma(this.m12, _t44, Math.fma(_t17, _t40, -(_t15 * _t39))), _t44};
    }

    /** Private per-column body of {@code lookAlong_translation_s524747ee_tail}; reached only through it. */
    private float[] lookAlong_translation_s524747ee_tail_s6772785_c2(float dirX, float _t14, float _t17, float dirY) {
        return new float[] {Math.fma(dirX, _t14, this.m02 * _t17), Math.fma(dirY, _t14, this.m12 * _t17), _t17};
    }

    /** Private tail of {@code lookAlong_translation}; reached only through it. */
    private Float3x3 lookAlong_translation_s524747ee_tail(float dirY, float _t14, float dirZ, float _t31, float _t38, float _t33, float _t32, float _t15, float dirX, int _props) {
        float _t16 = dirY * _t14;
        float _t17 = dirZ * _t14;
        float _t39 = _t31 * _t38;
        float _t40 = _t33 * _t38;
        float _t41 = _t32 * _t38;
        float[] _col0 = new float[] {Math.fma(this.m02, _t39, _t40), Math.fma(this.m12, _t39, _t41), _t39};
        float[] _col1 = lookAlong_translation_s524747ee_tail_s6772785_c1(Math.fma(_t15, _t41, -(_t16 * _t40)), _t16, _t39, _t17, _t41, _t40, _t15);
        float[] _col2 = lookAlong_translation_s524747ee_tail_s6772785_c2(dirX, _t14, _t17, dirY);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Private body of {@code lookAlong}, specialized by runtime matrix properties; reached only
     * through the public {@code lookAlong} dispatcher.
     */
    private Float3x3 lookAlong_translation(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t22 = Math.fma(-dirX, _sp0, upX);
        float _t23 = Math.fma(-dirY, _sp0, upY);
        float _t24 = Math.fma(-dirZ, _sp0, upZ);
        float _t31 = Math.fma(dirY, _t22, -(dirX * _t23));
        float _t32 = Math.fma(dirX, _t24, -(dirZ * _t22));
        float _t33 = Math.fma(dirZ, _t23, -(dirY * _t24));
        float _t38 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        if (!(_t38 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _t38 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_translation_s524747ee_tail(dirY, _t14, dirZ, _t31, (1.0f / (float) java.lang.Math.sqrt(_t38)), _t33, _t32, dirX * _t14, dirX, 0);
    }

    /**
     * Private per-column body of {@code lookAlong_orthogonal_s524747ee_tail}. Shared by the
     * identical private paths of {@code lookAlong}, {@code rotateAxis}, {@code rotateXYZ},
     * {@code rotateXZY}, {@code rotateYXZ}, {@code rotateYZX}, {@code rotateZXY} and
     * {@code rotateZYX}; reached only through them.
     */
    private float[] lookAlong_orthogonal_s524747ee_tail_s5ff7ace2_c0(float _t39, float _t40, float _t41) {
        return new float[] {Math.fma(this.m02, _t39, Math.fma(this.m00, _t40, this.m01 * _t41)), Math.fma(this.m12, _t39, Math.fma(this.m10, _t40, this.m11 * _t41)), _t39};
    }

    /** Private tail of {@code lookAlong_orthogonal}; reached only through it. */
    private Float3x3 lookAlong_orthogonal_s524747ee_tail(float dirY, float _t14, float dirZ, float _t31, float _t38, float _t33, float _t32, float _t15, int _props) {
        float _t16 = dirY * _t14;
        float _t17 = dirZ * _t14;
        float _t39 = _t31 * _t38;
        float _t40 = _t33 * _t38;
        float _t41 = _t32 * _t38;
        float[] _col0 = lookAlong_orthogonal_s524747ee_tail_s5ff7ace2_c0(_t39, _t40, _t41);
        float[] _col1 = lookAlong_orthogonal_s524747ee_tail_s5ff7ace2_c0(Math.fma(_t15, _t41, -(_t16 * _t40)), Math.fma(_t16, _t39, -(_t17 * _t41)), Math.fma(_t17, _t40, -(_t15 * _t39)));
        float[] _col2 = lookAlong_orthogonal_s524747ee_tail_s5ff7ace2_c0(_t17, _t15, _t16);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Private body of {@code lookAlong}, specialized by runtime matrix properties; reached only
     * through the public {@code lookAlong} dispatcher.
     */
    private Float3x3 lookAlong_orthogonal(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t22 = Math.fma(-dirX, _sp0, upX);
        float _t23 = Math.fma(-dirY, _sp0, upY);
        float _t24 = Math.fma(-dirZ, _sp0, upZ);
        float _t31 = Math.fma(dirY, _t22, -(dirX * _t23));
        float _t32 = Math.fma(dirX, _t24, -(dirZ * _t22));
        float _t33 = Math.fma(dirZ, _t23, -(dirY * _t24));
        float _t38 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        if (!(_t38 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _t38 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_orthogonal_s524747ee_tail(dirY, _t14, dirZ, _t31, (1.0f / (float) java.lang.Math.sqrt(_t38)), _t33, _t32, dirX * _t14, 0);
    }

    /**
     * Private per-column body of {@code lookAlong_general_s524747ee_tail}. Shared by the identical
     * private paths of {@code lookAlong}, {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY},
     * {@code rotateYXZ}, {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only
     * through them.
     */
    private float[] lookAlong_general_s524747ee_tail_s5ff7ace2_c0(float _t39, float _t40, float _t41) {
        return new float[] {Math.fma(this.m02, _t39, Math.fma(this.m00, _t40, this.m01 * _t41)), Math.fma(this.m12, _t39, Math.fma(this.m10, _t40, this.m11 * _t41)), Math.fma(this.m22, _t39, Math.fma(this.m20, _t40, this.m21 * _t41))};
    }

    /** Private tail of {@code lookAlong_general}; reached only through it. */
    private Float3x3 lookAlong_general_s524747ee_tail(float dirY, float _t14, float dirZ, float _t31, float _t38, float _t33, float _t32, float _t15, int _props) {
        float _t16 = dirY * _t14;
        float _t17 = dirZ * _t14;
        float _t39 = _t31 * _t38;
        float _t40 = _t33 * _t38;
        float _t41 = _t32 * _t38;
        float[] _col0 = lookAlong_general_s524747ee_tail_s5ff7ace2_c0(_t39, _t40, _t41);
        float[] _col1 = lookAlong_general_s524747ee_tail_s5ff7ace2_c0(Math.fma(_t15, _t41, -(_t16 * _t40)), Math.fma(_t16, _t39, -(_t17 * _t41)), Math.fma(_t17, _t40, -(_t15 * _t39)));
        float[] _col2 = lookAlong_general_s524747ee_tail_s5ff7ace2_c0(_t17, _t15, _t16);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Private body of {@code lookAlong}, specialized by runtime matrix properties; reached only
     * through the public {@code lookAlong} dispatcher.
     */
    private Float3x3 lookAlong_general(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t22 = Math.fma(-dirX, _sp0, upX);
        float _t23 = Math.fma(-dirY, _sp0, upY);
        float _t24 = Math.fma(-dirZ, _sp0, upZ);
        float _t31 = Math.fma(dirY, _t22, -(dirX * _t23));
        float _t32 = Math.fma(dirX, _t24, -(dirZ * _t22));
        float _t33 = Math.fma(dirZ, _t23, -(dirY * _t24));
        float _t38 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        if (!(_t38 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _t38 < Float.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_general_s524747ee_tail(dirY, _t14, dirZ, _t31, (1.0f / (float) java.lang.Math.sqrt(_t38)), _t33, _t32, dirX * _t14, 0);
    }


    /**
     * Apply a rotation transformation that makes {@code +z} point along ({@code dirX},
     * {@code dirY}, {@code dirZ}) to this matrix, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Float3x3 lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return lookAlong_identity(dirX, dirY, dirZ, upX, upY, upZ);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return lookAlong_translation(dirX, dirY, dirZ, upX, upY, upZ);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return lookAlong_orthogonal(dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_general(dirX, dirY, dirZ, upX, upY, upZ);
    }


    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private Float3x3 lookAlong_degenerate(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float[] _bundle0 = lookAlong_degenerate_sedf50666_1(dirX, dirY, dirZ, upX, upY, upZ);
        float[] _bundle1 = lookAlong_degenerate_sedf50666_2(_bundle0);
        float[] _bundle2 = lookAlong_degenerate_sedf50666_3(_bundle0[3], _bundle0[4], _bundle0[5], _bundle1[1], _bundle1[0], _bundle1[2]);
        return new Float3x3(_bundle2[0], _bundle2[1], _bundle2[2], _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], 0);
    }

    /** Part 1 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private float[] lookAlong_degenerate_sedf50666_1(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_t16));
        float _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0f) {
            _t21 = 0.0f;
            _t22 = 0.0f;
            _t23 = 1.0f;
            _t24 = 1.0f;
            _t25 = 0.0f;
            _t26 = 0.0f;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        return new float[] {_t21, _t22, _t23, _t24, _t25, _t26};
    }

    /** Part 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private float[] lookAlong_degenerate_sedf50666_2(float[] _bundle0) {
        float _t25 = _bundle0[4];
        float _t24 = _bundle0[3];
        float _t26 = _bundle0[5];
        float _t21 = _bundle0[0];
        float _t22 = _bundle0[1];
        float _t23 = _bundle0[2];
        float _t34, _t35, _t39;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t34 = 0.0f;
            _t35 = -_t26;
            _t39 = _t25;
        } else {
            _t34 = _t26;
            _t35 = 0.0f;
            _t39 = -_t24;
        }
        float _t41 = -Math.fma(_t21, _t24, Math.fma(_t25, _t22, _t26 * _t23));
        float _t42 = Math.fma(_t41, _t25, _t22);
        float _t43 = Math.fma(_t41, _t26, _t23);
        float _t44 = Math.fma(_t41, _t24, _t21);
        float _t53 = Math.fma(_t42, _t26, -(_t43 * _t25));
        float _t54 = Math.fma(_t44, _t25, -(_t42 * _t24));
        float _t55 = Math.fma(_t43, _t24, -(_t44 * _t26));
        float _t59 = Math.fma(_t53, _t53, Math.fma(_t54, _t54, _t55 * _t55));
        float _t64, _t65, _t66, _t67;
        if (_t59 <= Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t64 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t34, _t34, Math.fma(_t35, _t35, _t39 * _t39))));
            _t65 = _t64 * _t34;
            _t66 = _t64 * _t35;
            _t67 = _t64 * _t39;
        } else {
            _t64 = (1.0f / (float) java.lang.Math.sqrt(_t59));
            _t65 = _t64 * _t53;
            _t66 = _t64 * _t55;
            _t67 = _t64 * _t54;
        }
        return new float[] {_t65, _t66, _t67};
    }

    /** Part 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private float[] lookAlong_degenerate_sedf50666_3(float _t24, float _t25, float _t26, float _t66, float _t65, float _t67) {
        float _t74 = Math.fma(_t66, _t24, -(_t65 * _t25));
        float _t75 = Math.fma(_t65, _t26, -(_t67 * _t24));
        float _t76 = Math.fma(_t67, _t25, -(_t66 * _t26));
        return new float[] {Math.fma(this.m02, _t65, Math.fma(this.m00, _t66, this.m01 * _t67)), Math.fma(this.m02, _t76, Math.fma(this.m00, _t75, this.m01 * _t74)), Math.fma(this.m02, _t24, Math.fma(this.m00, _t25, this.m01 * _t26)), Math.fma(this.m12, _t65, Math.fma(this.m10, _t66, this.m11 * _t67)), Math.fma(this.m12, _t76, Math.fma(this.m10, _t75, this.m11 * _t74)), Math.fma(this.m12, _t24, Math.fma(this.m10, _t25, this.m11 * _t26)), Math.fma(this.m22, _t65, Math.fma(this.m20, _t66, this.m21 * _t67)), Math.fma(this.m22, _t76, Math.fma(this.m20, _t75, this.m21 * _t74)), Math.fma(this.m22, _t24, Math.fma(this.m20, _t25, this.m21 * _t26))};
    }


    /**
     * Create the rotation part of the unit dual quaternion {@code dq} (the encoded translation is
     * dropped).
     * <p>
     * Valid input: {@code dq} must be a unit dual quaternion.
     *
     * @param dq the dual quaternion to convert
     * @return the resulting matrix
     */
    public static Float3x3 makeFromDualQuat(FloatDualQuat dq) {
        float dqRX = dq.rX();
        float dqRY = dq.rY();
        float dqRZ = dq.rZ();
        float dqRW = dq.rW();
        float _sp0 = dqRX + dqRX;
        float _t0 = dqRY * dqRY;
        float _t2 = dqRZ * dqRW;
        float _t3 = dqRY * dqRW;
        float _t4 = dqRX * dqRX;
        float _t5 = dqRY * dqRZ;
        float _t6 = Math.fma(-2.0f, dqRZ * dqRZ, 1.0f);
        return new Float3x3(Math.fma(-2.0f, _t0, _t6), Math.fma(-2.0f, _t2, _sp0 * dqRY), 2.0f * Math.fma(dqRX, dqRZ, _t3), 2.0f * Math.fma(dqRX, dqRY, _t2), Math.fma(-2.0f, _t4, _t6), Math.fma(-2.0f, dqRX * dqRW, _t5 + _t5), Math.fma(-2.0f, _t3, _sp0 * dqRZ), 2.0f * Math.fma(dqRX, dqRW, _t5), Math.fma(-2.0f, _t4, Math.fma(-2.0f, _t0, 1.0f)), 0);
    }


    /**
     * Create the rotation part of the unit dual quaternion ({@code dqRX}, {@code dqRY},
     * {@code dqRZ}, {@code dqRW}, {@code dqDX}, {@code dqDY}, {@code dqDZ}, {@code dqDW}) (the
     * encoded translation is dropped).
     * <p>
     * Valid input: {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)} must be a unit dual
     * quaternion.
     *
     * @param dqRX the {@code rX} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqRY the {@code rY} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqRZ the {@code rZ} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqRW the {@code rW} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqDX the {@code dX} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqDY the {@code dY} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqDZ the {@code dZ} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @param dqDW the {@code dW} component of the dual quaternion
     *        {@code (dqRX, dqRY, dqRZ, dqRW, dqDX, dqDY, dqDZ, dqDW)}
     * @return the resulting matrix
     */
    public static Float3x3 makeFromDualQuat(float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
        float _sp0 = dqRX + dqRX;
        float _t0 = dqRY * dqRY;
        float _t2 = dqRZ * dqRW;
        float _t3 = dqRY * dqRW;
        float _t4 = dqRX * dqRX;
        float _t5 = dqRY * dqRZ;
        float _t6 = Math.fma(-2.0f, dqRZ * dqRZ, 1.0f);
        return new Float3x3(Math.fma(-2.0f, _t0, _t6), Math.fma(-2.0f, _t2, _sp0 * dqRY), 2.0f * Math.fma(dqRX, dqRZ, _t3), 2.0f * Math.fma(dqRX, dqRY, _t2), Math.fma(-2.0f, _t4, _t6), Math.fma(-2.0f, dqRX * dqRW, _t5 + _t5), Math.fma(-2.0f, _t3, _sp0 * dqRZ), 2.0f * Math.fma(dqRX, dqRW, _t5), Math.fma(-2.0f, _t4, Math.fma(-2.0f, _t0, 1.0f)), 0);
    }


    /**
     * Create a rotation by {@code angle}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting matrix
     */
    public static Float3x3 makeRotation(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float3x3(_t1, -_t0, 0.0f, _t0, _t1, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Create a rotation of {@code angle} radians about the axis {@code axis}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return the resulting matrix
     */
    public static Float3x3 makeRotationAxis(float angle, Float3 axis) {
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisY;
        float _t3 = axisX * axisZ;
        float _t4 = axisY * axisZ;
        float _t5 = 1.0f - _t1;
        return new Float3x3(Math.fma(_t5, axisX * axisX, _t1), Math.fma(_t5, _t2, -(axisZ * _t0)), Math.fma(axisY, _t0, _t5 * _t3), Math.fma(axisZ, _t0, _t5 * _t2), Math.fma(_t5, axisY * axisY, _t1), Math.fma(_t5, _t4, -(axisX * _t0)), Math.fma(_t5, _t3, -(axisY * _t0)), Math.fma(axisX, _t0, _t5 * _t4), Math.fma(_t5, axisZ * axisZ, _t1), 0);
    }


    /**
     * Create a rotation of {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}).
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return the resulting matrix
     */
    public static Float3x3 makeRotationAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisY;
        float _t3 = axisX * axisZ;
        float _t4 = axisY * axisZ;
        float _t5 = 1.0f - _t1;
        return new Float3x3(Math.fma(_t5, axisX * axisX, _t1), Math.fma(_t5, _t2, -(axisZ * _t0)), Math.fma(axisY, _t0, _t5 * _t3), Math.fma(axisZ, _t0, _t5 * _t2), Math.fma(_t5, axisY * axisY, _t1), Math.fma(_t5, _t4, -(axisX * _t0)), Math.fma(_t5, _t3, -(axisY * _t0)), Math.fma(axisX, _t0, _t5 * _t4), Math.fma(_t5, axisZ * axisZ, _t1), 0);
    }


    /**
     * Create a rotation that makes {@code +z} point along {@code dir}.
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
     * @return the resulting matrix
     */
    public static Float3x3 makeRotationLookAlong(Float3 dir, Float3 up) {
        float dirX = dir.x();
        float dirY = dir.y();
        float dirZ = dir.z();
        float upX = up.x();
        float upY = up.y();
        float upZ = up.z();
        float _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t22 = Math.fma(-dirY, _sp0, upY);
        float _t23 = Math.fma(-dirZ, _sp0, upZ);
        float _t24 = Math.fma(-dirX, _sp0, upX);
        float _t31 = Math.fma(dirZ, _t22, -(dirY * _t23));
        float _t32 = Math.fma(dirY, _t24, -(dirX * _t22));
        float _t33 = Math.fma(dirX, _t23, -(dirZ * _t24));
        float _t38 = Math.fma(_t32, _t32, Math.fma(_t33, _t33, _t31 * _t31));
        if (!(_t38 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _t38 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_s524747ee_tail(dirZ, _t14, dirX, _t31, (1.0f / (float) java.lang.Math.sqrt(_t38)), _t32, _t33, dirY * _t14, 0);
    }

    /**
     * Private per-column body of {@code makeRotationLookAlong_s524747ee_tail}. Shared by 4
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static float[] makeRotationLookAlong_s524747ee_tail_s721f8c61_c0(float _t39, float _t41, float _t40) {
        return new float[] {_t39, _t41, _t40};
    }

    /** Private per-column body of {@code makeRotationLookAlong_s524747ee_tail}; reached only through it. */
    private static float[] makeRotationLookAlong_s524747ee_tail_s721f8c61_c1(float _t15, float _t40, float _t16, float _t41, float _t39, float _t17) {
        return new float[] {Math.fma(_t15, _t40, -(_t16 * _t41)), Math.fma(_t16, _t39, -(_t17 * _t40)), Math.fma(_t17, _t41, -(_t15 * _t39))};
    }

    /** Private tail of {@code makeRotationLookAlong}; reached only through it. */
    private static Float3x3 makeRotationLookAlong_s524747ee_tail(float dirZ, float _t14, float dirX, float _t31, float _t38, float _t32, float _t33, float _t15, int _props) {
        float _t16 = dirZ * _t14;
        float _t17 = dirX * _t14;
        float _t39 = _t31 * _t38;
        float _t40 = _t32 * _t38;
        float _t41 = _t33 * _t38;
        float[] _col0 = makeRotationLookAlong_s524747ee_tail_s721f8c61_c0(_t39, _t41, _t40);
        float[] _col1 = makeRotationLookAlong_s524747ee_tail_s721f8c61_c1(_t15, _t40, _t16, _t41, _t39, _t17);
        float[] _col2 = makeRotationLookAlong_s524747ee_tail_s721f8c61_c0(_t17, _t15, _t16);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Create a rotation that makes {@code +z} point along ({@code dirX}, {@code dirY},
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
     * @return the resulting matrix
     */
    public static Float3x3 makeRotationLookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t22 = Math.fma(-dirY, _sp0, upY);
        float _t23 = Math.fma(-dirZ, _sp0, upZ);
        float _t24 = Math.fma(-dirX, _sp0, upX);
        float _t31 = Math.fma(dirZ, _t22, -(dirY * _t23));
        float _t32 = Math.fma(dirY, _t24, -(dirX * _t22));
        float _t33 = Math.fma(dirX, _t23, -(dirZ * _t24));
        float _t38 = Math.fma(_t32, _t32, Math.fma(_t33, _t33, _t31 * _t31));
        if (!(_t38 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _t38 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_s524747ee_tail(dirZ, _t14, dirX, _t31, (1.0f / (float) java.lang.Math.sqrt(_t38)), _t32, _t33, dirY * _t14, 0);
    }

    /** Private per-column body of {@code makeRotationLookAlong_degenerate_s524747ee_tail}; reached only through it. */
    private static float[] makeRotationLookAlong_degenerate_s524747ee_tail_s3518a25b_c1(float _t65, float _t26, float _t67, float _t24, float _t66, float _t25) {
        return new float[] {Math.fma(_t65, _t26, -(_t67 * _t24)), Math.fma(_t66, _t24, -(_t65 * _t25)), Math.fma(_t67, _t25, -(_t66 * _t26))};
    }

    /** Private tail of {@code makeRotationLookAlong_degenerate}; reached only through it. */
    private static Float3x3 makeRotationLookAlong_degenerate_s524747ee_tail(float _t21, float _t22, float _t23, float _t27, float _t28, float _t25, float _t24, float _t26, float _t34, float _t35, int _props) {
        float _t39 = _t27 > _t28 ? _t25 : -_t24;
        float _t41 = -Math.fma(_t21, _t24, Math.fma(_t25, _t22, _t26 * _t23));
        float _t42 = Math.fma(_t41, _t25, _t22);
        float _t43 = Math.fma(_t41, _t26, _t23);
        float _t44 = Math.fma(_t41, _t24, _t21);
        float _t53 = Math.fma(_t42, _t26, -(_t43 * _t25));
        float _t54 = Math.fma(_t44, _t25, -(_t42 * _t24));
        float _t55 = Math.fma(_t43, _t24, -(_t44 * _t26));
        float _t59 = Math.fma(_t53, _t53, Math.fma(_t54, _t54, _t55 * _t55));
        float _t64, _t65, _t66, _t67;
        if (_t59 <= Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 1.4551915E-11f) {
            _t64 = (1.0f / (float) java.lang.Math.sqrt(Math.fma(_t34, _t34, Math.fma(_t35, _t35, _t39 * _t39))));
            _t65 = _t64 * _t34;
            _t66 = _t64 * _t35;
            _t67 = _t64 * _t39;
        } else {
            _t64 = (1.0f / (float) java.lang.Math.sqrt(_t59));
            _t65 = _t64 * _t53;
            _t66 = _t64 * _t55;
            _t67 = _t64 * _t54;
        }
        float[] _col0 = makeRotationLookAlong_s524747ee_tail_s721f8c61_c0(_t66, _t67, _t65);
        float[] _col1 = makeRotationLookAlong_degenerate_s524747ee_tail_s3518a25b_c1(_t65, _t26, _t67, _t24, _t66, _t25);
        float[] _col2 = makeRotationLookAlong_s524747ee_tail_s721f8c61_c0(_t25, _t26, _t24);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static Float3x3 makeRotationLookAlong_degenerate(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t0 = unitScale(dirX, dirY, dirZ);
        float _t1 = unitScale(upX, upY, upZ);
        float _t8 = dirZ * _t0;
        float _t9 = dirX * _t0;
        float _t10 = dirY * _t0;
        float _t16 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        float _t17 = (1.0f / (float) java.lang.Math.sqrt(_t16));
        float _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0f) {
            _t21 = 0.0f;
            _t22 = 0.0f;
            _t23 = 1.0f;
            _t24 = 1.0f;
            _t25 = 0.0f;
            _t26 = 0.0f;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        float _t27 = java.lang.Math.abs(_t25);
        float _t28 = java.lang.Math.abs(_t24);
        float _t34, _t35;
        if (_t27 > _t28) {
            _t34 = 0.0f;
            _t35 = -_t26;
        } else {
            _t34 = _t26;
            _t35 = 0.0f;
        }
        return makeRotationLookAlong_degenerate_s524747ee_tail(_t21, _t22, _t23, _t27, _t28, _t25, _t24, _t26, _t34, _t35, 0);
    }


    /**
     * Create the rotation represented by the quaternion {@code q}.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the rotation quaternion
     * @return the resulting matrix
     */
    public static Float3x3 makeRotationQuat(FloatQuat q) {
        float qX = q.x();
        float qY = q.y();
        float qZ = q.z();
        float qW = q.w();
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        return new Float3x3(Math.fma(-2.0f, Math.fma(qY, qY, _t0), 1.0f), 2.0f * Math.fma(qX, qY, -_t1), 2.0f * Math.fma(qX, qZ, _t2), 2.0f * Math.fma(qX, qY, _t1), Math.fma(-2.0f, Math.fma(qX, qX, _t0), 1.0f), 2.0f * Math.fma(qY, qZ, -(qX * qW)), 2.0f * Math.fma(qX, qZ, -_t2), 2.0f * Math.fma(qX, qW, qY * qZ), Math.fma(-2.0f, Math.fma(qX, qX, qY * qY), 1.0f), 0);
    }


    /**
     * Create the rotation represented by the quaternion ({@code qX}, {@code qY}, {@code qZ},
     * {@code qW}).
     * <p>
     * Valid input: {@code (qX, qY, qZ, qW)} must have unit length.
     *
     * @param qX the {@code x} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qY the {@code y} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qZ the {@code z} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @param qW the {@code w} component of the quaternion {@code (qX, qY, qZ, qW)}
     * @return the resulting matrix
     */
    public static Float3x3 makeRotationQuat(float qX, float qY, float qZ, float qW) {
        float _t0 = qZ * qZ;
        float _t1 = qZ * qW;
        float _t2 = qY * qW;
        return new Float3x3(Math.fma(-2.0f, Math.fma(qY, qY, _t0), 1.0f), 2.0f * Math.fma(qX, qY, -_t1), 2.0f * Math.fma(qX, qZ, _t2), 2.0f * Math.fma(qX, qY, _t1), Math.fma(-2.0f, Math.fma(qX, qX, _t0), 1.0f), 2.0f * Math.fma(qY, qZ, -(qX * qW)), 2.0f * Math.fma(qX, qZ, -_t2), 2.0f * Math.fma(qX, qW, qY * qZ), Math.fma(-2.0f, Math.fma(qX, qX, qY * qY), 1.0f), 0);
    }


    /**
     * Create a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting matrix
     */
    public static Float3x3 makeRotationX(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float3x3(1.0f, 0.0f, 0.0f, 0.0f, _t1, -_t0, 0.0f, _t0, _t1, 0);
    }


    /**
     * Create a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return the resulting matrix
     */
    public static Float3x3 makeRotationXYZ(float angleX, float angleY, float angleZ) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t0;
        float _t7 = _t0 * _t5;
        return new Float3x3(_t3 * _t4, -(_t1 * _t3), _t0, Math.fma(_t6, _t4, _t1 * _t5), Math.fma(_t5, _t4, -(_t6 * _t1)), -(_t2 * _t3), Math.fma(_t2, _t1, -(_t7 * _t4)), Math.fma(_t7, _t1, _t2 * _t4), _t5 * _t3, 0);
    }


    /**
     * Create a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return the resulting matrix
     */
    public static Float3x3 makeRotationXZY(float angleX, float angleZ, float angleY) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t1 * _t5;
        return new Float3x3(_t3 * _t4, -_t1, _t0 * _t4, Math.fma(_t7, _t3, _t2 * _t0), _t5 * _t4, Math.fma(_t7, _t0, -(_t2 * _t3)), Math.fma(_t6, _t3, -(_t0 * _t5)), _t2 * _t4, Math.fma(_t6, _t0, _t5 * _t3), 0);
    }


    /**
     * Create a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting matrix
     */
    public static Float3x3 makeRotationY(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float3x3(_t1, 0.0f, _t0, 0.0f, 1.0f, 0.0f, -_t0, 0.0f, _t1, 0);
    }


    /**
     * Create a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @return the resulting matrix
     */
    public static Float3x3 makeRotationYXZ(float angleY, float angleX, float angleZ) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleY);
        float _t2 = Math.sin(angleZ);
        float _t3 = Math.cosFromSin(_t1, angleY);
        float _t4 = Math.cosFromSin(_t2, angleZ);
        float _t5 = Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t3;
        return new Float3x3(Math.fma(_t6, _t2, _t3 * _t4), Math.fma(_t6, _t4, -(_t2 * _t3)), _t1 * _t5, _t2 * _t5, _t5 * _t4, -_t0, Math.fma(_t7, _t2, -(_t1 * _t4)), Math.fma(_t7, _t4, _t1 * _t2), _t5 * _t3, 0);
    }


    /**
     * Create a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return the resulting matrix
     */
    public static Float3x3 makeRotationYZX(float angleY, float angleZ, float angleX) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t1 * _t3;
        return new Float3x3(_t3 * _t4, Math.fma(_t2, _t0, -(_t7 * _t5)), Math.fma(_t7, _t2, _t0 * _t5), _t1, _t5 * _t4, -(_t2 * _t4), -(_t0 * _t4), Math.fma(_t6, _t5, _t2 * _t3), Math.fma(_t5, _t3, -(_t6 * _t2)), 0);
    }


    /**
     * Create a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting matrix
     */
    public static Float3x3 makeRotationZ(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float3x3(_t1, -_t0, 0.0f, _t0, _t1, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Create a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleX the angle in radians to rotate about the X axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @return the resulting matrix
     */
    public static Float3x3 makeRotationZXY(float angleZ, float angleX, float angleY) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t2 * _t4;
        return new Float3x3(Math.fma(_t3, _t4, -(_t6 * _t0)), -(_t1 * _t5), Math.fma(_t6, _t3, _t0 * _t4), Math.fma(_t7, _t0, _t1 * _t3), _t5 * _t4, Math.fma(_t0, _t1, -(_t7 * _t3)), -(_t0 * _t5), _t2, _t5 * _t3, 0);
    }


    /**
     * Create a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angleZ the angle in radians to rotate about the Z axis
     * @param angleY the angle in radians to rotate about the Y axis
     * @param angleX the angle in radians to rotate about the X axis
     * @return the resulting matrix
     */
    public static Float3x3 makeRotationZYX(float angleZ, float angleY, float angleX) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t4;
        return new Float3x3(_t3 * _t4, Math.fma(_t7, _t2, -(_t1 * _t5)), Math.fma(_t7, _t5, _t2 * _t1), _t1 * _t3, Math.fma(_t6, _t2, _t5 * _t4), Math.fma(_t6, _t5, -(_t2 * _t4)), -_t0, _t2 * _t3, _t5 * _t3, 0);
    }


    /**
     * Create a scaling transformation that scales by {@code v}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @return the resulting matrix
     */
    public static Float3x3 makeScaling(Float2 v) {
        float vX = v.x();
        float vY = v.y();
        return new Float3x3(vX, 0.0f, 0.0f, 0.0f, vY, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Create a scaling transformation that scales by ({@code vX}, {@code vY}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return the resulting matrix
     */
    public static Float3x3 makeScaling(float vX, float vY) {
        return new Float3x3(vX, 0.0f, 0.0f, 0.0f, vY, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Create a scaling transformation that scales by {@code s} of the x and y axes only (the 2D
     * homogeneous {@code diag(s, s, 1)}: the third row and column are left unscaled).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @return the resulting matrix
     */
    public static Float3x3 makeScaling(float s) {
        return new Float3x3(s, 0.0f, 0.0f, 0.0f, s, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Create a translation transformation that translates by {@code v}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @return the resulting matrix
     */
    public static Float3x3 makeTranslation(Float2 v) {
        float vX = v.x();
        float vY = v.y();
        return new Float3x3(1.0f, 0.0f, vX, 0.0f, 1.0f, vY, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION);
    }


    /**
     * Create a translation transformation that translates by ({@code vX}, {@code vY}).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the translation offsets {@code (vX, vY)}
     * @param vY the {@code y} component of the translation offsets {@code (vX, vY)}
     * @return the resulting matrix
     */
    public static Float3x3 makeTranslation(float vX, float vY) {
        return new Float3x3(1.0f, 0.0f, vX, 0.0f, 1.0f, vY, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION);
    }


    /**
     * Create the 2D view transformation that maps the rectangle
     * {@code [left, right] x [bottom, top]} onto {@code [-1, +1] x [-1, +1]}.
     * <p>
     * Valid input: {@code left} and {@code right} must differ; {@code bottom} and {@code top} must
     * differ.
     *
     * @param left the distance to the left edge of the view rectangle
     * @param right the distance to the right edge of the view rectangle
     * @param bottom the distance to the bottom edge of the view rectangle
     * @param top the distance to the top edge of the view rectangle
     * @return the resulting matrix
     */
    public static Float3x3 makeView(float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        return new Float3x3(_t0_inv + _t0_inv, 0.0f, -((left + right) * _t0_inv), 0.0f, _t1_inv + _t1_inv, -((bottom + top) * _t1_inv), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float3x3 preRotate_translation(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float3x3(_t1, -_t0, Math.fma(this.m02, _t1, -(this.m12 * _t0)), _t0, _t1, Math.fma(this.m02, _t0, this.m12 * _t1), 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float3x3 preRotate_orthogonal(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float3x3(Math.fma(this.m00, _t1, -(this.m10 * _t0)), Math.fma(this.m01, _t1, -(this.m11 * _t0)), Math.fma(this.m02, _t1, -(this.m12 * _t0)), Math.fma(this.m00, _t0, this.m10 * _t1), Math.fma(this.m01, _t0, this.m11 * _t1), Math.fma(this.m02, _t0, this.m12 * _t1), 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float3x3 preRotate_affine(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float3x3(Math.fma(this.m00, _t1, -(this.m10 * _t0)), Math.fma(this.m01, _t1, -(this.m11 * _t0)), Math.fma(this.m02, _t1, -(this.m12 * _t0)), Math.fma(this.m00, _t0, this.m10 * _t1), Math.fma(this.m01, _t0, this.m11 * _t1), Math.fma(this.m02, _t0, this.m12 * _t1), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Float3x3 preRotate_general(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float3x3(Math.fma(this.m00, _t1, -(this.m10 * _t0)), Math.fma(this.m01, _t1, -(this.m11 * _t0)), Math.fma(this.m02, _t1, -(this.m12 * _t0)), Math.fma(this.m00, _t0, this.m10 * _t1), Math.fma(this.m01, _t0, this.m11 * _t1), Math.fma(this.m02, _t0, this.m12 * _t1), this.m20, this.m21, this.m22, 0);
    }


    /**
     * Pre-multiply a rotation by {@code angle} onto this matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting matrix
     */
    public Float3x3 preRotate(float angle) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float _t0 = Math.sin(angle);
            float _t1 = Math.cosFromSin(_t0, angle);
            return new Float3x3(_t1, -_t0, 0.0f, _t0, _t1, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotate_translation(angle);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preRotate_orthogonal(angle);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preRotate_affine(angle);
        return preRotate_general(angle);
    }


    /**
     * Pre-multiply the rotation {@code angle} about the pivot point {@code pivot} onto this matrix,
     * returning the result as a value.
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
     * @return the resulting matrix
     */
    public Float3x3 preRotateAround(float angle, Float2 pivot) {
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity(angle, pivotX, pivotY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAround_translation(angle, pivotX, pivotY);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preRotateAround_orthogonal(angle, pivotX, pivotY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preRotateAround_affine(angle, pivotX, pivotY);
        return preRotateAround_general(angle, pivotX, pivotY);
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties. Shared by
     * the identical private paths of {@code preRotateAround} and {@code rotateAround}; reached only
     * through them.
     */
    private Float3x3 preRotateAround_identity(float angle, float pivotX, float pivotY) {
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        return new Float3x3(_t2, -_t0, Math.fma(pivotX, _t5, pivotY * _t0), _t0, _t2, Math.fma(pivotY, _t5, -(pivotX * _t0)), 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float3x3 preRotateAround_translation(float angle, float pivotX, float pivotY) {
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        return new Float3x3(_t2, -_t0, Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(this.m02, _t2, -(this.m12 * _t0)), _t0, _t2, Math.fma(this.m02, _t0, this.m12 * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0)), 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float3x3 preRotateAround_orthogonal(float angle, float pivotX, float pivotY) {
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        return new Float3x3(Math.fma(this.m00, _t2, -(this.m10 * _t0)), Math.fma(this.m01, _t2, -(this.m11 * _t0)), Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(this.m02, _t2, -(this.m12 * _t0)), Math.fma(this.m00, _t0, this.m10 * _t2), Math.fma(this.m01, _t0, this.m11 * _t2), Math.fma(this.m02, _t0, this.m12 * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0)), 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float3x3 preRotateAround_affine(float angle, float pivotX, float pivotY) {
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        return new Float3x3(Math.fma(this.m00, _t2, -(this.m10 * _t0)), Math.fma(this.m01, _t2, -(this.m11 * _t0)), Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(this.m02, _t2, -(this.m12 * _t0)), Math.fma(this.m00, _t0, this.m10 * _t2), Math.fma(this.m01, _t0, this.m11 * _t2), Math.fma(this.m02, _t0, this.m12 * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0)), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Float3x3 preRotateAround_general(float angle, float pivotX, float pivotY) {
        float _t0 = Math.sin(angle);
        float _t2 = Math.sin(0.5f * angle);
        float _t3 = Math.cosFromSin(_t0, angle);
        float _t8 = (_t2 + _t2) * _t2;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        return new Float3x3(Math.fma(this.m20, _t9, Math.fma(this.m00, _t3, -(this.m10 * _t0))), Math.fma(this.m21, _t9, Math.fma(this.m01, _t3, -(this.m11 * _t0))), Math.fma(this.m22, _t9, Math.fma(this.m02, _t3, -(this.m12 * _t0))), Math.fma(this.m20, _t10, Math.fma(this.m00, _t0, this.m10 * _t3)), Math.fma(this.m21, _t10, Math.fma(this.m01, _t0, this.m11 * _t3)), Math.fma(this.m22, _t10, Math.fma(this.m02, _t0, this.m12 * _t3)), this.m20, this.m21, this.m22, 0);
    }


    /**
     * Pre-multiply the rotation {@code angle} about the pivot point ({@code pivotX},
     * {@code pivotY}) onto this matrix, returning the result as a value.
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
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @return the resulting matrix
     */
    public Float3x3 preRotateAround(float angle, float pivotX, float pivotY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity(angle, pivotX, pivotY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAround_translation(angle, pivotX, pivotY);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preRotateAround_orthogonal(angle, pivotX, pivotY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preRotateAround_affine(angle, pivotX, pivotY);
        return preRotateAround_general(angle, pivotX, pivotY);
    }


    /**
     * Pre-multiply a rotation of {@code angle} radians about the axis {@code axis} onto this
     * matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return the resulting matrix
     */
    public Float3x3 preRotateAxis(float angle, Float3 axis) {
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return preRotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return preRotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return preRotateZ(axisZ * angle);
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAxis_identity(angle, axisX, axisY, axisZ);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAxis_translation(angle, axisX, axisY, axisZ);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preRotateAxis_orthogonal(angle, axisX, axisY, axisZ);
        return preRotateAxis_general(angle, axisX, axisY, axisZ);
    }


    /**
     * Private body of {@code preRotateAxis}, specialized by runtime matrix properties. Shared by
     * the identical private paths of {@code preRotateAxis} and {@code rotateAxis}; reached only
     * through them.
     */
    private Float3x3 preRotateAxis_identity(float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisY;
        float _t3 = axisX * axisZ;
        float _t4 = axisY * axisZ;
        float _t5 = 1.0f - _t1;
        return new Float3x3(Math.fma(_t5, axisX * axisX, _t1), Math.fma(_t5, _t2, -(axisZ * _t0)), Math.fma(axisY, _t0, _t5 * _t3), Math.fma(axisZ, _t0, _t5 * _t2), Math.fma(_t5, axisY * axisY, _t1), Math.fma(_t5, _t4, -(axisX * _t0)), Math.fma(_t5, _t3, -(axisY * _t0)), Math.fma(axisX, _t0, _t5 * _t4), Math.fma(_t5, axisZ * axisZ, _t1), 0);
    }


    /**
     * Private body of {@code preRotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotateAxis} dispatcher.
     */
    private Float3x3 preRotateAxis_translation(float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t3 = axisX * axisY;
        float _t5 = axisX * axisZ;
        float _t7 = axisY * axisZ;
        float _t9 = 1.0f - _t1;
        float _t14 = Math.fma(_t9, axisX * axisX, _t1);
        float _t15 = Math.fma(_t9, axisY * axisY, _t1);
        float _t16 = Math.fma(axisZ, _t0, _t9 * _t3);
        float _t17 = Math.fma(axisX, _t0, _t9 * _t7);
        float _t18 = Math.fma(_t9, _t3, -(axisZ * _t0));
        float _t19 = Math.fma(_t9, _t5, -(axisY * _t0));
        return new Float3x3(_t14, _t18, Math.fma(axisY, _t0, _t9 * _t5) + Math.fma(this.m02, _t14, this.m12 * _t18), _t16, _t15, Math.fma(this.m02, _t16, this.m12 * _t15) + Math.fma(_t9, _t7, -(axisX * _t0)), _t19, _t17, Math.fma(this.m02, _t19, Math.fma(this.m12, _t17, Math.fma(_t9, axisZ * axisZ, _t1))), 0);
    }

    /** Private per-column body of {@code preRotateAxis_orthogonal}; reached only through it. */
    private float[] preRotateAxis_orthogonal_s3b957671_c0(float _t14, float _t18, float _t16, float _t15, float _t19, float _t17) {
        return new float[] {Math.fma(this.m00, _t14, this.m10 * _t18), Math.fma(this.m00, _t16, this.m10 * _t15), Math.fma(this.m00, _t19, this.m10 * _t17)};
    }

    /** Private per-column body of {@code preRotateAxis_orthogonal}; reached only through it. */
    private float[] preRotateAxis_orthogonal_s3b957671_c1(float _t14, float _t18, float _t16, float _t15, float _t19, float _t17) {
        return new float[] {Math.fma(this.m01, _t14, this.m11 * _t18), Math.fma(this.m01, _t16, this.m11 * _t15), Math.fma(this.m01, _t19, this.m11 * _t17)};
    }

    /** Private per-column body of {@code preRotateAxis_orthogonal}; reached only through it. */
    private float[] preRotateAxis_orthogonal_s3b957671_c2(float axisY, float _t0, float _t9, float _t5, float _t14, float _t18, float _t16, float _t15, float _t7, float axisX, float _t19, float _t17, float axisZ, float _t1) {
        return new float[] {Math.fma(axisY, _t0, _t9 * _t5) + Math.fma(this.m02, _t14, this.m12 * _t18), Math.fma(this.m02, _t16, this.m12 * _t15) + Math.fma(_t9, _t7, -(axisX * _t0)), Math.fma(this.m02, _t19, Math.fma(this.m12, _t17, Math.fma(_t9, axisZ * axisZ, _t1)))};
    }


    /**
     * Private body of {@code preRotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotateAxis} dispatcher.
     */
    private Float3x3 preRotateAxis_orthogonal(float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t3 = axisX * axisY;
        float _t5 = axisX * axisZ;
        float _t7 = axisY * axisZ;
        float _t9 = 1.0f - _t1;
        float _t14 = Math.fma(_t9, axisX * axisX, _t1);
        float _t15 = Math.fma(_t9, axisY * axisY, _t1);
        float _t16 = Math.fma(axisZ, _t0, _t9 * _t3);
        float _t17 = Math.fma(axisX, _t0, _t9 * _t7);
        float _t18 = Math.fma(_t9, _t3, -(axisZ * _t0));
        float _t19 = Math.fma(_t9, _t5, -(axisY * _t0));
        float[] _col0 = preRotateAxis_orthogonal_s3b957671_c0(_t14, _t18, _t16, _t15, _t19, _t17);
        float[] _col1 = preRotateAxis_orthogonal_s3b957671_c1(_t14, _t18, _t16, _t15, _t19, _t17);
        float[] _col2 = preRotateAxis_orthogonal_s3b957671_c2(axisY, _t0, _t9, _t5, _t14, _t18, _t16, _t15, _t7, axisX, _t19, _t17, axisZ, _t1);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }

    /** Private per-column body of {@code preRotateAxis_general}; reached only through it. */
    private float[] preRotateAxis_general_s3b957671_c0(float _t21, float _t18, float _t24, float _t25, float _t22, float _t19, float _t20, float _t26, float _t23) {
        return new float[] {Math.fma(this.m20, _t21, Math.fma(this.m00, _t18, this.m10 * _t24)), Math.fma(this.m20, _t25, Math.fma(this.m00, _t22, this.m10 * _t19)), Math.fma(this.m20, _t20, Math.fma(this.m00, _t26, this.m10 * _t23))};
    }

    /** Private per-column body of {@code preRotateAxis_general}; reached only through it. */
    private float[] preRotateAxis_general_s3b957671_c1(float _t21, float _t18, float _t24, float _t25, float _t22, float _t19, float _t20, float _t26, float _t23) {
        return new float[] {Math.fma(this.m21, _t21, Math.fma(this.m01, _t18, this.m11 * _t24)), Math.fma(this.m21, _t25, Math.fma(this.m01, _t22, this.m11 * _t19)), Math.fma(this.m21, _t20, Math.fma(this.m01, _t26, this.m11 * _t23))};
    }

    /** Private per-column body of {@code preRotateAxis_general}; reached only through it. */
    private float[] preRotateAxis_general_s3b957671_c2(float _t21, float _t18, float _t24, float _t25, float _t22, float _t19, float _t20, float _t26, float _t23) {
        return new float[] {Math.fma(this.m22, _t21, Math.fma(this.m02, _t18, this.m12 * _t24)), Math.fma(this.m22, _t25, Math.fma(this.m02, _t22, this.m12 * _t19)), Math.fma(this.m22, _t20, Math.fma(this.m02, _t26, this.m12 * _t23))};
    }


    /**
     * Private body of {@code preRotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotateAxis} dispatcher.
     */
    private Float3x3 preRotateAxis_general(float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t4 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        float _t18 = Math.fma(_t11, axisX * axisX, _t1);
        float _t19 = Math.fma(_t11, axisY * axisY, _t1);
        float _t20 = Math.fma(_t11, axisZ * axisZ, _t1);
        float _t21 = Math.fma(axisY, _t0, _t11 * _t2);
        float _t22 = Math.fma(axisZ, _t0, _t11 * _t4);
        float _t23 = Math.fma(axisX, _t0, _t11 * _t6);
        float _t24 = Math.fma(_t11, _t4, -(axisZ * _t0));
        float _t25 = Math.fma(_t11, _t6, -(axisX * _t0));
        float _t26 = Math.fma(_t11, _t2, -(axisY * _t0));
        float[] _col0 = preRotateAxis_general_s3b957671_c0(_t21, _t18, _t24, _t25, _t22, _t19, _t20, _t26, _t23);
        float[] _col1 = preRotateAxis_general_s3b957671_c1(_t21, _t18, _t24, _t25, _t22, _t19, _t20, _t26, _t23);
        float[] _col2 = preRotateAxis_general_s3b957671_c2(_t21, _t18, _t24, _t25, _t22, _t19, _t20, _t26, _t23);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Pre-multiply a rotation of {@code angle} radians about the axis ({@code axisX},
     * {@code axisY}, {@code axisZ}) onto this matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return the resulting matrix
     */
    public Float3x3 preRotateAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return preRotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return preRotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return preRotateZ(axisZ * angle);
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAxis_identity(angle, axisX, axisY, axisZ);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotateAxis_translation(angle, axisX, axisY, axisZ);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preRotateAxis_orthogonal(angle, axisX, axisY, axisZ);
        return preRotateAxis_general(angle, axisX, axisY, axisZ);
    }


    /**
     * Pre-multiply a rotation of {@code angle} radians about the X axis onto this matrix, returning
     * the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting matrix
     */
    public Float3x3 preRotateX(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float3x3(this.m00, this.m01, this.m02, Math.fma(this.m10, _t1, -(this.m20 * _t0)), Math.fma(this.m11, _t1, -(this.m21 * _t0)), Math.fma(this.m12, _t1, -(this.m22 * _t0)), Math.fma(this.m10, _t0, this.m20 * _t1), Math.fma(this.m11, _t0, this.m21 * _t1), Math.fma(this.m12, _t0, this.m22 * _t1), 0);
    }


    /**
     * Pre-multiply a rotation of {@code angle} radians about the Y axis onto this matrix, returning
     * the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting matrix
     */
    public Float3x3 preRotateY(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float3x3(Math.fma(this.m00, _t1, this.m20 * _t0), Math.fma(this.m01, _t1, this.m21 * _t0), Math.fma(this.m02, _t1, this.m22 * _t0), this.m10, this.m11, this.m12, Math.fma(this.m20, _t1, -(this.m00 * _t0)), Math.fma(this.m21, _t1, -(this.m01 * _t0)), Math.fma(this.m22, _t1, -(this.m02 * _t0)), 0);
    }


    /**
     * Pre-multiply a rotation of {@code angle} radians about the Z axis onto this matrix, returning
     * the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code R * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code R * M * v}, the rotation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting matrix
     */
    public Float3x3 preRotateZ(float angle) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float _t0 = Math.sin(angle);
            float _t1 = Math.cosFromSin(_t0, angle);
            return new Float3x3(_t1, -_t0, 0.0f, _t0, _t1, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preRotate_translation(angle);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preRotate_orthogonal(angle);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preRotate_affine(angle);
        return preRotate_general(angle);
    }


    /**
     * Pre-multiply a scaling by {@code v} onto this matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code p} with the new matrix by using
     * {@code S * M * p}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @return the resulting matrix
     */
    public Float3x3 preScale(Float2 v) {
        float vX = v.x();
        float vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(vX, 0.0f, 0.0f, 0.0f, vY, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(vX, 0.0f, this.m02 * vX, 0.0f, vY, this.m12 * vY, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScale_orthogonal(vX, vY);
        return preScale_general(vX, vY);
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_orthogonal(float vX, float vY) {
        return new Float3x3(this.m00 * vX, this.m01 * vX, this.m02 * vX, this.m10 * vY, this.m11 * vY, this.m12 * vY, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_general(float vX, float vY) {
        return new Float3x3(this.m00 * vX, this.m01 * vX, this.m02 * vX, this.m10 * vY, this.m11 * vY, this.m12 * vY, this.m20, this.m21, this.m22, 0);
    }


    /**
     * Pre-multiply a scaling by ({@code vX}, {@code vY}) onto this matrix, returning the result as
     * a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return the resulting matrix
     */
    public Float3x3 preScale(float vX, float vY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(vX, 0.0f, 0.0f, 0.0f, vY, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(vX, 0.0f, this.m02 * vX, 0.0f, vY, this.m12 * vY, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScale_orthogonal(vX, vY);
        return preScale_general(vX, vY);
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_orthogonal(float s) {
        return new Float3x3(s * this.m00, s * this.m01, s * this.m02, s * this.m10, s * this.m11, s * this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Float3x3 preScale_general(float s) {
        return new Float3x3(s * this.m00, s * this.m01, s * this.m02, s * this.m10, s * this.m11, s * this.m12, this.m20, this.m21, this.m22, 0);
    }


    /**
     * Pre-multiply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) onto this matrix,
     * returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @return the resulting matrix
     */
    public Float3x3 preScale(float s) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(s, 0.0f, 0.0f, 0.0f, s, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(s, 0.0f, s * this.m02, 0.0f, s, s * this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScale_orthogonal(s);
        return preScale_general(s);
    }


    /**
     * Pre-multiply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) about the pivot point
     * {@code pivot} onto this matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivot the pivot point
     * @return the resulting matrix
     */
    public Float3x3 preScaleAround(float s, Float2 pivot) {
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float _t0 = 1.0f - s;
            return new Float3x3(s, 0.0f, pivotX * _t0, 0.0f, s, pivotY * _t0, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation(s, pivotX, pivotY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScaleAround_orthogonal(s, pivotX, pivotY);
        return preScaleAround_general(s, pivotX, pivotY);
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_translation(float s, float pivotX, float pivotY) {
        float _t0 = 1.0f - s;
        return new Float3x3(s, 0.0f, Math.fma(s, this.m02, pivotX * _t0), 0.0f, s, Math.fma(s, this.m12, pivotY * _t0), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_orthogonal(float s, float pivotX, float pivotY) {
        float _t0 = 1.0f - s;
        return new Float3x3(s * this.m00, s * this.m01, Math.fma(s, this.m02, pivotX * _t0), s * this.m10, s * this.m11, Math.fma(s, this.m12, pivotY * _t0), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_general(float s, float pivotX, float pivotY) {
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        return new Float3x3(Math.fma(s, this.m00, this.m20 * _t1), Math.fma(s, this.m01, this.m21 * _t1), Math.fma(s, this.m02, this.m22 * _t1), Math.fma(s, this.m10, this.m20 * _t2), Math.fma(s, this.m11, this.m21 * _t2), Math.fma(s, this.m12, this.m22 * _t2), this.m20, this.m21, this.m22, 0);
    }


    /**
     * Pre-multiply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) about the pivot point
     * ({@code pivotX}, {@code pivotY}) onto this matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @return the resulting matrix
     */
    public Float3x3 preScaleAround(float s, float pivotX, float pivotY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float _t0 = 1.0f - s;
            return new Float3x3(s, 0.0f, pivotX * _t0, 0.0f, s, pivotY * _t0, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation(s, pivotX, pivotY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScaleAround_orthogonal(s, pivotX, pivotY);
        return preScaleAround_general(s, pivotX, pivotY);
    }


    /**
     * Pre-multiply a scaling by {@code s} about the pivot point {@code pivot} onto this matrix,
     * returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code S * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code S * M * v}, the scaling will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the scale factors
     * @param pivot the pivot point
     * @return the resulting matrix
     */
    public Float3x3 preScaleAround(Float2 s, Float2 pivot) {
        float sX = s.x();
        float sY = s.y();
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(sX, 0.0f, pivotX * (1.0f - sX), 0.0f, sY, pivotY * (1.0f - sY), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation(sX, sY, pivotX, pivotY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScaleAround_orthogonal(sX, sY, pivotX, pivotY);
        return preScaleAround_general(sX, sY, pivotX, pivotY);
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_translation(float sX, float sY, float pivotX, float pivotY) {
        return new Float3x3(sX, 0.0f, Math.fma(pivotX, 1.0f - sX, sX * this.m02), 0.0f, sY, Math.fma(pivotY, 1.0f - sY, sY * this.m12), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_orthogonal(float sX, float sY, float pivotX, float pivotY) {
        return new Float3x3(sX * this.m00, sX * this.m01, Math.fma(pivotX, 1.0f - sX, sX * this.m02), sY * this.m10, sY * this.m11, Math.fma(pivotY, 1.0f - sY, sY * this.m12), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Float3x3 preScaleAround_general(float sX, float sY, float pivotX, float pivotY) {
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        return new Float3x3(Math.fma(sX, this.m00, this.m20 * _t2), Math.fma(sX, this.m01, this.m21 * _t2), Math.fma(sX, this.m02, this.m22 * _t2), Math.fma(sY, this.m10, this.m20 * _t3), Math.fma(sY, this.m11, this.m21 * _t3), Math.fma(sY, this.m12, this.m22 * _t3), this.m20, this.m21, this.m22, 0);
    }


    /**
     * Pre-multiply a scaling by ({@code sX}, {@code sY}) about the pivot point ({@code pivotX},
     * {@code pivotY}) onto this matrix, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Float3x3 preScaleAround(float sX, float sY, float pivotX, float pivotY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(sX, 0.0f, pivotX * (1.0f - sX), 0.0f, sY, pivotY * (1.0f - sY), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation(sX, sY, pivotX, pivotY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScaleAround_orthogonal(sX, sY, pivotX, pivotY);
        return preScaleAround_general(sX, sY, pivotX, pivotY);
    }


    /**
     * Pre-multiply a translation by {@code v} onto this matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code T * M}. So when transforming a vector {@code p} with the new matrix by using
     * {@code T * M * p}, the translation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @return the resulting matrix
     */
    public Float3x3 preTranslate(Float2 v) {
        float vX = v.x();
        float vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(1.0f, 0.0f, vX, 0.0f, 1.0f, vY, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(1.0f, 0.0f, this.m02 + vX, 0.0f, 1.0f, this.m12 + vY, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preTranslate_orthogonal(vX, vY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preTranslate_affine(vX, vY);
        return preTranslate_general(vX, vY);
    }


    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties; reached only
     * through the public {@code preTranslate} dispatcher.
     */
    private Float3x3 preTranslate_orthogonal(float vX, float vY) {
        return new Float3x3(this.m00, this.m01, this.m02 + vX, this.m10, this.m11, this.m12 + vY, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties; reached only
     * through the public {@code preTranslate} dispatcher.
     */
    private Float3x3 preTranslate_affine(float vX, float vY) {
        return new Float3x3(this.m00, this.m01, this.m02 + vX, this.m10, this.m11, this.m12 + vY, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties; reached only
     * through the public {@code preTranslate} dispatcher.
     */
    private Float3x3 preTranslate_general(float vX, float vY) {
        return new Float3x3(Math.fma(this.m20, vX, this.m00), Math.fma(this.m21, vX, this.m01), Math.fma(this.m22, vX, this.m02), Math.fma(this.m20, vY, this.m10), Math.fma(this.m21, vY, this.m11), Math.fma(this.m22, vY, this.m12), this.m20, this.m21, this.m22, 0);
    }


    /**
     * Pre-multiply a translation by ({@code vX}, {@code vY}) onto this matrix, returning the result
     * as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code T * M}. So when transforming a vector {@code v} with the new matrix by using
     * {@code T * M * v}, the translation will be applied last.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return the resulting matrix
     */
    public Float3x3 preTranslate(float vX, float vY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(1.0f, 0.0f, vX, 0.0f, 1.0f, vY, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(1.0f, 0.0f, this.m02 + vX, 0.0f, 1.0f, this.m12 + vY, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preTranslate_orthogonal(vX, vY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preTranslate_affine(vX, vY);
        return preTranslate_general(vX, vY);
    }


    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float3x3 rotate_translation(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float3x3(_t1, -_t0, this.m02, _t0, _t1, this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float3x3 rotate_orthogonal(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float3x3(Math.fma(this.m00, _t1, this.m01 * _t0), Math.fma(this.m01, _t1, -(this.m00 * _t0)), this.m02, Math.fma(this.m10, _t1, this.m11 * _t0), Math.fma(this.m11, _t1, -(this.m10 * _t0)), this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float3x3 rotate_affine(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float3x3(Math.fma(this.m00, _t1, this.m01 * _t0), Math.fma(this.m01, _t1, -(this.m00 * _t0)), this.m02, Math.fma(this.m10, _t1, this.m11 * _t0), Math.fma(this.m11, _t1, -(this.m10 * _t0)), this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Float3x3 rotate_general(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float3x3(Math.fma(this.m00, _t1, this.m01 * _t0), Math.fma(this.m01, _t1, -(this.m00 * _t0)), this.m02, Math.fma(this.m10, _t1, this.m11 * _t0), Math.fma(this.m11, _t1, -(this.m10 * _t0)), this.m12, Math.fma(this.m20, _t1, this.m21 * _t0), Math.fma(this.m21, _t1, -(this.m20 * _t0)), this.m22, 0);
    }


    /**
     * Apply a rotation by {@code angle} to this matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting matrix
     */
    public Float3x3 rotate(float angle) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float _t0 = Math.sin(angle);
            float _t1 = Math.cosFromSin(_t0, angle);
            return new Float3x3(_t1, -_t0, 0.0f, _t0, _t1, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotate_translation(angle);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotate_orthogonal(angle);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotate_affine(angle);
        return rotate_general(angle);
    }


    /**
     * Apply the rotation {@code angle} about the pivot point {@code pivot} to this matrix,
     * returning the result as a value.
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
     * @return the resulting matrix
     */
    public Float3x3 rotateAround(float angle, Float2 pivot) {
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity(angle, pivotX, pivotY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAround_translation(angle, pivotX, pivotY);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateAround_orthogonal(angle, pivotX, pivotY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateAround_affine(angle, pivotX, pivotY);
        return rotateAround_general(angle, pivotX, pivotY);
    }


    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float3x3 rotateAround_translation(float angle, float pivotX, float pivotY) {
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t5 = (_t3 + _t3) * _t3;
        return new Float3x3(_t2, -_t0, Math.fma(pivotX, _t5, Math.fma(pivotY, _t0, this.m02)), _t0, _t2, Math.fma(pivotY, _t5, Math.fma(-pivotX, _t0, this.m12)), 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float3x3 rotateAround_orthogonal(float angle, float pivotX, float pivotY) {
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        return new Float3x3(Math.fma(this.m00, _t2, this.m01 * _t0), Math.fma(this.m01, _t2, -(this.m00 * _t0)), Math.fma(this.m00, _t9, Math.fma(this.m01, _t10, this.m02)), Math.fma(this.m10, _t2, this.m11 * _t0), Math.fma(this.m11, _t2, -(this.m10 * _t0)), Math.fma(this.m10, _t9, Math.fma(this.m11, _t10, this.m12)), 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float3x3 rotateAround_affine(float angle, float pivotX, float pivotY) {
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        return new Float3x3(Math.fma(this.m00, _t2, this.m01 * _t0), Math.fma(this.m01, _t2, -(this.m00 * _t0)), Math.fma(this.m00, _t9, Math.fma(this.m01, _t10, this.m02)), Math.fma(this.m10, _t2, this.m11 * _t0), Math.fma(this.m11, _t2, -(this.m10 * _t0)), Math.fma(this.m10, _t9, Math.fma(this.m11, _t10, this.m12)), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Float3x3 rotateAround_general(float angle, float pivotX, float pivotY) {
        float _t0 = Math.sin(angle);
        float _t2 = Math.cosFromSin(_t0, angle);
        float _t3 = Math.sin(0.5f * angle);
        float _t8 = (_t3 + _t3) * _t3;
        float _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        float _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        return new Float3x3(Math.fma(this.m00, _t2, this.m01 * _t0), Math.fma(this.m01, _t2, -(this.m00 * _t0)), Math.fma(this.m00, _t9, Math.fma(this.m01, _t10, this.m02)), Math.fma(this.m10, _t2, this.m11 * _t0), Math.fma(this.m11, _t2, -(this.m10 * _t0)), Math.fma(this.m10, _t9, Math.fma(this.m11, _t10, this.m12)), Math.fma(this.m20, _t2, this.m21 * _t0), Math.fma(this.m21, _t2, -(this.m20 * _t0)), Math.fma(this.m20, _t9, Math.fma(this.m21, _t10, this.m22)), 0);
    }


    /**
     * Apply the rotation {@code angle} about the pivot point ({@code pivotX}, {@code pivotY}) to
     * this matrix, returning the result as a value.
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
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @return the resulting matrix
     */
    public Float3x3 rotateAround(float angle, float pivotX, float pivotY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAround_identity(angle, pivotX, pivotY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAround_translation(angle, pivotX, pivotY);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateAround_orthogonal(angle, pivotX, pivotY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateAround_affine(angle, pivotX, pivotY);
        return rotateAround_general(angle, pivotX, pivotY);
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis {@code axis} to this matrix,
     * returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return the resulting matrix
     */
    public Float3x3 rotateAxis(float angle, Float3 axis) {
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle);
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAxis_identity(angle, axisX, axisY, axisZ);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAxis_translation(angle, axisX, axisY, axisZ);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateAxis_orthogonal(angle, axisX, axisY, axisZ);
        return rotateAxis_general(angle, axisX, axisY, axisZ);
    }


    /**
     * Private body of {@code rotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAxis} dispatcher.
     */
    private Float3x3 rotateAxis_translation(float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t4 = axisY * axisZ;
        float _t5 = axisX * axisY;
        float _t7 = 1.0f - _t1;
        float _t10 = Math.fma(_t7, axisZ * axisZ, _t1);
        float _t11 = Math.fma(axisX, _t0, _t7 * _t4);
        float _t12 = Math.fma(_t7, _t2, -(axisY * _t0));
        return new Float3x3(Math.fma(_t7, axisX * axisX, Math.fma(this.m02, _t12, _t1)), Math.fma(this.m02, _t11, Math.fma(_t7, _t5, -(axisZ * _t0))), Math.fma(this.m02, _t10, Math.fma(axisY, _t0, _t7 * _t2)), Math.fma(this.m12, _t12, Math.fma(axisZ, _t0, _t7 * _t5)), Math.fma(_t7, axisY * axisY, Math.fma(this.m12, _t11, _t1)), Math.fma(this.m12, _t10, Math.fma(_t7, _t4, -(axisX * _t0))), _t12, _t11, _t10, 0);
    }


    /**
     * Private body of {@code rotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAxis} dispatcher.
     */
    private Float3x3 rotateAxis_orthogonal(float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        float[] _col0 = lookAlong_orthogonal_s524747ee_tail_s5ff7ace2_c0(Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(_t11, axisX * axisX, _t1), Math.fma(axisZ, _t0, _t11 * _t5));
        float[] _col1 = lookAlong_orthogonal_s524747ee_tail_s5ff7ace2_c0(Math.fma(axisX, _t0, _t11 * _t6), Math.fma(_t11, _t5, -(axisZ * _t0)), Math.fma(_t11, axisY * axisY, _t1));
        float[] _col2 = lookAlong_orthogonal_s524747ee_tail_s5ff7ace2_c0(Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(_t11, _t6, -(axisX * _t0)));
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Private body of {@code rotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAxis} dispatcher.
     */
    private Float3x3 rotateAxis_general(float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        float[] _col0 = lookAlong_general_s524747ee_tail_s5ff7ace2_c0(Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(_t11, axisX * axisX, _t1), Math.fma(axisZ, _t0, _t11 * _t5));
        float[] _col1 = lookAlong_general_s524747ee_tail_s5ff7ace2_c0(Math.fma(axisX, _t0, _t11 * _t6), Math.fma(_t11, _t5, -(axisZ * _t0)), Math.fma(_t11, axisY * axisY, _t1));
        float[] _col2 = lookAlong_general_s524747ee_tail_s5ff7ace2_c0(Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(_t11, _t6, -(axisX * _t0)));
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Apply a rotation of {@code angle} radians about the axis ({@code axisX}, {@code axisY},
     * {@code axisZ}) to this matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: {@code (axisX, axisY, axisZ)} must have unit length.
     *
     * @param angle the angle in radians
     * @param axisX the {@code x} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisY the {@code y} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @param axisZ the {@code z} component of the rotation axis {@code (axisX, axisY, axisZ)}
     * @return the resulting matrix
     */
    public Float3x3 rotateAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle);
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return preRotateAxis_identity(angle, axisX, axisY, axisZ);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateAxis_translation(angle, axisX, axisY, axisZ);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateAxis_orthogonal(angle, axisX, axisY, axisZ);
        return rotateAxis_general(angle, axisX, axisY, axisZ);
    }


    /**
     * Apply a rotation of {@code angle} radians about the X axis to this matrix, returning the
     * result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting matrix
     */
    public Float3x3 rotateX(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float3x3(this.m00, Math.fma(this.m01, _t1, this.m02 * _t0), Math.fma(this.m02, _t1, -(this.m01 * _t0)), this.m10, Math.fma(this.m11, _t1, this.m12 * _t0), Math.fma(this.m12, _t1, -(this.m11 * _t0)), this.m20, Math.fma(this.m21, _t1, this.m22 * _t0), Math.fma(this.m22, _t1, -(this.m21 * _t0)), 0);
    }


    /**
     * Private body of {@code rotateX180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX180} dispatcher.
     */
    private Float3x3 rotateX180_orthogonal() {
        return new Float3x3(this.m11, this.m10, -this.m02, this.m10, -this.m11, -this.m12, 0.0f, 0.0f, -1.0f, 0);
    }


    /**
     * Private body of {@code rotateX180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX180} dispatcher.
     */
    private Float3x3 rotateX180_affine() {
        return new Float3x3(this.m00, -this.m01, -this.m02, this.m10, -this.m11, -this.m12, 0.0f, 0.0f, -1.0f, 0);
    }


    /**
     * Private body of {@code rotateX180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX180} dispatcher.
     */
    private Float3x3 rotateX180_general() {
        return new Float3x3(this.m00, -this.m01, -this.m02, this.m10, -this.m11, -this.m12, this.m20, -this.m21, -this.m22, 0);
    }


    /**
     * Apply a rotation of 180 degrees about the X axis to this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 rotateX180() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(1.0f, 0.0f, -this.m02, 0.0f, -1.0f, -this.m12, 0.0f, 0.0f, -1.0f, 0);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateX180_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateX180_affine();
        return rotateX180_general();
    }


    /**
     * Private body of {@code rotateX270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX270} dispatcher.
     */
    private Float3x3 rotateX270_orthogonal() {
        return new Float3x3(this.m00, -this.m02, this.m01, this.m10, -this.m12, this.m11, 0.0f, -1.0f, 0.0f, 0);
    }


    /**
     * Private body of {@code rotateX270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX270} dispatcher.
     */
    private Float3x3 rotateX270_general() {
        return new Float3x3(this.m00, -this.m02, this.m01, this.m10, -this.m12, this.m11, this.m20, -this.m22, this.m21, 0);
    }


    /**
     * Apply a rotation of 270 degrees about the X axis to this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 rotateX270() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, -1.0f, 0.0f, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(1.0f, -this.m02, 0.0f, 0.0f, -this.m12, 1.0f, 0.0f, -1.0f, 0.0f, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateX270_orthogonal();
        return rotateX270_general();
    }


    /**
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Float3x3 rotateX90_orthogonal() {
        return new Float3x3(this.m11, this.m02, this.m10, this.m10, this.m12, -this.m11, 0.0f, 1.0f, 0.0f, 0);
    }


    /**
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Float3x3 rotateX90_affine() {
        return new Float3x3(this.m00, this.m02, -this.m01, this.m10, this.m12, -this.m11, 0.0f, 1.0f, 0.0f, 0);
    }


    /**
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Float3x3 rotateX90_general() {
        return new Float3x3(this.m00, this.m02, -this.m01, this.m10, this.m12, -this.m11, this.m20, this.m22, -this.m21, 0);
    }


    /**
     * Apply a rotation of 90 degrees about the X axis to this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 rotateX90() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(1.0f, this.m02, 0.0f, 0.0f, this.m12, -1.0f, 0.0f, 1.0f, 0.0f, 0);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateX90_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateX90_affine();
        return rotateX90_general();
    }


    /**
     * Private body of {@code rotateXYZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXYZ} dispatcher.
     */
    private Float3x3 rotateXYZ_identity(float angleX, float angleY, float angleZ) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t0;
        float _t7 = _t0 * _t5;
        return new Float3x3(_t3 * _t4, -(_t1 * _t3), _t0, Math.fma(_t6, _t4, _t1 * _t5), Math.fma(_t5, _t4, -(_t6 * _t1)), -(_t2 * _t3), Math.fma(_t2, _t1, -(_t7 * _t4)), Math.fma(_t7, _t1, _t2 * _t4), _t5 * _t3, 0);
    }


    /**
     * Private body of {@code rotateXYZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXYZ} dispatcher.
     */
    private Float3x3 rotateXYZ_translation(float angleX, float angleY, float angleZ) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleY);
        float _t3 = Math.cosFromSin(_t0, angleX);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleY);
        float _t6 = _t0 * _t2;
        float _t7 = _t2 * _t3;
        float _t9 = _t3 * _t5;
        float _t12 = Math.fma(_t7, _t1, _t0 * _t4);
        float _t13 = Math.fma(_t0, _t1, -(_t7 * _t4));
        return new Float3x3(Math.fma(this.m02, _t13, _t5 * _t4), Math.fma(this.m02, _t12, -(_t1 * _t5)), Math.fma(this.m02, _t9, _t2), Math.fma(this.m12, _t13, Math.fma(_t6, _t4, _t1 * _t3)), Math.fma(this.m12, _t12, Math.fma(_t3, _t4, -(_t6 * _t1))), Math.fma(this.m12, _t9, -(_t0 * _t5)), _t13, _t12, _t9, 0);
    }

    /**
     * Private per-column body of {@code rotateXYZ_orthogonal}. Shared by the identical private
     * paths of {@code rotateXYZ}, {@code rotateXZY} and {@code rotateZXY}; reached only through
     * them.
     */
    private float[] rotateXYZ_orthogonal_s6e793366_c1(float _t19, float _t21, float _t10) {
        return new float[] {Math.fma(this.m02, _t19, Math.fma(this.m01, _t21, -(this.m00 * _t10))), Math.fma(this.m12, _t19, Math.fma(this.m11, _t21, -(this.m10 * _t10))), _t19};
    }

    /**
     * Private per-column body of {@code rotateXYZ_orthogonal}. Shared by the identical private
     * paths of {@code rotateXYZ}, {@code rotateYXZ} and {@code rotateYZX}; reached only through
     * them.
     */
    private float[] rotateXYZ_orthogonal_s6e793366_c2(float _t15, float _t2, float _t11) {
        return new float[] {Math.fma(this.m02, _t15, Math.fma(this.m00, _t2, -(this.m01 * _t11))), Math.fma(this.m12, _t15, Math.fma(this.m10, _t2, -(this.m11 * _t11))), _t15};
    }


    /**
     * Private body of {@code rotateXYZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXYZ} dispatcher.
     */
    private Float3x3 rotateXYZ_orthogonal(float angleX, float angleY, float angleZ) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleY);
        float _t3 = Math.cosFromSin(_t0, angleX);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleY);
        float _t6 = _t0 * _t2;
        float _t7 = _t2 * _t3;
        float[] _col0 = lookAlong_orthogonal_s524747ee_tail_s5ff7ace2_c0(Math.fma(_t0, _t1, -(_t7 * _t4)), _t5 * _t4, Math.fma(_t6, _t4, _t1 * _t3));
        float[] _col1 = rotateXYZ_orthogonal_s6e793366_c1(Math.fma(_t7, _t1, _t0 * _t4), Math.fma(_t3, _t4, -(_t6 * _t1)), _t1 * _t5);
        float[] _col2 = rotateXYZ_orthogonal_s6e793366_c2(_t3 * _t5, _t2, _t0 * _t5);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }

    /**
     * Private per-column body of {@code rotateXYZ_general}. Shared by the identical private paths
     * of {@code rotateXYZ}, {@code rotateXZY} and {@code rotateZXY}; reached only through them.
     */
    private float[] rotateXYZ_general_s6e793366_c1(float _t19, float _t21, float _t10) {
        return new float[] {Math.fma(this.m02, _t19, Math.fma(this.m01, _t21, -(this.m00 * _t10))), Math.fma(this.m12, _t19, Math.fma(this.m11, _t21, -(this.m10 * _t10))), Math.fma(this.m22, _t19, Math.fma(this.m21, _t21, -(this.m20 * _t10)))};
    }

    /**
     * Private per-column body of {@code rotateXYZ_general}. Shared by the identical private paths
     * of {@code rotateXYZ}, {@code rotateYXZ} and {@code rotateYZX}; reached only through them.
     */
    private float[] rotateXYZ_general_s6e793366_c2(float _t15, float _t2, float _t11) {
        return new float[] {Math.fma(this.m02, _t15, Math.fma(this.m00, _t2, -(this.m01 * _t11))), Math.fma(this.m12, _t15, Math.fma(this.m10, _t2, -(this.m11 * _t11))), Math.fma(this.m22, _t15, Math.fma(this.m20, _t2, -(this.m21 * _t11)))};
    }


    /**
     * Private body of {@code rotateXYZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXYZ} dispatcher.
     */
    private Float3x3 rotateXYZ_general(float angleX, float angleY, float angleZ) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleY);
        float _t3 = Math.cosFromSin(_t0, angleX);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleY);
        float _t6 = _t0 * _t2;
        float _t7 = _t2 * _t3;
        float[] _col0 = lookAlong_general_s524747ee_tail_s5ff7ace2_c0(Math.fma(_t0, _t1, -(_t7 * _t4)), _t5 * _t4, Math.fma(_t6, _t4, _t1 * _t3));
        float[] _col1 = rotateXYZ_general_s6e793366_c1(Math.fma(_t7, _t1, _t0 * _t4), Math.fma(_t3, _t4, -(_t6 * _t1)), _t1 * _t5);
        float[] _col2 = rotateXYZ_general_s6e793366_c2(_t3 * _t5, _t2, _t0 * _t5);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Apply a rotation of {@code angleX}, {@code angleY} and {@code angleZ} radians about the X, Y
     * and Z axes, in that order (the matrix product {@code Rx * Ry * Rz}, so a vector is rotated
     * about the Z axis first, then Y, then X), to this matrix, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Float3x3 rotateXYZ(float angleX, float angleY, float angleZ) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateXYZ_identity(angleX, angleY, angleZ);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateXYZ_translation(angleX, angleY, angleZ);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateXYZ_orthogonal(angleX, angleY, angleZ);
        return rotateXYZ_general(angleX, angleY, angleZ);
    }


    /**
     * Private body of {@code rotateXZY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXZY} dispatcher.
     */
    private Float3x3 rotateXZY_identity(float angleX, float angleZ, float angleY) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t1 * _t5;
        return new Float3x3(_t3 * _t4, -_t1, _t0 * _t4, Math.fma(_t7, _t3, _t2 * _t0), _t5 * _t4, Math.fma(_t7, _t0, -(_t2 * _t3)), Math.fma(_t6, _t3, -(_t0 * _t5)), _t2 * _t4, Math.fma(_t6, _t0, _t5 * _t3), 0);
    }


    /**
     * Private body of {@code rotateXZY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXZY} dispatcher.
     */
    private Float3x3 rotateXZY_translation(float angleX, float angleZ, float angleY) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleY);
        float _t3 = Math.cosFromSin(_t2, angleY);
        float _t4 = Math.cosFromSin(_t0, angleX);
        float _t5 = Math.cosFromSin(_t1, angleZ);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t5;
        float _t9 = _t1 * _t4;
        float _t12 = Math.fma(_t6, _t2, _t4 * _t3);
        float _t13 = Math.fma(_t6, _t3, -(_t2 * _t4));
        return new Float3x3(Math.fma(this.m02, _t13, _t3 * _t5), Math.fma(this.m02, _t8, -_t1), Math.fma(this.m02, _t12, _t2 * _t5), Math.fma(this.m12, _t13, Math.fma(_t9, _t3, _t0 * _t2)), Math.fma(this.m12, _t8, _t4 * _t5), Math.fma(this.m12, _t12, Math.fma(_t9, _t2, -(_t0 * _t3))), _t13, _t8, _t12, 0);
    }


    /**
     * Private body of {@code rotateXZY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXZY} dispatcher.
     */
    private Float3x3 rotateXZY_orthogonal(float angleX, float angleZ, float angleY) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleY);
        float _t3 = Math.cosFromSin(_t2, angleY);
        float _t4 = Math.cosFromSin(_t0, angleX);
        float _t5 = Math.cosFromSin(_t1, angleZ);
        float _t6 = _t0 * _t1;
        float _t9 = _t1 * _t4;
        float[] _col0 = lookAlong_orthogonal_s524747ee_tail_s5ff7ace2_c0(Math.fma(_t6, _t3, -(_t2 * _t4)), _t3 * _t5, Math.fma(_t9, _t3, _t0 * _t2));
        float[] _col1 = rotateXYZ_orthogonal_s6e793366_c1(_t0 * _t5, _t4 * _t5, _t1);
        float[] _col2 = lookAlong_orthogonal_s524747ee_tail_s5ff7ace2_c0(Math.fma(_t6, _t2, _t4 * _t3), _t2 * _t5, Math.fma(_t9, _t2, -(_t0 * _t3)));
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Private body of {@code rotateXZY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXZY} dispatcher.
     */
    private Float3x3 rotateXZY_general(float angleX, float angleZ, float angleY) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleY);
        float _t3 = Math.cosFromSin(_t2, angleY);
        float _t4 = Math.cosFromSin(_t0, angleX);
        float _t5 = Math.cosFromSin(_t1, angleZ);
        float _t6 = _t0 * _t1;
        float _t9 = _t1 * _t4;
        float[] _col0 = lookAlong_general_s524747ee_tail_s5ff7ace2_c0(Math.fma(_t6, _t3, -(_t2 * _t4)), _t3 * _t5, Math.fma(_t9, _t3, _t0 * _t2));
        float[] _col1 = rotateXYZ_general_s6e793366_c1(_t0 * _t5, _t4 * _t5, _t1);
        float[] _col2 = lookAlong_general_s524747ee_tail_s5ff7ace2_c0(Math.fma(_t6, _t2, _t4 * _t3), _t2 * _t5, Math.fma(_t9, _t2, -(_t0 * _t3)));
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Apply a rotation of {@code angleX}, {@code angleZ} and {@code angleY} radians about the X, Z
     * and Y axes, in that order (the matrix product {@code Rx * Rz * Ry}, so a vector is rotated
     * about the Y axis first, then Z, then X), to this matrix, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Float3x3 rotateXZY(float angleX, float angleZ, float angleY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateXZY_identity(angleX, angleZ, angleY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateXZY_translation(angleX, angleZ, angleY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateXZY_orthogonal(angleX, angleZ, angleY);
        return rotateXZY_general(angleX, angleZ, angleY);
    }


    /**
     * Apply a rotation of -180 degrees about the X axis to this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 rotateXn180() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(1.0f, 0.0f, -this.m02, 0.0f, -1.0f, -this.m12, 0.0f, 0.0f, -1.0f, 0);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateX180_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateX180_affine();
        return rotateX180_general();
    }


    /**
     * Apply a rotation of -270 degrees about the X axis to this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 rotateXn270() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(1.0f, this.m02, 0.0f, 0.0f, this.m12, -1.0f, 0.0f, 1.0f, 0.0f, 0);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateX90_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateX90_affine();
        return rotateX90_general();
    }


    /**
     * Apply a rotation of -90 degrees about the X axis to this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 rotateXn90() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, -1.0f, 0.0f, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(1.0f, -this.m02, 0.0f, 0.0f, -this.m12, 1.0f, 0.0f, -1.0f, 0.0f, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateX270_orthogonal();
        return rotateX270_general();
    }


    /**
     * Apply a rotation of {@code angle} radians about the Y axis to this matrix, returning the
     * result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting matrix
     */
    public Float3x3 rotateY(float angle) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        return new Float3x3(Math.fma(this.m00, _t1, -(this.m02 * _t0)), this.m01, Math.fma(this.m00, _t0, this.m02 * _t1), Math.fma(this.m10, _t1, -(this.m12 * _t0)), this.m11, Math.fma(this.m10, _t0, this.m12 * _t1), Math.fma(this.m20, _t1, -(this.m22 * _t0)), this.m21, Math.fma(this.m20, _t0, this.m22 * _t1), 0);
    }


    /**
     * Private body of {@code rotateY180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY180} dispatcher.
     */
    private Float3x3 rotateY180_orthogonal() {
        return new Float3x3(-this.m00, this.m01, -this.m02, -this.m10, this.m11, -this.m12, 0.0f, 0.0f, -1.0f, 0);
    }


    /**
     * Private body of {@code rotateY180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY180} dispatcher.
     */
    private Float3x3 rotateY180_general() {
        return new Float3x3(-this.m00, this.m01, -this.m02, -this.m10, this.m11, -this.m12, -this.m20, this.m21, -this.m22, 0);
    }


    /**
     * Apply a rotation of 180 degrees about the Y axis to this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 rotateY180() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(-1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(-1.0f, 0.0f, -this.m02, 0.0f, 1.0f, -this.m12, 0.0f, 0.0f, -1.0f, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateY180_orthogonal();
        return rotateY180_general();
    }


    /**
     * Private body of {@code rotateY270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY270} dispatcher.
     */
    private Float3x3 rotateY270_orthogonal() {
        return new Float3x3(this.m02, this.m01, -this.m00, this.m12, this.m11, -this.m10, 1.0f, 0.0f, 0.0f, 0);
    }


    /**
     * Private body of {@code rotateY270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY270} dispatcher.
     */
    private Float3x3 rotateY270_general() {
        return new Float3x3(this.m02, this.m01, -this.m00, this.m12, this.m11, -this.m10, this.m22, this.m21, -this.m20, 0);
    }


    /**
     * Apply a rotation of 270 degrees about the Y axis to this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 rotateY270() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(this.m02, 0.0f, -1.0f, this.m12, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateY270_orthogonal();
        return rotateY270_general();
    }


    /**
     * Private body of {@code rotateY90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY90} dispatcher.
     */
    private Float3x3 rotateY90_orthogonal() {
        return new Float3x3(-this.m02, this.m01, this.m00, -this.m12, this.m11, this.m10, -1.0f, 0.0f, 0.0f, 0);
    }


    /**
     * Private body of {@code rotateY90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY90} dispatcher.
     */
    private Float3x3 rotateY90_general() {
        return new Float3x3(-this.m02, this.m01, this.m00, -this.m12, this.m11, this.m10, -this.m22, this.m21, this.m20, 0);
    }


    /**
     * Apply a rotation of 90 degrees about the Y axis to this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 rotateY90() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(-this.m02, 0.0f, 1.0f, -this.m12, 1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateY90_orthogonal();
        return rotateY90_general();
    }


    /**
     * Private body of {@code rotateYXZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYXZ} dispatcher.
     */
    private Float3x3 rotateYXZ_identity(float angleY, float angleX, float angleZ) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleY);
        float _t2 = Math.sin(angleZ);
        float _t3 = Math.cosFromSin(_t1, angleY);
        float _t4 = Math.cosFromSin(_t2, angleZ);
        float _t5 = Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t3;
        return new Float3x3(Math.fma(_t6, _t2, _t3 * _t4), Math.fma(_t6, _t4, -(_t2 * _t3)), _t1 * _t5, _t2 * _t5, _t5 * _t4, -_t0, Math.fma(_t7, _t2, -(_t1 * _t4)), Math.fma(_t7, _t4, _t1 * _t2), _t5 * _t3, 0);
    }


    /**
     * Private body of {@code rotateYXZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYXZ} dispatcher.
     */
    private Float3x3 rotateYXZ_translation(float angleY, float angleX, float angleZ) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleY);
        float _t2 = Math.sin(angleZ);
        float _t3 = Math.cosFromSin(_t1, angleY);
        float _t4 = Math.cosFromSin(_t2, angleZ);
        float _t5 = Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        float _t11 = _t5 * _t3;
        float _t12 = Math.fma(_t8, _t4, _t1 * _t2);
        float _t13 = Math.fma(_t8, _t2, -(_t1 * _t4));
        return new Float3x3(Math.fma(this.m02, _t13, Math.fma(_t6, _t2, _t3 * _t4)), Math.fma(this.m02, _t12, Math.fma(_t6, _t4, -(_t2 * _t3))), Math.fma(this.m02, _t11, _t1 * _t5), Math.fma(this.m12, _t13, _t2 * _t5), Math.fma(this.m12, _t12, _t5 * _t4), Math.fma(this.m12, _t11, -_t0), _t13, _t12, _t11, 0);
    }


    /**
     * Private body of {@code rotateYXZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYXZ} dispatcher.
     */
    private Float3x3 rotateYXZ_orthogonal(float angleY, float angleX, float angleZ) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleY);
        float _t2 = Math.sin(angleZ);
        float _t3 = Math.cosFromSin(_t1, angleY);
        float _t4 = Math.cosFromSin(_t2, angleZ);
        float _t5 = Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        float[] _col0 = lookAlong_orthogonal_s524747ee_tail_s5ff7ace2_c0(Math.fma(_t8, _t2, -(_t1 * _t4)), Math.fma(_t6, _t2, _t3 * _t4), _t2 * _t5);
        float[] _col1 = lookAlong_orthogonal_s524747ee_tail_s5ff7ace2_c0(Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t6, _t4, -(_t2 * _t3)), _t5 * _t4);
        float[] _col2 = rotateXYZ_orthogonal_s6e793366_c2(_t5 * _t3, _t1 * _t5, _t0);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Private body of {@code rotateYXZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYXZ} dispatcher.
     */
    private Float3x3 rotateYXZ_general(float angleY, float angleX, float angleZ) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleY);
        float _t2 = Math.sin(angleZ);
        float _t3 = Math.cosFromSin(_t1, angleY);
        float _t4 = Math.cosFromSin(_t2, angleZ);
        float _t5 = Math.cosFromSin(_t0, angleX);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        float[] _col0 = lookAlong_general_s524747ee_tail_s5ff7ace2_c0(Math.fma(_t8, _t2, -(_t1 * _t4)), Math.fma(_t6, _t2, _t3 * _t4), _t2 * _t5);
        float[] _col1 = lookAlong_general_s524747ee_tail_s5ff7ace2_c0(Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t6, _t4, -(_t2 * _t3)), _t5 * _t4);
        float[] _col2 = rotateXYZ_general_s6e793366_c2(_t5 * _t3, _t1 * _t5, _t0);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Apply a rotation of {@code angleY}, {@code angleX} and {@code angleZ} radians about the Y, X
     * and Z axes, in that order (the matrix product {@code Ry * Rx * Rz}, so a vector is rotated
     * about the Z axis first, then X, then Y), to this matrix, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Float3x3 rotateYXZ(float angleY, float angleX, float angleZ) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateYXZ_identity(angleY, angleX, angleZ);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateYXZ_translation(angleY, angleX, angleZ);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateYXZ_orthogonal(angleY, angleX, angleZ);
        return rotateYXZ_general(angleY, angleX, angleZ);
    }


    /**
     * Private body of {@code rotateYZX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYZX} dispatcher.
     */
    private Float3x3 rotateYZX_identity(float angleY, float angleZ, float angleX) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t1 * _t3;
        return new Float3x3(_t3 * _t4, Math.fma(_t2, _t0, -(_t7 * _t5)), Math.fma(_t7, _t2, _t0 * _t5), _t1, _t5 * _t4, -(_t2 * _t4), -(_t0 * _t4), Math.fma(_t6, _t5, _t2 * _t3), Math.fma(_t5, _t3, -(_t6 * _t2)), 0);
    }


    /**
     * Private body of {@code rotateYZX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYZX} dispatcher.
     */
    private Float3x3 rotateYZX_translation(float angleY, float angleZ, float angleX) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t4;
        float _t9 = _t1 * _t3;
        float _t12 = Math.fma(_t6, _t5, _t2 * _t3);
        float _t13 = Math.fma(_t5, _t3, -(_t6 * _t2));
        return new Float3x3(Math.fma(_t3, _t4, -(this.m02 * _t7)), Math.fma(this.m02, _t12, Math.fma(_t2, _t0, -(_t9 * _t5))), Math.fma(this.m02, _t13, Math.fma(_t9, _t2, _t0 * _t5)), Math.fma(-this.m12, _t7, _t1), Math.fma(this.m12, _t12, _t5 * _t4), Math.fma(this.m12, _t13, -(_t2 * _t4)), -_t7, _t12, _t13, 0);
    }

    /**
     * Private per-column body of {@code rotateYZX_orthogonal}. Shared by the identical private
     * paths of {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only through
     * them.
     */
    private float[] rotateYZX_orthogonal_s71a48226_c0(float _t7, float _t13, float _t1) {
        return new float[] {Math.fma(-this.m02, _t7, Math.fma(this.m00, _t13, this.m01 * _t1)), Math.fma(-this.m12, _t7, Math.fma(this.m10, _t13, this.m11 * _t1)), -_t7};
    }


    /**
     * Private body of {@code rotateYZX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYZX} dispatcher.
     */
    private Float3x3 rotateYZX_orthogonal(float angleY, float angleZ, float angleX) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t1, angleZ);
        float _t4 = Math.cosFromSin(_t0, angleY);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t9 = _t1 * _t4;
        float[] _col0 = rotateYZX_orthogonal_s71a48226_c0(_t0 * _t3, _t4 * _t3, _t1);
        float[] _col1 = lookAlong_orthogonal_s524747ee_tail_s5ff7ace2_c0(Math.fma(_t6, _t5, _t2 * _t4), Math.fma(_t2, _t0, -(_t9 * _t5)), _t5 * _t3);
        float[] _col2 = rotateXYZ_orthogonal_s6e793366_c2(Math.fma(_t5, _t4, -(_t6 * _t2)), Math.fma(_t9, _t2, _t0 * _t5), _t2 * _t3);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }

    /**
     * Private per-column body of {@code rotateYZX_general}. Shared by the identical private paths
     * of {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only through them.
     */
    private float[] rotateYZX_general_s71a48226_c0(float _t7, float _t13, float _t1) {
        return new float[] {Math.fma(-this.m02, _t7, Math.fma(this.m00, _t13, this.m01 * _t1)), Math.fma(-this.m12, _t7, Math.fma(this.m10, _t13, this.m11 * _t1)), Math.fma(-this.m22, _t7, Math.fma(this.m20, _t13, this.m21 * _t1))};
    }


    /**
     * Private body of {@code rotateYZX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYZX} dispatcher.
     */
    private Float3x3 rotateYZX_general(float angleY, float angleZ, float angleX) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t1, angleZ);
        float _t4 = Math.cosFromSin(_t0, angleY);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t9 = _t1 * _t4;
        float[] _col0 = rotateYZX_general_s71a48226_c0(_t0 * _t3, _t4 * _t3, _t1);
        float[] _col1 = lookAlong_general_s524747ee_tail_s5ff7ace2_c0(Math.fma(_t6, _t5, _t2 * _t4), Math.fma(_t2, _t0, -(_t9 * _t5)), _t5 * _t3);
        float[] _col2 = rotateXYZ_general_s6e793366_c2(Math.fma(_t5, _t4, -(_t6 * _t2)), Math.fma(_t9, _t2, _t0 * _t5), _t2 * _t3);
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Apply a rotation of {@code angleY}, {@code angleZ} and {@code angleX} radians about the Y, Z
     * and X axes, in that order (the matrix product {@code Ry * Rz * Rx}, so a vector is rotated
     * about the X axis first, then Z, then Y), to this matrix, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Float3x3 rotateYZX(float angleY, float angleZ, float angleX) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateYZX_identity(angleY, angleZ, angleX);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateYZX_translation(angleY, angleZ, angleX);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateYZX_orthogonal(angleY, angleZ, angleX);
        return rotateYZX_general(angleY, angleZ, angleX);
    }


    /**
     * Apply a rotation of -180 degrees about the Y axis to this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 rotateYn180() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(-1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(-1.0f, 0.0f, -this.m02, 0.0f, 1.0f, -this.m12, 0.0f, 0.0f, -1.0f, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateY180_orthogonal();
        return rotateY180_general();
    }


    /**
     * Apply a rotation of -270 degrees about the Y axis to this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 rotateYn270() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(-this.m02, 0.0f, 1.0f, -this.m12, 1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateY90_orthogonal();
        return rotateY90_general();
    }


    /**
     * Apply a rotation of -90 degrees about the Y axis to this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 rotateYn90() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(this.m02, 0.0f, -1.0f, this.m12, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateY270_orthogonal();
        return rotateY270_general();
    }


    /**
     * Apply a rotation of {@code angle} radians about the Z axis to this matrix, returning the
     * result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting matrix
     */
    public Float3x3 rotateZ(float angle) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float _t0 = Math.sin(angle);
            float _t1 = Math.cosFromSin(_t0, angle);
            return new Float3x3(_t1, -_t0, 0.0f, _t0, _t1, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotate_translation(angle);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotate_orthogonal(angle);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotate_affine(angle);
        return rotate_general(angle);
    }


    /**
     * Private body of {@code rotateZ180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ180} dispatcher.
     */
    private Float3x3 rotateZ180_orthogonal() {
        float _t0 = -this.m11;
        return new Float3x3(_t0, this.m10, this.m02, -this.m10, _t0, this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code rotateZ180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ180} dispatcher.
     */
    private Float3x3 rotateZ180_affine() {
        return new Float3x3(-this.m00, -this.m01, this.m02, -this.m10, -this.m11, this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code rotateZ180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ180} dispatcher.
     */
    private Float3x3 rotateZ180_general() {
        return new Float3x3(-this.m00, -this.m01, this.m02, -this.m10, -this.m11, this.m12, -this.m20, -this.m21, this.m22, 0);
    }


    /**
     * Apply a rotation of 180 degrees about the Z axis to this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 rotateZ180() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(-1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(-1.0f, 0.0f, this.m02, 0.0f, -1.0f, this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateZ180_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZ180_affine();
        return rotateZ180_general();
    }


    /**
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Float3x3 rotateZ270_orthogonal() {
        return new Float3x3(this.m10, this.m11, this.m02, -this.m11, this.m10, this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Float3x3 rotateZ270_affine() {
        return new Float3x3(-this.m01, this.m00, this.m02, -this.m11, this.m10, this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Float3x3 rotateZ270_general() {
        return new Float3x3(-this.m01, this.m00, this.m02, -this.m11, this.m10, this.m12, -this.m21, this.m20, this.m22, 0);
    }


    /**
     * Apply a rotation of 270 degrees about the Z axis to this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 rotateZ270() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(0.0f, 1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(0.0f, 1.0f, this.m02, -1.0f, 0.0f, this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateZ270_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZ270_affine();
        return rotateZ270_general();
    }


    /**
     * Private body of {@code rotateZ90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ90} dispatcher.
     */
    private Float3x3 rotateZ90_orthogonal() {
        return new Float3x3(this.m01, -this.m00, this.m02, this.m11, -this.m10, this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code rotateZ90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ90} dispatcher.
     */
    private Float3x3 rotateZ90_affine() {
        return new Float3x3(this.m01, -this.m00, this.m02, this.m11, -this.m10, this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code rotateZ90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ90} dispatcher.
     */
    private Float3x3 rotateZ90_general() {
        return new Float3x3(this.m01, -this.m00, this.m02, this.m11, -this.m10, this.m12, this.m21, -this.m20, this.m22, 0);
    }


    /**
     * Apply a rotation of 90 degrees about the Z axis to this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 rotateZ90() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(0.0f, -1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(0.0f, -1.0f, this.m02, 1.0f, 0.0f, this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateZ90_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZ90_affine();
        return rotateZ90_general();
    }


    /**
     * Private body of {@code rotateZXY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZXY} dispatcher.
     */
    private Float3x3 rotateZXY_identity(float angleZ, float angleX, float angleY) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t2 * _t1;
        float _t7 = _t2 * _t4;
        return new Float3x3(Math.fma(_t3, _t4, -(_t6 * _t0)), -(_t1 * _t5), Math.fma(_t6, _t3, _t0 * _t4), Math.fma(_t7, _t0, _t1 * _t3), _t5 * _t4, Math.fma(_t0, _t1, -(_t7 * _t3)), -(_t0 * _t5), _t2, _t5 * _t3, 0);
    }


    /**
     * Private body of {@code rotateZXY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZXY} dispatcher.
     */
    private Float3x3 rotateZXY_translation(float angleZ, float angleX, float angleY) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleX);
        float _t2 = Math.sin(angleZ);
        float _t3 = Math.cosFromSin(_t1, angleX);
        float _t4 = Math.cosFromSin(_t0, angleY);
        float _t5 = Math.cosFromSin(_t2, angleZ);
        float _t6 = _t1 * _t2;
        float _t7 = _t0 * _t3;
        float _t8 = _t1 * _t5;
        float _t9 = _t3 * _t4;
        return new Float3x3(Math.fma(-this.m02, _t7, Math.fma(_t4, _t5, -(_t6 * _t0))), Math.fma(this.m02, _t1, -(_t2 * _t3)), Math.fma(this.m02, _t9, Math.fma(_t6, _t4, _t0 * _t5)), Math.fma(-this.m12, _t7, Math.fma(_t8, _t0, _t2 * _t4)), Math.fma(this.m12, _t1, _t3 * _t5), Math.fma(this.m12, _t9, Math.fma(_t0, _t2, -(_t8 * _t4))), -_t7, _t1, _t9, 0);
    }


    /**
     * Private body of {@code rotateZXY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZXY} dispatcher.
     */
    private Float3x3 rotateZXY_orthogonal(float angleZ, float angleX, float angleY) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleX);
        float _t2 = Math.sin(angleZ);
        float _t3 = Math.cosFromSin(_t1, angleX);
        float _t4 = Math.cosFromSin(_t0, angleY);
        float _t5 = Math.cosFromSin(_t2, angleZ);
        float _t6 = _t1 * _t2;
        float _t8 = _t1 * _t5;
        float[] _col0 = rotateYZX_orthogonal_s71a48226_c0(_t0 * _t3, Math.fma(_t4, _t5, -(_t6 * _t0)), Math.fma(_t8, _t0, _t2 * _t4));
        float[] _col1 = rotateXYZ_orthogonal_s6e793366_c1(_t1, _t3 * _t5, _t2 * _t3);
        float[] _col2 = lookAlong_orthogonal_s524747ee_tail_s5ff7ace2_c0(_t3 * _t4, Math.fma(_t6, _t4, _t0 * _t5), Math.fma(_t0, _t2, -(_t8 * _t4)));
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Private body of {@code rotateZXY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZXY} dispatcher.
     */
    private Float3x3 rotateZXY_general(float angleZ, float angleX, float angleY) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleX);
        float _t2 = Math.sin(angleZ);
        float _t3 = Math.cosFromSin(_t1, angleX);
        float _t4 = Math.cosFromSin(_t0, angleY);
        float _t5 = Math.cosFromSin(_t2, angleZ);
        float _t6 = _t1 * _t2;
        float _t8 = _t1 * _t5;
        float[] _col0 = rotateYZX_general_s71a48226_c0(_t0 * _t3, Math.fma(_t4, _t5, -(_t6 * _t0)), Math.fma(_t8, _t0, _t2 * _t4));
        float[] _col1 = rotateXYZ_general_s6e793366_c1(_t1, _t3 * _t5, _t2 * _t3);
        float[] _col2 = lookAlong_general_s524747ee_tail_s5ff7ace2_c0(_t3 * _t4, Math.fma(_t6, _t4, _t0 * _t5), Math.fma(_t0, _t2, -(_t8 * _t4)));
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Apply a rotation of {@code angleZ}, {@code angleX} and {@code angleY} radians about the Z, X
     * and Y axes, in that order (the matrix product {@code Rz * Rx * Ry}, so a vector is rotated
     * about the Y axis first, then X, then Z), to this matrix, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Float3x3 rotateZXY(float angleZ, float angleX, float angleY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateZXY_identity(angleZ, angleX, angleY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateZXY_translation(angleZ, angleX, angleY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZXY_orthogonal(angleZ, angleX, angleY);
        return rotateZXY_general(angleZ, angleX, angleY);
    }


    /**
     * Private body of {@code rotateZYX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZYX} dispatcher.
     */
    private Float3x3 rotateZYX_identity(float angleZ, float angleY, float angleX) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t0 * _t4;
        return new Float3x3(_t3 * _t4, Math.fma(_t7, _t2, -(_t1 * _t5)), Math.fma(_t7, _t5, _t2 * _t1), _t1 * _t3, Math.fma(_t6, _t2, _t5 * _t4), Math.fma(_t6, _t5, -(_t2 * _t4)), -_t0, _t2 * _t3, _t5 * _t3, 0);
    }


    /**
     * Private body of {@code rotateZYX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZYX} dispatcher.
     */
    private Float3x3 rotateZYX_translation(float angleZ, float angleY, float angleX) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t7 = _t2 * _t3;
        float _t8 = _t0 * _t4;
        float _t9 = _t5 * _t3;
        return new Float3x3(Math.fma(_t3, _t4, -(this.m02 * _t0)), Math.fma(this.m02, _t7, Math.fma(_t8, _t2, -(_t1 * _t5))), Math.fma(this.m02, _t9, Math.fma(_t8, _t5, _t2 * _t1)), Math.fma(_t1, _t3, -(this.m12 * _t0)), Math.fma(this.m12, _t7, Math.fma(_t6, _t2, _t5 * _t4)), Math.fma(this.m12, _t9, Math.fma(_t6, _t5, -(_t2 * _t4))), -_t0, _t7, _t9, 0);
    }


    /**
     * Private body of {@code rotateZYX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZYX} dispatcher.
     */
    private Float3x3 rotateZYX_orthogonal(float angleZ, float angleY, float angleX) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t10 = _t0 * _t4;
        float[] _col0 = rotateYZX_orthogonal_s71a48226_c0(_t0, _t3 * _t4, _t1 * _t3);
        float[] _col1 = lookAlong_orthogonal_s524747ee_tail_s5ff7ace2_c0(_t2 * _t3, Math.fma(_t10, _t2, -(_t1 * _t5)), Math.fma(_t6, _t2, _t5 * _t4));
        float[] _col2 = lookAlong_orthogonal_s524747ee_tail_s5ff7ace2_c0(_t5 * _t3, Math.fma(_t10, _t5, _t2 * _t1), Math.fma(_t6, _t5, -(_t2 * _t4)));
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Private body of {@code rotateZYX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZYX} dispatcher.
     */
    private Float3x3 rotateZYX_general(float angleZ, float angleY, float angleX) {
        float _t0 = Math.sin(angleY);
        float _t1 = Math.sin(angleZ);
        float _t2 = Math.sin(angleX);
        float _t3 = Math.cosFromSin(_t0, angleY);
        float _t4 = Math.cosFromSin(_t1, angleZ);
        float _t5 = Math.cosFromSin(_t2, angleX);
        float _t6 = _t0 * _t1;
        float _t10 = _t0 * _t4;
        float[] _col0 = rotateYZX_general_s71a48226_c0(_t0, _t3 * _t4, _t1 * _t3);
        float[] _col1 = lookAlong_general_s524747ee_tail_s5ff7ace2_c0(_t2 * _t3, Math.fma(_t10, _t2, -(_t1 * _t5)), Math.fma(_t6, _t2, _t5 * _t4));
        float[] _col2 = lookAlong_general_s524747ee_tail_s5ff7ace2_c0(_t5 * _t3, Math.fma(_t10, _t5, _t2 * _t1), Math.fma(_t6, _t5, -(_t2 * _t4)));
        return new Float3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Apply a rotation of {@code angleZ}, {@code angleY} and {@code angleX} radians about the Z, Y
     * and X axes, in that order (the matrix product {@code Rz * Ry * Rx}, so a vector is rotated
     * about the X axis first, then Y, then Z), to this matrix, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Float3x3 rotateZYX(float angleZ, float angleY, float angleX) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return rotateZYX_identity(angleZ, angleY, angleX);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return rotateZYX_translation(angleZ, angleY, angleX);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZYX_orthogonal(angleZ, angleY, angleX);
        return rotateZYX_general(angleZ, angleY, angleX);
    }


    /**
     * Apply a rotation of -180 degrees about the Z axis to this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 rotateZn180() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(-1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(-1.0f, 0.0f, this.m02, 0.0f, -1.0f, this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateZ180_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZ180_affine();
        return rotateZ180_general();
    }


    /**
     * Apply a rotation of -270 degrees about the Z axis to this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 rotateZn270() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(0.0f, -1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(0.0f, -1.0f, this.m02, 1.0f, 0.0f, this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateZ90_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZ90_affine();
        return rotateZ90_general();
    }


    /**
     * Apply a rotation of -90 degrees about the Z axis to this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code R} the rotation matrix, then the new matrix
     * will be {@code M * R}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * R * v}, the rotation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Float3x3 rotateZn90() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(0.0f, 1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(0.0f, 1.0f, this.m02, -1.0f, 0.0f, this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateZ270_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZ270_affine();
        return rotateZ270_general();
    }


    /**
     * Apply a scaling by {@code v} to this matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code p} with the new matrix by using
     * {@code M * S * p}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @return the resulting matrix
     */
    public Float3x3 scale(Float2 v) {
        float vX = v.x();
        float vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(vX, 0.0f, 0.0f, 0.0f, vY, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(vX, 0.0f, this.m02, 0.0f, vY, this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scale_orthogonal(vX, vY);
        return scale_general(vX, vY);
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Float3x3 scale_orthogonal(float vX, float vY) {
        return new Float3x3(this.m00 * vX, this.m01 * vY, this.m02, this.m10 * vX, this.m11 * vY, this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Float3x3 scale_general(float vX, float vY) {
        return new Float3x3(this.m00 * vX, this.m01 * vY, this.m02, this.m10 * vX, this.m11 * vY, this.m12, this.m20 * vX, this.m21 * vY, this.m22, 0);
    }


    /**
     * Apply a scaling by ({@code vX}, {@code vY}) to this matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return the resulting matrix
     */
    public Float3x3 scale(float vX, float vY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(vX, 0.0f, 0.0f, 0.0f, vY, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(vX, 0.0f, this.m02, 0.0f, vY, this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scale_orthogonal(vX, vY);
        return scale_general(vX, vY);
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Float3x3 scale_orthogonal(float s) {
        return new Float3x3(s * this.m00, s * this.m01, this.m02, s * this.m10, s * this.m11, this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Float3x3 scale_general(float s) {
        return new Float3x3(s * this.m00, s * this.m01, this.m02, s * this.m10, s * this.m11, this.m12, s * this.m20, s * this.m21, this.m22, 0);
    }


    /**
     * Apply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) to this matrix, returning
     * the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @return the resulting matrix
     */
    public Float3x3 scale(float s) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(s, 0.0f, 0.0f, 0.0f, s, 0.0f, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(s, 0.0f, this.m02, 0.0f, s, this.m12, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scale_orthogonal(s);
        return scale_general(s);
    }


    /**
     * Apply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) about the pivot point
     * {@code pivot} to this matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivot the pivot point
     * @return the resulting matrix
     */
    public Float3x3 scaleAround(float s, Float2 pivot) {
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float _t0 = 1.0f - s;
            return new Float3x3(s, 0.0f, pivotX * _t0, 0.0f, s, pivotY * _t0, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation(s, pivotX, pivotY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scaleAround_orthogonal(s, pivotX, pivotY);
        return scaleAround_general(s, pivotX, pivotY);
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_translation(float s, float pivotX, float pivotY) {
        float _t0 = 1.0f - s;
        return new Float3x3(s, 0.0f, Math.fma(pivotX, _t0, this.m02), 0.0f, s, Math.fma(pivotY, _t0, this.m12), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_orthogonal(float s, float pivotX, float pivotY) {
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        return new Float3x3(s * this.m00, s * this.m01, Math.fma(this.m00, _t1, Math.fma(this.m01, _t2, this.m02)), s * this.m10, s * this.m11, Math.fma(this.m10, _t1, Math.fma(this.m11, _t2, this.m12)), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_general(float s, float pivotX, float pivotY) {
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        return new Float3x3(s * this.m00, s * this.m01, Math.fma(this.m00, _t1, Math.fma(this.m01, _t2, this.m02)), s * this.m10, s * this.m11, Math.fma(this.m10, _t1, Math.fma(this.m11, _t2, this.m12)), s * this.m20, s * this.m21, Math.fma(this.m20, _t1, Math.fma(this.m21, _t2, this.m22)), 0);
    }


    /**
     * Apply a scaling by {@code s} of the x and y axes only (the 2D homogeneous
     * {@code diag(s, s, 1)}: the third row and column are left unscaled) about the pivot point
     * ({@code pivotX}, {@code pivotY}) to this matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the uniform scale factor
     * @param pivotX the {@code x} component of the vector {@code (pivotX, pivotY)}
     * @param pivotY the {@code y} component of the vector {@code (pivotX, pivotY)}
     * @return the resulting matrix
     */
    public Float3x3 scaleAround(float s, float pivotX, float pivotY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            float _t0 = 1.0f - s;
            return new Float3x3(s, 0.0f, pivotX * _t0, 0.0f, s, pivotY * _t0, 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation(s, pivotX, pivotY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scaleAround_orthogonal(s, pivotX, pivotY);
        return scaleAround_general(s, pivotX, pivotY);
    }


    /**
     * Apply a scaling by {@code s} about the pivot point {@code pivot} to this matrix, returning
     * the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code S} the scaling matrix, then the new matrix
     * will be {@code M * S}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * S * v}, the scaling will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param s the scale factors
     * @param pivot the pivot point
     * @return the resulting matrix
     */
    public Float3x3 scaleAround(Float2 s, Float2 pivot) {
        float sX = s.x();
        float sY = s.y();
        float pivotX = pivot.x();
        float pivotY = pivot.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(sX, 0.0f, pivotX * (1.0f - sX), 0.0f, sY, pivotY * (1.0f - sY), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation(sX, sY, pivotX, pivotY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scaleAround_orthogonal(sX, sY, pivotX, pivotY);
        return scaleAround_general(sX, sY, pivotX, pivotY);
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_translation(float sX, float sY, float pivotX, float pivotY) {
        return new Float3x3(sX, 0.0f, Math.fma(pivotX, 1.0f - sX, this.m02), 0.0f, sY, Math.fma(pivotY, 1.0f - sY, this.m12), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_orthogonal(float sX, float sY, float pivotX, float pivotY) {
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        return new Float3x3(sX * this.m00, sY * this.m01, Math.fma(this.m00, _t2, Math.fma(this.m01, _t3, this.m02)), sX * this.m10, sY * this.m11, Math.fma(this.m10, _t2, Math.fma(this.m11, _t3, this.m12)), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Float3x3 scaleAround_general(float sX, float sY, float pivotX, float pivotY) {
        float _t2 = pivotX * (1.0f - sX);
        float _t3 = pivotY * (1.0f - sY);
        return new Float3x3(sX * this.m00, sY * this.m01, Math.fma(this.m00, _t2, Math.fma(this.m01, _t3, this.m02)), sX * this.m10, sY * this.m11, Math.fma(this.m10, _t2, Math.fma(this.m11, _t3, this.m12)), sX * this.m20, sY * this.m21, Math.fma(this.m20, _t2, Math.fma(this.m21, _t3, this.m22)), 0);
    }


    /**
     * Apply a scaling by ({@code sX}, {@code sY}) about the pivot point ({@code pivotX},
     * {@code pivotY}) to this matrix, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Float3x3 scaleAround(float sX, float sY, float pivotX, float pivotY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(sX, 0.0f, pivotX * (1.0f - sX), 0.0f, sY, pivotY * (1.0f - sY), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation(sX, sY, pivotX, pivotY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scaleAround_orthogonal(sX, sY, pivotX, pivotY);
        return scaleAround_general(sX, sY, pivotX, pivotY);
    }


    /**
     * Apply a translation by {@code v} to this matrix, returning the result as a value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code p} with the new matrix by using
     * {@code M * T * p}, the translation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @return the resulting matrix
     */
    public Float3x3 translate(Float2 v) {
        float vX = v.x();
        float vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(1.0f, 0.0f, vX, 0.0f, 1.0f, vY, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(1.0f, 0.0f, this.m02 + vX, 0.0f, 1.0f, this.m12 + vY, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return translate_orthogonal(vX, vY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return translate_affine(vX, vY);
        return translate_general(vX, vY);
    }


    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Float3x3 translate_orthogonal(float vX, float vY) {
        return new Float3x3(this.m00, this.m01, Math.fma(this.m00, vX, Math.fma(this.m01, vY, this.m02)), this.m10, this.m11, Math.fma(this.m10, vX, Math.fma(this.m11, vY, this.m12)), 0.0f, 0.0f, 1.0f, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Float3x3 translate_affine(float vX, float vY) {
        return new Float3x3(this.m00, this.m01, Math.fma(this.m00, vX, Math.fma(this.m01, vY, this.m02)), this.m10, this.m11, Math.fma(this.m10, vX, Math.fma(this.m11, vY, this.m12)), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Float3x3 translate_general(float vX, float vY) {
        return new Float3x3(this.m00, this.m01, Math.fma(this.m00, vX, Math.fma(this.m01, vY, this.m02)), this.m10, this.m11, Math.fma(this.m10, vX, Math.fma(this.m11, vY, this.m12)), this.m20, this.m21, Math.fma(this.m20, vX, Math.fma(this.m21, vY, this.m22)), 0);
    }


    /**
     * Apply a translation by ({@code vX}, {@code vY}) to this matrix, returning the result as a
     * value.
     * <p>
     * If {@code M} is {@code this} matrix and {@code T} the translation matrix, then the new matrix
     * will be {@code M * T}. So when transforming a vector {@code v} with the new matrix by using
     * {@code M * T * v}, the translation will be applied first.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the translation offsets {@code (vX, vY)}
     * @param vY the {@code y} component of the translation offsets {@code (vX, vY)}
     * @return the resulting matrix
     */
    public Float3x3 translate(float vX, float vY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3x3(1.0f, 0.0f, vX, 0.0f, 1.0f, vY, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3x3(1.0f, 0.0f, this.m02 + vX, 0.0f, 1.0f, this.m12 + vY, 0.0f, 0.0f, 1.0f, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return translate_orthogonal(vX, vY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return translate_affine(vX, vY);
        return translate_general(vX, vY);
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float3x3 view_identity(float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        return new Float3x3(_t0_inv + _t0_inv, 0.0f, -((left + right) * _t0_inv), 0.0f, _t1_inv + _t1_inv, -((bottom + top) * _t1_inv), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float3x3 view_translation(float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        return new Float3x3(_t0_inv + _t0_inv, 0.0f, Math.fma(-(left + right), _t0_inv, this.m02), 0.0f, _t1_inv + _t1_inv, Math.fma(-(bottom + top), _t1_inv, this.m12), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float3x3 view_orthogonal(float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        return new Float3x3(_sp0 * this.m00, _sp1 * this.m01, Math.fma(-this.m01, _sp3, Math.fma(-this.m00, _sp2, this.m02)), _sp0 * this.m10, _sp1 * this.m11, Math.fma(-this.m11, _sp3, Math.fma(-this.m10, _sp2, this.m12)), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float3x3 view_affine(float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        return new Float3x3(_sp0 * this.m00, _sp1 * this.m01, this.m02 + Math.fma(-this.m01, _sp3, -(this.m00 * _sp2)), _sp0 * this.m10, _sp1 * this.m11, this.m12 + Math.fma(-this.m11, _sp3, -(this.m10 * _sp2)), 0.0f, 0.0f, 1.0f, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Float3x3 view_general(float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _sp0 = _t0_inv + _t0_inv;
        float _t1_inv = 1.0f / (top - bottom);
        float _sp1 = _t1_inv + _t1_inv;
        float _sp2 = _t0_inv * (left + right);
        float _sp3 = _t1_inv * (bottom + top);
        return new Float3x3(_sp0 * this.m00, _sp1 * this.m01, this.m02 + Math.fma(-this.m01, _sp3, -(this.m00 * _sp2)), _sp0 * this.m10, _sp1 * this.m11, this.m12 + Math.fma(-this.m11, _sp3, -(this.m10 * _sp2)), _sp0 * this.m20, _sp1 * this.m21, this.m22 + Math.fma(-this.m21, _sp3, -(this.m20 * _sp2)), 0);
    }


    /**
     * Apply a 2D view transformation that maps the rectangle {@code [left, right] x [bottom, top]}
     * onto {@code [-1, +1] x [-1, +1]} to this matrix, returning the result as a value.
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
     * @return the resulting matrix
     */
    public Float3x3 view(float left, float right, float bottom, float top) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return view_identity(left, right, bottom, top);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return view_translation(left, right, bottom, top);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return view_orthogonal(left, right, bottom, top);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return view_affine(left, right, bottom, top);
        return view_general(left, right, bottom, top);
    }


    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the right operand of the product
     * @return the resulting vector
     */
    public Float3 mul(Float3 v) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3(vX, vY, vZ);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3(Math.fma(this.m02, vZ, vX), Math.fma(this.m12, vZ, vY), vZ);
        return mul_general(vX, vY, vZ);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Float3 mul_general(float vX, float vY, float vZ) {
        return new Float3(Math.fma(this.m02, vZ, Math.fma(this.m00, vX, this.m01 * vY)), Math.fma(this.m12, vZ, Math.fma(this.m10, vX, this.m11 * vY)), Math.fma(this.m22, vZ, Math.fma(this.m20, vX, this.m21 * vY)));
    }


    /**
     * Multiply this matrix by the given vector, i.e. compute the matrix-vector product
     * {@code this * v}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY, vZ)}
     * @param vY the {@code y} component of the vector {@code (vX, vY, vZ)}
     * @param vZ the {@code z} component of the vector {@code (vX, vY, vZ)}
     * @return the resulting vector
     */
    public Float3 mul(float vX, float vY, float vZ) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float3(vX, vY, vZ);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float3(Math.fma(this.m02, vZ, vX), Math.fma(this.m12, vZ, vY), vZ);
        return mul_general(vX, vY, vZ);
    }


    /**
     * Transform the given direction by this matrix, ignoring any translation, returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the direction to transform
     * @return the resulting vector
     */
    public Float2 transformDirection(Float2 v) {
        float vX = v.x();
        float vY = v.y();
        if ((this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float2(vX, vY);
        return transformDirection_general(vX, vY);
    }


    /**
     * Private body of {@code transformDirection}, specialized by runtime matrix properties; reached
     * only through the public {@code transformDirection} dispatcher.
     */
    private Float2 transformDirection_general(float vX, float vY) {
        return new Float2(Math.fma(this.m00, vX, this.m01 * vY), Math.fma(this.m10, vX, this.m11 * vY));
    }


    /**
     * Transform the given direction by this matrix, ignoring any translation, returning the result
     * as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return the resulting vector
     */
    public Float2 transformDirection(float vX, float vY) {
        if ((this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float2(vX, vY);
        return transformDirection_general(vX, vY);
    }


    /**
     * Transform the given position by this matrix, treating it as a point with an implicit
     * {@code w = 1}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the position to transform
     * @return the resulting vector
     */
    public Float2 transformPosition(Float2 v) {
        float vX = v.x();
        float vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float2(vX, vY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float2(this.m02 + vX, this.m12 + vY);
        return transformPosition_general(vX, vY);
    }


    /**
     * Private body of {@code transformPosition}, specialized by runtime matrix properties; reached
     * only through the public {@code transformPosition} dispatcher.
     */
    private Float2 transformPosition_general(float vX, float vY) {
        return new Float2(Math.fma(this.m00, vX, Math.fma(this.m01, vY, this.m02)), Math.fma(this.m10, vX, Math.fma(this.m11, vY, this.m12)));
    }


    /**
     * Transform the given position by this matrix, treating it as a point with an implicit
     * {@code w = 1}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param vX the {@code x} component of the vector {@code (vX, vY)}
     * @param vY the {@code y} component of the vector {@code (vX, vY)}
     * @return the resulting vector
     */
    public Float2 transformPosition(float vX, float vY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Float2(vX, vY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Float2(this.m02 + vX, this.m12 + vY);
        return transformPosition_general(vX, vY);
    }

    /**
     * {@return a copy with the {@code m00} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m00} element
     */
    public Float3x3 withM00(float v) {
        return new Float3x3(v, m01, m02, m10, m11, m12, m20, m21, m22);
    }

    /**
     * {@return a copy with the {@code m01} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m01} element
     */
    public Float3x3 withM01(float v) {
        return new Float3x3(m00, v, m02, m10, m11, m12, m20, m21, m22);
    }

    /**
     * {@return a copy with the {@code m02} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m02} element
     */
    public Float3x3 withM02(float v) {
        return new Float3x3(m00, m01, v, m10, m11, m12, m20, m21, m22);
    }

    /**
     * {@return a copy with the {@code m10} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m10} element
     */
    public Float3x3 withM10(float v) {
        return new Float3x3(m00, m01, m02, v, m11, m12, m20, m21, m22);
    }

    /**
     * {@return a copy with the {@code m11} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m11} element
     */
    public Float3x3 withM11(float v) {
        return new Float3x3(m00, m01, m02, m10, v, m12, m20, m21, m22);
    }

    /**
     * {@return a copy with the {@code m12} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m12} element
     */
    public Float3x3 withM12(float v) {
        return new Float3x3(m00, m01, m02, m10, m11, v, m20, m21, m22);
    }

    /**
     * {@return a copy with the {@code m20} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m20} element
     */
    public Float3x3 withM20(float v) {
        return new Float3x3(m00, m01, m02, m10, m11, m12, v, m21, m22);
    }

    /**
     * {@return a copy with the {@code m21} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m21} element
     */
    public Float3x3 withM21(float v) {
        return new Float3x3(m00, m01, m02, m10, m11, m12, m20, v, m22);
    }

    /**
     * {@return a copy with the {@code m22} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m22} element
     */
    public Float3x3 withM22(float v) {
        return new Float3x3(m00, m01, m02, m10, m11, m12, m20, m21, v);
    }

    /**
     * {@return a copy with the cached property bits replaced by {@code properties}}
     * <p>
     * The bits are trusted as-is and never validated: wrong bits produce wrong results from every
     * dispatched operation. Prefer the {@code make*} factories, or the element constructor, which
     * computes the bits itself. {@code withProperties(determineProperties())} recomputes them from
     * the elements.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param properties the cached property bits, taken as given
     */
    public Float3x3 withProperties(int properties) {
        return new Float3x3(m00, m01, m02, m10, m11, m12, m20, m21, m22, properties);
    }

    @Override public String toString() {
        return "Float3x3(\n    " + m00() + ", " + m01() + ", " + m02() + "\n    " + m10() + ", " + m11() + ", " + m12() + "\n    " + m20() + ", " + m21() + ", " + m22() + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Float3x3)) return false;
        Float3x3 o = (Float3x3) obj;
        return Float.floatToIntBits(m00) == Float.floatToIntBits(o.m00)
            && Float.floatToIntBits(m01) == Float.floatToIntBits(o.m01)
            && Float.floatToIntBits(m02) == Float.floatToIntBits(o.m02)
            && Float.floatToIntBits(m10) == Float.floatToIntBits(o.m10)
            && Float.floatToIntBits(m11) == Float.floatToIntBits(o.m11)
            && Float.floatToIntBits(m12) == Float.floatToIntBits(o.m12)
            && Float.floatToIntBits(m20) == Float.floatToIntBits(o.m20)
            && Float.floatToIntBits(m21) == Float.floatToIntBits(o.m21)
            && Float.floatToIntBits(m22) == Float.floatToIntBits(o.m22);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(m00);
        h = 31 * h + Float.floatToIntBits(m01);
        h = 31 * h + Float.floatToIntBits(m02);
        h = 31 * h + Float.floatToIntBits(m10);
        h = 31 * h + Float.floatToIntBits(m11);
        h = 31 * h + Float.floatToIntBits(m12);
        h = 31 * h + Float.floatToIntBits(m20);
        h = 31 * h + Float.floatToIntBits(m21);
        h = 31 * h + Float.floatToIntBits(m22);
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Float.isFinite(m00)
            && Float.isFinite(m01)
            && Float.isFinite(m02)
            && Float.isFinite(m10)
            && Float.isFinite(m11)
            && Float.isFinite(m12)
            && Float.isFinite(m20)
            && Float.isFinite(m21)
            && Float.isFinite(m22);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Float.isNaN(m00)
            || Float.isNaN(m01)
            || Float.isNaN(m02)
            || Float.isNaN(m10)
            || Float.isNaN(m11)
            || Float.isNaN(m12)
            || Float.isNaN(m20)
            || Float.isNaN(m21)
            || Float.isNaN(m22);
    }

    /**
     * Compare this value component-wise against {@code other}, allowing a difference of at
     * most {@code epsilon} per component.
     * <p>
     * {@code equalsEpsilon} compares per component with a tolerance: an infinite component never
     * compares equal, not even to an equal infinity (the difference {@code Inf - Inf} is NaN), and
     * a NaN component never compares equal to anything.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param other the value to compare against
     * @param epsilon the maximum allowed difference per component
     * @return {@code true} if all components differ by at most {@code epsilon}, {@code false} otherwise
     */
    public boolean equalsEpsilon(Float3x3 other, float epsilon) {
        return java.lang.Math.abs(m00 - other.m00()) <= epsilon
            && java.lang.Math.abs(m01 - other.m01()) <= epsilon
            && java.lang.Math.abs(m02 - other.m02()) <= epsilon
            && java.lang.Math.abs(m10 - other.m10()) <= epsilon
            && java.lang.Math.abs(m11 - other.m11()) <= epsilon
            && java.lang.Math.abs(m12 - other.m12()) <= epsilon
            && java.lang.Math.abs(m20 - other.m20()) <= epsilon
            && java.lang.Math.abs(m21 - other.m21()) <= epsilon
            && java.lang.Math.abs(m22 - other.m22()) <= epsilon;
    }

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final Float3x3SegOps SEG_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float3x3SegOpsUnsafe()
                        : new Float3x3SegOpsMS();
        static final Float3x3BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float3x3BbOpsUnsafe()
                        : new Float3x3BbOpsApi();
        static final Float3x3RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Float3x3RawOpsUnsafe()
                        : new Float3x3RawOpsApi();
    }


    /**
     * Store the elements into the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] storeCM(float[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m20;
        dest[offset + 3] = this.m01;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = this.m21;
        dest[offset + 6] = this.m02;
        dest[offset + 7] = this.m12;
        dest[offset + 8] = this.m22;
        return dest;
    }

    /**
     * Store the elements into the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] storeCM(float[] dest) { return storeCM(dest, 0); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCM(float[] src, int offset) {
        float _c0 = src[offset];
        float _c3 = src[offset + 1];
        float _c6 = src[offset + 2];
        float _c1 = src[offset + 3];
        float _c4 = src[offset + 4];
        float _c7 = src[offset + 5];
        float _c2 = src[offset + 6];
        float _c5 = src[offset + 7];
        float _c8 = src[offset + 8];
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCM(float[] src) { return loadCM(src, 0); }

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
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeCM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }

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
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }

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
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeCMRelative(FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 9);
        return buf;
    }

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
     * @param buf the source buffer
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf);
    }

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
     * @param buf the source buffer
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(index, buf);
    }

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
     * @param buf the source buffer
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadCMRelative(FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x3 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
        buf.position(pos + 9);
        return r;
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }

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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }

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
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 36);
        return buf;
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
     * @param buf the source byte buffer
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf);
    }

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
     * @param buf the source byte buffer
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(index, buf);
    }

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
     * @param buf the source byte buffer
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x3 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
        buf.position(pos + 36);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    public Float3x3 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(address);
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
    public MemorySegment storeCM(MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest); }

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
    public MemorySegment storeCM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest);
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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCM(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(0L, src); }

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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCM(offset, src);
    }


    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code double}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] storeCM(double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m20;
        dest[offset + 3] = this.m01;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = this.m21;
        dest[offset + 6] = this.m02;
        dest[offset + 7] = this.m12;
        dest[offset + 8] = this.m22;
        return dest;
    }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeCM(double[] dest) { return storeCM(dest, 0); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code double}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCM(double[] src, int offset) {
        float _c0 = (float) src[offset];
        float _c3 = (float) src[offset + 1];
        float _c6 = (float) src[offset + 2];
        float _c1 = (float) src[offset + 3];
        float _c4 = (float) src[offset + 4];
        float _c7 = (float) src[offset + 5];
        float _c2 = (float) src[offset + 6];
        float _c5 = (float) src[offset + 7];
        float _c8 = (float) src[offset + 8];
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCM(double[] src) { return loadCM(src, 0); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at its current position (the position is not modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeCM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at the given absolute index (the position is not used or modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at its current position and advancing the position accordingly.
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
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeCMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 9);
        return buf;
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified).
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
     * @param buf the source buffer
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified).
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
     * @param buf the source buffer
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly.
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
     * @param buf the source buffer
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadCMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x3 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
        buf.position(pos + 9);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code double}, starting at its current position (the position is not modified).
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code double}, starting at the given absolute index (the position is not used or
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code double}, starting at its current position and advancing the position accordingly.
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
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 72);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at its current position (the position is not modified).
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
     * @param buf the source byte buffer
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at the given absolute index (the position is not used or
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
     * @param buf the source byte buffer
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at its current position and advancing the position accordingly.
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
     * @param buf the source byte buffer
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadCMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x3 r = StoreLoad.BB_OPS.loadCMDoubleAbsolute(pos, buf);
        buf.position(pos + 72);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, converting each
     * element to {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    public Float3x3 storeCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address in column-major order, converting each
     * element from {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment in column-major order, converting each
     * element to {@code double}.
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
    public MemorySegment storeCMDouble(MemorySegment dest) { return StoreLoad.SEG_OPS.storeCMDouble(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, converting each
     * element to {@code double}, starting at the given offset.
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
    public MemorySegment storeCMDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCMDouble(this, offset, dest);
    }

    /**
     * Load the elements from the given memory segment in column-major order, converting each
     * element from {@code double}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCMDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadCMDouble(0L, src); }

    /**
     * Load the elements from the given memory segment in column-major order, converting each
     * element from {@code double}, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCMDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadCMDouble(offset, src);
    }


    /**
     * Store the elements into the given array in row-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] storeRM(float[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[offset + 3] = this.m10;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = this.m12;
        dest[offset + 6] = this.m20;
        dest[offset + 7] = this.m21;
        dest[offset + 8] = this.m22;
        return dest;
    }

    /**
     * Store the elements into the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] storeRM(float[] dest) { return storeRM(dest, 0); }

    /**
     * Load the elements from the given array in row-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRM(float[] src, int offset) {
        float _c0 = src[offset];
        float _c1 = src[offset + 1];
        float _c2 = src[offset + 2];
        float _c3 = src[offset + 3];
        float _c4 = src[offset + 4];
        float _c5 = src[offset + 5];
        float _c6 = src[offset + 6];
        float _c7 = src[offset + 7];
        float _c8 = src[offset + 8];
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    /**
     * Load the elements from the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRM(float[] src) { return loadRM(src, 0); }

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
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeRM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }

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
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }

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
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeRMRelative(FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 9);
        return buf;
    }

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
     * @param buf the source buffer
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf);
    }

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
     * @param buf the source buffer
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(index, buf);
    }

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
     * @param buf the source buffer
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadRMRelative(FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x3 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
        buf.position(pos + 9);
        return r;
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }

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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }

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
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 36);
        return buf;
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
     * @param buf the source byte buffer
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf);
    }

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
     * @param buf the source byte buffer
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(index, buf);
    }

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
     * @param buf the source byte buffer
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x3 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
        buf.position(pos + 36);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in row-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    public Float3x3 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address in row-major order. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(address);
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
    public MemorySegment storeRM(MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest); }

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
    public MemorySegment storeRM(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest);
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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRM(MemorySegment src) { return StoreLoad.SEG_OPS.loadRM(0L, src); }

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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRM(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRM(offset, src);
    }


    /**
     * Store the elements into the given array in row-major order, converting each element to
     * {@code double}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] storeRM(double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[offset + 3] = this.m10;
        dest[offset + 4] = this.m11;
        dest[offset + 5] = this.m12;
        dest[offset + 6] = this.m20;
        dest[offset + 7] = this.m21;
        dest[offset + 8] = this.m22;
        return dest;
    }

    /**
     * Store the elements into the given array in row-major order, converting each element to
     * {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeRM(double[] dest) { return storeRM(dest, 0); }

    /**
     * Load the elements from the given array in row-major order, converting each element from
     * {@code double}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRM(double[] src, int offset) {
        float _c0 = (float) src[offset];
        float _c1 = (float) src[offset + 1];
        float _c2 = (float) src[offset + 2];
        float _c3 = (float) src[offset + 3];
        float _c4 = (float) src[offset + 4];
        float _c5 = (float) src[offset + 5];
        float _c6 = (float) src[offset + 6];
        float _c7 = (float) src[offset + 7];
        float _c8 = (float) src[offset + 8];
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    /**
     * Load the elements from the given array in row-major order, converting each element from
     * {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRM(double[] src) { return loadRM(src, 0); }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code double}, starting at its current position (the position is not modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeRM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code double}, starting at the given absolute index (the position is not used or modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code double}, starting at its current position and advancing the position accordingly.
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
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeRMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 9);
        return buf;
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified).
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
     * @param buf the source buffer
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified).
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
     * @param buf the source buffer
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly.
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
     * @param buf the source buffer
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadRMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x3 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
        buf.position(pos + 9);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code double}, starting at its current position (the position is not modified).
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code double}, starting at the given absolute index (the position is not used or modified).
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code double}, starting at its current position and advancing the position accordingly.
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
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, pos, buf);
        buf.position(pos + 72);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified).
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
     * @param buf the source byte buffer
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRMDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified).
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
     * @param buf the source byte buffer
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRMDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly.
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
     * @param buf the source byte buffer
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadRMDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x3 r = StoreLoad.BB_OPS.loadRMDoubleAbsolute(pos, buf);
        buf.position(pos + 72);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, converting each
     * element to {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    public Float3x3 storeRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address in row-major order, converting each
     * element from {@code double}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRMDoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(address);
    }

    /**
     * Store the elements into the given memory segment in row-major order, converting each element
     * to {@code double}.
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
    public MemorySegment storeRMDouble(MemorySegment dest) { return StoreLoad.SEG_OPS.storeRMDouble(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in row-major order, converting each element
     * to {@code double}, starting at the given offset.
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
    public MemorySegment storeRMDouble(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRMDouble(this, offset, dest);
    }

    /**
     * Load the elements from the given memory segment in row-major order, converting each element
     * from {@code double}.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRMDouble(MemorySegment src) { return StoreLoad.SEG_OPS.loadRMDouble(0L, src); }

    /**
     * Load the elements from the given memory segment in row-major order, converting each element
     * from {@code double}, starting at the given offset.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param offset the start offset into the memory segment, in bytes
     * @param src the source memory segment
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRMDouble(long offset, MemorySegment src) {
        return StoreLoad.SEG_OPS.loadRMDouble(offset, src);
    }


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
    public float[] storeCM(float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m20;
        dest[_p1] = this.m01;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m21;
        dest[_p2] = this.m02;
        dest[_p2 + 1] = this.m12;
        dest[_p2 + 2] = this.m22;
        return dest;
    }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        float _c0 = src[offset];
        float _c3 = src[offset + 1];
        float _c6 = src[offset + 2];
        float _c1 = src[_p1];
        float _c4 = src[_p1 + 1];
        float _c7 = src[_p1 + 2];
        float _c2 = src[_p2];
        float _c5 = src[_p2 + 1];
        float _c8 = src[_p2 + 2];
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

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
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public FloatBuffer storeCM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
    }

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
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public FloatBuffer storeCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }

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
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeCMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride);
        return buf;
    }

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
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf, stride);
    }

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
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(index, buf, stride);
    }

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
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadCMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x3 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
        buf.position(pos + 3 * stride);
        return r;
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
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public ByteBuffer storeCM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
    }

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
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public ByteBuffer storeCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }

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
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 4);
        return buf;
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
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf, stride);
    }

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
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(index, buf, stride);
    }

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
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x3 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
        buf.position(pos + (3 * stride) * 4);
        return r;
    }

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
    public Float3x3 storeCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride);
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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(address, stride);
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
    public MemorySegment storeCM(MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest, stride); }

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
    public MemorySegment storeCM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride);
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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(0L, src, stride); }

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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCM(offset, src, stride);
    }


    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code double}, starting at the given offset, with {@code stride} elements between the starts
     * of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public double[] storeCM(double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m20;
        dest[_p1] = this.m01;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m21;
        dest[_p2] = this.m02;
        dest[_p2 + 1] = this.m12;
        dest[_p2 + 2] = this.m22;
        return dest;
    }

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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        float _c0 = (float) src[offset];
        float _c3 = (float) src[offset + 1];
        float _c6 = (float) src[offset + 2];
        float _c1 = (float) src[_p1];
        float _c4 = (float) src[_p1 + 1];
        float _c7 = (float) src[_p1 + 2];
        float _c2 = (float) src[_p2];
        float _c5 = (float) src[_p2 + 1];
        float _c8 = (float) src[_p2 + 2];
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at its current position (the position is not modified), with
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
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public DoubleBuffer storeCM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at the given absolute index (the position is not used or modified),
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
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public DoubleBuffer storeCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at its current position and advancing the position accordingly, with
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
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeCMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride);
        return buf;
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified), with
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
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified),
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
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(index, buf, stride);
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly, with
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
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadCMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x3 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
        buf.position(pos + 3 * stride);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code double}, starting at its current position (the position is not modified), with
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
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public ByteBuffer storeCMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code double}, starting at the given absolute index (the position is not used or
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
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public ByteBuffer storeCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, index, buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code double}, starting at its current position and advancing the position accordingly,
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
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 8);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at its current position (the position is not modified), with
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
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at the given absolute index (the position is not used or
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
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMDoubleAbsolute(index, buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code double}, starting at its current position and advancing the position accordingly,
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
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadCMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x3 r = StoreLoad.BB_OPS.loadCMDoubleAbsolute(pos, buf, stride);
        buf.position(pos + (3 * stride) * 8);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, converting each
     * element to {@code double}, with {@code stride} elements between the starts of consecutive
     * columns. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return this
     */
    public Float3x3 storeCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMDoubleUnsafe(this, address, stride);
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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMDoubleUnsafe(address, stride);
    }

    /**
     * Store the elements into the given memory segment in column-major order, converting each
     * element to {@code double}, with {@code stride} elements between the starts of consecutive
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
    public MemorySegment storeCMDouble(MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCMDouble(this, 0L, dest, stride); }

    /**
     * Store the elements into the given memory segment in column-major order, converting each
     * element to {@code double}, starting at the given offset, with {@code stride} elements between
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
    public MemorySegment storeCMDouble(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeCMDouble(this, offset, dest, stride);
    }

    /**
     * Load the elements from the given memory segment in column-major order, converting each
     * element from {@code double}, with {@code stride} elements between the starts of consecutive
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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCMDouble(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCMDouble(0L, src, stride); }

    /**
     * Load the elements from the given memory segment in column-major order, converting each
     * element from {@code double}, starting at the given offset, with {@code stride} elements
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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadCMDouble(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadCMDouble(offset, src, stride);
    }


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
    public float[] storeRM(float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[_p1] = this.m10;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m12;
        dest[_p2] = this.m20;
        dest[_p2 + 1] = this.m21;
        dest[_p2 + 2] = this.m22;
        return dest;
    }

    /**
     * Load the elements from the given array in row-major order, starting at the given offset, with
     * {@code stride} elements between the starts of consecutive rows.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        float _c0 = src[offset];
        float _c1 = src[offset + 1];
        float _c2 = src[offset + 2];
        float _c3 = src[_p1];
        float _c4 = src[_p1 + 1];
        float _c5 = src[_p1 + 2];
        float _c6 = src[_p2];
        float _c7 = src[_p2 + 1];
        float _c8 = src[_p2 + 2];
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

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
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public FloatBuffer storeRM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
    }

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
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public FloatBuffer storeRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }

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
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeRMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride);
        return buf;
    }

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
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf, stride);
    }

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
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(index, buf, stride);
    }

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
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadRMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x3 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
        buf.position(pos + 3 * stride);
        return r;
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
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public ByteBuffer storeRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
    }

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
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public ByteBuffer storeRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }

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
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 4);
        return buf;
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
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRM(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf, stride);
    }

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
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(index, buf, stride);
    }

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
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x3 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
        buf.position(pos + (3 * stride) * 4);
        return r;
    }

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
    public Float3x3 storeRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address, stride);
    }

    /**
     * Load the elements from the given raw memory address in row-major order, with {@code stride}
     * elements between the starts of consecutive rows. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(address, stride);
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
    public MemorySegment storeRM(MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRM(this, 0L, dest, stride); }

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
    public MemorySegment storeRM(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRM(this, offset, dest, stride);
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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRM(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRM(0L, src, stride); }

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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRM(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRM(offset, src, stride);
    }


    /**
     * Store the elements into the given array in row-major order, converting each element to
     * {@code double}, starting at the given offset, with {@code stride} elements between the starts
     * of consecutive rows.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive rows
     * @return dest
     */
    public double[] storeRM(double[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[_p1] = this.m10;
        dest[_p1 + 1] = this.m11;
        dest[_p1 + 2] = this.m12;
        dest[_p2] = this.m20;
        dest[_p2 + 1] = this.m21;
        dest[_p2 + 2] = this.m22;
        return dest;
    }

    /**
     * Load the elements from the given array in row-major order, converting each element from
     * {@code double}, starting at the given offset, with {@code stride} elements between the starts
     * of consecutive rows.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        float _c0 = (float) src[offset];
        float _c1 = (float) src[offset + 1];
        float _c2 = (float) src[offset + 2];
        float _c3 = (float) src[_p1];
        float _c4 = (float) src[_p1 + 1];
        float _c5 = (float) src[_p1 + 2];
        float _c6 = (float) src[_p2];
        float _c7 = (float) src[_p2 + 1];
        float _c8 = (float) src[_p2 + 2];
        return new Float3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code double}, starting at its current position (the position is not modified), with
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
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public DoubleBuffer storeRM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code double}, starting at the given absolute index (the position is not used or modified),
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
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public DoubleBuffer storeRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code double}, starting at its current position and advancing the position accordingly, with
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
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeRMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + 3 * stride);
        return buf;
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified), with
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
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRM(DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified),
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
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(index, buf, stride);
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly, with
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
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadRMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x3 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
        buf.position(pos + 3 * stride);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code double}, starting at its current position (the position is not modified), with
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
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public ByteBuffer storeRMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code double}, starting at the given absolute index (the position is not used or modified),
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
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public ByteBuffer storeRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, index, buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code double}, starting at its current position and advancing the position accordingly, with
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
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMDoubleAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 8);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified), with
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
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRMDouble(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified),
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
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRMDoubleAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMDoubleAbsolute(index, buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly, with
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
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadRMDoubleRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Float3x3 r = StoreLoad.BB_OPS.loadRMDoubleAbsolute(pos, buf, stride);
        buf.position(pos + (3 * stride) * 8);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, converting each
     * element to {@code double}, with {@code stride} elements between the starts of consecutive
     * rows. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive rows
     * @return this
     */
    public Float3x3 storeRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMDoubleUnsafe(this, address, stride);
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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRMDoubleUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMDoubleUnsafe(address, stride);
    }

    /**
     * Store the elements into the given memory segment in row-major order, converting each element
     * to {@code double}, with {@code stride} elements between the starts of consecutive rows.
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
    public MemorySegment storeRMDouble(MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeRMDouble(this, 0L, dest, stride); }

    /**
     * Store the elements into the given memory segment in row-major order, converting each element
     * to {@code double}, starting at the given offset, with {@code stride} elements between the
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
    public MemorySegment storeRMDouble(long offset, MemorySegment dest, int stride) {
        return StoreLoad.SEG_OPS.storeRMDouble(this, offset, dest, stride);
    }

    /**
     * Load the elements from the given memory segment in row-major order, converting each element
     * from {@code double}, with {@code stride} elements between the starts of consecutive rows.
     * <p>
     * With the UNSAFE backend, offsets into direct buffers and native segments are not
     * bounds-checked and segment liveness / thread confinement is not verified; the API backend
     * performs the standard checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source memory segment
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRMDouble(MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadRMDouble(0L, src, stride); }

    /**
     * Load the elements from the given memory segment in row-major order, converting each element
     * from {@code double}, starting at the given offset, with {@code stride} elements between the
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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadRMDouble(long offset, MemorySegment src, int stride) {
        return StoreLoad.SEG_OPS.loadRMDouble(offset, src, stride);
    }


    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] storeCM4x4(float[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m20;
        dest[offset + 3] = 0.0f;
        dest[offset + 4] = this.m01;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = this.m21;
        dest[offset + 7] = 0.0f;
        dest[offset + 8] = this.m02;
        dest[offset + 9] = this.m12;
        dest[offset + 10] = this.m22;
        dest[offset + 11] = 0.0f;
        dest[offset + 12] = 0.0f;
        dest[offset + 13] = 0.0f;
        dest[offset + 14] = 0.0f;
        dest[offset + 15] = 1.0f;
        return dest;
    }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] storeCM4x4(float[] dest) { return storeCM4x4(dest, 0); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at its current position (the position is not modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeCM4x4(FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at the given absolute index (the position is not used or modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeCM4x4Absolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at its current position and advancing the position accordingly.
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
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeCM4x4Relative(FloatBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM4x4Absolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, starting at its current position (the position is not modified).
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM4x4(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, starting at the given absolute index (the position is not used or modified).
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM4x4Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, starting at its current position and advancing the position accordingly.
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
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCM4x4Relative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM4x4Absolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, identity-extended
     * to a 4x4 matrix. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    public Float3x3 storeCM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4Unsafe(this, address);
    }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 4x4 matrix.
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
    public MemorySegment storeCM4x4(MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM4x4(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 4x4 matrix, starting at the given offset.
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
    public MemorySegment storeCM4x4(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM4x4(this, offset, dest);
    }


    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code double}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] storeCM4x4(double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m10;
        dest[offset + 2] = this.m20;
        dest[offset + 3] = 0.0;
        dest[offset + 4] = this.m01;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = this.m21;
        dest[offset + 7] = 0.0;
        dest[offset + 8] = this.m02;
        dest[offset + 9] = this.m12;
        dest[offset + 10] = this.m22;
        dest[offset + 11] = 0.0;
        dest[offset + 12] = 0.0;
        dest[offset + 13] = 0.0;
        dest[offset + 14] = 0.0;
        dest[offset + 15] = 1.0;
        return dest;
    }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeCM4x4(double[] dest) { return storeCM4x4(dest, 0); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code double}, starting at its current position (the
     * position is not modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeCM4x4(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code double}, starting at the given absolute index (the
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
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeCM4x4Absolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code double}, starting at its current position and
     * advancing the position accordingly.
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
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeCM4x4Relative(DoubleBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM4x4Absolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, converting each element to {@code double}, starting at its current position (the
     * position is not modified).
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM4x4Double(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4DoubleAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, converting each element to {@code double}, starting at the given absolute index
     * (the position is not used or modified).
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM4x4DoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4DoubleAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, converting each element to {@code double}, starting at its current position and
     * advancing the position accordingly.
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
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCM4x4DoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 128) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM4x4DoubleAbsolute(this, pos, buf);
        buf.position(pos + 128);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, identity-extended
     * to a 4x4 matrix, converting each element to {@code double}. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    public Float3x3 storeCM4x4DoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4DoubleUnsafe(this, address);
    }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 4x4 matrix, converting each element to {@code double}.
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
    public MemorySegment storeCM4x4Double(MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM4x4Double(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 4x4 matrix, converting each element to {@code double}, starting at the given offset.
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
    public MemorySegment storeCM4x4Double(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeCM4x4Double(this, offset, dest);
    }


    /**
     * Store the elements into the given array in row-major order, identity-extended to a 4x4
     * matrix, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] storeRM4x4(float[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[offset + 3] = 0.0f;
        dest[offset + 4] = this.m10;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = this.m12;
        dest[offset + 7] = 0.0f;
        dest[offset + 8] = this.m20;
        dest[offset + 9] = this.m21;
        dest[offset + 10] = this.m22;
        dest[offset + 11] = 0.0f;
        dest[offset + 12] = 0.0f;
        dest[offset + 13] = 0.0f;
        dest[offset + 14] = 0.0f;
        dest[offset + 15] = 1.0f;
        return dest;
    }

    /**
     * Store the elements into the given array in row-major order, identity-extended to a 4x4
     * matrix.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] storeRM4x4(float[] dest) { return storeRM4x4(dest, 0); }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, starting at its current position (the position is not modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeRM4x4(FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, starting at the given absolute index (the position is not used or modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeRM4x4Absolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, starting at its current position and advancing the position accordingly.
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
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeRM4x4Relative(FloatBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM4x4Absolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 4x4
     * matrix, starting at its current position (the position is not modified).
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM4x4(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 4x4
     * matrix, starting at the given absolute index (the position is not used or modified).
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM4x4Absolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 4x4
     * matrix, starting at its current position and advancing the position accordingly.
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
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRM4x4Relative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM4x4Absolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, identity-extended to
     * a 4x4 matrix. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    public Float3x3 storeRM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4Unsafe(this, address);
    }

    /**
     * Store the elements into the given memory segment in row-major order, identity-extended to a
     * 4x4 matrix.
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
    public MemorySegment storeRM4x4(MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM4x4(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in row-major order, identity-extended to a
     * 4x4 matrix, starting at the given offset.
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
    public MemorySegment storeRM4x4(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM4x4(this, offset, dest);
    }


    /**
     * Store the elements into the given array in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code double}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] storeRM4x4(double[] dest, int offset) {
        dest[offset] = this.m00;
        dest[offset + 1] = this.m01;
        dest[offset + 2] = this.m02;
        dest[offset + 3] = 0.0;
        dest[offset + 4] = this.m10;
        dest[offset + 5] = this.m11;
        dest[offset + 6] = this.m12;
        dest[offset + 7] = 0.0;
        dest[offset + 8] = this.m20;
        dest[offset + 9] = this.m21;
        dest[offset + 10] = this.m22;
        dest[offset + 11] = 0.0;
        dest[offset + 12] = 0.0;
        dest[offset + 13] = 0.0;
        dest[offset + 14] = 0.0;
        dest[offset + 15] = 1.0;
        return dest;
    }

    /**
     * Store the elements into the given array in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeRM4x4(double[] dest) { return storeRM4x4(dest, 0); }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code double}, starting at its current position (the
     * position is not modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeRM4x4(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code double}, starting at the given absolute index (the
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
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeRM4x4Absolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code double}, starting at its current position and
     * advancing the position accordingly.
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
     * @param buf the destination buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeRM4x4Relative(DoubleBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM4x4Absolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code double}, starting at its current position (the
     * position is not modified).
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM4x4Double(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4DoubleAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code double}, starting at the given absolute index (the
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM4x4DoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4DoubleAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code double}, starting at its current position and
     * advancing the position accordingly.
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
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRM4x4DoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 128) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM4x4DoubleAbsolute(this, pos, buf);
        buf.position(pos + 128);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, identity-extended to
     * a 4x4 matrix, converting each element to {@code double}. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    public Float3x3 storeRM4x4DoubleUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4DoubleUnsafe(this, address);
    }

    /**
     * Store the elements into the given memory segment in row-major order, identity-extended to a
     * 4x4 matrix, converting each element to {@code double}.
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
    public MemorySegment storeRM4x4Double(MemorySegment dest) { return StoreLoad.SEG_OPS.storeRM4x4Double(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in row-major order, identity-extended to a
     * 4x4 matrix, converting each element to {@code double}, starting at the given offset.
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
    public MemorySegment storeRM4x4Double(long offset, MemorySegment dest) {
        return StoreLoad.SEG_OPS.storeRM4x4Double(this, offset, dest);
    }


    /**
     * Store the elements into the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] store(float[] dest) { return storeCM(dest, 0); }

    /**
     * Store the elements into the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] store(float[] dest, int offset) { return storeCM(dest, offset); }

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
    public FloatBuffer store(FloatBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, dest.position(), dest); }

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
    public FloatBuffer store(int index, FloatBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest); }

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
    public FloatBuffer storeRelative(FloatBuffer dest) { return storeCMRelative(dest); }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store(double[] dest) { return storeCM(dest, 0); }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code double}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] store(double[] dest, int offset) { return storeCM(dest, offset); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at its current position (the position is not modified).
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
    public DoubleBuffer store(DoubleBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, dest.position(), dest); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at the given absolute index (the position is not used or modified).
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
    public DoubleBuffer store(int index, DoubleBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at its current position and advancing the position accordingly.
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
    public DoubleBuffer storeRelative(DoubleBuffer dest) { return storeCMRelative(dest); }

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
    public ByteBuffer store(ByteBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, dest.position(), dest); }

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
    public ByteBuffer store(int index, ByteBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest); }

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
    public ByteBuffer storeRelative(ByteBuffer dest) { return storeCMRelative(dest); }

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
    public MemorySegment store(MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM(this, 0L, dest); }

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
    public MemorySegment store(long offset, MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM(this, offset, dest); }

    /**
     * Store the elements into the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    public Float3x3 storeUnsafe(long address) { return StoreLoad.RAW_OPS.storeCMUnsafe(this, address); }

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
    public float[] store(float[] dest, int offset, int stride) { return storeCM(dest, offset, stride); }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code double}, starting at the given offset, with {@code stride} elements between the starts
     * of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public double[] store(double[] dest, int offset, int stride) { return storeCM(dest, offset, stride); }

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
    public FloatBuffer store(int index, FloatBuffer dest, int stride) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest, stride); }

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
    public FloatBuffer storeRelative(FloatBuffer dest, int stride) { return storeCMRelative(dest, stride); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at the given absolute index (the position is not used or modified),
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
    public DoubleBuffer store(int index, DoubleBuffer dest, int stride) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest, stride); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code double}, starting at its current position and advancing the position accordingly, with
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
    public DoubleBuffer storeRelative(DoubleBuffer dest, int stride) { return storeCMRelative(dest, stride); }

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
    public ByteBuffer store(int index, ByteBuffer dest, int stride) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest, stride); }

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
    public ByteBuffer storeRelative(ByteBuffer dest, int stride) { return storeCMRelative(dest, stride); }

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
    public MemorySegment store(long offset, MemorySegment dest, int stride) { return StoreLoad.SEG_OPS.storeCM(this, offset, dest, stride); }

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
    public Float3x3 storeUnsafe(long address, int stride) { return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride); }

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 load(float[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 load(float[] src, int offset) { return loadCM(src, offset); }

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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 load(FloatBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 load(int index, FloatBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

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
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadRelative(FloatBuffer src) { return loadCMRelative(src); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 load(double[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code double}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 load(double[] src, int offset) { return loadCM(src, offset); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at its current position (the position is not modified).
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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 load(DoubleBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified).
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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 load(int index, DoubleBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly.
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
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadRelative(DoubleBuffer src) { return loadCMRelative(src); }

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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 load(ByteBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 load(int index, ByteBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

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
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadRelative(ByteBuffer src) { return loadCMRelative(src); }

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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 load(MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(0L, src); }

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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 load(long offset, MemorySegment src) { return StoreLoad.SEG_OPS.loadCM(offset, src); }

    /**
     * Load the elements from the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadUnsafe(long address) { return StoreLoad.RAW_OPS.loadCMUnsafe(address); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 load(float[] src, int offset, int stride) { return loadCM(src, offset, stride); }

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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 load(double[] src, int offset, int stride) { return loadCM(src, offset, stride); }

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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 load(int index, FloatBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

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
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadRelative(FloatBuffer src, int stride) { return loadCMRelative(src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at the given absolute index (the position is not used or modified),
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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 load(int index, DoubleBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code double}, starting at its current position and advancing the position accordingly, with
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
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadRelative(DoubleBuffer src, int stride) { return loadCMRelative(src, stride); }

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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 load(int index, ByteBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

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
     * @return a new {@code Float3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Float3x3 loadRelative(ByteBuffer src, int stride) { return loadCMRelative(src, stride); }

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
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 load(long offset, MemorySegment src, int stride) { return StoreLoad.SEG_OPS.loadCM(offset, src, stride); }

    /**
     * Load the elements from the given raw memory address in column-major order, with
     * {@code stride} elements between the starts of consecutive columns. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Float3x3} holding the loaded elements
     */
    public static Float3x3 loadUnsafe(long address, int stride) { return StoreLoad.RAW_OPS.loadCMUnsafe(address, stride); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] store4x4(float[] dest) { return storeCM4x4(dest, 0); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] store4x4(float[] dest, int offset) { return storeCM4x4(dest, offset); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at its current position (the position is not modified).
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
    public FloatBuffer store4x4(FloatBuffer dest) { return StoreLoad.BB_OPS.storeCM4x4Absolute(this, dest.position(), dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at the given absolute index (the position is not used or modified).
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
    public FloatBuffer store4x4(int index, FloatBuffer dest) { return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at its current position and advancing the position accordingly.
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
    public FloatBuffer store4x4Relative(FloatBuffer dest) { return storeCM4x4Relative(dest); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code double}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store4x4(double[] dest) { return storeCM4x4(dest, 0); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code double}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] store4x4(double[] dest, int offset) { return storeCM4x4(dest, offset); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code double}, starting at its current position (the
     * position is not modified).
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
    public DoubleBuffer store4x4(DoubleBuffer dest) { return StoreLoad.BB_OPS.storeCM4x4Absolute(this, dest.position(), dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code double}, starting at the given absolute index (the
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
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    public DoubleBuffer store4x4(int index, DoubleBuffer dest) { return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code double}, starting at its current position and
     * advancing the position accordingly.
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
    public DoubleBuffer store4x4Relative(DoubleBuffer dest) { return storeCM4x4Relative(dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, starting at its current position (the position is not modified).
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
    public ByteBuffer store4x4(ByteBuffer dest) { return StoreLoad.BB_OPS.storeCM4x4Absolute(this, dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, starting at the given absolute index (the position is not used or modified).
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
    public ByteBuffer store4x4(int index, ByteBuffer dest) { return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, starting at its current position and advancing the position accordingly.
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
    public ByteBuffer store4x4Relative(ByteBuffer dest) { return storeCM4x4Relative(dest); }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 4x4 matrix.
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
    public MemorySegment store4x4(MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM4x4(this, 0L, dest); }

    /**
     * Store the elements into the given memory segment in column-major order, identity-extended to
     * a 4x4 matrix, starting at the given offset.
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
    public MemorySegment store4x4(long offset, MemorySegment dest) { return StoreLoad.SEG_OPS.storeCM4x4(this, offset, dest); }

    /**
     * Store the elements into the given raw memory address in column-major order, identity-extended
     * to a 4x4 matrix. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     */
    public Float3x3 store4x4Unsafe(long address) { return StoreLoad.RAW_OPS.storeCM4x4Unsafe(this, address); }

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
}
