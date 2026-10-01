// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2;

import org.joml2.internal.storeload.*;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * Immutable 3x3 matrix of double-precision {@code double} components.
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
 * {@code Double.doubleToLongBits}: {@code 0.0} and {@code -0.0} are not equal, and NaN is equal to
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
public record Double3x3(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, int properties) {

    /** The number of bytes one instance occupies in the natural {@code store}/{@code load} layout. */
    public static final int BYTES = 72;

    /** The number of rows - the tight stride of the column-major ({@code storeCM}/{@code loadCM}) strided overloads. */
    public static final int ROWS = 3;
    /** The number of columns - the tight stride of the row-major ({@code storeRM}/{@code loadRM}) strided overloads. */
    public static final int COLUMNS = 3;

    /** The zero matrix (all components 0). */
    public static final Double3x3 ZERO = new Double3x3(0, 0, 0, 0, 0, 0, 0, 0, 0);

    /** The identity matrix. */
    public static final Double3x3 IDENTITY = new Double3x3();

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
    public Double3x3(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, int properties) {
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
    public Double3x3() {
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
    public Double3x3(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
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
    public Double3x3(Double3 c0, Double3 c1, Double3 c2) {
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
    public Double3x3(Double3 c0, Double3 c1, Double3 c2, int properties) {
        this(c0.x(), c1.x(), c2.x(), c0.y(), c1.y(), c2.y(), c0.z(), c1.z(), c2.z(), properties);
    }

    /**
     * Create a matrix by identity-extending {@code src} to this square shape.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public Double3x3(Double2x3 src) {
        this(src.m00(), src.m01(), src.m02(), src.m10(), src.m11(), src.m12(), 0, 0, 1);
    }

    /**
     * Create a matrix by truncating {@code src} to the overlapping cells.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public Double3x3(Double4x4 src) {
        this(src.m00(), src.m01(), src.m02(), src.m10(), src.m11(), src.m12(), src.m20(), src.m21(), src.m22());
    }

    /**
     * Create a matrix by truncating {@code src} to the overlapping cells.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public Double3x3(Double3x4 src) {
        this(src.m00(), src.m01(), src.m02(), src.m10(), src.m11(), src.m12(), src.m20(), src.m21(), src.m22());
    }

    /**
     * Create a matrix by identity-extending {@code src} to this square shape.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the matrix to convert
     */
    public Double3x3(Double2x2 src) {
        this(src.m00(), src.m01(), 0, src.m10(), src.m11(), 0, 0, 0, 1);
    }

    /** {@return the element in row 0, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public double m00() { return m00; }
    /** {@return the element in row 0, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public double m01() { return m01; }
    /** {@return the element in row 0, column 2} <p>Valid input: any value, NaN and the infinities included. */
    public double m02() { return m02; }
    /** {@return the element in row 1, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public double m10() { return m10; }
    /** {@return the element in row 1, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public double m11() { return m11; }
    /** {@return the element in row 1, column 2} <p>Valid input: any value, NaN and the infinities included. */
    public double m12() { return m12; }
    /** {@return the element in row 2, column 0} <p>Valid input: any value, NaN and the infinities included. */
    public double m20() { return m20; }
    /** {@return the element in row 2, column 1} <p>Valid input: any value, NaN and the infinities included. */
    public double m21() { return m21; }
    /** {@return the element in row 2, column 2} <p>Valid input: any value, NaN and the infinities included. */
    public double m22() { return m22; }
    /** {@return the cached structural property bits} <p>Valid input: any value, NaN and the infinities included. */
    public int properties() { return properties; }

    private static int props(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
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
    private Double3 getColumn_identity(int col) {
        return switch (col) {
            case 0 -> new Double3(1.0, 0.0, 0.0);
            case 1 -> new Double3(0.0, 1.0, 0.0);
            case 2 -> new Double3(0.0, 0.0, 1.0);
            default -> throw new IndexOutOfBoundsException("Index out of range: " + col);
        };
    }


    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties; reached only
     * through the public {@code getColumn} dispatcher.
     */
    private Double3 getColumn_translation(int col) {
        return switch (col) {
            case 0 -> new Double3(1.0, 0.0, 0.0);
            case 1 -> new Double3(0.0, 1.0, 0.0);
            case 2 -> new Double3(this.m02, this.m12, 1.0);
            default -> throw new IndexOutOfBoundsException("Index out of range: " + col);
        };
    }


    /**
     * Private body of {@code getColumn}, specialized by runtime matrix properties; reached only
     * through the public {@code getColumn} dispatcher.
     */
    private Double3 getColumn_general(int col) {
        return switch (col) {
            case 0 -> new Double3(this.m00, this.m10, this.m20);
            case 1 -> new Double3(this.m01, this.m11, this.m21);
            case 2 -> new Double3(this.m02, this.m12, this.m22);
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
    public Double3 getColumn(int col) {
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
    private Double3 getEulerAnglesXYZ_identity() {
        return Double3.ZERO;
    }


    /**
     * Private body of {@code getEulerAnglesXYZ}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesXYZ} dispatcher.
     */
    private Double3 getEulerAnglesXYZ_translation() {
        double _t0 = Math.fma(this.m12, this.m12, 1.0);
        return new Double3(_t0 < Math.fma(this.m12, this.m12, Math.fma(this.m02, this.m02, 1.0)) * 1.0E-15 ? 0.0 : Math.atan2(-this.m12, 1.0), Math.atan2(this.m02, java.lang.Math.sqrt(_t0)), 0.0);
    }


    /**
     * Private body of {@code getEulerAnglesXYZ}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesXYZ} dispatcher.
     */
    private Double3 getEulerAnglesXYZ_general() {
        double _t1 = Math.fma(this.m12, this.m12, this.m22 * this.m22);
        if (_t1 < Math.fma(this.m02, this.m02, _t1) * 1.0E-15) {
            return new Double3(Math.atan2(this.m21, this.m11), Math.atan2(this.m02, java.lang.Math.sqrt(_t1)), 0.0);
        } else {
            return new Double3(Math.atan2(-this.m12, this.m22), Math.atan2(this.m02, java.lang.Math.sqrt(_t1)), Math.atan2(-this.m01, this.m00));
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
     * {@code double} resolution over its whole range, down to 0.
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
    public Double3 getEulerAnglesXYZ() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getEulerAnglesXYZ_identity();
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getEulerAnglesXYZ_translation();
        return getEulerAnglesXYZ_general();
    }


    /**
     * Private body of {@code getEulerAnglesXZY}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesXZY} dispatcher.
     */
    private Double3 getEulerAnglesXZY_translation() {
        return new Double3(0.0, Math.atan2(this.m02, 1.0), 0.0);
    }


    /**
     * Private body of {@code getEulerAnglesXZY}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesXZY} dispatcher.
     */
    private Double3 getEulerAnglesXZY_general() {
        double _t1 = Math.fma(this.m11, this.m11, this.m21 * this.m21);
        if (_t1 < Math.fma(this.m01, this.m01, _t1) * 1.0E-15) {
            return new Double3(Math.atan2(-this.m12, this.m22), 0.0, Math.atan2(-this.m01, java.lang.Math.sqrt(_t1)));
        } else {
            return new Double3(Math.atan2(this.m21, this.m11), Math.atan2(this.m02, this.m00), Math.atan2(-this.m01, java.lang.Math.sqrt(_t1)));
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
     * {@code double} resolution over its whole range, down to 0.
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
    public Double3 getEulerAnglesXZY() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getEulerAnglesXYZ_identity();
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getEulerAnglesXZY_translation();
        return getEulerAnglesXZY_general();
    }


    /**
     * Private body of {@code getEulerAnglesYXZ}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesYXZ} dispatcher.
     */
    private Double3 getEulerAnglesYXZ_translation() {
        double _t0 = Math.fma(this.m02, this.m02, 1.0);
        return new Double3(Math.atan2(-this.m12, java.lang.Math.sqrt(_t0)), _t0 < Math.fma(this.m02, this.m02, Math.fma(this.m12, this.m12, 1.0)) * 1.0E-15 ? 0.0 : Math.atan2(this.m02, 1.0), 0.0);
    }


    /**
     * Private body of {@code getEulerAnglesYXZ}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesYXZ} dispatcher.
     */
    private Double3 getEulerAnglesYXZ_general() {
        double _t1 = Math.fma(this.m02, this.m02, this.m22 * this.m22);
        if (_t1 < Math.fma(this.m12, this.m12, _t1) * 1.0E-15) {
            return new Double3(Math.atan2(-this.m12, java.lang.Math.sqrt(_t1)), Math.atan2(-this.m20, this.m00), 0.0);
        } else {
            return new Double3(Math.atan2(-this.m12, java.lang.Math.sqrt(_t1)), Math.atan2(this.m02, this.m22), Math.atan2(this.m10, this.m11));
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
     * {@code double} resolution over its whole range, down to 0.
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
    public Double3 getEulerAnglesYXZ() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getEulerAnglesXYZ_identity();
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getEulerAnglesYXZ_translation();
        return getEulerAnglesYXZ_general();
    }


    /**
     * Private body of {@code getEulerAnglesYZX}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesYZX} dispatcher.
     */
    private Double3 getEulerAnglesYZX_translation() {
        return new Double3(Math.atan2(-this.m12, 1.0), 0.0, 0.0);
    }


    /**
     * Private body of {@code getEulerAnglesYZX}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesYZX} dispatcher.
     */
    private Double3 getEulerAnglesYZX_general() {
        double _t1 = Math.fma(this.m11, this.m11, this.m12 * this.m12);
        if (_t1 < Math.fma(this.m10, this.m10, _t1) * 1.0E-15) {
            return new Double3(0.0, Math.atan2(this.m02, this.m22), Math.atan2(this.m10, java.lang.Math.sqrt(_t1)));
        } else {
            return new Double3(Math.atan2(-this.m12, this.m11), Math.atan2(-this.m20, this.m00), Math.atan2(this.m10, java.lang.Math.sqrt(_t1)));
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
     * {@code double} resolution over its whole range, down to 0.
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
    public Double3 getEulerAnglesYZX() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getEulerAnglesXYZ_identity();
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getEulerAnglesYZX_translation();
        return getEulerAnglesYZX_general();
    }


    /**
     * Private body of {@code getEulerAnglesZXY}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesZXY} dispatcher.
     */
    private Double3 getEulerAnglesZXY_affine() {
        return new Double3(Math.atan2(0.0, java.lang.Math.sqrt(Math.fma(this.m01, this.m01, this.m11 * this.m11))), 0.0, Math.atan2(-this.m01, this.m11));
    }


    /**
     * Private body of {@code getEulerAnglesZXY}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesZXY} dispatcher.
     */
    private Double3 getEulerAnglesZXY_general() {
        double _t1 = Math.fma(this.m01, this.m01, this.m11 * this.m11);
        if (_t1 < Math.fma(this.m21, this.m21, _t1) * 1.0E-15) {
            return new Double3(Math.atan2(this.m21, java.lang.Math.sqrt(_t1)), 0.0, Math.atan2(this.m10, this.m00));
        } else {
            return new Double3(Math.atan2(this.m21, java.lang.Math.sqrt(_t1)), Math.atan2(-this.m20, this.m22), Math.atan2(-this.m01, this.m11));
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
     * {@code double} resolution over its whole range, down to 0.
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
    public Double3 getEulerAnglesZXY() {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getEulerAnglesXYZ_identity();
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double3(0.0, 0.0, Math.atan2(-this.m01, this.m11));
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return getEulerAnglesZXY_affine();
        return getEulerAnglesZXY_general();
    }


    /**
     * Private body of {@code getEulerAnglesZYX}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesZYX} dispatcher.
     */
    private Double3 getEulerAnglesZYX_orthogonal() {
        return new Double3(0.0, 0.0, Math.atan2(this.m10, this.m00));
    }


    /**
     * Private body of {@code getEulerAnglesZYX}, specialized by runtime matrix properties; reached
     * only through the public {@code getEulerAnglesZYX} dispatcher.
     */
    private Double3 getEulerAnglesZYX_general() {
        double _t1 = Math.fma(this.m21, this.m21, this.m22 * this.m22);
        if (_t1 < Math.fma(this.m20, this.m20, _t1) * 1.0E-15) {
            return new Double3(0.0, Math.atan2(-this.m20, java.lang.Math.sqrt(_t1)), Math.atan2(-this.m01, this.m11));
        } else {
            return new Double3(Math.atan2(this.m21, this.m22), Math.atan2(-this.m20, java.lang.Math.sqrt(_t1)), Math.atan2(this.m10, this.m00));
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
     * {@code double} resolution over its whole range, down to 0.
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
    public Double3 getEulerAnglesZYX() {
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
    private DoubleQuat getNormalizedRotation_identity() {
        return new DoubleQuat(0.0, 0.0, 0.0, 1.0);
    }


    /**
     * Private body of {@code getNormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getNormalizedRotation} dispatcher.
     */
    private DoubleQuat getNormalizedRotation_translation() {
        double _t2 = (1.0 / java.lang.Math.sqrt(Math.fma(this.m02, this.m02, Math.fma(this.m12, this.m12, 1.0))));
        double _t4 = 1.0 + (2.0 + _t2);
        double _sp1 = (1.0 / java.lang.Math.sqrt(_t4)) * 0.5 * _t2;
        return new DoubleQuat(-(_sp1 * this.m12), _sp1 * this.m02, 0.0, 0.5 * java.lang.Math.sqrt(_t4));
    }


    /**
     * Private body of {@code getNormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getNormalizedRotation} dispatcher.
     */
    private DoubleQuat getNormalizedRotation_general() {
        double _t6 = Math.fma(this.m21, this.m21, Math.fma(this.m01, this.m01, this.m11 * this.m11));
        double _t7 = Math.fma(this.m22, this.m22, Math.fma(this.m02, this.m02, this.m12 * this.m12));
        double _t8 = Math.fma(this.m20, this.m20, Math.fma(this.m00, this.m00, this.m10 * this.m10));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t6));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t11 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t21, _t23, _t27;
        if (_t6 != 0.0) {
            _t21 = this.m01 * _t9;
            _t23 = this.m11 * _t9;
            _t27 = this.m21 * _t9;
        } else {
            _t21 = 0.0;
            _t23 = 0.0;
            _t27 = 0.0;
        }
        double _t22, _t24, _t26;
        if (_t7 != 0.0) {
            _t22 = this.m12 * _t10;
            _t24 = this.m02 * _t10;
            _t26 = this.m22 * _t10;
        } else {
            _t22 = 0.0;
            _t24 = 0.0;
            _t26 = 0.0;
        }
        double _t25, _t28, _t29;
        if (_t8 != 0.0) {
            _t25 = this.m20 * _t11;
            _t28 = this.m00 * _t11;
            _t29 = this.m10 * _t11;
        } else {
            _t25 = 0.0;
            _t28 = 0.0;
            _t29 = 0.0;
        }
        return getNormalizedRotation_general_seaf28661_1(_t21, _t23, _t27, _t22, _t24, _t26, _t25, _t28, _t29, _t27 - _t22, _t27 + _t22);
    }

    /** Piece 2 of {@code getNormalizedRotation_general}, split to fit the inline budget; reached only through it. */
    private DoubleQuat getNormalizedRotation_general_seaf28661_1(double _t21, double _t23, double _t27, double _t22, double _t24, double _t26, double _t25, double _t28, double _t29, double _t36, double _t39) {
        double _t49, _t50, _t51;
        if (Math.fma(Math.fma(_t21, _t22, -(_t23 * _t24)), _t25, Math.fma(Math.fma(_t23, _t26, -(_t27 * _t22)), _t28, Math.fma(_t27, _t24, -(_t21 * _t26)) * _t29)) < 0.0) {
            _t49 = -_t28;
            _t50 = -_t29;
            _t51 = -_t25;
        } else {
            _t49 = _t28;
            _t50 = _t29;
            _t51 = _t25;
        }
        double _t52 = _t49 + _t23;
        double _t58 = _t52 + _t26;
        double _t62 = 1.0 + _t58;
        double _t63 = 1.0 + (_t49 - (_t23 + _t26));
        double _t64 = 1.0 + (_t23 - (_t49 + _t26));
        double _t65 = 1.0 + (_t26 - _t52);
        return getNormalizedRotation_general_seaf28661_2(_t23, _t26, _t36, _t39, _t49, _t50 + _t21, _t51 + _t24, _t24 - _t51, _t50 - _t21, _t58, _t62, _t63, _t64, _t65, 0.5 * (1.0 / java.lang.Math.sqrt(_t62)), 0.5 * (1.0 / java.lang.Math.sqrt(_t64)), 0.5 * (1.0 / java.lang.Math.sqrt(_t65)), 0.5 * (1.0 / java.lang.Math.sqrt(_t63)));
    }

    /** Piece 3 of {@code getNormalizedRotation_general}, split to fit the inline budget; reached only through it. */
    private DoubleQuat getNormalizedRotation_general_seaf28661_2(double _t23, double _t26, double _t36, double _t39, double _t49, double _t53, double _t55, double _t56, double _t57, double _t58, double _t62, double _t63, double _t64, double _t65, double _sp0, double _sp1, double _sp2, double _sp3) {
        double _sfx0, _sfx1, _sfx2, _sfx3;
        if (_t58 > 0.0) {
            _sfx0 = _sp0 * _t36;
            _sfx1 = _sp0 * _t56;
            _sfx2 = _sp0 * _t57;
            _sfx3 = 0.5 * java.lang.Math.sqrt(_t62);
        } else {
            if (_t49 > java.lang.Math.max(_t23, _t26)) {
                _sfx0 = 0.5 * java.lang.Math.sqrt(_t63);
                _sfx1 = _sp3 * _t53;
                _sfx2 = _sp3 * _t55;
                _sfx3 = _sp3 * _t36;
            } else {
                if (_t23 > _t26) {
                    _sfx0 = _sp1 * _t53;
                    _sfx1 = 0.5 * java.lang.Math.sqrt(_t64);
                    _sfx2 = _sp1 * _t39;
                    _sfx3 = _sp1 * _t56;
                } else {
                    _sfx0 = _sp2 * _t55;
                    _sfx1 = _sp2 * _t39;
                    _sfx2 = 0.5 * java.lang.Math.sqrt(_t65);
                    _sfx3 = _sp2 * _t57;
                }
            }
        }
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }


    /**
     * Extract the rotation of this matrix as a quaternion, column-normalizing the linear block
     * first to strip scale (skew is not removed: a sheared block yields a quaternion that is not
     * unit length), returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting quaternion
     */
    public DoubleQuat getNormalizedRotation() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getNormalizedRotation_identity();
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getNormalizedRotation_translation();
        return getNormalizedRotation_general();
    }


    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Double3 getRow_translation(int row) {
        return switch (row) {
            case 0 -> new Double3(1.0, 0.0, this.m02);
            case 1 -> new Double3(0.0, 1.0, this.m12);
            case 2 -> new Double3(0.0, 0.0, 1.0);
            default -> throw new IndexOutOfBoundsException("Index out of range: " + row);
        };
    }


    /**
     * Private body of {@code getRow}, specialized by runtime matrix properties; reached only
     * through the public {@code getRow} dispatcher.
     */
    private Double3 getRow_general(int row) {
        return switch (row) {
            case 0 -> new Double3(this.m00, this.m01, this.m02);
            case 1 -> new Double3(this.m10, this.m11, this.m12);
            case 2 -> new Double3(this.m20, this.m21, this.m22);
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
    public Double3 getRow(int row) {
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
    private Double3 getScale_identity() {
        return new Double3(1.0, 1.0, 1.0);
    }


    /**
     * Private body of {@code getScale}, specialized by runtime matrix properties; reached only
     * through the public {@code getScale} dispatcher.
     */
    private Double3 getScale_translation() {
        return new Double3(1.0, 1.0, java.lang.Math.sqrt(Math.fma(this.m02, this.m02, Math.fma(this.m12, this.m12, 1.0))));
    }


    /**
     * Private body of {@code getScale}, specialized by runtime matrix properties; reached only
     * through the public {@code getScale} dispatcher.
     */
    private Double3 getScale_general() {
        return new Double3(java.lang.Math.sqrt(Math.fma(this.m20, this.m20, Math.fma(this.m00, this.m00, this.m10 * this.m10))), java.lang.Math.sqrt(Math.fma(this.m21, this.m21, Math.fma(this.m01, this.m01, this.m11 * this.m11))), java.lang.Math.sqrt(Math.fma(this.m22, this.m22, Math.fma(this.m02, this.m02, this.m12 * this.m12))));
    }


    /**
     * Get the scaling factors of this matrix, as the lengths of its basis columns (always
     * non-negative; skew is ignored), returning the result as a value.
     * <p>
     * For a 2D homogeneous 3x3 matrix the third factor is simply the length of the third column -
     * {@code sqrt(m02² + m12² + 1)} for a 2D affine transform, not a scale of anything.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting vector
     */
    public Double3 getScale() {
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
    public Double2 getTranslation() {
        if ((this.properties & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return Double2.ZERO;
        return new Double2(this.m02, this.m12);
    }

    /** Private tail of {@code getUnnormalizedRotation_orthogonal}; reached only through it. */
    private DoubleQuat getUnnormalizedRotation_orthogonal_s0_tail(double _t11, double _t6, double _sp0, double _t14, double _t0, double _sp2, double _t15, double _sp1, double _t16, double _t12, double _sp3, double _t13, double _t10) {
        double _t17 = (1.0 / java.lang.Math.sqrt(_t11));
        if (_t6 > 0.0) {
            return new DoubleQuat(-(_sp0 * _t14), _sp1 * _t14, _sp3 * _t14, 0.5 * java.lang.Math.sqrt(_t10));
        } else {
            if (this.m00 > _t0) {
                return new DoubleQuat(0.5 * java.lang.Math.sqrt(_t11), _sp2 * _t17, _sp1 * _t17, -(_sp0 * _t17));
            } else {
                if (this.m11 > 1.0) {
                    return new DoubleQuat(_sp2 * _t15, 0.5 * java.lang.Math.sqrt(_t12), _sp0 * _t15, _sp1 * _t15);
                } else {
                    return new DoubleQuat(_sp1 * _t16, _sp0 * _t16, 0.5 * java.lang.Math.sqrt(_t13), _sp3 * _t16);
                }
            }
        }
    }


    /**
     * Private body of {@code getUnnormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getUnnormalizedRotation} dispatcher.
     */
    private DoubleQuat getUnnormalizedRotation_orthogonal() {
        double _t3 = this.m00 + this.m11;
        double _t6 = 1.0 + _t3;
        double _t10 = 1.0 + _t6;
        double _t12 = 1.0 + (this.m11 - (1.0 + this.m00));
        double _t13 = 1.0 + (1.0 - _t3);
        return getUnnormalizedRotation_orthogonal_s0_tail(1.0 + (this.m00 - (1.0 + this.m11)), _t6, 0.5 * this.m12, (1.0 / java.lang.Math.sqrt(_t10)), java.lang.Math.max(this.m11, 1.0), 0.5 * (this.m01 + this.m10), (1.0 / java.lang.Math.sqrt(_t12)), 0.5 * this.m02, (1.0 / java.lang.Math.sqrt(_t13)), _t12, 0.5 * (this.m10 - this.m01), _t13, _t10);
    }

    /** Private tail of {@code getUnnormalizedRotation_general}; reached only through it. */
    private DoubleQuat getUnnormalizedRotation_general_s0_tail(double _t10, double _sp0, double _t1, double _t2, double _t15, double _sp1, double _t4, double _sp2, double _t6, double _t7, double _sp3, double _t16, double _t8, double _t9, double _t17, double _t14) {
        double _sfx0, _sfx1, _sfx2;
        if (_t10 > 0.0) {
            _sfx0 = _sp0 * _t1;
            _sfx1 = _sp0 * _t7;
            _sfx2 = _sp0 * _t9;
        } else {
            if (this.m00 > _t2) {
                _sfx0 = 0.5 * java.lang.Math.sqrt(_t15);
                _sfx1 = _sp3 * _t4;
                _sfx2 = _sp3 * _t6;
            } else {
                if (this.m11 > this.m22) {
                    _sfx0 = _sp1 * _t4;
                    _sfx1 = 0.5 * java.lang.Math.sqrt(_t16);
                    _sfx2 = _sp1 * _t8;
                } else {
                    _sfx0 = _sp2 * _t6;
                    _sfx1 = _sp2 * _t8;
                    _sfx2 = 0.5 * java.lang.Math.sqrt(_t17);
                }
            }
        }
        return getUnnormalizedRotation_general_s0_tail2(_t10, _t14, _t2, _sp3, _t1, _sp1, _t7, _sp2, _t9, _sfx0, _sfx1, _sfx2);
    }

    /** Private tail of {@code getUnnormalizedRotation_general}; reached only through it. */
    private DoubleQuat getUnnormalizedRotation_general_s0_tail2(double _t10, double _t14, double _t2, double _sp3, double _t1, double _sp1, double _t7, double _sp2, double _t9, double _sfx0, double _sfx1, double _sfx2) {
        double _sfx3 = _t10 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t14) : this.m00 > _t2 ? _sp3 * _t1 : this.m11 > this.m22 ? _sp1 * _t7 : _sp2 * _t9;
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }


    /**
     * Private body of {@code getUnnormalizedRotation}, specialized by runtime matrix properties;
     * reached only through the public {@code getUnnormalizedRotation} dispatcher.
     */
    private DoubleQuat getUnnormalizedRotation_general() {
        double _t0 = this.m00 + this.m11;
        double _t10 = this.m22 + _t0;
        double _t14 = 1.0 + _t10;
        double _t15 = 1.0 + (this.m00 - (this.m11 + this.m22));
        double _t16 = 1.0 + (this.m11 - (this.m00 + this.m22));
        double _t17 = 1.0 + (this.m22 - _t0);
        return getUnnormalizedRotation_general_s0_tail(_t10, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), this.m21 - this.m12, java.lang.Math.max(this.m11, this.m22), _t15, 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), this.m01 + this.m10, 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), this.m02 + this.m20, this.m02 - this.m20, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), _t16, this.m12 + this.m21, this.m10 - this.m01, _t17, _t14);
    }


    /**
     * Extract the rotation of this matrix as a quaternion directly from the linear block, without
     * normalizing it, returning the result as a value.
     * <p>
     * Valid input: this matrix must be a rotation matrix.
     *
     * @return the resulting quaternion
     */
    public DoubleQuat getUnnormalizedRotation() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return getNormalizedRotation_identity();
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new DoubleQuat(-(0.25 * this.m12), 0.25 * this.m02, 0.0, 1.0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return getUnnormalizedRotation_orthogonal();
        return getUnnormalizedRotation_general();
    }


    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties. Shared by the
     * identical private paths of {@code cofactor} and {@code normal}; reached only through them.
     */
    private Double3x3 cofactor_orthogonal() {
        return new Double3x3(this.m11, -this.m10, 0.0, this.m10, this.m11, 0.0, Math.fma(-this.m02, this.m11, -(this.m10 * this.m12)), Math.fma(this.m02, this.m10, -(this.m11 * this.m12)), 1.0, 0);
    }


    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties; reached only
     * through the public {@code cofactor} dispatcher.
     */
    private Double3x3 cofactor_affine() {
        return new Double3x3(this.m11, -this.m10, 0.0, -this.m01, this.m00, 0.0, Math.fma(this.m01, this.m12, -(this.m02 * this.m11)), Math.fma(this.m02, this.m10, -(this.m00 * this.m12)), Math.fma(this.m00, this.m11, -(this.m01 * this.m10)), 0);
    }


    /**
     * Private body of {@code cofactor}, specialized by runtime matrix properties; reached only
     * through the public {@code cofactor} dispatcher.
     */
    private Double3x3 cofactor_general() {
        return new Double3x3(Math.fma(this.m11, this.m22, -(this.m12 * this.m21)), Math.fma(this.m12, this.m20, -(this.m10 * this.m22)), Math.fma(this.m10, this.m21, -(this.m11 * this.m20)), Math.fma(this.m02, this.m21, -(this.m01 * this.m22)), Math.fma(this.m00, this.m22, -(this.m02 * this.m20)), Math.fma(this.m01, this.m20, -(this.m00 * this.m21)), Math.fma(this.m01, this.m12, -(this.m02 * this.m11)), Math.fma(this.m02, this.m10, -(this.m00 * this.m12)), Math.fma(this.m00, this.m11, -(this.m01 * this.m10)), 0);
    }


    /**
     * Compute the cofactor matrix of this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Double3x3 cofactor() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, -this.m02, -this.m12, 1.0, 0);
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
    public double determinant() {
        return Math.fma(this.m02, Math.fma(this.m10, this.m21, -(this.m11 * this.m20)), Math.fma(this.m00, Math.fma(this.m11, this.m22, -(this.m12 * this.m21)), -(this.m01 * Math.fma(this.m10, this.m22, -(this.m12 * this.m20)))));
    }


    /**
     * Compute the Frobenius norm of this matrix.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the Frobenius norm of this matrix
     */
    public double frobeniusNorm() {
        return java.lang.Math.sqrt(Math.fma(this.m00, this.m00, this.m01 * this.m01) + Math.fma(this.m02, this.m02, this.m10 * this.m10) + (Math.fma(this.m11, this.m11, this.m12 * this.m12) + Math.fma(this.m20, this.m20, Math.fma(this.m21, this.m21, this.m22 * this.m22))));
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double3x3 invert_orthogonal() {
        return new Double3x3(this.m11, this.m10, Math.fma(-this.m02, this.m11, -(this.m10 * this.m12)), -this.m10, this.m11, Math.fma(this.m02, this.m10, -(this.m11 * this.m12)), 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double3x3 invert_affine() {
        double _t3 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(java.lang.Math.abs(_t3) > 2.2250738585072014E-308 && java.lang.Math.abs(_t3) < 4.49423283715579E307)) return invert_degenerate();
        double _t3_inv = 1.0 / _t3;
        return new Double3x3(this.m11 * _t3_inv, -(this.m01 * _t3_inv), Math.fma(this.m01, this.m12, -(this.m02 * this.m11)) * _t3_inv, -(this.m10 * _t3_inv), this.m00 * _t3_inv, Math.fma(this.m02, this.m10, -(this.m00 * this.m12)) * _t3_inv, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }

    /**
     * Private per-column body of {@code invert_general}. Shared by the identical private paths of
     * {@code invert} and {@code invertProduct}; reached only through them.
     */
    private double[] invert_general_s0_c0(double _t6, double _t13_inv, double _t7) {
        return new double[] {_t6 * _t13_inv, Math.fma(this.m12, this.m20, -(this.m10 * this.m22)) * _t13_inv, _t7 * _t13_inv};
    }

    /**
     * Private per-column body of {@code invert_general}. Shared by the identical private paths of
     * {@code invert} and {@code invertProduct}; reached only through them.
     */
    private double[] invert_general_s0_c1(double _t13_inv) {
        return new double[] {Math.fma(this.m02, this.m21, -(this.m01 * this.m22)) * _t13_inv, Math.fma(this.m00, this.m22, -(this.m02 * this.m20)) * _t13_inv, Math.fma(this.m01, this.m20, -(this.m00 * this.m21)) * _t13_inv};
    }

    /**
     * Private per-column body of {@code invert_general}. Shared by the identical private paths of
     * {@code invert} and {@code invertProduct}; reached only through them.
     */
    private double[] invert_general_s0_c2(double _t13_inv) {
        return new double[] {Math.fma(this.m01, this.m12, -(this.m02 * this.m11)) * _t13_inv, Math.fma(this.m02, this.m10, -(this.m00 * this.m12)) * _t13_inv, Math.fma(this.m00, this.m11, -(this.m01 * this.m10)) * _t13_inv};
    }


    /**
     * Private body of {@code invert}, specialized by runtime matrix properties; reached only
     * through the public {@code invert} dispatcher.
     */
    private Double3x3 invert_general() {
        double _t6 = Math.fma(this.m11, this.m22, -(this.m12 * this.m21));
        double _t7 = Math.fma(this.m10, this.m21, -(this.m11 * this.m20));
        double _t13 = Math.fma(this.m02, _t7, Math.fma(this.m00, _t6, -(this.m01 * Math.fma(this.m10, this.m22, -(this.m12 * this.m20)))));
        if (!(java.lang.Math.abs(_t13) > 2.2250738585072014E-308 && java.lang.Math.abs(_t13) < 4.49423283715579E307)) return invert_degenerate();
        double _t13_inv = 1.0 / _t13;
        double[] _col0 = invert_general_s0_c0(_t6, _t13_inv, _t7);
        double[] _col1 = invert_general_s0_c1(_t13_inv);
        double[] _col2 = invert_general_s0_c2(_t13_inv);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Invert this matrix, returning the result as a value.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @return the resulting matrix
     */
    public Double3x3 invert() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(1.0, 0.0, -this.m02, 0.0, 1.0, -this.m12, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invert_affine();
        return invert_general();
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double3x3 invert_degenerate_translation() {
        double _t0 = unitScale(1.0, 0.0, this.m02);
        double _t1 = unitScale(0.0, 1.0, this.m12);
        double _t2_inv = 1.0 / _t0;
        double _t3_inv = 1.0 / _t1;
        return new Double3x3(_t0 * _t2_inv, 0.0, -(this.m02 * _t0 * _t2_inv), 0.0, _t1 * _t3_inv, -(this.m12 * _t1 * _t3_inv), 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double3x3 invert_degenerate_orthogonal() {
        double _t0 = unitScale(this.m10, this.m11, this.m12);
        double _t1 = unitScale(this.m00, this.m01, this.m02);
        double _t8 = this.m11 * _t0;
        double _t9 = this.m00 * _t1;
        double _t10 = this.m01 * _t1;
        double _t11 = this.m10 * _t0;
        double _t12 = this.m12 * _t0;
        double _t13 = this.m02 * _t1;
        double _t16_inv = 1.0 / Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t0 * _t16_inv;
        double _sp0 = _t1 * _t16_inv;
        return new Double3x3(_t8 * _sp0, -(_t10 * _sp1), Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv, -(_t11 * _sp0), _t9 * _sp1, Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double3x3 invert_degenerate_affine() {
        double _t0 = unitScale(this.m10, this.m11, this.m12);
        double _t1 = unitScale(this.m00, this.m01, this.m02);
        double _t8 = this.m11 * _t0;
        double _t9 = this.m00 * _t1;
        double _t10 = this.m01 * _t1;
        double _t11 = this.m10 * _t0;
        double _t12 = this.m12 * _t0;
        double _t13 = this.m02 * _t1;
        double _t16_inv = 1.0 / Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t0 * _t16_inv;
        double _sp0 = _t1 * _t16_inv;
        return new Double3x3(_t8 * _sp0, -(_t10 * _sp1), Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv, -(_t11 * _sp0), _t9 * _sp1, Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }

    /**
     * Private per-column body of {@code invert_degenerate_general_s0_tail}. Shared by the identical
     * private paths of {@code invert} and {@code invertProduct}; reached only through them.
     */
    private double[] invert_degenerate_general_s0_tail_s118b9478_c0(double _t27, double _sp0, double _t14, double _t17, double _t16, double _t13, double _t28) {
        return new double[] {_t27 * _sp0, Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0, _t28 * _sp0};
    }

    /**
     * Private per-column body of {@code invert_degenerate_general_s0_tail}. Shared by the identical
     * private paths of {@code invert} and {@code invertProduct}; reached only through them.
     */
    private double[] invert_degenerate_general_s0_tail_s118b9478_c1(double _t18, double _t15, double _t20, double _t13, double _sp1, double _t19, double _t17) {
        return new double[] {Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1, Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1, Math.fma(_t20, _t17, -(_t19 * _t15)) * _sp1};
    }

    /**
     * Private per-column body of {@code invert_degenerate_general_s0_tail}. Shared by the identical
     * private paths of {@code invert} and {@code invertProduct}; reached only through them.
     */
    private double[] invert_degenerate_general_s0_tail_s118b9478_c2(double _t20, double _t14, double _t18, double _t12, double _sp2, double _t16, double _t19) {
        return new double[] {Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2, Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2, Math.fma(_t19, _t12, -(_t20 * _t16)) * _sp2};
    }

    /**
     * Private tail of {@code invert_degenerate_general}. Shared by the identical private paths of
     * {@code invert} and {@code invertProduct}; reached only through them.
     */
    private Double3x3 invert_degenerate_general_s0_tail(double _t2, double _t33_inv, double _t27, double _t18, double _t15, double _t20, double _t13, double _sp1, double _t14, double _t12, double _sp2, double _t17, double _t16, double _t19, double _t28, int _props) {
        double[] _col0 = invert_degenerate_general_s0_tail_s118b9478_c0(_t27, _t2 * _t33_inv, _t14, _t17, _t16, _t13, _t28);
        double[] _col1 = invert_degenerate_general_s0_tail_s118b9478_c1(_t18, _t15, _t20, _t13, _sp1, _t19, _t17);
        double[] _col2 = invert_degenerate_general_s0_tail_s118b9478_c2(_t20, _t14, _t18, _t12, _sp2, _t16, _t19);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN). Shared by the identical private paths of {@code invert} and {@code invertProduct};
     * reached only through them.
     */
    private Double3x3 invert_degenerate_general() {
        double _t0 = unitScale(this.m10, this.m11, this.m12);
        double _t1 = unitScale(this.m20, this.m21, this.m22);
        double _t2 = unitScale(this.m00, this.m01, this.m02);
        double _t12 = this.m11 * _t0;
        double _t13 = this.m22 * _t1;
        double _t14 = this.m12 * _t0;
        double _t15 = this.m21 * _t1;
        double _t16 = this.m10 * _t0;
        double _t17 = this.m20 * _t1;
        double _t18 = this.m02 * _t2;
        double _t19 = this.m00 * _t2;
        double _t20 = this.m01 * _t2;
        double _t27 = Math.fma(_t12, _t13, -(_t14 * _t15));
        double _t28 = Math.fma(_t16, _t15, -(_t12 * _t17));
        double _t33_inv = 1.0 / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        return invert_degenerate_general_s0_tail(_t2, _t33_inv, _t27, _t18, _t15, _t20, _t13, _t0 * _t33_inv, _t14, _t12, _t1 * _t33_inv, _t17, _t16, _t19, _t28, 0);
    }


    /**
     * Degenerate-input path of {@code invert}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double3x3 invert_degenerate() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return invert_degenerate_translation();
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return invert_degenerate_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return invert_degenerate_affine();
        return invert_degenerate_general();
    }

    /**
     * Private per-column body of {@code invertProduct_general_s74c57dcd_tail}. Shared by 8
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private double[] invertProduct_general_s74c57dcd_tail_s4181cd16_c1(double _t20, double _t22, double _t26, double _t19, double _t40_inv, double _t25, double _t24) {
        return new double[] {Math.fma(_t20, _t22, -(_t26 * _t19)) * _t40_inv, Math.fma(_t25, _t19, -(_t24 * _t22)) * _t40_inv, Math.fma(_t24, _t26, -(_t25 * _t20)) * _t40_inv};
    }

    /** Private tail of {@code invertProduct_general}; reached only through it. */
    private Double3x3 invertProduct_general_s74c57dcd_tail(Double3x3 other, double _t18, double _t19, double _t20, double _t21, double _t23, double _t24, double _t22, int _props) {
        double _t25 = Math.fma(other.m20(), this.m02, Math.fma(other.m00(), this.m00, other.m10() * this.m01));
        double _t26 = Math.fma(other.m21(), this.m02, Math.fma(other.m01(), this.m00, other.m11() * this.m01));
        double _t33 = Math.fma(_t18, _t19, -(_t20 * _t21));
        double _t34 = Math.fma(_t23, _t20, -(_t24 * _t18));
        double _t40 = Math.fma(_t22, _t34, Math.fma(_t25, _t33, -(_t26 * Math.fma(_t23, _t19, -(_t24 * _t21)))));
        if (!(java.lang.Math.abs(_t40) > 2.2250738585072014E-308 && java.lang.Math.abs(_t40) < 4.49423283715579E307)) return null;
        double _t40_inv = 1.0 / _t40;
        double[] _col0 = invert_degenerate_general_s0_tail_s118b9478_c0(_t33, _t40_inv, _t24, _t21, _t23, _t19, _t34);
        double[] _col1 = invertProduct_general_s74c57dcd_tail_s4181cd16_c1(_t20, _t22, _t26, _t19, _t40_inv, _t25, _t24);
        double[] _col2 = invertProduct_general_s74c57dcd_tail_s4181cd16_c1(_t26, _t21, _t18, _t22, _t40_inv, _t23, _t25);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_general(Double3x3 other) {
        Double3x3 _r = invertProduct_general_s74c57dcd_tail(other, Math.fma(other.m21(), this.m12, Math.fma(other.m01(), this.m10, other.m11() * this.m11)), Math.fma(other.m22(), this.m22, Math.fma(other.m02(), this.m20, other.m12() * this.m21)), Math.fma(other.m21(), this.m22, Math.fma(other.m01(), this.m20, other.m11() * this.m21)), Math.fma(other.m22(), this.m12, Math.fma(other.m02(), this.m10, other.m12() * this.m11)), Math.fma(other.m20(), this.m12, Math.fma(other.m00(), this.m10, other.m10() * this.m11)), Math.fma(other.m20(), this.m22, Math.fma(other.m00(), this.m20, other.m10() * this.m21)), Math.fma(other.m22(), this.m02, Math.fma(other.m02(), this.m00, other.m12() * this.m01)), 0);
        return _r != null ? _r : invertProduct_degenerate(other);
    }

    /** Private per-column body of {@code invertProduct_identity}; reached only through it. */
    private double[] invertProduct_identity_s74c57dcd_c0(double _t6, double _t13_inv, Double3x3 other, double _t7) {
        return new double[] {_t6 * _t13_inv, Math.fma(other.m12(), other.m20(), -(other.m10() * other.m22())) * _t13_inv, _t7 * _t13_inv};
    }

    /** Private per-column body of {@code invertProduct_identity}; reached only through it. */
    private double[] invertProduct_identity_s74c57dcd_c1(Double3x3 other, double _t13_inv) {
        return new double[] {Math.fma(other.m02(), other.m21(), -(other.m01() * other.m22())) * _t13_inv, Math.fma(other.m00(), other.m22(), -(other.m02() * other.m20())) * _t13_inv, Math.fma(other.m01(), other.m20(), -(other.m00() * other.m21())) * _t13_inv};
    }

    /** Private per-column body of {@code invertProduct_identity}; reached only through it. */
    private double[] invertProduct_identity_s74c57dcd_c2(Double3x3 other, double _t13_inv) {
        return new double[] {Math.fma(other.m01(), other.m12(), -(other.m02() * other.m11())) * _t13_inv, Math.fma(other.m02(), other.m10(), -(other.m00() * other.m12())) * _t13_inv, Math.fma(other.m00(), other.m11(), -(other.m01() * other.m10())) * _t13_inv};
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_identity(Double3x3 other) {
        double _t6 = Math.fma(other.m11(), other.m22(), -(other.m12() * other.m21()));
        double _t7 = Math.fma(other.m10(), other.m21(), -(other.m11() * other.m20()));
        double _t13 = Math.fma(other.m02(), _t7, Math.fma(other.m00(), _t6, -(other.m01() * Math.fma(other.m10(), other.m22(), -(other.m12() * other.m20())))));
        if (!(java.lang.Math.abs(_t13) > 2.2250738585072014E-308 && java.lang.Math.abs(_t13) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t13_inv = 1.0 / _t13;
        double[] _col0 = invertProduct_identity_s74c57dcd_c0(_t6, _t13_inv, other, _t7);
        double[] _col1 = invertProduct_identity_s74c57dcd_c1(other, _t13_inv);
        double[] _col2 = invertProduct_identity_s74c57dcd_c2(other, _t13_inv);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], other.properties());
    }

    /**
     * Private per-column body of {@code invertProduct_translation}. Shared by 2 identical private
     * paths of {@code invertProduct}; reached only through it.
     */
    private double[] invertProduct_translation_s74c57dcd_c0(double _t12, double _t19_inv, Double3x3 other, double _t1, double _t3, double _t13) {
        return new double[] {_t12 * _t19_inv, Math.fma(other.m20(), _t1, -(other.m22() * _t3)) * _t19_inv, _t13 * _t19_inv};
    }

    /**
     * Private per-column body of {@code invertProduct_translation}. Shared by 2 identical private
     * paths of {@code invertProduct}; reached only through it.
     */
    private double[] invertProduct_translation_s74c57dcd_c1(Double3x3 other, double _t2, double _t5, double _t19_inv, double _t4) {
        return new double[] {Math.fma(other.m21(), _t2, -(other.m22() * _t5)) * _t19_inv, Math.fma(other.m22(), _t4, -(other.m20() * _t2)) * _t19_inv, Math.fma(other.m20(), _t5, -(other.m21() * _t4)) * _t19_inv};
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_translation(Double3x3 other) {
        double _t0 = Math.fma(other.m21(), this.m12, other.m11());
        double _t1 = Math.fma(other.m22(), this.m12, other.m12());
        double _t2 = Math.fma(other.m22(), this.m02, other.m02());
        double _t3 = Math.fma(other.m20(), this.m12, other.m10());
        double _t4 = Math.fma(other.m20(), this.m02, other.m00());
        double _t5 = Math.fma(other.m21(), this.m02, other.m01());
        double _t12 = Math.fma(other.m22(), _t0, -(other.m21() * _t1));
        double _t13 = Math.fma(other.m21(), _t3, -(other.m20() * _t0));
        double _t19 = Math.fma(_t2, _t13, Math.fma(_t4, _t12, -(_t5 * Math.fma(other.m22(), _t3, -(other.m20() * _t1)))));
        if (!(java.lang.Math.abs(_t19) > 2.2250738585072014E-308 && java.lang.Math.abs(_t19) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t19_inv = 1.0 / _t19;
        double[] _col0 = invertProduct_translation_s74c57dcd_c0(_t12, _t19_inv, other, _t1, _t3, _t13);
        double[] _col1 = invertProduct_translation_s74c57dcd_c1(other, _t2, _t5, _t19_inv, _t4);
        double[] _col2 = invert_degenerate_general_s0_tail_s118b9478_c2(_t5, _t1, _t2, _t0, _t19_inv, _t3, _t4);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], Joml.BIT_TRANSLATION & other.properties());
    }

    /** Private tail of {@code invertProduct_orthogonal}; reached only through it. */
    private Double3x3 invertProduct_orthogonal_s1c4ff883_tail(double _t14, double _t25, double _t16, double _t24, double _t17, Double3x3 other, double _t15, double _t13, double _t12, int _props) {
        double _t31 = Math.fma(_t14, _t25, Math.fma(_t16, _t24, -(_t17 * Math.fma(other.m22(), _t15, -(other.m20() * _t13)))));
        if (!(java.lang.Math.abs(_t31) > 2.2250738585072014E-308 && java.lang.Math.abs(_t31) < 4.49423283715579E307)) return null;
        double _t31_inv = 1.0 / _t31;
        double[] _col0 = invertProduct_translation_s74c57dcd_c0(_t24, _t31_inv, other, _t13, _t15, _t25);
        double[] _col1 = invertProduct_translation_s74c57dcd_c1(other, _t14, _t17, _t31_inv, _t16);
        double[] _col2 = invertProduct_general_s74c57dcd_tail_s4181cd16_c1(_t17, _t13, _t12, _t14, _t31_inv, _t15, _t16);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_orthogonal(Double3x3 other, int _props) {
        double _t12 = Math.fma(other.m21(), this.m12, Math.fma(other.m01(), this.m10, other.m11() * this.m11));
        double _t13 = Math.fma(other.m22(), this.m12, Math.fma(other.m02(), this.m10, other.m12() * this.m11));
        double _t15 = Math.fma(other.m20(), this.m12, Math.fma(other.m00(), this.m10, other.m10() * this.m11));
        Double3x3 _r = invertProduct_orthogonal_s1c4ff883_tail(Math.fma(other.m22(), this.m02, Math.fma(other.m02(), this.m00, other.m12() * this.m01)), Math.fma(other.m21(), _t15, -(other.m20() * _t12)), Math.fma(other.m20(), this.m02, Math.fma(other.m00(), this.m00, other.m10() * this.m01)), Math.fma(other.m22(), _t12, -(other.m21() * _t13)), Math.fma(other.m21(), this.m02, Math.fma(other.m01(), this.m00, other.m11() * this.m01)), other, _t15, _t13, _t12, _props);
        return _r != null ? _r : invertProduct_degenerate(other);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_identity_translation(Double3x3 other) {
        return new Double3x3(1.0, 0.0, -other.m02(), 0.0, 1.0, -other.m12(), 0.0, 0.0, 1.0, other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_identity_affine(Double3x3 other) {
        double _t3 = Math.fma(other.m00(), other.m11(), -(other.m01() * other.m10()));
        if (!(java.lang.Math.abs(_t3) > 2.2250738585072014E-308 && java.lang.Math.abs(_t3) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t3_inv = 1.0 / _t3;
        return new Double3x3(other.m11() * _t3_inv, -(other.m01() * _t3_inv), Math.fma(other.m01(), other.m12(), -(other.m02() * other.m11())) * _t3_inv, -(other.m10() * _t3_inv), other.m00() * _t3_inv, Math.fma(other.m02(), other.m10(), -(other.m00() * other.m12())) * _t3_inv, 0.0, 0.0, 1.0, other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_translation_identity(Double3x3 other) {
        return new Double3x3(1.0, 0.0, -this.m02, 0.0, 1.0, -this.m12, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_translation_translation(Double3x3 other) {
        return new Double3x3(1.0, 0.0, -(other.m02() + this.m02), 0.0, 1.0, -(other.m12() + this.m12), 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_translation_affine(Double3x3 other) {
        double _t1 = other.m12() + this.m12;
        double _t2 = other.m02() + this.m02;
        double _t5 = Math.fma(other.m00(), other.m11(), -(other.m01() * other.m10()));
        if (!(java.lang.Math.abs(_t5) > 2.2250738585072014E-308 && java.lang.Math.abs(_t5) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t5_inv = 1.0 / _t5;
        return new Double3x3(other.m11() * _t5_inv, -(other.m01() * _t5_inv), Math.fma(other.m01(), _t1, -(other.m11() * _t2)) * _t5_inv, -(other.m10() * _t5_inv), other.m00() * _t5_inv, Math.fma(other.m10(), _t2, -(other.m00() * _t1)) * _t5_inv, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_orthogonal_identity(Double3x3 other) {
        return new Double3x3(this.m11, this.m10, Math.fma(-this.m02, this.m11, -(this.m10 * this.m12)), -this.m10, this.m11, Math.fma(this.m02, this.m10, -(this.m11 * this.m12)), 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_orthogonal_translation(Double3x3 other) {
        double _t0 = -this.m10;
        return new Double3x3(this.m11, this.m10, Math.fma(_t0, this.m12, Math.fma(-this.m02, this.m11, -other.m02())), _t0, this.m11, Math.fma(this.m02, this.m10, Math.fma(-this.m11, this.m12, -other.m12())), 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_orthogonal_affine(Double3x3 other, int _props) {
        double _t6 = Math.fma(other.m01(), this.m10, other.m11() * this.m11);
        double _t7 = Math.fma(other.m00(), this.m00, other.m10() * this.m01);
        double _t8 = Math.fma(other.m00(), this.m10, other.m10() * this.m11);
        double _t9 = Math.fma(other.m01(), this.m00, other.m11() * this.m01);
        double _t10 = Math.fma(other.m02(), this.m10, Math.fma(other.m12(), this.m11, this.m12));
        double _t11 = Math.fma(other.m02(), this.m00, Math.fma(other.m12(), this.m01, this.m02));
        double _t15 = Math.fma(_t7, _t6, -(_t8 * _t9));
        if (!(java.lang.Math.abs(_t15) > 2.2250738585072014E-308 && java.lang.Math.abs(_t15) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t15_inv = 1.0 / _t15;
        return new Double3x3(_t6 * _t15_inv, -(_t9 * _t15_inv), Math.fma(_t10, _t9, -(_t11 * _t6)) * _t15_inv, -(_t8 * _t15_inv), _t7 * _t15_inv, Math.fma(_t11, _t8, -(_t10 * _t7)) * _t15_inv, 0.0, 0.0, 1.0, _props);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_affine_identity(Double3x3 other) {
        double _t3 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(java.lang.Math.abs(_t3) > 2.2250738585072014E-308 && java.lang.Math.abs(_t3) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t3_inv = 1.0 / _t3;
        return new Double3x3(this.m11 * _t3_inv, -(this.m01 * _t3_inv), Math.fma(this.m01, this.m12, -(this.m02 * this.m11)) * _t3_inv, -(this.m10 * _t3_inv), this.m00 * _t3_inv, Math.fma(this.m02, this.m10, -(this.m00 * this.m12)) * _t3_inv, 0.0, 0.0, 1.0, Joml.BIT_AFFINE & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_affine_translation(Double3x3 other) {
        double _t5 = Math.fma(other.m02(), this.m10, Math.fma(other.m12(), this.m11, this.m12));
        double _t6 = Math.fma(other.m02(), this.m00, Math.fma(other.m12(), this.m01, this.m02));
        double _t7 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(java.lang.Math.abs(_t7) > 2.2250738585072014E-308 && java.lang.Math.abs(_t7) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t7_inv = 1.0 / _t7;
        return new Double3x3(this.m11 * _t7_inv, -(this.m01 * _t7_inv), Math.fma(this.m01, _t5, -(this.m11 * _t6)) * _t7_inv, -(this.m10 * _t7_inv), this.m00 * _t7_inv, Math.fma(this.m10, _t6, -(this.m00 * _t5)) * _t7_inv, 0.0, 0.0, 1.0, Joml.BIT_AFFINE & other.properties());
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_general_identity(Double3x3 other) {
        double _t6 = Math.fma(this.m11, this.m22, -(this.m12 * this.m21));
        double _t7 = Math.fma(this.m10, this.m21, -(this.m11 * this.m20));
        double _t13 = Math.fma(this.m02, _t7, Math.fma(this.m00, _t6, -(this.m01 * Math.fma(this.m10, this.m22, -(this.m12 * this.m20)))));
        if (!(java.lang.Math.abs(_t13) > 2.2250738585072014E-308 && java.lang.Math.abs(_t13) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t13_inv = 1.0 / _t13;
        double[] _col0 = invert_general_s0_c0(_t6, _t13_inv, _t7);
        double[] _col1 = invert_general_s0_c1(_t13_inv);
        double[] _col2 = invert_general_s0_c2(_t13_inv);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }

    /** Private per-column body of {@code invertProduct_general_translation}; reached only through it. */
    private double[] invertProduct_general_translation_s74c57dcd_c0(double _t13, double _t19_inv, double _t7, double _t6, double _t5) {
        return new double[] {_t13 * _t19_inv, Math.fma(this.m20, _t7, -(this.m10 * _t6)) * _t19_inv, _t5 * _t19_inv};
    }

    /** Private per-column body of {@code invertProduct_general_translation}; reached only through it. */
    private double[] invertProduct_general_translation_s74c57dcd_c1(double _t8, double _t6, double _t19_inv) {
        return new double[] {Math.fma(this.m21, _t8, -(this.m01 * _t6)) * _t19_inv, Math.fma(this.m00, _t6, -(this.m20 * _t8)) * _t19_inv, Math.fma(this.m01, this.m20, -(this.m00 * this.m21)) * _t19_inv};
    }

    /** Private per-column body of {@code invertProduct_general_translation}; reached only through it. */
    private double[] invertProduct_general_translation_s74c57dcd_c2(double _t7, double _t8, double _t19_inv) {
        return new double[] {Math.fma(this.m01, _t7, -(this.m11 * _t8)) * _t19_inv, Math.fma(this.m10, _t8, -(this.m00 * _t7)) * _t19_inv, Math.fma(this.m00, this.m11, -(this.m01 * this.m10)) * _t19_inv};
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_general_translation(Double3x3 other) {
        double _t5 = Math.fma(this.m10, this.m21, -(this.m11 * this.m20));
        double _t6 = Math.fma(other.m02(), this.m20, Math.fma(other.m12(), this.m21, this.m22));
        double _t7 = Math.fma(other.m02(), this.m10, Math.fma(other.m12(), this.m11, this.m12));
        double _t8 = Math.fma(other.m02(), this.m00, Math.fma(other.m12(), this.m01, this.m02));
        double _t13 = Math.fma(this.m11, _t6, -(this.m21 * _t7));
        double _t19 = Math.fma(_t8, _t5, Math.fma(this.m00, _t13, -(this.m01 * Math.fma(this.m10, _t6, -(this.m20 * _t7)))));
        if (!(java.lang.Math.abs(_t19) > 2.2250738585072014E-308 && java.lang.Math.abs(_t19) < 4.49423283715579E307)) return invertProduct_degenerate(other);
        double _t19_inv = 1.0 / _t19;
        double[] _col0 = invertProduct_general_translation_s74c57dcd_c0(_t13, _t19_inv, _t7, _t6, _t5);
        double[] _col1 = invertProduct_general_translation_s74c57dcd_c1(_t8, _t6, _t19_inv);
        double[] _col2 = invertProduct_general_translation_s74c57dcd_c2(_t7, _t8, _t19_inv);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }

    /**
     * Private per-column body of {@code invertProduct_general_affine_s74c57dcd_tail}. Shared by 4
     * identical private paths of {@code invertProduct}; reached only through it.
     */
    private double[] invertProduct_general_affine_s74c57dcd_tail_s37b9a40c_c1(double _t17, double _t10, double _t15, double _t14, double _t31_inv, double _t13, double _t12) {
        return new double[] {Math.fma(_t17, _t10, -(_t15 * _t14)) * _t31_inv, Math.fma(_t15, _t13, -(_t17 * _t12)) * _t31_inv, Math.fma(_t12, _t14, -(_t13 * _t10)) * _t31_inv};
    }

    /** Private tail of {@code invertProduct_general_affine}; reached only through it. */
    private Double3x3 invertProduct_general_affine_s74c57dcd_tail(double _t17, double _t24, double _t13, double _t25, double _t14, double _t15, double _t11, double _t16, double _t12, double _t10, double _t9, int _props) {
        double _t31 = Math.fma(_t17, _t24, Math.fma(_t13, _t25, -(_t14 * Math.fma(_t15, _t11, -(_t16 * _t12)))));
        if (!(java.lang.Math.abs(_t31) > 2.2250738585072014E-308 && java.lang.Math.abs(_t31) < 4.49423283715579E307)) return null;
        double _t31_inv = 1.0 / _t31;
        double[] _col0 = invert_degenerate_general_s0_tail_s118b9478_c0(_t25, _t31_inv, _t16, _t12, _t15, _t11, _t24);
        double[] _col1 = invertProduct_general_affine_s74c57dcd_tail_s37b9a40c_c1(_t17, _t10, _t15, _t14, _t31_inv, _t13, _t12);
        double[] _col2 = invertProduct_general_affine_s74c57dcd_tail_s37b9a40c_c1(_t16, _t14, _t17, _t9, _t31_inv, _t11, _t13);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Private body of {@code invertProduct}, specialized by runtime matrix properties; reached only
     * through the public {@code invertProduct} dispatcher.
     */
    private Double3x3 invertProduct_general_affine(Double3x3 other) {
        double _t9 = Math.fma(other.m01(), this.m10, other.m11() * this.m11);
        double _t10 = Math.fma(other.m01(), this.m20, other.m11() * this.m21);
        double _t11 = Math.fma(other.m00(), this.m10, other.m10() * this.m11);
        double _t12 = Math.fma(other.m00(), this.m20, other.m10() * this.m21);
        double _t15 = Math.fma(other.m02(), this.m20, Math.fma(other.m12(), this.m21, this.m22));
        double _t16 = Math.fma(other.m02(), this.m10, Math.fma(other.m12(), this.m11, this.m12));
        Double3x3 _r = invertProduct_general_affine_s74c57dcd_tail(Math.fma(other.m02(), this.m00, Math.fma(other.m12(), this.m01, this.m02)), Math.fma(_t11, _t10, -(_t12 * _t9)), Math.fma(other.m00(), this.m00, other.m10() * this.m01), Math.fma(_t15, _t9, -(_t16 * _t10)), Math.fma(other.m01(), this.m00, other.m11() * this.m01), _t15, _t11, _t16, _t12, _t10, _t9, 0);
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
    public Double3x3 invertProduct(Double3x3 other) {
        int p = this.properties;
        int q = other.properties();
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, other.properties());
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
    private Double3x3 invertProduct_s7500000e_tail(double _t23, double _t20, double _t24, double _t18, double _t22, double _t25, double _t33, double _t26, double _t19, double _t21, int _props) {
        double _t34 = Math.fma(_t23, _t20, -(_t24 * _t18));
        double _t40 = Math.fma(_t22, _t34, Math.fma(_t25, _t33, -(_t26 * Math.fma(_t23, _t19, -(_t24 * _t21)))));
        if (!(java.lang.Math.abs(_t40) > 2.2250738585072014E-308 && java.lang.Math.abs(_t40) < 4.49423283715579E307)) return null;
        double _t40_inv = 1.0 / _t40;
        double[] _col0 = invert_degenerate_general_s0_tail_s118b9478_c0(_t33, _t40_inv, _t24, _t21, _t23, _t19, _t34);
        double[] _col1 = invertProduct_general_s74c57dcd_tail_s4181cd16_c1(_t20, _t22, _t26, _t19, _t40_inv, _t25, _t24);
        double[] _col2 = invertProduct_general_s74c57dcd_tail_s4181cd16_c1(_t26, _t21, _t18, _t22, _t40_inv, _t23, _t25);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
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
    public Double3x3 invertProduct(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
        double _t18 = Math.fma(m21, this.m12, Math.fma(m01, this.m10, m11 * this.m11));
        double _t19 = Math.fma(m22, this.m22, Math.fma(m02, this.m20, m12 * this.m21));
        double _t20 = Math.fma(m21, this.m22, Math.fma(m01, this.m20, m11 * this.m21));
        double _t21 = Math.fma(m22, this.m12, Math.fma(m02, this.m10, m12 * this.m11));
        Double3x3 _r = invertProduct_s7500000e_tail(Math.fma(m20, this.m12, Math.fma(m00, this.m10, m10 * this.m11)), _t20, Math.fma(m20, this.m22, Math.fma(m00, this.m20, m10 * this.m21)), _t18, Math.fma(m22, this.m02, Math.fma(m02, this.m00, m12 * this.m01)), Math.fma(m20, this.m02, Math.fma(m00, this.m00, m10 * this.m01)), Math.fma(_t18, _t19, -(_t20 * _t21)), Math.fma(m21, this.m02, Math.fma(m01, this.m00, m11 * this.m01)), _t19, _t21, 0);
        return _r != null ? _r : invertProduct_degenerate(m00, m01, m02, m10, m11, m12, m20, m21, m22);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_general(Double3x3 other) {
        double[] _bundle0 = invertProduct_degenerate_general_s251dcacb_1(other);
        double[] _bundle1 = invertProduct_degenerate_general_s251dcacb_2(other, _bundle0);
        double[] _bundle2 = invertProduct_degenerate_general_s251dcacb_3(_bundle0[1], _bundle0[2], _bundle1[0], _bundle1[1], _bundle1[2], _bundle1[3], _bundle1[4], _bundle1[5], _bundle1[6], _bundle1[7], _bundle1[8], _bundle1[9]);
        return new Double3x3(_bundle2[0], _bundle2[1], _bundle2[2], _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], 0);
    }

    /** Part 1 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private double[] invertProduct_degenerate_general_s251dcacb_1(Double3x3 other) {
        return new double[] {Math.fma(other.m21(), this.m12, Math.fma(other.m01(), this.m10, other.m11() * this.m11)), Math.fma(other.m20(), this.m12, Math.fma(other.m00(), this.m10, other.m10() * this.m11)), Math.fma(other.m22(), this.m12, Math.fma(other.m02(), this.m10, other.m12() * this.m11)), Math.fma(other.m22(), this.m22, Math.fma(other.m02(), this.m20, other.m12() * this.m21))};
    }

    /** Part 2 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private double[] invertProduct_degenerate_general_s251dcacb_2(Double3x3 other, double[] _bundle0) {
        double _t18 = _bundle0[0];
        double _t21 = _bundle0[3];
        double _t22 = Math.fma(other.m20(), this.m22, Math.fma(other.m00(), this.m20, other.m10() * this.m21));
        double _t23 = Math.fma(other.m21(), this.m22, Math.fma(other.m01(), this.m20, other.m11() * this.m21));
        double _t24 = Math.fma(other.m20(), this.m02, Math.fma(other.m00(), this.m00, other.m10() * this.m01));
        double _t25 = Math.fma(other.m21(), this.m02, Math.fma(other.m01(), this.m00, other.m11() * this.m01));
        double _t26 = Math.fma(other.m22(), this.m02, Math.fma(other.m02(), this.m00, other.m12() * this.m01));
        double _t27 = unitScale(_bundle0[1], _t18, _bundle0[2]);
        double _t28 = unitScale(_t22, _t23, _t21);
        return new double[] {_t22, _t24, _t25, _t26, _t27, _t28, unitScale(_t24, _t25, _t26), _t18 * _t27, _t21 * _t28, _t23 * _t28};
    }

    /** Part 3 of {@code invertProduct_degenerate_general}, split to fit the inline budget; reached only through it. */
    private double[] invertProduct_degenerate_general_s251dcacb_3(double _t19, double _t20, double _t22, double _t24, double _t25, double _t26, double _t27, double _t28, double _t29, double _t39, double _t40, double _t41) {
        double _t42 = _t20 * _t27;
        double _t43 = _t19 * _t27;
        double _t44 = _t22 * _t28;
        double _t45 = _t26 * _t29;
        double _t46 = _t24 * _t29;
        double _t47 = _t25 * _t29;
        double _t54 = Math.fma(_t39, _t40, -(_t41 * _t42));
        double _t55 = Math.fma(_t43, _t41, -(_t44 * _t39));
        double _t60_inv = 1.0 / Math.fma(_t55, _t45, Math.fma(_t54, _t46, -(Math.fma(_t43, _t40, -(_t44 * _t42)) * _t47)));
        double _sp2 = _t28 * _t60_inv;
        double _sp1 = _t27 * _t60_inv;
        double _sp0 = _t29 * _t60_inv;
        return new double[] {_t54 * _sp0, Math.fma(_t41, _t45, -(_t47 * _t40)) * _sp1, Math.fma(_t47, _t42, -(_t39 * _t45)) * _sp2, Math.fma(_t44, _t42, -(_t43 * _t40)) * _sp0, Math.fma(_t46, _t40, -(_t44 * _t45)) * _sp1, Math.fma(_t43, _t45, -(_t46 * _t42)) * _sp2, _t55 * _sp0, Math.fma(_t44, _t47, -(_t46 * _t41)) * _sp1, Math.fma(_t46, _t39, -(_t43 * _t47)) * _sp2};
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_identity(Double3x3 other) {
        double _t0 = unitScale(other.m10(), other.m11(), other.m12());
        double _t1 = unitScale(other.m20(), other.m21(), other.m22());
        double _t2 = unitScale(other.m00(), other.m01(), other.m02());
        double _t12 = other.m11() * _t0;
        double _t13 = other.m22() * _t1;
        double _t14 = other.m12() * _t0;
        double _t15 = other.m21() * _t1;
        double _t16 = other.m10() * _t0;
        double _t17 = other.m20() * _t1;
        double _t18 = other.m02() * _t2;
        double _t19 = other.m00() * _t2;
        double _t20 = other.m01() * _t2;
        double _t27 = Math.fma(_t12, _t13, -(_t14 * _t15));
        double _t28 = Math.fma(_t16, _t15, -(_t12 * _t17));
        double _t33_inv = 1.0 / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        return invert_degenerate_general_s0_tail(_t2, _t33_inv, _t27, _t18, _t15, _t20, _t13, _t0 * _t33_inv, _t14, _t12, _t1 * _t33_inv, _t17, _t16, _t19, _t28, Joml.BIT_ORTHOGONAL & other.properties());
    }

    /** Private tail of {@code invertProduct_degenerate_translation}; reached only through it. */
    private Double3x3 invertProduct_degenerate_translation_s74c57dcd_tail(double _t34, double _t24, double _t33, double _t25, double _t10, double _t23, double _t12, double _t22, double _t26, double _t0, double _t13, double _t14, double _t11, double _t21, int _props) {
        double _t39_inv = 1.0 / Math.fma(_t34, _t24, Math.fma(_t33, _t25, -(Math.fma(_t10, _t23, -(_t12 * _t22)) * _t26)));
        double[] _col0 = invert_degenerate_general_s0_tail_s118b9478_c0(_t33, _t14 * _t39_inv, _t12, _t22, _t10, _t23, _t34);
        double[] _col1 = invert_degenerate_general_s0_tail_s118b9478_c2(_t11, _t24, _t10, _t26, _t13 * _t39_inv, _t25, _t12);
        double[] _col2 = invert_degenerate_general_s0_tail_s118b9478_c2(_t26, _t22, _t24, _t21, _t0 * _t39_inv, _t23, _t25);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_translation(Double3x3 other) {
        double _t0 = unitScale(other.m20(), other.m21(), other.m22());
        double _t1 = Math.fma(other.m21(), this.m12, other.m11());
        double _t2 = Math.fma(other.m20(), this.m12, other.m10());
        double _t3 = Math.fma(other.m22(), this.m12, other.m12());
        double _t4 = Math.fma(other.m20(), this.m02, other.m00());
        double _t5 = Math.fma(other.m21(), this.m02, other.m01());
        double _t6 = Math.fma(other.m22(), this.m02, other.m02());
        double _t10 = other.m22() * _t0;
        double _t11 = other.m21() * _t0;
        double _t12 = other.m20() * _t0;
        double _t13 = unitScale(_t2, _t1, _t3);
        double _t14 = unitScale(_t4, _t5, _t6);
        double _t21 = _t1 * _t13;
        double _t22 = _t3 * _t13;
        double _t23 = _t2 * _t13;
        return invertProduct_degenerate_translation_s74c57dcd_tail(Math.fma(_t11, _t23, -(_t12 * _t21)), _t6 * _t14, Math.fma(_t10, _t21, -(_t11 * _t22)), _t4 * _t14, _t10, _t23, _t12, _t22, _t5 * _t14, _t0, _t13, _t14, _t11, _t21, Joml.BIT_ORTHOGONAL & other.properties());
    }

    /** Private tail of {@code invertProduct_degenerate_orthogonal}; reached only through it. */
    private Double3x3 invertProduct_degenerate_orthogonal_s1c4ff883_tail(double _t20, double _t19, double _t21, double _t22, double _t23, double _t24, double _t16, double _t17, double _t18, double _t6, int _props) {
        double _t25 = unitScale(_t20, _t19, _t21);
        double _t26 = unitScale(_t22, _t23, _t24);
        double _t33 = _t19 * _t25;
        double _t34 = _t21 * _t25;
        double _t35 = _t20 * _t25;
        double _t36 = _t24 * _t26;
        double _t37 = _t22 * _t26;
        double _t38 = _t23 * _t26;
        double _t45 = Math.fma(_t16, _t33, -(_t17 * _t34));
        double _t46 = Math.fma(_t17, _t35, -(_t18 * _t33));
        double _t51_inv = 1.0 / Math.fma(_t46, _t36, Math.fma(_t45, _t37, -(Math.fma(_t16, _t35, -(_t18 * _t34)) * _t38)));
        double[] _col0 = invert_degenerate_general_s0_tail_s118b9478_c0(_t45, _t26 * _t51_inv, _t18, _t34, _t16, _t35, _t46);
        double[] _col1 = invert_degenerate_general_s0_tail_s118b9478_c2(_t17, _t36, _t16, _t38, _t25 * _t51_inv, _t37, _t18);
        double[] _col2 = invertProduct_general_s74c57dcd_tail_s4181cd16_c1(_t38, _t34, _t33, _t36, _t6 * _t51_inv, _t35, _t37);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_orthogonal(Double3x3 other, int _props) {
        double _t6 = unitScale(other.m20(), other.m21(), other.m22());
        return invertProduct_degenerate_orthogonal_s1c4ff883_tail(Math.fma(other.m20(), this.m12, Math.fma(other.m00(), this.m10, other.m10() * this.m11)), Math.fma(other.m21(), this.m12, Math.fma(other.m01(), this.m10, other.m11() * this.m11)), Math.fma(other.m22(), this.m12, Math.fma(other.m02(), this.m10, other.m12() * this.m11)), Math.fma(other.m20(), this.m02, Math.fma(other.m00(), this.m00, other.m10() * this.m01)), Math.fma(other.m21(), this.m02, Math.fma(other.m01(), this.m00, other.m11() * this.m01)), Math.fma(other.m22(), this.m02, Math.fma(other.m02(), this.m00, other.m12() * this.m01)), other.m22() * _t6, other.m21() * _t6, other.m20() * _t6, _t6, _props);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_identity_translation(Double3x3 other) {
        double _t0 = unitScale(1.0, 0.0, other.m02());
        double _t1 = unitScale(0.0, 1.0, other.m12());
        double _t2_inv = 1.0 / _t0;
        double _t3_inv = 1.0 / _t1;
        return new Double3x3(_t0 * _t2_inv, 0.0, -(other.m02() * _t0 * _t2_inv), 0.0, _t1 * _t3_inv, -(other.m12() * _t1 * _t3_inv), 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_identity_affine(Double3x3 other) {
        double _t0 = unitScale(other.m10(), other.m11(), other.m12());
        double _t1 = unitScale(other.m00(), other.m01(), other.m02());
        double _t8 = other.m11() * _t0;
        double _t9 = other.m00() * _t1;
        double _t10 = other.m01() * _t1;
        double _t11 = other.m10() * _t0;
        double _t12 = other.m12() * _t0;
        double _t13 = other.m02() * _t1;
        double _t16_inv = 1.0 / Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t0 * _t16_inv;
        double _sp0 = _t1 * _t16_inv;
        return new Double3x3(_t8 * _sp0, -(_t10 * _sp1), Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv, -(_t11 * _sp0), _t9 * _sp1, Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_translation_identity(Double3x3 other) {
        double _t0 = unitScale(1.0, 0.0, this.m02);
        double _t1 = unitScale(0.0, 1.0, this.m12);
        double _t2_inv = 1.0 / _t0;
        double _t3_inv = 1.0 / _t1;
        return new Double3x3(_t0 * _t2_inv, 0.0, -(this.m02 * _t0 * _t2_inv), 0.0, _t1 * _t3_inv, -(this.m12 * _t1 * _t3_inv), 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_translation_translation(Double3x3 other) {
        double _t0 = other.m02() + this.m02;
        double _t1 = other.m12() + this.m12;
        double _t2 = unitScale(1.0, 0.0, _t0);
        double _t3 = unitScale(0.0, 1.0, _t1);
        double _t4_inv = 1.0 / _t2;
        double _t5_inv = 1.0 / _t3;
        return new Double3x3(_t2 * _t4_inv, 0.0, -(_t0 * _t2 * _t4_inv), 0.0, _t3 * _t5_inv, -(_t1 * _t3 * _t5_inv), 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_translation_affine(Double3x3 other) {
        double _t0 = other.m12() + this.m12;
        double _t1 = other.m02() + this.m02;
        double _t2 = unitScale(other.m10(), other.m11(), _t0);
        double _t3 = unitScale(other.m00(), other.m01(), _t1);
        double _t8 = other.m11() * _t2;
        double _t9 = other.m00() * _t3;
        double _t10 = other.m01() * _t3;
        double _t11 = other.m10() * _t2;
        double _t14 = _t0 * _t2;
        double _t15 = _t1 * _t3;
        double _t18_inv = 1.0 / Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t2 * _t18_inv;
        double _sp0 = _t3 * _t18_inv;
        return new Double3x3(_t8 * _sp0, -(_t10 * _sp1), Math.fma(_t10, _t14, -(_t8 * _t15)) * _t18_inv, -(_t11 * _sp0), _t9 * _sp1, Math.fma(_t11, _t15, -(_t9 * _t14)) * _t18_inv, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_orthogonal_identity(int _props) {
        double _t0 = unitScale(this.m10, this.m11, this.m12);
        double _t1 = unitScale(this.m00, this.m01, this.m02);
        double _t8 = this.m11 * _t0;
        double _t9 = this.m00 * _t1;
        double _t10 = this.m01 * _t1;
        double _t11 = this.m10 * _t0;
        double _t12 = this.m12 * _t0;
        double _t13 = this.m02 * _t1;
        double _t16_inv = 1.0 / Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t0 * _t16_inv;
        double _sp0 = _t1 * _t16_inv;
        return new Double3x3(_t8 * _sp0, -(_t10 * _sp1), Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv, -(_t11 * _sp0), _t9 * _sp1, Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv, 0.0, 0.0, 1.0, _props);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_orthogonal_translation(Double3x3 other, int _props) {
        double _t2 = Math.fma(other.m02(), this.m10, Math.fma(other.m12(), this.m11, this.m12));
        double _t3 = Math.fma(other.m02(), this.m00, Math.fma(other.m12(), this.m01, this.m02));
        double _t4 = unitScale(this.m10, this.m11, _t2);
        double _t5 = unitScale(this.m00, this.m01, _t3);
        double _t10 = this.m11 * _t4;
        double _t11 = this.m00 * _t5;
        double _t12 = this.m01 * _t5;
        double _t13 = this.m10 * _t4;
        double _t16 = _t2 * _t4;
        double _t17 = _t3 * _t5;
        double _t20_inv = 1.0 / Math.fma(_t11, _t10, -(_t12 * _t13));
        double _sp1 = _t4 * _t20_inv;
        double _sp0 = _t5 * _t20_inv;
        return new Double3x3(_t10 * _sp0, -(_t12 * _sp1), Math.fma(_t12, _t16, -(_t10 * _t17)) * _t20_inv, -(_t13 * _sp0), _t11 * _sp1, Math.fma(_t13, _t17, -(_t11 * _t16)) * _t20_inv, 0.0, 0.0, 1.0, _props);
    }

    /** Private per-column body of {@code invertProduct_degenerate_orthogonal_affine_s1c4ff883_tail}; reached only through it. */
    private double[] invertProduct_degenerate_orthogonal_affine_s1c4ff883_tail_s20088037_c0(double _t18, double _sp0, double _t20) {
        return new double[] {_t18 * _sp0, -(_t20 * _sp0), 0.0};
    }

    /** Private per-column body of {@code invertProduct_degenerate_orthogonal_affine_s1c4ff883_tail}; reached only through it. */
    private double[] invertProduct_degenerate_orthogonal_affine_s1c4ff883_tail_s20088037_c2(double _t24, double _t21, double _t25, double _t18, double _t28_inv, double _t20, double _t19) {
        return new double[] {Math.fma(_t24, _t21, -(_t25 * _t18)) * _t28_inv, Math.fma(_t25, _t20, -(_t24 * _t19)) * _t28_inv, 1.0};
    }

    /** Private tail of {@code invertProduct_degenerate_orthogonal_affine}; reached only through it. */
    private Double3x3 invertProduct_degenerate_orthogonal_affine_s1c4ff883_tail(double _t13, double _t28_inv, double _t18, double _t21, double _sp1, double _t24, double _t25, double _t20, double _t19, int _props) {
        double[] _col0 = invertProduct_degenerate_orthogonal_affine_s1c4ff883_tail_s20088037_c0(_t18, _t13 * _t28_inv, _t20);
        double[] _col1 = new double[] {-(_t21 * _sp1), _t19 * _sp1, 0.0};
        double[] _col2 = invertProduct_degenerate_orthogonal_affine_s1c4ff883_tail_s20088037_c2(_t24, _t21, _t25, _t18, _t28_inv, _t20, _t19);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_orthogonal_affine(Double3x3 other, int _props) {
        double _t6 = Math.fma(other.m01(), this.m10, other.m11() * this.m11);
        double _t7 = Math.fma(other.m00(), this.m10, other.m10() * this.m11);
        double _t8 = Math.fma(other.m00(), this.m00, other.m10() * this.m01);
        double _t9 = Math.fma(other.m01(), this.m00, other.m11() * this.m01);
        double _t10 = Math.fma(other.m02(), this.m10, Math.fma(other.m12(), this.m11, this.m12));
        double _t11 = Math.fma(other.m02(), this.m00, Math.fma(other.m12(), this.m01, this.m02));
        double _t12 = unitScale(_t7, _t6, _t10);
        double _t13 = unitScale(_t8, _t9, _t11);
        double _t18 = _t6 * _t12;
        double _t19 = _t8 * _t13;
        double _t20 = _t7 * _t12;
        double _t21 = _t9 * _t13;
        double _t28_inv = 1.0 / Math.fma(_t19, _t18, -(_t20 * _t21));
        return invertProduct_degenerate_orthogonal_affine_s1c4ff883_tail(_t13, _t28_inv, _t18, _t21, _t12 * _t28_inv, _t10 * _t12, _t11 * _t13, _t20, _t19, _props);
    }

    /** Private per-column body of {@code invertProduct_degenerate_general_translation_s74c57dcd_tail}; reached only through it. */
    private double[] invertProduct_degenerate_general_translation_s74c57dcd_tail_sc01aa89_c1(double _t16, double _t26, double _t20, double _t24, double _sp1, double _t19, double _t18) {
        return new double[] {Math.fma(_t16, _t26, -(_t20 * _t24)) * _sp1, Math.fma(_t19, _t24, -(_t18 * _t26)) * _sp1, Math.fma(_t20, _t18, -(_t19 * _t16)) * _sp1};
    }

    /** Private per-column body of {@code invertProduct_degenerate_general_translation_s74c57dcd_tail}; reached only through it. */
    private double[] invertProduct_degenerate_general_translation_s74c57dcd_tail_sc01aa89_c2(double _t20, double _t25, double _t15, double _t26, double _sp2, double _t17, double _t19) {
        return new double[] {Math.fma(_t20, _t25, -(_t15 * _t26)) * _sp2, Math.fma(_t17, _t26, -(_t19 * _t25)) * _sp2, Math.fma(_t19, _t15, -(_t20 * _t17)) * _sp2};
    }

    /** Private tail of {@code invertProduct_degenerate_general_translation}; reached only through it. */
    private Double3x3 invertProduct_degenerate_general_translation_s74c57dcd_tail(double _t33, double _t26, double _t34, double _t19, double _t17, double _t24, double _t18, double _t25, double _t20, double _t7, double _t6, double _t8, double _t16, double _t15, int _props) {
        double _t39_inv = 1.0 / Math.fma(_t33, _t26, Math.fma(_t34, _t19, -(Math.fma(_t17, _t24, -(_t18 * _t25)) * _t20)));
        double[] _col0 = invert_degenerate_general_s0_tail_s118b9478_c0(_t34, _t8 * _t39_inv, _t18, _t25, _t17, _t24, _t33);
        double[] _col1 = invertProduct_degenerate_general_translation_s74c57dcd_tail_sc01aa89_c1(_t16, _t26, _t20, _t24, _t6 * _t39_inv, _t19, _t18);
        double[] _col2 = invertProduct_degenerate_general_translation_s74c57dcd_tail_sc01aa89_c2(_t20, _t25, _t15, _t26, _t7 * _t39_inv, _t17, _t19);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_general_translation(Double3x3 other) {
        double _t3 = Math.fma(other.m02(), this.m10, Math.fma(other.m12(), this.m11, this.m12));
        double _t4 = Math.fma(other.m02(), this.m20, Math.fma(other.m12(), this.m21, this.m22));
        double _t5 = Math.fma(other.m02(), this.m00, Math.fma(other.m12(), this.m01, this.m02));
        double _t6 = unitScale(this.m10, this.m11, _t3);
        double _t7 = unitScale(this.m20, this.m21, _t4);
        double _t8 = unitScale(this.m00, this.m01, _t5);
        double _t15 = this.m11 * _t6;
        double _t16 = this.m21 * _t7;
        double _t17 = this.m10 * _t6;
        double _t18 = this.m20 * _t7;
        double _t24 = _t4 * _t7;
        double _t25 = _t3 * _t6;
        return invertProduct_degenerate_general_translation_s74c57dcd_tail(Math.fma(_t17, _t16, -(_t15 * _t18)), _t5 * _t8, Math.fma(_t15, _t24, -(_t16 * _t25)), this.m00 * _t8, _t17, _t24, _t18, _t25, this.m01 * _t8, _t7, _t6, _t8, _t16, _t15, 0);
    }

    /** Private tail of {@code invertProduct_degenerate_general_affine}; reached only through it. */
    private Double3x3 invertProduct_degenerate_general_affine_s74c57dcd_tail(double _t10, double _t18, double _t12, double _t19, double _t9, double _t13, double _t20, double _t14, double _t15, double _t16, double _t17, double _t27, int _props) {
        double _t28 = _t10 * _t18;
        double _t29 = _t12 * _t19;
        double _t30 = _t9 * _t18;
        double _t31 = _t13 * _t20;
        double _t32 = _t14 * _t20;
        double _t36 = _t15 * _t18;
        double _t37 = _t16 * _t19;
        double _t38 = _t17 * _t20;
        double _t45 = Math.fma(_t29, _t28, -(_t30 * _t27));
        double _t46 = Math.fma(_t36, _t27, -(_t37 * _t28));
        double _t51_inv = 1.0 / Math.fma(_t45, _t38, Math.fma(_t46, _t31, -(Math.fma(_t36, _t29, -(_t37 * _t30)) * _t32)));
        double[] _col0 = invert_degenerate_general_s0_tail_s118b9478_c0(_t46, _t20 * _t51_inv, _t37, _t30, _t36, _t29, _t45);
        double[] _col1 = invertProduct_general_affine_s74c57dcd_tail_s37b9a40c_c1(_t38, _t28, _t36, _t32, _t19 * _t51_inv, _t31, _t30);
        double[] _col2 = invertProduct_general_affine_s74c57dcd_tail_s37b9a40c_c1(_t37, _t32, _t38, _t27, _t18 * _t51_inv, _t29, _t31);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate_general_affine(Double3x3 other) {
        double _t9 = Math.fma(other.m00(), this.m20, other.m10() * this.m21);
        double _t10 = Math.fma(other.m01(), this.m20, other.m11() * this.m21);
        double _t11 = Math.fma(other.m01(), this.m10, other.m11() * this.m11);
        double _t12 = Math.fma(other.m00(), this.m10, other.m10() * this.m11);
        double _t13 = Math.fma(other.m00(), this.m00, other.m10() * this.m01);
        double _t14 = Math.fma(other.m01(), this.m00, other.m11() * this.m01);
        double _t15 = Math.fma(other.m02(), this.m20, Math.fma(other.m12(), this.m21, this.m22));
        double _t16 = Math.fma(other.m02(), this.m10, Math.fma(other.m12(), this.m11, this.m12));
        double _t17 = Math.fma(other.m02(), this.m00, Math.fma(other.m12(), this.m01, this.m02));
        double _t19 = unitScale(_t12, _t11, _t16);
        return invertProduct_degenerate_general_affine_s74c57dcd_tail(_t10, unitScale(_t9, _t10, _t15), _t12, _t19, _t9, _t13, unitScale(_t13, _t14, _t17), _t14, _t15, _t16, _t17, _t11 * _t19, 0);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate(Double3x3 other) {
        int p = this.properties;
        int q = other.properties();
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, other.properties());
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
    private Double3x3 invertProduct_degenerate_s7500000e_tail(double _t22, double _t23, double _t21, double _t24, double _t25, double _t26, double _t18, double _t27, double _t20, double _t19, int _props) {
        double _t28 = unitScale(_t22, _t23, _t21);
        double _t29 = unitScale(_t24, _t25, _t26);
        double _t39 = _t18 * _t27;
        double _t40 = _t21 * _t28;
        double _t41 = _t23 * _t28;
        double _t42 = _t20 * _t27;
        double _t43 = _t19 * _t27;
        double _t44 = _t22 * _t28;
        double _t45 = _t26 * _t29;
        double _t46 = _t24 * _t29;
        double _t47 = _t25 * _t29;
        double _t54 = Math.fma(_t39, _t40, -(_t41 * _t42));
        double _t55 = Math.fma(_t43, _t41, -(_t44 * _t39));
        double _t60_inv = 1.0 / Math.fma(_t55, _t45, Math.fma(_t54, _t46, -(Math.fma(_t43, _t40, -(_t44 * _t42)) * _t47)));
        double[] _col0 = invert_degenerate_general_s0_tail_s118b9478_c0(_t54, _t29 * _t60_inv, _t44, _t42, _t43, _t40, _t55);
        double[] _col1 = invertProduct_general_s74c57dcd_tail_s4181cd16_c1(_t41, _t45, _t47, _t40, _t27 * _t60_inv, _t46, _t44);
        double[] _col2 = invertProduct_general_s74c57dcd_tail_s4181cd16_c1(_t47, _t42, _t39, _t45, _t28 * _t60_inv, _t43, _t46);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Degenerate-input path of {@code invertProduct}: its methods leave here when the determinant
     * or its reciprocal leaves the normal floating-point range (a singular matrix, one scaled far
     * from 1, NaN); reached only through them.
     */
    private Double3x3 invertProduct_degenerate(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
        double _t18 = Math.fma(m21, this.m12, Math.fma(m01, this.m10, m11 * this.m11));
        double _t19 = Math.fma(m20, this.m12, Math.fma(m00, this.m10, m10 * this.m11));
        double _t20 = Math.fma(m22, this.m12, Math.fma(m02, this.m10, m12 * this.m11));
        return invertProduct_degenerate_s7500000e_tail(Math.fma(m20, this.m22, Math.fma(m00, this.m20, m10 * this.m21)), Math.fma(m21, this.m22, Math.fma(m01, this.m20, m11 * this.m21)), Math.fma(m22, this.m22, Math.fma(m02, this.m20, m12 * this.m21)), Math.fma(m20, this.m02, Math.fma(m00, this.m00, m10 * this.m01)), Math.fma(m21, this.m02, Math.fma(m01, this.m00, m11 * this.m01)), Math.fma(m22, this.m02, Math.fma(m02, this.m00, m12 * this.m01)), _t18, unitScale(_t19, _t18, _t20), _t20, _t19, 0);
    }


    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Double3x3 normal_affine() {
        double _t3 = Math.fma(this.m00, this.m11, -(this.m01 * this.m10));
        if (!(java.lang.Math.abs(_t3) > 2.2250738585072014E-308 && java.lang.Math.abs(_t3) < 4.49423283715579E307)) return normal_degenerate();
        double _t3_inv = 1.0 / _t3;
        return new Double3x3(this.m11 * _t3_inv, -(this.m10 * _t3_inv), 0.0, -(this.m01 * _t3_inv), this.m00 * _t3_inv, 0.0, Math.fma(this.m01, this.m12, -(this.m02 * this.m11)) * _t3_inv, Math.fma(this.m02, this.m10, -(this.m00 * this.m12)) * _t3_inv, 1.0, 0);
    }

    /** Private per-column body of {@code normal_general}; reached only through it. */
    private double[] normal_general_s0_c0(double _t6, double _t13_inv) {
        return new double[] {_t6 * _t13_inv, Math.fma(this.m02, this.m21, -(this.m01 * this.m22)) * _t13_inv, Math.fma(this.m01, this.m12, -(this.m02 * this.m11)) * _t13_inv};
    }

    /** Private per-column body of {@code normal_general}; reached only through it. */
    private double[] normal_general_s0_c1(double _t13_inv) {
        return new double[] {Math.fma(this.m12, this.m20, -(this.m10 * this.m22)) * _t13_inv, Math.fma(this.m00, this.m22, -(this.m02 * this.m20)) * _t13_inv, Math.fma(this.m02, this.m10, -(this.m00 * this.m12)) * _t13_inv};
    }

    /** Private per-column body of {@code normal_general}; reached only through it. */
    private double[] normal_general_s0_c2(double _t7, double _t13_inv) {
        return new double[] {_t7 * _t13_inv, Math.fma(this.m01, this.m20, -(this.m00 * this.m21)) * _t13_inv, Math.fma(this.m00, this.m11, -(this.m01 * this.m10)) * _t13_inv};
    }


    /**
     * Private body of {@code normal}, specialized by runtime matrix properties; reached only
     * through the public {@code normal} dispatcher.
     */
    private Double3x3 normal_general() {
        double _t6 = Math.fma(this.m11, this.m22, -(this.m12 * this.m21));
        double _t7 = Math.fma(this.m10, this.m21, -(this.m11 * this.m20));
        double _t13 = Math.fma(this.m02, _t7, Math.fma(this.m00, _t6, -(this.m01 * Math.fma(this.m10, this.m22, -(this.m12 * this.m20)))));
        if (!(java.lang.Math.abs(_t13) > 2.2250738585072014E-308 && java.lang.Math.abs(_t13) < 4.49423283715579E307)) return normal_degenerate();
        double _t13_inv = 1.0 / _t13;
        double[] _col0 = normal_general_s0_c0(_t6, _t13_inv);
        double[] _col1 = normal_general_s0_c1(_t13_inv);
        double[] _col2 = normal_general_s0_c2(_t7, _t13_inv);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Compute the normal matrix of this matrix, i.e. the transpose of its inverse, returning the
     * result as a value.
     * <p>
     * Valid input: this matrix must be invertible.
     *
     * @return the resulting matrix
     */
    public Double3x3 normal() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, -this.m02, -this.m12, 1.0, 0);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return cofactor_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return normal_affine();
        return normal_general();
    }


    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double3x3 normal_degenerate_translation() {
        double _t0 = unitScale(1.0, 0.0, this.m02);
        double _t1 = unitScale(0.0, 1.0, this.m12);
        double _t2_inv = 1.0 / _t0;
        double _t3_inv = 1.0 / _t1;
        return new Double3x3(_t0 * _t2_inv, 0.0, 0.0, 0.0, _t1 * _t3_inv, 0.0, -(this.m02 * _t0 * _t2_inv), -(this.m12 * _t1 * _t3_inv), 1.0, 0);
    }


    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double3x3 normal_degenerate_orthogonal() {
        double _t0 = unitScale(this.m10, this.m11, this.m12);
        double _t1 = unitScale(this.m00, this.m01, this.m02);
        double _t8 = this.m11 * _t0;
        double _t9 = this.m00 * _t1;
        double _t10 = this.m01 * _t1;
        double _t11 = this.m10 * _t0;
        double _t12 = this.m12 * _t0;
        double _t13 = this.m02 * _t1;
        double _t16_inv = 1.0 / Math.fma(_t9, _t8, -(_t10 * _t11));
        double _sp1 = _t0 * _t16_inv;
        double _sp0 = _t1 * _t16_inv;
        return new Double3x3(_t8 * _sp0, -(_t11 * _sp0), 0.0, -(_t10 * _sp1), _t9 * _sp1, 0.0, Math.fma(_t10, _t12, -(_t13 * _t8)) * _t16_inv, Math.fma(_t13, _t11, -(_t9 * _t12)) * _t16_inv, 1.0, 0);
    }

    /**
     * Private per-column body of {@code normal_degenerate_general_s0_tail}. Shared by 2 identical
     * private paths of {@code normal}; reached only through it.
     */
    private double[] normal_degenerate_general_s0_tail_s4f20a9a_c0(double _t27, double _sp0, double _t18, double _t15, double _t20, double _t13, double _sp1, double _t14, double _t12, double _sp2) {
        return new double[] {_t27 * _sp0, Math.fma(_t18, _t15, -(_t20 * _t13)) * _sp1, Math.fma(_t20, _t14, -(_t18 * _t12)) * _sp2};
    }

    /** Private per-column body of {@code normal_degenerate_general_s0_tail}; reached only through it. */
    private double[] normal_degenerate_general_s0_tail_s4f20a9a_c1(double _t14, double _t17, double _t16, double _t13, double _sp0, double _t19, double _t18, double _sp1, double _sp2) {
        return new double[] {Math.fma(_t14, _t17, -(_t16 * _t13)) * _sp0, Math.fma(_t19, _t13, -(_t18 * _t17)) * _sp1, Math.fma(_t18, _t16, -(_t19 * _t14)) * _sp2};
    }

    /** Private tail of {@code normal_degenerate_general}; reached only through it. */
    private Double3x3 normal_degenerate_general_s0_tail(double _t2, double _t33_inv, double _t27, double _t14, double _t17, double _t16, double _t13, double _t28, double _t18, double _t15, double _t20, double _sp1, double _t19, double _t12, double _sp2, int _props) {
        double _sp0 = _t2 * _t33_inv;
        double[] _col0 = normal_degenerate_general_s0_tail_s4f20a9a_c0(_t27, _sp0, _t18, _t15, _t20, _t13, _sp1, _t14, _t12, _sp2);
        double[] _col1 = normal_degenerate_general_s0_tail_s4f20a9a_c1(_t14, _t17, _t16, _t13, _sp0, _t19, _t18, _sp1, _sp2);
        double[] _col2 = normal_degenerate_general_s0_tail_s4f20a9a_c0(_t28, _sp0, _t20, _t17, _t19, _t15, _sp1, _t12, _t16, _sp2);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double3x3 normal_degenerate_general() {
        double _t0 = unitScale(this.m10, this.m11, this.m12);
        double _t1 = unitScale(this.m20, this.m21, this.m22);
        double _t2 = unitScale(this.m00, this.m01, this.m02);
        double _t12 = this.m11 * _t0;
        double _t13 = this.m22 * _t1;
        double _t14 = this.m12 * _t0;
        double _t15 = this.m21 * _t1;
        double _t16 = this.m10 * _t0;
        double _t17 = this.m20 * _t1;
        double _t18 = this.m02 * _t2;
        double _t19 = this.m00 * _t2;
        double _t20 = this.m01 * _t2;
        double _t27 = Math.fma(_t12, _t13, -(_t14 * _t15));
        double _t28 = Math.fma(_t16, _t15, -(_t12 * _t17));
        double _t33_inv = 1.0 / Math.fma(_t28, _t18, Math.fma(_t27, _t19, -(Math.fma(_t16, _t13, -(_t14 * _t17)) * _t20)));
        return normal_degenerate_general_s0_tail(_t2, _t33_inv, _t27, _t14, _t17, _t16, _t13, _t28, _t18, _t15, _t20, _t0 * _t33_inv, _t19, _t12, _t1 * _t33_inv, 0);
    }


    /**
     * Degenerate-input path of {@code normal}: its methods leave here when the determinant or its
     * reciprocal leaves the normal floating-point range (a singular matrix, one scaled far from 1,
     * NaN); reached only through them.
     */
    private Double3x3 normal_degenerate() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
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
    public double trace() {
        return this.m22 + (this.m00 + this.m11);
    }


    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Double3x3 transpose_orthogonal() {
        return new Double3x3(this.m00, this.m10, 0.0, this.m01, this.m11, 0.0, this.m02, this.m12, 1.0, 0);
    }


    /**
     * Private body of {@code transpose}, specialized by runtime matrix properties; reached only
     * through the public {@code transpose} dispatcher.
     */
    private Double3x3 transpose_general() {
        return new Double3x3(this.m00, this.m10, this.m20, this.m01, this.m11, this.m21, this.m02, this.m12, this.m22, 0);
    }


    /**
     * Transpose this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Double3x3 transpose() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, this.m02, this.m12, 1.0, 0);
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
    public Double3x3 add(Double3x3 other) {
        return new Double3x3(other.m00() + this.m00, other.m01() + this.m01, other.m02() + this.m02, other.m10() + this.m10, other.m11() + this.m11, other.m12() + this.m12, other.m20() + this.m20, other.m21() + this.m21, other.m22() + this.m22, 0);
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
    public Double3x3 add(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
        return new Double3x3(m00 + this.m00, m01 + this.m01, m02 + this.m02, m10 + this.m10, m11 + this.m11, m12 + this.m12, m20 + this.m20, m21 + this.m21, m22 + this.m22, 0);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal(double scalar) {
        return new Double3x3(scalar * this.m00, scalar * this.m01, scalar * this.m02, scalar * this.m10, scalar * this.m11, scalar * this.m12, 0.0, 0.0, scalar, 0);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_general(double scalar) {
        return new Double3x3(scalar * this.m00, scalar * this.m01, scalar * this.m02, scalar * this.m10, scalar * this.m11, scalar * this.m12, scalar * this.m20, scalar * this.m21, scalar * this.m22, 0);
    }


    /**
     * Multiply each component of this matrix by {@code scalar}, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param scalar the factor to multiply each component by
     * @return the resulting matrix
     */
    public Double3x3 mul(double scalar) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(scalar, 0.0, 0.0, 0.0, scalar, 0.0, 0.0, 0.0, scalar, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(scalar, 0.0, scalar * this.m02, 0.0, scalar, scalar * this.m12, 0.0, 0.0, scalar, 0);
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
    public Double3x3 negate() {
        return new Double3x3(-this.m00, -this.m01, -this.m02, -this.m10, -this.m11, -this.m12, -this.m20, -this.m21, -this.m22, 0);
    }


    /**
     * Subtract {@code other} from this matrix, returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the matrix to subtract
     * @return the resulting matrix
     */
    public Double3x3 sub(Double3x3 other) {
        return new Double3x3(this.m00 - other.m00(), this.m01 - other.m01(), this.m02 - other.m02(), this.m10 - other.m10(), this.m11 - other.m11(), this.m12 - other.m12(), this.m20 - other.m20(), this.m21 - other.m21(), this.m22 - other.m22(), 0);
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
    public Double3x3 sub(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
        return new Double3x3(this.m00 - m00, this.m01 - m01, this.m02 - m02, this.m10 - m10, this.m11 - m11, this.m12 - m12, this.m20 - m20, this.m21 - m21, this.m22 - m22, 0);
    }


    /**
     * Create a new matrix from the given values.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the matrix to copy
     * @return the resulting matrix
     */
    public Double3x3 set(Double3x3 v) {
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
    public Double3x3 set(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
        return new Double3x3(m00, m01, m02, m10, m11, m12, m20, m21, m22);
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
    public Double3x3 set(Double2x2 m) {
        return new Double3x3(m.m00(), m.m01(), 0.0, m.m10(), m.m11(), 0.0, 0.0, 0.0, 1.0);
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
    public Double3x3 set(Double2x3 m) {
        return new Double3x3(m.m00(), m.m01(), m.m02(), m.m10(), m.m11(), m.m12(), 0.0, 0.0, 1.0);
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
    public Double3x3 set(Double3x4 m) {
        return new Double3x3(m.m00(), m.m01(), m.m02(), m.m10(), m.m11(), m.m12(), m.m20(), m.m21(), m.m22());
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
    public Double3x3 set(Double4x4 m) {
        return new Double3x3(m.m00(), m.m01(), m.m02(), m.m10(), m.m11(), m.m12(), m.m20(), m.m21(), m.m22());
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
    public Double3x3 withTranslation(Double2 t) {
        double tX = t.x();
        double tY = t.y();
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(1.0, 0.0, tX, 0.0, 1.0, tY, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double3x3(this.m00, this.m01, tX, this.m10, this.m11, tY, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return new Double3x3(this.m00, this.m01, tX, this.m10, this.m11, tY, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        return withTranslation_general(tX, tY);
    }


    /**
     * Private body of {@code withTranslation}, specialized by runtime matrix properties; reached
     * only through the public {@code withTranslation} dispatcher.
     */
    private Double3x3 withTranslation_general(double tX, double tY) {
        return new Double3x3(this.m00, this.m01, tX, this.m10, this.m11, tY, this.m20, this.m21, this.m22, 0);
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
    public Double3x3 withTranslation(double tX, double tY) {
        int p = this.properties;
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(1.0, 0.0, tX, 0.0, 1.0, tY, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double3x3(this.m00, this.m01, tX, this.m10, this.m11, tY, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return new Double3x3(this.m00, this.m01, tX, this.m10, this.m11, tY, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        return withTranslation_general(tX, tY);
    }


    /**
     * Convert this matrix to {@code float} precision, returning the result as a new instance.
     * <p>
     * The conversion may lose precision or range.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return a new {@code Float3x3} holding the result
     */
    public Float3x3 toFloat() {
        return new Float3x3((float) (this.m00), (float) (this.m01), (float) (this.m02), (float) (this.m10), (float) (this.m11), (float) (this.m12), (float) (this.m20), (float) (this.m21), (float) (this.m22));
    }


    /**
     * Create the given rigid transform's rotation block (the translation is dropped).
     * <p>
     * Valid input: the rotation of {@code r} must have unit length.
     *
     * @param r the rigid transform to convert
     * @return the resulting matrix
     */
    public static Double3x3 makeFromRigid(DoubleRigid r) {
        double rRX = r.rX();
        double rRY = r.rY();
        double rRZ = r.rZ();
        double rRW = r.rW();
        double _t0 = rRZ * rRZ;
        double _t1 = rRZ * rRW;
        double _t2 = rRY * rRW;
        return new Double3x3(Math.fma(-2.0, Math.fma(rRY, rRY, _t0), 1.0), 2.0 * Math.fma(rRX, rRY, -_t1), 2.0 * Math.fma(rRX, rRZ, _t2), 2.0 * Math.fma(rRX, rRY, _t1), Math.fma(-2.0, Math.fma(rRX, rRX, _t0), 1.0), 2.0 * Math.fma(rRY, rRZ, -(rRX * rRW)), 2.0 * Math.fma(rRX, rRZ, -_t2), 2.0 * Math.fma(rRX, rRW, rRY * rRZ), Math.fma(-2.0, Math.fma(rRX, rRX, rRY * rRY), 1.0), 0);
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
    public static Double3x3 makeFromRigid(double rTX, double rTY, double rTZ, double rRX, double rRY, double rRZ, double rRW) {
        double _t0 = rRZ * rRZ;
        double _t1 = rRZ * rRW;
        double _t2 = rRY * rRW;
        return new Double3x3(Math.fma(-2.0, Math.fma(rRY, rRY, _t0), 1.0), 2.0 * Math.fma(rRX, rRY, -_t1), 2.0 * Math.fma(rRX, rRZ, _t2), 2.0 * Math.fma(rRX, rRY, _t1), Math.fma(-2.0, Math.fma(rRX, rRX, _t0), 1.0), 2.0 * Math.fma(rRY, rRZ, -(rRX * rRW)), 2.0 * Math.fma(rRX, rRZ, -_t2), 2.0 * Math.fma(rRX, rRW, rRY * rRZ), Math.fma(-2.0, Math.fma(rRX, rRX, rRY * rRY), 1.0), 0);
    }


    /**
     * Create the given transform's linear block {@code R * S} (the translation is dropped).
     * <p>
     * Valid input: the rotation of {@code t} must have unit length.
     *
     * @param t the transform to convert
     * @return the resulting matrix
     */
    public static Double3x3 makeFromTransform(DoubleTransform t) {
        double tRX = t.rX();
        double tRY = t.rY();
        double tRZ = t.rZ();
        double tRW = t.rW();
        double tSX = t.sX();
        double tSY = t.sY();
        double tSZ = t.sZ();
        double _t0 = tSX + tSX;
        double _t1 = tSY + tSY;
        double _t2 = tSZ + tSZ;
        double _t3 = tRZ * tRZ;
        double _t4 = tRZ * tRW;
        double _t5 = tRY * tRW;
        return new Double3x3(Math.fma(-Math.fma(tRY, tRY, _t3), _t0, tSX), Math.fma(tRX, tRY, -_t4) * _t1, Math.fma(tRX, tRZ, _t5) * _t2, Math.fma(tRX, tRY, _t4) * _t0, Math.fma(-Math.fma(tRX, tRX, _t3), _t1, tSY), Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2, Math.fma(tRX, tRZ, -_t5) * _t0, Math.fma(tRX, tRW, tRY * tRZ) * _t1, Math.fma(-Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ), 0);
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
    public static Double3x3 makeFromTransform(double tTX, double tTY, double tTZ, double tRX, double tRY, double tRZ, double tRW, double tSX, double tSY, double tSZ) {
        double _t0 = tSX + tSX;
        double _t1 = tSY + tSY;
        double _t2 = tSZ + tSZ;
        double _t3 = tRZ * tRZ;
        double _t4 = tRZ * tRW;
        double _t5 = tRY * tRW;
        return new Double3x3(Math.fma(-Math.fma(tRY, tRY, _t3), _t0, tSX), Math.fma(tRX, tRY, -_t4) * _t1, Math.fma(tRX, tRZ, _t5) * _t2, Math.fma(tRX, tRY, _t4) * _t0, Math.fma(-Math.fma(tRX, tRX, _t3), _t1, tSY), Math.fma(tRY, tRZ, -(tRX * tRW)) * _t2, Math.fma(tRX, tRZ, -_t5) * _t0, Math.fma(tRX, tRW, tRY * tRZ) * _t1, Math.fma(-Math.fma(tRX, tRX, tRY * tRY), _t2, tSZ), 0);
    }


    /**
     * Private body of {@code to2x2}, specialized by runtime matrix properties; reached only through
     * the public {@code to2x2} dispatcher.
     */
    private Double2x2 to2x2_general() {
        return new Double2x2(this.m00, this.m01, this.m10, this.m11, 0);
    }


    /**
     * Extract the upper-left 2x2 linear block of this matrix (dropping the translation column and
     * the last row), returning the result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Double2x2 to2x2() {
        if ((this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double2x2(1.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
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
    public Double2x3 to2x3() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2x3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double2x3(1.0, 0.0, this.m02, 0.0, 1.0, this.m12, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return new Double2x3(this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, Joml.BIT_ORTHOGONAL);
        return new Double2x3(this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code to3x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x4} dispatcher.
     */
    private Double3x4 to3x4_orthogonal() {
        return new Double3x4(this.m00, this.m01, this.m02, 0.0, this.m10, this.m11, this.m12, 0.0, 0.0, 0.0, 1.0, 0.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code to3x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to3x4} dispatcher.
     */
    private Double3x4 to3x4_general() {
        return new Double3x4(this.m00, this.m01, this.m02, 0.0, this.m10, this.m11, this.m12, 0.0, this.m20, this.m21, this.m22, 0.0, Joml.BIT_AFFINE);
    }


    /**
     * Extend this matrix to a 3x4 matrix with a zero translation column, returning the result as a
     * value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Double3x4 to3x4() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x4(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x4(1.0, 0.0, this.m02, 0.0, 0.0, 1.0, this.m12, 0.0, 0.0, 0.0, 1.0, 0.0, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return to3x4_orthogonal();
        return to3x4_general();
    }


    /**
     * Private body of {@code to4x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to4x4} dispatcher.
     */
    private Double4x4 to4x4_orthogonal() {
        return new Double4x4(this.m00, this.m01, this.m02, 0.0, this.m10, this.m11, this.m12, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code to4x4}, specialized by runtime matrix properties; reached only through
     * the public {@code to4x4} dispatcher.
     */
    private Double4x4 to4x4_general() {
        return new Double4x4(this.m00, this.m01, this.m02, 0.0, this.m10, this.m11, this.m12, 0.0, this.m20, this.m21, this.m22, 0.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Extend this matrix to a 4x4 matrix, filling the missing cells with identity, returning the
     * result as a value.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the resulting matrix
     */
    public Double4x4 to4x4() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double4x4(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double4x4(1.0, 0.0, this.m02, 0.0, 0.0, 1.0, this.m12, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return to4x4_orthogonal();
        return to4x4_general();
    }

    /** Private tail of {@code toDualQuat_orthogonal}; reached only through it. */
    private DoubleDualQuat toDualQuat_orthogonal_s0_tail(double _t9, double _sp1, double _t13, double _t0, double _sp2, double _t8, double _t5, double _sp0, double _t14, double _sp3, double _t7, double _t12, double _t11, double _sfx0) {
        double _sfx1, _sfx2, _sfx3;
        if (_t9 > 0.0) {
            _sfx1 = _sp1 * _t13;
            _sfx2 = _sp3 * _t13;
            _sfx3 = 0.5 * java.lang.Math.sqrt(_t11);
        } else {
            if (this.m00 > _t0) {
                _sfx1 = _sp2 * _t8;
                _sfx2 = _sp1 * _t8;
                _sfx3 = -(_sp0 * _t8);
            } else {
                if (this.m11 > 1.0) {
                    _sfx1 = 0.5 * java.lang.Math.sqrt(_t5);
                    _sfx2 = _sp0 * _t7;
                    _sfx3 = _sp1 * _t7;
                } else {
                    _sfx1 = _sp0 * _t14;
                    _sfx2 = 0.5 * java.lang.Math.sqrt(_t12);
                    _sfx3 = _sp3 * _t14;
                }
            }
        }
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0, 0.0, 0.0, 0.0);
    }


    /**
     * Private body of {@code toDualQuat}, specialized by runtime matrix properties; reached only
     * through the public {@code toDualQuat} dispatcher.
     */
    private DoubleDualQuat toDualQuat_orthogonal() {
        double _sp1 = 0.5 * this.m02;
        double _sp0 = 0.5 * this.m12;
        double _t0 = java.lang.Math.max(this.m11, 1.0);
        double _t3 = this.m00 - this.m11;
        double _sp2 = 0.5 * (this.m01 + this.m10);
        double _t5 = this.m11 - this.m00;
        double _t7 = (1.0 / java.lang.Math.sqrt(_t5));
        double _t9 = 1.0 + (this.m00 + this.m11);
        double _t11 = 1.0 + _t9;
        double _t12 = 1.0 + (1.0 - this.m00 - this.m11);
        double _t13 = (1.0 / java.lang.Math.sqrt(_t11));
        double _t14 = (1.0 / java.lang.Math.sqrt(_t12));
        double _sfx0 = _t9 > 0.0 ? -(_sp0 * _t13) : this.m00 > _t0 ? 0.5 * java.lang.Math.sqrt(_t3) : this.m11 > 1.0 ? _sp2 * _t7 : _sp1 * _t14;
        return toDualQuat_orthogonal_s0_tail(_t9, _sp1, _t13, _t0, _sp2, (1.0 / java.lang.Math.sqrt(_t3)), _t5, _sp0, _t14, 0.5 * (this.m10 - this.m01), _t7, _t12, _t11, _sfx0);
    }

    /** Private tail of {@code toDualQuat_general}; reached only through it. */
    private DoubleDualQuat toDualQuat_general_s0_tail(double _t13, double _sp0, double _t3, double _t4, double _t15, double _sp1, double _t5, double _sp2, double _t6, double _t7, double _sp3, double _t16, double _t8, double _t9, double _t17, double _t14) {
        double _sfx0, _sfx1, _sfx2;
        if (_t13 > 0.0) {
            _sfx0 = _sp0 * _t3;
            _sfx1 = _sp0 * _t7;
            _sfx2 = _sp0 * _t9;
        } else {
            if (this.m00 > _t4) {
                _sfx0 = 0.5 * java.lang.Math.sqrt(_t15);
                _sfx1 = _sp3 * _t5;
                _sfx2 = _sp3 * _t6;
            } else {
                if (this.m11 > this.m22) {
                    _sfx0 = _sp1 * _t5;
                    _sfx1 = 0.5 * java.lang.Math.sqrt(_t16);
                    _sfx2 = _sp1 * _t8;
                } else {
                    _sfx0 = _sp2 * _t6;
                    _sfx1 = _sp2 * _t8;
                    _sfx2 = 0.5 * java.lang.Math.sqrt(_t17);
                }
            }
        }
        return toDualQuat_general_s0_tail2(_t13, _t14, _t4, _sp3, _t3, _sp1, _t7, _sp2, _t9, _sfx0, _sfx1, _sfx2);
    }

    /** Private tail of {@code toDualQuat_general}; reached only through it. */
    private DoubleDualQuat toDualQuat_general_s0_tail2(double _t13, double _t14, double _t4, double _sp3, double _t3, double _sp1, double _t7, double _sp2, double _t9, double _sfx0, double _sfx1, double _sfx2) {
        double _sfx3 = _t13 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t14) : this.m00 > _t4 ? _sp3 * _t3 : this.m11 > this.m22 ? _sp1 * _t7 : _sp2 * _t9;
        return new DoubleDualQuat(_sfx0, _sfx1, _sfx2, _sfx3, 0.0, 0.0, 0.0, 0.0);
    }


    /**
     * Private body of {@code toDualQuat}, specialized by runtime matrix properties; reached only
     * through the public {@code toDualQuat} dispatcher.
     */
    private DoubleDualQuat toDualQuat_general() {
        double _t1 = 1.0 - this.m00;
        double _t13 = this.m22 + (this.m00 + this.m11);
        double _t14 = 1.0 + _t13;
        double _t15 = this.m00 + (1.0 - this.m11 - this.m22);
        double _t16 = this.m11 + (_t1 - this.m22);
        double _t17 = this.m22 + (_t1 - this.m11);
        return toDualQuat_general_s0_tail(_t13, 0.5 * (1.0 / java.lang.Math.sqrt(_t14)), this.m21 - this.m12, java.lang.Math.max(this.m11, this.m22), _t15, 0.5 * (1.0 / java.lang.Math.sqrt(_t16)), this.m01 + this.m10, 0.5 * (1.0 / java.lang.Math.sqrt(_t17)), this.m02 + this.m20, this.m02 - this.m20, 0.5 * (1.0 / java.lang.Math.sqrt(_t15)), _t16, this.m12 + this.m21, this.m10 - this.m01, _t17, _t14);
    }


    /**
     * Convert this matrix (assumed orthonormal) to a pure-rotation dual quaternion, returning the
     * result as a value.
     * <p>
     * Valid input: this matrix must be a rotation matrix.
     *
     * @return the resulting dual quaternion
     */
    public DoubleDualQuat toDualQuat() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new DoubleDualQuat(0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new DoubleDualQuat(-(0.25 * this.m12), 0.25 * this.m02, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return toDualQuat_orthogonal();
        return toDualQuat_general();
    }


    /**
     * Private body of {@code toRigid}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code toRigid}; reached only through it.
     */
    private DoubleRigid toRigid_identity() {
        return new DoubleRigid(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0);
    }

    /**
     * Private tail of {@code toRigid_translation}. Shared by 2 identical private paths of
     * {@code toRigid}; reached only through it.
     */
    private DoubleRigid toRigid_translation_s0_tail(double _t18, double _t13, double _sp0, double _t19, double _t8, double _t4, double _t11, double _t3, double _sp1, double _t15, double _t12, double _t16, double _t17) {
        double _t20 = (1.0 / java.lang.Math.sqrt(_t18));
        if (_t13 > 0.0) {
            return new DoubleRigid(0.0, 0.0, 0.0, -(_sp0 * _t19), _sp1 * _t19, 0.0, 0.5 * java.lang.Math.sqrt(_t17));
        } else {
            if (_t8 > _t4) {
                return new DoubleRigid(0.0, 0.0, 0.0, 0.5 * java.lang.Math.sqrt(_t11), 0.0, _sp1 * _t12, -(_sp0 * _t12));
            } else {
                if (1.0 > _t3) {
                    return new DoubleRigid(0.0, 0.0, 0.0, 0.0, 0.5 * java.lang.Math.sqrt(_t15), _sp0 * _t16, _sp1 * _t16);
                } else {
                    return new DoubleRigid(0.0, 0.0, 0.0, _sp1 * _t20, _sp0 * _t20, 0.5 * java.lang.Math.sqrt(_t18), 0.0);
                }
            }
        }
    }


    /**
     * Private body of {@code toRigid}, specialized by runtime matrix properties; reached only
     * through the public {@code toRigid} dispatcher.
     */
    private DoubleRigid toRigid_translation() {
        double _ct0 = Math.fma(this.m02, this.m02, Math.fma(this.m12, this.m12, 1.0));
        if (!(_ct0 > 2.2250738585072014E-308 && _ct0 < Double.POSITIVE_INFINITY)) return toRigid_degenerate();
        double _t3 = (1.0 / java.lang.Math.sqrt(_ct0));
        double _t8 = _t3 < 0.0 ? -1.0 : 1.0;
        double _t11 = _t8 - _t3;
        double _t13 = 1.0 + _t8 + _t3;
        double _t15 = 2.0 - _t8 - _t3;
        double _t17 = 1.0 + _t13;
        return toRigid_translation_s0_tail(1.0 + _t3 - _t8 - 1.0, _t13, 0.5 * this.m12 * _t3, (1.0 / java.lang.Math.sqrt(_t17)), _t8, java.lang.Math.max(1.0, _t3), _t11, _t3, 0.5 * this.m02 * _t3, _t15, (1.0 / java.lang.Math.sqrt(_t11)), (1.0 / java.lang.Math.sqrt(_t15)), _t17);
    }


    /**
     * Private body of {@code toRigid}, specialized by runtime matrix properties; reached only
     * through the public {@code toRigid} dispatcher.
     */
    private DoubleRigid toRigid_general() {
        double _ct1 = Math.fma(this.m21, this.m21, Math.fma(this.m01, this.m01, this.m11 * this.m11));
        if (!(_ct1 > 2.2250738585072014E-308 && _ct1 < Double.POSITIVE_INFINITY)) return toRigid_degenerate();
        double _t15 = (1.0 / java.lang.Math.sqrt(_ct1));
        double _ct2 = Math.fma(this.m22, this.m22, Math.fma(this.m02, this.m02, this.m12 * this.m12));
        if (!(_ct2 > 2.2250738585072014E-308 && _ct2 < Double.POSITIVE_INFINITY)) return toRigid_degenerate();
        double _t16 = (1.0 / java.lang.Math.sqrt(_ct2));
        double _ct3 = Math.fma(this.m20, this.m20, Math.fma(this.m00, this.m00, this.m10 * this.m10));
        if (!(_ct3 > 2.2250738585072014E-308 && _ct3 < Double.POSITIVE_INFINITY)) return toRigid_degenerate();
        double _t17 = (1.0 / java.lang.Math.sqrt(_ct3));
        double _t20 = this.m12 * _t16;
        double _t23 = this.m21 * _t15;
        return toRigid_general_sd4c4a30_1(_t15, _t16, -this.m11, -this.m22, this.m10 * _t17, this.m22 * _t16, _t20, this.m20 * _t17, _t23, this.m11 * _t15, this.m00 * _t17, Math.fma(this.m12, _t16, _t23), Math.fma(this.m21, _t15, -_t20));
    }

    /** Piece 2 of {@code toRigid_general}, split to fit the inline budget; reached only through it. */
    private DoubleRigid toRigid_general_sd4c4a30_1(double _t15, double _t16, double _t0, double _t1, double _t18, double _t19, double _t20, double _t21, double _t23, double _t24, double _t26, double _t31, double _t35) {
        double _t47, _t48, _t49;
        if (Math.fma(-Math.fma(_t18, _t19, -(_t20 * _t21)), this.m01 * _t15, Math.fma(Math.fma(_t18, _t23, -(_t24 * _t21)), this.m02 * _t16, Math.fma(_t24, _t19, -(_t20 * _t23)) * _t26)) < 0.0) {
            _t47 = -_t26;
            _t48 = -_t18;
            _t49 = -_t21;
        } else {
            _t47 = _t26;
            _t48 = _t18;
            _t49 = _t21;
        }
        double _t51 = 1.0 + _t47;
        double _t52 = 1.0 - _t47;
        double _t63 = Math.fma(this.m11, _t15, Math.fma(this.m22, _t16, _t51));
        double _t65 = Math.fma(this.m11, _t15, Math.fma(_t1, _t16, _t52));
        double _t66 = Math.fma(this.m22, _t16, Math.fma(_t0, _t15, _t52));
        double _t67 = Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t51));
        return toRigid_general_sd4c4a30_2(_t15, _t16, _t19, _t24, _t31, _t35, _t47, Math.fma(this.m01, _t15, _t48), Math.fma(this.m02, _t16, _t49), Math.fma(this.m02, _t16, -_t49), Math.fma(-this.m01, _t15, _t48), _t63, 0.5 * (1.0 / java.lang.Math.sqrt(_t63)), _t65, _t66, _t67, 0.5 * (1.0 / java.lang.Math.sqrt(_t65)), 0.5 * (1.0 / java.lang.Math.sqrt(_t66)), 0.5 * (1.0 / java.lang.Math.sqrt(_t67)), 0.0, 0.0, 0.0);
    }

    /** Piece 3 of {@code toRigid_general}, split to fit the inline budget; reached only through it. */
    private DoubleRigid toRigid_general_sd4c4a30_2(double _t15, double _t16, double _t19, double _t24, double _t31, double _t35, double _t47, double _t54, double _t55, double _t56, double _t57, double _t63, double _sp0, double _t65, double _t66, double _t67, double _sp1, double _sp2, double _sp3, double _sfx0, double _sfx1, double _sfx2) {
        double _sfx3, _sfx4, _sfx5, _sfx6;
        if (Math.fma(this.m11, _t15, Math.fma(this.m22, _t16, _t47)) > 0.0) {
            _sfx3 = _sp0 * _t35;
            _sfx4 = _sp0 * _t56;
            _sfx5 = _sp0 * _t57;
            _sfx6 = 0.5 * java.lang.Math.sqrt(_t63);
        } else {
            if (_t47 > java.lang.Math.max(_t24, _t19)) {
                _sfx3 = 0.5 * java.lang.Math.sqrt(_t67);
                _sfx4 = _sp3 * _t54;
                _sfx5 = _sp3 * _t55;
                _sfx6 = _sp3 * _t35;
            } else {
                if (_t24 > _t19) {
                    _sfx3 = _sp1 * _t54;
                    _sfx4 = 0.5 * java.lang.Math.sqrt(_t65);
                    _sfx5 = _sp1 * _t31;
                    _sfx6 = _sp1 * _t56;
                } else {
                    _sfx3 = _sp2 * _t55;
                    _sfx4 = _sp2 * _t31;
                    _sfx5 = 0.5 * java.lang.Math.sqrt(_t66);
                    _sfx6 = _sp2 * _t57;
                }
            }
        }
        return new DoubleRigid(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6);
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
    public DoubleRigid toRigid() {
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
    private DoubleRigid toRigid_degenerate_translation() {
        double _t0 = unitScale(this.m02, this.m12, 1.0);
        double _t4 = this.m02 * _t0;
        double _t5 = this.m12 * _t0;
        double _t8 = Math.fma(_t0, _t0, Math.fma(_t4, _t4, _t5 * _t5));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t13, _sp0, _sp1;
        if (_t8 <= 0.0) {
            _t13 = 1.0;
            _sp0 = 0.0;
            _sp1 = 0.0;
        } else {
            _t13 = _t9 * _t0;
            _sp0 = 0.5 * _t9 * _t5;
            _sp1 = 0.5 * _t9 * _t4;
        }
        double _t18 = _t13 < 0.0 ? -1.0 : 1.0;
        double _t21 = _t18 - _t13;
        double _t23 = 1.0 + _t18 + _t13;
        double _t25 = 2.0 - _t18 - _t13;
        double _t27 = 1.0 + _t23;
        return toRigid_translation_s0_tail(1.0 + _t13 - _t18 - 1.0, _t23, _sp0, (1.0 / java.lang.Math.sqrt(_t27)), _t18, java.lang.Math.max(1.0, _t13), _t21, _t13, _sp1, _t25, (1.0 / java.lang.Math.sqrt(_t21)), (1.0 / java.lang.Math.sqrt(_t25)), _t27);
    }

    /** Private tail of {@code toRigid_degenerate_general}; reached only through it. */
    private DoubleRigid toRigid_degenerate_general_s0_tail(double _t31, double _t15, double _t16, double _t32, double _t13, double _t12, double _t14, double _t33, double _t34, double _t35, double _t36, double _t27, double _t28, double _t29) {
        double _t37 = _t31 * _t15;
        double _t38 = _t31 * _t16;
        double _t39 = _t32 * _t13;
        double _t40 = _t32 * _t12;
        double _t41 = _t32 * _t14;
        double _t50 = java.lang.Math.abs(_t40);
        double _t51 = java.lang.Math.abs(_t39);
        double _t72, _t75, _t87;
        if (java.lang.Math.abs(_t33) < java.lang.Math.abs(_t34)) {
            _t72 = _t35;
            _t75 = 0.0;
            _t87 = -_t34;
        } else {
            _t72 = 0.0;
            _t75 = -_t35;
            _t87 = _t33;
        }
        double _t73, _t76, _t88;
        if (java.lang.Math.abs(_t37) < java.lang.Math.abs(_t38)) {
            _t73 = _t36;
            _t76 = 0.0;
            _t88 = -_t38;
        } else {
            _t73 = 0.0;
            _t76 = -_t36;
            _t88 = _t37;
        }
        double _t74, _t77;
        if (_t50 < _t51) {
            _t74 = _t41;
            _t77 = 0.0;
        } else {
            _t74 = 0.0;
            _t77 = -_t41;
        }
        return toRigid_degenerate_general_s0_tail2(_t50, _t51, _t39, _t40, _t75, _t87, _t72, _t76, _t88, _t73, _t77, _t74, _t27, _t28, _t29, _t36, _t37, _t33, _t35, _t34, _t38, _t41);
    }

    /** Private tail of {@code toRigid_degenerate_general}; reached only through it. */
    private DoubleRigid toRigid_degenerate_general_s0_tail2(double _t50, double _t51, double _t39, double _t40, double _t75, double _t87, double _t72, double _t76, double _t88, double _t73, double _t77, double _t74, double _t27, double _t28, double _t29, double _t36, double _t37, double _t33, double _t35, double _t34, double _t38, double _t41) {
        double _t89 = _t50 < _t51 ? -_t39 : _t40;
        double _t99 = (1.0 / java.lang.Math.sqrt(Math.fma(_t75, _t75, Math.fma(_t87, _t87, _t72 * _t72))));
        double _t100 = (1.0 / java.lang.Math.sqrt(Math.fma(_t76, _t76, Math.fma(_t88, _t88, _t73 * _t73))));
        double _t101 = (1.0 / java.lang.Math.sqrt(Math.fma(_t77, _t77, Math.fma(_t89, _t89, _t74 * _t74))));
        return toRigid_degenerate_general_s0_tail3(_t27, _t28, _t29, _t99 * _t72, _t36, _t100 * _t76, _t37, _t100 * _t88, _t33, _t35, _t39, _t101 * _t89, _t34, _t99 * _t75, _t40, _t99 * _t87, _t100 * _t73, _t38, _t41, _t101 * _t74, _t101 * _t77);
    }

    /** Private tail of {@code toRigid_degenerate_general}; reached only through it. */
    private DoubleRigid toRigid_degenerate_general_s0_tail3(double _t27, double _t28, double _t29, double _t102, double _t36, double _t105, double _t37, double _t114, double _t33, double _t35, double _t39, double _t115, double _t34, double _t106, double _t40, double _t116, double _t103, double _t38, double _t41, double _t104, double _t107) {
        double _t165, _t166;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t165 = 0.0;
                    _t166 = 0.0;
                } else {
                    _t165 = _t102;
                    _t166 = Math.fma(_t33, _t102, -(_t34 * _t106));
                }
            } else {
                _t165 = _t29 <= 0.0 ? Math.fma(_t36, _t105, -(_t37 * _t114)) : Math.fma(_t33, _t36, -(_t35 * _t37));
                _t166 = _t36;
            }
        } else {
            _t165 = _t39;
            _t166 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t115 : Math.fma(_t33, _t39, -(_t34 * _t40)) : _t36;
        }
        return toRigid_degenerate_general_s0_tail4(_t27, _t28, _t29, _t116, _t37, _t103, _t38, _t105, _t34, _t33, _t41, _t104, _t35, _t106, _t40, _t39, _t115, _t36, _t114, _t107, _t102, _t166, _t165);
    }

    /** Private tail of {@code toRigid_degenerate_general}; reached only through it. */
    private DoubleRigid toRigid_degenerate_general_s0_tail4(double _t27, double _t28, double _t29, double _t116, double _t37, double _t103, double _t38, double _t105, double _t34, double _t33, double _t41, double _t104, double _t35, double _t106, double _t40, double _t39, double _t115, double _t36, double _t114, double _t107, double _t102, double _t166, double _t165) {
        double _t167, _t168;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t167 = 1.0;
                    _t168 = 0.0;
                } else {
                    _t167 = _t116;
                    _t168 = Math.fma(_t35, _t106, -(_t33 * _t116));
                }
            } else {
                _t167 = _t29 <= 0.0 ? Math.fma(_t37, _t103, -(_t38 * _t105)) : Math.fma(_t34, _t37, -(_t33 * _t38));
                _t168 = _t38;
            }
        } else {
            _t167 = _t41;
            _t168 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t104 : Math.fma(_t35, _t40, -(_t33 * _t41)) : _t38;
        }
        return toRigid_degenerate_general_s0_tail5(_t29, _t27, _t28, _t105, _t39, _t115, _t41, _t104, _t36, _t38, _t33, _t106, _t114, _t103, _t35, _t34, _t40, _t107, _t116, _t102, _t37, _t166, _t167, _t165, _t168);
    }

    /** Private tail of {@code toRigid_degenerate_general}; reached only through it. */
    private DoubleRigid toRigid_degenerate_general_s0_tail5(double _t29, double _t27, double _t28, double _t105, double _t39, double _t115, double _t41, double _t104, double _t36, double _t38, double _t33, double _t106, double _t114, double _t103, double _t35, double _t34, double _t40, double _t107, double _t116, double _t102, double _t37, double _t166, double _t167, double _t165, double _t168) {
        double _t169, _t170;
        if (_t29 <= 0.0) {
            if (_t27 <= 0.0) {
                if (_t28 <= 0.0) {
                    _t169 = 0.0;
                    _t170 = 0.0;
                } else {
                    _t169 = _t105;
                    _t170 = Math.fma(_t38, _t114, -(_t36 * _t103));
                }
            } else {
                _t169 = _t28 <= 0.0 ? Math.fma(_t39, _t115, -(_t41 * _t104)) : Math.fma(_t39, _t36, -(_t41 * _t38));
                _t170 = _t40;
            }
        } else {
            _t169 = _t33;
            _t170 = _t27 <= 0.0 ? _t28 <= 0.0 ? _t106 : Math.fma(_t35, _t38, -(_t34 * _t36)) : _t40;
        }
        return toRigid_degenerate_general_s0_tail6(_t28, _t29, _t27, _t107, _t34, _t116, _t35, _t102, _t41, _t39, _t37, _t114, _t40, _t104, _t38, _t103, _t115, _t36, _t170, _t166, _t167, _t165, _t168, _t169);
    }

    /** Private tail of {@code toRigid_degenerate_general}; reached only through it. */
    private DoubleRigid toRigid_degenerate_general_s0_tail6(double _t28, double _t29, double _t27, double _t107, double _t34, double _t116, double _t35, double _t102, double _t41, double _t39, double _t37, double _t114, double _t40, double _t104, double _t38, double _t103, double _t115, double _t36, double _t170, double _t166, double _t167, double _t165, double _t168, double _t169) {
        double _t171, _t172;
        if (_t28 <= 0.0) {
            if (_t29 <= 0.0) {
                if (_t27 <= 0.0) {
                    _t171 = 1.0;
                    _t172 = 0.0;
                } else {
                    _t171 = _t107;
                    _t172 = Math.fma(_t40, _t104, -(_t39 * _t107));
                }
            } else {
                _t171 = _t27 <= 0.0 ? Math.fma(_t34, _t116, -(_t35 * _t102)) : Math.fma(_t34, _t41, -(_t35 * _t39));
                _t172 = _t35;
            }
        } else {
            _t171 = _t37;
            _t172 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t114 : Math.fma(_t40, _t38, -(_t39 * _t37)) : _t35;
        }
        return toRigid_degenerate_general_s0_tail7(_t29, _t27, _t28, _t103, _t41, _t107, _t40, _t115, _t37, _t36, _t34, _t170, _t166, _t167, _t171, _t165, _t168, _t169, _t172);
    }

    /** Private tail of {@code toRigid_degenerate_general}; reached only through it. */
    private DoubleRigid toRigid_degenerate_general_s0_tail7(double _t29, double _t27, double _t28, double _t103, double _t41, double _t107, double _t40, double _t115, double _t37, double _t36, double _t34, double _t170, double _t166, double _t167, double _t171, double _t165, double _t168, double _t169, double _t172) {
        double _t173 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t28 <= 0.0 ? 1.0 : _t103 : _t28 <= 0.0 ? Math.fma(_t41, _t107, -(_t40 * _t115)) : Math.fma(_t41, _t37, -(_t40 * _t36)) : _t34;
        double _t194, _t195, _t196;
        if (Math.fma(Math.fma(_t165, _t166, -(_t167 * _t168)), _t169, Math.fma(Math.fma(_t170, _t168, -(_t165 * _t171)), _t172, Math.fma(_t167, _t171, -(_t170 * _t166)) * _t173)) < 0.0) {
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
    private DoubleRigid toRigid_degenerate_general_s0_tail8(double _t195, double _t165, double _t194, double _t167, double _t171, double _t182, double _t183, double _t199, double _t200, double _t201, double _t184) {
        double _t206 = _t194 + _t167 + _t171;
        double _t207 = 1.0 + _t206;
        double _t208 = 1.0 + _t194 - _t167 - _t171;
        double _t209 = 1.0 + _t167 - _t194 - _t171;
        double _t210 = 1.0 + _t171 - _t194 - _t167;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t207));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t209));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sfx3 = _t206 > 0.0 ? _sp0 * _t182 : _t194 > _t183 ? 0.5 * java.lang.Math.sqrt(_t208) : _t167 > _t171 ? _sp1 * _t199 : _sp2 * _t200;
        return toRigid_degenerate_general_s0_tail9(_t206, _sp0, _t201, _t194, _t183, 0.5 * (1.0 / java.lang.Math.sqrt(_t208)), _t199, _t167, _t171, _t209, _sp2, _t184, _t195 - _t165, _t200, _sp1, _t210, _t207, _t182, 0.0, 0.0, 0.0, _sfx3);
    }

    /** Private tail of {@code toRigid_degenerate_general}; reached only through it. */
    private DoubleRigid toRigid_degenerate_general_s0_tail9(double _t206, double _sp0, double _t201, double _t194, double _t183, double _sp3, double _t199, double _t167, double _t171, double _t209, double _sp2, double _t184, double _t202, double _t200, double _sp1, double _t210, double _t207, double _t182, double _sfx0, double _sfx1, double _sfx2, double _sfx3) {
        double _sfx4, _sfx5, _sfx6;
        if (_t206 > 0.0) {
            _sfx4 = _sp0 * _t201;
            _sfx5 = _sp0 * _t202;
            _sfx6 = 0.5 * java.lang.Math.sqrt(_t207);
        } else {
            if (_t194 > _t183) {
                _sfx4 = _sp3 * _t199;
                _sfx5 = _sp3 * _t200;
                _sfx6 = _sp3 * _t182;
            } else {
                if (_t167 > _t171) {
                    _sfx4 = 0.5 * java.lang.Math.sqrt(_t209);
                    _sfx5 = _sp1 * _t184;
                    _sfx6 = _sp1 * _t201;
                } else {
                    _sfx4 = _sp2 * _t184;
                    _sfx5 = 0.5 * java.lang.Math.sqrt(_t210);
                    _sfx6 = _sp2 * _t202;
                }
            }
        }
        return new DoubleRigid(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6);
    }


    /**
     * Degenerate-input path of {@code toRigid}: its methods leave here when a column of the linear
     * block is zero or its squared length leaves the normal floating-point range (or is NaN);
     * reached only through them.
     */
    private DoubleRigid toRigid_degenerate_general() {
        double _t0 = unitScale(this.m01, this.m11, this.m21);
        double _t1 = unitScale(this.m02, this.m12, this.m22);
        double _t2 = unitScale(this.m00, this.m10, this.m20);
        double _t12 = this.m21 * _t0;
        double _t13 = this.m01 * _t0;
        double _t14 = this.m11 * _t0;
        double _t15 = this.m22 * _t1;
        double _t16 = this.m02 * _t1;
        double _t17 = this.m12 * _t1;
        double _t18 = this.m20 * _t2;
        double _t19 = this.m00 * _t2;
        double _t20 = this.m10 * _t2;
        double _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t31 = (1.0 / java.lang.Math.sqrt(_t28));
        return toRigid_degenerate_general_s0_tail(_t31, _t15, _t16, (1.0 / java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t30 * _t20, _t31 * _t17, _t27, _t28, _t29);
    }


    /**
     * Degenerate-input path of {@code toRigid}: its methods leave here when a column of the linear
     * block is zero or its squared length leaves the normal floating-point range (or is NaN);
     * reached only through them.
     */
    private DoubleRigid toRigid_degenerate() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return toRigid_identity();
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return toRigid_degenerate_translation();
        return toRigid_degenerate_general();
    }


    /**
     * Private body of {@code toTransform}, specialized by runtime matrix properties. Shared by 2
     * identical private paths of {@code toTransform}; reached only through it.
     */
    private DoubleTransform toTransform_identity() {
        return new DoubleTransform(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0);
    }

    /** Private tail of {@code toTransform_translation}; reached only through it. */
    private DoubleTransform toTransform_translation_s0_tail(double _t18, double _t13, double _sp0, double _t19, double _t8, double _t4, double _t11, double _t3, double _sp1, double _t15, double _t12, double _t16, double _t17, double _t2) {
        double _t20 = (1.0 / java.lang.Math.sqrt(_t18));
        if (_t13 > 0.0) {
            return new DoubleTransform(0.0, 0.0, 0.0, -(_sp0 * _t19), _sp1 * _t19, 0.0, 0.5 * java.lang.Math.sqrt(_t17), _t8, 1.0, java.lang.Math.sqrt(_t2));
        } else {
            if (_t8 > _t4) {
                return new DoubleTransform(0.0, 0.0, 0.0, 0.5 * java.lang.Math.sqrt(_t11), 0.0, _sp1 * _t12, -(_sp0 * _t12), _t8, 1.0, java.lang.Math.sqrt(_t2));
            } else {
                if (1.0 > _t3) {
                    return new DoubleTransform(0.0, 0.0, 0.0, 0.0, 0.5 * java.lang.Math.sqrt(_t15), _sp0 * _t16, _sp1 * _t16, _t8, 1.0, java.lang.Math.sqrt(_t2));
                } else {
                    return new DoubleTransform(0.0, 0.0, 0.0, _sp1 * _t20, _sp0 * _t20, 0.5 * java.lang.Math.sqrt(_t18), 0.0, _t8, 1.0, java.lang.Math.sqrt(_t2));
                }
            }
        }
    }


    /**
     * Private body of {@code toTransform}, specialized by runtime matrix properties; reached only
     * through the public {@code toTransform} dispatcher.
     */
    private DoubleTransform toTransform_translation() {
        double _t2 = Math.fma(this.m02, this.m02, Math.fma(this.m12, this.m12, 1.0));
        if (!(_t2 > 2.2250738585072014E-308 && _t2 < Double.POSITIVE_INFINITY)) return toTransform_degenerate();
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        double _t8 = _t3 < 0.0 ? -1.0 : 1.0;
        double _t11 = _t8 - _t3;
        double _t13 = 1.0 + _t8 + _t3;
        double _t15 = 2.0 - _t8 - _t3;
        double _t17 = 1.0 + _t13;
        return toTransform_translation_s0_tail(1.0 + _t3 - _t8 - 1.0, _t13, 0.5 * this.m12 * _t3, (1.0 / java.lang.Math.sqrt(_t17)), _t8, java.lang.Math.max(1.0, _t3), _t11, _t3, 0.5 * this.m02 * _t3, _t15, (1.0 / java.lang.Math.sqrt(_t11)), (1.0 / java.lang.Math.sqrt(_t15)), _t17, _t2);
    }


    /**
     * Private body of {@code toTransform}, specialized by runtime matrix properties; reached only
     * through the public {@code toTransform} dispatcher.
     */
    private DoubleTransform toTransform_general() {
        double _t12 = Math.fma(this.m21, this.m21, Math.fma(this.m01, this.m01, this.m11 * this.m11));
        if (!(_t12 > 2.2250738585072014E-308 && _t12 < Double.POSITIVE_INFINITY)) return toTransform_degenerate();
        double _t13 = Math.fma(this.m22, this.m22, Math.fma(this.m02, this.m02, this.m12 * this.m12));
        if (!(_t13 > 2.2250738585072014E-308 && _t13 < Double.POSITIVE_INFINITY)) return toTransform_degenerate();
        double _t14 = Math.fma(this.m20, this.m20, Math.fma(this.m00, this.m00, this.m10 * this.m10));
        if (!(_t14 > 2.2250738585072014E-308 && _t14 < Double.POSITIVE_INFINITY)) return toTransform_degenerate();
        double _t18 = java.lang.Math.sqrt(_t14);
        double _t17 = 1.0 / _t18;
        double _sfx8 = java.lang.Math.sqrt(_t12);
        double _t15 = 1.0 / _sfx8;
        double _t24 = this.m21 * _t15;
        double _sfx9 = java.lang.Math.sqrt(_t13);
        double _t16 = 1.0 / _sfx9;
        double _t21 = this.m12 * _t16;
        return toTransform_general_s7465a3e1_1(-this.m11, -this.m22, _t18, this.m10 * _t17, this.m20 * _t17, this.m00 * _t17, 0.0, 0.0, 0.0, _sfx8, _t15, _t24, this.m11 * _t15, _sfx9, _t16, this.m22 * _t16, _t21, Math.fma(this.m12, _t16, _t24), Math.fma(this.m21, _t15, -_t21));
    }

    /** Piece 2 of {@code toTransform_general}, split to fit the inline budget; reached only through it. */
    private DoubleTransform toTransform_general_s7465a3e1_1(double _t0, double _t1, double _t18, double _t19, double _t22, double _t27, double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t24, double _t25, double _sfx9, double _t16, double _t20, double _t21, double _t32, double _t36) {
        double _t48, _t49, _t50, _sfx7;
        if (Math.fma(-Math.fma(_t19, _t20, -(_t21 * _t22)), this.m01 * _t15, Math.fma(Math.fma(_t19, _t24, -(_t25 * _t22)), this.m02 * _t16, Math.fma(_t25, _t20, -(_t21 * _t24)) * _t27)) < 0.0) {
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
        double _t52 = 1.0 + _t48;
        double _t53 = 1.0 - _t48;
        double _t64 = Math.fma(this.m11, _t15, Math.fma(this.m22, _t16, _t52));
        double _t66 = Math.fma(this.m11, _t15, Math.fma(_t1, _t16, _t53));
        double _t67 = Math.fma(this.m22, _t16, Math.fma(_t0, _t15, _t53));
        return toTransform_general_s7465a3e1_2(_sfx0, _sfx1, _sfx2, _sfx8, _t15, _t25, _sfx9, _t16, _t20, _t32, _t36, _t48, _sfx7, Math.fma(this.m01, _t15, _t49), Math.fma(this.m02, _t16, _t50), Math.fma(this.m02, _t16, -_t50), Math.fma(-this.m01, _t15, _t49), _t64, 0.5 * (1.0 / java.lang.Math.sqrt(_t64)), _t66, _t67, Math.fma(_t0, _t15, Math.fma(_t1, _t16, _t52)), 0.5 * (1.0 / java.lang.Math.sqrt(_t66)), 0.5 * (1.0 / java.lang.Math.sqrt(_t67)));
    }

    /** Piece 3 of {@code toTransform_general}, split to fit the inline budget; reached only through it. */
    private DoubleTransform toTransform_general_s7465a3e1_2(double _sfx0, double _sfx1, double _sfx2, double _sfx8, double _t15, double _t25, double _sfx9, double _t16, double _t20, double _t32, double _t36, double _t48, double _sfx7, double _t55, double _t56, double _t57, double _t58, double _t64, double _sp0, double _t66, double _t67, double _t68, double _sp1, double _sp2) {
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t68));
        double _sfx3, _sfx4, _sfx5, _sfx6;
        if (Math.fma(this.m11, _t15, Math.fma(this.m22, _t16, _t48)) > 0.0) {
            _sfx3 = _sp0 * _t36;
            _sfx4 = _sp0 * _t57;
            _sfx5 = _sp0 * _t58;
            _sfx6 = 0.5 * java.lang.Math.sqrt(_t64);
        } else {
            if (_t48 > java.lang.Math.max(_t25, _t20)) {
                _sfx3 = 0.5 * java.lang.Math.sqrt(_t68);
                _sfx4 = _sp3 * _t55;
                _sfx5 = _sp3 * _t56;
                _sfx6 = _sp3 * _t36;
            } else {
                if (_t25 > _t20) {
                    _sfx3 = _sp1 * _t55;
                    _sfx4 = 0.5 * java.lang.Math.sqrt(_t66);
                    _sfx5 = _sp1 * _t32;
                    _sfx6 = _sp1 * _t57;
                } else {
                    _sfx3 = _sp2 * _t56;
                    _sfx4 = _sp2 * _t32;
                    _sfx5 = 0.5 * java.lang.Math.sqrt(_t67);
                    _sfx6 = _sp2 * _t58;
                }
            }
        }
        return new DoubleTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
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
    public DoubleTransform toTransform() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return toTransform_identity();
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return toTransform_translation();
        return toTransform_general();
    }

    /** Private tail of {@code toTransform_degenerate_translation}; reached only through it. */
    private DoubleTransform toTransform_degenerate_translation_s0_tail(double _t28, double _t23, double _sp0, double _t29, double _t18, double _t14, double _t21, double _t13, double _sp1, double _t25, double _t22, double _t26, double _t27, double _t8, double _t0) {
        double _t30 = (1.0 / java.lang.Math.sqrt(_t28));
        return new DoubleTransform(0.0, 0.0, 0.0, _t23 > 0.0 ? -(_sp0 * _t29) : _t18 > _t14 ? 0.5 * java.lang.Math.sqrt(_t21) : 1.0 > _t13 ? 0.0 : _sp1 * _t30, _t23 > 0.0 ? _sp1 * _t29 : _t18 > _t14 ? 0.0 : 1.0 > _t13 ? 0.5 * java.lang.Math.sqrt(_t25) : _sp0 * _t30, _t23 > 0.0 ? 0.0 : _t18 > _t14 ? _sp1 * _t22 : 1.0 > _t13 ? _sp0 * _t26 : 0.5 * java.lang.Math.sqrt(_t28), _t23 > 0.0 ? 0.5 * java.lang.Math.sqrt(_t27) : _t18 > _t14 ? -(_sp0 * _t22) : 1.0 > _t13 ? _sp1 * _t26 : 0.0, _t18, 1.0, _t8 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t8) / _t0);
    }


    /**
     * Degenerate-input path of {@code toTransform}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private DoubleTransform toTransform_degenerate_translation() {
        double _t0 = unitScale(this.m02, this.m12, 1.0);
        double _t4 = this.m02 * _t0;
        double _t5 = this.m12 * _t0;
        double _t8 = Math.fma(_t0, _t0, Math.fma(_t4, _t4, _t5 * _t5));
        double _t9 = (1.0 / java.lang.Math.sqrt(_t8));
        double _t13, _sp0, _sp1;
        if (_t8 <= 0.0) {
            _t13 = 1.0;
            _sp0 = 0.0;
            _sp1 = 0.0;
        } else {
            _t13 = _t9 * _t0;
            _sp0 = 0.5 * _t9 * _t5;
            _sp1 = 0.5 * _t9 * _t4;
        }
        double _t18 = _t13 < 0.0 ? -1.0 : 1.0;
        double _t21 = _t18 - _t13;
        double _t23 = 1.0 + _t18 + _t13;
        double _t25 = 2.0 - _t18 - _t13;
        double _t27 = 1.0 + _t23;
        return toTransform_degenerate_translation_s0_tail(1.0 + _t13 - _t18 - 1.0, _t23, _sp0, (1.0 / java.lang.Math.sqrt(_t27)), _t18, java.lang.Math.max(1.0, _t13), _t21, _t13, _sp1, _t25, (1.0 / java.lang.Math.sqrt(_t21)), (1.0 / java.lang.Math.sqrt(_t25)), _t27, _t8, _t0);
    }

    /** Private tail of {@code toTransform_degenerate_general}; reached only through it. */
    private DoubleTransform toTransform_degenerate_general_s0_tail(double _t31, double _t17, double _t15, double _t16, double _t32, double _t13, double _t12, double _t14, double _t35, double _t36, double _t29, double _t2, double _t37, double _t27, double _t28, double _t0, double _t1) {
        double _t38 = _t31 * _t17;
        double _t39 = _t31 * _t15;
        double _t40 = _t31 * _t16;
        double _t41 = _t32 * _t13;
        double _t42 = _t32 * _t12;
        double _t43 = _t32 * _t14;
        double _t44 = java.lang.Math.abs(_t35);
        double _t45 = java.lang.Math.abs(_t36);
        double _t48 = java.lang.Math.abs(_t39);
        double _t49 = java.lang.Math.abs(_t40);
        double _t52 = java.lang.Math.abs(_t42);
        double _t53 = java.lang.Math.abs(_t41);
        double _t75, _t78;
        if (_t44 < _t45) {
            _t75 = _t37;
            _t78 = 0.0;
        } else {
            _t75 = 0.0;
            _t78 = -_t37;
        }
        double _t76, _t79;
        if (_t48 < _t49) {
            _t76 = _t38;
            _t79 = 0.0;
        } else {
            _t76 = 0.0;
            _t79 = -_t38;
        }
        double _t77, _t80;
        if (_t52 < _t53) {
            _t77 = _t43;
            _t80 = 0.0;
        } else {
            _t77 = 0.0;
            _t80 = -_t43;
        }
        return toTransform_degenerate_general_s0_tail2(_t44, _t45, _t36, _t35, _t48, _t49, _t40, _t39, _t52, _t53, _t41, _t42, _t78, _t75, _t79, _t76, _t80, _t77, _t27, _t28, _t29, _t38, _t37, _t43, _t29 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t29) / _t2, _t0, _t1);
    }

    /** Private tail of {@code toTransform_degenerate_general}; reached only through it. */
    private DoubleTransform toTransform_degenerate_general_s0_tail2(double _t44, double _t45, double _t36, double _t35, double _t48, double _t49, double _t40, double _t39, double _t52, double _t53, double _t41, double _t42, double _t78, double _t75, double _t79, double _t76, double _t80, double _t77, double _t27, double _t28, double _t29, double _t38, double _t37, double _t43, double _t56, double _t0, double _t1) {
        double _t90 = _t44 < _t45 ? -_t36 : _t35;
        double _t91 = _t48 < _t49 ? -_t40 : _t39;
        double _t92 = _t52 < _t53 ? -_t41 : _t42;
        double _t102 = (1.0 / java.lang.Math.sqrt(Math.fma(_t78, _t78, Math.fma(_t90, _t90, _t75 * _t75))));
        double _t103 = (1.0 / java.lang.Math.sqrt(Math.fma(_t79, _t79, Math.fma(_t91, _t91, _t76 * _t76))));
        double _t104 = (1.0 / java.lang.Math.sqrt(Math.fma(_t80, _t80, Math.fma(_t92, _t92, _t77 * _t77))));
        return toTransform_degenerate_general_s0_tail3(_t27, _t28, _t29, _t102 * _t75, _t38, _t103 * _t79, _t39, _t103 * _t91, _t35, _t37, _t41, _t104 * _t92, _t36, _t102 * _t78, _t42, _t102 * _t90, _t103 * _t76, _t40, _t43, _t104 * _t77, _t104 * _t80, _t56, _t0, _t1);
    }

    /** Private tail of {@code toTransform_degenerate_general}; reached only through it. */
    private DoubleTransform toTransform_degenerate_general_s0_tail3(double _t27, double _t28, double _t29, double _t105, double _t38, double _t108, double _t39, double _t117, double _t35, double _t37, double _t41, double _t118, double _t36, double _t109, double _t42, double _t119, double _t106, double _t40, double _t43, double _t107, double _t110, double _t56, double _t0, double _t1) {
        double _t168, _t169;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t168 = 0.0;
                    _t169 = 0.0;
                } else {
                    _t168 = _t105;
                    _t169 = Math.fma(_t35, _t105, -(_t36 * _t109));
                }
            } else {
                _t168 = _t29 <= 0.0 ? Math.fma(_t38, _t108, -(_t39 * _t117)) : Math.fma(_t35, _t38, -(_t37 * _t39));
                _t169 = _t38;
            }
        } else {
            _t168 = _t41;
            _t169 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t118 : Math.fma(_t35, _t41, -(_t36 * _t42)) : _t38;
        }
        return toTransform_degenerate_general_s0_tail4(_t27, _t28, _t29, _t119, _t39, _t106, _t40, _t108, _t36, _t35, _t43, _t107, _t37, _t109, _t42, _t41, _t118, _t38, _t117, _t110, _t105, _t169, _t168, _t56, _t0, _t1);
    }

    /** Private tail of {@code toTransform_degenerate_general}; reached only through it. */
    private DoubleTransform toTransform_degenerate_general_s0_tail4(double _t27, double _t28, double _t29, double _t119, double _t39, double _t106, double _t40, double _t108, double _t36, double _t35, double _t43, double _t107, double _t37, double _t109, double _t42, double _t41, double _t118, double _t38, double _t117, double _t110, double _t105, double _t169, double _t168, double _t56, double _t0, double _t1) {
        double _t170, _t171;
        if (_t27 <= 0.0) {
            if (_t28 <= 0.0) {
                if (_t29 <= 0.0) {
                    _t170 = 1.0;
                    _t171 = 0.0;
                } else {
                    _t170 = _t119;
                    _t171 = Math.fma(_t37, _t109, -(_t35 * _t119));
                }
            } else {
                _t170 = _t29 <= 0.0 ? Math.fma(_t39, _t106, -(_t40 * _t108)) : Math.fma(_t36, _t39, -(_t35 * _t40));
                _t171 = _t40;
            }
        } else {
            _t170 = _t43;
            _t171 = _t28 <= 0.0 ? _t29 <= 0.0 ? _t107 : Math.fma(_t37, _t42, -(_t35 * _t43)) : _t40;
        }
        return toTransform_degenerate_general_s0_tail5(_t29, _t27, _t28, _t108, _t41, _t118, _t43, _t107, _t38, _t40, _t35, _t109, _t117, _t106, _t37, _t36, _t42, _t110, _t119, _t105, _t39, _t169, _t170, _t168, _t171, _t56, _t0, _t1);
    }

    /** Private tail of {@code toTransform_degenerate_general}; reached only through it. */
    private DoubleTransform toTransform_degenerate_general_s0_tail5(double _t29, double _t27, double _t28, double _t108, double _t41, double _t118, double _t43, double _t107, double _t38, double _t40, double _t35, double _t109, double _t117, double _t106, double _t37, double _t36, double _t42, double _t110, double _t119, double _t105, double _t39, double _t169, double _t170, double _t168, double _t171, double _t56, double _t0, double _t1) {
        double _t172, _t173;
        if (_t29 <= 0.0) {
            if (_t27 <= 0.0) {
                if (_t28 <= 0.0) {
                    _t172 = 0.0;
                    _t173 = 0.0;
                } else {
                    _t172 = _t108;
                    _t173 = Math.fma(_t40, _t117, -(_t38 * _t106));
                }
            } else {
                _t172 = _t28 <= 0.0 ? Math.fma(_t41, _t118, -(_t43 * _t107)) : Math.fma(_t41, _t38, -(_t43 * _t40));
                _t173 = _t42;
            }
        } else {
            _t172 = _t35;
            _t173 = _t27 <= 0.0 ? _t28 <= 0.0 ? _t109 : Math.fma(_t37, _t40, -(_t36 * _t38)) : _t42;
        }
        return toTransform_degenerate_general_s0_tail6(_t28, _t29, _t27, _t110, _t36, _t119, _t37, _t105, _t43, _t41, _t39, _t117, _t42, _t107, _t40, _t106, _t118, _t38, _t173, _t169, _t170, _t168, _t171, _t172, _t56, _t0, _t1);
    }

    /** Private tail of {@code toTransform_degenerate_general}; reached only through it. */
    private DoubleTransform toTransform_degenerate_general_s0_tail6(double _t28, double _t29, double _t27, double _t110, double _t36, double _t119, double _t37, double _t105, double _t43, double _t41, double _t39, double _t117, double _t42, double _t107, double _t40, double _t106, double _t118, double _t38, double _t173, double _t169, double _t170, double _t168, double _t171, double _t172, double _t56, double _t0, double _t1) {
        double _t174, _t175;
        if (_t28 <= 0.0) {
            if (_t29 <= 0.0) {
                if (_t27 <= 0.0) {
                    _t174 = 1.0;
                    _t175 = 0.0;
                } else {
                    _t174 = _t110;
                    _t175 = Math.fma(_t42, _t107, -(_t41 * _t110));
                }
            } else {
                _t174 = _t27 <= 0.0 ? Math.fma(_t36, _t119, -(_t37 * _t105)) : Math.fma(_t36, _t43, -(_t37 * _t41));
                _t175 = _t37;
            }
        } else {
            _t174 = _t39;
            _t175 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t117 : Math.fma(_t42, _t40, -(_t41 * _t39)) : _t37;
        }
        return toTransform_degenerate_general_s0_tail7(_t29, _t27, _t28, _t106, _t43, _t110, _t42, _t118, _t39, _t38, _t36, _t173, _t169, _t170, _t174, _t168, _t171, _t172, _t175, _t56, _t0, _t1);
    }

    /** Private tail of {@code toTransform_degenerate_general}; reached only through it. */
    private DoubleTransform toTransform_degenerate_general_s0_tail7(double _t29, double _t27, double _t28, double _t106, double _t43, double _t110, double _t42, double _t118, double _t39, double _t38, double _t36, double _t173, double _t169, double _t170, double _t174, double _t168, double _t171, double _t172, double _t175, double _t56, double _t0, double _t1) {
        double _t176 = _t29 <= 0.0 ? _t27 <= 0.0 ? _t28 <= 0.0 ? 1.0 : _t106 : _t28 <= 0.0 ? Math.fma(_t43, _t110, -(_t42 * _t118)) : Math.fma(_t43, _t39, -(_t42 * _t38)) : _t36;
        double _t196 = Math.fma(Math.fma(_t168, _t169, -(_t170 * _t171)), _t172, Math.fma(Math.fma(_t173, _t171, -(_t168 * _t174)), _t175, Math.fma(_t170, _t174, -(_t173 * _t169)) * _t176));
        double _t197, _t198, _t199;
        if (_t196 < 0.0) {
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
    private DoubleTransform toTransform_degenerate_general_s0_tail8(double _t199, double _t171, double _t198, double _t168, double _t197, double _t170, double _t174, double _t185, double _t186, double _t202, double _t187, double _t196, double _t56, double _t27, double _t0, double _t28, double _t1) {
        double _t203 = _t199 + _t171;
        double _t209 = _t197 + _t170 + _t174;
        double _t210 = 1.0 + _t209;
        double _t211 = 1.0 + _t197 - _t170 - _t174;
        double _t212 = 1.0 + _t170 - _t197 - _t174;
        double _t213 = 1.0 + _t174 - _t197 - _t170;
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t210));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t212));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t213));
        double _sfx3 = _t209 > 0.0 ? _sp0 * _t185 : _t197 > _t186 ? 0.5 * java.lang.Math.sqrt(_t211) : _t170 > _t174 ? _sp1 * _t202 : _sp2 * _t203;
        return toTransform_degenerate_general_s0_tail9(_t209, _sp0, _t171 - _t199, _t197, _t186, 0.5 * (1.0 / java.lang.Math.sqrt(_t211)), _t202, _t170, _t174, _t212, _sp2, _t187, _t198 - _t168, _t203, _sp1, _t213, _t210, _t185, _t196, _t56, _t27, _t0, _t28, _t1, 0.0, 0.0, 0.0, _sfx3);
    }

    /** Private tail of {@code toTransform_degenerate_general}; reached only through it. */
    private DoubleTransform toTransform_degenerate_general_s0_tail9(double _t209, double _sp0, double _t204, double _t197, double _t186, double _sp3, double _t202, double _t170, double _t174, double _t212, double _sp2, double _t187, double _t205, double _t203, double _sp1, double _t213, double _t210, double _t185, double _t196, double _t56, double _t27, double _t0, double _t28, double _t1, double _sfx0, double _sfx1, double _sfx2, double _sfx3) {
        double _sfx4, _sfx5, _sfx6;
        if (_t209 > 0.0) {
            _sfx4 = _sp0 * _t204;
            _sfx5 = _sp0 * _t205;
            _sfx6 = 0.5 * java.lang.Math.sqrt(_t210);
        } else {
            if (_t197 > _t186) {
                _sfx4 = _sp3 * _t202;
                _sfx5 = _sp3 * _t203;
                _sfx6 = _sp3 * _t185;
            } else {
                if (_t170 > _t174) {
                    _sfx4 = 0.5 * java.lang.Math.sqrt(_t212);
                    _sfx5 = _sp1 * _t187;
                    _sfx6 = _sp1 * _t204;
                } else {
                    _sfx4 = _sp2 * _t187;
                    _sfx5 = 0.5 * java.lang.Math.sqrt(_t213);
                    _sfx6 = _sp2 * _t205;
                }
            }
        }
        double _sfx7 = _t196 < 0.0 ? -_t56 : _t56;
        double _sfx8 = _t27 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t27) / _t0;
        double _sfx9 = _t28 <= 0.0 ? 0.0 : java.lang.Math.sqrt(_t28) / _t1;
        return new DoubleTransform(_sfx0, _sfx1, _sfx2, _sfx3, _sfx4, _sfx5, _sfx6, _sfx7, _sfx8, _sfx9);
    }


    /**
     * Degenerate-input path of {@code toTransform}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private DoubleTransform toTransform_degenerate_general() {
        double _t0 = unitScale(this.m01, this.m11, this.m21);
        double _t1 = unitScale(this.m02, this.m12, this.m22);
        double _t2 = unitScale(this.m00, this.m10, this.m20);
        double _t12 = this.m21 * _t0;
        double _t13 = this.m01 * _t0;
        double _t14 = this.m11 * _t0;
        double _t15 = this.m22 * _t1;
        double _t16 = this.m02 * _t1;
        double _t17 = this.m12 * _t1;
        double _t18 = this.m20 * _t2;
        double _t19 = this.m00 * _t2;
        double _t20 = this.m10 * _t2;
        double _t27 = Math.fma(_t12, _t12, Math.fma(_t13, _t13, _t14 * _t14));
        double _t28 = Math.fma(_t15, _t15, Math.fma(_t16, _t16, _t17 * _t17));
        double _t29 = Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        return toTransform_degenerate_general_s0_tail((1.0 / java.lang.Math.sqrt(_t28)), _t17, _t15, _t16, (1.0 / java.lang.Math.sqrt(_t27)), _t13, _t12, _t14, _t30 * _t18, _t30 * _t19, _t29, _t2, _t30 * _t20, _t27, _t28, _t0, _t1);
    }


    /**
     * Degenerate-input path of {@code toTransform}: its methods leave here when a column of the
     * linear block is zero or its squared length leaves the normal floating-point range (or is
     * NaN); reached only through them.
     */
    private DoubleTransform toTransform_degenerate() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return toTransform_identity();
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return toTransform_degenerate_translation();
        return toTransform_degenerate_general();
    }


    /**
     * Private body of {@code decomposeRotation}, specialized by runtime matrix properties; reached
     * only through the public {@code decomposeRotation} dispatcher.
     */
    private DoubleQuat decomposeRotation_general() {
        double _t2 = Math.fma(this.m20, this.m20, Math.fma(this.m00, this.m00, this.m10 * this.m10));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        double _t7, _t8, _t9;
        if (_t2 != 0.0) {
            _t7 = this.m20 * _t3;
            _t8 = this.m00 * _t3;
            _t9 = this.m10 * _t3;
        } else {
            _t7 = 0.0;
            _t8 = 0.0;
            _t9 = 0.0;
        }
        double _t19 = -Math.fma(this.m21, _t7, Math.fma(this.m01, _t8, this.m11 * _t9));
        double _t21 = Math.fma(_t19, _t7, this.m21);
        double _t22 = Math.fma(_t19, _t8, this.m01);
        double _t23 = Math.fma(_t19, _t9, this.m11);
        double _t29 = Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23));
        double _t30 = (1.0 / java.lang.Math.sqrt(_t29));
        double _t34, _t35, _t36;
        if (_t29 != 0.0) {
            _t34 = _t22 * _t30;
            _t35 = _t21 * _t30;
            _t36 = _t23 * _t30;
        } else {
            _t34 = 0.0;
            _t35 = 0.0;
            _t36 = 0.0;
        }
        return decomposeRotation_general_s2273f2d9_1(_t7, _t8, _t9, -Math.fma(this.m22, _t7, Math.fma(this.m02, _t8, this.m12 * _t9)), _t34, _t35, _t36);
    }

    /** Piece 2 of {@code decomposeRotation_general}, split to fit the inline budget; reached only through it. */
    private DoubleQuat decomposeRotation_general_s2273f2d9_1(double _t7, double _t8, double _t9, double _t20, double _t34, double _t35, double _t36) {
        double _t40 = -Math.fma(Math.fma(_t20, _t7, this.m22), _t35, Math.fma(Math.fma(_t20, _t8, this.m02), _t34, Math.fma(_t20, _t9, this.m12) * _t36));
        double _t44 = Math.fma(_t20, _t7, Math.fma(_t40, _t35, this.m22));
        double _t45 = Math.fma(_t20, _t8, Math.fma(_t40, _t34, this.m02));
        double _t46 = Math.fma(_t20, _t9, Math.fma(_t40, _t36, this.m12));
        double _t49 = Math.fma(_t44, _t44, Math.fma(_t45, _t45, _t46 * _t46));
        double _t50 = (1.0 / java.lang.Math.sqrt(_t49));
        double _t54, _t55, _t56;
        if (_t49 != 0.0) {
            _t54 = _t46 * _t50;
            _t55 = _t45 * _t50;
            _t56 = _t44 * _t50;
        } else {
            _t54 = 0.0;
            _t55 = 0.0;
            _t56 = 0.0;
        }
        double _t73, _t74, _t75;
        if (Math.fma(Math.fma(_t34, _t54, -(_t36 * _t55)), _t7, Math.fma(Math.fma(_t36, _t56, -(_t35 * _t54)), _t8, Math.fma(_t35, _t55, -(_t34 * _t56)) * _t9)) < 0.0) {
            _t73 = -_t8;
            _t74 = -_t9;
            _t75 = -_t7;
        } else {
            _t73 = _t8;
            _t74 = _t9;
            _t75 = _t7;
        }
        return decomposeRotation_general_s2273f2d9_2(_t36, _t56, _t35 - _t54, _t35 + _t54, _t73, _t73 + _t36, _t74 + _t34, _t74 - _t34, _t75 + _t55, _t55 - _t75);
    }

    /** Piece 3 of {@code decomposeRotation_general}, split to fit the inline budget; reached only through it. */
    private DoubleQuat decomposeRotation_general_s2273f2d9_2(double _t36, double _t56, double _t60, double _t63, double _t73, double _t76, double _t77, double _t78, double _t80, double _t81) {
        double _t82 = _t76 + _t56;
        double _t86 = 1.0 + _t82;
        double _t87 = 1.0 + (_t73 - (_t36 + _t56));
        double _t88 = 1.0 + (_t36 - (_t73 + _t56));
        double _t89 = 1.0 + (_t56 - _t76);
        double _sp0 = 0.5 * (1.0 / java.lang.Math.sqrt(_t86));
        double _sp1 = 0.5 * (1.0 / java.lang.Math.sqrt(_t88));
        double _sp2 = 0.5 * (1.0 / java.lang.Math.sqrt(_t89));
        double _sp3 = 0.5 * (1.0 / java.lang.Math.sqrt(_t87));
        double _sfx0, _sfx1, _sfx2, _sfx3;
        if (_t82 > 0.0) {
            _sfx0 = _sp0 * _t60;
            _sfx1 = _sp0 * _t81;
            _sfx2 = _sp0 * _t78;
            _sfx3 = 0.5 * java.lang.Math.sqrt(_t86);
        } else {
            if (_t73 > java.lang.Math.max(_t36, _t56)) {
                _sfx0 = 0.5 * java.lang.Math.sqrt(_t87);
                _sfx1 = _sp3 * _t77;
                _sfx2 = _sp3 * _t80;
                _sfx3 = _sp3 * _t60;
            } else {
                if (_t36 > _t56) {
                    _sfx0 = _sp1 * _t77;
                    _sfx1 = 0.5 * java.lang.Math.sqrt(_t88);
                    _sfx2 = _sp1 * _t63;
                    _sfx3 = _sp1 * _t81;
                } else {
                    _sfx0 = _sp2 * _t80;
                    _sfx1 = _sp2 * _t63;
                    _sfx2 = 0.5 * java.lang.Math.sqrt(_t89);
                    _sfx3 = _sp2 * _t78;
                }
            }
        }
        return new DoubleQuat(_sfx0, _sfx1, _sfx2, _sfx3);
    }


    /**
     * Extract the rotation part of this matrix, returning the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting quaternion
     */
    public DoubleQuat decomposeRotation() {
        if ((this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getNormalizedRotation_identity();
        return decomposeRotation_general();
    }

    /** Private tail of {@code decomposeScale_general}; reached only through it. */
    private Double3 decomposeScale_general_s0_tail(double _t27, double _t20, double _t28, double _t19, double _t21, double _t18, double _t8, double _t9, double _t10, double _t4) {
        double _t32, _t33, _t34;
        if (_t27 != 0.0) {
            _t32 = _t20 * _t28;
            _t33 = _t19 * _t28;
            _t34 = _t21 * _t28;
        } else {
            _t32 = 0.0;
            _t33 = 0.0;
            _t34 = 0.0;
        }
        double _t38 = -Math.fma(Math.fma(_t18, _t8, this.m22), _t33, Math.fma(Math.fma(_t18, _t9, this.m02), _t32, Math.fma(_t18, _t10, this.m12) * _t34));
        double _t42 = Math.fma(_t18, _t8, Math.fma(_t38, _t33, this.m22));
        double _t43 = Math.fma(_t18, _t9, Math.fma(_t38, _t32, this.m02));
        double _t44 = Math.fma(_t18, _t10, Math.fma(_t38, _t34, this.m12));
        double _t47 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44));
        return decomposeScale_general_s0_tail2(_t47, _t44, (1.0 / java.lang.Math.sqrt(_t47)), _t43, _t42, _t32, _t34, _t8, _t33, _t9, _t10, _t4, _t27);
    }

    /** Private tail of {@code decomposeScale_general}; reached only through it. */
    private Double3 decomposeScale_general_s0_tail2(double _t47, double _t44, double _t48, double _t43, double _t42, double _t32, double _t34, double _t8, double _t33, double _t9, double _t10, double _t4, double _t27) {
        double _t52, _t53, _t54;
        if (_t47 != 0.0) {
            _t52 = _t44 * _t48;
            _t53 = _t43 * _t48;
            _t54 = _t42 * _t48;
        } else {
            _t52 = 0.0;
            _t53 = 0.0;
            _t54 = 0.0;
        }
        double _sfx0 = Math.fma(Math.fma(_t32, _t52, -(_t34 * _t53)), _t8, Math.fma(Math.fma(_t34, _t54, -(_t33 * _t52)), _t9, Math.fma(_t33, _t53, -(_t32 * _t54)) * _t10)) < 0.0 ? -_t4 : _t4;
        double _sfx1 = java.lang.Math.sqrt(_t27);
        double _sfx2 = java.lang.Math.sqrt(_t47);
        return new Double3(_sfx0, _sfx1, _sfx2);
    }


    /**
     * Private body of {@code decomposeScale}, specialized by runtime matrix properties; reached
     * only through the public {@code decomposeScale} dispatcher.
     */
    private Double3 decomposeScale_general() {
        double _t2 = Math.fma(this.m20, this.m20, Math.fma(this.m00, this.m00, this.m10 * this.m10));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        double _t8, _t9, _t10;
        if (_t2 != 0.0) {
            _t8 = this.m20 * _t3;
            _t9 = this.m00 * _t3;
            _t10 = this.m10 * _t3;
        } else {
            _t8 = 0.0;
            _t9 = 0.0;
            _t10 = 0.0;
        }
        double _t17 = -Math.fma(this.m21, _t8, Math.fma(this.m01, _t9, this.m11 * _t10));
        double _t19 = Math.fma(_t17, _t8, this.m21);
        double _t20 = Math.fma(_t17, _t9, this.m01);
        double _t21 = Math.fma(_t17, _t10, this.m11);
        double _t27 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
        return decomposeScale_general_s0_tail(_t27, _t20, (1.0 / java.lang.Math.sqrt(_t27)), _t19, _t21, -Math.fma(this.m22, _t8, Math.fma(this.m02, _t9, this.m12 * _t10)), _t8, _t9, _t10, java.lang.Math.sqrt(_t2));
    }


    /**
     * Extract the scaling factors of this matrix via Gram-Schmidt orthogonalization (skew-aware;
     * the x factor carries the sign of a reflection when the determinant is negative), returning
     * the result as a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}.
     *
     * @return the resulting vector
     */
    public Double3 decomposeScale() {
        if ((this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return getScale_identity();
        return decomposeScale_general();
    }


    /**
     * Private body of {@code decomposeSkew}, specialized by runtime matrix properties; reached only
     * through the public {@code decomposeSkew} dispatcher.
     */
    private Double3 decomposeSkew_translation() {
        return new Double3(this.m12, this.m02, 0.0);
    }

    /** Private tail of {@code decomposeSkew_general}; reached only through it. */
    private Double3 decomposeSkew_general_s0_tail(double _t26, double _t19, double _t27, double _t20, double _t21, double _t16, double _t7, double _t8, double _t9, double _t14, double _t28) {
        double _t32, _t33, _t34;
        if (_t26 != 0.0) {
            _t32 = _t19 * _t27;
            _t33 = _t20 * _t27;
            _t34 = _t21 * _t27;
        } else {
            _t32 = 0.0;
            _t33 = 0.0;
            _t34 = 0.0;
        }
        double _t37 = Math.fma(Math.fma(_t16, _t7, this.m22), _t32, Math.fma(Math.fma(_t16, _t8, this.m02), _t33, Math.fma(_t16, _t9, this.m12) * _t34));
        double _t38 = -_t37;
        double _t42 = Math.fma(_t16, _t7, Math.fma(_t38, _t32, this.m22));
        double _t43 = Math.fma(_t16, _t8, Math.fma(_t38, _t33, this.m02));
        double _t44 = Math.fma(_t16, _t9, Math.fma(_t38, _t34, this.m12));
        double _t47 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, _t44 * _t44));
        double _t48 = (1.0 / java.lang.Math.sqrt(_t47));
        return decomposeSkew_general_s0_tail2(_t47, _t44, _t48, _t43, _t42, _t33, _t34, _t7, _t32, _t8, _t9, _t37, _t14 * _t48, _t28);
    }

    /** Private tail of {@code decomposeSkew_general}; reached only through it. */
    private Double3 decomposeSkew_general_s0_tail2(double _t47, double _t44, double _t48, double _t43, double _t42, double _t33, double _t34, double _t7, double _t32, double _t8, double _t9, double _t37, double _t49, double _t28) {
        double _t53, _t54, _t55;
        if (_t47 != 0.0) {
            _t53 = _t44 * _t48;
            _t54 = _t43 * _t48;
            _t55 = _t42 * _t48;
        } else {
            _t53 = 0.0;
            _t54 = 0.0;
            _t55 = 0.0;
        }
        double _sfx0 = _t37 * _t48;
        double _sfx1, _sfx2;
        if (Math.fma(Math.fma(_t33, _t53, -(_t34 * _t54)), _t7, Math.fma(Math.fma(_t34, _t55, -(_t32 * _t53)), _t8, Math.fma(_t32, _t54, -(_t33 * _t55)) * _t9)) < 0.0) {
            _sfx1 = -_t49;
            _sfx2 = -_t28;
        } else {
            _sfx1 = _t49;
            _sfx2 = _t28;
        }
        return new Double3(_sfx0, _sfx1, _sfx2);
    }


    /**
     * Private body of {@code decomposeSkew}, specialized by runtime matrix properties; reached only
     * through the public {@code decomposeSkew} dispatcher.
     */
    private Double3 decomposeSkew_general() {
        double _t2 = Math.fma(this.m20, this.m20, Math.fma(this.m00, this.m00, this.m10 * this.m10));
        double _t3 = (1.0 / java.lang.Math.sqrt(_t2));
        double _t7, _t8, _t9;
        if (_t2 != 0.0) {
            _t7 = this.m20 * _t3;
            _t8 = this.m00 * _t3;
            _t9 = this.m10 * _t3;
        } else {
            _t7 = 0.0;
            _t8 = 0.0;
            _t9 = 0.0;
        }
        double _t14 = Math.fma(this.m22, _t7, Math.fma(this.m02, _t8, this.m12 * _t9));
        double _t15 = Math.fma(this.m21, _t7, Math.fma(this.m01, _t8, this.m11 * _t9));
        double _t17 = -_t15;
        double _t19 = Math.fma(_t17, _t7, this.m21);
        double _t20 = Math.fma(_t17, _t8, this.m01);
        double _t21 = Math.fma(_t17, _t9, this.m11);
        double _t26 = Math.fma(_t19, _t19, Math.fma(_t20, _t20, _t21 * _t21));
        double _t27 = (1.0 / java.lang.Math.sqrt(_t26));
        return decomposeSkew_general_s0_tail(_t26, _t19, _t27, _t20, _t21, -_t14, _t7, _t8, _t9, _t14, _t15 * _t27);
    }


    /**
     * Extract the shear (skew) factors of this matrix via Gram-Schmidt orthogonalization, as
     * {@code (skewYZ, skewXZ, skewXY)} (all zero for a shear-free matrix), returning the result as
     * a value.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1.5e-154} and {@code 3.8e153}; this
     * matrix must be invertible.
     *
     * @return the resulting vector
     */
    public Double3 decomposeSkew() {
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
    public static Double3x3 makeIdentity() {
        return new Double3x3(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_IDENTITY);
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
    public Double3x3 lerp(Double3x3 other, double t) {
        return new Double3x3(Math.fma(t, other.m00() - this.m00, this.m00), Math.fma(t, other.m01() - this.m01, this.m01), Math.fma(t, other.m02() - this.m02, this.m02), Math.fma(t, other.m10() - this.m10, this.m10), Math.fma(t, other.m11() - this.m11, this.m11), Math.fma(t, other.m12() - this.m12, this.m12), Math.fma(t, other.m20() - this.m20, this.m20), Math.fma(t, other.m21() - this.m21, this.m21), Math.fma(t, other.m22() - this.m22, this.m22), ((Joml.UNIQUE_IDENTITY | Joml.UNIQUE_TRANSLATION | Joml.UNIQUE_AFFINE) & this.properties & other.properties()) | ((Joml.UNIQUE_TRANSLATION & this.properties & other.properties()) >> 1));
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
    public Double3x3 lerp(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, double t) {
        return new Double3x3(Math.fma(t, m00 - this.m00, this.m00), Math.fma(t, m01 - this.m01, this.m01), Math.fma(t, m02 - this.m02, this.m02), Math.fma(t, m10 - this.m10, this.m10), Math.fma(t, m11 - this.m11, this.m11), Math.fma(t, m12 - this.m12, this.m12), Math.fma(t, m20 - this.m20, this.m20), Math.fma(t, m21 - this.m21, this.m21), Math.fma(t, m22 - this.m22, this.m22), 0);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_general(Double3x3 right) {
        return new Double3x3(Math.fma(right.m20(), this.m02, Math.fma(right.m00(), this.m00, right.m10() * this.m01)), Math.fma(right.m21(), this.m02, Math.fma(right.m01(), this.m00, right.m11() * this.m01)), Math.fma(right.m22(), this.m02, Math.fma(right.m02(), this.m00, right.m12() * this.m01)), Math.fma(right.m20(), this.m12, Math.fma(right.m00(), this.m10, right.m10() * this.m11)), Math.fma(right.m21(), this.m12, Math.fma(right.m01(), this.m10, right.m11() * this.m11)), Math.fma(right.m22(), this.m12, Math.fma(right.m02(), this.m10, right.m12() * this.m11)), Math.fma(right.m20(), this.m22, Math.fma(right.m00(), this.m20, right.m10() * this.m21)), Math.fma(right.m21(), this.m22, Math.fma(right.m01(), this.m20, right.m11() * this.m21)), Math.fma(right.m22(), this.m22, Math.fma(right.m02(), this.m20, right.m12() * this.m21)), 0);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_translation(Double3x3 right) {
        return new Double3x3(Math.fma(right.m20(), this.m02, right.m00()), Math.fma(right.m21(), this.m02, right.m01()), Math.fma(right.m22(), this.m02, right.m02()), Math.fma(right.m20(), this.m12, right.m10()), Math.fma(right.m21(), this.m12, right.m11()), Math.fma(right.m22(), this.m12, right.m12()), right.m20(), right.m21(), right.m22(), Joml.BIT_TRANSLATION & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal(Double3x3 right) {
        return new Double3x3(Math.fma(right.m20(), this.m02, Math.fma(right.m00(), this.m11, -(right.m10() * this.m10))), Math.fma(right.m21(), this.m02, Math.fma(right.m01(), this.m11, -(right.m11() * this.m10))), Math.fma(right.m22(), this.m02, Math.fma(right.m02(), this.m11, -(right.m12() * this.m10))), Math.fma(right.m20(), this.m12, Math.fma(right.m00(), this.m10, right.m10() * this.m11)), Math.fma(right.m21(), this.m12, Math.fma(right.m01(), this.m10, right.m11() * this.m11)), Math.fma(right.m22(), this.m12, Math.fma(right.m02(), this.m10, right.m12() * this.m11)), right.m20(), right.m21(), right.m22(), Joml.BIT_ORTHOGONAL & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_affine(Double3x3 right) {
        return new Double3x3(Math.fma(right.m20(), this.m02, Math.fma(right.m00(), this.m00, right.m10() * this.m01)), Math.fma(right.m21(), this.m02, Math.fma(right.m01(), this.m00, right.m11() * this.m01)), Math.fma(right.m22(), this.m02, Math.fma(right.m02(), this.m00, right.m12() * this.m01)), Math.fma(right.m20(), this.m12, Math.fma(right.m00(), this.m10, right.m10() * this.m11)), Math.fma(right.m21(), this.m12, Math.fma(right.m01(), this.m10, right.m11() * this.m11)), Math.fma(right.m22(), this.m12, Math.fma(right.m02(), this.m10, right.m12() * this.m11)), right.m20(), right.m21(), right.m22(), Joml.BIT_AFFINE & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_translation_translation(Double3x3 right) {
        return new Double3x3(1.0, 0.0, right.m02() + this.m02, 0.0, 1.0, right.m12() + this.m12, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_translation_affine(Double3x3 right) {
        return new Double3x3(right.m00(), right.m01(), right.m02() + this.m02, right.m10(), right.m11(), right.m12() + this.m12, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal_translation(Double3x3 right, int _props) {
        return new Double3x3(this.m00, this.m01, Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02)), this.m10, this.m11, Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), 0.0, 0.0, 1.0, _props);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal_affine(Double3x3 right) {
        return new Double3x3(Math.fma(right.m00(), this.m11, -(right.m10() * this.m10)), Math.fma(right.m01(), this.m11, -(right.m11() * this.m10)), Math.fma(-right.m12(), this.m10, Math.fma(right.m02(), this.m11, this.m02)), Math.fma(right.m00(), this.m10, right.m10() * this.m11), Math.fma(right.m01(), this.m10, right.m11() * this.m11), Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_affine_affine(Double3x3 right) {
        return new Double3x3(Math.fma(right.m00(), this.m00, right.m10() * this.m01), Math.fma(right.m01(), this.m00, right.m11() * this.m01), Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02)), Math.fma(right.m00(), this.m10, right.m10() * this.m11), Math.fma(right.m01(), this.m10, right.m11() * this.m11), Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), 0.0, 0.0, 1.0, Joml.BIT_AFFINE & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_general_translation(Double3x3 right) {
        return new Double3x3(this.m00, this.m01, Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02)), this.m10, this.m11, Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), this.m20, this.m21, Math.fma(right.m02(), this.m20, Math.fma(right.m12(), this.m21, this.m22)), 0);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_general_affine(Double3x3 right) {
        return new Double3x3(Math.fma(right.m00(), this.m00, right.m10() * this.m01), Math.fma(right.m01(), this.m00, right.m11() * this.m01), Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02)), Math.fma(right.m00(), this.m10, right.m10() * this.m11), Math.fma(right.m01(), this.m10, right.m11() * this.m11), Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), Math.fma(right.m00(), this.m20, right.m10() * this.m21), Math.fma(right.m01(), this.m20, right.m11() * this.m21), Math.fma(right.m02(), this.m20, Math.fma(right.m12(), this.m21, this.m22)), 0);
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
    public Double3x3 mul(Double3x3 right) {
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
    public Double3x3 mul(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
        return new Double3x3(Math.fma(m20, this.m02, Math.fma(m00, this.m00, m10 * this.m01)), Math.fma(m21, this.m02, Math.fma(m01, this.m00, m11 * this.m01)), Math.fma(m22, this.m02, Math.fma(m02, this.m00, m12 * this.m01)), Math.fma(m20, this.m12, Math.fma(m00, this.m10, m10 * this.m11)), Math.fma(m21, this.m12, Math.fma(m01, this.m10, m11 * this.m11)), Math.fma(m22, this.m12, Math.fma(m02, this.m10, m12 * this.m11)), Math.fma(m20, this.m22, Math.fma(m00, this.m20, m10 * this.m21)), Math.fma(m21, this.m22, Math.fma(m01, this.m20, m11 * this.m21)), Math.fma(m22, this.m22, Math.fma(m02, this.m20, m12 * this.m21)), 0);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_identity(Double2x2 right) {
        return new Double3x3(right.m00(), right.m01(), 0.0, right.m10(), right.m11(), 0.0, 0.0, 0.0, 1.0, (right.properties() & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_translation(Double2x2 right) {
        return new Double3x3(right.m00(), right.m01(), this.m02, right.m10(), right.m11(), this.m12, 0.0, 0.0, 1.0, (right.properties() & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal(Double2x2 right) {
        return new Double3x3(Math.fma(right.m00(), this.m11, -(right.m10() * this.m10)), Math.fma(right.m01(), this.m11, -(right.m11() * this.m10)), this.m02, Math.fma(right.m00(), this.m10, right.m10() * this.m11), Math.fma(right.m01(), this.m10, right.m11() * this.m11), this.m12, 0.0, 0.0, 1.0, (right.properties() & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_affine(Double2x2 right) {
        return new Double3x3(Math.fma(right.m00(), this.m00, right.m10() * this.m01), Math.fma(right.m01(), this.m00, right.m11() * this.m01), this.m02, Math.fma(right.m00(), this.m10, right.m10() * this.m11), Math.fma(right.m01(), this.m10, right.m11() * this.m11), this.m12, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_general(Double2x2 right) {
        return new Double3x3(Math.fma(right.m00(), this.m00, right.m10() * this.m01), Math.fma(right.m01(), this.m00, right.m11() * this.m01), this.m02, Math.fma(right.m00(), this.m10, right.m10() * this.m11), Math.fma(right.m01(), this.m10, right.m11() * this.m11), this.m12, Math.fma(right.m00(), this.m20, right.m10() * this.m21), Math.fma(right.m01(), this.m20, right.m11() * this.m21), this.m22, 0);
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
    public Double3x3 mul(Double2x2 right) {
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
    private Double3x3 mul_general(Double2x3 right) {
        return new Double3x3(Math.fma(right.m00(), this.m00, right.m10() * this.m01), Math.fma(right.m01(), this.m00, right.m11() * this.m01), Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02)), Math.fma(right.m00(), this.m10, right.m10() * this.m11), Math.fma(right.m01(), this.m10, right.m11() * this.m11), Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), Math.fma(right.m00(), this.m20, right.m10() * this.m21), Math.fma(right.m01(), this.m20, right.m11() * this.m21), Math.fma(right.m02(), this.m20, Math.fma(right.m12(), this.m21, this.m22)), 0);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_identity(Double2x3 right) {
        return new Double3x3(right.m00(), right.m01(), right.m02(), right.m10(), right.m11(), right.m12(), 0.0, 0.0, 1.0, right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_translation(Double2x3 right) {
        return new Double3x3(right.m00(), right.m01(), right.m02() + this.m02, right.m10(), right.m11(), right.m12() + this.m12, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal(Double2x3 right) {
        return new Double3x3(Math.fma(right.m00(), this.m11, -(right.m10() * this.m10)), Math.fma(right.m01(), this.m11, -(right.m11() * this.m10)), Math.fma(-right.m12(), this.m10, Math.fma(right.m02(), this.m11, this.m02)), Math.fma(right.m00(), this.m10, right.m10() * this.m11), Math.fma(right.m01(), this.m10, right.m11() * this.m11), Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_affine(Double2x3 right) {
        return new Double3x3(Math.fma(right.m00(), this.m00, right.m10() * this.m01), Math.fma(right.m01(), this.m00, right.m11() * this.m01), Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02)), Math.fma(right.m00(), this.m10, right.m10() * this.m11), Math.fma(right.m01(), this.m10, right.m11() * this.m11), Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), 0.0, 0.0, 1.0, Joml.BIT_AFFINE & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_translation_translation(Double2x3 right) {
        return new Double3x3(1.0, 0.0, right.m02() + this.m02, 0.0, 1.0, right.m12() + this.m12, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION & right.properties());
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_orthogonal_translation(Double2x3 right, int _props) {
        return new Double3x3(this.m00, this.m01, Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02)), this.m10, this.m11, Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), 0.0, 0.0, 1.0, _props);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3x3 mul_general_translation(Double2x3 right) {
        return new Double3x3(this.m00, this.m01, Math.fma(right.m02(), this.m00, Math.fma(right.m12(), this.m01, this.m02)), this.m10, this.m11, Math.fma(right.m02(), this.m10, Math.fma(right.m12(), this.m11, this.m12)), this.m20, this.m21, Math.fma(right.m02(), this.m20, Math.fma(right.m12(), this.m21, this.m22)), 0);
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
    public Double3x3 mul(Double2x3 right) {
        int p = this.properties;
        int q = right.properties();
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(1.0, 0.0, right.m02(), 0.0, 1.0, right.m12(), 0.0, 0.0, 1.0, right.properties());
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
    private Double3x3 preMul_general(Double3x3 other) {
        return new Double3x3(Math.fma(other.m02(), this.m20, Math.fma(other.m00(), this.m00, other.m01() * this.m10)), Math.fma(other.m02(), this.m21, Math.fma(other.m00(), this.m01, other.m01() * this.m11)), Math.fma(other.m02(), this.m22, Math.fma(other.m00(), this.m02, other.m01() * this.m12)), Math.fma(other.m12(), this.m20, Math.fma(other.m10(), this.m00, other.m11() * this.m10)), Math.fma(other.m12(), this.m21, Math.fma(other.m10(), this.m01, other.m11() * this.m11)), Math.fma(other.m12(), this.m22, Math.fma(other.m10(), this.m02, other.m11() * this.m12)), Math.fma(other.m22(), this.m20, Math.fma(other.m20(), this.m00, other.m21() * this.m10)), Math.fma(other.m22(), this.m21, Math.fma(other.m20(), this.m01, other.m21() * this.m11)), Math.fma(other.m22(), this.m22, Math.fma(other.m20(), this.m02, other.m21() * this.m12)), 0);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_translation(Double3x3 other) {
        return new Double3x3(other.m00(), other.m01(), Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02())), other.m10(), other.m11(), Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12())), other.m20(), other.m21(), Math.fma(other.m20(), this.m02, Math.fma(other.m21(), this.m12, other.m22())), Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_orthogonal(Double3x3 other) {
        return new Double3x3(Math.fma(other.m00(), this.m11, other.m01() * this.m10), Math.fma(other.m01(), this.m11, -(other.m00() * this.m10)), Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02())), Math.fma(other.m10(), this.m11, other.m11() * this.m10), Math.fma(other.m11(), this.m11, -(other.m10() * this.m10)), Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12())), Math.fma(other.m20(), this.m11, other.m21() * this.m10), Math.fma(other.m21(), this.m11, -(other.m20() * this.m10)), Math.fma(other.m20(), this.m02, Math.fma(other.m21(), this.m12, other.m22())), Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_affine(Double3x3 other) {
        return new Double3x3(Math.fma(other.m00(), this.m00, other.m01() * this.m10), Math.fma(other.m00(), this.m01, other.m01() * this.m11), Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02())), Math.fma(other.m10(), this.m00, other.m11() * this.m10), Math.fma(other.m10(), this.m01, other.m11() * this.m11), Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12())), Math.fma(other.m20(), this.m00, other.m21() * this.m10), Math.fma(other.m20(), this.m01, other.m21() * this.m11), Math.fma(other.m20(), this.m02, Math.fma(other.m21(), this.m12, other.m22())), Joml.BIT_AFFINE & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_translation_translation(Double3x3 other) {
        return new Double3x3(1.0, 0.0, other.m02() + this.m02, 0.0, 1.0, other.m12() + this.m12, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_translation_affine(Double3x3 other) {
        return new Double3x3(other.m00(), other.m01(), Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02())), other.m10(), other.m11(), Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12())), 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_orthogonal_translation(Double3x3 other, int _props) {
        return new Double3x3(this.m00, this.m01, other.m02() + this.m02, this.m10, this.m11, other.m12() + this.m12, 0.0, 0.0, 1.0, _props);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_orthogonal_affine(Double3x3 other) {
        return new Double3x3(Math.fma(other.m00(), this.m11, other.m01() * this.m10), Math.fma(other.m01(), this.m11, -(other.m00() * this.m10)), Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02())), Math.fma(other.m10(), this.m11, other.m11() * this.m10), Math.fma(other.m11(), this.m11, -(other.m10() * this.m10)), Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12())), 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_affine_affine(Double3x3 other) {
        return new Double3x3(Math.fma(other.m00(), this.m00, other.m01() * this.m10), Math.fma(other.m00(), this.m01, other.m01() * this.m11), Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02())), Math.fma(other.m10(), this.m00, other.m11() * this.m10), Math.fma(other.m10(), this.m01, other.m11() * this.m11), Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12())), 0.0, 0.0, 1.0, Joml.BIT_AFFINE & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_general_translation(Double3x3 other) {
        return new Double3x3(Math.fma(other.m02(), this.m20, this.m00), Math.fma(other.m02(), this.m21, this.m01), Math.fma(other.m02(), this.m22, this.m02), Math.fma(other.m12(), this.m20, this.m10), Math.fma(other.m12(), this.m21, this.m11), Math.fma(other.m12(), this.m22, this.m12), this.m20, this.m21, this.m22, 0);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_general_affine(Double3x3 other) {
        return new Double3x3(Math.fma(other.m02(), this.m20, Math.fma(other.m00(), this.m00, other.m01() * this.m10)), Math.fma(other.m02(), this.m21, Math.fma(other.m00(), this.m01, other.m01() * this.m11)), Math.fma(other.m02(), this.m22, Math.fma(other.m00(), this.m02, other.m01() * this.m12)), Math.fma(other.m12(), this.m20, Math.fma(other.m10(), this.m00, other.m11() * this.m10)), Math.fma(other.m12(), this.m21, Math.fma(other.m10(), this.m01, other.m11() * this.m11)), Math.fma(other.m12(), this.m22, Math.fma(other.m10(), this.m02, other.m11() * this.m12)), this.m20, this.m21, this.m22, 0);
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
    public Double3x3 preMul(Double3x3 other) {
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
    public Double3x3 preMul(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
        return new Double3x3(Math.fma(m02, this.m20, Math.fma(m00, this.m00, m01 * this.m10)), Math.fma(m02, this.m21, Math.fma(m00, this.m01, m01 * this.m11)), Math.fma(m02, this.m22, Math.fma(m00, this.m02, m01 * this.m12)), Math.fma(m12, this.m20, Math.fma(m10, this.m00, m11 * this.m10)), Math.fma(m12, this.m21, Math.fma(m10, this.m01, m11 * this.m11)), Math.fma(m12, this.m22, Math.fma(m10, this.m02, m11 * this.m12)), Math.fma(m22, this.m20, Math.fma(m20, this.m00, m21 * this.m10)), Math.fma(m22, this.m21, Math.fma(m20, this.m01, m21 * this.m11)), Math.fma(m22, this.m22, Math.fma(m20, this.m02, m21 * this.m12)), 0);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_identity(Double2x2 other) {
        return new Double3x3(other.m00(), other.m01(), 0.0, other.m10(), other.m11(), 0.0, 0.0, 0.0, 1.0, (other.properties() & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_IDENTITY : Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_translation(Double2x2 other) {
        return new Double3x3(other.m00(), other.m01(), Math.fma(other.m00(), this.m02, other.m01() * this.m12), other.m10(), other.m11(), Math.fma(other.m10(), this.m02, other.m11() * this.m12), 0.0, 0.0, 1.0, (other.properties() & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_TRANSLATION : Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_orthogonal(Double2x2 other) {
        return new Double3x3(Math.fma(other.m00(), this.m11, other.m01() * this.m10), Math.fma(other.m01(), this.m11, -(other.m00() * this.m10)), Math.fma(other.m00(), this.m02, other.m01() * this.m12), Math.fma(other.m10(), this.m11, other.m11() * this.m10), Math.fma(other.m11(), this.m11, -(other.m10() * this.m10)), Math.fma(other.m10(), this.m02, other.m11() * this.m12), 0.0, 0.0, 1.0, (other.properties() & Joml.UNIQUE_IDENTITY) != 0 ? Joml.BIT_ORTHOGONAL : Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_affine(Double2x2 other) {
        return new Double3x3(Math.fma(other.m00(), this.m00, other.m01() * this.m10), Math.fma(other.m00(), this.m01, other.m01() * this.m11), Math.fma(other.m00(), this.m02, other.m01() * this.m12), Math.fma(other.m10(), this.m00, other.m11() * this.m10), Math.fma(other.m10(), this.m01, other.m11() * this.m11), Math.fma(other.m10(), this.m02, other.m11() * this.m12), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_general(Double2x2 other) {
        return new Double3x3(Math.fma(other.m00(), this.m00, other.m01() * this.m10), Math.fma(other.m00(), this.m01, other.m01() * this.m11), Math.fma(other.m00(), this.m02, other.m01() * this.m12), Math.fma(other.m10(), this.m00, other.m11() * this.m10), Math.fma(other.m10(), this.m01, other.m11() * this.m11), Math.fma(other.m10(), this.m02, other.m11() * this.m12), this.m20, this.m21, this.m22, 0);
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
    public Double3x3 preMul(Double2x2 other) {
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
    private Double3x3 preMul_general(Double2x3 other) {
        return new Double3x3(Math.fma(other.m02(), this.m20, Math.fma(other.m00(), this.m00, other.m01() * this.m10)), Math.fma(other.m02(), this.m21, Math.fma(other.m00(), this.m01, other.m01() * this.m11)), Math.fma(other.m02(), this.m22, Math.fma(other.m00(), this.m02, other.m01() * this.m12)), Math.fma(other.m12(), this.m20, Math.fma(other.m10(), this.m00, other.m11() * this.m10)), Math.fma(other.m12(), this.m21, Math.fma(other.m10(), this.m01, other.m11() * this.m11)), Math.fma(other.m12(), this.m22, Math.fma(other.m10(), this.m02, other.m11() * this.m12)), this.m20, this.m21, this.m22, 0);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_identity(Double2x3 other) {
        return new Double3x3(other.m00(), other.m01(), other.m02(), other.m10(), other.m11(), other.m12(), 0.0, 0.0, 1.0, other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_translation(Double2x3 other) {
        return new Double3x3(other.m00(), other.m01(), Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02())), other.m10(), other.m11(), Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12())), 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_orthogonal(Double2x3 other) {
        return new Double3x3(Math.fma(other.m00(), this.m11, other.m01() * this.m10), Math.fma(other.m01(), this.m11, -(other.m00() * this.m10)), Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02())), Math.fma(other.m10(), this.m11, other.m11() * this.m10), Math.fma(other.m11(), this.m11, -(other.m10() * this.m10)), Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12())), 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_affine(Double2x3 other) {
        return new Double3x3(Math.fma(other.m00(), this.m00, other.m01() * this.m10), Math.fma(other.m00(), this.m01, other.m01() * this.m11), Math.fma(other.m00(), this.m02, Math.fma(other.m01(), this.m12, other.m02())), Math.fma(other.m10(), this.m00, other.m11() * this.m10), Math.fma(other.m10(), this.m01, other.m11() * this.m11), Math.fma(other.m10(), this.m02, Math.fma(other.m11(), this.m12, other.m12())), 0.0, 0.0, 1.0, Joml.BIT_AFFINE & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_translation_translation(Double2x3 other) {
        return new Double3x3(1.0, 0.0, other.m02() + this.m02, 0.0, 1.0, other.m12() + this.m12, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION & other.properties());
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_orthogonal_translation(Double2x3 other, int _props) {
        return new Double3x3(this.m00, this.m01, other.m02() + this.m02, this.m10, this.m11, other.m12() + this.m12, 0.0, 0.0, 1.0, _props);
    }


    /**
     * Private body of {@code preMul}, specialized by runtime matrix properties; reached only
     * through the public {@code preMul} dispatcher.
     */
    private Double3x3 preMul_general_translation(Double2x3 other) {
        return new Double3x3(Math.fma(other.m02(), this.m20, this.m00), Math.fma(other.m02(), this.m21, this.m01), Math.fma(other.m02(), this.m22, this.m02), Math.fma(other.m12(), this.m20, this.m10), Math.fma(other.m12(), this.m21, this.m11), Math.fma(other.m12(), this.m22, this.m12), this.m20, this.m21, this.m22, 0);
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
    public Double3x3 preMul(Double2x3 other) {
        int p = this.properties;
        int q = other.properties();
        if ((q & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return this;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            if ((q & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(1.0, 0.0, other.m02(), 0.0, 1.0, other.m12(), 0.0, 0.0, 1.0, other.properties());
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
    public Double3x3 addScaled(Double3x3 other, double weight) {
        return new Double3x3(Math.fma(weight, other.m00(), this.m00), Math.fma(weight, other.m01(), this.m01), Math.fma(weight, other.m02(), this.m02), Math.fma(weight, other.m10(), this.m10), Math.fma(weight, other.m11(), this.m11), Math.fma(weight, other.m12(), this.m12), Math.fma(weight, other.m20(), this.m20), Math.fma(weight, other.m21(), this.m21), Math.fma(weight, other.m22(), this.m22), 0);
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
    public Double3x3 addScaled(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22, double weight) {
        return new Double3x3(Math.fma(weight, m00, this.m00), Math.fma(weight, m01, this.m01), Math.fma(weight, m02, this.m02), Math.fma(weight, m10, this.m10), Math.fma(weight, m11, this.m11), Math.fma(weight, m12, this.m12), Math.fma(weight, m20, this.m20), Math.fma(weight, m21, this.m21), Math.fma(weight, m22, this.m22), 0);
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
    public static Double3x3 makeOuterProduct(Double3 col, Double3 row) {
        double colX = col.x();
        double colY = col.y();
        double colZ = col.z();
        double rowX = row.x();
        double rowY = row.y();
        double rowZ = row.z();
        return new Double3x3(colX * rowX, colX * rowY, colX * rowZ, colY * rowX, colY * rowY, colY * rowZ, colZ * rowX, colZ * rowY, colZ * rowZ, 0);
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
    public static Double3x3 makeOuterProduct(double colX, double colY, double colZ, double rowX, double rowY, double rowZ) {
        return new Double3x3(colX * rowX, colX * rowY, colX * rowZ, colY * rowX, colY * rowY, colY * rowZ, colZ * rowX, colZ * rowY, colZ * rowZ, 0);
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
    public Double3x3 lookAlong(Double3 dir, Double3 up) {
        double dirX = dir.x();
        double dirY = dir.y();
        double dirZ = dir.z();
        double upX = up.x();
        double upY = up.y();
        double upZ = up.z();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return lookAlong_identity(dirX, dirY, dirZ, upX, upY, upZ);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return lookAlong_translation(dirX, dirY, dirZ, upX, upY, upZ);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return lookAlong_orthogonal(dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_general(dirX, dirY, dirZ, upX, upY, upZ);
    }

    /** Private per-column body of {@code lookAlong_identity_s6a304d84_tail}; reached only through it. */
    private double[] lookAlong_identity_s6a304d84_tail_s49153e33_c1(double _t15, double _t40, double _t16, double _t41, double _t39, double _t17) {
        return new double[] {Math.fma(_t15, _t40, -(_t16 * _t41)), Math.fma(_t16, _t39, -(_t17 * _t40)), Math.fma(_t17, _t41, -(_t15 * _t39))};
    }

    /** Private tail of {@code lookAlong_identity}; reached only through it. */
    private Double3x3 lookAlong_identity_s6a304d84_tail(double dirZ, double _t14, double dirX, double _t31, double _t38, double _t32, double _t33, double _t15, int _props) {
        double _t16 = dirZ * _t14;
        double _t17 = dirX * _t14;
        double _t39 = _t31 * _t38;
        double _t40 = _t32 * _t38;
        double _t41 = _t33 * _t38;
        double[] _col0 = new double[] {_t39, _t41, _t40};
        double[] _col1 = lookAlong_identity_s6a304d84_tail_s49153e33_c1(_t15, _t40, _t16, _t41, _t39, _t17);
        double[] _col2 = new double[] {_t17, _t15, _t16};
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Private body of {@code lookAlong}, specialized by runtime matrix properties; reached only
     * through the public {@code lookAlong} dispatcher.
     */
    private Double3x3 lookAlong_identity(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        double _t14 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t22 = Math.fma(-dirY, _sp0, upY);
        double _t23 = Math.fma(-dirZ, _sp0, upZ);
        double _t24 = Math.fma(-dirX, _sp0, upX);
        double _t31 = Math.fma(dirZ, _t22, -(dirY * _t23));
        double _t32 = Math.fma(dirY, _t24, -(dirX * _t22));
        double _t33 = Math.fma(dirX, _t23, -(dirZ * _t24));
        double _t38 = Math.fma(_t32, _t32, Math.fma(_t33, _t33, _t31 * _t31));
        if (!(_t38 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _t38 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_identity_s6a304d84_tail(dirZ, _t14, dirX, _t31, (1.0 / java.lang.Math.sqrt(_t38)), _t32, _t33, dirY * _t14, 0);
    }

    /** Private per-column body of {@code lookAlong_translation_s6a304d84_tail}; reached only through it. */
    private double[] lookAlong_translation_s6a304d84_tail_s5f4019de_c1(double _t44, double _t16, double _t39, double _t17, double _t41, double _t40, double _t15) {
        return new double[] {Math.fma(this.m02, _t44, Math.fma(_t16, _t39, -(_t17 * _t41))), Math.fma(this.m12, _t44, Math.fma(_t17, _t40, -(_t15 * _t39))), _t44};
    }

    /** Private per-column body of {@code lookAlong_translation_s6a304d84_tail}; reached only through it. */
    private double[] lookAlong_translation_s6a304d84_tail_s5f4019de_c2(double dirX, double _t14, double _t17, double dirY) {
        return new double[] {Math.fma(dirX, _t14, this.m02 * _t17), Math.fma(dirY, _t14, this.m12 * _t17), _t17};
    }

    /** Private tail of {@code lookAlong_translation}; reached only through it. */
    private Double3x3 lookAlong_translation_s6a304d84_tail(double dirY, double _t14, double dirZ, double _t31, double _t38, double _t33, double _t32, double _t15, double dirX, int _props) {
        double _t16 = dirY * _t14;
        double _t17 = dirZ * _t14;
        double _t39 = _t31 * _t38;
        double _t40 = _t33 * _t38;
        double _t41 = _t32 * _t38;
        double[] _col0 = new double[] {Math.fma(this.m02, _t39, _t40), Math.fma(this.m12, _t39, _t41), _t39};
        double[] _col1 = lookAlong_translation_s6a304d84_tail_s5f4019de_c1(Math.fma(_t15, _t41, -(_t16 * _t40)), _t16, _t39, _t17, _t41, _t40, _t15);
        double[] _col2 = lookAlong_translation_s6a304d84_tail_s5f4019de_c2(dirX, _t14, _t17, dirY);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Private body of {@code lookAlong}, specialized by runtime matrix properties; reached only
     * through the public {@code lookAlong} dispatcher.
     */
    private Double3x3 lookAlong_translation(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        double _t14 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t22 = Math.fma(-dirX, _sp0, upX);
        double _t23 = Math.fma(-dirY, _sp0, upY);
        double _t24 = Math.fma(-dirZ, _sp0, upZ);
        double _t31 = Math.fma(dirY, _t22, -(dirX * _t23));
        double _t32 = Math.fma(dirX, _t24, -(dirZ * _t22));
        double _t33 = Math.fma(dirZ, _t23, -(dirY * _t24));
        double _t38 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        if (!(_t38 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _t38 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_translation_s6a304d84_tail(dirY, _t14, dirZ, _t31, (1.0 / java.lang.Math.sqrt(_t38)), _t33, _t32, dirX * _t14, dirX, 0);
    }

    /**
     * Private per-column body of {@code lookAlong_orthogonal_s6a304d84_tail}. Shared by the
     * identical private paths of {@code lookAlong}, {@code rotateAxis}, {@code rotateXYZ},
     * {@code rotateXZY}, {@code rotateYXZ}, {@code rotateYZX}, {@code rotateZXY} and
     * {@code rotateZYX}; reached only through them.
     */
    private double[] lookAlong_orthogonal_s6a304d84_tail_s18d80cb4_c0(double _t39, double _t40, double _t41) {
        return new double[] {Math.fma(this.m02, _t39, Math.fma(this.m00, _t40, this.m01 * _t41)), Math.fma(this.m12, _t39, Math.fma(this.m10, _t40, this.m11 * _t41)), _t39};
    }

    /** Private tail of {@code lookAlong_orthogonal}; reached only through it. */
    private Double3x3 lookAlong_orthogonal_s6a304d84_tail(double dirY, double _t14, double dirZ, double _t31, double _t38, double _t33, double _t32, double _t15, int _props) {
        double _t16 = dirY * _t14;
        double _t17 = dirZ * _t14;
        double _t39 = _t31 * _t38;
        double _t40 = _t33 * _t38;
        double _t41 = _t32 * _t38;
        double[] _col0 = lookAlong_orthogonal_s6a304d84_tail_s18d80cb4_c0(_t39, _t40, _t41);
        double[] _col1 = lookAlong_orthogonal_s6a304d84_tail_s18d80cb4_c0(Math.fma(_t15, _t41, -(_t16 * _t40)), Math.fma(_t16, _t39, -(_t17 * _t41)), Math.fma(_t17, _t40, -(_t15 * _t39)));
        double[] _col2 = lookAlong_orthogonal_s6a304d84_tail_s18d80cb4_c0(_t17, _t15, _t16);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Private body of {@code lookAlong}, specialized by runtime matrix properties; reached only
     * through the public {@code lookAlong} dispatcher.
     */
    private Double3x3 lookAlong_orthogonal(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        double _t14 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t22 = Math.fma(-dirX, _sp0, upX);
        double _t23 = Math.fma(-dirY, _sp0, upY);
        double _t24 = Math.fma(-dirZ, _sp0, upZ);
        double _t31 = Math.fma(dirY, _t22, -(dirX * _t23));
        double _t32 = Math.fma(dirX, _t24, -(dirZ * _t22));
        double _t33 = Math.fma(dirZ, _t23, -(dirY * _t24));
        double _t38 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        if (!(_t38 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _t38 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_orthogonal_s6a304d84_tail(dirY, _t14, dirZ, _t31, (1.0 / java.lang.Math.sqrt(_t38)), _t33, _t32, dirX * _t14, 0);
    }

    /**
     * Private per-column body of {@code lookAlong_general_s6a304d84_tail}. Shared by the identical
     * private paths of {@code lookAlong}, {@code rotateAxis}, {@code rotateXYZ}, {@code rotateXZY},
     * {@code rotateYXZ}, {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only
     * through them.
     */
    private double[] lookAlong_general_s6a304d84_tail_s18d80cb4_c0(double _t39, double _t40, double _t41) {
        return new double[] {Math.fma(this.m02, _t39, Math.fma(this.m00, _t40, this.m01 * _t41)), Math.fma(this.m12, _t39, Math.fma(this.m10, _t40, this.m11 * _t41)), Math.fma(this.m22, _t39, Math.fma(this.m20, _t40, this.m21 * _t41))};
    }

    /** Private tail of {@code lookAlong_general}; reached only through it. */
    private Double3x3 lookAlong_general_s6a304d84_tail(double dirY, double _t14, double dirZ, double _t31, double _t38, double _t33, double _t32, double _t15, int _props) {
        double _t16 = dirY * _t14;
        double _t17 = dirZ * _t14;
        double _t39 = _t31 * _t38;
        double _t40 = _t33 * _t38;
        double _t41 = _t32 * _t38;
        double[] _col0 = lookAlong_general_s6a304d84_tail_s18d80cb4_c0(_t39, _t40, _t41);
        double[] _col1 = lookAlong_general_s6a304d84_tail_s18d80cb4_c0(Math.fma(_t15, _t41, -(_t16 * _t40)), Math.fma(_t16, _t39, -(_t17 * _t41)), Math.fma(_t17, _t40, -(_t15 * _t39)));
        double[] _col2 = lookAlong_general_s6a304d84_tail_s18d80cb4_c0(_t17, _t15, _t16);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Private body of {@code lookAlong}, specialized by runtime matrix properties; reached only
     * through the public {@code lookAlong} dispatcher.
     */
    private Double3x3 lookAlong_general(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        double _t14 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t22 = Math.fma(-dirX, _sp0, upX);
        double _t23 = Math.fma(-dirY, _sp0, upY);
        double _t24 = Math.fma(-dirZ, _sp0, upZ);
        double _t31 = Math.fma(dirY, _t22, -(dirX * _t23));
        double _t32 = Math.fma(dirX, _t24, -(dirZ * _t22));
        double _t33 = Math.fma(dirZ, _t23, -(dirY * _t24));
        double _t38 = Math.fma(_t31, _t31, Math.fma(_t32, _t32, _t33 * _t33));
        if (!(_t38 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _t38 < Double.POSITIVE_INFINITY)) return lookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_general_s6a304d84_tail(dirY, _t14, dirZ, _t31, (1.0 / java.lang.Math.sqrt(_t38)), _t33, _t32, dirX * _t14, 0);
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
    public Double3x3 lookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
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
    private Double3x3 lookAlong_degenerate(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double[] _bundle0 = lookAlong_degenerate_s62ce1b16_1(dirX, dirY, dirZ, upX, upY, upZ);
        double[] _bundle1 = lookAlong_degenerate_s62ce1b16_2(_bundle0[4], _bundle0[3], _bundle0[5], _bundle0[0], _bundle0[1], _bundle0[2]);
        double[] _bundle2 = lookAlong_degenerate_s62ce1b16_3(_bundle0[3], _bundle0[4], _bundle0[5], _bundle1[1], _bundle1[0], _bundle1[2]);
        return new Double3x3(_bundle2[0], _bundle2[1], _bundle2[2], _bundle2[3], _bundle2[4], _bundle2[5], _bundle2[6], _bundle2[7], _bundle2[8], 0);
    }

    /** Part 1 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private double[] lookAlong_degenerate_s62ce1b16_1(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = 1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        return new double[] {_t21, _t22, _t23, _t24, _t25, _t26};
    }

    /** Part 2 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private double[] lookAlong_degenerate_s62ce1b16_2(double _t25, double _t24, double _t26, double _t21, double _t22, double _t23) {
        double _t34, _t35, _t39;
        if (java.lang.Math.abs(_t25) > java.lang.Math.abs(_t24)) {
            _t34 = 0.0;
            _t35 = -_t26;
            _t39 = _t25;
        } else {
            _t34 = _t26;
            _t35 = 0.0;
            _t39 = -_t24;
        }
        double _t41 = -Math.fma(_t21, _t24, Math.fma(_t25, _t22, _t26 * _t23));
        double _t42 = Math.fma(_t41, _t25, _t22);
        double _t43 = Math.fma(_t41, _t26, _t23);
        double _t44 = Math.fma(_t41, _t24, _t21);
        double _t53 = Math.fma(_t42, _t26, -(_t43 * _t25));
        double _t54 = Math.fma(_t44, _t25, -(_t42 * _t24));
        double _t55 = Math.fma(_t43, _t24, -(_t44 * _t26));
        double _t59 = Math.fma(_t53, _t53, Math.fma(_t54, _t54, _t55 * _t55));
        double _t64, _t65, _t66, _t67;
        if (_t59 <= Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 5.048709793414476E-29) {
            _t64 = (1.0 / java.lang.Math.sqrt(Math.fma(_t34, _t34, Math.fma(_t35, _t35, _t39 * _t39))));
            _t65 = _t64 * _t34;
            _t66 = _t64 * _t35;
            _t67 = _t64 * _t39;
        } else {
            _t64 = (1.0 / java.lang.Math.sqrt(_t59));
            _t65 = _t64 * _t53;
            _t66 = _t64 * _t55;
            _t67 = _t64 * _t54;
        }
        return new double[] {_t65, _t66, _t67};
    }

    /** Part 3 of {@code lookAlong_degenerate}, split to fit the inline budget; reached only through it. */
    private double[] lookAlong_degenerate_s62ce1b16_3(double _t24, double _t25, double _t26, double _t66, double _t65, double _t67) {
        double _t74 = Math.fma(_t66, _t24, -(_t65 * _t25));
        double _t75 = Math.fma(_t65, _t26, -(_t67 * _t24));
        double _t76 = Math.fma(_t67, _t25, -(_t66 * _t26));
        return new double[] {Math.fma(this.m02, _t65, Math.fma(this.m00, _t66, this.m01 * _t67)), Math.fma(this.m02, _t76, Math.fma(this.m00, _t75, this.m01 * _t74)), Math.fma(this.m02, _t24, Math.fma(this.m00, _t25, this.m01 * _t26)), Math.fma(this.m12, _t65, Math.fma(this.m10, _t66, this.m11 * _t67)), Math.fma(this.m12, _t76, Math.fma(this.m10, _t75, this.m11 * _t74)), Math.fma(this.m12, _t24, Math.fma(this.m10, _t25, this.m11 * _t26)), Math.fma(this.m22, _t65, Math.fma(this.m20, _t66, this.m21 * _t67)), Math.fma(this.m22, _t76, Math.fma(this.m20, _t75, this.m21 * _t74)), Math.fma(this.m22, _t24, Math.fma(this.m20, _t25, this.m21 * _t26))};
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
    public static Double3x3 makeFromDualQuat(DoubleDualQuat dq) {
        double dqRX = dq.rX();
        double dqRY = dq.rY();
        double dqRZ = dq.rZ();
        double dqRW = dq.rW();
        double _sp0 = dqRX + dqRX;
        double _t0 = dqRY * dqRY;
        double _t2 = dqRZ * dqRW;
        double _t3 = dqRY * dqRW;
        double _t4 = dqRX * dqRX;
        double _t5 = dqRY * dqRZ;
        double _t6 = Math.fma(-2.0, dqRZ * dqRZ, 1.0);
        return new Double3x3(Math.fma(-2.0, _t0, _t6), Math.fma(-2.0, _t2, _sp0 * dqRY), 2.0 * Math.fma(dqRX, dqRZ, _t3), 2.0 * Math.fma(dqRX, dqRY, _t2), Math.fma(-2.0, _t4, _t6), Math.fma(-2.0, dqRX * dqRW, _t5 + _t5), Math.fma(-2.0, _t3, _sp0 * dqRZ), 2.0 * Math.fma(dqRX, dqRW, _t5), Math.fma(-2.0, _t4, Math.fma(-2.0, _t0, 1.0)), 0);
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
    public static Double3x3 makeFromDualQuat(double dqRX, double dqRY, double dqRZ, double dqRW, double dqDX, double dqDY, double dqDZ, double dqDW) {
        double _sp0 = dqRX + dqRX;
        double _t0 = dqRY * dqRY;
        double _t2 = dqRZ * dqRW;
        double _t3 = dqRY * dqRW;
        double _t4 = dqRX * dqRX;
        double _t5 = dqRY * dqRZ;
        double _t6 = Math.fma(-2.0, dqRZ * dqRZ, 1.0);
        return new Double3x3(Math.fma(-2.0, _t0, _t6), Math.fma(-2.0, _t2, _sp0 * dqRY), 2.0 * Math.fma(dqRX, dqRZ, _t3), 2.0 * Math.fma(dqRX, dqRY, _t2), Math.fma(-2.0, _t4, _t6), Math.fma(-2.0, dqRX * dqRW, _t5 + _t5), Math.fma(-2.0, _t3, _sp0 * dqRZ), 2.0 * Math.fma(dqRX, dqRW, _t5), Math.fma(-2.0, _t4, Math.fma(-2.0, _t0, 1.0)), 0);
    }


    /**
     * Create a rotation by {@code angle}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting matrix
     */
    public static Double3x3 makeRotation(double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        return new Double3x3(_t1, -_t0, 0.0, _t0, _t1, 0.0, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
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
    public static Double3x3 makeRotationAxis(double angle, Double3 axis) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisY;
        double _t3 = axisX * axisZ;
        double _t4 = axisY * axisZ;
        double _t5 = 1.0 - _t1;
        return new Double3x3(Math.fma(_t5, axisX * axisX, _t1), Math.fma(_t5, _t2, -(axisZ * _t0)), Math.fma(axisY, _t0, _t5 * _t3), Math.fma(axisZ, _t0, _t5 * _t2), Math.fma(_t5, axisY * axisY, _t1), Math.fma(_t5, _t4, -(axisX * _t0)), Math.fma(_t5, _t3, -(axisY * _t0)), Math.fma(axisX, _t0, _t5 * _t4), Math.fma(_t5, axisZ * axisZ, _t1), 0);
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
    public static Double3x3 makeRotationAxis(double angle, double axisX, double axisY, double axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisY;
        double _t3 = axisX * axisZ;
        double _t4 = axisY * axisZ;
        double _t5 = 1.0 - _t1;
        return new Double3x3(Math.fma(_t5, axisX * axisX, _t1), Math.fma(_t5, _t2, -(axisZ * _t0)), Math.fma(axisY, _t0, _t5 * _t3), Math.fma(axisZ, _t0, _t5 * _t2), Math.fma(_t5, axisY * axisY, _t1), Math.fma(_t5, _t4, -(axisX * _t0)), Math.fma(_t5, _t3, -(axisY * _t0)), Math.fma(axisX, _t0, _t5 * _t4), Math.fma(_t5, axisZ * axisZ, _t1), 0);
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
    public static Double3x3 makeRotationLookAlong(Double3 dir, Double3 up) {
        return makeRotationLookAlong(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private per-column body of {@code makeRotationLookAlong_s6a304d84_tail}. Shared by 4
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private static double[] makeRotationLookAlong_s6a304d84_tail_s49153e33_c0(double _t39, double _t41, double _t40) {
        return new double[] {_t39, _t41, _t40};
    }

    /** Private per-column body of {@code makeRotationLookAlong_s6a304d84_tail}; reached only through it. */
    private static double[] makeRotationLookAlong_s6a304d84_tail_s49153e33_c1(double _t15, double _t40, double _t16, double _t41, double _t39, double _t17) {
        return new double[] {Math.fma(_t15, _t40, -(_t16 * _t41)), Math.fma(_t16, _t39, -(_t17 * _t40)), Math.fma(_t17, _t41, -(_t15 * _t39))};
    }

    /** Private tail of {@code makeRotationLookAlong}; reached only through it. */
    private static Double3x3 makeRotationLookAlong_s6a304d84_tail(double dirZ, double _t14, double dirX, double _t31, double _t38, double _t32, double _t33, double _t15, int _props) {
        double _t16 = dirZ * _t14;
        double _t17 = dirX * _t14;
        double _t39 = _t31 * _t38;
        double _t40 = _t32 * _t38;
        double _t41 = _t33 * _t38;
        double[] _col0 = makeRotationLookAlong_s6a304d84_tail_s49153e33_c0(_t39, _t41, _t40);
        double[] _col1 = makeRotationLookAlong_s6a304d84_tail_s49153e33_c1(_t15, _t40, _t16, _t41, _t39, _t17);
        double[] _col2 = makeRotationLookAlong_s6a304d84_tail_s49153e33_c0(_t17, _t15, _t16);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
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
    public static Double3x3 makeRotationLookAlong(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t7 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _sp0 = Math.fma(dirZ, upZ, Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 2.2250738585072014E-308 && _t7 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        double _t14 = (1.0 / java.lang.Math.sqrt(_t7));
        double _t22 = Math.fma(-dirY, _sp0, upY);
        double _t23 = Math.fma(-dirZ, _sp0, upZ);
        double _t24 = Math.fma(-dirX, _sp0, upX);
        double _t31 = Math.fma(dirZ, _t22, -(dirY * _t23));
        double _t32 = Math.fma(dirY, _t24, -(dirX * _t22));
        double _t33 = Math.fma(dirX, _t23, -(dirZ * _t24));
        double _t38 = Math.fma(_t32, _t32, Math.fma(_t33, _t33, _t31 * _t31));
        if (!(_t38 > Math.fma(_t7, Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)) * 5.048709793414476E-29, 2.2250738585072014E-308) && _t38 < Double.POSITIVE_INFINITY)) return makeRotationLookAlong_degenerate(dirX, dirY, dirZ, upX, upY, upZ);
        return makeRotationLookAlong_s6a304d84_tail(dirZ, _t14, dirX, _t31, (1.0 / java.lang.Math.sqrt(_t38)), _t32, _t33, dirY * _t14, 0);
    }

    /** Private per-column body of {@code makeRotationLookAlong_degenerate_s6a304d84_tail}; reached only through it. */
    private static double[] makeRotationLookAlong_degenerate_s6a304d84_tail_s46ddd8e7_c1(double _t65, double _t26, double _t67, double _t24, double _t66, double _t25) {
        return new double[] {Math.fma(_t65, _t26, -(_t67 * _t24)), Math.fma(_t66, _t24, -(_t65 * _t25)), Math.fma(_t67, _t25, -(_t66 * _t26))};
    }

    /** Private tail of {@code makeRotationLookAlong_degenerate}; reached only through it. */
    private static Double3x3 makeRotationLookAlong_degenerate_s6a304d84_tail(double _t21, double _t22, double _t23, double _t27, double _t28, double _t25, double _t24, double _t26, double _t34, double _t35, int _props) {
        double _t39 = _t27 > _t28 ? _t25 : -_t24;
        double _t41 = -Math.fma(_t21, _t24, Math.fma(_t25, _t22, _t26 * _t23));
        double _t42 = Math.fma(_t41, _t25, _t22);
        double _t43 = Math.fma(_t41, _t26, _t23);
        double _t44 = Math.fma(_t41, _t24, _t21);
        double _t53 = Math.fma(_t42, _t26, -(_t43 * _t25));
        double _t54 = Math.fma(_t44, _t25, -(_t42 * _t24));
        double _t55 = Math.fma(_t43, _t24, -(_t44 * _t26));
        double _t59 = Math.fma(_t53, _t53, Math.fma(_t54, _t54, _t55 * _t55));
        double _t64, _t65, _t66, _t67;
        if (_t59 <= Math.fma(_t21, _t21, Math.fma(_t22, _t22, _t23 * _t23)) * 5.048709793414476E-29) {
            _t64 = (1.0 / java.lang.Math.sqrt(Math.fma(_t34, _t34, Math.fma(_t35, _t35, _t39 * _t39))));
            _t65 = _t64 * _t34;
            _t66 = _t64 * _t35;
            _t67 = _t64 * _t39;
        } else {
            _t64 = (1.0 / java.lang.Math.sqrt(_t59));
            _t65 = _t64 * _t53;
            _t66 = _t64 * _t55;
            _t67 = _t64 * _t54;
        }
        double[] _col0 = makeRotationLookAlong_s6a304d84_tail_s49153e33_c0(_t66, _t67, _t65);
        double[] _col1 = makeRotationLookAlong_degenerate_s6a304d84_tail_s46ddd8e7_c1(_t65, _t26, _t67, _t24, _t66, _t25);
        double[] _col2 = makeRotationLookAlong_s6a304d84_tail_s49153e33_c0(_t25, _t26, _t24);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], _props);
    }


    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    private static Double3x3 makeRotationLookAlong_degenerate(double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t0 = unitScale(dirX, dirY, dirZ);
        double _t1 = unitScale(upX, upY, upZ);
        double _t8 = dirZ * _t0;
        double _t9 = dirX * _t0;
        double _t10 = dirY * _t0;
        double _t16 = Math.fma(_t8, _t8, Math.fma(_t9, _t9, _t10 * _t10));
        double _t17 = (1.0 / java.lang.Math.sqrt(_t16));
        double _t21, _t22, _t23, _t24, _t25, _t26;
        if (_t16 == 0.0) {
            _t21 = 0.0;
            _t22 = 0.0;
            _t23 = 1.0;
            _t24 = 1.0;
            _t25 = 0.0;
            _t26 = 0.0;
        } else {
            _t21 = upZ * _t1;
            _t22 = upX * _t1;
            _t23 = upY * _t1;
            _t24 = _t17 * _t8;
            _t25 = _t17 * _t9;
            _t26 = _t17 * _t10;
        }
        double _t27 = java.lang.Math.abs(_t25);
        double _t28 = java.lang.Math.abs(_t24);
        double _t34, _t35;
        if (_t27 > _t28) {
            _t34 = 0.0;
            _t35 = -_t26;
        } else {
            _t34 = _t26;
            _t35 = 0.0;
        }
        return makeRotationLookAlong_degenerate_s6a304d84_tail(_t21, _t22, _t23, _t27, _t28, _t25, _t24, _t26, _t34, _t35, 0);
    }


    /**
     * Create the rotation represented by the quaternion {@code q}.
     * <p>
     * Valid input: {@code q} must have unit length.
     *
     * @param q the rotation quaternion
     * @return the resulting matrix
     */
    public static Double3x3 makeRotationQuat(DoubleQuat q) {
        double qX = q.x();
        double qY = q.y();
        double qZ = q.z();
        double qW = q.w();
        double _t0 = qZ * qZ;
        double _t1 = qZ * qW;
        double _t2 = qY * qW;
        return new Double3x3(Math.fma(-2.0, Math.fma(qY, qY, _t0), 1.0), 2.0 * Math.fma(qX, qY, -_t1), 2.0 * Math.fma(qX, qZ, _t2), 2.0 * Math.fma(qX, qY, _t1), Math.fma(-2.0, Math.fma(qX, qX, _t0), 1.0), 2.0 * Math.fma(qY, qZ, -(qX * qW)), 2.0 * Math.fma(qX, qZ, -_t2), 2.0 * Math.fma(qX, qW, qY * qZ), Math.fma(-2.0, Math.fma(qX, qX, qY * qY), 1.0), 0);
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
    public static Double3x3 makeRotationQuat(double qX, double qY, double qZ, double qW) {
        double _t0 = qZ * qZ;
        double _t1 = qZ * qW;
        double _t2 = qY * qW;
        return new Double3x3(Math.fma(-2.0, Math.fma(qY, qY, _t0), 1.0), 2.0 * Math.fma(qX, qY, -_t1), 2.0 * Math.fma(qX, qZ, _t2), 2.0 * Math.fma(qX, qY, _t1), Math.fma(-2.0, Math.fma(qX, qX, _t0), 1.0), 2.0 * Math.fma(qY, qZ, -(qX * qW)), 2.0 * Math.fma(qX, qZ, -_t2), 2.0 * Math.fma(qX, qW, qY * qZ), Math.fma(-2.0, Math.fma(qX, qX, qY * qY), 1.0), 0);
    }


    /**
     * Create a rotation of {@code angle} radians about the X axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting matrix
     */
    public static Double3x3 makeRotationX(double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        return new Double3x3(1.0, 0.0, 0.0, 0.0, _t1, -_t0, 0.0, _t0, _t1, 0);
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
    public static Double3x3 makeRotationXYZ(double angleX, double angleY, double angleZ) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t0;
        double _t7 = _t0 * _t5;
        return new Double3x3(_t3 * _t4, -(_t1 * _t3), _t0, Math.fma(_t6, _t4, _t1 * _t5), Math.fma(_t5, _t4, -(_t6 * _t1)), -(_t2 * _t3), Math.fma(_t2, _t1, -(_t7 * _t4)), Math.fma(_t7, _t1, _t2 * _t4), _t5 * _t3, 0);
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
    public static Double3x3 makeRotationXZY(double angleX, double angleZ, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t1 * _t5;
        return new Double3x3(_t3 * _t4, -_t1, _t0 * _t4, Math.fma(_t7, _t3, _t2 * _t0), _t5 * _t4, Math.fma(_t7, _t0, -(_t2 * _t3)), Math.fma(_t6, _t3, -(_t0 * _t5)), _t2 * _t4, Math.fma(_t6, _t0, _t5 * _t3), 0);
    }


    /**
     * Create a rotation of {@code angle} radians about the Y axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting matrix
     */
    public static Double3x3 makeRotationY(double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        return new Double3x3(_t1, 0.0, _t0, 0.0, 1.0, 0.0, -_t0, 0.0, _t1, 0);
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
    public static Double3x3 makeRotationYXZ(double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t3;
        return new Double3x3(Math.fma(_t6, _t2, _t3 * _t4), Math.fma(_t6, _t4, -(_t2 * _t3)), _t1 * _t5, _t2 * _t5, _t5 * _t4, -_t0, Math.fma(_t7, _t2, -(_t1 * _t4)), Math.fma(_t7, _t4, _t1 * _t2), _t5 * _t3, 0);
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
    public static Double3x3 makeRotationYZX(double angleY, double angleZ, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t1 * _t3;
        return new Double3x3(_t3 * _t4, Math.fma(_t2, _t0, -(_t7 * _t5)), Math.fma(_t7, _t2, _t0 * _t5), _t1, _t5 * _t4, -(_t2 * _t4), -(_t0 * _t4), Math.fma(_t6, _t5, _t2 * _t3), Math.fma(_t5, _t3, -(_t6 * _t2)), 0);
    }


    /**
     * Create a rotation of {@code angle} radians about the Z axis.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param angle the angle in radians
     * @return the resulting matrix
     */
    public static Double3x3 makeRotationZ(double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        return new Double3x3(_t1, -_t0, 0.0, _t0, _t1, 0.0, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
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
    public static Double3x3 makeRotationZXY(double angleZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t2 * _t4;
        return new Double3x3(Math.fma(_t3, _t4, -(_t6 * _t0)), -(_t1 * _t5), Math.fma(_t6, _t3, _t0 * _t4), Math.fma(_t7, _t0, _t1 * _t3), _t5 * _t4, Math.fma(_t0, _t1, -(_t7 * _t3)), -(_t0 * _t5), _t2, _t5 * _t3, 0);
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
    public static Double3x3 makeRotationZYX(double angleZ, double angleY, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t4;
        return new Double3x3(_t3 * _t4, Math.fma(_t7, _t2, -(_t1 * _t5)), Math.fma(_t7, _t5, _t2 * _t1), _t1 * _t3, Math.fma(_t6, _t2, _t5 * _t4), Math.fma(_t6, _t5, -(_t2 * _t4)), -_t0, _t2 * _t3, _t5 * _t3, 0);
    }


    /**
     * Create a scaling transformation that scales by {@code v}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the scale factors
     * @return the resulting matrix
     */
    public static Double3x3 makeScaling(Double2 v) {
        double vX = v.x();
        double vY = v.y();
        return new Double3x3(vX, 0.0, 0.0, 0.0, vY, 0.0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
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
    public static Double3x3 makeScaling(double vX, double vY) {
        return new Double3x3(vX, 0.0, 0.0, 0.0, vY, 0.0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
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
    public static Double3x3 makeScaling(double s) {
        return new Double3x3(s, 0.0, 0.0, 0.0, s, 0.0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Create a translation transformation that translates by {@code v}.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param v the translation offsets
     * @return the resulting matrix
     */
    public static Double3x3 makeTranslation(Double2 v) {
        double vX = v.x();
        double vY = v.y();
        return new Double3x3(1.0, 0.0, vX, 0.0, 1.0, vY, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION);
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
    public static Double3x3 makeTranslation(double vX, double vY) {
        return new Double3x3(1.0, 0.0, vX, 0.0, 1.0, vY, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION);
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
    public static Double3x3 makeView(double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        return new Double3x3(_t0_inv + _t0_inv, 0.0, -((left + right) * _t0_inv), 0.0, _t1_inv + _t1_inv, -((bottom + top) * _t1_inv), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double3x3 preRotate_translation(double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        return new Double3x3(_t1, -_t0, Math.fma(this.m02, _t1, -(this.m12 * _t0)), _t0, _t1, Math.fma(this.m02, _t0, this.m12 * _t1), 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double3x3 preRotate_orthogonal(double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        return new Double3x3(Math.fma(this.m00, _t1, -(this.m10 * _t0)), Math.fma(this.m01, _t1, -(this.m11 * _t0)), Math.fma(this.m02, _t1, -(this.m12 * _t0)), Math.fma(this.m00, _t0, this.m10 * _t1), Math.fma(this.m01, _t0, this.m11 * _t1), Math.fma(this.m02, _t0, this.m12 * _t1), 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double3x3 preRotate_affine(double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        return new Double3x3(Math.fma(this.m00, _t1, -(this.m10 * _t0)), Math.fma(this.m01, _t1, -(this.m11 * _t0)), Math.fma(this.m02, _t1, -(this.m12 * _t0)), Math.fma(this.m00, _t0, this.m10 * _t1), Math.fma(this.m01, _t0, this.m11 * _t1), Math.fma(this.m02, _t0, this.m12 * _t1), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preRotate}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotate} dispatcher.
     */
    private Double3x3 preRotate_general(double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        return new Double3x3(Math.fma(this.m00, _t1, -(this.m10 * _t0)), Math.fma(this.m01, _t1, -(this.m11 * _t0)), Math.fma(this.m02, _t1, -(this.m12 * _t0)), Math.fma(this.m00, _t0, this.m10 * _t1), Math.fma(this.m01, _t0, this.m11 * _t1), Math.fma(this.m02, _t0, this.m12 * _t1), this.m20, this.m21, this.m22, 0);
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
    public Double3x3 preRotate(double angle) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            return new Double3x3(_t1, -_t0, 0.0, _t0, _t1, 0.0, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
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
    public Double3x3 preRotateAround(double angle, Double2 pivot) {
        double pivotX = pivot.x();
        double pivotY = pivot.y();
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
    private Double3x3 preRotateAround_identity(double angle, double pivotX, double pivotY) {
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        return new Double3x3(_t2, -_t0, Math.fma(pivotX, _t5, pivotY * _t0), _t0, _t2, Math.fma(pivotY, _t5, -(pivotX * _t0)), 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Double3x3 preRotateAround_translation(double angle, double pivotX, double pivotY) {
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        return new Double3x3(_t2, -_t0, Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(this.m02, _t2, -(this.m12 * _t0)), _t0, _t2, Math.fma(this.m02, _t0, this.m12 * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0)), 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Double3x3 preRotateAround_orthogonal(double angle, double pivotX, double pivotY) {
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        return new Double3x3(Math.fma(this.m00, _t2, -(this.m10 * _t0)), Math.fma(this.m01, _t2, -(this.m11 * _t0)), Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(this.m02, _t2, -(this.m12 * _t0)), Math.fma(this.m00, _t0, this.m10 * _t2), Math.fma(this.m01, _t0, this.m11 * _t2), Math.fma(this.m02, _t0, this.m12 * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0)), 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Double3x3 preRotateAround_affine(double angle, double pivotX, double pivotY) {
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        return new Double3x3(Math.fma(this.m00, _t2, -(this.m10 * _t0)), Math.fma(this.m01, _t2, -(this.m11 * _t0)), Math.fma(pivotX, _t5, pivotY * _t0) + Math.fma(this.m02, _t2, -(this.m12 * _t0)), Math.fma(this.m00, _t0, this.m10 * _t2), Math.fma(this.m01, _t0, this.m11 * _t2), Math.fma(this.m02, _t0, this.m12 * _t2) + Math.fma(pivotY, _t5, -(pivotX * _t0)), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preRotateAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preRotateAround} dispatcher.
     */
    private Double3x3 preRotateAround_general(double angle, double pivotX, double pivotY) {
        double _t0 = Math.sin(angle);
        double _t2 = Math.sin(0.5 * angle);
        double _t3 = Math.cosFromSin(_t0, angle);
        double _t8 = (_t2 + _t2) * _t2;
        double _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        return new Double3x3(Math.fma(this.m20, _t9, Math.fma(this.m00, _t3, -(this.m10 * _t0))), Math.fma(this.m21, _t9, Math.fma(this.m01, _t3, -(this.m11 * _t0))), Math.fma(this.m22, _t9, Math.fma(this.m02, _t3, -(this.m12 * _t0))), Math.fma(this.m20, _t10, Math.fma(this.m00, _t0, this.m10 * _t3)), Math.fma(this.m21, _t10, Math.fma(this.m01, _t0, this.m11 * _t3)), Math.fma(this.m22, _t10, Math.fma(this.m02, _t0, this.m12 * _t3)), this.m20, this.m21, this.m22, 0);
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
    public Double3x3 preRotateAround(double angle, double pivotX, double pivotY) {
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
    public Double3x3 preRotateAxis(double angle, Double3 axis) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
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
    private Double3x3 preRotateAxis_identity(double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisY;
        double _t3 = axisX * axisZ;
        double _t4 = axisY * axisZ;
        double _t5 = 1.0 - _t1;
        return new Double3x3(Math.fma(_t5, axisX * axisX, _t1), Math.fma(_t5, _t2, -(axisZ * _t0)), Math.fma(axisY, _t0, _t5 * _t3), Math.fma(axisZ, _t0, _t5 * _t2), Math.fma(_t5, axisY * axisY, _t1), Math.fma(_t5, _t4, -(axisX * _t0)), Math.fma(_t5, _t3, -(axisY * _t0)), Math.fma(axisX, _t0, _t5 * _t4), Math.fma(_t5, axisZ * axisZ, _t1), 0);
    }


    /**
     * Private body of {@code preRotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotateAxis} dispatcher.
     */
    private Double3x3 preRotateAxis_translation(double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t3 = axisX * axisY;
        double _t5 = axisX * axisZ;
        double _t7 = axisY * axisZ;
        double _t9 = 1.0 - _t1;
        double _t14 = Math.fma(_t9, axisX * axisX, _t1);
        double _t15 = Math.fma(_t9, axisY * axisY, _t1);
        double _t16 = Math.fma(axisZ, _t0, _t9 * _t3);
        double _t17 = Math.fma(axisX, _t0, _t9 * _t7);
        double _t18 = Math.fma(_t9, _t3, -(axisZ * _t0));
        double _t19 = Math.fma(_t9, _t5, -(axisY * _t0));
        return new Double3x3(_t14, _t18, Math.fma(axisY, _t0, _t9 * _t5) + Math.fma(this.m02, _t14, this.m12 * _t18), _t16, _t15, Math.fma(this.m02, _t16, this.m12 * _t15) + Math.fma(_t9, _t7, -(axisX * _t0)), _t19, _t17, Math.fma(this.m02, _t19, Math.fma(this.m12, _t17, Math.fma(_t9, axisZ * axisZ, _t1))), 0);
    }

    /** Private per-column body of {@code preRotateAxis_orthogonal}; reached only through it. */
    private double[] preRotateAxis_orthogonal_s56e2ebbb_c0(double _t14, double _t18, double _t16, double _t15, double _t19, double _t17) {
        return new double[] {Math.fma(this.m00, _t14, this.m10 * _t18), Math.fma(this.m00, _t16, this.m10 * _t15), Math.fma(this.m00, _t19, this.m10 * _t17)};
    }

    /** Private per-column body of {@code preRotateAxis_orthogonal}; reached only through it. */
    private double[] preRotateAxis_orthogonal_s56e2ebbb_c1(double _t14, double _t18, double _t16, double _t15, double _t19, double _t17) {
        return new double[] {Math.fma(this.m01, _t14, this.m11 * _t18), Math.fma(this.m01, _t16, this.m11 * _t15), Math.fma(this.m01, _t19, this.m11 * _t17)};
    }

    /** Private per-column body of {@code preRotateAxis_orthogonal}; reached only through it. */
    private double[] preRotateAxis_orthogonal_s56e2ebbb_c2(double axisY, double _t0, double _t9, double _t5, double _t14, double _t18, double _t16, double _t15, double _t7, double axisX, double _t19, double _t17, double axisZ, double _t1) {
        return new double[] {Math.fma(axisY, _t0, _t9 * _t5) + Math.fma(this.m02, _t14, this.m12 * _t18), Math.fma(this.m02, _t16, this.m12 * _t15) + Math.fma(_t9, _t7, -(axisX * _t0)), Math.fma(this.m02, _t19, Math.fma(this.m12, _t17, Math.fma(_t9, axisZ * axisZ, _t1)))};
    }


    /**
     * Private body of {@code preRotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotateAxis} dispatcher.
     */
    private Double3x3 preRotateAxis_orthogonal(double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t3 = axisX * axisY;
        double _t5 = axisX * axisZ;
        double _t7 = axisY * axisZ;
        double _t9 = 1.0 - _t1;
        double _t14 = Math.fma(_t9, axisX * axisX, _t1);
        double _t15 = Math.fma(_t9, axisY * axisY, _t1);
        double _t16 = Math.fma(axisZ, _t0, _t9 * _t3);
        double _t17 = Math.fma(axisX, _t0, _t9 * _t7);
        double _t18 = Math.fma(_t9, _t3, -(axisZ * _t0));
        double _t19 = Math.fma(_t9, _t5, -(axisY * _t0));
        double[] _col0 = preRotateAxis_orthogonal_s56e2ebbb_c0(_t14, _t18, _t16, _t15, _t19, _t17);
        double[] _col1 = preRotateAxis_orthogonal_s56e2ebbb_c1(_t14, _t18, _t16, _t15, _t19, _t17);
        double[] _col2 = preRotateAxis_orthogonal_s56e2ebbb_c2(axisY, _t0, _t9, _t5, _t14, _t18, _t16, _t15, _t7, axisX, _t19, _t17, axisZ, _t1);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }

    /** Private per-column body of {@code preRotateAxis_general}; reached only through it. */
    private double[] preRotateAxis_general_s56e2ebbb_c0(double _t21, double _t18, double _t24, double _t25, double _t22, double _t19, double _t20, double _t26, double _t23) {
        return new double[] {Math.fma(this.m20, _t21, Math.fma(this.m00, _t18, this.m10 * _t24)), Math.fma(this.m20, _t25, Math.fma(this.m00, _t22, this.m10 * _t19)), Math.fma(this.m20, _t20, Math.fma(this.m00, _t26, this.m10 * _t23))};
    }

    /** Private per-column body of {@code preRotateAxis_general}; reached only through it. */
    private double[] preRotateAxis_general_s56e2ebbb_c1(double _t21, double _t18, double _t24, double _t25, double _t22, double _t19, double _t20, double _t26, double _t23) {
        return new double[] {Math.fma(this.m21, _t21, Math.fma(this.m01, _t18, this.m11 * _t24)), Math.fma(this.m21, _t25, Math.fma(this.m01, _t22, this.m11 * _t19)), Math.fma(this.m21, _t20, Math.fma(this.m01, _t26, this.m11 * _t23))};
    }

    /** Private per-column body of {@code preRotateAxis_general}; reached only through it. */
    private double[] preRotateAxis_general_s56e2ebbb_c2(double _t21, double _t18, double _t24, double _t25, double _t22, double _t19, double _t20, double _t26, double _t23) {
        return new double[] {Math.fma(this.m22, _t21, Math.fma(this.m02, _t18, this.m12 * _t24)), Math.fma(this.m22, _t25, Math.fma(this.m02, _t22, this.m12 * _t19)), Math.fma(this.m22, _t20, Math.fma(this.m02, _t26, this.m12 * _t23))};
    }


    /**
     * Private body of {@code preRotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code preRotateAxis} dispatcher.
     */
    private Double3x3 preRotateAxis_general(double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t4 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        double _t18 = Math.fma(_t11, axisX * axisX, _t1);
        double _t19 = Math.fma(_t11, axisY * axisY, _t1);
        double _t20 = Math.fma(_t11, axisZ * axisZ, _t1);
        double _t21 = Math.fma(axisY, _t0, _t11 * _t2);
        double _t22 = Math.fma(axisZ, _t0, _t11 * _t4);
        double _t23 = Math.fma(axisX, _t0, _t11 * _t6);
        double _t24 = Math.fma(_t11, _t4, -(axisZ * _t0));
        double _t25 = Math.fma(_t11, _t6, -(axisX * _t0));
        double _t26 = Math.fma(_t11, _t2, -(axisY * _t0));
        double[] _col0 = preRotateAxis_general_s56e2ebbb_c0(_t21, _t18, _t24, _t25, _t22, _t19, _t20, _t26, _t23);
        double[] _col1 = preRotateAxis_general_s56e2ebbb_c1(_t21, _t18, _t24, _t25, _t22, _t19, _t20, _t26, _t23);
        double[] _col2 = preRotateAxis_general_s56e2ebbb_c2(_t21, _t18, _t24, _t25, _t22, _t19, _t20, _t26, _t23);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
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
    public Double3x3 preRotateAxis(double angle, double axisX, double axisY, double axisZ) {
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
    public Double3x3 preRotateX(double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        return new Double3x3(this.m00, this.m01, this.m02, Math.fma(this.m10, _t1, -(this.m20 * _t0)), Math.fma(this.m11, _t1, -(this.m21 * _t0)), Math.fma(this.m12, _t1, -(this.m22 * _t0)), Math.fma(this.m10, _t0, this.m20 * _t1), Math.fma(this.m11, _t0, this.m21 * _t1), Math.fma(this.m12, _t0, this.m22 * _t1), 0);
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
    public Double3x3 preRotateY(double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        return new Double3x3(Math.fma(this.m00, _t1, this.m20 * _t0), Math.fma(this.m01, _t1, this.m21 * _t0), Math.fma(this.m02, _t1, this.m22 * _t0), this.m10, this.m11, this.m12, Math.fma(this.m20, _t1, -(this.m00 * _t0)), Math.fma(this.m21, _t1, -(this.m01 * _t0)), Math.fma(this.m22, _t1, -(this.m02 * _t0)), 0);
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
    public Double3x3 preRotateZ(double angle) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            return new Double3x3(_t1, -_t0, 0.0, _t0, _t1, 0.0, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
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
    public Double3x3 preScale(Double2 v) {
        double vX = v.x();
        double vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(vX, 0.0, 0.0, 0.0, vY, 0.0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(vX, 0.0, this.m02 * vX, 0.0, vY, this.m12 * vY, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScale_orthogonal(vX, vY);
        return preScale_general(vX, vY);
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double3x3 preScale_orthogonal(double vX, double vY) {
        return new Double3x3(this.m00 * vX, this.m01 * vX, this.m02 * vX, this.m10 * vY, this.m11 * vY, this.m12 * vY, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double3x3 preScale_general(double vX, double vY) {
        return new Double3x3(this.m00 * vX, this.m01 * vX, this.m02 * vX, this.m10 * vY, this.m11 * vY, this.m12 * vY, this.m20, this.m21, this.m22, 0);
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
    public Double3x3 preScale(double vX, double vY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(vX, 0.0, 0.0, 0.0, vY, 0.0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(vX, 0.0, this.m02 * vX, 0.0, vY, this.m12 * vY, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScale_orthogonal(vX, vY);
        return preScale_general(vX, vY);
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double3x3 preScale_orthogonal(double s) {
        return new Double3x3(s * this.m00, s * this.m01, s * this.m02, s * this.m10, s * this.m11, s * this.m12, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preScale}, specialized by runtime matrix properties; reached only
     * through the public {@code preScale} dispatcher.
     */
    private Double3x3 preScale_general(double s) {
        return new Double3x3(s * this.m00, s * this.m01, s * this.m02, s * this.m10, s * this.m11, s * this.m12, this.m20, this.m21, this.m22, 0);
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
    public Double3x3 preScale(double s) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(s, 0.0, 0.0, 0.0, s, 0.0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(s, 0.0, s * this.m02, 0.0, s, s * this.m12, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
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
    public Double3x3 preScaleAround(double s, Double2 pivot) {
        double pivotX = pivot.x();
        double pivotY = pivot.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double _t0 = 1.0 - s;
            return new Double3x3(s, 0.0, pivotX * _t0, 0.0, s, pivotY * _t0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation(s, pivotX, pivotY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScaleAround_orthogonal(s, pivotX, pivotY);
        return preScaleAround_general(s, pivotX, pivotY);
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double3x3 preScaleAround_translation(double s, double pivotX, double pivotY) {
        double _t0 = 1.0 - s;
        return new Double3x3(s, 0.0, Math.fma(s, this.m02, pivotX * _t0), 0.0, s, Math.fma(s, this.m12, pivotY * _t0), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double3x3 preScaleAround_orthogonal(double s, double pivotX, double pivotY) {
        double _t0 = 1.0 - s;
        return new Double3x3(s * this.m00, s * this.m01, Math.fma(s, this.m02, pivotX * _t0), s * this.m10, s * this.m11, Math.fma(s, this.m12, pivotY * _t0), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double3x3 preScaleAround_general(double s, double pivotX, double pivotY) {
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        return new Double3x3(Math.fma(s, this.m00, this.m20 * _t1), Math.fma(s, this.m01, this.m21 * _t1), Math.fma(s, this.m02, this.m22 * _t1), Math.fma(s, this.m10, this.m20 * _t2), Math.fma(s, this.m11, this.m21 * _t2), Math.fma(s, this.m12, this.m22 * _t2), this.m20, this.m21, this.m22, 0);
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
    public Double3x3 preScaleAround(double s, double pivotX, double pivotY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double _t0 = 1.0 - s;
            return new Double3x3(s, 0.0, pivotX * _t0, 0.0, s, pivotY * _t0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
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
    public Double3x3 preScaleAround(Double2 s, Double2 pivot) {
        double sX = s.x();
        double sY = s.y();
        double pivotX = pivot.x();
        double pivotY = pivot.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(sX, 0.0, pivotX * (1.0 - sX), 0.0, sY, pivotY * (1.0 - sY), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return preScaleAround_translation(sX, sY, pivotX, pivotY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preScaleAround_orthogonal(sX, sY, pivotX, pivotY);
        return preScaleAround_general(sX, sY, pivotX, pivotY);
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double3x3 preScaleAround_translation(double sX, double sY, double pivotX, double pivotY) {
        return new Double3x3(sX, 0.0, Math.fma(pivotX, 1.0 - sX, sX * this.m02), 0.0, sY, Math.fma(pivotY, 1.0 - sY, sY * this.m12), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double3x3 preScaleAround_orthogonal(double sX, double sY, double pivotX, double pivotY) {
        return new Double3x3(sX * this.m00, sX * this.m01, Math.fma(pivotX, 1.0 - sX, sX * this.m02), sY * this.m10, sY * this.m11, Math.fma(pivotY, 1.0 - sY, sY * this.m12), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preScaleAround}, specialized by runtime matrix properties; reached
     * only through the public {@code preScaleAround} dispatcher.
     */
    private Double3x3 preScaleAround_general(double sX, double sY, double pivotX, double pivotY) {
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        return new Double3x3(Math.fma(sX, this.m00, this.m20 * _t2), Math.fma(sX, this.m01, this.m21 * _t2), Math.fma(sX, this.m02, this.m22 * _t2), Math.fma(sY, this.m10, this.m20 * _t3), Math.fma(sY, this.m11, this.m21 * _t3), Math.fma(sY, this.m12, this.m22 * _t3), this.m20, this.m21, this.m22, 0);
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
    public Double3x3 preScaleAround(double sX, double sY, double pivotX, double pivotY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(sX, 0.0, pivotX * (1.0 - sX), 0.0, sY, pivotY * (1.0 - sY), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
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
    public Double3x3 preTranslate(Double2 v) {
        double vX = v.x();
        double vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(1.0, 0.0, vX, 0.0, 1.0, vY, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(1.0, 0.0, this.m02 + vX, 0.0, 1.0, this.m12 + vY, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preTranslate_orthogonal(vX, vY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preTranslate_affine(vX, vY);
        return preTranslate_general(vX, vY);
    }


    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties; reached only
     * through the public {@code preTranslate} dispatcher.
     */
    private Double3x3 preTranslate_orthogonal(double vX, double vY) {
        return new Double3x3(this.m00, this.m01, this.m02 + vX, this.m10, this.m11, this.m12 + vY, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties; reached only
     * through the public {@code preTranslate} dispatcher.
     */
    private Double3x3 preTranslate_affine(double vX, double vY) {
        return new Double3x3(this.m00, this.m01, this.m02 + vX, this.m10, this.m11, this.m12 + vY, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code preTranslate}, specialized by runtime matrix properties; reached only
     * through the public {@code preTranslate} dispatcher.
     */
    private Double3x3 preTranslate_general(double vX, double vY) {
        return new Double3x3(Math.fma(this.m20, vX, this.m00), Math.fma(this.m21, vX, this.m01), Math.fma(this.m22, vX, this.m02), Math.fma(this.m20, vY, this.m10), Math.fma(this.m21, vY, this.m11), Math.fma(this.m22, vY, this.m12), this.m20, this.m21, this.m22, 0);
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
    public Double3x3 preTranslate(double vX, double vY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(1.0, 0.0, vX, 0.0, 1.0, vY, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(1.0, 0.0, this.m02 + vX, 0.0, 1.0, this.m12 + vY, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return preTranslate_orthogonal(vX, vY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return preTranslate_affine(vX, vY);
        return preTranslate_general(vX, vY);
    }


    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double3x3 rotate_translation(double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        return new Double3x3(_t1, -_t0, this.m02, _t0, _t1, this.m12, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double3x3 rotate_orthogonal(double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        return new Double3x3(Math.fma(this.m00, _t1, this.m01 * _t0), Math.fma(this.m01, _t1, -(this.m00 * _t0)), this.m02, Math.fma(this.m10, _t1, this.m11 * _t0), Math.fma(this.m11, _t1, -(this.m10 * _t0)), this.m12, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double3x3 rotate_affine(double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        return new Double3x3(Math.fma(this.m00, _t1, this.m01 * _t0), Math.fma(this.m01, _t1, -(this.m00 * _t0)), this.m02, Math.fma(this.m10, _t1, this.m11 * _t0), Math.fma(this.m11, _t1, -(this.m10 * _t0)), this.m12, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code rotate}, specialized by runtime matrix properties; reached only
     * through the public {@code rotate} dispatcher.
     */
    private Double3x3 rotate_general(double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        return new Double3x3(Math.fma(this.m00, _t1, this.m01 * _t0), Math.fma(this.m01, _t1, -(this.m00 * _t0)), this.m02, Math.fma(this.m10, _t1, this.m11 * _t0), Math.fma(this.m11, _t1, -(this.m10 * _t0)), this.m12, Math.fma(this.m20, _t1, this.m21 * _t0), Math.fma(this.m21, _t1, -(this.m20 * _t0)), this.m22, 0);
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
    public Double3x3 rotate(double angle) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            return new Double3x3(_t1, -_t0, 0.0, _t0, _t1, 0.0, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
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
    public Double3x3 rotateAround(double angle, Double2 pivot) {
        double pivotX = pivot.x();
        double pivotY = pivot.y();
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
    private Double3x3 rotateAround_translation(double angle, double pivotX, double pivotY) {
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t5 = (_t3 + _t3) * _t3;
        return new Double3x3(_t2, -_t0, Math.fma(pivotX, _t5, Math.fma(pivotY, _t0, this.m02)), _t0, _t2, Math.fma(pivotY, _t5, Math.fma(-pivotX, _t0, this.m12)), 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Double3x3 rotateAround_orthogonal(double angle, double pivotX, double pivotY) {
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        return new Double3x3(Math.fma(this.m00, _t2, this.m01 * _t0), Math.fma(this.m01, _t2, -(this.m00 * _t0)), Math.fma(this.m00, _t9, Math.fma(this.m01, _t10, this.m02)), Math.fma(this.m10, _t2, this.m11 * _t0), Math.fma(this.m11, _t2, -(this.m10 * _t0)), Math.fma(this.m10, _t9, Math.fma(this.m11, _t10, this.m12)), 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Double3x3 rotateAround_affine(double angle, double pivotX, double pivotY) {
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        return new Double3x3(Math.fma(this.m00, _t2, this.m01 * _t0), Math.fma(this.m01, _t2, -(this.m00 * _t0)), Math.fma(this.m00, _t9, Math.fma(this.m01, _t10, this.m02)), Math.fma(this.m10, _t2, this.m11 * _t0), Math.fma(this.m11, _t2, -(this.m10 * _t0)), Math.fma(this.m10, _t9, Math.fma(this.m11, _t10, this.m12)), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code rotateAround}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAround} dispatcher.
     */
    private Double3x3 rotateAround_general(double angle, double pivotX, double pivotY) {
        double _t0 = Math.sin(angle);
        double _t2 = Math.cosFromSin(_t0, angle);
        double _t3 = Math.sin(0.5 * angle);
        double _t8 = (_t3 + _t3) * _t3;
        double _t9 = Math.fma(pivotX, _t8, pivotY * _t0);
        double _t10 = Math.fma(pivotY, _t8, -(pivotX * _t0));
        return new Double3x3(Math.fma(this.m00, _t2, this.m01 * _t0), Math.fma(this.m01, _t2, -(this.m00 * _t0)), Math.fma(this.m00, _t9, Math.fma(this.m01, _t10, this.m02)), Math.fma(this.m10, _t2, this.m11 * _t0), Math.fma(this.m11, _t2, -(this.m10 * _t0)), Math.fma(this.m10, _t9, Math.fma(this.m11, _t10, this.m12)), Math.fma(this.m20, _t2, this.m21 * _t0), Math.fma(this.m21, _t2, -(this.m20 * _t0)), Math.fma(this.m20, _t9, Math.fma(this.m21, _t10, this.m22)), 0);
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
    public Double3x3 rotateAround(double angle, double pivotX, double pivotY) {
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
    public Double3x3 rotateAxis(double angle, Double3 axis) {
        double axisX = axis.x();
        double axisY = axis.y();
        double axisZ = axis.z();
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
    private Double3x3 rotateAxis_translation(double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t4 = axisY * axisZ;
        double _t5 = axisX * axisY;
        double _t7 = 1.0 - _t1;
        double _t10 = Math.fma(_t7, axisZ * axisZ, _t1);
        double _t11 = Math.fma(axisX, _t0, _t7 * _t4);
        double _t12 = Math.fma(_t7, _t2, -(axisY * _t0));
        return new Double3x3(Math.fma(_t7, axisX * axisX, Math.fma(this.m02, _t12, _t1)), Math.fma(this.m02, _t11, Math.fma(_t7, _t5, -(axisZ * _t0))), Math.fma(this.m02, _t10, Math.fma(axisY, _t0, _t7 * _t2)), Math.fma(this.m12, _t12, Math.fma(axisZ, _t0, _t7 * _t5)), Math.fma(_t7, axisY * axisY, Math.fma(this.m12, _t11, _t1)), Math.fma(this.m12, _t10, Math.fma(_t7, _t4, -(axisX * _t0))), _t12, _t11, _t10, 0);
    }


    /**
     * Private body of {@code rotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAxis} dispatcher.
     */
    private Double3x3 rotateAxis_orthogonal(double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t5 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        double[] _col0 = lookAlong_orthogonal_s6a304d84_tail_s18d80cb4_c0(Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(_t11, axisX * axisX, _t1), Math.fma(axisZ, _t0, _t11 * _t5));
        double[] _col1 = lookAlong_orthogonal_s6a304d84_tail_s18d80cb4_c0(Math.fma(axisX, _t0, _t11 * _t6), Math.fma(_t11, _t5, -(axisZ * _t0)), Math.fma(_t11, axisY * axisY, _t1));
        double[] _col2 = lookAlong_orthogonal_s6a304d84_tail_s18d80cb4_c0(Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(_t11, _t6, -(axisX * _t0)));
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Private body of {@code rotateAxis}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateAxis} dispatcher.
     */
    private Double3x3 rotateAxis_general(double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t5 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        double[] _col0 = lookAlong_general_s6a304d84_tail_s18d80cb4_c0(Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(_t11, axisX * axisX, _t1), Math.fma(axisZ, _t0, _t11 * _t5));
        double[] _col1 = lookAlong_general_s6a304d84_tail_s18d80cb4_c0(Math.fma(axisX, _t0, _t11 * _t6), Math.fma(_t11, _t5, -(axisZ * _t0)), Math.fma(_t11, axisY * axisY, _t1));
        double[] _col2 = lookAlong_general_s6a304d84_tail_s18d80cb4_c0(Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(_t11, _t6, -(axisX * _t0)));
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
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
    public Double3x3 rotateAxis(double angle, double axisX, double axisY, double axisZ) {
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
    public Double3x3 rotateX(double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        return new Double3x3(this.m00, Math.fma(this.m01, _t1, this.m02 * _t0), Math.fma(this.m02, _t1, -(this.m01 * _t0)), this.m10, Math.fma(this.m11, _t1, this.m12 * _t0), Math.fma(this.m12, _t1, -(this.m11 * _t0)), this.m20, Math.fma(this.m21, _t1, this.m22 * _t0), Math.fma(this.m22, _t1, -(this.m21 * _t0)), 0);
    }


    /**
     * Private body of {@code rotateX180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX180} dispatcher.
     */
    private Double3x3 rotateX180_orthogonal() {
        return new Double3x3(this.m11, this.m10, -this.m02, this.m10, -this.m11, -this.m12, 0.0, 0.0, -1.0, 0);
    }


    /**
     * Private body of {@code rotateX180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX180} dispatcher.
     */
    private Double3x3 rotateX180_affine() {
        return new Double3x3(this.m00, -this.m01, -this.m02, this.m10, -this.m11, -this.m12, 0.0, 0.0, -1.0, 0);
    }


    /**
     * Private body of {@code rotateX180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX180} dispatcher.
     */
    private Double3x3 rotateX180_general() {
        return new Double3x3(this.m00, -this.m01, -this.m02, this.m10, -this.m11, -this.m12, this.m20, -this.m21, -this.m22, 0);
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
    public Double3x3 rotateX180() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(1.0, 0.0, 0.0, 0.0, -1.0, 0.0, 0.0, 0.0, -1.0, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(1.0, 0.0, -this.m02, 0.0, -1.0, -this.m12, 0.0, 0.0, -1.0, 0);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateX180_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateX180_affine();
        return rotateX180_general();
    }


    /**
     * Private body of {@code rotateX270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX270} dispatcher.
     */
    private Double3x3 rotateX270_orthogonal() {
        return new Double3x3(this.m00, -this.m02, this.m01, this.m10, -this.m12, this.m11, 0.0, -1.0, 0.0, 0);
    }


    /**
     * Private body of {@code rotateX270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX270} dispatcher.
     */
    private Double3x3 rotateX270_general() {
        return new Double3x3(this.m00, -this.m02, this.m01, this.m10, -this.m12, this.m11, this.m20, -this.m22, this.m21, 0);
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
    public Double3x3 rotateX270() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, -1.0, 0.0, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(1.0, -this.m02, 0.0, 0.0, -this.m12, 1.0, 0.0, -1.0, 0.0, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateX270_orthogonal();
        return rotateX270_general();
    }


    /**
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Double3x3 rotateX90_orthogonal() {
        return new Double3x3(this.m11, this.m02, this.m10, this.m10, this.m12, -this.m11, 0.0, 1.0, 0.0, 0);
    }


    /**
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Double3x3 rotateX90_affine() {
        return new Double3x3(this.m00, this.m02, -this.m01, this.m10, this.m12, -this.m11, 0.0, 1.0, 0.0, 0);
    }


    /**
     * Private body of {@code rotateX90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateX90} dispatcher.
     */
    private Double3x3 rotateX90_general() {
        return new Double3x3(this.m00, this.m02, -this.m01, this.m10, this.m12, -this.m11, this.m20, this.m22, -this.m21, 0);
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
    public Double3x3 rotateX90() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(1.0, 0.0, 0.0, 0.0, 0.0, -1.0, 0.0, 1.0, 0.0, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(1.0, this.m02, 0.0, 0.0, this.m12, -1.0, 0.0, 1.0, 0.0, 0);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateX90_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateX90_affine();
        return rotateX90_general();
    }


    /**
     * Private body of {@code rotateXYZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXYZ} dispatcher.
     */
    private Double3x3 rotateXYZ_identity(double angleX, double angleY, double angleZ) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t0;
        double _t7 = _t0 * _t5;
        return new Double3x3(_t3 * _t4, -(_t1 * _t3), _t0, Math.fma(_t6, _t4, _t1 * _t5), Math.fma(_t5, _t4, -(_t6 * _t1)), -(_t2 * _t3), Math.fma(_t2, _t1, -(_t7 * _t4)), Math.fma(_t7, _t1, _t2 * _t4), _t5 * _t3, 0);
    }


    /**
     * Private body of {@code rotateXYZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXYZ} dispatcher.
     */
    private Double3x3 rotateXYZ_translation(double angleX, double angleY, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _t3 = Math.cosFromSin(_t0, angleX);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleY);
        double _t6 = _t0 * _t2;
        double _t7 = _t2 * _t3;
        double _t9 = _t3 * _t5;
        double _t12 = Math.fma(_t7, _t1, _t0 * _t4);
        double _t13 = Math.fma(_t0, _t1, -(_t7 * _t4));
        return new Double3x3(Math.fma(this.m02, _t13, _t5 * _t4), Math.fma(this.m02, _t12, -(_t1 * _t5)), Math.fma(this.m02, _t9, _t2), Math.fma(this.m12, _t13, Math.fma(_t6, _t4, _t1 * _t3)), Math.fma(this.m12, _t12, Math.fma(_t3, _t4, -(_t6 * _t1))), Math.fma(this.m12, _t9, -(_t0 * _t5)), _t13, _t12, _t9, 0);
    }

    /**
     * Private per-column body of {@code rotateXYZ_orthogonal}. Shared by the identical private
     * paths of {@code rotateXYZ}, {@code rotateXZY} and {@code rotateZXY}; reached only through
     * them.
     */
    private double[] rotateXYZ_orthogonal_s361a4ff5_c1(double _t19, double _t21, double _t10) {
        return new double[] {Math.fma(this.m02, _t19, Math.fma(this.m01, _t21, -(this.m00 * _t10))), Math.fma(this.m12, _t19, Math.fma(this.m11, _t21, -(this.m10 * _t10))), _t19};
    }

    /**
     * Private per-column body of {@code rotateXYZ_orthogonal}. Shared by the identical private
     * paths of {@code rotateXYZ}, {@code rotateYXZ} and {@code rotateYZX}; reached only through
     * them.
     */
    private double[] rotateXYZ_orthogonal_s361a4ff5_c2(double _t15, double _t2, double _t11) {
        return new double[] {Math.fma(this.m02, _t15, Math.fma(this.m00, _t2, -(this.m01 * _t11))), Math.fma(this.m12, _t15, Math.fma(this.m10, _t2, -(this.m11 * _t11))), _t15};
    }


    /**
     * Private body of {@code rotateXYZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXYZ} dispatcher.
     */
    private Double3x3 rotateXYZ_orthogonal(double angleX, double angleY, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _t3 = Math.cosFromSin(_t0, angleX);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleY);
        double _t6 = _t0 * _t2;
        double _t7 = _t2 * _t3;
        double[] _col0 = lookAlong_orthogonal_s6a304d84_tail_s18d80cb4_c0(Math.fma(_t0, _t1, -(_t7 * _t4)), _t5 * _t4, Math.fma(_t6, _t4, _t1 * _t3));
        double[] _col1 = rotateXYZ_orthogonal_s361a4ff5_c1(Math.fma(_t7, _t1, _t0 * _t4), Math.fma(_t3, _t4, -(_t6 * _t1)), _t1 * _t5);
        double[] _col2 = rotateXYZ_orthogonal_s361a4ff5_c2(_t3 * _t5, _t2, _t0 * _t5);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }

    /**
     * Private per-column body of {@code rotateXYZ_general}. Shared by the identical private paths
     * of {@code rotateXYZ}, {@code rotateXZY} and {@code rotateZXY}; reached only through them.
     */
    private double[] rotateXYZ_general_s361a4ff5_c1(double _t19, double _t21, double _t10) {
        return new double[] {Math.fma(this.m02, _t19, Math.fma(this.m01, _t21, -(this.m00 * _t10))), Math.fma(this.m12, _t19, Math.fma(this.m11, _t21, -(this.m10 * _t10))), Math.fma(this.m22, _t19, Math.fma(this.m21, _t21, -(this.m20 * _t10)))};
    }

    /**
     * Private per-column body of {@code rotateXYZ_general}. Shared by the identical private paths
     * of {@code rotateXYZ}, {@code rotateYXZ} and {@code rotateYZX}; reached only through them.
     */
    private double[] rotateXYZ_general_s361a4ff5_c2(double _t15, double _t2, double _t11) {
        return new double[] {Math.fma(this.m02, _t15, Math.fma(this.m00, _t2, -(this.m01 * _t11))), Math.fma(this.m12, _t15, Math.fma(this.m10, _t2, -(this.m11 * _t11))), Math.fma(this.m22, _t15, Math.fma(this.m20, _t2, -(this.m21 * _t11)))};
    }


    /**
     * Private body of {@code rotateXYZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXYZ} dispatcher.
     */
    private Double3x3 rotateXYZ_general(double angleX, double angleY, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _t3 = Math.cosFromSin(_t0, angleX);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleY);
        double _t6 = _t0 * _t2;
        double _t7 = _t2 * _t3;
        double[] _col0 = lookAlong_general_s6a304d84_tail_s18d80cb4_c0(Math.fma(_t0, _t1, -(_t7 * _t4)), _t5 * _t4, Math.fma(_t6, _t4, _t1 * _t3));
        double[] _col1 = rotateXYZ_general_s361a4ff5_c1(Math.fma(_t7, _t1, _t0 * _t4), Math.fma(_t3, _t4, -(_t6 * _t1)), _t1 * _t5);
        double[] _col2 = rotateXYZ_general_s361a4ff5_c2(_t3 * _t5, _t2, _t0 * _t5);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
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
    public Double3x3 rotateXYZ(double angleX, double angleY, double angleZ) {
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
    private Double3x3 rotateXZY_identity(double angleX, double angleZ, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t1 * _t5;
        return new Double3x3(_t3 * _t4, -_t1, _t0 * _t4, Math.fma(_t7, _t3, _t2 * _t0), _t5 * _t4, Math.fma(_t7, _t0, -(_t2 * _t3)), Math.fma(_t6, _t3, -(_t0 * _t5)), _t2 * _t4, Math.fma(_t6, _t0, _t5 * _t3), 0);
    }


    /**
     * Private body of {@code rotateXZY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXZY} dispatcher.
     */
    private Double3x3 rotateXZY_translation(double angleX, double angleZ, double angleY) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _t3 = Math.cosFromSin(_t2, angleY);
        double _t4 = Math.cosFromSin(_t0, angleX);
        double _t5 = Math.cosFromSin(_t1, angleZ);
        double _t6 = _t0 * _t1;
        double _t8 = _t0 * _t5;
        double _t9 = _t1 * _t4;
        double _t12 = Math.fma(_t6, _t2, _t4 * _t3);
        double _t13 = Math.fma(_t6, _t3, -(_t2 * _t4));
        return new Double3x3(Math.fma(this.m02, _t13, _t3 * _t5), Math.fma(this.m02, _t8, -_t1), Math.fma(this.m02, _t12, _t2 * _t5), Math.fma(this.m12, _t13, Math.fma(_t9, _t3, _t0 * _t2)), Math.fma(this.m12, _t8, _t4 * _t5), Math.fma(this.m12, _t12, Math.fma(_t9, _t2, -(_t0 * _t3))), _t13, _t8, _t12, 0);
    }


    /**
     * Private body of {@code rotateXZY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXZY} dispatcher.
     */
    private Double3x3 rotateXZY_orthogonal(double angleX, double angleZ, double angleY) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _t3 = Math.cosFromSin(_t2, angleY);
        double _t4 = Math.cosFromSin(_t0, angleX);
        double _t5 = Math.cosFromSin(_t1, angleZ);
        double _t6 = _t0 * _t1;
        double _t9 = _t1 * _t4;
        double[] _col0 = lookAlong_orthogonal_s6a304d84_tail_s18d80cb4_c0(Math.fma(_t6, _t3, -(_t2 * _t4)), _t3 * _t5, Math.fma(_t9, _t3, _t0 * _t2));
        double[] _col1 = rotateXYZ_orthogonal_s361a4ff5_c1(_t0 * _t5, _t4 * _t5, _t1);
        double[] _col2 = lookAlong_orthogonal_s6a304d84_tail_s18d80cb4_c0(Math.fma(_t6, _t2, _t4 * _t3), _t2 * _t5, Math.fma(_t9, _t2, -(_t0 * _t3)));
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Private body of {@code rotateXZY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateXZY} dispatcher.
     */
    private Double3x3 rotateXZY_general(double angleX, double angleZ, double angleY) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleY);
        double _t3 = Math.cosFromSin(_t2, angleY);
        double _t4 = Math.cosFromSin(_t0, angleX);
        double _t5 = Math.cosFromSin(_t1, angleZ);
        double _t6 = _t0 * _t1;
        double _t9 = _t1 * _t4;
        double[] _col0 = lookAlong_general_s6a304d84_tail_s18d80cb4_c0(Math.fma(_t6, _t3, -(_t2 * _t4)), _t3 * _t5, Math.fma(_t9, _t3, _t0 * _t2));
        double[] _col1 = rotateXYZ_general_s361a4ff5_c1(_t0 * _t5, _t4 * _t5, _t1);
        double[] _col2 = lookAlong_general_s6a304d84_tail_s18d80cb4_c0(Math.fma(_t6, _t2, _t4 * _t3), _t2 * _t5, Math.fma(_t9, _t2, -(_t0 * _t3)));
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
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
    public Double3x3 rotateXZY(double angleX, double angleZ, double angleY) {
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
    public Double3x3 rotateXn180() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(1.0, 0.0, 0.0, 0.0, -1.0, 0.0, 0.0, 0.0, -1.0, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(1.0, 0.0, -this.m02, 0.0, -1.0, -this.m12, 0.0, 0.0, -1.0, 0);
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
    public Double3x3 rotateXn270() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(1.0, 0.0, 0.0, 0.0, 0.0, -1.0, 0.0, 1.0, 0.0, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(1.0, this.m02, 0.0, 0.0, this.m12, -1.0, 0.0, 1.0, 0.0, 0);
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
    public Double3x3 rotateXn90() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, -1.0, 0.0, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(1.0, -this.m02, 0.0, 0.0, -this.m12, 1.0, 0.0, -1.0, 0.0, 0);
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
    public Double3x3 rotateY(double angle) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        return new Double3x3(Math.fma(this.m00, _t1, -(this.m02 * _t0)), this.m01, Math.fma(this.m00, _t0, this.m02 * _t1), Math.fma(this.m10, _t1, -(this.m12 * _t0)), this.m11, Math.fma(this.m10, _t0, this.m12 * _t1), Math.fma(this.m20, _t1, -(this.m22 * _t0)), this.m21, Math.fma(this.m20, _t0, this.m22 * _t1), 0);
    }


    /**
     * Private body of {@code rotateY180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY180} dispatcher.
     */
    private Double3x3 rotateY180_orthogonal() {
        return new Double3x3(-this.m00, this.m01, -this.m02, -this.m10, this.m11, -this.m12, 0.0, 0.0, -1.0, 0);
    }


    /**
     * Private body of {@code rotateY180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY180} dispatcher.
     */
    private Double3x3 rotateY180_general() {
        return new Double3x3(-this.m00, this.m01, -this.m02, -this.m10, this.m11, -this.m12, -this.m20, this.m21, -this.m22, 0);
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
    public Double3x3 rotateY180() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(-1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, -1.0, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(-1.0, 0.0, -this.m02, 0.0, 1.0, -this.m12, 0.0, 0.0, -1.0, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateY180_orthogonal();
        return rotateY180_general();
    }


    /**
     * Private body of {@code rotateY270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY270} dispatcher.
     */
    private Double3x3 rotateY270_orthogonal() {
        return new Double3x3(this.m02, this.m01, -this.m00, this.m12, this.m11, -this.m10, 1.0, 0.0, 0.0, 0);
    }


    /**
     * Private body of {@code rotateY270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY270} dispatcher.
     */
    private Double3x3 rotateY270_general() {
        return new Double3x3(this.m02, this.m01, -this.m00, this.m12, this.m11, -this.m10, this.m22, this.m21, -this.m20, 0);
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
    public Double3x3 rotateY270() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(0.0, 0.0, -1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(this.m02, 0.0, -1.0, this.m12, 1.0, 0.0, 1.0, 0.0, 0.0, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateY270_orthogonal();
        return rotateY270_general();
    }


    /**
     * Private body of {@code rotateY90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY90} dispatcher.
     */
    private Double3x3 rotateY90_orthogonal() {
        return new Double3x3(-this.m02, this.m01, this.m00, -this.m12, this.m11, this.m10, -1.0, 0.0, 0.0, 0);
    }


    /**
     * Private body of {@code rotateY90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateY90} dispatcher.
     */
    private Double3x3 rotateY90_general() {
        return new Double3x3(-this.m02, this.m01, this.m00, -this.m12, this.m11, this.m10, -this.m22, this.m21, this.m20, 0);
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
    public Double3x3 rotateY90() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(0.0, 0.0, 1.0, 0.0, 1.0, 0.0, -1.0, 0.0, 0.0, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(-this.m02, 0.0, 1.0, -this.m12, 1.0, 0.0, -1.0, 0.0, 0.0, 0);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateY90_orthogonal();
        return rotateY90_general();
    }


    /**
     * Private body of {@code rotateYXZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYXZ} dispatcher.
     */
    private Double3x3 rotateYXZ_identity(double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t3;
        return new Double3x3(Math.fma(_t6, _t2, _t3 * _t4), Math.fma(_t6, _t4, -(_t2 * _t3)), _t1 * _t5, _t2 * _t5, _t5 * _t4, -_t0, Math.fma(_t7, _t2, -(_t1 * _t4)), Math.fma(_t7, _t4, _t1 * _t2), _t5 * _t3, 0);
    }


    /**
     * Private body of {@code rotateYXZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYXZ} dispatcher.
     */
    private Double3x3 rotateYXZ_translation(double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t8 = _t0 * _t3;
        double _t11 = _t5 * _t3;
        double _t12 = Math.fma(_t8, _t4, _t1 * _t2);
        double _t13 = Math.fma(_t8, _t2, -(_t1 * _t4));
        return new Double3x3(Math.fma(this.m02, _t13, Math.fma(_t6, _t2, _t3 * _t4)), Math.fma(this.m02, _t12, Math.fma(_t6, _t4, -(_t2 * _t3))), Math.fma(this.m02, _t11, _t1 * _t5), Math.fma(this.m12, _t13, _t2 * _t5), Math.fma(this.m12, _t12, _t5 * _t4), Math.fma(this.m12, _t11, -_t0), _t13, _t12, _t11, 0);
    }


    /**
     * Private body of {@code rotateYXZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYXZ} dispatcher.
     */
    private Double3x3 rotateYXZ_orthogonal(double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t8 = _t0 * _t3;
        double[] _col0 = lookAlong_orthogonal_s6a304d84_tail_s18d80cb4_c0(Math.fma(_t8, _t2, -(_t1 * _t4)), Math.fma(_t6, _t2, _t3 * _t4), _t2 * _t5);
        double[] _col1 = lookAlong_orthogonal_s6a304d84_tail_s18d80cb4_c0(Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t6, _t4, -(_t2 * _t3)), _t5 * _t4);
        double[] _col2 = rotateXYZ_orthogonal_s361a4ff5_c2(_t5 * _t3, _t1 * _t5, _t0);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Private body of {@code rotateYXZ}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYXZ} dispatcher.
     */
    private Double3x3 rotateYXZ_general(double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t6 = _t0 * _t1;
        double _t8 = _t0 * _t3;
        double[] _col0 = lookAlong_general_s6a304d84_tail_s18d80cb4_c0(Math.fma(_t8, _t2, -(_t1 * _t4)), Math.fma(_t6, _t2, _t3 * _t4), _t2 * _t5);
        double[] _col1 = lookAlong_general_s6a304d84_tail_s18d80cb4_c0(Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t6, _t4, -(_t2 * _t3)), _t5 * _t4);
        double[] _col2 = rotateXYZ_general_s361a4ff5_c2(_t5 * _t3, _t1 * _t5, _t0);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
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
    public Double3x3 rotateYXZ(double angleY, double angleX, double angleZ) {
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
    private Double3x3 rotateYZX_identity(double angleY, double angleZ, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t1 * _t3;
        return new Double3x3(_t3 * _t4, Math.fma(_t2, _t0, -(_t7 * _t5)), Math.fma(_t7, _t2, _t0 * _t5), _t1, _t5 * _t4, -(_t2 * _t4), -(_t0 * _t4), Math.fma(_t6, _t5, _t2 * _t3), Math.fma(_t5, _t3, -(_t6 * _t2)), 0);
    }


    /**
     * Private body of {@code rotateYZX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYZX} dispatcher.
     */
    private Double3x3 rotateYZX_translation(double angleY, double angleZ, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t4;
        double _t9 = _t1 * _t3;
        double _t12 = Math.fma(_t6, _t5, _t2 * _t3);
        double _t13 = Math.fma(_t5, _t3, -(_t6 * _t2));
        return new Double3x3(Math.fma(_t3, _t4, -(this.m02 * _t7)), Math.fma(this.m02, _t12, Math.fma(_t2, _t0, -(_t9 * _t5))), Math.fma(this.m02, _t13, Math.fma(_t9, _t2, _t0 * _t5)), Math.fma(-this.m12, _t7, _t1), Math.fma(this.m12, _t12, _t5 * _t4), Math.fma(this.m12, _t13, -(_t2 * _t4)), -_t7, _t12, _t13, 0);
    }

    /**
     * Private per-column body of {@code rotateYZX_orthogonal}. Shared by the identical private
     * paths of {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only through
     * them.
     */
    private double[] rotateYZX_orthogonal_s2c94f613_c0(double _t7, double _t13, double _t1) {
        return new double[] {Math.fma(-this.m02, _t7, Math.fma(this.m00, _t13, this.m01 * _t1)), Math.fma(-this.m12, _t7, Math.fma(this.m10, _t13, this.m11 * _t1)), -_t7};
    }


    /**
     * Private body of {@code rotateYZX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYZX} dispatcher.
     */
    private Double3x3 rotateYZX_orthogonal(double angleY, double angleZ, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t1, angleZ);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t9 = _t1 * _t4;
        double[] _col0 = rotateYZX_orthogonal_s2c94f613_c0(_t0 * _t3, _t4 * _t3, _t1);
        double[] _col1 = lookAlong_orthogonal_s6a304d84_tail_s18d80cb4_c0(Math.fma(_t6, _t5, _t2 * _t4), Math.fma(_t2, _t0, -(_t9 * _t5)), _t5 * _t3);
        double[] _col2 = rotateXYZ_orthogonal_s361a4ff5_c2(Math.fma(_t5, _t4, -(_t6 * _t2)), Math.fma(_t9, _t2, _t0 * _t5), _t2 * _t3);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }

    /**
     * Private per-column body of {@code rotateYZX_general}. Shared by the identical private paths
     * of {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only through them.
     */
    private double[] rotateYZX_general_s2c94f613_c0(double _t7, double _t13, double _t1) {
        return new double[] {Math.fma(-this.m02, _t7, Math.fma(this.m00, _t13, this.m01 * _t1)), Math.fma(-this.m12, _t7, Math.fma(this.m10, _t13, this.m11 * _t1)), Math.fma(-this.m22, _t7, Math.fma(this.m20, _t13, this.m21 * _t1))};
    }


    /**
     * Private body of {@code rotateYZX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateYZX} dispatcher.
     */
    private Double3x3 rotateYZX_general(double angleY, double angleZ, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t1, angleZ);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t9 = _t1 * _t4;
        double[] _col0 = rotateYZX_general_s2c94f613_c0(_t0 * _t3, _t4 * _t3, _t1);
        double[] _col1 = lookAlong_general_s6a304d84_tail_s18d80cb4_c0(Math.fma(_t6, _t5, _t2 * _t4), Math.fma(_t2, _t0, -(_t9 * _t5)), _t5 * _t3);
        double[] _col2 = rotateXYZ_general_s361a4ff5_c2(Math.fma(_t5, _t4, -(_t6 * _t2)), Math.fma(_t9, _t2, _t0 * _t5), _t2 * _t3);
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
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
    public Double3x3 rotateYZX(double angleY, double angleZ, double angleX) {
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
    public Double3x3 rotateYn180() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(-1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, -1.0, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(-1.0, 0.0, -this.m02, 0.0, 1.0, -this.m12, 0.0, 0.0, -1.0, 0);
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
    public Double3x3 rotateYn270() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(0.0, 0.0, 1.0, 0.0, 1.0, 0.0, -1.0, 0.0, 0.0, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(-this.m02, 0.0, 1.0, -this.m12, 1.0, 0.0, -1.0, 0.0, 0.0, 0);
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
    public Double3x3 rotateYn90() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(0.0, 0.0, -1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(this.m02, 0.0, -1.0, this.m12, 1.0, 0.0, 1.0, 0.0, 0.0, 0);
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
    public Double3x3 rotateZ(double angle) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double _t0 = Math.sin(angle);
            double _t1 = Math.cosFromSin(_t0, angle);
            return new Double3x3(_t1, -_t0, 0.0, _t0, _t1, 0.0, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
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
    private Double3x3 rotateZ180_orthogonal() {
        double _t0 = -this.m11;
        return new Double3x3(_t0, this.m10, this.m02, -this.m10, _t0, this.m12, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code rotateZ180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ180} dispatcher.
     */
    private Double3x3 rotateZ180_affine() {
        return new Double3x3(-this.m00, -this.m01, this.m02, -this.m10, -this.m11, this.m12, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code rotateZ180}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ180} dispatcher.
     */
    private Double3x3 rotateZ180_general() {
        return new Double3x3(-this.m00, -this.m01, this.m02, -this.m10, -this.m11, this.m12, -this.m20, -this.m21, this.m22, 0);
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
    public Double3x3 rotateZ180() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(-1.0, 0.0, 0.0, 0.0, -1.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(-1.0, 0.0, this.m02, 0.0, -1.0, this.m12, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateZ180_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZ180_affine();
        return rotateZ180_general();
    }


    /**
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Double3x3 rotateZ270_orthogonal() {
        return new Double3x3(this.m10, this.m11, this.m02, -this.m11, this.m10, this.m12, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Double3x3 rotateZ270_affine() {
        return new Double3x3(-this.m01, this.m00, this.m02, -this.m11, this.m10, this.m12, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code rotateZ270}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ270} dispatcher.
     */
    private Double3x3 rotateZ270_general() {
        return new Double3x3(-this.m01, this.m00, this.m02, -this.m11, this.m10, this.m12, -this.m21, this.m20, this.m22, 0);
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
    public Double3x3 rotateZ270() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(0.0, 1.0, 0.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(0.0, 1.0, this.m02, -1.0, 0.0, this.m12, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateZ270_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZ270_affine();
        return rotateZ270_general();
    }


    /**
     * Private body of {@code rotateZ90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ90} dispatcher.
     */
    private Double3x3 rotateZ90_orthogonal() {
        return new Double3x3(this.m01, -this.m00, this.m02, this.m11, -this.m10, this.m12, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code rotateZ90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ90} dispatcher.
     */
    private Double3x3 rotateZ90_affine() {
        return new Double3x3(this.m01, -this.m00, this.m02, this.m11, -this.m10, this.m12, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code rotateZ90}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZ90} dispatcher.
     */
    private Double3x3 rotateZ90_general() {
        return new Double3x3(this.m01, -this.m00, this.m02, this.m11, -this.m10, this.m12, this.m21, -this.m20, this.m22, 0);
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
    public Double3x3 rotateZ90() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(0.0, -1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(0.0, -1.0, this.m02, 1.0, 0.0, this.m12, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return rotateZ90_orthogonal();
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return rotateZ90_affine();
        return rotateZ90_general();
    }


    /**
     * Private body of {@code rotateZXY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZXY} dispatcher.
     */
    private Double3x3 rotateZXY_identity(double angleZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t2 * _t1;
        double _t7 = _t2 * _t4;
        return new Double3x3(Math.fma(_t3, _t4, -(_t6 * _t0)), -(_t1 * _t5), Math.fma(_t6, _t3, _t0 * _t4), Math.fma(_t7, _t0, _t1 * _t3), _t5 * _t4, Math.fma(_t0, _t1, -(_t7 * _t3)), -(_t0 * _t5), _t2, _t5 * _t3, 0);
    }


    /**
     * Private body of {@code rotateZXY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZXY} dispatcher.
     */
    private Double3x3 rotateZXY_translation(double angleZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleX);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleX);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleZ);
        double _t6 = _t1 * _t2;
        double _t7 = _t0 * _t3;
        double _t8 = _t1 * _t5;
        double _t9 = _t3 * _t4;
        return new Double3x3(Math.fma(-this.m02, _t7, Math.fma(_t4, _t5, -(_t6 * _t0))), Math.fma(this.m02, _t1, -(_t2 * _t3)), Math.fma(this.m02, _t9, Math.fma(_t6, _t4, _t0 * _t5)), Math.fma(-this.m12, _t7, Math.fma(_t8, _t0, _t2 * _t4)), Math.fma(this.m12, _t1, _t3 * _t5), Math.fma(this.m12, _t9, Math.fma(_t0, _t2, -(_t8 * _t4))), -_t7, _t1, _t9, 0);
    }


    /**
     * Private body of {@code rotateZXY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZXY} dispatcher.
     */
    private Double3x3 rotateZXY_orthogonal(double angleZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleX);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleX);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleZ);
        double _t6 = _t1 * _t2;
        double _t8 = _t1 * _t5;
        double[] _col0 = rotateYZX_orthogonal_s2c94f613_c0(_t0 * _t3, Math.fma(_t4, _t5, -(_t6 * _t0)), Math.fma(_t8, _t0, _t2 * _t4));
        double[] _col1 = rotateXYZ_orthogonal_s361a4ff5_c1(_t1, _t3 * _t5, _t2 * _t3);
        double[] _col2 = lookAlong_orthogonal_s6a304d84_tail_s18d80cb4_c0(_t3 * _t4, Math.fma(_t6, _t4, _t0 * _t5), Math.fma(_t0, _t2, -(_t8 * _t4)));
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Private body of {@code rotateZXY}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZXY} dispatcher.
     */
    private Double3x3 rotateZXY_general(double angleZ, double angleX, double angleY) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleX);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleX);
        double _t4 = Math.cosFromSin(_t0, angleY);
        double _t5 = Math.cosFromSin(_t2, angleZ);
        double _t6 = _t1 * _t2;
        double _t8 = _t1 * _t5;
        double[] _col0 = rotateYZX_general_s2c94f613_c0(_t0 * _t3, Math.fma(_t4, _t5, -(_t6 * _t0)), Math.fma(_t8, _t0, _t2 * _t4));
        double[] _col1 = rotateXYZ_general_s361a4ff5_c1(_t1, _t3 * _t5, _t2 * _t3);
        double[] _col2 = lookAlong_general_s6a304d84_tail_s18d80cb4_c0(_t3 * _t4, Math.fma(_t6, _t4, _t0 * _t5), Math.fma(_t0, _t2, -(_t8 * _t4)));
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
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
    public Double3x3 rotateZXY(double angleZ, double angleX, double angleY) {
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
    private Double3x3 rotateZYX_identity(double angleZ, double angleY, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t0 * _t4;
        return new Double3x3(_t3 * _t4, Math.fma(_t7, _t2, -(_t1 * _t5)), Math.fma(_t7, _t5, _t2 * _t1), _t1 * _t3, Math.fma(_t6, _t2, _t5 * _t4), Math.fma(_t6, _t5, -(_t2 * _t4)), -_t0, _t2 * _t3, _t5 * _t3, 0);
    }


    /**
     * Private body of {@code rotateZYX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZYX} dispatcher.
     */
    private Double3x3 rotateZYX_translation(double angleZ, double angleY, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t7 = _t2 * _t3;
        double _t8 = _t0 * _t4;
        double _t9 = _t5 * _t3;
        return new Double3x3(Math.fma(_t3, _t4, -(this.m02 * _t0)), Math.fma(this.m02, _t7, Math.fma(_t8, _t2, -(_t1 * _t5))), Math.fma(this.m02, _t9, Math.fma(_t8, _t5, _t2 * _t1)), Math.fma(_t1, _t3, -(this.m12 * _t0)), Math.fma(this.m12, _t7, Math.fma(_t6, _t2, _t5 * _t4)), Math.fma(this.m12, _t9, Math.fma(_t6, _t5, -(_t2 * _t4))), -_t0, _t7, _t9, 0);
    }


    /**
     * Private body of {@code rotateZYX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZYX} dispatcher.
     */
    private Double3x3 rotateZYX_orthogonal(double angleZ, double angleY, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t10 = _t0 * _t4;
        double[] _col0 = rotateYZX_orthogonal_s2c94f613_c0(_t0, _t3 * _t4, _t1 * _t3);
        double[] _col1 = lookAlong_orthogonal_s6a304d84_tail_s18d80cb4_c0(_t2 * _t3, Math.fma(_t10, _t2, -(_t1 * _t5)), Math.fma(_t6, _t2, _t5 * _t4));
        double[] _col2 = lookAlong_orthogonal_s6a304d84_tail_s18d80cb4_c0(_t5 * _t3, Math.fma(_t10, _t5, _t2 * _t1), Math.fma(_t6, _t5, -(_t2 * _t4)));
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
    }


    /**
     * Private body of {@code rotateZYX}, specialized by runtime matrix properties; reached only
     * through the public {@code rotateZYX} dispatcher.
     */
    private Double3x3 rotateZYX_general(double angleZ, double angleY, double angleX) {
        double _t0 = Math.sin(angleY);
        double _t1 = Math.sin(angleZ);
        double _t2 = Math.sin(angleX);
        double _t3 = Math.cosFromSin(_t0, angleY);
        double _t4 = Math.cosFromSin(_t1, angleZ);
        double _t5 = Math.cosFromSin(_t2, angleX);
        double _t6 = _t0 * _t1;
        double _t10 = _t0 * _t4;
        double[] _col0 = rotateYZX_general_s2c94f613_c0(_t0, _t3 * _t4, _t1 * _t3);
        double[] _col1 = lookAlong_general_s6a304d84_tail_s18d80cb4_c0(_t2 * _t3, Math.fma(_t10, _t2, -(_t1 * _t5)), Math.fma(_t6, _t2, _t5 * _t4));
        double[] _col2 = lookAlong_general_s6a304d84_tail_s18d80cb4_c0(_t5 * _t3, Math.fma(_t10, _t5, _t2 * _t1), Math.fma(_t6, _t5, -(_t2 * _t4)));
        return new Double3x3(_col0[0], _col1[0], _col2[0], _col0[1], _col1[1], _col2[1], _col0[2], _col1[2], _col2[2], 0);
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
    public Double3x3 rotateZYX(double angleZ, double angleY, double angleX) {
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
    public Double3x3 rotateZn180() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(-1.0, 0.0, 0.0, 0.0, -1.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(-1.0, 0.0, this.m02, 0.0, -1.0, this.m12, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
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
    public Double3x3 rotateZn270() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(0.0, -1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(0.0, -1.0, this.m02, 1.0, 0.0, this.m12, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
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
    public Double3x3 rotateZn90() {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(0.0, 1.0, 0.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(0.0, 1.0, this.m02, -1.0, 0.0, this.m12, 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
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
    public Double3x3 scale(Double2 v) {
        double vX = v.x();
        double vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(vX, 0.0, 0.0, 0.0, vY, 0.0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(vX, 0.0, this.m02, 0.0, vY, this.m12, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scale_orthogonal(vX, vY);
        return scale_general(vX, vY);
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Double3x3 scale_orthogonal(double vX, double vY) {
        return new Double3x3(this.m00 * vX, this.m01 * vY, this.m02, this.m10 * vX, this.m11 * vY, this.m12, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Double3x3 scale_general(double vX, double vY) {
        return new Double3x3(this.m00 * vX, this.m01 * vY, this.m02, this.m10 * vX, this.m11 * vY, this.m12, this.m20 * vX, this.m21 * vY, this.m22, 0);
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
    public Double3x3 scale(double vX, double vY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(vX, 0.0, 0.0, 0.0, vY, 0.0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(vX, 0.0, this.m02, 0.0, vY, this.m12, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scale_orthogonal(vX, vY);
        return scale_general(vX, vY);
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Double3x3 scale_orthogonal(double s) {
        return new Double3x3(s * this.m00, s * this.m01, this.m02, s * this.m10, s * this.m11, this.m12, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code scale}, specialized by runtime matrix properties; reached only through
     * the public {@code scale} dispatcher.
     */
    private Double3x3 scale_general(double s) {
        return new Double3x3(s * this.m00, s * this.m01, this.m02, s * this.m10, s * this.m11, this.m12, s * this.m20, s * this.m21, this.m22, 0);
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
    public Double3x3 scale(double s) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(s, 0.0, 0.0, 0.0, s, 0.0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(s, 0.0, this.m02, 0.0, s, this.m12, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
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
    public Double3x3 scaleAround(double s, Double2 pivot) {
        double pivotX = pivot.x();
        double pivotY = pivot.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double _t0 = 1.0 - s;
            return new Double3x3(s, 0.0, pivotX * _t0, 0.0, s, pivotY * _t0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        }
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation(s, pivotX, pivotY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scaleAround_orthogonal(s, pivotX, pivotY);
        return scaleAround_general(s, pivotX, pivotY);
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double3x3 scaleAround_translation(double s, double pivotX, double pivotY) {
        double _t0 = 1.0 - s;
        return new Double3x3(s, 0.0, Math.fma(pivotX, _t0, this.m02), 0.0, s, Math.fma(pivotY, _t0, this.m12), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double3x3 scaleAround_orthogonal(double s, double pivotX, double pivotY) {
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        return new Double3x3(s * this.m00, s * this.m01, Math.fma(this.m00, _t1, Math.fma(this.m01, _t2, this.m02)), s * this.m10, s * this.m11, Math.fma(this.m10, _t1, Math.fma(this.m11, _t2, this.m12)), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double3x3 scaleAround_general(double s, double pivotX, double pivotY) {
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        return new Double3x3(s * this.m00, s * this.m01, Math.fma(this.m00, _t1, Math.fma(this.m01, _t2, this.m02)), s * this.m10, s * this.m11, Math.fma(this.m10, _t1, Math.fma(this.m11, _t2, this.m12)), s * this.m20, s * this.m21, Math.fma(this.m20, _t1, Math.fma(this.m21, _t2, this.m22)), 0);
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
    public Double3x3 scaleAround(double s, double pivotX, double pivotY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) {
            double _t0 = 1.0 - s;
            return new Double3x3(s, 0.0, pivotX * _t0, 0.0, s, pivotY * _t0, 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
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
    public Double3x3 scaleAround(Double2 s, Double2 pivot) {
        double sX = s.x();
        double sY = s.y();
        double pivotX = pivot.x();
        double pivotY = pivot.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(sX, 0.0, pivotX * (1.0 - sX), 0.0, sY, pivotY * (1.0 - sY), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return scaleAround_translation(sX, sY, pivotX, pivotY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return scaleAround_orthogonal(sX, sY, pivotX, pivotY);
        return scaleAround_general(sX, sY, pivotX, pivotY);
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double3x3 scaleAround_translation(double sX, double sY, double pivotX, double pivotY) {
        return new Double3x3(sX, 0.0, Math.fma(pivotX, 1.0 - sX, this.m02), 0.0, sY, Math.fma(pivotY, 1.0 - sY, this.m12), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double3x3 scaleAround_orthogonal(double sX, double sY, double pivotX, double pivotY) {
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        return new Double3x3(sX * this.m00, sY * this.m01, Math.fma(this.m00, _t2, Math.fma(this.m01, _t3, this.m02)), sX * this.m10, sY * this.m11, Math.fma(this.m10, _t2, Math.fma(this.m11, _t3, this.m12)), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code scaleAround}, specialized by runtime matrix properties; reached only
     * through the public {@code scaleAround} dispatcher.
     */
    private Double3x3 scaleAround_general(double sX, double sY, double pivotX, double pivotY) {
        double _t2 = pivotX * (1.0 - sX);
        double _t3 = pivotY * (1.0 - sY);
        return new Double3x3(sX * this.m00, sY * this.m01, Math.fma(this.m00, _t2, Math.fma(this.m01, _t3, this.m02)), sX * this.m10, sY * this.m11, Math.fma(this.m10, _t2, Math.fma(this.m11, _t3, this.m12)), sX * this.m20, sY * this.m21, Math.fma(this.m20, _t2, Math.fma(this.m21, _t3, this.m22)), 0);
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
    public Double3x3 scaleAround(double sX, double sY, double pivotX, double pivotY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(sX, 0.0, pivotX * (1.0 - sX), 0.0, sY, pivotY * (1.0 - sY), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
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
    public Double3x3 translate(Double2 v) {
        double vX = v.x();
        double vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(1.0, 0.0, vX, 0.0, 1.0, vY, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(1.0, 0.0, this.m02 + vX, 0.0, 1.0, this.m12 + vY, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return translate_orthogonal(vX, vY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return translate_affine(vX, vY);
        return translate_general(vX, vY);
    }


    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Double3x3 translate_orthogonal(double vX, double vY) {
        return new Double3x3(this.m00, this.m01, Math.fma(this.m00, vX, Math.fma(this.m01, vY, this.m02)), this.m10, this.m11, Math.fma(this.m10, vX, Math.fma(this.m11, vY, this.m12)), 0.0, 0.0, 1.0, Joml.BIT_ORTHOGONAL);
    }


    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Double3x3 translate_affine(double vX, double vY) {
        return new Double3x3(this.m00, this.m01, Math.fma(this.m00, vX, Math.fma(this.m01, vY, this.m02)), this.m10, this.m11, Math.fma(this.m10, vX, Math.fma(this.m11, vY, this.m12)), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code translate}, specialized by runtime matrix properties; reached only
     * through the public {@code translate} dispatcher.
     */
    private Double3x3 translate_general(double vX, double vY) {
        return new Double3x3(this.m00, this.m01, Math.fma(this.m00, vX, Math.fma(this.m01, vY, this.m02)), this.m10, this.m11, Math.fma(this.m10, vX, Math.fma(this.m11, vY, this.m12)), this.m20, this.m21, Math.fma(this.m20, vX, Math.fma(this.m21, vY, this.m22)), 0);
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
    public Double3x3 translate(double vX, double vY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3x3(1.0, 0.0, vX, 0.0, 1.0, vY, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3x3(1.0, 0.0, this.m02 + vX, 0.0, 1.0, this.m12 + vY, 0.0, 0.0, 1.0, Joml.BIT_TRANSLATION);
        if ((p & Joml.BIT_ORTHOGONAL) == Joml.BIT_ORTHOGONAL) return translate_orthogonal(vX, vY);
        if ((p & Joml.BIT_AFFINE) == Joml.BIT_AFFINE) return translate_affine(vX, vY);
        return translate_general(vX, vY);
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double3x3 view_identity(double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        return new Double3x3(_t0_inv + _t0_inv, 0.0, -((left + right) * _t0_inv), 0.0, _t1_inv + _t1_inv, -((bottom + top) * _t1_inv), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double3x3 view_translation(double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _t1_inv = 1.0 / (top - bottom);
        return new Double3x3(_t0_inv + _t0_inv, 0.0, Math.fma(-(left + right), _t0_inv, this.m02), 0.0, _t1_inv + _t1_inv, Math.fma(-(bottom + top), _t1_inv, this.m12), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double3x3 view_orthogonal(double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        return new Double3x3(_sp0 * this.m00, _sp1 * this.m01, Math.fma(-this.m01, _sp3, Math.fma(-this.m00, _sp2, this.m02)), _sp0 * this.m10, _sp1 * this.m11, Math.fma(-this.m11, _sp3, Math.fma(-this.m10, _sp2, this.m12)), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double3x3 view_affine(double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        return new Double3x3(_sp0 * this.m00, _sp1 * this.m01, this.m02 + Math.fma(-this.m01, _sp3, -(this.m00 * _sp2)), _sp0 * this.m10, _sp1 * this.m11, this.m12 + Math.fma(-this.m11, _sp3, -(this.m10 * _sp2)), 0.0, 0.0, 1.0, Joml.BIT_AFFINE);
    }


    /**
     * Private body of {@code view}, specialized by runtime matrix properties; reached only through
     * the public {@code view} dispatcher.
     */
    private Double3x3 view_general(double left, double right, double bottom, double top) {
        double _t0_inv = 1.0 / (right - left);
        double _sp0 = _t0_inv + _t0_inv;
        double _t1_inv = 1.0 / (top - bottom);
        double _sp1 = _t1_inv + _t1_inv;
        double _sp2 = _t0_inv * (left + right);
        double _sp3 = _t1_inv * (bottom + top);
        return new Double3x3(_sp0 * this.m00, _sp1 * this.m01, this.m02 + Math.fma(-this.m01, _sp3, -(this.m00 * _sp2)), _sp0 * this.m10, _sp1 * this.m11, this.m12 + Math.fma(-this.m11, _sp3, -(this.m10 * _sp2)), _sp0 * this.m20, _sp1 * this.m21, this.m22 + Math.fma(-this.m21, _sp3, -(this.m20 * _sp2)), 0);
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
    public Double3x3 view(double left, double right, double bottom, double top) {
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
    public Double3 mul(Double3 v) {
        double vX = v.x();
        double vY = v.y();
        double vZ = v.z();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3(vX, vY, vZ);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3(Math.fma(this.m02, vZ, vX), Math.fma(this.m12, vZ, vY), vZ);
        return mul_general(vX, vY, vZ);
    }


    /**
     * Private body of {@code mul}, specialized by runtime matrix properties; reached only through
     * the public {@code mul} dispatcher.
     */
    private Double3 mul_general(double vX, double vY, double vZ) {
        return new Double3(Math.fma(this.m02, vZ, Math.fma(this.m00, vX, this.m01 * vY)), Math.fma(this.m12, vZ, Math.fma(this.m10, vX, this.m11 * vY)), Math.fma(this.m22, vZ, Math.fma(this.m20, vX, this.m21 * vY)));
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
    public Double3 mul(double vX, double vY, double vZ) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double3(vX, vY, vZ);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double3(Math.fma(this.m02, vZ, vX), Math.fma(this.m12, vZ, vY), vZ);
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
    public Double2 transformDirection(Double2 v) {
        double vX = v.x();
        double vY = v.y();
        if ((this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double2(vX, vY);
        return transformDirection_general(vX, vY);
    }


    /**
     * Private body of {@code transformDirection}, specialized by runtime matrix properties; reached
     * only through the public {@code transformDirection} dispatcher.
     */
    private Double2 transformDirection_general(double vX, double vY) {
        return new Double2(Math.fma(this.m00, vX, this.m01 * vY), Math.fma(this.m10, vX, this.m11 * vY));
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
    public Double2 transformDirection(double vX, double vY) {
        if ((this.properties & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double2(vX, vY);
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
    public Double2 transformPosition(Double2 v) {
        double vX = v.x();
        double vY = v.y();
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2(vX, vY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double2(this.m02 + vX, this.m12 + vY);
        return transformPosition_general(vX, vY);
    }


    /**
     * Private body of {@code transformPosition}, specialized by runtime matrix properties; reached
     * only through the public {@code transformPosition} dispatcher.
     */
    private Double2 transformPosition_general(double vX, double vY) {
        return new Double2(Math.fma(this.m00, vX, Math.fma(this.m01, vY, this.m02)), Math.fma(this.m10, vX, Math.fma(this.m11, vY, this.m12)));
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
    public Double2 transformPosition(double vX, double vY) {
        int p = this.properties;
        if ((p & Joml.BIT_IDENTITY) == Joml.BIT_IDENTITY) return new Double2(vX, vY);
        if ((p & Joml.BIT_TRANSLATION) == Joml.BIT_TRANSLATION) return new Double2(this.m02 + vX, this.m12 + vY);
        return transformPosition_general(vX, vY);
    }

    /**
     * {@return a copy with the {@code m00} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m00} element
     */
    public Double3x3 withM00(double v) {
        return new Double3x3(v, m01, m02, m10, m11, m12, m20, m21, m22);
    }

    /**
     * {@return a copy with the {@code m01} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m01} element
     */
    public Double3x3 withM01(double v) {
        return new Double3x3(m00, v, m02, m10, m11, m12, m20, m21, m22);
    }

    /**
     * {@return a copy with the {@code m02} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m02} element
     */
    public Double3x3 withM02(double v) {
        return new Double3x3(m00, m01, v, m10, m11, m12, m20, m21, m22);
    }

    /**
     * {@return a copy with the {@code m10} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m10} element
     */
    public Double3x3 withM10(double v) {
        return new Double3x3(m00, m01, m02, v, m11, m12, m20, m21, m22);
    }

    /**
     * {@return a copy with the {@code m11} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m11} element
     */
    public Double3x3 withM11(double v) {
        return new Double3x3(m00, m01, m02, m10, v, m12, m20, m21, m22);
    }

    /**
     * {@return a copy with the {@code m12} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m12} element
     */
    public Double3x3 withM12(double v) {
        return new Double3x3(m00, m01, m02, m10, m11, v, m20, m21, m22);
    }

    /**
     * {@return a copy with the {@code m20} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m20} element
     */
    public Double3x3 withM20(double v) {
        return new Double3x3(m00, m01, m02, m10, m11, m12, v, m21, m22);
    }

    /**
     * {@return a copy with the {@code m21} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m21} element
     */
    public Double3x3 withM21(double v) {
        return new Double3x3(m00, m01, m02, m10, m11, m12, m20, v, m22);
    }

    /**
     * {@return a copy with the {@code m22} element replaced by {@code v}}
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param v the new value of the {@code m22} element
     */
    public Double3x3 withM22(double v) {
        return new Double3x3(m00, m01, m02, m10, m11, m12, m20, m21, v);
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
    public Double3x3 withProperties(int properties) {
        return new Double3x3(m00, m01, m02, m10, m11, m12, m20, m21, m22, properties);
    }

    @Override public String toString() {
        return "Double3x3(\n    " + m00() + ", " + m01() + ", " + m02() + "\n    " + m10() + ", " + m11() + ", " + m12() + "\n    " + m20() + ", " + m21() + ", " + m22() + "\n)";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Double3x3)) return false;
        Double3x3 o = (Double3x3) obj;
        return Double.doubleToLongBits(m00) == Double.doubleToLongBits(o.m00)
            && Double.doubleToLongBits(m01) == Double.doubleToLongBits(o.m01)
            && Double.doubleToLongBits(m02) == Double.doubleToLongBits(o.m02)
            && Double.doubleToLongBits(m10) == Double.doubleToLongBits(o.m10)
            && Double.doubleToLongBits(m11) == Double.doubleToLongBits(o.m11)
            && Double.doubleToLongBits(m12) == Double.doubleToLongBits(o.m12)
            && Double.doubleToLongBits(m20) == Double.doubleToLongBits(o.m20)
            && Double.doubleToLongBits(m21) == Double.doubleToLongBits(o.m21)
            && Double.doubleToLongBits(m22) == Double.doubleToLongBits(o.m22);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + (int)(Double.doubleToLongBits(m00) ^ (Double.doubleToLongBits(m00) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m01) ^ (Double.doubleToLongBits(m01) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m02) ^ (Double.doubleToLongBits(m02) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m10) ^ (Double.doubleToLongBits(m10) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m11) ^ (Double.doubleToLongBits(m11) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m12) ^ (Double.doubleToLongBits(m12) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m20) ^ (Double.doubleToLongBits(m20) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m21) ^ (Double.doubleToLongBits(m21) >>> 32));
        h = 31 * h + (int)(Double.doubleToLongBits(m22) ^ (Double.doubleToLongBits(m22) >>> 32));
        return h;
    }

    /** {@return whether all components of this value are finite, i.e. neither NaN nor infinite} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isFinite() {
        return Double.isFinite(m00)
            && Double.isFinite(m01)
            && Double.isFinite(m02)
            && Double.isFinite(m10)
            && Double.isFinite(m11)
            && Double.isFinite(m12)
            && Double.isFinite(m20)
            && Double.isFinite(m21)
            && Double.isFinite(m22);
    }

    /** {@return whether any component of this value is NaN} <p>Valid input: any value, NaN and the infinities included. */
    public boolean isNaN() {
        return Double.isNaN(m00)
            || Double.isNaN(m01)
            || Double.isNaN(m02)
            || Double.isNaN(m10)
            || Double.isNaN(m11)
            || Double.isNaN(m12)
            || Double.isNaN(m20)
            || Double.isNaN(m21)
            || Double.isNaN(m22);
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
    public boolean equalsEpsilon(Double3x3 other, double epsilon) {
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
        static final Double3x3BbOps BB_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double3x3BbOpsUnsafe()
                        : new Double3x3BbOpsApi();
        static final Double3x3RawOps RAW_OPS =
                Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
                        ? new Double3x3RawOpsUnsafe()
                        : new Double3x3RawOpsApi();
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
     * Store the elements into the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeCM(double[] dest) { return storeCM(dest, 0); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCM(double[] src, int offset) {
        double _c0 = src[offset];
        double _c3 = src[offset + 1];
        double _c6 = src[offset + 2];
        double _c1 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c7 = src[offset + 5];
        double _c2 = src[offset + 6];
        double _c5 = src[offset + 7];
        double _c8 = src[offset + 8];
        return new Double3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCM(double[] src) { return loadCM(src, 0); }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
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
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeCM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, starting at the given
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
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
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
     * @param buf the source buffer
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf);
    }

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
     * @param buf the source buffer
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(index, buf);
    }

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
     * @param buf the source buffer
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadCMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x3 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf);
        buf.position(pos + 72);
        return buf;
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
     * @param buf the source byte buffer
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf);
    }

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
     * @param buf the source byte buffer
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(index, buf);
    }

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
     * @param buf the source byte buffer
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadCMRelative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x3 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
        buf.position(pos + 72);
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
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public Double3x3 storeCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static Double3x3 loadCMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(address);
    }


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
    public float[] storeCM(float[] dest, int offset) {
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[offset + 2] = (float) this.m20;
        dest[offset + 3] = (float) this.m01;
        dest[offset + 4] = (float) this.m11;
        dest[offset + 5] = (float) this.m21;
        dest[offset + 6] = (float) this.m02;
        dest[offset + 7] = (float) this.m12;
        dest[offset + 8] = (float) this.m22;
        return dest;
    }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] storeCM(float[] dest) { return storeCM(dest, 0); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCM(float[] src, int offset) {
        double _c0 = src[offset];
        double _c3 = src[offset + 1];
        double _c6 = src[offset + 2];
        double _c1 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c7 = src[offset + 5];
        double _c2 = src[offset + 6];
        double _c5 = src[offset + 7];
        double _c8 = src[offset + 8];
        return new Double3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCM(float[] src) { return loadCM(src, 0); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at its current position (the position is not modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeCM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at its current position and advancing the position accordingly.
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
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified).
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
     * @param buf the source buffer
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified).
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
     * @param buf the source buffer
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadCMAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly.
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
     * @param buf the source buffer
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadCMRelative(FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x3 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf);
        buf.position(pos + 9);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at its current position (the position is not modified).
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at the given absolute index (the position is not used or
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at its current position and advancing the position accordingly.
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
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMFloatAbsolute(this, pos, buf);
        buf.position(pos + 36);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at its current position (the position is not modified).
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
     * @param buf the source byte buffer
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at the given absolute index (the position is not used or
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
     * @param buf the source byte buffer
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at its current position and advancing the position accordingly.
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
     * @param buf the source byte buffer
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadCMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x3 r = StoreLoad.BB_OPS.loadCMFloatAbsolute(pos, buf);
        buf.position(pos + 36);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, converting each
     * element to {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public Double3x3 storeCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address in column-major order, converting each
     * element from {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static Double3x3 loadCMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(address);
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
     * Store the elements into the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeRM(double[] dest) { return storeRM(dest, 0); }

    /**
     * Load the elements from the given array in row-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRM(double[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c5 = src[offset + 5];
        double _c6 = src[offset + 6];
        double _c7 = src[offset + 7];
        double _c8 = src[offset + 8];
        return new Double3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    /**
     * Load the elements from the given array in row-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRM(double[] src) { return loadRM(src, 0); }

    /**
     * Store the elements into the given buffer in row-major order, starting at its current position
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
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeRM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, starting at the given absolute
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
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, starting at its current position
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
     * @param buf the source buffer
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRM(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf);
    }

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
     * @param buf the source buffer
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRMAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(index, buf);
    }

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
     * @param buf the source buffer
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadRMRelative(DoubleBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x3 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf);
        buf.position(pos + 72);
        return buf;
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
     * @param buf the source byte buffer
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRM(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf);
    }

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
     * @param buf the source byte buffer
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRMAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(index, buf);
    }

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
     * @param buf the source byte buffer
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadRMRelative(ByteBuffer buf) {
        if (buf.remaining() < 72) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x3 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
        buf.position(pos + 72);
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
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public Double3x3 storeRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address in row-major order. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static Double3x3 loadRMUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(address);
    }


    /**
     * Store the elements into the given array in row-major order, converting each element to
     * {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] storeRM(float[] dest, int offset) {
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[offset + 2] = (float) this.m02;
        dest[offset + 3] = (float) this.m10;
        dest[offset + 4] = (float) this.m11;
        dest[offset + 5] = (float) this.m12;
        dest[offset + 6] = (float) this.m20;
        dest[offset + 7] = (float) this.m21;
        dest[offset + 8] = (float) this.m22;
        return dest;
    }

    /**
     * Store the elements into the given array in row-major order, converting each element to
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] storeRM(float[] dest) { return storeRM(dest, 0); }

    /**
     * Load the elements from the given array in row-major order, converting each element from
     * {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRM(float[] src, int offset) {
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[offset + 3];
        double _c4 = src[offset + 4];
        double _c5 = src[offset + 5];
        double _c6 = src[offset + 6];
        double _c7 = src[offset + 7];
        double _c8 = src[offset + 8];
        return new Double3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    /**
     * Load the elements from the given array in row-major order, converting each element from
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRM(float[] src) { return loadRM(src, 0); }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code float}, starting at its current position (the position is not modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeRM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code float}, starting at its current position and advancing the position accordingly.
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
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified).
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
     * @param buf the source buffer
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRM(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified).
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
     * @param buf the source buffer
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRMAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadRMAbsolute(index, buf);
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly.
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
     * @param buf the source buffer
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadRMRelative(FloatBuffer buf) {
        if (buf.remaining() < 9) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x3 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf);
        buf.position(pos + 9);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code float}, starting at its current position (the position is not modified).
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified).
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code float}, starting at its current position and advancing the position accordingly.
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
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMFloatAbsolute(this, pos, buf);
        buf.position(pos + 36);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified).
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
     * @param buf the source byte buffer
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRMFloat(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(buf.position(), buf);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified).
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
     * @param buf the source byte buffer
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRMFloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(index, buf);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly.
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
     * @param buf the source byte buffer
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadRMFloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 36) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x3 r = StoreLoad.BB_OPS.loadRMFloatAbsolute(pos, buf);
        buf.position(pos + 36);
        return r;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, converting each
     * element to {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public Double3x3 storeRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address);
    }

    /**
     * Load the elements from the given raw memory address in row-major order, converting each
     * element from {@code float}. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static Double3x3 loadRMFloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(address);
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
     * Load the elements from the given array in column-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        double _c0 = src[offset];
        double _c3 = src[offset + 1];
        double _c6 = src[offset + 2];
        double _c1 = src[_p1];
        double _c4 = src[_p1 + 1];
        double _c7 = src[_p1 + 2];
        double _c2 = src[_p2];
        double _c5 = src[_p2 + 1];
        double _c8 = src[_p2 + 2];
        return new Double3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
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
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public DoubleBuffer storeCM(DoubleBuffer buf, int stride) {
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * Store the elements into the given buffer in column-major order, starting at its current
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
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCM(DoubleBuffer buf, int stride) {
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCMAbsolute(int index, DoubleBuffer buf, int stride) {
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadCMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x3 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 8);
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCM(ByteBuffer buf, int stride) {
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCMAbsolute(int index, ByteBuffer buf, int stride) {
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadCMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x3 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
        buf.position(pos + (3 * stride) * 8);
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
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public Double3x3 storeCMUnsafe(long address, int stride) {
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
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static Double3x3 loadCMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMUnsafe(address, stride);
    }


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
    public float[] storeCM(float[] dest, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[offset + 2] = (float) this.m20;
        dest[_p1] = (float) this.m01;
        dest[_p1 + 1] = (float) this.m11;
        dest[_p1 + 2] = (float) this.m21;
        dest[_p2] = (float) this.m02;
        dest[_p2 + 1] = (float) this.m12;
        dest[_p2 + 2] = (float) this.m22;
        return dest;
    }

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
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        double _c0 = src[offset];
        double _c3 = src[offset + 1];
        double _c6 = src[offset + 2];
        double _c1 = src[_p1];
        double _c4 = src[_p1 + 1];
        double _c7 = src[_p1 + 2];
        double _c2 = src[_p2];
        double _c5 = src[_p2 + 1];
        double _c8 = src[_p2 + 2];
        return new Double3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at its current position (the position is not modified), with
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
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public FloatBuffer storeCM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified),
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
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public FloatBuffer storeCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMAbsolute(this, index, buf, stride);
    }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at its current position and advancing the position accordingly, with
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
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified), with
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
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified),
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
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMAbsolute(index, buf, stride);
    }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly, with
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
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadCMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x3 r = StoreLoad.BB_OPS.loadCMAbsolute(pos, buf, stride);
        buf.position(pos + 3 * stride);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at its current position (the position is not modified), with
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
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public ByteBuffer storeCMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at the given absolute index (the position is not used or
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
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     */
    public ByteBuffer storeCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeCMFloatAbsolute(this, index, buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, converting each element
     * to {@code float}, starting at its current position and advancing the position accordingly,
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
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 4);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at its current position (the position is not modified), with
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
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at the given absolute index (the position is not used or
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
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadCMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadCMFloatAbsolute(index, buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in column-major order, converting each element
     * from {@code float}, starting at its current position and advancing the position accordingly,
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
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadCMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x3 r = StoreLoad.BB_OPS.loadCMFloatAbsolute(pos, buf, stride);
        buf.position(pos + (3 * stride) * 4);
        return r;
    }

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
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public Double3x3 storeCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeCMFloatUnsafe(this, address, stride);
    }

    /**
     * Load the elements from the given raw memory address in column-major order, converting each
     * element from {@code float}, with {@code stride} elements between the starts of consecutive
     * columns. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static Double3x3 loadCMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadCMFloatUnsafe(address, stride);
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
     * Load the elements from the given array in row-major order, starting at the given offset, with
     * {@code stride} elements between the starts of consecutive rows.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRM(double[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[_p1];
        double _c4 = src[_p1 + 1];
        double _c5 = src[_p1 + 2];
        double _c6 = src[_p2];
        double _c7 = src[_p2 + 1];
        double _c8 = src[_p2 + 2];
        return new Double3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    /**
     * Store the elements into the given buffer in row-major order, starting at its current position
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
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public DoubleBuffer storeRM(DoubleBuffer buf, int stride) {
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * Store the elements into the given buffer in row-major order, starting at its current position
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
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRM(DoubleBuffer buf, int stride) {
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRMAbsolute(int index, DoubleBuffer buf, int stride) {
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadRMRelative(DoubleBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x3 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 8);
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRM(ByteBuffer buf, int stride) {
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param index the absolute byte index in the byte buffer
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRMAbsolute(int index, ByteBuffer buf, int stride) {
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadRMRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 8) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x3 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
        buf.position(pos + (3 * stride) * 8);
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
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public Double3x3 storeRMUnsafe(long address, int stride) {
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
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static Double3x3 loadRMUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMUnsafe(address, stride);
    }


    /**
     * Store the elements into the given array in row-major order, converting each element to
     * {@code float}, starting at the given offset, with {@code stride} elements between the starts
     * of consecutive rows.
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
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[offset + 2] = (float) this.m02;
        dest[_p1] = (float) this.m10;
        dest[_p1 + 1] = (float) this.m11;
        dest[_p1 + 2] = (float) this.m12;
        dest[_p2] = (float) this.m20;
        dest[_p2 + 1] = (float) this.m21;
        dest[_p2 + 2] = (float) this.m22;
        return dest;
    }

    /**
     * Load the elements from the given array in row-major order, converting each element from
     * {@code float}, starting at the given offset, with {@code stride} elements between the starts
     * of consecutive rows.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRM(float[] src, int offset, int stride) {
        int _p1 = offset + stride;
        int _p2 = _p1 + stride;
        double _c0 = src[offset];
        double _c1 = src[offset + 1];
        double _c2 = src[offset + 2];
        double _c3 = src[_p1];
        double _c4 = src[_p1 + 1];
        double _c5 = src[_p1 + 2];
        double _c6 = src[_p2];
        double _c7 = src[_p2 + 1];
        double _c8 = src[_p2 + 2];
        return new Double3x3(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8);
    }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code float}, starting at its current position (the position is not modified), with
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
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public FloatBuffer storeRM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified),
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
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public FloatBuffer storeRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMAbsolute(this, index, buf, stride);
    }

    /**
     * Store the elements into the given buffer in row-major order, converting each element to
     * {@code float}, starting at its current position and advancing the position accordingly, with
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
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified), with
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
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRM(FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified),
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
     * @param index the absolute element index in the buffer
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRMAbsolute(int index, FloatBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMAbsolute(index, buf, stride);
    }

    /**
     * Load the elements from the given buffer in row-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly, with
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
     * @param buf the source buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadRMRelative(FloatBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x3 r = StoreLoad.BB_OPS.loadRMAbsolute(pos, buf, stride);
        buf.position(pos + 3 * stride);
        return r;
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code float}, starting at its current position (the position is not modified), with
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
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public ByteBuffer storeRMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, buf.position(), buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified),
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
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     */
    public ByteBuffer storeRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.storeRMFloatAbsolute(this, index, buf, stride);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, converting each element to
     * {@code float}, starting at its current position and advancing the position accordingly, with
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
     * @param buf the destination byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRMFloatAbsolute(this, pos, buf, stride);
        buf.position(pos + (3 * stride) * 4);
        return buf;
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified), with
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
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRMFloat(ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(buf.position(), buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified),
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
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 loadRMFloatAbsolute(int index, ByteBuffer buf, int stride) {
        return StoreLoad.BB_OPS.loadRMFloatAbsolute(index, buf, stride);
    }

    /**
     * Load the elements from the given byte buffer in row-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly, with
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
     * @param buf the source byte buffer
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadRMFloatRelative(ByteBuffer buf, int stride) {
        if (buf.remaining() < 3L * stride * 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        Double3x3 r = StoreLoad.BB_OPS.loadRMFloatAbsolute(pos, buf, stride);
        buf.position(pos + (3 * stride) * 4);
        return r;
    }

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
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public Double3x3 storeRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.storeRMFloatUnsafe(this, address, stride);
    }

    /**
     * Load the elements from the given raw memory address in row-major order, converting each
     * element from {@code float}, with {@code stride} elements between the starts of consecutive
     * rows. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive rows
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static Double3x3 loadRMFloatUnsafe(long address, int stride) {
        return StoreLoad.RAW_OPS.loadRMFloatUnsafe(address, stride);
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
     * matrix.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeCM4x4(double[] dest) { return storeCM4x4(dest, 0); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at its current position (the position is not modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeCM4x4(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at the given absolute index (the position is not used or modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeCM4x4Absolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at its current position and advancing the position accordingly.
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
     * 4x4 matrix, starting at its current position (the position is not modified).
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCM4x4Relative(ByteBuffer buf) {
        if (buf.remaining() < 128) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM4x4Absolute(this, pos, buf);
        buf.position(pos + 128);
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
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public Double3x3 storeCM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4Unsafe(this, address);
    }


    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] storeCM4x4(float[] dest, int offset) {
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m10;
        dest[offset + 2] = (float) this.m20;
        dest[offset + 3] = 0.0f;
        dest[offset + 4] = (float) this.m01;
        dest[offset + 5] = (float) this.m11;
        dest[offset + 6] = (float) this.m21;
        dest[offset + 7] = 0.0f;
        dest[offset + 8] = (float) this.m02;
        dest[offset + 9] = (float) this.m12;
        dest[offset + 10] = (float) this.m22;
        dest[offset + 11] = 0.0f;
        dest[offset + 12] = 0.0f;
        dest[offset + 13] = 0.0f;
        dest[offset + 14] = 0.0f;
        dest[offset + 15] = 1.0f;
        return dest;
    }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] storeCM4x4(float[] dest) { return storeCM4x4(dest, 0); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at its current position (the
     * position is not modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeCM4x4(FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at the given absolute index (the
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
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeCM4x4Absolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at its current position and
     * advancing the position accordingly.
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
     * 4x4 matrix, converting each element to {@code float}, starting at its current position (the
     * position is not modified).
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM4x4Float(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4FloatAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, converting each element to {@code float}, starting at the given absolute index
     * (the position is not used or modified).
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeCM4x4FloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeCM4x4FloatAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, converting each element to {@code float}, starting at its current position and
     * advancing the position accordingly.
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
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeCM4x4FloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeCM4x4FloatAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in column-major order, identity-extended
     * to a 4x4 matrix, converting each element to {@code float}. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public Double3x3 storeCM4x4FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeCM4x4FloatUnsafe(this, address);
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
     * matrix.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] storeRM4x4(double[] dest) { return storeRM4x4(dest, 0); }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, starting at its current position (the position is not modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeRM4x4(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, starting at the given absolute index (the position is not used or modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public DoubleBuffer storeRM4x4Absolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, starting at its current position and advancing the position accordingly.
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
     * matrix, starting at its current position (the position is not modified).
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRM4x4Relative(ByteBuffer buf) {
        if (buf.remaining() < 128) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM4x4Absolute(this, pos, buf);
        buf.position(pos + 128);
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
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public Double3x3 storeRM4x4Unsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4Unsafe(this, address);
    }


    /**
     * Store the elements into the given array in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public float[] storeRM4x4(float[] dest, int offset) {
        dest[offset] = (float) this.m00;
        dest[offset + 1] = (float) this.m01;
        dest[offset + 2] = (float) this.m02;
        dest[offset + 3] = 0.0f;
        dest[offset + 4] = (float) this.m10;
        dest[offset + 5] = (float) this.m11;
        dest[offset + 6] = (float) this.m12;
        dest[offset + 7] = 0.0f;
        dest[offset + 8] = (float) this.m20;
        dest[offset + 9] = (float) this.m21;
        dest[offset + 10] = (float) this.m22;
        dest[offset + 11] = 0.0f;
        dest[offset + 12] = 0.0f;
        dest[offset + 13] = 0.0f;
        dest[offset + 14] = 0.0f;
        dest[offset + 15] = 1.0f;
        return dest;
    }

    /**
     * Store the elements into the given array in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] storeRM4x4(float[] dest) { return storeRM4x4(dest, 0); }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at its current position (the
     * position is not modified).
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
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeRM4x4(FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at the given absolute index (the
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
     * @param index the absolute element index in the buffer
     * @param buf the destination buffer
     * @return buf
     */
    public FloatBuffer storeRM4x4Absolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4Absolute(this, index, buf);
    }

    /**
     * Store the elements into the given buffer in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at its current position and
     * advancing the position accordingly.
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
     * matrix, converting each element to {@code float}, starting at its current position (the
     * position is not modified).
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM4x4Float(ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4FloatAbsolute(this, buf.position(), buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at the given absolute index (the
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
     * @param buf the destination byte buffer
     * @return buf
     */
    public ByteBuffer storeRM4x4FloatAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeRM4x4FloatAbsolute(this, index, buf);
    }

    /**
     * Store the elements into the given byte buffer in row-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at its current position and
     * advancing the position accordingly.
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
     * @param buf the destination byte buffer
     * @return buf
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRM4x4FloatRelative(ByteBuffer buf) {
        if (buf.remaining() < 64) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeRM4x4FloatAbsolute(this, pos, buf);
        buf.position(pos + 64);
        return buf;
    }

    /**
     * Store the elements into the given raw memory address in row-major order, identity-extended to
     * a 4x4 matrix, converting each element to {@code float}. No bounds or liveness checks are
     * performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public Double3x3 storeRM4x4FloatUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeRM4x4FloatUnsafe(this, address);
    }


    /**
     * Store the elements into the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store(double[] dest) { return storeCM(dest, 0); }

    /**
     * Store the elements into the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @param offset the start offset in the array, in elements
     * @return dest
     */
    public double[] store(double[] dest, int offset) { return storeCM(dest, offset); }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
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
     * @param dest the destination buffer
     * @return dest
     */
    public DoubleBuffer store(DoubleBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, dest.position(), dest); }

    /**
     * Store the elements into the given buffer in column-major order, starting at the given
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
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    public DoubleBuffer store(int index, DoubleBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
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
     * @param dest the destination buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeRelative(DoubleBuffer dest) { return storeCMRelative(dest); }

    /**
     * Store the elements into the given array in column-major order, converting each element to
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] store(float[] dest) { return storeCM(dest, 0); }

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
    public float[] store(float[] dest, int offset) { return storeCM(dest, offset); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at its current position (the position is not modified).
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
    public FloatBuffer store(FloatBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, dest.position(), dest); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified).
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
    public FloatBuffer store(int index, FloatBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at its current position and advancing the position accordingly.
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
    public FloatBuffer storeRelative(FloatBuffer dest) { return storeCMRelative(dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
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
    public ByteBuffer store(int index, ByteBuffer dest) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at its current
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
     * @param dest the destination byte buffer
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the byte buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public ByteBuffer storeRelative(ByteBuffer dest) { return storeCMRelative(dest); }

    /**
     * Store the elements into the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public Double3x3 storeUnsafe(long address) { return StoreLoad.RAW_OPS.storeCMUnsafe(this, address); }

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
    public double[] store(double[] dest, int offset, int stride) { return storeCM(dest, offset, stride); }

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
    public float[] store(float[] dest, int offset, int stride) { return storeCM(dest, offset, stride); }

    /**
     * Store the elements into the given buffer in column-major order, starting at the given
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
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public DoubleBuffer store(int index, DoubleBuffer dest, int stride) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest, stride); }

    /**
     * Store the elements into the given buffer in column-major order, starting at its current
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
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public DoubleBuffer storeRelative(DoubleBuffer dest, int stride) { return storeCMRelative(dest, stride); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at the given absolute index (the position is not used or modified),
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
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     */
    public FloatBuffer store(int index, FloatBuffer dest, int stride) { return StoreLoad.BB_OPS.storeCMAbsolute(this, index, dest, stride); }

    /**
     * Store the elements into the given buffer in column-major order, converting each element to
     * {@code float}, starting at its current position and advancing the position accordingly, with
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
     * @param dest the destination buffer
     * @param stride the number of elements between the starts of consecutive columns
     * @return dest
     * @throws java.nio.BufferOverflowException if less remains in the buffer than the position
     *        advances over; nothing is written and the position is unchanged
     */
    public FloatBuffer storeRelative(FloatBuffer dest, int stride) { return storeCMRelative(dest, stride); }

    /**
     * Store the elements into the given byte buffer in column-major order, starting at the given
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
     * With the UNSAFE backend, offsets into direct buffers are not bounds-checked; the API backend
     * goes through the buffer's own {@code get}/{@code put} methods and performs the standard
     * checks.
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
     * Store the elements into the given raw memory address in column-major order, with
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
    public Double3x3 storeUnsafe(long address, int stride) { return StoreLoad.RAW_OPS.storeCMUnsafe(this, address, stride); }

    /**
     * Load the elements from the given array in column-major order.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 load(double[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 load(double[] src, int offset) { return loadCM(src, offset); }

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
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 load(DoubleBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

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
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 load(int index, DoubleBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

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
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadRelative(DoubleBuffer src) { return loadCMRelative(src); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 load(float[] src) { return loadCM(src, 0); }

    /**
     * Load the elements from the given array in column-major order, converting each element from
     * {@code float}, starting at the given offset.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 load(float[] src, int offset) { return loadCM(src, offset); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position (the position is not modified).
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
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 load(FloatBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified).
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
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 load(int index, FloatBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly.
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
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadRelative(FloatBuffer src) { return loadCMRelative(src); }

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
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 load(ByteBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(src.position(), src); }

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
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 load(int index, ByteBuffer src) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src); }

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
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadRelative(ByteBuffer src) { return loadCMRelative(src); }

    /**
     * Load the elements from the given raw memory address in column-major order. No bounds or
     * liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static Double3x3 loadUnsafe(long address) { return StoreLoad.RAW_OPS.loadCMUnsafe(address); }

    /**
     * Load the elements from the given array in column-major order, starting at the given offset,
     * with {@code stride} elements between the starts of consecutive columns.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param src the source array
     * @param offset the start offset in the array, in elements
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 load(double[] src, int offset, int stride) { return loadCM(src, offset, stride); }

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
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 load(float[] src, int offset, int stride) { return loadCM(src, offset, stride); }

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
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 load(int index, DoubleBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

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
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadRelative(DoubleBuffer src, int stride) { return loadCMRelative(src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at the given absolute index (the position is not used or modified),
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
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 load(int index, FloatBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

    /**
     * Load the elements from the given buffer in column-major order, converting each element from
     * {@code float}, starting at its current position and advancing the position accordingly, with
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
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the buffer than the position
     *        advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadRelative(FloatBuffer src, int stride) { return loadCMRelative(src, stride); }

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
     * @return a new {@code Double3x3} holding the loaded elements
     */
    public static Double3x3 load(int index, ByteBuffer src, int stride) { return StoreLoad.BB_OPS.loadCMAbsolute(index, src, stride); }

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
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws java.nio.BufferUnderflowException if less remains in the byte buffer than the
     *        position advances over; nothing is loaded and the position is unchanged
     */
    public static Double3x3 loadRelative(ByteBuffer src, int stride) { return loadCMRelative(src, stride); }

    /**
     * Load the elements from the given raw memory address in column-major order, with
     * {@code stride} elements between the starts of consecutive columns. No bounds or liveness
     * checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @param stride the number of elements between the starts of consecutive columns
     * @return a new {@code Double3x3} holding the loaded elements
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public static Double3x3 loadUnsafe(long address, int stride) { return StoreLoad.RAW_OPS.loadCMUnsafe(address, stride); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public double[] store4x4(double[] dest) { return storeCM4x4(dest, 0); }

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
    public double[] store4x4(double[] dest, int offset) { return storeCM4x4(dest, offset); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at its current position (the position is not modified).
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
    public DoubleBuffer store4x4(DoubleBuffer dest) { return StoreLoad.BB_OPS.storeCM4x4Absolute(this, dest.position(), dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at the given absolute index (the position is not used or modified).
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
    public DoubleBuffer store4x4(int index, DoubleBuffer dest) { return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, starting at its current position and advancing the position accordingly.
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
    public DoubleBuffer store4x4Relative(DoubleBuffer dest) { return storeCM4x4Relative(dest); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param dest the destination array
     * @return dest
     */
    public float[] store4x4(float[] dest) { return storeCM4x4(dest, 0); }

    /**
     * Store the elements into the given array in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at the given offset.
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
     * matrix, converting each element to {@code float}, starting at its current position (the
     * position is not modified).
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
    public FloatBuffer store4x4(FloatBuffer dest) { return StoreLoad.BB_OPS.storeCM4x4Absolute(this, dest.position(), dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at the given absolute index (the
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
     * @param index the absolute element index in the buffer
     * @param dest the destination buffer
     * @return dest
     */
    public FloatBuffer store4x4(int index, FloatBuffer dest) { return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, dest); }

    /**
     * Store the elements into the given buffer in column-major order, identity-extended to a 4x4
     * matrix, converting each element to {@code float}, starting at its current position and
     * advancing the position accordingly.
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
    public FloatBuffer store4x4Relative(FloatBuffer dest) { return storeCM4x4Relative(dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, starting at its current position (the position is not modified).
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
    public ByteBuffer store4x4(ByteBuffer dest) { return StoreLoad.BB_OPS.storeCM4x4Absolute(this, dest.position(), dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, starting at the given absolute index (the position is not used or modified).
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
    public ByteBuffer store4x4(int index, ByteBuffer dest) { return StoreLoad.BB_OPS.storeCM4x4Absolute(this, index, dest); }

    /**
     * Store the elements into the given byte buffer in column-major order, identity-extended to a
     * 4x4 matrix, starting at its current position and advancing the position accordingly.
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
    public ByteBuffer store4x4Relative(ByteBuffer dest) { return storeCM4x4Relative(dest); }

    /**
     * Store the elements into the given raw memory address in column-major order, identity-extended
     * to a 4x4 matrix. No bounds or liveness checks are performed.
     * <p>
     * Valid input: any value, NaN and the infinities included.
     *
     * @param address the raw memory address
     * @return this
     * @throws UnsupportedOperationException if the API store/load backend is active (JDK 9 / JDK 17
     *        variants only)
     */
    public Double3x3 store4x4Unsafe(long address) { return StoreLoad.RAW_OPS.storeCM4x4Unsafe(this, address); }

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
